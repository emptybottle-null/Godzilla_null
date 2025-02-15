/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.util.Arrays;

public class SubjectKeyIdentifier
extends ASN1Object {
    private byte[] keyidentifier;

    public static SubjectKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return SubjectKeyIdentifier.getInstance(ASN1OctetString.getInstance(aSN1TaggedObject, bl));
    }

    public static SubjectKeyIdentifier getInstance(Object object) {
        if (object instanceof SubjectKeyIdentifier) {
            return (SubjectKeyIdentifier)object;
        }
        if (object != null) {
            return new SubjectKeyIdentifier(ASN1OctetString.getInstance(object));
        }
        return null;
    }

    public static SubjectKeyIdentifier fromExtensions(Extensions extensions) {
        return SubjectKeyIdentifier.getInstance(extensions.getExtensionParsedValue(Extension.subjectKeyIdentifier));
    }

    public SubjectKeyIdentifier(byte[] byArray) {
        this.keyidentifier = Arrays.clone(byArray);
    }

    protected SubjectKeyIdentifier(ASN1OctetString aSN1OctetString) {
        this(aSN1OctetString.getOctets());
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone(this.keyidentifier);
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.getKeyIdentifier());
    }
}

