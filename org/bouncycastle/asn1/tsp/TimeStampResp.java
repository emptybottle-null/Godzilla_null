/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.tsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cms.ContentInfo;

public class TimeStampResp
extends ASN1Object {
    PKIStatusInfo pkiStatusInfo;
    ContentInfo timeStampToken;

    public static TimeStampResp getInstance(Object object) {
        if (object instanceof TimeStampResp) {
            return (TimeStampResp)object;
        }
        if (object != null) {
            return new TimeStampResp(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private TimeStampResp(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.pkiStatusInfo = PKIStatusInfo.getInstance(enumeration.nextElement());
        if (enumeration.hasMoreElements()) {
            this.timeStampToken = ContentInfo.getInstance(enumeration.nextElement());
        }
    }

    public TimeStampResp(PKIStatusInfo pKIStatusInfo, ContentInfo contentInfo) {
        this.pkiStatusInfo = pKIStatusInfo;
        this.timeStampToken = contentInfo;
    }

    public PKIStatusInfo getStatus() {
        return this.pkiStatusInfo;
    }

    public ContentInfo getTimeStampToken() {
        return this.timeStampToken;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiStatusInfo);
        if (this.timeStampToken != null) {
            aSN1EncodableVector.add(this.timeStampToken);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

