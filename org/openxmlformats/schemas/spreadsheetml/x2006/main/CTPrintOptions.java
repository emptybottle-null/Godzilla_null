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
import org.w3c.dom.Node;

public interface CTPrintOptions
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPrintOptions.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctprintoptions943atype");

    public boolean getHorizontalCentered();

    public XmlBoolean xgetHorizontalCentered();

    public boolean isSetHorizontalCentered();

    public void setHorizontalCentered(boolean var1);

    public void xsetHorizontalCentered(XmlBoolean var1);

    public void unsetHorizontalCentered();

    public boolean getVerticalCentered();

    public XmlBoolean xgetVerticalCentered();

    public boolean isSetVerticalCentered();

    public void setVerticalCentered(boolean var1);

    public void xsetVerticalCentered(XmlBoolean var1);

    public void unsetVerticalCentered();

    public boolean getHeadings();

    public XmlBoolean xgetHeadings();

    public boolean isSetHeadings();

    public void setHeadings(boolean var1);

    public void xsetHeadings(XmlBoolean var1);

    public void unsetHeadings();

    public boolean getGridLines();

    public XmlBoolean xgetGridLines();

    public boolean isSetGridLines();

    public void setGridLines(boolean var1);

    public void xsetGridLines(XmlBoolean var1);

    public void unsetGridLines();

    public boolean getGridLinesSet();

    public XmlBoolean xgetGridLinesSet();

    public boolean isSetGridLinesSet();

    public void setGridLinesSet(boolean var1);

    public void xsetGridLinesSet(XmlBoolean var1);

    public void unsetGridLinesSet();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPrintOptions.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPrintOptions newInstance() {
            return (CTPrintOptions)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPrintOptions newInstance(XmlOptions xmlOptions) {
            return (CTPrintOptions)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPrintOptions parse(String string) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPrintOptions parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPrintOptions parse(File file) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPrintOptions parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPrintOptions parse(URL uRL) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPrintOptions parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPrintOptions parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPrintOptions parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPrintOptions parse(Reader reader) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPrintOptions parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPrintOptions parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPrintOptions parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPrintOptions parse(Node node) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPrintOptions parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPrintOptions parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPrintOptions parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPrintOptions)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

