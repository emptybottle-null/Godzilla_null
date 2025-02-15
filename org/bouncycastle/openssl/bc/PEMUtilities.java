/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.bc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;

class PEMUtilities {
    private static final Map KEYSIZES = new HashMap();
    private static final Set PKCS5_SCHEME_1 = new HashSet();
    private static final Set PKCS5_SCHEME_2 = new HashSet();

    PEMUtilities() {
    }

    static int getKeySize(String string) {
        if (!KEYSIZES.containsKey(string)) {
            throw new IllegalStateException("no key size for algorithm: " + string);
        }
        return (Integer)KEYSIZES.get(string);
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

    public static KeyParameter generateSecretKeyForPKCS5Scheme2(String string, char[] cArray, byte[] byArray, int n) {
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA1Digest());
        pKCS5S2ParametersGenerator.init(PBEParametersGenerator.PKCS5PasswordToBytes(cArray), byArray, n);
        return (KeyParameter)((PBEParametersGenerator)pKCS5S2ParametersGenerator).generateDerivedParameters(PEMUtilities.getKeySize(string));
    }

    static byte[] crypt(boolean bl, byte[] byArray, char[] cArray, String string, byte[] byArray2) throws PEMException {
        BlockCipher blockCipher;
        KeyParameter keyParameter;
        byte[] byArray3 = byArray2;
        String string2 = "CBC";
        PKCS7Padding pKCS7Padding = new PKCS7Padding();
        if (string.endsWith("-CFB")) {
            string2 = "CFB";
            pKCS7Padding = null;
        }
        if (string.endsWith("-ECB") || "DES-EDE".equals(string) || "DES-EDE3".equals(string)) {
            string2 = "ECB";
            byArray3 = null;
        }
        if (string.endsWith("-OFB")) {
            string2 = "OFB";
            pKCS7Padding = null;
        }
        if (string.startsWith("DES-EDE")) {
            boolean bl2 = !string.startsWith("DES-EDE3");
            keyParameter = PEMUtilities.getKey(cArray, 24, byArray2, bl2);
            blockCipher = new DESedeEngine();
        } else if (string.startsWith("DES-")) {
            keyParameter = PEMUtilities.getKey(cArray, 8, byArray2);
            blockCipher = new DESEngine();
        } else if (string.startsWith("BF-")) {
            keyParameter = PEMUtilities.getKey(cArray, 16, byArray2);
            blockCipher = new BlowfishEngine();
        } else if (string.startsWith("RC2-")) {
            int n = 128;
            if (string.startsWith("RC2-40-")) {
                n = 40;
            } else if (string.startsWith("RC2-64-")) {
                n = 64;
            }
            keyParameter = new RC2Parameters(PEMUtilities.getKey(cArray, n / 8, byArray2).getKey(), n);
            blockCipher = new RC2Engine();
        } else if (string.startsWith("AES-")) {
            int n;
            byte[] byArray4 = byArray2;
            if (byArray4.length > 8) {
                byArray4 = new byte[8];
                System.arraycopy(byArray2, 0, byArray4, 0, 8);
            }
            if (string.startsWith("AES-128-")) {
                n = 128;
            } else if (string.startsWith("AES-192-")) {
                n = 192;
            } else if (string.startsWith("AES-256-")) {
                n = 256;
            } else {
                throw new EncryptionException("unknown AES encryption with private key: " + string);
            }
            keyParameter = PEMUtilities.getKey(cArray, n / 8, byArray4);
            blockCipher = new AESEngine();
        } else {
            throw new EncryptionException("unknown encryption with private key: " + string);
        }
        if (string2.equals("CBC")) {
            blockCipher = new CBCBlockCipher(blockCipher);
        } else if (string2.equals("CFB")) {
            blockCipher = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
        } else if (string2.equals("OFB")) {
            blockCipher = new OFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
        }
        try {
            BufferedBlockCipher bufferedBlockCipher = pKCS7Padding == null ? new BufferedBlockCipher(blockCipher) : new PaddedBufferedBlockCipher(blockCipher, pKCS7Padding);
            if (byArray3 == null) {
                bufferedBlockCipher.init(bl, keyParameter);
            } else {
                bufferedBlockCipher.init(bl, new ParametersWithIV(keyParameter, byArray3));
            }
            byte[] byArray5 = new byte[bufferedBlockCipher.getOutputSize(byArray.length)];
            int n = bufferedBlockCipher.processBytes(byArray, 0, byArray.length, byArray5, 0);
            n += bufferedBlockCipher.doFinal(byArray5, n);
            if (n == byArray5.length) {
                return byArray5;
            }
            byte[] byArray6 = new byte[n];
            System.arraycopy(byArray5, 0, byArray6, 0, n);
            return byArray6;
        } catch (Exception exception) {
            throw new EncryptionException("exception using cipher - please check password and data.", (Throwable)exception);
        }
    }

    private static KeyParameter getKey(char[] cArray, int n, byte[] byArray) throws PEMException {
        return PEMUtilities.getKey(cArray, n, byArray, false);
    }

    private static KeyParameter getKey(char[] cArray, int n, byte[] byArray, boolean bl) throws PEMException {
        OpenSSLPBEParametersGenerator openSSLPBEParametersGenerator = new OpenSSLPBEParametersGenerator();
        openSSLPBEParametersGenerator.init(PBEParametersGenerator.PKCS5PasswordToBytes(cArray), byArray, 1);
        KeyParameter keyParameter = (KeyParameter)((PBEParametersGenerator)openSSLPBEParametersGenerator).generateDerivedParameters(n * 8);
        if (bl && keyParameter.getKey().length == 24) {
            byte[] byArray2 = keyParameter.getKey();
            System.arraycopy(byArray2, 0, byArray2, 16, 8);
            return new KeyParameter(byArray2);
        }
        return keyParameter;
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
    }
}

