/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXParameters;
import java.security.cert.X509CertSelector;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509CertStoreSelector;

public class ExtendedPKIXBuilderParameters
extends ExtendedPKIXParameters {
    private int maxPathLength = 5;
    private Set excludedCerts = Collections.EMPTY_SET;

    public Set getExcludedCerts() {
        return Collections.unmodifiableSet(this.excludedCerts);
    }

    public void setExcludedCerts(Set set) {
        if (set == null) {
            set = Collections.EMPTY_SET;
        } else {
            this.excludedCerts = new HashSet(set);
        }
    }

    public ExtendedPKIXBuilderParameters(Set set, Selector selector) throws InvalidAlgorithmParameterException {
        super(set);
        this.setTargetConstraints(selector);
    }

    public void setMaxPathLength(int n) {
        if (n < -1) {
            throw new InvalidParameterException("The maximum path length parameter can not be less than -1.");
        }
        this.maxPathLength = n;
    }

    public int getMaxPathLength() {
        return this.maxPathLength;
    }

    protected void setParams(PKIXParameters pKIXParameters) {
        PKIXParameters pKIXParameters2;
        super.setParams(pKIXParameters);
        if (pKIXParameters instanceof ExtendedPKIXBuilderParameters) {
            pKIXParameters2 = (ExtendedPKIXBuilderParameters)pKIXParameters;
            this.maxPathLength = ((ExtendedPKIXBuilderParameters)pKIXParameters2).maxPathLength;
            this.excludedCerts = new HashSet(((ExtendedPKIXBuilderParameters)pKIXParameters2).excludedCerts);
        }
        if (pKIXParameters instanceof PKIXBuilderParameters) {
            pKIXParameters2 = (PKIXBuilderParameters)pKIXParameters;
            this.maxPathLength = ((PKIXBuilderParameters)pKIXParameters2).getMaxPathLength();
        }
    }

    public Object clone() {
        ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = null;
        try {
            extendedPKIXBuilderParameters = new ExtendedPKIXBuilderParameters(this.getTrustAnchors(), this.getTargetConstraints());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        extendedPKIXBuilderParameters.setParams(this);
        return extendedPKIXBuilderParameters;
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pKIXParameters) {
        ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters;
        try {
            extendedPKIXBuilderParameters = new ExtendedPKIXBuilderParameters(pKIXParameters.getTrustAnchors(), X509CertStoreSelector.getInstance((X509CertSelector)pKIXParameters.getTargetCertConstraints()));
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        extendedPKIXBuilderParameters.setParams(pKIXParameters);
        return extendedPKIXBuilderParameters;
    }
}

