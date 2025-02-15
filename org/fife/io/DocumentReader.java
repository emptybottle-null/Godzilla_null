/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.io;

import java.io.Reader;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;

public class DocumentReader
extends Reader {
    private long position = 0L;
    private long mark = -1L;
    private Document document;
    private Segment segment;

    public DocumentReader(Document document) {
        this.document = document;
        this.segment = new Segment();
    }

    @Override
    public void close() {
    }

    @Override
    public void mark(int readAheadLimit) {
        this.mark = this.position;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public int read() {
        if (this.position >= (long)this.document.getLength()) {
            return -1;
        }
        try {
            this.document.getText((int)this.position, 1, this.segment);
            ++this.position;
            return this.segment.array[this.segment.offset];
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            return -1;
        }
    }

    @Override
    public int read(char[] array) {
        return this.read(array, 0, array.length);
    }

    @Override
    public int read(char[] cbuf, int off, int len) {
        if (this.position >= (long)this.document.getLength()) {
            return -1;
        }
        int k = len;
        if (this.position + (long)k >= (long)this.document.getLength()) {
            k = this.document.getLength() - (int)this.position;
        }
        if (off + k >= cbuf.length) {
            k = cbuf.length - off;
        }
        try {
            this.document.getText((int)this.position, k, this.segment);
            this.position += (long)k;
            System.arraycopy(this.segment.array, this.segment.offset, cbuf, off, k);
            return k;
        } catch (BadLocationException ble) {
            return -1;
        }
    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public void reset() {
        if (this.mark == -1L) {
            this.position = 0L;
        } else {
            this.position = this.mark;
            this.mark = -1L;
        }
    }

    @Override
    public long skip(long n) {
        if (this.position + n <= (long)this.document.getLength()) {
            this.position += n;
            return n;
        }
        long temp = this.position;
        this.position = this.document.getLength();
        return (long)this.document.getLength() - temp;
    }

    public void seek(long pos) {
        this.position = Math.min(pos, (long)this.document.getLength());
    }
}

