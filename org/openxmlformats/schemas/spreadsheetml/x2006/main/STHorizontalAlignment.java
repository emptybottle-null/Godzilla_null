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

public interface STHorizontalAlignment
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STHorizontalAlignment.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sthorizontalalignmentf92etype");
    public static final Enum GENERAL = Enum.forString("general");
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum FILL = Enum.forString("fill");
    public static final Enum JUSTIFY = Enum.forString("justify");
    public static final Enum CENTER_CONTINUOUS = Enum.forString("centerContinuous");
    public static final Enum DISTRIBUTED = Enum.forString("distributed");
    public static final int INT_GENERAL = 1;
    public static final int INT_LEFT = 2;
    public static final int INT_CENTER = 3;
    public static final int INT_RIGHT = 4;
    public static final int INT_FILL = 5;
    public static final int INT_JUSTIFY = 6;
    public static final int INT_CENTER_CONTINUOUS = 7;
    public static final int INT_DISTRIBUTED = 8;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STHorizontalAlignment newValue(Object object) {
            return (STHorizontalAlignment)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STHorizontalAlignment.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STHorizontalAlignment newInstance() {
            return (STHorizontalAlignment)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STHorizontalAlignment newInstance(XmlOptions xmlOptions) {
            return (STHorizontalAlignment)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STHorizontalAlignment parse(String string) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STHorizontalAlignment parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(File file) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STHorizontalAlignment parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(URL uRL) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STHorizontalAlignment parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(InputStream inputStream) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STHorizontalAlignment parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(Reader reader) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STHorizontalAlignment parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STHorizontalAlignment parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STHorizontalAlignment parse(Node node) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STHorizontalAlignment parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STHorizontalAlignment parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STHorizontalAlignment parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STHorizontalAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_GENERAL = 1;
        static final int INT_LEFT = 2;
        static final int INT_CENTER = 3;
        static final int INT_RIGHT = 4;
        static final int INT_FILL = 5;
        static final int INT_JUSTIFY = 6;
        static final int INT_CENTER_CONTINUOUS = 7;
        static final int INT_DISTRIBUTED = 8;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("general", 1), new Enum("left", 2), new Enum("center", 3), new Enum("right", 4), new Enum("fill", 5), new Enum("justify", 6), new Enum("centerContinuous", 7), new Enum("distributed", 8)});
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

