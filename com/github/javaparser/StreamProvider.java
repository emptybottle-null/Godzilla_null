/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Provider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class StreamProvider
implements Provider {
    private Reader m_aReader;

    @Deprecated
    public StreamProvider(InputStream stream, String charsetName) throws IOException {
        this(new BufferedReader(new InputStreamReader(stream, charsetName)));
    }

    public StreamProvider(InputStream stream, Charset charset) {
        this(new BufferedReader(new InputStreamReader(stream, charset)));
    }

    public StreamProvider(Reader reader) {
        this.m_aReader = reader;
    }

    @Override
    public int read(char[] aDest, int nOfs, int nLen) throws IOException {
        int result = this.m_aReader.read(aDest, nOfs, nLen);
        if (result == 0 && nOfs < aDest.length && nLen > 0) {
            result = -1;
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if (this.m_aReader != null) {
            this.m_aReader.close();
        }
    }
}

