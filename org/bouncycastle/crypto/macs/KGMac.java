/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.KGCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class KGMac
implements Mac {
    private final KGCMBlockCipher cipher;
    private final int macSizeBits;

    public KGMac(KGCMBlockCipher kGCMBlockCipher) {
        this.cipher = kGCMBlockCipher;
        this.macSizeBits = kGCMBlockCipher.getUnderlyingCipher().getBlockSize() * 8;
    }

    public KGMac(KGCMBlockCipher kGCMBlockCipher, int n) {
        this.cipher = kGCMBlockCipher;
        this.macSizeBits = n;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("KGMAC requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        byte[] byArray = parametersWithIV.getIV();
        KeyParameter keyParameter = (KeyParameter)parametersWithIV.getParameters();
        this.cipher.init(true, new AEADParameters(keyParameter, this.macSizeBits, byArray));
    }

    public String getAlgorithmName() {
        return this.cipher.getUnderlyingCipher().getAlgorithmName() + "-KGMAC";
    }

    public int getMacSize() {
        return this.macSizeBits / 8;
    }

    public void update(byte by) throws IllegalStateException {
        this.cipher.processAADByte(by);
    }

    public void update(byte[] byArray, int n, int n2) throws DataLengthException, IllegalStateException {
        this.cipher.processAADBytes(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        try {
            return this.cipher.doFinal(byArray, n);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new IllegalStateException(invalidCipherTextException.toString());
        }
    }

    public void reset() {
        this.cipher.reset();
    }
}

