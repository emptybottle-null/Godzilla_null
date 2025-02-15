/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Provider;
import java.io.IOException;

public class StringProvider
implements Provider {
    private String m_sStr;
    private int m_nPos = 0;
    private final int m_nLen;

    public StringProvider(String sStr) {
        this.m_sStr = sStr;
        this.m_nLen = sStr.length();
    }

    @Override
    public int read(char[] aDest, int nOfs, int nLen) throws IOException {
        int nLeft = this.m_nLen - this.m_nPos;
        if (nLeft <= 0) {
            return -1;
        }
        int nCharsRead = aDest.length - nOfs;
        if (nLen < nCharsRead) {
            nCharsRead = nLen;
        }
        if (nLeft < nCharsRead) {
            nCharsRead = nLeft;
        }
        this.m_sStr.getChars(this.m_nPos, this.m_nPos + nCharsRead, aDest, nOfs);
        this.m_nPos += nCharsRead;
        return nCharsRead;
    }

    @Override
    public void close() {
        this.m_sStr = null;
    }
}

