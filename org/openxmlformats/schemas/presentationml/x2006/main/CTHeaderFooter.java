/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.w3c.dom.Node;

public interface CTHeaderFooter
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHeaderFooter.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctheaderfooterb29dtype");

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public boolean getSldNum();

    public XmlBoolean xgetSldNum();

    public boolean isSetSldNum();

    public void setSldNum(boolean var1);

    public void xsetSldNum(XmlBoolean var1);

    public void unsetSldNum();

    public boolean getHdr();

    public XmlBoolean xgetHdr();

    public boolean isSetHdr();

    public void setHdr(boolean var1);

    public void xsetHdr(XmlBoolean var1);

    public void unsetHdr();

    public boolean getFtr();

    public XmlBoolean xgetFtr();

    public boolean isSetFtr();

    public void setFtr(boolean var1);

    public void xsetFtr(XmlBoolean var1);

    public void unsetFtr();

    public boolean getDt();

    public XmlBoolean xgetDt();

    public boolean isSetDt();

    public void setDt(boolean var1);

    public void xsetDt(XmlBoolean var1);

    public void unsetDt();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHeaderFooter.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHeaderFooter newInstance() {
            return (CTHeaderFooter)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHeaderFooter newInstance(XmlOptions xmlOptions) {
            return (CTHeaderFooter)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHeaderFooter parse(String string) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHeaderFooter parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHeaderFooter parse(File file) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHeaderFooter parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHeaderFooter parse(URL uRL) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHeaderFooter parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHeaderFooter parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHeaderFooter parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHeaderFooter parse(Reader reader) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHeaderFooter parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHeaderFooter parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHeaderFooter parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHeaderFooter parse(Node node) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHeaderFooter parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHeaderFooter parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHeaderFooter parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

