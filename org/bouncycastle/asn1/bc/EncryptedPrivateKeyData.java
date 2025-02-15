/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.bc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.x509.Certificate;

public class EncryptedPrivateKeyData
extends ASN1Object {
    private final EncryptedPrivateKeyInfo encryptedPrivateKeyInfo;
    private final Certificate[] certificateChain;

    public EncryptedPrivateKeyData(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo, Certificate[] certificateArray) {
        this.encryptedPrivateKeyInfo = encryptedPrivateKeyInfo;
        this.certificateChain = new Certificate[certificateArray.length];
        System.arraycopy(certificateArray, 0, this.certificateChain, 0, certificateArray.length);
    }

    private EncryptedPrivateKeyData(ASN1Sequence aSN1Sequence) {
        this.encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(aSN1Sequence.getObjectAt(0));
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        this.certificateChain = new Certificate[aSN1Sequence2.size()];
        for (int i = 0; i != this.certificateChain.length; ++i) {
            this.certificateChain[i] = Certificate.getInstance(aSN1Sequence2.getObjectAt(i));
        }
    }

    public static EncryptedPrivateKeyData getInstance(Object object) {
        if (object instanceof EncryptedPrivateKeyData) {
            return (EncryptedPrivateKeyData)object;
        }
        if (object != null) {
            return new EncryptedPrivateKeyData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public Certificate[] getCertificateChain() {
        Certificate[] certificateArray = new Certificate[this.certificateChain.length];
        System.arraycopy(this.certificateChain, 0, certificateArray, 0, this.certificateChain.length);
        return certificateArray;
    }

    public EncryptedPrivateKeyInfo getEncryptedPrivateKeyInfo() {
        return this.encryptedPrivateKeyInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.encryptedPrivateKeyInfo);
        aSN1EncodableVector.add(new DERSequence(this.certificateChain));
        return new DERSequence(aSN1EncodableVector);
    }
}

