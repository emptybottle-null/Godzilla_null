/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class DHParameter
extends ASN1Object {
    ASN1Integer p;
    ASN1Integer g;
    ASN1Integer l;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int n) {
        this.p = new ASN1Integer(bigInteger);
        this.g = new ASN1Integer(bigInteger2);
        this.l = n != 0 ? new ASN1Integer(n) : null;
    }

    public static DHParameter getInstance(Object object) {
        if (object instanceof DHParameter) {
            return (DHParameter)object;
        }
        if (object != null) {
            return new DHParameter(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.p = ASN1Integer.getInstance(enumeration.nextElement());
        this.g = ASN1Integer.getInstance(enumeration.nextElement());
        this.l = enumeration.hasMoreElements() ? (ASN1Integer)enumeration.nextElement() : null;
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.g.getPositiveValue();
    }

    public BigInteger getL() {
        if (this.l == null) {
            return null;
        }
        return this.l.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.g);
        if (this.getL() != null) {
            aSN1EncodableVector.add(this.l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

