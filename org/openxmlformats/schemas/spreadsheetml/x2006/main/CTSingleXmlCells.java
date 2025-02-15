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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.w3c.dom.Node;

public interface CTSingleXmlCells
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSingleXmlCells.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsinglexmlcells5a6btype");

    public List<CTSingleXmlCell> getSingleXmlCellList();

    @Deprecated
    public CTSingleXmlCell[] getSingleXmlCellArray();

    public CTSingleXmlCell getSingleXmlCellArray(int var1);

    public int sizeOfSingleXmlCellArray();

    public void setSingleXmlCellArray(CTSingleXmlCell[] var1);

    public void setSingleXmlCellArray(int var1, CTSingleXmlCell var2);

    public CTSingleXmlCell insertNewSingleXmlCell(int var1);

    public CTSingleXmlCell addNewSingleXmlCell();

    public void removeSingleXmlCell(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSingleXmlCells.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSingleXmlCells newInstance() {
            return (CTSingleXmlCells)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSingleXmlCells newInstance(XmlOptions xmlOptions) {
            return (CTSingleXmlCells)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSingleXmlCells parse(String string) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSingleXmlCells parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(File file) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSingleXmlCells parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(URL uRL) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSingleXmlCells parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSingleXmlCells parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(Reader reader) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSingleXmlCells parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSingleXmlCells parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSingleXmlCells parse(Node node) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSingleXmlCells parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSingleXmlCells parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSingleXmlCells parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSingleXmlCells)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

