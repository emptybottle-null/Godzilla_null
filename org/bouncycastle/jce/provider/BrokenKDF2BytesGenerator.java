/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;

public class BrokenKDF2BytesGenerator
implements DerivationFunction {
    private Digest digest;
    private byte[] shared;
    private byte[] iv;

    public BrokenKDF2BytesGenerator(Digest digest) {
        this.digest = digest;
    }

    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters)derivationParameters;
        this.shared = kDFParameters.getSharedSecret();
        this.iv = kDFParameters.getIV();
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        if (byArray.length - n2 < n) {
            throw new OutputLengthException("output buffer too small");
        }
        long l = (long)n2 * 8L;
        if (l > (long)this.digest.getDigestSize() * 8L * 0x80000000L) {
            new IllegalArgumentException("Output length to large");
        }
        int n3 = (int)(l / (long)this.digest.getDigestSize());
        byte[] byArray2 = null;
        byArray2 = new byte[this.digest.getDigestSize()];
        for (int i = 1; i <= n3; ++i) {
            this.digest.update(this.shared, 0, this.shared.length);
            this.digest.update((byte)(i & 0xFF));
            this.digest.update((byte)(i >> 8 & 0xFF));
            this.digest.update((byte)(i >> 16 & 0xFF));
            this.digest.update((byte)(i >> 24 & 0xFF));
            this.digest.update(this.iv, 0, this.iv.length);
            this.digest.doFinal(byArray2, 0);
            if (n2 - n > byArray2.length) {
                System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
                n += byArray2.length;
                continue;
            }
            System.arraycopy(byArray2, 0, byArray, n, n2 - n);
        }
        this.digest.reset();
        return n2;
    }
}

