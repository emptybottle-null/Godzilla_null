/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path.validations;

import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;

public class KeyUsageValidation
implements CertPathValidation {
    private boolean isMandatory;

    public KeyUsageValidation() {
        this(true);
    }

    public KeyUsageValidation(boolean bl) {
        this.isMandatory = bl;
    }

    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        certPathValidationContext.addHandledExtension(Extension.keyUsage);
        if (!certPathValidationContext.isEndEntity()) {
            KeyUsage keyUsage = KeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
            if (keyUsage != null) {
                if (!keyUsage.hasUsages(4)) {
                    throw new CertPathValidationException("Issuer certificate KeyUsage extension does not permit key signing");
                }
            } else if (this.isMandatory) {
                throw new CertPathValidationException("KeyUsage extension not present in CA certificate");
            }
        }
    }

    public Memoable copy() {
        return new KeyUsageValidation(this.isMandatory);
    }

    public void reset(Memoable memoable) {
        KeyUsageValidation keyUsageValidation = (KeyUsageValidation)memoable;
        this.isMandatory = keyUsageValidation.isMandatory;
    }
}

