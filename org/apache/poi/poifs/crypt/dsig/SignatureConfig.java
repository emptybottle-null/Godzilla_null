/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.OOXMLURIDereferencer;
import org.apache.poi.poifs.crypt.dsig.SignatureMarshalListener;
import org.apache.poi.poifs.crypt.dsig.facets.KeyInfoSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.OOXMLSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.Office2010SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.XAdESSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampServiceValidator;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.events.EventListener;

public class SignatureConfig {
    public static final String SIGNATURE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final POILogger LOG = POILogFactory.getLogger(SignatureConfig.class);
    private static final String DigestMethod_SHA224 = "http://www.w3.org/2001/04/xmldsig-more#sha224";
    private static final String DigestMethod_SHA384 = "http://www.w3.org/2001/04/xmldsig-more#sha384";
    private ThreadLocal<OPCPackage> opcPackage = new ThreadLocal();
    private ThreadLocal<XMLSignatureFactory> signatureFactory = new ThreadLocal();
    private ThreadLocal<KeyInfoFactory> keyInfoFactory = new ThreadLocal();
    private ThreadLocal<Provider> provider = new ThreadLocal();
    private List<SignatureFacet> signatureFacets = new ArrayList<SignatureFacet>();
    private HashAlgorithm digestAlgo = HashAlgorithm.sha256;
    private Date executionTime = new Date();
    private PrivateKey key;
    private List<X509Certificate> signingCertificateChain;
    private SignaturePolicyService signaturePolicyService;
    private URIDereferencer uriDereferencer;
    private String canonicalizationMethod = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
    private boolean includeEntireCertificateChain = true;
    private boolean includeIssuerSerial;
    private boolean includeKeyValue;
    private TimeStampService tspService = new TSPTimeStampService();
    private String tspUrl;
    private boolean tspOldProtocol;
    private HashAlgorithm tspDigestAlgo;
    private String tspUser;
    private String tspPass;
    private TimeStampServiceValidator tspValidator;
    private String tspRequestPolicy = "1.3.6.1.4.1.13762.3";
    private String userAgent = "POI XmlSign Service TSP Client";
    private String proxyUrl;
    private RevocationDataService revocationDataService;
    private HashAlgorithm xadesDigestAlgo;
    private String xadesRole;
    private String xadesSignatureId = "idSignedProperties";
    private boolean xadesSignaturePolicyImplied = true;
    private String xadesCanonicalizationMethod = "http://www.w3.org/2001/10/xml-exc-c14n#";
    private boolean xadesIssuerNameNoReverseOrder = true;
    private String packageSignatureId = "idPackageSignature";
    private String signatureDescription = "Office OpenXML Document";
    private EventListener signatureMarshalListener;
    private final Map<String, String> namespacePrefixes = new HashMap<String, String>();
    private boolean updateConfigOnValidate = false;
    private boolean allowMultipleSignatures = false;

    protected void init(boolean onlyValidation) {
        if (this.opcPackage == null) {
            throw new EncryptedDocumentException("opcPackage is null");
        }
        if (this.uriDereferencer == null) {
            this.uriDereferencer = new OOXMLURIDereferencer();
        }
        if (this.uriDereferencer instanceof SignatureConfigurable) {
            ((SignatureConfigurable)((Object)this.uriDereferencer)).setSignatureConfig(this);
        }
        if (this.namespacePrefixes.isEmpty()) {
            this.namespacePrefixes.put("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi");
            this.namespacePrefixes.put("http://uri.etsi.org/01903/v1.3.2#", "xd");
        }
        if (onlyValidation) {
            return;
        }
        if (this.signatureMarshalListener == null) {
            this.signatureMarshalListener = new SignatureMarshalListener();
        }
        if (this.signatureMarshalListener instanceof SignatureConfigurable) {
            ((SignatureConfigurable)((Object)this.signatureMarshalListener)).setSignatureConfig(this);
        }
        if (this.tspService != null) {
            this.tspService.setSignatureConfig(this);
        }
        if (this.signatureFacets.isEmpty()) {
            this.addSignatureFacet(new OOXMLSignatureFacet());
            this.addSignatureFacet(new KeyInfoSignatureFacet());
            this.addSignatureFacet(new XAdESSignatureFacet());
            this.addSignatureFacet(new Office2010SignatureFacet());
        }
        for (SignatureFacet sf : this.signatureFacets) {
            sf.setSignatureConfig(this);
        }
    }

    public void addSignatureFacet(SignatureFacet signatureFacet) {
        this.signatureFacets.add(signatureFacet);
    }

    public List<SignatureFacet> getSignatureFacets() {
        return this.signatureFacets;
    }

