/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class OriginatorPublicKey
extends ASN1Object {
    private AlgorithmIdentifier algorithm;
    private DERBitString publicKey;

    public OriginatorPublicKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.algorithm = algorithmIdentifier;
        this.publicKey = new DERBitString(byArray);
    }

    public OriginatorPublicKey(ASN1Sequence aSN1Sequence) {
        this.algorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.publicKey = (DERBitString)aSN1Sequence.getObjectAt(1);
    }

    public static OriginatorPublicKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return OriginatorPublicKey.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static OriginatorPublicKey getInstance(Object object) {
        if (object instanceof OriginatorPublicKey) {
            return (OriginatorPublicKey)object;
        }
        if (object != null) {
            return new OriginatorPublicKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public DERBitString getPublicKey() {
        return this.publicKey;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algorithm);
        aSN1EncodableVector.add(this.publicKey);
        return new DERSequence(aSN1EncodableVector);
    }
}

