/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.w3c.dom.Node;

public interface CTTable
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTable.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttable5f3ftype");

    public CTTableProperties getTblPr();

    public boolean isSetTblPr();

    public void setTblPr(CTTableProperties var1);

    public CTTableProperties addNewTblPr();

    public void unsetTblPr();

    public CTTableGrid getTblGrid();

    public void setTblGrid(CTTableGrid var1);

    public CTTableGrid addNewTblGrid();

    public List<CTTableRow> getTrList();

    @Deprecated
    public CTTableRow[] getTrArray();

    public CTTableRow getTrArray(int var1);

    public int sizeOfTrArray();

    public void setTrArray(CTTableRow[] var1);

    public void setTrArray(int var1, CTTableRow var2);

    public CTTableRow insertNewTr(int var1);

    public CTTableRow addNewTr();

    public void removeTr(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTable.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTable newInstance() {
            return (CTTable)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTable newInstance(XmlOptions xmlOptions) {
            return (CTTable)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTable parse(String string) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTable parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTable parse(File file) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTable parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTable parse(URL uRL) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTable parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTable parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTable parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTable parse(Reader reader) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTable parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTable)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTable parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTable parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTable parse(Node node) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTable parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTable)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTable parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTable)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTable parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTable)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

