/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

public enum HmacAlgorithms {
    HMAC_MD5("HmacMD5"),
    HMAC_SHA_1("HmacSHA1"),
    HMAC_SHA_224("HmacSHA224"),
    HMAC_SHA_256("HmacSHA256"),
    HMAC_SHA_384("HmacSHA384"),
    HMAC_SHA_512("HmacSHA512");

    private final String name;

    private HmacAlgorithms(String algorithm) {
        this.name = algorithm;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}

