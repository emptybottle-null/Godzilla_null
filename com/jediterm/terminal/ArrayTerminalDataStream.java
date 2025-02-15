/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.TerminalDataStream;
import com.jediterm.terminal.util.CharUtils;
import java.io.IOException;

public class ArrayTerminalDataStream
implements TerminalDataStream {
    protected char[] myBuf;
    protected int myOffset;
    protected int myLength;

    public ArrayTerminalDataStream(char[] buf, int offset, int length) {
        this.myBuf = buf;
        this.myOffset = offset;
        this.myLength = length;
    }

    public ArrayTerminalDataStream(char[] buf) {
        this(buf, 0, buf.length);
    }

    @Override
    public char getChar() throws IOException {
        if (this.myLength == 0) {
            throw new TerminalDataStream.EOF();
        }
        --this.myLength;
        return this.myBuf[this.myOffset++];
    }

    @Override
    public void pushChar(char c) throws TerminalDataStream.EOF {
        if (this.myOffset == 0) {
            char[] newBuf = this.myBuf.length - this.myLength == 0 ? new char[this.myBuf.length + 1] : this.myBuf;
            this.myOffset = newBuf.length - this.myLength;
            System.arraycopy(this.myBuf, 0, newBuf, this.myOffset, this.myLength);
            this.myBuf = newBuf;
        }
        ++this.myLength;
        this.myBuf[--this.myOffset] = c;
    }

    @Override
    public String readNonControlCharacters(int maxChars) throws IOException {
        String nonControlCharacters = CharUtils.getNonControlCharacters(maxChars, this.myBuf, this.myOffset, this.myLength);
        this.myOffset += nonControlCharacters.length();
        this.myLength -= nonControlCharacters.length();
        return nonControlCharacters;
    }

    @Override
    public void pushBackBuffer(char[] bytes, int length) throws TerminalDataStream.EOF {
        for (int i = length - 1; i >= 0; --i) {
            this.pushChar(bytes[i]);
        }
    }

    @Override
    public boolean isEmpty() {
        return this.myLength == 0;
    }
}

