/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.xml.transform.sax.SAXResult;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class XMLResult
extends SAXResult {
    private XMLWriter xmlWriter;

    public XMLResult() {
        this(new XMLWriter());
    }

    public XMLResult(Writer writer) {
        this(new XMLWriter(writer));
    }

    public XMLResult(Writer writer, OutputFormat format) {
        this(new XMLWriter(writer, format));
    }

    public XMLResult(OutputStream out) throws UnsupportedEncodingException {
        this(new XMLWriter(out));
    }

    public XMLResult(OutputStream out, OutputFormat format) throws UnsupportedEncodingException {
        this(new XMLWriter(out, format));
    }

    public XMLResult(XMLWriter xmlWriter) {
        super(xmlWriter);
        this.xmlWriter = xmlWriter;
        this.setLexicalHandler(xmlWriter);
    }

    public XMLWriter getXMLWriter() {
        return this.xmlWriter;
    }

    public void setXMLWriter(XMLWriter writer) {
        this.xmlWriter = writer;
        this.setHandler(this.xmlWriter);
        this.setLexicalHandler(this.xmlWriter);
    }

    @Override
    public ContentHandler getHandler() {
        return this.xmlWriter;
    }

    @Override
    public LexicalHandler getLexicalHandler() {
        return this.xmlWriter;
    }
}

