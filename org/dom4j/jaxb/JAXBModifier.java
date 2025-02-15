/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.ElementModifier;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXModifier;
import org.dom4j.io.XMLWriter;
import org.dom4j.jaxb.JAXBObjectModifier;
import org.dom4j.jaxb.JAXBRuntimeException;
import org.dom4j.jaxb.JAXBSupport;
import org.xml.sax.InputSource;

public class JAXBModifier
extends JAXBSupport {
    private SAXModifier modifier;
    private XMLWriter xmlWriter;
    private boolean pruneElements;
    private OutputFormat outputFormat;
    private HashMap<String, JAXBObjectModifier> modifiers = new HashMap();

    public JAXBModifier(String contextPath) {
        super(contextPath);
        this.outputFormat = new OutputFormat();
    }

    public JAXBModifier(String contextPath, ClassLoader classloader) {
        super(contextPath, classloader);
        this.outputFormat = new OutputFormat();
    }

    public JAXBModifier(String contextPath, OutputFormat outputFormat) {
        super(contextPath);
        this.outputFormat = outputFormat;
    }

    public JAXBModifier(String contextPath, ClassLoader classloader, OutputFormat outputFormat) {
        super(contextPath, classloader);
        this.outputFormat = outputFormat;
    }

    public Document modify(File source) throws DocumentException, IOException {
        return this.installModifier().modify(source);
    }

    public Document modify(File source, Charset charset) throws DocumentException, IOException {
        try {
            InputStreamReader reader = new InputStreamReader((InputStream)new FileInputStream(source), charset);
            return this.installModifier().modify(reader);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        } catch (FileNotFoundException ex) {
            throw new DocumentException(ex.getMessage(), ex);
        }
    }

    public Document modify(InputSource source) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream source) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream source, String systemId) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader r) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(r);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader source, String systemId) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(String url) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(url);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(URL source) throws DocumentException, IOException {
        try {
            return this.installModifier().modify(source);
        } catch (JAXBRuntimeException ex) {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public void setOutput(File file) throws IOException {
        this.createXMLWriter().setOutputStream(new FileOutputStream(file));
    }

    public void setOutput(OutputStream outputStream) throws IOException {
        this.createXMLWriter().setOutputStream(outputStream);
    }

    public void setOutput(Writer writer) throws IOException {
        this.createXMLWriter().setWriter(writer);
    }

    public void addObjectModifier(String path, JAXBObjectModifier mod) {
        this.modifiers.put(path, mod);
    }

    public void removeObjectModifier(String path) {
        this.modifiers.remove(path);
        this.getModifier().removeModifier(path);
    }

    public void resetObjectModifiers() {
        this.modifiers.clear();
        this.getModifier().resetModifiers();
    }

    public boolean isPruneElements() {
        return this.pruneElements;
    }

    public void setPruneElements(boolean pruneElements) {
        this.pruneElements = pruneElements;
    }

    private SAXModifier installModifier() throws IOException {
        this.modifier = new SAXModifier(this.isPruneElements());
        this.modifier.resetModifiers();
        for (Map.Entry<String, JAXBObjectModifier> entry : this.modifiers.entrySet()) {
            JAXBElementModifier mod = new JAXBElementModifier(this, entry.getValue());
            this.getModifier().addModifier(entry.getKey(), mod);
        }
        this.modifier.setXMLWriter(this.getXMLWriter());
        return this.modifier;
    }

    private SAXModifier getModifier() {
        if (this.modifier == null) {
            this.modifier = new SAXModifier(this.isPruneElements());
        }
        return this.modifier;
    }

    private XMLWriter getXMLWriter() {
        return this.xmlWriter;
    }

    private XMLWriter createXMLWriter() throws IOException {
        if (this.xmlWriter == null) {
            this.xmlWriter = new XMLWriter(this.outputFormat);
        }
        return this.xmlWriter;
    }

    private class JAXBElementModifier
    implements ElementModifier {
        private JAXBModifier jaxbModifier;
        private JAXBObjectModifier objectModifier;

        public JAXBElementModifier(JAXBModifier jaxbModifier, JAXBObjectModifier objectModifier) {
            this.jaxbModifier = jaxbModifier;
            this.objectModifier = objectModifier;
        }

        @Override
        public Element modifyElement(Element element) throws Exception {
            javax.xml.bind.Element originalObject = this.jaxbModifier.unmarshal(element);
            javax.xml.bind.Element modifiedObject = this.objectModifier.modifyObject(originalObject);
            return this.jaxbModifier.marshal(modifiedObject);
        }
    }
}

