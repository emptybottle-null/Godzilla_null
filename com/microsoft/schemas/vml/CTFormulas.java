/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.vml.CTF;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTFormulas
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFormulas.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctformulas808btype");

    public List<CTF> getFList();

    @Deprecated
    public CTF[] getFArray();

    public CTF getFArray(int var1);

    public int sizeOfFArray();

    public void setFArray(CTF[] var1);

    public void setFArray(int var1, CTF var2);

    public CTF insertNewF(int var1);

    public CTF addNewF();

    public void removeF(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFormulas.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFormulas newInstance() {
            return (CTFormulas)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFormulas newInstance(XmlOptions xmlOptions) {
            return (CTFormulas)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFormulas parse(String string) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFormulas parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFormulas parse(File file) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFormulas parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFormulas parse(URL uRL) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFormulas parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFormulas parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFormulas parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFormulas parse(Reader reader) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFormulas parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFormulas)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFormulas parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFormulas parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFormulas parse(Node node) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFormulas parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFormulas)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFormulas parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFormulas)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFormulas parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFormulas)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

