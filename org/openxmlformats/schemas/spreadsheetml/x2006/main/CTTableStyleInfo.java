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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTTableStyleInfo
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyleInfo.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestyleinfo499atype");

    public String getName();

    public STXstring xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public void unsetName();

    public boolean getShowFirstColumn();

    public XmlBoolean xgetShowFirstColumn();

    public boolean isSetShowFirstColumn();

    public void setShowFirstColumn(boolean var1);

    public void xsetShowFirstColumn(XmlBoolean var1);

    public void unsetShowFirstColumn();

    public boolean getShowLastColumn();

    public XmlBoolean xgetShowLastColumn();

    public boolean isSetShowLastColumn();

    public void setShowLastColumn(boolean var1);

    public void xsetShowLastColumn(XmlBoolean var1);

    public void unsetShowLastColumn();

    public boolean getShowRowStripes();

    public XmlBoolean xgetShowRowStripes();

    public boolean isSetShowRowStripes();

    public void setShowRowStripes(boolean var1);

    public void xsetShowRowStripes(XmlBoolean var1);

    public void unsetShowRowStripes();

    public boolean getShowColumnStripes();

    public XmlBoolean xgetShowColumnStripes();

    public boolean isSetShowColumnStripes();

    public void setShowColumnStripes(boolean var1);

    public void xsetShowColumnStripes(XmlBoolean var1);

    public void unsetShowColumnStripes();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyleInfo.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyleInfo newInstance() {
            return (CTTableStyleInfo)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyleInfo newInstance(XmlOptions xmlOptions) {
            return (CTTableStyleInfo)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyleInfo parse(String string) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyleInfo parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(File file) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyleInfo parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyleInfo parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyleInfo parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyleInfo parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyleInfo parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyleInfo parse(Node node) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyleInfo parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyleInfo parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyleInfo parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyleInfo)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

