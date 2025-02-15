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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttCertValidityPeriod;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.AttributeCertificateInfo;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.AttributeCertificateHolder;
import org.bouncycastle.cert.AttributeCertificateIssuer;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.CertUtils;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;

public class X509AttributeCertificateHolder
implements Encodable,
Serializable {
    private static final long serialVersionUID = 20170722001L;
    private static Attribute[] EMPTY_ARRAY = new Attribute[0];
    private transient AttributeCertificate attrCert;
    private transient Extensions extensions;

    private static AttributeCertificate parseBytes(byte[] byArray) throws IOException {
        try {
            return AttributeCertificate.getInstance(CertUtils.parseNonEmptyASN1(byArray));
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public X509AttributeCertificateHolder(byte[] byArray) throws IOException {
        this(X509AttributeCertificateHolder.parseBytes(byArray));
    }

    public X509AttributeCertificateHolder(AttributeCertificate attributeCertificate) {
        this.init(attributeCertificate);
    }

    private void init(AttributeCertificate attributeCertificate) {
        this.attrCert = attributeCertificate;
        this.extensions = attributeCertificate.getAcinfo().getExtensions();
    }

    public byte[] getEncoded() throws IOException {
        return this.attrCert.getEncoded();
    }

    public int getVersion() {
        return this.attrCert.getAcinfo().getVersion().getValue().intValue() + 1;
    }

    public BigInteger getSerialNumber() {
        return this.attrCert.getAcinfo().getSerialNumber().getValue();
    }

    public AttributeCertificateHolder getHolder() {
        return new AttributeCertificateHolder((ASN1Sequence)this.attrCert.getAcinfo().getHolder().toASN1Primitive());
    }

    public AttributeCertificateIssuer getIssuer() {
        return new AttributeCertificateIssuer(this.attrCert.getAcinfo().getIssuer());
    }

    public Date getNotBefore() {
        return CertUtils.recoverDate(this.attrCert.getAcinfo().getAttrCertValidityPeriod().getNotBeforeTime());
    }

    public Date getNotAfter() {
        return CertUtils.recoverDate(this.attrCert.getAcinfo().getAttrCertValidityPeriod().getNotAfterTime());
    }

    public Attribute[] getAttributes() {
        ASN1Sequence aSN1Sequence = this.attrCert.getAcinfo().getAttributes();
        Attribute[] attributeArray = new Attribute[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            attributeArray[i] = Attribute.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return attributeArray;
    }

    public Attribute[] getAttributes(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        ASN1Sequence aSN1Sequence = this.attrCert.getAcinfo().getAttributes();
        ArrayList<Attribute> arrayList = new ArrayList<Attribute>();
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            Attribute attribute = Attribute.getInstance(aSN1Sequence.getObjectAt(i));
            if (!attribute.getAttrType().equals(aSN1ObjectIdentifier)) continue;
            arrayList.add(attribute);
        }
        if (arrayList.size() == 0) {
            return EMPTY_ARRAY;
        }
        return arrayList.toArray(new Attribute[arrayList.size()]);
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

    public boolean[] getIssuerUniqueID() {
        return CertUtils.bitStringToBoolean(this.attrCert.getAcinfo().getIssuerUniqueID());
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.attrCert.getSignatureAlgorithm();
    }

    public byte[] getSignature() {
        return this.attrCert.getSignatureValue().getOctets();
    }

    public AttributeCertificate toASN1Structure() {
        return this.attrCert;
    }

    public boolean isValidOn(Date date) {
        AttCertValidityPeriod attCertValidityPeriod = this.attrCert.getAcinfo().getAttrCertValidityPeriod();
        return !date.before(CertUtils.recoverDate(attCertValidityPeriod.getNotBeforeTime())) && !date.after(CertUtils.recoverDate(attCertValidityPeriod.getNotAfterTime()));
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        ContentVerifier contentVerifier;
        AttributeCertificateInfo attributeCertificateInfo = this.attrCert.getAcinfo();
        if (!CertUtils.isAlgIdEqual(attributeCertificateInfo.getSignature(), this.attrCert.getSignatureAlgorithm())) {
            throw new CertException("signature invalid - algorithm identifier mismatch");
        }
        try {
            contentVerifier = contentVerifierProvider.get(attributeCertificateInfo.getSignature());
            OutputStream outputStream = contentVerifier.getOutputStream();
            DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
            dEROutputStream.writeObject(attributeCertificateInfo);
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
        if (!(object instanceof X509AttributeCertificateHolder)) {
            return false;
        }
        X509AttributeCertificateHolder x509AttributeCertificateHolder = (X509AttributeCertificateHolder)object;
        return this.attrCert.equals(x509AttributeCertificateHolder.attrCert);
    }

    public int hashCode() {
        return this.attrCert.hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.init(AttributeCertificate.getInstance(objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }
}

