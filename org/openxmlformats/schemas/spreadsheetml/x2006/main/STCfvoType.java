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

public interface STCfvoType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STCfvoType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stcfvotypeeb0ftype");
    public static final Enum NUM = Enum.forString("num");
    public static final Enum PERCENT = Enum.forString("percent");
    public static final Enum MAX = Enum.forString("max");
    public static final Enum MIN = Enum.forString("min");
    public static final Enum FORMULA = Enum.forString("formula");
    public static final Enum PERCENTILE = Enum.forString("percentile");
    public static final int INT_NUM = 1;
    public static final int INT_PERCENT = 2;
    public static final int INT_MAX = 3;
    public static final int INT_MIN = 4;
    public static final int INT_FORMULA = 5;
    public static final int INT_PERCENTILE = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STCfvoType newValue(Object object) {
            return (STCfvoType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STCfvoType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STCfvoType newInstance() {
            return (STCfvoType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STCfvoType newInstance(XmlOptions xmlOptions) {
            return (STCfvoType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STCfvoType parse(String string) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STCfvoType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STCfvoType parse(File file) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STCfvoType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STCfvoType parse(URL uRL) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STCfvoType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STCfvoType parse(InputStream inputStream) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STCfvoType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STCfvoType parse(Reader reader) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STCfvoType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCfvoType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STCfvoType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STCfvoType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STCfvoType parse(Node node) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STCfvoType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STCfvoType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STCfvoType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STCfvoType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STCfvoType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STCfvoType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NUM = 1;
        static final int INT_PERCENT = 2;
        static final int INT_MAX = 3;
        static final int INT_MIN = 4;
        static final int INT_FORMULA = 5;
        static final int INT_PERCENTILE = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("num", 1), new Enum("percent", 2), new Enum("max", 3), new Enum("min", 4), new Enum("formula", 5), new Enum("percentile", 6)});
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

