/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class OtherRecipientInfo
extends ASN1Object {
    private ASN1ObjectIdentifier oriType;
    private ASN1Encodable oriValue;

    public OtherRecipientInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.oriType = aSN1ObjectIdentifier;
        this.oriValue = aSN1Encodable;
    }

    public OtherRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.oriType = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.oriValue = aSN1Sequence.getObjectAt(1);
    }

    public static OtherRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return OtherRecipientInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static OtherRecipientInfo getInstance(Object object) {
        if (object instanceof OtherRecipientInfo) {
            return (OtherRecipientInfo)object;
        }
        if (object != null) {
            return new OtherRecipientInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1ObjectIdentifier getType() {
        return this.oriType;
    }

    public ASN1Encodable getValue() {
        return this.oriValue;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oriType);
        aSN1EncodableVector.add(this.oriValue);
        return new DERSequence(aSN1EncodableVector);
    }
}

