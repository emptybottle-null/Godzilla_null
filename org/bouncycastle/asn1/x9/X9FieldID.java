/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;

public class X9FieldID
extends ASN1Object
implements X9ObjectIdentifiers {
    private ASN1ObjectIdentifier id;
    private ASN1Primitive parameters;

    public X9FieldID(BigInteger bigInteger) {
        this.id = prime_field;
        this.parameters = new ASN1Integer(bigInteger);
    }

    public X9FieldID(int n, int n2) {
        this(n, n2, 0, 0);
    }

    public X9FieldID(int n, int n2, int n3, int n4) {
        this.id = characteristic_two_field;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(n));
        if (n3 == 0) {
            if (n4 != 0) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            aSN1EncodableVector.add(tpBasis);
            aSN1EncodableVector.add(new ASN1Integer(n2));
        } else {
            if (n3 <= n2 || n4 <= n3) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            aSN1EncodableVector.add(ppBasis);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(new ASN1Integer(n2));
            aSN1EncodableVector2.add(new ASN1Integer(n3));
            aSN1EncodableVector2.add(new ASN1Integer(n4));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        this.parameters = new DERSequence(aSN1EncodableVector);
    }

    private X9FieldID(ASN1Sequence aSN1Sequence) {
        this.id = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.parameters = aSN1Sequence.getObjectAt(1).toASN1Primitive();
    }

    public static X9FieldID getInstance(Object object) {
        if (object instanceof X9FieldID) {
            return (X9FieldID)object;
        }
        if (object != null) {
            return new X9FieldID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1ObjectIdentifier getIdentifier() {
        return this.id;
    }

    public ASN1Primitive getParameters() {
        return this.parameters;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.id);
        aSN1EncodableVector.add(this.parameters);
        return new DERSequence(aSN1EncodableVector);
    }
}

