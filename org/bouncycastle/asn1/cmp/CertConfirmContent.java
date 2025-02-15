/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cmp.CertStatus;

public class CertConfirmContent
extends ASN1Object {
    private ASN1Sequence content;

    private CertConfirmContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CertConfirmContent getInstance(Object object) {
        if (object instanceof CertConfirmContent) {
            return (CertConfirmContent)object;
        }
        if (object != null) {
            return new CertConfirmContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public CertStatus[] toCertStatusArray() {
        CertStatus[] certStatusArray = new CertStatus[this.content.size()];
        for (int i = 0; i != certStatusArray.length; ++i) {
            certStatusArray[i] = CertStatus.getInstance(this.content.getObjectAt(i));
        }
        return certStatusArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

