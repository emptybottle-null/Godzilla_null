/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathUtils;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.cert.path.CertPathValidationResult;
import org.bouncycastle.cert.path.CertPathValidationResultBuilder;

public class CertPath {
    private final X509CertificateHolder[] certificates;

    public CertPath(X509CertificateHolder[] x509CertificateHolderArray) {
        this.certificates = this.copyArray(x509CertificateHolderArray);
    }

    public X509CertificateHolder[] getCertificates() {
        return this.copyArray(this.certificates);
    }

    public CertPathValidationResult validate(CertPathValidation[] certPathValidationArray) {
        CertPathValidationContext certPathValidationContext = new CertPathValidationContext(CertPathUtils.getCriticalExtensionsOIDs(this.certificates));
        for (int i = 0; i != certPathValidationArray.length; ++i) {
            for (int j = this.certificates.length - 1; j >= 0; --j) {
                try {
                    certPathValidationContext.setIsEndEntity(j == 0);
                    certPathValidationArray[i].validate(certPathValidationContext, this.certificates[j]);
                    continue;
                } catch (CertPathValidationException certPathValidationException) {
                    return new CertPathValidationResult(certPathValidationContext, j, i, certPathValidationException);
                }
            }
        }
        return new CertPathValidationResult(certPathValidationContext);
    }

    public CertPathValidationResult evaluate(CertPathValidation[] certPathValidationArray) {
        CertPathValidationContext certPathValidationContext = new CertPathValidationContext(CertPathUtils.getCriticalExtensionsOIDs(this.certificates));
        CertPathValidationResultBuilder certPathValidationResultBuilder = new CertPathValidationResultBuilder();
        for (int i = 0; i != certPathValidationArray.length; ++i) {
            for (int j = this.certificates.length - 1; j >= 0; --j) {
                try {
                    certPathValidationContext.setIsEndEntity(j == 0);
                    certPathValidationArray[i].validate(certPathValidationContext, this.certificates[j]);
                    continue;
                } catch (CertPathValidationException certPathValidationException) {
                    certPathValidationResultBuilder.addException(certPathValidationException);
                }
            }
        }
        return certPathValidationResultBuilder.build();
    }

    private X509CertificateHolder[] copyArray(X509CertificateHolder[] x509CertificateHolderArray) {
        X509CertificateHolder[] x509CertificateHolderArray2 = new X509CertificateHolder[x509CertificateHolderArray.length];
        System.arraycopy(x509CertificateHolderArray, 0, x509CertificateHolderArray2, 0, x509CertificateHolderArray2.length);
        return x509CertificateHolderArray2;
    }

    public int length() {
        return this.certificates.length;
    }
}

