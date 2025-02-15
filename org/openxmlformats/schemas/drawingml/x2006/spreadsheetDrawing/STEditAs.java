/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

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

public interface STEditAs
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STEditAs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("steditasad40type");
    public static final Enum TWO_CELL = Enum.forString("twoCell");
    public static final Enum ONE_CELL = Enum.forString("oneCell");
    public static final Enum ABSOLUTE = Enum.forString("absolute");
    public static final int INT_TWO_CELL = 1;
    public static final int INT_ONE_CELL = 2;
    public static final int INT_ABSOLUTE = 3;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STEditAs newValue(Object object) {
            return (STEditAs)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STEditAs.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STEditAs newInstance() {
            return (STEditAs)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STEditAs newInstance(XmlOptions xmlOptions) {
            return (STEditAs)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STEditAs parse(String string) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STEditAs parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STEditAs parse(File file) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STEditAs parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STEditAs parse(URL uRL) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STEditAs parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STEditAs parse(InputStream inputStream) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STEditAs parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STEditAs parse(Reader reader) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STEditAs parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STEditAs)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STEditAs parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STEditAs parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STEditAs parse(Node node) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STEditAs parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STEditAs)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STEditAs parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STEditAs)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STEditAs parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STEditAs)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_TWO_CELL = 1;
        static final int INT_ONE_CELL = 2;
        static final int INT_ABSOLUTE = 3;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("twoCell", 1), new Enum("oneCell", 2), new Enum("absolute", 3)});
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

