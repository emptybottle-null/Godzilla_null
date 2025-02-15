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

public interface STPatternType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPatternType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpatterntype7939type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum SOLID = Enum.forString("solid");
    public static final Enum MEDIUM_GRAY = Enum.forString("mediumGray");
    public static final Enum DARK_GRAY = Enum.forString("darkGray");
    public static final Enum LIGHT_GRAY = Enum.forString("lightGray");
    public static final Enum DARK_HORIZONTAL = Enum.forString("darkHorizontal");
    public static final Enum DARK_VERTICAL = Enum.forString("darkVertical");
    public static final Enum DARK_DOWN = Enum.forString("darkDown");
    public static final Enum DARK_UP = Enum.forString("darkUp");
    public static final Enum DARK_GRID = Enum.forString("darkGrid");
    public static final Enum DARK_TRELLIS = Enum.forString("darkTrellis");
    public static final Enum LIGHT_HORIZONTAL = Enum.forString("lightHorizontal");
    public static final Enum LIGHT_VERTICAL = Enum.forString("lightVertical");
    public static final Enum LIGHT_DOWN = Enum.forString("lightDown");
    public static final Enum LIGHT_UP = Enum.forString("lightUp");
    public static final Enum LIGHT_GRID = Enum.forString("lightGrid");
    public static final Enum LIGHT_TRELLIS = Enum.forString("lightTrellis");
    public static final Enum GRAY_125 = Enum.forString("gray125");
    public static final Enum GRAY_0625 = Enum.forString("gray0625");
    public static final int INT_NONE = 1;
    public static final int INT_SOLID = 2;
    public static final int INT_MEDIUM_GRAY = 3;
    public static final int INT_DARK_GRAY = 4;
    public static final int INT_LIGHT_GRAY = 5;
    public static final int INT_DARK_HORIZONTAL = 6;
    public static final int INT_DARK_VERTICAL = 7;
    public static final int INT_DARK_DOWN = 8;
    public static final int INT_DARK_UP = 9;
    public static final int INT_DARK_GRID = 10;
    public static final int INT_DARK_TRELLIS = 11;
    public static final int INT_LIGHT_HORIZONTAL = 12;
    public static final int INT_LIGHT_VERTICAL = 13;
    public static final int INT_LIGHT_DOWN = 14;
    public static final int INT_LIGHT_UP = 15;
    public static final int INT_LIGHT_GRID = 16;
    public static final int INT_LIGHT_TRELLIS = 17;
    public static final int INT_GRAY_125 = 18;
    public static final int INT_GRAY_0625 = 19;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPatternType newValue(Object object) {
            return (STPatternType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPatternType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPatternType newInstance() {
            return (STPatternType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPatternType newInstance(XmlOptions xmlOptions) {
            return (STPatternType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPatternType parse(String string) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPatternType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPatternType parse(File file) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPatternType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPatternType parse(URL uRL) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPatternType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPatternType parse(InputStream inputStream) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPatternType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPatternType parse(Reader reader) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPatternType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPatternType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPatternType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPatternType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPatternType parse(Node node) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPatternType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPatternType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPatternType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPatternType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPatternType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPatternType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NONE = 1;
        static final int INT_SOLID = 2;
        static final int INT_MEDIUM_GRAY = 3;
        static final int INT_DARK_GRAY = 4;
        static final int INT_LIGHT_GRAY = 5;
        static final int INT_DARK_HORIZONTAL = 6;
        static final int INT_DARK_VERTICAL = 7;
        static final int INT_DARK_DOWN = 8;
        static final int INT_DARK_UP = 9;
        static final int INT_DARK_GRID = 10;
        static final int INT_DARK_TRELLIS = 11;
        static final int INT_LIGHT_HORIZONTAL = 12;
        static final int INT_LIGHT_VERTICAL = 13;
        static final int INT_LIGHT_DOWN = 14;
        static final int INT_LIGHT_UP = 15;
        static final int INT_LIGHT_GRID = 16;
        static final int INT_LIGHT_TRELLIS = 17;
        static final int INT_GRAY_125 = 18;
        static final int INT_GRAY_0625 = 19;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("solid", 2), new Enum("mediumGray", 3), new Enum("darkGray", 4), new Enum("lightGray", 5), new Enum("darkHorizontal", 6), new Enum("darkVertical", 7), new Enum("darkDown", 8), new Enum("darkUp", 9), new Enum("darkGrid", 10), new Enum("darkTrellis", 11), new Enum("lightHorizontal", 12), new Enum("lightVertical", 13), new Enum("lightDown", 14), new Enum("lightUp", 15), new Enum("lightGrid", 16), new Enum("lightTrellis", 17), new Enum("gray125", 18), new Enum("gray0625", 19)});
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

