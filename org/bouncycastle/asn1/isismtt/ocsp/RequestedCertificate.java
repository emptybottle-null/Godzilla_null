/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.ocsp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Certificate;

public class RequestedCertificate
extends ASN1Object
implements ASN1Choice {
    public static final int certificate = -1;
    public static final int publicKeyCertificate = 0;
    public static final int attributeCertificate = 1;
    private Certificate cert;
    private byte[] publicKeyCert;
    private byte[] attributeCert;

    public static RequestedCertificate getInstance(Object object) {
        if (object == null || object instanceof RequestedCertificate) {
            return (RequestedCertificate)object;
        }
        if (object instanceof ASN1Sequence) {
            return new RequestedCertificate(Certificate.getInstance(object));
        }
        if (object instanceof ASN1TaggedObject) {
            return new RequestedCertificate((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static RequestedCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        if (!bl) {
            throw new IllegalArgumentException("choice item must be explicitly tagged");
        }
        return RequestedCertificate.getInstance(aSN1TaggedObject.getObject());
    }

    private RequestedCertificate(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.getTagNo() == 0) {
            this.publicKeyCert = ASN1OctetString.getInstance(aSN1TaggedObject, true).getOctets();
        } else if (aSN1TaggedObject.getTagNo() == 1) {
            this.attributeCert = ASN1OctetString.getInstance(aSN1TaggedObject, true).getOctets();
        } else {
            throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
        }
    }

    public RequestedCertificate(Certificate certificate) {
        this.cert = certificate;
    }

    public RequestedCertificate(int n, byte[] byArray) {
        this(new DERTaggedObject(n, new DEROctetString(byArray)));
    }

    public int getType() {
        if (this.cert != null) {
            return -1;
        }
        if (this.publicKeyCert != null) {
            return 0;
        }
        return 1;
    }

    public byte[] getCertificateBytes() {
        if (this.cert != null) {
            try {
                return this.cert.getEncoded();
            } catch (IOException iOException) {
                throw new IllegalStateException("can't decode certificate: " + iOException);
            }
        }
        if (this.publicKeyCert != null) {
            return this.publicKeyCert;
        }
        return this.attributeCert;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.publicKeyCert != null) {
            return new DERTaggedObject(0, new DEROctetString(this.publicKeyCert));
        }
        if (this.attributeCert != null) {
            return new DERTaggedObject(1, new DEROctetString(this.attributeCert));
        }
        return this.cert.toASN1Primitive();
    }
}

