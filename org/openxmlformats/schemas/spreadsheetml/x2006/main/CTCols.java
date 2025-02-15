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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.w3c.dom.Node;

public interface CTCols
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCols.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcols627ctype");

    public List<CTCol> getColList();

    @Deprecated
    public CTCol[] getColArray();

    public CTCol getColArray(int var1);

    public int sizeOfColArray();

    public void setColArray(CTCol[] var1);

    public void setColArray(int var1, CTCol var2);

    public CTCol insertNewCol(int var1);

    public CTCol addNewCol();

    public void removeCol(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCols.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCols newInstance() {
            return (CTCols)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCols newInstance(XmlOptions xmlOptions) {
            return (CTCols)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCols parse(String string) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCols parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCols parse(File file) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCols parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCols parse(URL uRL) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCols parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCols parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCols parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCols parse(Reader reader) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCols parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCols)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCols parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCols parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCols parse(Node node) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCols parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCols)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCols parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCols)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCols parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCols)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

