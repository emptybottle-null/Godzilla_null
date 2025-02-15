/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.util.Pack;

public class BaseKDFBytesGenerator
implements DigestDerivationFunction {
    private int counterStart;
    private Digest digest;
    private byte[] shared;
    private byte[] iv;

    protected BaseKDFBytesGenerator(int n, Digest digest) {
        this.counterStart = n;
        this.digest = digest;
    }

    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters)derivationParameters;
            this.shared = kDFParameters.getSharedSecret();
            this.iv = kDFParameters.getIV();
        } else if (derivationParameters instanceof ISO18033KDFParameters) {
            ISO18033KDFParameters iSO18033KDFParameters = (ISO18033KDFParameters)derivationParameters;
            this.shared = iSO18033KDFParameters.getSeed();
            this.iv = null;
        } else {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        if (byArray.length - n2 < n) {
            throw new OutputLengthException("output buffer too small");
        }
        long l = n2;
        int n3 = this.digest.getDigestSize();
        if (l > 0x1FFFFFFFFL) {
            throw new IllegalArgumentException("Output length too large");
        }
        int n4 = (int)((l + (long)n3 - 1L) / (long)n3);
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        byte[] byArray3 = new byte[4];
        Pack.intToBigEndian(this.counterStart, byArray3, 0);
        int n5 = this.counterStart & 0xFFFFFF00;
        for (int i = 0; i < n4; ++i) {
            this.digest.update(this.shared, 0, this.shared.length);
            this.digest.update(byArray3, 0, byArray3.length);
            if (this.iv != null) {
                this.digest.update(this.iv, 0, this.iv.length);
            }
            this.digest.doFinal(byArray2, 0);
            if (n2 > n3) {
                System.arraycopy(byArray2, 0, byArray, n, n3);
                n += n3;
                n2 -= n3;
            } else {
                System.arraycopy(byArray2, 0, byArray, n, n2);
            }
            byArray3[3] = (byte)(byArray3[3] + 1);
            if (byArray3[3] != 0) continue;
            Pack.intToBigEndian(n5 += 256, byArray3, 0);
        }
        this.digest.reset();
        return (int)l;
    }
}

