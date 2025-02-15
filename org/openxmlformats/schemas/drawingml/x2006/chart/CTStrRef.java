/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.w3c.dom.Node;

public interface CTStrRef
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStrRef.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstrref5d1atype");

    public String getF();

    public XmlString xgetF();

    public void setF(String var1);

    public void xsetF(XmlString var1);

    public CTStrData getStrCache();

    public boolean isSetStrCache();

    public void setStrCache(CTStrData var1);

    public CTStrData addNewStrCache();

    public void unsetStrCache();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStrRef.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStrRef newInstance() {
            return (CTStrRef)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStrRef newInstance(XmlOptions xmlOptions) {
            return (CTStrRef)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStrRef parse(String string) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStrRef parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStrRef parse(File file) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStrRef parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStrRef parse(URL uRL) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStrRef parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStrRef parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStrRef parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStrRef parse(Reader reader) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStrRef parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrRef)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStrRef parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStrRef parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStrRef parse(Node node) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStrRef parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStrRef)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStrRef parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStrRef)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStrRef parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStrRef)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

