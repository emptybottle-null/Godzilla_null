/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.BodyPartID;

public class OtherMsg
extends ASN1Object {
    private final BodyPartID bodyPartID;
    private final ASN1ObjectIdentifier otherMsgType;
    private final ASN1Encodable otherMsgValue;

    public OtherMsg(BodyPartID bodyPartID, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.bodyPartID = bodyPartID;
        this.otherMsgType = aSN1ObjectIdentifier;
        this.otherMsgValue = aSN1Encodable;
    }

    private OtherMsg(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.bodyPartID = BodyPartID.getInstance(aSN1Sequence.getObjectAt(0));
        this.otherMsgType = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.otherMsgValue = aSN1Sequence.getObjectAt(2);
    }

    public static OtherMsg getInstance(Object object) {
        if (object instanceof OtherMsg) {
            return (OtherMsg)object;
        }
        if (object != null) {
            return new OtherMsg(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static OtherMsg getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return OtherMsg.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.bodyPartID);
        aSN1EncodableVector.add(this.otherMsgType);
        aSN1EncodableVector.add(this.otherMsgValue);
        return new DERSequence(aSN1EncodableVector);
    }

    public BodyPartID getBodyPartID() {
        return this.bodyPartID;
    }

    public ASN1ObjectIdentifier getOtherMsgType() {
        return this.otherMsgType;
    }

    public ASN1Encodable getOtherMsgValue() {
        return this.otherMsgValue;
    }
}

