/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path.validations;

import java.math.BigInteger;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;

public class BasicConstraintsValidation
implements CertPathValidation {
    private boolean isMandatory;
    private BasicConstraints bc;
    private int pathLengthRemaining;
    private BigInteger maxPathLength;

    public BasicConstraintsValidation() {
        this(true);
    }

    public BasicConstraintsValidation(boolean bl) {
        this.isMandatory = bl;
    }

    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        if (this.maxPathLength != null && this.pathLengthRemaining < 0) {
            throw new CertPathValidationException("BasicConstraints path length exceeded");
        }
        certPathValidationContext.addHandledExtension(Extension.basicConstraints);
        BasicConstraints basicConstraints = BasicConstraints.fromExtensions(x509CertificateHolder.getExtensions());
        if (basicConstraints != null) {
            if (this.bc != null) {
                int n;
                BigInteger bigInteger;
                if (basicConstraints.isCA() && (bigInteger = basicConstraints.getPathLenConstraint()) != null && (n = bigInteger.intValue()) < this.pathLengthRemaining) {
                    this.pathLengthRemaining = n;
                    this.bc = basicConstraints;
                }
            } else {
                this.bc = basicConstraints;
                if (basicConstraints.isCA()) {
                    this.maxPathLength = basicConstraints.getPathLenConstraint();
                    if (this.maxPathLength != null) {
                        this.pathLengthRemaining = this.maxPathLength.intValue();
                    }
                }
            }
        } else if (this.bc != null) {
            --this.pathLengthRemaining;
        }
        if (this.isMandatory && this.bc == null) {
            throw new CertPathValidationException("BasicConstraints not present in path");
        }
    }

    public Memoable copy() {
        BasicConstraintsValidation basicConstraintsValidation = new BasicConstraintsValidation(this.isMandatory);
        basicConstraintsValidation.bc = this.bc;
        basicConstraintsValidation.pathLengthRemaining = this.pathLengthRemaining;
        return basicConstraintsValidation;
    }

    public void reset(Memoable memoable) {
        BasicConstraintsValidation basicConstraintsValidation = (BasicConstraintsValidation)memoable;
        this.isMandatory = basicConstraintsValidation.isMandatory;
        this.bc = basicConstraintsValidation.bc;
        this.pathLengthRemaining = basicConstraintsValidation.pathLengthRemaining;
    }
}

