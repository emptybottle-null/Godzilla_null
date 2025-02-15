/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.NoSuchElementException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.springframework.util.ClassUtils;

abstract class AbstractXMLEventReader
implements XMLEventReader {
    private boolean closed;

    AbstractXMLEventReader() {
    }

    public Object next() {
        try {
            return this.nextEvent();
        } catch (XMLStreamException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported on " + ClassUtils.getShortName(this.getClass()));
    }

    @Override
    public Object getProperty(String name) throws IllegalArgumentException {
        throw new IllegalArgumentException("Property not supported: [" + name + "]");
    }

    @Override
    public void close() {
        this.closed = true;
    }

    protected void checkIfClosed() throws XMLStreamException {
        if (this.closed) {
            throw new XMLStreamException("XMLEventReader has been closed");
        }
    }
}

