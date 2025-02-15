/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CRL;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXCRLStoreSelector;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.jce.provider.CertStatus;
import org.bouncycastle.jce.provider.PKIXCRLUtil;
import org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.PrincipalUtils;
import org.bouncycastle.jce.provider.ReasonsMask;
import org.bouncycastle.util.Arrays;

class RFC3280CertPathUtilities {
    private static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    public static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    public static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    public static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    public static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    public static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    public static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    public static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    public static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    public static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    public static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    public static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    public static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    public static final String KEY_USAGE = Extension.keyUsage.getId();
    public static final String CRL_NUMBER = Extension.cRLNumber.getId();
    public static final String ANY_POLICY = "2.5.29.32.0";
    protected static final int KEY_CERT_SIGN = 5;
    protected static final int CRL_SIGN = 6;
    protected static final String[] crlReasons = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    RFC3280CertPathUtilities() {
    }

    protected static void processCRLB2(DistributionPoint distributionPoint, Object object, X509CRL x509CRL) throws AnnotatedException {
        IssuingDistributionPoint issuingDistributionPoint = null;
        try {
            issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRL, ISSUING_DISTRIBUTION_POINT));
        } catch (Exception exception) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", exception);
        }
        if (issuingDistributionPoint != null) {
            ASN1Object aSN1Object;
            if (issuingDistributionPoint.getDistributionPoint() != null) {
                Object object2;
                aSN1Object = IssuingDistributionPoint.getInstance(issuingDistributionPoint).getDistributionPoint();
                ArrayList<GeneralName> arrayList = new ArrayList<GeneralName>();
                if (((DistributionPointName)aSN1Object).getType() == 0) {
                    object2 = GeneralNames.getInstance(((DistributionPointName)aSN1Object).getName()).getNames();
                    for (int i = 0; i < ((GeneralName[])object2).length; ++i) {
                        arrayList.add(object2[i]);
                    }
                }
                if (((DistributionPointName)aSN1Object).getType() == 1) {
                    object2 = new ASN1EncodableVector();
                    try {
                        Enumeration enumeration = ASN1Sequence.getInstance(PrincipalUtils.getIssuerPrincipal(x509CRL)).getObjects();
                        while (enumeration.hasMoreElements()) {
                            ((ASN1EncodableVector)object2).add((ASN1Encodable)enumeration.nextElement());
                        }
                    } catch (Exception exception) {
                        throw new AnnotatedException("Could not read CRL issuer.", exception);
                    }
                    ((ASN1EncodableVector)object2).add(((DistributionPointName)aSN1Object).getName());
                    arrayList.add(new GeneralName(X500Name.getInstance(new DERSequence((ASN1EncodableVector)object2))));
                }
                boolean bl = false;
                if (distributionPoint.getDistributionPoint() != null) {
                    int n;
                    aSN1Object = distributionPoint.getDistributionPoint();
                    GeneralName[] generalNameArray = null;
                    if (((DistributionPointName)aSN1Object).getType() == 0) {
                        generalNameArray = GeneralNames.getInstance(((DistributionPointName)aSN1Object).getName()).getNames();
                    }
                    if (((DistributionPointName)aSN1Object).getType() == 1) {
                        if (distributionPoint.getCRLIssuer() != null) {
                            generalNameArray = distributionPoint.getCRLIssuer().getNames();
                        } else {
                            generalNameArray = new GeneralName[1];
                            try {
                                generalNameArray[0] = new GeneralName(X500Name.getInstance(PrincipalUtils.getEncodedIssuerPrincipal(object).getEncoded()));
                            } catch (Exception exception) {
                                throw new AnnotatedException("Could not read certificate issuer.", exception);
                            }
                        }
                        for (n = 0; n < generalNameArray.length; ++n) {
                            Enumeration enumeration = ASN1Sequence.getInstance(generalNameArray[n].getName().toASN1Primitive()).getObjects();
                            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                            while (enumeration.hasMoreElements()) {
                                aSN1EncodableVector.add((ASN1Encodable)enumeration.nextElement());
                            }
                            aSN1EncodableVector.add(((DistributionPointName)aSN1Object).getName());
                            generalNameArray[n] = new GeneralName(X500Name.getInstance(new DERSequence(aSN1EncodableVector)));
                        }
                    }
                    if (generalNameArray != null) {
                        for (n = 0; n < generalNameArray.length; ++n) {
                            if (!arrayList.contains(generalNameArray[n])) continue;
                            bl = true;
                            break;
                        }
                    }
                    if (!bl) {
                        throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                    }
                } else {
                    if (distributionPoint.getCRLIssuer() == null) {
                        throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
                    }
                    GeneralName[] generalNameArray = distributionPoint.getCRLIssuer().getNames();
                    for (int i = 0; i < generalNameArray.length; ++i) {
                        if (!arrayList.contains(generalNameArray[i])) continue;
                        bl = true;
                        break;
                    }
                    if (!bl) {
                        throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                    }
                }
            }
            aSN1Object = null;
            try {
                aSN1Object = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Extension)object, BASIC_CONSTRAINTS));
            } catch (Exception exception) {
                throw new AnnotatedException("Basic constraints extension could not be decoded.", exception);
            }
            if (object instanceof X509Certificate) {
                if (issuingDistributionPoint.onlyContainsUserCerts() && aSN1Object != null && ((BasicConstraints)aSN1Object).isCA()) {
                    throw new AnnotatedException("CA Cert CRL only contains user certificates.");
                }
                if (issuingDistributionPoint.onlyContainsCACerts() && (aSN1Object == null || !((BasicConstraints)aSN1Object).isCA())) {
                    throw new AnnotatedException("End CRL only contains CA certificates.");
                }
            }
            if (issuingDistributionPoint.onlyContainsAttributeCerts()) {
                throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
            }
        }
    }

    protected static void processCRLB1(DistributionPoint distributionPoint, Object object, X509CRL x509CRL) throws AnnotatedException {
        byte[] byArray;
        ASN1Primitive aSN1Primitive = CertPathValidatorUtilities.getExtensionValue(x509CRL, ISSUING_DISTRIBUTION_POINT);
        boolean bl = false;
        if (aSN1Primitive != null && IssuingDistributionPoint.getInstance(aSN1Primitive).isIndirectCRL()) {
            bl = true;
        }
        try {
            byArray = PrincipalUtils.getIssuerPrincipal(x509CRL).getEncoded();
        } catch (IOException iOException) {
            throw new AnnotatedException("Exception encoding CRL issuer: " + iOException.getMessage(), iOException);
        }
        boolean bl2 = false;
        if (distributionPoint.getCRLIssuer() != null) {
            GeneralName[] generalNameArray = distributionPoint.getCRLIssuer().getNames();
            for (int i = 0; i < generalNameArray.length; ++i) {
                if (generalNameArray[i].getTagNo() != 4) continue;
                try {
                    if (!Arrays.areEqual(generalNameArray[i].getName().toASN1Primitive().getEncoded(), byArray)) continue;
                    bl2 = true;
                    continue;
                } catch (IOException iOException) {
                    throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", iOException);
                }
            }
            if (bl2 && !bl) {
                throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
            }
            if (!bl2) {
                throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
            }
        } else if (PrincipalUtils.getIssuerPrincipal(x509CRL).equals(PrincipalUtils.getEncodedIssuerPrincipal(object))) {
            bl2 = true;
        }
        if (!bl2) {
            throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
        }
    }

    protected static ReasonsMask processCRLD(X509CRL x509CRL, DistributionPoint distributionPoint) throws AnnotatedException {
        IssuingDistributionPoint issuingDistributionPoint = null;
        try {
            issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRL, ISSUING_DISTRIBUTION_POINT));
        } catch (Exception exception) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", exception);
        }
        if (issuingDistributionPoint != null && issuingDistributionPoint.getOnlySomeReasons() != null && distributionPoint.getReasons() != null) {
            return new ReasonsMask(distributionPoint.getReasons()).intersect(new ReasonsMask(issuingDistributionPoint.getOnlySomeReasons()));
        }
        if ((issuingDistributionPoint == null || issuingDistributionPoint.getOnlySomeReasons() == null) && distributionPoint.getReasons() == null) {
            return ReasonsMask.allReasons;
        }
        return (distributionPoint.getReasons() == null ? ReasonsMask.allReasons : new ReasonsMask(distributionPoint.getReasons())).intersect(issuingDistributionPoint == null ? ReasonsMask.allReasons : new ReasonsMask(issuingDistributionPoint.getOnlySomeReasons()));
    }

    protected static Set processCRLF(X509CRL x509CRL, Object object, X509Certificate x509Certificate, PublicKey publicKey, PKIXExtendedParameters pKIXExtendedParameters, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        Object object2;
        Object object3;
        Object object4;
        Serializable serializable;
        Collection collection;
        Object object5;
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            object5 = PrincipalUtils.getIssuerPrincipal(x509CRL).getEncoded();
            x509CertSelector.setSubject((byte[])object5);
        } catch (IOException iOException) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", iOException);
        }
        object5 = new PKIXCertStoreSelector.Builder(x509CertSelector).build();
        try {
            collection = CertPathValidatorUtilities.findCertificates((PKIXCertStoreSelector)object5, pKIXExtendedParameters.getCertificateStores());
            collection.addAll(CertPathValidatorUtilities.findCertificates((PKIXCertStoreSelector)object5, pKIXExtendedParameters.getCertStores()));
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Issuer certificate for CRL cannot be searched.", annotatedException);
        }
        collection.add(x509Certificate);
        Iterator iterator = collection.iterator();
        ArrayList<Serializable> arrayList = new ArrayList<Serializable>();
        ArrayList<PublicKey> arrayList2 = new ArrayList<PublicKey>();
        while (iterator.hasNext()) {
            serializable = (X509Certificate)iterator.next();
            if (((Certificate)serializable).equals(x509Certificate)) {
                arrayList.add(serializable);
                arrayList2.add(publicKey);
                continue;
            }
            try {
                object4 = new PKIXCertPathBuilderSpi();
                X509CertSelector x509CertSelector2 = new X509CertSelector();
                x509CertSelector2.setCertificate((X509Certificate)serializable);
                object3 = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTargetConstraints(new PKIXCertStoreSelector.Builder(x509CertSelector2).build());
                if (list.contains(serializable)) {
                    ((PKIXExtendedParameters.Builder)object3).setRevocationEnabled(false);
                } else {
                    ((PKIXExtendedParameters.Builder)object3).setRevocationEnabled(true);
                }
                object2 = new PKIXExtendedBuilderParameters.Builder(((PKIXExtendedParameters.Builder)object3).build()).build();
                List<? extends Certificate> list2 = ((PKIXCertPathBuilderSpi)object4).engineBuild((CertPathParameters)object2).getCertPath().getCertificates();
                arrayList.add(serializable);
                arrayList2.add(CertPathValidatorUtilities.getNextWorkingKey(list2, 0, jcaJceHelper));
            } catch (CertPathBuilderException certPathBuilderException) {
                throw new AnnotatedException("CertPath for CRL signer failed to validate.", certPathBuilderException);
            } catch (CertPathValidatorException certPathValidatorException) {
                throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", certPathValidatorException);
            } catch (Exception exception) {
                throw new AnnotatedException(exception.getMessage());
            }
        }
        serializable = new HashSet();
        object4 = null;
        for (int i = 0; i < arrayList.size(); ++i) {
            object3 = (X509Certificate)arrayList.get(i);
            object2 = ((X509Certificate)object3).getKeyUsage();
            if (!(object2 == null || ((boolean[])object2).length >= 7 && object2[6])) {
                object4 = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
                continue;
            }
            serializable.add(arrayList2.get(i));
        }
        if (serializable.isEmpty() && object4 == null) {
            throw new AnnotatedException("Cannot find a valid issuer certificate.");
        }
        if (serializable.isEmpty() && object4 != null) {
            throw object4;
        }
        return serializable;
    }

    protected static PublicKey processCRLG(X509CRL x509CRL, Set set) throws AnnotatedException {
        Exception exception = null;
        for (PublicKey publicKey : set) {
            try {
                x509CRL.verify(publicKey);
                return publicKey;
            } catch (Exception exception2) {
                exception = exception2;
            }
        }
        throw new AnnotatedException("Cannot verify CRL.", exception);
    }

    protected static X509CRL processCRLH(Set set, PublicKey publicKey) throws AnnotatedException {
        Exception exception = null;
        for (X509CRL x509CRL : set) {
            try {
                x509CRL.verify(publicKey);
                return x509CRL;
            } catch (Exception exception2) {
                exception = exception2;
            }
        }
        if (exception != null) {
            throw new AnnotatedException("Cannot verify delta CRL.", exception);
        }
        return null;
    }

    protected static Set processCRLA1i(Date date, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, X509CRL x509CRL) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        if (pKIXExtendedParameters.isUseDeltasEnabled()) {
            CRLDistPoint cRLDistPoint = null;
            try {
                cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, FRESHEST_CRL));
            } catch (AnnotatedException annotatedException) {
                throw new AnnotatedException("Freshest CRL extension could not be decoded from certificate.", annotatedException);
            }
            if (cRLDistPoint == null) {
                try {
                    cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRL, FRESHEST_CRL));
                } catch (AnnotatedException annotatedException) {
                    throw new AnnotatedException("Freshest CRL extension could not be decoded from CRL.", annotatedException);
                }
            }
            if (cRLDistPoint != null) {
                ArrayList<PKIXCRLStore> arrayList = new ArrayList<PKIXCRLStore>();
                arrayList.addAll(pKIXExtendedParameters.getCRLStores());
                try {
                    arrayList.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(cRLDistPoint, pKIXExtendedParameters.getNamedCRLStoreMap()));
                } catch (AnnotatedException annotatedException) {
                    throw new AnnotatedException("No new delta CRL locations could be added from Freshest CRL extension.", annotatedException);
                }
                try {
                    hashSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(date, x509CRL, pKIXExtendedParameters.getCertStores(), arrayList));
                } catch (AnnotatedException annotatedException) {
                    throw new AnnotatedException("Exception obtaining delta CRLs.", annotatedException);
                }
            }
        }
        return hashSet;
    }

    protected static Set[] processCRLA1ii(Date date, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, X509CRL x509CRL) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        X509CRLSelector x509CRLSelector = new X509CRLSelector();
        x509CRLSelector.setCertificateChecking(x509Certificate);
        try {
            x509CRLSelector.addIssuerName(PrincipalUtils.getIssuerPrincipal(x509CRL).getEncoded());
        } catch (IOException iOException) {
            throw new AnnotatedException("Cannot extract issuer from CRL." + iOException, iOException);
        }
        PKIXCRLStoreSelector<? extends CRL> pKIXCRLStoreSelector = new PKIXCRLStoreSelector.Builder(x509CRLSelector).setCompleteCRLEnabled(true).build();
        Date date2 = date;
        if (pKIXExtendedParameters.getDate() != null) {
            date2 = pKIXExtendedParameters.getDate();
        }
        Set set = CRL_UTIL.findCRLs(pKIXCRLStoreSelector, date2, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores());
        if (pKIXExtendedParameters.isUseDeltasEnabled()) {
            try {
                hashSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(date2, x509CRL, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores()));
            } catch (AnnotatedException annotatedException) {
                throw new AnnotatedException("Exception obtaining delta CRLs.", annotatedException);
            }
        }
        return new Set[]{set, hashSet};
    }

    protected static void processCRLC(X509CRL x509CRL, X509CRL x509CRL2, PKIXExtendedParameters pKIXExtendedParameters) throws AnnotatedException {
        if (x509CRL == null) {
            return;
        }
        IssuingDistributionPoint issuingDistributionPoint = null;
        try {
            issuingDistributionPoint = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRL2, ISSUING_DISTRIBUTION_POINT));
        } catch (Exception exception) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", exception);
        }
        if (pKIXExtendedParameters.isUseDeltasEnabled()) {
            if (!PrincipalUtils.getIssuerPrincipal(x509CRL).equals(PrincipalUtils.getIssuerPrincipal(x509CRL2))) {
                throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
            }
            IssuingDistributionPoint issuingDistributionPoint2 = null;
            try {
                issuingDistributionPoint2 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRL, ISSUING_DISTRIBUTION_POINT));
            } catch (Exception exception) {
                throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", exception);
            }
            boolean bl = false;
            if (issuingDistributionPoint == null) {
                if (issuingDistributionPoint2 == null) {
                    bl = true;
                }
            } else if (issuingDistributionPoint.equals(issuingDistributionPoint2)) {
                bl = true;
            }
            if (!bl) {
                throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
            }
            ASN1Primitive aSN1Primitive = null;
            try {
                aSN1Primitive = CertPathValidatorUtilities.getExtensionValue(x509CRL2, AUTHORITY_KEY_IDENTIFIER);
            } catch (AnnotatedException annotatedException) {
                throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", annotatedException);
            }
            ASN1Primitive aSN1Primitive2 = null;
            try {
                aSN1Primitive2 = CertPathValidatorUtilities.getExtensionValue(x509CRL, AUTHORITY_KEY_IDENTIFIER);
            } catch (AnnotatedException annotatedException) {
                throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", annotatedException);
            }
            if (aSN1Primitive == null) {
                throw new AnnotatedException("CRL authority key identifier is null.");
            }
            if (aSN1Primitive2 == null) {
                throw new AnnotatedException("Delta CRL authority key identifier is null.");
            }
            if (!aSN1Primitive.equals(aSN1Primitive2)) {
                throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
            }
        }
    }

    protected static void processCRLI(Date date, X509CRL x509CRL, Object object, CertStatus certStatus, PKIXExtendedParameters pKIXExtendedParameters) throws AnnotatedException {
        if (pKIXExtendedParameters.isUseDeltasEnabled() && x509CRL != null) {
            CertPathValidatorUtilities.getCertStatus(date, x509CRL, object, certStatus);
        }
    }

    protected static void processCRLJ(Date date, X509CRL x509CRL, Object object, CertStatus certStatus) throws AnnotatedException {
        if (certStatus.getCertStatus() == 11) {
            CertPathValidatorUtilities.getCertStatus(date, x509CRL, object, certStatus);
        }
    }

    protected static PKIXPolicyNode prepareCertB(CertPath certPath, int n, List[] listArray, PKIXPolicyNode pKIXPolicyNode, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        int n3 = list.size();
        int n4 = n3 - n;
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, POLICY_MAPPINGS));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", (Throwable)annotatedException, certPath, n);
        }
        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
        if (aSN1Sequence != null) {
            Object object3;
            Object object2;
            ASN1Sequence aSN1Sequence2 = aSN1Sequence;
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            HashSet<String> hashSet = new HashSet<String>();
            for (int i = 0; i < aSN1Sequence2.size(); ++i) {
                ASN1Sequence object3 = (ASN1Sequence)aSN1Sequence2.getObjectAt(i);
                String iterator = ((ASN1ObjectIdentifier)object3.getObjectAt(0)).getId();
                object2 = ((ASN1ObjectIdentifier)object3.getObjectAt(1)).getId();
                if (!hashMap.containsKey(iterator)) {
                    object3 = new HashSet();
                    object3.add(object2);
                    hashMap.put(iterator, object3);
                    hashSet.add(iterator);
                    continue;
                }
                object3 = (Set)hashMap.get(iterator);
                object3.add(object2);
            }
            block9: for (String string : hashSet) {
                Iterable iterable;
                if (n2 > 0) {
                    boolean bl = false;
                    for (Object object3 : listArray[n4]) {
                        if (!((PKIXPolicyNode)object3).getValidPolicy().equals(string)) continue;
                        bl = true;
                        ((PKIXPolicyNode)object3).expectedPolicies = (Set)hashMap.get(string);
                        break;
                    }
                    if (bl) continue;
                    for (Object object3 : listArray[n4]) {
                        PKIXPolicyNode certPathValidatorException;
                        if (!ANY_POLICY.equals(((PKIXPolicyNode)object3).getValidPolicy())) continue;
                        Set set = null;
                        iterable = null;
                        try {
                            iterable = (ASN1Sequence)CertPathValidatorUtilities.getExtensionValue(x509Certificate, CERTIFICATE_POLICIES);
                        } catch (AnnotatedException j) {
                            throw new ExtCertPathValidatorException("Certificate policies extension could not be decoded.", (Throwable)j, certPath, n);
                        }
                        Enumeration enumeration = ((ASN1Sequence)iterable).getObjects();
                        while (enumeration.hasMoreElements()) {
                            PolicyInformation pKIXPolicyNode5 = null;
                            try {
                                pKIXPolicyNode5 = PolicyInformation.getInstance(enumeration.nextElement());
                            } catch (Exception exception) {
                                throw new CertPathValidatorException("Policy information could not be decoded.", (Throwable)exception, certPath, n);
                            }
                            if (!ANY_POLICY.equals(pKIXPolicyNode5.getPolicyIdentifier().getId())) continue;
                            try {
                                set = CertPathValidatorUtilities.getQualifierSet(pKIXPolicyNode5.getPolicyQualifiers());
                                break;
                            } catch (CertPathValidatorException certPathValidatorException2) {
                                throw new ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", (Throwable)certPathValidatorException2, certPath, n);
                            }
                        }
                        boolean bl2 = false;
                        if (x509Certificate.getCriticalExtensionOIDs() != null) {
                            bl2 = x509Certificate.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
                        }
                        if (!ANY_POLICY.equals((certPathValidatorException = (PKIXPolicyNode)((PKIXPolicyNode)object3).getParent()).getValidPolicy())) continue block9;
                        PKIXPolicyNode pKIXPolicyNode3 = new PKIXPolicyNode(new ArrayList(), n4, (Set)hashMap.get(string), certPathValidatorException, set, string, bl2);
                        certPathValidatorException.addChild(pKIXPolicyNode3);
                        listArray[n4].add(pKIXPolicyNode3);
                        continue block9;
                    }
                    continue;
                }
                if (n2 > 0) continue;
                Iterator iterator = listArray[n4].iterator();
                while (iterator.hasNext()) {
                    object2 = (PKIXPolicyNode)iterator.next();
                    if (!((PKIXPolicyNode)object2).getValidPolicy().equals(string)) continue;
                    object3 = (PKIXPolicyNode)((PKIXPolicyNode)object2).getParent();
                    ((PKIXPolicyNode)object3).removeChild((PKIXPolicyNode)object2);
                    iterator.remove();
                    for (int i = n4 - 1; i >= 0; --i) {
                        PKIXPolicyNode pKIXPolicyNode4;
                        iterable = listArray[i];
                        for (int j = 0; j < iterable.size() && ((pKIXPolicyNode4 = (PKIXPolicyNode)iterable.get(j)).hasChildren() || (pKIXPolicyNode2 = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode2, listArray, pKIXPolicyNode4)) != null); ++j) {
                        }
                    }
                }
            }
        }
        return pKIXPolicyNode2;
    }

    protected static void prepareNextCertA(CertPath certPath, int n) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, POLICY_MAPPINGS));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", (Throwable)annotatedException, certPath, n);
        }
        if (aSN1Sequence != null) {
            ASN1Sequence aSN1Sequence2 = aSN1Sequence;
            for (int i = 0; i < aSN1Sequence2.size(); ++i) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = null;
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = null;
                try {
                    ASN1Sequence aSN1Sequence3 = DERSequence.getInstance(aSN1Sequence2.getObjectAt(i));
                    aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(aSN1Sequence3.getObjectAt(0));
                    aSN1ObjectIdentifier2 = ASN1ObjectIdentifier.getInstance(aSN1Sequence3.getObjectAt(1));
                } catch (Exception exception) {
                    throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", (Throwable)exception, certPath, n);
                }
                if (ANY_POLICY.equals(aSN1ObjectIdentifier.getId())) {
                    throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", null, certPath, n);
                }
                if (!ANY_POLICY.equals(aSN1ObjectIdentifier2.getId())) continue;
                throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy,", null, certPath, n);
            }
        }
    }

    protected static void processCertF(CertPath certPath, int n, PKIXPolicyNode pKIXPolicyNode, int n2) throws CertPathValidatorException {
        if (n2 <= 0 && pKIXPolicyNode == null) {
            throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", null, certPath, n);
        }
    }

    protected static PKIXPolicyNode processCertE(CertPath certPath, int n, PKIXPolicyNode pKIXPolicyNode) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CERTIFICATE_POLICIES));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", (Throwable)annotatedException, certPath, n);
        }
        if (aSN1Sequence == null) {
            pKIXPolicyNode = null;
        }
        return pKIXPolicyNode;
    }

    protected static void processCertBC(CertPath certPath, int n, PKIXNameConstraintValidator pKIXNameConstraintValidator) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        int n2 = list.size();
        int n3 = n2 - n;
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) || n3 >= n2) {
            ASN1Sequence aSN1Sequence;
            X500Name x500Name = PrincipalUtils.getSubjectPrincipal(x509Certificate);
            try {
                aSN1Sequence = DERSequence.getInstance(x500Name.getEncoded());
            } catch (Exception exception) {
                throw new CertPathValidatorException("Exception extracting subject name when checking subtrees.", (Throwable)exception, certPath, n);
            }
            try {
                pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
            } catch (PKIXNameConstraintValidatorException pKIXNameConstraintValidatorException) {
                throw new CertPathValidatorException("Subtree check for certificate subject failed.", (Throwable)pKIXNameConstraintValidatorException, certPath, n);
            }
            GeneralNames generalNames = null;
            try {
                generalNames = GeneralNames.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME));
            } catch (Exception exception) {
                throw new CertPathValidatorException("Subject alternative name extension could not be decoded.", (Throwable)exception, certPath, n);
            }
            RDN[] rDNArray = X500Name.getInstance(aSN1Sequence).getRDNs(BCStyle.EmailAddress);
            for (int i = 0; i != rDNArray.length; ++i) {
                String string = ((ASN1String)((Object)rDNArray[i].getFirst().getValue())).getString();
                GeneralName generalName = new GeneralName(1, string);
                try {
                    pKIXNameConstraintValidator.checkPermitted(generalName);
                    pKIXNameConstraintValidator.checkExcluded(generalName);
                    continue;
                } catch (PKIXNameConstraintValidatorException pKIXNameConstraintValidatorException) {
                    throw new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", (Throwable)pKIXNameConstraintValidatorException, certPath, n);
                }
            }
            if (generalNames != null) {
                GeneralName[] generalNameArray = null;
                try {
                    generalNameArray = generalNames.getNames();
                } catch (Exception exception) {
                    throw new CertPathValidatorException("Subject alternative name contents could not be decoded.", (Throwable)exception, certPath, n);
                }
                for (int i = 0; i < generalNameArray.length; ++i) {
                    try {
                        pKIXNameConstraintValidator.checkPermitted(generalNameArray[i]);
                        pKIXNameConstraintValidator.checkExcluded(generalNameArray[i]);
                        continue;
                    } catch (PKIXNameConstraintValidatorException pKIXNameConstraintValidatorException) {
                        throw new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", (Throwable)pKIXNameConstraintValidatorException, certPath, n);
                    }
                }
            }
        }
    }

    protected static PKIXPolicyNode processCertD(CertPath certPath, int n, Set set, PKIXPolicyNode pKIXPolicyNode, List[] listArray, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        int n3 = list.size();
        int n4 = n3 - n;
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CERTIFICATE_POLICIES));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", (Throwable)annotatedException, certPath, n);
        }
        if (aSN1Sequence != null && pKIXPolicyNode != null) {
            PKIXPolicyNode pKIXPolicyNode2;
            int n5;
            Collection collection;
            Object object;
            Object object2;
            Enumeration enumeration = aSN1Sequence.getObjects();
            HashSet<String> hashSet = new HashSet<String>();
            while (enumeration.hasMoreElements()) {
                object2 = PolicyInformation.getInstance(enumeration.nextElement());
                object = ((PolicyInformation)object2).getPolicyIdentifier();
                hashSet.add(((ASN1ObjectIdentifier)object).getId());
                if (ANY_POLICY.equals(((ASN1ObjectIdentifier)object).getId())) continue;
                collection = null;
                try {
                    collection = CertPathValidatorUtilities.getQualifierSet(((PolicyInformation)object2).getPolicyQualifiers());
                } catch (CertPathValidatorException certPathValidatorException) {
                    throw new ExtCertPathValidatorException("Policy qualifier info set could not be build.", (Throwable)certPathValidatorException, certPath, n);
                }
                n5 = CertPathValidatorUtilities.processCertD1i(n4, listArray, (ASN1ObjectIdentifier)object, (Set)collection);
                if (n5 != 0) continue;
                CertPathValidatorUtilities.processCertD1ii(n4, listArray, (ASN1ObjectIdentifier)object, collection);
            }
            if (set.isEmpty() || set.contains(ANY_POLICY)) {
                set.clear();
                set.addAll(hashSet);
            } else {
                object2 = set.iterator();
                object = new HashSet();
                while (object2.hasNext()) {
                    collection = (Collection)object2.next();
                    if (!hashSet.contains(collection)) continue;
                    object.add(collection);
                }
                set.clear();
                set.addAll(object);
            }
            if (n2 > 0 || n4 < n3 && CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
                enumeration = aSN1Sequence.getObjects();
                while (enumeration.hasMoreElements()) {
                    object2 = PolicyInformation.getInstance(enumeration.nextElement());
                    if (!ANY_POLICY.equals(((PolicyInformation)object2).getPolicyIdentifier().getId())) continue;
                    object = CertPathValidatorUtilities.getQualifierSet(((PolicyInformation)object2).getPolicyQualifiers());
                    collection = listArray[n4 - 1];
                    for (n5 = 0; n5 < collection.size(); ++n5) {
                        pKIXPolicyNode2 = (PKIXPolicyNode)collection.get(n5);
                        for (Object e : pKIXPolicyNode2.getExpectedPolicies()) {
                            Object object3;
                            String string;
                            if (e instanceof String) {
                                string = (String)e;
                            } else {
                                if (!(e instanceof ASN1ObjectIdentifier)) continue;
                                string = ((ASN1ObjectIdentifier)e).getId();
                            }
                            boolean bl = false;
                            Iterator iterator = pKIXPolicyNode2.getChildren();
                            while (iterator.hasNext()) {
                                object3 = (PKIXPolicyNode)iterator.next();
                                if (!string.equals(((PKIXPolicyNode)object3).getValidPolicy())) continue;
                                bl = true;
                            }
                            if (bl) continue;
                            object3 = new HashSet();
                            object3.add(string);
                            PKIXPolicyNode pKIXPolicyNode3 = new PKIXPolicyNode(new ArrayList(), n4, (Set)object3, pKIXPolicyNode2, (Set)object, string, false);
                            pKIXPolicyNode2.addChild(pKIXPolicyNode3);
                            listArray[n4].add(pKIXPolicyNode3);
                        }
                    }
                }
            }
            object2 = pKIXPolicyNode;
            for (int i = n4 - 1; i >= 0; --i) {
                collection = listArray[i];
                for (n5 = 0; n5 < collection.size() && ((pKIXPolicyNode2 = (PKIXPolicyNode)collection.get(n5)).hasChildren() || (object2 = CertPathValidatorUtilities.removePolicyNode((PKIXPolicyNode)object2, listArray, pKIXPolicyNode2)) != null); ++n5) {
                }
            }
            Set<String> set2 = x509Certificate.getCriticalExtensionOIDs();
            if (set2 != null) {
                boolean bl = set2.contains(CERTIFICATE_POLICIES);
                List list2 = listArray[n4];
                for (int i = 0; i < list2.size(); ++i) {
                    PKIXPolicyNode pKIXPolicyNode4 = (PKIXPolicyNode)list2.get(i);
                    pKIXPolicyNode4.setCritical(bl);
                }
            }
            return object2;
        }
        return null;
    }

    protected static void processCertA(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters, int n, PublicKey publicKey, boolean bl, X500Name x500Name, X509Certificate x509Certificate, JcaJceHelper jcaJceHelper) throws ExtCertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate2 = (X509Certificate)list.get(n);
        if (!bl) {
            try {
                CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, publicKey, pKIXExtendedParameters.getSigProvider());
            } catch (GeneralSecurityException generalSecurityException) {
                throw new ExtCertPathValidatorException("Could not validate certificate signature.", (Throwable)generalSecurityException, certPath, n);
            }
        }
        try {
            x509Certificate2.checkValidity(CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, certPath, n));
        } catch (CertificateExpiredException certificateExpiredException) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + certificateExpiredException.getMessage(), (Throwable)certificateExpiredException, certPath, n);
        } catch (CertificateNotYetValidException certificateNotYetValidException) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + certificateNotYetValidException.getMessage(), (Throwable)certificateNotYetValidException, certPath, n);
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Could not validate time of certificate.", (Throwable)annotatedException, certPath, n);
        }
        if (pKIXExtendedParameters.isRevocationEnabled()) {
            try {
                RFC3280CertPathUtilities.checkCRLs(pKIXExtendedParameters, x509Certificate2, CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, certPath, n), x509Certificate, publicKey, list, jcaJceHelper);
            } catch (AnnotatedException annotatedException) {
                Throwable throwable = annotatedException;
                if (null != annotatedException.getCause()) {
                    throwable = annotatedException.getCause();
                }
                throw new ExtCertPathValidatorException(annotatedException.getMessage(), throwable, certPath, n);
            }
        }
        if (!PrincipalUtils.getEncodedIssuerPrincipal(x509Certificate2).equals(x500Name)) {
            throw new ExtCertPathValidatorException("IssuerName(" + PrincipalUtils.getEncodedIssuerPrincipal(x509Certificate2) + ") does not match SubjectName(" + x500Name + ") of signing certificate.", null, certPath, n);
        }
    }

    protected static int prepareNextCertI1(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, POLICY_CONSTRAINTS));
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", (Throwable)exception, certPath, n);
        }
        if (aSN1Sequence != null) {
            Enumeration enumeration = aSN1Sequence.getObjects();
            while (enumeration.hasMoreElements()) {
                try {
                    ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(enumeration.nextElement());
                    if (aSN1TaggedObject.getTagNo() != 0) continue;
                    int n3 = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue();
                    if (n3 < n2) {
                        return n3;
                    }
                    break;
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", (Throwable)illegalArgumentException, certPath, n);
                }
            }
        }
        return n2;
    }

    protected static int prepareNextCertI2(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, POLICY_CONSTRAINTS));
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", (Throwable)exception, certPath, n);
        }
        if (aSN1Sequence != null) {
            Enumeration enumeration = aSN1Sequence.getObjects();
            while (enumeration.hasMoreElements()) {
                try {
                    ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(enumeration.nextElement());
                    if (aSN1TaggedObject.getTagNo() != 1) continue;
                    int n3 = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue();
                    if (n3 < n2) {
                        return n3;
                    }
                    break;
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", (Throwable)illegalArgumentException, certPath, n);
                }
            }
        }
        return n2;
    }

    protected static void prepareNextCertG(CertPath certPath, int n, PKIXNameConstraintValidator pKIXNameConstraintValidator) throws CertPathValidatorException {
        GeneralSubtree[] generalSubtreeArray;
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        NameConstraints nameConstraints = null;
        try {
            generalSubtreeArray = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, NAME_CONSTRAINTS));
            if (generalSubtreeArray != null) {
                nameConstraints = NameConstraints.getInstance(generalSubtreeArray);
            }
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", (Throwable)exception, certPath, n);
        }
        if (nameConstraints != null) {
            GeneralSubtree[] generalSubtreeArray2;
            generalSubtreeArray = nameConstraints.getPermittedSubtrees();
            if (generalSubtreeArray != null) {
                try {
                    pKIXNameConstraintValidator.intersectPermittedSubtree(generalSubtreeArray);
                } catch (Exception exception) {
                    throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", (Throwable)exception, certPath, n);
                }
            }
            if ((generalSubtreeArray2 = nameConstraints.getExcludedSubtrees()) != null) {
                for (int i = 0; i != generalSubtreeArray2.length; ++i) {
                    try {
                        pKIXNameConstraintValidator.addExcludedSubtree(generalSubtreeArray2[i]);
                        continue;
                    } catch (Exception exception) {
                        throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", (Throwable)exception, certPath, n);
                    }
                }
            }
        }
    }

    private static void checkCRL(DistributionPoint distributionPoint, PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, CertStatus certStatus, ReasonsMask reasonsMask, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        Date date2 = new Date(System.currentTimeMillis());
        if (date.getTime() > date2.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        Set set = CertPathValidatorUtilities.getCompleteCRLs(distributionPoint, x509Certificate, date2, pKIXExtendedParameters);
        boolean bl = false;
        AnnotatedException annotatedException = null;
        Iterator iterator = set.iterator();
        while (iterator.hasNext() && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
            try {
                Set<String> set2;
                X509CRL x509CRL = (X509CRL)iterator.next();
                ReasonsMask reasonsMask2 = RFC3280CertPathUtilities.processCRLD(x509CRL, distributionPoint);
                if (!reasonsMask2.hasNewReasons(reasonsMask)) continue;
                Set set3 = RFC3280CertPathUtilities.processCRLF(x509CRL, x509Certificate, x509Certificate2, publicKey, pKIXExtendedParameters, list, jcaJceHelper);
                PublicKey publicKey2 = RFC3280CertPathUtilities.processCRLG(x509CRL, set3);
                X509CRL x509CRL2 = null;
                Date date3 = date2;
                if (pKIXExtendedParameters.getDate() != null) {
                    date3 = pKIXExtendedParameters.getDate();
                }
                if (pKIXExtendedParameters.isUseDeltasEnabled()) {
                    set2 = CertPathValidatorUtilities.getDeltaCRLs(date3, x509CRL, pKIXExtendedParameters.getCertStores(), pKIXExtendedParameters.getCRLStores());
                    x509CRL2 = RFC3280CertPathUtilities.processCRLH(set2, publicKey2);
                }
                if (pKIXExtendedParameters.getValidityModel() != 1 && x509Certificate.getNotAfter().getTime() < x509CRL.getThisUpdate().getTime()) {
                    throw new AnnotatedException("No valid CRL for current time found.");
                }
                RFC3280CertPathUtilities.processCRLB1(distributionPoint, x509Certificate, x509CRL);
                RFC3280CertPathUtilities.processCRLB2(distributionPoint, x509Certificate, x509CRL);
                RFC3280CertPathUtilities.processCRLC(x509CRL2, x509CRL, pKIXExtendedParameters);
                RFC3280CertPathUtilities.processCRLI(date, x509CRL2, x509Certificate, certStatus, pKIXExtendedParameters);
                RFC3280CertPathUtilities.processCRLJ(date, x509CRL, x509Certificate, certStatus);
                if (certStatus.getCertStatus() == 8) {
                    certStatus.setCertStatus(11);
                }
                reasonsMask.addReasons(reasonsMask2);
                set2 = x509CRL.getCriticalExtensionOIDs();
                if (set2 != null) {
                    set2 = new HashSet<String>(set2);
                    set2.remove(Extension.issuingDistributionPoint.getId());
                    set2.remove(Extension.deltaCRLIndicator.getId());
                    if (!set2.isEmpty()) {
                        throw new AnnotatedException("CRL contains unsupported critical extensions.");
                    }
                }
                if (x509CRL2 != null && (set2 = x509CRL2.getCriticalExtensionOIDs()) != null) {
                    set2 = new HashSet<String>(set2);
                    set2.remove(Extension.issuingDistributionPoint.getId());
                    set2.remove(Extension.deltaCRLIndicator.getId());
                    if (!set2.isEmpty()) {
                        throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                    }
                }
                bl = true;
            } catch (AnnotatedException annotatedException2) {
                annotatedException = annotatedException2;
            }
        }
        if (!bl) {
            throw annotatedException;
        }
    }

    protected static void checkCRLs(PKIXExtendedParameters pKIXExtendedParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, List list, JcaJceHelper jcaJceHelper) throws AnnotatedException {
        Object object;
        Object object2;
        Object object3;
        AnnotatedException annotatedException = null;
        CRLDistPoint cRLDistPoint = null;
        try {
            cRLDistPoint = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CRL_DISTRIBUTION_POINTS));
        } catch (Exception exception) {
            throw new AnnotatedException("CRL distribution point extension could not be read.", exception);
        }
        PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXExtendedParameters);
        try {
            object3 = CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(cRLDistPoint, pKIXExtendedParameters.getNamedCRLStoreMap());
            object2 = object3.iterator();
            while (object2.hasNext()) {
                builder.addCRLStore(object2.next());
            }
        } catch (AnnotatedException annotatedException2) {
            throw new AnnotatedException("No additional CRL locations could be decoded from CRL distribution point extension.", annotatedException2);
        }
        object3 = new CertStatus();
        object2 = new ReasonsMask();
        PKIXExtendedParameters pKIXExtendedParameters2 = builder.build();
        boolean bl = false;
        if (cRLDistPoint != null) {
            object = null;
            try {
                object = cRLDistPoint.getDistributionPoints();
            } catch (Exception exception) {
                throw new AnnotatedException("Distribution points could not be read.", exception);
            }
            if (object != null) {
                for (int i = 0; i < ((DistributionPoint[])object).length && ((CertStatus)object3).getCertStatus() == 11 && !((ReasonsMask)object2).isAllReasons(); ++i) {
                    try {
                        RFC3280CertPathUtilities.checkCRL(object[i], pKIXExtendedParameters2, x509Certificate, date, x509Certificate2, publicKey, (CertStatus)object3, (ReasonsMask)object2, list, jcaJceHelper);
                        bl = true;
                        continue;
                    } catch (AnnotatedException annotatedException3) {
                        annotatedException = annotatedException3;
                    }
                }
            }
        }
        if (((CertStatus)object3).getCertStatus() == 11 && !((ReasonsMask)object2).isAllReasons()) {
            try {
                object = null;
                try {
                    object = new ASN1InputStream(PrincipalUtils.getEncodedIssuerPrincipal(x509Certificate).getEncoded()).readObject();
                } catch (Exception exception) {
                    throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", exception);
                }
                DistributionPoint distributionPoint = new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, (ASN1Encodable)object))), null, null);
                PKIXExtendedParameters pKIXExtendedParameters3 = (PKIXExtendedParameters)pKIXExtendedParameters.clone();
                RFC3280CertPathUtilities.checkCRL(distributionPoint, pKIXExtendedParameters3, x509Certificate, date, x509Certificate2, publicKey, (CertStatus)object3, (ReasonsMask)object2, list, jcaJceHelper);
                bl = true;
            } catch (AnnotatedException annotatedException4) {
                annotatedException = annotatedException4;
            }
        }
        if (!bl) {
            if (annotatedException instanceof AnnotatedException) {
                throw annotatedException;
            }
            throw new AnnotatedException("No valid CRL found.", annotatedException);
        }
        if (((CertStatus)object3).getCertStatus() != 11) {
            object = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
            ((DateFormat)object).setTimeZone(TimeZone.getTimeZone("UTC"));
            String string = "Certificate revocation after " + ((DateFormat)object).format(((CertStatus)object3).getRevocationDate());
            string = string + ", reason: " + crlReasons[((CertStatus)object3).getCertStatus()];
            throw new AnnotatedException(string);
        }
        if (!((ReasonsMask)object2).isAllReasons() && ((CertStatus)object3).getCertStatus() == 11) {
            ((CertStatus)object3).setCertStatus(12);
        }
        if (((CertStatus)object3).getCertStatus() == 12) {
            throw new AnnotatedException("Certificate status could not be determined.");
        }
    }

    protected static int prepareNextCertJ(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        int n3;
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Integer aSN1Integer = null;
        try {
            aSN1Integer = ASN1Integer.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, INHIBIT_ANY_POLICY));
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", (Throwable)exception, certPath, n);
        }
        if (aSN1Integer != null && (n3 = aSN1Integer.getValue().intValue()) < n2) {
            return n3;
        }
        return n2;
    }

    protected static void prepareNextCertK(CertPath certPath, int n) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        BasicConstraints basicConstraints = null;
        try {
            basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", (Throwable)exception, certPath, n);
        }
        if (basicConstraints != null) {
            if (!basicConstraints.isCA()) {
                throw new CertPathValidatorException("Not a CA certificate");
            }
        } else {
            throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints");
        }
    }

    protected static int prepareNextCertL(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
            if (n2 <= 0) {
                throw new ExtCertPathValidatorException("Max path length not greater than zero", null, certPath, n);
            }
            return n2 - 1;
        }
        return n2;
    }

    protected static int prepareNextCertM(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        int n3;
        BigInteger bigInteger;
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        BasicConstraints basicConstraints = null;
        try {
            basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", (Throwable)exception, certPath, n);
        }
        if (basicConstraints != null && (bigInteger = basicConstraints.getPathLenConstraint()) != null && (n3 = bigInteger.intValue()) < n2) {
            return n3;
        }
        return n2;
    }

    protected static void prepareNextCertN(CertPath certPath, int n) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        boolean[] blArray = x509Certificate.getKeyUsage();
        if (blArray != null && !blArray[5]) {
            throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", null, certPath, n);
        }
    }

    protected static void prepareNextCertO(CertPath certPath, int n, Set set, List list) throws CertPathValidatorException {
        List<? extends Certificate> list2 = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list2.get(n);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            try {
                ((PKIXCertPathChecker)iterator.next()).check(x509Certificate, set);
            } catch (CertPathValidatorException certPathValidatorException) {
                throw new CertPathValidatorException(certPathValidatorException.getMessage(), certPathValidatorException.getCause(), certPath, n);
            }
        }
        if (!set.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, n);
        }
    }

    protected static int prepareNextCertH1(CertPath certPath, int n, int n2) {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) && n2 != 0) {
            return n2 - 1;
        }
        return n2;
    }

    protected static int prepareNextCertH2(CertPath certPath, int n, int n2) {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) && n2 != 0) {
            return n2 - 1;
        }
        return n2;
    }

    protected static int prepareNextCertH3(CertPath certPath, int n, int n2) {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) && n2 != 0) {
            return n2 - 1;
        }
        return n2;
    }

    protected static int wrapupCertA(int n, X509Certificate x509Certificate) {
        if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate) && n != 0) {
            --n;
        }
        return n;
    }

    protected static int wrapupCertB(CertPath certPath, int n, int n2) throws CertPathValidatorException {
        List<? extends Certificate> list = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list.get(n);
        ASN1Sequence aSN1Sequence = null;
        try {
            aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, POLICY_CONSTRAINTS));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", (Throwable)annotatedException, certPath, n);
        }
        if (aSN1Sequence != null) {
            Enumeration enumeration = aSN1Sequence.getObjects();
            while (enumeration.hasMoreElements()) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0: {
                        int n3;
                        try {
                            n3 = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue();
                        } catch (Exception exception) {
                            throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", (Throwable)exception, certPath, n);
                        }
                        if (n3 != 0) break;
                        return 0;
                    }
                }
            }
        }
        return n2;
    }

    protected static void wrapupCertF(CertPath certPath, int n, List list, Set set) throws CertPathValidatorException {
        List<? extends Certificate> list2 = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate)list2.get(n);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            try {
                ((PKIXCertPathChecker)iterator.next()).check(x509Certificate, set);
            } catch (CertPathValidatorException certPathValidatorException) {
                throw new ExtCertPathValidatorException("Additional certificate path checker failed.", (Throwable)certPathValidatorException, certPath, n);
            }
        }
        if (!set.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, n);
        }
    }

    /*
     * WARNING - void declaration
     */
    protected static PKIXPolicyNode wrapupCertG(CertPath certPath, PKIXExtendedParameters pKIXExtendedParameters, Set set, int n, List[] listArray, PKIXPolicyNode pKIXPolicyNode, Set set2) throws CertPathValidatorException {
        PKIXPolicyNode pKIXPolicyNode2;
        int n2 = certPath.getCertificates().size();
        if (pKIXPolicyNode == null) {
            if (pKIXExtendedParameters.isExplicitPolicyRequired()) {
                throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, n);
            }
            pKIXPolicyNode2 = null;
        } else if (CertPathValidatorUtilities.isAnyPolicy(set)) {
            if (pKIXExtendedParameters.isExplicitPolicyRequired()) {
                Object object;
                if (set2.isEmpty()) {
                    throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, n);
                }
                HashSet hashSet = new HashSet();
                for (int i = 0; i < listArray.length; ++i) {
                    List object2 = listArray[i];
                    for (int list = 0; list < object2.size(); ++list) {
                        PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode)object2.get(list);
                        if (!ANY_POLICY.equals(pKIXPolicyNode3.getValidPolicy())) continue;
                        object = pKIXPolicyNode3.getChildren();
                        while (object.hasNext()) {
                            hashSet.add(object.next());
                        }
                    }
                }
                for (PKIXPolicyNode object3 : hashSet) {
                    String string = object3.getValidPolicy();
                    if (set2.contains(string)) continue;
                }
                if (pKIXPolicyNode != null) {
                    void var11_19;
                    int i = n2 - 1;
                    while (var11_19 >= 0) {
                        List list = listArray[var11_19];
                        for (int j = 0; j < list.size(); ++j) {
                            object = (PKIXPolicyNode)list.get(j);
                            if (((PKIXPolicyNode)object).hasChildren()) continue;
                            pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArray, (PKIXPolicyNode)object);
                        }
                        --var11_19;
                    }
                }
            }
            pKIXPolicyNode2 = pKIXPolicyNode;
        } else {
            Object object;
            HashSet<PKIXPolicyNode> hashSet = new HashSet<PKIXPolicyNode>();
            for (int i = 0; i < listArray.length; ++i) {
                List list = listArray[i];
                for (int j = 0; j < list.size(); ++j) {
                    PKIXPolicyNode pKIXPolicyNode4 = (PKIXPolicyNode)list.get(j);
                    if (!ANY_POLICY.equals(pKIXPolicyNode4.getValidPolicy())) continue;
                    object = pKIXPolicyNode4.getChildren();
                    while (object.hasNext()) {
                        PKIXPolicyNode pKIXPolicyNode5 = (PKIXPolicyNode)object.next();
                        if (ANY_POLICY.equals(pKIXPolicyNode5.getValidPolicy())) continue;
                        hashSet.add(pKIXPolicyNode5);
                    }
                }
            }
            for (PKIXPolicyNode pKIXPolicyNode6 : hashSet) {
                String string = pKIXPolicyNode6.getValidPolicy();
                if (set.contains(string)) continue;
                pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArray, pKIXPolicyNode6);
            }
            if (pKIXPolicyNode != null) {
                void var11_24;
                int n3 = n2 - 1;
                while (var11_24 >= 0) {
                    List list = listArray[var11_24];
                    for (int i = 0; i < list.size(); ++i) {
                        object = (PKIXPolicyNode)list.get(i);
                        if (((PKIXPolicyNode)object).hasChildren()) continue;
                        pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArray, (PKIXPolicyNode)object);
                    }
                    --var11_24;
                }
            }
            pKIXPolicyNode2 = pKIXPolicyNode;
        }
        return pKIXPolicyNode2;
    }
}

