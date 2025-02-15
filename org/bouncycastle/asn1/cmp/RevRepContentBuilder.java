/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cmp.RevRepContent;
import org.bouncycastle.asn1.crmf.CertId;
import org.bouncycastle.asn1.x509.CertificateList;

public class RevRepContentBuilder {
    private ASN1EncodableVector status = new ASN1EncodableVector();
    private ASN1EncodableVector revCerts = new ASN1EncodableVector();
    private ASN1EncodableVector crls = new ASN1EncodableVector();

    public RevRepContentBuilder add(PKIStatusInfo pKIStatusInfo) {
        this.status.add(pKIStatusInfo);
        return this;
    }

    public RevRepContentBuilder add(PKIStatusInfo pKIStatusInfo, CertId certId) {
        if (this.status.size() != this.revCerts.size()) {
            throw new IllegalStateException("status and revCerts sequence must be in common order");
        }
        this.status.add(pKIStatusInfo);
        this.revCerts.add(certId);
        return this;
    }

    public RevRepContentBuilder addCrl(CertificateList certificateList) {
        this.crls.add(certificateList);
        return this;
    }

    public RevRepContent build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DERSequence(this.status));
        if (this.revCerts.size() != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DERSequence(this.revCerts)));
        }
        if (this.crls.size() != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, new DERSequence(this.crls)));
        }
        return RevRepContent.getInstance(new DERSequence(aSN1EncodableVector));
    }
}

