/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class ECGOST3410ParamSetParameters
extends ASN1Object {
    ASN1Integer p;
    ASN1Integer q;
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer x;
    ASN1Integer y;

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ECGOST3410ParamSetParameters.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static ECGOST3410ParamSetParameters getInstance(Object object) {
        if (object == null || object instanceof ECGOST3410ParamSetParameters) {
            return (ECGOST3410ParamSetParameters)object;
        }
        if (object instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + object.getClass().getName());
    }

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int n, BigInteger bigInteger5) {
        this.a = new ASN1Integer(bigInteger);
        this.b = new ASN1Integer(bigInteger2);
        this.p = new ASN1Integer(bigInteger3);
        this.q = new ASN1Integer(bigInteger4);
        this.x = new ASN1Integer(n);
        this.y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.a = (ASN1Integer)enumeration.nextElement();
        this.b = (ASN1Integer)enumeration.nextElement();
        this.p = (ASN1Integer)enumeration.nextElement();
        this.q = (ASN1Integer)enumeration.nextElement();
        this.x = (ASN1Integer)enumeration.nextElement();
        this.y = (ASN1Integer)enumeration.nextElement();
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.a);
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.q);
        aSN1EncodableVector.add(this.x);
        aSN1EncodableVector.add(this.y);
        return new DERSequence(aSN1EncodableVector);
    }
}

