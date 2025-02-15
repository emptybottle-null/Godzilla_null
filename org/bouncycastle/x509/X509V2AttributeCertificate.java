/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.x509.AttributeCertificateHolder;
import org.bouncycastle.x509.AttributeCertificateIssuer;
import org.bouncycastle.x509.X509Attribute;
import org.bouncycastle.x509.X509AttributeCertificate;

public class X509V2AttributeCertificate
implements X509AttributeCertificate {
    private AttributeCertificate cert;
    private Date notBefore;
    private Date notAfter;

    private static AttributeCertificate getObject(InputStream inputStream) throws IOException {
        try {
            return AttributeCertificate.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (IOException iOException) {
            throw iOException;
        } catch (Exception exception) {
            throw new IOException("exception decoding certificate structure: " + exception.toString());
        }
    }

    public X509V2AttributeCertificate(InputStream inputStream) throws IOException {
        this(X509V2AttributeCertificate.getObject(inputStream));
    }

    public X509V2AttributeCertificate(byte[] byArray) throws IOException {
        this(new ByteArrayInputStream(byArray));
    }

    X509V2AttributeCertificate(AttributeCertificate attributeCertificate) throws IOException {
        this.cert = attributeCertificate;
        try {
            this.notAfter = attributeCertificate.getAcinfo().getAttrCertValidityPeriod().getNotAfterTime().getDate();
            this.notBefore = attributeCertificate.getAcinfo().getAttrCertValidityPeriod().getNotBeforeTime().getDate();
        } catch (ParseException parseException) {
            throw new IOException("invalid data structure in certificate!");
        }
    }

    public int getVersion() {
        return this.cert.getAcinfo().getVersion().getValue().intValue() + 1;
    }

    public BigInteger getSerialNumber() {
        return this.cert.getAcinfo().getSerialNumber().getValue();
    }

    public AttributeCertificateHolder getHolder() {
        return new AttributeCertificateHolder((ASN1Sequence)this.cert.getAcinfo().getHolder().toASN1Primitive());
    }

    public AttributeCertificateIssuer getIssuer() {
        return new AttributeCertificateIssuer(this.cert.getAcinfo().getIssuer());
    }

    public Date getNotBefore() {
        return this.notBefore;
    }

    public Date getNotAfter() {
        return this.notAfter;
    }

    public boolean[] getIssuerUniqueID() {
        DERBitString dERBitString = this.cert.getAcinfo().getIssuerUniqueID();
        if (dERBitString != null) {
            byte[] byArray = dERBitString.getBytes();
            boolean[] blArray = new boolean[byArray.length * 8 - dERBitString.getPadBits()];
            for (int i = 0; i != blArray.length; ++i) {
                blArray[i] = (byArray[i / 8] & 128 >>> i % 8) != 0;
            }
            return blArray;
        }
        return null;
    }

    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        this.checkValidity(new Date());
    }

    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        if (date.after(this.getNotAfter())) {
            throw new CertificateExpiredException("certificate expired on " + this.getNotAfter());
        }
        if (date.before(this.getNotBefore())) {
            throw new CertificateNotYetValidException("certificate not valid till " + this.getNotBefore());
        }
    }

    public byte[] getSignature() {
        return this.cert.getSignatureValue().getOctets();
    }

    public final void verify(PublicKey publicKey, String string) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature = null;
        if (!this.cert.getSignatureAlgorithm().equals(this.cert.getAcinfo().getSignature())) {
            throw new CertificateException("Signature algorithm in certificate info not same as outer certificate");
        }
        signature = Signature.getInstance(this.cert.getSignatureAlgorithm().getAlgorithm().getId(), string);
        signature.initVerify(publicKey);
        try {
            signature.update(this.cert.getAcinfo().getEncoded());
        } catch (IOException iOException) {
            throw new SignatureException("Exception encoding certificate info object");
        }
        if (!signature.verify(this.getSignature())) {
            throw new InvalidKeyException("Public key presented not for certificate signature");
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.cert.getEncoded();
    }

    public byte[] getExtensionValue(String string) {
        Extension extension;
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions != null && (extension = extensions.getExtension(new ASN1ObjectIdentifier(string))) != null) {
            try {
                return extension.getExtnValue().getEncoded("DER");
            } catch (Exception exception) {
                throw new RuntimeException("error encoding " + exception.toString());
            }
        }
        return null;
    }

    private Set getExtensionOIDs(boolean bl) {
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions != null) {
            HashSet<String> hashSet = new HashSet<String>();
            Enumeration enumeration = extensions.oids();
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (extension.isCritical() != bl) continue;
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
            return hashSet;
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs() {
        return this.getExtensionOIDs(false);
    }

    public Set getCriticalExtensionOIDs() {
        return this.getExtensionOIDs(true);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set set = this.getCriticalExtensionOIDs();
        return set != null && !set.isEmpty();
    }

    public X509Attribute[] getAttributes() {
        ASN1Sequence aSN1Sequence = this.cert.getAcinfo().getAttributes();
        X509Attribute[] x509AttributeArray = new X509Attribute[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            x509AttributeArray[i] = new X509Attribute(aSN1Sequence.getObjectAt(i));
        }
        return x509AttributeArray;
    }

    public X509Attribute[] getAttributes(String string) {
        ASN1Sequence aSN1Sequence = this.cert.getAcinfo().getAttributes();
        ArrayList<X509Attribute> arrayList = new ArrayList<X509Attribute>();
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            X509Attribute x509Attribute = new X509Attribute(aSN1Sequence.getObjectAt(i));
            if (!x509Attribute.getOID().equals(string)) continue;
            arrayList.add(x509Attribute);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList.toArray(new X509Attribute[arrayList.size()]);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509AttributeCertificate)) {
            return false;
        }
        X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate)object;
        try {
            byte[] byArray = this.getEncoded();
            byte[] byArray2 = x509AttributeCertificate.getEncoded();
            return Arrays.areEqual(byArray, byArray2);
        } catch (IOException iOException) {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(this.getEncoded());
        } catch (IOException iOException) {
            return 0;
        }
    }
}

