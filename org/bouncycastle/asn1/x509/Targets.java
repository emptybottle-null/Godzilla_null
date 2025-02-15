/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Target;

public class Targets
extends ASN1Object {
    private ASN1Sequence targets;

    public static Targets getInstance(Object object) {
        if (object instanceof Targets) {
            return (Targets)object;
        }
        if (object != null) {
            return new Targets(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private Targets(ASN1Sequence aSN1Sequence) {
        this.targets = aSN1Sequence;
    }

    public Targets(Target[] targetArray) {
        this.targets = new DERSequence(targetArray);
    }

    public Target[] getTargets() {
        Target[] targetArray = new Target[this.targets.size()];
        int n = 0;
        Enumeration enumeration = this.targets.getObjects();
        while (enumeration.hasMoreElements()) {
            targetArray[n++] = Target.getInstance(enumeration.nextElement());
        }
        return targetArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.targets;
    }
}

