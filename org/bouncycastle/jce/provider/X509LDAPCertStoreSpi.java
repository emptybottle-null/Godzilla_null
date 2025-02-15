/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;

public class X509LDAPCertStoreSpi
extends CertStoreSpi {
    private X509LDAPCertStoreParameters params;
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";

    public X509LDAPCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (!(certStoreParameters instanceof X509LDAPCertStoreParameters)) {
            throw new InvalidAlgorithmParameterException(X509LDAPCertStoreSpi.class.getName() + ": parameter must be a " + X509LDAPCertStoreParameters.class.getName() + " object\n" + certStoreParameters.toString());
        }
        this.params = (X509LDAPCertStoreParameters)certStoreParameters;
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
        int n = string3.toLowerCase().indexOf(string2.toLowerCase());
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

    public Collection engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        if (!(certSelector instanceof X509CertSelector)) {
            throw new CertStoreException("selector is not a X509CertSelector");
        }
        X509CertSelector x509CertSelector = (X509CertSelector)certSelector;
        HashSet<Certificate> hashSet = new HashSet<Certificate>();
        Set set = this.getEndCertificates(x509CertSelector);
        set.addAll(this.getCACertificates(x509CertSelector));
        set.addAll(this.getCrossCertificates(x509CertSelector));
        Iterator iterator = set.iterator();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
            while (iterator.hasNext()) {
                Object object;
                byte[] byArray = (byte[])iterator.next();
                if (byArray == null || byArray.length == 0) continue;
                ArrayList<byte[]> arrayList = new ArrayList<byte[]>();
                arrayList.add(byArray);
                try {
                    object = CertificatePair.getInstance(new ASN1InputStream(byArray).readObject());
                    arrayList.clear();
                    if (((CertificatePair)object).getForward() != null) {
                        arrayList.add(((CertificatePair)object).getForward().getEncoded());
                    }
                    if (((CertificatePair)object).getReverse() != null) {
                        arrayList.add(((CertificatePair)object).getReverse().getEncoded());
                    }
                } catch (IOException iOException) {
                } catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
                object = arrayList.iterator();
                while (object.hasNext()) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])object.next());
                    try {
                        Certificate certificate = certificateFactory.generateCertificate(byteArrayInputStream);
                        if (!x509CertSelector.match(certificate)) continue;
                        hashSet.add(certificate);
                    } catch (Exception exception) {}
                }
            }
        } catch (Exception exception) {
            throw new CertStoreException("certificate cannot be constructed from LDAP result: " + exception);
        }
        return hashSet;
    }

    private Set certSubjectSerialSearch(X509CertSelector x509CertSelector, String[] stringArray, String string, String string2) throws CertStoreException {
        HashSet hashSet = new HashSet();
        try {
            if (x509CertSelector.getSubjectAsBytes() != null || x509CertSelector.getSubjectAsString() != null || x509CertSelector.getCertificate() != null) {
                String string3 = null;
                String string4 = null;
                if (x509CertSelector.getCertificate() != null) {
                    string3 = x509CertSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                    string4 = x509CertSelector.getCertificate().getSerialNumber().toString();
                } else {
                    string3 = x509CertSelector.getSubjectAsBytes() != null ? new X500Principal(x509CertSelector.getSubjectAsBytes()).getName("RFC1779") : x509CertSelector.getSubjectAsString();
                }
                String string5 = this.parseDN(string3, string2);
                hashSet.addAll(this.search(string, "*" + string5 + "*", stringArray));
                if (string4 != null && this.params.getSearchForSerialNumberIn() != null) {
                    string5 = string4;
                    string = this.params.getSearchForSerialNumberIn();
                    hashSet.addAll(this.search(string, "*" + string5 + "*", stringArray));
                }
            } else {
                hashSet.addAll(this.search(string, "*", stringArray));
            }
        } catch (IOException iOException) {
            throw new CertStoreException("exception processing selector: " + iOException);
        }
        return hashSet;
    }

    private Set getEndCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] stringArray = new String[]{this.params.getUserCertificateAttribute()};
        String string = this.params.getLdapUserCertificateAttributeName();
        String string2 = this.params.getUserCertificateSubjectAttributeName();
        Set set = this.certSubjectSerialSearch(x509CertSelector, stringArray, string, string2);
        return set;
    }

    private Set getCACertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String string;
        String string2;
        String[] stringArray = new String[]{this.params.getCACertificateAttribute()};
        Set set = this.certSubjectSerialSearch(x509CertSelector, stringArray, string2 = this.params.getLdapCACertificateAttributeName(), string = this.params.getCACertificateSubjectAttributeName());
        if (set.isEmpty()) {
            set.addAll(this.search(null, "*", stringArray));
        }
        return set;
    }

    private Set getCrossCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String string;
        String string2;
        String[] stringArray = new String[]{this.params.getCrossCertificateAttribute()};
        Set set = this.certSubjectSerialSearch(x509CertSelector, stringArray, string2 = this.params.getLdapCrossCertificateAttributeName(), string = this.params.getCrossCertificateSubjectAttributeName());
        if (set.isEmpty()) {
            set.addAll(this.search(null, "*", stringArray));
        }
        return set;
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        Object object;
        String[] stringArray = new String[]{this.params.getCertificateRevocationListAttribute()};
        if (!(cRLSelector instanceof X509CRLSelector)) {
            throw new CertStoreException("selector is not a X509CRLSelector");
        }
        X509CRLSelector x509CRLSelector = (X509CRLSelector)cRLSelector;
        HashSet<Object> hashSet = new HashSet<Object>();
        String string = this.params.getLdapCertificateRevocationListAttributeName();
        HashSet hashSet2 = new HashSet();
        if (x509CRLSelector.getIssuerNames() != null) {
            for (Object object2 : x509CRLSelector.getIssuerNames()) {
                String string2;
                object = null;
                if (object2 instanceof String) {
                    string2 = this.params.getCertificateRevocationListIssuerAttributeName();
                    object = this.parseDN((String)object2, string2);
                } else {
                    string2 = this.params.getCertificateRevocationListIssuerAttributeName();
                    object = this.parseDN(new X500Principal((byte[])object2).getName("RFC1779"), string2);
                }
                hashSet2.addAll(this.search(string, "*" + (String)object + "*", stringArray));
            }
        } else {
            hashSet2.addAll(this.search(string, "*", stringArray));
        }
        hashSet2.addAll(this.search(null, "*", stringArray));
        Iterator<Object> iterator = hashSet2.iterator();
        try {
            Object object2;
            object2 = CertificateFactory.getInstance("X.509", "BC");
            while (iterator.hasNext()) {
                object = ((CertificateFactory)object2).generateCRL(new ByteArrayInputStream((byte[])iterator.next()));
                if (!x509CRLSelector.match((CRL)object)) continue;
                hashSet.add(object);
            }
        } catch (Exception exception) {
            throw new CertStoreException("CRL cannot be constructed from LDAP result " + exception);
        }
        return hashSet;
    }

    private Set search(String string, String string2, String[] stringArray) throws CertStoreException {
        String string3 = string + "=" + string2;
        if (string == null) {
            string3 = null;
        }
        DirContext dirContext = null;
        HashSet hashSet = new HashSet();
        try {
            dirContext = this.connectLDAP();
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(2);
            searchControls.setCountLimit(0L);
            for (int i = 0; i < stringArray.length; ++i) {
                String[] stringArray2 = new String[]{stringArray[i]};
                searchControls.setReturningAttributes(stringArray2);
                String string4 = "(&(" + string3 + ")(" + stringArray2[0] + "=*))";
                if (string3 == null) {
                    string4 = "(" + stringArray2[0] + "=*)";
                }
                NamingEnumeration<SearchResult> namingEnumeration = dirContext.search(this.params.getBaseDN(), string4, searchControls);
                while (namingEnumeration.hasMoreElements()) {
                    SearchResult searchResult = namingEnumeration.next();
                    NamingEnumeration<?> namingEnumeration2 = searchResult.getAttributes().getAll().next().getAll();
                    while (namingEnumeration2.hasMore()) {
                        Object obj = namingEnumeration2.next();
                        hashSet.add(obj);
                    }
                }
            }
        } catch (Exception exception) {
            throw new CertStoreException("Error getting results from LDAP directory " + exception);
        } finally {
            try {
                if (null != dirContext) {
                    dirContext.close();
                }
            } catch (Exception exception) {}
        }
        return hashSet;
    }
}

