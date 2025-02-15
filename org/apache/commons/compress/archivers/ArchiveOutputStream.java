/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

public abstract class ArchiveOutputStream
extends OutputStream {
    private final byte[] oneByte = new byte[1];
    static final int BYTE_MASK = 255;
    private long bytesWritten = 0L;

    public abstract void putArchiveEntry(ArchiveEntry var1) throws IOException;

    public abstract void closeArchiveEntry() throws IOException;

    public abstract void finish() throws IOException;

    public abstract ArchiveEntry createArchiveEntry(File var1, String var2) throws IOException;

    @Override
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte)(b & 0xFF);
        this.write(this.oneByte, 0, 1);
    }

    protected void count(int written) {
        this.count((long)written);
    }

    protected void count(long written) {
        if (written != -1L) {
            this.bytesWritten += written;
        }
    }

    @Deprecated
    public int getCount() {
        return (int)this.bytesWritten;
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        return true;
    }
}

