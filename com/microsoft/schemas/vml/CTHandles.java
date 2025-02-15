/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.vml.CTH;
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

public interface CTHandles
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHandles.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cthandles5c1ftype");

    public List<CTH> getHList();

    @Deprecated
    public CTH[] getHArray();

    public CTH getHArray(int var1);

    public int sizeOfHArray();

    public void setHArray(CTH[] var1);

    public void setHArray(int var1, CTH var2);

    public CTH insertNewH(int var1);

    public CTH addNewH();

    public void removeH(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHandles.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHandles newInstance() {
            return (CTHandles)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHandles newInstance(XmlOptions xmlOptions) {
            return (CTHandles)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHandles parse(String string) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHandles parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHandles parse(File file) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHandles parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHandles parse(URL uRL) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHandles parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHandles parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHandles parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHandles parse(Reader reader) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHandles parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHandles)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHandles parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHandles parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHandles parse(Node node) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHandles parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHandles)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHandles parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHandles)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHandles parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHandles)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

