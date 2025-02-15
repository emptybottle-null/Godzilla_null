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

public interface CTXmlPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTXmlPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctxmlpr2c58type");

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTXmlPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTXmlPr newInstance() {
            return (CTXmlPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTXmlPr newInstance(XmlOptions xmlOptions) {
            return (CTXmlPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTXmlPr parse(String string) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTXmlPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTXmlPr parse(File file) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTXmlPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTXmlPr parse(URL uRL) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTXmlPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTXmlPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTXmlPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTXmlPr parse(Reader reader) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTXmlPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXmlPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTXmlPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTXmlPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTXmlPr parse(Node node) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTXmlPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTXmlPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTXmlPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTXmlPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTXmlPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTXmlPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

