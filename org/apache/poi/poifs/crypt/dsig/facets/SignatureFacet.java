/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.GeneralSecurityException;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Document;

public abstract class SignatureFacet
implements SignatureConfig.SignatureConfigurable {
    private static final POILogger LOG = POILogFactory.getLogger(SignatureFacet.class);
    public static final String XML_NS = "http://www.w3.org/2000/xmlns/";
    public static final String XML_DIGSIG_NS = "http://www.w3.org/2000/09/xmldsig#";
    public static final String OO_DIGSIG_NS = "http://schemas.openxmlformats.org/package/2006/digital-signature";
    public static final String MS_DIGSIG_NS = "http://schemas.microsoft.com/office/2006/digsig";
    public static final String XADES_132_NS = "http://uri.etsi.org/01903/v1.3.2#";
    public static final String XADES_141_NS = "http://uri.etsi.org/01903/v1.4.1#";
    protected SignatureConfig signatureConfig;

    @Override
    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }

    public void preSign(Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
    }

    public void postSign(Document document) throws MarshalException {
    }

    protected XMLSignatureFactory getSignatureFactory() {
        return this.signatureConfig.getSignatureFactory();
    }

    protected Transform newTransform(String canonicalizationMethod) throws XMLSignatureException {
        return this.newTransform(canonicalizationMethod, null);
    }

    protected Transform newTransform(String canonicalizationMethod, TransformParameterSpec paramSpec) throws XMLSignatureException {
        try {
            return this.getSignatureFactory().newTransform(canonicalizationMethod, paramSpec);
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException("unknown canonicalization method: " + canonicalizationMethod, e);
        }
    }

    protected Reference newReference(String uri, List<Transform> transforms, String type, String id, byte[] digestValue) throws XMLSignatureException {
        return SignatureFacet.newReference(uri, transforms, type, id, digestValue, this.signatureConfig);
    }

    public static Reference newReference(String uri, List<Transform> transforms, String type, String id, byte[] digestValue, SignatureConfig signatureConfig) throws XMLSignatureException {
        DigestMethod digestMethod;
        String digestMethodUri = signatureConfig.getDigestMethodUri();
        XMLSignatureFactory sigFac = signatureConfig.getSignatureFactory();
        try {
            digestMethod = sigFac.newDigestMethod(digestMethodUri, null);
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException("unknown digest method uri: " + digestMethodUri, e);
        }
        Reference reference = digestValue == null ? sigFac.newReference(uri, digestMethod, transforms, type, id) : sigFac.newReference(uri, digestMethod, transforms, type, id, digestValue);
        return reference;
    }
}

