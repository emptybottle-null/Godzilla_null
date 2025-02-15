/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;

public class DHPublicKey
extends ASN1Object {
    private ASN1Integer y;

    public static DHPublicKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DHPublicKey.getInstance(ASN1Integer.getInstance(aSN1TaggedObject, bl));
    }

    public static DHPublicKey getInstance(Object object) {
        if (object == null || object instanceof DHPublicKey) {
            return (DHPublicKey)object;
        }
        if (object instanceof ASN1Integer) {
            return new DHPublicKey((ASN1Integer)object);
        }
        throw new IllegalArgumentException("Invalid DHPublicKey: " + object.getClass().getName());
    }

    private DHPublicKey(ASN1Integer aSN1Integer) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.y = aSN1Integer;
    }

    public DHPublicKey(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.y = new ASN1Integer(bigInteger);
    }

    public BigInteger getY() {
        return this.y.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.y;
    }
}

