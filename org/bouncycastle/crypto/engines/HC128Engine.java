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

public class HC128Engine
implements StreamCipher {
    private int[] p = new int[512];
    private int[] q = new int[512];
    private int cnt = 0;
    private byte[] key;
    private byte[] iv;
    private boolean initialised;
    private byte[] buf = new byte[4];
    private int idx = 0;

    private static int f1(int n) {
        return HC128Engine.rotateRight(n, 7) ^ HC128Engine.rotateRight(n, 18) ^ n >>> 3;
    }

    private static int f2(int n) {
        return HC128Engine.rotateRight(n, 17) ^ HC128Engine.rotateRight(n, 19) ^ n >>> 10;
    }

    private int g1(int n, int n2, int n3) {
        return (HC128Engine.rotateRight(n, 10) ^ HC128Engine.rotateRight(n3, 23)) + HC128Engine.rotateRight(n2, 8);
    }

    private int g2(int n, int n2, int n3) {
        return (HC128Engine.rotateLeft(n, 10) ^ HC128Engine.rotateLeft(n3, 23)) + HC128Engine.rotateLeft(n2, 8);
    }

    private static int rotateLeft(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    private static int rotateRight(int n, int n2) {
        return n >>> n2 | n << -n2;
    }

    private int h1(int n) {
        return this.q[n & 0xFF] + this.q[(n >> 16 & 0xFF) + 256];
    }

    private int h2(int n) {
        return this.p[n & 0xFF] + this.p[(n >> 16 & 0xFF) + 256];
    }

    private static int mod1024(int n) {
        return n & 0x3FF;
    }

    private static int mod512(int n) {
        return n & 0x1FF;
    }

    private static int dim(int n, int n2) {
        return HC128Engine.mod512(n - n2);
    }

    private int step() {
        int n;
        int n2 = HC128Engine.mod512(this.cnt);
        if (this.cnt < 512) {
            int n3 = n2;
            this.p[n3] = this.p[n3] + this.g1(this.p[HC128Engine.dim(n2, 3)], this.p[HC128Engine.dim(n2, 10)], this.p[HC128Engine.dim(n2, 511)]);
            n = this.h1(this.p[HC128Engine.dim(n2, 12)]) ^ this.p[n2];
        } else {
            int n4 = n2;
            this.q[n4] = this.q[n4] + this.g2(this.q[HC128Engine.dim(n2, 3)], this.q[HC128Engine.dim(n2, 10)], this.q[HC128Engine.dim(n2, 511)]);
            n = this.h2(this.q[HC128Engine.dim(n2, 12)]) ^ this.q[n2];
        }
        this.cnt = HC128Engine.mod1024(this.cnt + 1);
        return n;
    }

    private void init() {
        int n;
        if (this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.idx = 0;
        this.cnt = 0;
        int[] nArray = new int[1280];
        for (n = 0; n < 16; ++n) {
            int n2 = n >> 2;
            nArray[n2] = nArray[n2] | (this.key[n] & 0xFF) << 8 * (n & 3);
        }
        System.arraycopy(nArray, 0, nArray, 4, 4);
        for (n = 0; n < this.iv.length && n < 16; ++n) {
            int n3 = (n >> 2) + 8;
            nArray[n3] = nArray[n3] | (this.iv[n] & 0xFF) << 8 * (n & 3);
        }
        System.arraycopy(nArray, 8, nArray, 12, 4);
        for (n = 16; n < 1280; ++n) {
            nArray[n] = HC128Engine.f2(nArray[n - 2]) + nArray[n - 7] + HC128Engine.f1(nArray[n - 15]) + nArray[n - 16] + n;
        }
        System.arraycopy(nArray, 256, this.p, 0, 512);
        System.arraycopy(nArray, 768, this.q, 0, 512);
        for (n = 0; n < 512; ++n) {
            this.p[n] = this.step();
        }
        for (n = 0; n < 512; ++n) {
            this.q[n] = this.step();
        }
        this.cnt = 0;
    }

    public String getAlgorithmName() {
        return "HC-128";
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
            throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
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
}

