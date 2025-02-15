/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.dom.DOMComment;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentType;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMEntityReference;
import org.dom4j.dom.DOMNamespace;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.dom.DOMProcessingInstruction;
import org.dom4j.dom.DOMText;
import org.dom4j.util.SingletonStrategy;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;

public class DOMDocumentFactory
extends DocumentFactory
implements DOMImplementation {
    private static SingletonStrategy<DOMDocumentFactory> singleton = null;

    public static DocumentFactory getInstance() {
        return singleton.instance();
    }

    @Override
    public Document createDocument() {
        DOMDocument answer = new DOMDocument();
        answer.setDocumentFactory(this);
        return answer;
    }

    @Override
    public DocumentType createDocType(String name, String publicId, String systemId) {
        return new DOMDocumentType(name, publicId, systemId);
    }

    @Override
    public Element createElement(QName qname) {
        return new DOMElement(qname);
    }

    public Element createElement(QName qname, int attributeCount) {
        return new DOMElement(qname, attributeCount);
    }

    @Override
    public Attribute createAttribute(Element owner, QName qname, String value) {
        return new DOMAttribute(qname, value);
    }

    @Override
    public CDATA createCDATA(String text) {
        return new DOMCDATA(text);
    }

    @Override
    public Comment createComment(String text) {
        return new DOMComment(text);
    }

    @Override
    public Text createText(String text) {
        return new DOMText(text);
    }

    public Entity createEntity(String name) {
        return new DOMEntityReference(name);
    }

    @Override
    public Entity createEntity(String name, String text) {
        return new DOMEntityReference(name, text);
    }

    @Override
    public Namespace createNamespace(String prefix, String uri) {
        return new DOMNamespace(prefix, uri);
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        return new DOMProcessingInstruction(target, data);
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, Map<String, String> data) {
        return new DOMProcessingInstruction(target, data);
    }

    @Override
    public boolean hasFeature(String feat, String version) {
        if ("XML".equalsIgnoreCase(feat) || "Core".equalsIgnoreCase(feat)) {
            return version == null || version.length() == 0 || "1.0".equals(version) || "2.0".equals(version);
        }
        return false;
    }

    @Override
    public org.w3c.dom.DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
        return new DOMDocumentType(qualifiedName, publicId, systemId);
    }

    @Override
    public org.w3c.dom.Document createDocument(String namespaceURI, String qualifiedName, org.w3c.dom.DocumentType docType) throws DOMException {
        DOMDocument document;
        if (docType != null) {
            DOMDocumentType documentType = this.asDocumentType(docType);
            document = new DOMDocument(documentType);
        } else {
            document = new DOMDocument();
        }
        document.addElement(this.createQName(qualifiedName, namespaceURI));
        return document;
    }

    protected DOMDocumentType asDocumentType(org.w3c.dom.DocumentType docType) {
        if (docType instanceof DOMDocumentType) {
            return (DOMDocumentType)docType;
        }
        return new DOMDocumentType(docType.getName(), docType.getPublicId(), docType.getSystemId());
    }

    @Override
    public Object getFeature(String feature, String version) {
        DOMNodeHelper.notSupported();
        return null;
    }

    static {
        try {
            String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
            Class<?> clazz = null;
            try {
                String singletonClass = defaultSingletonClass;
                singletonClass = System.getProperty("org.dom4j.dom.DOMDocumentFactory.singleton.strategy", singletonClass);
                clazz = Class.forName(singletonClass);
            } catch (Exception exc1) {
                try {
                    String singletonClass = defaultSingletonClass;
                    clazz = Class.forName(singletonClass);
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            singleton = (SingletonStrategy)clazz.newInstance();
            singleton.setSingletonClassName(DOMDocumentFactory.class.getName());
        } catch (Exception exception) {
            // empty catch block
        }
    }
}

