/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class ElGamalParameter
extends ASN1Object {
    ASN1Integer p;
    ASN1Integer g;

    public ElGamalParameter(BigInteger bigInteger, BigInteger bigInteger2) {
        this.p = new ASN1Integer(bigInteger);
        this.g = new ASN1Integer(bigInteger2);
    }

    private ElGamalParameter(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.p = (ASN1Integer)enumeration.nextElement();
        this.g = (ASN1Integer)enumeration.nextElement();
    }

    public static ElGamalParameter getInstance(Object object) {
        if (object instanceof ElGamalParameter) {
            return (ElGamalParameter)object;
        }
        if (object != null) {
            return new ElGamalParameter(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.g.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.g);
        return new DERSequence(aSN1EncodableVector);
    }
}

