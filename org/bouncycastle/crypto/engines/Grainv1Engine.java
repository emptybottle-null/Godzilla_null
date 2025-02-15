/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class Grainv1Engine
implements StreamCipher {
    private static final int STATE_SIZE = 5;
    private byte[] workingKey;
    private byte[] workingIV;
    private byte[] out;
    private int[] lfsr;
    private int[] nfsr;
    private int output;
    private int index = 2;
    private boolean initialised = false;

    public String getAlgorithmName() {
        return "Grain v1";
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        byte[] byArray = parametersWithIV.getIV();
        if (byArray == null || byArray.length != 8) {
            throw new IllegalArgumentException("Grain v1 requires exactly 8 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter)parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[5];
        this.nfsr = new int[5];
        this.out = new byte[2];
        System.arraycopy(byArray, 0, this.workingIV, 0, byArray.length);
        System.arraycopy(keyParameter.getKey(), 0, this.workingKey, 0, keyParameter.getKey().length);
        this.reset();
    }

    private void initGrain() {
        for (int i = 0; i < 10; ++i) {
            this.output = this.getOutput();
            this.nfsr = this.shift(this.nfsr, this.getOutputNFSR() ^ this.lfsr[0] ^ this.output);
            this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() ^ this.output);
        }
        this.initialised = true;
    }

    private int getOutputNFSR() {
        int n = this.nfsr[0];
        int n2 = this.nfsr[0] >>> 9 | this.nfsr[1] << 7;
        int n3 = this.nfsr[0] >>> 14 | this.nfsr[1] << 2;
        int n4 = this.nfsr[0] >>> 15 | this.nfsr[1] << 1;
        int n5 = this.nfsr[1] >>> 5 | this.nfsr[2] << 11;
        int n6 = this.nfsr[1] >>> 12 | this.nfsr[2] << 4;
        int n7 = this.nfsr[2] >>> 1 | this.nfsr[3] << 15;
        int n8 = this.nfsr[2] >>> 5 | this.nfsr[3] << 11;
        int n9 = this.nfsr[2] >>> 13 | this.nfsr[3] << 3;
        int n10 = this.nfsr[3] >>> 4 | this.nfsr[4] << 12;
        int n11 = this.nfsr[3] >>> 12 | this.nfsr[4] << 4;
        int n12 = this.nfsr[3] >>> 14 | this.nfsr[4] << 2;
        int n13 = this.nfsr[3] >>> 15 | this.nfsr[4] << 1;
        return (n12 ^ n11 ^ n10 ^ n9 ^ n8 ^ n7 ^ n6 ^ n5 ^ n3 ^ n2 ^ n ^ n13 & n11 ^ n8 & n7 ^ n4 & n2 ^ n11 & n10 & n9 ^ n7 & n6 & n5 ^ n13 & n9 & n6 & n2 ^ n11 & n10 & n8 & n7 ^ n13 & n11 & n5 & n4 ^ n13 & n11 & n10 & n9 & n8 ^ n7 & n6 & n5 & n4 & n2 ^ n10 & n9 & n8 & n7 & n6 & n5) & 0xFFFF;
    }

    private int getOutputLFSR() {
        int n = this.lfsr[0];
        int n2 = this.lfsr[0] >>> 13 | this.lfsr[1] << 3;
        int n3 = this.lfsr[1] >>> 7 | this.lfsr[2] << 9;
        int n4 = this.lfsr[2] >>> 6 | this.lfsr[3] << 10;
        int n5 = this.lfsr[3] >>> 3 | this.lfsr[4] << 13;
        int n6 = this.lfsr[3] >>> 14 | this.lfsr[4] << 2;
        return (n ^ n2 ^ n3 ^ n4 ^ n5 ^ n6) & 0xFFFF;
    }

    private int getOutput() {
        int n = this.nfsr[0] >>> 1 | this.nfsr[1] << 15;
        int n2 = this.nfsr[0] >>> 2 | this.nfsr[1] << 14;
        int n3 = this.nfsr[0] >>> 4 | this.nfsr[1] << 12;
        int n4 = this.nfsr[0] >>> 10 | this.nfsr[1] << 6;
        int n5 = this.nfsr[1] >>> 15 | this.nfsr[2] << 1;
        int n6 = this.nfsr[2] >>> 11 | this.nfsr[3] << 5;
        int n7 = this.nfsr[3] >>> 8 | this.nfsr[4] << 8;
        int n8 = this.nfsr[3] >>> 15 | this.nfsr[4] << 1;
        int n9 = this.lfsr[0] >>> 3 | this.lfsr[1] << 13;
        int n10 = this.lfsr[1] >>> 9 | this.lfsr[2] << 7;
        int n11 = this.lfsr[2] >>> 14 | this.lfsr[3] << 2;
        int n12 = this.lfsr[4];
        return (n10 ^ n8 ^ n9 & n12 ^ n11 & n12 ^ n12 & n8 ^ n9 & n10 & n11 ^ n9 & n11 & n12 ^ n9 & n11 & n8 ^ n10 & n11 & n8 ^ n11 & n12 & n8 ^ n ^ n2 ^ n3 ^ n4 ^ n5 ^ n6 ^ n7) & 0xFFFF;
    }

    private int[] shift(int[] nArray, int n) {
        nArray[0] = nArray[1];
        nArray[1] = nArray[2];
        nArray[2] = nArray[3];
        nArray[3] = nArray[4];
        nArray[4] = n;
        return nArray;
    }

    private void setKey(byte[] byArray, byte[] byArray2) {
        byArray2[8] = -1;
        byArray2[9] = -1;
        this.workingKey = byArray;
        this.workingIV = byArray2;
        int n = 0;
        for (int i = 0; i < this.nfsr.length; ++i) {
            this.nfsr[i] = (this.workingKey[n + 1] << 8 | this.workingKey[n] & 0xFF) & 0xFFFF;
            this.lfsr[i] = (this.workingIV[n + 1] << 8 | this.workingIV[n] & 0xFF) & 0xFFFF;
            n += 2;
        }
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n3 + n2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i = 0; i < n2; ++i) {
            byArray2[n3 + i] = (byte)(byArray[n + i] ^ this.getKeyStream());
        }
        return n2;
    }

    public void reset() {
        this.index = 2;
        this.setKey(this.workingKey, this.workingIV);
        this.initGrain();
    }

    private void oneRound() {
        this.output = this.getOutput();
        this.out[0] = (byte)this.output;
        this.out[1] = (byte)(this.output >> 8);
        this.nfsr = this.shift(this.nfsr, this.getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = this.shift(this.lfsr, this.getOutputLFSR());
    }

    public byte returnByte(byte by) {
        if (!this.initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        return (byte)(by ^ this.getKeyStream());
    }

    private byte getKeyStream() {
        if (this.index > 1) {
            this.oneRound();
            this.index = 0;
        }
        return this.out[this.index++];
    }
}

