/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.ResponderID;

public class OcspIdentifier
extends ASN1Object {
    private ResponderID ocspResponderID;
    private ASN1GeneralizedTime producedAt;

    public static OcspIdentifier getInstance(Object object) {
        if (object instanceof OcspIdentifier) {
            return (OcspIdentifier)object;
        }
        if (object != null) {
            return new OcspIdentifier(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private OcspIdentifier(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.ocspResponderID = ResponderID.getInstance(aSN1Sequence.getObjectAt(0));
        this.producedAt = (ASN1GeneralizedTime)aSN1Sequence.getObjectAt(1);
    }

    public OcspIdentifier(ResponderID responderID, ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.ocspResponderID = responderID;
        this.producedAt = aSN1GeneralizedTime;
    }

    public ResponderID getOcspResponderID() {
        return this.ocspResponderID;
    }

    public ASN1GeneralizedTime getProducedAt() {
        return this.producedAt;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.ocspResponderID);
        aSN1EncodableVector.add(this.producedAt);
        return new DERSequence(aSN1EncodableVector);
    }
}

