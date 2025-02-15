/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;

public class OpenSSLPBEParametersGenerator
extends PBEParametersGenerator {
    private Digest digest = DigestFactory.createMD5();

    public void init(byte[] byArray, byte[] byArray2) {
        super.init(byArray, byArray2, 1);
    }

    private byte[] generateDerivedKey(int n) {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        byte[] byArray2 = new byte[n];
        int n2 = 0;
        while (true) {
            this.digest.update(this.password, 0, this.password.length);
            this.digest.update(this.salt, 0, this.salt.length);
            this.digest.doFinal(byArray, 0);
            int n3 = n > byArray.length ? byArray.length : n;
            System.arraycopy(byArray, 0, byArray2, n2, n3);
            n2 += n3;
            if ((n -= n3) == 0) break;
            this.digest.reset();
            this.digest.update(byArray, 0, byArray.length);
        }
        return byArray2;
    }

    public CipherParameters generateDerivedParameters(int n) {
        byte[] byArray = this.generateDerivedKey(n /= 8);
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

