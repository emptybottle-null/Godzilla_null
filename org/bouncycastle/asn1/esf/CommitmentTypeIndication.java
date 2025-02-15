/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class CommitmentTypeIndication
extends ASN1Object {
    private ASN1ObjectIdentifier commitmentTypeId;
    private ASN1Sequence commitmentTypeQualifier;

    private CommitmentTypeIndication(ASN1Sequence aSN1Sequence) {
        this.commitmentTypeId = (ASN1ObjectIdentifier)aSN1Sequence.getObjectAt(0);
        if (aSN1Sequence.size() > 1) {
            this.commitmentTypeQualifier = (ASN1Sequence)aSN1Sequence.getObjectAt(1);
        }
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.commitmentTypeId = aSN1ObjectIdentifier;
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Sequence aSN1Sequence) {
        this.commitmentTypeId = aSN1ObjectIdentifier;
        this.commitmentTypeQualifier = aSN1Sequence;
    }

    public static CommitmentTypeIndication getInstance(Object object) {
        if (object == null || object instanceof CommitmentTypeIndication) {
            return (CommitmentTypeIndication)object;
        }
        return new CommitmentTypeIndication(ASN1Sequence.getInstance(object));
    }

    public ASN1ObjectIdentifier getCommitmentTypeId() {
        return this.commitmentTypeId;
    }

    public ASN1Sequence getCommitmentTypeQualifier() {
        return this.commitmentTypeQualifier;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.commitmentTypeId);
        if (this.commitmentTypeQualifier != null) {
            aSN1EncodableVector.add(this.commitmentTypeQualifier);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

