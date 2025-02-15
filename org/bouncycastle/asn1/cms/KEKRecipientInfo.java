/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class KEKRecipientInfo
extends ASN1Object {
    private ASN1Integer version;
    private KEKIdentifier kekid;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;

    public KEKRecipientInfo(KEKIdentifier kEKIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(4L);
        this.kekid = kEKIdentifier;
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKey = aSN1OctetString;
    }

    public KEKRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
        this.kekid = KEKIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.encryptedKey = (ASN1OctetString)aSN1Sequence.getObjectAt(3);
    }

    public static KEKRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return KEKRecipientInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static KEKRecipientInfo getInstance(Object object) {
        if (object instanceof KEKRecipientInfo) {
            return (KEKRecipientInfo)object;
        }
        if (object != null) {
            return new KEKRecipientInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public KEKIdentifier getKekid() {
        return this.kekid;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.kekid);
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }
}

