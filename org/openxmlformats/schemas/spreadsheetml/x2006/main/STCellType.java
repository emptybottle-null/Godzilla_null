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

public interface STCellType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STCellType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stcelltypebf95type");
    public static final Enum B = Enum.forString("b");
    public static final Enum N = Enum.forString("n");
    public static final Enum E = Enum.forString("e");
    public static final Enum S = Enum.forString("s");
    public static final Enum STR = Enum.forString("str");
    public static final Enum INLINE_STR = Enum.forString("inlineStr");
    public static final int INT_B = 1;
    public static final int INT_N = 2;
    public static final int INT_E = 3;
    public static final int INT_S = 4;
    public static final int INT_STR = 5;
    public static final int INT_INLINE_STR = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STCellType newValue(Object object) {
            return (STCellType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STCellType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STCellType newInstance() {
            return (STCellType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STCellType newInstance(XmlOptions xmlOptions) {
            return (STCellType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STCellType parse(String string) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STCellType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STCellType parse(File file) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STCellType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STCellType parse(URL uRL) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STCellType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STCellType parse(InputStream inputStream) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STCellType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STCellType parse(Reader reader) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STCellType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCellType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STCellType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STCellType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STCellType parse(Node node) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STCellType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STCellType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STCellType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STCellType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STCellType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STCellType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_B = 1;
        static final int INT_N = 2;
        static final int INT_E = 3;
        static final int INT_S = 4;
        static final int INT_STR = 5;
        static final int INT_INLINE_STR = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("b", 1), new Enum("n", 2), new Enum("e", 3), new Enum("s", 4), new Enum("str", 5), new Enum("inlineStr", 6)});
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

