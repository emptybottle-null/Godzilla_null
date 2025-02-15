/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt;

public abstract class AsymmetricBlockCipher
extends CipherSpiExt {
    protected AlgorithmParameterSpec paramSpec;
    protected ByteArrayOutputStream buf = new ByteArrayOutputStream();
    protected int maxPlainTextSize;
    protected int cipherTextSize;

    public final int getBlockSize() {
        return this.opMode == 1 ? this.maxPlainTextSize : this.cipherTextSize;
    }

    public final byte[] getIV() {
        return null;
    }

    public final int getOutputSize(int n) {
        int n2;
        int n3 = n + this.buf.size();
        if (n3 > (n2 = this.getBlockSize())) {
            return 0;
        }
        return n2;
    }

    public final AlgorithmParameterSpec getParameters() {
        return this.paramSpec;
    }

    public final void initEncrypt(Key key) throws InvalidKeyException {
        try {
            this.initEncrypt(key, null, new SecureRandom());
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            this.initEncrypt(key, null, secureRandom);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.initEncrypt(key, algorithmParameterSpec, new SecureRandom());
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 1;
        this.initCipherEncrypt(key, algorithmParameterSpec, secureRandom);
    }

    public final void initDecrypt(Key key) throws InvalidKeyException {
        try {
            this.initDecrypt(key, null);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 2;
        this.initCipherDecrypt(key, algorithmParameterSpec);
    }

    public final byte[] update(byte[] byArray, int n, int n2) {
        if (n2 != 0) {
            this.buf.write(byArray, n, n2);
        }
        return new byte[0];
    }

    public final int update(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        this.update(byArray, n, n2);
        return 0;
    }

    public final byte[] doFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        this.checkLength(n2);
        this.update(byArray, n, n2);
        byte[] byArray2 = this.buf.toByteArray();
        this.buf.reset();
        switch (this.opMode) {
            case 1: {
                return this.messageEncrypt(byArray2);
            }
            case 2: {
                return this.messageDecrypt(byArray2);
            }
        }
        return null;
    }

    public final int doFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        if (byArray2.length < this.getOutputSize(n2)) {
            throw new ShortBufferException("Output buffer too short.");
        }
        byte[] byArray3 = this.doFinal(byArray, n, n2);
        System.arraycopy(byArray3, 0, byArray2, n3, byArray3.length);
        return byArray3.length;
    }

    protected final void setMode(String string) {
    }

    protected final void setPadding(String string) {
    }

    protected void checkLength(int n) throws IllegalBlockSizeException {
        int n2 = n + this.buf.size();
        if (this.opMode == 1) {
            if (n2 > this.maxPlainTextSize) {
                throw new IllegalBlockSizeException("The length of the plaintext (" + n2 + " bytes) is not supported by " + "the cipher (max. " + this.maxPlainTextSize + " bytes).");
            }
        } else if (this.opMode == 2 && n2 != this.cipherTextSize) {
            throw new IllegalBlockSizeException("Illegal ciphertext length (expected " + this.cipherTextSize + " bytes, was " + n2 + " bytes).");
        }
    }

    protected abstract void initCipherEncrypt(Key var1, AlgorithmParameterSpec var2, SecureRandom var3) throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract void initCipherDecrypt(Key var1, AlgorithmParameterSpec var2) throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract byte[] messageEncrypt(byte[] var1) throws IllegalBlockSizeException, BadPaddingException;

    protected abstract byte[] messageDecrypt(byte[] var1) throws IllegalBlockSizeException, BadPaddingException;
}

