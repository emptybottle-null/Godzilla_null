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

public interface STDataConsolidateFunction
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STDataConsolidateFunction.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stdataconsolidatefunction1206type");
    public static final Enum AVERAGE = Enum.forString("average");
    public static final Enum COUNT = Enum.forString("count");
    public static final Enum COUNT_NUMS = Enum.forString("countNums");
    public static final Enum MAX = Enum.forString("max");
    public static final Enum MIN = Enum.forString("min");
    public static final Enum PRODUCT = Enum.forString("product");
    public static final Enum STD_DEV = Enum.forString("stdDev");
    public static final Enum STD_DEVP = Enum.forString("stdDevp");
    public static final Enum SUM = Enum.forString("sum");
    public static final Enum VAR = Enum.forString("var");
    public static final Enum VARP = Enum.forString("varp");
    public static final int INT_AVERAGE = 1;
    public static final int INT_COUNT = 2;
    public static final int INT_COUNT_NUMS = 3;
    public static final int INT_MAX = 4;
    public static final int INT_MIN = 5;
    public static final int INT_PRODUCT = 6;
    public static final int INT_STD_DEV = 7;
    public static final int INT_STD_DEVP = 8;
    public static final int INT_SUM = 9;
    public static final int INT_VAR = 10;
    public static final int INT_VARP = 11;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STDataConsolidateFunction newValue(Object object) {
            return (STDataConsolidateFunction)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STDataConsolidateFunction.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STDataConsolidateFunction newInstance() {
            return (STDataConsolidateFunction)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STDataConsolidateFunction newInstance(XmlOptions xmlOptions) {
            return (STDataConsolidateFunction)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(String string) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STDataConsolidateFunction parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(File file) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STDataConsolidateFunction parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(URL uRL) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STDataConsolidateFunction parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(InputStream inputStream) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STDataConsolidateFunction parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(Reader reader) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STDataConsolidateFunction parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STDataConsolidateFunction parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STDataConsolidateFunction parse(Node node) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STDataConsolidateFunction parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STDataConsolidateFunction parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STDataConsolidateFunction parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STDataConsolidateFunction)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_AVERAGE = 1;
        static final int INT_COUNT = 2;
        static final int INT_COUNT_NUMS = 3;
        static final int INT_MAX = 4;
        static final int INT_MIN = 5;
        static final int INT_PRODUCT = 6;
        static final int INT_STD_DEV = 7;
        static final int INT_STD_DEVP = 8;
        static final int INT_SUM = 9;
        static final int INT_VAR = 10;
        static final int INT_VARP = 11;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("average", 1), new Enum("count", 2), new Enum("countNums", 3), new Enum("max", 4), new Enum("min", 5), new Enum("product", 6), new Enum("stdDev", 7), new Enum("stdDevp", 8), new Enum("sum", 9), new Enum("var", 10), new Enum("varp", 11)});
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

