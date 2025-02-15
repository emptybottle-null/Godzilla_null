/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path.validations;

import org.bouncycastle.cert.path.CertPath;
import org.bouncycastle.cert.path.validations.CertificatePoliciesValidation;

public class CertificatePoliciesValidationBuilder {
    private boolean isExplicitPolicyRequired;
    private boolean isAnyPolicyInhibited;
    private boolean isPolicyMappingInhibited;

    public void setAnyPolicyInhibited(boolean bl) {
        this.isAnyPolicyInhibited = bl;
    }

    public void setExplicitPolicyRequired(boolean bl) {
        this.isExplicitPolicyRequired = bl;
    }

    public void setPolicyMappingInhibited(boolean bl) {
        this.isPolicyMappingInhibited = bl;
    }

    public CertificatePoliciesValidation build(int n) {
        return new CertificatePoliciesValidation(n, this.isExplicitPolicyRequired, this.isAnyPolicyInhibited, this.isPolicyMappingInhibited);
    }

    public CertificatePoliciesValidation build(CertPath certPath) {
        return this.build(certPath.length());
    }
}

