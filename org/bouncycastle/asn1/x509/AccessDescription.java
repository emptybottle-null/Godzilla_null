/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.GeneralName;

public class AccessDescription
extends ASN1Object {
    public static final ASN1ObjectIdentifier id_ad_caIssuers = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2");
    public static final ASN1ObjectIdentifier id_ad_ocsp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1");
    ASN1ObjectIdentifier accessMethod = null;
    GeneralName accessLocation = null;

    public static AccessDescription getInstance(Object object) {
        if (object instanceof AccessDescription) {
            return (AccessDescription)object;
        }
        if (object != null) {
            return new AccessDescription(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private AccessDescription(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("wrong number of elements in sequence");
        }
        this.accessMethod = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.accessLocation = GeneralName.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public AccessDescription(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName generalName) {
        this.accessMethod = aSN1ObjectIdentifier;
        this.accessLocation = generalName;
    }

    public ASN1ObjectIdentifier getAccessMethod() {
        return this.accessMethod;
    }

    public GeneralName getAccessLocation() {
        return this.accessLocation;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.accessMethod);
        aSN1EncodableVector.add(this.accessLocation);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AccessDescription: Oid(" + this.accessMethod.getId() + ")";
    }
}

