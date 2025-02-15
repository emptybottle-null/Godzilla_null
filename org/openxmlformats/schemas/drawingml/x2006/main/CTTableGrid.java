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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.w3c.dom.Node;

public interface CTTableGrid
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableGrid.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablegrid69a5type");

    public List<CTTableCol> getGridColList();

    @Deprecated
    public CTTableCol[] getGridColArray();

    public CTTableCol getGridColArray(int var1);

    public int sizeOfGridColArray();

    public void setGridColArray(CTTableCol[] var1);

    public void setGridColArray(int var1, CTTableCol var2);

    public CTTableCol insertNewGridCol(int var1);

    public CTTableCol addNewGridCol();

    public void removeGridCol(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableGrid.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableGrid newInstance() {
            return (CTTableGrid)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableGrid newInstance(XmlOptions xmlOptions) {
            return (CTTableGrid)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableGrid parse(String string) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableGrid parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableGrid parse(File file) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableGrid parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableGrid parse(URL uRL) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableGrid parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableGrid parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableGrid parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableGrid parse(Reader reader) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableGrid parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableGrid)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableGrid parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableGrid parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableGrid parse(Node node) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableGrid parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableGrid)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableGrid parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableGrid)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableGrid parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableGrid)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

