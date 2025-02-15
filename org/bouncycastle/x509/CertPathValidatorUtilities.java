/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyQualifierInfo;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.isismtt.ISISMTTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.CertStatus;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.PKIXCRLUtil;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509Store;
import org.bouncycastle.x509.X509StoreParameters;

class CertPathValidatorUtilities {
    protected static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    protected static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    protected static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    protected static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    protected static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    protected static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    protected static final String KEY_USAGE = Extension.keyUsage.getId();
    protected static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    protected static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    protected static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    protected static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    protected static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    protected static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    protected static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    protected static final String ANY_POLICY = "2.5.29.32.0";
    protected static final String CRL_NUMBER = Extension.cRLNumber.getId();
    protected static final int KEY_CERT_SIGN = 5;
    protected static final int CRL_SIGN = 6;
    protected static final String[] crlReasons = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    CertPathValidatorUtilities() {
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate x509Certificate, Set set) throws AnnotatedException {
        return CertPathValidatorUtilities.findTrustAnchor(x509Certificate, set, null);
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate x509Certificate, Set set, String string) throws AnnotatedException {
        TrustAnchor trustAnchor = null;
        PublicKey publicKey = null;
        Exception exception = null;
        X509CertSelector x509CertSelector = new X509CertSelector();
        X500Principal x500Principal = CertPathValidatorUtilities.getEncodedIssuerPrincipal(x509Certificate);
        try {
            x509CertSelector.setSubject(x500Principal.getEncoded());
        } catch (IOException iOException) {
            throw new AnnotatedException("Cannot set subject search criteria for trust anchor.", iOException);
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext() && trustAnchor == null) {
            block15: {
                trustAnchor = (TrustAnchor)iterator.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        publicKey = trustAnchor.getTrustedCert().getPublicKey();
                    } else {
                        trustAnchor = null;
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null) {
                    try {
                        X500Principal x500Principal2 = new X500Principal(trustAnchor.getCAName());
                        if (x500Principal.equals(x500Principal2)) {
                            publicKey = trustAnchor.getCAPublicKey();
                            break block15;
                        }
                        trustAnchor = null;
                    } catch (IllegalArgumentException illegalArgumentException) {
                        trustAnchor = null;
                    }
                } else {
                    trustAnchor = null;
                }
            }
            if (publicKey == null) continue;
            try {
                CertPathValidatorUtilities.verifyX509Certificate(x509Certificate, publicKey, string);
            } catch (Exception exception2) {
                exception = exception2;
                trustAnchor = null;
                publicKey = null;
            }
        }
        if (trustAnchor == null && exception != null) {
            throw new AnnotatedException("TrustAnchor found but certificate validation failed.", exception);
        }
        return trustAnchor;
    }

    protected static void addAdditionalStoresFromAltNames(X509Certificate x509Certificate, ExtendedPKIXParameters extendedPKIXParameters) throws CertificateParsingException {
        if (x509Certificate.getIssuerAlternativeNames() != null) {
            for (List<?> list : x509Certificate.getIssuerAlternativeNames()) {
                if (!list.get(0).equals(Integers.valueOf(6))) continue;
                String string = (String)list.get(1);
                CertPathValidatorUtilities.addAdditionalStoreFromLocation(string, extendedPKIXParameters);
            }
        }
    }

    protected static X500Principal getEncodedIssuerPrincipal(Object object) {
        if (object instanceof X509Certificate) {
            return ((X509Certificate)object).getIssuerX500Principal();
        }
        return (X500Principal)((X509AttributeCertificate)object).getIssuer().getPrincipals()[0];
    }

    protected static Date getValidDate(PKIXParameters pKIXParameters) {
        Date date = pKIXParameters.getDate();
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    protected static X500Principal getSubjectPrincipal(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectX500Principal();
    }

    protected static boolean isSelfIssued(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectDN().equals(x509Certificate.getIssuerDN());
    }

    protected static ASN1Primitive getExtensionValue(X509Extension x509Extension, String string) throws AnnotatedException {
        byte[] byArray = x509Extension.getExtensionValue(string);
        if (byArray == null) {
            return null;
        }
        return CertPathValidatorUtilities.getObject(string, byArray);
    }

    private static ASN1Primitive getObject(String string, byte[] byArray) throws AnnotatedException {
        try {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
            ASN1OctetString aSN1OctetString = (ASN1OctetString)aSN1InputStream.readObject();
            aSN1InputStream = new ASN1InputStream(aSN1OctetString.getOctets());
            return aSN1InputStream.readObject();
        } catch (Exception exception) {
            throw new AnnotatedException("exception processing extension " + string, exception);
        }
    }

    protected static X500Principal getIssuerPrincipal(X509CRL x509CRL) {
        return x509CRL.getIssuerX500Principal();
    }

    protected static AlgorithmIdentifier getAlgorithmIdentifier(PublicKey publicKey) throws CertPathValidatorException {
        try {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(publicKey.getEncoded());
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(aSN1InputStream.readObject());
            return subjectPublicKeyInfo.getAlgorithmId();
        } catch (Exception exception) {
            throw new ExtCertPathValidatorException("Subject public key cannot be decoded.", exception);
        }
    }

    protected static final Set getQualifierSet(ASN1Sequence aSN1Sequence) throws CertPathValidatorException {
        HashSet<PolicyQualifierInfo> hashSet = new HashSet<PolicyQualifierInfo>();
        if (aSN1Sequence == null) {
            return hashSet;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            try {
                aSN1OutputStream.writeObject((ASN1Encodable)enumeration.nextElement());
                hashSet.add(new PolicyQualifierInfo(byteArrayOutputStream.toByteArray()));
            } catch (IOException iOException) {
                throw new ExtCertPathValidatorException("Policy qualifier info cannot be decoded.", iOException);
            }
            byteArrayOutputStream.reset();
        }
        return hashSet;
    }

    protected static PKIXPolicyNode removePolicyNode(PKIXPolicyNode pKIXPolicyNode, List[] listArray, PKIXPolicyNode pKIXPolicyNode2) {
        PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode)pKIXPolicyNode2.getParent();
        if (pKIXPolicyNode == null) {
            return null;
        }
        if (pKIXPolicyNode3 == null) {
            for (int i = 0; i < listArray.length; ++i) {
                listArray[i] = new ArrayList();
            }
            return null;
        }
        pKIXPolicyNode3.removeChild(pKIXPolicyNode2);
        CertPathValidatorUtilities.removePolicyNodeRecurse(listArray, pKIXPolicyNode2);
        return pKIXPolicyNode;
    }

    private static void removePolicyNodeRecurse(List[] listArray, PKIXPolicyNode pKIXPolicyNode) {
        listArray[pKIXPolicyNode.getDepth()].remove(pKIXPolicyNode);
        if (pKIXPolicyNode.hasChildren()) {
            Iterator iterator = pKIXPolicyNode.getChildren();
            while (iterator.hasNext()) {
                PKIXPolicyNode pKIXPolicyNode2 = (PKIXPolicyNode)iterator.next();
                CertPathValidatorUtilities.removePolicyNodeRecurse(listArray, pKIXPolicyNode2);
            }
        }
    }

    protected static boolean processCertD1i(int n, List[] listArray, ASN1ObjectIdentifier aSN1ObjectIdentifier, Set set) {
        List list = listArray[n - 1];
        for (int i = 0; i < list.size(); ++i) {
            PKIXPolicyNode pKIXPolicyNode = (PKIXPolicyNode)list.get(i);
            Set set2 = pKIXPolicyNode.getExpectedPolicies();
            if (!set2.contains(aSN1ObjectIdentifier.getId())) continue;
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.add(aSN1ObjectIdentifier.getId());
            PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), n, hashSet, pKIXPolicyNode, set, aSN1ObjectIdentifier.getId(), false);
            pKIXPolicyNode.addChild(pKIXPolicyNode2);
            listArray[n].add(pKIXPolicyNode2);
            return true;
        }
        return false;
    }

    protected static void processCertD1ii(int n, List[] listArray, ASN1ObjectIdentifier aSN1ObjectIdentifier, Set set) {
        List list = listArray[n - 1];
        for (int i = 0; i < list.size(); ++i) {
            PKIXPolicyNode pKIXPolicyNode = (PKIXPolicyNode)list.get(i);
            if (!ANY_POLICY.equals(pKIXPolicyNode.getValidPolicy())) continue;
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.add(aSN1ObjectIdentifier.getId());
            PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), n, hashSet, pKIXPolicyNode, set, aSN1ObjectIdentifier.getId(), false);
            pKIXPolicyNode.addChild(pKIXPolicyNode2);
            listArray[n].add(pKIXPolicyNode2);
            return;
        }
    }

    protected static void prepareNextCertB1(int n, List[] listArray, String string, Map map, X509Certificate x509Certificate) throws AnnotatedException, CertPathValidatorException {
        boolean bl = false;
        for (PKIXPolicyNode pKIXPolicyNode : listArray[n]) {
            if (!pKIXPolicyNode.getValidPolicy().equals(string)) continue;
            bl = true;
            pKIXPolicyNode.setExpectedPolicies((Set)map.get(string));
            break;
        }
        if (!bl) {
            for (PKIXPolicyNode pKIXPolicyNode : listArray[n]) {
                PKIXPolicyNode pKIXPolicyNode2;
                if (!ANY_POLICY.equals(pKIXPolicyNode.getValidPolicy())) continue;
                Set set = null;
                ASN1Sequence aSN1Sequence = null;
                try {
                    aSN1Sequence = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CERTIFICATE_POLICIES));
                } catch (Exception exception) {
                    throw new AnnotatedException("Certificate policies cannot be decoded.", exception);
                }
                Enumeration enumeration = aSN1Sequence.getObjects();
                while (enumeration.hasMoreElements()) {
                    PolicyInformation policyInformation = null;
                    try {
                        policyInformation = PolicyInformation.getInstance(enumeration.nextElement());
                    } catch (Exception exception) {
                        throw new AnnotatedException("Policy information cannot be decoded.", exception);
                    }
                    if (!ANY_POLICY.equals(policyInformation.getPolicyIdentifier().getId())) continue;
                    try {
                        set = CertPathValidatorUtilities.getQualifierSet(policyInformation.getPolicyQualifiers());
                        break;
                    } catch (CertPathValidatorException certPathValidatorException) {
                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be built.", certPathValidatorException);
                    }
                }
                boolean bl2 = false;
                if (x509Certificate.getCriticalExtensionOIDs() != null) {
                    bl2 = x509Certificate.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
                }
                if (!ANY_POLICY.equals((pKIXPolicyNode2 = (PKIXPolicyNode)pKIXPolicyNode.getParent()).getValidPolicy())) break;
                PKIXPolicyNode pKIXPolicyNode3 = new PKIXPolicyNode(new ArrayList(), n, (Set)map.get(string), pKIXPolicyNode2, set, string, bl2);
                pKIXPolicyNode2.addChild(pKIXPolicyNode3);
                listArray[n].add(pKIXPolicyNode3);
                break;
            }
        }
    }

    protected static PKIXPolicyNode prepareNextCertB2(int n, List[] listArray, String string, PKIXPolicyNode pKIXPolicyNode) {
        Iterator iterator = listArray[n].iterator();
        while (iterator.hasNext()) {
            PKIXPolicyNode pKIXPolicyNode2 = (PKIXPolicyNode)iterator.next();
            if (!pKIXPolicyNode2.getValidPolicy().equals(string)) continue;
            PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode)pKIXPolicyNode2.getParent();
            pKIXPolicyNode3.removeChild(pKIXPolicyNode2);
            iterator.remove();
            for (int i = n - 1; i >= 0; --i) {
                PKIXPolicyNode pKIXPolicyNode4;
                List list = listArray[i];
                for (int j = 0; j < list.size() && ((pKIXPolicyNode4 = (PKIXPolicyNode)list.get(j)).hasChildren() || (pKIXPolicyNode = CertPathValidatorUtilities.removePolicyNode(pKIXPolicyNode, listArray, pKIXPolicyNode4)) != null); ++j) {
                }
            }
        }
        return pKIXPolicyNode;
    }

    protected static boolean isAnyPolicy(Set set) {
        return set == null || set.contains(ANY_POLICY) || set.isEmpty();
    }

    protected static void addAdditionalStoreFromLocation(String string, ExtendedPKIXParameters extendedPKIXParameters) {
        if (extendedPKIXParameters.isAdditionalLocationsEnabled()) {
            try {
                if (string.startsWith("ldap://")) {
                    string = string.substring(7);
                    String string2 = null;
                    String string3 = null;
                    if (string.indexOf("/") != -1) {
                        string2 = string.substring(string.indexOf("/"));
                        string3 = "ldap://" + string.substring(0, string.indexOf("/"));
                    } else {
                        string3 = "ldap://" + string;
                    }
                    X509LDAPCertStoreParameters x509LDAPCertStoreParameters = new X509LDAPCertStoreParameters.Builder(string3, string2).build();
                    extendedPKIXParameters.addAdditionalStore(X509Store.getInstance("CERTIFICATE/LDAP", (X509StoreParameters)x509LDAPCertStoreParameters, "BC"));
                    extendedPKIXParameters.addAdditionalStore(X509Store.getInstance("CRL/LDAP", (X509StoreParameters)x509LDAPCertStoreParameters, "BC"));
                    extendedPKIXParameters.addAdditionalStore(X509Store.getInstance("ATTRIBUTECERTIFICATE/LDAP", (X509StoreParameters)x509LDAPCertStoreParameters, "BC"));
                    extendedPKIXParameters.addAdditionalStore(X509Store.getInstance("CERTIFICATEPAIR/LDAP", (X509StoreParameters)x509LDAPCertStoreParameters, "BC"));
                }
            } catch (Exception exception) {
                throw new RuntimeException("Exception adding X.509 stores.");
            }
        }
    }

    protected static Collection findCertificates(X509CertStoreSelector x509CertStoreSelector, List list) throws AnnotatedException {
        HashSet<? extends Certificate> hashSet = new HashSet<Certificate>();
        Iterator iterator = list.iterator();
        CertificateFactory certificateFactory = new CertificateFactory();
        while (iterator.hasNext()) {
            Object object;
            Object e = iterator.next();
            if (e instanceof Store) {
                object = (Store)e;
                try {
                    for (Object t : object.getMatches(x509CertStoreSelector)) {
                        if (t instanceof Encodable) {
                            hashSet.add(certificateFactory.engineGenerateCertificate(new ByteArrayInputStream(((Encodable)t).getEncoded())));
                            continue;
                        }
                        if (t instanceof Certificate) {
                            hashSet.add((Certificate)t);
                            continue;
                        }
                        throw new AnnotatedException("Unknown object found in certificate store.");
                    }
                    continue;
                } catch (StoreException storeException) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", storeException);
                } catch (IOException iOException) {
                    throw new AnnotatedException("Problem while extracting certificates from X.509 store.", iOException);
                } catch (CertificateException certificateException) {
                    throw new AnnotatedException("Problem while extracting certificates from X.509 store.", certificateException);
                }
            }
            object = (CertStore)e;
            try {
                hashSet.addAll(((CertStore)object).getCertificates(x509CertStoreSelector));
            } catch (CertStoreException certStoreException) {
                throw new AnnotatedException("Problem while picking certificates from certificate store.", certStoreException);
            }
        }
        return hashSet;
    }

    protected static Collection findCertificates(PKIXCertStoreSelector pKIXCertStoreSelector, List list) throws AnnotatedException {
        HashSet<Object> hashSet = new HashSet<Object>();
        for (Object e : list) {
            Object object;
            if (e instanceof Store) {
                object = (Store)e;
                try {
                    hashSet.addAll(object.getMatches(pKIXCertStoreSelector));
                    continue;
                } catch (StoreException storeException) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", storeException);
                }
            }
            object = (CertStore)e;
            try {
                hashSet.addAll(PKIXCertStoreSelector.getCertificates(pKIXCertStoreSelector, (CertStore)object));
            } catch (CertStoreException certStoreException) {
                throw new AnnotatedException("Problem while picking certificates from certificate store.", certStoreException);
            }
        }
        return hashSet;
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, List list) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        for (Object e : list) {
            if (!(e instanceof X509Store)) continue;
            X509Store x509Store = (X509Store)e;
            try {
                hashSet.addAll(x509Store.getMatches((Selector)x509AttributeCertStoreSelector));
            } catch (StoreException storeException) {
                throw new AnnotatedException("Problem while picking certificates from X.509 store.", storeException);
            }
        }
        return hashSet;
    }

    protected static void addAdditionalStoresFromCRLDistributionPoint(CRLDistPoint cRLDistPoint, ExtendedPKIXParameters extendedPKIXParameters) throws AnnotatedException {
        if (cRLDistPoint != null) {
            DistributionPoint[] distributionPointArray = null;
            try {
                distributionPointArray = cRLDistPoint.getDistributionPoints();
            } catch (Exception exception) {
                throw new AnnotatedException("Distribution points could not be read.", exception);
            }
            for (int i = 0; i < distributionPointArray.length; ++i) {
                DistributionPointName distributionPointName = distributionPointArray[i].getDistributionPoint();
                if (distributionPointName == null || distributionPointName.getType() != 0) continue;
                GeneralName[] generalNameArray = GeneralNames.getInstance(distributionPointName.getName()).getNames();
                for (int j = 0; j < generalNameArray.length; ++j) {
                    if (generalNameArray[j].getTagNo() != 6) continue;
                    String string = DERIA5String.getInstance(generalNameArray[j].getName()).getString();
                    CertPathValidatorUtilities.addAdditionalStoreFromLocation(string, extendedPKIXParameters);
                }
            }
        }
    }

    protected static void getCRLIssuersFromDistributionPoint(DistributionPoint distributionPoint, Collection collection, X509CRLSelector x509CRLSelector, ExtendedPKIXParameters extendedPKIXParameters) throws AnnotatedException {
        Object object;
        ArrayList<X500Principal> arrayList = new ArrayList<X500Principal>();
        if (distributionPoint.getCRLIssuer() != null) {
            object = distributionPoint.getCRLIssuer().getNames();
            for (int i = 0; i < ((Object)object).length; ++i) {
                if (((GeneralName)object[i]).getTagNo() != 4) continue;
                try {
                    arrayList.add(new X500Principal(((GeneralName)object[i]).getName().toASN1Primitive().getEncoded()));
                    continue;
                } catch (IOException iOException) {
                    throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", iOException);
                }
            }
        } else {
            if (distributionPoint.getDistributionPoint() == null) {
                throw new AnnotatedException("CRL issuer is omitted from distribution point but no distributionPoint field present.");
            }
            object = collection.iterator();
            while (object.hasNext()) {
                arrayList.add((X500Principal)object.next());
            }
        }
        object = arrayList.iterator();
        while (object.hasNext()) {
            try {
                x509CRLSelector.addIssuerName(((X500Principal)object.next()).getEncoded());
            } catch (IOException iOException) {
                throw new AnnotatedException("Cannot decode CRL issuer information.", iOException);
            }
        }
    }

    private static BigInteger getSerialNumber(Object object) {
        if (object instanceof X509Certificate) {
            return ((X509Certificate)object).getSerialNumber();
        }
        return ((X509AttributeCertificate)object).getSerialNumber();
    }

    protected static void getCertStatus(Date date, X509CRL x509CRL, Object object, CertStatus certStatus) throws AnnotatedException {
        Object object2;
        boolean bl;
        X509CRLEntry x509CRLEntry = null;
        try {
            bl = CertPathValidatorUtilities.isIndirectCRL(x509CRL);
        } catch (CRLException cRLException) {
            throw new AnnotatedException("Failed check for indirect CRL.", cRLException);
        }
        if (bl) {
            x509CRLEntry = x509CRL.getRevokedCertificate(CertPathValidatorUtilities.getSerialNumber(object));
            if (x509CRLEntry == null) {
                return;
            }
            object2 = x509CRLEntry.getCertificateIssuer();
            if (object2 == null) {
                object2 = CertPathValidatorUtilities.getIssuerPrincipal(x509CRL);
            }
            if (!CertPathValidatorUtilities.getEncodedIssuerPrincipal(object).equals(object2)) {
                return;
            }
        } else {
            if (!CertPathValidatorUtilities.getEncodedIssuerPrincipal(object).equals(CertPathValidatorUtilities.getIssuerPrincipal(x509CRL))) {
                return;
            }
            x509CRLEntry = x509CRL.getRevokedCertificate(CertPathValidatorUtilities.getSerialNumber(object));
            if (x509CRLEntry == null) {
                return;
            }
        }
        object2 = null;
        if (x509CRLEntry.hasExtensions()) {
            try {
                object2 = ASN1Enumerated.getInstance(CertPathValidatorUtilities.getExtensionValue(x509CRLEntry, org.bouncycastle.asn1.x509.X509Extension.reasonCode.getId()));
            } catch (Exception exception) {
                throw new AnnotatedException("Reason code CRL entry extension could not be decoded.", exception);
            }
        }
        if (date.getTime() >= x509CRLEntry.getRevocationDate().getTime() || object2 == null || ((ASN1Enumerated)object2).getValue().intValue() == 0 || ((ASN1Enumerated)object2).getValue().intValue() == 1 || ((ASN1Enumerated)object2).getValue().intValue() == 2 || ((ASN1Enumerated)object2).getValue().intValue() == 8) {
            if (object2 != null) {
                certStatus.setCertStatus(((ASN1Enumerated)object2).getValue().intValue());
            } else {
                certStatus.setCertStatus(0);
            }
            certStatus.setRevocationDate(x509CRLEntry.getRevocationDate());
        }
    }

    protected static Set getDeltaCRLs(Date date, ExtendedPKIXParameters extendedPKIXParameters, X509CRL x509CRL) throws AnnotatedException {
        Object object;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            x509CRLStoreSelector.addIssuerName(CertPathValidatorUtilities.getIssuerPrincipal(x509CRL).getEncoded());
        } catch (IOException iOException) {
            throw new AnnotatedException("Cannot extract issuer from CRL.", iOException);
        }
        BigInteger bigInteger = null;
        try {
            object = CertPathValidatorUtilities.getExtensionValue(x509CRL, CRL_NUMBER);
            if (object != null) {
                bigInteger = ASN1Integer.getInstance(object).getPositiveValue();
            }
        } catch (Exception exception) {
            throw new AnnotatedException("CRL number extension could not be extracted from CRL.", exception);
        }
        object = null;
        try {
            object = x509CRL.getExtensionValue(ISSUING_DISTRIBUTION_POINT);
        } catch (Exception exception) {
            throw new AnnotatedException("Issuing distribution point extension value could not be read.", exception);
        }
        x509CRLStoreSelector.setMinCRLNumber(bigInteger == null ? null : bigInteger.add(BigInteger.valueOf(1L)));
        x509CRLStoreSelector.setIssuingDistributionPoint((byte[])object);
        x509CRLStoreSelector.setIssuingDistributionPointEnabled(true);
        x509CRLStoreSelector.setMaxBaseCRLNumber(bigInteger);
        Set set = CRL_UTIL.findCRLs(x509CRLStoreSelector, extendedPKIXParameters, date);
        HashSet<X509CRL> hashSet = new HashSet<X509CRL>();
        for (X509CRL x509CRL2 : set) {
            if (!CertPathValidatorUtilities.isDeltaCRL(x509CRL2)) continue;
            hashSet.add(x509CRL2);
        }
        return hashSet;
    }

    private static boolean isDeltaCRL(X509CRL x509CRL) {
        Set<String> set = x509CRL.getCriticalExtensionOIDs();
        if (set == null) {
            return false;
        }
        return set.contains(Extension.deltaCRLIndicator.getId());
    }

    protected static Set getCompleteCRLs(DistributionPoint distributionPoint, Object object, Date date, ExtendedPKIXParameters extendedPKIXParameters) throws AnnotatedException {
        Set set;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            set = new HashSet<Principal>();
            if (object instanceof X509AttributeCertificate) {
                set.add(((X509AttributeCertificate)object).getIssuer().getPrincipals()[0]);
            } else {
                set.add(CertPathValidatorUtilities.getEncodedIssuerPrincipal(object));
            }
            CertPathValidatorUtilities.getCRLIssuersFromDistributionPoint(distributionPoint, set, x509CRLStoreSelector, extendedPKIXParameters);
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Could not get issuer information from distribution point.", annotatedException);
        }
        if (object instanceof X509Certificate) {
            x509CRLStoreSelector.setCertificateChecking((X509Certificate)object);
        } else if (object instanceof X509AttributeCertificate) {
            x509CRLStoreSelector.setAttrCertificateChecking((X509AttributeCertificate)object);
        }
        x509CRLStoreSelector.setCompleteCRLEnabled(true);
        set = CRL_UTIL.findCRLs(x509CRLStoreSelector, extendedPKIXParameters, date);
        if (set.isEmpty()) {
            if (object instanceof X509AttributeCertificate) {
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate)object;
                throw new AnnotatedException("No CRLs found for issuer \"" + x509AttributeCertificate.getIssuer().getPrincipals()[0] + "\"");
            }
            X509Certificate x509Certificate = (X509Certificate)object;
            throw new AnnotatedException("No CRLs found for issuer \"" + x509Certificate.getIssuerX500Principal() + "\"");
        }
        return set;
    }

    protected static Date getValidCertDateFromValidityModel(ExtendedPKIXParameters extendedPKIXParameters, CertPath certPath, int n) throws AnnotatedException {
        if (extendedPKIXParameters.getValidityModel() == 1) {
            if (n <= 0) {
                return CertPathValidatorUtilities.getValidDate(extendedPKIXParameters);
            }
            if (n - 1 == 0) {
                ASN1GeneralizedTime aSN1GeneralizedTime = null;
                try {
                    byte[] byArray = ((X509Certificate)certPath.getCertificates().get(n - 1)).getExtensionValue(ISISMTTObjectIdentifiers.id_isismtt_at_dateOfCertGen.getId());
                    if (byArray != null) {
                        aSN1GeneralizedTime = ASN1GeneralizedTime.getInstance(ASN1Primitive.fromByteArray(byArray));
                    }
                } catch (IOException iOException) {
                    throw new AnnotatedException("Date of cert gen extension could not be read.");
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new AnnotatedException("Date of cert gen extension could not be read.");
                }
                if (aSN1GeneralizedTime != null) {
                    try {
                        return aSN1GeneralizedTime.getDate();
                    } catch (ParseException parseException) {
                        throw new AnnotatedException("Date from date of cert gen extension could not be parsed.", parseException);
                    }
                }
                return ((X509Certificate)certPath.getCertificates().get(n - 1)).getNotBefore();
            }
            return ((X509Certificate)certPath.getCertificates().get(n - 1)).getNotBefore();
        }
        return CertPathValidatorUtilities.getValidDate(extendedPKIXParameters);
    }

    protected static PublicKey getNextWorkingKey(List list, int n) throws CertPathValidatorException {
        Certificate certificate = (Certificate)list.get(n);
        PublicKey publicKey = certificate.getPublicKey();
        if (!(publicKey instanceof DSAPublicKey)) {
            return publicKey;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey)publicKey;
        if (dSAPublicKey.getParams() != null) {
            return dSAPublicKey;
        }
        for (int i = n + 1; i < list.size(); ++i) {
            X509Certificate x509Certificate = (X509Certificate)list.get(i);
            publicKey = x509Certificate.getPublicKey();
            if (!(publicKey instanceof DSAPublicKey)) {
                throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
            }
            DSAPublicKey dSAPublicKey2 = (DSAPublicKey)publicKey;
            if (dSAPublicKey2.getParams() == null) continue;
            DSAParams dSAParams = dSAPublicKey2.getParams();
            DSAPublicKeySpec dSAPublicKeySpec = new DSAPublicKeySpec(dSAPublicKey.getY(), dSAParams.getP(), dSAParams.getQ(), dSAParams.getG());
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "BC");
                return keyFactory.generatePublic(dSAPublicKeySpec);
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
        }
        throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
    }

    static Collection findIssuerCerts(X509Certificate x509Certificate, List list, List list2) throws AnnotatedException {
        Iterator iterator;
        Serializable serializable;
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(x509Certificate.getIssuerX500Principal().getEncoded());
        } catch (IOException iOException) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate could not be set.", iOException);
        }
        PKIXCertStoreSelector<? extends Certificate> pKIXCertStoreSelector = new PKIXCertStoreSelector.Builder(x509CertSelector).build();
        HashSet hashSet = new HashSet();
        try {
            serializable = new ArrayList();
            serializable.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector, list));
            serializable.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector, list2));
            iterator = serializable.iterator();
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Issuer certificate cannot be searched.", annotatedException);
        }
        serializable = null;
        while (iterator.hasNext()) {
            serializable = (X509Certificate)iterator.next();
            hashSet.add(serializable);
        }
        return hashSet;
    }

    protected static void verifyX509Certificate(X509Certificate x509Certificate, PublicKey publicKey, String string) throws GeneralSecurityException {
        if (string == null) {
            x509Certificate.verify(publicKey);
        } else {
            x509Certificate.verify(publicKey, string);
        }
    }

    static boolean isIndirectCRL(X509CRL x509CRL) throws CRLException {
        try {
            byte[] byArray = x509CRL.getExtensionValue(Extension.issuingDistributionPoint.getId());
            return byArray != null && IssuingDistributionPoint.getInstance(ASN1OctetString.getInstance(byArray).getOctets()).isIndirectCRL();
        } catch (Exception exception) {
            throw new CRLException("Exception reading IssuingDistributionPoint: " + exception);
        }
    }
}

