/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xmlbeans.XmlException;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SignaturePart {
    private static final POILogger LOG = POILogFactory.getLogger(SignaturePart.class);
    private static final String XMLSEC_VALIDATE_MANIFEST = "org.jcp.xml.dsig.validateManifests";
    private final PackagePart signaturePart;
    private final SignatureConfig signatureConfig;
    private X509Certificate signer;
    private List<X509Certificate> certChain;

    SignaturePart(PackagePart signaturePart, SignatureConfig signatureConfig) {
        this.signaturePart = signaturePart;
        this.signatureConfig = signatureConfig;
    }

    public PackagePart getPackagePart() {
        return this.signaturePart;
    }

    public X509Certificate getSigner() {
        return this.signer;
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }

    public SignatureDocument getSignatureDocument() throws IOException, XmlException {
        return SignatureDocument.Factory.parse(this.signaturePart.getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    public boolean validate() {
        KeyInfoKeySelector keySelector = new KeyInfoKeySelector();
        XPath xpath = XPathHelper.getFactory().newXPath();
        xpath.setNamespaceContext(new XPathNSContext());
        try {
            Document doc = DocumentHelper.readDocument(this.signaturePart.getInputStream());
            NodeList nl = (NodeList)xpath.compile("//*[@Id]").evaluate(doc, XPathConstants.NODESET);
            int length = nl.getLength();
            for (int i = 0; i < length; ++i) {
                ((Element)nl.item(i)).setIdAttribute("Id", true);
            }
            DOMValidateContext domValidateContext = new DOMValidateContext(keySelector, (Node)doc);
            domValidateContext.setProperty(XMLSEC_VALIDATE_MANIFEST, Boolean.TRUE);
            domValidateContext.setURIDereferencer(this.signatureConfig.getUriDereferencer());
            XMLSignatureFactory xmlSignatureFactory = this.signatureConfig.getSignatureFactory();
            XMLSignature xmlSignature = xmlSignatureFactory.unmarshalXMLSignature(domValidateContext);
            boolean valid = xmlSignature.validate(domValidateContext);
            if (valid) {
                this.signer = keySelector.getSigner();
                this.certChain = keySelector.getCertChain();
                this.extractConfig(doc, xmlSignature);
            }
            return valid;
        } catch (IOException e) {
            String s = "error in reading document";
            LOG.log(7, s, e);
            throw new EncryptedDocumentException(s, e);
        } catch (SAXException e) {
            String s = "error in parsing document";
            LOG.log(7, s, e);
            throw new EncryptedDocumentException(s, e);
        } catch (XPathExpressionException e) {
            String s = "error in searching document with xpath expression";
            LOG.log(7, s, e);
            throw new EncryptedDocumentException(s, e);
        } catch (MarshalException e) {
            String s = "error in unmarshalling the signature";
            LOG.log(7, s, e);
            throw new EncryptedDocumentException(s, e);
        } catch (XMLSignatureException e) {
            String s = "error in validating the signature";
            LOG.log(7, s, e);
            throw new EncryptedDocumentException(s, e);
        }
    }

    private void extractConfig(Document doc, XMLSignature xmlSignature) throws XPathExpressionException {
        if (!this.signatureConfig.isUpdateConfigOnValidate()) {
            return;
        }
        this.signatureConfig.setSigningCertificateChain(this.certChain);
        this.signatureConfig.setSignatureMethodFromUri(xmlSignature.getSignedInfo().getSignatureMethod().getAlgorithm());
        XPath xpath = XPathHelper.getFactory().newXPath();
        xpath.setNamespaceContext(new XPathNSContext());
        HashMap<String, Consumer<String>> m = new HashMap<String, Consumer<String>>();
        m.put("//mdssi:SignatureTime/mdssi:Value", this.signatureConfig::setExecutionTime);
        m.put("//xd:ClaimedRole", this.signatureConfig::setXadesRole);
        m.put("//dsss:SignatureComments", this.signatureConfig::setSignatureDescription);
        m.put("//xd:QualifyingProperties//xd:SignedSignatureProperties//ds:DigestMethod/@Algorithm", this.signatureConfig::setXadesDigestAlgo);
        m.put("//ds:CanonicalizationMethod", this.signatureConfig::setCanonicalizationMethod);
        for (Map.Entry me : m.entrySet()) {
            String val = (String)xpath.compile((String)me.getKey()).evaluate(doc, XPathConstants.STRING);
            ((Consumer)me.getValue()).accept(val);
        }
    }

    private class XPathNSContext
    implements NamespaceContext {
        final Map<String, String> nsMap = new HashMap<String, String>();

        private XPathNSContext() {
            SignaturePart.this.signatureConfig.getNamespacePrefixes().forEach((k, v) -> this.nsMap.put((String)v, (String)k));
            this.nsMap.put("dsss", "http://schemas.microsoft.com/office/2006/digsig");
            this.nsMap.put("ds", "http://www.w3.org/2000/09/xmldsig#");
        }

        @Override
        public String getNamespaceURI(String prefix) {
            return this.nsMap.get(prefix);
        }

        @Override
        public Iterator getPrefixes(String val) {
            return null;
        }

        @Override
        public String getPrefix(String uri) {
            return null;
        }
    }
}

