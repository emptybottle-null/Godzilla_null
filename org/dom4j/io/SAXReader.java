/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.ElementHandler;
import org.dom4j.io.DispatchHandler;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXHelper;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXReader {
    private static final String SAX_STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String SAX_DECL_HANDLER = "http://xml.org/sax/properties/declaration-handler";
    private static final String SAX_LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
    private static final String SAX_LEXICALHANDLER = "http://xml.org/sax/handlers/LexicalHandler";
    private DocumentFactory factory;
    private XMLReader xmlReader;
    private boolean validating;
    private DispatchHandler dispatchHandler;
    private ErrorHandler errorHandler;
    private EntityResolver entityResolver;
    private boolean stringInternEnabled = true;
    private boolean includeInternalDTDDeclarations = false;
    private boolean includeExternalDTDDeclarations = false;
    private boolean mergeAdjacentText = false;
    private boolean stripWhitespaceText = false;
    private boolean ignoreComments = false;
    private String encoding = null;
    private XMLFilter xmlFilter;

    public static SAXReader createDefault() {
        SAXReader reader = new SAXReader();
        try {
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        } catch (SAXException sAXException) {
            // empty catch block
        }
        return reader;
    }

    public SAXReader() {
    }

    public SAXReader(boolean validating) {
        this.validating = validating;
    }

    public SAXReader(DocumentFactory factory) {
        this.factory = factory;
    }

    public SAXReader(DocumentFactory factory, boolean validating) {
        this.factory = factory;
        this.validating = validating;
    }

    public SAXReader(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    public SAXReader(XMLReader xmlReader, boolean validating) {
        this.xmlReader = xmlReader;
        this.validating = validating;
    }

    public SAXReader(String xmlReaderClassName) throws SAXException {
        if (xmlReaderClassName != null) {
            this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
        }
    }

    public SAXReader(String xmlReaderClassName, boolean validating) throws SAXException {
        if (xmlReaderClassName != null) {
            this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
        }
        this.validating = validating;
    }

    public void setProperty(String name, Object value) throws SAXException {
        this.getXMLReader().setProperty(name, value);
    }

    public void setFeature(String name, boolean value) throws SAXException {
        this.getXMLReader().setFeature(name, value);
    }

    public Document read(File file) throws DocumentException {
        try {
            String path;
            InputSource source = new InputSource(new FileInputStream(file));
            if (this.encoding != null) {
                source.setEncoding(this.encoding);
            }
            if ((path = file.getAbsolutePath()) != null) {
                StringBuffer sb = new StringBuffer("file://");
                if (!path.startsWith(File.separator)) {
                    sb.append("/");
                }
                path = path.replace('\\', '/');
                sb.append(path);
                source.setSystemId(sb.toString());
            }
            return this.read(source);
        } catch (FileNotFoundException e) {
            throw new DocumentException(e.getMessage(), e);
        }
    }

    public Document read(URL url) throws DocumentException {
        String systemID = url.toExternalForm();
        InputSource source = new InputSource(systemID);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(String systemId) throws DocumentException {
        InputSource source = new InputSource(systemId);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(InputStream in) throws DocumentException {
        InputSource source = new InputSource(in);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(Reader reader) throws DocumentException {
        InputSource source = new InputSource(reader);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(InputStream in, String systemId) throws DocumentException {
        InputSource source = new InputSource(in);
        source.setSystemId(systemId);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(Reader reader, String systemId) throws DocumentException {
        InputSource source = new InputSource(reader);
        source.setSystemId(systemId);
        if (this.encoding != null) {
            source.setEncoding(this.encoding);
        }
        return this.read(source);
    }

    public Document read(InputSource in) throws DocumentException {
        try {
            XMLReader reader = this.getXMLReader();
            reader = this.installXMLFilter(reader);
            EntityResolver thatEntityResolver = this.entityResolver;
            if (thatEntityResolver == null) {
                this.entityResolver = thatEntityResolver = this.createDefaultEntityResolver(in.getSystemId());
            }
            reader.setEntityResolver(thatEntityResolver);
            SAXContentHandler contentHandler = this.createContentHandler(reader);
            contentHandler.setEntityResolver(thatEntityResolver);
            contentHandler.setInputSource(in);
            boolean internal = this.isIncludeInternalDTDDeclarations();
            boolean external = this.isIncludeExternalDTDDeclarations();
            contentHandler.setIncludeInternalDTDDeclarations(internal);
            contentHandler.setIncludeExternalDTDDeclarations(external);
            contentHandler.setMergeAdjacentText(this.isMergeAdjacentText());
            contentHandler.setStripWhitespaceText(this.isStripWhitespaceText());
            contentHandler.setIgnoreComments(this.isIgnoreComments());
            reader.setContentHandler(contentHandler);
            this.configureReader(reader, contentHandler);
            reader.parse(in);
            return contentHandler.getDocument();
        } catch (Exception e) {
            if (e instanceof SAXParseException) {
                SAXParseException parseException = (SAXParseException)e;
                String systemId = parseException.getSystemId();
                if (systemId == null) {
                    systemId = "";
                }
                String message = "Error on line " + parseException.getLineNumber() + " of document " + systemId + " : " + parseException.getMessage();
                throw new DocumentException(message, e);
            }
            throw new DocumentException(e.getMessage(), e);
        }
    }

    public boolean isValidating() {
        return this.validating;
    }

    public void setValidation(boolean validation) {
        this.validating = validation;
    }

    public boolean isIncludeInternalDTDDeclarations() {
        return this.includeInternalDTDDeclarations;
    }

    public void setIncludeInternalDTDDeclarations(boolean include) {
        this.includeInternalDTDDeclarations = include;
    }

    public boolean isIncludeExternalDTDDeclarations() {
        return this.includeExternalDTDDeclarations;
    }

    public void setIncludeExternalDTDDeclarations(boolean include) {
        this.includeExternalDTDDeclarations = include;
    }

    public boolean isStringInternEnabled() {
        return this.stringInternEnabled;
    }

    public void setStringInternEnabled(boolean stringInternEnabled) {
        this.stringInternEnabled = stringInternEnabled;
    }

    public boolean isMergeAdjacentText() {
        return this.mergeAdjacentText;
    }

    public void setMergeAdjacentText(boolean mergeAdjacentText) {
        this.mergeAdjacentText = mergeAdjacentText;
    }

    public boolean isStripWhitespaceText() {
        return this.stripWhitespaceText;
    }

    public void setStripWhitespaceText(boolean stripWhitespaceText) {
        this.stripWhitespaceText = stripWhitespaceText;
    }

    public boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    public void setIgnoreComments(boolean ignoreComments) {
        this.ignoreComments = ignoreComments;
    }

    public DocumentFactory getDocumentFactory() {
        if (this.factory == null) {
            this.factory = DocumentFactory.getInstance();
        }
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public XMLReader getXMLReader() throws SAXException {
        if (this.xmlReader == null) {
            this.xmlReader = this.createXMLReader();
        }
        return this.xmlReader;
    }

    public void setXMLReader(XMLReader reader) {
        this.xmlReader = reader;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setXMLReaderClassName(String xmlReaderClassName) throws SAXException {
        this.setXMLReader(XMLReaderFactory.createXMLReader(xmlReaderClassName));
    }

    public void addHandler(String path, ElementHandler handler) {
        this.getDispatchHandler().addHandler(path, handler);
    }

    public void removeHandler(String path) {
        this.getDispatchHandler().removeHandler(path);
    }

    public void setDefaultHandler(ElementHandler handler) {
        this.getDispatchHandler().setDefaultHandler(handler);
    }

    public void resetHandlers() {
        this.getDispatchHandler().resetHandlers();
    }

    public XMLFilter getXMLFilter() {
        return this.xmlFilter;
    }

    public void setXMLFilter(XMLFilter filter) {
        this.xmlFilter = filter;
    }

    protected XMLReader installXMLFilter(XMLReader reader) {
        XMLFilter filter = this.getXMLFilter();
        if (filter != null) {
            XMLReader parent;
            XMLFilter root = filter;
            while ((parent = root.getParent()) instanceof XMLFilter) {
                root = (XMLFilter)parent;
            }
            root.setParent(reader);
            return filter;
        }
        return reader;
    }

    protected DispatchHandler getDispatchHandler() {
        if (this.dispatchHandler == null) {
            this.dispatchHandler = new DispatchHandler();
        }
        return this.dispatchHandler;
    }

    protected void setDispatchHandler(DispatchHandler dispatchHandler) {
        this.dispatchHandler = dispatchHandler;
    }

    protected XMLReader createXMLReader() throws SAXException {
        return SAXHelper.createXMLReader(this.isValidating());
    }

    protected void configureReader(XMLReader reader, DefaultHandler handler) throws DocumentException {
        block5: {
            SAXHelper.setParserProperty(reader, SAX_LEXICALHANDLER, handler);
            SAXHelper.setParserProperty(reader, SAX_LEXICAL_HANDLER, handler);
            if (this.includeInternalDTDDeclarations || this.includeExternalDTDDeclarations) {
                SAXHelper.setParserProperty(reader, SAX_DECL_HANDLER, handler);
            }
            SAXHelper.setParserFeature(reader, SAX_STRING_INTERNING, this.isStringInternEnabled());
            try {
                reader.setFeature("http://xml.org/sax/features/validation", this.isValidating());
                if (this.errorHandler != null) {
                    reader.setErrorHandler(this.errorHandler);
                } else {
                    reader.setErrorHandler(handler);
                }
            } catch (Exception e) {
                if (!this.isValidating()) break block5;
                throw new DocumentException("Validation not supported for XMLReader: " + reader, e);
            }
        }
    }

    protected SAXContentHandler createContentHandler(XMLReader reader) {
        return new SAXContentHandler(this.getDocumentFactory(), this.dispatchHandler);
    }

    protected EntityResolver createDefaultEntityResolver(String systemId) {
        int idx;
        String prefix = null;
        if (systemId != null && systemId.length() > 0 && (idx = systemId.lastIndexOf(47)) > 0) {
            prefix = systemId.substring(0, idx + 1);
        }
        return new SAXEntityResolver(prefix);
    }

    protected static class SAXEntityResolver
    implements EntityResolver,
    Serializable {
        protected String uriPrefix;

        public SAXEntityResolver(String uriPrefix) {
            this.uriPrefix = uriPrefix;
        }

        @Override
        public InputSource resolveEntity(String publicId, String systemId) {
            if (systemId != null && systemId.length() > 0 && this.uriPrefix != null && systemId.indexOf(58) <= 0) {
                systemId = this.uriPrefix + systemId;
            }
            return new InputSource(systemId);
        }
    }
}

