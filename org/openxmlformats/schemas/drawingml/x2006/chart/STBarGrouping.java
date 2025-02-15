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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STBarGrouping
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STBarGrouping.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stbargrouping8400type");
    public static final Enum PERCENT_STACKED = Enum.forString("percentStacked");
    public static final Enum CLUSTERED = Enum.forString("clustered");
    public static final Enum STANDARD = Enum.forString("standard");
    public static final Enum STACKED = Enum.forString("stacked");
    public static final int INT_PERCENT_STACKED = 1;
    public static final int INT_CLUSTERED = 2;
    public static final int INT_STANDARD = 3;
    public static final int INT_STACKED = 4;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STBarGrouping newValue(Object object) {
            return (STBarGrouping)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STBarGrouping.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STBarGrouping newInstance() {
            return (STBarGrouping)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STBarGrouping newInstance(XmlOptions xmlOptions) {
            return (STBarGrouping)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STBarGrouping parse(String string) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STBarGrouping parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STBarGrouping parse(File file) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STBarGrouping parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STBarGrouping parse(URL uRL) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STBarGrouping parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STBarGrouping parse(InputStream inputStream) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STBarGrouping parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STBarGrouping parse(Reader reader) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STBarGrouping parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBarGrouping)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STBarGrouping parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STBarGrouping parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STBarGrouping parse(Node node) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STBarGrouping parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STBarGrouping)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STBarGrouping parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STBarGrouping)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STBarGrouping parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STBarGrouping)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

    public static final class Enum
    extends StringEnumAbstractBase {
        static final int INT_PERCENT_STACKED = 1;
        static final int INT_CLUSTERED = 2;
        static final int INT_STANDARD = 3;
        static final int INT_STACKED = 4;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("percentStacked", 1), new Enum("clustered", 2), new Enum("standard", 3), new Enum("stacked", 4)});
        private static final long serialVersionUID = 1L;

        public static Enum forString(String string) {
            return (Enum)table.forString(string);
        }

        public static Enum forInt(int n) {
            return (Enum)table.forInt(n);
        }

        private Enum(String string, int n) {
            super(string, n);
        }

        private Object readResolve() {
            return Enum.forInt(this.intValue());
        }
    }
}

