/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.CertUtils;
import org.bouncycastle.cert.X509CRLEntryHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;

public class X509CRLHolder
implements Encodable,
Serializable {
    private static final long serialVersionUID = 20170722001L;
    private transient CertificateList x509CRL;
    private transient boolean isIndirect;
    private transient Extensions extensions;
    private transient GeneralNames issuerName;

    private static CertificateList parseStream(InputStream inputStream) throws IOException {
        try {
            ASN1Primitive aSN1Primitive = new ASN1InputStream(inputStream, true).readObject();
            if (aSN1Primitive == null) {
                throw new IOException("no content found");
            }
            return CertificateList.getInstance(aSN1Primitive);
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    private static boolean isIndirectCRL(Extensions extensions) {
        if (extensions == null) {
            return false;
        }
        Extension extension = extensions.getExtension(Extension.issuingDistributionPoint);
        return extension != null && IssuingDistributionPoint.getInstance(extension.getParsedValue()).isIndirectCRL();
    }

    public X509CRLHolder(byte[] byArray) throws IOException {
        this(X509CRLHolder.parseStream(new ByteArrayInputStream(byArray)));
    }

    public X509CRLHolder(InputStream inputStream) throws IOException {
        this(X509CRLHolder.parseStream(inputStream));
    }

    public X509CRLHolder(CertificateList certificateList) {
        this.init(certificateList);
    }

    private void init(CertificateList certificateList) {
        this.x509CRL = certificateList;
        this.extensions = certificateList.getTBSCertList().getExtensions();
        this.isIndirect = X509CRLHolder.isIndirectCRL(this.extensions);
        this.issuerName = new GeneralNames(new GeneralName(certificateList.getIssuer()));
    }

    public byte[] getEncoded() throws IOException {
        return this.x509CRL.getEncoded();
    }

    public X500Name getIssuer() {
        return X500Name.getInstance(this.x509CRL.getIssuer());
    }

    public X509CRLEntryHolder getRevokedCertificate(BigInteger bigInteger) {
        GeneralNames generalNames = this.issuerName;
        Enumeration enumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (enumeration.hasMoreElements()) {
            Extension extension;
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry)enumeration.nextElement();
            if (cRLEntry.getUserCertificate().getValue().equals(bigInteger)) {
                return new X509CRLEntryHolder(cRLEntry, this.isIndirect, generalNames);
            }
            if (!this.isIndirect || !cRLEntry.hasExtensions() || (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) == null) continue;
            generalNames = GeneralNames.getInstance(extension.getParsedValue());
        }
        return null;
    }

    public Collection getRevokedCertificates() {
        TBSCertList.CRLEntry[] cRLEntryArray = this.x509CRL.getRevokedCertificates();
        ArrayList<X509CRLEntryHolder> arrayList = new ArrayList<X509CRLEntryHolder>(cRLEntryArray.length);
        GeneralNames generalNames = this.issuerName;
        Enumeration enumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (enumeration.hasMoreElements()) {
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry)enumeration.nextElement();
            X509CRLEntryHolder x509CRLEntryHolder = new X509CRLEntryHolder(cRLEntry, this.isIndirect, generalNames);
            arrayList.add(x509CRLEntryHolder);
            generalNames = x509CRLEntryHolder.getCertificateIssuer();
        }
        return arrayList;
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

    public CertificateList toASN1Structure() {
        return this.x509CRL;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        ContentVerifier contentVerifier;
        TBSCertList tBSCertList = this.x509CRL.getTBSCertList();
        if (!CertUtils.isAlgIdEqual(tBSCertList.getSignature(), this.x509CRL.getSignatureAlgorithm())) {
            throw new CertException("signature invalid - algorithm identifier mismatch");
        }
        try {
            contentVerifier = contentVerifierProvider.get(tBSCertList.getSignature());
            OutputStream outputStream = contentVerifier.getOutputStream();
            DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
            dEROutputStream.writeObject(tBSCertList);
            outputStream.close();
        } catch (Exception exception) {
            throw new CertException("unable to process signature: " + exception.getMessage(), exception);
        }
        return contentVerifier.verify(this.x509CRL.getSignature().getOctets());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509CRLHolder)) {
            return false;
        }
        X509CRLHolder x509CRLHolder = (X509CRLHolder)object;
        return this.x509CRL.equals(x509CRLHolder.x509CRL);
    }

    public int hashCode() {
        return this.x509CRL.hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.init(CertificateList.getInstance(objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }
}

