/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.x509.X509AttributeCertificate;

class PrincipalUtils {
    PrincipalUtils() {
    }

    static X500Name getSubjectPrincipal(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getSubjectX500Principal().getEncoded());
    }

    static X500Name getIssuerPrincipal(X509CRL x509CRL) {
        return X500Name.getInstance(x509CRL.getIssuerX500Principal().getEncoded());
    }

    static X500Name getIssuerPrincipal(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getIssuerX500Principal().getEncoded());
    }

    static X500Name getCA(TrustAnchor trustAnchor) {
        return X500Name.getInstance(trustAnchor.getCA().getEncoded());
    }

    static X500Name getEncodedIssuerPrincipal(Object object) {
        if (object instanceof X509Certificate) {
            return PrincipalUtils.getIssuerPrincipal((X509Certificate)object);
        }
        return X500Name.getInstance(((X500Principal)((X509AttributeCertificate)object).getIssuer().getPrincipals()[0]).getEncoded());
    }
}

