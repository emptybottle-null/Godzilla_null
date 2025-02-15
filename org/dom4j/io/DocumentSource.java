/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import javax.xml.transform.sax.SAXSource;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DocumentInputSource;
import org.dom4j.io.SAXWriter;
import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class DocumentSource
extends SAXSource {
    public static final String DOM4J_FEATURE = "http://org.dom4j.io.DoucmentSource/feature";
    private XMLReader xmlReader = new SAXWriter();

    public DocumentSource(Node node) {
        this.setDocument(node.getDocument());
    }

    public DocumentSource(Document document) {
        this.setDocument(document);
    }

    public Document getDocument() {
        DocumentInputSource source = (DocumentInputSource)this.getInputSource();
        return source.getDocument();
    }

    public void setDocument(Document document) {
        super.setInputSource(new DocumentInputSource(document));
    }

    @Override
    public XMLReader getXMLReader() {
        return this.xmlReader;
    }

    @Override
    public void setInputSource(InputSource inputSource) throws UnsupportedOperationException {
        if (!(inputSource instanceof DocumentInputSource)) {
            throw new UnsupportedOperationException();
        }
        super.setInputSource((DocumentInputSource)inputSource);
    }

    @Override
    public void setXMLReader(XMLReader reader) throws UnsupportedOperationException {
        if (reader instanceof SAXWriter) {
            this.xmlReader = (SAXWriter)reader;
        } else if (reader instanceof XMLFilter) {
            XMLReader parent;
            XMLFilter filter = (XMLFilter)reader;
            while ((parent = filter.getParent()) instanceof XMLFilter) {
                filter = (XMLFilter)parent;
            }
            filter.setParent(this.xmlReader);
            this.xmlReader = filter;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}

