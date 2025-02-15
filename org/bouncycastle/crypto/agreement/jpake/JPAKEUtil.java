/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;

public class JPAKEUtil {
    static final BigInteger ZERO = BigInteger.valueOf(0L);
    static final BigInteger ONE = BigInteger.valueOf(1L);

    public static BigInteger generateX1(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2 = ZERO;
        BigInteger bigInteger3 = bigInteger.subtract(ONE);
        return BigIntegers.createRandomInRange(bigInteger2, bigInteger3, secureRandom);
    }

    public static BigInteger generateX2(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2 = ONE;
        BigInteger bigInteger3 = bigInteger.subtract(ONE);
        return BigIntegers.createRandomInRange(bigInteger2, bigInteger3, secureRandom);
    }

    public static BigInteger calculateS(char[] cArray) {
        return new BigInteger(Strings.toUTF8ByteArray(cArray));
    }

    public static BigInteger calculateGx(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.modPow(bigInteger3, bigInteger);
    }

    public static BigInteger calculateGA(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return bigInteger2.multiply(bigInteger3).multiply(bigInteger4).mod(bigInteger);
    }

    public static BigInteger calculateX2s(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.multiply(bigInteger3).mod(bigInteger);
    }

    public static BigInteger calculateA(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return bigInteger3.modPow(bigInteger4, bigInteger);
    }

    public static BigInteger[] calculateZeroKnowledgeProof(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, String string, Digest digest, SecureRandom secureRandom) {
        BigInteger[] bigIntegerArray = new BigInteger[2];
        BigInteger bigInteger6 = ZERO;
        BigInteger bigInteger7 = bigInteger2.subtract(ONE);
        BigInteger bigInteger8 = BigIntegers.createRandomInRange(bigInteger6, bigInteger7, secureRandom);
        BigInteger bigInteger9 = bigInteger3.modPow(bigInteger8, bigInteger);
        BigInteger bigInteger10 = JPAKEUtil.calculateHashForZeroKnowledgeProof(bigInteger3, bigInteger9, bigInteger4, string, digest);
        bigIntegerArray[0] = bigInteger9;
        bigIntegerArray[1] = bigInteger8.subtract(bigInteger5.multiply(bigInteger10)).mod(bigInteger2);
        return bigIntegerArray;
    }

    private static BigInteger calculateHashForZeroKnowledgeProof(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String string, Digest digest) {
        digest.reset();
        JPAKEUtil.updateDigestIncludingSize(digest, bigInteger);
        JPAKEUtil.updateDigestIncludingSize(digest, bigInteger2);
        JPAKEUtil.updateDigestIncludingSize(digest, bigInteger3);
        JPAKEUtil.updateDigestIncludingSize(digest, string);
        byte[] byArray = new byte[digest.getDigestSize()];
        digest.doFinal(byArray, 0);
        return new BigInteger(byArray);
    }

    public static void validateGx4(BigInteger bigInteger) throws CryptoException {
        if (bigInteger.equals(ONE)) {
            throw new CryptoException("g^x validation failed.  g^x should not be 1.");
        }
    }

    public static void validateGa(BigInteger bigInteger) throws CryptoException {
        if (bigInteger.equals(ONE)) {
            throw new CryptoException("ga is equal to 1.  It should not be.  The chances of this happening are on the order of 2^160 for a 160-bit q.  Try again.");
        }
    }

    public static void validateZeroKnowledgeProof(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger[] bigIntegerArray, String string, Digest digest) throws CryptoException {
        BigInteger bigInteger5 = bigIntegerArray[0];
        BigInteger bigInteger6 = bigIntegerArray[1];
        BigInteger bigInteger7 = JPAKEUtil.calculateHashForZeroKnowledgeProof(bigInteger3, bigInteger5, bigInteger4, string, digest);
        if (bigInteger4.compareTo(ZERO) != 1 || bigInteger4.compareTo(bigInteger) != -1 || bigInteger4.modPow(bigInteger2, bigInteger).compareTo(ONE) != 0 || bigInteger3.modPow(bigInteger6, bigInteger).multiply(bigInteger4.modPow(bigInteger7, bigInteger)).mod(bigInteger).compareTo(bigInteger5) != 0) {
            throw new CryptoException("Zero-knowledge proof validation failed");
        }
    }

