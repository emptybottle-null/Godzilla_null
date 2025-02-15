/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertRuntimeException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestCalculator;

public class X509ExtensionUtils {
    private DigestCalculator calculator;

    public X509ExtensionUtils(DigestCalculator digestCalculator) {
        this.calculator = digestCalculator;
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(X509CertificateHolder x509CertificateHolder) {
        GeneralName generalName = new GeneralName(x509CertificateHolder.getIssuer());
        return new AuthorityKeyIdentifier(this.getSubjectKeyIdentifier(x509CertificateHolder), new GeneralNames(generalName), x509CertificateHolder.getSerialNumber());
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new AuthorityKeyIdentifier(this.calculateIdentifier(subjectPublicKeyInfo));
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo, GeneralNames generalNames, BigInteger bigInteger) {
        return new AuthorityKeyIdentifier(this.calculateIdentifier(subjectPublicKeyInfo), generalNames, bigInteger);
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new SubjectKeyIdentifier(this.calculateIdentifier(subjectPublicKeyInfo));
    }

    public SubjectKeyIdentifier createTruncatedSubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        byte[] byArray = this.calculateIdentifier(subjectPublicKeyInfo);
        byte[] byArray2 = new byte[8];
        System.arraycopy(byArray, byArray.length - 8, byArray2, 0, byArray2.length);
        byArray2[0] = (byte)(byArray2[0] & 0xF);
        byArray2[0] = (byte)(byArray2[0] | 0x40);
        return new SubjectKeyIdentifier(byArray2);
    }

    private byte[] getSubjectKeyIdentifier(X509CertificateHolder x509CertificateHolder) {
        if (x509CertificateHolder.getVersionNumber() != 3) {
            return this.calculateIdentifier(x509CertificateHolder.getSubjectPublicKeyInfo());
        }
        Extension extension = x509CertificateHolder.getExtension(Extension.subjectKeyIdentifier);
        if (extension != null) {
            return ASN1OctetString.getInstance(extension.getParsedValue()).getOctets();
        }
        return this.calculateIdentifier(x509CertificateHolder.getSubjectPublicKeyInfo());
    }

    private byte[] calculateIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        byte[] byArray = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        OutputStream outputStream = this.calculator.getOutputStream();
        try {
            outputStream.write(byArray);
            outputStream.close();
        } catch (IOException iOException) {
            throw new CertRuntimeException("unable to calculate identifier: " + iOException.getMessage(), iOException);
        }
        return this.calculator.getDigest();
    }
}

