/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.AbstractCharStream;
import com.github.javaparser.Provider;
import java.io.IOException;

public class SimpleCharStream
extends AbstractCharStream {
    private Provider inputStream;

    @Override
    protected int streamRead(char[] aBuf, int nOfs, int nLen) throws IOException {
        return this.inputStream.read(aBuf, nOfs, nLen);
    }

    @Override
    protected void streamClose() throws IOException {
        this.inputStream.close();
    }

    public SimpleCharStream(Provider dstream, int startline, int startcolumn, int buffersize) {
        super(startline, startcolumn, buffersize);
        this.inputStream = dstream;
    }

    public SimpleCharStream(Provider dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public SimpleCharStream(Provider dstream) {
        this(dstream, 1, 1, 4096);
    }

    public void reInit(Provider dstream, int startline, int startcolumn, int buffersize) {
        this.inputStream = dstream;
        super.reInit(startline, startcolumn, buffersize);
    }

    public void reInit(Provider dstream, int startline, int startcolumn) {
        this.reInit(dstream, startline, startcolumn, 4096);
    }

    public void reInit(Provider dstream) {
        this.reInit(dstream, 1, 1, 4096);
    }
}

