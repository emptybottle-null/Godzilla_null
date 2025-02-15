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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.w3c.dom.Node;

public interface CTBookViews
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBookViews.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbookviewsb864type");

    public List<CTBookView> getWorkbookViewList();

    @Deprecated
    public CTBookView[] getWorkbookViewArray();

    public CTBookView getWorkbookViewArray(int var1);

    public int sizeOfWorkbookViewArray();

    public void setWorkbookViewArray(CTBookView[] var1);

    public void setWorkbookViewArray(int var1, CTBookView var2);

    public CTBookView insertNewWorkbookView(int var1);

    public CTBookView addNewWorkbookView();

    public void removeWorkbookView(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBookViews.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBookViews newInstance() {
            return (CTBookViews)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBookViews newInstance(XmlOptions xmlOptions) {
            return (CTBookViews)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBookViews parse(String string) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBookViews parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBookViews parse(File file) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBookViews parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBookViews parse(URL uRL) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBookViews parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBookViews parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBookViews parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBookViews parse(Reader reader) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBookViews parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookViews)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBookViews parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBookViews parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBookViews parse(Node node) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBookViews parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBookViews)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBookViews parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBookViews)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBookViews parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBookViews)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

