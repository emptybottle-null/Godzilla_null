/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class PKCS5S1ParametersGenerator
extends PBEParametersGenerator {
    private Digest digest;

    public PKCS5S1ParametersGenerator(Digest digest) {
        this.digest = digest;
    }

    private byte[] generateDerivedKey() {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.update(this.password, 0, this.password.length);
        this.digest.update(this.salt, 0, this.salt.length);
        this.digest.doFinal(byArray, 0);
        for (int i = 1; i < this.iterationCount; ++i) {
            this.digest.update(byArray, 0, byArray.length);
            this.digest.doFinal(byArray, 0);
        }
        return byArray;
    }

    public CipherParameters generateDerivedParameters(int n) {
        if ((n /= 8) > this.digest.getDigestSize()) {
            throw new IllegalArgumentException("Can't generate a derived key " + n + " bytes long.");
        }
        byte[] byArray = this.generateDerivedKey();
        return new KeyParameter(byArray, 0, n);
    }

    public CipherParameters generateDerivedParameters(int n, int n2) {
        if ((n /= 8) + (n2 /= 8) > this.digest.getDigestSize()) {
            throw new IllegalArgumentException("Can't generate a derived key " + (n + n2) + " bytes long.");
        }
        byte[] byArray = this.generateDerivedKey();
        return new ParametersWithIV(new KeyParameter(byArray, 0, n), byArray, n, n2);
    }

    public CipherParameters generateDerivedMacParameters(int n) {
        return this.generateDerivedParameters(n);
    }
}

