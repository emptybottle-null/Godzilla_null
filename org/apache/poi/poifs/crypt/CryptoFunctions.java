/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

@Internal
public final class CryptoFunctions {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final int[] INITIAL_CODE_ARRAY = new int[]{57840, 7439, 52380, 33984, 4364, 3600, 61902, 12606, 6258, 57657, 54287, 34041, 10252, 43370, 20163};
    private static final byte[] PAD_ARRAY = new byte[]{-69, -1, -1, -70, -1, -1, -71, -128, 0, -66, 15, 0, -65, 15, 0};
    private static final int[][] ENCRYPTION_MATRIX = new int[][]{{44796, 19929, 39858, 10053, 20106, 40212, 10761}, {31585, 63170, 64933, 60267, 50935, 40399, 11199}, {17763, 35526, 1453, 2906, 5812, 11624, 23248}, {885, 1770, 3540, 7080, 14160, 28320, 56640}, {55369, 41139, 20807, 41614, 21821, 43642, 17621}, {28485, 56970, 44341, 19019, 38038, 14605, 29210}, {60195, 50791, 40175, 10751, 21502, 43004, 24537}, {18387, 36774, 3949, 7898, 15796, 31592, 63184}, {47201, 24803, 49606, 37805, 14203, 28406, 56812}, {17824, 35648, 1697, 3394, 6788, 13576, 27152}, {43601, 17539, 35078, 557, 1114, 2228, 4456}, {30388, 60776, 51953, 34243, 7079, 14158, 28316}, {14128, 28256, 56512, 43425, 17251, 34502, 7597}, {13105, 26210, 52420, 35241, 883, 1766, 3532}, {4129, 8258, 16516, 33032, 4657, 9314, 18628}};

    private CryptoFunctions() {
    }

    public static byte[] hashPassword(String password, HashAlgorithm hashAlgorithm, byte[] salt, int spinCount) {
        return CryptoFunctions.hashPassword(password, hashAlgorithm, salt, spinCount, true);
    }

