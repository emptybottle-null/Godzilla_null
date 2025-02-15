/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet
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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;
import org.w3c.dom.Node;

public interface CTExternalBook
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTExternalBook.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctexternalbookc89dtype");

    public CTExternalSheetNames getSheetNames();

    public boolean isSetSheetNames();

    public void setSheetNames(CTExternalSheetNames var1);

    public CTExternalSheetNames addNewSheetNames();

    public void unsetSheetNames();

    public CTExternalDefinedNames getDefinedNames();

    public boolean isSetDefinedNames();

    public void setDefinedNames(CTExternalDefinedNames var1);

    public CTExternalDefinedNames addNewDefinedNames();

    public void unsetDefinedNames();

    public CTExternalSheetDataSet getSheetDataSet();

    public boolean isSetSheetDataSet();

    public void setSheetDataSet(CTExternalSheetDataSet var1);

    public CTExternalSheetDataSet addNewSheetDataSet();

    public void unsetSheetDataSet();

    public String getId();

    public STRelationshipId xgetId();

    public void setId(String var1);

    public void xsetId(STRelationshipId var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTExternalBook.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTExternalBook newInstance() {
            return (CTExternalBook)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTExternalBook newInstance(XmlOptions xmlOptions) {
            return (CTExternalBook)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTExternalBook parse(String string) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTExternalBook parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTExternalBook parse(File file) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTExternalBook parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTExternalBook parse(URL uRL) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTExternalBook parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTExternalBook parse(InputStream inputStream) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTExternalBook parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTExternalBook parse(Reader reader) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTExternalBook parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalBook)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTExternalBook parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTExternalBook parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTExternalBook parse(Node node) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTExternalBook parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalBook)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTExternalBook parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTExternalBook)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTExternalBook parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTExternalBook)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

