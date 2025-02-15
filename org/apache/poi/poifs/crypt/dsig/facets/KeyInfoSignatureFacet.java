/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.jcp.xml.dsig.internal.dom.DOMKeyInfo
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.Key;
import java.security.KeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.apache.jcp.xml.dsig.internal.dom.DOMKeyInfo;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KeyInfoSignatureFacet
extends SignatureFacet {
    private static final POILogger LOG = POILogFactory.getLogger(KeyInfoSignatureFacet.class);

    @Override
    public void postSign(Document document) throws MarshalException {
        LOG.log(1, "postSign");
        NodeList nl = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Object");
        Node nextSibling = nl.getLength() == 0 ? null : nl.item(0);
        KeyInfoFactory keyInfoFactory = this.signatureConfig.getKeyInfoFactory();
        ArrayList<Object> x509DataObjects = new ArrayList<Object>();
        X509Certificate signingCertificate = this.signatureConfig.getSigningCertificateChain().get(0);
        ArrayList<XMLStructure> keyInfoContent = new ArrayList<XMLStructure>();
        if (this.signatureConfig.isIncludeKeyValue()) {
            KeyValue keyValue;
            try {
                keyValue = keyInfoFactory.newKeyValue(signingCertificate.getPublicKey());
            } catch (KeyException e) {
                throw new RuntimeException("key exception: " + e.getMessage(), e);
            }
            keyInfoContent.add(keyValue);
        }
        if (this.signatureConfig.isIncludeIssuerSerial()) {
            x509DataObjects.add(keyInfoFactory.newX509IssuerSerial(signingCertificate.getIssuerX500Principal().toString(), signingCertificate.getSerialNumber()));
        }
        if (this.signatureConfig.isIncludeEntireCertificateChain()) {
            x509DataObjects.addAll(this.signatureConfig.getSigningCertificateChain());
        } else {
            x509DataObjects.add(signingCertificate);
        }
        if (!x509DataObjects.isEmpty()) {
            X509Data x509Data = keyInfoFactory.newX509Data(x509DataObjects);
            keyInfoContent.add(x509Data);
        }
        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(keyInfoContent);
        DOMKeyInfo domKeyInfo = (DOMKeyInfo)keyInfo;
        Key key = new Key(){
            private static final long serialVersionUID = 1L;

            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }
        };
        Element n = document.getDocumentElement();
        DOMSignContext domSignContext = nextSibling == null ? new DOMSignContext(key, (Node)n) : new DOMSignContext(key, (Node)n, nextSibling);
        this.signatureConfig.getNamespacePrefixes().forEach(domSignContext::putNamespacePrefix);
        DOMStructure domStructure = new DOMStructure(n);
        domKeyInfo.marshal((XMLStructure)domStructure, (XMLCryptoContext)domSignContext);
        if (nextSibling != null) {
            NodeList kiNl = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
            if (kiNl.getLength() != 1) {
                throw new RuntimeException("KeyInfo wasn't set");
            }
            nextSibling.getParentNode().insertBefore(kiNl.item(0), nextSibling);
        }
    }
}

