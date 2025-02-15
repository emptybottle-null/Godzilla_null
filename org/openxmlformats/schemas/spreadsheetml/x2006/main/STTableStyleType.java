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

public interface STTableStyleType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTableStyleType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttablestyletype9936type");
    public static final Enum WHOLE_TABLE = Enum.forString("wholeTable");
    public static final Enum HEADER_ROW = Enum.forString("headerRow");
    public static final Enum TOTAL_ROW = Enum.forString("totalRow");
    public static final Enum FIRST_COLUMN = Enum.forString("firstColumn");
    public static final Enum LAST_COLUMN = Enum.forString("lastColumn");
    public static final Enum FIRST_ROW_STRIPE = Enum.forString("firstRowStripe");
    public static final Enum SECOND_ROW_STRIPE = Enum.forString("secondRowStripe");
    public static final Enum FIRST_COLUMN_STRIPE = Enum.forString("firstColumnStripe");
    public static final Enum SECOND_COLUMN_STRIPE = Enum.forString("secondColumnStripe");
    public static final Enum FIRST_HEADER_CELL = Enum.forString("firstHeaderCell");
    public static final Enum LAST_HEADER_CELL = Enum.forString("lastHeaderCell");
    public static final Enum FIRST_TOTAL_CELL = Enum.forString("firstTotalCell");
    public static final Enum LAST_TOTAL_CELL = Enum.forString("lastTotalCell");
    public static final Enum FIRST_SUBTOTAL_COLUMN = Enum.forString("firstSubtotalColumn");
    public static final Enum SECOND_SUBTOTAL_COLUMN = Enum.forString("secondSubtotalColumn");
    public static final Enum THIRD_SUBTOTAL_COLUMN = Enum.forString("thirdSubtotalColumn");
    public static final Enum FIRST_SUBTOTAL_ROW = Enum.forString("firstSubtotalRow");
    public static final Enum SECOND_SUBTOTAL_ROW = Enum.forString("secondSubtotalRow");
    public static final Enum THIRD_SUBTOTAL_ROW = Enum.forString("thirdSubtotalRow");
    public static final Enum BLANK_ROW = Enum.forString("blankRow");
    public static final Enum FIRST_COLUMN_SUBHEADING = Enum.forString("firstColumnSubheading");
    public static final Enum SECOND_COLUMN_SUBHEADING = Enum.forString("secondColumnSubheading");
    public static final Enum THIRD_COLUMN_SUBHEADING = Enum.forString("thirdColumnSubheading");
    public static final Enum FIRST_ROW_SUBHEADING = Enum.forString("firstRowSubheading");
    public static final Enum SECOND_ROW_SUBHEADING = Enum.forString("secondRowSubheading");
    public static final Enum THIRD_ROW_SUBHEADING = Enum.forString("thirdRowSubheading");
    public static final Enum PAGE_FIELD_LABELS = Enum.forString("pageFieldLabels");
    public static final Enum PAGE_FIELD_VALUES = Enum.forString("pageFieldValues");
    public static final int INT_WHOLE_TABLE = 1;
    public static final int INT_HEADER_ROW = 2;
    public static final int INT_TOTAL_ROW = 3;
    public static final int INT_FIRST_COLUMN = 4;
    public static final int INT_LAST_COLUMN = 5;
    public static final int INT_FIRST_ROW_STRIPE = 6;
    public static final int INT_SECOND_ROW_STRIPE = 7;
    public static final int INT_FIRST_COLUMN_STRIPE = 8;
    public static final int INT_SECOND_COLUMN_STRIPE = 9;
    public static final int INT_FIRST_HEADER_CELL = 10;
    public static final int INT_LAST_HEADER_CELL = 11;
    public static final int INT_FIRST_TOTAL_CELL = 12;
    public static final int INT_LAST_TOTAL_CELL = 13;
    public static final int INT_FIRST_SUBTOTAL_COLUMN = 14;
    public static final int INT_SECOND_SUBTOTAL_COLUMN = 15;
    public static final int INT_THIRD_SUBTOTAL_COLUMN = 16;
    public static final int INT_FIRST_SUBTOTAL_ROW = 17;
    public static final int INT_SECOND_SUBTOTAL_ROW = 18;
    public static final int INT_THIRD_SUBTOTAL_ROW = 19;
    public static final int INT_BLANK_ROW = 20;
    public static final int INT_FIRST_COLUMN_SUBHEADING = 21;
    public static final int INT_SECOND_COLUMN_SUBHEADING = 22;
    public static final int INT_THIRD_COLUMN_SUBHEADING = 23;
    public static final int INT_FIRST_ROW_SUBHEADING = 24;
    public static final int INT_SECOND_ROW_SUBHEADING = 25;
    public static final int INT_THIRD_ROW_SUBHEADING = 26;
    public static final int INT_PAGE_FIELD_LABELS = 27;
    public static final int INT_PAGE_FIELD_VALUES = 28;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTableStyleType newValue(Object object) {
            return (STTableStyleType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTableStyleType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTableStyleType newInstance() {
            return (STTableStyleType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTableStyleType newInstance(XmlOptions xmlOptions) {
            return (STTableStyleType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTableStyleType parse(String string) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTableStyleType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTableStyleType parse(File file) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTableStyleType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTableStyleType parse(URL uRL) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTableStyleType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTableStyleType parse(InputStream inputStream) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTableStyleType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTableStyleType parse(Reader reader) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTableStyleType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTableStyleType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTableStyleType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTableStyleType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTableStyleType parse(Node node) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTableStyleType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTableStyleType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTableStyleType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTableStyleType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTableStyleType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTableStyleType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_WHOLE_TABLE = 1;
        static final int INT_HEADER_ROW = 2;
        static final int INT_TOTAL_ROW = 3;
        static final int INT_FIRST_COLUMN = 4;
        static final int INT_LAST_COLUMN = 5;
        static final int INT_FIRST_ROW_STRIPE = 6;
        static final int INT_SECOND_ROW_STRIPE = 7;
        static final int INT_FIRST_COLUMN_STRIPE = 8;
        static final int INT_SECOND_COLUMN_STRIPE = 9;
        static final int INT_FIRST_HEADER_CELL = 10;
        static final int INT_LAST_HEADER_CELL = 11;
        static final int INT_FIRST_TOTAL_CELL = 12;
        static final int INT_LAST_TOTAL_CELL = 13;
        static final int INT_FIRST_SUBTOTAL_COLUMN = 14;
        static final int INT_SECOND_SUBTOTAL_COLUMN = 15;
        static final int INT_THIRD_SUBTOTAL_COLUMN = 16;
        static final int INT_FIRST_SUBTOTAL_ROW = 17;
        static final int INT_SECOND_SUBTOTAL_ROW = 18;
        static final int INT_THIRD_SUBTOTAL_ROW = 19;
        static final int INT_BLANK_ROW = 20;
        static final int INT_FIRST_COLUMN_SUBHEADING = 21;
        static final int INT_SECOND_COLUMN_SUBHEADING = 22;
        static final int INT_THIRD_COLUMN_SUBHEADING = 23;
        static final int INT_FIRST_ROW_SUBHEADING = 24;
        static final int INT_SECOND_ROW_SUBHEADING = 25;
        static final int INT_THIRD_ROW_SUBHEADING = 26;
        static final int INT_PAGE_FIELD_LABELS = 27;
        static final int INT_PAGE_FIELD_VALUES = 28;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("wholeTable", 1), new Enum("headerRow", 2), new Enum("totalRow", 3), new Enum("firstColumn", 4), new Enum("lastColumn", 5), new Enum("firstRowStripe", 6), new Enum("secondRowStripe", 7), new Enum("firstColumnStripe", 8), new Enum("secondColumnStripe", 9), new Enum("firstHeaderCell", 10), new Enum("lastHeaderCell", 11), new Enum("firstTotalCell", 12), new Enum("lastTotalCell", 13), new Enum("firstSubtotalColumn", 14), new Enum("secondSubtotalColumn", 15), new Enum("thirdSubtotalColumn", 16), new Enum("firstSubtotalRow", 17), new Enum("secondSubtotalRow", 18), new Enum("thirdSubtotalRow", 19), new Enum("blankRow", 20), new Enum("firstColumnSubheading", 21), new Enum("secondColumnSubheading", 22), new Enum("thirdColumnSubheading", 23), new Enum("firstRowSubheading", 24), new Enum("secondRowSubheading", 25), new Enum("thirdRowSubheading", 26), new Enum("pageFieldLabels", 27), new Enum("pageFieldValues", 28)});
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

