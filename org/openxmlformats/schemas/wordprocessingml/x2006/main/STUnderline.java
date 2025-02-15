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

public interface STUnderline
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STUnderline.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stunderlinef416type");
    public static final Enum SINGLE = Enum.forString("single");
    public static final Enum WORDS = Enum.forString("words");
    public static final Enum DOUBLE = Enum.forString("double");
    public static final Enum THICK = Enum.forString("thick");
    public static final Enum DOTTED = Enum.forString("dotted");
    public static final Enum DOTTED_HEAVY = Enum.forString("dottedHeavy");
    public static final Enum DASH = Enum.forString("dash");
    public static final Enum DASHED_HEAVY = Enum.forString("dashedHeavy");
    public static final Enum DASH_LONG = Enum.forString("dashLong");
    public static final Enum DASH_LONG_HEAVY = Enum.forString("dashLongHeavy");
    public static final Enum DOT_DASH = Enum.forString("dotDash");
    public static final Enum DASH_DOT_HEAVY = Enum.forString("dashDotHeavy");
    public static final Enum DOT_DOT_DASH = Enum.forString("dotDotDash");
    public static final Enum DASH_DOT_DOT_HEAVY = Enum.forString("dashDotDotHeavy");
    public static final Enum WAVE = Enum.forString("wave");
    public static final Enum WAVY_HEAVY = Enum.forString("wavyHeavy");
    public static final Enum WAVY_DOUBLE = Enum.forString("wavyDouble");
    public static final Enum NONE = Enum.forString("none");
    public static final int INT_SINGLE = 1;
    public static final int INT_WORDS = 2;
    public static final int INT_DOUBLE = 3;
    public static final int INT_THICK = 4;
    public static final int INT_DOTTED = 5;
    public static final int INT_DOTTED_HEAVY = 6;
    public static final int INT_DASH = 7;
    public static final int INT_DASHED_HEAVY = 8;
    public static final int INT_DASH_LONG = 9;
    public static final int INT_DASH_LONG_HEAVY = 10;
    public static final int INT_DOT_DASH = 11;
    public static final int INT_DASH_DOT_HEAVY = 12;
    public static final int INT_DOT_DOT_DASH = 13;
    public static final int INT_DASH_DOT_DOT_HEAVY = 14;
    public static final int INT_WAVE = 15;
    public static final int INT_WAVY_HEAVY = 16;
    public static final int INT_WAVY_DOUBLE = 17;
    public static final int INT_NONE = 18;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STUnderline newValue(Object object) {
            return (STUnderline)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STUnderline.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STUnderline newInstance() {
            return (STUnderline)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STUnderline newInstance(XmlOptions xmlOptions) {
            return (STUnderline)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STUnderline parse(String string) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STUnderline parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STUnderline parse(File file) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STUnderline parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STUnderline parse(URL uRL) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STUnderline parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STUnderline parse(InputStream inputStream) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STUnderline parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STUnderline parse(Reader reader) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STUnderline parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STUnderline)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STUnderline parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STUnderline parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STUnderline parse(Node node) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STUnderline parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STUnderline)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STUnderline parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STUnderline)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STUnderline parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STUnderline)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_SINGLE = 1;
        static final int INT_WORDS = 2;
        static final int INT_DOUBLE = 3;
        static final int INT_THICK = 4;
        static final int INT_DOTTED = 5;
        static final int INT_DOTTED_HEAVY = 6;
        static final int INT_DASH = 7;
        static final int INT_DASHED_HEAVY = 8;
        static final int INT_DASH_LONG = 9;
        static final int INT_DASH_LONG_HEAVY = 10;
        static final int INT_DOT_DASH = 11;
        static final int INT_DASH_DOT_HEAVY = 12;
        static final int INT_DOT_DOT_DASH = 13;
        static final int INT_DASH_DOT_DOT_HEAVY = 14;
        static final int INT_WAVE = 15;
        static final int INT_WAVY_HEAVY = 16;
        static final int INT_WAVY_DOUBLE = 17;
        static final int INT_NONE = 18;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("single", 1), new Enum("words", 2), new Enum("double", 3), new Enum("thick", 4), new Enum("dotted", 5), new Enum("dottedHeavy", 6), new Enum("dash", 7), new Enum("dashedHeavy", 8), new Enum("dashLong", 9), new Enum("dashLongHeavy", 10), new Enum("dotDash", 11), new Enum("dashDotHeavy", 12), new Enum("dotDotDash", 13), new Enum("dashDotDotHeavy", 14), new Enum("wave", 15), new Enum("wavyHeavy", 16), new Enum("wavyDouble", 17), new Enum("none", 18)});
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

