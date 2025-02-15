/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ObjectType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ObjectType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("objecttypec966type");

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public String getMimeType();

    public XmlString xgetMimeType();

    public boolean isSetMimeType();

    public void setMimeType(String var1);

    public void xsetMimeType(XmlString var1);

    public void unsetMimeType();

    public String getEncoding();

    public XmlAnyURI xgetEncoding();

    public boolean isSetEncoding();

    public void setEncoding(String var1);

    public void xsetEncoding(XmlAnyURI var1);

    public void unsetEncoding();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ObjectType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ObjectType newInstance() {
            return (ObjectType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ObjectType newInstance(XmlOptions xmlOptions) {
            return (ObjectType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ObjectType parse(String string) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ObjectType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ObjectType parse(File file) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ObjectType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ObjectType parse(URL uRL) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ObjectType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ObjectType parse(InputStream inputStream) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ObjectType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ObjectType parse(Reader reader) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ObjectType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ObjectType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ObjectType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ObjectType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ObjectType parse(Node node) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ObjectType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ObjectType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ObjectType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ObjectType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ObjectType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ObjectType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

