/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x9.X9ECParameters;

public class X962Parameters
extends ASN1Object
implements ASN1Choice {
    private ASN1Primitive params = null;

    public static X962Parameters getInstance(Object object) {
        if (object == null || object instanceof X962Parameters) {
            return (X962Parameters)object;
        }
        if (object instanceof ASN1Primitive) {
            return new X962Parameters((ASN1Primitive)object);
        }
        if (object instanceof byte[]) {
            try {
                return new X962Parameters(ASN1Primitive.fromByteArray((byte[])object));
            } catch (Exception exception) {
                throw new IllegalArgumentException("unable to parse encoded data: " + exception.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance()");
    }

    public static X962Parameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return X962Parameters.getInstance(aSN1TaggedObject.getObject());
    }

    public X962Parameters(X9ECParameters x9ECParameters) {
        this.params = x9ECParameters.toASN1Primitive();
    }

    public X962Parameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.params = aSN1ObjectIdentifier;
    }

    public X962Parameters(ASN1Null aSN1Null) {
        this.params = aSN1Null;
    }

    public X962Parameters(ASN1Primitive aSN1Primitive) {
        this.params = aSN1Primitive;
    }

    public boolean isNamedCurve() {
        return this.params instanceof ASN1ObjectIdentifier;
    }

    public boolean isImplicitlyCA() {
        return this.params instanceof ASN1Null;
    }

    public ASN1Primitive getParameters() {
        return this.params;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.params;
    }
}

