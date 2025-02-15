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
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class RSAPrivateKey
extends ASN1Object {
    private BigInteger version;
    private BigInteger modulus;
    private BigInteger publicExponent;
    private BigInteger privateExponent;
    private BigInteger prime1;
    private BigInteger prime2;
    private BigInteger exponent1;
    private BigInteger exponent2;
    private BigInteger coefficient;
    private ASN1Sequence otherPrimeInfos = null;

    public static RSAPrivateKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return RSAPrivateKey.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static RSAPrivateKey getInstance(Object object) {
        if (object instanceof RSAPrivateKey) {
            return (RSAPrivateKey)object;
        }
        if (object != null) {
            return new RSAPrivateKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public RSAPrivateKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.version = BigInteger.valueOf(0L);
        this.modulus = bigInteger;
        this.publicExponent = bigInteger2;
        this.privateExponent = bigInteger3;
        this.prime1 = bigInteger4;
        this.prime2 = bigInteger5;
        this.exponent1 = bigInteger6;
        this.exponent2 = bigInteger7;
        this.coefficient = bigInteger8;
    }

    private RSAPrivateKey(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        BigInteger bigInteger = ((ASN1Integer)enumeration.nextElement()).getValue();
        if (bigInteger.intValue() != 0 && bigInteger.intValue() != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        this.version = bigInteger;
        this.modulus = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.publicExponent = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.privateExponent = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.prime1 = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.prime2 = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.exponent1 = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.exponent2 = ((ASN1Integer)enumeration.nextElement()).getValue();
        this.coefficient = ((ASN1Integer)enumeration.nextElement()).getValue();
        if (enumeration.hasMoreElements()) {
            this.otherPrimeInfos = (ASN1Sequence)enumeration.nextElement();
        }
    }

    public BigInteger getVersion() {
        return this.version;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }

    public BigInteger getPrivateExponent() {
        return this.privateExponent;
    }

    public BigInteger getPrime1() {
        return this.prime1;
    }

    public BigInteger getPrime2() {
        return this.prime2;
    }

    public BigInteger getExponent1() {
        return this.exponent1;
    }

    public BigInteger getExponent2() {
        return this.exponent2;
    }

    public BigInteger getCoefficient() {
        return this.coefficient;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.version));
        aSN1EncodableVector.add(new ASN1Integer(this.getModulus()));
        aSN1EncodableVector.add(new ASN1Integer(this.getPublicExponent()));
        aSN1EncodableVector.add(new ASN1Integer(this.getPrivateExponent()));
        aSN1EncodableVector.add(new ASN1Integer(this.getPrime1()));
        aSN1EncodableVector.add(new ASN1Integer(this.getPrime2()));
        aSN1EncodableVector.add(new ASN1Integer(this.getExponent1()));
        aSN1EncodableVector.add(new ASN1Integer(this.getExponent2()));
        aSN1EncodableVector.add(new ASN1Integer(this.getCoefficient()));
        if (this.otherPrimeInfos != null) {
            aSN1EncodableVector.add(this.otherPrimeInfos);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

