/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import org.bouncycastle.util.encoders.Translator;

public class BufferedEncoder {
    protected byte[] buf;
    protected int bufOff;
    protected Translator translator;

    public BufferedEncoder(Translator translator, int n) {
        this.translator = translator;
        if (n % translator.getEncodedBlockSize() != 0) {
            throw new IllegalArgumentException("buffer size not multiple of input block size");
        }
        this.buf = new byte[n];
        this.bufOff = 0;
    }

    public int processByte(byte by, byte[] byArray, int n) {
        int n2 = 0;
        this.buf[this.bufOff++] = by;
        if (this.bufOff == this.buf.length) {
            n2 = this.translator.encode(this.buf, 0, this.buf.length, byArray, n);
            this.bufOff = 0;
        }
        return n2;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        if (n2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int n4 = 0;
        int n5 = this.buf.length - this.bufOff;
        if (n2 > n5) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n5);
            n4 += this.translator.encode(this.buf, 0, this.buf.length, byArray2, n3);
            this.bufOff = 0;
            n3 += n4;
            int n6 = (n2 -= n5) - n2 % this.buf.length;
            n4 += this.translator.encode(byArray, n += n5, n6, byArray2, n3);
            n2 -= n6;
            n += n6;
        }
        if (n2 != 0) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
            this.bufOff += n2;
        }
        return n4;
    }
}

