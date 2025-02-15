/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.dtd.AttributeDecl;
import org.dom4j.dtd.Decl;
import org.dom4j.dtd.ElementDecl;
import org.dom4j.dtd.ExternalEntityDecl;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.io.DispatchHandler;
import org.dom4j.io.ElementStack;
import org.dom4j.tree.AbstractElement;
import org.dom4j.tree.NamespaceStack;
import org.xml.sax.Attributes;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.DefaultHandler;

public class SAXContentHandler
extends DefaultHandler
implements LexicalHandler,
DeclHandler,
DTDHandler {
    private DocumentFactory documentFactory;
    private Document document;
    private ElementStack elementStack;
    private NamespaceStack namespaceStack;
    private ElementHandler elementHandler;
    private Locator locator;
    private String entity;
    private boolean insideDTDSection;
    private boolean insideCDATASection;
    private StringBuffer cdataText;
    private List<Decl> internalDTDDeclarations;
    private List<Decl> externalDTDDeclarations;
    private int declaredNamespaceIndex;
    private EntityResolver entityResolver;
    private InputSource inputSource;
    private Element currentElement;
    private boolean includeInternalDTDDeclarations = false;
    private boolean includeExternalDTDDeclarations = false;
    private int entityLevel;
    private boolean internalDTDsubset = false;
    private boolean mergeAdjacentText = false;
    private boolean textInTextBuffer = false;
    private boolean ignoreComments = false;
    private StringBuffer textBuffer;
    private boolean stripWhitespaceText = false;

    public SAXContentHandler() {
        this(DocumentFactory.getInstance());
    }

    public SAXContentHandler(DocumentFactory documentFactory) {
        this(documentFactory, null);
    }

    public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler) {
        this(documentFactory, elementHandler, null);
        this.elementStack = this.createElementStack();
    }

    public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler, ElementStack elementStack) {
        this.documentFactory = documentFactory;
        this.elementHandler = elementHandler;
        this.elementStack = elementStack;
        this.namespaceStack = new NamespaceStack(documentFactory);
    }

    public Document getDocument() {
        if (this.document == null) {
            this.document = this.createDocument();
        }
        return this.document;
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
        if (this.currentElement != null) {
            this.currentElement.addProcessingInstruction(target, data);
        } else {
            this.getDocument().addProcessingInstruction(target, data);
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
        if (this.elementHandler != null && this.elementHandler instanceof DispatchHandler) {
            this.elementStack.setDispatchHandler((DispatchHandler)this.elementHandler);
        }
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
            branch = this.getDocument();
        }
        Element element = branch.addElement(qName);
        this.addDeclaredNamespaces(element);
        this.addAttributes(element, attributes);
        this.elementStack.pushElement(element);
        this.currentElement = element;
        this.entity = null;
        if (this.elementHandler != null) {
            this.elementHandler.onStart(this.elementStack);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            this.completeCurrentTextNode();
        }
        if (this.elementHandler != null && this.currentElement != null) {
            this.elementHandler.onEnd(this.elementStack);
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
            if (this.entity != null) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    this.completeCurrentTextNode();
                }
                this.currentElement.addEntity(this.entity, new String(ch, start, end));
                this.entity = null;
            } else if (this.insideCDATASection) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    this.completeCurrentTextNode();
                }
                this.cdataText.append(new String(ch, start, end));
            } else if (this.mergeAdjacentText) {
                this.textBuffer.append(ch, start, end);
                this.textInTextBuffer = true;
            } else {
                this.currentElement.addText(new String(ch, start, end));
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
        this.getDocument().addDocType(name, publicId, systemId);
        this.insideDTDSection = true;
        this.internalDTDsubset = true;
    }

    @Override
    public void endDTD() throws SAXException {
        this.insideDTDSection = false;
        DocumentType docType = this.getDocument().getDocType();
        if (docType != null) {
            if (this.internalDTDDeclarations != null) {
                docType.setInternalDeclarations(this.internalDTDDeclarations);
            }
            if (this.externalDTDDeclarations != null) {
                docType.setExternalDeclarations(this.externalDTDDeclarations);
            }
        }
        this.internalDTDDeclarations = null;
        this.externalDTDDeclarations = null;
    }

    @Override
    public void startEntity(String name) throws SAXException {
        ++this.entityLevel;
        this.entity = null;
        if (!this.insideDTDSection && !this.isIgnorableEntity(name)) {
            this.entity = name;
        }
        this.internalDTDsubset = false;
    }

    @Override
    public void endEntity(String name) throws SAXException {
        --this.entityLevel;
        this.entity = null;
        if (this.entityLevel == 0) {
            this.internalDTDsubset = true;
        }
    }

    @Override
    public void startCDATA() throws SAXException {
        this.insideCDATASection = true;
        this.cdataText = new StringBuffer();
    }

    @Override
    public void endCDATA() throws SAXException {
        this.insideCDATASection = false;
        this.currentElement.addCDATA(this.cdataText.toString());
    }

    @Override
    public void comment(char[] ch, int start, int end) throws SAXException {
        if (!this.ignoreComments) {
            if (this.mergeAdjacentText && this.textInTextBuffer) {
                this.completeCurrentTextNode();
            }
            String text = new String(ch, start, end);
            if (!this.insideDTDSection && text.length() > 0) {
                if (this.currentElement != null) {
                    this.currentElement.addComment(text);
                } else {
                    this.getDocument().addComment(text);
                }
            }
        }
    }

    @Override
    public void elementDecl(String name, String model) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                this.addDTDDeclaration(new ElementDecl(name, model));
            }
        } else if (this.includeExternalDTDDeclarations) {
            this.addExternalDTDDeclaration(new ElementDecl(name, model));
        }
    }

    @Override
    public void attributeDecl(String eName, String aName, String type, String valueDefault, String val) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                this.addDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
            }
        } else if (this.includeExternalDTDDeclarations) {
            this.addExternalDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
        }
    }

    @Override
    public void internalEntityDecl(String name, String value) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                this.addDTDDeclaration(new InternalEntityDecl(name, value));
            }
        } else if (this.includeExternalDTDDeclarations) {
            this.addExternalDTDDeclaration(new InternalEntityDecl(name, value));
        }
    }

    @Override
    public void externalEntityDecl(String name, String publicId, String sysId) throws SAXException {
        ExternalEntityDecl declaration = new ExternalEntityDecl(name, publicId, sysId);
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                this.addDTDDeclaration(declaration);
            }
        } else if (this.includeExternalDTDDeclarations) {
            this.addExternalDTDDeclaration(declaration);
        }
    }

    @Override
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
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

    public boolean isIncludeInternalDTDDeclarations() {
        return this.includeInternalDTDDeclarations;
    }

    public void setIncludeInternalDTDDeclarations(boolean include) {
        this.includeInternalDTDDeclarations = include;
    }

    public boolean isIncludeExternalDTDDeclarations() {
        return this.includeExternalDTDDeclarations;
    }

    public void setIncludeExternalDTDDeclarations(boolean include) {
        this.includeExternalDTDDeclarations = include;
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
                this.currentElement.addText(this.textBuffer.toString());
            }
        } else {
            this.currentElement.addText(this.textBuffer.toString());
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

    protected boolean isIgnorableEntity(String name) {
        return "amp".equals(name) || "apos".equals(name) || "gt".equals(name) || "lt".equals(name) || "quot".equals(name);
    }

    protected void addDeclaredNamespaces(Element element) {
        Namespace elementNamespace = element.getNamespace();
        int size = this.namespaceStack.size();
        while (this.declaredNamespaceIndex < size) {
            Namespace namespace = this.namespaceStack.getNamespace(this.declaredNamespaceIndex);
            element.add(namespace);
            ++this.declaredNamespaceIndex;
        }
    }

    protected void addAttributes(Element element, Attributes attributes) {
        boolean noNamespaceAttributes = false;
        if (element instanceof AbstractElement) {
            AbstractElement baseElement = (AbstractElement)element;
            baseElement.setAttributes(attributes, this.namespaceStack, noNamespaceAttributes);
        } else {
            int size = attributes.getLength();
            for (int i = 0; i < size; ++i) {
                String attributeQName = attributes.getQName(i);
                if (!noNamespaceAttributes && attributeQName.startsWith("xmlns")) continue;
                String attributeURI = attributes.getURI(i);
                String attributeLocalName = attributes.getLocalName(i);
                String attributeValue = attributes.getValue(i);
                QName qName = this.namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeQName);
                element.addAttribute(qName, attributeValue);
            }
        }
    }

    protected void addDTDDeclaration(Decl declaration) {
        if (this.internalDTDDeclarations == null) {
            this.internalDTDDeclarations = new ArrayList<Decl>();
        }
        this.internalDTDDeclarations.add(declaration);
    }

    protected void addExternalDTDDeclaration(Decl declaration) {
        if (this.externalDTDDeclarations == null) {
            this.externalDTDDeclarations = new ArrayList<Decl>();
        }
        this.externalDTDDeclarations.add(declaration);
    }

    protected ElementStack createElementStack() {
        return new ElementStack();
    }
}