    public static byte[] hashPassword(String password, HashAlgorithm hashAlgorithm, byte[] salt, int spinCount, boolean iteratorFirst) {
        if (password == null) {
            password = "VelvetSweatshop";
        }
        MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgorithm);
        hashAlg.update(salt);
        byte[] hash = hashAlg.digest(StringUtil.getToUnicodeLE(password));
        byte[] iterator = new byte[4];
        byte[] first = iteratorFirst ? iterator : hash;
        byte[] second = iteratorFirst ? hash : iterator;
        try {
            for (int i = 0; i < spinCount; ++i) {
                LittleEndian.putInt(iterator, 0, i);
                hashAlg.reset();
                hashAlg.update(first);
                hashAlg.update(second);
                hashAlg.digest(hash, 0, hash.length);
            }
        } catch (DigestException e) {
            throw new EncryptedDocumentException("error in password hashing");
        }
        return hash;
    }

    public static byte[] generateIv(HashAlgorithm hashAlgorithm, byte[] salt, byte[] blockKey, int blockSize) {
        byte[] iv = salt;
        if (blockKey != null) {
            MessageDigest hashAlgo = CryptoFunctions.getMessageDigest(hashAlgorithm);
            hashAlgo.update(salt);
            iv = hashAlgo.digest(blockKey);
        }
        return CryptoFunctions.getBlock36(iv, blockSize);
    }

    public static byte[] generateKey(byte[] passwordHash, HashAlgorithm hashAlgorithm, byte[] blockKey, int keySize) {
        MessageDigest hashAlgo = CryptoFunctions.getMessageDigest(hashAlgorithm);
        hashAlgo.update(passwordHash);
        byte[] key = hashAlgo.digest(blockKey);
        return CryptoFunctions.getBlock36(key, keySize);
    }

    public static Cipher getCipher(SecretKey key, CipherAlgorithm cipherAlgorithm, ChainingMode chain, byte[] vec, int cipherMode) {
        return CryptoFunctions.getCipher(key, cipherAlgorithm, chain, vec, cipherMode, null);
    }

    public static Cipher getCipher(Key key, CipherAlgorithm cipherAlgorithm, ChainingMode chain, byte[] vec, int cipherMode, String padding) {
        int keySizeInBytes = key.getEncoded().length;
        if (padding == null) {
            padding = "NoPadding";
        }
        try {
            Cipher cipher;
            if (Cipher.getMaxAllowedKeyLength(cipherAlgorithm.jceId) < keySizeInBytes * 8) {
                throw new EncryptedDocumentException("Export Restrictions in place - please install JCE Unlimited Strength Jurisdiction Policy files");
            }
            if (cipherAlgorithm == CipherAlgorithm.rc4) {
                cipher = Cipher.getInstance(cipherAlgorithm.jceId);
            } else if (cipherAlgorithm.needsBouncyCastle) {
                CryptoFunctions.registerBouncyCastle();
                cipher = Cipher.getInstance(cipherAlgorithm.jceId + "/" + chain.jceId + "/" + padding, "BC");
            } else {
                cipher = Cipher.getInstance(cipherAlgorithm.jceId + "/" + chain.jceId + "/" + padding);
            }
            if (vec == null) {
                cipher.init(cipherMode, key);
            } else {
                AlgorithmParameterSpec aps = cipherAlgorithm == CipherAlgorithm.rc2 ? new RC2ParameterSpec(key.getEncoded().length * 8, vec) : new IvParameterSpec(vec);
                cipher.init(cipherMode, key, aps);
            }
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    private static byte[] getBlock36(byte[] hash, int size) {
        return CryptoFunctions.getBlockX(hash, size, (byte)54);
    }

    public static byte[] getBlock0(byte[] hash, int size) {
        return CryptoFunctions.getBlockX(hash, size, (byte)0);
    }

    private static byte[] getBlockX(byte[] hash, int size, byte fill) {
        if (hash.length == size) {
            return hash;
        }
        byte[] result = IOUtils.safelyAllocate(size, 100000);
        Arrays.fill(result, fill);
        System.arraycopy(hash, 0, result, 0, Math.min(result.length, hash.length));
        return result;
    }

    public static MessageDigest getMessageDigest(HashAlgorithm hashAlgorithm) {
        try {
            if (hashAlgorithm.needsBouncyCastle) {
                CryptoFunctions.registerBouncyCastle();
                return MessageDigest.getInstance(hashAlgorithm.jceId, "BC");
            }
            return MessageDigest.getInstance(hashAlgorithm.jceId);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("hash algo not supported", e);
        }
    }

    public static Mac getMac(HashAlgorithm hashAlgorithm) {
        try {
            if (hashAlgorithm.needsBouncyCastle) {
                CryptoFunctions.registerBouncyCastle();
                return Mac.getInstance(hashAlgorithm.jceHmacId, "BC");
            }
            return Mac.getInstance(hashAlgorithm.jceHmacId);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("hmac algo not supported", e);
        }
    }

    public static void registerBouncyCastle() {
        if (Security.getProvider("BC") != null) {
            return;
        }
        try {
            ClassLoader cl = CryptoFunctions.class.getClassLoader();
            String bcProviderName = "org.bouncycastle.jce.provider.BouncyCastleProvider";
            Class<?> clazz = cl.loadClass(bcProviderName);
            Security.addProvider((Provider)clazz.newInstance());
        } catch (Exception e) {
            throw new EncryptedDocumentException("Only the BouncyCastle provider supports your encryption settings - please add it to the classpath.", e);
        }
    }

    public static int createXorVerifier1(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        byte[] arrByteChars = CryptoFunctions.toAnsiPassword(password);
        short verifier = 0;
        if (!password.isEmpty()) {
            for (int i = arrByteChars.length - 1; i >= 0; --i) {
                verifier = CryptoFunctions.rotateLeftBase15Bit(verifier);
                verifier = (short)(verifier ^ arrByteChars[i]);
            }
            verifier = CryptoFunctions.rotateLeftBase15Bit(verifier);
            verifier = (short)(verifier ^ arrByteChars.length);
            verifier = (short)(verifier ^ 0xCE4B);
        }
        return verifier & 0xFFFF;
    }

    public static int createXorVerifier2(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        byte[] generatedKey = new byte[4];
        int maxPasswordLength = 15;
        if (!password.isEmpty()) {
            password = password.substring(0, Math.min(password.length(), 15));
            byte[] arrByteChars = CryptoFunctions.toAnsiPassword(password);
            int highOrderWord = INITIAL_CODE_ARRAY[arrByteChars.length - 1];
            int line = 15 - arrByteChars.length;
            for (byte ch : arrByteChars) {
                for (int xor : ENCRYPTION_MATRIX[line++]) {
                    if ((ch & 1) == 1) {
                        highOrderWord ^= xor;
                    }
                    ch = (byte)(ch >>> 1);
                }
            }
            int verifier = CryptoFunctions.createXorVerifier1(password);
            LittleEndian.putShort(generatedKey, 0, (short)verifier);
            LittleEndian.putShort(generatedKey, 2, (short)highOrderWord);
        }
        return LittleEndian.getInt(generatedKey);
    }

    public static String xorHashPassword(String password) {
        int hashedPassword = CryptoFunctions.createXorVerifier2(password);
        return String.format(Locale.ROOT, "%1$08X", hashedPassword);
    }

    public static String xorHashPasswordReversed(String password) {
        int hashedPassword = CryptoFunctions.createXorVerifier2(password);
        return String.format(Locale.ROOT, "%1$02X%2$02X%3$02X%4$02X", hashedPassword & 0xFF, hashedPassword >>> 8 & 0xFF, hashedPassword >>> 16 & 0xFF, hashedPassword >>> 24 & 0xFF);
    }

    public static int createXorKey1(String password) {
        return CryptoFunctions.createXorVerifier2(password) >>> 16;
    }

    public static byte[] createXorArray1(String password) {
        if (password.length() > 15) {
            password = password.substring(0, 15);
        }
        byte[] passBytes = password.getBytes(StandardCharsets.US_ASCII);
        byte[] obfuscationArray = new byte[16];
        System.arraycopy(passBytes, 0, obfuscationArray, 0, passBytes.length);
        System.arraycopy(PAD_ARRAY, 0, obfuscationArray, passBytes.length, PAD_ARRAY.length - passBytes.length + 1);
        int xorKey = CryptoFunctions.createXorKey1(password);
        int nRotateSize = 2;
        byte[] baseKeyLE = new byte[]{(byte)(xorKey & 0xFF), (byte)(xorKey >>> 8 & 0xFF)};
        for (int i = 0; i < obfuscationArray.length; ++i) {
            int n = i;
            obfuscationArray[n] = (byte)(obfuscationArray[n] ^ baseKeyLE[i & 1]);
            obfuscationArray[i] = CryptoFunctions.rotateLeft(obfuscationArray[i], nRotateSize);
        }
        return obfuscationArray;
    }

    private static byte[] toAnsiPassword(String password) {
        byte[] arrByteChars = new byte[password.length()];
        for (int i = 0; i < password.length(); ++i) {
            char intTemp = password.charAt(i);
            byte lowByte = (byte)(intTemp & 0xFF);
            byte highByte = (byte)(intTemp >>> 8 & 0xFF);
            arrByteChars[i] = lowByte != 0 ? lowByte : highByte;
        }
        return arrByteChars;
    }

    private static byte rotateLeft(byte bits, int shift) {
        return (byte)((bits & 0xFF) << shift | (bits & 0xFF) >>> 8 - shift);
    }

    private static short rotateLeftBase15Bit(short verifier) {
        short intermediate1 = (short)((verifier & 0x4000) != 0 ? 1 : 0);
        short intermediate2 = (short)(verifier << 1 & Short.MAX_VALUE);
        return (short)(intermediate1 | intermediate2);
    }
}

