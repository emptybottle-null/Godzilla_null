/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class GMSSPublicKey
extends ASN1Object {
    private ASN1Integer version;
    private byte[] publicKey;

    private GMSSPublicKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("size of seq = " + aSN1Sequence.size());
        }
        this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.publicKey = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
    }

    public GMSSPublicKey(byte[] byArray) {
        this.version = new ASN1Integer(0L);
        this.publicKey = byArray;
    }

    public static GMSSPublicKey getInstance(Object object) {
        if (object instanceof GMSSPublicKey) {
            return (GMSSPublicKey)object;
        }
        if (object != null) {
            return new GMSSPublicKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(new DEROctetString(this.publicKey));
        return new DERSequence(aSN1EncodableVector);
    }
}

