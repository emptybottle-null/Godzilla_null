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

public interface STPane
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPane.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpane2ac1type");
    public static final Enum BOTTOM_RIGHT = Enum.forString("bottomRight");
    public static final Enum TOP_RIGHT = Enum.forString("topRight");
    public static final Enum BOTTOM_LEFT = Enum.forString("bottomLeft");
    public static final Enum TOP_LEFT = Enum.forString("topLeft");
    public static final int INT_BOTTOM_RIGHT = 1;
    public static final int INT_TOP_RIGHT = 2;
    public static final int INT_BOTTOM_LEFT = 3;
    public static final int INT_TOP_LEFT = 4;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPane newValue(Object object) {
            return (STPane)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPane.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPane newInstance() {
            return (STPane)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPane newInstance(XmlOptions xmlOptions) {
            return (STPane)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPane parse(String string) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPane parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPane parse(File file) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPane parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPane parse(URL uRL) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPane parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPane parse(InputStream inputStream) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPane parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPane parse(Reader reader) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPane parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPane)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPane parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPane parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPane parse(Node node) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPane parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPane)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPane parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPane)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPane parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPane)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_BOTTOM_RIGHT = 1;
        static final int INT_TOP_RIGHT = 2;
        static final int INT_BOTTOM_LEFT = 3;
        static final int INT_TOP_LEFT = 4;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("bottomRight", 1), new Enum("topRight", 2), new Enum("bottomLeft", 3), new Enum("topLeft", 4)});
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

