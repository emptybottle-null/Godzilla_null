/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;

public class ResponderID
extends ASN1Object
implements ASN1Choice {
    private ASN1Encodable value;

    public ResponderID(ASN1OctetString aSN1OctetString) {
        this.value = aSN1OctetString;
    }

    public ResponderID(X500Name x500Name) {
        this.value = x500Name;
    }

    public static ResponderID getInstance(Object object) {
        if (object instanceof ResponderID) {
            return (ResponderID)object;
        }
        if (object instanceof DEROctetString) {
            return new ResponderID((DEROctetString)object);
        }
        if (object instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)object;
            if (aSN1TaggedObject.getTagNo() == 1) {
                return new ResponderID(X500Name.getInstance(aSN1TaggedObject, true));
            }
            return new ResponderID(ASN1OctetString.getInstance(aSN1TaggedObject, true));
        }
        return new ResponderID(X500Name.getInstance(object));
    }

    public static ResponderID getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ResponderID.getInstance(aSN1TaggedObject.getObject());
    }

    public byte[] getKeyHash() {
        if (this.value instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString)this.value;
            return aSN1OctetString.getOctets();
        }
        return null;
    }

    public X500Name getName() {
        if (this.value instanceof ASN1OctetString) {
            return null;
        }
        return X500Name.getInstance(this.value);
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.value instanceof ASN1OctetString) {
            return new DERTaggedObject(true, 2, this.value);
        }
        return new DERTaggedObject(true, 1, this.value);
    }
}

