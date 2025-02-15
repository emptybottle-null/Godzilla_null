/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXmlDataType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTXmlColumnPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTXmlColumnPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctxmlcolumnprc14etype");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getMapId();

    public XmlUnsignedInt xgetMapId();

    public void setMapId(long var1);

    public void xsetMapId(XmlUnsignedInt var1);

    public String getXpath();

    public STXstring xgetXpath();

    public void setXpath(String var1);

    public void xsetXpath(STXstring var1);

    public boolean getDenormalized();

    public XmlBoolean xgetDenormalized();

    public boolean isSetDenormalized();

    public void setDenormalized(boolean var1);

    public void xsetDenormalized(XmlBoolean var1);

    public void unsetDenormalized();

    public STXmlDataType.Enum getXmlDataType();

    public STXmlDataType xgetXmlDataType();

    public void setXmlDataType(STXmlDataType.Enum var1);

    public void xsetXmlDataType(STXmlDataType var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTXmlColumnPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTXmlColumnPr newInstance() {
            return (CTXmlColumnPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTXmlColumnPr newInstance(XmlOptions xmlOptions) {
            return (CTXmlColumnPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTXmlColumnPr parse(String string) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTXmlColumnPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(File file) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTXmlColumnPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(URL uRL) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTXmlColumnPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTXmlColumnPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(Reader reader) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTXmlColumnPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTXmlColumnPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTXmlColumnPr parse(Node node) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTXmlColumnPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTXmlColumnPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTXmlColumnPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTXmlColumnPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

