/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;

public class RC4Engine
implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private int x = 0;
    private int y = 0;
    private byte[] workingKey = null;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = ((KeyParameter)cipherParameters).getKey();
            this.setKey(this.workingKey);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "RC4";
    }

    public byte returnByte(byte by) {
        this.x = this.x + 1 & 0xFF;
        this.y = this.engineState[this.x] + this.y & 0xFF;
        byte by2 = this.engineState[this.x];
        this.engineState[this.x] = this.engineState[this.y];
        this.engineState[this.y] = by2;
        return (byte)(by ^ this.engineState[this.engineState[this.x] + this.engineState[this.y] & 0xFF]);
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n3 + n2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i = 0; i < n2; ++i) {
            this.x = this.x + 1 & 0xFF;
            this.y = this.engineState[this.x] + this.y & 0xFF;
            byte by = this.engineState[this.x];
            this.engineState[this.x] = this.engineState[this.y];
            this.engineState[this.y] = by;
            byArray2[i + n3] = (byte)(byArray[i + n] ^ this.engineState[this.engineState[this.x] + this.engineState[this.y] & 0xFF]);
        }
        return n2;
    }

    public void reset() {
        this.setKey(this.workingKey);
    }

    private void setKey(byte[] byArray) {
        int n;
        this.workingKey = byArray;
        this.x = 0;
        this.y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (n = 0; n < 256; ++n) {
            this.engineState[n] = (byte)n;
        }
        n = 0;
        int n2 = 0;
        for (int i = 0; i < 256; ++i) {
            n2 = (byArray[n] & 0xFF) + this.engineState[i] + n2 & 0xFF;
            byte by = this.engineState[i];
            this.engineState[i] = this.engineState[n2];
            this.engineState[n2] = by;
            n = (n + 1) % byArray.length;
        }
    }
}

