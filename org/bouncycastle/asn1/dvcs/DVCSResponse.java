/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.dvcs.DVCSCertInfo;
import org.bouncycastle.asn1.dvcs.DVCSErrorNotice;

public class DVCSResponse
extends ASN1Object
implements ASN1Choice {
    private DVCSCertInfo dvCertInfo;
    private DVCSErrorNotice dvErrorNote;

    public DVCSResponse(DVCSCertInfo dVCSCertInfo) {
        this.dvCertInfo = dVCSCertInfo;
    }

    public DVCSResponse(DVCSErrorNotice dVCSErrorNotice) {
        this.dvErrorNote = dVCSErrorNotice;
    }

    public static DVCSResponse getInstance(Object object) {
        if (object == null || object instanceof DVCSResponse) {
            return (DVCSResponse)object;
        }
        if (object instanceof byte[]) {
            try {
                return DVCSResponse.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + iOException.getMessage());
            }
        }
        if (object instanceof ASN1Sequence) {
            DVCSCertInfo dVCSCertInfo = DVCSCertInfo.getInstance(object);
            return new DVCSResponse(dVCSCertInfo);
        }
        if (object instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(object);
            DVCSErrorNotice dVCSErrorNotice = DVCSErrorNotice.getInstance(aSN1TaggedObject, false);
            return new DVCSResponse(dVCSErrorNotice);
        }
        throw new IllegalArgumentException("Couldn't convert from object to DVCSResponse: " + object.getClass().getName());
    }

    public static DVCSResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DVCSResponse.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public DVCSCertInfo getCertInfo() {
        return this.dvCertInfo;
    }

    public DVCSErrorNotice getErrorNotice() {
        return this.dvErrorNote;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.dvCertInfo != null) {
            return this.dvCertInfo.toASN1Primitive();
        }
        return new DERTaggedObject(false, 0, this.dvErrorNote);
    }

    public String toString() {
        if (this.dvCertInfo != null) {
            return "DVCSResponse {\ndvCertInfo: " + this.dvCertInfo.toString() + "}\n";
        }
        return "DVCSResponse {\ndvErrorNote: " + this.dvErrorNote.toString() + "}\n";
    }
}

