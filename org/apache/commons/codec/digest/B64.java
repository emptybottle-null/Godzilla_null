/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

import java.security.SecureRandom;
import java.util.Random;

class B64 {
    static final String B64T_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final char[] B64T_ARRAY = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    B64() {
    }

    static void b64from24bit(byte b2, byte b1, byte b0, int outLen, StringBuilder buffer) {
        int w = b2 << 16 & 0xFFFFFF | b1 << 8 & 0xFFFF | b0 & 0xFF;
        int n = outLen;
        while (n-- > 0) {
            buffer.append(B64T_ARRAY[w & 0x3F]);
            w >>= 6;
        }
    }

    static String getRandomSalt(int num) {
        return B64.getRandomSalt(num, new SecureRandom());
    }

    static String getRandomSalt(int num, Random random) {
        StringBuilder saltString = new StringBuilder(num);
        for (int i = 1; i <= num; ++i) {
            saltString.append(B64T_STRING.charAt(random.nextInt(B64T_STRING.length())));
        }
        return saltString.toString();
    }
}

