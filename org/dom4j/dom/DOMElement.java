/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMAttributeNodeMap;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultElement;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

public class DOMElement
extends DefaultElement
implements Element {
    private static final DocumentFactory DOCUMENT_FACTORY = DOMDocumentFactory.getInstance();

    public DOMElement(String name) {
        super(name);
    }

    public DOMElement(QName qname) {
        super(qname);
    }

    public DOMElement(QName qname, int attributeCount) {
        super(qname, attributeCount);
    }

    public DOMElement(String name, Namespace namespace) {
        super(name, namespace);
    }

    public boolean supports(String feature, String version) {
        return DOMNodeHelper.supports(this, feature, version);
    }

    @Override
    public String getNamespaceURI() {
        return this.getQName().getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return this.getQName().getNamespacePrefix();
    }

    @Override
    public void setPrefix(String prefix) throws DOMException {
        DOMNodeHelper.setPrefix(this, prefix);
    }

    @Override
    public String getLocalName() {
        return this.getQName().getName();
    }

    @Override
    public String getNodeName() {
        return this.getName();
    }

    @Override
    public String getNodeValue() throws DOMException {
        return null;
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
    }

    @Override
    public org.w3c.dom.Node getParentNode() {
        return DOMNodeHelper.getParentNode(this);
    }

    @Override
    public NodeList getChildNodes() {
        return DOMNodeHelper.createNodeList(this.content());
    }

    @Override
    public org.w3c.dom.Node getFirstChild() {
        return DOMNodeHelper.asDOMNode(this.node(0));
    }

    @Override
    public org.w3c.dom.Node getLastChild() {
        return DOMNodeHelper.asDOMNode(this.node(this.nodeCount() - 1));
    }

    @Override
    public org.w3c.dom.Node getPreviousSibling() {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    @Override
    public org.w3c.dom.Node getNextSibling() {
        return DOMNodeHelper.getNextSibling(this);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return new DOMAttributeNodeMap(this);
    }

    @Override
    public Document getOwnerDocument() {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    @Override
    public org.w3c.dom.Node insertBefore(org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.insertBefore(this, newChild, refChild);
    }

    @Override
    public org.w3c.dom.Node replaceChild(org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.replaceChild(this, newChild, oldChild);
    }

    @Override
    public org.w3c.dom.Node removeChild(org.w3c.dom.Node oldChild) throws DOMException {
        return DOMNodeHelper.removeChild(this, oldChild);
    }

    @Override
    public org.w3c.dom.Node appendChild(org.w3c.dom.Node newChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.appendChild(this, newChild);
    }

    private void checkNewChildNode(org.w3c.dom.Node newChild) throws DOMException {
        short nodeType = newChild.getNodeType();
        if (nodeType != 1 && nodeType != 3 && nodeType != 8 && nodeType != 7 && nodeType != 4 && nodeType != 5) {
            throw new DOMException(3, "Given node cannot be a child of element");
        }
    }

    @Override
    public boolean hasChildNodes() {
        return this.nodeCount() > 0;
    }

    @Override
    public org.w3c.dom.Node cloneNode(boolean deep) {
        return DOMNodeHelper.cloneNode(this, deep);
    }

    @Override
    public boolean isSupported(String feature, String version) {
        return DOMNodeHelper.isSupported(this, feature, version);
    }

    @Override
    public boolean hasAttributes() {
        return DOMNodeHelper.hasAttributes(this);
    }

    @Override
    public String getTagName() {
        return this.getName();
    }

    @Override
    public String getAttribute(String name) {
        String answer = this.attributeValue(name);
        return answer != null ? answer : "";
    }

    @Override
    public void setAttribute(String name, String value) throws DOMException {
        this.addAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) throws DOMException {
        Attribute attribute = this.attribute(name);
        if (attribute != null) {
            this.remove(attribute);
        }
    }

    @Override
    public Attr getAttributeNode(String name) {
        return DOMNodeHelper.asDOMAttr(this.attribute(name));
    }

    @Override
    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException(7, "No modification allowed");
        }
        Attribute attribute = this.attribute(newAttr);
        if (attribute != newAttr) {
            if (newAttr.getOwnerElement() != null) {
                throw new DOMException(10, "Attribute is already in use");
            }
            Attribute newAttribute = this.createAttribute(newAttr);
            if (attribute != null) {
                attribute.detach();
            }
            this.add(newAttribute);
        }
        return DOMNodeHelper.asDOMAttr(attribute);
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        Attribute attribute = this.attribute(oldAttr);
        if (attribute != null) {
            attribute.detach();
            return DOMNodeHelper.asDOMAttr(attribute);
        }
        throw new DOMException(8, "No such attribute");
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName) {
        String answer;
        Attribute attribute = this.attribute(namespaceURI, localName);
        if (attribute != null && (answer = attribute.getValue()) != null) {
            return answer;
        }
        return "";
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
        Attribute attribute = this.attribute(namespaceURI, qualifiedName);
        if (attribute != null) {
            attribute.setValue(value);
        } else {
            QName qname = this.getQName(namespaceURI, qualifiedName);
            this.addAttribute(qname, value);
        }
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
        Attribute attribute = this.attribute(namespaceURI, localName);
        if (attribute != null) {
            this.remove(attribute);
        }
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        Attribute attribute = this.attribute(namespaceURI, localName);
        if (attribute != null) {
            DOMNodeHelper.asDOMAttr(attribute);
        }
        return null;
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        Attribute attribute = this.attribute(newAttr.getNamespaceURI(), newAttr.getLocalName());
        if (attribute != null) {
            attribute.setValue(newAttr.getValue());
        } else {
            attribute = this.createAttribute(newAttr);
            this.add(attribute);
        }
        return DOMNodeHelper.asDOMAttr(attribute);
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        ArrayList<Node> list = new ArrayList<Node>();
        DOMNodeHelper.appendElementsByTagName(list, this, name);
        return DOMNodeHelper.createNodeList(list);
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespace, String lName) {
        ArrayList<Node> list = new ArrayList<Node>();
        DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, lName);
        return DOMNodeHelper.createNodeList(list);
    }

    @Override
    public boolean hasAttribute(String name) {
        return this.attribute(name) != null;
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName) {
        return this.attribute(namespaceURI, localName) != null;
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        DocumentFactory factory = this.getQName().getDocumentFactory();
        return factory != null ? factory : DOCUMENT_FACTORY;
    }

    protected Attribute attribute(Attr attr) {
        return this.attribute(DOCUMENT_FACTORY.createQName(attr.getLocalName(), attr.getPrefix(), attr.getNamespaceURI()));
    }

    protected Attribute attribute(String namespaceURI, String localName) {
        List<Attribute> attributes = this.attributeList();
        int size = attributes.size();
        for (Attribute attribute : attributes) {
            if (!localName.equals(attribute.getName()) || (namespaceURI != null && namespaceURI.length() != 0 || attribute.getNamespaceURI() != null && attribute.getNamespaceURI().length() != 0) && (namespaceURI == null || !namespaceURI.equals(attribute.getNamespaceURI()))) continue;
            return attribute;
        }
        return null;
    }

    protected Attribute createAttribute(Attr newAttr) {
        QName qname = null;
        String name = newAttr.getLocalName();
        if (name != null) {
            String prefix = newAttr.getPrefix();
            String uri = newAttr.getNamespaceURI();
            qname = this.getDocumentFactory().createQName(name, prefix, uri);
        } else {
            name = newAttr.getName();
            qname = this.getDocumentFactory().createQName(name);
        }
        return new DOMAttribute(qname, newAttr.getValue());
    }

    protected QName getQName(String namespace, String qualifiedName) {
        int index = qualifiedName.indexOf(58);
        String prefix = "";
        String localName = qualifiedName;
        if (index >= 0) {
            prefix = qualifiedName.substring(0, index);
            localName = qualifiedName.substring(index + 1);
        }
        return this.getDocumentFactory().createQName(localName, prefix, namespace);
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void setIdAttribute(String name, boolean isId) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public String getBaseURI() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public short compareDocumentPosition(org.w3c.dom.Node other) throws DOMException {
        DOMNodeHelper.notSupported();
        return 0;
    }

    @Override
    public String getTextContent() throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void setTextContent(String textContent) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public boolean isSameNode(org.w3c.dom.Node other) {
        return DOMNodeHelper.isNodeSame(this, other);
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        DOMNodeHelper.notSupported();
        return false;
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public boolean isEqualNode(org.w3c.dom.Node other) {
        return DOMNodeHelper.isNodeEquals(this, other);
    }

    @Override
    public Object getFeature(String feature, String version) {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public Object getUserData(String key) {
        DOMNodeHelper.notSupported();
        return null;
    }
}

