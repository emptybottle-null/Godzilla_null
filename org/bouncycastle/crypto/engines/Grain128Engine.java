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

public class Grain128Engine
implements StreamCipher {
    private static final int STATE_SIZE = 4;
    private byte[] workingKey;
    private byte[] workingIV;
    private byte[] out;
    private int[] lfsr;
    private int[] nfsr;
    private int output;
    private int index = 4;
    private boolean initialised = false;

    public String getAlgorithmName() {
        return "Grain-128";
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        byte[] byArray = parametersWithIV.getIV();
        if (byArray == null || byArray.length != 12) {
            throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter)parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.out = new byte[4];
        System.arraycopy(byArray, 0, this.workingIV, 0, byArray.length);
        System.arraycopy(keyParameter.getKey(), 0, this.workingKey, 0, keyParameter.getKey().length);
        this.reset();
    }

    private void initGrain() {
        for (int i = 0; i < 8; ++i) {
            this.output = this.getOutput();
            this.nfsr = this.shift(this.nfsr, this.getOutputNFSR() ^ this.lfsr[0] ^ this.output);
            this.lfsr = this.shift(this.lfsr, this.getOutputLFSR() ^ this.output);
        }
        this.initialised = true;
    }

    private int getOutputNFSR() {
        int n = this.nfsr[0];
        int n2 = this.nfsr[0] >>> 3 | this.nfsr[1] << 29;
        int n3 = this.nfsr[0] >>> 11 | this.nfsr[1] << 21;
        int n4 = this.nfsr[0] >>> 13 | this.nfsr[1] << 19;
        int n5 = this.nfsr[0] >>> 17 | this.nfsr[1] << 15;
        int n6 = this.nfsr[0] >>> 18 | this.nfsr[1] << 14;
        int n7 = this.nfsr[0] >>> 26 | this.nfsr[1] << 6;
        int n8 = this.nfsr[0] >>> 27 | this.nfsr[1] << 5;
        int n9 = this.nfsr[1] >>> 8 | this.nfsr[2] << 24;
        int n10 = this.nfsr[1] >>> 16 | this.nfsr[2] << 16;
        int n11 = this.nfsr[1] >>> 24 | this.nfsr[2] << 8;
        int n12 = this.nfsr[1] >>> 27 | this.nfsr[2] << 5;
        int n13 = this.nfsr[1] >>> 29 | this.nfsr[2] << 3;
        int n14 = this.nfsr[2] >>> 1 | this.nfsr[3] << 31;
        int n15 = this.nfsr[2] >>> 3 | this.nfsr[3] << 29;
        int n16 = this.nfsr[2] >>> 4 | this.nfsr[3] << 28;
        int n17 = this.nfsr[2] >>> 20 | this.nfsr[3] << 12;
        int n18 = this.nfsr[2] >>> 27 | this.nfsr[3] << 5;
        int n19 = this.nfsr[3];
        return n ^ n7 ^ n11 ^ n18 ^ n19 ^ n2 & n15 ^ n3 & n4 ^ n5 & n6 ^ n8 & n12 ^ n9 & n10 ^ n13 & n14 ^ n16 & n17;
    }

    private int getOutputLFSR() {
        int n = this.lfsr[0];
        int n2 = this.lfsr[0] >>> 7 | this.lfsr[1] << 25;
        int n3 = this.lfsr[1] >>> 6 | this.lfsr[2] << 26;
        int n4 = this.lfsr[2] >>> 6 | this.lfsr[3] << 26;
        int n5 = this.lfsr[2] >>> 17 | this.lfsr[3] << 15;
        int n6 = this.lfsr[3];
        return n ^ n2 ^ n3 ^ n4 ^ n5 ^ n6;
    }

    private int getOutput() {
        int n = this.nfsr[0] >>> 2 | this.nfsr[1] << 30;
        int n2 = this.nfsr[0] >>> 12 | this.nfsr[1] << 20;
        int n3 = this.nfsr[0] >>> 15 | this.nfsr[1] << 17;
        int n4 = this.nfsr[1] >>> 4 | this.nfsr[2] << 28;
        int n5 = this.nfsr[1] >>> 13 | this.nfsr[2] << 19;
        int n6 = this.nfsr[2];
        int n7 = this.nfsr[2] >>> 9 | this.nfsr[3] << 23;
        int n8 = this.nfsr[2] >>> 25 | this.nfsr[3] << 7;
        int n9 = this.nfsr[2] >>> 31 | this.nfsr[3] << 1;
        int n10 = this.lfsr[0] >>> 8 | this.lfsr[1] << 24;
        int n11 = this.lfsr[0] >>> 13 | this.lfsr[1] << 19;
        int n12 = this.lfsr[0] >>> 20 | this.lfsr[1] << 12;
        int n13 = this.lfsr[1] >>> 10 | this.lfsr[2] << 22;
        int n14 = this.lfsr[1] >>> 28 | this.lfsr[2] << 4;
        int n15 = this.lfsr[2] >>> 15 | this.lfsr[3] << 17;
        int n16 = this.lfsr[2] >>> 29 | this.lfsr[3] << 3;
        int n17 = this.lfsr[2] >>> 31 | this.lfsr[3] << 1;
        return n2 & n10 ^ n11 & n12 ^ n9 & n13 ^ n14 & n15 ^ n2 & n9 & n17 ^ n16 ^ n ^ n3 ^ n4 ^ n5 ^ n6 ^ n7 ^ n8;
    }

    private int[] shift(int[] nArray, int n) {
        nArray[0] = nArray[1];
        nArray[1] = nArray[2];
        nArray[2] = nArray[3];
        nArray[3] = n;
        return nArray;
    }

    private void setKey(byte[] byArray, byte[] byArray2) {
        byArray2[12] = -1;
        byArray2[13] = -1;
        byArray2[14] = -1;
        byArray2[15] = -1;
        this.workingKey = byArray;
        this.workingIV = byArray2;
        int n = 0;
        for (int i = 0; i < this.nfsr.length; ++i) {
            this.nfsr[i] = this.workingKey[n + 3] << 24 | this.workingKey[n + 2] << 16 & 0xFF0000 | this.workingKey[n + 1] << 8 & 0xFF00 | this.workingKey[n] & 0xFF;
            this.lfsr[i] = this.workingIV[n + 3] << 24 | this.workingIV[n + 2] << 16 & 0xFF0000 | this.workingIV[n + 1] << 8 & 0xFF00 | this.workingIV[n] & 0xFF;
            n += 4;
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
        this.index = 4;
        this.setKey(this.workingKey, this.workingIV);
        this.initGrain();
    }

    private void oneRound() {
        this.output = this.getOutput();
        this.out[0] = (byte)this.output;
        this.out[1] = (byte)(this.output >> 8);
        this.out[2] = (byte)(this.output >> 16);
        this.out[3] = (byte)(this.output >> 24);
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
        if (this.index > 3) {
            this.oneRound();
            this.index = 0;
        }
        return this.out[this.index++];
    }
}

