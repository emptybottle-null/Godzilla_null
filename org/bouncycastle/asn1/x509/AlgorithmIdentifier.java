/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class AlgorithmIdentifier
extends ASN1Object {
    private ASN1ObjectIdentifier algorithm;
    private ASN1Encodable parameters;

    public static AlgorithmIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return AlgorithmIdentifier.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static AlgorithmIdentifier getInstance(Object object) {
        if (object instanceof AlgorithmIdentifier) {
            return (AlgorithmIdentifier)object;
        }
        if (object != null) {
            return new AlgorithmIdentifier(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.algorithm = aSN1ObjectIdentifier;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.algorithm = aSN1ObjectIdentifier;
        this.parameters = aSN1Encodable;
    }

    private AlgorithmIdentifier(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.algorithm = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.parameters = aSN1Sequence.size() == 2 ? aSN1Sequence.getObjectAt(1) : null;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public ASN1Encodable getParameters() {
        return this.parameters;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algorithm);
        if (this.parameters != null) {
            aSN1EncodableVector.add(this.parameters);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

