/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.ISO9796d1Encoding;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Strings;

public class CipherSpi
extends BaseCipherSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private AsymmetricBlockCipher cipher;
    private AlgorithmParameterSpec paramSpec;
    private AlgorithmParameters engineParams;
    private boolean publicKeyOnly = false;
    private boolean privateKeyOnly = false;
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    public CipherSpi(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = asymmetricBlockCipher;
    }

    public CipherSpi(OAEPParameterSpec oAEPParameterSpec) {
        try {
            this.initFromSpec(oAEPParameterSpec);
        } catch (NoSuchPaddingException noSuchPaddingException) {
            throw new IllegalArgumentException(noSuchPaddingException.getMessage());
        }
    }

    public CipherSpi(boolean bl, boolean bl2, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.publicKeyOnly = bl;
        this.privateKeyOnly = bl2;
        this.cipher = asymmetricBlockCipher;
    }

    private void initFromSpec(OAEPParameterSpec oAEPParameterSpec) throws NoSuchPaddingException {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)oAEPParameterSpec.getMGFParameters();
        Digest digest = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
        if (digest == null) {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
        }
        this.cipher = new OAEPEncoding(new RSABlindedEngine(), digest, ((PSource.PSpecified)oAEPParameterSpec.getPSource()).getValue());
        this.paramSpec = oAEPParameterSpec;
    }

    protected int engineGetBlockSize() {
        try {
            return this.cipher.getInputBlockSize();
        } catch (NullPointerException nullPointerException) {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected int engineGetKeySize(Key key) {
        if (key instanceof RSAPrivateKey) {
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey)key;
            return rSAPrivateKey.getModulus().bitLength();
        }
        if (key instanceof RSAPublicKey) {
            RSAPublicKey rSAPublicKey = (RSAPublicKey)key;
            return rSAPublicKey.getModulus().bitLength();
        }
        throw new IllegalArgumentException("not an RSA key!");
    }

    protected int engineGetOutputSize(int n) {
        try {
            return this.cipher.getOutputBlockSize();
        } catch (NullPointerException nullPointerException) {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.paramSpec != null) {
            try {
                this.engineParams = this.helper.createAlgorithmParameters("OAEP");
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
        if (string2.equals("1")) {
            this.privateKeyOnly = true;
            this.publicKeyOnly = false;
            return;
        }
        if (string2.equals("2")) {
            this.privateKeyOnly = false;
            this.publicKeyOnly = true;
            return;
        }
        throw new NoSuchAlgorithmException("can't support mode " + string);
    }

    protected void engineSetPadding(String string) throws NoSuchPaddingException {
        String string2 = Strings.toUpperCase(string);
        if (string2.equals("NOPADDING")) {
            this.cipher = new RSABlindedEngine();
        } else if (string2.equals("PKCS1PADDING")) {
            this.cipher = new PKCS1Encoding(new RSABlindedEngine());
        } else if (string2.equals("ISO9796-1PADDING")) {
            this.cipher = new ISO9796d1Encoding(new RSABlindedEngine());
        } else if (string2.equals("OAEPWITHMD5ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPPADDING")) {
            this.initFromSpec(OAEPParameterSpec.DEFAULT);
        } else if (string2.equals("OAEPWITHSHA1ANDMGF1PADDING") || string2.equals("OAEPWITHSHA-1ANDMGF1PADDING")) {
            this.initFromSpec(OAEPParameterSpec.DEFAULT);
        } else if (string2.equals("OAEPWITHSHA224ANDMGF1PADDING") || string2.equals("OAEPWITHSHA-224ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA256ANDMGF1PADDING") || string2.equals("OAEPWITHSHA-256ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA384ANDMGF1PADDING") || string2.equals("OAEPWITHSHA-384ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA512ANDMGF1PADDING") || string2.equals("OAEPWITHSHA-512ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA3-224ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA3-224", "MGF1", new MGF1ParameterSpec("SHA3-224"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA3-256ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA3-256", "MGF1", new MGF1ParameterSpec("SHA3-256"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA3-384ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA3-384", "MGF1", new MGF1ParameterSpec("SHA3-384"), PSource.PSpecified.DEFAULT));
        } else if (string2.equals("OAEPWITHSHA3-512ANDMGF1PADDING")) {
            this.initFromSpec(new OAEPParameterSpec("SHA3-512", "MGF1", new MGF1ParameterSpec("SHA3-512"), PSource.PSpecified.DEFAULT));
        } else {
            throw new NoSuchPaddingException(string + " unavailable with RSA.");
        }
    }

    protected void engineInit(int n, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters cipherParameters;
        if (algorithmParameterSpec == null || algorithmParameterSpec instanceof OAEPParameterSpec) {
            if (key instanceof RSAPublicKey) {
                if (this.privateKeyOnly && n == 1) {
                    throw new InvalidKeyException("mode 1 requires RSAPrivateKey");
                }
                cipherParameters = RSAUtil.generatePublicKeyParameter((RSAPublicKey)key);
            } else if (key instanceof RSAPrivateKey) {
                if (this.publicKeyOnly && n == 1) {
                    throw new InvalidKeyException("mode 2 requires RSAPublicKey");
                }
                cipherParameters = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)key);
            } else {
                throw new InvalidKeyException("unknown key type passed to RSA");
            }
            if (algorithmParameterSpec != null) {
                OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec)algorithmParameterSpec;
                this.paramSpec = algorithmParameterSpec;
                if (!oAEPParameterSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !oAEPParameterSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1.getId())) {
                    throw new InvalidAlgorithmParameterException("unknown mask generation function specified");
                }
                if (!(oAEPParameterSpec.getMGFParameters() instanceof MGF1ParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("unkown MGF parameters");
                }
                Digest digest = DigestFactory.getDigest(oAEPParameterSpec.getDigestAlgorithm());
                if (digest == null) {
                    throw new InvalidAlgorithmParameterException("no match on digest algorithm: " + oAEPParameterSpec.getDigestAlgorithm());
                }
                MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)oAEPParameterSpec.getMGFParameters();
                Digest digest2 = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
                if (digest2 == null) {
                    throw new InvalidAlgorithmParameterException("no match on MGF digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
                }
                this.cipher = new OAEPEncoding(new RSABlindedEngine(), digest, digest2, ((PSource.PSpecified)oAEPParameterSpec.getPSource()).getValue());
            }
        } else {
            throw new InvalidAlgorithmParameterException("unknown parameter type: " + algorithmParameterSpec.getClass().getName());
        }
        if (!(this.cipher instanceof RSABlindedEngine)) {
            cipherParameters = secureRandom != null ? new ParametersWithRandom(cipherParameters, secureRandom) : new ParametersWithRandom(cipherParameters, new SecureRandom());
        }
        this.bOut.reset();
        switch (n) {
            case 1: 
            case 3: {
                this.cipher.init(true, cipherParameters);
                break;
            }
            case 2: 
            case 4: {
                this.cipher.init(false, cipherParameters);
                break;
            }
            default: {
                throw new InvalidParameterException("unknown opmode " + n + " passed to RSA");
            }
        }
    }

    protected void engineInit(int n, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        OAEPParameterSpec oAEPParameterSpec = null;
        if (algorithmParameters != null) {
            try {
                oAEPParameterSpec = algorithmParameters.getParameterSpec(OAEPParameterSpec.class);
            } catch (InvalidParameterSpecException invalidParameterSpecException) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + invalidParameterSpecException.toString(), invalidParameterSpecException);
            }
        }
        this.engineParams = algorithmParameters;
        this.engineInit(n, key, oAEPParameterSpec, secureRandom);
    }

    protected void engineInit(int n, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            this.engineInit(n, key, (AlgorithmParameterSpec)null, secureRandom);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidKeyException("Eeeek! " + invalidAlgorithmParameterException.toString(), invalidAlgorithmParameterException);
        }
    }

    protected byte[] engineUpdate(byte[] byArray, int n, int n2) {
        this.bOut.write(byArray, n, n2);
        if (this.cipher instanceof RSABlindedEngine ? this.bOut.size() > this.cipher.getInputBlockSize() + 1 : this.bOut.size() > this.cipher.getInputBlockSize()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        return null;
    }

    protected int engineUpdate(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        this.bOut.write(byArray, n, n2);
        if (this.cipher instanceof RSABlindedEngine ? this.bOut.size() > this.cipher.getInputBlockSize() + 1 : this.bOut.size() > this.cipher.getInputBlockSize()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        return 0;
    }

    protected byte[] engineDoFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        if (byArray != null) {
            this.bOut.write(byArray, n, n2);
        }
        if (this.cipher instanceof RSABlindedEngine ? this.bOut.size() > this.cipher.getInputBlockSize() + 1 : this.bOut.size() > this.cipher.getInputBlockSize()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        return this.getOutput();
    }

    protected int engineDoFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalBlockSizeException, BadPaddingException {
        if (byArray != null) {
            this.bOut.write(byArray, n, n2);
        }
        if (this.cipher instanceof RSABlindedEngine ? this.bOut.size() > this.cipher.getInputBlockSize() + 1 : this.bOut.size() > this.cipher.getInputBlockSize()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        byte[] byArray3 = this.getOutput();
        for (int i = 0; i != byArray3.length; ++i) {
            byArray2[n3 + i] = byArray3[i];
        }
        return byArray3.length;
    }

    private byte[] getOutput() throws BadPaddingException {
        try {
            byte[] byArray = this.bOut.toByteArray();
            byte[] byArray2 = this.cipher.processBlock(byArray, 0, byArray.length);
            return byArray2;
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new BadBlockException("unable to decrypt block", invalidCipherTextException);
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new BadBlockException("unable to decrypt block", arrayIndexOutOfBoundsException);
        } finally {
            this.bOut.reset();
        }
    }

    public static class ISO9796d1Padding
    extends CipherSpi {
        public ISO9796d1Padding() {
            super(new ISO9796d1Encoding(new RSABlindedEngine()));
        }
    }

    public static class NoPadding
    extends CipherSpi {
        public NoPadding() {
            super(new RSABlindedEngine());
        }
    }

    public static class OAEPPadding
    extends CipherSpi {
        public OAEPPadding() {
            super(OAEPParameterSpec.DEFAULT);
        }
    }

    public static class PKCS1v1_5Padding
    extends CipherSpi {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding_PrivateOnly
    extends CipherSpi {
        public PKCS1v1_5Padding_PrivateOnly() {
            super(false, true, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding_PublicOnly
    extends CipherSpi {
        public PKCS1v1_5Padding_PublicOnly() {
            super(true, false, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }
}

