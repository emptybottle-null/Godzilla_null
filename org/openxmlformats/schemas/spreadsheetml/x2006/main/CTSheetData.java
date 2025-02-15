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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.w3c.dom.Node;

public interface CTSheetData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetdata8408type");

    public List<CTRow> getRowList();

    @Deprecated
    public CTRow[] getRowArray();

    public CTRow getRowArray(int var1);

    public int sizeOfRowArray();

    public void setRowArray(CTRow[] var1);

    public void setRowArray(int var1, CTRow var2);

    public CTRow insertNewRow(int var1);

    public CTRow addNewRow();

    public void removeRow(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetData newInstance() {
            return (CTSheetData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetData newInstance(XmlOptions xmlOptions) {
            return (CTSheetData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetData parse(String string) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetData parse(File file) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetData parse(URL uRL) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetData parse(Reader reader) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetData parse(Node node) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

