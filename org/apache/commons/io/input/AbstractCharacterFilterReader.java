/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.IntPredicate;

public abstract class AbstractCharacterFilterReader
extends FilterReader {
    protected static final IntPredicate SKIP_NONE = ch -> false;
    private final IntPredicate skip;

    protected AbstractCharacterFilterReader(Reader reader) {
        this(reader, SKIP_NONE);
    }

    protected AbstractCharacterFilterReader(Reader reader, IntPredicate skip) {
        super(reader);
        this.skip = skip == null ? SKIP_NONE : skip;
    }

    protected boolean filter(int ch) {
        return this.skip.test(ch);
    }

    @Override
    public int read() throws IOException {
        int ch;
        while ((ch = this.in.read()) != -1 && this.filter(ch)) {
        }
        return ch;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        if (read == -1) {
            return -1;
        }
        int pos = off - 1;
        for (int readPos = off; readPos < off + read; ++readPos) {
            if (this.filter(cbuf[readPos]) || ++pos >= readPos) continue;
            cbuf[pos] = cbuf[readPos];
        }
        return pos - off + 1;
    }
}

