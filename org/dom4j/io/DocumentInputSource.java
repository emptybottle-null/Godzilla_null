/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

class DocumentInputSource
extends InputSource {
    private Document document;

    public DocumentInputSource() {
    }

    public DocumentInputSource(Document document) {
        this.document = document;
        this.setSystemId(document.getName());
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
        this.setSystemId(document.getName());
    }

    @Override
    public void setCharacterStream(Reader characterStream) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader getCharacterStream() {
        try {
            StringWriter out = new StringWriter();
            XMLWriter writer = new XMLWriter(out);
            writer.write(this.document);
            writer.flush();
            return new StringReader(out.toString());
        } catch (IOException e) {
            return new Reader(){

                @Override
                public int read(char[] ch, int offset, int length) throws IOException {
                    throw e;
                }

                @Override
                public void close() throws IOException {
                }
            };
        }
    }
}

