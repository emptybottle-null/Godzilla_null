/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

public class X509CRLStoreSelector
extends X509CRLSelector
implements Selector {
    private boolean deltaCRLIndicator = false;
    private boolean completeCRLEnabled = false;
    private BigInteger maxBaseCRLNumber = null;
    private byte[] issuingDistributionPoint = null;
    private boolean issuingDistributionPointEnabled = false;
    private X509AttributeCertificate attrCertChecking;

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    public void setIssuingDistributionPointEnabled(boolean bl) {
        this.issuingDistributionPointEnabled = bl;
    }

    public void setAttrCertificateChecking(X509AttributeCertificate x509AttributeCertificate) {
        this.attrCertChecking = x509AttributeCertificate;
    }

    public X509AttributeCertificate getAttrCertificateChecking() {
        return this.attrCertChecking;
    }

    public boolean match(Object object) {
        byte[] byArray;
        if (!(object instanceof X509CRL)) {
            return false;
        }
        X509CRL x509CRL = (X509CRL)object;
        ASN1Integer aSN1Integer = null;
        try {
            byArray = x509CRL.getExtensionValue(X509Extensions.DeltaCRLIndicator.getId());
            if (byArray != null) {
                aSN1Integer = ASN1Integer.getInstance(X509ExtensionUtil.fromExtensionValue(byArray));
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
            byArray = x509CRL.getExtensionValue(X509Extensions.IssuingDistributionPoint.getId());
            if (this.issuingDistributionPoint == null ? byArray != null : !Arrays.areEqual(byArray, this.issuingDistributionPoint)) {
                return false;
            }
        }
        return super.match((X509CRL)object);
    }

    public boolean match(CRL cRL) {
        return this.match((Object)cRL);
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    public void setDeltaCRLIndicatorEnabled(boolean bl) {
        this.deltaCRLIndicator = bl;
    }

    public static X509CRLStoreSelector getInstance(X509CRLSelector x509CRLSelector) {
        if (x509CRLSelector == null) {
            throw new IllegalArgumentException("cannot create from null selector");
        }
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        x509CRLStoreSelector.setCertificateChecking(x509CRLSelector.getCertificateChecking());
        x509CRLStoreSelector.setDateAndTime(x509CRLSelector.getDateAndTime());
        try {
            x509CRLStoreSelector.setIssuerNames(x509CRLSelector.getIssuerNames());
        } catch (IOException iOException) {
            throw new IllegalArgumentException(iOException.getMessage());
        }
        x509CRLStoreSelector.setIssuers(x509CRLSelector.getIssuers());
        x509CRLStoreSelector.setMaxCRLNumber(x509CRLSelector.getMaxCRL());
        x509CRLStoreSelector.setMinCRLNumber(x509CRLSelector.getMinCRL());
        return x509CRLStoreSelector;
    }

    public Object clone() {
        X509CRLStoreSelector x509CRLStoreSelector = X509CRLStoreSelector.getInstance(this);
        x509CRLStoreSelector.deltaCRLIndicator = this.deltaCRLIndicator;
        x509CRLStoreSelector.completeCRLEnabled = this.completeCRLEnabled;
        x509CRLStoreSelector.maxBaseCRLNumber = this.maxBaseCRLNumber;
        x509CRLStoreSelector.attrCertChecking = this.attrCertChecking;
        x509CRLStoreSelector.issuingDistributionPointEnabled = this.issuingDistributionPointEnabled;
        x509CRLStoreSelector.issuingDistributionPoint = Arrays.clone(this.issuingDistributionPoint);
        return x509CRLStoreSelector;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public void setCompleteCRLEnabled(boolean bl) {
        this.completeCRLEnabled = bl;
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public void setMaxBaseCRLNumber(BigInteger bigInteger) {
        this.maxBaseCRLNumber = bigInteger;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public void setIssuingDistributionPoint(byte[] byArray) {
        this.issuingDistributionPoint = Arrays.clone(byArray);
    }
}

