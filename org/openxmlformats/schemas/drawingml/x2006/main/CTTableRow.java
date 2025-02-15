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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.w3c.dom.Node;

public interface CTTableRow
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableRow.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablerow4ac7type");

    public List<CTTableCell> getTcList();

    @Deprecated
    public CTTableCell[] getTcArray();

    public CTTableCell getTcArray(int var1);

    public int sizeOfTcArray();

    public void setTcArray(CTTableCell[] var1);

    public void setTcArray(int var1, CTTableCell var2);

    public CTTableCell insertNewTc(int var1);

    public CTTableCell addNewTc();

    public void removeTc(int var1);

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getH();

    public STCoordinate xgetH();

    public void setH(long var1);

    public void xsetH(STCoordinate var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableRow.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableRow newInstance() {
            return (CTTableRow)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableRow newInstance(XmlOptions xmlOptions) {
            return (CTTableRow)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableRow parse(String string) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableRow parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableRow parse(File file) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableRow parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableRow parse(URL uRL) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableRow parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableRow parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableRow parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableRow parse(Reader reader) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableRow parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableRow)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableRow parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableRow parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableRow parse(Node node) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableRow parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableRow)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableRow parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableRow)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableRow parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableRow)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

