/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class GCMParameters
extends ASN1Object {
    private byte[] nonce;
    private int icvLen;

    public static GCMParameters getInstance(Object object) {
        if (object instanceof GCMParameters) {
            return (GCMParameters)object;
        }
        if (object != null) {
            return new GCMParameters(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private GCMParameters(ASN1Sequence aSN1Sequence) {
        this.nonce = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets();
        this.icvLen = aSN1Sequence.size() == 2 ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue() : 12;
    }

    public GCMParameters(byte[] byArray, int n) {
        this.nonce = Arrays.clone(byArray);
        this.icvLen = n;
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public int getIcvLen() {
        return this.icvLen;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.nonce));
        if (this.icvLen != 12) {
            aSN1EncodableVector.add(new ASN1Integer(this.icvLen));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

