/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class NoekeonEngine
implements BlockCipher {
    private static final int genericSize = 16;
    private static final int[] nullVector = new int[]{0, 0, 0, 0};
    private static final int[] roundConstants = new int[]{128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212};
    private int[] state = new int[4];
    private int[] subKeys = new int[4];
    private int[] decryptKeys = new int[4];
    private boolean _initialised = false;
    private boolean _forEncryption;

    public String getAlgorithmName() {
        return "Noekeon";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
        }
        this._forEncryption = bl;
        this._initialised = true;
        KeyParameter keyParameter = (KeyParameter)cipherParameters;
        this.setKey(keyParameter.getKey());
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (!this._initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        return this._forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
    }

    private void setKey(byte[] byArray) {
        this.subKeys[0] = this.bytesToIntBig(byArray, 0);
        this.subKeys[1] = this.bytesToIntBig(byArray, 4);
        this.subKeys[2] = this.bytesToIntBig(byArray, 8);
        this.subKeys[3] = this.bytesToIntBig(byArray, 12);
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        this.state[0] = this.bytesToIntBig(byArray, n);
        this.state[1] = this.bytesToIntBig(byArray, n + 4);
        this.state[2] = this.bytesToIntBig(byArray, n + 8);
        this.state[3] = this.bytesToIntBig(byArray, n + 12);
        for (n3 = 0; n3 < 16; ++n3) {
            this.state[0] = this.state[0] ^ roundConstants[n3];
            this.theta(this.state, this.subKeys);
            this.pi1(this.state);
            this.gamma(this.state);
            this.pi2(this.state);
        }
        this.state[0] = this.state[0] ^ roundConstants[n3];
        this.theta(this.state, this.subKeys);
        this.intToBytesBig(this.state[0], byArray2, n2);
        this.intToBytesBig(this.state[1], byArray2, n2 + 4);
        this.intToBytesBig(this.state[2], byArray2, n2 + 8);
        this.intToBytesBig(this.state[3], byArray2, n2 + 12);
        return 16;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        this.state[0] = this.bytesToIntBig(byArray, n);
        this.state[1] = this.bytesToIntBig(byArray, n + 4);
        this.state[2] = this.bytesToIntBig(byArray, n + 8);
        this.state[3] = this.bytesToIntBig(byArray, n + 12);
        System.arraycopy(this.subKeys, 0, this.decryptKeys, 0, this.subKeys.length);
        this.theta(this.decryptKeys, nullVector);
        for (n3 = 16; n3 > 0; --n3) {
            this.theta(this.state, this.decryptKeys);
            this.state[0] = this.state[0] ^ roundConstants[n3];
            this.pi1(this.state);
            this.gamma(this.state);
            this.pi2(this.state);
        }
        this.theta(this.state, this.decryptKeys);
        this.state[0] = this.state[0] ^ roundConstants[n3];
        this.intToBytesBig(this.state[0], byArray2, n2);
        this.intToBytesBig(this.state[1], byArray2, n2 + 4);
        this.intToBytesBig(this.state[2], byArray2, n2 + 8);
        this.intToBytesBig(this.state[3], byArray2, n2 + 12);
        return 16;
    }

    private void gamma(int[] nArray) {
        nArray[1] = nArray[1] ^ ~nArray[3] & ~nArray[2];
        nArray[0] = nArray[0] ^ nArray[2] & nArray[1];
        int n = nArray[3];
        nArray[3] = nArray[0];
        nArray[0] = n;
        nArray[2] = nArray[2] ^ (nArray[0] ^ nArray[1] ^ nArray[3]);
        nArray[1] = nArray[1] ^ ~nArray[3] & ~nArray[2];
        nArray[0] = nArray[0] ^ nArray[2] & nArray[1];
    }

    private void theta(int[] nArray, int[] nArray2) {
        int n = nArray[0] ^ nArray[2];
        n ^= this.rotl(n, 8) ^ this.rotl(n, 24);
        nArray[1] = nArray[1] ^ n;
        nArray[3] = nArray[3] ^ n;
        for (int i = 0; i < 4; ++i) {
            int n2 = i;
            nArray[n2] = nArray[n2] ^ nArray2[i];
        }
        n = nArray[1] ^ nArray[3];
        n ^= this.rotl(n, 8) ^ this.rotl(n, 24);
        nArray[0] = nArray[0] ^ n;
        nArray[2] = nArray[2] ^ n;
    }

    private void pi1(int[] nArray) {
        nArray[1] = this.rotl(nArray[1], 1);
        nArray[2] = this.rotl(nArray[2], 5);
        nArray[3] = this.rotl(nArray[3], 2);
    }

    private void pi2(int[] nArray) {
        nArray[1] = this.rotl(nArray[1], 31);
        nArray[2] = this.rotl(nArray[2], 27);
        nArray[3] = this.rotl(nArray[3], 30);
    }

    private int bytesToIntBig(byte[] byArray, int n) {
        return byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n] & 0xFF;
    }

    private void intToBytesBig(int n, byte[] byArray, int n2) {
        byArray[n2++] = (byte)(n >>> 24);
        byArray[n2++] = (byte)(n >>> 16);
        byArray[n2++] = (byte)(n >>> 8);
        byArray[n2] = (byte)n;
    }

    private int rotl(int n, int n2) {
        return n << n2 | n >>> 32 - n2;
    }
}

