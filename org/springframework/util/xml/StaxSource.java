/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.sax.SAXSource;
import org.springframework.lang.Nullable;
import org.springframework.util.xml.StaxEventXMLReader;
import org.springframework.util.xml.StaxStreamXMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

class StaxSource
extends SAXSource {
    @Nullable
    private XMLEventReader eventReader;
    @Nullable
    private XMLStreamReader streamReader;

    StaxSource(XMLEventReader eventReader) {
        super(new StaxEventXMLReader(eventReader), new InputSource());
        this.eventReader = eventReader;
    }

    StaxSource(XMLStreamReader streamReader) {
        super(new StaxStreamXMLReader(streamReader), new InputSource());
        this.streamReader = streamReader;
    }

    @Nullable
    XMLEventReader getXMLEventReader() {
        return this.eventReader;
    }

    @Nullable
    XMLStreamReader getXMLStreamReader() {
        return this.streamReader;
    }

    @Override
    public void setInputSource(InputSource inputSource) {
        throw new UnsupportedOperationException("setInputSource is not supported");
    }

    @Override
    public void setXMLReader(XMLReader reader) {
        throw new UnsupportedOperationException("setXMLReader is not supported");
    }
}

