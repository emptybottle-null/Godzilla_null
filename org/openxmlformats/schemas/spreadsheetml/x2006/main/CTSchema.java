/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTSchema
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSchema.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctschema0e6atype");

    public String getID();

    public XmlString xgetID();

    public void setID(String var1);

    public void xsetID(XmlString var1);

    public String getSchemaRef();

    public XmlString xgetSchemaRef();

    public boolean isSetSchemaRef();

    public void setSchemaRef(String var1);

    public void xsetSchemaRef(XmlString var1);

    public void unsetSchemaRef();

    public String getNamespace();

    public XmlString xgetNamespace();

    public boolean isSetNamespace();

    public void setNamespace(String var1);

    public void xsetNamespace(XmlString var1);

    public void unsetNamespace();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSchema.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSchema newInstance() {
            return (CTSchema)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSchema newInstance(XmlOptions xmlOptions) {
            return (CTSchema)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSchema parse(String string) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSchema parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSchema parse(File file) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSchema parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSchema parse(URL uRL) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSchema parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSchema parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSchema parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSchema parse(Reader reader) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSchema parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSchema)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSchema parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSchema parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSchema parse(Node node) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSchema parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSchema)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSchema parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSchema)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSchema parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSchema)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

