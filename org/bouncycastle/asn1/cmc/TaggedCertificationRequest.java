/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.CertificationRequest;

public class TaggedCertificationRequest
extends ASN1Object {
    private final BodyPartID bodyPartID;
    private final CertificationRequest certificationRequest;

    public TaggedCertificationRequest(BodyPartID bodyPartID, CertificationRequest certificationRequest) {
        this.bodyPartID = bodyPartID;
        this.certificationRequest = certificationRequest;
    }

    private TaggedCertificationRequest(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.bodyPartID = BodyPartID.getInstance(aSN1Sequence.getObjectAt(0));
        this.certificationRequest = CertificationRequest.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static TaggedCertificationRequest getInstance(Object object) {
        if (object instanceof TaggedCertificationRequest) {
            return (TaggedCertificationRequest)object;
        }
        if (object != null) {
            return new TaggedCertificationRequest(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static TaggedCertificationRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TaggedCertificationRequest.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.bodyPartID);
        aSN1EncodableVector.add(this.certificationRequest);
        return new DERSequence(aSN1EncodableVector);
    }
}

