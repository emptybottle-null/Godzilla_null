/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.PrincipalUtils;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathValidatorSpi
extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        HashSet hashSet;
        PublicKey publicKey;
        X500Name x500Name;
        TrustAnchor trustAnchor;
        PKIXExtendedParameters pKIXExtendedParameters;
        Object object;
        if (certPathParameters instanceof PKIXParameters) {
            object = new PKIXExtendedParameters.Builder((PKIXParameters)certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters)certPathParameters;
                ((PKIXExtendedParameters.Builder)object).setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                ((PKIXExtendedParameters.Builder)object).setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = ((PKIXExtendedParameters.Builder)object).build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters)certPathParameters).getBaseParameters();
        } else if (certPathParameters instanceof PKIXExtendedParameters) {
            pKIXExtendedParameters = (PKIXExtendedParameters)certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        object = certPath.getCertificates();
        int n = object.size();
        if (object.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set set = pKIXExtendedParameters.getInitialPolicies();
        try {
            trustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate)object.get(object.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (trustAnchor == null) {
                throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
            }
            PKIXCertPathValidatorSpi.checkCertificate(trustAnchor.getTrustedCert());
        } catch (AnnotatedException annotatedException) {
            throw new CertPathValidatorException(annotatedException.getMessage(), annotatedException.getUnderlyingException(), certPath, object.size() - 1);
        }
        pKIXExtendedParameters = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(trustAnchor).build();
        int n2 = 0;
        List[] listArray = new ArrayList[n + 1];
        for (int i = 0; i < listArray.length; ++i) {
            listArray[i] = new ArrayList();
        }
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("2.5.29.32.0");
        PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet2, null, new HashSet(), "2.5.29.32.0", false);
        listArray[0].add(pKIXPolicyNode);
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        HashSet hashSet3 = new HashSet();
        int n3 = pKIXExtendedParameters.isExplicitPolicyRequired() ? 0 : n + 1;
        int n4 = pKIXExtendedParameters.isAnyPolicyInhibited() ? 0 : n + 1;
        int n5 = pKIXExtendedParameters.isPolicyMappingInhibited() ? 0 : n + 1;
        X509Certificate x509Certificate = trustAnchor.getTrustedCert();
        try {
            if (x509Certificate != null) {
                x500Name = PrincipalUtils.getSubjectPrincipal(x509Certificate);
                publicKey = x509Certificate.getPublicKey();
            } else {
                x500Name = PrincipalUtils.getCA(trustAnchor);
                publicKey = trustAnchor.getCAPublicKey();
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", (Throwable)illegalArgumentException, certPath, -1);
        }
        AlgorithmIdentifier algorithmIdentifier = null;
        try {
            algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(publicKey);
        } catch (CertPathValidatorException certPathValidatorException) {
            throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", (Throwable)certPathValidatorException, certPath, -1);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
        ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
        int n6 = n;
        if (pKIXExtendedParameters.getTargetConstraints() != null && !pKIXExtendedParameters.getTargetConstraints().match((X509Certificate)object.get(0))) {
            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
        }
        List list = pKIXExtendedParameters.getCertPathCheckers();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ((PKIXCertPathChecker)iterator.next()).init(false);
        }
        X509Certificate x509Certificate2 = null;
        for (n2 = object.size() - 1; n2 >= 0; --n2) {
            int n7 = n - n2;
            x509Certificate2 = (X509Certificate)object.get(n2);
            boolean bl = n2 == object.size() - 1;
            try {
                PKIXCertPathValidatorSpi.checkCertificate(x509Certificate2);
            } catch (AnnotatedException annotatedException) {
                throw new CertPathValidatorException(annotatedException.getMessage(), annotatedException.getUnderlyingException(), certPath, n2);
            }
            RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters, n2, publicKey, bl, x500Name, x509Certificate, this.helper);
            RFC3280CertPathUtilities.processCertBC(certPath, n2, pKIXNameConstraintValidator);
            pKIXPolicyNode = RFC3280CertPathUtilities.processCertD(certPath, n2, hashSet3, pKIXPolicyNode, listArray, n4);
            pKIXPolicyNode = RFC3280CertPathUtilities.processCertE(certPath, n2, pKIXPolicyNode);
            RFC3280CertPathUtilities.processCertF(certPath, n2, pKIXPolicyNode, n3);
            if (n7 == n) continue;
            if (x509Certificate2 != null && x509Certificate2.getVersion() == 1) {
                if (n7 == 1 && x509Certificate2.equals(trustAnchor.getTrustedCert())) continue;
                throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, n2);
            }
            RFC3280CertPathUtilities.prepareNextCertA(certPath, n2);
            pKIXPolicyNode = RFC3280CertPathUtilities.prepareCertB(certPath, n2, listArray, pKIXPolicyNode, n5);
            RFC3280CertPathUtilities.prepareNextCertG(certPath, n2, pKIXNameConstraintValidator);
            n3 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, n2, n3);
            n5 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, n2, n5);
            n4 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, n2, n4);
            n3 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, n2, n3);
            n5 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, n2, n5);
            n4 = RFC3280CertPathUtilities.prepareNextCertJ(certPath, n2, n4);
            RFC3280CertPathUtilities.prepareNextCertK(certPath, n2);
            n6 = RFC3280CertPathUtilities.prepareNextCertL(certPath, n2, n6);
            n6 = RFC3280CertPathUtilities.prepareNextCertM(certPath, n2, n6);
            RFC3280CertPathUtilities.prepareNextCertN(certPath, n2);
            hashSet = x509Certificate2.getCriticalExtensionOIDs();
            if (hashSet != null) {
                hashSet = new HashSet(hashSet);
                hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
            } else {
                hashSet = new HashSet();
            }
            RFC3280CertPathUtilities.prepareNextCertO(certPath, n2, hashSet, list);
            x509Certificate = x509Certificate2;
            x500Name = PrincipalUtils.getSubjectPrincipal(x509Certificate);
            try {
                publicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), n2, this.helper);
            } catch (CertPathValidatorException certPathValidatorException) {
                throw new CertPathValidatorException("Next working key could not be retrieved.", (Throwable)certPathValidatorException, certPath, n2);
            }
            algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(publicKey);
            aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
            aSN1Encodable = algorithmIdentifier.getParameters();
        }
        n3 = RFC3280CertPathUtilities.wrapupCertA(n3, x509Certificate2);
        n3 = RFC3280CertPathUtilities.wrapupCertB(certPath, n2 + 1, n3);
        Set<String> set2 = x509Certificate2.getCriticalExtensionOIDs();
        if (set2 != null) {
            set2 = new HashSet<String>(set2);
            set2.remove(RFC3280CertPathUtilities.KEY_USAGE);
            set2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
            set2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
            set2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
            set2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
            set2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
            set2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
            set2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
            set2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
            set2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
            set2.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
            set2.remove(Extension.extendedKeyUsage.getId());
        } else {
            set2 = new HashSet<String>();
        }
        RFC3280CertPathUtilities.wrapupCertF(certPath, n2 + 1, list, set2);
        hashSet = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters, set, n2 + 1, listArray, pKIXPolicyNode, hashSet3);
        if (n3 > 0 || hashSet != null) {
            return new PKIXCertPathValidatorResult(trustAnchor, (PolicyNode)((Object)hashSet), x509Certificate2.getPublicKey());
        }
        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, n2);
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (CertificateEncodingException certificateEncodingException) {
            throw new AnnotatedException("unable to process TBSCertificate");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new AnnotatedException(illegalArgumentException.getMessage());
        }
    }
}

