/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.x509.PKIXAttrCertChecker;
import org.bouncycastle.x509.X509CertStoreSelector;

public class ExtendedPKIXParameters
extends PKIXParameters {
    private List stores = new ArrayList();
    private Selector selector;
    private boolean additionalLocationsEnabled;
    private List additionalStores = new ArrayList();
    private Set trustedACIssuers = new HashSet();
    private Set necessaryACAttributes = new HashSet();
    private Set prohibitedACAttributes = new HashSet();
    private Set attrCertCheckers = new HashSet();
    public static final int PKIX_VALIDITY_MODEL = 0;
    public static final int CHAIN_VALIDITY_MODEL = 1;
    private int validityModel = 0;
    private boolean useDeltas = false;

    public ExtendedPKIXParameters(Set set) throws InvalidAlgorithmParameterException {
        super(set);
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pKIXParameters) {
        ExtendedPKIXParameters extendedPKIXParameters;
        try {
            extendedPKIXParameters = new ExtendedPKIXParameters((Set)pKIXParameters.getTrustAnchors());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        extendedPKIXParameters.setParams(pKIXParameters);
        return extendedPKIXParameters;
    }

    protected void setParams(PKIXParameters pKIXParameters) {
        this.setDate(pKIXParameters.getDate());
        this.setCertPathCheckers(pKIXParameters.getCertPathCheckers());
        this.setCertStores((List)pKIXParameters.getCertStores());
        this.setAnyPolicyInhibited(pKIXParameters.isAnyPolicyInhibited());
        this.setExplicitPolicyRequired(pKIXParameters.isExplicitPolicyRequired());
        this.setPolicyMappingInhibited(pKIXParameters.isPolicyMappingInhibited());
        this.setRevocationEnabled(pKIXParameters.isRevocationEnabled());
        this.setInitialPolicies(pKIXParameters.getInitialPolicies());
        this.setPolicyQualifiersRejected(pKIXParameters.getPolicyQualifiersRejected());
        this.setSigProvider(pKIXParameters.getSigProvider());
        this.setTargetCertConstraints(pKIXParameters.getTargetCertConstraints());
        try {
            this.setTrustAnchors(pKIXParameters.getTrustAnchors());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        if (pKIXParameters instanceof ExtendedPKIXParameters) {
            ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters)pKIXParameters;
            this.validityModel = extendedPKIXParameters.validityModel;
            this.useDeltas = extendedPKIXParameters.useDeltas;
            this.additionalLocationsEnabled = extendedPKIXParameters.additionalLocationsEnabled;
            this.selector = extendedPKIXParameters.selector == null ? null : (Selector)extendedPKIXParameters.selector.clone();
            this.stores = new ArrayList(extendedPKIXParameters.stores);
            this.additionalStores = new ArrayList(extendedPKIXParameters.additionalStores);
            this.trustedACIssuers = new HashSet(extendedPKIXParameters.trustedACIssuers);
            this.prohibitedACAttributes = new HashSet(extendedPKIXParameters.prohibitedACAttributes);
            this.necessaryACAttributes = new HashSet(extendedPKIXParameters.necessaryACAttributes);
            this.attrCertCheckers = new HashSet(extendedPKIXParameters.attrCertCheckers);
        }
    }

    public boolean isUseDeltasEnabled() {
        return this.useDeltas;
    }

    public void setUseDeltasEnabled(boolean bl) {
        this.useDeltas = bl;
    }

    public int getValidityModel() {
        return this.validityModel;
    }

    public void setCertStores(List list) {
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                this.addCertStore((CertStore)iterator.next());
            }
        }
    }

    public void setStores(List list) {
        if (list == null) {
            this.stores = new ArrayList();
        } else {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() instanceof Store) continue;
                throw new ClassCastException("All elements of list must be of type org.bouncycastle.util.Store.");
            }
            this.stores = new ArrayList(list);
        }
    }

    public void addStore(Store store) {
        if (store != null) {
            this.stores.add(store);
        }
    }

    public void addAdditionalStore(Store store) {
        if (store != null) {
            this.additionalStores.add(store);
        }
    }

    public void addAddionalStore(Store store) {
        this.addAdditionalStore(store);
    }

    public List getAdditionalStores() {
        return Collections.unmodifiableList(this.additionalStores);
    }

    public List getStores() {
        return Collections.unmodifiableList(new ArrayList(this.stores));
    }

    public void setValidityModel(int n) {
        this.validityModel = n;
    }

    public Object clone() {
        ExtendedPKIXParameters extendedPKIXParameters;
        try {
            extendedPKIXParameters = new ExtendedPKIXParameters((Set)this.getTrustAnchors());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        extendedPKIXParameters.setParams(this);
        return extendedPKIXParameters;
    }

    public boolean isAdditionalLocationsEnabled() {
        return this.additionalLocationsEnabled;
    }

    public void setAdditionalLocationsEnabled(boolean bl) {
        this.additionalLocationsEnabled = bl;
    }

    public Selector getTargetConstraints() {
        if (this.selector != null) {
            return (Selector)this.selector.clone();
        }
        return null;
    }

    public void setTargetConstraints(Selector selector) {
        this.selector = selector != null ? (Selector)selector.clone() : null;
    }

    public void setTargetCertConstraints(CertSelector certSelector) {
        super.setTargetCertConstraints(certSelector);
        this.selector = certSelector != null ? X509CertStoreSelector.getInstance((X509CertSelector)certSelector) : null;
    }

    public Set getTrustedACIssuers() {
        return Collections.unmodifiableSet(this.trustedACIssuers);
    }

    public void setTrustedACIssuers(Set set) {
        if (set == null) {
            this.trustedACIssuers.clear();
            return;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof TrustAnchor) continue;
            throw new ClassCastException("All elements of set must be of type " + TrustAnchor.class.getName() + ".");
        }
        this.trustedACIssuers.clear();
        this.trustedACIssuers.addAll(set);
    }

    public Set getNecessaryACAttributes() {
        return Collections.unmodifiableSet(this.necessaryACAttributes);
    }

    public void setNecessaryACAttributes(Set set) {
        if (set == null) {
            this.necessaryACAttributes.clear();
            return;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof String) continue;
            throw new ClassCastException("All elements of set must be of type String.");
        }
        this.necessaryACAttributes.clear();
        this.necessaryACAttributes.addAll(set);
    }

    public Set getProhibitedACAttributes() {
        return Collections.unmodifiableSet(this.prohibitedACAttributes);
    }

    public void setProhibitedACAttributes(Set set) {
        if (set == null) {
            this.prohibitedACAttributes.clear();
            return;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof String) continue;
            throw new ClassCastException("All elements of set must be of type String.");
        }
        this.prohibitedACAttributes.clear();
        this.prohibitedACAttributes.addAll(set);
    }

    public Set getAttrCertCheckers() {
        return Collections.unmodifiableSet(this.attrCertCheckers);
    }

    public void setAttrCertCheckers(Set set) {
        if (set == null) {
            this.attrCertCheckers.clear();
            return;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof PKIXAttrCertChecker) continue;
            throw new ClassCastException("All elements of set must be of type " + PKIXAttrCertChecker.class.getName() + ".");
        }
        this.attrCertCheckers.clear();
        this.attrCertCheckers.addAll(set);
    }
}

