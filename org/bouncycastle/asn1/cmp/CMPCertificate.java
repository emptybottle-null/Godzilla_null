/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Certificate;

public class CMPCertificate
extends ASN1Object
implements ASN1Choice {
    private Certificate x509v3PKCert;
    private int otherTagValue;
    private ASN1Object otherCert;

    public CMPCertificate(AttributeCertificate attributeCertificate) {
        this(1, attributeCertificate);
    }

    public CMPCertificate(int n, ASN1Object aSN1Object) {
        this.otherTagValue = n;
        this.otherCert = aSN1Object;
    }

    public CMPCertificate(Certificate certificate) {
        if (certificate.getVersionNumber() != 3) {
            throw new IllegalArgumentException("only version 3 certificates allowed");
        }
        this.x509v3PKCert = certificate;
    }

    public static CMPCertificate getInstance(Object object) {
        if (object == null || object instanceof CMPCertificate) {
            return (CMPCertificate)object;
        }
        if (object instanceof byte[]) {
            try {
                object = ASN1Primitive.fromByteArray((byte[])object);
            } catch (IOException iOException) {
                throw new IllegalArgumentException("Invalid encoding in CMPCertificate");
            }
        }
        if (object instanceof ASN1Sequence) {
            return new CMPCertificate(Certificate.getInstance(object));
        }
        if (object instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)object;
            return new CMPCertificate(aSN1TaggedObject.getTagNo(), aSN1TaggedObject.getObject());
        }
        throw new IllegalArgumentException("Invalid object: " + object.getClass().getName());
    }

    public boolean isX509v3PKCert() {
        return this.x509v3PKCert != null;
    }

    public Certificate getX509v3PKCert() {
        return this.x509v3PKCert;
    }

    public AttributeCertificate getX509v2AttrCert() {
        return AttributeCertificate.getInstance(this.otherCert);
    }

    public int getOtherCertTag() {
        return this.otherTagValue;
    }

    public ASN1Object getOtherCert() {
        return this.otherCert;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.otherCert != null) {
            return new DERTaggedObject(true, this.otherTagValue, this.otherCert);
        }
        return this.x509v3PKCert.toASN1Primitive();
    }
}

