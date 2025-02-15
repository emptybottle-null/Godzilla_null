/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.bc;

import java.security.SecureRandom;
import org.bouncycastle.cert.crmf.EncryptedValuePadder;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.MGF1BytesGenerator;
import org.bouncycastle.crypto.params.MGFParameters;

public class BcFixedLengthMGF1Padder
implements EncryptedValuePadder {
    private int length;
    private SecureRandom random;
    private Digest dig = new SHA1Digest();

    public BcFixedLengthMGF1Padder(int n) {
        this(n, null);
    }

    public BcFixedLengthMGF1Padder(int n, SecureRandom secureRandom) {
        this.length = n;
        this.random = secureRandom;
    }

    public byte[] getPaddedData(byte[] byArray) {
        int n;
        byte[] byArray2 = new byte[this.length];
        byte[] byArray3 = new byte[this.dig.getDigestSize()];
        byte[] byArray4 = new byte[this.length - this.dig.getDigestSize()];
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        this.random.nextBytes(byArray3);
        MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(this.dig);
        mGF1BytesGenerator.init(new MGFParameters(byArray3));
        mGF1BytesGenerator.generateBytes(byArray4, 0, byArray4.length);
        System.arraycopy(byArray3, 0, byArray2, 0, byArray3.length);
        System.arraycopy(byArray, 0, byArray2, byArray3.length, byArray.length);
        for (n = byArray3.length + byArray.length + 1; n != byArray2.length; ++n) {
            byArray2[n] = (byte)(1 + this.random.nextInt(255));
        }
        for (n = 0; n != byArray4.length; ++n) {
            int n2 = n + byArray3.length;
            byArray2[n2] = (byte)(byArray2[n2] ^ byArray4[n]);
        }
        return byArray2;
    }

    public byte[] getUnpaddedData(byte[] byArray) {
        int n;
        byte[] byArray2 = new byte[this.dig.getDigestSize()];
        byte[] byArray3 = new byte[this.length - this.dig.getDigestSize()];
        System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
        MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(this.dig);
        mGF1BytesGenerator.init(new MGFParameters(byArray2));
        mGF1BytesGenerator.generateBytes(byArray3, 0, byArray3.length);
        for (n = 0; n != byArray3.length; ++n) {
            int n2 = n + byArray2.length;
            byArray[n2] = (byte)(byArray[n2] ^ byArray3[n]);
        }
        n = 0;
        for (int i = byArray.length - 1; i != byArray2.length; --i) {
            if (byArray[i] != 0) continue;
            n = i;
            break;
        }
        if (n == 0) {
            throw new IllegalStateException("bad padding in encoding");
        }
        byte[] byArray4 = new byte[n - byArray2.length];
        System.arraycopy(byArray, byArray2.length, byArray4, 0, byArray4.length);
        return byArray4;
    }
}

