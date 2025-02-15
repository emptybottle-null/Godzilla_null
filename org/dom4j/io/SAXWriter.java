/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.io.DocumentInputSource;
import org.dom4j.tree.NamespaceStack;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.LocatorImpl;

public class SAXWriter
implements XMLReader {
    protected static final String[] LEXICAL_HANDLER_NAMES = new String[]{"http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler"};
    protected static final String FEATURE_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String FEATURE_NAMESPACES = "http://xml.org/sax/features/namespaces";
    private ContentHandler contentHandler;
    private DTDHandler dtdHandler;
    private EntityResolver entityResolver;
    private ErrorHandler errorHandler;
    private LexicalHandler lexicalHandler;
    private AttributesImpl attributes = new AttributesImpl();
    private Map<String, Boolean> features = new HashMap<String, Boolean>();
    private Map<String, Object> properties = new HashMap<String, Object>();
    private boolean declareNamespaceAttributes;

    public SAXWriter() {
        this.properties.put(FEATURE_NAMESPACE_PREFIXES, Boolean.FALSE);
        this.properties.put(FEATURE_NAMESPACE_PREFIXES, Boolean.TRUE);
    }

    public SAXWriter(ContentHandler contentHandler) {
        this();
        this.contentHandler = contentHandler;
    }

    public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler) {
        this();
        this.contentHandler = contentHandler;
        this.lexicalHandler = lexicalHandler;
    }

    public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler, EntityResolver entityResolver) {
        this();
        this.contentHandler = contentHandler;
        this.lexicalHandler = lexicalHandler;
        this.entityResolver = entityResolver;
    }

    public void write(Node node) throws SAXException {
        short nodeType = node.getNodeType();
        switch (nodeType) {
            case 1: {
                this.write((Element)node);
                break;
            }
            case 2: {
                this.write((Attribute)node);
                break;
            }
            case 3: {
                this.write(node.getText());
                break;
            }
            case 4: {
                this.write((CDATA)node);
                break;
            }
            case 5: {
                this.write((Entity)node);
                break;
            }
            case 7: {
                this.write((ProcessingInstruction)node);
                break;
            }
            case 8: {
                this.write((Comment)node);
                break;
            }
            case 9: {
                this.write((Document)node);
                break;
            }
            case 10: {
                this.write((DocumentType)node);
                break;
            }
            case 13: {
                break;
            }
            default: {
                throw new SAXException("Invalid node type: " + node);
            }
        }
    }

    public void write(Document document) throws SAXException {
        if (document != null) {
            this.checkForNullHandlers();
            this.documentLocator(document);
            this.startDocument();
            this.entityResolver(document);
            this.dtdHandler(document);
            this.writeContent(document, new NamespaceStack());
            this.endDocument();
        }
    }

    public void write(Element element) throws SAXException {
        this.write(element, new NamespaceStack());
    }

    public void writeOpen(Element element) throws SAXException {
        this.startElement(element, null);
    }

    public void writeClose(Element element) throws SAXException {
        this.endElement(element);
    }

    public void write(String text) throws SAXException {
        if (text != null) {
            char[] chars = text.toCharArray();
            this.contentHandler.characters(chars, 0, chars.length);
        }
    }

    public void write(CDATA cdata) throws SAXException {
        String text = cdata.getText();
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startCDATA();
            this.write(text);
            this.lexicalHandler.endCDATA();
        } else {
            this.write(text);
        }
    }

    public void write(Comment comment) throws SAXException {
        if (this.lexicalHandler != null) {
            String text = comment.getText();
            char[] chars = text.toCharArray();
            this.lexicalHandler.comment(chars, 0, chars.length);
        }
    }

    public void write(Entity entity) throws SAXException {
        String text = entity.getText();
        if (this.lexicalHandler != null) {
            String name = entity.getName();
            this.lexicalHandler.startEntity(name);
            this.write(text);
            this.lexicalHandler.endEntity(name);
        } else {
            this.write(text);
        }
    }

    public void write(ProcessingInstruction pi) throws SAXException {
        String target = pi.getTarget();
        String text = pi.getText();
        this.contentHandler.processingInstruction(target, text);
    }

    public boolean isDeclareNamespaceAttributes() {
        return this.declareNamespaceAttributes;
    }

    public void setDeclareNamespaceAttributes(boolean declareNamespaceAttrs) {
        this.declareNamespaceAttributes = declareNamespaceAttrs;
    }

    @Override
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    @Override
    public void setContentHandler(ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }

    @Override
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {
        this.dtdHandler = handler;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    @Override
    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        this.lexicalHandler = lexicalHandler;
    }

    public void setXMLReader(XMLReader xmlReader) {
        this.setContentHandler(xmlReader.getContentHandler());
        this.setDTDHandler(xmlReader.getDTDHandler());
        this.setEntityResolver(xmlReader.getEntityResolver());
        this.setErrorHandler(xmlReader.getErrorHandler());
    }

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        Boolean answer = this.features.get(name);
        return answer != null && answer != false;
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (FEATURE_NAMESPACE_PREFIXES.equals(name)) {
            this.setDeclareNamespaceAttributes(value);
        } else if (FEATURE_NAMESPACE_PREFIXES.equals(name) && !value) {
            String msg = "Namespace feature is always supported in dom4j";
            throw new SAXNotSupportedException(msg);
        }
        this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override
    public void setProperty(String name, Object value) {
        for (String lexicalHandlerName : LEXICAL_HANDLER_NAMES) {
            if (!lexicalHandlerName.equals(name)) continue;
            this.setLexicalHandler((LexicalHandler)value);
            return;
        }
        this.properties.put(name, value);
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        for (String lexicalHandlerName : LEXICAL_HANDLER_NAMES) {
            if (!lexicalHandlerName.equals(name)) continue;
            return this.getLexicalHandler();
        }
        return this.properties.get(name);
    }

    @Override
    public void parse(String systemId) throws SAXNotSupportedException {
        throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
    }

    @Override
    public void parse(InputSource input) throws SAXException {
        if (!(input instanceof DocumentInputSource)) {
            throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
        }
        DocumentInputSource documentInput = (DocumentInputSource)input;
        Document document = documentInput.getDocument();
        this.write(document);
    }

    protected void writeContent(Branch branch, NamespaceStack namespaceStack) throws SAXException {
        Iterator<Node> iter = branch.nodeIterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node instanceof Element) {
                this.write((Element)node, namespaceStack);
                continue;
            }
            if (node instanceof CharacterData) {
                if (node instanceof Text) {
                    Text text = (Text)node;
                    this.write(text.getText());
                    continue;
                }
                if (node instanceof CDATA) {
                    this.write((CDATA)node);
                    continue;
                }
                if (node instanceof Comment) {
                    this.write((Comment)node);
                    continue;
                }
                throw new SAXException("Invalid Node in DOM4J content: " + node + " of type: " + node.getClass());
            }
            if (node instanceof Entity) {
                this.write((Entity)node);
                continue;
            }
            if (node instanceof ProcessingInstruction) {
                this.write((ProcessingInstruction)node);
                continue;
            }
            if (node instanceof Namespace) {
                this.write((Namespace)node);
                continue;
            }
            throw new SAXException("Invalid Node in DOM4J content: " + node);
        }
    }

    protected void documentLocator(Document document) throws SAXException {
        LocatorImpl locator = new LocatorImpl();
        String publicID = null;
        String systemID = null;
        DocumentType docType = document.getDocType();
        if (docType != null) {
            publicID = docType.getPublicID();
            systemID = docType.getSystemID();
        }
        if (publicID != null) {
            locator.setPublicId(publicID);
        }
        if (systemID != null) {
            locator.setSystemId(systemID);
        }
        locator.setLineNumber(-1);
        locator.setColumnNumber(-1);
        this.contentHandler.setDocumentLocator(locator);
    }

    protected void entityResolver(Document document) throws SAXException {
        DocumentType docType;
        if (this.entityResolver != null && (docType = document.getDocType()) != null) {
            String publicID = docType.getPublicID();
            String systemID = docType.getSystemID();
            if (publicID != null || systemID != null) {
                try {
                    this.entityResolver.resolveEntity(publicID, systemID);
                } catch (IOException e) {
                    throw new SAXException("Could not resolve publicID: " + publicID + " systemID: " + systemID, e);
                }
            }
        }
    }

    protected void dtdHandler(Document document) throws SAXException {
    }

    protected void startDocument() throws SAXException {
        this.contentHandler.startDocument();
    }

    protected void endDocument() throws SAXException {
        this.contentHandler.endDocument();
    }

    protected void write(Element element, NamespaceStack namespaceStack) throws SAXException {
        int stackSize = namespaceStack.size();
        AttributesImpl namespaceAttributes = this.startPrefixMapping(element, namespaceStack);
        this.startElement(element, namespaceAttributes);
        this.writeContent(element, namespaceStack);
        this.endElement(element);
        this.endPrefixMapping(namespaceStack, stackSize);
    }

    protected AttributesImpl startPrefixMapping(Element element, NamespaceStack namespaceStack) throws SAXException {
        AttributesImpl namespaceAttributes = null;
        Namespace elementNamespace = element.getNamespace();
        if (elementNamespace != null && !this.isIgnoreableNamespace(elementNamespace, namespaceStack)) {
            namespaceStack.push(elementNamespace);
            this.contentHandler.startPrefixMapping(elementNamespace.getPrefix(), elementNamespace.getURI());
            namespaceAttributes = this.addNamespaceAttribute(namespaceAttributes, elementNamespace);
        }
        List<Namespace> declaredNamespaces = element.declaredNamespaces();
        for (Namespace namespace : declaredNamespaces) {
            if (this.isIgnoreableNamespace(namespace, namespaceStack)) continue;
            namespaceStack.push(namespace);
            this.contentHandler.startPrefixMapping(namespace.getPrefix(), namespace.getURI());
            namespaceAttributes = this.addNamespaceAttribute(namespaceAttributes, namespace);
        }
        return namespaceAttributes;
    }

    protected void endPrefixMapping(NamespaceStack stack, int stackSize) throws SAXException {
        while (stack.size() > stackSize) {
            Namespace namespace = stack.pop();
            if (namespace == null) continue;
            this.contentHandler.endPrefixMapping(namespace.getPrefix());
        }
    }

    protected void startElement(Element element, AttributesImpl namespaceAttributes) throws SAXException {
        this.contentHandler.startElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName(), this.createAttributes(element, namespaceAttributes));
    }

    protected void endElement(Element element) throws SAXException {
        this.contentHandler.endElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName());
    }

    protected Attributes createAttributes(Element element, Attributes namespaceAttributes) throws SAXException {
        this.attributes.clear();
        if (namespaceAttributes != null) {
            this.attributes.setAttributes(namespaceAttributes);
        }
        Iterator<Attribute> iter = element.attributeIterator();
        while (iter.hasNext()) {
            Attribute attribute = iter.next();
            this.attributes.addAttribute(attribute.getNamespaceURI(), attribute.getName(), attribute.getQualifiedName(), "CDATA", attribute.getValue());
        }
        return this.attributes;
    }

    protected AttributesImpl addNamespaceAttribute(AttributesImpl attrs, Namespace namespace) {
        if (this.declareNamespaceAttributes) {
            if (attrs == null) {
                attrs = new AttributesImpl();
            }
            String prefix = namespace.getPrefix();
            String qualifiedName = "xmlns";
            if (prefix != null && prefix.length() > 0) {
                qualifiedName = "xmlns:" + prefix;
            }
            String uri = "";
            String localName = prefix;
            String type = "CDATA";
            String value = namespace.getURI();
            attrs.addAttribute(uri, localName, qualifiedName, type, value);
        }
        return attrs;
    }

    protected boolean isIgnoreableNamespace(Namespace namespace, NamespaceStack namespaceStack) {
        if (namespace.equals(Namespace.NO_NAMESPACE) || namespace.equals(Namespace.XML_NAMESPACE)) {
            return true;
        }
        String uri = namespace.getURI();
        if (uri == null || uri.length() <= 0) {
            return true;
        }
        return namespaceStack.contains(namespace);
    }

    protected void checkForNullHandlers() {
    }
}

