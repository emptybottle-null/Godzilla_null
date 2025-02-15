/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.io.IOException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.fife.io.DocumentReader;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.AbstractParser;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser
extends AbstractParser {
    private SAXParserFactory spf;
    private DefaultParseResult result;
    private EntityResolver entityResolver;

    public XmlParser() {
        this(null);
    }

    public XmlParser(EntityResolver resolver) {
        this.entityResolver = resolver;
        this.result = new DefaultParseResult(this);
        try {
            this.spf = SAXParserFactory.newInstance();
        } catch (FactoryConfigurationError fce) {
            fce.printStackTrace();
        }
    }

    public boolean isValidating() {
        return this.spf.isValidating();
    }

    @Override
    public ParseResult parse(RSyntaxDocument doc, String style) {
        this.result.clearNotices();
        Element root = doc.getDefaultRootElement();
        this.result.setParsedLines(0, root.getElementCount() - 1);
        if (this.spf == null || doc.getLength() == 0) {
            return this.result;
        }
        try {
            SAXParser sp = this.spf.newSAXParser();
            Handler handler = new Handler(doc);
            DocumentReader r = new DocumentReader(doc);
            InputSource input = new InputSource(r);
            sp.parse(input, (DefaultHandler)handler);
            r.close();
        } catch (SAXParseException sp) {
        } catch (Exception e) {
            this.result.addNotice(new DefaultParserNotice(this, "Error parsing XML: " + e.getMessage(), 0, -1, -1));
        }
        return this.result;
    }

    public void setValidating(boolean validating) {
        this.spf.setValidating(validating);
    }

    private final class Handler
    extends DefaultHandler {
        private Document doc;

        private Handler(Document doc) {
            this.doc = doc;
        }

        private void doError(SAXParseException e, ParserNotice.Level level) {
            int line = e.getLineNumber() - 1;
            Element root = this.doc.getDefaultRootElement();
            Element elem = root.getElement(line);
            int offs = elem.getStartOffset();
            int len = elem.getEndOffset() - offs;
            if (line == root.getElementCount() - 1) {
                ++len;
            }
            DefaultParserNotice pn = new DefaultParserNotice(XmlParser.this, e.getMessage(), line, offs, len);
            pn.setLevel(level);
            XmlParser.this.result.addNotice(pn);
        }

        @Override
        public void error(SAXParseException e) {
            this.doError(e, ParserNotice.Level.ERROR);
        }

        @Override
        public void fatalError(SAXParseException e) {
            this.doError(e, ParserNotice.Level.ERROR);
        }

        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
            if (XmlParser.this.entityResolver != null) {
                return XmlParser.this.entityResolver.resolveEntity(publicId, systemId);
            }
            return super.resolveEntity(publicId, systemId);
        }

        @Override
        public void warning(SAXParseException e) {
            this.doError(e, ParserNotice.Level.WARNING);
        }
    }
}

