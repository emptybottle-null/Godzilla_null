/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

public class FixedSecureRandom
extends SecureRandom {
    private byte[] _data;
    private int _index;
    private int _intPad;

    public FixedSecureRandom(byte[] byArray) {
        this(false, new byte[][]{byArray});
    }

    public FixedSecureRandom(byte[][] byArray) {
        this(false, byArray);
    }

    public FixedSecureRandom(boolean bl, byte[] byArray) {
        this(bl, new byte[][]{byArray});
    }

    public FixedSecureRandom(boolean bl, byte[][] byArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != byArray.length; ++i) {
            try {
                byteArrayOutputStream.write(byArray[i]);
                continue;
            } catch (IOException iOException) {
                throw new IllegalArgumentException("can't save value array.");
            }
        }
        this._data = byteArrayOutputStream.toByteArray();
        if (bl) {
            this._intPad = this._data.length % 4;
        }
    }

    public void nextBytes(byte[] byArray) {
        System.arraycopy(this._data, this._index, byArray, 0, byArray.length);
        this._index += byArray.length;
    }

    public byte[] generateSeed(int n) {
        byte[] byArray = new byte[n];
        this.nextBytes(byArray);
        return byArray;
    }

    public int nextInt() {
        int n = 0;
        n |= this.nextValue() << 24;
        n |= this.nextValue() << 16;
        if (this._intPad == 2) {
            --this._intPad;
        } else {
            n |= this.nextValue() << 8;
        }
        if (this._intPad == 1) {
            --this._intPad;
        } else {
            n |= this.nextValue();
        }
        return n;
    }

    public long nextLong() {
        long l = 0L;
        l |= (long)this.nextValue() << 56;
        l |= (long)this.nextValue() << 48;
        l |= (long)this.nextValue() << 40;
        l |= (long)this.nextValue() << 32;
        l |= (long)this.nextValue() << 24;
        l |= (long)this.nextValue() << 16;
        l |= (long)this.nextValue() << 8;
        return l |= (long)this.nextValue();
    }

    public boolean isExhausted() {
        return this._index == this._data.length;
    }

    private int nextValue() {
        return this._data[this._index++] & 0xFF;
    }
}

