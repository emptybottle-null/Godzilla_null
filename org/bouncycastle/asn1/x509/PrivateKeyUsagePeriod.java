/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

public class PrivateKeyUsagePeriod
extends ASN1Object {
    private ASN1GeneralizedTime _notBefore;
    private ASN1GeneralizedTime _notAfter;

    public static PrivateKeyUsagePeriod getInstance(Object object) {
        if (object instanceof PrivateKeyUsagePeriod) {
            return (PrivateKeyUsagePeriod)object;
        }
        if (object != null) {
            return new PrivateKeyUsagePeriod(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private PrivateKeyUsagePeriod(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this._notBefore = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, false);
                continue;
            }
            if (aSN1TaggedObject.getTagNo() != 1) continue;
            this._notAfter = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, false);
        }
    }

    public ASN1GeneralizedTime getNotBefore() {
        return this._notBefore;
    }

    public ASN1GeneralizedTime getNotAfter() {
        return this._notAfter;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this._notBefore != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this._notBefore));
        }
        if (this._notAfter != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this._notAfter));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

