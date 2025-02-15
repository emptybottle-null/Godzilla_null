/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import java.util.ArrayList;
import org.dom4j.DocumentFactory;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMDocumentType;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.tree.DefaultDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class DOMDocument
extends DefaultDocument
implements Document {
    private static final DOMDocumentFactory DOCUMENT_FACTORY = (DOMDocumentFactory)DOMDocumentFactory.getInstance();

    public DOMDocument() {
        this.init();
    }

    public DOMDocument(String name) {
        super(name);
        this.init();
    }

    public DOMDocument(DOMElement rootElement) {
        super(rootElement);
        this.init();
    }

    public DOMDocument(DOMDocumentType docType) {
        super(docType);
        this.init();
    }

    public DOMDocument(DOMElement rootElement, DOMDocumentType docType) {
        super(rootElement, docType);
        this.init();
    }

    public DOMDocument(String name, DOMElement rootElement, DOMDocumentType docType) {
        super(name, rootElement, docType);
        this.init();
    }

    private void init() {
        this.setDocumentFactory(DOCUMENT_FACTORY);
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
        return "#document";
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
        return null;
    }

    @Override
    public Document getOwnerDocument() {
        return null;
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
        if (nodeType != 1 && nodeType != 8 && nodeType != 7 && nodeType != 10) {
            throw new DOMException(3, "Given node cannot be a child of document");
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
    public NodeList getElementsByTagName(String name) {
        ArrayList<Node> list = new ArrayList<Node>();
        DOMNodeHelper.appendElementsByTagName(list, this, name);
        return DOMNodeHelper.createNodeList(list);
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespace, String name) {
        ArrayList<Node> list = new ArrayList<Node>();
        DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, name);
        return DOMNodeHelper.createNodeList(list);
    }

    @Override
    public DocumentType getDoctype() {
        return DOMNodeHelper.asDOMDocumentType(this.getDocType());
    }

    @Override
    public DOMImplementation getImplementation() {
        if (this.getDocumentFactory() instanceof DOMImplementation) {
            return (DOMImplementation)((Object)this.getDocumentFactory());
        }
        return DOCUMENT_FACTORY;
    }

    @Override
    public Element getDocumentElement() {
        return DOMNodeHelper.asDOMElement(this.getRootElement());
    }

    @Override
    public Element createElement(String name) throws DOMException {
        return (Element)((Object)this.getDocumentFactory().createElement(name));
    }

    @Override
    public DocumentFragment createDocumentFragment() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public Text createTextNode(String data) {
        return (Text)((Object)this.getDocumentFactory().createText(data));
    }

    @Override
    public Comment createComment(String data) {
        return (Comment)((Object)this.getDocumentFactory().createComment(data));
    }

    @Override
    public CDATASection createCDATASection(String data) throws DOMException {
        return (CDATASection)((Object)this.getDocumentFactory().createCDATA(data));
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        return (ProcessingInstruction)((Object)this.getDocumentFactory().createProcessingInstruction(target, data));
    }

    @Override
    public Attr createAttribute(String name) throws DOMException {
        QName qname = this.getDocumentFactory().createQName(name);
        return (Attr)((Object)this.getDocumentFactory().createAttribute(null, qname, ""));
    }

    @Override
    public EntityReference createEntityReference(String name) throws DOMException {
        return (EntityReference)((Object)this.getDocumentFactory().createEntity(name, null));
    }

    @Override
    public org.w3c.dom.Node importNode(org.w3c.dom.Node importedNode, boolean deep) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        QName qname = this.getDocumentFactory().createQName(qualifiedName, namespaceURI);
        return (Element)((Object)this.getDocumentFactory().createElement(qname));
    }

    @Override
    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        QName qname = this.getDocumentFactory().createQName(qualifiedName, namespaceURI);
        return (Attr)((Object)this.getDocumentFactory().createAttribute(null, qname, null));
    }

    @Override
    public Element getElementById(String elementId) {
        return DOMNodeHelper.asDOMElement(this.elementByID(elementId));
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        if (super.getDocumentFactory() == null) {
            return DOCUMENT_FACTORY;
        }
        return super.getDocumentFactory();
    }

    @Override
    public String getInputEncoding() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public String getXmlEncoding() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public boolean getXmlStandalone() {
        DOMNodeHelper.notSupported();
        return false;
    }

    @Override
    public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public String getXmlVersion() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void setXmlVersion(String xmlVersion) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public boolean getStrictErrorChecking() {
        DOMNodeHelper.notSupported();
        return false;
    }

    @Override
    public void setStrictErrorChecking(boolean strictErrorChecking) {
        DOMNodeHelper.notSupported();
    }

    @Override
    public String getDocumentURI() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void setDocumentURI(String documentURI) {
        DOMNodeHelper.notSupported();
    }

    @Override
    public org.w3c.dom.Node adoptNode(org.w3c.dom.Node source) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public DOMConfiguration getDomConfig() {
        DOMNodeHelper.notSupported();
        return null;
    }

    @Override
    public void normalizeDocument() {
        DOMNodeHelper.notSupported();
    }

    @Override
    public org.w3c.dom.Node renameNode(org.w3c.dom.Node n, String namespaceURI, String qualifiedName) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
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
    public boolean isEqualNode(org.w3c.dom.Node other) {
        DOMNodeHelper.notSupported();
        return false;
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

