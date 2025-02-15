/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509.extension;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

public class AuthorityKeyIdentifierStructure
extends AuthorityKeyIdentifier {
    public AuthorityKeyIdentifierStructure(byte[] byArray) throws IOException {
        super((ASN1Sequence)X509ExtensionUtil.fromExtensionValue(byArray));
    }

    public AuthorityKeyIdentifierStructure(X509Extension x509Extension) {
        super((ASN1Sequence)x509Extension.getParsedValue());
    }

    public AuthorityKeyIdentifierStructure(Extension extension) {
        super((ASN1Sequence)extension.getParsedValue());
    }

    private static ASN1Sequence fromCertificate(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            if (x509Certificate.getVersion() != 3) {
                GeneralName generalName = new GeneralName(PrincipalUtil.getIssuerX509Principal(x509Certificate));
                SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(x509Certificate.getPublicKey().getEncoded());
                return (ASN1Sequence)new AuthorityKeyIdentifier(subjectPublicKeyInfo, new GeneralNames(generalName), x509Certificate.getSerialNumber()).toASN1Primitive();
            }
            GeneralName generalName = new GeneralName(PrincipalUtil.getIssuerX509Principal(x509Certificate));
            byte[] byArray = x509Certificate.getExtensionValue(Extension.subjectKeyIdentifier.getId());
            if (byArray != null) {
                ASN1OctetString aSN1OctetString = (ASN1OctetString)X509ExtensionUtil.fromExtensionValue(byArray);
                return (ASN1Sequence)new AuthorityKeyIdentifier(aSN1OctetString.getOctets(), new GeneralNames(generalName), x509Certificate.getSerialNumber()).toASN1Primitive();
            }
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(x509Certificate.getPublicKey().getEncoded());
            return (ASN1Sequence)new AuthorityKeyIdentifier(subjectPublicKeyInfo, new GeneralNames(generalName), x509Certificate.getSerialNumber()).toASN1Primitive();
        } catch (Exception exception) {
            throw new CertificateParsingException("Exception extracting certificate details: " + exception.toString());
        }
    }

    private static ASN1Sequence fromKey(PublicKey publicKey) throws InvalidKeyException {
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
            return (ASN1Sequence)new AuthorityKeyIdentifier(subjectPublicKeyInfo).toASN1Primitive();
        } catch (Exception exception) {
            throw new InvalidKeyException("can't process key: " + exception);
        }
    }

    public AuthorityKeyIdentifierStructure(X509Certificate x509Certificate) throws CertificateParsingException {
        super(AuthorityKeyIdentifierStructure.fromCertificate(x509Certificate));
    }

    public AuthorityKeyIdentifierStructure(PublicKey publicKey) throws InvalidKeyException {
        super(AuthorityKeyIdentifierStructure.fromKey(publicKey));
    }
}

