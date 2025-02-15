/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.ContentInfo;

public class SCVPReqRes
extends ASN1Object {
    private final ContentInfo request;
    private final ContentInfo response;

    public static SCVPReqRes getInstance(Object object) {
        if (object instanceof SCVPReqRes) {
            return (SCVPReqRes)object;
        }
        if (object != null) {
            return new SCVPReqRes(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SCVPReqRes(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.request = ContentInfo.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0)), true);
            this.response = ContentInfo.getInstance(aSN1Sequence.getObjectAt(1));
        } else {
            this.request = null;
            this.response = ContentInfo.getInstance(aSN1Sequence.getObjectAt(0));
        }
    }

    public SCVPReqRes(ContentInfo contentInfo) {
        this.request = null;
        this.response = contentInfo;
    }

    public SCVPReqRes(ContentInfo contentInfo, ContentInfo contentInfo2) {
        this.request = contentInfo;
        this.response = contentInfo2;
    }

    public ContentInfo getRequest() {
        return this.request;
    }

    public ContentInfo getResponse() {
        return this.response;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.request != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.request));
        }
        aSN1EncodableVector.add(this.response);
        return new DERSequence(aSN1EncodableVector);
    }
}

