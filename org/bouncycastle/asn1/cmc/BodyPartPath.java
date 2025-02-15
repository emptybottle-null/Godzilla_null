/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.Utils;

public class BodyPartPath
extends ASN1Object {
    private final BodyPartID[] bodyPartIDs;

    public static BodyPartPath getInstance(Object object) {
        if (object instanceof BodyPartPath) {
            return (BodyPartPath)object;
        }
        if (object != null) {
            return new BodyPartPath(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static BodyPartPath getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return BodyPartPath.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public BodyPartPath(BodyPartID bodyPartID) {
        this.bodyPartIDs = new BodyPartID[]{bodyPartID};
    }

    public BodyPartPath(BodyPartID[] bodyPartIDArray) {
        this.bodyPartIDs = Utils.clone(bodyPartIDArray);
    }

    private BodyPartPath(ASN1Sequence aSN1Sequence) {
        this.bodyPartIDs = Utils.toBodyPartIDArray(aSN1Sequence);
    }

    public BodyPartID[] getBodyPartIDs() {
        return Utils.clone(this.bodyPartIDs);
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.bodyPartIDs);
    }
}

