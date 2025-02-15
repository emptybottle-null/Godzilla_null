/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.sax.SAXResult;
import org.springframework.lang.Nullable;
import org.springframework.util.xml.StaxEventHandler;
import org.springframework.util.xml.StaxStreamHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

class StaxResult
extends SAXResult {
    @Nullable
    private XMLEventWriter eventWriter;
    @Nullable
    private XMLStreamWriter streamWriter;

    public StaxResult(XMLEventWriter eventWriter) {
        StaxEventHandler handler = new StaxEventHandler(eventWriter);
        super.setHandler(handler);
        super.setLexicalHandler(handler);
        this.eventWriter = eventWriter;
    }

    public StaxResult(XMLStreamWriter streamWriter) {
        StaxStreamHandler handler = new StaxStreamHandler(streamWriter);
        super.setHandler(handler);
        super.setLexicalHandler(handler);
        this.streamWriter = streamWriter;
    }

    @Nullable
    public XMLEventWriter getXMLEventWriter() {
        return this.eventWriter;
    }

    @Nullable
    public XMLStreamWriter getXMLStreamWriter() {
        return this.streamWriter;
    }

    @Override
    public void setHandler(ContentHandler handler) {
        throw new UnsupportedOperationException("setHandler is not supported");
    }

    @Override
    public void setLexicalHandler(LexicalHandler handler) {
        throw new UnsupportedOperationException("setLexicalHandler is not supported");
    }
}

