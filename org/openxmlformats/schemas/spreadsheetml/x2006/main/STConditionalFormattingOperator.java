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

public interface STConditionalFormattingOperator
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STConditionalFormattingOperator.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stconditionalformattingoperatora99etype");
    public static final Enum LESS_THAN = Enum.forString("lessThan");
    public static final Enum LESS_THAN_OR_EQUAL = Enum.forString("lessThanOrEqual");
    public static final Enum EQUAL = Enum.forString("equal");
    public static final Enum NOT_EQUAL = Enum.forString("notEqual");
    public static final Enum GREATER_THAN_OR_EQUAL = Enum.forString("greaterThanOrEqual");
    public static final Enum GREATER_THAN = Enum.forString("greaterThan");
    public static final Enum BETWEEN = Enum.forString("between");
    public static final Enum NOT_BETWEEN = Enum.forString("notBetween");
    public static final Enum CONTAINS_TEXT = Enum.forString("containsText");
    public static final Enum NOT_CONTAINS = Enum.forString("notContains");
    public static final Enum BEGINS_WITH = Enum.forString("beginsWith");
    public static final Enum ENDS_WITH = Enum.forString("endsWith");
    public static final int INT_LESS_THAN = 1;
    public static final int INT_LESS_THAN_OR_EQUAL = 2;
    public static final int INT_EQUAL = 3;
    public static final int INT_NOT_EQUAL = 4;
    public static final int INT_GREATER_THAN_OR_EQUAL = 5;
    public static final int INT_GREATER_THAN = 6;
    public static final int INT_BETWEEN = 7;
    public static final int INT_NOT_BETWEEN = 8;
    public static final int INT_CONTAINS_TEXT = 9;
    public static final int INT_NOT_CONTAINS = 10;
    public static final int INT_BEGINS_WITH = 11;
    public static final int INT_ENDS_WITH = 12;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STConditionalFormattingOperator newValue(Object object) {
            return (STConditionalFormattingOperator)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STConditionalFormattingOperator.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STConditionalFormattingOperator newInstance() {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STConditionalFormattingOperator newInstance(XmlOptions xmlOptions) {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(String string) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STConditionalFormattingOperator parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(File file) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STConditionalFormattingOperator parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(URL uRL) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STConditionalFormattingOperator parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(InputStream inputStream) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STConditionalFormattingOperator parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(Reader reader) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STConditionalFormattingOperator parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STConditionalFormattingOperator parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STConditionalFormattingOperator parse(Node node) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STConditionalFormattingOperator parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STConditionalFormattingOperator parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STConditionalFormattingOperator parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STConditionalFormattingOperator)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_LESS_THAN = 1;
        static final int INT_LESS_THAN_OR_EQUAL = 2;
        static final int INT_EQUAL = 3;
        static final int INT_NOT_EQUAL = 4;
        static final int INT_GREATER_THAN_OR_EQUAL = 5;
        static final int INT_GREATER_THAN = 6;
        static final int INT_BETWEEN = 7;
        static final int INT_NOT_BETWEEN = 8;
        static final int INT_CONTAINS_TEXT = 9;
        static final int INT_NOT_CONTAINS = 10;
        static final int INT_BEGINS_WITH = 11;
        static final int INT_ENDS_WITH = 12;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("lessThan", 1), new Enum("lessThanOrEqual", 2), new Enum("equal", 3), new Enum("notEqual", 4), new Enum("greaterThanOrEqual", 5), new Enum("greaterThan", 6), new Enum("between", 7), new Enum("notBetween", 8), new Enum("containsText", 9), new Enum("notContains", 10), new Enum("beginsWith", 11), new Enum("endsWith", 12)});
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

