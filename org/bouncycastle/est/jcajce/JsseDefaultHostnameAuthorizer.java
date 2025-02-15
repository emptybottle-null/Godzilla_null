/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLSession;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.jcajce.JsseHostnameAuthorizer;
import org.bouncycastle.util.Strings;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JsseDefaultHostnameAuthorizer
implements JsseHostnameAuthorizer {
    private final Set<String> knownSuffixes;

    public JsseDefaultHostnameAuthorizer(Set<String> set) {
        this.knownSuffixes = set;
    }

    @Override
    public boolean verified(String string, SSLSession sSLSession) throws IOException {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            X509Certificate x509Certificate = (X509Certificate)certificateFactory.generateCertificate(new ByteArrayInputStream(sSLSession.getPeerCertificates()[0].getEncoded()));
            return this.verify(string, x509Certificate);
        } catch (Exception exception) {
            if (exception instanceof ESTException) {
                throw (ESTException)exception;
            }
            throw new ESTException(exception.getMessage(), exception);
        }
    }

    public boolean verify(String string, X509Certificate x509Certificate) throws IOException {
        RDN[] rDNArray;
        try {
            rDNArray = x509Certificate.getSubjectAlternativeNames();
            if (rDNArray != null) {
                block6: for (List<?> list : rDNArray) {
                    switch (((Number)list.get(0)).intValue()) {
                        case 2: {
                            if (!JsseDefaultHostnameAuthorizer.isValidNameMatch(string, list.get(1).toString(), this.knownSuffixes)) continue block6;
                            return true;
                        }
                        case 7: {
                            if (!InetAddress.getByName(string).equals(InetAddress.getByName(list.get(1).toString()))) continue block6;
                            return true;
                        }
                    }
                    throw new RuntimeException("Unable to handle ");
                }
                return false;
            }
        } catch (Exception exception) {
            throw new ESTException(exception.getMessage(), exception);
        }
        if (x509Certificate.getSubjectX500Principal() == null) {
            return false;
        }
        rDNArray = X500Name.getInstance(x509Certificate.getSubjectX500Principal().getEncoded()).getRDNs();
        for (int i = 0; i != rDNArray.length; ++i) {
            RDN rDN = rDNArray[i];
            AttributeTypeAndValue[] attributeTypeAndValueArray = rDN.getTypesAndValues();
            for (int j = 0; j != attributeTypeAndValueArray.length; ++j) {
                AttributeTypeAndValue attributeTypeAndValue = attributeTypeAndValueArray[j];
                if (!attributeTypeAndValue.getType().equals(BCStyle.CN)) continue;
                return JsseDefaultHostnameAuthorizer.isValidNameMatch(string, rDN.getFirst().getValue().toString(), this.knownSuffixes);
            }
        }
        return false;
    }

    public static boolean isValidNameMatch(String string, String string2, Set<String> set) throws IOException {
        if (string2.contains("*")) {
            int n = string2.indexOf(42);
            if (n == string2.lastIndexOf("*")) {
                if (string2.contains("..") || string2.charAt(string2.length() - 1) == '*') {
                    return false;
                }
                int n2 = string2.indexOf(46, n);
                if (set != null && set.contains(Strings.toLowerCase(string2.substring(n2)))) {
                    throw new IOException("Wildcard `" + string2 + "` matches known public suffix.");
                }
                String string3 = Strings.toLowerCase(string2.substring(n + 1));
                String string4 = Strings.toLowerCase(string);
                if (string4.equals(string3)) {
                    return false;
                }
                if (string3.length() > string4.length()) {
                    return false;
                }
                if (n > 0) {
                    if (string4.startsWith(string2.substring(0, n - 1)) && string4.endsWith(string3)) {
                        return string4.substring(n, string4.length() - string3.length()).indexOf(46) < 0;
                    }
                    return false;
                }
                String string5 = string4.substring(0, string4.length() - string3.length());
                if (string5.indexOf(46) > 0) {
                    return false;
                }
                return string4.endsWith(string3);
            }
            return false;
        }
        return string.equalsIgnoreCase(string2);
    }
}

