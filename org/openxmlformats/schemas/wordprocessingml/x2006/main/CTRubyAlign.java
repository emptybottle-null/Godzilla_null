/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STRubyAlign
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRubyAlign;
import org.w3c.dom.Node;

public interface CTRubyAlign
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRubyAlign.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrubyalign41e7type");

    public STRubyAlign.Enum getVal();

    public STRubyAlign xgetVal();

    public void setVal(STRubyAlign.Enum var1);

    public void xsetVal(STRubyAlign var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRubyAlign.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRubyAlign newInstance() {
            return (CTRubyAlign)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRubyAlign newInstance(XmlOptions xmlOptions) {
            return (CTRubyAlign)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRubyAlign parse(String string) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRubyAlign parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRubyAlign parse(File file) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRubyAlign parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRubyAlign parse(URL uRL) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRubyAlign parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRubyAlign parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRubyAlign parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRubyAlign parse(Reader reader) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRubyAlign parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRubyAlign parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRubyAlign parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRubyAlign parse(Node node) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRubyAlign parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRubyAlign parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRubyAlign parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRubyAlign)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

