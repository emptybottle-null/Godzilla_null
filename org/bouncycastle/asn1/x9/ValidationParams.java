/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

public class ValidationParams
extends ASN1Object {
    private DERBitString seed;
    private ASN1Integer pgenCounter;

    public static ValidationParams getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ValidationParams.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static ValidationParams getInstance(Object object) {
        if (object instanceof ValidationParams) {
            return (ValidationParams)object;
        }
        if (object != null) {
            return new ValidationParams(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ValidationParams(byte[] byArray, int n) {
        if (byArray == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        }
        this.seed = new DERBitString(byArray);
        this.pgenCounter = new ASN1Integer(n);
    }

    public ValidationParams(DERBitString dERBitString, ASN1Integer aSN1Integer) {
        if (dERBitString == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        }
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'pgenCounter' cannot be null");
        }
        this.seed = dERBitString;
        this.pgenCounter = aSN1Integer;
    }

    private ValidationParams(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.seed = DERBitString.getInstance(aSN1Sequence.getObjectAt(0));
        this.pgenCounter = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public byte[] getSeed() {
        return this.seed.getBytes();
    }

    public BigInteger getPgenCounter() {
        return this.pgenCounter.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.seed);
        aSN1EncodableVector.add(this.pgenCounter);
        return new DERSequence(aSN1EncodableVector);
    }
}

