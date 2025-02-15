/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class PEMKeyPair {
    private final SubjectPublicKeyInfo publicKeyInfo;
    private final PrivateKeyInfo privateKeyInfo;

    public PEMKeyPair(SubjectPublicKeyInfo subjectPublicKeyInfo, PrivateKeyInfo privateKeyInfo) {
        this.publicKeyInfo = subjectPublicKeyInfo;
        this.privateKeyInfo = privateKeyInfo;
    }

    public PrivateKeyInfo getPrivateKeyInfo() {
        return this.privateKeyInfo;
    }

    public SubjectPublicKeyInfo getPublicKeyInfo() {
        return this.publicKeyInfo;
    }
}

