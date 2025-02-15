/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.V3TBSCertificateGenerator;
import org.bouncycastle.asn1.x509.X509ExtensionsGenerator;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.x509.ExtCertificateEncodingException;
import org.bouncycastle.x509.X509Util;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

public class X509V3CertificateGenerator {
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private final CertificateFactory certificateFactory = new CertificateFactory();
    private V3TBSCertificateGenerator tbsGen = new V3TBSCertificateGenerator();
    private ASN1ObjectIdentifier sigOID;
    private AlgorithmIdentifier sigAlgId;
    private String signatureAlgorithm;
    private X509ExtensionsGenerator extGenerator = new X509ExtensionsGenerator();

    public void reset() {
        this.tbsGen = new V3TBSCertificateGenerator();
        this.extGenerator.reset();
    }

    public void setSerialNumber(BigInteger bigInteger) {
        if (bigInteger.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("serial number must be a positive integer");
        }
        this.tbsGen.setSerialNumber(new ASN1Integer(bigInteger));
    }

    public void setIssuerDN(X500Principal x500Principal) {
        try {
            this.tbsGen.setIssuer(new X509Principal(x500Principal.getEncoded()));
        } catch (IOException iOException) {
            throw new IllegalArgumentException("can't process principal: " + iOException);
        }
    }

    public void setIssuerDN(X509Name x509Name) {
        this.tbsGen.setIssuer(x509Name);
    }

    public void setNotBefore(Date date) {
        this.tbsGen.setStartDate(new Time(date));
    }

    public void setNotAfter(Date date) {
        this.tbsGen.setEndDate(new Time(date));
    }

    public void setSubjectDN(X500Principal x500Principal) {
        try {
            this.tbsGen.setSubject(new X509Principal(x500Principal.getEncoded()));
        } catch (IOException iOException) {
            throw new IllegalArgumentException("can't process principal: " + iOException);
        }
    }

    public void setSubjectDN(X509Name x509Name) {
        this.tbsGen.setSubject(x509Name);
    }

    public void setPublicKey(PublicKey publicKey) throws IllegalArgumentException {
        try {
            this.tbsGen.setSubjectPublicKeyInfo(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(publicKey.getEncoded()).readObject()));
        } catch (Exception exception) {
            throw new IllegalArgumentException("unable to process key - " + exception.toString());
        }
    }

    public void setSignatureAlgorithm(String string) {
        this.signatureAlgorithm = string;
        try {
            this.sigOID = X509Util.getAlgorithmOID(string);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Unknown signature type requested: " + string);
        }
        this.sigAlgId = X509Util.getSigAlgID(this.sigOID, string);
        this.tbsGen.setSignature(this.sigAlgId);
    }

    public void setSubjectUniqueID(boolean[] blArray) {
        this.tbsGen.setSubjectUniqueID(this.booleanToBitString(blArray));
    }

    public void setIssuerUniqueID(boolean[] blArray) {
        this.tbsGen.setIssuerUniqueID(this.booleanToBitString(blArray));
    }

    private DERBitString booleanToBitString(boolean[] blArray) {
        int n;
        byte[] byArray = new byte[(blArray.length + 7) / 8];
        for (n = 0; n != blArray.length; ++n) {
            int n2 = n / 8;
            byArray[n2] = (byte)(byArray[n2] | (blArray[n] ? 1 << 7 - n % 8 : 0));
        }
        n = blArray.length % 8;
        if (n == 0) {
            return new DERBitString(byArray);
        }
        return new DERBitString(byArray, 8 - n);
    }

    public void addExtension(String string, boolean bl, ASN1Encodable aSN1Encodable) {
        this.addExtension(new ASN1ObjectIdentifier(string), bl, aSN1Encodable);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), bl, aSN1Encodable);
    }

    public void addExtension(String string, boolean bl, byte[] byArray) {
        this.addExtension(new ASN1ObjectIdentifier(string), bl, byArray);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), bl, byArray);
    }

    public void copyAndAddExtension(String string, boolean bl, X509Certificate x509Certificate) throws CertificateParsingException {
        byte[] byArray = x509Certificate.getExtensionValue(string);
        if (byArray == null) {
            throw new CertificateParsingException("extension " + string + " not present");
        }
        try {
            ASN1Primitive aSN1Primitive = X509ExtensionUtil.fromExtensionValue(byArray);
            this.addExtension(string, bl, (ASN1Encodable)aSN1Primitive);
        } catch (IOException iOException) {
            throw new CertificateParsingException(iOException.toString());
        }
    }

    public void copyAndAddExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, X509Certificate x509Certificate) throws CertificateParsingException {
        this.copyAndAddExtension(aSN1ObjectIdentifier.getId(), bl, x509Certificate);
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return this.generateX509Certificate(privateKey, "BC", null);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, SecureRandom secureRandom) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return this.generateX509Certificate(privateKey, "BC", secureRandom);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, String string) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
        return this.generateX509Certificate(privateKey, string, null);
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, String string, SecureRandom secureRandom) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
        try {
            return this.generate(privateKey, string, secureRandom);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw noSuchProviderException;
        } catch (SignatureException signatureException) {
            throw signatureException;
        } catch (InvalidKeyException invalidKeyException) {
            throw invalidKeyException;
        } catch (GeneralSecurityException generalSecurityException) {
            throw new SecurityException("exception: " + generalSecurityException);
        }
    }

    public X509Certificate generate(PrivateKey privateKey) throws CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return this.generate(privateKey, (SecureRandom)null);
    }

    public X509Certificate generate(PrivateKey privateKey, SecureRandom secureRandom) throws CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] byArray;
        TBSCertificate tBSCertificate = this.generateTbsCert();
        try {
            byArray = X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, privateKey, secureRandom, tBSCertificate);
        } catch (IOException iOException) {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", iOException);
        }
        try {
            return this.generateJcaObject(tBSCertificate, byArray);
        } catch (Exception exception) {
            throw new ExtCertificateEncodingException("exception producing certificate object", exception);
        }
    }

    public X509Certificate generate(PrivateKey privateKey, String string) throws CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return this.generate(privateKey, string, null);
    }

    public X509Certificate generate(PrivateKey privateKey, String string, SecureRandom secureRandom) throws CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] byArray;
        TBSCertificate tBSCertificate = this.generateTbsCert();
        try {
            byArray = X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, string, privateKey, secureRandom, tBSCertificate);
        } catch (IOException iOException) {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", iOException);
        }
        try {
            return this.generateJcaObject(tBSCertificate, byArray);
        } catch (Exception exception) {
            throw new ExtCertificateEncodingException("exception producing certificate object", exception);
        }
    }

    private TBSCertificate generateTbsCert() {
        if (!this.extGenerator.isEmpty()) {
            this.tbsGen.setExtensions(this.extGenerator.generate());
        }
        return this.tbsGen.generateTBSCertificate();
    }

    private X509Certificate generateJcaObject(TBSCertificate tBSCertificate, byte[] byArray) throws Exception {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(tBSCertificate);
        aSN1EncodableVector.add(this.sigAlgId);
        aSN1EncodableVector.add(new DERBitString(byArray));
        return (X509Certificate)this.certificateFactory.engineGenerateCertificate(new ByteArrayInputStream(new DERSequence(aSN1EncodableVector).getEncoded("DER")));
    }

    public Iterator getSignatureAlgNames() {
        return X509Util.getAlgNames();
    }
}

