/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.ElementModifier;
import org.dom4j.io.PruningDispatchHandler;
import org.dom4j.io.SAXHelper;
import org.dom4j.io.SAXModifyElementHandler;
import org.dom4j.io.SAXModifyException;
import org.dom4j.io.SAXModifyReader;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXModifier {
    private XMLWriter xmlWriter;
    private XMLReader xmlReader;
    private boolean pruneElements;
    private SAXModifyReader modifyReader;
    private HashMap<String, ElementModifier> modifiers = new HashMap();

    public SAXModifier() {
    }

    public SAXModifier(boolean pruneElements) {
        this.pruneElements = pruneElements;
    }

    public SAXModifier(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    public SAXModifier(XMLReader xmlReader, boolean pruneElements) {
        this.xmlReader = xmlReader;
    }

    public Document modify(File source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputSource source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream source, String systemId) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader source, String systemId) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(URL source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(String source) throws DocumentException {
        try {
            return this.installModifyReader().read(source);
        } catch (SAXModifyException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public void addModifier(String path, ElementModifier modifier) {
        this.modifiers.put(path, modifier);
    }

    public void resetModifiers() {
        this.modifiers.clear();
        this.getSAXModifyReader().resetHandlers();
    }

    public void removeModifier(String path) {
        this.modifiers.remove(path);
        this.getSAXModifyReader().removeHandler(path);
    }

    public DocumentFactory getDocumentFactory() {
        return this.getSAXModifyReader().getDocumentFactory();
    }

    public void setDocumentFactory(DocumentFactory factory) {
        this.getSAXModifyReader().setDocumentFactory(factory);
    }

    public XMLWriter getXMLWriter() {
        return this.xmlWriter;
    }

    public void setXMLWriter(XMLWriter writer) {
        this.xmlWriter = writer;
    }

    public boolean isPruneElements() {
        return this.pruneElements;
    }

    private SAXReader installModifyReader() throws DocumentException {
        try {
            SAXModifyReader reader = this.getSAXModifyReader();
            if (this.isPruneElements()) {
                this.modifyReader.setDispatchHandler(new PruningDispatchHandler());
            }
            reader.resetHandlers();
            for (Map.Entry<String, ElementModifier> entry : this.modifiers.entrySet()) {
                SAXModifyElementHandler handler = new SAXModifyElementHandler(entry.getValue());
                reader.addHandler(entry.getKey(), handler);
            }
            reader.setXMLWriter(this.getXMLWriter());
            reader.setXMLReader(this.getXMLReader());
            return reader;
        } catch (SAXException ex) {
            throw new DocumentException(ex.getMessage(), ex);
        }
    }

    private XMLReader getXMLReader() throws SAXException {
        if (this.xmlReader == null) {
            this.xmlReader = SAXHelper.createXMLReader(false);
        }
        return this.xmlReader;
    }

    private SAXModifyReader getSAXModifyReader() {
        if (this.modifyReader == null) {
            this.modifyReader = new SAXModifyReader();
        }
        return this.modifyReader;
    }
}

