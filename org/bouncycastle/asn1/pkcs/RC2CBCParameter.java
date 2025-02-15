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

public class RC2CBCParameter
extends ASN1Object {
    ASN1Integer version;
    ASN1OctetString iv;

    public static RC2CBCParameter getInstance(Object object) {
        if (object instanceof RC2CBCParameter) {
            return (RC2CBCParameter)object;
        }
        if (object != null) {
            return new RC2CBCParameter(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public RC2CBCParameter(byte[] byArray) {
        this.version = null;
        this.iv = new DEROctetString(byArray);
    }

    public RC2CBCParameter(int n, byte[] byArray) {
        this.version = new ASN1Integer(n);
        this.iv = new DEROctetString(byArray);
    }

    private RC2CBCParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1) {
            this.version = null;
            this.iv = (ASN1OctetString)aSN1Sequence.getObjectAt(0);
        } else {
            this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
            this.iv = (ASN1OctetString)aSN1Sequence.getObjectAt(1);
        }
    }

    public BigInteger getRC2ParameterVersion() {
        if (this.version == null) {
            return null;
        }
        return this.version.getValue();
    }

    public byte[] getIV() {
        return this.iv.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version != null) {
            aSN1EncodableVector.add(this.version);
        }
        aSN1EncodableVector.add(this.iv);
        return new DERSequence(aSN1EncodableVector);
    }
}

