/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.jce.provider.X509AttrCertParser;
import org.bouncycastle.jce.provider.X509CRLParser;
import org.bouncycastle.jce.provider.X509CertPairParser;
import org.bouncycastle.jce.provider.X509CertParser;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;
import org.bouncycastle.x509.util.StreamParsingException;

public class LDAPStoreHelper {
    private X509LDAPCertStoreParameters params;
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private Map cacheMap = new HashMap(cacheSize);
    private static int cacheSize = 32;
    private static long lifeTime = 60000L;

    public LDAPStoreHelper(X509LDAPCertStoreParameters x509LDAPCertStoreParameters) {
        this.params = x509LDAPCertStoreParameters;
    }

    private DirContext connectLDAP() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", LDAP_PROVIDER);
        properties.setProperty("java.naming.batchsize", "0");
        properties.setProperty("java.naming.provider.url", this.params.getLdapURL());
        properties.setProperty("java.naming.factory.url.pkgs", URL_CONTEXT_PREFIX);
        properties.setProperty("java.naming.referral", REFERRALS_IGNORE);
        properties.setProperty("java.naming.security.authentication", SEARCH_SECURITY_LEVEL);
        InitialDirContext initialDirContext = new InitialDirContext(properties);
        return initialDirContext;
    }

    private String parseDN(String string, String string2) {
        String string3 = string;
        int n = string3.toLowerCase().indexOf(string2.toLowerCase() + "=");
        if (n == -1) {
            return "";
        }
        int n2 = (string3 = string3.substring(n + string2.length())).indexOf(44);
        if (n2 == -1) {
            n2 = string3.length();
        }
        while (string3.charAt(n2 - 1) == '\\') {
            if ((n2 = string3.indexOf(44, n2 + 1)) != -1) continue;
            n2 = string3.length();
        }
        string3 = string3.substring(0, n2);
        n = string3.indexOf(61);
        if ((string3 = string3.substring(n + 1)).charAt(0) == ' ') {
            string3 = string3.substring(1);
        }
        if (string3.startsWith("\"")) {
            string3 = string3.substring(1);
        }
        if (string3.endsWith("\"")) {
            string3 = string3.substring(0, string3.length() - 1);
        }
        return string3;
    }

    private Set createCerts(List list, X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet<X509Certificate> hashSet = new HashSet<X509Certificate>();
        Iterator iterator = list.iterator();
        X509CertParser x509CertParser = new X509CertParser();
        while (iterator.hasNext()) {
            try {
                x509CertParser.engineInit(new ByteArrayInputStream((byte[])iterator.next()));
                X509Certificate x509Certificate = (X509Certificate)x509CertParser.engineRead();
                if (!x509CertStoreSelector.match((Object)x509Certificate)) continue;
                hashSet.add(x509Certificate);
            } catch (Exception exception) {}
        }
        return hashSet;
    }

    private List certSubjectSerialSearch(X509CertStoreSelector x509CertStoreSelector, String[] stringArray, String[] stringArray2, String[] stringArray3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String string = null;
        String string2 = null;
        string = this.getSubjectAsString(x509CertStoreSelector);
        if (x509CertStoreSelector.getSerialNumber() != null) {
            string2 = x509CertStoreSelector.getSerialNumber().toString();
        }
        if (x509CertStoreSelector.getCertificate() != null) {
            string = x509CertStoreSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            string2 = x509CertStoreSelector.getCertificate().getSerialNumber().toString();
        }
        String string3 = null;
        if (string != null) {
            for (int i = 0; i < stringArray3.length; ++i) {
                string3 = this.parseDN(string, stringArray3[i]);
                arrayList.addAll(this.search(stringArray2, "*" + string3 + "*", stringArray));
            }
        }
        if (string2 != null && this.params.getSearchForSerialNumberIn() != null) {
            string3 = string2;
            arrayList.addAll(this.search(this.splitString(this.params.getSearchForSerialNumberIn()), string3, stringArray));
        }
        if (string2 == null && string == null) {
            arrayList.addAll(this.search(stringArray2, "*", stringArray));
        }
        return arrayList;
    }

    private List crossCertificatePairSubjectSearch(X509CertPairStoreSelector x509CertPairStoreSelector, String[] stringArray, String[] stringArray2, String[] stringArray3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String string = null;
        if (x509CertPairStoreSelector.getForwardSelector() != null) {
            string = this.getSubjectAsString(x509CertPairStoreSelector.getForwardSelector());
        }
        if (x509CertPairStoreSelector.getCertPair() != null && x509CertPairStoreSelector.getCertPair().getForward() != null) {
            string = x509CertPairStoreSelector.getCertPair().getForward().getSubjectX500Principal().getName("RFC1779");
        }
        String string2 = null;
        if (string != null) {
            for (int i = 0; i < stringArray3.length; ++i) {
                string2 = this.parseDN(string, stringArray3[i]);
                arrayList.addAll(this.search(stringArray2, "*" + string2 + "*", stringArray));
            }
        }
        if (string == null) {
            arrayList.addAll(this.search(stringArray2, "*", stringArray));
        }
        return arrayList;
    }

    private List attrCertSubjectSerialSearch(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, String[] stringArray, String[] stringArray2, String[] stringArray3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String string = null;
        String string22 = null;
        HashSet<String> hashSet = new HashSet<String>();
        Principal[] principalArray = null;
        if (x509AttributeCertStoreSelector.getHolder() != null) {
            if (x509AttributeCertStoreSelector.getHolder().getSerialNumber() != null) {
                hashSet.add(x509AttributeCertStoreSelector.getHolder().getSerialNumber().toString());
            }
            if (x509AttributeCertStoreSelector.getHolder().getEntityNames() != null) {
                principalArray = x509AttributeCertStoreSelector.getHolder().getEntityNames();
            }
        }
        if (x509AttributeCertStoreSelector.getAttributeCert() != null) {
            if (x509AttributeCertStoreSelector.getAttributeCert().getHolder().getEntityNames() != null) {
                principalArray = x509AttributeCertStoreSelector.getAttributeCert().getHolder().getEntityNames();
            }
            hashSet.add(x509AttributeCertStoreSelector.getAttributeCert().getSerialNumber().toString());
        }
        if (principalArray != null) {
            string = principalArray[0] instanceof X500Principal ? ((X500Principal)principalArray[0]).getName("RFC1779") : principalArray[0].getName();
        }
        if (x509AttributeCertStoreSelector.getSerialNumber() != null) {
            hashSet.add(x509AttributeCertStoreSelector.getSerialNumber().toString());
        }
        String string3 = null;
        if (string != null) {
            for (int i = 0; i < stringArray3.length; ++i) {
                string3 = this.parseDN(string, stringArray3[i]);
                arrayList.addAll(this.search(stringArray2, "*" + string3 + "*", stringArray));
            }
        }
        if (hashSet.size() > 0 && this.params.getSearchForSerialNumberIn() != null) {
            for (String string22 : hashSet) {
                arrayList.addAll(this.search(this.splitString(this.params.getSearchForSerialNumberIn()), string22, stringArray));
            }
        }
        if (hashSet.size() == 0 && string == null) {
            arrayList.addAll(this.search(stringArray2, "*", stringArray));
        }
        return arrayList;
    }

    private List cRLIssuerSearch(X509CRLStoreSelector x509CRLStoreSelector, String[] stringArray, String[] stringArray2, String[] stringArray3) throws StoreException {
        Principal[] principalArray;
        ArrayList arrayList = new ArrayList();
        String string = null;
        HashSet<Principal> hashSet = new HashSet<Principal>();
        if (x509CRLStoreSelector.getIssuers() != null) {
            hashSet.addAll(x509CRLStoreSelector.getIssuers());
        }
        if (x509CRLStoreSelector.getCertificateChecking() != null) {
            hashSet.add(this.getCertificateIssuer(x509CRLStoreSelector.getCertificateChecking()));
        }
        if (x509CRLStoreSelector.getAttrCertificateChecking() != null) {
            principalArray = x509CRLStoreSelector.getAttrCertificateChecking().getIssuer().getPrincipals();
            for (int i = 0; i < principalArray.length; ++i) {
                if (!(principalArray[i] instanceof X500Principal)) continue;
                hashSet.add(principalArray[i]);
            }
        }
        principalArray = hashSet.iterator();
        while (principalArray.hasNext()) {
            string = ((X500Principal)principalArray.next()).getName("RFC1779");
            String string2 = null;
            for (int i = 0; i < stringArray3.length; ++i) {
                string2 = this.parseDN(string, stringArray3[i]);
                arrayList.addAll(this.search(stringArray2, "*" + string2 + "*", stringArray));
            }
        }
        if (string == null) {
            arrayList.addAll(this.search(stringArray2, "*", stringArray));
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private List search(String[] stringArray, String string, String[] stringArray2) throws StoreException {
        ArrayList arrayList;
        String string2 = null;
        if (stringArray == null) {
            string2 = null;
        } else {
            string2 = "";
            if (string.equals("**")) {
                string = "*";
            }
            for (int i = 0; i < stringArray.length; ++i) {
                string2 = string2 + "(" + stringArray[i] + "=" + string + ")";
            }
            string2 = "(|" + string2 + ")";
        }
        String string3 = "";
        for (int i = 0; i < stringArray2.length; ++i) {
            string3 = string3 + "(" + stringArray2[i] + "=*)";
        }
        string3 = "(|" + string3 + ")";
        String string4 = "(&" + string2 + "" + string3 + ")";
        if (string2 == null) {
            string4 = string3;
        }
        if ((arrayList = this.getFromCache(string4)) != null) {
            return arrayList;
        }
        DirContext dirContext = null;
        arrayList = new ArrayList();
        try {
            dirContext = this.connectLDAP();
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(2);
            searchControls.setCountLimit(0L);
            searchControls.setReturningAttributes(stringArray2);
            NamingEnumeration<SearchResult> namingEnumeration = dirContext.search(this.params.getBaseDN(), string4, searchControls);
            while (namingEnumeration.hasMoreElements()) {
                SearchResult searchResult = namingEnumeration.next();
                NamingEnumeration<?> namingEnumeration2 = searchResult.getAttributes().getAll().next().getAll();
                while (namingEnumeration2.hasMore()) {
                    arrayList.add(namingEnumeration2.next());
                }
            }
            this.addToCache(string4, arrayList);
        } catch (NamingException namingException) {
        } finally {
            try {
                if (null != dirContext) {
                    dirContext.close();
                }
            } catch (Exception exception) {}
        }
        return arrayList;
    }

    private Set createCRLs(List list, X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        HashSet<X509CRL> hashSet = new HashSet<X509CRL>();
        X509CRLParser x509CRLParser = new X509CRLParser();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            try {
                x509CRLParser.engineInit(new ByteArrayInputStream((byte[])iterator.next()));
                X509CRL x509CRL = (X509CRL)x509CRLParser.engineRead();
                if (!x509CRLStoreSelector.match((Object)x509CRL)) continue;
                hashSet.add(x509CRL);
            } catch (StreamParsingException streamParsingException) {}
        }
        return hashSet;
    }

    private Set createCrossCertificatePairs(List list, X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        HashSet<X509CertificatePair> hashSet = new HashSet<X509CertificatePair>();
        for (int i = 0; i < list.size(); ++i) {
            try {
                X509CertificatePair x509CertificatePair;
                try {
                    X509CertPairParser x509CertPairParser = new X509CertPairParser();
                    x509CertPairParser.engineInit(new ByteArrayInputStream((byte[])list.get(i)));
                    x509CertificatePair = (X509CertificatePair)x509CertPairParser.engineRead();
                } catch (StreamParsingException streamParsingException) {
                    byte[] byArray = (byte[])list.get(i);
                    byte[] byArray2 = (byte[])list.get(i + 1);
                    x509CertificatePair = new X509CertificatePair(new CertificatePair(Certificate.getInstance(new ASN1InputStream(byArray).readObject()), Certificate.getInstance(new ASN1InputStream(byArray2).readObject())));
                    ++i;
                }
                if (!x509CertPairStoreSelector.match(x509CertificatePair)) continue;
                hashSet.add(x509CertificatePair);
                continue;
            } catch (CertificateParsingException certificateParsingException) {
                continue;
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        return hashSet;
    }

    private Set createAttributeCertificates(List list, X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        HashSet<X509AttributeCertificate> hashSet = new HashSet<X509AttributeCertificate>();
        Iterator iterator = list.iterator();
        X509AttrCertParser x509AttrCertParser = new X509AttrCertParser();
        while (iterator.hasNext()) {
            try {
                x509AttrCertParser.engineInit(new ByteArrayInputStream((byte[])iterator.next()));
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate)x509AttrCertParser.engineRead();
                if (!x509AttributeCertStoreSelector.match(x509AttributeCertificate)) continue;
                hashSet.add(x509AttributeCertificate);
            } catch (StreamParsingException streamParsingException) {}
        }
        return hashSet;
    }

    public Collection getAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAuthorityRevocationListAttribute());
        List list = this.cRLIssuerSearch(x509CRLStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAuthorityRevocationListAttributeName()), stringArray = this.splitString(this.params.getAuthorityRevocationListIssuerAttributeName()));
        Set set = this.createCRLs(list, x509CRLStoreSelector);
        if (set.size() == 0) {
            X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
            list = this.cRLIssuerSearch(x509CRLStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCRLs(list, x509CRLStoreSelector));
        }
        return set;
    }

    public Collection getAttributeCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAttributeCertificateRevocationListAttribute());
        List list = this.cRLIssuerSearch(x509CRLStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAttributeCertificateRevocationListAttributeName()), stringArray = this.splitString(this.params.getAttributeCertificateRevocationListIssuerAttributeName()));
        Set set = this.createCRLs(list, x509CRLStoreSelector);
        if (set.size() == 0) {
            X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
            list = this.cRLIssuerSearch(x509CRLStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCRLs(list, x509CRLStoreSelector));
        }
        return set;
    }

    public Collection getAttributeAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAttributeAuthorityRevocationListAttribute());
        List list = this.cRLIssuerSearch(x509CRLStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAttributeAuthorityRevocationListAttributeName()), stringArray = this.splitString(this.params.getAttributeAuthorityRevocationListIssuerAttributeName()));
        Set set = this.createCRLs(list, x509CRLStoreSelector);
        if (set.size() == 0) {
            X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
            list = this.cRLIssuerSearch(x509CRLStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCRLs(list, x509CRLStoreSelector));
        }
        return set;
    }

    public Collection getCrossCertificatePairs(X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getCrossCertificateAttribute());
        List list = this.crossCertificatePairSubjectSearch(x509CertPairStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapCrossCertificateAttributeName()), stringArray = this.splitString(this.params.getCrossCertificateSubjectAttributeName()));
        Set set = this.createCrossCertificatePairs(list, x509CertPairStoreSelector);
        if (set.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            X509CertPairStoreSelector x509CertPairStoreSelector2 = new X509CertPairStoreSelector();
            x509CertPairStoreSelector2.setForwardSelector(x509CertStoreSelector);
            x509CertPairStoreSelector2.setReverseSelector(x509CertStoreSelector);
            list = this.crossCertificatePairSubjectSearch(x509CertPairStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCrossCertificatePairs(list, x509CertPairStoreSelector));
        }
        return set;
    }

    public Collection getUserCertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getUserCertificateAttribute());
        List list = this.certSubjectSerialSearch(x509CertStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapUserCertificateAttributeName()), stringArray = this.splitString(this.params.getUserCertificateSubjectAttributeName()));
        Set set = this.createCerts(list, x509CertStoreSelector);
        if (set.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector2 = new X509CertStoreSelector();
            list = this.certSubjectSerialSearch(x509CertStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCerts(list, x509CertStoreSelector));
        }
        return set;
    }

    public Collection getAACertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAACertificateAttribute());
        List list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAACertificateAttributeName()), stringArray = this.splitString(this.params.getAACertificateSubjectAttributeName()));
        Set set = this.createAttributeCertificates(list, x509AttributeCertStoreSelector);
        if (set.size() == 0) {
            X509AttributeCertStoreSelector x509AttributeCertStoreSelector2 = new X509AttributeCertStoreSelector();
            list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createAttributeCertificates(list, x509AttributeCertStoreSelector));
        }
        return set;
    }

    public Collection getAttributeDescriptorCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAttributeDescriptorCertificateAttribute());
        List list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAttributeDescriptorCertificateAttributeName()), stringArray = this.splitString(this.params.getAttributeDescriptorCertificateSubjectAttributeName()));
        Set set = this.createAttributeCertificates(list, x509AttributeCertStoreSelector);
        if (set.size() == 0) {
            X509AttributeCertStoreSelector x509AttributeCertStoreSelector2 = new X509AttributeCertStoreSelector();
            list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createAttributeCertificates(list, x509AttributeCertStoreSelector));
        }
        return set;
    }

    public Collection getCACertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getCACertificateAttribute());
        List list = this.certSubjectSerialSearch(x509CertStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapCACertificateAttributeName()), stringArray = this.splitString(this.params.getCACertificateSubjectAttributeName()));
        Set set = this.createCerts(list, x509CertStoreSelector);
        if (set.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector2 = new X509CertStoreSelector();
            list = this.certSubjectSerialSearch(x509CertStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCerts(list, x509CertStoreSelector));
        }
        return set;
    }

    public Collection getDeltaCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getDeltaRevocationListAttribute());
        List list = this.cRLIssuerSearch(x509CRLStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapDeltaRevocationListAttributeName()), stringArray = this.splitString(this.params.getDeltaRevocationListIssuerAttributeName()));
        Set set = this.createCRLs(list, x509CRLStoreSelector);
        if (set.size() == 0) {
            X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
            list = this.cRLIssuerSearch(x509CRLStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCRLs(list, x509CRLStoreSelector));
        }
        return set;
    }

    public Collection getAttributeCertificateAttributes(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getAttributeCertificateAttributeAttribute());
        List list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapAttributeCertificateAttributeAttributeName()), stringArray = this.splitString(this.params.getAttributeCertificateAttributeSubjectAttributeName()));
        Set set = this.createAttributeCertificates(list, x509AttributeCertStoreSelector);
        if (set.size() == 0) {
            X509AttributeCertStoreSelector x509AttributeCertStoreSelector2 = new X509AttributeCertStoreSelector();
            list = this.attrCertSubjectSerialSearch(x509AttributeCertStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createAttributeCertificates(list, x509AttributeCertStoreSelector));
        }
        return set;
    }

    public Collection getCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] stringArray;
        String[] stringArray2;
        String[] stringArray3 = this.splitString(this.params.getCertificateRevocationListAttribute());
        List list = this.cRLIssuerSearch(x509CRLStoreSelector, stringArray3, stringArray2 = this.splitString(this.params.getLdapCertificateRevocationListAttributeName()), stringArray = this.splitString(this.params.getCertificateRevocationListIssuerAttributeName()));
        Set set = this.createCRLs(list, x509CRLStoreSelector);
        if (set.size() == 0) {
            X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
            list = this.cRLIssuerSearch(x509CRLStoreSelector2, stringArray3, stringArray2, stringArray);
            set.addAll(this.createCRLs(list, x509CRLStoreSelector));
        }
        return set;
    }

    private synchronized void addToCache(String string, List list) {
        Date date = new Date(System.currentTimeMillis());
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(date);
        arrayList.add(list);
        if (this.cacheMap.containsKey(string)) {
            this.cacheMap.put(string, arrayList);
        } else {
            if (this.cacheMap.size() >= cacheSize) {
                Iterator iterator = this.cacheMap.entrySet().iterator();
                long l = date.getTime();
                Object var8_7 = null;
                while (iterator.hasNext()) {
                    Map.Entry entry = iterator.next();
                    long l2 = ((Date)((List)entry.getValue()).get(0)).getTime();
                    if (l2 >= l) continue;
                    l = l2;
                    var8_7 = entry.getKey();
                }
                this.cacheMap.remove(var8_7);
            }
            this.cacheMap.put(string, arrayList);
        }
    }

    private List getFromCache(String string) {
        List list = (List)this.cacheMap.get(string);
        long l = System.currentTimeMillis();
        if (list != null) {
            if (((Date)list.get(0)).getTime() < l - lifeTime) {
                return null;
            }
            return (List)list.get(1);
        }
        return null;
    }

    private String[] splitString(String string) {
        return string.split("\\s+");
    }

    private String getSubjectAsString(X509CertStoreSelector x509CertStoreSelector) {
        try {
            byte[] byArray = x509CertStoreSelector.getSubjectAsBytes();
            if (byArray != null) {
                return new X500Principal(byArray).getName("RFC1779");
            }
        } catch (IOException iOException) {
            throw new StoreException("exception processing name: " + iOException.getMessage(), iOException);
        }
        return null;
    }

    private X500Principal getCertificateIssuer(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal();
    }
}

