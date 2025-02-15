/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;

public class RC2WrapEngine
implements Wrapper {
    private CBCBlockCipher engine;
    private CipherParameters param;
    private ParametersWithIV paramPlusIV;
    private byte[] iv;
    private boolean forWrapping;
    private SecureRandom sr;
    private static final byte[] IV2 = new byte[]{74, -35, -94, 44, 121, -24, 33, 5};
    Digest sha1 = DigestFactory.createSHA1();
    byte[] digest = new byte[20];

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forWrapping = bl;
        this.engine = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.sr = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.sr = new SecureRandom();
        }
        if (cipherParameters instanceof ParametersWithIV) {
            this.paramPlusIV = (ParametersWithIV)cipherParameters;
            this.iv = this.paramPlusIV.getIV();
            this.param = this.paramPlusIV.getParameters();
            if (!this.forWrapping) throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            if (this.iv != null && this.iv.length == 8) return;
            throw new IllegalArgumentException("IV is not 8 octets");
        }
        this.param = cipherParameters;
        if (!this.forWrapping) return;
        this.iv = new byte[8];
        this.sr.nextBytes(this.iv);
        this.paramPlusIV = new ParametersWithIV(this.param, this.iv);
    }

    public String getAlgorithmName() {
        return "RC2";
    }

    public byte[] wrap(byte[] byArray, int n, int n2) {
        if (!this.forWrapping) {
            throw new IllegalStateException("Not initialized for wrapping");
        }
        int n3 = n2 + 1;
        if (n3 % 8 != 0) {
            n3 += 8 - n3 % 8;
        }
        byte[] byArray2 = new byte[n3];
        byArray2[0] = (byte)n2;
        System.arraycopy(byArray, n, byArray2, 1, n2);
        byte[] byArray3 = new byte[byArray2.length - n2 - 1];
        if (byArray3.length > 0) {
            this.sr.nextBytes(byArray3);
            System.arraycopy(byArray3, 0, byArray2, n2 + 1, byArray3.length);
        }
        byte[] byArray4 = this.calculateCMSKeyChecksum(byArray2);
        byte[] byArray5 = new byte[byArray2.length + byArray4.length];
        System.arraycopy(byArray2, 0, byArray5, 0, byArray2.length);
        System.arraycopy(byArray4, 0, byArray5, byArray2.length, byArray4.length);
        byte[] byArray6 = new byte[byArray5.length];
        System.arraycopy(byArray5, 0, byArray6, 0, byArray5.length);
        int n4 = byArray5.length / this.engine.getBlockSize();
        int n5 = byArray5.length % this.engine.getBlockSize();
        if (n5 != 0) {
            throw new IllegalStateException("Not multiple of block length");
        }
        this.engine.init(true, this.paramPlusIV);
        for (int i = 0; i < n4; ++i) {
            int n6 = i * this.engine.getBlockSize();
            this.engine.processBlock(byArray6, n6, byArray6, n6);
        }
        byte[] byArray7 = new byte[this.iv.length + byArray6.length];
        System.arraycopy(this.iv, 0, byArray7, 0, this.iv.length);
        System.arraycopy(byArray6, 0, byArray7, this.iv.length, byArray6.length);
        byte[] byArray8 = new byte[byArray7.length];
        for (int i = 0; i < byArray7.length; ++i) {
            byArray8[i] = byArray7[byArray7.length - (i + 1)];
        }
        ParametersWithIV parametersWithIV = new ParametersWithIV(this.param, IV2);
        this.engine.init(true, parametersWithIV);
        for (int i = 0; i < n4 + 1; ++i) {
            int n7 = i * this.engine.getBlockSize();
            this.engine.processBlock(byArray8, n7, byArray8, n7);
        }
        return byArray8;
    }

    public byte[] unwrap(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        int n3;
        if (this.forWrapping) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (byArray == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        }
        if (n2 % this.engine.getBlockSize() != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + this.engine.getBlockSize());
        }
        ParametersWithIV parametersWithIV = new ParametersWithIV(this.param, IV2);
        this.engine.init(false, parametersWithIV);
        byte[] byArray2 = new byte[n2];
        System.arraycopy(byArray, n, byArray2, 0, n2);
        for (int i = 0; i < byArray2.length / this.engine.getBlockSize(); ++i) {
            n3 = i * this.engine.getBlockSize();
            this.engine.processBlock(byArray2, n3, byArray2, n3);
        }
        byte[] byArray3 = new byte[byArray2.length];
        for (n3 = 0; n3 < byArray2.length; ++n3) {
            byArray3[n3] = byArray2[byArray2.length - (n3 + 1)];
        }
        this.iv = new byte[8];
        byte[] byArray4 = new byte[byArray3.length - 8];
        System.arraycopy(byArray3, 0, this.iv, 0, 8);
        System.arraycopy(byArray3, 8, byArray4, 0, byArray3.length - 8);
        this.paramPlusIV = new ParametersWithIV(this.param, this.iv);
        this.engine.init(false, this.paramPlusIV);
        byte[] byArray5 = new byte[byArray4.length];
        System.arraycopy(byArray4, 0, byArray5, 0, byArray4.length);
        for (int i = 0; i < byArray5.length / this.engine.getBlockSize(); ++i) {
            int n4 = i * this.engine.getBlockSize();
            this.engine.processBlock(byArray5, n4, byArray5, n4);
        }
        byte[] byArray6 = new byte[byArray5.length - 8];
        byte[] byArray7 = new byte[8];
        System.arraycopy(byArray5, 0, byArray6, 0, byArray5.length - 8);
        System.arraycopy(byArray5, byArray5.length - 8, byArray7, 0, 8);
        if (!this.checkCMSKeyChecksum(byArray6, byArray7)) {
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        if (byArray6.length - ((byArray6[0] & 0xFF) + 1) > 7) {
            throw new InvalidCipherTextException("too many pad bytes (" + (byArray6.length - ((byArray6[0] & 0xFF) + 1)) + ")");
        }
        byte[] byArray8 = new byte[byArray6[0]];
        System.arraycopy(byArray6, 1, byArray8, 0, byArray8.length);
        return byArray8;
    }

    private byte[] calculateCMSKeyChecksum(byte[] byArray) {
        byte[] byArray2 = new byte[8];
        this.sha1.update(byArray, 0, byArray.length);
        this.sha1.doFinal(this.digest, 0);
        System.arraycopy(this.digest, 0, byArray2, 0, 8);
        return byArray2;
    }

    private boolean checkCMSKeyChecksum(byte[] byArray, byte[] byArray2) {
        return Arrays.constantTimeAreEqual(this.calculateCMSKeyChecksum(byArray), byArray2);
    }
}

