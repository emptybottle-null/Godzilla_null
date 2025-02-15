/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.est;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.Attribute;

public class AttrOrOID
extends ASN1Object
implements ASN1Choice {
    private final ASN1ObjectIdentifier oid;
    private final Attribute attribute;

    public AttrOrOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.oid = aSN1ObjectIdentifier;
        this.attribute = null;
    }

    public AttrOrOID(Attribute attribute) {
        this.oid = null;
        this.attribute = attribute;
    }

    public static AttrOrOID getInstance(Object object) {
        if (object instanceof AttrOrOID) {
            return (AttrOrOID)object;
        }
        if (object != null) {
            if (object instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                    return new AttrOrOID(ASN1ObjectIdentifier.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new AttrOrOID(Attribute.getInstance(aSN1Primitive));
                }
            }
            if (object instanceof byte[]) {
                try {
                    return AttrOrOID.getInstance(ASN1Primitive.fromByteArray((byte[])object));
                } catch (IOException iOException) {
                    throw new IllegalArgumentException("unknown encoding in getInstance()");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
        }
        return null;
    }

    public boolean isOid() {
        return this.oid != null;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.oid != null) {
            return this.oid;
        }
        return this.attribute.toASN1Primitive();
    }
}

