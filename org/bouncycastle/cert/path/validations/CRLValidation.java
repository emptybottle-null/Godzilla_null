/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path.validations;

import java.util.Collection;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;

public class CRLValidation
implements CertPathValidation {
    private Store crls;
    private X500Name workingIssuerName;

    public CRLValidation(X500Name x500Name, Store store) {
        this.workingIssuerName = x500Name;
        this.crls = store;
    }

    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        Collection collection = this.crls.getMatches(new Selector(){

            public boolean match(Object object) {
                X509CRLHolder x509CRLHolder = (X509CRLHolder)object;
                return x509CRLHolder.getIssuer().equals(CRLValidation.this.workingIssuerName);
            }

            public Object clone() {
                return this;
            }
        });
        if (collection.isEmpty()) {
            throw new CertPathValidationException("CRL for " + this.workingIssuerName + " not found");
        }
        for (X509CRLHolder x509CRLHolder : collection) {
            if (x509CRLHolder.getRevokedCertificate(x509CertificateHolder.getSerialNumber()) == null) continue;
            throw new CertPathValidationException("Certificate revoked");
        }
        this.workingIssuerName = x509CertificateHolder.getSubject();
    }

    public Memoable copy() {
        return new CRLValidation(this.workingIssuerName, this.crls);
    }

    public void reset(Memoable memoable) {
        CRLValidation cRLValidation = (CRLValidation)memoable;
        this.workingIssuerName = cRLValidation.workingIssuerName;
        this.crls = cRLValidation.crls;
    }
}

