/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.w3c.dom.Node;

public interface CTAutoFilter
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAutoFilter.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctautofiltera8d0type");

    public List<CTFilterColumn> getFilterColumnList();

    @Deprecated
    public CTFilterColumn[] getFilterColumnArray();

    public CTFilterColumn getFilterColumnArray(int var1);

    public int sizeOfFilterColumnArray();

    public void setFilterColumnArray(CTFilterColumn[] var1);

    public void setFilterColumnArray(int var1, CTFilterColumn var2);

    public CTFilterColumn insertNewFilterColumn(int var1);

    public CTFilterColumn addNewFilterColumn();

    public void removeFilterColumn(int var1);

    public CTSortState getSortState();

    public boolean isSetSortState();

    public void setSortState(CTSortState var1);

    public CTSortState addNewSortState();

    public void unsetSortState();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getRef();

    public STRef xgetRef();

    public boolean isSetRef();

    public void setRef(String var1);

    public void xsetRef(STRef var1);

    public void unsetRef();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAutoFilter.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAutoFilter newInstance() {
            return (CTAutoFilter)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAutoFilter newInstance(XmlOptions xmlOptions) {
            return (CTAutoFilter)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAutoFilter parse(String string) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAutoFilter parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAutoFilter parse(File file) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAutoFilter parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAutoFilter parse(URL uRL) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAutoFilter parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAutoFilter parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAutoFilter parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAutoFilter parse(Reader reader) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAutoFilter parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAutoFilter parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAutoFilter parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAutoFilter parse(Node node) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAutoFilter parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAutoFilter parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAutoFilter parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAutoFilter)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

