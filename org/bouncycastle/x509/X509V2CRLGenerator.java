/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.V2TBSCertListGenerator;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509ExtensionsGenerator;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.X509CRLObject;
import org.bouncycastle.x509.X509Util;

public class X509V2CRLGenerator {
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private V2TBSCertListGenerator tbsGen = new V2TBSCertListGenerator();
    private ASN1ObjectIdentifier sigOID;
    private AlgorithmIdentifier sigAlgId;
    private String signatureAlgorithm;
    private X509ExtensionsGenerator extGenerator = new X509ExtensionsGenerator();

    public void reset() {
        this.tbsGen = new V2TBSCertListGenerator();
        this.extGenerator.reset();
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

    public void setThisUpdate(Date date) {
        this.tbsGen.setThisUpdate(new Time(date));
    }

    public void setNextUpdate(Date date) {
        this.tbsGen.setNextUpdate(new Time(date));
    }

    public void addCRLEntry(BigInteger bigInteger, Date date, int n) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), n);
    }

    public void addCRLEntry(BigInteger bigInteger, Date date, int n, Date date2) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), n, new ASN1GeneralizedTime(date2));
    }

    public void addCRLEntry(BigInteger bigInteger, Date date, X509Extensions x509Extensions) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), Extensions.getInstance(x509Extensions));
    }

    public void addCRL(X509CRL x509CRL) throws CRLException {
        Set<? extends X509CRLEntry> set = x509CRL.getRevokedCertificates();
        if (set != null) {
            for (X509CRLEntry x509CRLEntry : set) {
                ASN1InputStream aSN1InputStream = new ASN1InputStream(x509CRLEntry.getEncoded());
                try {
                    this.tbsGen.addCRLEntry(ASN1Sequence.getInstance(aSN1InputStream.readObject()));
                } catch (IOException iOException) {
                    throw new CRLException("exception processing encoding of CRL: " + iOException.toString());
                }
            }
        }
    }

    public void setSignatureAlgorithm(String string) {
        this.signatureAlgorithm = string;
        try {
            this.sigOID = X509Util.getAlgorithmOID(string);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
        this.sigAlgId = X509Util.getSigAlgID(this.sigOID, string);
        this.tbsGen.setSignature(this.sigAlgId);
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

    public X509CRL generateX509CRL(PrivateKey privateKey) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return this.generateX509CRL(privateKey, "BC", null);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509CRL generateX509CRL(PrivateKey privateKey, SecureRandom secureRandom) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return this.generateX509CRL(privateKey, "BC", secureRandom);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509CRL generateX509CRL(PrivateKey privateKey, String string) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
        return this.generateX509CRL(privateKey, string, null);
    }

    public X509CRL generateX509CRL(PrivateKey privateKey, String string, SecureRandom secureRandom) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
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

    public X509CRL generate(PrivateKey privateKey) throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return this.generate(privateKey, (SecureRandom)null);
    }

    public X509CRL generate(PrivateKey privateKey, SecureRandom secureRandom) throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] byArray;
        TBSCertList tBSCertList = this.generateCertList();
        try {
            byArray = X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, privateKey, secureRandom, tBSCertList);
        } catch (IOException iOException) {
            throw new ExtCRLException("cannot generate CRL encoding", iOException);
        }
        return this.generateJcaObject(tBSCertList, byArray);
    }

    public X509CRL generate(PrivateKey privateKey, String string) throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return this.generate(privateKey, string, null);
    }

    public X509CRL generate(PrivateKey privateKey, String string, SecureRandom secureRandom) throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] byArray;
        TBSCertList tBSCertList = this.generateCertList();
        try {
            byArray = X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, string, privateKey, secureRandom, tBSCertList);
        } catch (IOException iOException) {
            throw new ExtCRLException("cannot generate CRL encoding", iOException);
        }
        return this.generateJcaObject(tBSCertList, byArray);
    }

    private TBSCertList generateCertList() {
        if (!this.extGenerator.isEmpty()) {
            this.tbsGen.setExtensions(this.extGenerator.generate());
        }
        return this.tbsGen.generateTBSCertList();
    }

    private X509CRL generateJcaObject(TBSCertList tBSCertList, byte[] byArray) throws CRLException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(tBSCertList);
        aSN1EncodableVector.add(this.sigAlgId);
        aSN1EncodableVector.add(new DERBitString(byArray));
        return new X509CRLObject(new CertificateList(new DERSequence(aSN1EncodableVector)));
    }

    public Iterator getSignatureAlgNames() {
        return X509Util.getAlgNames();
    }

    private static class ExtCRLException
    extends CRLException {
        Throwable cause;

        ExtCRLException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}

