/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.OtherMsg;
import org.bouncycastle.asn1.cmc.TaggedAttribute;
import org.bouncycastle.asn1.cmc.TaggedContentInfo;
import org.bouncycastle.asn1.cmc.TaggedRequest;

public class PKIData
extends ASN1Object {
    private final TaggedAttribute[] controlSequence;
    private final TaggedRequest[] reqSequence;
    private final TaggedContentInfo[] cmsSequence;
    private final OtherMsg[] otherMsgSequence;

    public PKIData(TaggedAttribute[] taggedAttributeArray, TaggedRequest[] taggedRequestArray, TaggedContentInfo[] taggedContentInfoArray, OtherMsg[] otherMsgArray) {
        this.controlSequence = taggedAttributeArray;
        this.reqSequence = taggedRequestArray;
        this.cmsSequence = taggedContentInfoArray;
        this.otherMsgSequence = otherMsgArray;
    }

    private PKIData(ASN1Sequence aSN1Sequence) {
        int n;
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("Sequence not 4 elements.");
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(0);
        this.controlSequence = new TaggedAttribute[aSN1Sequence2.size()];
        for (n = 0; n < this.controlSequence.length; ++n) {
            this.controlSequence[n] = TaggedAttribute.getInstance(aSN1Sequence2.getObjectAt(n));
        }
        aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(1);
        this.reqSequence = new TaggedRequest[aSN1Sequence2.size()];
        for (n = 0; n < this.reqSequence.length; ++n) {
            this.reqSequence[n] = TaggedRequest.getInstance(aSN1Sequence2.getObjectAt(n));
        }
        aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(2);
        this.cmsSequence = new TaggedContentInfo[aSN1Sequence2.size()];
        for (n = 0; n < this.cmsSequence.length; ++n) {
            this.cmsSequence[n] = TaggedContentInfo.getInstance(aSN1Sequence2.getObjectAt(n));
        }
        aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(3);
        this.otherMsgSequence = new OtherMsg[aSN1Sequence2.size()];
        for (n = 0; n < this.otherMsgSequence.length; ++n) {
            this.otherMsgSequence[n] = OtherMsg.getInstance(aSN1Sequence2.getObjectAt(n));
        }
    }

    public static PKIData getInstance(Object object) {
        if (object instanceof PKIData) {
            return (PKIData)object;
        }
        if (object != null) {
            return new PKIData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{new DERSequence(this.controlSequence), new DERSequence(this.reqSequence), new DERSequence(this.cmsSequence), new DERSequence(this.otherMsgSequence)});
    }

    public TaggedAttribute[] getControlSequence() {
        return this.controlSequence;
    }

    public TaggedRequest[] getReqSequence() {
        return this.reqSequence;
    }

    public TaggedContentInfo[] getCmsSequence() {
        return this.cmsSequence;
    }

    public OtherMsg[] getOtherMsgSequence() {
        return this.otherMsgSequence;
    }
}

