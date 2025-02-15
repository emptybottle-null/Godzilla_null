/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.AttributeCertificateIssuer;

public class JcaAttributeCertificateIssuer
extends AttributeCertificateIssuer {
    public JcaAttributeCertificateIssuer(X509Certificate x509Certificate) {
        this(x509Certificate.getIssuerX500Principal());
    }

    public JcaAttributeCertificateIssuer(X500Principal x500Principal) {
        super(X500Name.getInstance(x500Principal.getEncoded()));
    }
}

