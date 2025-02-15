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

public class PKCS12PBEParams
extends ASN1Object {
    ASN1Integer iterations;
    ASN1OctetString iv;

    public PKCS12PBEParams(byte[] byArray, int n) {
        this.iv = new DEROctetString(byArray);
        this.iterations = new ASN1Integer(n);
    }

    private PKCS12PBEParams(ASN1Sequence aSN1Sequence) {
        this.iv = (ASN1OctetString)aSN1Sequence.getObjectAt(0);
        this.iterations = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static PKCS12PBEParams getInstance(Object object) {
        if (object instanceof PKCS12PBEParams) {
            return (PKCS12PBEParams)object;
        }
        if (object != null) {
            return new PKCS12PBEParams(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BigInteger getIterations() {
        return this.iterations.getValue();
    }

    public byte[] getIV() {
        return this.iv.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.iv);
        aSN1EncodableVector.add(this.iterations);
        return new DERSequence(aSN1EncodableVector);
    }
}

