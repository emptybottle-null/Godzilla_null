/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.CertUtils;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;

public class X509CertificateHolder
implements Encodable,
Serializable {
    private static final long serialVersionUID = 20170722001L;
    private transient Certificate x509Certificate;
    private transient Extensions extensions;

    private static Certificate parseBytes(byte[] byArray) throws IOException {
        try {
            return Certificate.getInstance(CertUtils.parseNonEmptyASN1(byArray));
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public X509CertificateHolder(byte[] byArray) throws IOException {
        this(X509CertificateHolder.parseBytes(byArray));
    }

    public X509CertificateHolder(Certificate certificate) {
        this.init(certificate);
    }

    private void init(Certificate certificate) {
        this.x509Certificate = certificate;
        this.extensions = certificate.getTBSCertificate().getExtensions();
    }

    public int getVersionNumber() {
        return this.x509Certificate.getVersionNumber();
    }

    public int getVersion() {
        return this.x509Certificate.getVersionNumber();
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (this.extensions != null) {
            return this.extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public List getExtensionOIDs() {
        return CertUtils.getExtensionOIDs(this.extensions);
    }

    public Set getCriticalExtensionOIDs() {
        return CertUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public Set getNonCriticalExtensionOIDs() {
        return CertUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public BigInteger getSerialNumber() {
        return this.x509Certificate.getSerialNumber().getValue();
    }

    public X500Name getIssuer() {
        return X500Name.getInstance(this.x509Certificate.getIssuer());
    }

    public X500Name getSubject() {
        return X500Name.getInstance(this.x509Certificate.getSubject());
    }

    public Date getNotBefore() {
        return this.x509Certificate.getStartDate().getDate();
    }

    public Date getNotAfter() {
        return this.x509Certificate.getEndDate().getDate();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.x509Certificate.getSubjectPublicKeyInfo();
    }

    public Certificate toASN1Structure() {
        return this.x509Certificate;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.x509Certificate.getSignatureAlgorithm();
    }

    public byte[] getSignature() {
        return this.x509Certificate.getSignature().getOctets();
    }

    public boolean isValidOn(Date date) {
        return !date.before(this.x509Certificate.getStartDate().getDate()) && !date.after(this.x509Certificate.getEndDate().getDate());
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        ContentVerifier contentVerifier;
        TBSCertificate tBSCertificate = this.x509Certificate.getTBSCertificate();
        if (!CertUtils.isAlgIdEqual(tBSCertificate.getSignature(), this.x509Certificate.getSignatureAlgorithm())) {
            throw new CertException("signature invalid - algorithm identifier mismatch");
        }
        try {
            contentVerifier = contentVerifierProvider.get(tBSCertificate.getSignature());
            OutputStream outputStream = contentVerifier.getOutputStream();
            DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
            dEROutputStream.writeObject(tBSCertificate);
            outputStream.close();
        } catch (Exception exception) {
            throw new CertException("unable to process signature: " + exception.getMessage(), exception);
        }
        return contentVerifier.verify(this.getSignature());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509CertificateHolder)) {
            return false;
        }
        X509CertificateHolder x509CertificateHolder = (X509CertificateHolder)object;
        return this.x509Certificate.equals(x509CertificateHolder.x509Certificate);
    }

    public int hashCode() {
        return this.x509Certificate.hashCode();
    }

    public byte[] getEncoded() throws IOException {
        return this.x509Certificate.getEncoded();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.init(Certificate.getInstance(objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }
}

