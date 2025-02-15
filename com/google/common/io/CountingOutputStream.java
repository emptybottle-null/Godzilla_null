/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Beta
@GwtIncompatible
public final class CountingOutputStream
extends FilterOutputStream {
    private long count;

    public CountingOutputStream(OutputStream out) {
        super(Preconditions.checkNotNull(out));
    }

    public long getCount() {
        return this.count;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.count += (long)len;
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
        ++this.count;
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}

