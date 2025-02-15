/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class Base64Utils {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static byte[] encode(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getEncoder().encode(src);
    }

    public static byte[] decode(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getDecoder().decode(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlEncoder().encode(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlDecoder().decode(src);
    }

    public static String encodeToString(byte[] src) {
        if (src.length == 0) {
            return "";
        }
        return new String(Base64Utils.encode(src), DEFAULT_CHARSET);
    }

    public static byte[] decodeFromString(String src) {
        if (src.isEmpty()) {
            return new byte[0];
        }
        return Base64Utils.decode(src.getBytes(DEFAULT_CHARSET));
    }

    public static String encodeToUrlSafeString(byte[] src) {
        return new String(Base64Utils.encodeUrlSafe(src), DEFAULT_CHARSET);
    }

    public static byte[] decodeFromUrlSafeString(String src) {
        return Base64Utils.decodeUrlSafe(src.getBytes(DEFAULT_CHARSET));
    }
}

