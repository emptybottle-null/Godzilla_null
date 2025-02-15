/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.DSTU7564Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class DSTU7564Mac
implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private DSTU7564Digest engine;
    private int macSize;
    private byte[] paddedKey;
    private byte[] invertedKey;
    private long inputLength;

    public DSTU7564Mac(int n) {
        this.engine = new DSTU7564Digest(n);
        this.macSize = n / 8;
        this.paddedKey = null;
        this.invertedKey = null;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            byte[] byArray = ((KeyParameter)cipherParameters).getKey();
            this.invertedKey = new byte[byArray.length];
            this.paddedKey = this.padKey(byArray);
            for (int i = 0; i < this.invertedKey.length; ++i) {
                this.invertedKey[i] = ~byArray[i];
            }
        } else {
            throw new IllegalArgumentException("Bad parameter passed");
        }
        this.engine.update(this.paddedKey, 0, this.paddedKey.length);
    }

    public String getAlgorithmName() {
        return "DSTU7564Mac";
    }

    public int getMacSize() {
        return this.macSize;
    }

    public void update(byte by) throws IllegalStateException {
        this.engine.update(by);
        ++this.inputLength;
    }

    public void update(byte[] byArray, int n, int n2) throws DataLengthException, IllegalStateException {
        if (byArray.length - n < n2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (this.paddedKey == null) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        this.engine.update(byArray, n, n2);
        this.inputLength += (long)n2;
    }

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        if (this.paddedKey == null) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (byArray.length - n < this.macSize) {
            throw new OutputLengthException("Output buffer too short");
        }
        this.pad();
        this.engine.update(this.invertedKey, 0, this.invertedKey.length);
        this.inputLength = 0L;
        return this.engine.doFinal(byArray, n);
    }

    public void reset() {
        this.inputLength = 0L;
        this.engine.reset();
        if (this.paddedKey != null) {
            this.engine.update(this.paddedKey, 0, this.paddedKey.length);
        }
    }

    private void pad() {
        int n = this.engine.getByteLength() - (int)(this.inputLength % (long)this.engine.getByteLength());
        if (n < 13) {
            n += this.engine.getByteLength();
        }
        byte[] byArray = new byte[n];
        byArray[0] = -128;
        Pack.longToLittleEndian(this.inputLength * 8L, byArray, byArray.length - 12);
        this.engine.update(byArray, 0, byArray.length);
    }

    private byte[] padKey(byte[] byArray) {
        int n = (byArray.length + this.engine.getByteLength() - 1) / this.engine.getByteLength() * this.engine.getByteLength();
        int n2 = this.engine.getByteLength() - byArray.length % this.engine.getByteLength();
        if (n2 < 13) {
            n += this.engine.getByteLength();
        }
        byte[] byArray2 = new byte[n];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        byArray2[byArray.length] = -128;
        Pack.intToLittleEndian(byArray.length * 8, byArray2, byArray2.length - 12);
        return byArray2;
    }
}

