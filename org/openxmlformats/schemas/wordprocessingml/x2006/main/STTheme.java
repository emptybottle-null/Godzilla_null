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

public interface STTheme
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTheme.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttheme58b9type");
    public static final Enum MAJOR_EAST_ASIA = Enum.forString("majorEastAsia");
    public static final Enum MAJOR_BIDI = Enum.forString("majorBidi");
    public static final Enum MAJOR_ASCII = Enum.forString("majorAscii");
    public static final Enum MAJOR_H_ANSI = Enum.forString("majorHAnsi");
    public static final Enum MINOR_EAST_ASIA = Enum.forString("minorEastAsia");
    public static final Enum MINOR_BIDI = Enum.forString("minorBidi");
    public static final Enum MINOR_ASCII = Enum.forString("minorAscii");
    public static final Enum MINOR_H_ANSI = Enum.forString("minorHAnsi");
    public static final int INT_MAJOR_EAST_ASIA = 1;
    public static final int INT_MAJOR_BIDI = 2;
    public static final int INT_MAJOR_ASCII = 3;
    public static final int INT_MAJOR_H_ANSI = 4;
    public static final int INT_MINOR_EAST_ASIA = 5;
    public static final int INT_MINOR_BIDI = 6;
    public static final int INT_MINOR_ASCII = 7;
    public static final int INT_MINOR_H_ANSI = 8;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTheme newValue(Object object) {
            return (STTheme)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTheme.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTheme newInstance() {
            return (STTheme)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTheme newInstance(XmlOptions xmlOptions) {
            return (STTheme)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTheme parse(String string) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTheme parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTheme parse(File file) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTheme parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTheme parse(URL uRL) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTheme parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTheme parse(InputStream inputStream) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTheme parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTheme parse(Reader reader) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTheme parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTheme)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTheme parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTheme parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTheme parse(Node node) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTheme parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTheme)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTheme parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTheme)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTheme parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTheme)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_MAJOR_EAST_ASIA = 1;
        static final int INT_MAJOR_BIDI = 2;
        static final int INT_MAJOR_ASCII = 3;
        static final int INT_MAJOR_H_ANSI = 4;
        static final int INT_MINOR_EAST_ASIA = 5;
        static final int INT_MINOR_BIDI = 6;
        static final int INT_MINOR_ASCII = 7;
        static final int INT_MINOR_H_ANSI = 8;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("majorEastAsia", 1), new Enum("majorBidi", 2), new Enum("majorAscii", 3), new Enum("majorHAnsi", 4), new Enum("minorEastAsia", 5), new Enum("minorBidi", 6), new Enum("minorAscii", 7), new Enum("minorHAnsi", 8)});
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