    public static BigInteger calculateKeyingMaterial(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6) {
        return bigInteger3.modPow(bigInteger4.multiply(bigInteger5).negate().mod(bigInteger2), bigInteger).multiply(bigInteger6).modPow(bigInteger4, bigInteger);
    }

    public static void validateParticipantIdsDiffer(String string, String string2) throws CryptoException {
        if (string.equals(string2)) {
            throw new CryptoException("Both participants are using the same participantId (" + string + "). This is not allowed. " + "Each participant must use a unique participantId.");
        }
    }

    public static void validateParticipantIdsEqual(String string, String string2) throws CryptoException {
        if (!string.equals(string2)) {
            throw new CryptoException("Received payload from incorrect partner (" + string2 + "). Expected to receive payload from " + string + ".");
        }
    }

    public static void validateNotNull(Object object, String string) {
        if (object == null) {
            throw new NullPointerException(string + " must not be null");
        }
    }

    public static BigInteger calculateMacTag(String string, String string2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, Digest digest) {
        byte[] byArray = JPAKEUtil.calculateMacKey(bigInteger5, digest);
        HMac hMac = new HMac(digest);
        byte[] byArray2 = new byte[hMac.getMacSize()];
        hMac.init(new KeyParameter(byArray));
        JPAKEUtil.updateMac((Mac)hMac, "KC_1_U");
        JPAKEUtil.updateMac((Mac)hMac, string);
        JPAKEUtil.updateMac((Mac)hMac, string2);
        JPAKEUtil.updateMac((Mac)hMac, bigInteger);
        JPAKEUtil.updateMac((Mac)hMac, bigInteger2);
        JPAKEUtil.updateMac((Mac)hMac, bigInteger3);
        JPAKEUtil.updateMac((Mac)hMac, bigInteger4);
        hMac.doFinal(byArray2, 0);
        Arrays.fill(byArray, (byte)0);
        return new BigInteger(byArray2);
    }

    private static byte[] calculateMacKey(BigInteger bigInteger, Digest digest) {
        digest.reset();
        JPAKEUtil.updateDigest(digest, bigInteger);
        JPAKEUtil.updateDigest(digest, "JPAKE_KC");
        byte[] byArray = new byte[digest.getDigestSize()];
        digest.doFinal(byArray, 0);
        return byArray;
    }

    public static void validateMacTag(String string, String string2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, Digest digest, BigInteger bigInteger6) throws CryptoException {
        BigInteger bigInteger7 = JPAKEUtil.calculateMacTag(string2, string, bigInteger3, bigInteger4, bigInteger, bigInteger2, bigInteger5, digest);
        if (!bigInteger7.equals(bigInteger6)) {
            throw new CryptoException("Partner MacTag validation failed. Therefore, the password, MAC, or digest algorithm of each participant does not match.");
        }
    }

    private static void updateDigest(Digest digest, BigInteger bigInteger) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(bigInteger);
        digest.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static void updateDigestIncludingSize(Digest digest, BigInteger bigInteger) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(bigInteger);
        digest.update(JPAKEUtil.intToByteArray(byArray.length), 0, 4);
        digest.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static void updateDigest(Digest digest, String string) {
        byte[] byArray = Strings.toUTF8ByteArray(string);
        digest.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static void updateDigestIncludingSize(Digest digest, String string) {
        byte[] byArray = Strings.toUTF8ByteArray(string);
        digest.update(JPAKEUtil.intToByteArray(byArray.length), 0, 4);
        digest.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static void updateMac(Mac mac, BigInteger bigInteger) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(bigInteger);
        mac.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static void updateMac(Mac mac, String string) {
        byte[] byArray = Strings.toUTF8ByteArray(string);
        mac.update(byArray, 0, byArray.length);
        Arrays.fill(byArray, (byte)0);
    }

    private static byte[] intToByteArray(int n) {
        return new byte[]{(byte)(n >>> 24), (byte)(n >>> 16), (byte)(n >>> 8), (byte)n};
    }
}

