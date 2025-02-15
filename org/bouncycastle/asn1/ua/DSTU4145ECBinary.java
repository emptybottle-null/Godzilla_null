/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ua;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ua.DSTU4145BinaryField;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;

public class DSTU4145ECBinary
extends ASN1Object {
    BigInteger version = BigInteger.valueOf(0L);
    DSTU4145BinaryField f;
    ASN1Integer a;
    ASN1OctetString b;
    ASN1Integer n;
    ASN1OctetString bp;

    public DSTU4145ECBinary(ECDomainParameters eCDomainParameters) {
        ECCurve eCCurve = eCDomainParameters.getCurve();
        if (!ECAlgorithms.isF2mCurve(eCCurve)) {
            throw new IllegalArgumentException("only binary domain is possible");
        }
        PolynomialExtensionField polynomialExtensionField = (PolynomialExtensionField)eCCurve.getField();
        int[] nArray = polynomialExtensionField.getMinimalPolynomial().getExponentsPresent();
        if (nArray.length == 3) {
            this.f = new DSTU4145BinaryField(nArray[2], nArray[1]);
        } else if (nArray.length == 5) {
            this.f = new DSTU4145BinaryField(nArray[4], nArray[1], nArray[2], nArray[3]);
        } else {
            throw new IllegalArgumentException("curve must have a trinomial or pentanomial basis");
        }
        this.a = new ASN1Integer(eCCurve.getA().toBigInteger());
        this.b = new DEROctetString(eCCurve.getB().getEncoded());
        this.n = new ASN1Integer(eCDomainParameters.getN());
        this.bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCDomainParameters.getG()));
    }

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        int n = 0;
        if (aSN1Sequence.getObjectAt(n) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(n);
            if (aSN1TaggedObject.isExplicit() && 0 == aSN1TaggedObject.getTagNo()) {
                this.version = ASN1Integer.getInstance(aSN1TaggedObject.getLoadedObject()).getValue();
                ++n;
            } else {
                throw new IllegalArgumentException("object parse error");
            }
        }
        this.f = DSTU4145BinaryField.getInstance(aSN1Sequence.getObjectAt(n));
        this.a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(++n));
        this.b = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(++n));
        this.n = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(++n));
        this.bp = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(++n));
    }

    public static DSTU4145ECBinary getInstance(Object object) {
        if (object instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary)object;
        }
        if (object != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public DSTU4145BinaryField getField() {
        return this.f;
    }

    public BigInteger getA() {
        return this.a.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.b.getOctets());
    }

    public BigInteger getN() {
        return this.n.getValue();
    }

    public byte[] getG() {
        return Arrays.clone(this.bp.getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (0 != this.version.compareTo(BigInteger.valueOf(0L))) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new ASN1Integer(this.version)));
        }
        aSN1EncodableVector.add(this.f);
        aSN1EncodableVector.add(this.a);
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.n);
        aSN1EncodableVector.add(this.bp);
        return new DERSequence(aSN1EncodableVector);
    }
}

