/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;

public class CertificateID {
    public static final AlgorithmIdentifier HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    private final CertID id;

    public CertificateID(CertID certID) {
        if (certID == null) {
            throw new IllegalArgumentException("'id' cannot be null");
        }
        this.id = certID;
    }

    public CertificateID(DigestCalculator digestCalculator, X509CertificateHolder x509CertificateHolder, BigInteger bigInteger) throws OCSPException {
        this.id = CertificateID.createCertID(digestCalculator, x509CertificateHolder, new ASN1Integer(bigInteger));
    }

    public ASN1ObjectIdentifier getHashAlgOID() {
        return this.id.getHashAlgorithm().getAlgorithm();
    }

    public byte[] getIssuerNameHash() {
        return this.id.getIssuerNameHash().getOctets();
    }

    public byte[] getIssuerKeyHash() {
        return this.id.getIssuerKeyHash().getOctets();
    }

    public BigInteger getSerialNumber() {
        return this.id.getSerialNumber().getValue();
    }

    public boolean matchesIssuer(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws OCSPException {
        try {
            return CertificateID.createCertID(digestCalculatorProvider.get(this.id.getHashAlgorithm()), x509CertificateHolder, this.id.getSerialNumber()).equals(this.id);
        } catch (OperatorCreationException operatorCreationException) {
            throw new OCSPException("unable to create digest calculator: " + operatorCreationException.getMessage(), operatorCreationException);
        }
    }

    public CertID toASN1Primitive() {
        return this.id;
    }

    public boolean equals(Object object) {
        if (!(object instanceof CertificateID)) {
            return false;
        }
        CertificateID certificateID = (CertificateID)object;
        return this.id.toASN1Primitive().equals(certificateID.id.toASN1Primitive());
    }

    public int hashCode() {
        return this.id.toASN1Primitive().hashCode();
    }

    public static CertificateID deriveCertificateID(CertificateID certificateID, BigInteger bigInteger) {
        return new CertificateID(new CertID(certificateID.id.getHashAlgorithm(), certificateID.id.getIssuerNameHash(), certificateID.id.getIssuerKeyHash(), new ASN1Integer(bigInteger)));
    }

    private static CertID createCertID(DigestCalculator digestCalculator, X509CertificateHolder x509CertificateHolder, ASN1Integer aSN1Integer) throws OCSPException {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(x509CertificateHolder.toASN1Structure().getSubject().getEncoded("DER"));
            outputStream.close();
            DEROctetString dEROctetString = new DEROctetString(digestCalculator.getDigest());
            SubjectPublicKeyInfo subjectPublicKeyInfo = x509CertificateHolder.getSubjectPublicKeyInfo();
            outputStream = digestCalculator.getOutputStream();
            outputStream.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            outputStream.close();
            DEROctetString dEROctetString2 = new DEROctetString(digestCalculator.getDigest());
            return new CertID(digestCalculator.getAlgorithmIdentifier(), dEROctetString, dEROctetString2, aSN1Integer);
        } catch (Exception exception) {
            throw new OCSPException("problem creating ID: " + exception, exception);
        }
    }
}

