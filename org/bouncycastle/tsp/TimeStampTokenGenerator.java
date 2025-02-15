/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerationException;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TSPUtil;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;

public class TimeStampTokenGenerator {
    public static final int R_SECONDS = 0;
    public static final int R_TENTHS_OF_SECONDS = 1;
    public static final int R_MICROSECONDS = 2;
    public static final int R_MILLISECONDS = 3;
    private int resolution = 0;
    private Locale locale = null;
    private int accuracySeconds = -1;
    private int accuracyMillis = -1;
    private int accuracyMicros = -1;
    boolean ordering = false;
    GeneralName tsa = null;
    private ASN1ObjectIdentifier tsaPolicyOID;
    private List certs = new ArrayList();
    private List crls = new ArrayList();
    private List attrCerts = new ArrayList();
    private Map otherRevoc = new HashMap();
    private SignerInfoGenerator signerInfoGen;

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier) throws IllegalArgumentException, TSPException {
        this(signerInfoGenerator, digestCalculator, aSN1ObjectIdentifier, false);
    }

    public TimeStampTokenGenerator(final SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl) throws IllegalArgumentException, TSPException {
        this.signerInfoGen = signerInfoGenerator;
        this.tsaPolicyOID = aSN1ObjectIdentifier;
        if (!signerInfoGenerator.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("SignerInfoGenerator must have an associated certificate");
        }
        X509CertificateHolder x509CertificateHolder = signerInfoGenerator.getAssociatedCertificate();
        TSPUtil.validateCertificate(x509CertificateHolder);
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(x509CertificateHolder.getEncoded());
            outputStream.close();
            if (digestCalculator.getAlgorithmIdentifier().getAlgorithm().equals(OIWObjectIdentifiers.idSHA1)) {
                final ESSCertID eSSCertID = new ESSCertID(digestCalculator.getDigest(), bl ? new IssuerSerial(new GeneralNames(new GeneralName(x509CertificateHolder.getIssuer())), x509CertificateHolder.getSerialNumber()) : null);
                this.signerInfoGen = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator(){

                    public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
                        AttributeTable attributeTable = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        if (attributeTable.get(PKCSObjectIdentifiers.id_aa_signingCertificate) == null) {
                            return attributeTable.add(PKCSObjectIdentifiers.id_aa_signingCertificate, new SigningCertificate(eSSCertID));
                        }
                        return attributeTable;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            } else {
                AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(digestCalculator.getAlgorithmIdentifier().getAlgorithm());
                final ESSCertIDv2 eSSCertIDv2 = new ESSCertIDv2(algorithmIdentifier, digestCalculator.getDigest(), bl ? new IssuerSerial(new GeneralNames(new GeneralName(x509CertificateHolder.getIssuer())), new ASN1Integer(x509CertificateHolder.getSerialNumber())) : null);
                this.signerInfoGen = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator(){

                    public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
                        AttributeTable attributeTable = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        if (attributeTable.get(PKCSObjectIdentifiers.id_aa_signingCertificateV2) == null) {
                            return attributeTable.add(PKCSObjectIdentifiers.id_aa_signingCertificateV2, new SigningCertificateV2(eSSCertIDv2));
                        }
                        return attributeTable;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            }
        } catch (IOException iOException) {
            throw new TSPException("Exception processing certificate.", iOException);
        }
    }

    public void addCertificates(Store store) {
        this.certs.addAll(store.getMatches(null));
    }

    public void addCRLs(Store store) {
        this.crls.addAll(store.getMatches(null));
    }

    public void addAttributeCertificates(Store store) {
        this.attrCerts.addAll(store.getMatches(null));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        this.otherRevoc.put(aSN1ObjectIdentifier, store.getMatches(null));
    }

    public void setResolution(int n) {
        this.resolution = n;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setAccuracySeconds(int n) {
        this.accuracySeconds = n;
    }

    public void setAccuracyMillis(int n) {
        this.accuracyMillis = n;
    }

    public void setAccuracyMicros(int n) {
        this.accuracyMicros = n;
    }

    public void setOrdering(boolean bl) {
        this.ordering = bl;
    }

    public void setTSA(GeneralName generalName) {
        this.tsa = generalName;
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        return this.generate(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, Extensions extensions) throws TSPException {
        Object object;
        Object object2;
        ASN1Primitive aSN1Primitive;
        ASN1Integer aSN1Integer;
        ASN1Primitive aSN1Primitive2;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = timeStampRequest.getMessageImprintAlgOID();
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
        MessageImprint messageImprint = new MessageImprint(algorithmIdentifier, timeStampRequest.getMessageImprintDigest());
        Accuracy accuracy = null;
        if (this.accuracySeconds > 0 || this.accuracyMillis > 0 || this.accuracyMicros > 0) {
            aSN1Primitive2 = null;
            if (this.accuracySeconds > 0) {
                aSN1Primitive2 = new ASN1Integer(this.accuracySeconds);
            }
            aSN1Integer = null;
            if (this.accuracyMillis > 0) {
                aSN1Integer = new ASN1Integer(this.accuracyMillis);
            }
            aSN1Primitive = null;
            if (this.accuracyMicros > 0) {
                aSN1Primitive = new ASN1Integer(this.accuracyMicros);
            }
            accuracy = new Accuracy((ASN1Integer)aSN1Primitive2, aSN1Integer, (ASN1Integer)aSN1Primitive);
        }
        aSN1Primitive2 = null;
        if (this.ordering) {
            aSN1Primitive2 = ASN1Boolean.getInstance(this.ordering);
        }
        aSN1Integer = null;
        if (timeStampRequest.getNonce() != null) {
            aSN1Integer = new ASN1Integer(timeStampRequest.getNonce());
        }
        aSN1Primitive = this.tsaPolicyOID;
        if (timeStampRequest.getReqPolicy() != null) {
            aSN1Primitive = timeStampRequest.getReqPolicy();
        }
        Extensions extensions2 = timeStampRequest.getExtensions();
        if (extensions != null) {
            object2 = new ExtensionsGenerator();
            if (extensions2 != null) {
                object = extensions2.oids();
                while (object.hasMoreElements()) {
                    ((ExtensionsGenerator)object2).addExtension(extensions2.getExtension(ASN1ObjectIdentifier.getInstance(object.nextElement())));
                }
            }
            object = extensions.oids();
            while (object.hasMoreElements()) {
                ((ExtensionsGenerator)object2).addExtension(extensions.getExtension(ASN1ObjectIdentifier.getInstance(object.nextElement())));
            }
            extensions2 = ((ExtensionsGenerator)object2).generate();
        }
        object2 = this.resolution == 0 ? (this.locale == null ? new ASN1GeneralizedTime(date) : new ASN1GeneralizedTime(date, this.locale)) : this.createGeneralizedTime(date);
        object = new TSTInfo((ASN1ObjectIdentifier)aSN1Primitive, messageImprint, new ASN1Integer(bigInteger), (ASN1GeneralizedTime)object2, accuracy, (ASN1Boolean)aSN1Primitive2, aSN1Integer, this.tsa, extensions2);
        try {
            Encodable encodable2;
            CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
            if (timeStampRequest.getCertReq()) {
                cMSSignedDataGenerator.addCertificates(new CollectionStore(this.certs));
                cMSSignedDataGenerator.addAttributeCertificates(new CollectionStore(this.attrCerts));
            }
            cMSSignedDataGenerator.addCRLs(new CollectionStore(this.crls));
            if (!this.otherRevoc.isEmpty()) {
                for (Encodable encodable2 : this.otherRevoc.keySet()) {
                    cMSSignedDataGenerator.addOtherRevocationInfo((ASN1ObjectIdentifier)encodable2, new CollectionStore((Collection)this.otherRevoc.get(encodable2)));
                }
            }
            cMSSignedDataGenerator.addSignerInfoGenerator(this.signerInfoGen);
            Object object3 = ((ASN1Object)object).getEncoded("DER");
            encodable2 = cMSSignedDataGenerator.generate(new CMSProcessableByteArray(PKCSObjectIdentifiers.id_ct_TSTInfo, (byte[])object3), true);
            return new TimeStampToken((CMSSignedData)encodable2);
        } catch (CMSException cMSException) {
            throw new TSPException("Error generating time-stamp token", cMSException);
        } catch (IOException iOException) {
            throw new TSPException("Exception encoding info", iOException);
        }
    }

    private ASN1GeneralizedTime createGeneralizedTime(Date date) throws TSPException {
        String string = "yyyyMMddHHmmss.SSS";
        SimpleDateFormat simpleDateFormat = this.locale == null ? new SimpleDateFormat(string) : new SimpleDateFormat(string, this.locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(date));
        int n = stringBuilder.indexOf(".");
        if (n < 0) {
            stringBuilder.append("Z");
            return new ASN1GeneralizedTime(stringBuilder.toString());
        }
        switch (this.resolution) {
            case 1: {
                if (stringBuilder.length() <= n + 2) break;
                stringBuilder.delete(n + 2, stringBuilder.length());
                break;
            }
            case 2: {
                if (stringBuilder.length() <= n + 3) break;
                stringBuilder.delete(n + 3, stringBuilder.length());
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new TSPException("unknown time-stamp resolution: " + this.resolution);
            }
        }
        while (stringBuilder.charAt(stringBuilder.length() - 1) == '0') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (stringBuilder.length() - 1 == n) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("Z");
        return new ASN1GeneralizedTime(stringBuilder.toString());
    }
}

