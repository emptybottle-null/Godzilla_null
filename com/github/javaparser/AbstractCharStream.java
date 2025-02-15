/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.CharStream;
import java.io.IOException;

public abstract class AbstractCharStream
implements CharStream {
    public static final int DEFAULT_BUF_SIZE = 4096;
    private int m_nTabSize = 1;
    protected char[] buffer;
    protected int bufsize;
    protected int bufpos;
    protected int available;
    protected int tokenBegin;
    protected int inBuf;
    protected int maxNextCharInd;
    private int[] m_aBufLine;
    private int[] m_aBufColumn;
    private int m_nLineNo;
    private int m_nColumnNo;
    private boolean m_bPrevCharIsCR;
    private boolean m_bPrevCharIsLF;
    private boolean m_bTrackLineColumn = true;

    static final int hexval(char c) throws IOException {
        switch (c) {
            case '0': {
                return 0;
            }
            case '1': {
                return 1;
            }
            case '2': {
                return 2;
            }
            case '3': {
                return 3;
            }
            case '4': {
                return 4;
            }
            case '5': {
                return 5;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 7;
            }
            case '8': {
                return 8;
            }
            case '9': {
                return 9;
            }
            case 'A': 
            case 'a': {
                return 10;
            }
            case 'B': 
            case 'b': {
                return 11;
            }
            case 'C': 
            case 'c': {
                return 12;
            }
            case 'D': 
            case 'd': {
                return 13;
            }
            case 'E': 
            case 'e': {
                return 14;
            }
            case 'F': 
            case 'f': {
                return 15;
            }
        }
        throw new IOException("Invalid hex char '" + c + "' (=" + c + ") provided!");
    }

    public AbstractCharStream(int nStartLine, int nStartColumn, int nBufferSize) {
        this.reInit(nStartLine, nStartColumn, nBufferSize);
    }

    public final void reInit(int nStartLine, int nStartColumn, int nBufferSize) {
        this.m_nLineNo = nStartLine;
        this.m_nColumnNo = nStartColumn - 1;
        this.m_bPrevCharIsCR = false;
        this.m_bPrevCharIsLF = false;
        if (this.buffer == null || nBufferSize != this.buffer.length) {
            this.bufsize = nBufferSize;
            this.available = nBufferSize;
            this.buffer = new char[nBufferSize];
            this.m_aBufLine = new int[nBufferSize];
            this.m_aBufColumn = new int[nBufferSize];
        }
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
    }

    protected abstract int streamRead(char[] var1, int var2, int var3) throws IOException;

    protected abstract void streamClose() throws IOException;

    protected int getBufSizeAfterExpansion() {
        return this.bufsize * 2;
    }

    protected void expandBuff(boolean bWrapAround) {
        int nNewBufSize = this.getBufSizeAfterExpansion();
        char[] newbuffer = new char[nNewBufSize];
        int[] newbufline = new int[nNewBufSize];
        int[] newbufcolumn = new int[nNewBufSize];
        int nPreservedChars = this.bufsize - this.tokenBegin;
        if (bWrapAround) {
            System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, nPreservedChars);
            System.arraycopy(this.buffer, 0, newbuffer, nPreservedChars, this.bufpos);
            this.buffer = newbuffer;
            System.arraycopy(this.m_aBufLine, this.tokenBegin, newbufline, 0, nPreservedChars);
            System.arraycopy(this.m_aBufLine, 0, newbufline, nPreservedChars, this.bufpos);
            this.m_aBufLine = newbufline;
            System.arraycopy(this.m_aBufColumn, this.tokenBegin, newbufcolumn, 0, nPreservedChars);
            System.arraycopy(this.m_aBufColumn, 0, newbufcolumn, nPreservedChars, this.bufpos);
            this.m_aBufColumn = newbufcolumn;
            this.bufpos += nPreservedChars;
            this.maxNextCharInd = this.bufpos;
        } else {
            System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, nPreservedChars);
            this.buffer = newbuffer;
            System.arraycopy(this.m_aBufLine, this.tokenBegin, newbufline, 0, nPreservedChars);
            this.m_aBufLine = newbufline;
            System.arraycopy(this.m_aBufColumn, this.tokenBegin, newbufcolumn, 0, nPreservedChars);
            this.m_aBufColumn = newbufcolumn;
            this.bufpos -= this.tokenBegin;
            this.maxNextCharInd = this.bufpos;
        }
        this.bufsize = nNewBufSize;
        this.available = nNewBufSize;
        this.tokenBegin = 0;
    }

    protected final void internalAdjustBuffSize() {
        int nHalfBufferSize = this.bufsize / 2;
        if (this.available == this.bufsize) {
            if (this.tokenBegin < 0) {
                this.bufpos = 0;
                this.maxNextCharInd = 0;
            } else if (this.tokenBegin > nHalfBufferSize) {
                this.bufpos = 0;
                this.maxNextCharInd = 0;
                this.available = this.tokenBegin;
            } else {
                this.expandBuff(false);
            }
        } else if (this.available > this.tokenBegin) {
            this.available = this.bufsize;
        } else if (this.tokenBegin - this.available < nHalfBufferSize) {
            this.expandBuff(true);
        } else {
            this.available = this.tokenBegin;
        }
    }

    protected void fillBuff() throws IOException {
        if (this.maxNextCharInd == this.available) {
            this.internalAdjustBuffSize();
        }
        try {
            int nCharsRead = this.streamRead(this.buffer, this.maxNextCharInd, this.available - this.maxNextCharInd);
            if (nCharsRead == -1) {
                this.streamClose();
                throw new IOException("PGCC end of stream");
            }
            this.maxNextCharInd += nCharsRead;
        } catch (IOException ex) {
            --this.bufpos;
            this.backup(0);
            if (this.tokenBegin == -1) {
                this.tokenBegin = this.bufpos;
            }
            throw ex;
        }
    }

    protected final void internalSetBufLineColumn(int nLine, int nColumn) {
        this.m_aBufLine[this.bufpos] = nLine;
        this.m_aBufColumn[this.bufpos] = nColumn;
    }

    protected final void internalUpdateLineColumn(char c) {
        ++this.m_nColumnNo;
        if (this.m_bPrevCharIsLF) {
            this.m_bPrevCharIsLF = false;
            this.m_nColumnNo = 1;
            ++this.m_nLineNo;
        } else if (this.m_bPrevCharIsCR) {
            this.m_bPrevCharIsCR = false;
            if (c == '\n') {
                this.m_bPrevCharIsLF = true;
            } else {
                this.m_nColumnNo = 1;
                ++this.m_nLineNo;
            }
        }
        switch (c) {
            case '\r': {
                this.m_bPrevCharIsCR = true;
                break;
            }
            case '\n': {
                this.m_bPrevCharIsLF = true;
                break;
            }
            case '\t': {
                --this.m_nColumnNo;
                this.m_nColumnNo += this.m_nTabSize - this.m_nColumnNo % this.m_nTabSize;
            }
        }
        this.internalSetBufLineColumn(this.m_nLineNo, this.m_nColumnNo);
    }

    @Override
    public char readChar() throws IOException {
        if (this.inBuf > 0) {
            --this.inBuf;
            ++this.bufpos;
            if (this.bufpos == this.bufsize) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        ++this.bufpos;
        if (this.bufpos >= this.maxNextCharInd) {
            this.fillBuff();
        }
        char c = this.buffer[this.bufpos];
        if (this.m_bTrackLineColumn) {
            this.internalUpdateLineColumn(c);
        }
        return c;
    }

    @Override
    public char beginToken() throws IOException {
        this.tokenBegin = -1;
        char c = this.readChar();
        this.tokenBegin = this.bufpos;
        return c;
    }

    @Override
    public int getBeginColumn() {
        return this.m_aBufColumn[this.tokenBegin];
    }

    @Override
    public int getBeginLine() {
        return this.m_aBufLine[this.tokenBegin];
    }

    @Override
    public int getEndColumn() {
        return this.m_aBufColumn[this.bufpos];
    }

    @Override
    public int getEndLine() {
        return this.m_aBufLine[this.bufpos];
    }

    @Override
    public void backup(int nAmount) {
        if (nAmount > this.bufsize) {
            throw new IllegalStateException("Cannot back " + nAmount + " chars which is larger than the internal buffer size (" + this.bufsize + ")");
        }
        this.inBuf += nAmount;
        this.bufpos -= nAmount;
        if (this.bufpos < 0) {
            this.bufpos += this.bufsize;
        }
    }

    @Override
    public String getImage() {
        if (this.bufpos >= this.tokenBegin) {
            return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
        }
        return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
    }

    @Override
    public char[] getSuffix(int len) {
        char[] ret = new char[len];
        if (this.bufpos + 1 >= len) {
            System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
        } else {
            int nPart1 = len - this.bufpos - 1;
            System.arraycopy(this.buffer, this.bufsize - nPart1, ret, 0, nPart1);
            System.arraycopy(this.buffer, 0, ret, nPart1, this.bufpos + 1);
        }
        return ret;
    }

    @Override
    public void done() {
        this.buffer = null;
        this.m_aBufLine = null;
        this.m_aBufColumn = null;
    }

    @Override
    public final int getTabSize() {
        return this.m_nTabSize;
    }

    @Override
    public final void setTabSize(int nTabSize) {
        this.m_nTabSize = nTabSize;
    }

    public final void adjustBeginLineColumn(int nNewLine, int newCol) {
        int i;
        int start = this.tokenBegin;
        int newLine = nNewLine;
        int len = this.bufpos >= this.tokenBegin ? this.bufpos - this.tokenBegin + this.inBuf + 1 : this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
        int j = 0;
        int k = 0;
        int nextColDiff = 0;
        int columnDiff = 0;
        for (i = 0; i < len && this.m_aBufLine[j = start % this.bufsize] == this.m_aBufLine[k = ++start % this.bufsize]; ++i) {
            this.m_aBufLine[j] = newLine;
            nextColDiff = columnDiff + this.m_aBufColumn[k] - this.m_aBufColumn[j];
            this.m_aBufColumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
        }
        if (i < len) {
            this.m_aBufLine[j] = newLine++;
            this.m_aBufColumn[j] = newCol + columnDiff;
            while (i++ < len) {
                j = start % this.bufsize;
                if (this.m_aBufLine[j] != this.m_aBufLine[++start % this.bufsize]) {
                    this.m_aBufLine[j] = newLine++;
                    continue;
                }
                this.m_aBufLine[j] = newLine;
            }
        }
        this.m_nLineNo = this.m_aBufLine[j];
        this.m_nColumnNo = this.m_aBufColumn[j];
    }

    protected final int getLine() {
        return this.m_nLineNo;
    }

    protected final int getColumn() {
        return this.m_nColumnNo;
    }

    @Override
    public final boolean isTrackLineColumn() {
        return this.m_bTrackLineColumn;
    }

    @Override
    public final void setTrackLineColumn(boolean bTrackLineColumn) {
        this.m_bTrackLineColumn = bTrackLineColumn;
    }
}

