/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.jcajce.provider.asymmetric.x509.PEMUtil;
import org.bouncycastle.jcajce.provider.asymmetric.x509.PKIXCertPath;
import org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLObject;
import org.bouncycastle.jcajce.provider.asymmetric.x509.X509CertificateObject;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.io.Streams;

public class CertificateFactory
extends CertificateFactorySpi {
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private static final PEMUtil PEM_CERT_PARSER = new PEMUtil("CERTIFICATE");
    private static final PEMUtil PEM_CRL_PARSER = new PEMUtil("CRL");
    private static final PEMUtil PEM_PKCS7_PARSER = new PEMUtil("PKCS7");
    private ASN1Set sData = null;
    private int sDataObjectCount = 0;
    private InputStream currentStream = null;
    private ASN1Set sCrlData = null;
    private int sCrlDataObjectCount = 0;
    private InputStream currentCrlStream = null;

    private java.security.cert.Certificate readDERCertificate(ASN1InputStream aSN1InputStream) throws IOException, CertificateParsingException {
        return this.getCertificate(ASN1Sequence.getInstance(aSN1InputStream.readObject()));
    }

    private java.security.cert.Certificate readPEMCertificate(InputStream inputStream) throws IOException, CertificateParsingException {
        return this.getCertificate(PEM_CERT_PARSER.readPEMObject(inputStream));
    }

    private java.security.cert.Certificate getCertificate(ASN1Sequence aSN1Sequence) throws CertificateParsingException {
        if (aSN1Sequence == null) {
            return null;
        }
        if (aSN1Sequence.size() > 1 && aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.sData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(1), true)).getCertificates();
            return this.getCertificate();
        }
        return new X509CertificateObject(this.bcHelper, Certificate.getInstance(aSN1Sequence));
    }

    private java.security.cert.Certificate getCertificate() throws CertificateParsingException {
        if (this.sData != null) {
            while (this.sDataObjectCount < this.sData.size()) {
                ASN1Encodable aSN1Encodable;
                if (!((aSN1Encodable = this.sData.getObjectAt(this.sDataObjectCount++)) instanceof ASN1Sequence)) continue;
                return new X509CertificateObject(this.bcHelper, Certificate.getInstance(aSN1Encodable));
            }
        }
        return null;
    }

    protected CRL createCRL(CertificateList certificateList) throws CRLException {
        return new X509CRLObject(this.bcHelper, certificateList);
    }

    private CRL readPEMCRL(InputStream inputStream) throws IOException, CRLException {
        return this.getCRL(PEM_CRL_PARSER.readPEMObject(inputStream));
    }

    private CRL readDERCRL(ASN1InputStream aSN1InputStream) throws IOException, CRLException {
        return this.getCRL(ASN1Sequence.getInstance(aSN1InputStream.readObject()));
    }

    private CRL getCRL(ASN1Sequence aSN1Sequence) throws CRLException {
        if (aSN1Sequence == null) {
            return null;
        }
        if (aSN1Sequence.size() > 1 && aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.sCrlData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(1), true)).getCRLs();
            return this.getCRL();
        }
        return this.createCRL(CertificateList.getInstance(aSN1Sequence));
    }

    private CRL getCRL() throws CRLException {
        if (this.sCrlData == null || this.sCrlDataObjectCount >= this.sCrlData.size()) {
            return null;
        }
        return this.createCRL(CertificateList.getInstance(this.sCrlData.getObjectAt(this.sCrlDataObjectCount++)));
    }

    public java.security.cert.Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        if (this.currentStream == null) {
            this.currentStream = inputStream;
            this.sData = null;
            this.sDataObjectCount = 0;
        } else if (this.currentStream != inputStream) {
            this.currentStream = inputStream;
            this.sData = null;
            this.sDataObjectCount = 0;
        }
        try {
            if (this.sData != null) {
                if (this.sDataObjectCount != this.sData.size()) {
                    return this.getCertificate();
                }
                this.sData = null;
                this.sDataObjectCount = 0;
                return null;
            }
            InputStream inputStream2 = inputStream.markSupported() ? inputStream : new ByteArrayInputStream(Streams.readAll(inputStream));
            inputStream2.mark(1);
            int n = inputStream2.read();
            if (n == -1) {
                return null;
            }
            inputStream2.reset();
            if (n != 48) {
                return this.readPEMCertificate(inputStream2);
            }
            return this.readDERCertificate(new ASN1InputStream(inputStream2));
        } catch (Exception exception) {
            throw new ExCertificateException("parsing issue: " + exception.getMessage(), exception);
        }
    }

    public Collection engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        java.security.cert.Certificate certificate;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ArrayList<java.security.cert.Certificate> arrayList = new ArrayList<java.security.cert.Certificate>();
        while ((certificate = this.engineGenerateCertificate(bufferedInputStream)) != null) {
            arrayList.add(certificate);
        }
        return arrayList;
    }

    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        if (this.currentCrlStream == null) {
            this.currentCrlStream = inputStream;
            this.sCrlData = null;
            this.sCrlDataObjectCount = 0;
        } else if (this.currentCrlStream != inputStream) {
            this.currentCrlStream = inputStream;
            this.sCrlData = null;
            this.sCrlDataObjectCount = 0;
        }
        try {
            if (this.sCrlData != null) {
                if (this.sCrlDataObjectCount != this.sCrlData.size()) {
                    return this.getCRL();
                }
                this.sCrlData = null;
                this.sCrlDataObjectCount = 0;
                return null;
            }
            InputStream inputStream2 = inputStream.markSupported() ? inputStream : new ByteArrayInputStream(Streams.readAll(inputStream));
            inputStream2.mark(1);
            int n = inputStream2.read();
            if (n == -1) {
                return null;
            }
            inputStream2.reset();
            if (n != 48) {
                return this.readPEMCRL(inputStream2);
            }
            return this.readDERCRL(new ASN1InputStream(inputStream2, true));
        } catch (CRLException cRLException) {
            throw cRLException;
        } catch (Exception exception) {
            throw new CRLException(exception.toString());
        }
    }

    public Collection engineGenerateCRLs(InputStream inputStream) throws CRLException {
        CRL cRL;
        ArrayList<CRL> arrayList = new ArrayList<CRL>();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        while ((cRL = this.engineGenerateCRL(bufferedInputStream)) != null) {
            arrayList.add(cRL);
        }
        return arrayList;
    }

    public Iterator engineGetCertPathEncodings() {
        return PKIXCertPath.certPathEncodings.iterator();
    }

    public CertPath engineGenerateCertPath(InputStream inputStream) throws CertificateException {
        return this.engineGenerateCertPath(inputStream, "PkiPath");
    }

    public CertPath engineGenerateCertPath(InputStream inputStream, String string) throws CertificateException {
        return new PKIXCertPath(inputStream, string);
    }

    public CertPath engineGenerateCertPath(List list) throws CertificateException {
        for (Object e : list) {
            if (e == null || e instanceof X509Certificate) continue;
            throw new CertificateException("list contains non X509Certificate object while creating CertPath\n" + e.toString());
        }
        return new PKIXCertPath(list);
    }

    private class ExCertificateException
    extends CertificateException {
        private Throwable cause;

        public ExCertificateException(Throwable throwable) {
            this.cause = throwable;
        }

        public ExCertificateException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}

