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
import org.w3c.dom.Node;

public interface CTOutlinePr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOutlinePr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoutlineprc483type");

    public boolean getApplyStyles();

    public XmlBoolean xgetApplyStyles();

    public boolean isSetApplyStyles();

    public void setApplyStyles(boolean var1);

    public void xsetApplyStyles(XmlBoolean var1);

    public void unsetApplyStyles();

    public boolean getSummaryBelow();

    public XmlBoolean xgetSummaryBelow();

    public boolean isSetSummaryBelow();

    public void setSummaryBelow(boolean var1);

    public void xsetSummaryBelow(XmlBoolean var1);

    public void unsetSummaryBelow();

    public boolean getSummaryRight();

    public XmlBoolean xgetSummaryRight();

    public boolean isSetSummaryRight();

    public void setSummaryRight(boolean var1);

    public void xsetSummaryRight(XmlBoolean var1);

    public void unsetSummaryRight();

    public boolean getShowOutlineSymbols();

    public XmlBoolean xgetShowOutlineSymbols();

    public boolean isSetShowOutlineSymbols();

    public void setShowOutlineSymbols(boolean var1);

    public void xsetShowOutlineSymbols(XmlBoolean var1);

    public void unsetShowOutlineSymbols();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOutlinePr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOutlinePr newInstance() {
            return (CTOutlinePr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOutlinePr newInstance(XmlOptions xmlOptions) {
            return (CTOutlinePr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOutlinePr parse(String string) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOutlinePr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOutlinePr parse(File file) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOutlinePr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOutlinePr parse(URL uRL) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOutlinePr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOutlinePr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOutlinePr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOutlinePr parse(Reader reader) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOutlinePr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOutlinePr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOutlinePr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOutlinePr parse(Node node) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOutlinePr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOutlinePr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOutlinePr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOutlinePr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

