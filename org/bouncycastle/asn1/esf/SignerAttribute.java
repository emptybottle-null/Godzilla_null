/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.AttributeCertificate;

public class SignerAttribute
extends ASN1Object {
    private Object[] values;

    public static SignerAttribute getInstance(Object object) {
        if (object instanceof SignerAttribute) {
            return (SignerAttribute)object;
        }
        if (object != null) {
            return new SignerAttribute(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SignerAttribute(ASN1Sequence aSN1Sequence) {
        int n = 0;
        this.values = new Object[aSN1Sequence.size()];
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(enumeration.nextElement());
            if (aSN1TaggedObject.getTagNo() == 0) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                Attribute[] attributeArray = new Attribute[aSN1Sequence2.size()];
                for (int i = 0; i != attributeArray.length; ++i) {
                    attributeArray[i] = Attribute.getInstance(aSN1Sequence2.getObjectAt(i));
                }
                this.values[n] = attributeArray;
            } else if (aSN1TaggedObject.getTagNo() == 1) {
                this.values[n] = AttributeCertificate.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
            } else {
                throw new IllegalArgumentException("illegal tag: " + aSN1TaggedObject.getTagNo());
            }
            ++n;
        }
    }

    public SignerAttribute(Attribute[] attributeArray) {
        this.values = new Object[1];
        this.values[0] = attributeArray;
    }

    public SignerAttribute(AttributeCertificate attributeCertificate) {
        this.values = new Object[1];
        this.values[0] = attributeCertificate;
    }

    public Object[] getValues() {
        Object[] objectArray = new Object[this.values.length];
        System.arraycopy(this.values, 0, objectArray, 0, objectArray.length);
        return objectArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.values.length; ++i) {
            if (this.values[i] instanceof Attribute[]) {
                aSN1EncodableVector.add(new DERTaggedObject(0, new DERSequence((Attribute[])this.values[i])));
                continue;
            }
            aSN1EncodableVector.add(new DERTaggedObject(1, (AttributeCertificate)this.values[i]));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

