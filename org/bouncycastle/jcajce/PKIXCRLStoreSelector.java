/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PKIXCRLStoreSelector<T extends CRL>
implements Selector<T> {
    private final CRLSelector baseSelector;
    private final boolean deltaCRLIndicator;
    private final boolean completeCRLEnabled;
    private final BigInteger maxBaseCRLNumber;
    private final byte[] issuingDistributionPoint;
    private final boolean issuingDistributionPointEnabled;

    private PKIXCRLStoreSelector(Builder builder) {
        this.baseSelector = builder.baseSelector;
        this.deltaCRLIndicator = builder.deltaCRLIndicator;
        this.completeCRLEnabled = builder.completeCRLEnabled;
        this.maxBaseCRLNumber = builder.maxBaseCRLNumber;
        this.issuingDistributionPoint = builder.issuingDistributionPoint;
        this.issuingDistributionPointEnabled = builder.issuingDistributionPointEnabled;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    @Override
    public boolean match(CRL cRL) {
        byte[] byArray;
        if (!(cRL instanceof X509CRL)) {
            return this.baseSelector.match(cRL);
        }
        X509CRL x509CRL = (X509CRL)cRL;
        ASN1Integer aSN1Integer = null;
        try {
            byArray = x509CRL.getExtensionValue(Extension.deltaCRLIndicator.getId());
            if (byArray != null) {
                aSN1Integer = ASN1Integer.getInstance(ASN1OctetString.getInstance(byArray).getOctets());
            }
        } catch (Exception exception) {
            return false;
        }
        if (this.isDeltaCRLIndicatorEnabled() && aSN1Integer == null) {
            return false;
        }
        if (this.isCompleteCRLEnabled() && aSN1Integer != null) {
            return false;
        }
        if (aSN1Integer != null && this.maxBaseCRLNumber != null && aSN1Integer.getPositiveValue().compareTo(this.maxBaseCRLNumber) == 1) {
            return false;
        }
        if (this.issuingDistributionPointEnabled) {
            byArray = x509CRL.getExtensionValue(Extension.issuingDistributionPoint.getId());
            if (this.issuingDistributionPoint == null ? byArray != null : !Arrays.areEqual(byArray, this.issuingDistributionPoint)) {
                return false;
            }
        }
        return this.baseSelector.match(cRL);
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    @Override
    public Object clone() {
        return this;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public X509Certificate getCertificateChecking() {
        if (this.baseSelector instanceof X509CRLSelector) {
            return ((X509CRLSelector)this.baseSelector).getCertificateChecking();
        }
        return null;
    }

    public static Collection<? extends CRL> getCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, CertStore certStore) throws CertStoreException {
        return certStore.getCRLs(new SelectorClone(pKIXCRLStoreSelector));
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final CRLSelector baseSelector;
        private boolean deltaCRLIndicator = false;
        private boolean completeCRLEnabled = false;
        private BigInteger maxBaseCRLNumber = null;
        private byte[] issuingDistributionPoint = null;
        private boolean issuingDistributionPointEnabled = false;

        public Builder(CRLSelector cRLSelector) {
            this.baseSelector = (CRLSelector)cRLSelector.clone();
        }

        public Builder setCompleteCRLEnabled(boolean bl) {
            this.completeCRLEnabled = bl;
            return this;
        }

        public Builder setDeltaCRLIndicatorEnabled(boolean bl) {
            this.deltaCRLIndicator = bl;
            return this;
        }

        public void setMaxBaseCRLNumber(BigInteger bigInteger) {
            this.maxBaseCRLNumber = bigInteger;
        }

        public void setIssuingDistributionPointEnabled(boolean bl) {
            this.issuingDistributionPointEnabled = bl;
        }

        public void setIssuingDistributionPoint(byte[] byArray) {
            this.issuingDistributionPoint = Arrays.clone(byArray);
        }

        public PKIXCRLStoreSelector<? extends CRL> build() {
            return new PKIXCRLStoreSelector(this);
        }
    }

    private static class SelectorClone
    extends X509CRLSelector {
        private final PKIXCRLStoreSelector selector;

        SelectorClone(PKIXCRLStoreSelector pKIXCRLStoreSelector) {
            this.selector = pKIXCRLStoreSelector;
            if (pKIXCRLStoreSelector.baseSelector instanceof X509CRLSelector) {
                X509CRLSelector x509CRLSelector = (X509CRLSelector)pKIXCRLStoreSelector.baseSelector;
                this.setCertificateChecking(x509CRLSelector.getCertificateChecking());
                this.setDateAndTime(x509CRLSelector.getDateAndTime());
                this.setIssuers(x509CRLSelector.getIssuers());
                this.setMinCRLNumber(x509CRLSelector.getMinCRL());
                this.setMaxCRLNumber(x509CRLSelector.getMaxCRL());
            }
        }

        public boolean match(CRL cRL) {
            return this.selector == null ? cRL != null : this.selector.match(cRL);
        }
    }
}

