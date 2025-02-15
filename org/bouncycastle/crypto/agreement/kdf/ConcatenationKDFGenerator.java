/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;

public class ConcatenationKDFGenerator
implements DerivationFunction {
    private Digest digest;
    private byte[] shared;
    private byte[] otherInfo;
    private int hLen;

    public ConcatenationKDFGenerator(Digest digest) {
        this.digest = digest;
        this.hLen = digest.getDigestSize();
    }

    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters)derivationParameters;
        this.shared = kDFParameters.getSharedSecret();
        this.otherInfo = kDFParameters.getIV();
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
        int n3 = 1;
        int n4 = 0;
        this.digest.reset();
        if (n2 > this.hLen) {
            do {
                this.ItoOSP(n3, byArray3);
                this.digest.update(byArray3, 0, byArray3.length);
                this.digest.update(this.shared, 0, this.shared.length);
                this.digest.update(this.otherInfo, 0, this.otherInfo.length);
                this.digest.doFinal(byArray2, 0);
                System.arraycopy(byArray2, 0, byArray, n + n4, this.hLen);
                n4 += this.hLen;
            } while (n3++ < n2 / this.hLen);
        }
        if (n4 < n2) {
            this.ItoOSP(n3, byArray3);
            this.digest.update(byArray3, 0, byArray3.length);
            this.digest.update(this.shared, 0, this.shared.length);
            this.digest.update(this.otherInfo, 0, this.otherInfo.length);
            this.digest.doFinal(byArray2, 0);
            System.arraycopy(byArray2, 0, byArray, n + n4, n2 - n4);
        }
        return n2;
    }
}

