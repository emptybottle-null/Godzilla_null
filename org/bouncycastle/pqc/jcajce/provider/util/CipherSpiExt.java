/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public abstract class CipherSpiExt
extends CipherSpi {
    public static final int ENCRYPT_MODE = 1;
    public static final int DECRYPT_MODE = 2;
    protected int opMode;

    protected final void engineInit(int n, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            this.engineInit(n, key, (AlgorithmParameterSpec)null, secureRandom);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidParameterException(invalidAlgorithmParameterException.getMessage());
        }
    }

    protected final void engineInit(int n, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameters == null) {
            this.engineInit(n, key, secureRandom);
            return;
        }
        AlgorithmParameterSpec algorithmParameterSpec = null;
        this.engineInit(n, key, algorithmParameterSpec, secureRandom);
    }

    protected void engineInit(int n, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof AlgorithmParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        }
        if (key == null || !(key instanceof Key)) {
            throw new InvalidKeyException();
        }
        this.opMode = n;
        if (n == 1) {
            SecureRandom secureRandom2 = secureRandom;
            this.initEncrypt(key, algorithmParameterSpec, secureRandom2);
        } else if (n == 2) {
            this.initDecrypt(key, algorithmParameterSpec);
        }
    }

    protected final byte[] engineDoFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        return this.doFinal(byArray, n, n2);
    }

    protected final int engineDoFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        return this.doFinal(byArray, n, n2, byArray2, n3);
    }

    protected final int engineGetBlockSize() {
        return this.getBlockSize();
    }

    protected final int engineGetKeySize(Key key) throws InvalidKeyException {
        if (!(key instanceof Key)) {
            throw new InvalidKeyException("Unsupported key.");
        }
        return this.getKeySize(key);
    }

    protected final byte[] engineGetIV() {
        return this.getIV();
    }

    protected final int engineGetOutputSize(int n) {
        return this.getOutputSize(n);
    }

    protected final AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected final void engineSetMode(String string) throws NoSuchAlgorithmException {
        this.setMode(string);
    }

    protected final void engineSetPadding(String string) throws NoSuchPaddingException {
        this.setPadding(string);
    }

    protected final byte[] engineUpdate(byte[] byArray, int n, int n2) {
        return this.update(byArray, n, n2);
    }

    protected final int engineUpdate(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws ShortBufferException {
        return this.update(byArray, n, n2, byArray2, n3);
    }

    public abstract void initEncrypt(Key var1, AlgorithmParameterSpec var2, SecureRandom var3) throws InvalidKeyException, InvalidAlgorithmParameterException;

    public abstract void initDecrypt(Key var1, AlgorithmParameterSpec var2) throws InvalidKeyException, InvalidAlgorithmParameterException;

    public abstract String getName();

    public abstract int getBlockSize();

    public abstract int getOutputSize(int var1);

    public abstract int getKeySize(Key var1) throws InvalidKeyException;

    public abstract AlgorithmParameterSpec getParameters();

    public abstract byte[] getIV();

    protected abstract void setMode(String var1) throws NoSuchAlgorithmException;

    protected abstract void setPadding(String var1) throws NoSuchPaddingException;

    public final byte[] update(byte[] byArray) {
        return this.update(byArray, 0, byArray.length);
    }

    public abstract byte[] update(byte[] var1, int var2, int var3);

    public abstract int update(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException;

    public final byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        return this.doFinal(null, 0, 0);
    }

    public final byte[] doFinal(byte[] byArray) throws IllegalBlockSizeException, BadPaddingException {
        return this.doFinal(byArray, 0, byArray.length);
    }

    public abstract byte[] doFinal(byte[] var1, int var2, int var3) throws IllegalBlockSizeException, BadPaddingException;

    public abstract int doFinal(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;
}

