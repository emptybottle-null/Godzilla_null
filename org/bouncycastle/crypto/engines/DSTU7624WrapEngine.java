/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.util.ArrayList;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class DSTU7624WrapEngine
implements Wrapper {
    private static final int BYTES_IN_INTEGER = 4;
    private boolean forWrapping;
    private DSTU7624Engine engine;
    private byte[] B;
    private byte[] intArray;
    private byte[] checkSumArray;
    private byte[] zeroArray;
    private ArrayList<byte[]> Btemp;

    public DSTU7624WrapEngine(int n) {
        this.engine = new DSTU7624Engine(n);
        this.B = new byte[this.engine.getBlockSize() / 2];
        this.checkSumArray = new byte[this.engine.getBlockSize()];
        this.zeroArray = new byte[this.engine.getBlockSize()];
        this.Btemp = new ArrayList();
        this.intArray = new byte[4];
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom)cipherParameters).getParameters();
        }
        this.forWrapping = bl;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameters passed to DSTU7624WrapEngine");
        }
        this.engine.init(bl, cipherParameters);
    }

    public String getAlgorithmName() {
        return "DSTU7624WrapEngine";
    }

    public byte[] wrap(byte[] byArray, int n, int n2) {
        int n3;
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        if (n2 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("wrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
        }
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        int n4 = 2 * (1 + n2 / this.engine.getBlockSize());
        int n5 = (n4 - 1) * 6;
        byte[] byArray2 = new byte[n2 + this.engine.getBlockSize()];
        System.arraycopy(byArray, n, byArray2, 0, n2);
        System.arraycopy(byArray2, 0, this.B, 0, this.engine.getBlockSize() / 2);
        this.Btemp.clear();
        int n6 = byArray2.length - this.engine.getBlockSize() / 2;
        int n7 = this.engine.getBlockSize() / 2;
        while (n6 != 0) {
            byte[] byArray3 = new byte[this.engine.getBlockSize() / 2];
            System.arraycopy(byArray2, n7, byArray3, 0, this.engine.getBlockSize() / 2);
            this.Btemp.add(byArray3);
            n6 -= this.engine.getBlockSize() / 2;
            n7 += this.engine.getBlockSize() / 2;
        }
        for (n3 = 0; n3 < n5; ++n3) {
            int n8;
            System.arraycopy(this.B, 0, byArray2, 0, this.engine.getBlockSize() / 2);
            System.arraycopy(this.Btemp.get(0), 0, byArray2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
            this.engine.processBlock(byArray2, 0, byArray2, 0);
            this.intToBytes(n3 + 1, this.intArray, 0);
            for (n8 = 0; n8 < 4; ++n8) {
                int n9 = n8 + this.engine.getBlockSize() / 2;
                byArray2[n9] = (byte)(byArray2[n9] ^ this.intArray[n8]);
            }
            System.arraycopy(byArray2, this.engine.getBlockSize() / 2, this.B, 0, this.engine.getBlockSize() / 2);
            for (n8 = 2; n8 < n4; ++n8) {
                System.arraycopy(this.Btemp.get(n8 - 1), 0, this.Btemp.get(n8 - 2), 0, this.engine.getBlockSize() / 2);
            }
            System.arraycopy(byArray2, 0, this.Btemp.get(n4 - 2), 0, this.engine.getBlockSize() / 2);
        }
        System.arraycopy(this.B, 0, byArray2, 0, this.engine.getBlockSize() / 2);
        n7 = this.engine.getBlockSize() / 2;
        for (n3 = 0; n3 < n4 - 1; ++n3) {
            System.arraycopy(this.Btemp.get(n3), 0, byArray2, n7, this.engine.getBlockSize() / 2);
            n7 += this.engine.getBlockSize() / 2;
        }
        return byArray2;
    }

    public byte[] unwrap(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        int n3;
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        if (n2 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("unwrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
        }
        int n4 = 2 * n2 / this.engine.getBlockSize();
        int n5 = (n4 - 1) * 6;
        byte[] byArray2 = new byte[n2];
        System.arraycopy(byArray, n, byArray2, 0, n2);
        byte[] byArray3 = new byte[this.engine.getBlockSize() / 2];
        System.arraycopy(byArray2, 0, byArray3, 0, this.engine.getBlockSize() / 2);
        this.Btemp.clear();
        int n6 = byArray2.length - this.engine.getBlockSize() / 2;
        int n7 = this.engine.getBlockSize() / 2;
        while (n6 != 0) {
            byte[] byArray4 = new byte[this.engine.getBlockSize() / 2];
            System.arraycopy(byArray2, n7, byArray4, 0, this.engine.getBlockSize() / 2);
            this.Btemp.add(byArray4);
            n6 -= this.engine.getBlockSize() / 2;
            n7 += this.engine.getBlockSize() / 2;
        }
        for (n3 = 0; n3 < n5; ++n3) {
            int n8;
            System.arraycopy(this.Btemp.get(n4 - 2), 0, byArray2, 0, this.engine.getBlockSize() / 2);
            System.arraycopy(byArray3, 0, byArray2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
            this.intToBytes(n5 - n3, this.intArray, 0);
            for (n8 = 0; n8 < 4; ++n8) {
                int n9 = n8 + this.engine.getBlockSize() / 2;
                byArray2[n9] = (byte)(byArray2[n9] ^ this.intArray[n8]);
            }
            this.engine.processBlock(byArray2, 0, byArray2, 0);
            System.arraycopy(byArray2, 0, byArray3, 0, this.engine.getBlockSize() / 2);
            for (n8 = 2; n8 < n4; ++n8) {
                System.arraycopy(this.Btemp.get(n4 - n8 - 1), 0, this.Btemp.get(n4 - n8), 0, this.engine.getBlockSize() / 2);
            }
            System.arraycopy(byArray2, this.engine.getBlockSize() / 2, this.Btemp.get(0), 0, this.engine.getBlockSize() / 2);
        }
        System.arraycopy(byArray3, 0, byArray2, 0, this.engine.getBlockSize() / 2);
        n7 = this.engine.getBlockSize() / 2;
        for (n3 = 0; n3 < n4 - 1; ++n3) {
            System.arraycopy(this.Btemp.get(n3), 0, byArray2, n7, this.engine.getBlockSize() / 2);
            n7 += this.engine.getBlockSize() / 2;
        }
        System.arraycopy(byArray2, byArray2.length - this.engine.getBlockSize(), this.checkSumArray, 0, this.engine.getBlockSize());
        byte[] byArray5 = new byte[byArray2.length - this.engine.getBlockSize()];
        if (!Arrays.areEqual(this.checkSumArray, this.zeroArray)) {
            throw new InvalidCipherTextException("checksum failed");
        }
        System.arraycopy(byArray2, 0, byArray5, 0, byArray2.length - this.engine.getBlockSize());
        return byArray5;
    }

    private void intToBytes(int n, byte[] byArray, int n2) {
        byArray[n2 + 3] = (byte)(n >> 24);
        byArray[n2 + 2] = (byte)(n >> 16);
        byArray[n2 + 1] = (byte)(n >> 8);
        byArray[n2] = (byte)n;
    }
}

