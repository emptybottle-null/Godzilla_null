/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.CRC32;
import org.apache.poi.EmptyFileException;
import org.apache.poi.POIDocument;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.BoundedInputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class IOUtils {
    private static final POILogger logger = POILogFactory.getLogger(IOUtils.class);
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static byte[] SKIP_BYTE_BUFFER;
    private static int BYTE_ARRAY_MAX_OVERRIDE;

    private IOUtils() {
    }

    public static void setByteArrayMaxOverride(int maxOverride) {
        BYTE_ARRAY_MAX_OVERRIDE = maxOverride;
    }

    public static byte[] peekFirst8Bytes(InputStream stream) throws IOException, EmptyFileException {
        return IOUtils.peekFirstNBytes(stream, 8);
    }

    private static void checkByteSizeLimit(int length) {
        if (BYTE_ARRAY_MAX_OVERRIDE != -1 && length > BYTE_ARRAY_MAX_OVERRIDE) {
            IOUtils.throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
        }
    }

    public static byte[] peekFirstNBytes(InputStream stream, int limit) throws IOException, EmptyFileException {
        IOUtils.checkByteSizeLimit(limit);
        stream.mark(limit);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(limit);
        IOUtils.copy((InputStream)new BoundedInputStream(stream, limit), bos);
        int readBytes = bos.size();
        if (readBytes == 0) {
            throw new EmptyFileException();
        }
        if (readBytes < limit) {
            bos.write(new byte[limit - readBytes]);
        }
        byte[] peekedBytes = bos.toByteArray();
        if (stream instanceof PushbackInputStream) {
            PushbackInputStream pin = (PushbackInputStream)stream;
            pin.unread(peekedBytes, 0, readBytes);
        } else {
            stream.reset();
        }
        return peekedBytes;
    }

    public static byte[] toByteArray(InputStream stream) throws IOException {
        return IOUtils.toByteArray(stream, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream stream, int length) throws IOException {
        return IOUtils.toByteArray(stream, length, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream stream, long length, int maxLength) throws IOException {
        int readBytes;
        int len;
        if (length < 0L || (long)maxLength < 0L) {
            throw new RecordFormatException("Can't allocate an array of length < 0");
        }
        if (length > Integer.MAX_VALUE) {
            throw new RecordFormatException("Can't allocate an array > 2147483647");
        }
        if (length != Integer.MAX_VALUE || maxLength != Integer.MAX_VALUE) {
            IOUtils.checkLength(length, maxLength);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream((len = Math.min((int)length, maxLength)) == Integer.MAX_VALUE ? 4096 : len);
        byte[] buffer = new byte[4096];
        int totalBytes = 0;
        do {
            readBytes = stream.read(buffer, 0, Math.min(buffer.length, len - totalBytes));
            totalBytes += Math.max(readBytes, 0);
            if (readBytes > 0) {
                baos.write(buffer, 0, readBytes);
            }
            IOUtils.checkByteSizeLimit(readBytes);
        } while (totalBytes < len && readBytes > -1);
        if (maxLength != Integer.MAX_VALUE && totalBytes == maxLength) {
            throw new IOException("MaxLength (" + maxLength + ") reached - stream seems to be invalid.");
        }
        if (len != Integer.MAX_VALUE && totalBytes < len) {
            throw new EOFException("unexpected EOF - expected len: " + len + " - actual len: " + totalBytes);
        }
        return baos.toByteArray();
    }

    private static void checkLength(long length, int maxLength) {
        if (BYTE_ARRAY_MAX_OVERRIDE > 0) {
            if (length > (long)BYTE_ARRAY_MAX_OVERRIDE) {
                IOUtils.throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
            }
        } else if (length > (long)maxLength) {
            IOUtils.throwRFE(length, maxLength);
        }
    }

    public static byte[] toByteArray(ByteBuffer buffer, int length) {
        if (buffer.hasArray() && buffer.arrayOffset() == 0) {
            return buffer.array();
        }
        IOUtils.checkByteSizeLimit(length);
        byte[] data = new byte[length];
        buffer.get(data);
        return data;
    }

    public static int readFully(InputStream in, byte[] b) throws IOException {
        return IOUtils.readFully(in, b, 0, b.length);
    }

    public static int readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int got;
        int total = 0;
        do {
            if ((got = in.read(b, off + total, len - total)) >= 0) continue;
            return total == 0 ? -1 : total;
        } while ((total += got) != len);
        return total;
    }

    public static int readFully(ReadableByteChannel channel, ByteBuffer b) throws IOException {
        int got;
        int total = 0;
        do {
            if ((got = channel.read(b)) >= 0) continue;
            return total == 0 ? -1 : total;
        } while ((total += got) != b.capacity() && b.position() != b.capacity());
        return total;
    }

    @Deprecated
    @Removal(version="4.2")
    public static void write(POIDocument doc, OutputStream out) throws IOException {
        try {
            doc.write(out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Deprecated
    @Removal(version="4.2")
    public static void write(Workbook doc, OutputStream out) throws IOException {
        try {
            doc.write(out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Deprecated
    @Removal(version="4.2")
    public static void writeAndClose(POIDocument doc, OutputStream out) throws IOException {
        try {
            IOUtils.write(doc, out);
        } finally {
            IOUtils.closeQuietly(doc);
        }
    }

    @Deprecated
    @Removal(version="4.2")
    public static void writeAndClose(POIDocument doc, File out) throws IOException {
        try {
            doc.write(out);
        } finally {
            IOUtils.closeQuietly(doc);
        }
    }

    @Deprecated
    @Removal(version="4.2")
    public static void writeAndClose(POIDocument doc) throws IOException {
        try {
            doc.write();
        } finally {
            IOUtils.closeQuietly(doc);
        }
    }

    @Deprecated
    @Removal(version="4.2")
    public static void writeAndClose(Workbook doc, OutputStream out) throws IOException {
        try {
            doc.write(out);
        } finally {
            IOUtils.closeQuietly(doc);
        }
    }

    public static long copy(InputStream inp, OutputStream out) throws IOException {
        return IOUtils.copy(inp, out, -1L);
    }

    public static long copy(InputStream inp, OutputStream out, long limit) throws IOException {
        byte[] buff = new byte[4096];
        long totalCount = 0L;
        int readBytes = -1;
        do {
            int todoBytes;
            if ((todoBytes = (int)(limit < 0L ? (long)buff.length : Math.min(limit - totalCount, (long)buff.length))) <= 0 || (readBytes = inp.read(buff, 0, todoBytes)) <= 0) continue;
            out.write(buff, 0, readBytes);
            totalCount += (long)readBytes;
        } while (readBytes >= 0 && (limit == -1L || totalCount < limit));
        return totalCount;
    }

    public static long copy(InputStream srcStream, File destFile) throws IOException {
        File destDirectory = destFile.getParentFile();
        if (!destDirectory.exists() && !destDirectory.mkdirs()) {
            throw new RuntimeException("Can't create destination directory: " + destDirectory);
        }
        try (FileOutputStream destStream = new FileOutputStream(destFile);){
            long l = IOUtils.copy(srcStream, destStream);
            return l;
        }
    }

    public static long calculateChecksum(byte[] data) {
        CRC32 sum = new CRC32();
        sum.update(data, 0, data.length);
        return sum.getValue();
    }

    public static long calculateChecksum(InputStream stream) throws IOException {
        int count;
        CRC32 sum = new CRC32();
        byte[] buf = new byte[4096];
        while ((count = stream.read(buf)) != -1) {
            if (count <= 0) continue;
            sum.update(buf, 0, count);
        }
        return sum.getValue();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception exc) {
            logger.log(7, "Unable to close resource: " + exc, exc);
        }
    }

    public static long skipFully(InputStream input, long toSkip) throws IOException {
        long remain;
        long n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (toSkip == 0L) {
            return 0L;
        }
        if (SKIP_BYTE_BUFFER == null) {
            SKIP_BYTE_BUFFER = new byte[2048];
        }
        for (remain = toSkip; remain > 0L && (n = (long)input.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L))) >= 0L; remain -= n) {
        }
        if (toSkip == remain) {
            return -1L;
        }
        return toSkip - remain;
    }

    public static byte[] safelyAllocate(long length, int maxLength) {
        IOUtils.safelyAllocateCheck(length, maxLength);
        IOUtils.checkByteSizeLimit((int)length);
        return new byte[(int)length];
    }

    public static void safelyAllocateCheck(long length, int maxLength) {
        if (length < 0L) {
            throw new RecordFormatException("Can't allocate an array of length < 0, but had " + length + " and " + maxLength);
        }
        if (length > Integer.MAX_VALUE) {
            throw new RecordFormatException("Can't allocate an array > 2147483647");
        }
        IOUtils.checkLength(length, maxLength);
    }

    public static int readByte(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        }
        return b;
    }

    private static void throwRFE(long length, int maxLength) {
        throw new RecordFormatException("Tried to allocate an array of length " + length + ", but " + maxLength + " is the maximum for this record type.\nIf the file is not corrupt, please open an issue on bugzilla to request \nincreasing the maximum allowable size for this record type.\nAs a temporary workaround, consider setting a higher override value with IOUtils.setByteArrayMaxOverride()");
    }

    static {
        BYTE_ARRAY_MAX_OVERRIDE = -1;
    }
}

