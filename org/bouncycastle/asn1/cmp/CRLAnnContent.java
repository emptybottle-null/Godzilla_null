/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.CertificateList;

public class CRLAnnContent
extends ASN1Object {
    private ASN1Sequence content;

    private CRLAnnContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CRLAnnContent getInstance(Object object) {
        if (object instanceof CRLAnnContent) {
            return (CRLAnnContent)object;
        }
        if (object != null) {
            return new CRLAnnContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public CRLAnnContent(CertificateList certificateList) {
        this.content = new DERSequence(certificateList);
    }

    public CertificateList[] getCertificateLists() {
        CertificateList[] certificateListArray = new CertificateList[this.content.size()];
        for (int i = 0; i != certificateListArray.length; ++i) {
            certificateListArray[i] = CertificateList.getInstance(this.content.getObjectAt(i));
        }
        return certificateListArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

