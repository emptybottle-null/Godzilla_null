/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.CRLNumber;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.jcajce.provider.asymmetric.x509.ExtCRLException;
import org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLEntryObject;
import org.bouncycastle.jcajce.provider.asymmetric.x509.X509SignatureUtil;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

class X509CRLObject
extends X509CRL {
    private JcaJceHelper bcHelper;
    private CertificateList c;
    private String sigAlgName;
    private byte[] sigAlgParams;
    private boolean isIndirect;
    private boolean isHashCodeSet = false;
    private int hashCodeValue;

    static boolean isIndirectCRL(X509CRL x509CRL) throws CRLException {
        try {
            byte[] byArray = x509CRL.getExtensionValue(Extension.issuingDistributionPoint.getId());
            return byArray != null && IssuingDistributionPoint.getInstance(ASN1OctetString.getInstance(byArray).getOctets()).isIndirectCRL();
        } catch (Exception exception) {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", exception);
        }
    }

    protected X509CRLObject(JcaJceHelper jcaJceHelper, CertificateList certificateList) throws CRLException {
        this.bcHelper = jcaJceHelper;
        this.c = certificateList;
        try {
            this.sigAlgName = X509SignatureUtil.getSignatureName(certificateList.getSignatureAlgorithm());
            this.sigAlgParams = (byte[])(certificateList.getSignatureAlgorithm().getParameters() != null ? certificateList.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded("DER") : null);
            this.isIndirect = X509CRLObject.isIndirectCRL(this);
        } catch (Exception exception) {
            throw new CRLException("CRL contents invalid: " + exception);
        }
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set set = this.getCriticalExtensionOIDs();
        if (set == null) {
            return false;
        }
        set.remove(Extension.issuingDistributionPoint.getId());
        set.remove(Extension.deltaCRLIndicator.getId());
        return !set.isEmpty();
    }

    private Set getExtensionOIDs(boolean bl) {
        Extensions extensions;
        if (this.getVersion() == 2 && (extensions = this.c.getTBSCertList().getExtensions()) != null) {
            HashSet<String> hashSet = new HashSet<String>();
            Enumeration enumeration = extensions.oids();
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (bl != extension.isCritical()) continue;
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
            return hashSet;
        }
        return null;
    }

    public Set getCriticalExtensionOIDs() {
        return this.getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs() {
        return this.getExtensionOIDs(false);
    }

    public byte[] getExtensionValue(String string) {
        Extension extension;
        Extensions extensions = this.c.getTBSCertList().getExtensions();
        if (extensions != null && (extension = extensions.getExtension(new ASN1ObjectIdentifier(string))) != null) {
            try {
                return extension.getExtnValue().getEncoded();
            } catch (Exception exception) {
                throw new IllegalStateException("error parsing " + exception.toString());
            }
        }
        return null;
    }

    public byte[] getEncoded() throws CRLException {
        try {
            return this.c.getEncoded("DER");
        } catch (IOException iOException) {
            throw new CRLException(iOException.toString());
        }
    }

    public void verify(PublicKey publicKey) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        try {
            signature = this.bcHelper.createSignature(this.getSigAlgName());
        } catch (Exception exception) {
            signature = Signature.getInstance(this.getSigAlgName());
        }
        this.doVerify(publicKey, signature);
    }

    public void verify(PublicKey publicKey, String string) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature = string != null ? Signature.getInstance(this.getSigAlgName(), string) : Signature.getInstance(this.getSigAlgName());
        this.doVerify(publicKey, signature);
    }

    public void verify(PublicKey publicKey, Provider provider) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = provider != null ? Signature.getInstance(this.getSigAlgName(), provider) : Signature.getInstance(this.getSigAlgName());
        this.doVerify(publicKey, signature);
    }

    private void doVerify(PublicKey publicKey, Signature signature) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (!this.c.getSignatureAlgorithm().equals(this.c.getTBSCertList().getSignature())) {
            throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
        }
        signature.initVerify(publicKey);
        signature.update(this.getTBSCertList());
        if (!signature.verify(this.getSignature())) {
            throw new SignatureException("CRL does not verify with supplied public key.");
        }
    }

    public int getVersion() {
        return this.c.getVersionNumber();
    }

    public Principal getIssuerDN() {
        return new X509Principal(X500Name.getInstance(this.c.getIssuer().toASN1Primitive()));
    }

    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.c.getIssuer().getEncoded());
        } catch (IOException iOException) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getThisUpdate() {
        return this.c.getThisUpdate().getDate();
    }

    public Date getNextUpdate() {
        if (this.c.getNextUpdate() != null) {
            return this.c.getNextUpdate().getDate();
        }
        return null;
    }

    private Set loadCRLEntries() {
        HashSet<X509CRLEntryObject> hashSet = new HashSet<X509CRLEntryObject>();
        Enumeration enumeration = this.c.getRevokedCertificateEnumeration();
        X500Name x500Name = null;
        while (enumeration.hasMoreElements()) {
            Extension extension;
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry)enumeration.nextElement();
            X509CRLEntryObject x509CRLEntryObject = new X509CRLEntryObject(cRLEntry, this.isIndirect, x500Name);
            hashSet.add(x509CRLEntryObject);
            if (!this.isIndirect || !cRLEntry.hasExtensions() || (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) == null) continue;
            x500Name = X500Name.getInstance(GeneralNames.getInstance(extension.getParsedValue()).getNames()[0].getName());
        }
        return hashSet;
    }

    public X509CRLEntry getRevokedCertificate(BigInteger bigInteger) {
        Enumeration enumeration = this.c.getRevokedCertificateEnumeration();
        X500Name x500Name = null;
        while (enumeration.hasMoreElements()) {
            Extension extension;
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry)enumeration.nextElement();
            if (bigInteger.equals(cRLEntry.getUserCertificate().getValue())) {
                return new X509CRLEntryObject(cRLEntry, this.isIndirect, x500Name);
            }
            if (!this.isIndirect || !cRLEntry.hasExtensions() || (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) == null) continue;
            x500Name = X500Name.getInstance(GeneralNames.getInstance(extension.getParsedValue()).getNames()[0].getName());
        }
        return null;
    }

    public Set getRevokedCertificates() {
        Set set = this.loadCRLEntries();
        if (!set.isEmpty()) {
            return Collections.unmodifiableSet(set);
        }
        return null;
    }

    public byte[] getTBSCertList() throws CRLException {
        try {
            return this.c.getTBSCertList().getEncoded("DER");
        } catch (IOException iOException) {
            throw new CRLException(iOException.toString());
        }
    }

    public byte[] getSignature() {
        return this.c.getSignature().getOctets();
    }

    public String getSigAlgName() {
        return this.sigAlgName;
    }

    public String getSigAlgOID() {
        return this.c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    public byte[] getSigAlgParams() {
        if (this.sigAlgParams != null) {
            byte[] byArray = new byte[this.sigAlgParams.length];
            System.arraycopy(this.sigAlgParams, 0, byArray, 0, byArray.length);
            return byArray;
        }
        return null;
    }

    public String toString() {
        Object object;
        Object object2;
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("              Version: ").append(this.getVersion()).append(string);
        stringBuffer.append("             IssuerDN: ").append(this.getIssuerDN()).append(string);
        stringBuffer.append("          This update: ").append(this.getThisUpdate()).append(string);
        stringBuffer.append("          Next update: ").append(this.getNextUpdate()).append(string);
        stringBuffer.append("  Signature Algorithm: ").append(this.getSigAlgName()).append(string);
        byte[] byArray = this.getSignature();
        stringBuffer.append("            Signature: ").append(new String(Hex.encode(byArray, 0, 20))).append(string);
        for (int i = 20; i < byArray.length; i += 20) {
            if (i < byArray.length - 20) {
                stringBuffer.append("                       ").append(new String(Hex.encode(byArray, i, 20))).append(string);
                continue;
            }
            stringBuffer.append("                       ").append(new String(Hex.encode(byArray, i, byArray.length - i))).append(string);
        }
        Extensions extensions = this.c.getTBSCertList().getExtensions();
        if (extensions != null) {
            object2 = extensions.oids();
            if (object2.hasMoreElements()) {
                stringBuffer.append("           Extensions: ").append(string);
            }
            while (object2.hasMoreElements()) {
                object = (ASN1ObjectIdentifier)object2.nextElement();
                Extension extension = extensions.getExtension((ASN1ObjectIdentifier)object);
                if (extension.getExtnValue() != null) {
                    byte[] byArray2 = extension.getExtnValue().getOctets();
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray2);
                    stringBuffer.append("                       critical(").append(extension.isCritical()).append(") ");
                    try {
                        if (((ASN1Primitive)object).equals(Extension.cRLNumber)) {
                            stringBuffer.append(new CRLNumber(ASN1Integer.getInstance(aSN1InputStream.readObject()).getPositiveValue())).append(string);
                            continue;
                        }
                        if (((ASN1Primitive)object).equals(Extension.deltaCRLIndicator)) {
                            stringBuffer.append("Base CRL: " + new CRLNumber(ASN1Integer.getInstance(aSN1InputStream.readObject()).getPositiveValue())).append(string);
                            continue;
                        }
                        if (((ASN1Primitive)object).equals(Extension.issuingDistributionPoint)) {
                            stringBuffer.append(IssuingDistributionPoint.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (((ASN1Primitive)object).equals(Extension.cRLDistributionPoints)) {
                            stringBuffer.append(CRLDistPoint.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (((ASN1Primitive)object).equals(Extension.freshestCRL)) {
                            stringBuffer.append(CRLDistPoint.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        stringBuffer.append(((ASN1ObjectIdentifier)object).getId());
                        stringBuffer.append(" value = ").append(ASN1Dump.dumpAsString(aSN1InputStream.readObject())).append(string);
                    } catch (Exception exception) {
                        stringBuffer.append(((ASN1ObjectIdentifier)object).getId());
                        stringBuffer.append(" value = ").append("*****").append(string);
                    }
                    continue;
                }
                stringBuffer.append(string);
            }
        }
        if ((object2 = this.getRevokedCertificates()) != null) {
            object = object2.iterator();
            while (object.hasNext()) {
                stringBuffer.append(object.next());
                stringBuffer.append(string);
            }
        }
        return stringBuffer.toString();
    }

    public boolean isRevoked(java.security.cert.Certificate certificate) {
        if (!certificate.getType().equals("X.509")) {
            throw new IllegalArgumentException("X.509 CRL used with non X.509 Cert");
        }
        Enumeration enumeration = this.c.getRevokedCertificateEnumeration();
        X500Name x500Name = this.c.getIssuer();
        if (enumeration.hasMoreElements()) {
            BigInteger bigInteger = ((X509Certificate)certificate).getSerialNumber();
            while (enumeration.hasMoreElements()) {
                ASN1Object aSN1Object;
                TBSCertList.CRLEntry cRLEntry = TBSCertList.CRLEntry.getInstance(enumeration.nextElement());
                if (this.isIndirect && cRLEntry.hasExtensions() && (aSN1Object = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                    x500Name = X500Name.getInstance(GeneralNames.getInstance(aSN1Object.getParsedValue()).getNames()[0].getName());
                }
                if (!cRLEntry.getUserCertificate().getValue().equals(bigInteger)) continue;
                if (certificate instanceof X509Certificate) {
                    aSN1Object = X500Name.getInstance(((X509Certificate)certificate).getIssuerX500Principal().getEncoded());
                } else {
                    try {
                        aSN1Object = Certificate.getInstance(certificate.getEncoded()).getIssuer();
                    } catch (CertificateEncodingException certificateEncodingException) {
                        throw new IllegalArgumentException("Cannot process certificate: " + certificateEncodingException.getMessage());
                    }
                }
                return x500Name.equals(aSN1Object);
            }
        }
        return false;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof X509CRL)) {
            return false;
        }
        if (object instanceof X509CRLObject) {
            boolean bl;
            X509CRLObject x509CRLObject = (X509CRLObject)object;
            if (this.isHashCodeSet && (bl = x509CRLObject.isHashCodeSet) && x509CRLObject.hashCodeValue != this.hashCodeValue) {
                return false;
            }
            return this.c.equals(x509CRLObject.c);
        }
        return super.equals(object);
    }

    public int hashCode() {
        if (!this.isHashCodeSet) {
            this.isHashCodeSet = true;
            this.hashCodeValue = super.hashCode();
        }
        return this.hashCodeValue;
    }
}

