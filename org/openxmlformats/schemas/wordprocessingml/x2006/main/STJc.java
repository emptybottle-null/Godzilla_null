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

public interface STJc
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STJc.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stjc977ftype");
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum BOTH = Enum.forString("both");
    public static final Enum MEDIUM_KASHIDA = Enum.forString("mediumKashida");
    public static final Enum DISTRIBUTE = Enum.forString("distribute");
    public static final Enum NUM_TAB = Enum.forString("numTab");
    public static final Enum HIGH_KASHIDA = Enum.forString("highKashida");
    public static final Enum LOW_KASHIDA = Enum.forString("lowKashida");
    public static final Enum THAI_DISTRIBUTE = Enum.forString("thaiDistribute");
    public static final int INT_LEFT = 1;
    public static final int INT_CENTER = 2;
    public static final int INT_RIGHT = 3;
    public static final int INT_BOTH = 4;
    public static final int INT_MEDIUM_KASHIDA = 5;
    public static final int INT_DISTRIBUTE = 6;
    public static final int INT_NUM_TAB = 7;
    public static final int INT_HIGH_KASHIDA = 8;
    public static final int INT_LOW_KASHIDA = 9;
    public static final int INT_THAI_DISTRIBUTE = 10;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STJc newValue(Object object) {
            return (STJc)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STJc.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STJc newInstance() {
            return (STJc)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STJc newInstance(XmlOptions xmlOptions) {
            return (STJc)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STJc parse(String string) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STJc parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STJc parse(File file) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STJc parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STJc parse(URL uRL) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STJc parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STJc parse(InputStream inputStream) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STJc parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STJc parse(Reader reader) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STJc parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STJc)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STJc parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STJc parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STJc parse(Node node) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STJc parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STJc)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STJc parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STJc)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STJc parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STJc)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_LEFT = 1;
        static final int INT_CENTER = 2;
        static final int INT_RIGHT = 3;
        static final int INT_BOTH = 4;
        static final int INT_MEDIUM_KASHIDA = 5;
        static final int INT_DISTRIBUTE = 6;
        static final int INT_NUM_TAB = 7;
        static final int INT_HIGH_KASHIDA = 8;
        static final int INT_LOW_KASHIDA = 9;
        static final int INT_THAI_DISTRIBUTE = 10;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("left", 1), new Enum("center", 2), new Enum("right", 3), new Enum("both", 4), new Enum("mediumKashida", 5), new Enum("distribute", 6), new Enum("numTab", 7), new Enum("highKashida", 8), new Enum("lowKashida", 9), new Enum("thaiDistribute", 10)});
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

