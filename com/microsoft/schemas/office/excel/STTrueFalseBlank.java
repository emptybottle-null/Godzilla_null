/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.excel;

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

public interface STTrueFalseBlank
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTrueFalseBlank.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttruefalseblanka061type");
    public static final Enum TRUE = Enum.forString("True");
    public static final Enum T = Enum.forString("t");
    public static final Enum FALSE = Enum.forString("False");
    public static final Enum F = Enum.forString("f");
    public static final Enum X = Enum.forString("");
    public static final int INT_TRUE = 1;
    public static final int INT_T = 2;
    public static final int INT_FALSE = 3;
    public static final int INT_F = 4;
    public static final int INT_X = 5;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTrueFalseBlank newValue(Object object) {
            return (STTrueFalseBlank)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTrueFalseBlank.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTrueFalseBlank newInstance() {
            return (STTrueFalseBlank)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTrueFalseBlank newInstance(XmlOptions xmlOptions) {
            return (STTrueFalseBlank)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTrueFalseBlank parse(String string) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTrueFalseBlank parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(File file) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTrueFalseBlank parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(URL uRL) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTrueFalseBlank parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(InputStream inputStream) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTrueFalseBlank parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(Reader reader) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTrueFalseBlank parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTrueFalseBlank parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTrueFalseBlank parse(Node node) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTrueFalseBlank parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTrueFalseBlank parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTrueFalseBlank parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTrueFalseBlank)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_TRUE = 1;
        static final int INT_T = 2;
        static final int INT_FALSE = 3;
        static final int INT_F = 4;
        static final int INT_X = 5;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("True", 1), new Enum("t", 2), new Enum("False", 3), new Enum("f", 4), new Enum("", 5)});
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

