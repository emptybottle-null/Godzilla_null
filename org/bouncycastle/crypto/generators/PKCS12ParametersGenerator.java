/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class PKCS12ParametersGenerator
extends PBEParametersGenerator {
    public static final int KEY_MATERIAL = 1;
    public static final int IV_MATERIAL = 2;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;
    private int u;
    private int v;

    public PKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (!(digest instanceof ExtendedDigest)) {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        }
        this.u = digest.getDigestSize();
        this.v = ((ExtendedDigest)digest).getByteLength();
    }

    private void adjust(byte[] byArray, int n, byte[] byArray2) {
        int n2 = (byArray2[byArray2.length - 1] & 0xFF) + (byArray[n + byArray2.length - 1] & 0xFF) + 1;
        byArray[n + byArray2.length - 1] = (byte)n2;
        n2 >>>= 8;
        for (int i = byArray2.length - 2; i >= 0; --i) {
            byArray[n + i] = (byte)(n2 += (byArray2[i] & 0xFF) + (byArray[n + i] & 0xFF));
            n2 >>>= 8;
        }
    }

    private byte[] generateDerivedKey(int n, int n2) {
        byte[] byArray;
        byte[] byArray2;
        byte[] byArray3 = new byte[this.v];
        byte[] byArray4 = new byte[n2];
        for (int i = 0; i != byArray3.length; ++i) {
            byArray3[i] = (byte)n;
        }
        if (this.salt != null && this.salt.length != 0) {
            byArray2 = new byte[this.v * ((this.salt.length + this.v - 1) / this.v)];
            for (int i = 0; i != byArray2.length; ++i) {
                byArray2[i] = this.salt[i % this.salt.length];
            }
        } else {
            byArray2 = new byte[]{};
        }
        if (this.password != null && this.password.length != 0) {
            byArray = new byte[this.v * ((this.password.length + this.v - 1) / this.v)];
            for (int i = 0; i != byArray.length; ++i) {
                byArray[i] = this.password[i % this.password.length];
            }
        } else {
            byArray = new byte[]{};
        }
        byte[] byArray5 = new byte[byArray2.length + byArray.length];
        System.arraycopy(byArray2, 0, byArray5, 0, byArray2.length);
        System.arraycopy(byArray, 0, byArray5, byArray2.length, byArray.length);
        byte[] byArray6 = new byte[this.v];
        int n3 = (n2 + this.u - 1) / this.u;
        byte[] byArray7 = new byte[this.u];
        for (int i = 1; i <= n3; ++i) {
            int n4;
            this.digest.update(byArray3, 0, byArray3.length);
            this.digest.update(byArray5, 0, byArray5.length);
            this.digest.doFinal(byArray7, 0);
            for (n4 = 1; n4 < this.iterationCount; ++n4) {
                this.digest.update(byArray7, 0, byArray7.length);
                this.digest.doFinal(byArray7, 0);
            }
            for (n4 = 0; n4 != byArray6.length; ++n4) {
                byArray6[n4] = byArray7[n4 % byArray7.length];
            }
            for (n4 = 0; n4 != byArray5.length / this.v; ++n4) {
                this.adjust(byArray5, n4 * this.v, byArray6);
            }
            if (i == n3) {
                System.arraycopy(byArray7, 0, byArray4, (i - 1) * this.u, byArray4.length - (i - 1) * this.u);
                continue;
            }
            System.arraycopy(byArray7, 0, byArray4, (i - 1) * this.u, byArray7.length);
        }
        return byArray4;
    }

    public CipherParameters generateDerivedParameters(int n) {
        byte[] byArray = this.generateDerivedKey(1, n /= 8);
        return new KeyParameter(byArray, 0, n);
    }

    public CipherParameters generateDerivedParameters(int n, int n2) {
        byte[] byArray = this.generateDerivedKey(1, n /= 8);
        byte[] byArray2 = this.generateDerivedKey(2, n2 /= 8);
        return new ParametersWithIV(new KeyParameter(byArray, 0, n), byArray2, 0, n2);
    }

    public CipherParameters generateDerivedMacParameters(int n) {
        byte[] byArray = this.generateDerivedKey(3, n /= 8);
        return new KeyParameter(byArray, 0, n);
    }
}

