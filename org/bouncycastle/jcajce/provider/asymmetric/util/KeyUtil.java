/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class KeyUtil {
    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algorithmIdentifier, aSN1Encodable));
        } catch (Exception exception) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algorithmIdentifier, byArray));
        } catch (Exception exception) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            return subjectPublicKeyInfo.getEncoded("DER");
        } catch (Exception exception) {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        try {
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, aSN1Encodable.toASN1Primitive());
            return KeyUtil.getEncodedPrivateKeyInfo(privateKeyInfo);
        } catch (Exception exception) {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(PrivateKeyInfo privateKeyInfo) {
        try {
            return privateKeyInfo.getEncoded("DER");
        } catch (Exception exception) {
            return null;
        }
    }
}

