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

public interface STDataValidationOperator
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STDataValidationOperator.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stdatavalidationoperatore0e0type");
    public static final Enum BETWEEN = Enum.forString("between");
    public static final Enum NOT_BETWEEN = Enum.forString("notBetween");
    public static final Enum EQUAL = Enum.forString("equal");
    public static final Enum NOT_EQUAL = Enum.forString("notEqual");
    public static final Enum LESS_THAN = Enum.forString("lessThan");
    public static final Enum LESS_THAN_OR_EQUAL = Enum.forString("lessThanOrEqual");
    public static final Enum GREATER_THAN = Enum.forString("greaterThan");
    public static final Enum GREATER_THAN_OR_EQUAL = Enum.forString("greaterThanOrEqual");
    public static final int INT_BETWEEN = 1;
    public static final int INT_NOT_BETWEEN = 2;
    public static final int INT_EQUAL = 3;
    public static final int INT_NOT_EQUAL = 4;
    public static final int INT_LESS_THAN = 5;
    public static final int INT_LESS_THAN_OR_EQUAL = 6;
    public static final int INT_GREATER_THAN = 7;
    public static final int INT_GREATER_THAN_OR_EQUAL = 8;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STDataValidationOperator newValue(Object object) {
            return (STDataValidationOperator)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STDataValidationOperator.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STDataValidationOperator newInstance() {
            return (STDataValidationOperator)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STDataValidationOperator newInstance(XmlOptions xmlOptions) {
            return (STDataValidationOperator)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STDataValidationOperator parse(String string) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STDataValidationOperator parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STDataValidationOperator parse(File file) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STDataValidationOperator parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STDataValidationOperator parse(URL uRL) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STDataValidationOperator parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STDataValidationOperator parse(InputStream inputStream) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STDataValidationOperator parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STDataValidationOperator parse(Reader reader) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STDataValidationOperator parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STDataValidationOperator parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STDataValidationOperator parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STDataValidationOperator parse(Node node) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STDataValidationOperator parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STDataValidationOperator parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STDataValidationOperator parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STDataValidationOperator)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_BETWEEN = 1;
        static final int INT_NOT_BETWEEN = 2;
        static final int INT_EQUAL = 3;
        static final int INT_NOT_EQUAL = 4;
        static final int INT_LESS_THAN = 5;
        static final int INT_LESS_THAN_OR_EQUAL = 6;
        static final int INT_GREATER_THAN = 7;
        static final int INT_GREATER_THAN_OR_EQUAL = 8;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("between", 1), new Enum("notBetween", 2), new Enum("equal", 3), new Enum("notEqual", 4), new Enum("lessThan", 5), new Enum("lessThanOrEqual", 6), new Enum("greaterThan", 7), new Enum("greaterThanOrEqual", 8)});
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

