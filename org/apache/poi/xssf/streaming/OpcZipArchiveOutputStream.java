/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.xssf.streaming.OpcOutputStream;

class OpcZipArchiveOutputStream
extends ZipArchiveOutputStream {
    private final OpcOutputStream out;

    OpcZipArchiveOutputStream(OutputStream out) {
        super(out);
        this.out = new OpcOutputStream(out);
    }

    @Override
    public void setLevel(int level) {
        this.out.setLevel(level);
    }

    @Override
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        this.out.putNextEntry(archiveEntry.getName());
    }

    @Override
    public void closeArchiveEntry() throws IOException {
        this.out.closeEntry();
    }

    @Override
    public void finish() throws IOException {
        this.out.finish();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.out.write(b);
    }
}

