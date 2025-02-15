/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

public abstract class BaseWrapCipher
extends CipherSpi
implements PBE {
    private Class[] availableSpecs = new Class[]{GOST28147WrapParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class};
    protected int pbeType = 2;
    protected int pbeHash = 1;
    protected int pbeKeySize;
    protected int pbeIvSize;
    protected AlgorithmParameters engineParams = null;
    protected Wrapper wrapEngine = null;
    private int ivSize;
    private byte[] iv;
    private final JcaJceHelper helper = new BCJcaJceHelper();

    protected BaseWrapCipher() {
    }

    protected BaseWrapCipher(Wrapper wrapper) {
        this(wrapper, 0);
    }

    protected BaseWrapCipher(Wrapper wrapper, int n) {
        this.wrapEngine = wrapper;
        this.ivSize = n;
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return Arrays.clone(this.iv);
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
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

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void engineInit(int n, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        void var5_16;
        Object object;
        if (key instanceof BCPBEKey) {
            object = (BCPBEKey)key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                CipherParameters cipherParameters = PBE.Util.makePBEParameters((BCPBEKey)object, algorithmParameterSpec, this.wrapEngine.getAlgorithmName());
            } else {
                if (((BCPBEKey)object).getParam() == null) throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                CipherParameters cipherParameters = ((BCPBEKey)object).getParam();
            }
        } else {
            KeyParameter keyParameter = new KeyParameter(key.getEncoded());
        }
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            void var5_10;
            object = (IvParameterSpec)algorithmParameterSpec;
            ParametersWithIV parametersWithIV = new ParametersWithIV((CipherParameters)var5_10, ((IvParameterSpec)object).getIV());
        }
        if (algorithmParameterSpec instanceof GOST28147WrapParameterSpec) {
            void var5_14;
            object = (GOST28147WrapParameterSpec)algorithmParameterSpec;
            byte[] byArray = ((GOST28147WrapParameterSpec)object).getSBox();
            if (byArray != null) {
                void var5_12;
                ParametersWithSBox parametersWithSBox = new ParametersWithSBox((CipherParameters)var5_12, byArray);
            }
            ParametersWithUKM parametersWithUKM = new ParametersWithUKM((CipherParameters)var5_14, ((GOST28147WrapParameterSpec)object).getUKM());
        }
        if (var5_16 instanceof KeyParameter && this.ivSize != 0) {
            this.iv = new byte[this.ivSize];
            secureRandom.nextBytes(this.iv);
            ParametersWithIV parametersWithIV = new ParametersWithIV((CipherParameters)var5_16, this.iv);
        }
        if (secureRandom != null) {
            void var5_18;
            ParametersWithRandom parametersWithRandom = new ParametersWithRandom((CipherParameters)var5_18, secureRandom);
        }
        switch (n) {
            case 3: {
                void var5_20;
                this.wrapEngine.init(true, (CipherParameters)var5_20);
                return;
            }
            case 4: {
                void var5_20;
                this.wrapEngine.init(false, (CipherParameters)var5_20);
                return;
            }
            case 1: 
            case 2: {
                throw new IllegalArgumentException("engine only valid for wrapping");
            }
            default: {
                System.out.println("eeek!");
            }
        }
    }

    protected void engineInit(int n, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            for (int i = 0; i != this.availableSpecs.length; ++i) {
                try {
                    algorithmParameterSpec = (AlgorithmParameterSpec)algorithmParameters.getParameterSpec(this.availableSpecs[i]);
                    break;
                } catch (Exception exception) {
                    continue;
                }
            }
            if (algorithmParameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        this.engineParams = algorithmParameters;
        this.engineInit(n, key, algorithmParameterSpec, secureRandom);
    }

    protected void engineInit(int n, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            this.engineInit(n, key, (AlgorithmParameterSpec)null, secureRandom);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new IllegalArgumentException(invalidAlgorithmParameterException.getMessage());
        }
    }

    protected byte[] engineUpdate(byte[] byArray, int n, int n2) {
        throw new RuntimeException("not supported for wrapping");
    }

    protected int engineUpdate(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws ShortBufferException {
        throw new RuntimeException("not supported for wrapping");
    }

    protected byte[] engineDoFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        return null;
    }

    protected int engineDoFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        return 0;
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

    protected Key engineUnwrap(byte[] byArray, String string, int n) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] byArray2;
        try {
            byArray2 = this.wrapEngine == null ? this.engineDoFinal(byArray, 0, byArray.length) : this.wrapEngine.unwrap(byArray, 0, byArray.length);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new InvalidKeyException(invalidCipherTextException.getMessage());
        } catch (BadPaddingException badPaddingException) {
            throw new InvalidKeyException(badPaddingException.getMessage());
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
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new InvalidKeyException("Unknown key type " + noSuchProviderException.getMessage());
        } catch (InvalidKeySpecException invalidKeySpecException) {
            throw new InvalidKeyException("Unknown key type " + invalidKeySpecException.getMessage());
        }
        throw new InvalidKeyException("Unknown key type " + n);
    }
}

