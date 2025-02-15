/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.asn1.eac.UnsignedInteger;
import org.bouncycastle.util.Arrays;

public class ECDSAPublicKey
extends PublicKeyDataObject {
    private ASN1ObjectIdentifier usage;
    private BigInteger primeModulusP;
    private BigInteger firstCoefA;
    private BigInteger secondCoefB;
    private byte[] basePointG;
    private BigInteger orderOfBasePointR;
    private byte[] publicPointY;
    private BigInteger cofactorF;
    private int options;
    private static final int P = 1;
    private static final int A = 2;
    private static final int B = 4;
    private static final int G = 8;
    private static final int R = 16;
    private static final int Y = 32;
    private static final int F = 64;

    ECDSAPublicKey(ASN1Sequence aSN1Sequence) throws IllegalArgumentException {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.usage = ASN1ObjectIdentifier.getInstance(enumeration.nextElement());
        this.options = 0;
        while (enumeration.hasMoreElements()) {
            Object e = enumeration.nextElement();
            if (e instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)e;
                switch (aSN1TaggedObject.getTagNo()) {
                    case 1: {
                        this.setPrimeModulusP(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                        break;
                    }
                    case 2: {
                        this.setFirstCoefA(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                        break;
                    }
                    case 3: {
                        this.setSecondCoefB(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                        break;
                    }
                    case 4: {
                        this.setBasePointG(ASN1OctetString.getInstance(aSN1TaggedObject, false));
                        break;
                    }
                    case 5: {
                        this.setOrderOfBasePointR(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                        break;
                    }
                    case 6: {
                        this.setPublicPointY(ASN1OctetString.getInstance(aSN1TaggedObject, false));
                        break;
                    }
                    case 7: {
                        this.setCofactorF(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                        break;
                    }
                    default: {
                        this.options = 0;
                        throw new IllegalArgumentException("Unknown Object Identifier!");
                    }
                }
                continue;
            }
            throw new IllegalArgumentException("Unknown Object Identifier!");
        }
        if (this.options != 32 && this.options != 127) {
            throw new IllegalArgumentException("All options must be either present or absent!");
        }
    }

    public ECDSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) throws IllegalArgumentException {
        this.usage = aSN1ObjectIdentifier;
        this.setPublicPointY(new DEROctetString(byArray));
    }

    public ECDSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte[] byArray, BigInteger bigInteger4, byte[] byArray2, int n) {
        this.usage = aSN1ObjectIdentifier;
        this.setPrimeModulusP(bigInteger);
        this.setFirstCoefA(bigInteger2);
        this.setSecondCoefB(bigInteger3);
        this.setBasePointG(new DEROctetString(byArray));
        this.setOrderOfBasePointR(bigInteger4);
        this.setPublicPointY(new DEROctetString(byArray2));
        this.setCofactorF(BigInteger.valueOf(n));
    }

    public ASN1ObjectIdentifier getUsage() {
        return this.usage;
    }

    public byte[] getBasePointG() {
        if ((this.options & 8) != 0) {
            return Arrays.clone(this.basePointG);
        }
        return null;
    }

    private void setBasePointG(ASN1OctetString aSN1OctetString) throws IllegalArgumentException {
        if ((this.options & 8) == 0) {
            this.options |= 8;
        } else {
            throw new IllegalArgumentException("Base Point G already set");
        }
        this.basePointG = aSN1OctetString.getOctets();
    }

    public BigInteger getCofactorF() {
        if ((this.options & 0x40) != 0) {
            return this.cofactorF;
        }
        return null;
    }

    private void setCofactorF(BigInteger bigInteger) throws IllegalArgumentException {
        if ((this.options & 0x40) == 0) {
            this.options |= 0x40;
        } else {
            throw new IllegalArgumentException("Cofactor F already set");
        }
        this.cofactorF = bigInteger;
    }

    public BigInteger getFirstCoefA() {
        if ((this.options & 2) != 0) {
            return this.firstCoefA;
        }
        return null;
    }

    private void setFirstCoefA(BigInteger bigInteger) throws IllegalArgumentException {
        if ((this.options & 2) == 0) {
            this.options |= 2;
        } else {
            throw new IllegalArgumentException("First Coef A already set");
        }
        this.firstCoefA = bigInteger;
    }

    public BigInteger getOrderOfBasePointR() {
        if ((this.options & 0x10) != 0) {
            return this.orderOfBasePointR;
        }
        return null;
    }

    private void setOrderOfBasePointR(BigInteger bigInteger) throws IllegalArgumentException {
        if ((this.options & 0x10) == 0) {
            this.options |= 0x10;
        } else {
            throw new IllegalArgumentException("Order of base point R already set");
        }
        this.orderOfBasePointR = bigInteger;
    }

    public BigInteger getPrimeModulusP() {
        if ((this.options & 1) != 0) {
            return this.primeModulusP;
        }
        return null;
    }

    private void setPrimeModulusP(BigInteger bigInteger) {
        if ((this.options & 1) == 0) {
            this.options |= 1;
        } else {
            throw new IllegalArgumentException("Prime Modulus P already set");
        }
        this.primeModulusP = bigInteger;
    }

    public byte[] getPublicPointY() {
        if ((this.options & 0x20) != 0) {
            return Arrays.clone(this.publicPointY);
        }
        return null;
    }

    private void setPublicPointY(ASN1OctetString aSN1OctetString) throws IllegalArgumentException {
        if ((this.options & 0x20) == 0) {
            this.options |= 0x20;
        } else {
            throw new IllegalArgumentException("Public Point Y already set");
        }
        this.publicPointY = aSN1OctetString.getOctets();
    }

    public BigInteger getSecondCoefB() {
        if ((this.options & 4) != 0) {
            return this.secondCoefB;
        }
        return null;
    }

    private void setSecondCoefB(BigInteger bigInteger) throws IllegalArgumentException {
        if ((this.options & 4) == 0) {
            this.options |= 4;
        } else {
            throw new IllegalArgumentException("Second Coef B already set");
        }
        this.secondCoefB = bigInteger;
    }

    public boolean hasParameters() {
        return this.primeModulusP != null;
    }

    public ASN1EncodableVector getASN1EncodableVector(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        if (!bl) {
            aSN1EncodableVector.add(new UnsignedInteger(1, this.getPrimeModulusP()));
            aSN1EncodableVector.add(new UnsignedInteger(2, this.getFirstCoefA()));
            aSN1EncodableVector.add(new UnsignedInteger(3, this.getSecondCoefB()));
            aSN1EncodableVector.add(new DERTaggedObject(false, 4, new DEROctetString(this.getBasePointG())));
            aSN1EncodableVector.add(new UnsignedInteger(5, this.getOrderOfBasePointR()));
        }
        aSN1EncodableVector.add(new DERTaggedObject(false, 6, new DEROctetString(this.getPublicPointY())));
        if (!bl) {
            aSN1EncodableVector.add(new UnsignedInteger(7, this.getCofactorF()));
        }
        return aSN1EncodableVector;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.getASN1EncodableVector(this.usage, !this.hasParameters()));
    }
}

