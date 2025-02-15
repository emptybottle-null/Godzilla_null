/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class PKIXCertPath
extends CertPath {
    private final JcaJceHelper helper;
    static final List certPathEncodings;
    private List certificates;

    private List sortCerts(List list) {
        int n;
        Serializable serializable;
        if (list.size() < 2) {
            return list;
        }
        X500Principal x500Principal = ((X509Certificate)list.get(0)).getIssuerX500Principal();
        boolean bl = true;
        for (int i = 1; i != list.size(); ++i) {
            serializable = (X509Certificate)list.get(i);
            if (!x500Principal.equals(serializable.getSubjectX500Principal())) {
                bl = false;
                break;
            }
            x500Principal = ((X509Certificate)list.get(i)).getIssuerX500Principal();
        }
        if (bl) {
            return list;
        }
        ArrayList<X509Certificate> arrayList = new ArrayList<X509Certificate>(list.size());
        serializable = new ArrayList(list);
        for (n = 0; n < list.size(); ++n) {
            X509Certificate x509Certificate = (X509Certificate)list.get(n);
            boolean bl2 = false;
            X500Principal x500Principal2 = x509Certificate.getSubjectX500Principal();
            for (int i = 0; i != list.size(); ++i) {
                X509Certificate x509Certificate2 = (X509Certificate)list.get(i);
                if (!x509Certificate2.getIssuerX500Principal().equals(x500Principal2)) continue;
                bl2 = true;
                break;
            }
            if (bl2) continue;
            arrayList.add(x509Certificate);
            list.remove(n);
        }
        if (arrayList.size() > 1) {
            return serializable;
        }
        block3: for (n = 0; n != arrayList.size(); ++n) {
            x500Principal = ((X509Certificate)arrayList.get(n)).getIssuerX500Principal();
            for (int i = 0; i < list.size(); ++i) {
                X509Certificate x509Certificate = (X509Certificate)list.get(i);
                if (!x500Principal.equals(x509Certificate.getSubjectX500Principal())) continue;
                arrayList.add(x509Certificate);
                list.remove(i);
                continue block3;
            }
        }
        if (list.size() > 0) {
            return serializable;
        }
        return arrayList;
    }

    PKIXCertPath(List list) {
        super("X.509");
        this.helper = new BCJcaJceHelper();
        this.certificates = this.sortCerts(new ArrayList(list));
    }

    PKIXCertPath(InputStream inputStream, String string) throws CertificateException {
        block8: {
            super("X.509");
            this.helper = new BCJcaJceHelper();
            try {
                if (string.equalsIgnoreCase("PkiPath")) {
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(inputStream);
                    ASN1Primitive aSN1Primitive = aSN1InputStream.readObject();
                    if (!(aSN1Primitive instanceof ASN1Sequence)) {
                        throw new CertificateException("input stream does not contain a ASN1 SEQUENCE while reading PkiPath encoded data to load CertPath");
                    }
                    Enumeration enumeration = ((ASN1Sequence)aSN1Primitive).getObjects();
                    this.certificates = new ArrayList();
                    CertificateFactory certificateFactory = this.helper.createCertificateFactory("X.509");
                    while (enumeration.hasMoreElements()) {
                        ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
                        byte[] byArray = aSN1Encodable.toASN1Primitive().getEncoded("DER");
                        this.certificates.add(0, certificateFactory.generateCertificate(new ByteArrayInputStream(byArray)));
                    }
                    break block8;
                }
                if (string.equalsIgnoreCase("PKCS7") || string.equalsIgnoreCase("PEM")) {
                    Certificate certificate;
                    inputStream = new BufferedInputStream(inputStream);
                    this.certificates = new ArrayList();
                    CertificateFactory certificateFactory = this.helper.createCertificateFactory("X.509");
                    while ((certificate = certificateFactory.generateCertificate(inputStream)) != null) {
                        this.certificates.add(certificate);
                    }
                    break block8;
                }
                throw new CertificateException("unsupported encoding: " + string);
            } catch (IOException iOException) {
                throw new CertificateException("IOException throw while decoding CertPath:\n" + iOException.toString());
            } catch (NoSuchProviderException noSuchProviderException) {
                throw new CertificateException("BouncyCastle provider not found while trying to get a CertificateFactory:\n" + noSuchProviderException.toString());
            }
        }
        this.certificates = this.sortCerts(this.certificates);
    }

    public Iterator getEncodings() {
        return certPathEncodings.iterator();
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        Object e;
        Iterator iterator = this.getEncodings();
        if (iterator.hasNext() && (e = iterator.next()) instanceof String) {
            return this.getEncoded((String)e);
        }
        return null;
    }

    public byte[] getEncoded(String string) throws CertificateEncodingException {
        if (string.equalsIgnoreCase("PkiPath")) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ListIterator listIterator = this.certificates.listIterator(this.certificates.size());
            while (listIterator.hasPrevious()) {
                aSN1EncodableVector.add(this.toASN1Object((X509Certificate)listIterator.previous()));
            }
            return this.toDEREncoded(new DERSequence(aSN1EncodableVector));
        }
        if (string.equalsIgnoreCase("PKCS7")) {
            ContentInfo contentInfo = new ContentInfo(PKCSObjectIdentifiers.data, null);
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (int i = 0; i != this.certificates.size(); ++i) {
                aSN1EncodableVector.add(this.toASN1Object((X509Certificate)this.certificates.get(i)));
            }
            SignedData signedData = new SignedData(new ASN1Integer(1L), new DERSet(), contentInfo, new DERSet(aSN1EncodableVector), null, new DERSet());
            return this.toDEREncoded(new ContentInfo(PKCSObjectIdentifiers.signedData, signedData));
        }
        if (string.equalsIgnoreCase("PEM")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(byteArrayOutputStream));
            try {
                for (int i = 0; i != this.certificates.size(); ++i) {
                    pemWriter.writeObject(new PemObject("CERTIFICATE", ((X509Certificate)this.certificates.get(i)).getEncoded()));
                }
                pemWriter.close();
            } catch (Exception exception) {
                throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
            }
            return byteArrayOutputStream.toByteArray();
        }
        throw new CertificateEncodingException("unsupported encoding: " + string);
    }

    public List getCertificates() {
        return Collections.unmodifiableList(new ArrayList(this.certificates));
    }

    private ASN1Primitive toASN1Object(X509Certificate x509Certificate) throws CertificateEncodingException {
        try {
            return new ASN1InputStream(x509Certificate.getEncoded()).readObject();
        } catch (Exception exception) {
            throw new CertificateEncodingException("Exception while encoding certificate: " + exception.toString());
        }
    }

    private byte[] toDEREncoded(ASN1Encodable aSN1Encodable) throws CertificateEncodingException {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded("DER");
        } catch (IOException iOException) {
            throw new CertificateEncodingException("Exception thrown: " + iOException);
        }
    }

    static {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("PkiPath");
        arrayList.add("PEM");
        arrayList.add("PKCS7");
        certPathEncodings = Collections.unmodifiableList(arrayList);
    }
}

