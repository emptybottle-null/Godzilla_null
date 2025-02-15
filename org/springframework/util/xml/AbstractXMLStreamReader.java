/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.springframework.lang.Nullable;

abstract class AbstractXMLStreamReader
implements XMLStreamReader {
    AbstractXMLStreamReader() {
    }

    @Override
    public String getElementText() throws XMLStreamException {
        if (this.getEventType() != 1) {
            throw new XMLStreamException("Parser must be on START_ELEMENT to read next text", this.getLocation());
        }
        int eventType = this.next();
        StringBuilder builder = new StringBuilder();
        while (eventType != 2) {
            if (eventType == 4 || eventType == 12 || eventType == 6 || eventType == 9) {
                builder.append(this.getText());
            } else if (eventType != 3 && eventType != 5) {
                if (eventType == 8) {
                    throw new XMLStreamException("Unexpected end of document when reading element text content", this.getLocation());
                }
                if (eventType == 1) {
                    throw new XMLStreamException("Element text content may not contain START_ELEMENT", this.getLocation());
                }
                throw new XMLStreamException("Unexpected event type " + eventType, this.getLocation());
            }
            eventType = this.next();
        }
        return builder.toString();
    }

    @Override
    public String getAttributeLocalName(int index) {
        return this.getAttributeName(index).getLocalPart();
    }

    @Override
    public String getAttributeNamespace(int index) {
        return this.getAttributeName(index).getNamespaceURI();
    }

    @Override
    public String getAttributePrefix(int index) {
        return this.getAttributeName(index).getPrefix();
    }

    @Override
    public String getNamespaceURI() {
        int eventType = this.getEventType();
        if (eventType == 1 || eventType == 2) {
            return this.getName().getNamespaceURI();
        }
        throw new IllegalStateException("Parser must be on START_ELEMENT or END_ELEMENT state");
    }

    @Override
    public String getNamespaceURI(String prefix) {
        return this.getNamespaceContext().getNamespaceURI(prefix);
    }

    @Override
    public boolean hasText() {
        int eventType = this.getEventType();
        return eventType == 6 || eventType == 4 || eventType == 5 || eventType == 12 || eventType == 9;
    }

    @Override
    public String getPrefix() {
        int eventType = this.getEventType();
        if (eventType == 1 || eventType == 2) {
            return this.getName().getPrefix();
        }
        throw new IllegalStateException("Parser must be on START_ELEMENT or END_ELEMENT state");
    }

    @Override
    public boolean hasName() {
        int eventType = this.getEventType();
        return eventType == 1 || eventType == 2;
    }

    @Override
    public boolean isWhiteSpace() {
        return this.getEventType() == 6;
    }

    @Override
    public boolean isStartElement() {
        return this.getEventType() == 1;
    }

    @Override
    public boolean isEndElement() {
        return this.getEventType() == 2;
    }

    @Override
    public boolean isCharacters() {
        return this.getEventType() == 4;
    }

    @Override
    public int nextTag() throws XMLStreamException {
        int eventType = this.next();
        while (eventType == 4 && this.isWhiteSpace() || eventType == 12 && this.isWhiteSpace() || eventType == 6 || eventType == 3 || eventType == 5) {
            eventType = this.next();
        }
        if (eventType != 1 && eventType != 2) {
            throw new XMLStreamException("expected start or end tag", this.getLocation());
        }
        return eventType;
    }

    @Override
    public void require(int expectedType, String namespaceURI, String localName) throws XMLStreamException {
        int eventType = this.getEventType();
        if (eventType != expectedType) {
            throw new XMLStreamException("Expected [" + expectedType + "] but read [" + eventType + "]");
        }
    }

    @Override
    @Nullable
    public String getAttributeValue(@Nullable String namespaceURI, String localName) {
        for (int i = 0; i < this.getAttributeCount(); ++i) {
            QName name = this.getAttributeName(i);
            if (!name.getLocalPart().equals(localName) || namespaceURI != null && !name.getNamespaceURI().equals(namespaceURI)) continue;
            return this.getAttributeValue(i);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.getEventType() != 8;
    }

    @Override
    public String getLocalName() {
        return this.getName().getLocalPart();
    }

    @Override
    public char[] getTextCharacters() {
        return this.getText().toCharArray();
    }

    @Override
    public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) {
        char[] source = this.getTextCharacters();
        length = Math.min(length, source.length);
        System.arraycopy(source, sourceStart, target, targetStart, length);
        return length;
    }

    @Override
    public int getTextLength() {
        return this.getText().length();
    }
}

