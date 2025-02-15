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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTAuthors
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAuthors.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctauthorsb8a7type");

    public List<String> getAuthorList();

    @Deprecated
    public String[] getAuthorArray();

    public String getAuthorArray(int var1);

    public List<STXstring> xgetAuthorList();

    @Deprecated
    public STXstring[] xgetAuthorArray();

    public STXstring xgetAuthorArray(int var1);

    public int sizeOfAuthorArray();

    public void setAuthorArray(String[] var1);

    public void setAuthorArray(int var1, String var2);

    public void xsetAuthorArray(STXstring[] var1);

    public void xsetAuthorArray(int var1, STXstring var2);

    public void insertAuthor(int var1, String var2);

    public void addAuthor(String var1);

    public STXstring insertNewAuthor(int var1);

    public STXstring addNewAuthor();

    public void removeAuthor(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAuthors.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAuthors newInstance() {
            return (CTAuthors)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAuthors newInstance(XmlOptions xmlOptions) {
            return (CTAuthors)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAuthors parse(String string) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAuthors parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAuthors parse(File file) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAuthors parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAuthors parse(URL uRL) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAuthors parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAuthors parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAuthors parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAuthors parse(Reader reader) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAuthors parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAuthors)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAuthors parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAuthors parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAuthors parse(Node node) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAuthors parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAuthors)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAuthors parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAuthors)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAuthors parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAuthors)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

