/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import org.apache.poi.EncryptedDocumentException;

public enum HashAlgorithm {
    none("", 0, "", 0, "", false, ""),
    sha1("SHA-1", 32772, "SHA1", 20, "HmacSHA1", false, "1.3.14.3.2.26"),
    sha256("SHA-256", 32780, "SHA256", 32, "HmacSHA256", false, "2.16.840.1.101.3.4.2.1"),
    sha384("SHA-384", 32781, "SHA384", 48, "HmacSHA384", false, "2.16.840.1.101.3.4.2.2"),
    sha512("SHA-512", 32782, "SHA512", 64, "HmacSHA512", false, "2.16.840.1.101.3.4.2.3"),
    md5("MD5", -1, "MD5", 16, "HmacMD5", false, "1.2.840.113549.2.5"),
    md2("MD2", -1, "MD2", 16, "Hmac-MD2", true, "1.2.840.113549.2.2"),
    md4("MD4", -1, "MD4", 16, "Hmac-MD4", true, "1.2.840.113549.2.4"),
    ripemd128("RipeMD128", -1, "RIPEMD-128", 16, "HMac-RipeMD128", true, "1.3.36.3.2.2"),
    ripemd160("RipeMD160", -1, "RIPEMD-160", 20, "HMac-RipeMD160", true, "1.3.36.3.2.1"),
    whirlpool("Whirlpool", -1, "WHIRLPOOL", 64, "HMac-Whirlpool", true, "1.0.10118.3.0.55"),
    sha224("SHA-224", -1, "SHA224", 28, "HmacSHA224", true, "2.16.840.1.101.3.4.2.4"),
    ripemd256("RipeMD256", -1, "RIPEMD-256", 32, "HMac-RipeMD256", true, "1.3.36.3.2.3");

    public final String jceId;
    public final int ecmaId;
    public final String ecmaString;
    public final int hashSize;
    public final String jceHmacId;
    public final boolean needsBouncyCastle;
    public final String rsaOid;

    private HashAlgorithm(String jceId, int ecmaId, String ecmaString, int hashSize, String jceHmacId, boolean needsBouncyCastle, String rsaOid) {
        this.jceId = jceId;
        this.ecmaId = ecmaId;
        this.ecmaString = ecmaString;
        this.hashSize = hashSize;
        this.jceHmacId = jceHmacId;
        this.needsBouncyCastle = needsBouncyCastle;
        this.rsaOid = rsaOid;
    }

    public static HashAlgorithm fromEcmaId(int ecmaId) {
        for (HashAlgorithm ha : HashAlgorithm.values()) {
            if (ha.ecmaId != ecmaId) continue;
            return ha;
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }

    public static HashAlgorithm fromEcmaId(String ecmaString) {
        for (HashAlgorithm ha : HashAlgorithm.values()) {
            if (!ha.ecmaString.equals(ecmaString)) continue;
            return ha;
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }

    public static HashAlgorithm fromString(String string) {
        for (HashAlgorithm ha : HashAlgorithm.values()) {
            if (!ha.ecmaString.equalsIgnoreCase(string) && !ha.jceId.equalsIgnoreCase(string)) continue;
            return ha;
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }
}

