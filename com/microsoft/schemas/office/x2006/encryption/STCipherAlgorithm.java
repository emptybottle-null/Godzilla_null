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

public interface STCipherAlgorithm
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STCipherAlgorithm.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("stcipheralgorithme346type");
    public static final Enum AES = Enum.forString("AES");
    public static final Enum RC_2 = Enum.forString("RC2");
    public static final Enum RC_4 = Enum.forString("RC4");
    public static final Enum DES = Enum.forString("DES");
    public static final Enum DESX = Enum.forString("DESX");
    public static final Enum X_3_DES = Enum.forString("3DES");
    public static final Enum X_3_DES_112 = Enum.forString("3DES_112");
    public static final int INT_AES = 1;
    public static final int INT_RC_2 = 2;
    public static final int INT_RC_4 = 3;
    public static final int INT_DES = 4;
    public static final int INT_DESX = 5;
    public static final int INT_X_3_DES = 6;
    public static final int INT_X_3_DES_112 = 7;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STCipherAlgorithm newValue(Object object) {
            return (STCipherAlgorithm)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STCipherAlgorithm.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STCipherAlgorithm newInstance() {
            return (STCipherAlgorithm)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STCipherAlgorithm newInstance(XmlOptions xmlOptions) {
            return (STCipherAlgorithm)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STCipherAlgorithm parse(String string) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STCipherAlgorithm parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(File file) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STCipherAlgorithm parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(URL uRL) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STCipherAlgorithm parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(InputStream inputStream) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STCipherAlgorithm parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(Reader reader) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STCipherAlgorithm parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STCipherAlgorithm parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STCipherAlgorithm parse(Node node) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STCipherAlgorithm parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STCipherAlgorithm parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STCipherAlgorithm parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STCipherAlgorithm)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_AES = 1;
        static final int INT_RC_2 = 2;
        static final int INT_RC_4 = 3;
        static final int INT_DES = 4;
        static final int INT_DESX = 5;
        static final int INT_X_3_DES = 6;
        static final int INT_X_3_DES_112 = 7;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("AES", 1), new Enum("RC2", 2), new Enum("RC4", 3), new Enum("DES", 4), new Enum("DESX", 5), new Enum("3DES", 6), new Enum("3DES_112", 7)});
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

