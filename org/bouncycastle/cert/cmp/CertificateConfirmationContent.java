/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import org.bouncycastle.asn1.cmp.CertConfirmContent;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.cert.cmp.CertificateStatus;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;

public class CertificateConfirmationContent {
    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private CertConfirmContent content;

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent) {
        this(certConfirmContent, new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.content = certConfirmContent;
    }

    public CertConfirmContent toASN1Structure() {
        return this.content;
    }

    public CertificateStatus[] getStatusMessages() {
        CertStatus[] certStatusArray = this.content.toCertStatusArray();
        CertificateStatus[] certificateStatusArray = new CertificateStatus[certStatusArray.length];
        for (int i = 0; i != certificateStatusArray.length; ++i) {
            certificateStatusArray[i] = new CertificateStatus(this.digestAlgFinder, certStatusArray[i]);
        }
        return certificateStatusArray;
    }
}

