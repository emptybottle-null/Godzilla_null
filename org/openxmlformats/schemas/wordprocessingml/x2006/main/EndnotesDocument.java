/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.w3c.dom.Node;

public interface EndnotesDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(EndnotesDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("endnotes960edoctype");

    public CTEndnotes getEndnotes();

    public void setEndnotes(CTEndnotes var1);

    public CTEndnotes addNewEndnotes();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(EndnotesDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static EndnotesDocument newInstance() {
            return (EndnotesDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static EndnotesDocument newInstance(XmlOptions xmlOptions) {
            return (EndnotesDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static EndnotesDocument parse(String string) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static EndnotesDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static EndnotesDocument parse(File file) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static EndnotesDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static EndnotesDocument parse(URL uRL) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static EndnotesDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static EndnotesDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static EndnotesDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static EndnotesDocument parse(Reader reader) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static EndnotesDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static EndnotesDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static EndnotesDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static EndnotesDocument parse(Node node) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static EndnotesDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static EndnotesDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static EndnotesDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (EndnotesDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

