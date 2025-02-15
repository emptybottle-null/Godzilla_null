/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class BaseCipherSpi
extends CipherSpi {
    private Class[] availableSpecs = new Class[]{IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
    private final JcaJceHelper helper = new BCJcaJceHelper();
    protected AlgorithmParameters engineParams = null;
    protected Wrapper wrapEngine = null;
    private int ivSize;
    private byte[] iv;

    protected BaseCipherSpi() {
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return null;
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length;
    }

    protected int engineGetOutputSize(int n) {
        return -1;
    }

    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected final AlgorithmParameters createParametersInstance(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return this.helper.createAlgorithmParameters(string);
    }

    protected void engineSetMode(String string) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("can't support mode " + string);
    }

    protected void engineSetPadding(String string) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + string + " unknown.");
    }

    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        byte[] byArray = key.getEncoded();
        if (byArray == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            if (this.wrapEngine == null) {
                return this.engineDoFinal(byArray, 0, byArray.length);
            }
            return this.wrapEngine.wrap(byArray, 0, byArray.length);
        } catch (BadPaddingException badPaddingException) {
            throw new IllegalBlockSizeException(badPaddingException.getMessage());
        }
    }

    protected Key engineUnwrap(byte[] byArray, String string, int n) throws InvalidKeyException {
        byte[] byArray2;
        try {
            byArray2 = this.wrapEngine == null ? this.engineDoFinal(byArray, 0, byArray.length) : this.wrapEngine.unwrap(byArray, 0, byArray.length);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new InvalidKeyException(invalidCipherTextException.getMessage());
        } catch (BadPaddingException badPaddingException) {
            throw new InvalidKeyException("unable to unwrap"){

                public synchronized Throwable getCause() {
                    return badPaddingException;
                }
            };
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            throw new InvalidKeyException(illegalBlockSizeException.getMessage());
        }
        if (n == 3) {
            return new SecretKeySpec(byArray2, string);
        }
        if (string.equals("") && n == 2) {
            try {
                PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(byArray2);
                PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(privateKeyInfo);
                if (privateKey != null) {
                    return privateKey;
                }
                throw new InvalidKeyException("algorithm " + privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm() + " not supported");
            } catch (Exception exception) {
                throw new InvalidKeyException("Invalid key encoding.");
            }
        }
        try {
            KeyFactory keyFactory = this.helper.createKeyFactory(string);
            if (n == 1) {
                return keyFactory.generatePublic(new X509EncodedKeySpec(byArray2));
            }
            if (n == 2) {
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(byArray2));
            }
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new InvalidKeyException("Unknown key type " + noSuchAlgorithmException.getMessage());
        } catch (InvalidKeySpecException invalidKeySpecException) {
            throw new InvalidKeyException("Unknown key type " + invalidKeySpecException.getMessage());
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new InvalidKeyException("Unknown key type " + noSuchProviderException.getMessage());
        }
        throw new InvalidKeyException("Unknown key type " + n);
    }
}

