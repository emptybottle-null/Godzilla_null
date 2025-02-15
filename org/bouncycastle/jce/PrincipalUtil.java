/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.X509Principal;

public class PrincipalUtil {
    public static X509Principal getIssuerX509Principal(X509Certificate x509Certificate) throws CertificateEncodingException {
        try {
            TBSCertificateStructure tBSCertificateStructure = TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(x509Certificate.getTBSCertificate()));
            return new X509Principal(X509Name.getInstance(tBSCertificateStructure.getIssuer()));
        } catch (IOException iOException) {
            throw new CertificateEncodingException(iOException.toString());
        }
    }

    public static X509Principal getSubjectX509Principal(X509Certificate x509Certificate) throws CertificateEncodingException {
        try {
            TBSCertificateStructure tBSCertificateStructure = TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(x509Certificate.getTBSCertificate()));
            return new X509Principal(X509Name.getInstance(tBSCertificateStructure.getSubject()));
        } catch (IOException iOException) {
            throw new CertificateEncodingException(iOException.toString());
        }
    }

    public static X509Principal getIssuerX509Principal(X509CRL x509CRL) throws CRLException {
        try {
            TBSCertList tBSCertList = TBSCertList.getInstance(ASN1Primitive.fromByteArray(x509CRL.getTBSCertList()));
            return new X509Principal(X509Name.getInstance(tBSCertList.getIssuer()));
        } catch (IOException iOException) {
            throw new CRLException(iOException.toString());
        }
    }
}

