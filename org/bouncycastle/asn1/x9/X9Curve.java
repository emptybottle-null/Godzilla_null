/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x9.X9FieldElement;
import org.bouncycastle.asn1.x9.X9FieldID;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;

public class X9Curve
extends ASN1Object
implements X9ObjectIdentifiers {
    private ECCurve curve;
    private byte[] seed;
    private ASN1ObjectIdentifier fieldIdentifier = null;

    public X9Curve(ECCurve eCCurve) {
        this.curve = eCCurve;
        this.seed = null;
        this.setFieldIdentifier();
    }

    public X9Curve(ECCurve eCCurve, byte[] byArray) {
        this.curve = eCCurve;
        this.seed = byArray;
        this.setFieldIdentifier();
    }

    public X9Curve(X9FieldID x9FieldID, ASN1Sequence aSN1Sequence) {
        this.fieldIdentifier = x9FieldID.getIdentifier();
        if (this.fieldIdentifier.equals(prime_field)) {
            BigInteger bigInteger = ((ASN1Integer)x9FieldID.getParameters()).getValue();
            X9FieldElement x9FieldElement = new X9FieldElement(bigInteger, (ASN1OctetString)aSN1Sequence.getObjectAt(0));
            X9FieldElement x9FieldElement2 = new X9FieldElement(bigInteger, (ASN1OctetString)aSN1Sequence.getObjectAt(1));
            this.curve = new ECCurve.Fp(bigInteger, x9FieldElement.getValue().toBigInteger(), x9FieldElement2.getValue().toBigInteger());
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            ASN1Object aSN1Object;
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(x9FieldID.getParameters());
            int n = ((ASN1Integer)aSN1Sequence2.getObjectAt(0)).getValue().intValue();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)aSN1Sequence2.getObjectAt(1);
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            if (aSN1ObjectIdentifier.equals(tpBasis)) {
                n2 = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).getValue().intValue();
            } else if (aSN1ObjectIdentifier.equals(ppBasis)) {
                aSN1Object = ASN1Sequence.getInstance(aSN1Sequence2.getObjectAt(2));
                n2 = ASN1Integer.getInstance(((ASN1Sequence)aSN1Object).getObjectAt(0)).getValue().intValue();
                n3 = ASN1Integer.getInstance(((ASN1Sequence)aSN1Object).getObjectAt(1)).getValue().intValue();
                n4 = ASN1Integer.getInstance(((ASN1Sequence)aSN1Object).getObjectAt(2)).getValue().intValue();
            } else {
                throw new IllegalArgumentException("This type of EC basis is not implemented");
            }
            aSN1Object = new X9FieldElement(n, n2, n3, n4, (ASN1OctetString)aSN1Sequence.getObjectAt(0));
            X9FieldElement x9FieldElement = new X9FieldElement(n, n2, n3, n4, (ASN1OctetString)aSN1Sequence.getObjectAt(1));
            this.curve = new ECCurve.F2m(n, n2, n3, n4, ((X9FieldElement)aSN1Object).getValue().toBigInteger(), x9FieldElement.getValue().toBigInteger());
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
        if (aSN1Sequence.size() == 3) {
            this.seed = ((DERBitString)aSN1Sequence.getObjectAt(2)).getBytes();
        }
    }

    private void setFieldIdentifier() {
        if (ECAlgorithms.isFpCurve(this.curve)) {
            this.fieldIdentifier = prime_field;
        } else if (ECAlgorithms.isF2mCurve(this.curve)) {
            this.fieldIdentifier = characteristic_two_field;
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.fieldIdentifier.equals(prime_field)) {
            aSN1EncodableVector.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            aSN1EncodableVector.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            aSN1EncodableVector.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            aSN1EncodableVector.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        }
        if (this.seed != null) {
            aSN1EncodableVector.add(new DERBitString(this.seed));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

