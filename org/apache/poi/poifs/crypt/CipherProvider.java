/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import org.apache.poi.EncryptedDocumentException;

public enum CipherProvider {
    rc4("RC4", 1, "Microsoft Base Cryptographic Provider v1.0"),
    aes("AES", 24, "Microsoft Enhanced RSA and AES Cryptographic Provider");

    public final String jceId;
    public final int ecmaId;
    public final String cipherProviderName;

    public static CipherProvider fromEcmaId(int ecmaId) {
        for (CipherProvider cp : CipherProvider.values()) {
            if (cp.ecmaId != ecmaId) continue;
            return cp;
        }
        throw new EncryptedDocumentException("cipher provider not found");
    }

    private CipherProvider(String jceId, int ecmaId, String cipherProviderName) {
        this.jceId = jceId;
        this.ecmaId = ecmaId;
        this.cipherProviderName = cipherProviderName;
    }
}

