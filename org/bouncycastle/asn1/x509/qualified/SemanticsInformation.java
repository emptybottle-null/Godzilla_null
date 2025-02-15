/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.GeneralName;

public class SemanticsInformation
extends ASN1Object {
    private ASN1ObjectIdentifier semanticsIdentifier;
    private GeneralName[] nameRegistrationAuthorities;

    public static SemanticsInformation getInstance(Object object) {
        if (object instanceof SemanticsInformation) {
            return (SemanticsInformation)object;
        }
        if (object != null) {
            return new SemanticsInformation(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SemanticsInformation(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        if (aSN1Sequence.size() < 1) {
            throw new IllegalArgumentException("no objects in SemanticsInformation");
        }
        Object object = enumeration.nextElement();
        if (object instanceof ASN1ObjectIdentifier) {
            this.semanticsIdentifier = ASN1ObjectIdentifier.getInstance(object);
            object = enumeration.hasMoreElements() ? enumeration.nextElement() : null;
        }
        if (object != null) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(object);
            this.nameRegistrationAuthorities = new GeneralName[aSN1Sequence2.size()];
            for (int i = 0; i < aSN1Sequence2.size(); ++i) {
                this.nameRegistrationAuthorities[i] = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
            }
        }
    }

    public SemanticsInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName[] generalNameArray) {
        this.semanticsIdentifier = aSN1ObjectIdentifier;
        this.nameRegistrationAuthorities = SemanticsInformation.cloneNames(generalNameArray);
    }

    public SemanticsInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.semanticsIdentifier = aSN1ObjectIdentifier;
        this.nameRegistrationAuthorities = null;
    }

    public SemanticsInformation(GeneralName[] generalNameArray) {
        this.semanticsIdentifier = null;
        this.nameRegistrationAuthorities = SemanticsInformation.cloneNames(generalNameArray);
    }

    public ASN1ObjectIdentifier getSemanticsIdentifier() {
        return this.semanticsIdentifier;
    }

    public GeneralName[] getNameRegistrationAuthorities() {
        return SemanticsInformation.cloneNames(this.nameRegistrationAuthorities);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.semanticsIdentifier != null) {
            aSN1EncodableVector.add(this.semanticsIdentifier);
        }
        if (this.nameRegistrationAuthorities != null) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            for (int i = 0; i < this.nameRegistrationAuthorities.length; ++i) {
                aSN1EncodableVector2.add(this.nameRegistrationAuthorities[i]);
            }
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    private static GeneralName[] cloneNames(GeneralName[] generalNameArray) {
        if (generalNameArray != null) {
            GeneralName[] generalNameArray2 = new GeneralName[generalNameArray.length];
            System.arraycopy(generalNameArray, 0, generalNameArray2, 0, generalNameArray.length);
            return generalNameArray2;
        }
        return null;
    }
}

