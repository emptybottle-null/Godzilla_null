/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class ObjectDigestInfo
extends ASN1Object {
    public static final int publicKey = 0;
    public static final int publicKeyCert = 1;
    public static final int otherObjectDigest = 2;
    ASN1Enumerated digestedObjectType;
    ASN1ObjectIdentifier otherObjectTypeID;
    AlgorithmIdentifier digestAlgorithm;
    DERBitString objectDigest;

    public static ObjectDigestInfo getInstance(Object object) {
        if (object instanceof ObjectDigestInfo) {
            return (ObjectDigestInfo)object;
        }
        if (object != null) {
            return new ObjectDigestInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static ObjectDigestInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ObjectDigestInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ObjectDigestInfo(int n, ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.digestedObjectType = new ASN1Enumerated(n);
        if (n == 2) {
            this.otherObjectTypeID = aSN1ObjectIdentifier;
        }
        this.digestAlgorithm = algorithmIdentifier;
        this.objectDigest = new DERBitString(byArray);
    }

    private ObjectDigestInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 4 || aSN1Sequence.size() < 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.digestedObjectType = ASN1Enumerated.getInstance(aSN1Sequence.getObjectAt(0));
        int n = 0;
        if (aSN1Sequence.size() == 4) {
            this.otherObjectTypeID = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            ++n;
        }
        this.digestAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1 + n));
        this.objectDigest = DERBitString.getInstance(aSN1Sequence.getObjectAt(2 + n));
    }

    public ASN1Enumerated getDigestedObjectType() {
        return this.digestedObjectType;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        return this.otherObjectTypeID;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public DERBitString getObjectDigest() {
        return this.objectDigest;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.digestedObjectType);
        if (this.otherObjectTypeID != null) {
            aSN1EncodableVector.add(this.otherObjectTypeID);
        }
        aSN1EncodableVector.add(this.digestAlgorithm);
        aSN1EncodableVector.add(this.objectDigest);
        return new DERSequence(aSN1EncodableVector);
    }
}

