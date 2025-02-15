/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.cert.path.CertPathValidationResult;

class CertPathValidationResultBuilder {
    CertPathValidationResultBuilder() {
    }

    public CertPathValidationResult build() {
        return new CertPathValidationResult(null, 0, 0, null);
    }

    public void addException(CertPathValidationException certPathValidationException) {
    }
}

