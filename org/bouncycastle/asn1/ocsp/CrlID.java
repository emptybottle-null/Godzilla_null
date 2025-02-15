/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

public class CrlID
extends ASN1Object {
    private DERIA5String crlUrl;
    private ASN1Integer crlNum;
    private ASN1GeneralizedTime crlTime;

    private CrlID(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        block5: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.crlUrl = DERIA5String.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
                case 1: {
                    this.crlNum = ASN1Integer.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
                case 2: {
                    this.crlTime = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
            }
            throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
        }
    }

    public static CrlID getInstance(Object object) {
        if (object instanceof CrlID) {
            return (CrlID)object;
        }
        if (object != null) {
            return new CrlID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public DERIA5String getCrlUrl() {
        return this.crlUrl;
    }

    public ASN1Integer getCrlNum() {
        return this.crlNum;
    }

    public ASN1GeneralizedTime getCrlTime() {
        return this.crlTime;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.crlUrl != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.crlUrl));
        }
        if (this.crlNum != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.crlNum));
        }
        if (this.crlTime != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.crlTime));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

