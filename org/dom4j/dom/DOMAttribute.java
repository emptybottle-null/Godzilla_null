/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultAttribute;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

public class DOMAttribute
extends DefaultAttribute
implements Attr {
    public DOMAttribute(QName qname) {
        super(qname);
    }

    public DOMAttribute(QName qname, String value) {
        super(qname, value);
    }

    public DOMAttribute(Element parent, QName qname, String value) {
        super(parent, qname, value);
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
        return DOMNodeHelper.getNodeValue(this);
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
        DOMNodeHelper.setNodeValue(this, nodeValue);
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        return DOMNodeHelper.getChildNodes(this);
    }

    @Override
    public Node getFirstChild() {
        return DOMNodeHelper.getFirstChild(this);
    }

    @Override
    public Node getLastChild() {
        return DOMNodeHelper.getLastChild(this);
    }

    @Override
    public Node getPreviousSibling() {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    @Override
    public Node getNextSibling() {
        return DOMNodeHelper.getNextSibling(this);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public Document getOwnerDocument() {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.insertBefore(this, newChild, refChild);
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.replaceChild(this, newChild, oldChild);
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        return DOMNodeHelper.removeChild(this, oldChild);
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        this.checkNewChildNode(newChild);
        return DOMNodeHelper.appendChild(this, newChild);
    }

    private void checkNewChildNode(Node newChild) throws DOMException {
        short nodeType = newChild.getNodeType();
        if (nodeType != 3 && nodeType != 5) {
            throw new DOMException(3, "The node cannot be a child of attribute");
        }
    }

    @Override
    public boolean hasChildNodes() {
        return DOMNodeHelper.hasChildNodes(this);
    }

    @Override
    public Node cloneNode(boolean deep) {
        return DOMNodeHelper.cloneNode(this, deep);
    }

    @Override
    public void normalize() {
        DOMNodeHelper.normalize(this);
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
    public boolean getSpecified() {
        return true;
    }

    @Override
    public org.w3c.dom.Element getOwnerElement() {
        return DOMNodeHelper.asDOMElement(this.getParent());
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public boolean isId() {
        DOMNodeHelper.notSupported();
        return false;
    }

    @Override
    public String getBaseURI() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public short compareDocumentPosition(Node other) throws DOMException {
        DOMNodeHelper.notSupported();
        return 0;
    }

    @Override
    public String getTextContent() throws DOMException {
        return this.getValue();
    }

    @Override
    public void setTextContent(String textContent) throws DOMException {
        this.setValue(textContent);
    }

    @Override
    public boolean isSameNode(Node other) {
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
    public boolean isEqualNode(Node other) {
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

