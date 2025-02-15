/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.AbstractStaxXMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.AttributesImpl;

class StaxStreamXMLReader
extends AbstractStaxXMLReader {
    private static final String DEFAULT_XML_VERSION = "1.0";
    private final XMLStreamReader reader;
    private String xmlVersion = "1.0";
    @Nullable
    private String encoding;

    StaxStreamXMLReader(XMLStreamReader reader) {
        int event = reader.getEventType();
        if (event != 7 && event != 1) {
            throw new IllegalStateException("XMLEventReader not at start of document or element");
        }
        this.reader = reader;
    }

    @Override
    protected void parseInternal() throws SAXException, XMLStreamException {
        boolean documentStarted = false;
        boolean documentEnded = false;
        int elementDepth = 0;
        int eventType = this.reader.getEventType();
        while (true) {
            if (eventType != 7 && eventType != 8 && !documentStarted) {
                this.handleStartDocument();
                documentStarted = true;
            }
            switch (eventType) {
                case 1: {
                    ++elementDepth;
                    this.handleStartElement();
                    break;
                }
                case 2: {
                    if (--elementDepth < 0) break;
                    this.handleEndElement();
                    break;
                }
                case 3: {
                    this.handleProcessingInstruction();
                    break;
                }
                case 4: 
                case 6: 
                case 12: {
                    this.handleCharacters();
                    break;
                }
                case 7: {
                    this.handleStartDocument();
                    documentStarted = true;
                    break;
                }
                case 8: {
                    this.handleEndDocument();
                    documentEnded = true;
                    break;
                }
                case 5: {
                    this.handleComment();
                    break;
                }
                case 11: {
                    this.handleDtd();
                    break;
                }
                case 9: {
                    this.handleEntityReference();
                }
            }
            if (!this.reader.hasNext() || elementDepth < 0) break;
            eventType = this.reader.next();
        }
        if (!documentEnded) {
            this.handleEndDocument();
        }
    }

    private void handleStartDocument() throws SAXException {
        ContentHandler contentHandler;
        if (7 == this.reader.getEventType()) {
            String xmlVersion = this.reader.getVersion();
            if (StringUtils.hasLength(xmlVersion)) {
                this.xmlVersion = xmlVersion;
            }
            this.encoding = this.reader.getCharacterEncodingScheme();
        }
        if ((contentHandler = this.getContentHandler()) != null) {
            final Location location = this.reader.getLocation();
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
                    return StaxStreamXMLReader.this.xmlVersion;
                }

                @Override
                @Nullable
                public String getEncoding() {
                    return StaxStreamXMLReader.this.encoding;
                }
            });
            contentHandler.startDocument();
            if (this.reader.standaloneSet()) {
                this.setStandalone(this.reader.isStandalone());
            }
        }
    }

    private void handleStartElement() throws SAXException {
        if (this.getContentHandler() != null) {
            QName qName = this.reader.getName();
            if (this.hasNamespacesFeature()) {
                int i;
                for (i = 0; i < this.reader.getNamespaceCount(); ++i) {
                    this.startPrefixMapping(this.reader.getNamespacePrefix(i), this.reader.getNamespaceURI(i));
                }
                for (i = 0; i < this.reader.getAttributeCount(); ++i) {
                    String prefix = this.reader.getAttributePrefix(i);
                    String namespace = this.reader.getAttributeNamespace(i);
                    if (!StringUtils.hasLength(namespace)) continue;
                    this.startPrefixMapping(prefix, namespace);
                }
                this.getContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalPart(), this.toQualifiedName(qName), this.getAttributes());
            } else {
                this.getContentHandler().startElement("", "", this.toQualifiedName(qName), this.getAttributes());
            }
        }
    }

    private void handleEndElement() throws SAXException {
        if (this.getContentHandler() != null) {
            QName qName = this.reader.getName();
            if (this.hasNamespacesFeature()) {
                this.getContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalPart(), this.toQualifiedName(qName));
                for (int i = 0; i < this.reader.getNamespaceCount(); ++i) {
                    String prefix = this.reader.getNamespacePrefix(i);
                    if (prefix == null) {
                        prefix = "";
                    }
                    this.endPrefixMapping(prefix);
                }
            } else {
                this.getContentHandler().endElement("", "", this.toQualifiedName(qName));
            }
        }
    }

    private void handleCharacters() throws SAXException {
        if (12 == this.reader.getEventType() && this.getLexicalHandler() != null) {
            this.getLexicalHandler().startCDATA();
        }
        if (this.getContentHandler() != null) {
            this.getContentHandler().characters(this.reader.getTextCharacters(), this.reader.getTextStart(), this.reader.getTextLength());
        }
        if (12 == this.reader.getEventType() && this.getLexicalHandler() != null) {
            this.getLexicalHandler().endCDATA();
        }
    }

    private void handleComment() throws SAXException {
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().comment(this.reader.getTextCharacters(), this.reader.getTextStart(), this.reader.getTextLength());
        }
    }

    private void handleDtd() throws SAXException {
        if (this.getLexicalHandler() != null) {
            Location location = this.reader.getLocation();
            this.getLexicalHandler().startDTD(null, location.getPublicId(), location.getSystemId());
        }
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().endDTD();
        }
    }

    private void handleEntityReference() throws SAXException {
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().startEntity(this.reader.getLocalName());
        }
        if (this.getLexicalHandler() != null) {
            this.getLexicalHandler().endEntity(this.reader.getLocalName());
        }
    }

    private void handleEndDocument() throws SAXException {
        if (this.getContentHandler() != null) {
            this.getContentHandler().endDocument();
        }
    }

    private void handleProcessingInstruction() throws SAXException {
        if (this.getContentHandler() != null) {
            this.getContentHandler().processingInstruction(this.reader.getPITarget(), this.reader.getPIData());
        }
    }

    private Attributes getAttributes() {
        int i;
        AttributesImpl attributes = new AttributesImpl();
        for (i = 0; i < this.reader.getAttributeCount(); ++i) {
            String type;
            String namespace = this.reader.getAttributeNamespace(i);
            if (namespace == null || !this.hasNamespacesFeature()) {
                namespace = "";
            }
            if ((type = this.reader.getAttributeType(i)) == null) {
                type = "CDATA";
            }
            attributes.addAttribute(namespace, this.reader.getAttributeLocalName(i), this.toQualifiedName(this.reader.getAttributeName(i)), type, this.reader.getAttributeValue(i));
        }
        if (this.hasNamespacePrefixesFeature()) {
            for (i = 0; i < this.reader.getNamespaceCount(); ++i) {
                String prefix = this.reader.getNamespacePrefix(i);
                String namespaceUri = this.reader.getNamespaceURI(i);
                String qName = StringUtils.hasLength(prefix) ? "xmlns:" + prefix : "xmlns";
                attributes.addAttribute("", "", qName, "CDATA", namespaceUri);
            }
        }
        return attributes;
    }
}

