/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;

class PEMUtilities {
    private static final Map KEYSIZES = new HashMap();
    private static final Set PKCS5_SCHEME_1 = new HashSet();
    private static final Set PKCS5_SCHEME_2 = new HashSet();
    private static final Map PRFS = new HashMap();
    private static final Map PRFS_SALT = new HashMap();

    PEMUtilities() {
    }

    static int getKeySize(String string) {
        if (!KEYSIZES.containsKey(string)) {
            throw new IllegalStateException("no key size for algorithm: " + string);
        }
        return (Integer)KEYSIZES.get(string);
    }

    static int getSaltSize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (!PRFS_SALT.containsKey(aSN1ObjectIdentifier)) {
            throw new IllegalStateException("no salt size for algorithm: " + aSN1ObjectIdentifier);
        }
        return (Integer)PRFS_SALT.get(aSN1ObjectIdentifier);
    }

    static boolean isHmacSHA1(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier == null || algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_hmacWithSHA1);
    }

    static boolean isPKCS5Scheme1(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    static boolean isPKCS5Scheme2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String string, char[] cArray, byte[] byArray, int n) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = jcaJceHelper.createSecretKeyFactory("PBKDF2with8BIT");
        SecretKey secretKey = secretKeyFactory.generateSecret(new PBEKeySpec(cArray, byArray, n, PEMUtilities.getKeySize(string)));
        return new SecretKeySpec(secretKey.getEncoded(), string);
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String string, char[] cArray, byte[] byArray, int n, AlgorithmIdentifier algorithmIdentifier) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        String string2 = (String)PRFS.get(algorithmIdentifier.getAlgorithm());
        if (string2 == null) {
            throw new NoSuchAlgorithmException("unknown PRF in PKCS#2: " + algorithmIdentifier.getAlgorithm());
        }
        SecretKeyFactory secretKeyFactory = jcaJceHelper.createSecretKeyFactory(string2);
        SecretKey secretKey = secretKeyFactory.generateSecret(new PBEKeySpec(cArray, byArray, n, PEMUtilities.getKeySize(string)));
        return new SecretKeySpec(secretKey.getEncoded(), string);
    }

    static byte[] crypt(boolean bl, JcaJceHelper jcaJceHelper, byte[] byArray, char[] cArray, String string, byte[] byArray2) throws PEMException {
        SecretKey secretKey;
        String string2;
        AlgorithmParameterSpec algorithmParameterSpec = new IvParameterSpec(byArray2);
        String string3 = "CBC";
        String string4 = "PKCS5Padding";
        if (string.endsWith("-CFB")) {
            string3 = "CFB";
            string4 = "NoPadding";
        }
        if (string.endsWith("-ECB") || "DES-EDE".equals(string) || "DES-EDE3".equals(string)) {
            string3 = "ECB";
            algorithmParameterSpec = null;
        }
        if (string.endsWith("-OFB")) {
            string3 = "OFB";
            string4 = "NoPadding";
        }
        if (string.startsWith("DES-EDE")) {
            string2 = "DESede";
            boolean bl2 = !string.startsWith("DES-EDE3");
            secretKey = PEMUtilities.getKey(jcaJceHelper, cArray, string2, 24, byArray2, bl2);
        } else if (string.startsWith("DES-")) {
            string2 = "DES";
            secretKey = PEMUtilities.getKey(jcaJceHelper, cArray, string2, 8, byArray2);
        } else if (string.startsWith("BF-")) {
            string2 = "Blowfish";
            secretKey = PEMUtilities.getKey(jcaJceHelper, cArray, string2, 16, byArray2);
        } else if (string.startsWith("RC2-")) {
            string2 = "RC2";
            int n = 128;
            if (string.startsWith("RC2-40-")) {
                n = 40;
            } else if (string.startsWith("RC2-64-")) {
                n = 64;
            }
            secretKey = PEMUtilities.getKey(jcaJceHelper, cArray, string2, n / 8, byArray2);
            algorithmParameterSpec = algorithmParameterSpec == null ? new RC2ParameterSpec(n) : new RC2ParameterSpec(n, byArray2);
        } else if (string.startsWith("AES-")) {
            int n;
            string2 = "AES";
            byte[] byArray3 = byArray2;
            if (byArray3.length > 8) {
                byArray3 = new byte[8];
                System.arraycopy(byArray2, 0, byArray3, 0, 8);
            }
            if (string.startsWith("AES-128-")) {
                n = 128;
            } else if (string.startsWith("AES-192-")) {
                n = 192;
            } else if (string.startsWith("AES-256-")) {
                n = 256;
            } else {
                throw new EncryptionException("unknown AES encryption with private key");
            }
            secretKey = PEMUtilities.getKey(jcaJceHelper, cArray, "AES", n / 8, byArray3);
        } else {
            throw new EncryptionException("unknown encryption with private key");
        }
        String string5 = string2 + "/" + string3 + "/" + string4;
        try {
            int n;
            Cipher cipher = jcaJceHelper.createCipher(string5);
            int n2 = n = bl ? 1 : 2;
            if (algorithmParameterSpec == null) {
                cipher.init(n, secretKey);
            } else {
                cipher.init(n, (Key)secretKey, algorithmParameterSpec);
            }
            return cipher.doFinal(byArray);
        } catch (Exception exception) {
            throw new EncryptionException("exception using cipher - please check password and data.", (Throwable)exception);
        }
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArray, String string, int n, byte[] byArray) throws PEMException {
        return PEMUtilities.getKey(jcaJceHelper, cArray, string, n, byArray, false);
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArray, String string, int n, byte[] byArray, boolean bl) throws PEMException {
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArray, byArray, 1, n * 8);
            SecretKeyFactory secretKeyFactory = jcaJceHelper.createSecretKeyFactory("PBKDF-OpenSSL");
            byte[] byArray2 = secretKeyFactory.generateSecret(pBEKeySpec).getEncoded();
            if (bl && byArray2.length >= 24) {
                System.arraycopy(byArray2, 0, byArray2, 16, 8);
            }
            return new SecretKeySpec(byArray2, string);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new PEMException("Unable to create OpenSSL PBDKF: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }

    static {
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.id_PBES2);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes128_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes192_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes256_CBC);
        KEYSIZES.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "PBKDF2withHMACSHA1");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "PBKDF2withHMACSHA256");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "PBKDF2withHMACSHA512");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "PBKDF2withHMACSHA224");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "PBKDF2withHMACSHA384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, "PBKDF2withHMACSHA3-224");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, "PBKDF2withHMACSHA3-256");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, "PBKDF2withHMACSHA3-384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, "PBKDF2withHMACSHA3-512");
        PRFS.put(CryptoProObjectIdentifiers.gostR3411Hmac, "PBKDF2withHMACGOST3411");
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(20));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(32));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(64));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(28));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(48));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(28));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(32));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(48));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(64));
        PRFS_SALT.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(32));
    }
}

