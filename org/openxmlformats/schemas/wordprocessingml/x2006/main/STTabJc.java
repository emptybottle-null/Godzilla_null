/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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

public interface STTabJc
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTabJc.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttabjc10f4type");
    public static final Enum CLEAR = Enum.forString("clear");
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum DECIMAL = Enum.forString("decimal");
    public static final Enum BAR = Enum.forString("bar");
    public static final Enum NUM = Enum.forString("num");
    public static final int INT_CLEAR = 1;
    public static final int INT_LEFT = 2;
    public static final int INT_CENTER = 3;
    public static final int INT_RIGHT = 4;
    public static final int INT_DECIMAL = 5;
    public static final int INT_BAR = 6;
    public static final int INT_NUM = 7;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTabJc newValue(Object object) {
            return (STTabJc)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTabJc.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTabJc newInstance() {
            return (STTabJc)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTabJc newInstance(XmlOptions xmlOptions) {
            return (STTabJc)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTabJc parse(String string) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTabJc parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTabJc parse(File file) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTabJc parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTabJc parse(URL uRL) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTabJc parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTabJc parse(InputStream inputStream) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTabJc parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTabJc parse(Reader reader) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTabJc parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabJc)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTabJc parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTabJc parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTabJc parse(Node node) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTabJc parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTabJc)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTabJc parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTabJc)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTabJc parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTabJc)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_CLEAR = 1;
        static final int INT_LEFT = 2;
        static final int INT_CENTER = 3;
        static final int INT_RIGHT = 4;
        static final int INT_DECIMAL = 5;
        static final int INT_BAR = 6;
        static final int INT_NUM = 7;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("clear", 1), new Enum("left", 2), new Enum("center", 3), new Enum("right", 4), new Enum("decimal", 5), new Enum("bar", 6), new Enum("num", 7)});
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

