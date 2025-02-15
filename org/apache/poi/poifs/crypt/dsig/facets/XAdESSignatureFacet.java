/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.DataObjectFormatType
 *  org.etsi.uri.x01903.v13.DataObjectFormatType$Factory
 *  org.etsi.uri.x01903.v13.ObjectIdentifierType
 *  org.etsi.uri.x01903.v13.SignaturePolicyIdType
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.MessageDigest;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.SignerRoleType;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XAdESSignatureFacet
extends SignatureFacet {
    private static final POILogger LOG = POILogFactory.getLogger(XAdESSignatureFacet.class);
    private static final String XADES_TYPE = "http://uri.etsi.org/01903#SignedProperties";
    private final Map<String, String> dataObjectFormatMimeTypes = new HashMap<String, String>();

    @Override
    public void preSign(Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        LOG.log(1, "preSign");
        QualifyingPropertiesDocument qualDoc = QualifyingPropertiesDocument.Factory.newInstance();
        QualifyingPropertiesType qualifyingProperties = qualDoc.addNewQualifyingProperties();
        qualifyingProperties.setTarget("#" + this.signatureConfig.getPackageSignatureId());
        SignedPropertiesType signedProperties = qualifyingProperties.addNewSignedProperties();
        signedProperties.setId(this.signatureConfig.getXadesSignatureId());
        SignedSignaturePropertiesType signedSignatureProperties = signedProperties.addNewSignedSignatureProperties();
        this.addSigningTime(signedSignatureProperties);
        this.addCertificate(signedSignatureProperties);
        this.addXadesRole(signedSignatureProperties);
        this.addPolicy(signedSignatureProperties);
        this.addMimeTypes(signedProperties);
        objects.add(this.addXadesObject(document, qualifyingProperties));
        references.add(this.addXadesReference());
    }

    private void addSigningTime(SignedSignaturePropertiesType signedSignatureProperties) {
        Calendar xmlGregorianCalendar = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
        xmlGregorianCalendar.setTime(this.signatureConfig.getExecutionTime());
        xmlGregorianCalendar.clear(14);
        signedSignatureProperties.setSigningTime(xmlGregorianCalendar);
    }

    private void addCertificate(SignedSignaturePropertiesType signedSignatureProperties) {
        List<X509Certificate> chain = this.signatureConfig.getSigningCertificateChain();
        if (chain == null || chain.isEmpty()) {
            throw new RuntimeException("no signing certificate chain available");
        }
        CertIDListType signingCertificates = signedSignatureProperties.addNewSigningCertificate();
        CertIDType certId = signingCertificates.addNewCert();
        XAdESSignatureFacet.setCertID(certId, this.signatureConfig, this.signatureConfig.isXadesIssuerNameNoReverseOrder(), chain.get(0));
    }

    private void addXadesRole(SignedSignaturePropertiesType signedSignatureProperties) {
        String role = this.signatureConfig.getXadesRole();
        if (role == null || role.isEmpty()) {
            return;
        }
        SignerRoleType signerRole = signedSignatureProperties.addNewSignerRole();
        signedSignatureProperties.setSignerRole(signerRole);
        ClaimedRolesListType claimedRolesList = signerRole.addNewClaimedRoles();
        AnyType claimedRole = claimedRolesList.addNewClaimedRole();
        XmlString roleString = XmlString.Factory.newInstance();
        roleString.setStringValue(role);
        XAdESSignatureFacet.insertXChild(claimedRole, roleString);
    }

    private void addPolicy(SignedSignaturePropertiesType signedSignatureProperties) {
        SignaturePolicyService policyService = this.signatureConfig.getSignaturePolicyService();
        if (policyService == null) {
            if (this.signatureConfig.isXadesSignaturePolicyImplied()) {
                signedSignatureProperties.addNewSignaturePolicyIdentifier().addNewSignaturePolicyImplied();
            }
            return;
        }
        SignaturePolicyIdentifierType policyId = signedSignatureProperties.addNewSignaturePolicyIdentifier();
        SignaturePolicyIdType signaturePolicyId = policyId.addNewSignaturePolicyId();
        ObjectIdentifierType oit = signaturePolicyId.addNewSigPolicyId();
        oit.setDescription(policyService.getSignaturePolicyDescription());
        oit.addNewIdentifier().setStringValue(policyService.getSignaturePolicyIdentifier());
        byte[] signaturePolicyDocumentData = policyService.getSignaturePolicyDocument();
        DigestAlgAndValueType sigPolicyHash = signaturePolicyId.addNewSigPolicyHash();
        XAdESSignatureFacet.setDigestAlgAndValue(sigPolicyHash, signaturePolicyDocumentData, this.signatureConfig.getDigestAlgo());
        String signaturePolicyDownloadUrl = policyService.getSignaturePolicyDownloadUrl();
        if (signaturePolicyDownloadUrl == null) {
            return;
        }
        AnyType sigPolicyQualifier = signaturePolicyId.addNewSigPolicyQualifiers().addNewSigPolicyQualifier();
        XmlString spUriElement = XmlString.Factory.newInstance();
        spUriElement.setStringValue(signaturePolicyDownloadUrl);
        XAdESSignatureFacet.insertXChild(sigPolicyQualifier, spUriElement);
    }

    private void addMimeTypes(SignedPropertiesType signedProperties) {
        if (this.dataObjectFormatMimeTypes.isEmpty()) {
            return;
        }
        List dataObjectFormats = signedProperties.addNewSignedDataObjectProperties().getDataObjectFormatList();
        this.dataObjectFormatMimeTypes.forEach((key, value) -> {
            DataObjectFormatType dof = DataObjectFormatType.Factory.newInstance();
            dof.setObjectReference("#" + key);
            dof.setMimeType(value);
            dataObjectFormats.add(dof);
        });
    }

    private XMLObject addXadesObject(Document document, QualifyingPropertiesType qualifyingProperties) {
        Node qualDocElSrc = qualifyingProperties.getDomNode();
        Node qualDocEl = document.importNode(qualDocElSrc, true);
        List<XMLStructure> xadesObjectContent = Arrays.asList(new DOMStructure(qualDocEl));
        return this.getSignatureFactory().newXMLObject(xadesObjectContent, null, null, null);
    }

    private Reference addXadesReference() throws XMLSignatureException {
        List<Transform> transforms = Collections.singletonList(this.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315"));
        return this.newReference("#" + this.signatureConfig.getXadesSignatureId(), transforms, XADES_TYPE, null, null);
    }

    protected static void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValue, byte[] data, HashAlgorithm digestAlgo) {
        DigestMethodType digestMethod = digestAlgAndValue.addNewDigestMethod();
        digestMethod.setAlgorithm(SignatureConfig.getDigestMethodUri(digestAlgo));
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(digestAlgo);
        byte[] digestValue = messageDigest.digest(data);
        digestAlgAndValue.setDigestValue(digestValue);
    }

    protected static void setCertID(CertIDType certId, SignatureConfig signatureConfig, boolean issuerNameNoReverseOrder, X509Certificate certificate) {
        byte[] encodedCertificate;
        X509IssuerSerialType issuerSerial = certId.addNewIssuerSerial();
        String issuerName = issuerNameNoReverseOrder ? certificate.getIssuerDN().getName().replace(",", ", ") : certificate.getIssuerX500Principal().toString();
        issuerSerial.setX509IssuerName(issuerName);
        issuerSerial.setX509SerialNumber(certificate.getSerialNumber());
        try {
            encodedCertificate = certificate.getEncoded();
        } catch (CertificateEncodingException e) {
            throw new RuntimeException("certificate encoding error: " + e.getMessage(), e);
        }
        DigestAlgAndValueType certDigest = certId.addNewCertDigest();
        XAdESSignatureFacet.setDigestAlgAndValue(certDigest, encodedCertificate, signatureConfig.getXadesDigestAlgo());
    }

    public void addMimeType(String dsReferenceUri, String mimetype) {
        this.dataObjectFormatMimeTypes.put(dsReferenceUri, mimetype);
    }

    protected static void insertXChild(XmlObject root, XmlObject child) {
        XmlCursor rootCursor = root.newCursor();
        rootCursor.toEndToken();
        XmlCursor childCursor = child.newCursor();
        childCursor.toNextToken();
        childCursor.moveXml(rootCursor);
        childCursor.dispose();
        rootCursor.dispose();
    }
}

