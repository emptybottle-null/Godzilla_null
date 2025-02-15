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

public interface STThemeColor
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STThemeColor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stthemecolor063etype");
    public static final Enum DARK_1 = Enum.forString("dark1");
    public static final Enum LIGHT_1 = Enum.forString("light1");
    public static final Enum DARK_2 = Enum.forString("dark2");
    public static final Enum LIGHT_2 = Enum.forString("light2");
    public static final Enum ACCENT_1 = Enum.forString("accent1");
    public static final Enum ACCENT_2 = Enum.forString("accent2");
    public static final Enum ACCENT_3 = Enum.forString("accent3");
    public static final Enum ACCENT_4 = Enum.forString("accent4");
    public static final Enum ACCENT_5 = Enum.forString("accent5");
    public static final Enum ACCENT_6 = Enum.forString("accent6");
    public static final Enum HYPERLINK = Enum.forString("hyperlink");
    public static final Enum FOLLOWED_HYPERLINK = Enum.forString("followedHyperlink");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum BACKGROUND_1 = Enum.forString("background1");
    public static final Enum TEXT_1 = Enum.forString("text1");
    public static final Enum BACKGROUND_2 = Enum.forString("background2");
    public static final Enum TEXT_2 = Enum.forString("text2");
    public static final int INT_DARK_1 = 1;
    public static final int INT_LIGHT_1 = 2;
    public static final int INT_DARK_2 = 3;
    public static final int INT_LIGHT_2 = 4;
    public static final int INT_ACCENT_1 = 5;
    public static final int INT_ACCENT_2 = 6;
    public static final int INT_ACCENT_3 = 7;
    public static final int INT_ACCENT_4 = 8;
    public static final int INT_ACCENT_5 = 9;
    public static final int INT_ACCENT_6 = 10;
    public static final int INT_HYPERLINK = 11;
    public static final int INT_FOLLOWED_HYPERLINK = 12;
    public static final int INT_NONE = 13;
    public static final int INT_BACKGROUND_1 = 14;
    public static final int INT_TEXT_1 = 15;
    public static final int INT_BACKGROUND_2 = 16;
    public static final int INT_TEXT_2 = 17;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STThemeColor newValue(Object object) {
            return (STThemeColor)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STThemeColor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STThemeColor newInstance() {
            return (STThemeColor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STThemeColor newInstance(XmlOptions xmlOptions) {
            return (STThemeColor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STThemeColor parse(String string) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STThemeColor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STThemeColor parse(File file) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STThemeColor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STThemeColor parse(URL uRL) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STThemeColor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STThemeColor parse(InputStream inputStream) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STThemeColor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STThemeColor parse(Reader reader) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STThemeColor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STThemeColor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STThemeColor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STThemeColor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STThemeColor parse(Node node) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STThemeColor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STThemeColor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STThemeColor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STThemeColor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STThemeColor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STThemeColor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DARK_1 = 1;
        static final int INT_LIGHT_1 = 2;
        static final int INT_DARK_2 = 3;
        static final int INT_LIGHT_2 = 4;
        static final int INT_ACCENT_1 = 5;
        static final int INT_ACCENT_2 = 6;
        static final int INT_ACCENT_3 = 7;
        static final int INT_ACCENT_4 = 8;
        static final int INT_ACCENT_5 = 9;
        static final int INT_ACCENT_6 = 10;
        static final int INT_HYPERLINK = 11;
        static final int INT_FOLLOWED_HYPERLINK = 12;
        static final int INT_NONE = 13;
        static final int INT_BACKGROUND_1 = 14;
        static final int INT_TEXT_1 = 15;
        static final int INT_BACKGROUND_2 = 16;
        static final int INT_TEXT_2 = 17;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("dark1", 1), new Enum("light1", 2), new Enum("dark2", 3), new Enum("light2", 4), new Enum("accent1", 5), new Enum("accent2", 6), new Enum("accent3", 7), new Enum("accent4", 8), new Enum("accent5", 9), new Enum("accent6", 10), new Enum("hyperlink", 11), new Enum("followedHyperlink", 12), new Enum("none", 13), new Enum("background1", 14), new Enum("text1", 15), new Enum("background2", 16), new Enum("text2", 17)});
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

