/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.BodyPartID;

public class LraPopWitness
extends ASN1Object {
    private final BodyPartID pkiDataBodyid;
    private final ASN1Sequence bodyIds;

    public LraPopWitness(BodyPartID bodyPartID, ASN1Sequence aSN1Sequence) {
        this.pkiDataBodyid = bodyPartID;
        this.bodyIds = aSN1Sequence;
    }

    private LraPopWitness(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.pkiDataBodyid = BodyPartID.getInstance(aSN1Sequence.getObjectAt(0));
        this.bodyIds = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static LraPopWitness getInstance(Object object) {
        if (object instanceof LraPopWitness) {
            return (LraPopWitness)object;
        }
        if (object != null) {
            return new LraPopWitness(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BodyPartID getPkiDataBodyid() {
        return this.pkiDataBodyid;
    }

    public BodyPartID[] getBodyIds() {
        BodyPartID[] bodyPartIDArray = new BodyPartID[this.bodyIds.size()];
        for (int i = 0; i != this.bodyIds.size(); ++i) {
            bodyPartIDArray[i] = BodyPartID.getInstance(this.bodyIds.getObjectAt(i));
        }
        return bodyPartIDArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiDataBodyid);
        aSN1EncodableVector.add(this.bodyIds);
        return new DERSequence(aSN1EncodableVector);
    }
}

