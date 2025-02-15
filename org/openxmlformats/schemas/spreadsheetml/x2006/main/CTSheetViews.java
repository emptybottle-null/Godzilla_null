/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.w3c.dom.Node;

public interface CTSheetViews
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetViews.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetviewsb918type");

    public List<CTSheetView> getSheetViewList();

    @Deprecated
    public CTSheetView[] getSheetViewArray();

    public CTSheetView getSheetViewArray(int var1);

    public int sizeOfSheetViewArray();

    public void setSheetViewArray(CTSheetView[] var1);

    public void setSheetViewArray(int var1, CTSheetView var2);

    public CTSheetView insertNewSheetView(int var1);

    public CTSheetView addNewSheetView();

    public void removeSheetView(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetViews.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetViews newInstance() {
            return (CTSheetViews)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetViews newInstance(XmlOptions xmlOptions) {
            return (CTSheetViews)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetViews parse(String string) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetViews parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetViews parse(File file) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetViews parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetViews parse(URL uRL) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetViews parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetViews parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetViews parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetViews parse(Reader reader) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetViews parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetViews)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetViews parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetViews parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetViews parse(Node node) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetViews parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetViews)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetViews parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetViews)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetViews parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetViews)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

