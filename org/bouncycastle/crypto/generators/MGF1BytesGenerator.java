/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.MGFParameters;

public class MGF1BytesGenerator
implements DerivationFunction {
    private Digest digest;
    private byte[] seed;
    private int hLen;

    public MGF1BytesGenerator(Digest digest) {
        this.digest = digest;
        this.hLen = digest.getDigestSize();
    }

    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof MGFParameters)) {
            throw new IllegalArgumentException("MGF parameters required for MGF1Generator");
        }
        MGFParameters mGFParameters = (MGFParameters)derivationParameters;
        this.seed = mGFParameters.getSeed();
    }

    public Digest getDigest() {
        return this.digest;
    }

    private void ItoOSP(int n, byte[] byArray) {
        byArray[0] = (byte)(n >>> 24);
        byArray[1] = (byte)(n >>> 16);
        byArray[2] = (byte)(n >>> 8);
        byArray[3] = (byte)(n >>> 0);
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        if (byArray.length - n2 < n) {
            throw new OutputLengthException("output buffer too small");
        }
        byte[] byArray2 = new byte[this.hLen];
        byte[] byArray3 = new byte[4];
        int n3 = 0;
        this.digest.reset();
        if (n2 > this.hLen) {
            do {
                this.ItoOSP(n3, byArray3);
                this.digest.update(this.seed, 0, this.seed.length);
                this.digest.update(byArray3, 0, byArray3.length);
                this.digest.doFinal(byArray2, 0);
                System.arraycopy(byArray2, 0, byArray, n + n3 * this.hLen, this.hLen);
            } while (++n3 < n2 / this.hLen);
        }
        if (n3 * this.hLen < n2) {
            this.ItoOSP(n3, byArray3);
            this.digest.update(this.seed, 0, this.seed.length);
            this.digest.update(byArray3, 0, byArray3.length);
            this.digest.doFinal(byArray2, 0);
            System.arraycopy(byArray2, 0, byArray, n + n3 * this.hLen, n2 - n3 * this.hLen);
        }
        return n2;
    }
}

