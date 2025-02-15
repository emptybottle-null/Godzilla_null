/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class CompressedData
extends ASN1Object {
    private ASN1Integer version;
    private AlgorithmIdentifier compressionAlgorithm;
    private ContentInfo encapContentInfo;

    public CompressedData(AlgorithmIdentifier algorithmIdentifier, ContentInfo contentInfo) {
        this.version = new ASN1Integer(0L);
        this.compressionAlgorithm = algorithmIdentifier;
        this.encapContentInfo = contentInfo;
    }

    private CompressedData(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer)aSN1Sequence.getObjectAt(0);
        this.compressionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.encapContentInfo = ContentInfo.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static CompressedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return CompressedData.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static CompressedData getInstance(Object object) {
        if (object instanceof CompressedData) {
            return (CompressedData)object;
        }
        if (object != null) {
            return new CompressedData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier() {
        return this.compressionAlgorithm;
    }

    public ContentInfo getEncapContentInfo() {
        return this.encapContentInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.compressionAlgorithm);
        aSN1EncodableVector.add(this.encapContentInfo);
        return new BERSequence(aSN1EncodableVector);
    }
}

