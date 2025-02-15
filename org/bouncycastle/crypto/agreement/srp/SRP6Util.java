/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.BigIntegers;

public class SRP6Util {
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);

    public static BigInteger calculateK(Digest digest, BigInteger bigInteger, BigInteger bigInteger2) {
        return SRP6Util.hashPaddedPair(digest, bigInteger, bigInteger, bigInteger2);
    }

    public static BigInteger calculateU(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return SRP6Util.hashPaddedPair(digest, bigInteger, bigInteger2, bigInteger3);
    }

    public static BigInteger calculateX(Digest digest, BigInteger bigInteger, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        byte[] byArray4 = new byte[digest.getDigestSize()];
        digest.update(byArray2, 0, byArray2.length);
        digest.update((byte)58);
        digest.update(byArray3, 0, byArray3.length);
        digest.doFinal(byArray4, 0);
        digest.update(byArray, 0, byArray.length);
        digest.update(byArray4, 0, byArray4.length);
        digest.doFinal(byArray4, 0);
        return new BigInteger(1, byArray4);
    }

    public static BigInteger generatePrivateValue(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        int n = Math.min(256, bigInteger.bitLength() / 2);
        BigInteger bigInteger3 = ONE.shiftLeft(n - 1);
        BigInteger bigInteger4 = bigInteger.subtract(ONE);
        return BigIntegers.createRandomInRange(bigInteger3, bigInteger4, secureRandom);
    }

    public static BigInteger validatePublicValue(BigInteger bigInteger, BigInteger bigInteger2) throws CryptoException {
        if ((bigInteger2 = bigInteger2.mod(bigInteger)).equals(ZERO)) {
            throw new CryptoException("Invalid public value: 0");
        }
        return bigInteger2;
    }

    public static BigInteger calculateM1(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        BigInteger bigInteger5 = SRP6Util.hashPaddedTriplet(digest, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        return bigInteger5;
    }

    public static BigInteger calculateM2(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        BigInteger bigInteger5 = SRP6Util.hashPaddedTriplet(digest, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        return bigInteger5;
    }

    public static BigInteger calculateKey(Digest digest, BigInteger bigInteger, BigInteger bigInteger2) {
        int n = (bigInteger.bitLength() + 7) / 8;
        byte[] byArray = SRP6Util.getPadded(bigInteger2, n);
        digest.update(byArray, 0, byArray.length);
        byte[] byArray2 = new byte[digest.getDigestSize()];
        digest.doFinal(byArray2, 0);
        return new BigInteger(1, byArray2);
    }

    private static BigInteger hashPaddedTriplet(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        int n = (bigInteger.bitLength() + 7) / 8;
        byte[] byArray = SRP6Util.getPadded(bigInteger2, n);
        byte[] byArray2 = SRP6Util.getPadded(bigInteger3, n);
        byte[] byArray3 = SRP6Util.getPadded(bigInteger4, n);
        digest.update(byArray, 0, byArray.length);
        digest.update(byArray2, 0, byArray2.length);
        digest.update(byArray3, 0, byArray3.length);
        byte[] byArray4 = new byte[digest.getDigestSize()];
        digest.doFinal(byArray4, 0);
        return new BigInteger(1, byArray4);
    }

    private static BigInteger hashPaddedPair(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        int n = (bigInteger.bitLength() + 7) / 8;
        byte[] byArray = SRP6Util.getPadded(bigInteger2, n);
        byte[] byArray2 = SRP6Util.getPadded(bigInteger3, n);
        digest.update(byArray, 0, byArray.length);
        digest.update(byArray2, 0, byArray2.length);
        byte[] byArray3 = new byte[digest.getDigestSize()];
        digest.doFinal(byArray3, 0);
        return new BigInteger(1, byArray3);
    }

    private static byte[] getPadded(BigInteger bigInteger, int n) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(bigInteger);
        if (byArray.length < n) {
            byte[] byArray2 = new byte[n];
            System.arraycopy(byArray, 0, byArray2, n - byArray.length, byArray.length);
            byArray = byArray2;
        }
        return byArray;
    }
}