    public void setSignatureFacets(List<SignatureFacet> signatureFacets) {
        this.signatureFacets = signatureFacets;
    }

    public HashAlgorithm getDigestAlgo() {
        return this.digestAlgo;
    }

    public void setDigestAlgo(HashAlgorithm digestAlgo) {
        this.digestAlgo = digestAlgo;
    }

    public OPCPackage getOpcPackage() {
        return this.opcPackage.get();
    }

    public void setOpcPackage(OPCPackage opcPackage) {
        this.opcPackage.set(opcPackage);
    }

    public PrivateKey getKey() {
        return this.key;
    }

    public void setKey(PrivateKey key) {
        this.key = key;
    }

    public List<X509Certificate> getSigningCertificateChain() {
        return this.signingCertificateChain;
    }

    public void setSigningCertificateChain(List<X509Certificate> signingCertificateChain) {
        this.signingCertificateChain = signingCertificateChain;
    }

    public Date getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public String formatExecutionTime() {
        SimpleDateFormat fmt = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
        fmt.setTimeZone(LocaleUtil.TIMEZONE_UTC);
        return fmt.format(this.getExecutionTime());
    }

    public void setExecutionTime(String executionTime) {
        if (executionTime != null && !"".equals(executionTime)) {
            SimpleDateFormat fmt = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
            fmt.setTimeZone(LocaleUtil.TIMEZONE_UTC);
            try {
                this.executionTime = fmt.parse(executionTime);
            } catch (ParseException e) {
                LOG.log(5, "Illegal execution time: " + executionTime);
            }
        }
    }

    public SignaturePolicyService getSignaturePolicyService() {
        return this.signaturePolicyService;
    }

    public void setSignaturePolicyService(SignaturePolicyService signaturePolicyService) {
        this.signaturePolicyService = signaturePolicyService;
    }

    public URIDereferencer getUriDereferencer() {
        return this.uriDereferencer;
    }

    public void setUriDereferencer(URIDereferencer uriDereferencer) {
        this.uriDereferencer = uriDereferencer;
    }

    public String getSignatureDescription() {
        return this.signatureDescription;
    }

    public void setSignatureDescription(String signatureDescription) {
        this.signatureDescription = signatureDescription;
    }

    public String getCanonicalizationMethod() {
        return this.canonicalizationMethod;
    }

