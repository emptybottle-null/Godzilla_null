/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.VariableContext
 */
package org.dom4j.util;

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
import org.dom4j.NodeFilter;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.rule.Pattern;
import org.jaxen.VariableContext;

public abstract class ProxyDocumentFactory {
    private DocumentFactory proxy;

    public ProxyDocumentFactory() {
        this.proxy = DocumentFactory.getInstance();
    }

    public ProxyDocumentFactory(DocumentFactory proxy) {
        this.proxy = proxy;
    }

    public Document createDocument() {
        return this.proxy.createDocument();
    }

    public Document createDocument(Element rootElement) {
        return this.proxy.createDocument(rootElement);
    }

    public DocumentType createDocType(String name, String publicId, String systemId) {
        return this.proxy.createDocType(name, publicId, systemId);
    }

    public Element createElement(QName qname) {
        return this.proxy.createElement(qname);
    }

    public Element createElement(String name) {
        return this.proxy.createElement(name);
    }

    public Attribute createAttribute(Element owner, QName qname, String value) {
        return this.proxy.createAttribute(owner, qname, value);
    }

    public Attribute createAttribute(Element owner, String name, String value) {
        return this.proxy.createAttribute(owner, name, value);
    }

    public CDATA createCDATA(String text) {
        return this.proxy.createCDATA(text);
    }

    public Comment createComment(String text) {
        return this.proxy.createComment(text);
    }

    public Text createText(String text) {
        return this.proxy.createText(text);
    }

    public Entity createEntity(String name, String text) {
        return this.proxy.createEntity(name, text);
    }

    public Namespace createNamespace(String prefix, String uri) {
        return this.proxy.createNamespace(prefix, uri);
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        return this.proxy.createProcessingInstruction(target, data);
    }

    public ProcessingInstruction createProcessingInstruction(String target, Map<String, String> data) {
        return this.proxy.createProcessingInstruction(target, data);
    }

    public QName createQName(String localName, Namespace namespace) {
        return this.proxy.createQName(localName, namespace);
    }

    public QName createQName(String localName) {
        return this.proxy.createQName(localName);
    }

    public QName createQName(String name, String prefix, String uri) {
        return this.proxy.createQName(name, prefix, uri);
    }

    public QName createQName(String qualifiedName, String uri) {
        return this.proxy.createQName(qualifiedName, uri);
    }

    public XPath createXPath(String xpathExpression) {
        return this.proxy.createXPath(xpathExpression);
    }

    public XPath createXPath(String xpathExpression, VariableContext variableContext) {
        return this.proxy.createXPath(xpathExpression, variableContext);
    }

    public NodeFilter createXPathFilter(String xpathFilterExpression, VariableContext variableContext) {
        return this.proxy.createXPathFilter(xpathFilterExpression, variableContext);
    }

    public NodeFilter createXPathFilter(String xpathFilterExpression) {
        return this.proxy.createXPathFilter(xpathFilterExpression);
    }

    public Pattern createPattern(String xpathPattern) {
        return this.proxy.createPattern(xpathPattern);
    }

    protected DocumentFactory getProxy() {
        return this.proxy;
    }

    protected void setProxy(DocumentFactory proxy) {
        if (proxy == null) {
            proxy = DocumentFactory.getInstance();
        }
        this.proxy = proxy;
    }
}

