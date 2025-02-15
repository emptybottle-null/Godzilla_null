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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.w3c.dom.Node;

public interface CTSheets
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheets.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheets49fdtype");

    public List<CTSheet> getSheetList();

    @Deprecated
    public CTSheet[] getSheetArray();

    public CTSheet getSheetArray(int var1);

    public int sizeOfSheetArray();

    public void setSheetArray(CTSheet[] var1);

    public void setSheetArray(int var1, CTSheet var2);

    public CTSheet insertNewSheet(int var1);

    public CTSheet addNewSheet();

    public void removeSheet(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheets.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheets newInstance() {
            return (CTSheets)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheets newInstance(XmlOptions xmlOptions) {
            return (CTSheets)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheets parse(String string) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheets parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheets parse(File file) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheets parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheets parse(URL uRL) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheets parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheets parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheets parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheets parse(Reader reader) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheets parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheets)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheets parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheets parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheets parse(Node node) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheets parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheets)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheets parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheets)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheets parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheets)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

