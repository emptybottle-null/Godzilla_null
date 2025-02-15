/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class VMPCMac
implements Mac {
    private byte g;
    private byte n = 0;
    private byte[] P = null;
    private byte s = 0;
    private byte[] T;
    private byte[] workingIV;
    private byte[] workingKey;
    private byte x1;
    private byte x2;
    private byte x3;
    private byte x4;

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        int n2;
        int n3;
        for (n3 = 1; n3 < 25; ++n3) {
            this.s = this.P[this.s + this.P[this.n & 0xFF] & 0xFF];
            this.x4 = this.P[this.x4 + this.x3 + n3 & 0xFF];
            this.x3 = this.P[this.x3 + this.x2 + n3 & 0xFF];
            this.x2 = this.P[this.x2 + this.x1 + n3 & 0xFF];
            this.x1 = this.P[this.x1 + this.s + n3 & 0xFF];
            this.T[this.g & 0x1F] = (byte)(this.T[this.g & 0x1F] ^ this.x1);
            this.T[this.g + 1 & 0x1F] = (byte)(this.T[this.g + 1 & 0x1F] ^ this.x2);
            this.T[this.g + 2 & 0x1F] = (byte)(this.T[this.g + 2 & 0x1F] ^ this.x3);
            this.T[this.g + 3 & 0x1F] = (byte)(this.T[this.g + 3 & 0x1F] ^ this.x4);
            this.g = (byte)(this.g + 4 & 0x1F);
            n2 = this.P[this.n & 0xFF];
            this.P[this.n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = n2;
            this.n = (byte)(this.n + 1 & 0xFF);
        }
        for (n3 = 0; n3 < 768; ++n3) {
            this.s = this.P[this.s + this.P[n3 & 0xFF] + this.T[n3 & 0x1F] & 0xFF];
            n2 = this.P[n3 & 0xFF];
            this.P[n3 & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = n2;
        }
        byte[] byArray2 = new byte[20];
        for (n2 = 0; n2 < 20; ++n2) {
            this.s = this.P[this.s + this.P[n2 & 0xFF] & 0xFF];
            byArray2[n2] = this.P[this.P[this.P[this.s & 0xFF] & 0xFF] + 1 & 0xFF];
            byte by = this.P[n2 & 0xFF];
            this.P[n2 & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
        this.reset();
        return byArray2.length;
    }

    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    public int getMacSize() {
        return 20;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        KeyParameter keyParameter = (KeyParameter)parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        this.workingIV = parametersWithIV.getIV();
        if (this.workingIV == null || this.workingIV.length < 1 || this.workingIV.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = keyParameter.getKey();
        this.reset();
    }

    private void initKey(byte[] byArray, byte[] byArray2) {
        byte by;
        int n;
        this.s = 0;
        this.P = new byte[256];
        for (n = 0; n < 256; ++n) {
            this.P[n] = (byte)n;
        }
        for (n = 0; n < 768; ++n) {
            this.s = this.P[this.s + this.P[n & 0xFF] + byArray[n % byArray.length] & 0xFF];
            by = this.P[n & 0xFF];
            this.P[n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        for (n = 0; n < 768; ++n) {
            this.s = this.P[this.s + this.P[n & 0xFF] + byArray2[n % byArray2.length] & 0xFF];
            by = this.P[n & 0xFF];
            this.P[n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        this.n = 0;
    }

    public void reset() {
        this.initKey(this.workingKey, this.workingIV);
        this.n = 0;
        this.x4 = 0;
        this.x3 = 0;
        this.x2 = 0;
        this.x1 = 0;
        this.g = 0;
        this.T = new byte[32];
        for (int i = 0; i < 32; ++i) {
            this.T[i] = 0;
        }
    }

    public void update(byte by) throws IllegalStateException {
        this.s = this.P[this.s + this.P[this.n & 0xFF] & 0xFF];
        byte by2 = (byte)(by ^ this.P[this.P[this.P[this.s & 0xFF] & 0xFF] + 1 & 0xFF]);
        this.x4 = this.P[this.x4 + this.x3 & 0xFF];
        this.x3 = this.P[this.x3 + this.x2 & 0xFF];
        this.x2 = this.P[this.x2 + this.x1 & 0xFF];
        this.x1 = this.P[this.x1 + this.s + by2 & 0xFF];
        this.T[this.g & 0x1F] = (byte)(this.T[this.g & 0x1F] ^ this.x1);
        this.T[this.g + 1 & 0x1F] = (byte)(this.T[this.g + 1 & 0x1F] ^ this.x2);
        this.T[this.g + 2 & 0x1F] = (byte)(this.T[this.g + 2 & 0x1F] ^ this.x3);
        this.T[this.g + 3 & 0x1F] = (byte)(this.T[this.g + 3 & 0x1F] ^ this.x4);
        this.g = (byte)(this.g + 4 & 0x1F);
        byte by3 = this.P[this.n & 0xFF];
        this.P[this.n & 0xFF] = this.P[this.s & 0xFF];
        this.P[this.s & 0xFF] = by3;
        this.n = (byte)(this.n + 1 & 0xFF);
    }

    public void update(byte[] byArray, int n, int n2) throws DataLengthException, IllegalStateException {
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i = 0; i < n2; ++i) {
            this.update(byArray[n + i]);
        }
    }
}

