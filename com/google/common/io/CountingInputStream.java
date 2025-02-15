/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
@GwtIncompatible
public final class CountingInputStream
extends FilterInputStream {
    private long count;
    private long mark = -1L;

    public CountingInputStream(InputStream in) {
        super(Preconditions.checkNotNull(in));
    }

    public long getCount() {
        return this.count;
    }

    @Override
    public int read() throws IOException {
        int result = this.in.read();
        if (result != -1) {
            ++this.count;
        }
        return result;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = this.in.read(b, off, len);
        if (result != -1) {
            this.count += (long)result;
        }
        return result;
    }

    @Override
    public long skip(long n) throws IOException {
        long result = this.in.skip(n);
        this.count += result;
        return result;
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
        this.mark = this.count;
    }

    @Override
    public synchronized void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (this.mark == -1L) {
            throw new IOException("Mark not set");
        }
        this.in.reset();
        this.count = this.mark;
    }
}

