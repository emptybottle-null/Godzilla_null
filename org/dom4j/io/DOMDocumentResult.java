/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import javax.xml.transform.sax.SAXResult;
import org.dom4j.io.DOMSAXContentHandler;
import org.w3c.dom.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class DOMDocumentResult
extends SAXResult {
    private DOMSAXContentHandler contentHandler;

    public DOMDocumentResult() {
        this(new DOMSAXContentHandler());
    }

    public DOMDocumentResult(DOMSAXContentHandler contentHandler) {
        this.contentHandler = contentHandler;
        super.setHandler(this.contentHandler);
        super.setLexicalHandler(this.contentHandler);
    }

    public Document getDocument() {
        return this.contentHandler.getDocument();
    }

    @Override
    public void setHandler(ContentHandler handler) {
        if (handler instanceof DOMSAXContentHandler) {
            this.contentHandler = (DOMSAXContentHandler)handler;
            super.setHandler(this.contentHandler);
        }
    }

    @Override
    public void setLexicalHandler(LexicalHandler handler) {
        if (handler instanceof DOMSAXContentHandler) {
            this.contentHandler = (DOMSAXContentHandler)handler;
            super.setLexicalHandler(this.contentHandler);
        }
    }
}

