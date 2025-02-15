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

public class HC256Engine
implements StreamCipher {
    private int[] p = new int[1024];
    private int[] q = new int[1024];
    private int cnt = 0;
    private byte[] key;
    private byte[] iv;
    private boolean initialised;
    private byte[] buf = new byte[4];
    private int idx = 0;

    private int step() {
        int n;
        int n2 = this.cnt & 0x3FF;
        if (this.cnt < 1024) {
            int n3 = this.p[n2 - 3 & 0x3FF];
            int n4 = this.p[n2 - 1023 & 0x3FF];
            int n5 = n2;
            this.p[n5] = this.p[n5] + (this.p[n2 - 10 & 0x3FF] + (HC256Engine.rotateRight(n3, 10) ^ HC256Engine.rotateRight(n4, 23)) + this.q[(n3 ^ n4) & 0x3FF]);
            n3 = this.p[n2 - 12 & 0x3FF];
            n = this.q[n3 & 0xFF] + this.q[(n3 >> 8 & 0xFF) + 256] + this.q[(n3 >> 16 & 0xFF) + 512] + this.q[(n3 >> 24 & 0xFF) + 768] ^ this.p[n2];
        } else {
            int n6 = this.q[n2 - 3 & 0x3FF];
            int n7 = this.q[n2 - 1023 & 0x3FF];
            int n8 = n2;
            this.q[n8] = this.q[n8] + (this.q[n2 - 10 & 0x3FF] + (HC256Engine.rotateRight(n6, 10) ^ HC256Engine.rotateRight(n7, 23)) + this.p[(n6 ^ n7) & 0x3FF]);
            n6 = this.q[n2 - 12 & 0x3FF];
            n = this.p[n6 & 0xFF] + this.p[(n6 >> 8 & 0xFF) + 256] + this.p[(n6 >> 16 & 0xFF) + 512] + this.p[(n6 >> 24 & 0xFF) + 768] ^ this.q[n2];
        }
        this.cnt = this.cnt + 1 & 0x7FF;
        return n;
    }

    private void init() {
        int n;
        Object[] objectArray;
        if (this.key.length != 32 && this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128/256 bits long");
        }
        if (this.iv.length < 16) {
            throw new IllegalArgumentException("The IV must be at least 128 bits long");
        }
        if (this.key.length != 32) {
            objectArray = new byte[32];
            System.arraycopy(this.key, 0, objectArray, 0, this.key.length);
            System.arraycopy(this.key, 0, objectArray, 16, this.key.length);
            this.key = objectArray;
        }
        if (this.iv.length < 32) {
            objectArray = new byte[32];
            System.arraycopy(this.iv, 0, objectArray, 0, this.iv.length);
            System.arraycopy(this.iv, 0, objectArray, this.iv.length, objectArray.length - this.iv.length);
            this.iv = objectArray;
        }
        this.idx = 0;
        this.cnt = 0;
        objectArray = new int[2560];
        for (n = 0; n < 32; ++n) {
            int n2 = n >> 2;
            objectArray[n2] = objectArray[n2] | (this.key[n] & 0xFF) << 8 * (n & 3);
        }
        for (n = 0; n < 32; ++n) {
            int n3 = (n >> 2) + 8;
            objectArray[n3] = objectArray[n3] | (this.iv[n] & 0xFF) << 8 * (n & 3);
        }
        for (n = 16; n < 2560; ++n) {
            byte by = objectArray[n - 2];
            byte by2 = objectArray[n - 15];
            objectArray[n] = (HC256Engine.rotateRight(by, 17) ^ HC256Engine.rotateRight(by, 19) ^ by >>> 10) + objectArray[n - 7] + (HC256Engine.rotateRight(by2, 7) ^ HC256Engine.rotateRight(by2, 18) ^ by2 >>> 3) + objectArray[n - 16] + n;
        }
        System.arraycopy(objectArray, 512, this.p, 0, 1024);
        System.arraycopy(objectArray, 1536, this.q, 0, 1024);
        for (n = 0; n < 4096; ++n) {
            this.step();
        }
        this.cnt = 0;
    }

    public String getAlgorithmName() {
        return "HC-256";
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2 = cipherParameters;
        if (cipherParameters instanceof ParametersWithIV) {
            this.iv = ((ParametersWithIV)cipherParameters).getIV();
            cipherParameters2 = ((ParametersWithIV)cipherParameters).getParameters();
        } else {
            this.iv = new byte[0];
        }
        if (!(cipherParameters2 instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to HC256 init - " + cipherParameters.getClass().getName());
        }
        this.key = ((KeyParameter)cipherParameters2).getKey();
        this.init();
        this.initialised = true;
    }

    private byte getByte() {
        int n;
        if (this.idx == 0) {
            n = this.step();
            this.buf[0] = (byte)(n & 0xFF);
            this.buf[1] = (byte)((n >>= 8) & 0xFF);
            this.buf[2] = (byte)((n >>= 8) & 0xFF);
            this.buf[3] = (byte)((n >>= 8) & 0xFF);
        }
        n = this.buf[this.idx];
        this.idx = this.idx + 1 & 3;
        return (byte)n;
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
            byArray2[n3 + i] = (byte)(byArray[n + i] ^ this.getByte());
        }
        return n2;
    }

    public void reset() {
        this.init();
    }

    public byte returnByte(byte by) {
        return (byte)(by ^ this.getByte());
    }

    private static int rotateRight(int n, int n2) {
        return n >>> n2 | n << -n2;
    }
}

