/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RC2CBCParameter;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.JcaJceExtHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;

public class EnvelopedDataHelper {
    protected static final SecretKeySizeProvider KEY_SIZE_PROVIDER = DefaultSecretKeySizeProvider.INSTANCE;
    protected static final Map BASE_CIPHER_NAMES = new HashMap();
    protected static final Map CIPHER_ALG_NAMES = new HashMap();
    protected static final Map MAC_ALG_NAMES = new HashMap();
    private static final Map PBKDF2_ALG_NAMES = new HashMap();
    private static final short[] rc2Table;
    private static final short[] rc2Ekb;
    private JcaJceExtHelper helper;

    EnvelopedDataHelper(JcaJceExtHelper jcaJceExtHelper) {
        this.helper = jcaJceExtHelper;
    }

    String getBaseCipherName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (string == null) {
            return aSN1ObjectIdentifier.getId();
        }
        return string;
    }

    Key getJceKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return (Key)genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new SecretKeySpec((byte[])genericKey.getRepresentation(), "ENC");
        }
        throw new IllegalArgumentException("unknown generic key type");
    }

    public Key getJceKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return (Key)genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new SecretKeySpec((byte[])genericKey.getRepresentation(), this.getBaseCipherName(aSN1ObjectIdentifier));
        }
        throw new IllegalArgumentException("unknown generic key type");
    }

    public void keySizeCheck(AlgorithmIdentifier algorithmIdentifier, Key key) throws CMSException {
        int n = KEY_SIZE_PROVIDER.getKeySize(algorithmIdentifier);
        if (n > 0) {
            byte[] byArray = null;
            try {
                byArray = key.getEncoded();
            } catch (Exception exception) {
                // empty catch block
            }
            if (byArray != null && byArray.length * 8 != n) {
                throw new CMSException("Expected key size for algorithm OID not found in recipient.");
            }
        }
    }

    Cipher createCipher(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)CIPHER_ALG_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createCipher(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create cipher: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    Mac createMac(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)MAC_ALG_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createMac(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createMac(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create mac: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    Cipher createRFC3211Wrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (string == null) {
            throw new CMSException("no name for " + aSN1ObjectIdentifier);
        }
        string = string + "RFC3211Wrap";
        try {
            return this.helper.createCipher(string);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create cipher: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    KeyAgreement createKeyAgreement(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createKeyAgreement(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createKeyAgreement(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create key agreement: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    AlgorithmParameterGenerator createAlgorithmParameterGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws GeneralSecurityException {
        String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (string != null) {
            try {
                return this.helper.createAlgorithmParameterGenerator(string);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                // empty catch block
            }
        }
        return this.helper.createAlgorithmParameterGenerator(aSN1ObjectIdentifier.getId());
    }

    public Cipher createContentCipher(final Key key, final AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        return (Cipher)EnvelopedDataHelper.execute(new JCECallback(){

            public Object doInJCE() throws CMSException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
                Cipher cipher = EnvelopedDataHelper.this.createCipher(algorithmIdentifier.getAlgorithm());
                ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
                String string = algorithmIdentifier.getAlgorithm().getId();
                if (aSN1Encodable != null && !(aSN1Encodable instanceof ASN1Null)) {
                    try {
                        AlgorithmParameters algorithmParameters = EnvelopedDataHelper.this.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
                        CMSUtils.loadParameters(algorithmParameters, aSN1Encodable);
                        cipher.init(2, key, algorithmParameters);
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        if (string.equals(CMSAlgorithm.DES_CBC.getId()) || string.equals(CMSEnvelopedDataGenerator.DES_EDE3_CBC) || string.equals("1.3.6.1.4.1.188.7.1.1.2") || string.equals(CMSEnvelopedDataGenerator.AES128_CBC) || string.equals(CMSEnvelopedDataGenerator.AES192_CBC) || string.equals(CMSEnvelopedDataGenerator.AES256_CBC)) {
                            cipher.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Encodable).getOctets()));
                        }
                        throw noSuchAlgorithmException;
                    }
                } else if (string.equals(CMSAlgorithm.DES_CBC.getId()) || string.equals(CMSEnvelopedDataGenerator.DES_EDE3_CBC) || string.equals("1.3.6.1.4.1.188.7.1.1.2") || string.equals("1.2.840.113533.7.66.10")) {
                    cipher.init(2, key, new IvParameterSpec(new byte[8]));
                } else {
                    cipher.init(2, key);
                }
                return cipher;
            }
        });
    }

    Mac createContentMac(final Key key, final AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        return (Mac)EnvelopedDataHelper.execute(new JCECallback(){

            public Object doInJCE() throws CMSException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
                Mac mac = EnvelopedDataHelper.this.createMac(algorithmIdentifier.getAlgorithm());
                ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
                String string = algorithmIdentifier.getAlgorithm().getId();
                if (aSN1Encodable != null && !(aSN1Encodable instanceof ASN1Null)) {
                    AlgorithmParameters algorithmParameters = EnvelopedDataHelper.this.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
                    CMSUtils.loadParameters(algorithmParameters, aSN1Encodable);
                    mac.init(key, algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class));
                } else {
                    mac.init(key);
                }
                return mac;
            }
        });
    }

    AlgorithmParameters createAlgorithmParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (string != null) {
            try {
                return this.helper.createAlgorithmParameters(string);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                // empty catch block
            }
        }
        return this.helper.createAlgorithmParameters(aSN1ObjectIdentifier.getId());
    }

    KeyPairGenerator createKeyPairGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createKeyPairGenerator(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createKeyPairGenerator(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create key pair generator: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    public KeyGenerator createKeyGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createKeyGenerator(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createKeyGenerator(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create key generator: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    AlgorithmParameters generateParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, SecureRandom secureRandom) throws CMSException {
        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = this.createAlgorithmParameterGenerator(aSN1ObjectIdentifier);
            if (aSN1ObjectIdentifier.equals(CMSAlgorithm.RC2_CBC)) {
                byte[] byArray = new byte[8];
                secureRandom.nextBytes(byArray);
                try {
                    algorithmParameterGenerator.init(new RC2ParameterSpec(secretKey.getEncoded().length * 8, byArray), secureRandom);
                } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
                    throw new CMSException("parameters generation error: " + invalidAlgorithmParameterException, invalidAlgorithmParameterException);
                }
            }
            return algorithmParameterGenerator.generateParameters();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return null;
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("exception creating algorithm parameter generator: " + generalSecurityException, generalSecurityException);
        }
    }

    AlgorithmIdentifier getAlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameters algorithmParameters) throws CMSException {
        ASN1Encodable aSN1Encodable = algorithmParameters != null ? CMSUtils.extractParameters(algorithmParameters) : DERNull.INSTANCE;
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, aSN1Encodable);
    }

    static Object execute(JCECallback jCECallback) throws CMSException {
        try {
            return jCECallback.doInJCE();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new CMSException("can't find algorithm.", noSuchAlgorithmException);
        } catch (InvalidKeyException invalidKeyException) {
            throw new CMSException("key invalid in message.", invalidKeyException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new CMSException("can't find provider.", noSuchProviderException);
        } catch (NoSuchPaddingException noSuchPaddingException) {
            throw new CMSException("required padding not supported.", noSuchPaddingException);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new CMSException("algorithm parameters invalid.", invalidAlgorithmParameterException);
        } catch (InvalidParameterSpecException invalidParameterSpecException) {
            throw new CMSException("MAC algorithm parameter spec invalid.", invalidParameterSpecException);
        }
    }

    public KeyFactory createKeyFactory(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            String string = (String)BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createKeyFactory(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createKeyFactory(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("cannot create key factory: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return this.helper.createAsymmetricUnwrapper(algorithmIdentifier, privateKey);
    }

    public JceKTSKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] byArray, byte[] byArray2) {
        return this.helper.createAsymmetricUnwrapper(algorithmIdentifier, privateKey, byArray, byArray2);
    }

    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return this.helper.createSymmetricUnwrapper(algorithmIdentifier, secretKey);
    }

    public AlgorithmIdentifier getAlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            return new AlgorithmIdentifier(aSN1ObjectIdentifier, new DEROctetString(((IvParameterSpec)algorithmParameterSpec).getIV()));
        }
        if (algorithmParameterSpec instanceof RC2ParameterSpec) {
            RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec)algorithmParameterSpec;
            int n = ((RC2ParameterSpec)algorithmParameterSpec).getEffectiveKeyBits();
            if (n != -1) {
                int n2 = n < 256 ? rc2Table[n] : n;
                return new AlgorithmIdentifier(aSN1ObjectIdentifier, new RC2CBCParameter(n2, rC2ParameterSpec.getIV()));
            }
            return new AlgorithmIdentifier(aSN1ObjectIdentifier, new RC2CBCParameter(rC2ParameterSpec.getIV()));
        }
        throw new IllegalStateException("unknown parameter spec: " + algorithmParameterSpec);
    }

    SecretKeyFactory createSecretKeyFactory(String string) throws NoSuchProviderException, NoSuchAlgorithmException {
        return this.helper.createSecretKeyFactory(string);
    }

    byte[] calculateDerivedKey(int n, char[] cArray, AlgorithmIdentifier algorithmIdentifier, int n2) throws CMSException {
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(algorithmIdentifier.getParameters());
        try {
            SecretKeyFactory secretKeyFactory = n == 0 ? this.helper.createSecretKeyFactory("PBKDF2with8BIT") : this.helper.createSecretKeyFactory((String)PBKDF2_ALG_NAMES.get(pBKDF2Params.getPrf()));
            SecretKey secretKey = secretKeyFactory.generateSecret(new PBEKeySpec(cArray, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), n2));
            return secretKey.getEncoded();
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CMSException("Unable to calculate derived key from password: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    static {
        BASE_CIPHER_NAMES.put(CMSAlgorithm.DES_CBC, "DES");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.AES128_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.AES192_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.AES256_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.RC2_CBC, "RC2");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.CAST5_CBC, "CAST5");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.CAMELLIA128_CBC, "Camellia");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.CAMELLIA192_CBC, "Camellia");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.CAMELLIA256_CBC, "Camellia");
        BASE_CIPHER_NAMES.put(CMSAlgorithm.SEED_CBC, "SEED");
        BASE_CIPHER_NAMES.put(PKCSObjectIdentifiers.rc4, "RC4");
        BASE_CIPHER_NAMES.put(CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.DES_CBC, "DES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.RC2_CBC, "RC2/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.CAST5_CBC, "CAST5/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.CAMELLIA128_CBC, "Camellia/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.CAMELLIA192_CBC, "Camellia/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.CAMELLIA256_CBC, "Camellia/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.SEED_CBC, "SEED/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(PKCSObjectIdentifiers.rc4, "RC4");
        MAC_ALG_NAMES.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDEMac");
        MAC_ALG_NAMES.put(CMSAlgorithm.AES128_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSAlgorithm.AES192_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSAlgorithm.AES256_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSAlgorithm.RC2_CBC, "RC2Mac");
        PBKDF2_ALG_NAMES.put(PasswordRecipient.PRF.HMacSHA1.getAlgorithmID(), "PBKDF2WITHHMACSHA1");
        PBKDF2_ALG_NAMES.put(PasswordRecipient.PRF.HMacSHA224.getAlgorithmID(), "PBKDF2WITHHMACSHA224");
        PBKDF2_ALG_NAMES.put(PasswordRecipient.PRF.HMacSHA256.getAlgorithmID(), "PBKDF2WITHHMACSHA256");
        PBKDF2_ALG_NAMES.put(PasswordRecipient.PRF.HMacSHA384.getAlgorithmID(), "PBKDF2WITHHMACSHA384");
        PBKDF2_ALG_NAMES.put(PasswordRecipient.PRF.HMacSHA512.getAlgorithmID(), "PBKDF2WITHHMACSHA512");
        rc2Table = new short[]{189, 86, 234, 242, 162, 241, 172, 42, 176, 147, 209, 156, 27, 51, 253, 208, 48, 4, 182, 220, 125, 223, 50, 75, 247, 203, 69, 155, 49, 187, 33, 90, 65, 159, 225, 217, 74, 77, 158, 218, 160, 104, 44, 195, 39, 95, 128, 54, 62, 238, 251, 149, 26, 254, 206, 168, 52, 169, 19, 240, 166, 63, 216, 12, 120, 36, 175, 35, 82, 193, 103, 23, 245, 102, 144, 231, 232, 7, 184, 96, 72, 230, 30, 83, 243, 146, 164, 114, 140, 8, 21, 110, 134, 0, 132, 250, 244, 127, 138, 66, 25, 246, 219, 205, 20, 141, 80, 18, 186, 60, 6, 78, 236, 179, 53, 17, 161, 136, 142, 43, 148, 153, 183, 113, 116, 211, 228, 191, 58, 222, 150, 14, 188, 10, 237, 119, 252, 55, 107, 3, 121, 137, 98, 198, 215, 192, 210, 124, 106, 139, 34, 163, 91, 5, 93, 2, 117, 213, 97, 227, 24, 143, 85, 81, 173, 31, 11, 94, 133, 229, 194, 87, 99, 202, 61, 108, 180, 197, 204, 112, 178, 145, 89, 13, 71, 32, 200, 79, 88, 224, 1, 226, 22, 56, 196, 111, 59, 15, 101, 70, 190, 126, 45, 123, 130, 249, 64, 181, 29, 115, 248, 235, 38, 199, 135, 151, 37, 84, 177, 40, 170, 152, 157, 165, 100, 109, 122, 212, 16, 129, 68, 239, 73, 214, 174, 46, 221, 118, 92, 47, 167, 28, 201, 9, 105, 154, 131, 207, 41, 57, 185, 233, 76, 255, 67, 171};
        rc2Ekb = new short[]{93, 190, 155, 139, 17, 153, 110, 77, 89, 243, 133, 166, 63, 183, 131, 197, 228, 115, 107, 58, 104, 90, 192, 71, 160, 100, 52, 12, 241, 208, 82, 165, 185, 30, 150, 67, 65, 216, 212, 44, 219, 248, 7, 119, 42, 202, 235, 239, 16, 28, 22, 13, 56, 114, 47, 137, 193, 249, 128, 196, 109, 174, 48, 61, 206, 32, 99, 254, 230, 26, 199, 184, 80, 232, 36, 23, 252, 37, 111, 187, 106, 163, 68, 83, 217, 162, 1, 171, 188, 182, 31, 152, 238, 154, 167, 45, 79, 158, 142, 172, 224, 198, 73, 70, 41, 244, 148, 138, 175, 225, 91, 195, 179, 123, 87, 209, 124, 156, 237, 135, 64, 140, 226, 203, 147, 20, 201, 97, 46, 229, 204, 246, 94, 168, 92, 214, 117, 141, 98, 149, 88, 105, 118, 161, 74, 181, 85, 9, 120, 51, 130, 215, 221, 121, 245, 27, 11, 222, 38, 33, 40, 116, 4, 151, 86, 223, 60, 240, 55, 57, 220, 255, 6, 164, 234, 66, 8, 218, 180, 113, 176, 207, 18, 122, 78, 250, 108, 29, 132, 0, 200, 127, 145, 69, 170, 43, 194, 177, 143, 213, 186, 242, 173, 25, 178, 103, 54, 247, 15, 10, 146, 125, 227, 157, 233, 144, 62, 35, 39, 102, 19, 236, 129, 21, 189, 34, 191, 159, 126, 169, 81, 75, 76, 251, 2, 211, 112, 134, 49, 231, 59, 5, 3, 84, 96, 72, 101, 24, 210, 205, 95, 50, 136, 14, 53, 253};
    }

    static interface JCECallback {
        public Object doInJCE() throws CMSException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;
    }
}

