/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.OtherKeyAttribute;

public class RecipientKeyIdentifier
extends ASN1Object {
    private ASN1OctetString subjectKeyIdentifier;
    private ASN1GeneralizedTime date;
    private OtherKeyAttribute other;

    public RecipientKeyIdentifier(ASN1OctetString aSN1OctetString, ASN1GeneralizedTime aSN1GeneralizedTime, OtherKeyAttribute otherKeyAttribute) {
        this.subjectKeyIdentifier = aSN1OctetString;
        this.date = aSN1GeneralizedTime;
        this.other = otherKeyAttribute;
    }

    public RecipientKeyIdentifier(byte[] byArray, ASN1GeneralizedTime aSN1GeneralizedTime, OtherKeyAttribute otherKeyAttribute) {
        this.subjectKeyIdentifier = new DEROctetString(byArray);
        this.date = aSN1GeneralizedTime;
        this.other = otherKeyAttribute;
    }

    public RecipientKeyIdentifier(byte[] byArray) {
        this(byArray, null, null);
    }

    public RecipientKeyIdentifier(ASN1Sequence aSN1Sequence) {
        this.subjectKeyIdentifier = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
        switch (aSN1Sequence.size()) {
            case 1: {
                break;
            }
            case 2: {
                if (aSN1Sequence.getObjectAt(1) instanceof ASN1GeneralizedTime) {
                    this.date = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(1));
                    break;
                }
                this.other = OtherKeyAttribute.getInstance(aSN1Sequence.getObjectAt(2));
                break;
            }
            case 3: {
                this.date = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(1));
                this.other = OtherKeyAttribute.getInstance(aSN1Sequence.getObjectAt(2));
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid RecipientKeyIdentifier");
            }
        }
    }

    public static RecipientKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return RecipientKeyIdentifier.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static RecipientKeyIdentifier getInstance(Object object) {
        if (object instanceof RecipientKeyIdentifier) {
            return (RecipientKeyIdentifier)object;
        }
        if (object != null) {
            return new RecipientKeyIdentifier(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1OctetString getSubjectKeyIdentifier() {
        return this.subjectKeyIdentifier;
    }

    public ASN1GeneralizedTime getDate() {
        return this.date;
    }

    public OtherKeyAttribute getOtherKeyAttribute() {
        return this.other;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.subjectKeyIdentifier);
        if (this.date != null) {
            aSN1EncodableVector.add(this.date);
        }
        if (this.other != null) {
            aSN1EncodableVector.add(this.other);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

