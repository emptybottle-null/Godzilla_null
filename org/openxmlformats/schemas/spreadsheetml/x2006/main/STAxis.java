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

public interface STAxis
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STAxis.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("staxis45batype");
    public static final Enum AXIS_ROW = Enum.forString("axisRow");
    public static final Enum AXIS_COL = Enum.forString("axisCol");
    public static final Enum AXIS_PAGE = Enum.forString("axisPage");
    public static final Enum AXIS_VALUES = Enum.forString("axisValues");
    public static final int INT_AXIS_ROW = 1;
    public static final int INT_AXIS_COL = 2;
    public static final int INT_AXIS_PAGE = 3;
    public static final int INT_AXIS_VALUES = 4;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STAxis newValue(Object object) {
            return (STAxis)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STAxis.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STAxis newInstance() {
            return (STAxis)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STAxis newInstance(XmlOptions xmlOptions) {
            return (STAxis)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STAxis parse(String string) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STAxis parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STAxis parse(File file) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STAxis parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STAxis parse(URL uRL) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STAxis parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STAxis parse(InputStream inputStream) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STAxis parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STAxis parse(Reader reader) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STAxis parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STAxis)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STAxis parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STAxis parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STAxis parse(Node node) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STAxis parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STAxis)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STAxis parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STAxis)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STAxis parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STAxis)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_AXIS_ROW = 1;
        static final int INT_AXIS_COL = 2;
        static final int INT_AXIS_PAGE = 3;
        static final int INT_AXIS_VALUES = 4;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("axisRow", 1), new Enum("axisCol", 2), new Enum("axisPage", 3), new Enum("axisValues", 4)});
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

