/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

public class CertPolicyId
extends ASN1Object {
    private ASN1ObjectIdentifier id;

    private CertPolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.id = aSN1ObjectIdentifier;
    }

    public static CertPolicyId getInstance(Object object) {
        if (object instanceof CertPolicyId) {
            return (CertPolicyId)object;
        }
        if (object != null) {
            return new CertPolicyId(ASN1ObjectIdentifier.getInstance(object));
        }
        return null;
    }

    public String getId() {
        return this.id.getId();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.id;
    }
}

