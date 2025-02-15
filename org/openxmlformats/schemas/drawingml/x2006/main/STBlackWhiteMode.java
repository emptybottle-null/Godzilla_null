/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STBlackWhiteMode
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STBlackWhiteMode.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stblackwhitemode0558type");
    public static final Enum CLR = Enum.forString("clr");
    public static final Enum AUTO = Enum.forString("auto");
    public static final Enum GRAY = Enum.forString("gray");
    public static final Enum LT_GRAY = Enum.forString("ltGray");
    public static final Enum INV_GRAY = Enum.forString("invGray");
    public static final Enum GRAY_WHITE = Enum.forString("grayWhite");
    public static final Enum BLACK_GRAY = Enum.forString("blackGray");
    public static final Enum BLACK_WHITE = Enum.forString("blackWhite");
    public static final Enum BLACK = Enum.forString("black");
    public static final Enum WHITE = Enum.forString("white");
    public static final Enum HIDDEN = Enum.forString("hidden");
    public static final int INT_CLR = 1;
    public static final int INT_AUTO = 2;
    public static final int INT_GRAY = 3;
    public static final int INT_LT_GRAY = 4;
    public static final int INT_INV_GRAY = 5;
    public static final int INT_GRAY_WHITE = 6;
    public static final int INT_BLACK_GRAY = 7;
    public static final int INT_BLACK_WHITE = 8;
    public static final int INT_BLACK = 9;
    public static final int INT_WHITE = 10;
    public static final int INT_HIDDEN = 11;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STBlackWhiteMode newValue(Object object) {
            return (STBlackWhiteMode)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STBlackWhiteMode.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STBlackWhiteMode newInstance() {
            return (STBlackWhiteMode)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STBlackWhiteMode newInstance(XmlOptions xmlOptions) {
            return (STBlackWhiteMode)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STBlackWhiteMode parse(String string) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STBlackWhiteMode parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(File file) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STBlackWhiteMode parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(URL uRL) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STBlackWhiteMode parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(InputStream inputStream) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STBlackWhiteMode parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(Reader reader) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STBlackWhiteMode parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STBlackWhiteMode parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STBlackWhiteMode parse(Node node) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STBlackWhiteMode parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STBlackWhiteMode parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STBlackWhiteMode parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STBlackWhiteMode)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_CLR = 1;
        static final int INT_AUTO = 2;
        static final int INT_GRAY = 3;
        static final int INT_LT_GRAY = 4;
        static final int INT_INV_GRAY = 5;
        static final int INT_GRAY_WHITE = 6;
        static final int INT_BLACK_GRAY = 7;
        static final int INT_BLACK_WHITE = 8;
        static final int INT_BLACK = 9;
        static final int INT_WHITE = 10;
        static final int INT_HIDDEN = 11;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("clr", 1), new Enum("auto", 2), new Enum("gray", 3), new Enum("ltGray", 4), new Enum("invGray", 5), new Enum("grayWhite", 6), new Enum("blackGray", 7), new Enum("blackWhite", 8), new Enum("black", 9), new Enum("white", 10), new Enum("hidden", 11)});
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

