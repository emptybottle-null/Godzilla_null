/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Attribute;

public class SubjectDirectoryAttributes
extends ASN1Object {
    private Vector attributes = new Vector();

    public static SubjectDirectoryAttributes getInstance(Object object) {
        if (object instanceof SubjectDirectoryAttributes) {
            return (SubjectDirectoryAttributes)object;
        }
        if (object != null) {
            return new SubjectDirectoryAttributes(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SubjectDirectoryAttributes(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(enumeration.nextElement());
            this.attributes.addElement(Attribute.getInstance(aSN1Sequence2));
        }
    }

    public SubjectDirectoryAttributes(Vector vector) {
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            this.attributes.addElement(enumeration.nextElement());
        }
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumeration = this.attributes.elements();
        while (enumeration.hasMoreElements()) {
            aSN1EncodableVector.add((Attribute)enumeration.nextElement());
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public Vector getAttributes() {
        return this.attributes;
    }
}

