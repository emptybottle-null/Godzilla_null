/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

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

public interface STHashAlgorithm
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STHashAlgorithm.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("sthashalgorithm65e3type");
    public static final Enum SHA_1 = Enum.forString("SHA1");
    public static final Enum SHA_256 = Enum.forString("SHA256");
    public static final Enum SHA_384 = Enum.forString("SHA384");
    public static final Enum SHA_512 = Enum.forString("SHA512");
    public static final Enum MD_5 = Enum.forString("MD5");
    public static final Enum MD_4 = Enum.forString("MD4");
    public static final Enum MD_2 = Enum.forString("MD2");
    public static final Enum RIPEMD_128 = Enum.forString("RIPEMD-128");
    public static final Enum RIPEMD_160 = Enum.forString("RIPEMD-160");
    public static final Enum WHIRLPOOL = Enum.forString("WHIRLPOOL");
    public static final int INT_SHA_1 = 1;
    public static final int INT_SHA_256 = 2;
    public static final int INT_SHA_384 = 3;
    public static final int INT_SHA_512 = 4;
    public static final int INT_MD_5 = 5;
    public static final int INT_MD_4 = 6;
    public static final int INT_MD_2 = 7;
    public static final int INT_RIPEMD_128 = 8;
    public static final int INT_RIPEMD_160 = 9;
    public static final int INT_WHIRLPOOL = 10;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STHashAlgorithm newValue(Object object) {
            return (STHashAlgorithm)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STHashAlgorithm.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STHashAlgorithm newInstance() {
            return (STHashAlgorithm)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STHashAlgorithm newInstance(XmlOptions xmlOptions) {
            return (STHashAlgorithm)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STHashAlgorithm parse(String string) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STHashAlgorithm parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STHashAlgorithm parse(File file) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STHashAlgorithm parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STHashAlgorithm parse(URL uRL) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STHashAlgorithm parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STHashAlgorithm parse(InputStream inputStream) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STHashAlgorithm parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STHashAlgorithm parse(Reader reader) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STHashAlgorithm parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STHashAlgorithm parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STHashAlgorithm parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STHashAlgorithm parse(Node node) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STHashAlgorithm parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STHashAlgorithm parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STHashAlgorithm parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STHashAlgorithm)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_SHA_1 = 1;
        static final int INT_SHA_256 = 2;
        static final int INT_SHA_384 = 3;
        static final int INT_SHA_512 = 4;
        static final int INT_MD_5 = 5;
        static final int INT_MD_4 = 6;
        static final int INT_MD_2 = 7;
        static final int INT_RIPEMD_128 = 8;
        static final int INT_RIPEMD_160 = 9;
        static final int INT_WHIRLPOOL = 10;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("SHA1", 1), new Enum("SHA256", 2), new Enum("SHA384", 3), new Enum("SHA512", 4), new Enum("MD5", 5), new Enum("MD4", 6), new Enum("MD2", 7), new Enum("RIPEMD-128", 8), new Enum("RIPEMD-160", 9), new Enum("WHIRLPOOL", 10)});
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

