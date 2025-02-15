/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import javax.xml.transform.sax.SAXResult;
import org.dom4j.Document;
import org.dom4j.io.SAXContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class DocumentResult
extends SAXResult {
    private SAXContentHandler contentHandler;

    public DocumentResult() {
        this(new SAXContentHandler());
    }

    public DocumentResult(SAXContentHandler contentHandler) {
        this.contentHandler = contentHandler;
        super.setHandler(this.contentHandler);
        super.setLexicalHandler(this.contentHandler);
    }

    public Document getDocument() {
        return this.contentHandler.getDocument();
    }

    @Override
    public void setHandler(ContentHandler handler) {
        if (handler instanceof SAXContentHandler) {
            this.contentHandler = (SAXContentHandler)handler;
            super.setHandler(this.contentHandler);
        }
    }

    @Override
    public void setLexicalHandler(LexicalHandler handler) {
        if (handler instanceof SAXContentHandler) {
            this.contentHandler = (SAXContentHandler)handler;
            super.setLexicalHandler(this.contentHandler);
        }
    }
}

