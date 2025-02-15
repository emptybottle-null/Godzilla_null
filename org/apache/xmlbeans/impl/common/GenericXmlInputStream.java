/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.xml.stream.ReferenceResolver;
import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLName;
import org.apache.xmlbeans.xml.stream.XMLStreamException;

public class GenericXmlInputStream
implements XMLInputStream {
    private boolean _initialized;
    private EventItem _nextEvent;
    private int _elementCount;
    private GenericXmlInputStream _master;

    public GenericXmlInputStream() {
        this._master = this;
        this._elementCount = 1;
    }

    private GenericXmlInputStream(GenericXmlInputStream master) {
        this._master = master;
        this._master.ensureInit();
        this._nextEvent = master._nextEvent;
    }

    protected XMLEvent nextEvent() throws XMLStreamException {
        throw new RuntimeException("nextEvent not overridden");
    }

    private void ensureInit() {
        if (!this._master._initialized) {
            try {
                this._master._nextEvent = this.getNextEvent();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            this._master._initialized = true;
        }
    }

    private EventItem getNextEvent() throws XMLStreamException {
        XMLEvent e = this.nextEvent();
        return e == null ? null : new EventItem(e);
    }

    @Override
    public XMLEvent next() throws XMLStreamException {
        this.ensureInit();
        EventItem currentEvent = this._nextEvent;
        if (this._nextEvent != null) {
            if (this._nextEvent._next == null) {
                this._nextEvent._next = this._master.getNextEvent();
            }
            this._nextEvent = this._nextEvent._next;
        }
        if (currentEvent == null) {
            return null;
        }
        if (currentEvent.getType() == 4) {
            if (--this._elementCount <= 0) {
                this._nextEvent = null;
            }
        } else if (currentEvent.getType() == 2) {
            ++this._elementCount;
        }
        return currentEvent._event;
    }

    @Override
    public boolean hasNext() throws XMLStreamException {
        this.ensureInit();
        return this._nextEvent != null;
    }

    @Override
    public void skip() throws XMLStreamException {
        this.next();
    }

    @Override
    public void skipElement() throws XMLStreamException {
        this.ensureInit();
        while (this._nextEvent != null && this._nextEvent.getType() != 2) {
            this.next();
        }
        int count = 0;
        while (this._nextEvent != null) {
            int type = this.next().getType();
            if (type == 2) {
                ++count;
            } else if (type == 4 && --count == 0) break;
            this.next();
        }
    }

    @Override
    public XMLEvent peek() throws XMLStreamException {
        this.ensureInit();
        return this._nextEvent._event;
    }

    @Override
    public boolean skip(int eventType) throws XMLStreamException {
        this.ensureInit();
        while (this._nextEvent != null) {
            if (this._nextEvent.getType() == eventType) {
                return true;
            }
            this.next();
        }
        return false;
    }

    @Override
    public boolean skip(XMLName name) throws XMLStreamException {
        this.ensureInit();
        while (this._nextEvent != null) {
            if (this._nextEvent.hasName() && this._nextEvent.getName().equals(name)) {
                return true;
            }
            this.next();
        }
        return false;
    }

    @Override
    public boolean skip(XMLName name, int eventType) throws XMLStreamException {
        this.ensureInit();
        while (this._nextEvent != null) {
            if (this._nextEvent.getType() == eventType && this._nextEvent.hasName() && this._nextEvent.getName().equals(name)) {
                return true;
            }
            this.next();
        }
        return false;
    }

    @Override
    public XMLInputStream getSubStream() throws XMLStreamException {
        this.ensureInit();
        GenericXmlInputStream subStream = new GenericXmlInputStream(this);
        subStream.skip(2);
        return subStream;
    }

    @Override
    public void close() throws XMLStreamException {
    }

    @Override
    public ReferenceResolver getReferenceResolver() {
        this.ensureInit();
        throw new RuntimeException("Not impl");
    }

    @Override
    public void setReferenceResolver(ReferenceResolver resolver) {
        this.ensureInit();
        throw new RuntimeException("Not impl");
    }

    private class EventItem {
        final XMLEvent _event;
        EventItem _next;

        EventItem(XMLEvent e) {
            this._event = e;
        }

        int getType() {
            return this._event.getType();
        }

        boolean hasName() {
            return this._event.hasName();
        }

        XMLName getName() {
            return this._event.getName();
        }
    }
}

