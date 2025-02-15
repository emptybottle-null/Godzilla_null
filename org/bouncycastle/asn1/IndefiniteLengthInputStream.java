/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.LimitedInputStream;

class IndefiniteLengthInputStream
extends LimitedInputStream {
    private int _b1;
    private int _b2;
    private boolean _eofReached = false;
    private boolean _eofOn00 = true;

    IndefiniteLengthInputStream(InputStream inputStream, int n) throws IOException {
        super(inputStream, n);
        this._b1 = inputStream.read();
        this._b2 = inputStream.read();
        if (this._b2 < 0) {
            throw new EOFException();
        }
        this.checkForEof();
    }

    void setEofOn00(boolean bl) {
        this._eofOn00 = bl;
        this.checkForEof();
    }

    private boolean checkForEof() {
        if (!this._eofReached && this._eofOn00 && this._b1 == 0 && this._b2 == 0) {
            this._eofReached = true;
            this.setParentEofDetect(true);
        }
        return this._eofReached;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        if (this._eofOn00 || n2 < 3) {
            return super.read(byArray, n, n2);
        }
        if (this._eofReached) {
            return -1;
        }
        int n3 = this._in.read(byArray, n + 2, n2 - 2);
        if (n3 < 0) {
            throw new EOFException();
        }
        byArray[n] = (byte)this._b1;
        byArray[n + 1] = (byte)this._b2;
        this._b1 = this._in.read();
        this._b2 = this._in.read();
        if (this._b2 < 0) {
            throw new EOFException();
        }
        return n3 + 2;
    }

    public int read() throws IOException {
        if (this.checkForEof()) {
            return -1;
        }
        int n = this._in.read();
        if (n < 0) {
            throw new EOFException();
        }
        int n2 = this._b1;
        this._b1 = this._b2;
        this._b2 = n;
        return n2;
    }
}

