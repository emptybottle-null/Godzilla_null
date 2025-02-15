/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.w3c.dom.Node;

public interface CTSdtEndPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSdtEndPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsdtendprbc6etype");

    public List<CTRPr> getRPrList();

    @Deprecated
    public CTRPr[] getRPrArray();

    public CTRPr getRPrArray(int var1);

    public int sizeOfRPrArray();

    public void setRPrArray(CTRPr[] var1);

    public void setRPrArray(int var1, CTRPr var2);

    public CTRPr insertNewRPr(int var1);

    public CTRPr addNewRPr();

    public void removeRPr(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSdtEndPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSdtEndPr newInstance() {
            return (CTSdtEndPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSdtEndPr newInstance(XmlOptions xmlOptions) {
            return (CTSdtEndPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSdtEndPr parse(String string) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSdtEndPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSdtEndPr parse(File file) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSdtEndPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSdtEndPr parse(URL uRL) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSdtEndPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSdtEndPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSdtEndPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSdtEndPr parse(Reader reader) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSdtEndPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSdtEndPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSdtEndPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSdtEndPr parse(Node node) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSdtEndPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSdtEndPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSdtEndPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSdtEndPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

