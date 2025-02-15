/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STRotX
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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRotX;
import org.w3c.dom.Node;

public interface CTRotX
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRotX.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrotx3c3btype");

    public byte getVal();

    public STRotX xgetVal();

    public boolean isSetVal();

    public void setVal(byte var1);

    public void xsetVal(STRotX var1);

    public void unsetVal();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRotX.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRotX newInstance() {
            return (CTRotX)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRotX newInstance(XmlOptions xmlOptions) {
            return (CTRotX)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRotX parse(String string) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRotX parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRotX parse(File file) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRotX parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRotX parse(URL uRL) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRotX parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRotX parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRotX parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRotX parse(Reader reader) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRotX parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRotX)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRotX parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRotX parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRotX parse(Node node) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRotX parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRotX)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRotX parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRotX)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRotX parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRotX)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

