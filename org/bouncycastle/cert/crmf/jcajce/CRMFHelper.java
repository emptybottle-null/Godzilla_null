/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;

class CRMFHelper {
    protected static final Map BASE_CIPHER_NAMES = new HashMap();
    protected static final Map CIPHER_ALG_NAMES = new HashMap();
    protected static final Map DIGEST_ALG_NAMES = new HashMap();
    protected static final Map KEY_ALG_NAMES = new HashMap();
    protected static final Map MAC_ALG_NAMES = new HashMap();
    private JcaJceHelper helper;

    CRMFHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    PublicKey toPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws CRMFException {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded());
            AlgorithmIdentifier algorithmIdentifier = subjectPublicKeyInfo.getAlgorithm();
            return this.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(x509EncodedKeySpec);
        } catch (Exception exception) {
            throw new CRMFException("invalid key: " + exception.getMessage(), exception);
        }
    }

    Cipher createCipher(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
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
            throw new CRMFException("cannot create cipher: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    public KeyGenerator createKeyGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
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
            throw new CRMFException("cannot create key generator: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    Cipher createContentCipher(final Key key, final AlgorithmIdentifier algorithmIdentifier) throws CRMFException {
        return (Cipher)CRMFHelper.execute(new JCECallback(){

            public Object doInJCE() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
                Cipher cipher = CRMFHelper.this.createCipher(algorithmIdentifier.getAlgorithm());
                ASN1Primitive aSN1Primitive = (ASN1Primitive)algorithmIdentifier.getParameters();
                ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
                if (aSN1Primitive != null && !(aSN1Primitive instanceof ASN1Null)) {
                    try {
                        AlgorithmParameters algorithmParameters = CRMFHelper.this.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
                        try {
                            AlgorithmParametersUtils.loadParameters(algorithmParameters, aSN1Primitive);
                        } catch (IOException iOException) {
                            throw new CRMFException("error decoding algorithm parameters.", iOException);
                        }
                        cipher.init(2, key, algorithmParameters);
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        if (aSN1ObjectIdentifier.equals(CMSAlgorithm.DES_EDE3_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.IDEA_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.AES128_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.AES192_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.AES256_CBC)) {
                            cipher.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Primitive).getOctets()));
                        }
                        throw noSuchAlgorithmException;
                    }
                } else if (aSN1ObjectIdentifier.equals(CMSAlgorithm.DES_EDE3_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.IDEA_CBC) || aSN1ObjectIdentifier.equals(CMSAlgorithm.CAST5_CBC)) {
                    cipher.init(2, key, new IvParameterSpec(new byte[8]));
                } else {
                    cipher.init(2, key);
                }
                return cipher;
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

    KeyFactory createKeyFactory(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String string = (String)KEY_ALG_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createKeyFactory(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createKeyFactory(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CRMFException("cannot create cipher: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    MessageDigest createDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String string = (String)DIGEST_ALG_NAMES.get(aSN1ObjectIdentifier);
            if (string != null) {
                try {
                    return this.helper.createDigest(string);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    // empty catch block
                }
            }
            return this.helper.createDigest(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CRMFException("cannot create cipher: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    Mac createMac(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
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
            throw new CRMFException("cannot create mac: " + generalSecurityException.getMessage(), generalSecurityException);
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

    AlgorithmParameters generateParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, SecureRandom secureRandom) throws CRMFException {
        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = this.createAlgorithmParameterGenerator(aSN1ObjectIdentifier);
            if (aSN1ObjectIdentifier.equals(CMSAlgorithm.RC2_CBC)) {
                byte[] byArray = new byte[8];
                secureRandom.nextBytes(byArray);
                try {
                    algorithmParameterGenerator.init(new RC2ParameterSpec(secretKey.getEncoded().length * 8, byArray), secureRandom);
                } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
                    throw new CRMFException("parameters generation error: " + invalidAlgorithmParameterException, invalidAlgorithmParameterException);
                }
            }
            return algorithmParameterGenerator.generateParameters();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return null;
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CRMFException("exception creating algorithm parameter generator: " + generalSecurityException, generalSecurityException);
        }
    }

    AlgorithmIdentifier getAlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameters algorithmParameters) throws CRMFException {
        ASN1Encodable aSN1Encodable;
        if (algorithmParameters != null) {
            try {
                aSN1Encodable = AlgorithmParametersUtils.extractParameters(algorithmParameters);
            } catch (IOException iOException) {
                throw new CRMFException("cannot encode parameters: " + iOException.getMessage(), iOException);
            }
        } else {
            aSN1Encodable = DERNull.INSTANCE;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, aSN1Encodable);
    }

    static Object execute(JCECallback jCECallback) throws CRMFException {
        try {
            return jCECallback.doInJCE();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new CRMFException("can't find algorithm.", noSuchAlgorithmException);
        } catch (InvalidKeyException invalidKeyException) {
            throw new CRMFException("key invalid in message.", invalidKeyException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new CRMFException("can't find provider.", noSuchProviderException);
        } catch (NoSuchPaddingException noSuchPaddingException) {
            throw new CRMFException("required padding not supported.", noSuchPaddingException);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new CRMFException("algorithm parameters invalid.", invalidAlgorithmParameterException);
        } catch (InvalidParameterSpecException invalidParameterSpecException) {
            throw new CRMFException("MAC algorithm parameter spec invalid.", invalidParameterSpecException);
        }
    }

    static {
        BASE_CIPHER_NAMES.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()), "RSA/ECB/PKCS1Padding");
        DIGEST_ALG_NAMES.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        MAC_ALG_NAMES.put(IANAObjectIdentifiers.hmacSHA1, "HMACSHA1");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "HMACSHA1");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "HMACSHA224");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "HMACSHA256");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "HMACSHA384");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "HMACSHA512");
        KEY_ALG_NAMES.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        KEY_ALG_NAMES.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    static interface JCECallback {
        public Object doInJCE() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;
    }
}

