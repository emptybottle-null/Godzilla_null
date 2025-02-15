/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;

public class RecipientIdentifier
extends ASN1Object
implements ASN1Choice {
    private ASN1Encodable id;

    public RecipientIdentifier(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.id = issuerAndSerialNumber;
    }

    public RecipientIdentifier(ASN1OctetString aSN1OctetString) {
        this.id = new DERTaggedObject(false, 0, aSN1OctetString);
    }

    public RecipientIdentifier(ASN1Primitive aSN1Primitive) {
        this.id = aSN1Primitive;
    }

    public static RecipientIdentifier getInstance(Object object) {
        if (object == null || object instanceof RecipientIdentifier) {
            return (RecipientIdentifier)object;
        }
        if (object instanceof IssuerAndSerialNumber) {
            return new RecipientIdentifier((IssuerAndSerialNumber)object);
        }
        if (object instanceof ASN1OctetString) {
            return new RecipientIdentifier((ASN1OctetString)object);
        }
        if (object instanceof ASN1Primitive) {
            return new RecipientIdentifier((ASN1Primitive)object);
        }
        throw new IllegalArgumentException("Illegal object in RecipientIdentifier: " + object.getClass().getName());
    }

    public boolean isTagged() {
        return this.id instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getId() {
        if (this.id instanceof ASN1TaggedObject) {
            return ASN1OctetString.getInstance((ASN1TaggedObject)this.id, false);
        }
        return IssuerAndSerialNumber.getInstance(this.id);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.id.toASN1Primitive();
    }
}

