/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;

public class PKCS5S2ParametersGenerator
extends PBEParametersGenerator {
    private Mac hMac;
    private byte[] state;

    public PKCS5S2ParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public PKCS5S2ParametersGenerator(Digest digest) {
        this.hMac = new HMac(digest);
        this.state = new byte[this.hMac.getMacSize()];
    }

    private void F(byte[] byArray, int n, byte[] byArray2, byte[] byArray3, int n2) {
        if (n == 0) {
            throw new IllegalArgumentException("iteration count must be at least 1.");
        }
        if (byArray != null) {
            this.hMac.update(byArray, 0, byArray.length);
        }
        this.hMac.update(byArray2, 0, byArray2.length);
        this.hMac.doFinal(this.state, 0);
        System.arraycopy(this.state, 0, byArray3, n2, this.state.length);
        for (int i = 1; i < n; ++i) {
            this.hMac.update(this.state, 0, this.state.length);
            this.hMac.doFinal(this.state, 0);
            for (int j = 0; j != this.state.length; ++j) {
                int n3 = n2 + j;
                byArray3[n3] = (byte)(byArray3[n3] ^ this.state[j]);
            }
        }
    }

    private byte[] generateDerivedKey(int n) {
        int n2 = this.hMac.getMacSize();
        int n3 = (n + n2 - 1) / n2;
        byte[] byArray = new byte[4];
        byte[] byArray2 = new byte[n3 * n2];
        int n4 = 0;
        KeyParameter keyParameter = new KeyParameter(this.password);
        this.hMac.init(keyParameter);
        for (int i = 1; i <= n3; ++i) {
            int n5;
            int n6 = 3;
            do {
                n5 = n6--;
            } while ((byArray[n5] = (byte)(byArray[n5] + 1)) == 0);
            this.F(this.salt, this.iterationCount, byArray, byArray2, n4);
            n4 += n2;
        }
        return byArray2;
    }

    public CipherParameters generateDerivedParameters(int n) {
        byte[] byArray = Arrays.copyOfRange(this.generateDerivedKey(n /= 8), 0, n);
        return new KeyParameter(byArray, 0, n);
    }

    public CipherParameters generateDerivedParameters(int n, int n2) {
        byte[] byArray = this.generateDerivedKey((n /= 8) + (n2 /= 8));
        return new ParametersWithIV(new KeyParameter(byArray, 0, n), byArray, n, n2);
    }

    public CipherParameters generateDerivedMacParameters(int n) {
        return this.generateDerivedParameters(n);
    }
}

