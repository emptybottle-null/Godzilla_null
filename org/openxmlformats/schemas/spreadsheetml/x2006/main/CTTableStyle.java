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
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleElement;
import org.w3c.dom.Node;

public interface CTTableStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestylea24ctype");

    public List<CTTableStyleElement> getTableStyleElementList();

    @Deprecated
    public CTTableStyleElement[] getTableStyleElementArray();

    public CTTableStyleElement getTableStyleElementArray(int var1);

    public int sizeOfTableStyleElementArray();

    public void setTableStyleElementArray(CTTableStyleElement[] var1);

    public void setTableStyleElementArray(int var1, CTTableStyleElement var2);

    public CTTableStyleElement insertNewTableStyleElement(int var1);

    public CTTableStyleElement addNewTableStyleElement();

    public void removeTableStyleElement(int var1);

    public String getName();

    public XmlString xgetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public boolean getPivot();

    public XmlBoolean xgetPivot();

    public boolean isSetPivot();

    public void setPivot(boolean var1);

    public void xsetPivot(XmlBoolean var1);

    public void unsetPivot();

    public boolean getTable();

    public XmlBoolean xgetTable();

    public boolean isSetTable();

    public void setTable(boolean var1);

    public void xsetTable(XmlBoolean var1);

    public void unsetTable();

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyle newInstance() {
            return (CTTableStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyle newInstance(XmlOptions xmlOptions) {
            return (CTTableStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyle parse(String string) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyle parse(File file) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyle parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyle parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyle parse(Node node) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

