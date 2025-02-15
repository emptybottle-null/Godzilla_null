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
import org.bouncycastle.asn1.x509.Targets;

public class TargetInformation
extends ASN1Object {
    private ASN1Sequence targets;

    public static TargetInformation getInstance(Object object) {
        if (object instanceof TargetInformation) {
            return (TargetInformation)object;
        }
        if (object != null) {
            return new TargetInformation(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private TargetInformation(ASN1Sequence aSN1Sequence) {
        this.targets = aSN1Sequence;
    }

    public Targets[] getTargetsObjects() {
        Targets[] targetsArray = new Targets[this.targets.size()];
        int n = 0;
        Enumeration enumeration = this.targets.getObjects();
        while (enumeration.hasMoreElements()) {
            targetsArray[n++] = Targets.getInstance(enumeration.nextElement());
        }
        return targetsArray;
    }

    public TargetInformation(Targets targets) {
        this.targets = new DERSequence(targets);
    }

    public TargetInformation(Target[] targetArray) {
        this(new Targets(targetArray));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.targets;
    }
}

