/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.isismtt.x509.Admissions;
import org.bouncycastle.asn1.x509.GeneralName;

public class AdmissionSyntax
extends ASN1Object {
    private GeneralName admissionAuthority;
    private ASN1Sequence contentsOfAdmissions;

    public static AdmissionSyntax getInstance(Object object) {
        if (object == null || object instanceof AdmissionSyntax) {
            return (AdmissionSyntax)object;
        }
        if (object instanceof ASN1Sequence) {
            return new AdmissionSyntax((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    private AdmissionSyntax(ASN1Sequence aSN1Sequence) {
        switch (aSN1Sequence.size()) {
            case 1: {
                this.contentsOfAdmissions = DERSequence.getInstance(aSN1Sequence.getObjectAt(0));
                break;
            }
            case 2: {
                this.admissionAuthority = GeneralName.getInstance(aSN1Sequence.getObjectAt(0));
                this.contentsOfAdmissions = DERSequence.getInstance(aSN1Sequence.getObjectAt(1));
                break;
            }
            default: {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
            }
        }
    }

    public AdmissionSyntax(GeneralName generalName, ASN1Sequence aSN1Sequence) {
        this.admissionAuthority = generalName;
        this.contentsOfAdmissions = aSN1Sequence;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.admissionAuthority != null) {
            aSN1EncodableVector.add(this.admissionAuthority);
        }
        aSN1EncodableVector.add(this.contentsOfAdmissions);
        return new DERSequence(aSN1EncodableVector);
    }

    public GeneralName getAdmissionAuthority() {
        return this.admissionAuthority;
    }

    public Admissions[] getContentsOfAdmissions() {
        Admissions[] admissionsArray = new Admissions[this.contentsOfAdmissions.size()];
        int n = 0;
        Enumeration enumeration = this.contentsOfAdmissions.getObjects();
        while (enumeration.hasMoreElements()) {
            admissionsArray[n++] = Admissions.getInstance(enumeration.nextElement());
        }
        return admissionsArray;
    }
}

