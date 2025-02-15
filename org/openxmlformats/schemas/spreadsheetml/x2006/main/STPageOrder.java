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

public interface STPageOrder
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPageOrder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpageorderd2cetype");
    public static final Enum DOWN_THEN_OVER = Enum.forString("downThenOver");
    public static final Enum OVER_THEN_DOWN = Enum.forString("overThenDown");
    public static final int INT_DOWN_THEN_OVER = 1;
    public static final int INT_OVER_THEN_DOWN = 2;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPageOrder newValue(Object object) {
            return (STPageOrder)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPageOrder.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPageOrder newInstance() {
            return (STPageOrder)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPageOrder newInstance(XmlOptions xmlOptions) {
            return (STPageOrder)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPageOrder parse(String string) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPageOrder parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPageOrder parse(File file) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPageOrder parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPageOrder parse(URL uRL) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPageOrder parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPageOrder parse(InputStream inputStream) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPageOrder parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPageOrder parse(Reader reader) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPageOrder parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPageOrder)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPageOrder parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPageOrder parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPageOrder parse(Node node) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPageOrder parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPageOrder)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPageOrder parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPageOrder)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPageOrder parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPageOrder)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DOWN_THEN_OVER = 1;
        static final int INT_OVER_THEN_DOWN = 2;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("downThenOver", 1), new Enum("overThenDown", 2)});
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

