/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralSubtree;

public class NameConstraints
extends ASN1Object {
    private GeneralSubtree[] permitted;
    private GeneralSubtree[] excluded;

    public static NameConstraints getInstance(Object object) {
        if (object instanceof NameConstraints) {
            return (NameConstraints)object;
        }
        if (object != null) {
            return new NameConstraints(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        block4: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(enumeration.nextElement());
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.permitted = this.createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    continue block4;
                }
                case 1: {
                    this.excluded = this.createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    continue block4;
                }
            }
            throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
        }
    }

    public NameConstraints(GeneralSubtree[] generalSubtreeArray, GeneralSubtree[] generalSubtreeArray2) {
        this.permitted = NameConstraints.cloneSubtree(generalSubtreeArray);
        this.excluded = NameConstraints.cloneSubtree(generalSubtreeArray2);
    }

    private GeneralSubtree[] createArray(ASN1Sequence aSN1Sequence) {
        GeneralSubtree[] generalSubtreeArray = new GeneralSubtree[aSN1Sequence.size()];
        for (int i = 0; i != generalSubtreeArray.length; ++i) {
            generalSubtreeArray[i] = GeneralSubtree.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return generalSubtreeArray;
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return NameConstraints.cloneSubtree(this.permitted);
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return NameConstraints.cloneSubtree(this.excluded);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.permitted != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DERSequence(this.permitted)));
        }
        if (this.excluded != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DERSequence(this.excluded)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    private static GeneralSubtree[] cloneSubtree(GeneralSubtree[] generalSubtreeArray) {
        if (generalSubtreeArray != null) {
            GeneralSubtree[] generalSubtreeArray2 = new GeneralSubtree[generalSubtreeArray.length];
            System.arraycopy(generalSubtreeArray, 0, generalSubtreeArray2, 0, generalSubtreeArray2.length);
            return generalSubtreeArray2;
        }
        return null;
    }
}

