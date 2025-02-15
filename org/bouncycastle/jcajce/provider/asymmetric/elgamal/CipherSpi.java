/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.BufferedAsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.ISO9796d1Encoding;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.elgamal.ElGamalUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jce.interfaces.ElGamalKey;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import org.bouncycastle.util.Strings;

public class CipherSpi
extends BaseCipherSpi {
    private BufferedAsymmetricBlockCipher cipher;
    private AlgorithmParameterSpec paramSpec;
    private AlgorithmParameters engineParams;

    public CipherSpi(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = new BufferedAsymmetricBlockCipher(asymmetricBlockCipher);
    }

    private void initFromSpec(OAEPParameterSpec oAEPParameterSpec) throws NoSuchPaddingException {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)oAEPParameterSpec.getMGFParameters();
        Digest digest = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
        if (digest == null) {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
        }
        this.cipher = new BufferedAsymmetricBlockCipher(new OAEPEncoding(new ElGamalEngine(), digest, ((PSource.PSpecified)oAEPParameterSpec.getPSource()).getValue()));
        this.paramSpec = oAEPParameterSpec;
    }

    protected int engineGetBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    protected int engineGetKeySize(Key key) {
        if (key instanceof ElGamalKey) {
            ElGamalKey elGamalKey = (ElGamalKey)((Object)key);
            return elGamalKey.getParameters().getP().bitLength();
        }
        if (key instanceof DHKey) {
            DHKey dHKey = (DHKey)((Object)key);
            return dHKey.getParams().getP().bitLength();
        }
        throw new IllegalArgumentException("not an ElGamal key!");
    }

    protected int engineGetOutputSize(int n) {
        return this.cipher.getOutputBlockSize();
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.paramSpec != null) {
            try {
                this.engineParams = this.createParametersInstance("OAEP");
                this.engineParams.init(this.paramSpec);
            } catch (Exception exception) {
                throw new RuntimeException(exception.toString());
            }
        }
        return this.engineParams;
    }

    protected void engineSetMode(String string) throws NoSuchAlgorithmException {
        String string2 = Strings.toUpperCase(string);
        if (string2.equals("NONE") || string2.equals("ECB")) {
            return;
        }
        throw new NoSuchAlgorithmException("can't support mode " + string);
    }

    protected void engineSetPadding(String string) throws NoSuchPaddingException {
        String string2 = Strings.toUpperCase(string);
        if (string2.equals("NOPADDING")) {
            this.cipher = new BufferedAsymmetricBlockCipher(new ElGamalEngine());
        } else if (string2.equals("PKCS1PADDING")) {
            this.cipher = new BufferedAsymmetricBlockCipher(new PKCS1Encoding(new ElGamalEngine()));
        } else if (string2.equals("ISO9796-1PADDING")) {
            this.cipher = new BufferedAsymmetricBlockCipher(new ISO9796d1Encoding(new ElGamalEngine()));
        } else if (string2.equals("OAEPPADDING")) {
            this.initFromSpec(OAEPParameterSpec.DEFAULT);
        } else if (string2.equals("OAEPWITHMD5ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA1ANDMGF1PADDING")) {
            this.initFromSpec(OAEPParameterSpec.DEFAULT);
        } else if (string2.equals("OAEPWITHSHA224ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA256ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA384ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA512ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSource.PSpecified.DEFAULT));
        } else {
            throw new NoSuchPaddingException(string + " unavailable with ElGamal.");
        }
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void engineInit(int n, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException {
        if (algorithmParameterSpec != null) throw new IllegalArgumentException("unknown parameter type.");
        if (key instanceof ElGamalPublicKey) {
            AsymmetricKeyParameter asymmetricKeyParameter = ElGamalUtil.generatePublicKeyParameter((PublicKey)key);
        } else {
            if (!(key instanceof ElGamalPrivateKey)) throw new InvalidKeyException("unknown key type passed to ElGamal");
            AsymmetricKeyParameter asymmetricKeyParameter = ElGamalUtil.generatePrivateKeyParameter((PrivateKey)key);
        }
        if (secureRandom != null) {
            void var5_7;
            ParametersWithRandom parametersWithRandom = new ParametersWithRandom((CipherParameters)var5_7, secureRandom);
        }
        switch (n) {
            case 1: 
            case 3: {
                void var5_9;
                this.cipher.init(true, (CipherParameters)var5_9);
                return;
            }
            case 2: 
            case 4: {
                void var5_9;
                this.cipher.init(false, (CipherParameters)var5_9);
                return;
            }
            default: {
                throw new InvalidParameterException("unknown opmode " + n + " passed to ElGamal");
            }
        }
    }

    protected void engineInit(int n, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("can't handle parameters in ElGamal");
    }

    protected void engineInit(int n, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        this.engineInit(n, key, (AlgorithmParameterSpec)null, secureRandom);
    }

    protected byte[] engineUpdate(byte[] byArray, int n, int n2) {
        this.cipher.processBytes(byArray, n, n2);
        return null;
    }

    protected int engineUpdate(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        this.cipher.processBytes(byArray, n, n2);
        return 0;
    }

    protected byte[] engineDoFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        this.cipher.processBytes(byArray, n, n2);
        return this.getOutput();
    }

    protected int engineDoFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalBlockSizeException, BadPaddingException {
        this.cipher.processBytes(byArray, n, n2);
        byte[] byArray3 = this.getOutput();
        for (int i = 0; i != byArray3.length; ++i) {
            byArray2[n3 + i] = byArray3[i];
        }
        return byArray3.length;
    }

    private byte[] getOutput() throws BadPaddingException {
        try {
            return this.cipher.doFinal();
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new BadPaddingException("unable to decrypt block"){

                public synchronized Throwable getCause() {
                    return invalidCipherTextException;
                }
            };
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new BadBlockException("unable to decrypt block", arrayIndexOutOfBoundsException);
        }
    }

    public static class NoPadding
    extends CipherSpi {
        public NoPadding() {
            super(new ElGamalEngine());
        }
    }

    public static class PKCS1v1_5Padding
    extends CipherSpi {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new ElGamalEngine()));
        }
    }
}