    public void setCanonicalizationMethod(String canonicalizationMethod) {
        this.canonicalizationMethod = SignatureConfig.verifyCanonicalizationMethod(canonicalizationMethod, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
    }

    private static String verifyCanonicalizationMethod(String canonicalizationMethod, String defaultMethod) {
        if (canonicalizationMethod == null || canonicalizationMethod.isEmpty()) {
            return defaultMethod;
        }
        switch (canonicalizationMethod) {
            case "http://www.w3.org/TR/2001/REC-xml-c14n-20010315": 
            case "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments": 
            case "http://www.w3.org/2000/09/xmldsig#enveloped-signature": 
            case "http://www.w3.org/2001/10/xml-exc-c14n#": 
            case "http://www.w3.org/2001/10/xml-exc-c14n#WithComments": {
                return canonicalizationMethod;
            }
        }
        throw new EncryptedDocumentException("Unknown CanonicalizationMethod: " + canonicalizationMethod);
    }

    public String getPackageSignatureId() {
        return this.packageSignatureId;
    }

    public void setPackageSignatureId(String packageSignatureId) {
        this.packageSignatureId = SignatureConfig.nvl(packageSignatureId, "xmldsig-" + UUID.randomUUID());
    }

    public String getTspUrl() {
        return this.tspUrl;
    }

    public void setTspUrl(String tspUrl) {
        this.tspUrl = tspUrl;
    }

    public boolean isTspOldProtocol() {
        return this.tspOldProtocol;
    }

    public void setTspOldProtocol(boolean tspOldProtocol) {
        this.tspOldProtocol = tspOldProtocol;
    }

    public HashAlgorithm getTspDigestAlgo() {
        return SignatureConfig.nvl(this.tspDigestAlgo, this.digestAlgo);
    }

    public void setTspDigestAlgo(HashAlgorithm tspDigestAlgo) {
        this.tspDigestAlgo = tspDigestAlgo;
    }

    public String getProxyUrl() {
        return this.proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public TimeStampService getTspService() {
        return this.tspService;
    }

    public void setTspService(TimeStampService tspService) {
        this.tspService = tspService;
    }

    public String getTspUser() {
        return this.tspUser;
    }

    public void setTspUser(String tspUser) {
        this.tspUser = tspUser;
    }

    public String getTspPass() {
        return this.tspPass;
    }

    public void setTspPass(String tspPass) {
        this.tspPass = tspPass;
    }

    public TimeStampServiceValidator getTspValidator() {
        return this.tspValidator;
    }

    public void setTspValidator(TimeStampServiceValidator tspValidator) {
        this.tspValidator = tspValidator;
    }

    public RevocationDataService getRevocationDataService() {
        return this.revocationDataService;
    }

    public void setRevocationDataService(RevocationDataService revocationDataService) {
        this.revocationDataService = revocationDataService;
    }

    public HashAlgorithm getXadesDigestAlgo() {
        return SignatureConfig.nvl(this.xadesDigestAlgo, this.digestAlgo);
    }

    public void setXadesDigestAlgo(HashAlgorithm xadesDigestAlgo) {
        this.xadesDigestAlgo = xadesDigestAlgo;
    }

    public void setXadesDigestAlgo(String xadesDigestAlgo) {
        this.xadesDigestAlgo = SignatureConfig.getDigestMethodAlgo(xadesDigestAlgo);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getTspRequestPolicy() {
        return this.tspRequestPolicy;
    }

    public void setTspRequestPolicy(String tspRequestPolicy) {
        this.tspRequestPolicy = tspRequestPolicy;
    }

    public boolean isIncludeEntireCertificateChain() {
        return this.includeEntireCertificateChain;
    }

    public void setIncludeEntireCertificateChain(boolean includeEntireCertificateChain) {
        this.includeEntireCertificateChain = includeEntireCertificateChain;
    }

    public boolean isIncludeIssuerSerial() {
        return this.includeIssuerSerial;
    }

    public void setIncludeIssuerSerial(boolean includeIssuerSerial) {
        this.includeIssuerSerial = includeIssuerSerial;
    }

    public boolean isIncludeKeyValue() {
        return this.includeKeyValue;
    }

    public void setIncludeKeyValue(boolean includeKeyValue) {
        this.includeKeyValue = includeKeyValue;
    }

    public String getXadesRole() {
        return this.xadesRole;
    }

    public void setXadesRole(String xadesRole) {
        this.xadesRole = xadesRole;
    }

    public String getXadesSignatureId() {
        return SignatureConfig.nvl(this.xadesSignatureId, "idSignedProperties");
    }

    public void setXadesSignatureId(String xadesSignatureId) {
        this.xadesSignatureId = xadesSignatureId;
    }

    public boolean isXadesSignaturePolicyImplied() {
        return this.xadesSignaturePolicyImplied;
    }

    public void setXadesSignaturePolicyImplied(boolean xadesSignaturePolicyImplied) {
        this.xadesSignaturePolicyImplied = xadesSignaturePolicyImplied;
    }

    public boolean isXadesIssuerNameNoReverseOrder() {
        return this.xadesIssuerNameNoReverseOrder;
    }

    public void setXadesIssuerNameNoReverseOrder(boolean xadesIssuerNameNoReverseOrder) {
        this.xadesIssuerNameNoReverseOrder = xadesIssuerNameNoReverseOrder;
    }

    public EventListener getSignatureMarshalListener() {
        return this.signatureMarshalListener;
    }

    public void setSignatureMarshalListener(EventListener signatureMarshalListener) {
        this.signatureMarshalListener = signatureMarshalListener;
    }

    public Map<String, String> getNamespacePrefixes() {
        return this.namespacePrefixes;
    }

    public void setNamespacePrefixes(Map<String, String> namespacePrefixes) {
        this.namespacePrefixes.clear();
        this.namespacePrefixes.putAll(namespacePrefixes);
    }

    private static <T> T nvl(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public String getSignatureMethodUri() {
        switch (this.getDigestAlgo()) {
            case sha1: {
                return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
            }
            case sha224: {
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha224";
            }
            case sha256: {
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
            }
            case sha384: {
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384";
            }
            case sha512: {
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512";
            }
            case ripemd160: {
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160";
            }
        }
        throw new EncryptedDocumentException("Hash algorithm " + (Object)((Object)this.getDigestAlgo()) + " not supported for signing.");
    }

    public String getDigestMethodUri() {
        return SignatureConfig.getDigestMethodUri(this.getDigestAlgo());
    }

    public static String getDigestMethodUri(HashAlgorithm digestAlgo) {
        switch (digestAlgo) {
            case sha1: {
                return "http://www.w3.org/2000/09/xmldsig#sha1";
            }
            case sha224: {
                return DigestMethod_SHA224;
            }
            case sha256: {
                return "http://www.w3.org/2001/04/xmlenc#sha256";
            }
            case sha384: {
                return DigestMethod_SHA384;
            }
            case sha512: {
                return "http://www.w3.org/2001/04/xmlenc#sha512";
            }
            case ripemd160: {
                return "http://www.w3.org/2001/04/xmlenc#ripemd160";
            }
        }
        throw new EncryptedDocumentException("Hash algorithm " + (Object)((Object)digestAlgo) + " not supported for signing.");
    }

    private static HashAlgorithm getDigestMethodAlgo(String digestMethodUri) {
        if (digestMethodUri == null || digestMethodUri.isEmpty()) {
            return null;
        }
        switch (digestMethodUri) {
            case "http://www.w3.org/2000/09/xmldsig#sha1": {
                return HashAlgorithm.sha1;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#sha224": {
                return HashAlgorithm.sha224;
            }
            case "http://www.w3.org/2001/04/xmlenc#sha256": {
                return HashAlgorithm.sha256;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#sha384": {
                return HashAlgorithm.sha384;
            }
            case "http://www.w3.org/2001/04/xmlenc#sha512": {
                return HashAlgorithm.sha512;
            }
            case "http://www.w3.org/2001/04/xmlenc#ripemd160": {
                return HashAlgorithm.ripemd160;
            }
        }
        throw new EncryptedDocumentException("Hash algorithm " + digestMethodUri + " not supported for signing.");
    }

    public void setSignatureMethodFromUri(String signatureMethodUri) {
        switch (signatureMethodUri) {
            case "http://www.w3.org/2000/09/xmldsig#rsa-sha1": {
                this.setDigestAlgo(HashAlgorithm.sha1);
                break;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#rsa-sha224": {
                this.setDigestAlgo(HashAlgorithm.sha224);
                break;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256": {
                this.setDigestAlgo(HashAlgorithm.sha256);
                break;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384": {
                this.setDigestAlgo(HashAlgorithm.sha384);
                break;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512": {
                this.setDigestAlgo(HashAlgorithm.sha512);
                break;
            }
            case "http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160": {
                this.setDigestAlgo(HashAlgorithm.ripemd160);
                break;
            }
            default: {
                throw new EncryptedDocumentException("Hash algorithm " + signatureMethodUri + " not supported.");
            }
        }
    }

    public void setSignatureFactory(XMLSignatureFactory signatureFactory) {
        this.signatureFactory.set(signatureFactory);
    }

    public XMLSignatureFactory getSignatureFactory() {
        XMLSignatureFactory sigFac = this.signatureFactory.get();
        if (sigFac == null) {
            sigFac = XMLSignatureFactory.getInstance("DOM", this.getProvider());
            this.setSignatureFactory(sigFac);
        }
        return sigFac;
    }

    public void setKeyInfoFactory(KeyInfoFactory keyInfoFactory) {
        this.keyInfoFactory.set(keyInfoFactory);
    }

    public KeyInfoFactory getKeyInfoFactory() {
        KeyInfoFactory keyFac = this.keyInfoFactory.get();
        if (keyFac == null) {
            keyFac = KeyInfoFactory.getInstance("DOM", this.getProvider());
            this.setKeyInfoFactory(keyFac);
        }
        return keyFac;
    }

    public Provider getProvider() {
        Provider prov = this.provider.get();
        if (prov == null) {
            String[] dsigProviderNames;
            for (String pn : dsigProviderNames = new String[]{System.getProperty("jsr105Provider"), "org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI", "org.jcp.xml.dsig.internal.dom.XMLDSigRI"}) {
                if (pn == null) continue;
                try {
                    prov = (Provider)Class.forName(pn).newInstance();
                    break;
                } catch (Exception e) {
                    LOG.log(1, "XMLDsig-Provider '" + pn + "' can't be found - trying next.");
                }
            }
        }
        if (prov == null) {
            throw new RuntimeException("JRE doesn't support default xml signature provider - set jsr105Provider system property!");
        }
        return prov;
    }

    public String getXadesCanonicalizationMethod() {
        return this.xadesCanonicalizationMethod;
    }

    public void setXadesCanonicalizationMethod(String xadesCanonicalizationMethod) {
        this.xadesCanonicalizationMethod = SignatureConfig.verifyCanonicalizationMethod(xadesCanonicalizationMethod, "http://www.w3.org/2001/10/xml-exc-c14n#");
    }

    public boolean isUpdateConfigOnValidate() {
        return this.updateConfigOnValidate;
    }

    public void setUpdateConfigOnValidate(boolean updateConfigOnValidate) {
        this.updateConfigOnValidate = updateConfigOnValidate;
    }

    public boolean isAllowMultipleSignatures() {
        return this.allowMultipleSignatures;
    }

    public void setAllowMultipleSignatures(boolean allowMultipleSignatures) {
        this.allowMultipleSignatures = allowMultipleSignatures;
    }

    public static interface SignatureConfigurable {
        public void setSignatureConfig(SignatureConfig var1);
    }
}

