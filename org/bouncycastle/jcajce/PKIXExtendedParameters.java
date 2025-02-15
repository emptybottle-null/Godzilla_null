/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.security.cert.CertPathParameters;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PKIXExtendedParameters
implements CertPathParameters {
    public static final int PKIX_VALIDITY_MODEL = 0;
    public static final int CHAIN_VALIDITY_MODEL = 1;
    private final PKIXParameters baseParameters;
    private final PKIXCertStoreSelector targetConstraints;
    private final Date date;
    private final List<PKIXCertStore> extraCertStores;
    private final Map<GeneralName, PKIXCertStore> namedCertificateStoreMap;
    private final List<PKIXCRLStore> extraCRLStores;
    private final Map<GeneralName, PKIXCRLStore> namedCRLStoreMap;
    private final boolean revocationEnabled;
    private final boolean useDeltas;
    private final int validityModel;
    private final Set<TrustAnchor> trustAnchors;

    private PKIXExtendedParameters(Builder builder) {
        this.baseParameters = builder.baseParameters;
        this.date = builder.date;
        this.extraCertStores = Collections.unmodifiableList(builder.extraCertStores);
        this.namedCertificateStoreMap = Collections.unmodifiableMap(new HashMap(builder.namedCertificateStoreMap));
        this.extraCRLStores = Collections.unmodifiableList(builder.extraCRLStores);
        this.namedCRLStoreMap = Collections.unmodifiableMap(new HashMap(builder.namedCRLStoreMap));
        this.targetConstraints = builder.targetConstraints;
        this.revocationEnabled = builder.revocationEnabled;
        this.useDeltas = builder.useDeltas;
        this.validityModel = builder.validityModel;
        this.trustAnchors = Collections.unmodifiableSet(builder.trustAnchors);
    }

    public List<PKIXCertStore> getCertificateStores() {
        return this.extraCertStores;
    }

    public Map<GeneralName, PKIXCertStore> getNamedCertificateStoreMap() {
        return this.namedCertificateStoreMap;
    }

    public List<PKIXCRLStore> getCRLStores() {
        return this.extraCRLStores;
    }

    public Map<GeneralName, PKIXCRLStore> getNamedCRLStoreMap() {
        return this.namedCRLStoreMap;
    }

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public boolean isUseDeltasEnabled() {
        return this.useDeltas;
    }

    public int getValidityModel() {
        return this.validityModel;
    }

    @Override
    public Object clone() {
        return this;
    }

    public PKIXCertStoreSelector getTargetConstraints() {
        return this.targetConstraints;
    }

    public Set getTrustAnchors() {
        return this.trustAnchors;
    }

    public Set getInitialPolicies() {
        return this.baseParameters.getInitialPolicies();
    }

    public String getSigProvider() {
        return this.baseParameters.getSigProvider();
    }

    public boolean isExplicitPolicyRequired() {
        return this.baseParameters.isExplicitPolicyRequired();
    }

    public boolean isAnyPolicyInhibited() {
        return this.baseParameters.isAnyPolicyInhibited();
    }

    public boolean isPolicyMappingInhibited() {
        return this.baseParameters.isPolicyMappingInhibited();
    }

    public List getCertPathCheckers() {
        return this.baseParameters.getCertPathCheckers();
    }

    public List<CertStore> getCertStores() {
        return this.baseParameters.getCertStores();
    }

    public boolean isRevocationEnabled() {
        return this.revocationEnabled;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final PKIXParameters baseParameters;
        private final Date date;
        private PKIXCertStoreSelector targetConstraints;
        private List<PKIXCertStore> extraCertStores = new ArrayList<PKIXCertStore>();
        private Map<GeneralName, PKIXCertStore> namedCertificateStoreMap = new HashMap<GeneralName, PKIXCertStore>();
        private List<PKIXCRLStore> extraCRLStores = new ArrayList<PKIXCRLStore>();
        private Map<GeneralName, PKIXCRLStore> namedCRLStoreMap = new HashMap<GeneralName, PKIXCRLStore>();
        private boolean revocationEnabled;
        private int validityModel = 0;
        private boolean useDeltas = false;
        private Set<TrustAnchor> trustAnchors;

        public Builder(PKIXParameters pKIXParameters) {
            Date date;
            this.baseParameters = (PKIXParameters)pKIXParameters.clone();
            CertSelector certSelector = pKIXParameters.getTargetCertConstraints();
            if (certSelector != null) {
                this.targetConstraints = new PKIXCertStoreSelector.Builder(certSelector).build();
            }
            this.date = (date = pKIXParameters.getDate()) == null ? new Date() : date;
            this.revocationEnabled = pKIXParameters.isRevocationEnabled();
            this.trustAnchors = pKIXParameters.getTrustAnchors();
        }

        public Builder(PKIXExtendedParameters pKIXExtendedParameters) {
            this.baseParameters = pKIXExtendedParameters.baseParameters;
            this.date = pKIXExtendedParameters.date;
            this.targetConstraints = pKIXExtendedParameters.targetConstraints;
            this.extraCertStores = new ArrayList<PKIXCertStore>(pKIXExtendedParameters.extraCertStores);
            this.namedCertificateStoreMap = new HashMap<GeneralName, PKIXCertStore>(pKIXExtendedParameters.namedCertificateStoreMap);
            this.extraCRLStores = new ArrayList<PKIXCRLStore>(pKIXExtendedParameters.extraCRLStores);
            this.namedCRLStoreMap = new HashMap<GeneralName, PKIXCRLStore>(pKIXExtendedParameters.namedCRLStoreMap);
            this.useDeltas = pKIXExtendedParameters.useDeltas;
            this.validityModel = pKIXExtendedParameters.validityModel;
            this.revocationEnabled = pKIXExtendedParameters.isRevocationEnabled();
            this.trustAnchors = pKIXExtendedParameters.getTrustAnchors();
        }

        public Builder addCertificateStore(PKIXCertStore pKIXCertStore) {
            this.extraCertStores.add(pKIXCertStore);
            return this;
        }

        public Builder addNamedCertificateStore(GeneralName generalName, PKIXCertStore pKIXCertStore) {
            this.namedCertificateStoreMap.put(generalName, pKIXCertStore);
            return this;
        }

        public Builder addCRLStore(PKIXCRLStore pKIXCRLStore) {
            this.extraCRLStores.add(pKIXCRLStore);
            return this;
        }

        public Builder addNamedCRLStore(GeneralName generalName, PKIXCRLStore pKIXCRLStore) {
            this.namedCRLStoreMap.put(generalName, pKIXCRLStore);
            return this;
        }

        public Builder setTargetConstraints(PKIXCertStoreSelector pKIXCertStoreSelector) {
            this.targetConstraints = pKIXCertStoreSelector;
            return this;
        }

        public Builder setUseDeltasEnabled(boolean bl) {
            this.useDeltas = bl;
            return this;
        }

        public Builder setValidityModel(int n) {
            this.validityModel = n;
            return this;
        }

        public Builder setTrustAnchor(TrustAnchor trustAnchor) {
            this.trustAnchors = Collections.singleton(trustAnchor);
            return this;
        }

        public Builder setTrustAnchors(Set<TrustAnchor> set) {
            this.trustAnchors = set;
            return this;
        }

        public void setRevocationEnabled(boolean bl) {
            this.revocationEnabled = bl;
        }

        public PKIXExtendedParameters build() {
            return new PKIXExtendedParameters(this);
        }
    }
}

