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

public interface STObjectType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STObjectType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stobjecttype97a7type");
    public static final Enum BUTTON = Enum.forString("Button");
    public static final Enum CHECKBOX = Enum.forString("Checkbox");
    public static final Enum DIALOG = Enum.forString("Dialog");
    public static final Enum DROP = Enum.forString("Drop");
    public static final Enum EDIT = Enum.forString("Edit");
    public static final Enum G_BOX = Enum.forString("GBox");
    public static final Enum LABEL = Enum.forString("Label");
    public static final Enum LINE_A = Enum.forString("LineA");
    public static final Enum LIST = Enum.forString("List");
    public static final Enum MOVIE = Enum.forString("Movie");
    public static final Enum NOTE = Enum.forString("Note");
    public static final Enum PICT = Enum.forString("Pict");
    public static final Enum RADIO = Enum.forString("Radio");
    public static final Enum RECT_A = Enum.forString("RectA");
    public static final Enum SCROLL = Enum.forString("Scroll");
    public static final Enum SPIN = Enum.forString("Spin");
    public static final Enum SHAPE = Enum.forString("Shape");
    public static final Enum GROUP = Enum.forString("Group");
    public static final Enum RECT = Enum.forString("Rect");
    public static final int INT_BUTTON = 1;
    public static final int INT_CHECKBOX = 2;
    public static final int INT_DIALOG = 3;
    public static final int INT_DROP = 4;
    public static final int INT_EDIT = 5;
    public static final int INT_G_BOX = 6;
    public static final int INT_LABEL = 7;
    public static final int INT_LINE_A = 8;
    public static final int INT_LIST = 9;
    public static final int INT_MOVIE = 10;
    public static final int INT_NOTE = 11;
    public static final int INT_PICT = 12;
    public static final int INT_RADIO = 13;
    public static final int INT_RECT_A = 14;
    public static final int INT_SCROLL = 15;
    public static final int INT_SPIN = 16;
    public static final int INT_SHAPE = 17;
    public static final int INT_GROUP = 18;
    public static final int INT_RECT = 19;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STObjectType newValue(Object object) {
            return (STObjectType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STObjectType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STObjectType newInstance() {
            return (STObjectType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STObjectType newInstance(XmlOptions xmlOptions) {
            return (STObjectType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STObjectType parse(String string) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STObjectType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STObjectType parse(File file) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STObjectType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STObjectType parse(URL uRL) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STObjectType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STObjectType parse(InputStream inputStream) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STObjectType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STObjectType parse(Reader reader) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STObjectType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STObjectType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STObjectType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STObjectType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STObjectType parse(Node node) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STObjectType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STObjectType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STObjectType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STObjectType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STObjectType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STObjectType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_BUTTON = 1;
        static final int INT_CHECKBOX = 2;
        static final int INT_DIALOG = 3;
        static final int INT_DROP = 4;
        static final int INT_EDIT = 5;
        static final int INT_G_BOX = 6;
        static final int INT_LABEL = 7;
        static final int INT_LINE_A = 8;
        static final int INT_LIST = 9;
        static final int INT_MOVIE = 10;
        static final int INT_NOTE = 11;
        static final int INT_PICT = 12;
        static final int INT_RADIO = 13;
        static final int INT_RECT_A = 14;
        static final int INT_SCROLL = 15;
        static final int INT_SPIN = 16;
        static final int INT_SHAPE = 17;
        static final int INT_GROUP = 18;
        static final int INT_RECT = 19;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("Button", 1), new Enum("Checkbox", 2), new Enum("Dialog", 3), new Enum("Drop", 4), new Enum("Edit", 5), new Enum("GBox", 6), new Enum("Label", 7), new Enum("LineA", 8), new Enum("List", 9), new Enum("Movie", 10), new Enum("Note", 11), new Enum("Pict", 12), new Enum("Radio", 13), new Enum("RectA", 14), new Enum("Scroll", 15), new Enum("Spin", 16), new Enum("Shape", 17), new Enum("Group", 18), new Enum("Rect", 19)});
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

