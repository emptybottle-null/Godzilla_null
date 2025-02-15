/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

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
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ConnectType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ConnectType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("connecttypeea41type");

    public long getFromSheet();

    public XmlUnsignedInt xgetFromSheet();

    public void setFromSheet(long var1);

    public void xsetFromSheet(XmlUnsignedInt var1);

    public String getFromCell();

    public XmlString xgetFromCell();

    public boolean isSetFromCell();

    public void setFromCell(String var1);

    public void xsetFromCell(XmlString var1);

    public void unsetFromCell();

    public int getFromPart();

    public XmlInt xgetFromPart();

    public boolean isSetFromPart();

    public void setFromPart(int var1);

    public void xsetFromPart(XmlInt var1);

    public void unsetFromPart();

    public long getToSheet();

    public XmlUnsignedInt xgetToSheet();

    public void setToSheet(long var1);

    public void xsetToSheet(XmlUnsignedInt var1);

    public String getToCell();

    public XmlString xgetToCell();

    public boolean isSetToCell();

    public void setToCell(String var1);

    public void xsetToCell(XmlString var1);

    public void unsetToCell();

    public int getToPart();

    public XmlInt xgetToPart();

    public boolean isSetToPart();

    public void setToPart(int var1);

    public void xsetToPart(XmlInt var1);

    public void unsetToPart();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ConnectType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ConnectType newInstance() {
            return (ConnectType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ConnectType newInstance(XmlOptions xmlOptions) {
            return (ConnectType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ConnectType parse(String string) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ConnectType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ConnectType parse(File file) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ConnectType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ConnectType parse(URL uRL) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ConnectType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ConnectType parse(InputStream inputStream) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ConnectType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ConnectType parse(Reader reader) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ConnectType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ConnectType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ConnectType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ConnectType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ConnectType parse(Node node) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ConnectType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ConnectType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ConnectType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ConnectType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ConnectType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ConnectType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

