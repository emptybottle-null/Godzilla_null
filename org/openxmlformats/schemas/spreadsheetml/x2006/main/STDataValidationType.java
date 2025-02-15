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

public interface STDataValidationType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STDataValidationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stdatavalidationtypeabf6type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum WHOLE = Enum.forString("whole");
    public static final Enum DECIMAL = Enum.forString("decimal");
    public static final Enum LIST = Enum.forString("list");
    public static final Enum DATE = Enum.forString("date");
    public static final Enum TIME = Enum.forString("time");
    public static final Enum TEXT_LENGTH = Enum.forString("textLength");
    public static final Enum CUSTOM = Enum.forString("custom");
    public static final int INT_NONE = 1;
    public static final int INT_WHOLE = 2;
    public static final int INT_DECIMAL = 3;
    public static final int INT_LIST = 4;
    public static final int INT_DATE = 5;
    public static final int INT_TIME = 6;
    public static final int INT_TEXT_LENGTH = 7;
    public static final int INT_CUSTOM = 8;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STDataValidationType newValue(Object object) {
            return (STDataValidationType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STDataValidationType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STDataValidationType newInstance() {
            return (STDataValidationType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STDataValidationType newInstance(XmlOptions xmlOptions) {
            return (STDataValidationType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STDataValidationType parse(String string) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STDataValidationType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STDataValidationType parse(File file) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STDataValidationType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STDataValidationType parse(URL uRL) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STDataValidationType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STDataValidationType parse(InputStream inputStream) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STDataValidationType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STDataValidationType parse(Reader reader) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STDataValidationType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDataValidationType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STDataValidationType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STDataValidationType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STDataValidationType parse(Node node) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STDataValidationType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STDataValidationType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STDataValidationType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STDataValidationType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STDataValidationType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STDataValidationType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NONE = 1;
        static final int INT_WHOLE = 2;
        static final int INT_DECIMAL = 3;
        static final int INT_LIST = 4;
        static final int INT_DATE = 5;
        static final int INT_TIME = 6;
        static final int INT_TEXT_LENGTH = 7;
        static final int INT_CUSTOM = 8;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("whole", 2), new Enum("decimal", 3), new Enum("list", 4), new Enum("date", 5), new Enum("time", 6), new Enum("textLength", 7), new Enum("custom", 8)});
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

