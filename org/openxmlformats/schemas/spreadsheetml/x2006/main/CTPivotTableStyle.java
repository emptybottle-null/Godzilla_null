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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTPivotTableStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPivotTableStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpivottablestyle0f84type");

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public boolean getShowRowHeaders();

    public XmlBoolean xgetShowRowHeaders();

    public boolean isSetShowRowHeaders();

    public void setShowRowHeaders(boolean var1);

    public void xsetShowRowHeaders(XmlBoolean var1);

    public void unsetShowRowHeaders();

    public boolean getShowColHeaders();

    public XmlBoolean xgetShowColHeaders();

    public boolean isSetShowColHeaders();

    public void setShowColHeaders(boolean var1);

    public void xsetShowColHeaders(XmlBoolean var1);

    public void unsetShowColHeaders();

    public boolean getShowRowStripes();

    public XmlBoolean xgetShowRowStripes();

    public boolean isSetShowRowStripes();

    public void setShowRowStripes(boolean var1);

    public void xsetShowRowStripes(XmlBoolean var1);

    public void unsetShowRowStripes();

    public boolean getShowColStripes();

    public XmlBoolean xgetShowColStripes();

    public boolean isSetShowColStripes();

    public void setShowColStripes(boolean var1);

    public void xsetShowColStripes(XmlBoolean var1);

    public void unsetShowColStripes();

    public boolean getShowLastColumn();

    public XmlBoolean xgetShowLastColumn();

    public boolean isSetShowLastColumn();

    public void setShowLastColumn(boolean var1);

    public void xsetShowLastColumn(XmlBoolean var1);

    public void unsetShowLastColumn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPivotTableStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPivotTableStyle newInstance() {
            return (CTPivotTableStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPivotTableStyle newInstance(XmlOptions xmlOptions) {
            return (CTPivotTableStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPivotTableStyle parse(String string) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPivotTableStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(File file) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPivotTableStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(URL uRL) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPivotTableStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPivotTableStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(Reader reader) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPivotTableStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPivotTableStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPivotTableStyle parse(Node node) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPivotTableStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPivotTableStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPivotTableStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPivotTableStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

