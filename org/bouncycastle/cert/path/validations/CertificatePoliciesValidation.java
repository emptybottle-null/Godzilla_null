/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path.validations;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.PolicyConstraints;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.cert.path.validations.ValidationUtils;
import org.bouncycastle.util.Memoable;

public class CertificatePoliciesValidation
implements CertPathValidation {
    private int explicitPolicy;
    private int policyMapping;
    private int inhibitAnyPolicy;

    CertificatePoliciesValidation(int n) {
        this(n, false, false, false);
    }

    CertificatePoliciesValidation(int n, boolean bl, boolean bl2, boolean bl3) {
        this.explicitPolicy = bl ? 0 : n + 1;
        this.inhibitAnyPolicy = bl2 ? 0 : n + 1;
        this.policyMapping = bl3 ? 0 : n + 1;
    }

    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        certPathValidationContext.addHandledExtension(Extension.policyConstraints);
        certPathValidationContext.addHandledExtension(Extension.inhibitAnyPolicy);
        if (!certPathValidationContext.isEndEntity() && !ValidationUtils.isSelfIssued(x509CertificateHolder)) {
            int n;
            Object object;
            this.explicitPolicy = this.countDown(this.explicitPolicy);
            this.policyMapping = this.countDown(this.policyMapping);
            this.inhibitAnyPolicy = this.countDown(this.inhibitAnyPolicy);
            PolicyConstraints policyConstraints = PolicyConstraints.fromExtensions(x509CertificateHolder.getExtensions());
            if (policyConstraints != null) {
                BigInteger bigInteger;
                object = policyConstraints.getRequireExplicitPolicyMapping();
                if (object != null && ((BigInteger)object).intValue() < this.explicitPolicy) {
                    this.explicitPolicy = ((BigInteger)object).intValue();
                }
                if ((bigInteger = policyConstraints.getInhibitPolicyMapping()) != null && bigInteger.intValue() < this.policyMapping) {
                    this.policyMapping = bigInteger.intValue();
                }
            }
            if ((object = x509CertificateHolder.getExtension(Extension.inhibitAnyPolicy)) != null && (n = ASN1Integer.getInstance(((Extension)object).getParsedValue()).getValue().intValue()) < this.inhibitAnyPolicy) {
                this.inhibitAnyPolicy = n;
            }
        }
    }

    private int countDown(int n) {
        if (n != 0) {
            return n - 1;
        }
        return 0;
    }

    public Memoable copy() {
        return new CertificatePoliciesValidation(0);
    }

    public void reset(Memoable memoable) {
        CertificatePoliciesValidation certificatePoliciesValidation = (CertificatePoliciesValidation)memoable;
    }
}

