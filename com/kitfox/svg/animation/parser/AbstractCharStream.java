/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import com.kitfox.svg.animation.parser.CharStream;
import java.io.IOException;

public abstract class AbstractCharStream
implements CharStream {
    public static final int DEFAULT_BUF_SIZE = 4096;
    protected int bufpos = -1;
    protected int bufsize;
    protected int available;
    protected int tokenBegin;
    protected int[] bufline;
    protected int[] bufcolumn;
    protected int column = 0;
    protected int line = 1;
    protected boolean prevCharIsCR = false;
    protected boolean prevCharIsLF = false;
    protected char[] buffer;
    protected int maxNextCharInd = 0;
    protected int inBuf = 0;
    private int tabSize = 1;
    protected char[] nextCharBuf;
    protected int nextCharInd = -1;
    private boolean trackLineColumn = true;

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
        throw new IOException("Invalid hex char '" + c + "' provided!");
    }

    @Override
    public void setTabSize(int i) {
        this.tabSize = i;
    }

    @Override
    public int getTabSize() {
        return this.tabSize;
    }

    protected void expandBuff(boolean wrapAround) {
        char[] newbuffer = new char[this.bufsize + 2048];
        int[] newbufline = new int[this.bufsize + 2048];
        int[] newbufcolumn = new int[this.bufsize + 2048];
        try {
            if (wrapAround) {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin, this.bufpos);
                this.buffer = newbuffer;
                System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufline = newbufline;
                System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufcolumn = newbufcolumn;
                this.bufpos += this.bufsize - this.tokenBegin;
                this.maxNextCharInd = this.bufpos;
            } else {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
                this.buffer = newbuffer;
                System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
                this.bufline = newbufline;
                System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
                this.bufcolumn = newbufcolumn;
                this.bufpos -= this.tokenBegin;
                this.maxNextCharInd = this.bufpos;
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        this.bufsize += 2048;
        this.available = this.bufsize;
        this.tokenBegin = 0;
    }

    protected abstract int streamRead(char[] var1, int var2, int var3) throws IOException;

    protected abstract void streamClose() throws IOException;

    protected void fillBuff() throws IOException {
        if (this.maxNextCharInd == this.available) {
            if (this.available == this.bufsize) {
                if (this.tokenBegin > 2048) {
                    this.maxNextCharInd = 0;
                    this.bufpos = 0;
                    this.available = this.tokenBegin;
                } else if (this.tokenBegin < 0) {
                    this.maxNextCharInd = 0;
                    this.bufpos = 0;
                } else {
                    this.expandBuff(false);
                }
            } else if (this.available > this.tokenBegin) {
                this.available = this.bufsize;
            } else if (this.tokenBegin - this.available < 2048) {
                this.expandBuff(true);
            } else {
                this.available = this.tokenBegin;
            }
        }
        try {
            int i = this.streamRead(this.buffer, this.maxNextCharInd, this.available - this.maxNextCharInd);
            if (i == -1) {
                this.streamClose();
                throw new IOException();
            }
            this.maxNextCharInd += i;
            return;
        } catch (IOException e) {
            --this.bufpos;
            this.backup(0);
            if (this.tokenBegin == -1) {
                this.tokenBegin = this.bufpos;
            }
            throw e;
        }
    }

    @Override
    public char beginToken() throws IOException {
        this.tokenBegin = -1;
        char c = this.readChar();
        this.tokenBegin = this.bufpos;
        return c;
    }

    protected void updateLineColumn(char c) {
        ++this.column;
        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            this.column = 1;
            ++this.line;
        } else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == '\n') {
                this.prevCharIsLF = true;
            } else {
                this.column = 1;
                ++this.line;
            }
        }
        switch (c) {
            case '\r': {
                this.prevCharIsCR = true;
                break;
            }
            case '\n': {
                this.prevCharIsLF = true;
                break;
            }
            case '\t': {
                --this.column;
                this.column += this.tabSize - this.column % this.tabSize;
                break;
            }
        }
        this.bufline[this.bufpos] = this.line;
        this.bufcolumn[this.bufpos] = this.column;
    }

    @Override
    public char readChar() throws IOException {
        if (this.inBuf > 0) {
            --this.inBuf;
            if (++this.bufpos == this.bufsize) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        ++this.bufpos;
        if (this.bufpos >= this.maxNextCharInd) {
            this.fillBuff();
        }
        char c = this.buffer[this.bufpos];
        if (this.trackLineColumn) {
            this.updateLineColumn(c);
        }
        return c;
    }

    @Override
    public int getBeginColumn() {
        return this.bufcolumn[this.tokenBegin];
    }

    @Override
    public int getBeginLine() {
        return this.bufline[this.tokenBegin];
    }

    @Override
    public int getEndColumn() {
        return this.bufcolumn[this.bufpos];
    }

    @Override
    public int getEndLine() {
        return this.bufline[this.bufpos];
    }

    @Override
    public void backup(int amount) {
        this.inBuf += amount;
        this.bufpos -= amount;
        if (this.bufpos < 0) {
            this.bufpos += this.bufsize;
        }
    }

    public AbstractCharStream(int startline, int startcolumn, int buffersize) {
        this.line = startline;
        this.column = startcolumn - 1;
        this.bufsize = buffersize;
        this.available = buffersize;
        this.buffer = new char[buffersize];
        this.bufline = new int[buffersize];
        this.bufcolumn = new int[buffersize];
        this.nextCharBuf = new char[4096];
    }

    public void reInit(int startline, int startcolumn, int buffersize) {
        this.line = startline;
        this.column = startcolumn - 1;
        if (this.buffer == null || buffersize != this.buffer.length) {
            this.bufsize = buffersize;
            this.available = buffersize;
            this.buffer = new char[buffersize];
            this.bufline = new int[buffersize];
            this.bufcolumn = new int[buffersize];
            this.nextCharBuf = new char[4096];
        }
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
        this.nextCharInd = -1;
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
            System.arraycopy(this.buffer, this.bufsize - (len - this.bufpos - 1), ret, 0, len - this.bufpos - 1);
            System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
        }
        return ret;
    }

    @Override
    public void done() {
        this.nextCharBuf = null;
        this.buffer = null;
        this.bufline = null;
        this.bufcolumn = null;
    }

    public void adjustBeginLineColumn(int nNewLine, int newCol) {
        int i;
        int start = this.tokenBegin;
        int newLine = nNewLine;
        int len = this.bufpos >= this.tokenBegin ? this.bufpos - this.tokenBegin + this.inBuf + 1 : this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
        int j = 0;
        int k = 0;
        int nextColDiff = 0;
        int columnDiff = 0;
        for (i = 0; i < len && this.bufline[j = start % this.bufsize] == this.bufline[k = ++start % this.bufsize]; ++i) {
            this.bufline[j] = newLine;
            nextColDiff = columnDiff + this.bufcolumn[k] - this.bufcolumn[j];
            this.bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
        }
        if (i < len) {
            this.bufline[j] = newLine++;
            this.bufcolumn[j] = newCol + columnDiff;
            while (i++ < len) {
                j = start % this.bufsize;
                if (this.bufline[j] != this.bufline[++start % this.bufsize]) {
                    this.bufline[j] = newLine++;
                    continue;
                }
                this.bufline[j] = newLine;
            }
        }
        this.line = this.bufline[j];
        this.column = this.bufcolumn[j];
    }

    @Override
    public void setTrackLineColumn(boolean tlc) {
        this.trackLineColumn = tlc;
    }

    @Override
    public boolean isTrackLineColumn() {
        return this.trackLineColumn;
    }
}

