/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import org.dom4j.Element;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultNamespace;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class DOMNamespace
extends DefaultNamespace
implements Node {
    public DOMNamespace(String prefix, String uri) {
        super(prefix, uri);
    }

    public DOMNamespace(Element parent, String prefix, String uri) {
        super(parent, prefix, uri);
    }

    public boolean supports(String feature, String version) {
        return DOMNodeHelper.supports(this, feature, version);
    }

    @Override
    public String getNamespaceURI() {
        return DOMNodeHelper.getNamespaceURI(this);
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
        return DOMNodeHelper.getNodeValue(this);
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
        DOMNodeHelper.setNodeValue(this, nodeValue);
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
        return DOMNodeHelper.getAttributes(this);
    }

    @Override
    public Document getOwnerDocument() {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        return DOMNodeHelper.insertBefore(this, newChild, refChild);
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        return DOMNodeHelper.replaceChild(this, newChild, oldChild);
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        return DOMNodeHelper.removeChild(this, oldChild);
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        return DOMNodeHelper.appendChild(this, newChild);
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

