/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXModifyContentHandler;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class SAXModifyReader
extends SAXReader {
    private XMLWriter xmlWriter;
    private boolean pruneElements;

    public SAXModifyReader() {
    }

    public SAXModifyReader(boolean validating) {
        super(validating);
    }

    public SAXModifyReader(DocumentFactory factory) {
        super(factory);
    }

    public SAXModifyReader(DocumentFactory factory, boolean validating) {
        super(factory, validating);
    }

    public SAXModifyReader(XMLReader xmlReader) {
        super(xmlReader);
    }

    public SAXModifyReader(XMLReader xmlReader, boolean validating) {
        super(xmlReader, validating);
    }

    public SAXModifyReader(String xmlReaderClassName) throws SAXException {
        super(xmlReaderClassName);
    }

    public SAXModifyReader(String xmlReaderClassName, boolean validating) throws SAXException {
        super(xmlReaderClassName, validating);
    }

    public void setXMLWriter(XMLWriter writer) {
        this.xmlWriter = writer;
    }

    public boolean isPruneElements() {
        return this.pruneElements;
    }

    public void setPruneElements(boolean pruneElements) {
        this.pruneElements = pruneElements;
    }

    @Override
    protected SAXContentHandler createContentHandler(XMLReader reader) {
        SAXModifyContentHandler handler = new SAXModifyContentHandler(this.getDocumentFactory(), this.getDispatchHandler());
        handler.setXMLWriter(this.xmlWriter);
        return handler;
    }

    protected XMLWriter getXMLWriter() {
        return this.xmlWriter;
    }
}

