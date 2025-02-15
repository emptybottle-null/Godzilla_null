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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlCellPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.w3c.dom.Node;

public interface CTSingleXmlCell
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSingleXmlCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsinglexmlcell7790type");

    public CTXmlCellPr getXmlCellPr();

    public void setXmlCellPr(CTXmlCellPr var1);

    public CTXmlCellPr addNewXmlCellPr();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getId();

    public XmlUnsignedInt xgetId();

    public void setId(long var1);

    public void xsetId(XmlUnsignedInt var1);

    public String getR();

    public STCellRef xgetR();

    public void setR(String var1);

    public void xsetR(STCellRef var1);

    public long getConnectionId();

    public XmlUnsignedInt xgetConnectionId();

    public void setConnectionId(long var1);

    public void xsetConnectionId(XmlUnsignedInt var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSingleXmlCell.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSingleXmlCell newInstance() {
            return (CTSingleXmlCell)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSingleXmlCell newInstance(XmlOptions xmlOptions) {
            return (CTSingleXmlCell)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSingleXmlCell parse(String string) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSingleXmlCell parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(File file) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSingleXmlCell parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(URL uRL) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSingleXmlCell parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSingleXmlCell parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(Reader reader) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSingleXmlCell parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSingleXmlCell parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSingleXmlCell parse(Node node) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSingleXmlCell parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSingleXmlCell parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSingleXmlCell parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSingleXmlCell)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

