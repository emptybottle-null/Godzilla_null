/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.security.InvalidParameterException;
import java.security.cert.CertPathParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXExtendedParameters;

public class PKIXExtendedBuilderParameters
implements CertPathParameters {
    private final PKIXExtendedParameters baseParameters;
    private final Set<X509Certificate> excludedCerts;
    private final int maxPathLength;

    private PKIXExtendedBuilderParameters(Builder builder) {
        this.baseParameters = builder.baseParameters;
        this.excludedCerts = Collections.unmodifiableSet(builder.excludedCerts);
        this.maxPathLength = builder.maxPathLength;
    }

    public PKIXExtendedParameters getBaseParameters() {
        return this.baseParameters;
    }

    public Set getExcludedCerts() {
        return this.excludedCerts;
    }

    public int getMaxPathLength() {
        return this.maxPathLength;
    }

    public Object clone() {
        return this;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final PKIXExtendedParameters baseParameters;
        private int maxPathLength = 5;
        private Set<X509Certificate> excludedCerts = new HashSet<X509Certificate>();

        public Builder(PKIXBuilderParameters pKIXBuilderParameters) {
            this.baseParameters = new PKIXExtendedParameters.Builder(pKIXBuilderParameters).build();
            this.maxPathLength = pKIXBuilderParameters.getMaxPathLength();
        }

        public Builder(PKIXExtendedParameters pKIXExtendedParameters) {
            this.baseParameters = pKIXExtendedParameters;
        }

        public Builder addExcludedCerts(Set<X509Certificate> set) {
            this.excludedCerts.addAll(set);
            return this;
        }

        public Builder setMaxPathLength(int n) {
            if (n < -1) {
                throw new InvalidParameterException("The maximum path length parameter can not be less than -1.");
            }
            this.maxPathLength = n;
            return this;
        }

        public PKIXExtendedBuilderParameters build() {
            return new PKIXExtendedBuilderParameters(this);
        }
    }
}

