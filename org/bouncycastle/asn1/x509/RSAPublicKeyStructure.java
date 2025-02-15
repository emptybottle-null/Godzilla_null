/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class RSAPublicKeyStructure
extends ASN1Object {
    private BigInteger modulus;
    private BigInteger publicExponent;

    public static RSAPublicKeyStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return RSAPublicKeyStructure.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static RSAPublicKeyStructure getInstance(Object object) {
        if (object == null || object instanceof RSAPublicKeyStructure) {
            return (RSAPublicKeyStructure)object;
        }
        if (object instanceof ASN1Sequence) {
            return new RSAPublicKeyStructure((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("Invalid RSAPublicKeyStructure: " + object.getClass().getName());
    }

    public RSAPublicKeyStructure(BigInteger bigInteger, BigInteger bigInteger2) {
        this.modulus = bigInteger;
        this.publicExponent = bigInteger2;
    }

    public RSAPublicKeyStructure(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.modulus = ASN1Integer.getInstance(enumeration.nextElement()).getPositiveValue();
        this.publicExponent = ASN1Integer.getInstance(enumeration.nextElement()).getPositiveValue();
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.getModulus()));
        aSN1EncodableVector.add(new ASN1Integer(this.getPublicExponent()));
        return new DERSequence(aSN1EncodableVector);
    }
}

