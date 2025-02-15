/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import org.springframework.lang.Nullable;
import org.springframework.util.xml.AbstractStaxHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

class StaxEventHandler
extends AbstractStaxHandler {
    private final XMLEventFactory eventFactory;
    private final XMLEventWriter eventWriter;

    public StaxEventHandler(XMLEventWriter eventWriter) {
        this.eventFactory = XMLEventFactory.newInstance();
        this.eventWriter = eventWriter;
    }

    public StaxEventHandler(XMLEventWriter eventWriter, XMLEventFactory factory) {
        this.eventFactory = factory;
        this.eventWriter = eventWriter;
    }

    @Override
    public void setDocumentLocator(@Nullable Locator locator) {
        if (locator != null) {
            this.eventFactory.setLocation(new LocatorLocationAdapter(locator));
        }
    }

    @Override
    protected void startDocumentInternal() throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createStartDocument());
    }

    @Override
    protected void endDocumentInternal() throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createEndDocument());
    }

    @Override
    protected void startElementInternal(QName name, Attributes atts, Map<String, String> namespaceMapping) throws XMLStreamException {
        List<Attribute> attributes = this.getAttributes(atts);
        List<Namespace> namespaces = this.getNamespaces(namespaceMapping);
        this.eventWriter.add(this.eventFactory.createStartElement(name, attributes.iterator(), namespaces.iterator()));
    }

    private List<Namespace> getNamespaces(Map<String, String> namespaceMappings) {
        ArrayList<Namespace> result = new ArrayList<Namespace>(namespaceMappings.size());
        namespaceMappings.forEach((prefix, namespaceUri) -> result.add(this.eventFactory.createNamespace((String)prefix, (String)namespaceUri)));
        return result;
    }

    private List<Attribute> getAttributes(Attributes attributes) {
        int attrLength = attributes.getLength();
        ArrayList<Attribute> result = new ArrayList<Attribute>(attrLength);
        for (int i = 0; i < attrLength; ++i) {
            QName attrName = this.toQName(attributes.getURI(i), attributes.getQName(i));
            if (this.isNamespaceDeclaration(attrName)) continue;
            result.add(this.eventFactory.createAttribute(attrName, attributes.getValue(i)));
        }
        return result;
    }

    @Override
    protected void endElementInternal(QName name, Map<String, String> namespaceMapping) throws XMLStreamException {
        List<Namespace> namespaces = this.getNamespaces(namespaceMapping);
        this.eventWriter.add(this.eventFactory.createEndElement(name, namespaces.iterator()));
    }

    @Override
    protected void charactersInternal(String data) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createCharacters(data));
    }

    @Override
    protected void cDataInternal(String data) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createCData(data));
    }

    @Override
    protected void ignorableWhitespaceInternal(String data) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createIgnorableSpace(data));
    }

    @Override
    protected void processingInstructionInternal(String target, String data) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createProcessingInstruction(target, data));
    }

    @Override
    protected void dtdInternal(String dtd) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createDTD(dtd));
    }

    @Override
    protected void commentInternal(String comment) throws XMLStreamException {
        this.eventWriter.add(this.eventFactory.createComment(comment));
    }

    @Override
    protected void skippedEntityInternal(String name) {
    }

    private static final class LocatorLocationAdapter
    implements Location {
        private final Locator locator;

        public LocatorLocationAdapter(Locator locator) {
            this.locator = locator;
        }

        @Override
        public int getLineNumber() {
            return this.locator.getLineNumber();
        }

        @Override
        public int getColumnNumber() {
            return this.locator.getColumnNumber();
        }

        @Override
        public int getCharacterOffset() {
            return -1;
        }

        @Override
        public String getPublicId() {
            return this.locator.getPublicId();
        }

        @Override
        public String getSystemId() {
            return this.locator.getSystemId();
        }
    }
}

