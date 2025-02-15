/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.RevokedInfo;

public class CertStatus
extends ASN1Object
implements ASN1Choice {
    private int tagNo;
    private ASN1Encodable value;

    public CertStatus() {
        this.tagNo = 0;
        this.value = DERNull.INSTANCE;
    }

    public CertStatus(RevokedInfo revokedInfo) {
        this.tagNo = 1;
        this.value = revokedInfo;
    }

    public CertStatus(int n, ASN1Encodable aSN1Encodable) {
        this.tagNo = n;
        this.value = aSN1Encodable;
    }

    private CertStatus(ASN1TaggedObject aSN1TaggedObject) {
        this.tagNo = aSN1TaggedObject.getTagNo();
        switch (aSN1TaggedObject.getTagNo()) {
            case 0: {
                this.value = DERNull.INSTANCE;
                break;
            }
            case 1: {
                this.value = RevokedInfo.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 2: {
                this.value = DERNull.INSTANCE;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public static CertStatus getInstance(Object object) {
        if (object == null || object instanceof CertStatus) {
            return (CertStatus)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new CertStatus((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("unknown object in factory: " + object.getClass().getName());
    }

    public static CertStatus getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return CertStatus.getInstance(aSN1TaggedObject.getObject());
    }

    public int getTagNo() {
        return this.tagNo;
    }

    public ASN1Encodable getStatus() {
        return this.value;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.tagNo, this.value);
    }
}

