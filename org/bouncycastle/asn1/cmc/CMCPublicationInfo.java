/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.PKIPublicationInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class CMCPublicationInfo
extends ASN1Object {
    private final AlgorithmIdentifier hashAlg;
    private final ASN1Sequence certHashes;
    private final PKIPublicationInfo pubInfo;

    public CMCPublicationInfo(AlgorithmIdentifier algorithmIdentifier, byte[][] byArray, PKIPublicationInfo pKIPublicationInfo) {
        this.hashAlg = algorithmIdentifier;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != byArray.length; ++i) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(byArray[i])));
        }
        this.certHashes = new DERSequence(aSN1EncodableVector);
        this.pubInfo = pKIPublicationInfo;
    }

    private CMCPublicationInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.hashAlg = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.certHashes = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        this.pubInfo = PKIPublicationInfo.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static CMCPublicationInfo getInstance(Object object) {
        if (object instanceof CMCPublicationInfo) {
            return (CMCPublicationInfo)object;
        }
        if (object != null) {
            return new CMCPublicationInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getHashAlg() {
        return this.hashAlg;
    }

    public byte[][] getCertHashes() {
        byte[][] byArrayArray = new byte[this.certHashes.size()][];
        for (int i = 0; i != byArrayArray.length; ++i) {
            byArrayArray[i] = Arrays.clone(ASN1OctetString.getInstance(this.certHashes.getObjectAt(i)).getOctets());
        }
        return byArrayArray;
    }

    public PKIPublicationInfo getPubInfo() {
        return this.pubInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.hashAlg);
        aSN1EncodableVector.add(this.certHashes);
        aSN1EncodableVector.add(this.pubInfo);
        return new DERSequence(aSN1EncodableVector);
    }
}

