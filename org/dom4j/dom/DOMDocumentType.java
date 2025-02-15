/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultDocumentType;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class DOMDocumentType
extends DefaultDocumentType
implements DocumentType {
    public DOMDocumentType() {
    }

    public DOMDocumentType(String elementName, String systemID) {
        super(elementName, systemID);
    }

    public DOMDocumentType(String name, String publicID, String systemID) {
        super(name, publicID, systemID);
    }

    public boolean supports(String feature, String version) {
        return DOMNodeHelper.supports(this, feature, version);
    }

    @Override
    public String getNamespaceURI() {
        return DOMNodeHelper.getNamespaceURI(this);
    }

    @Override
    public String getPrefix() {
        return DOMNodeHelper.getPrefix(this);
    }

    @Override
    public void setPrefix(String prefix) throws DOMException {
        DOMNodeHelper.setPrefix(this, prefix);
    }

    @Override
    public String getLocalName() {
        return DOMNodeHelper.getLocalName(this);
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
    public Node getParentNode() {
        return DOMNodeHelper.getParentNode(this);
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
        throw new DOMException(3, "DocumentType nodes cannot have children");
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
    public NamedNodeMap getEntities() {
        return null;
    }

    @Override
    public NamedNodeMap getNotations() {
        return null;
    }

    @Override
    public String getPublicId() {
        return this.getPublicID();
    }

    @Override
    public String getSystemId() {
        return this.getSystemID();
    }

    @Override
    public String getInternalSubset() {
        return this.getElementName();
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
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void setTextContent(String textContent) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public boolean isSameNode(Node other) {
        DOMNodeHelper.notSupported();
        return false;
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

    @Override
    public short getNodeType() {
        DOMNodeHelper.notSupported();
        return 0;
    }
}

