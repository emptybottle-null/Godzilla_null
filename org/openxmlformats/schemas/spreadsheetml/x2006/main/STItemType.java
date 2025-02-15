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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STItemType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STItemType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stitemtype6186type");
    public static final Enum DATA = Enum.forString("data");
    public static final Enum DEFAULT = Enum.forString("default");
    public static final Enum SUM = Enum.forString("sum");
    public static final Enum COUNT_A = Enum.forString("countA");
    public static final Enum AVG = Enum.forString("avg");
    public static final Enum MAX = Enum.forString("max");
    public static final Enum MIN = Enum.forString("min");
    public static final Enum PRODUCT = Enum.forString("product");
    public static final Enum COUNT = Enum.forString("count");
    public static final Enum STD_DEV = Enum.forString("stdDev");
    public static final Enum STD_DEV_P = Enum.forString("stdDevP");
    public static final Enum VAR = Enum.forString("var");
    public static final Enum VAR_P = Enum.forString("varP");
    public static final Enum GRAND = Enum.forString("grand");
    public static final Enum BLANK = Enum.forString("blank");
    public static final int INT_DATA = 1;
    public static final int INT_DEFAULT = 2;
    public static final int INT_SUM = 3;
    public static final int INT_COUNT_A = 4;
    public static final int INT_AVG = 5;
    public static final int INT_MAX = 6;
    public static final int INT_MIN = 7;
    public static final int INT_PRODUCT = 8;
    public static final int INT_COUNT = 9;
    public static final int INT_STD_DEV = 10;
    public static final int INT_STD_DEV_P = 11;
    public static final int INT_VAR = 12;
    public static final int INT_VAR_P = 13;
    public static final int INT_GRAND = 14;
    public static final int INT_BLANK = 15;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STItemType newValue(Object object) {
            return (STItemType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STItemType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STItemType newInstance() {
            return (STItemType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STItemType newInstance(XmlOptions xmlOptions) {
            return (STItemType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STItemType parse(String string) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STItemType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STItemType parse(File file) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STItemType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STItemType parse(URL uRL) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STItemType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STItemType parse(InputStream inputStream) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STItemType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STItemType parse(Reader reader) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STItemType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STItemType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STItemType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STItemType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STItemType parse(Node node) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STItemType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STItemType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STItemType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STItemType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STItemType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STItemType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DATA = 1;
        static final int INT_DEFAULT = 2;
        static final int INT_SUM = 3;
        static final int INT_COUNT_A = 4;
        static final int INT_AVG = 5;
        static final int INT_MAX = 6;
        static final int INT_MIN = 7;
        static final int INT_PRODUCT = 8;
        static final int INT_COUNT = 9;
        static final int INT_STD_DEV = 10;
        static final int INT_STD_DEV_P = 11;
        static final int INT_VAR = 12;
        static final int INT_VAR_P = 13;
        static final int INT_GRAND = 14;
        static final int INT_BLANK = 15;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("data", 1), new Enum("default", 2), new Enum("sum", 3), new Enum("countA", 4), new Enum("avg", 5), new Enum("max", 6), new Enum("min", 7), new Enum("product", 8), new Enum("count", 9), new Enum("stdDev", 10), new Enum("stdDevP", 11), new Enum("var", 12), new Enum("varP", 13), new Enum("grand", 14), new Enum("blank", 15)});
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

