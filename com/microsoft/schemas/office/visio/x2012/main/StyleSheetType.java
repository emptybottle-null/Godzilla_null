/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.SheetType;
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface StyleSheetType
extends SheetType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(StyleSheetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stylesheettypeebcbtype");

    public long getID();

    public XmlUnsignedInt xgetID();

    public void setID(long var1);

    public void xsetID(XmlUnsignedInt var1);

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public String getNameU();

    public XmlString xgetNameU();

    public boolean isSetNameU();

    public void setNameU(String var1);

    public void xsetNameU(XmlString var1);

    public void unsetNameU();

    public boolean getIsCustomName();

    public XmlBoolean xgetIsCustomName();

    public boolean isSetIsCustomName();

    public void setIsCustomName(boolean var1);

    public void xsetIsCustomName(XmlBoolean var1);

    public void unsetIsCustomName();

    public boolean getIsCustomNameU();

    public XmlBoolean xgetIsCustomNameU();

    public boolean isSetIsCustomNameU();

    public void setIsCustomNameU(boolean var1);

    public void xsetIsCustomNameU(XmlBoolean var1);

    public void unsetIsCustomNameU();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(StyleSheetType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static StyleSheetType newInstance() {
            return (StyleSheetType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static StyleSheetType newInstance(XmlOptions xmlOptions) {
            return (StyleSheetType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static StyleSheetType parse(String string) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static StyleSheetType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static StyleSheetType parse(File file) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static StyleSheetType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static StyleSheetType parse(URL uRL) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static StyleSheetType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static StyleSheetType parse(InputStream inputStream) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static StyleSheetType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static StyleSheetType parse(Reader reader) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static StyleSheetType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static StyleSheetType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static StyleSheetType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static StyleSheetType parse(Node node) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static StyleSheetType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static StyleSheetType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (StyleSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static StyleSheetType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (StyleSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

