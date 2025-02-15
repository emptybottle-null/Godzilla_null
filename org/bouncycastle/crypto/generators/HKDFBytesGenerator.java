/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public class HKDFBytesGenerator
implements DerivationFunction {
    private HMac hMacHash;
    private int hashLen;
    private byte[] info;
    private byte[] currentT;
    private int generatedBytes;

    public HKDFBytesGenerator(Digest digest) {
        this.hMacHash = new HMac(digest);
        this.hashLen = digest.getDigestSize();
    }

    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof HKDFParameters)) {
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        }
        HKDFParameters hKDFParameters = (HKDFParameters)derivationParameters;
        if (hKDFParameters.skipExtract()) {
            this.hMacHash.init(new KeyParameter(hKDFParameters.getIKM()));
        } else {
            this.hMacHash.init(this.extract(hKDFParameters.getSalt(), hKDFParameters.getIKM()));
        }
        this.info = hKDFParameters.getInfo();
        this.generatedBytes = 0;
        this.currentT = new byte[this.hashLen];
    }

    private KeyParameter extract(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            this.hMacHash.init(new KeyParameter(new byte[this.hashLen]));
        } else {
            this.hMacHash.init(new KeyParameter(byArray));
        }
        this.hMacHash.update(byArray2, 0, byArray2.length);
        byte[] byArray3 = new byte[this.hashLen];
        this.hMacHash.doFinal(byArray3, 0);
        return new KeyParameter(byArray3);
    }

    private void expandNext() throws DataLengthException {
        int n = this.generatedBytes / this.hashLen + 1;
        if (n >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (this.generatedBytes != 0) {
            this.hMacHash.update(this.currentT, 0, this.hashLen);
        }
        this.hMacHash.update(this.info, 0, this.info.length);
        this.hMacHash.update((byte)n);
        this.hMacHash.doFinal(this.currentT, 0);
    }

    public Digest getDigest() {
        return this.hMacHash.getUnderlyingDigest();
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        if (this.generatedBytes + n2 > 255 * this.hashLen) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (this.generatedBytes % this.hashLen == 0) {
            this.expandNext();
        }
        int n3 = n2;
        int n4 = this.generatedBytes % this.hashLen;
        int n5 = this.hashLen - this.generatedBytes % this.hashLen;
        int n6 = Math.min(n5, n3);
        System.arraycopy(this.currentT, n4, byArray, n, n6);
        this.generatedBytes += n6;
        n3 -= n6;
        n += n6;
        while (n3 > 0) {
            this.expandNext();
            n6 = Math.min(this.hashLen, n3);
            System.arraycopy(this.currentT, 0, byArray, n, n6);
            this.generatedBytes += n6;
            n3 -= n6;
            n += n6;
        }
        return n2;
    }
}

