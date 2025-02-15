/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralName;

public class Target
extends ASN1Object
implements ASN1Choice {
    public static final int targetName = 0;
    public static final int targetGroup = 1;
    private GeneralName targName;
    private GeneralName targGroup;

    public static Target getInstance(Object object) {
        if (object == null || object instanceof Target) {
            return (Target)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new Target((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("unknown object in factory: " + object.getClass());
    }

    private Target(ASN1TaggedObject aSN1TaggedObject) {
        switch (aSN1TaggedObject.getTagNo()) {
            case 0: {
                this.targName = GeneralName.getInstance(aSN1TaggedObject, true);
                break;
            }
            case 1: {
                this.targGroup = GeneralName.getInstance(aSN1TaggedObject, true);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown tag: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public Target(int n, GeneralName generalName) {
        this(new DERTaggedObject(n, generalName));
    }

    public GeneralName getTargetGroup() {
        return this.targGroup;
    }

    public GeneralName getTargetName() {
        return this.targName;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.targName != null) {
            return new DERTaggedObject(true, 0, this.targName);
        }
        return new DERTaggedObject(true, 1, this.targGroup);
    }
}

