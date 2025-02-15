/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.util.Integers;

class Utils {
    static final Hashtable maxSecurityStrengths = new Hashtable();

    Utils() {
    }

    static int getMaxSecurityStrength(Digest digest) {
        return (Integer)maxSecurityStrengths.get(digest.getAlgorithmName());
    }

    static int getMaxSecurityStrength(Mac mac) {
        String string = mac.getAlgorithmName();
        return (Integer)maxSecurityStrengths.get(string.substring(0, string.indexOf("/")));
    }

    static byte[] hash_df(Digest digest, byte[] byArray, int n) {
        int n2;
        int n3;
        byte[] byArray2 = new byte[(n + 7) / 8];
        int n4 = byArray2.length / digest.getDigestSize();
        int n5 = 1;
        byte[] byArray3 = new byte[digest.getDigestSize()];
        for (n3 = 0; n3 <= n4; ++n3) {
            digest.update((byte)n5);
            digest.update((byte)(n >> 24));
            digest.update((byte)(n >> 16));
            digest.update((byte)(n >> 8));
            digest.update((byte)n);
            digest.update(byArray, 0, byArray.length);
            digest.doFinal(byArray3, 0);
            n2 = byArray2.length - n3 * byArray3.length > byArray3.length ? byArray3.length : byArray2.length - n3 * byArray3.length;
            System.arraycopy(byArray3, 0, byArray2, n3 * byArray3.length, n2);
            ++n5;
        }
        if (n % 8 != 0) {
            n3 = 8 - n % 8;
            n2 = 0;
            for (int i = 0; i != byArray2.length; ++i) {
                int n6 = byArray2[i] & 0xFF;
                byArray2[i] = (byte)(n6 >>> n3 | n2 << 8 - n3);
                n2 = n6;
            }
        }
        return byArray2;
    }

    static boolean isTooLarge(byte[] byArray, int n) {
        return byArray != null && byArray.length > n;
    }

    static {
        maxSecurityStrengths.put("SHA-1", Integers.valueOf(128));
        maxSecurityStrengths.put("SHA-224", Integers.valueOf(192));
        maxSecurityStrengths.put("SHA-256", Integers.valueOf(256));
        maxSecurityStrengths.put("SHA-384", Integers.valueOf(256));
        maxSecurityStrengths.put("SHA-512", Integers.valueOf(256));
        maxSecurityStrengths.put("SHA-512/224", Integers.valueOf(192));
        maxSecurityStrengths.put("SHA-512/256", Integers.valueOf(256));
    }
}

