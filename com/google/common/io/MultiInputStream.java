/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
final class MultiInputStream
extends InputStream {
    private Iterator<? extends ByteSource> it;
    private @Nullable InputStream in;

    public MultiInputStream(Iterator<? extends ByteSource> it) throws IOException {
        this.it = Preconditions.checkNotNull(it);
        this.advance();
    }

    @Override
    public void close() throws IOException {
        if (this.in != null) {
            try {
                this.in.close();
            } finally {
                this.in = null;
            }
        }
    }

    private void advance() throws IOException {
        this.close();
        if (this.it.hasNext()) {
            this.in = this.it.next().openStream();
        }
    }

    @Override
    public int available() throws IOException {
        if (this.in == null) {
            return 0;
        }
        return this.in.available();
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read() throws IOException {
        while (this.in != null) {
            int result = this.in.read();
            if (result != -1) {
                return result;
            }
            this.advance();
        }
        return -1;
    }

    @Override
    public int read(byte @Nullable [] b, int off, int len) throws IOException {
        while (this.in != null) {
            int result = this.in.read(b, off, len);
            if (result != -1) {
                return result;
            }
            this.advance();
        }
        return -1;
    }

    @Override
    public long skip(long n) throws IOException {
        if (this.in == null || n <= 0L) {
            return 0L;
        }
        long result = this.in.skip(n);
        if (result != 0L) {
            return result;
        }
        if (this.read() == -1) {
            return 0L;
        }
        return 1L + this.in.skip(n - 1L);
    }
}

