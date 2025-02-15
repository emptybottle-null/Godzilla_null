/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors;

import java.io.InputStream;

public abstract class CompressorInputStream
extends InputStream {
    private long bytesRead = 0L;

    protected void count(int read) {
        this.count((long)read);
    }

    protected void count(long read) {
        if (read != -1L) {
            this.bytesRead += read;
        }
    }

    protected void pushedBackBytes(long pushedBack) {
        this.bytesRead -= pushedBack;
    }

    @Deprecated
    public int getCount() {
        return (int)this.bytesRead;
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public long getUncompressedCount() {
        return this.getBytesRead();
    }
}

