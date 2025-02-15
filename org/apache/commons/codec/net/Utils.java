/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;

class Utils {
    private static final int RADIX = 16;

    Utils() {
    }

    static int digit16(byte b) throws DecoderException {
        int i = Character.digit((char)b, 16);
        if (i == -1) {
            throw new DecoderException("Invalid URL encoding: not a valid digit (radix 16): " + b);
        }
        return i;
    }

    static char hexDigit(int b) {
        return Character.toUpperCase(Character.forDigit(b & 0xF, 16));
    }
}

