/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
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

public interface ConnectsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ConnectsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("connectstype8750type");

    public List<ConnectType> getConnectList();

    @Deprecated
    public ConnectType[] getConnectArray();

    public ConnectType getConnectArray(int var1);

    public int sizeOfConnectArray();

    public void setConnectArray(ConnectType[] var1);

    public void setConnectArray(int var1, ConnectType var2);

    public ConnectType insertNewConnect(int var1);

    public ConnectType addNewConnect();

    public void removeConnect(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ConnectsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ConnectsType newInstance() {
            return (ConnectsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ConnectsType newInstance(XmlOptions xmlOptions) {
            return (ConnectsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ConnectsType parse(String string) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ConnectsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ConnectsType parse(File file) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ConnectsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ConnectsType parse(URL uRL) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ConnectsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ConnectsType parse(InputStream inputStream) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ConnectsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ConnectsType parse(Reader reader) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ConnectsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ConnectsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ConnectsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ConnectsType parse(Node node) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ConnectsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ConnectsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ConnectsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ConnectsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ConnectsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ConnectsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

