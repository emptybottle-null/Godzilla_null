/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmc.BodyPartPath;

public class BodyPartReference
extends ASN1Object
implements ASN1Choice {
    private final BodyPartID bodyPartID;
    private final BodyPartPath bodyPartPath;

    public BodyPartReference(BodyPartID bodyPartID) {
        this.bodyPartID = bodyPartID;
        this.bodyPartPath = null;
    }

    public BodyPartReference(BodyPartPath bodyPartPath) {
        this.bodyPartID = null;
        this.bodyPartPath = bodyPartPath;
    }

    public static BodyPartReference getInstance(Object object) {
        if (object instanceof BodyPartReference) {
            return (BodyPartReference)object;
        }
        if (object != null) {
            if (object instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Integer) {
                    return new BodyPartReference(BodyPartID.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new BodyPartReference(BodyPartPath.getInstance(aSN1Primitive));
                }
            }
            if (object instanceof byte[]) {
                try {
                    return BodyPartReference.getInstance(ASN1Primitive.fromByteArray((byte[])object));
                } catch (IOException iOException) {
                    throw new IllegalArgumentException("unknown encoding in getInstance()");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
        }
        return null;
    }

    public boolean isBodyPartID() {
        return this.bodyPartID != null;
    }

    public BodyPartID getBodyPartID() {
        return this.bodyPartID;
    }

    public BodyPartPath getBodyPartPath() {
        return this.bodyPartPath;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.bodyPartID != null) {
            return this.bodyPartID.toASN1Primitive();
        }
        return this.bodyPartPath.toASN1Primitive();
    }
}

