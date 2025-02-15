/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

public class MessageDigestAlgorithms {
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";
    public static final String SHA3_224 = "SHA3-224";
    public static final String SHA3_256 = "SHA3-256";
    public static final String SHA3_384 = "SHA3-384";
    public static final String SHA3_512 = "SHA3-512";

    public static String[] values() {
        return new String[]{MD2, MD5, SHA_1, SHA_224, SHA_256, SHA_384, SHA_512, SHA3_224, SHA3_256, SHA3_384, SHA3_512};
    }

    private MessageDigestAlgorithms() {
    }
}

