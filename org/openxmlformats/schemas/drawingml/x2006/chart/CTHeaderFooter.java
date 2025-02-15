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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.STXstring;
import org.w3c.dom.Node;

public interface CTHeaderFooter
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHeaderFooter.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctheaderfooter2c34type");

    public String getOddHeader();

    public STXstring xgetOddHeader();

    public boolean isSetOddHeader();

    public void setOddHeader(String var1);

    public void xsetOddHeader(STXstring var1);

    public void unsetOddHeader();

    public String getOddFooter();

    public STXstring xgetOddFooter();

    public boolean isSetOddFooter();

    public void setOddFooter(String var1);

    public void xsetOddFooter(STXstring var1);

    public void unsetOddFooter();

    public String getEvenHeader();

    public STXstring xgetEvenHeader();

    public boolean isSetEvenHeader();

    public void setEvenHeader(String var1);

    public void xsetEvenHeader(STXstring var1);

    public void unsetEvenHeader();

    public String getEvenFooter();

    public STXstring xgetEvenFooter();

    public boolean isSetEvenFooter();

    public void setEvenFooter(String var1);

    public void xsetEvenFooter(STXstring var1);

    public void unsetEvenFooter();

    public String getFirstHeader();

    public STXstring xgetFirstHeader();

    public boolean isSetFirstHeader();

    public void setFirstHeader(String var1);

    public void xsetFirstHeader(STXstring var1);

    public void unsetFirstHeader();

    public String getFirstFooter();

    public STXstring xgetFirstFooter();

    public boolean isSetFirstFooter();

    public void setFirstFooter(String var1);

    public void xsetFirstFooter(STXstring var1);

    public void unsetFirstFooter();

    public boolean getAlignWithMargins();

    public XmlBoolean xgetAlignWithMargins();

    public boolean isSetAlignWithMargins();

    public void setAlignWithMargins(boolean var1);

    public void xsetAlignWithMargins(XmlBoolean var1);

    public void unsetAlignWithMargins();

    public boolean getDifferentOddEven();

    public XmlBoolean xgetDifferentOddEven();

    public boolean isSetDifferentOddEven();

    public void setDifferentOddEven(boolean var1);

    public void xsetDifferentOddEven(XmlBoolean var1);

    public void unsetDifferentOddEven();

    public boolean getDifferentFirst();

    public XmlBoolean xgetDifferentFirst();

    public boolean isSetDifferentFirst();

    public void setDifferentFirst(boolean var1);

    public void xsetDifferentFirst(XmlBoolean var1);

    public void unsetDifferentFirst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHeaderFooter.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHeaderFooter newInstance() {
            return (CTHeaderFooter)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHeaderFooter newInstance(XmlOptions xmlOptions) {
            return (CTHeaderFooter)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHeaderFooter parse(String string) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHeaderFooter parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHeaderFooter parse(File file) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHeaderFooter parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHeaderFooter parse(URL uRL) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHeaderFooter parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHeaderFooter parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHeaderFooter parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHeaderFooter parse(Reader reader) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHeaderFooter parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHeaderFooter parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHeaderFooter parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHeaderFooter parse(Node node) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHeaderFooter parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHeaderFooter parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHeaderFooter parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHeaderFooter)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

