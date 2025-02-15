/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertSelector;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.jce.provider.CertStatus;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.jce.provider.ReasonsMask;
import org.bouncycastle.x509.PKIXAttrCertChecker;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CertStoreSelector;

class RFC3281CertPathUtilities {
    private static final String TARGET_INFORMATION = Extension.targetInformation.getId();
    private static final String NO_REV_AVAIL = Extension.noRevAvail.getId();
    private static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTHORITY_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    RFC3281CertPathUtilities() {
    }

    protected static void processAttrCert7(X509AttributeCertificate x509AttributeCertificate, CertPath certPath, CertPath certPath2, PKIXExtendedParameters pKIXExtendedParameters, Set set) throws CertPathValidatorException {
        Set<String> set2 = x509AttributeCertificate.getCriticalExtensionOIDs();
        if (set2.contains(TARGET_INFORMATION)) {
            try {
                TargetInformation.getInstance(CertPathValidatorUtilities.getExtensionValue(x509AttributeCertificate, TARGET_INFORMATION));
            } catch (AnnotatedException annotatedException) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", annotatedException);
            } catch (IllegalArgumentException illegalArgumentException) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", illegalArgumentException);
            }
        }
        set2.remove(TARGET_INFORMATION);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            ((PKIXAttrCertChecker)iterator.next()).check(x509AttributeCertificate, certPath, certPath2, set2);
        }
        if (!set2.isEmpty()) {
            throw new CertPathValidatorException("Attribute certificate contains unsupported critical extensions: " + set2);
        }
    }

    protected static void checkCRLs(X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, Date date, List list, JcaJceHelper jcaJceHelper) throws CertPathValidatorException {
        if (pKIXExtendedParameters.isRevocationEnabled()) {
            if (x509AttributeCertificate.getExtensionValue(NO_REV_AVAIL) == null) {
                PKIXExtendedParameters pKIXExtendedParameters2;
                Object object;
                CRLDistPoint cRLDistPoint = null;
                try {
                    cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509AttributeCertificate, CRL_DISTRIBUTION_POINTS));
                } catch (AnnotatedException annotatedException) {
                    throw new CertPathValidatorException("CRL distribution point extension could not be read.", annotatedException);
                }
                ArrayList<PKIXCRLStore> arrayList = new ArrayList<PKIXCRLStore>();
                try {
                    arrayList.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(cRLDistPoint, pKIXExtendedParameters.getNamedCRLStoreMap()));
                } catch (AnnotatedException annotatedException) {
                    throw new CertPathValidatorException("No additional CRL locations could be decoded from CRL distribution point extension.", annotatedException);
                }
                PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXExtendedParameters);
                Object object2 = arrayList.iterator();
                while (object2.hasNext()) {
                    builder.addCRLStore((PKIXCRLStore)((Object)arrayList));
                }
                pKIXExtendedParameters = builder.build();
                object2 = new CertStatus();
                ReasonsMask reasonsMask = new ReasonsMask();
                AnnotatedException annotatedException = null;
                boolean bl = false;
                if (cRLDistPoint != null) {
                    object = null;
                    try {
                        object = cRLDistPoint.getDistributionPoints();
                    } catch (Exception exception) {
                        throw new ExtCertPathValidatorException("Distribution points could not be read.", exception);
                    }
                    try {
                        for (int i = 0; i < ((DistributionPoint[])object).length && ((CertStatus)object2).getCertStatus() == 11 && !reasonsMask.isAllReasons(); ++i) {
                            pKIXExtendedParameters2 = (PKIXExtendedParameters)pKIXExtendedParameters.clone();
                            RFC3281CertPathUtilities.checkCRL(object[i], x509AttributeCertificate, pKIXExtendedParameters2, date, x509Certificate, (CertStatus)object2, reasonsMask, list, jcaJceHelper);
                            bl = true;
                        }
                    } catch (AnnotatedException annotatedException2) {
                        annotatedException = new AnnotatedException("No valid CRL for distribution point found.", annotatedException2);
                    }
                }
                if (((CertStatus)object2).getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
                    try {
                        object = null;
                        try {
                            object = new ASN1InputStream(((X500Principal)x509AttributeCertificate.getIssuer().getPrincipals()[0]).getEncoded()).readObject();
                        } catch (Exception exception) {
                            throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", exception);
                        }
                        DistributionPoint distributionPoint = new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, (ASN1Encodable)object))), null, null);
                        pKIXExtendedParameters2 = (PKIXExtendedParameters)pKIXExtendedParameters.clone();
                        RFC3281CertPathUtilities.checkCRL(distributionPoint, x509AttributeCertificate, pKIXExtendedParameters2, date, x509Certificate, (CertStatus)object2, reasonsMask, list, jcaJceHelper);
                        bl = true;
                    } catch (AnnotatedException annotatedException3) {
                        annotatedException = new AnnotatedException("No valid CRL for distribution point found.", annotatedException3);
                    }
                }
                if (!bl) {
                    throw new ExtCertPathValidatorException("No valid CRL found.", annotatedException);
                }
                if (((CertStatus)object2).getCertStatus() != 11) {
                    object = "Attribute certificate revocation after " + ((CertStatus)object2).getRevocationDate();
                    object = (String)object + ", reason: " + RFC3280CertPathUtilities.crlReasons[((CertStatus)object2).getCertStatus()];
                    throw new CertPathValidatorException((String)object);
                }
                if (!reasonsMask.isAllReasons() && ((CertStatus)object2).getCertStatus() == 11) {
                    ((CertStatus)object2).setCertStatus(12);
                }
                if (((CertStatus)object2).getCertStatus() == 12) {
                    throw new CertPathValidatorException("Attribute certificate status could not be determined.");
                }
            } else if (x509AttributeCertificate.getExtensionValue(CRL_DISTRIBUTION_POINTS) != null || x509AttributeCertificate.getExtensionValue(AUTHORITY_INFO_ACCESS) != null) {
                throw new CertPathValidatorException("No rev avail extension is set, but also an AC revocation pointer.");
            }
        }
    }

    protected static void additionalChecks(X509AttributeCertificate x509AttributeCertificate, Set set, Set set2) throws CertPathValidatorException {
        for (String string : set) {
            if (x509AttributeCertificate.getAttributes(string) == null) continue;
            throw new CertPathValidatorException("Attribute certificate contains prohibited attribute: " + string + ".");
        }
        for (String string : set2) {
            if (x509AttributeCertificate.getAttributes(string) != null) continue;
            throw new CertPathValidatorException("Attribute certificate does not contain necessary attribute: " + string + ".");
        }
    }

    protected static void processAttrCert5(X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters) throws CertPathValidatorException {
        try {
            x509AttributeCertificate.checkValidity(CertPathValidatorUtilities.getValidDate(pKIXExtendedParameters));
        } catch (CertificateExpiredException certificateExpiredException) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", certificateExpiredException);
        } catch (CertificateNotYetValidException certificateNotYetValidException) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", certificateNotYetValidException);
        }
    }

    protected static void processAttrCert4(X509Certificate x509Certificate, Set set) throws CertPathValidatorException {
        Set set2 = set;
        boolean bl = false;
        for (TrustAnchor trustAnchor : set2) {
            if (!x509Certificate.getSubjectX500Principal().getName("RFC2253").equals(trustAnchor.getCAName()) && !x509Certificate.equals(trustAnchor.getTrustedCert())) continue;
            bl = true;
        }
        if (!bl) {
            throw new CertPathValidatorException("Attribute certificate issuer is not directly trusted.");
        }
    }

    protected static void processAttrCert3(X509Certificate x509Certificate, PKIXExtendedParameters pKIXExtendedParameters) throws CertPathValidatorException {
        if (x509Certificate.getKeyUsage() != null && !x509Certificate.getKeyUsage()[0] && !x509Certificate.getKeyUsage()[1]) {
            throw new CertPathValidatorException("Attribute certificate issuer public key cannot be used to validate digital signatures.");
        }
        if (x509Certificate.getBasicConstraints() != -1) {
            throw new CertPathValidatorException("Attribute certificate issuer is also a public key certificate issuer.");
        }
    }

    protected static CertPathValidatorResult processAttrCert2(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters) throws CertPathValidatorException {
        CertPathValidator certPathValidator = null;
        try {
            certPathValidator = CertPathValidator.getInstance("PKIX", "BC");
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new ExtCertPathValidatorException("Support class could not be created.", noSuchProviderException);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new ExtCertPathValidatorException("Support class could not be created.", noSuchAlgorithmException);
        }
        try {
            return certPathValidator.validate(certPath, pKIXExtendedParameters);
        } catch (CertPathValidatorException certPathValidatorException) {
            throw new ExtCertPathValidatorException("Certification path for issuer certificate of attribute certificate could not be validated.", certPathValidatorException);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new RuntimeException(invalidAlgorithmParameterException.getMessage());
        }
    }

    protected static CertPath processAttrCert1(X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters) throws CertPathValidatorException {
        int n;
        Object object;
        Object object2;
        CertPathBuilderResult certPathBuilderResult = null;
        HashSet hashSet = new HashSet();
        if (x509AttributeCertificate.getHolder().getIssuer() != null) {
            object2 = new X509CertSelector();
            ((X509CertSelector)object2).setSerialNumber(x509AttributeCertificate.getHolder().getSerialNumber());
            object = x509AttributeCertificate.getHolder().getIssuer();
            for (n = 0; n < ((Principal[])object).length; ++n) {
                try {
                    if (object[n] instanceof X500Principal) {
                        ((X509CertSelector)object2).setIssuer(((X500Principal)object[n]).getEncoded());
                    }
                    hashSet.addAll(CertPathValidatorUtilities.findCertificates(new PKIXCertStoreSelector.Builder((CertSelector)object2).build(), pKIXExtendedParameters.getCertStores()));
                    continue;
                } catch (AnnotatedException annotatedException) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", annotatedException);
                } catch (IOException iOException) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", iOException);
                }
            }
            if (hashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in base certificate ID for attribute certificate cannot be found.");
            }
        }
        if (x509AttributeCertificate.getHolder().getEntityNames() != null) {
            object2 = new X509CertStoreSelector();
            object = x509AttributeCertificate.getHolder().getEntityNames();
            for (n = 0; n < ((Principal[])object).length; ++n) {
                try {
                    if (object[n] instanceof X500Principal) {
                        ((X509CertSelector)object2).setIssuer(((X500Principal)object[n]).getEncoded());
                    }
                    hashSet.addAll(CertPathValidatorUtilities.findCertificates(new PKIXCertStoreSelector.Builder((CertSelector)object2).build(), pKIXExtendedParameters.getCertStores()));
                    continue;
                } catch (AnnotatedException annotatedException) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", annotatedException);
                } catch (IOException iOException) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", iOException);
                }
            }
            if (hashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in entity name for attribute certificate cannot be found.");
            }
        }
        object2 = new PKIXExtendedParameters.Builder(pKIXExtendedParameters);
        object = null;
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            x509CertStoreSelector.setCertificate((X509Certificate)iterator.next());
            ((PKIXExtendedParameters.Builder)object2).setTargetConstraints(new PKIXCertStoreSelector.Builder(x509CertStoreSelector).build());
            CertPathBuilder certPathBuilder = null;
            try {
                certPathBuilder = CertPathBuilder.getInstance("PKIX", "BC");
            } catch (NoSuchProviderException noSuchProviderException) {
                throw new ExtCertPathValidatorException("Support class could not be created.", noSuchProviderException);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                throw new ExtCertPathValidatorException("Support class could not be created.", noSuchAlgorithmException);
            }
            try {
                certPathBuilderResult = certPathBuilder.build(new PKIXExtendedBuilderParameters.Builder(((PKIXExtendedParameters.Builder)object2).build()).build());
            } catch (CertPathBuilderException certPathBuilderException) {
                object = new ExtCertPathValidatorException("Certification path for public key certificate of attribute certificate could not be build.", certPathBuilderException);
            } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
                throw new RuntimeException(invalidAlgorithmParameterException.getMessage());
            }
        }
        if (object != null) {
            throw object;
        }
        return certPathBuilderResult.getCertPath();
    }

    private static void checkCRL(DistributionPoint distributionPoint, X509AttributeCertificate x509AttributeCertificate, PKIXExtendedParameters pKIXExtendedParameters, Date date, X509Certificate x509Certificate, CertStatus certStatus, ReasonsMask reasonsMask, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        if (x509AttributeCertificate.getExtensionValue(X509Extensions.NoRevAvail.getId()) != null) {
            return;
        }
        Date date2 = new Date(System.currentTimeMillis());
        if (date.getTime() > date2.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        Set set = CertPathValidatorUtilities.getCompleteCRLs(distributionPoint, x509AttributeCertificate, date2, pKIXExtendedParameters);
        boolean bl = false;
        AnnotatedException annotatedException = null;
        Iterator iterator = set.iterator();
        while (iterator.hasNext() && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
            try {
                X509CRL x509CRL = (X509CRL)iterator.next();
                ReasonsMask reasonsMask2 = RFC3280CertPathUtilities.processCRLD(x509CRL, distributionPoint);
                if (!reasonsMask2.hasNewReasons(reasonsMask)) continue;
                Set set2 = RFC3280CertPathUtilities.processCRLF(x509CRL, x509AttributeCertificate, null, null, pKIXExtendedParameters, list, jcaJceHelper);
                PublicKey publicKey = RFC3280CertPathUtilities.processCRLG(x509CRL, set2);
                X509CRL x509CRL2 = null;
                if (pKIXExtendedParameters.isUseDeltasEnabled()) {
                    Set set3 = CertPathValidatorUtilities.getDeltaCRLs(date2, x509CRL, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores());
                    x509CRL2 = RFC3280CertPathUtilities.processCRLH(set3, publicKey);
                }
                if (pKIXExtendedParameters.getValidityModel() != 1 && x509AttributeCertificate.getNotAfter().getTime() < x509CRL.getThisUpdate().getTime()) {
                    throw new AnnotatedException("No valid CRL for current time found.");
                }
                RFC3280CertPathUtilities.processCRLB1(distributionPoint, x509AttributeCertificate, x509CRL);
                RFC3280CertPathUtilities.processCRLB2(distributionPoint, x509AttributeCertificate, x509CRL);
                RFC3280CertPathUtilities.processCRLC(x509CRL2, x509CRL, pKIXExtendedParameters);
                RFC3280CertPathUtilities.processCRLI(date, x509CRL2, x509AttributeCertificate, certStatus, pKIXExtendedParameters);
                RFC3280CertPathUtilities.processCRLJ(date, x509CRL, x509AttributeCertificate, certStatus);
                if (certStatus.getCertStatus() == 8) {
                    certStatus.setCertStatus(11);
                }
                reasonsMask.addReasons(reasonsMask2);
                bl = true;
            } catch (AnnotatedException annotatedException2) {
                annotatedException = annotatedException2;
            }
        }
        if (!bl) {
            throw annotatedException;
        }
    }
}

