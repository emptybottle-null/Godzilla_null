/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.jcp.xml.dsig.internal.dom.ApacheNodeSetData
 *  org.apache.xml.security.signature.XMLSignatureInput
 */
package org.apache.poi.poifs.crypt.dsig.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.TransformService;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.ApacheNodeSetData;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.RelationshipReferenceDocument;
import org.w3.x2000.x09.xmldsig.TransformDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RelationshipTransformService
extends TransformService {
    public static final String TRANSFORM_URI = "http://schemas.openxmlformats.org/package/2006/RelationshipTransform";
    private final List<String> sourceIds;
    private static final POILogger LOG = POILogFactory.getLogger(RelationshipTransformService.class);

    public RelationshipTransformService() {
        LOG.log(1, "constructor");
        this.sourceIds = new ArrayList<String>();
    }

    public static synchronized void registerDsigProvider() {
        String dsigProvider = "POIXmlDsigProvider";
        if (Security.getProperty("POIXmlDsigProvider") == null) {
            Provider p = new Provider("POIXmlDsigProvider", 1.0, "POIXmlDsigProvider"){
                static final long serialVersionUID = 1L;
            };
            p.put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform", RelationshipTransformService.class.getName());
            p.put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform MechanismType", "DOM");
            Security.addProvider(p);
        }
    }

    @Override
    public void init(TransformParameterSpec params) throws InvalidAlgorithmParameterException {
        LOG.log(1, "init(params)");
        if (!(params instanceof RelationshipTransformParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        }
        RelationshipTransformParameterSpec relParams = (RelationshipTransformParameterSpec)params;
        this.sourceIds.addAll(relParams.sourceIds);
    }

    @Override
    public void init(XMLStructure parent, XMLCryptoContext context) throws InvalidAlgorithmParameterException {
        LOG.log(1, "init(parent,context)");
        LOG.log(1, "parent java type: " + parent.getClass().getName());
        DOMStructure domParent = (DOMStructure)parent;
        Node parentNode = domParent.getNode();
        try {
            TransformDocument transDoc = TransformDocument.Factory.parse(parentNode, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            XmlObject[] xoList = transDoc.getTransform().selectChildren(RelationshipReferenceDocument.type.getDocumentElementName());
            if (xoList.length == 0) {
                LOG.log(5, "no RelationshipReference/@SourceId parameters present");
            }
            for (XmlObject xo : xoList) {
                String sourceId = ((CTRelationshipReference)xo).getSourceId();
                LOG.log(1, "sourceId: ", sourceId);
                this.sourceIds.add(sourceId);
            }
        } catch (XmlException e) {
            throw new InvalidAlgorithmParameterException(e);
        }
    }

    @Override
    public void marshalParams(XMLStructure parent, XMLCryptoContext context) throws MarshalException {
        LOG.log(1, "marshallParams(parent,context)");
        DOMStructure domParent = (DOMStructure)parent;
        Element parentNode = (Element)domParent.getNode();
        Document doc = parentNode.getOwnerDocument();
        for (String sourceId : this.sourceIds) {
            Element el = doc.createElementNS("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi:RelationshipReference");
            el.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:mdssi", "http://schemas.openxmlformats.org/package/2006/digital-signature");
            el.setAttribute("SourceId", sourceId);
            parentNode.appendChild(el);
        }
    }

    @Override
    public AlgorithmParameterSpec getParameterSpec() {
        LOG.log(1, "getParameterSpec");
        return null;
    }

    @Override
    public Data transform(Data data, XMLCryptoContext context) throws TransformException {
        Document doc;
        LOG.log(1, "transform(data,context)");
        LOG.log(1, "data java type: " + data.getClass().getName());
        OctetStreamData octetStreamData = (OctetStreamData)data;
        LOG.log(1, "URI: " + octetStreamData.getURI());
        InputStream octetStream = octetStreamData.getOctetStream();
        try {
            doc = DocumentHelper.readDocument(octetStream);
        } catch (Exception e) {
            throw new TransformException(e.getMessage(), e);
        }
        Element root = doc.getDocumentElement();
        NodeList nl = root.getChildNodes();
        TreeMap<String, Element> rsList = new TreeMap<String, Element>();
        for (int i = nl.getLength() - 1; i >= 0; --i) {
            Element el;
            String id;
            Node n = nl.item(i);
            if ("Relationship".equals(n.getLocalName()) && this.sourceIds.contains(id = (el = (Element)n).getAttribute("Id"))) {
                String targetMode = el.getAttribute("TargetMode");
                if (targetMode == null || targetMode.isEmpty()) {
                    el.setAttribute("TargetMode", "Internal");
                }
                rsList.put(id, el);
            }
            root.removeChild(n);
        }
        for (Element el : rsList.values()) {
            root.appendChild(el);
        }
        LOG.log(1, "# Relationship elements: ", rsList.size());
        return new ApacheNodeSetData(new XMLSignatureInput((Node)root));
    }

    @Override
    public Data transform(Data data, XMLCryptoContext context, OutputStream os) throws TransformException {
        LOG.log(1, "transform(data,context,os)");
        return null;
    }

    @Override
    public boolean isFeatureSupported(String feature) {
        LOG.log(1, "isFeatureSupported(feature)");
        return false;
    }

    public static class RelationshipTransformParameterSpec
    implements TransformParameterSpec {
        List<String> sourceIds = new ArrayList<String>();

        public void addRelationshipReference(String relationshipId) {
            this.sourceIds.add(relationshipId);
        }

        public boolean hasSourceIds() {
            return !this.sourceIds.isEmpty();
        }
    }
}

