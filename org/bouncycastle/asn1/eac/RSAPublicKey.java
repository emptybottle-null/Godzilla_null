/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.asn1.eac.UnsignedInteger;

public class RSAPublicKey
extends PublicKeyDataObject {
    private ASN1ObjectIdentifier usage;
    private BigInteger modulus;
    private BigInteger exponent;
    private int valid = 0;
    private static int modulusValid = 1;
    private static int exponentValid = 2;

    RSAPublicKey(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.usage = ASN1ObjectIdentifier.getInstance(enumeration.nextElement());
        block4: while (enumeration.hasMoreElements()) {
            UnsignedInteger unsignedInteger = UnsignedInteger.getInstance(enumeration.nextElement());
            switch (unsignedInteger.getTagNo()) {
                case 1: {
                    this.setModulus(unsignedInteger);
                    continue block4;
                }
                case 2: {
                    this.setExponent(unsignedInteger);
                    continue block4;
                }
            }
            throw new IllegalArgumentException("Unknown DERTaggedObject :" + unsignedInteger.getTagNo() + "-> not an Iso7816RSAPublicKeyStructure");
        }
        if (this.valid != 3) {
            throw new IllegalArgumentException("missing argument -> not an Iso7816RSAPublicKeyStructure");
        }
    }

    public RSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger, BigInteger bigInteger2) {
        this.usage = aSN1ObjectIdentifier;
        this.modulus = bigInteger;
        this.exponent = bigInteger2;
    }

    public ASN1ObjectIdentifier getUsage() {
        return this.usage;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPublicExponent() {
        return this.exponent;
    }

    private void setModulus(UnsignedInteger unsignedInteger) {
        if ((this.valid & modulusValid) == 0) {
            this.valid |= modulusValid;
        } else {
            throw new IllegalArgumentException("Modulus already set");
        }
        this.modulus = unsignedInteger.getValue();
    }

    private void setExponent(UnsignedInteger unsignedInteger) {
        if ((this.valid & exponentValid) == 0) {
            this.valid |= exponentValid;
        } else {
            throw new IllegalArgumentException("Exponent already set");
        }
        this.exponent = unsignedInteger.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.usage);
        aSN1EncodableVector.add(new UnsignedInteger(1, this.getModulus()));
        aSN1EncodableVector.add(new UnsignedInteger(2, this.getPublicExponent()));
        return new DERSequence(aSN1EncodableVector);
    }
}

