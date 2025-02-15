/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.NotationDeclaration;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.AbstractStaxXMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.AttributesImpl;

class StaxEventXMLReader
extends AbstractStaxXMLReader {
    private static final String DEFAULT_XML_VERSION = "1.0";
    private final XMLEventReader reader;
    private String xmlVersion = "1.0";
    @Nullable
    private String encoding;

    StaxEventXMLReader(XMLEventReader reader) {
        try {
            XMLEvent event = reader.peek();
            if (event != null && !event.isStartDocument() && !event.isStartElement()) {
                throw new IllegalStateException("XMLEventReader not at start of document or element");
            }
        } catch (XMLStreamException ex) {
            throw new IllegalStateException("Could not read first element: " + ex.getMessage());
        }
        this.reader = reader;
    }

    @Override
    protected void parseInternal() throws SAXException, XMLStreamException {
        boolean documentStarted = false;
        boolean documentEnded = false;
        int elementDepth = 0;
        while (this.reader.hasNext() && elementDepth >= 0) {
            XMLEvent event = this.reader.nextEvent();
            if (!(event.isStartDocument() || event.isEndDocument() || documentStarted)) {
                this.handleStartDocument(event);
                documentStarted = true;
            }
            switch (event.getEventType()) {
                case 7: {
                    this.handleStartDocument(event);
                    documentStarted = true;
                    break;
                }
                case 1: {
                    ++elementDepth;
                    this.handleStartElement(event.asStartElement());
                    break;
                }
                case 2: {
                    if (--elementDepth < 0) break;
                    this.handleEndElement(event.asEndElement());
                    break;
                }
                case 3: {
                    this.handleProcessingInstruction((ProcessingInstruction)event);
                    break;
                }
                case 4: 
                case 6: 
                case 12: {
                    this.handleCharacters(event.asCharacters());
                    break;
                }
                case 8: {
                    this.handleEndDocument();
                    documentEnded = true;
                    break;
                }
                case 14: {
                    this.handleNotationDeclaration((NotationDeclaration)event);
                    break;
                }
                case 15: {
                    this.handleEntityDeclaration((EntityDeclaration)event);
                    break;
                }
                case 5: {
                    this.handleComment((Comment)event);
                    break;
                }
                case 11: {
                    this.handleDtd((DTD)event);
                    break;
                }
                case 9: {
                    this.handleEntityReference((EntityReference)event);
                }
            }
        }
        if (documentStarted && !documentEnded) {
            this.handleEndDocument();
        }
    }

    private void handleStartDocument(XMLEvent event) throws SAXException {
        ContentHandler contentHandler;
        if (event.isStartDocument()) {
            StartDocument startDocument = (StartDocument)event;
            String xmlVersion = startDocument.getVersion();
            if (StringUtils.hasLength(xmlVersion)) {
                this.xmlVersion = xmlVersion;
            }
            if (startDocument.encodingSet()) {
                this.encoding = startDocument.getCharacterEncodingScheme();
            }
        }
        if ((contentHandler = this.getContentHandler()) != null) {
            final Location location = event.getLocation();
            contentHandler.setDocumentLocator(new Locator2(){

                @Override
                public int getColumnNumber() {
                    return location != null ? location.getColumnNumber() : -1;
                }

                @Override
                public int getLineNumber() {
                    return location != null ? location.getLineNumber() : -1;
                }

                @Override
                @Nullable
                public String getPublicId() {
                    return location != null ? location.getPublicId() : null;
                }

                @Override
                @Nullable
                public String getSystemId() {
                    return location != null ? location.getSystemId() : null;
                }

                @Override
                public String getXMLVersion() {
                    return StaxEventXMLReader.this.xmlVersion;
                }

                @Override
                @Nullable
                public String getEncoding() {
                    return StaxEventXMLReader.this.encoding;
                }
            });
            contentHandler.startDocument();
        }
    }

    private void handleStartElement(StartElement startElement) throws SAXException {
        if (this.getContentHandler() != null) {
            QName qName = startElement.getName();
            if (this.hasNamespacesFeature()) {
                Iterator i = startElement.getNamespaces();
                while (i.hasNext()) {
                    Namespace namespace = (Namespace)i.next();
                    this.startPrefixMapping(namespace.getPrefix(), namespace.getNamespaceURI());
                }
                i = startElement.getAttributes();
                while (i.hasNext()) {
                    Attribute attribute = (Attribute)i.next();
                    QName attributeName = attribute.getName();
                    this.startPrefixMapping(attributeName.getPrefix(), attributeName.getNamespaceURI());
                }
                this.getContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalPart(), this.toQualifiedName(qName), this.getAttributes(startElement));
            } else {
                this.getContentHandler().startElement("", "", this.toQualifiedName(qName), this.getAttributes(startElement));
            }
        }
    }

    private void handleCharacters(Characters characters) throws SAXException {
        char[] data = characters.getData().toCharArray();
        if (this.getContentHandler() != null && characters.isIgnorableWhiteSpace()) {
            this.getContentHandler().ignorableWhitespace(data, 0, data.length);
            return;
        }
        if (characters.isCData() && this.getLexicalHandler() != null) {
            this.getLexicalHandler().startCDATA();
        }
        if (this.getContentHandler() != null) {
            this.getContentHandler().characters(data, 0, data.length);
        }
        if (characters.isCData() && this.getLexicalHandler() != null) {
            this.getLexicalHandler().endCDATA();
        }
    }

    private void handleEndElement(EndElement endElement) throws SAXException {
        if (this.getContentHandler() != null) {
            QName qName = endElement.getName();
            if (this.hasNamespacesFeature()) {
                this.getContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalPart(), this.toQualifiedName(qName));
                Iterator i = endElement.getNamespaces();
                while (i.hasNext()) {
                    Namespace namespace = (Namespace)i.next();
                    this.endPrefixMapping(namespace.getPrefix());
                }
            } else {
                this.getContentHandler().endElement("", "", this.toQualifiedName(qName));
            }
        }
    }

    private void handleEndDocument() throws SAXException {
        if (this.getContentHandler() != null) {
            this.getContentHandler().endDocument();
        }
    }

    private void handleNotationDeclaration(NotationDeclaration declaration) throws SAXException {
        if (this.getDTDHandler() != null) {
            this.getDTDHandler().notationDecl(declaration.getName(), declaration.getPublicId(), declaration.getSystemId());
        }
    }

    private void handleEntityDeclaration(EntityDeclaration entityDeclaration) throws SAXException {
        if (this.getDTDHandler() != null) {
            this.getDTDHandler().unparsedEntityDecl(entityDeclaration.getName(), entityDeclaration.getPublicId(), entityDeclaration.getSystemId(), entityDeclaration.getNotationName());
        }
    }

    private void handleProcessingInstruction(ProcessingInstruction pi) throws SAXException {
        if (this.getContentHandler() != null) {
            this.getContentHandler().processingInstruction(pi.getTarget(), pi.getData());
        }
    }

    private void handleComment(Comment comment) throws SAXException {
        if (this.getLexicalHandler() != null) {
            char[] ch = comment.getText().toCharArray();
            this.getLexicalHandler().comment(ch, 0, ch.length);
        }
    }

    private void handleDtd(DTD dtd) throws SAXException {
        if (this.getLexicalHandler() != null) {
            Location location = dtd.getLocation();
            this.getLexicalHandler().startDTD(null, location.getPublicId(), location.getSystemId());
        }
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().endDTD();
        }
    }

    private void handleEntityReference(EntityReference reference) throws SAXException {
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().startEntity(reference.getName());
        }
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().endEntity(reference.getName());
        }
    }

    private Attributes getAttributes(StartElement event) {
        AttributesImpl attributes = new AttributesImpl();
        Iterator i = event.getAttributes();
        while (i.hasNext()) {
            String type;
            Attribute attribute = (Attribute)i.next();
            QName qName = attribute.getName();
            String namespace = qName.getNamespaceURI();
            if (namespace == null || !this.hasNamespacesFeature()) {
                namespace = "";
            }
            if ((type = attribute.getDTDType()) == null) {
                type = "CDATA";
            }
            attributes.addAttribute(namespace, qName.getLocalPart(), this.toQualifiedName(qName), type, attribute.getValue());
        }
        if (this.hasNamespacePrefixesFeature()) {
            i = event.getNamespaces();
            while (i.hasNext()) {
                Namespace namespace = (Namespace)i.next();
                String prefix = namespace.getPrefix();
                String namespaceUri = namespace.getNamespaceURI();
                String qName = StringUtils.hasLength(prefix) ? "xmlns:" + prefix : "xmlns";
                attributes.addAttribute("", "", qName, "CDATA", namespaceUri);
            }
        }
        return attributes;
    }
}

