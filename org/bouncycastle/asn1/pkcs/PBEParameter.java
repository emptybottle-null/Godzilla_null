/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

public class PBEParameter
extends ASN1Object {
    ASN1Integer iterations;
    ASN1OctetString salt;

    public PBEParameter(byte[] byArray, int n) {
        if (byArray.length != 8) {
            throw new IllegalArgumentException("salt length must be 8");
        }
        this.salt = new DEROctetString(byArray);
        this.iterations = new ASN1Integer(n);
    }

    private PBEParameter(ASN1Sequence aSN1Sequence) {
        this.salt = (ASN1OctetString)aSN1Sequence.getObjectAt(0);
        this.iterations = (ASN1Integer)aSN1Sequence.getObjectAt(1);
    }

    public static PBEParameter getInstance(Object object) {
        if (object instanceof PBEParameter) {
            return (PBEParameter)object;
        }
        if (object != null) {
            return new PBEParameter(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.iterations.getValue();
    }

    public byte[] getSalt() {
        return this.salt.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.salt);
        aSN1EncodableVector.add(this.iterations);
        return new DERSequence(aSN1EncodableVector);
    }
}

