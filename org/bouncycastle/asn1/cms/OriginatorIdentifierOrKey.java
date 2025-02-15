/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.OriginatorPublicKey;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;

public class OriginatorIdentifierOrKey
extends ASN1Object
implements ASN1Choice {
    private ASN1Encodable id;

    public OriginatorIdentifierOrKey(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.id = issuerAndSerialNumber;
    }

    public OriginatorIdentifierOrKey(ASN1OctetString aSN1OctetString) {
        this(new SubjectKeyIdentifier(aSN1OctetString.getOctets()));
    }

    public OriginatorIdentifierOrKey(SubjectKeyIdentifier subjectKeyIdentifier) {
        this.id = new DERTaggedObject(false, 0, subjectKeyIdentifier);
    }

    public OriginatorIdentifierOrKey(OriginatorPublicKey originatorPublicKey) {
        this.id = new DERTaggedObject(false, 1, originatorPublicKey);
    }

    public OriginatorIdentifierOrKey(ASN1Primitive aSN1Primitive) {
        this.id = aSN1Primitive;
    }

    public static OriginatorIdentifierOrKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        if (!bl) {
            throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
        }
        return OriginatorIdentifierOrKey.getInstance(aSN1TaggedObject.getObject());
    }

    public static OriginatorIdentifierOrKey getInstance(Object object) {
        if (object == null || object instanceof OriginatorIdentifierOrKey) {
            return (OriginatorIdentifierOrKey)object;
        }
        if (object instanceof IssuerAndSerialNumber || object instanceof ASN1Sequence) {
            return new OriginatorIdentifierOrKey(IssuerAndSerialNumber.getInstance(object));
        }
        if (object instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)object;
            if (aSN1TaggedObject.getTagNo() == 0) {
                return new OriginatorIdentifierOrKey(SubjectKeyIdentifier.getInstance(aSN1TaggedObject, false));
            }
            if (aSN1TaggedObject.getTagNo() == 1) {
                return new OriginatorIdentifierOrKey(OriginatorPublicKey.getInstance(aSN1TaggedObject, false));
            }
        }
        throw new IllegalArgumentException("Invalid OriginatorIdentifierOrKey: " + object.getClass().getName());
    }

    public ASN1Encodable getId() {
        return this.id;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        if (this.id instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber)this.id;
        }
        return null;
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        if (this.id instanceof ASN1TaggedObject && ((ASN1TaggedObject)this.id).getTagNo() == 0) {
            return SubjectKeyIdentifier.getInstance((ASN1TaggedObject)this.id, false);
        }
        return null;
    }

    public OriginatorPublicKey getOriginatorKey() {
        if (this.id instanceof ASN1TaggedObject && ((ASN1TaggedObject)this.id).getTagNo() == 1) {
            return OriginatorPublicKey.getInstance((ASN1TaggedObject)this.id, false);
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.id.toASN1Primitive();
    }
}

