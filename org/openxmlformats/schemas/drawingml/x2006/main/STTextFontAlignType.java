/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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

public interface STTextFontAlignType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextFontAlignType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextfontaligntypecb44type");
    public static final Enum AUTO = Enum.forString("auto");
    public static final Enum T = Enum.forString("t");
    public static final Enum CTR = Enum.forString("ctr");
    public static final Enum BASE = Enum.forString("base");
    public static final Enum B = Enum.forString("b");
    public static final int INT_AUTO = 1;
    public static final int INT_T = 2;
    public static final int INT_CTR = 3;
    public static final int INT_BASE = 4;
    public static final int INT_B = 5;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextFontAlignType newValue(Object object) {
            return (STTextFontAlignType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextFontAlignType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextFontAlignType newInstance() {
            return (STTextFontAlignType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextFontAlignType newInstance(XmlOptions xmlOptions) {
            return (STTextFontAlignType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextFontAlignType parse(String string) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextFontAlignType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextFontAlignType parse(File file) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextFontAlignType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextFontAlignType parse(URL uRL) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextFontAlignType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextFontAlignType parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextFontAlignType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextFontAlignType parse(Reader reader) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextFontAlignType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextFontAlignType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextFontAlignType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextFontAlignType parse(Node node) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextFontAlignType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextFontAlignType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextFontAlignType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextFontAlignType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_AUTO = 1;
        static final int INT_T = 2;
        static final int INT_CTR = 3;
        static final int INT_BASE = 4;
        static final int INT_B = 5;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("auto", 1), new Enum("t", 2), new Enum("ctr", 3), new Enum("base", 4), new Enum("b", 5)});
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

