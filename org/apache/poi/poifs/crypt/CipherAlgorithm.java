/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CipherProvider;

public enum CipherAlgorithm {
    rc4(CipherProvider.rc4, "RC4", 26625, 64, new int[]{40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128}, -1, 20, "RC4", false),
    aes128(CipherProvider.aes, "AES", 26126, 128, new int[]{128}, 16, 32, "AES", false),
    aes192(CipherProvider.aes, "AES", 26127, 192, new int[]{192}, 16, 32, "AES", false),
    aes256(CipherProvider.aes, "AES", 26128, 256, new int[]{256}, 16, 32, "AES", false),
    rc2(null, "RC2", -1, 128, new int[]{40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128}, 8, 20, "RC2", false),
    des(null, "DES", -1, 64, new int[]{64}, 8, 32, "DES", false),
    des3(null, "DESede", -1, 192, new int[]{192}, 8, 32, "3DES", false),
    des3_112(null, "DESede", -1, 128, new int[]{128}, 8, 32, "3DES_112", true),
    rsa(null, "RSA", -1, 1024, new int[]{1024, 2048, 3072, 4096}, -1, -1, "", false);

    public final CipherProvider provider;
    public final String jceId;
    public final int ecmaId;
    public final int defaultKeySize;
    public final int[] allowedKeySize;
    public final int blockSize;
    public final int encryptedVerifierHashLength;
    public final String xmlId;
    public final boolean needsBouncyCastle;

    private CipherAlgorithm(CipherProvider provider, String jceId, int ecmaId, int defaultKeySize, int[] allowedKeySize, int blockSize, int encryptedVerifierHashLength, String xmlId, boolean needsBouncyCastle) {
        this.provider = provider;
        this.jceId = jceId;
        this.ecmaId = ecmaId;
        this.defaultKeySize = defaultKeySize;
        this.allowedKeySize = (int[])allowedKeySize.clone();
        this.blockSize = blockSize;
        this.encryptedVerifierHashLength = encryptedVerifierHashLength;
        this.xmlId = xmlId;
        this.needsBouncyCastle = needsBouncyCastle;
    }

    public static CipherAlgorithm fromEcmaId(int ecmaId) {
        for (CipherAlgorithm ca : CipherAlgorithm.values()) {
            if (ca.ecmaId != ecmaId) continue;
            return ca;
        }
        throw new EncryptedDocumentException("cipher algorithm " + ecmaId + " not found");
    }

    public static CipherAlgorithm fromXmlId(String xmlId, int keySize) {
        for (CipherAlgorithm ca : CipherAlgorithm.values()) {
            if (!ca.xmlId.equals(xmlId)) continue;
            for (int ks : ca.allowedKeySize) {
                if (ks != keySize) continue;
                return ca;
            }
        }
        throw new EncryptedDocumentException("cipher algorithm " + xmlId + "/" + keySize + " not found");
    }
}

