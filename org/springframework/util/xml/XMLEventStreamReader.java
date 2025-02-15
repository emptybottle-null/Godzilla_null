/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.XMLEvent;
import org.springframework.lang.Nullable;
import org.springframework.util.xml.AbstractXMLStreamReader;

class XMLEventStreamReader
extends AbstractXMLStreamReader {
    private XMLEvent event;
    private final XMLEventReader eventReader;

    public XMLEventStreamReader(XMLEventReader eventReader) throws XMLStreamException {
        this.eventReader = eventReader;
        this.event = eventReader.nextEvent();
    }

    @Override
    public QName getName() {
        if (this.event.isStartElement()) {
            return this.event.asStartElement().getName();
        }
        if (this.event.isEndElement()) {
            return this.event.asEndElement().getName();
        }
        throw new IllegalStateException();
    }

    @Override
    public Location getLocation() {
        return this.event.getLocation();
    }

    @Override
    public int getEventType() {
        return this.event.getEventType();
    }

    @Override
    @Nullable
    public String getVersion() {
        if (this.event.isStartDocument()) {
            return ((StartDocument)this.event).getVersion();
        }
        return null;
    }

    @Override
    public Object getProperty(String name) throws IllegalArgumentException {
        return this.eventReader.getProperty(name);
    }

    @Override
    public boolean isStandalone() {
        if (this.event.isStartDocument()) {
            return ((StartDocument)this.event).isStandalone();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean standaloneSet() {
        if (this.event.isStartDocument()) {
            return ((StartDocument)this.event).standaloneSet();
        }
        throw new IllegalStateException();
    }

    @Override
    @Nullable
    public String getEncoding() {
        return null;
    }

    @Override
    @Nullable
    public String getCharacterEncodingScheme() {
        return null;
    }

    @Override
    public String getPITarget() {
        if (this.event.isProcessingInstruction()) {
            return ((ProcessingInstruction)this.event).getTarget();
        }
        throw new IllegalStateException();
    }

    @Override
    public String getPIData() {
        if (this.event.isProcessingInstruction()) {
            return ((ProcessingInstruction)this.event).getData();
        }
        throw new IllegalStateException();
    }

    @Override
    public int getTextStart() {
        return 0;
    }

    @Override
    public String getText() {
        if (this.event.isCharacters()) {
            return this.event.asCharacters().getData();
        }
        if (this.event.getEventType() == 5) {
            return ((Comment)this.event).getText();
        }
        throw new IllegalStateException();
    }

    @Override
    public int getAttributeCount() {
        if (!this.event.isStartElement()) {
            throw new IllegalStateException();
        }
        Iterator attributes = this.event.asStartElement().getAttributes();
        return XMLEventStreamReader.countIterator(attributes);
    }

    @Override
    public boolean isAttributeSpecified(int index) {
        return this.getAttribute(index).isSpecified();
    }

    @Override
    public QName getAttributeName(int index) {
        return this.getAttribute(index).getName();
    }

    @Override
    public String getAttributeType(int index) {
        return this.getAttribute(index).getDTDType();
    }

    @Override
    public String getAttributeValue(int index) {
        return this.getAttribute(index).getValue();
    }

    private Attribute getAttribute(int index) {
        if (!this.event.isStartElement()) {
            throw new IllegalStateException();
        }
        int count = 0;
        Iterator attributes = this.event.asStartElement().getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = (Attribute)attributes.next();
            if (count == index) {
                return attribute;
            }
            ++count;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        if (this.event.isStartElement()) {
            return this.event.asStartElement().getNamespaceContext();
        }
        throw new IllegalStateException();
    }

    @Override
    public int getNamespaceCount() {
        Iterator namespaces;
        if (this.event.isStartElement()) {
            namespaces = this.event.asStartElement().getNamespaces();
        } else if (this.event.isEndElement()) {
            namespaces = this.event.asEndElement().getNamespaces();
        } else {
            throw new IllegalStateException();
        }
        return XMLEventStreamReader.countIterator(namespaces);
    }

    @Override
    public String getNamespacePrefix(int index) {
        return this.getNamespace(index).getPrefix();
    }

    @Override
    public String getNamespaceURI(int index) {
        return this.getNamespace(index).getNamespaceURI();
    }

    private Namespace getNamespace(int index) {
        Iterator namespaces;
        if (this.event.isStartElement()) {
            namespaces = this.event.asStartElement().getNamespaces();
        } else if (this.event.isEndElement()) {
            namespaces = this.event.asEndElement().getNamespaces();
        } else {
            throw new IllegalStateException();
        }
        int count = 0;
        while (namespaces.hasNext()) {
            Namespace namespace = (Namespace)namespaces.next();
            if (count == index) {
                return namespace;
            }
            ++count;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int next() throws XMLStreamException {
        this.event = this.eventReader.nextEvent();
        return this.event.getEventType();
    }

    @Override
    public void close() throws XMLStreamException {
        this.eventReader.close();
    }

    private static int countIterator(Iterator iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            ++count;
        }
        return count;
    }
}

