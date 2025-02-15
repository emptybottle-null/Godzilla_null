/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.isismtt.x509.NamingAuthority;
import org.bouncycastle.asn1.isismtt.x509.ProfessionInfo;
import org.bouncycastle.asn1.x509.GeneralName;

public class Admissions
extends ASN1Object {
    private GeneralName admissionAuthority;
    private NamingAuthority namingAuthority;
    private ASN1Sequence professionInfos;

    public static Admissions getInstance(Object object) {
        if (object == null || object instanceof Admissions) {
            return (Admissions)object;
        }
        if (object instanceof ASN1Sequence) {
            return new Admissions((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    private Admissions(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            switch (((ASN1TaggedObject)aSN1Encodable).getTagNo()) {
                case 0: {
                    this.admissionAuthority = GeneralName.getInstance((ASN1TaggedObject)aSN1Encodable, true);
                    break;
                }
                case 1: {
                    this.namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)aSN1Encodable, true);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Bad tag number: " + ((ASN1TaggedObject)aSN1Encodable).getTagNo());
                }
            }
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        }
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            switch (((ASN1TaggedObject)aSN1Encodable).getTagNo()) {
                case 1: {
                    this.namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)aSN1Encodable, true);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Bad tag number: " + ((ASN1TaggedObject)aSN1Encodable).getTagNo());
                }
            }
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        }
        this.professionInfos = ASN1Sequence.getInstance(aSN1Encodable);
        if (enumeration.hasMoreElements()) {
            throw new IllegalArgumentException("Bad object encountered: " + enumeration.nextElement().getClass());
        }
    }

    public Admissions(GeneralName generalName, NamingAuthority namingAuthority, ProfessionInfo[] professionInfoArray) {
        this.admissionAuthority = generalName;
        this.namingAuthority = namingAuthority;
        this.professionInfos = new DERSequence(professionInfoArray);
    }

    public GeneralName getAdmissionAuthority() {
        return this.admissionAuthority;
    }

    public NamingAuthority getNamingAuthority() {
        return this.namingAuthority;
    }

    public ProfessionInfo[] getProfessionInfos() {
        ProfessionInfo[] professionInfoArray = new ProfessionInfo[this.professionInfos.size()];
        int n = 0;
        Enumeration enumeration = this.professionInfos.getObjects();
        while (enumeration.hasMoreElements()) {
            professionInfoArray[n++] = ProfessionInfo.getInstance(enumeration.nextElement());
        }
        return professionInfoArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.admissionAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.admissionAuthority));
        }
        if (this.namingAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.namingAuthority));
        }
        aSN1EncodableVector.add(this.professionInfos);
        return new DERSequence(aSN1EncodableVector);
    }
}

