/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.xml.security.c14n.Canonicalizer
 *  org.etsi.uri.x01903.v14.ValidationDataType
 *  org.etsi.uri.x01903.v14.ValidationDataType$Factory
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.crypto.MarshalException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.XAdESSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RespID;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;
import org.etsi.uri.x01903.v14.ValidationDataType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XAdESXLSignatureFacet
extends SignatureFacet {
    private static final POILogger LOG = POILogFactory.getLogger(XAdESXLSignatureFacet.class);
    private final CertificateFactory certificateFactory;

    public XAdESXLSignatureFacet() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new RuntimeException("X509 JCA error: " + e.getMessage(), e);
        }
    }

    @Override
    public void postSign(Document document) throws MarshalException {
        NodeList nlSigVal;
        UnsignedSignaturePropertiesType unsignedSigProps;
        LOG.log(1, "XAdES-X-L post sign phase");
        QualifyingPropertiesDocument qualDoc = null;
        QualifyingPropertiesType qualProps = null;
        NodeList qualNl = document.getElementsByTagNameNS("http://uri.etsi.org/01903/v1.3.2#", "QualifyingProperties");
        if (qualNl.getLength() == 1) {
            try {
                qualDoc = QualifyingPropertiesDocument.Factory.parse(qualNl.item(0), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            } catch (XmlException e) {
                throw new MarshalException(e);
            }
        } else {
            throw new MarshalException("no XAdES-BES extension present");
        }
        qualProps = qualDoc.getQualifyingProperties();
        UnsignedPropertiesType unsignedProps = qualProps.getUnsignedProperties();
        if (unsignedProps == null) {
            unsignedProps = qualProps.addNewUnsignedProperties();
        }
        if ((unsignedSigProps = unsignedProps.getUnsignedSignatureProperties()) == null) {
            unsignedSigProps = unsignedProps.addNewUnsignedSignatureProperties();
        }
        if ((nlSigVal = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "SignatureValue")).getLength() != 1) {
            throw new IllegalArgumentException("SignatureValue is not set.");
        }
        RevocationData tsaRevocationDataXadesT = new RevocationData();
        LOG.log(1, "creating XAdES-T time-stamp");
        XAdESTimeStampType signatureTimeStamp = this.createXAdESTimeStamp(Collections.singletonList(nlSigVal.item(0)), tsaRevocationDataXadesT);
        unsignedSigProps.addNewSignatureTimeStamp().set(signatureTimeStamp);
        if (tsaRevocationDataXadesT.hasRevocationDataEntries()) {
            ValidationDataType validationData = this.createValidationData(tsaRevocationDataXadesT);
            XAdESSignatureFacet.insertXChild(unsignedSigProps, (XmlObject)validationData);
        }
        if (this.signatureConfig.getRevocationDataService() == null) {
            return;
        }
        CompleteCertificateRefsType completeCertificateRefs = unsignedSigProps.addNewCompleteCertificateRefs();
        CertIDListType certIdList = completeCertificateRefs.addNewCertRefs();
        List<X509Certificate> certChain = this.signatureConfig.getSigningCertificateChain();
        int chainSize = certChain.size();
        if (chainSize > 1) {
            for (X509Certificate cert : certChain.subList(1, chainSize)) {
                CertIDType certId = certIdList.addNewCert();
                XAdESSignatureFacet.setCertID(certId, this.signatureConfig, false, cert);
            }
        }
        CompleteRevocationRefsType completeRevocationRefs = unsignedSigProps.addNewCompleteRevocationRefs();
        RevocationData revocationData = this.signatureConfig.getRevocationDataService().getRevocationData(certChain);
        if (revocationData.hasCRLs()) {
            CRLRefsType crlRefs = completeRevocationRefs.addNewCRLRefs();
            completeRevocationRefs.setCRLRefs(crlRefs);
            for (byte[] encodedCrl : revocationData.getCRLs()) {
                X509CRL crl;
                CRLRefType crlRef = crlRefs.addNewCRLRef();
                try {
                    crl = (X509CRL)this.certificateFactory.generateCRL(new ByteArrayInputStream(encodedCrl));
                } catch (CRLException e) {
                    throw new RuntimeException("CRL parse error: " + e.getMessage(), e);
                }
                CRLIdentifierType crlIdentifier = crlRef.addNewCRLIdentifier();
                String issuerName = crl.getIssuerDN().getName().replace(",", ", ");
                crlIdentifier.setIssuer(issuerName);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                cal.setTime(crl.getThisUpdate());
                crlIdentifier.setIssueTime(cal);
                crlIdentifier.setNumber(this.getCrlNumber(crl));
                DigestAlgAndValueType digestAlgAndValue = crlRef.addNewDigestAlgAndValue();
                XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, encodedCrl, this.signatureConfig.getDigestAlgo());
            }
        }
        if (revocationData.hasOCSPs()) {
            OCSPRefsType ocspRefs = completeRevocationRefs.addNewOCSPRefs();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                try {
                    OCSPRefType ocspRef = ocspRefs.addNewOCSPRef();
                    DigestAlgAndValueType digestAlgAndValue = ocspRef.addNewDigestAlgAndValue();
                    XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, ocsp, this.signatureConfig.getDigestAlgo());
                    OCSPIdentifierType ocspIdentifier = ocspRef.addNewOCSPIdentifier();
                    OCSPResp ocspResp = new OCSPResp(ocsp);
                    BasicOCSPResp basicOcspResp = (BasicOCSPResp)ocspResp.getResponseObject();
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    cal.setTime(basicOcspResp.getProducedAt());
                    ocspIdentifier.setProducedAt(cal);
                    ResponderIDType responderId = ocspIdentifier.addNewResponderID();
                    RespID respId = basicOcspResp.getResponderId();
                    ResponderID ocspResponderId = respId.toASN1Primitive();
                    DERTaggedObject derTaggedObject = (DERTaggedObject)ocspResponderId.toASN1Primitive();
                    if (2 == derTaggedObject.getTagNo()) {
                        ASN1OctetString keyHashOctetString = (ASN1OctetString)derTaggedObject.getObject();
                        byte[] key = keyHashOctetString.getOctets();
                        responderId.setByKey(key);
                        continue;
                    }
                    X500Name name = X500Name.getInstance(derTaggedObject.getObject());
                    String nameStr = name.toString();
                    responderId.setByName(nameStr);
                } catch (Exception e) {
                    throw new RuntimeException("OCSP decoding error: " + e.getMessage(), e);
                }
            }
        }
        ArrayList<Node> timeStampNodesXadesX1 = new ArrayList<Node>();
        timeStampNodesXadesX1.add(nlSigVal.item(0));
        timeStampNodesXadesX1.add(signatureTimeStamp.getDomNode());
        timeStampNodesXadesX1.add(completeCertificateRefs.getDomNode());
        timeStampNodesXadesX1.add(completeRevocationRefs.getDomNode());
        RevocationData tsaRevocationDataXadesX1 = new RevocationData();
        LOG.log(1, "creating XAdES-X time-stamp");
        XAdESTimeStampType timeStampXadesX1 = this.createXAdESTimeStamp(timeStampNodesXadesX1, tsaRevocationDataXadesX1);
        if (tsaRevocationDataXadesX1.hasRevocationDataEntries()) {
            ValidationDataType timeStampXadesX1ValidationData = this.createValidationData(tsaRevocationDataXadesX1);
            XAdESSignatureFacet.insertXChild(unsignedSigProps, (XmlObject)timeStampXadesX1ValidationData);
        }
        unsignedSigProps.addNewSigAndRefsTimeStamp().set(timeStampXadesX1);
        CertificateValuesType certificateValues = unsignedSigProps.addNewCertificateValues();
        for (X509Certificate certificate : certChain) {
            EncapsulatedPKIDataType encapsulatedPKIDataType = certificateValues.addNewEncapsulatedX509Certificate();
            try {
                encapsulatedPKIDataType.setByteArrayValue(certificate.getEncoded());
            } catch (CertificateEncodingException e) {
                throw new RuntimeException("certificate encoding error: " + e.getMessage(), e);
            }
        }
        RevocationValuesType revocationValues = unsignedSigProps.addNewRevocationValues();
        this.createRevocationValues(revocationValues, revocationData);
        Node n = document.importNode(qualProps.getDomNode(), true);
        qualNl.item(0).getParentNode().replaceChild(n, qualNl.item(0));
    }

    public static byte[] getC14nValue(List<Node> nodeList, String c14nAlgoId) {
        ByteArrayOutputStream c14nValue = new ByteArrayOutputStream();
        try {
            for (Node node : nodeList) {
                Canonicalizer c14n = Canonicalizer.getInstance((String)c14nAlgoId);
                c14nValue.write(c14n.canonicalizeSubtree(node));
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("c14n error: " + e.getMessage(), e);
        }
        return c14nValue.toByteArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private BigInteger getCrlNumber(X509CRL crl) {
        BigInteger bigInteger;
        byte[] crlNumberExtensionValue = crl.getExtensionValue(Extension.cRLNumber.getId());
        if (null == crlNumberExtensionValue) {
            return null;
        }
        ASN1InputStream asn1IS1 = null;
        ASN1InputStream asn1IS2 = null;
        try {
            asn1IS1 = new ASN1InputStream(crlNumberExtensionValue);
            ASN1OctetString octetString = (ASN1OctetString)asn1IS1.readObject();
            byte[] octets = octetString.getOctets();
            asn1IS2 = new ASN1InputStream(octets);
            ASN1Integer integer = (ASN1Integer)asn1IS2.readObject();
            bigInteger = integer.getPositiveValue();
        } catch (Throwable throwable) {
            try {
                IOUtils.closeQuietly(asn1IS2);
                IOUtils.closeQuietly(asn1IS1);
                throw throwable;
            } catch (IOException e) {
                throw new RuntimeException("I/O error: " + e.getMessage(), e);
            }
        }
        IOUtils.closeQuietly(asn1IS2);
        IOUtils.closeQuietly(asn1IS1);
        return bigInteger;
    }

    private XAdESTimeStampType createXAdESTimeStamp(List<Node> nodeList, RevocationData revocationData) {
        byte[] c14nSignatureValueElement = XAdESXLSignatureFacet.getC14nValue(nodeList, this.signatureConfig.getXadesCanonicalizationMethod());
        return this.createXAdESTimeStamp(c14nSignatureValueElement, revocationData);
    }

    private XAdESTimeStampType createXAdESTimeStamp(byte[] data, RevocationData revocationData) {
        byte[] timeStampToken;
        try {
            timeStampToken = this.signatureConfig.getTspService().timeStamp(data, revocationData);
        } catch (Exception e) {
            throw new RuntimeException("error while creating a time-stamp: " + e.getMessage(), e);
        }
        XAdESTimeStampType xadesTimeStamp = XAdESTimeStampType.Factory.newInstance();
        xadesTimeStamp.setId("time-stamp-" + UUID.randomUUID());
        CanonicalizationMethodType c14nMethod = xadesTimeStamp.addNewCanonicalizationMethod();
        c14nMethod.setAlgorithm(this.signatureConfig.getXadesCanonicalizationMethod());
        EncapsulatedPKIDataType encapsulatedTimeStamp = xadesTimeStamp.addNewEncapsulatedTimeStamp();
        encapsulatedTimeStamp.setByteArrayValue(timeStampToken);
        encapsulatedTimeStamp.setId("time-stamp-token-" + UUID.randomUUID());
        return xadesTimeStamp;
    }

    private ValidationDataType createValidationData(RevocationData revocationData) {
        ValidationDataType validationData = ValidationDataType.Factory.newInstance();
        RevocationValuesType revocationValues = validationData.addNewRevocationValues();
        this.createRevocationValues(revocationValues, revocationData);
        return validationData;
    }

    private void createRevocationValues(RevocationValuesType revocationValues, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLValuesType crlValues = revocationValues.addNewCRLValues();
            for (byte[] crl : revocationData.getCRLs()) {
                EncapsulatedPKIDataType encapsulatedCrlValue = crlValues.addNewEncapsulatedCRLValue();
                encapsulatedCrlValue.setByteArrayValue(crl);
            }
        }
        if (revocationData.hasOCSPs()) {
            OCSPValuesType ocspValues = revocationValues.addNewOCSPValues();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                EncapsulatedPKIDataType encapsulatedOcspValue = ocspValues.addNewEncapsulatedOCSPValue();
                encapsulatedOcspValue.setByteArrayValue(ocsp);
            }
        }
    }
}

