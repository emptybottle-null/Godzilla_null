/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class OtherRevRefs
extends ASN1Object {
    private ASN1ObjectIdentifier otherRevRefType;
    private ASN1Encodable otherRevRefs;

    public static OtherRevRefs getInstance(Object object) {
        if (object instanceof OtherRevRefs) {
            return (OtherRevRefs)object;
        }
        if (object != null) {
            return new OtherRevRefs(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private OtherRevRefs(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.otherRevRefType = new ASN1ObjectIdentifier(((ASN1ObjectIdentifier)aSN1Sequence.getObjectAt(0)).getId());
        try {
            this.otherRevRefs = ASN1Primitive.fromByteArray(aSN1Sequence.getObjectAt(1).toASN1Primitive().getEncoded("DER"));
        } catch (IOException iOException) {
            throw new IllegalStateException();
        }
    }

    public OtherRevRefs(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.otherRevRefType = aSN1ObjectIdentifier;
        this.otherRevRefs = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getOtherRevRefType() {
        return this.otherRevRefType;
    }

    public ASN1Encodable getOtherRevRefs() {
        return this.otherRevRefs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.otherRevRefType);
        aSN1EncodableVector.add(this.otherRevRefs);
        return new DERSequence(aSN1EncodableVector);
    }
}

