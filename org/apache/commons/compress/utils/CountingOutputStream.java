/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream
extends FilterOutputStream {
    private long bytesWritten = 0L;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
        this.count(1L);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.count(len);
    }

    protected void count(long written) {
        if (written != -1L) {
            this.bytesWritten += written;
        }
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }
}

