/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.misc.NetscapeRevocationURL;
import org.bouncycastle.asn1.misc.VerisignCzagExtension;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jcajce.provider.asymmetric.x509.X509SignatureUtil;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

class X509CertificateObject
extends X509Certificate
implements PKCS12BagAttributeCarrier {
    private JcaJceHelper bcHelper;
    private Certificate c;
    private BasicConstraints basicConstraints;
    private boolean[] keyUsage;
    private boolean hashValueSet;
    private int hashValue;
    private PKCS12BagAttributeCarrier attrCarrier = new PKCS12BagAttributeCarrierImpl();

    public X509CertificateObject(JcaJceHelper jcaJceHelper, Certificate certificate) throws CertificateParsingException {
        byte[] byArray;
        this.bcHelper = jcaJceHelper;
        this.c = certificate;
        try {
            byArray = this.getExtensionBytes("2.5.29.19");
            if (byArray != null) {
                this.basicConstraints = BasicConstraints.getInstance(ASN1Primitive.fromByteArray(byArray));
            }
        } catch (Exception exception) {
            throw new CertificateParsingException("cannot construct BasicConstraints: " + exception);
        }
        try {
            byArray = this.getExtensionBytes("2.5.29.15");
            if (byArray != null) {
                DERBitString dERBitString = DERBitString.getInstance(ASN1Primitive.fromByteArray(byArray));
                int n = (byArray = dERBitString.getBytes()).length * 8 - dERBitString.getPadBits();
                this.keyUsage = new boolean[n < 9 ? 9 : n];
                for (int i = 0; i != n; ++i) {
                    this.keyUsage[i] = (byArray[i / 8] & 128 >>> i % 8) != 0;
                }
            } else {
                this.keyUsage = null;
            }
        } catch (Exception exception) {
            throw new CertificateParsingException("cannot construct KeyUsage: " + exception);
        }
    }

    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        this.checkValidity(new Date());
    }

    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        if (date.getTime() > this.getNotAfter().getTime()) {
            throw new CertificateExpiredException("certificate expired on " + this.c.getEndDate().getTime());
        }
        if (date.getTime() < this.getNotBefore().getTime()) {
            throw new CertificateNotYetValidException("certificate not valid till " + this.c.getStartDate().getTime());
        }
    }

    public int getVersion() {
        return this.c.getVersionNumber();
    }

    public BigInteger getSerialNumber() {
        return this.c.getSerialNumber().getValue();
    }

    public Principal getIssuerDN() {
        try {
            return new X509Principal(X500Name.getInstance(this.c.getIssuer().getEncoded()));
        } catch (IOException iOException) {
            return null;
        }
    }

    public X500Principal getIssuerX500Principal() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
            aSN1OutputStream.writeObject(this.c.getIssuer());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException iOException) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Principal getSubjectDN() {
        return new X509Principal(X500Name.getInstance(this.c.getSubject().toASN1Primitive()));
    }

    public X500Principal getSubjectX500Principal() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
            aSN1OutputStream.writeObject(this.c.getSubject());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException iOException) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getNotBefore() {
        return this.c.getStartDate().getDate();
    }

    public Date getNotAfter() {
        return this.c.getEndDate().getDate();
    }

    public byte[] getTBSCertificate() throws CertificateEncodingException {
        try {
            return this.c.getTBSCertificate().getEncoded("DER");
        } catch (IOException iOException) {
            throw new CertificateEncodingException(iOException.toString());
        }
    }

    public byte[] getSignature() {
        return this.c.getSignature().getOctets();
    }

    public String getSigAlgName() {
        return X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
    }

    public String getSigAlgOID() {
        return this.c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    public byte[] getSigAlgParams() {
        if (this.c.getSignatureAlgorithm().getParameters() != null) {
            try {
                return this.c.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded("DER");
            } catch (IOException iOException) {
                return null;
            }
        }
        return null;
    }

    public boolean[] getIssuerUniqueID() {
        DERBitString dERBitString = this.c.getTBSCertificate().getIssuerUniqueId();
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

    public boolean[] getSubjectUniqueID() {
        DERBitString dERBitString = this.c.getTBSCertificate().getSubjectUniqueId();
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

    public boolean[] getKeyUsage() {
        return this.keyUsage;
    }

    public List getExtendedKeyUsage() throws CertificateParsingException {
        byte[] byArray = this.getExtensionBytes("2.5.29.37");
        if (byArray != null) {
            try {
                ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
                ASN1Sequence aSN1Sequence = (ASN1Sequence)aSN1InputStream.readObject();
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i = 0; i != aSN1Sequence.size(); ++i) {
                    arrayList.add(((ASN1ObjectIdentifier)aSN1Sequence.getObjectAt(i)).getId());
                }
                return Collections.unmodifiableList(arrayList);
            } catch (Exception exception) {
                throw new CertificateParsingException("error processing extended key usage extension");
            }
        }
        return null;
    }

    public int getBasicConstraints() {
        if (this.basicConstraints != null) {
            if (this.basicConstraints.isCA()) {
                if (this.basicConstraints.getPathLenConstraint() == null) {
                    return Integer.MAX_VALUE;
                }
                return this.basicConstraints.getPathLenConstraint().intValue();
            }
            return -1;
        }
        return -1;
    }

    public Collection getSubjectAlternativeNames() throws CertificateParsingException {
        return X509CertificateObject.getAlternativeNames(this.getExtensionBytes(Extension.subjectAlternativeName.getId()));
    }

    public Collection getIssuerAlternativeNames() throws CertificateParsingException {
        return X509CertificateObject.getAlternativeNames(this.getExtensionBytes(Extension.issuerAlternativeName.getId()));
    }

    public Set getCriticalExtensionOIDs() {
        if (this.getVersion() == 3) {
            HashSet<String> hashSet = new HashSet<String>();
            Extensions extensions = this.c.getTBSCertificate().getExtensions();
            if (extensions != null) {
                Enumeration enumeration = extensions.oids();
                while (enumeration.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                    Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                    if (!extension.isCritical()) continue;
                    hashSet.add(aSN1ObjectIdentifier.getId());
                }
                return hashSet;
            }
        }
        return null;
    }

    private byte[] getExtensionBytes(String string) {
        Extension extension;
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions != null && (extension = extensions.getExtension(new ASN1ObjectIdentifier(string))) != null) {
            return extension.getExtnValue().getOctets();
        }
        return null;
    }

    public byte[] getExtensionValue(String string) {
        Extension extension;
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions != null && (extension = extensions.getExtension(new ASN1ObjectIdentifier(string))) != null) {
            try {
                return extension.getExtnValue().getEncoded();
            } catch (Exception exception) {
                throw new IllegalStateException("error parsing " + exception.toString());
            }
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs() {
        if (this.getVersion() == 3) {
            HashSet<String> hashSet = new HashSet<String>();
            Extensions extensions = this.c.getTBSCertificate().getExtensions();
            if (extensions != null) {
                Enumeration enumeration = extensions.oids();
                while (enumeration.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                    Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                    if (extension.isCritical()) continue;
                    hashSet.add(aSN1ObjectIdentifier.getId());
                }
                return hashSet;
            }
        }
        return null;
    }

    public boolean hasUnsupportedCriticalExtension() {
        Extensions extensions;
        if (this.getVersion() == 3 && (extensions = this.c.getTBSCertificate().getExtensions()) != null) {
            Enumeration enumeration = extensions.oids();
            while (enumeration.hasMoreElements()) {
                Extension extension;
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                if (aSN1ObjectIdentifier.equals(Extension.keyUsage) || aSN1ObjectIdentifier.equals(Extension.certificatePolicies) || aSN1ObjectIdentifier.equals(Extension.policyMappings) || aSN1ObjectIdentifier.equals(Extension.inhibitAnyPolicy) || aSN1ObjectIdentifier.equals(Extension.cRLDistributionPoints) || aSN1ObjectIdentifier.equals(Extension.issuingDistributionPoint) || aSN1ObjectIdentifier.equals(Extension.deltaCRLIndicator) || aSN1ObjectIdentifier.equals(Extension.policyConstraints) || aSN1ObjectIdentifier.equals(Extension.basicConstraints) || aSN1ObjectIdentifier.equals(Extension.subjectAlternativeName) || aSN1ObjectIdentifier.equals(Extension.nameConstraints) || !(extension = extensions.getExtension(aSN1ObjectIdentifier)).isCritical()) continue;
                return true;
            }
        }
        return false;
    }

    public PublicKey getPublicKey() {
        try {
            return BouncyCastleProvider.getPublicKey(this.c.getSubjectPublicKeyInfo());
        } catch (IOException iOException) {
            return null;
        }
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.c.getEncoded("DER");
        } catch (IOException iOException) {
            throw new CertificateEncodingException(iOException.toString());
        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof X509CertificateObject) {
            X509CertificateObject x509CertificateObject = (X509CertificateObject)object;
            if (this.hashValueSet && x509CertificateObject.hashValueSet && this.hashValue != x509CertificateObject.hashValue) {
                return false;
            }
            return this.c.equals(x509CertificateObject.c);
        }
        return super.equals(object);
    }

    public synchronized int hashCode() {
        if (!this.hashValueSet) {
            this.hashValue = super.hashCode();
            this.hashValueSet = true;
        }
        return this.hashValue;
    }

    public int originalHashCode() {
        try {
            int n = 0;
            byte[] byArray = this.getEncoded();
            for (int i = 1; i < byArray.length; ++i) {
                n += byArray[i] * i;
            }
            return n;
        } catch (CertificateEncodingException certificateEncodingException) {
            return 0;
        }
    }

    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("  [0]         Version: ").append(this.getVersion()).append(string);
        stringBuffer.append("         SerialNumber: ").append(this.getSerialNumber()).append(string);
        stringBuffer.append("             IssuerDN: ").append(this.getIssuerDN()).append(string);
        stringBuffer.append("           Start Date: ").append(this.getNotBefore()).append(string);
        stringBuffer.append("           Final Date: ").append(this.getNotAfter()).append(string);
        stringBuffer.append("            SubjectDN: ").append(this.getSubjectDN()).append(string);
        stringBuffer.append("           Public Key: ").append(this.getPublicKey()).append(string);
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
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions != null) {
            Enumeration enumeration = extensions.oids();
            if (enumeration.hasMoreElements()) {
                stringBuffer.append("       Extensions: \n");
            }
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (extension.getExtnValue() != null) {
                    byte[] byArray2 = extension.getExtnValue().getOctets();
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray2);
                    stringBuffer.append("                       critical(").append(extension.isCritical()).append(") ");
                    try {
                        if (aSN1ObjectIdentifier.equals(Extension.basicConstraints)) {
                            stringBuffer.append(BasicConstraints.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (aSN1ObjectIdentifier.equals(Extension.keyUsage)) {
                            stringBuffer.append(KeyUsage.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.netscapeCertType)) {
                            stringBuffer.append(new NetscapeCertType((DERBitString)aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.netscapeRevocationURL)) {
                            stringBuffer.append(new NetscapeRevocationURL((DERIA5String)aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.verisignCzagExtension)) {
                            stringBuffer.append(new VerisignCzagExtension((DERIA5String)aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ").append(ASN1Dump.dumpAsString(aSN1InputStream.readObject())).append(string);
                    } catch (Exception exception) {
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ").append("*****").append(string);
                    }
                    continue;
                }
                stringBuffer.append(string);
            }
        }
        return stringBuffer.toString();
    }

    public final void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        String string = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        try {
            signature = this.bcHelper.createSignature(string);
        } catch (Exception exception) {
            signature = Signature.getInstance(string);
        }
        this.checkSignature(publicKey, signature);
    }

    public final void verify(PublicKey publicKey, String string) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        String string2 = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        Signature signature = string != null ? Signature.getInstance(string2, string) : Signature.getInstance(string2);
        this.checkSignature(publicKey, signature);
    }

    public final void verify(PublicKey publicKey, Provider provider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String string = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        Signature signature = provider != null ? Signature.getInstance(string, provider) : Signature.getInstance(string);
        this.checkSignature(publicKey, signature);
    }

    private void checkSignature(PublicKey publicKey, Signature signature) throws CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (!this.isAlgIdEqual(this.c.getSignatureAlgorithm(), this.c.getTBSCertificate().getSignature())) {
            throw new CertificateException("signature algorithm in TBS cert not same as outer cert");
        }
        ASN1Encodable aSN1Encodable = this.c.getSignatureAlgorithm().getParameters();
        X509SignatureUtil.setSignatureParameters(signature, aSN1Encodable);
        signature.initVerify(publicKey);
        signature.update(this.getTBSCertificate());
        if (!signature.verify(this.getSignature())) {
            throw new SignatureException("certificate does not verify with supplied key");
        }
    }

    private boolean isAlgIdEqual(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (!algorithmIdentifier.getAlgorithm().equals(algorithmIdentifier2.getAlgorithm())) {
            return false;
        }
        if (algorithmIdentifier.getParameters() == null) {
            return algorithmIdentifier2.getParameters() == null || algorithmIdentifier2.getParameters().equals(DERNull.INSTANCE);
        }
        if (algorithmIdentifier2.getParameters() == null) {
            return algorithmIdentifier.getParameters() == null || algorithmIdentifier.getParameters().equals(DERNull.INSTANCE);
        }
        return algorithmIdentifier.getParameters().equals(algorithmIdentifier2.getParameters());
    }

    private static Collection getAlternativeNames(byte[] byArray) throws CertificateParsingException {
        if (byArray == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration enumeration = ASN1Sequence.getInstance(byArray).getObjects();
            block11: while (enumeration.hasMoreElements()) {
                GeneralName generalName = GeneralName.getInstance(enumeration.nextElement());
                ArrayList<Object> arrayList2 = new ArrayList<Object>();
                arrayList2.add(Integers.valueOf(generalName.getTagNo()));
                switch (generalName.getTagNo()) {
                    case 0: 
                    case 3: 
                    case 5: {
                        arrayList2.add(generalName.getEncoded());
                        break;
                    }
                    case 4: {
                        arrayList2.add(X500Name.getInstance(RFC4519Style.INSTANCE, generalName.getName()).toString());
                        break;
                    }
                    case 1: 
                    case 2: 
                    case 6: {
                        arrayList2.add(((ASN1String)((Object)generalName.getName())).getString());
                        break;
                    }
                    case 8: {
                        arrayList2.add(ASN1ObjectIdentifier.getInstance(generalName.getName()).getId());
                        break;
                    }
                    case 7: {
                        String string;
                        byte[] byArray2 = DEROctetString.getInstance(generalName.getName()).getOctets();
                        try {
                            string = InetAddress.getByAddress(byArray2).getHostAddress();
                        } catch (UnknownHostException unknownHostException) {
                            continue block11;
                        }
                        arrayList2.add(string);
                        break;
                    }
                    default: {
                        throw new IOException("Bad tag number: " + generalName.getTagNo());
                    }
                }
                arrayList.add(Collections.unmodifiableList(arrayList2));
            }
            if (arrayList.size() == 0) {
                return null;
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (Exception exception) {
            throw new CertificateParsingException(exception.getMessage());
        }
    }
}

