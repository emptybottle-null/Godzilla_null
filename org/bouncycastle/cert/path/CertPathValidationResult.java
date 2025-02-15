/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import java.util.Collections;
import java.util.Set;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;

public class CertPathValidationResult {
    private final boolean isValid;
    private final CertPathValidationException cause;
    private final Set unhandledCriticalExtensionOIDs;
    private int[] certIndexes;

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext) {
        this.unhandledCriticalExtensionOIDs = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.isValid = this.unhandledCriticalExtensionOIDs.isEmpty();
        this.cause = null;
    }

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext, int n, int n2, CertPathValidationException certPathValidationException) {
        this.unhandledCriticalExtensionOIDs = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.isValid = false;
        this.cause = certPathValidationException;
    }

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext, int[] nArray, int[] nArray2, CertPathValidationException[] certPathValidationExceptionArray) {
        this.unhandledCriticalExtensionOIDs = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.isValid = false;
        this.cause = certPathValidationExceptionArray[0];
        this.certIndexes = nArray;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public Exception getCause() {
        if (this.cause != null) {
            return this.cause;
        }
        if (!this.unhandledCriticalExtensionOIDs.isEmpty()) {
            return new CertPathValidationException("Unhandled Critical Extensions");
        }
        return null;
    }

    public Set getUnhandledCriticalExtensionOIDs() {
        return this.unhandledCriticalExtensionOIDs;
    }

    public boolean isDetailed() {
        return this.certIndexes != null;
    }
}

