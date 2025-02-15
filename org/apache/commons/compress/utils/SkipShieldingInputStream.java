/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SkipShieldingInputStream
extends FilterInputStream {
    private static final int SKIP_BUFFER_SIZE = 8192;
    private static final byte[] SKIP_BUFFER = new byte[8192];

    public SkipShieldingInputStream(InputStream in) {
        super(in);
    }

    @Override
    public long skip(long n) throws IOException {
        return n < 0L ? 0L : (long)this.read(SKIP_BUFFER, 0, (int)Math.min(n, 8192L));
    }
}

