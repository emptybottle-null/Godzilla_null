/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STOverlap
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
import org.openxmlformats.schemas.drawingml.x2006.chart.STOverlap;
import org.w3c.dom.Node;

public interface CTOverlap
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOverlap.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoverlapfd3ftype");

    public byte getVal();

    public STOverlap xgetVal();

    public boolean isSetVal();

    public void setVal(byte var1);

    public void xsetVal(STOverlap var1);

    public void unsetVal();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOverlap.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOverlap newInstance() {
            return (CTOverlap)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOverlap newInstance(XmlOptions xmlOptions) {
            return (CTOverlap)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOverlap parse(String string) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOverlap parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOverlap parse(File file) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOverlap parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOverlap parse(URL uRL) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOverlap parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOverlap parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOverlap parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOverlap parse(Reader reader) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOverlap parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOverlap)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOverlap parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOverlap parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOverlap parse(Node node) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOverlap parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOverlap)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOverlap parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOverlap)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOverlap parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOverlap)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

