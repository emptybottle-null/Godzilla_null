/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.dom4j.jaxb.JAXBObjectHandler;
import org.dom4j.jaxb.JAXBRuntimeException;
import org.dom4j.jaxb.JAXBSupport;
import org.xml.sax.InputSource;

public class JAXBReader
extends JAXBSupport {
    private SAXReader reader;
    private boolean pruneElements;

    public JAXBReader(String contextPath) {
        super(contextPath);
    }

    public JAXBReader(String contextPath, ClassLoader classloader) {
        super(contextPath, classloader);
    }

    public Document read(File source) throws DocumentException {
        return this.getReader().read(source);
    }

    public Document read(File file, Charset charset) throws DocumentException {
        try {
            InputStreamReader xmlReader = new InputStreamReader((InputStream)new FileInputStream(file), charset);
            return this.getReader().read(xmlReader);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        } catch (FileNotFoundException ex) {
            throw new DocumentException(ex.getMessage(), ex);
        }
    }

    public Document read(InputSource source) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(InputStream source) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(InputStream source, String systemId) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(Reader source) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(Reader source, String systemId) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(String source) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document read(URL source) throws DocumentException {
        try {
            return this.getReader().read(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public void addObjectHandler(String path, JAXBObjectHandler handler) {
        UnmarshalElementHandler eHandler = new UnmarshalElementHandler(this, handler);
        this.getReader().addHandler(path, eHandler);
    }

    public void removeObjectHandler(String path) {
        this.getReader().removeHandler(path);
    }

    public void addHandler(String path, ElementHandler handler) {
        this.getReader().addHandler(path, handler);
    }

    public void removeHandler(String path) {
        this.getReader().removeHandler(path);
    }

    public void resetHandlers() {
        this.getReader().resetHandlers();
    }

    public boolean isPruneElements() {
        return this.pruneElements;
    }

    public void setPruneElements(boolean pruneElements) {
        this.pruneElements = pruneElements;
        if (pruneElements) {
            this.getReader().setDefaultHandler(new PruningElementHandler());
        }
    }

    private SAXReader getReader() {
        if (this.reader == null) {
            this.reader = new SAXReader();
        }
        return this.reader;
    }

    private class PruningElementHandler
    implements ElementHandler {
        @Override
        public void onStart(ElementPath parm1) {
        }

        @Override
        public void onEnd(ElementPath elementPath) {
            Element elem = elementPath.getCurrent();
            elem.detach();
            elem = null;
        }
    }

    private class UnmarshalElementHandler
    implements ElementHandler {
        private JAXBReader jaxbReader;
        private JAXBObjectHandler handler;

        public UnmarshalElementHandler(JAXBReader documentReader, JAXBObjectHandler handler) {
            this.jaxbReader = documentReader;
            this.handler = handler;
        }

        @Override
        public void onStart(ElementPath elementPath) {
        }

        @Override
        public void onEnd(ElementPath elementPath) {
            try {
                Element elem = elementPath.getCurrent();
                javax.xml.bind.Element jaxbObject = this.jaxbReader.unmarshal(elem);
                if (this.jaxbReader.isPruneElements()) {
                    elem.detach();
                }
                this.handler.handleObject(jaxbObject);
            } catch (Exception ex) {
                throw new JAXBRuntimeException(ex);
            }
        }
    }
}

