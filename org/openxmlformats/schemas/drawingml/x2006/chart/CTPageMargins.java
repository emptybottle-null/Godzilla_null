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
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTPageMargins
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPageMargins.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpagemarginsb730type");

    public double getL();

    public XmlDouble xgetL();

    public void setL(double var1);

    public void xsetL(XmlDouble var1);

    public double getR();

    public XmlDouble xgetR();

    public void setR(double var1);

    public void xsetR(XmlDouble var1);

    public double getT();

    public XmlDouble xgetT();

    public void setT(double var1);

    public void xsetT(XmlDouble var1);

    public double getB();

    public XmlDouble xgetB();

    public void setB(double var1);

    public void xsetB(XmlDouble var1);

    public double getHeader();

    public XmlDouble xgetHeader();

    public void setHeader(double var1);

    public void xsetHeader(XmlDouble var1);

    public double getFooter();

    public XmlDouble xgetFooter();

    public void setFooter(double var1);

    public void xsetFooter(XmlDouble var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPageMargins.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPageMargins newInstance() {
            return (CTPageMargins)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPageMargins newInstance(XmlOptions xmlOptions) {
            return (CTPageMargins)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPageMargins parse(String string) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPageMargins parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPageMargins parse(File file) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPageMargins parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPageMargins parse(URL uRL) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPageMargins parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPageMargins parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPageMargins parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPageMargins parse(Reader reader) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPageMargins parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageMargins)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPageMargins parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPageMargins parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPageMargins parse(Node node) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPageMargins parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPageMargins)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPageMargins parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPageMargins)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPageMargins parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPageMargins)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

