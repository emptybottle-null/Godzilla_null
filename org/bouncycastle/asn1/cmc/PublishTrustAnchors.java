/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class PublishTrustAnchors
extends ASN1Object {
    private final ASN1Integer seqNumber;
    private final AlgorithmIdentifier hashAlgorithm;
    private final ASN1Sequence anchorHashes;

    public PublishTrustAnchors(BigInteger bigInteger, AlgorithmIdentifier algorithmIdentifier, byte[][] byArray) {
        this.seqNumber = new ASN1Integer(bigInteger);
        this.hashAlgorithm = algorithmIdentifier;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != byArray.length; ++i) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(byArray[i])));
        }
        this.anchorHashes = new DERSequence(aSN1EncodableVector);
    }

    private PublishTrustAnchors(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.seqNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.hashAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.anchorHashes = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static PublishTrustAnchors getInstance(Object object) {
        if (object instanceof PublishTrustAnchors) {
            return (PublishTrustAnchors)object;
        }
        if (object != null) {
            return new PublishTrustAnchors(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BigInteger getSeqNumber() {
        return this.seqNumber.getValue();
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public byte[][] getAnchorHashes() {
        byte[][] byArrayArray = new byte[this.anchorHashes.size()][];
        for (int i = 0; i != byArrayArray.length; ++i) {
            byArrayArray[i] = Arrays.clone(ASN1OctetString.getInstance(this.anchorHashes.getObjectAt(i)).getOctets());
        }
        return byArrayArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.seqNumber);
        aSN1EncodableVector.add(this.hashAlgorithm);
        aSN1EncodableVector.add(this.anchorHashes);
        return new DERSequence(aSN1EncodableVector);
    }
}

