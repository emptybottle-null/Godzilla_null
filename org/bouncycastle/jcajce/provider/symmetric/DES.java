/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.engines.RFC3211WrapEngine;
import org.bouncycastle.crypto.generators.DESKeyGenerator;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.macs.CFBBlockCipherMac;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.macs.ISO9797Alg3Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

public final class DES {
    private DES() {
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            byte[] byArray = new byte[8];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(byArray);
            try {
                algorithmParameters = this.createParametersInstance("DES");
                algorithmParameters.init(new IvParameterSpec(byArray));
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
            return algorithmParameters;
        }
    }

    public static class CBC
    extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new DESEngine()), 64);
        }
    }

    public static class CBCMAC
    extends BaseMac {
        public CBCMAC() {
            super(new CBCBlockCipherMac(new DESEngine()));
        }
    }

    public static class CMAC
    extends BaseMac {
        public CMAC() {
            super(new CMac(new DESEngine()));
        }
    }

    public static class DES64
    extends BaseMac {
        public DES64() {
            super(new CBCBlockCipherMac((BlockCipher)new DESEngine(), 64));
        }
    }

    public static class DES64with7816d4
    extends BaseMac {
        public DES64with7816d4() {
            super(new CBCBlockCipherMac(new DESEngine(), 64, new ISO7816d4Padding()));
        }
    }

    public static class DES9797Alg3
    extends BaseMac {
        public DES9797Alg3() {
            super(new ISO9797Alg3Mac(new DESEngine()));
        }
    }

    public static class DES9797Alg3with7816d4
    extends BaseMac {
        public DES9797Alg3with7816d4() {
            super(new ISO9797Alg3Mac((BlockCipher)new DESEngine(), new ISO7816d4Padding()));
        }
    }

    public static class DESCFB8
    extends BaseMac {
        public DESCFB8() {
            super(new CFBBlockCipherMac(new DESEngine()));
        }
    }

    public static class DESPBEKeyFactory
    extends BaseSecretKeyFactory {
        private boolean forCipher;
        private int scheme;
        private int digest;
        private int keySize;
        private int ivSize;

        public DESPBEKeyFactory(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, int n, int n2, int n3, int n4) {
            super(string, aSN1ObjectIdentifier);
            this.forCipher = bl;
            this.scheme = n;
            this.digest = n2;
            this.keySize = n3;
            this.ivSize = n4;
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec)keySpec;
                if (pBEKeySpec.getSalt() == null) {
                    if (this.scheme == 0 || this.scheme == 4) {
                        return new PBKDF1Key(pBEKeySpec.getPassword(), this.scheme == 0 ? PasswordConverter.ASCII : PasswordConverter.UTF8);
                    }
                    return new BCPBEKey(this.algName, this.algOid, this.scheme, this.digest, this.keySize, this.ivSize, pBEKeySpec, null);
                }
                CipherParameters cipherParameters = this.forCipher ? PBE.Util.makePBEParameters(pBEKeySpec, this.scheme, this.digest, this.keySize, this.ivSize) : PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, this.digest, this.keySize);
                KeyParameter keyParameter = cipherParameters instanceof ParametersWithIV ? (KeyParameter)((ParametersWithIV)cipherParameters).getParameters() : (KeyParameter)cipherParameters;
                DESParameters.setOddParity(keyParameter.getKey());
                return new BCPBEKey(this.algName, this.algOid, this.scheme, this.digest, this.keySize, this.ivSize, pBEKeySpec, cipherParameters);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static class ECB
    extends BaseBlockCipher {
        public ECB() {
            super(new DESEngine());
        }
    }

    public static class KeyFactory
    extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("DES", null);
        }

        protected KeySpec engineGetKeySpec(SecretKey secretKey, Class clazz) throws InvalidKeySpecException {
            if (clazz == null) {
                throw new InvalidKeySpecException("keySpec parameter is null");
            }
            if (secretKey == null) {
                throw new InvalidKeySpecException("key parameter is null");
            }
            if (SecretKeySpec.class.isAssignableFrom(clazz)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.algName);
            }
            if (DESKeySpec.class.isAssignableFrom(clazz)) {
                byte[] byArray = secretKey.getEncoded();
                try {
                    return new DESKeySpec(byArray);
                } catch (Exception exception) {
                    throw new InvalidKeySpecException(exception.toString());
                }
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof DESKeySpec) {
                DESKeySpec dESKeySpec = (DESKeySpec)keySpec;
                return new SecretKeySpec(dESKeySpec.getKey(), "DES");
            }
            return super.engineGenerateSecret(keySpec);
        }
    }

    public static class KeyGenerator
    extends BaseKeyGenerator {
        public KeyGenerator() {
            super("DES", 64, new DESKeyGenerator());
        }

        protected void engineInit(int n, SecureRandom secureRandom) {
            super.engineInit(n, secureRandom);
        }

        protected SecretKey engineGenerateKey() {
            if (this.uninitialised) {
                this.engine.init(new KeyGenerationParameters(new SecureRandom(), this.defaultKeySize));
                this.uninitialised = false;
            }
            return new SecretKeySpec(this.engine.generateKey(), this.algName);
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = DES.class.getName();
        private static final String PACKAGE = "org.bouncycastle.jcajce.provider.symmetric";

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.DES", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", OIWObjectIdentifiers.desCBC, PREFIX + "$CBC");
            this.addAlias(configurableProvider, OIWObjectIdentifiers.desCBC, "DES");
            configurableProvider.addAlgorithm("Cipher.DESRFC3211WRAP", PREFIX + "$RFC3211");
            configurableProvider.addAlgorithm("KeyGenerator.DES", PREFIX + "$KeyGenerator");
            configurableProvider.addAlgorithm("SecretKeyFactory.DES", PREFIX + "$KeyFactory");
            configurableProvider.addAlgorithm("Mac.DESCMAC", PREFIX + "$CMAC");
            configurableProvider.addAlgorithm("Mac.DESMAC", PREFIX + "$CBCMAC");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DES", "DESMAC");
            configurableProvider.addAlgorithm("Mac.DESMAC/CFB8", PREFIX + "$DESCFB8");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DES/CFB8", "DESMAC/CFB8");
            configurableProvider.addAlgorithm("Mac.DESMAC64", PREFIX + "$DES64");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DES64", "DESMAC64");
            configurableProvider.addAlgorithm("Mac.DESMAC64WITHISO7816-4PADDING", PREFIX + "$DES64with7816d4");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DES64WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DESISO9797ALG1MACWITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DESISO9797ALG1WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.addAlgorithm("Mac.DESWITHISO9797", PREFIX + "$DES9797Alg3");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.DESISO9797MAC", "DESWITHISO9797");
            configurableProvider.addAlgorithm("Mac.ISO9797ALG3MAC", PREFIX + "$DES9797Alg3");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.ISO9797ALG3", "ISO9797ALG3MAC");
            configurableProvider.addAlgorithm("Mac.ISO9797ALG3WITHISO7816-4PADDING", PREFIX + "$DES9797Alg3with7816d4");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.ISO9797ALG3MACWITHISO7816-4PADDING", "ISO9797ALG3WITHISO7816-4PADDING");
            configurableProvider.addAlgorithm("AlgorithmParameters.DES", "org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", OIWObjectIdentifiers.desCBC, "DES");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.DES", PREFIX + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + OIWObjectIdentifiers.desCBC, "DES");
            configurableProvider.addAlgorithm("Cipher.PBEWITHMD2ANDDES", PREFIX + "$PBEWithMD2");
            configurableProvider.addAlgorithm("Cipher.PBEWITHMD5ANDDES", PREFIX + "$PBEWithMD5");
            configurableProvider.addAlgorithm("Cipher.PBEWITHSHA1ANDDES", PREFIX + "$PBEWithSHA1");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBEWITHMD2ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBEWITHMD5ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBEWITHSHA1ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBEWITHMD2ANDDES", PREFIX + "$PBEWithMD2KeyFactory");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBEWITHMD5ANDDES", PREFIX + "$PBEWithMD5KeyFactory");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBEWITHSHA1ANDDES", PREFIX + "$PBEWithSHA1KeyFactory");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBEWITHMD2ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBEWITHMD5ANDDES");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBEWITHSHA1ANDDES");
        }

        private void addAlias(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + aSN1ObjectIdentifier.getId(), string);
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier.getId(), string);
        }
    }

    public static class PBEWithMD2
    extends BaseBlockCipher {
        public PBEWithMD2() {
            super(new CBCBlockCipher(new DESEngine()), 0, 5, 64, 8);
        }
    }

    public static class PBEWithMD2KeyFactory
    extends DESPBEKeyFactory {
        public PBEWithMD2KeyFactory() {
            super("PBEwithMD2andDES", PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, true, 0, 5, 64, 64);
        }
    }

    public static class PBEWithMD5
    extends BaseBlockCipher {
        public PBEWithMD5() {
            super(new CBCBlockCipher(new DESEngine()), 0, 0, 64, 8);
        }
    }

    public static class PBEWithMD5KeyFactory
    extends DESPBEKeyFactory {
        public PBEWithMD5KeyFactory() {
            super("PBEwithMD5andDES", PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, true, 0, 0, 64, 64);
        }
    }

    public static class PBEWithSHA1
    extends BaseBlockCipher {
        public PBEWithSHA1() {
            super(new CBCBlockCipher(new DESEngine()), 0, 1, 64, 8);
        }
    }

    public static class PBEWithSHA1KeyFactory
    extends DESPBEKeyFactory {
        public PBEWithSHA1KeyFactory() {
            super("PBEwithSHA1andDES", PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, true, 0, 1, 64, 64);
        }
    }

    public static class RFC3211
    extends BaseWrapCipher {
        public RFC3211() {
            super(new RFC3211WrapEngine(new DESEngine()), 8);
        }
    }
}

