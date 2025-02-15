/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.jce.provider.RFC3281CertPathUtilities;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;

public class PKIXAttrCertPathValidatorSpi
extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        Object object;
        Object object2;
        if (!(certPathParameters instanceof ExtendedPKIXParameters) && !(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + ExtendedPKIXParameters.class.getName() + " instance.");
        }
        Set set = new HashSet();
        Set set2 = new HashSet();
        Set set3 = new HashSet();
        HashSet hashSet = new HashSet();
        if (certPathParameters instanceof PKIXParameters) {
            object2 = new PKIXExtendedParameters.Builder((PKIXParameters)certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                object = (ExtendedPKIXParameters)certPathParameters;
                ((PKIXExtendedParameters.Builder)object2).setUseDeltasEnabled(((ExtendedPKIXParameters)object).isUseDeltasEnabled());
                ((PKIXExtendedParameters.Builder)object2).setValidityModel(((ExtendedPKIXParameters)object).getValidityModel());
                set = ((ExtendedPKIXParameters)object).getAttrCertCheckers();
                set2 = ((ExtendedPKIXParameters)object).getProhibitedACAttributes();
                set3 = ((ExtendedPKIXParameters)object).getNecessaryACAttributes();
            }
            pKIXExtendedParameters = ((PKIXExtendedParameters.Builder)object2).build();
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters)certPathParameters;
        }
        object2 = pKIXExtendedParameters.getTargetConstraints();
        if (!(object2 instanceof X509AttributeCertStoreSelector)) {
            throw new InvalidAlgorithmParameterException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + this.getClass().getName() + " class.");
        }
        object = ((X509AttributeCertStoreSelector)object2).getAttributeCert();
        CertPath certPath2 = RFC3281CertPathUtilities.processAttrCert1((X509AttributeCertificate)object, pKIXExtendedParameters);
        CertPathValidatorResult certPathValidatorResult = RFC3281CertPathUtilities.processAttrCert2(certPath, pKIXExtendedParameters);
        X509Certificate x509Certificate = (X509Certificate)certPath.getCertificates().get(0);
        RFC3281CertPathUtilities.processAttrCert3(x509Certificate, pKIXExtendedParameters);
        RFC3281CertPathUtilities.processAttrCert4(x509Certificate, hashSet);
        RFC3281CertPathUtilities.processAttrCert5((X509AttributeCertificate)object, pKIXExtendedParameters);
        RFC3281CertPathUtilities.processAttrCert7((X509AttributeCertificate)object, certPath, certPath2, pKIXExtendedParameters, set);
        RFC3281CertPathUtilities.additionalChecks((X509AttributeCertificate)object, set2, set3);
        Date date = null;
        try {
            date = CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, null, -1);
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", annotatedException);
        }
        RFC3281CertPathUtilities.checkCRLs((X509AttributeCertificate)object, pKIXExtendedParameters, x509Certificate, date, certPath.getCertificates(), this.helper);
        return certPathValidatorResult;
    }
}

