/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.VariableContext
 */
package org.dom4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.InvalidXPathException;
import org.dom4j.Namespace;
import org.dom4j.NodeFilter;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.rule.Pattern;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultEntity;
import org.dom4j.tree.DefaultProcessingInstruction;
import org.dom4j.tree.DefaultText;
import org.dom4j.tree.QNameCache;
import org.dom4j.util.SimpleSingleton;
import org.dom4j.util.SingletonStrategy;
import org.dom4j.xpath.DefaultXPath;
import org.dom4j.xpath.XPathPattern;
import org.jaxen.VariableContext;

public class DocumentFactory
implements Serializable {
    private static SingletonStrategy<DocumentFactory> singleton = null;
    protected transient QNameCache cache;
    private Map<String, String> xpathNamespaceURIs;

    private static SingletonStrategy<DocumentFactory> createSingleton() {
        SingletonStrategy<DocumentFactory> result;
        String documentFactoryClassName;
        try {
            documentFactoryClassName = System.getProperty("org.dom4j.factory", "org.dom4j.DocumentFactory");
        } catch (Exception e) {
            documentFactoryClassName = "org.dom4j.DocumentFactory";
        }
        try {
            String singletonClass = System.getProperty("org.dom4j.DocumentFactory.singleton.strategy", "org.dom4j.util.SimpleSingleton");
            Class<?> clazz = Class.forName(singletonClass);
            result = (SingletonStrategy)clazz.newInstance();
        } catch (Exception e) {
            result = new SimpleSingleton<DocumentFactory>();
        }
        result.setSingletonClassName(documentFactoryClassName);
        return result;
    }

    public DocumentFactory() {
        this.init();
    }

    public static synchronized DocumentFactory getInstance() {
        if (singleton == null) {
            singleton = DocumentFactory.createSingleton();
        }
        return singleton.instance();
    }

    public Document createDocument() {
        DefaultDocument answer = new DefaultDocument();
        answer.setDocumentFactory(this);
        return answer;
    }

    public Document createDocument(String encoding) {
        Document answer = this.createDocument();
        answer.setXMLEncoding(encoding);
        return answer;
    }

    public Document createDocument(Element rootElement) {
        Document answer = this.createDocument();
        answer.setRootElement(rootElement);
        return answer;
    }

    public DocumentType createDocType(String name, String publicId, String systemId) {
        return new DefaultDocumentType(name, publicId, systemId);
    }

    public Element createElement(QName qname) {
        return new DefaultElement(qname);
    }

    public Element createElement(String name) {
        return this.createElement(this.createQName(name));
    }

    public Element createElement(String qualifiedName, String namespaceURI) {
        return this.createElement(this.createQName(qualifiedName, namespaceURI));
    }

    public Attribute createAttribute(Element owner, QName qname, String value) {
        return new DefaultAttribute(qname, value);
    }

    public Attribute createAttribute(Element owner, String name, String value) {
        return this.createAttribute(owner, this.createQName(name), value);
    }

    public CDATA createCDATA(String text) {
        return new DefaultCDATA(text);
    }

    public Comment createComment(String text) {
        return new DefaultComment(text);
    }

    public Text createText(String text) {
        if (text == null) {
            String msg = "Adding text to an XML document must not be null";
            throw new IllegalArgumentException(msg);
        }
        return new DefaultText(text);
    }

    public Entity createEntity(String name, String text) {
        return new DefaultEntity(name, text);
    }

    public Namespace createNamespace(String prefix, String uri) {
        return Namespace.get(prefix, uri);
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        return new DefaultProcessingInstruction(target, data);
    }

    public ProcessingInstruction createProcessingInstruction(String target, Map<String, String> data) {
        return new DefaultProcessingInstruction(target, data);
    }

    public QName createQName(String localName, Namespace namespace) {
        return this.cache.get(localName, namespace);
    }

    public QName createQName(String localName) {
        return this.cache.get(localName);
    }

    public QName createQName(String name, String prefix, String uri) {
        return this.cache.get(name, Namespace.get(prefix, uri));
    }

    public QName createQName(String qualifiedName, String uri) {
        return this.cache.get(qualifiedName, uri);
    }

    public XPath createXPath(String xpathExpression) throws InvalidXPathException {
        DefaultXPath xpath = new DefaultXPath(xpathExpression);
        if (this.xpathNamespaceURIs != null) {
            xpath.setNamespaceURIs(this.xpathNamespaceURIs);
        }
        return xpath;
    }

    public XPath createXPath(String xpathExpression, VariableContext variableContext) {
        XPath xpath = this.createXPath(xpathExpression);
        xpath.setVariableContext(variableContext);
        return xpath;
    }

    public NodeFilter createXPathFilter(String xpathFilterExpression, VariableContext variableContext) {
        XPath answer = this.createXPath(xpathFilterExpression);
        answer.setVariableContext(variableContext);
        return answer;
    }

    public NodeFilter createXPathFilter(String xpathFilterExpression) {
        return this.createXPath(xpathFilterExpression);
    }

    public Pattern createPattern(String xpathPattern) {
        return new XPathPattern(xpathPattern);
    }

    public List<QName> getQNames() {
        return this.cache.getQNames();
    }

    public Map<String, String> getXPathNamespaceURIs() {
        return this.xpathNamespaceURIs;
    }

    public void setXPathNamespaceURIs(Map<String, String> namespaceURIs) {
        this.xpathNamespaceURIs = namespaceURIs;
    }

    protected static DocumentFactory createSingleton(String className) {
        try {
            Class<?> theClass = Class.forName(className, true, DocumentFactory.class.getClassLoader());
            return (DocumentFactory)theClass.newInstance();
        } catch (Throwable e) {
            System.out.println("WARNING: Cannot load DocumentFactory: " + className);
            return new DocumentFactory();
        }
    }

    protected QName intern(QName qname) {
        return this.cache.intern(qname);
    }

    protected QNameCache createQNameCache() {
        return new QNameCache(this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.init();
    }

    protected void init() {
        this.cache = this.createQNameCache();
    }
}

