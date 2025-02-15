/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class XMSSKeyParams
extends ASN1Object {
    private final ASN1Integer version;
    private final int height;
    private final AlgorithmIdentifier treeDigest;

    public XMSSKeyParams(int n, AlgorithmIdentifier algorithmIdentifier) {
        this.version = new ASN1Integer(0L);
        this.height = n;
        this.treeDigest = algorithmIdentifier;
    }

    private XMSSKeyParams(ASN1Sequence aSN1Sequence) {
        this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.height = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.treeDigest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static XMSSKeyParams getInstance(Object object) {
        if (object instanceof XMSSKeyParams) {
            return (XMSSKeyParams)object;
        }
        if (object != null) {
            return new XMSSKeyParams(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public int getHeight() {
        return this.height;
    }

    public AlgorithmIdentifier getTreeDigest() {
        return this.treeDigest;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(new ASN1Integer(this.height));
        aSN1EncodableVector.add(this.treeDigest);
        return new DERSequence(aSN1EncodableVector);
    }
}

