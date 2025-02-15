/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.dom.DOMComment;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;
import org.dom4j.io.ElementStack;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.DefaultHandler;

public class DOMSAXContentHandler
extends DefaultHandler
implements LexicalHandler {
    private DOMDocumentFactory documentFactory;
    private Document document;
    private ElementStack elementStack;
    private NamespaceStack namespaceStack;
    private Locator locator;
    private boolean insideCDATASection;
    private StringBuffer cdataText;
    private int declaredNamespaceIndex;
    private InputSource inputSource;
    private Element currentElement;
    private EntityResolver entityResolver;
    private boolean mergeAdjacentText = false;
    private boolean textInTextBuffer = false;
    private boolean ignoreComments = false;
    private StringBuffer textBuffer;
    private boolean stripWhitespaceText = false;

    public DOMSAXContentHandler() {
        this((DOMDocumentFactory)DOMDocumentFactory.getInstance());
    }

    public DOMSAXContentHandler(DOMDocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
        this.elementStack = this.createElementStack();
        this.namespaceStack = new NamespaceStack(documentFactory);
    }

    public org.w3c.dom.Document getDocument() {
        if (this.document == null) {
            this.document = this.createDocument();
        }
        return (org.w3c.dom.Document)((Object)this.document);
    }

    @Override
    public void setDocumentLocator(Locator documentLocator) {
        this.locator = documentLocator;
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            this.completeCurrentTextNode();
        }
        ProcessingInstruction pi = (ProcessingInstruction)((Object)this.documentFactory.createProcessingInstruction(target, data));
        if (this.currentElement != null) {
            ((org.w3c.dom.Element)((Object)this.currentElement)).appendChild(pi);
        } else {
            this.getDocument().appendChild(pi);
        }
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        this.namespaceStack.push(prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        this.namespaceStack.pop(prefix);
        this.declaredNamespaceIndex = this.namespaceStack.size();
    }

    @Override
    public void startDocument() throws SAXException {
        this.document = null;
        this.currentElement = null;
        this.elementStack.clear();
        this.namespaceStack.clear();
        this.declaredNamespaceIndex = 0;
        if (this.mergeAdjacentText && this.textBuffer == null) {
            this.textBuffer = new StringBuffer();
        }
        this.textInTextBuffer = false;
    }

    @Override
    public void endDocument() throws SAXException {
        this.namespaceStack.clear();
        this.elementStack.clear();
        this.currentElement = null;
        this.textBuffer = null;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            this.completeCurrentTextNode();
        }
        QName qName = this.namespaceStack.getQName(namespaceURI, localName, qualifiedName);
        Branch branch = this.currentElement;
        if (branch == null) {
            branch = (Document)((Object)this.getDocument());
        }
        DOMElement element = new DOMElement(qName);
        branch.add(element);
        this.addDeclaredNamespaces(element);
        this.addAttributes(element, attributes);
        this.elementStack.pushElement(element);
        this.currentElement = element;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            this.completeCurrentTextNode();
        }
        this.elementStack.popElement();
        this.currentElement = this.elementStack.peekElement();
    }

    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {
        if (end == 0) {
            return;
        }
        if (this.currentElement != null) {
            if (this.insideCDATASection) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    this.completeCurrentTextNode();
                }
                this.cdataText.append(new String(ch, start, end));
            } else if (this.mergeAdjacentText) {
                this.textBuffer.append(ch, start, end);
                this.textInTextBuffer = true;
            } else {
                DOMText text = new DOMText(new String(ch, start, end));
                ((DOMElement)this.currentElement).add(text);
            }
        }
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
    }

    @Override
    public void endDTD() throws SAXException {
    }

    @Override
    public void startEntity(String name) throws SAXException {
    }

    @Override
    public void endEntity(String name) throws SAXException {
    }

    @Override
    public void startCDATA() throws SAXException {
        this.insideCDATASection = true;
        this.cdataText = new StringBuffer();
    }

    @Override
    public void endCDATA() throws SAXException {
        this.insideCDATASection = false;
        DOMCDATA cdata = new DOMCDATA(this.cdataText.toString());
        ((DOMElement)this.currentElement).add(cdata);
    }

    @Override
    public void comment(char[] ch, int start, int end) throws SAXException {
        if (!this.ignoreComments) {
            String text;
            if (this.mergeAdjacentText && this.textInTextBuffer) {
                this.completeCurrentTextNode();
            }
            if ((text = new String(ch, start, end)).length() > 0) {
                DOMComment domComment = new DOMComment(text);
                if (this.currentElement != null) {
                    ((DOMElement)this.currentElement).add(domComment);
                } else {
                    this.getDocument().appendChild(domComment);
                }
            }
        }
    }

    public ElementStack getElementStack() {
        return this.elementStack;
    }

    public void setElementStack(ElementStack elementStack) {
        this.elementStack = elementStack;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public InputSource getInputSource() {
        return this.inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public boolean isMergeAdjacentText() {
        return this.mergeAdjacentText;
    }

    public void setMergeAdjacentText(boolean mergeAdjacentText) {
        this.mergeAdjacentText = mergeAdjacentText;
    }

    public boolean isStripWhitespaceText() {
        return this.stripWhitespaceText;
    }

    public void setStripWhitespaceText(boolean stripWhitespaceText) {
        this.stripWhitespaceText = stripWhitespaceText;
    }

    public boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    public void setIgnoreComments(boolean ignoreComments) {
        this.ignoreComments = ignoreComments;
    }

    protected void completeCurrentTextNode() {
        if (this.stripWhitespaceText) {
            boolean whitespace = true;
            int size = this.textBuffer.length();
            for (int i = 0; i < size; ++i) {
                if (Character.isWhitespace(this.textBuffer.charAt(i))) continue;
                whitespace = false;
                break;
            }
            if (!whitespace) {
                DOMText domText = new DOMText(this.textBuffer.toString());
                ((DOMElement)this.currentElement).add(domText);
            }
        } else {
            DOMText domText = new DOMText(this.textBuffer.toString());
            ((DOMElement)this.currentElement).add(domText);
        }
        this.textBuffer.setLength(0);
        this.textInTextBuffer = false;
    }

    protected Document createDocument() {
        String encoding = this.getEncoding();
        Document doc = this.documentFactory.createDocument(encoding);
        doc.setEntityResolver(this.entityResolver);
        if (this.inputSource != null) {
            doc.setName(this.inputSource.getSystemId());
        }
        return doc;
    }

    private String getEncoding() {
        if (this.locator == null) {
            return null;
        }
        if (this.locator instanceof Locator2) {
            return ((Locator2)this.locator).getEncoding();
        }
        return null;
    }

    protected void addDeclaredNamespaces(Element element) {
        int size = this.namespaceStack.size();
        while (this.declaredNamespaceIndex < size) {
            Namespace namespace = this.namespaceStack.getNamespace(this.declaredNamespaceIndex);
            String attributeName = this.attributeNameForNamespace(namespace);
            ((DOMElement)element).setAttribute(attributeName, namespace.getURI());
            ++this.declaredNamespaceIndex;
        }
    }

    protected void addAttributes(Element element, Attributes attributes) {
        int size = attributes.getLength();
        for (int i = 0; i < size; ++i) {
            String attributeQName = attributes.getQName(i);
            if (attributeQName.startsWith("xmlns")) continue;
            String attributeURI = attributes.getURI(i);
            String attributeLocalName = attributes.getLocalName(i);
            String attributeValue = attributes.getValue(i);
            QName qName = this.namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeQName);
            DOMAttribute domAttribute = new DOMAttribute(qName, attributeValue);
            ((DOMElement)element).setAttributeNode(domAttribute);
        }
    }

    protected ElementStack createElementStack() {
        return new ElementStack();
    }

    protected String attributeNameForNamespace(Namespace namespace) {
        String xmlns = "xmlns";
        String prefix = namespace.getPrefix();
        if (prefix.length() > 0) {
            return xmlns + ":" + prefix;
        }
        return xmlns;
    }
}

