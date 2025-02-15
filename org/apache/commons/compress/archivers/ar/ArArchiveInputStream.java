/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.ar;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class ArArchiveInputStream
extends ArchiveInputStream {
    private final InputStream input;
    private long offset = 0L;
    private boolean closed;
    private ArArchiveEntry currentEntry = null;
    private byte[] namebuffer = null;
    private long entryOffset = -1L;
    private static final int NAME_OFFSET = 0;
    private static final int NAME_LEN = 16;
    private static final int LAST_MODIFIED_OFFSET = 16;
    private static final int LAST_MODIFIED_LEN = 12;
    private static final int USER_ID_OFFSET = 28;
    private static final int USER_ID_LEN = 6;
    private static final int GROUP_ID_OFFSET = 34;
    private static final int GROUP_ID_LEN = 6;
    private static final int FILE_MODE_OFFSET = 40;
    private static final int FILE_MODE_LEN = 8;
    private static final int LENGTH_OFFSET = 48;
    private static final int LENGTH_LEN = 10;
    private final byte[] metaData = new byte[58];
    static final String BSD_LONGNAME_PREFIX = "#1/";
    private static final int BSD_LONGNAME_PREFIX_LEN = "#1/".length();
    private static final String BSD_LONGNAME_PATTERN = "^#1/\\d+";
    private static final String GNU_STRING_TABLE_NAME = "//";
    private static final String GNU_LONGNAME_PATTERN = "^/\\d+";

    public ArArchiveInputStream(InputStream pInput) {
        this.input = pInput;
        this.closed = false;
    }

    public ArArchiveEntry getNextArEntry() throws IOException {
        byte[] realized;
        if (this.currentEntry != null) {
            long entryEnd = this.entryOffset + this.currentEntry.getLength();
            long skipped = IOUtils.skip(this.input, entryEnd - this.offset);
            this.trackReadBytes(skipped);
            this.currentEntry = null;
        }
        if (this.offset == 0L) {
            byte[] expected = ArchiveUtils.toAsciiBytes("!<arch>\n");
            realized = new byte[expected.length];
            int read = IOUtils.readFully(this.input, realized);
            this.trackReadBytes(read);
            if (read != expected.length) {
                throw new IOException("Failed to read header. Occured at byte: " + this.getBytesRead());
            }
            if (!Arrays.equals(expected, realized)) {
                throw new IOException("Invalid header " + ArchiveUtils.toAsciiString(realized));
            }
        }
        if (this.offset % 2L != 0L) {
            if (this.input.read() < 0) {
                return null;
            }
            this.trackReadBytes(1L);
        }
        int read = IOUtils.readFully(this.input, this.metaData);
        this.trackReadBytes(read);
        if (read == 0) {
            return null;
        }
        if (read < this.metaData.length) {
            throw new IOException("Truncated ar archive");
        }
        byte[] expected = ArchiveUtils.toAsciiBytes("`\n");
        realized = new byte[expected.length];
        int read2 = IOUtils.readFully(this.input, realized);
        this.trackReadBytes(read2);
        if (read2 != expected.length) {
            throw new IOException("Failed to read entry trailer. Occured at byte: " + this.getBytesRead());
        }
        if (!Arrays.equals(expected, realized)) {
            throw new IOException("Invalid entry trailer. not read the content? Occured at byte: " + this.getBytesRead());
        }
        this.entryOffset = this.offset;
        String temp = ArchiveUtils.toAsciiString(this.metaData, 0, 16).trim();
        if (ArArchiveInputStream.isGNUStringTable(temp)) {
            this.currentEntry = this.readGNUStringTable(this.metaData, 48, 10);
            return this.getNextArEntry();
        }
        long len = this.asLong(this.metaData, 48, 10);
        if (temp.endsWith("/")) {
            temp = temp.substring(0, temp.length() - 1);
        } else if (this.isGNULongName(temp)) {
            int off = Integer.parseInt(temp.substring(1));
            temp = this.getExtendedName(off);
        } else if (ArArchiveInputStream.isBSDLongName(temp)) {
            temp = this.getBSDLongName(temp);
            int nameLen = temp.length();
            len -= (long)nameLen;
            this.entryOffset += (long)nameLen;
        }
        this.currentEntry = new ArArchiveEntry(temp, len, this.asInt(this.metaData, 28, 6, true), this.asInt(this.metaData, 34, 6, true), this.asInt(this.metaData, 40, 8, 8), this.asLong(this.metaData, 16, 12));
        return this.currentEntry;
    }

    private String getExtendedName(int offset) throws IOException {
        if (this.namebuffer == null) {
            throw new IOException("Cannot process GNU long filename as no // record was found");
        }
        for (int i = offset; i < this.namebuffer.length; ++i) {
            if (this.namebuffer[i] != 10 && this.namebuffer[i] != 0) continue;
            if (this.namebuffer[i - 1] == 47) {
                --i;
            }
            return ArchiveUtils.toAsciiString(this.namebuffer, offset, i - offset);
        }
        throw new IOException("Failed to read entry: " + offset);
    }

    private long asLong(byte[] byteArray, int offset, int len) {
        return Long.parseLong(ArchiveUtils.toAsciiString(byteArray, offset, len).trim());
    }

    private int asInt(byte[] byteArray, int offset, int len) {
        return this.asInt(byteArray, offset, len, 10, false);
    }

    private int asInt(byte[] byteArray, int offset, int len, boolean treatBlankAsZero) {
        return this.asInt(byteArray, offset, len, 10, treatBlankAsZero);
    }

    private int asInt(byte[] byteArray, int offset, int len, int base) {
        return this.asInt(byteArray, offset, len, base, false);
    }

    private int asInt(byte[] byteArray, int offset, int len, int base, boolean treatBlankAsZero) {
        String string = ArchiveUtils.toAsciiString(byteArray, offset, len).trim();
        if (string.length() == 0 && treatBlankAsZero) {
            return 0;
        }
        return Integer.parseInt(string, base);
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return this.getNextArEntry();
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.input.close();
        }
        this.currentEntry = null;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.currentEntry == null) {
            throw new IllegalStateException("No current ar entry");
        }
        long entryEnd = this.entryOffset + this.currentEntry.getLength();
        if (len < 0 || this.offset >= entryEnd) {
            return -1;
        }
        int toRead = (int)Math.min((long)len, entryEnd - this.offset);
        int ret = this.input.read(b, off, toRead);
        this.trackReadBytes(ret);
        return ret;
    }

    public static boolean matches(byte[] signature, int length) {
        return length >= 8 && signature[0] == 33 && signature[1] == 60 && signature[2] == 97 && signature[3] == 114 && signature[4] == 99 && signature[5] == 104 && signature[6] == 62 && signature[7] == 10;
    }

    private static boolean isBSDLongName(String name) {
        return name != null && name.matches(BSD_LONGNAME_PATTERN);
    }

    private String getBSDLongName(String bsdLongName) throws IOException {
        int nameLen = Integer.parseInt(bsdLongName.substring(BSD_LONGNAME_PREFIX_LEN));
        byte[] name = new byte[nameLen];
        int read = IOUtils.readFully(this.input, name);
        this.trackReadBytes(read);
        if (read != nameLen) {
            throw new EOFException();
        }
        return ArchiveUtils.toAsciiString(name);
    }

    private static boolean isGNUStringTable(String name) {
        return GNU_STRING_TABLE_NAME.equals(name);
    }

    private void trackReadBytes(long read) {
        this.count(read);
        if (read > 0L) {
            this.offset += read;
        }
    }

    private ArArchiveEntry readGNUStringTable(byte[] length, int offset, int len) throws IOException {
        int bufflen = this.asInt(length, offset, len);
        this.namebuffer = new byte[bufflen];
        int read = IOUtils.readFully(this.input, this.namebuffer, 0, bufflen);
        this.trackReadBytes(read);
        if (read != bufflen) {
            throw new IOException("Failed to read complete // record: expected=" + bufflen + " read=" + read);
        }
        return new ArArchiveEntry(GNU_STRING_TABLE_NAME, bufflen);
    }

    private boolean isGNULongName(String name) {
        return name != null && name.matches(GNU_LONGNAME_PATTERN);
    }
}

