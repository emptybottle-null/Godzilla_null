/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.math.ec.ECFieldElement;

public class X9FieldElement
extends ASN1Object {
    protected ECFieldElement f;
    private static X9IntegerConverter converter = new X9IntegerConverter();

    public X9FieldElement(ECFieldElement eCFieldElement) {
        this.f = eCFieldElement;
    }

    public X9FieldElement(BigInteger bigInteger, ASN1OctetString aSN1OctetString) {
        this(new ECFieldElement.Fp(bigInteger, new BigInteger(1, aSN1OctetString.getOctets())));
    }

    public X9FieldElement(int n, int n2, int n3, int n4, ASN1OctetString aSN1OctetString) {
        this(new ECFieldElement.F2m(n, n2, n3, n4, new BigInteger(1, aSN1OctetString.getOctets())));
    }

    public ECFieldElement getValue() {
        return this.f;
    }

    public ASN1Primitive toASN1Primitive() {
        int n = converter.getByteLength(this.f);
        byte[] byArray = converter.integerToBytes(this.f.toBigInteger(), n);
        return new DEROctetString(byArray);
    }
}

