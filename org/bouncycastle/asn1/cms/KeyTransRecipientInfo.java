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
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class KeyTransRecipientInfo
extends ASN1Object {
    private ASN1Integer version;
    private RecipientIdentifier rid;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;

    public KeyTransRecipientInfo(RecipientIdentifier recipientIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.version = recipientIdentifier.toASN1Primitive() instanceof ASN1TaggedObject ? new ASN1Integer(2L) : new ASN1Integer(0L);
        this.rid = recipientIdentifier;
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKey = aSN1OctetString;
    }

    public KeyTransRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
        this.rid = RecipientIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.encryptedKey = (ASN1OctetString)aSN1Sequence.getObjectAt(3);
    }

    public static KeyTransRecipientInfo getInstance(Object object) {
        if (object instanceof KeyTransRecipientInfo) {
            return (KeyTransRecipientInfo)object;
        }
        if (object != null) {
            return new KeyTransRecipientInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public RecipientIdentifier getRecipientIdentifier() {
        return this.rid;
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
        aSN1EncodableVector.add(this.rid);
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }
}

