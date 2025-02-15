/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import org.dom4j.CDATA;
import org.dom4j.Element;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultCDATA;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class DOMCDATA
extends DefaultCDATA
implements CDATASection {
    public DOMCDATA(String text) {
        super(text);
    }

    public DOMCDATA(Element parent, String text) {
        super(parent, text);
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
        return "#cdata-section";
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
        throw new DOMException(3, "CDATASection nodes cannot have children");
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
    public String getData() throws DOMException {
        return DOMNodeHelper.getData(this);
    }

    @Override
    public void setData(String data) throws DOMException {
        DOMNodeHelper.setData(this, data);
    }

    @Override
    public int getLength() {
        return DOMNodeHelper.getLength(this);
    }

    @Override
    public String substringData(int offset, int count) throws DOMException {
        return DOMNodeHelper.substringData(this, offset, count);
    }

    @Override
    public void appendData(String arg) throws DOMException {
        DOMNodeHelper.appendData(this, arg);
    }

    @Override
    public void insertData(int offset, String arg) throws DOMException {
        DOMNodeHelper.insertData(this, offset, arg);
    }

    @Override
    public void deleteData(int offset, int count) throws DOMException {
        DOMNodeHelper.deleteData(this, offset, count);
    }

    @Override
    public void replaceData(int offset, int count, String arg) throws DOMException {
        DOMNodeHelper.replaceData(this, offset, count, arg);
    }

    @Override
    public Text splitText(int offset) throws DOMException {
        int length;
        if (this.isReadOnly()) {
            throw new DOMException(7, "CharacterData node is read only: " + this);
        }
        String text = this.getText();
        int n = length = text != null ? text.length() : 0;
        if (offset < 0 || offset >= length) {
            throw new DOMException(1, "No text at offset: " + offset);
        }
        String start = text.substring(0, offset);
        String rest = text.substring(offset);
        this.setText(start);
        Element parent = this.getParent();
        CDATA newText = this.createCDATA(rest);
        if (parent != null) {
            parent.add(newText);
        }
        return DOMNodeHelper.asDOMText(newText);
    }

    protected CDATA createCDATA(String text) {
        return new DOMCDATA(text);
    }

    @Override
    public boolean isElementContentWhitespace() {
        DOMNodeHelper.notSupported();
        return false;
    }

    @Override
    public String getWholeText() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public Text replaceWholeText(String content) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
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

