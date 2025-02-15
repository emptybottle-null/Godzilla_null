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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;
import org.w3c.dom.Node;

public interface CTRuby
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRuby.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctruby9dcetype");

    public CTRubyPr getRubyPr();

    public void setRubyPr(CTRubyPr var1);

    public CTRubyPr addNewRubyPr();

    public CTRubyContent getRt();

    public void setRt(CTRubyContent var1);

    public CTRubyContent addNewRt();

    public CTRubyContent getRubyBase();

    public void setRubyBase(CTRubyContent var1);

    public CTRubyContent addNewRubyBase();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRuby.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRuby newInstance() {
            return (CTRuby)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRuby newInstance(XmlOptions xmlOptions) {
            return (CTRuby)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRuby parse(String string) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRuby parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRuby parse(File file) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRuby parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRuby parse(URL uRL) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRuby parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRuby parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRuby parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRuby parse(Reader reader) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRuby parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRuby)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRuby parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRuby parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRuby parse(Node node) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRuby parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRuby)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRuby parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRuby)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRuby parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRuby)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

