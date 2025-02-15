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

public interface STTextAlignType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextAlignType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextaligntypebc93type");
    public static final Enum L = Enum.forString("l");
    public static final Enum CTR = Enum.forString("ctr");
    public static final Enum R = Enum.forString("r");
    public static final Enum JUST = Enum.forString("just");
    public static final Enum JUST_LOW = Enum.forString("justLow");
    public static final Enum DIST = Enum.forString("dist");
    public static final Enum THAI_DIST = Enum.forString("thaiDist");
    public static final int INT_L = 1;
    public static final int INT_CTR = 2;
    public static final int INT_R = 3;
    public static final int INT_JUST = 4;
    public static final int INT_JUST_LOW = 5;
    public static final int INT_DIST = 6;
    public static final int INT_THAI_DIST = 7;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextAlignType newValue(Object object) {
            return (STTextAlignType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextAlignType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextAlignType newInstance() {
            return (STTextAlignType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextAlignType newInstance(XmlOptions xmlOptions) {
            return (STTextAlignType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextAlignType parse(String string) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextAlignType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextAlignType parse(File file) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextAlignType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextAlignType parse(URL uRL) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextAlignType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextAlignType parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextAlignType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextAlignType parse(Reader reader) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextAlignType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextAlignType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextAlignType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextAlignType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextAlignType parse(Node node) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextAlignType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextAlignType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextAlignType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextAlignType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextAlignType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextAlignType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_L = 1;
        static final int INT_CTR = 2;
        static final int INT_R = 3;
        static final int INT_JUST = 4;
        static final int INT_JUST_LOW = 5;
        static final int INT_DIST = 6;
        static final int INT_THAI_DIST = 7;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("l", 1), new Enum("ctr", 2), new Enum("r", 3), new Enum("just", 4), new Enum("justLow", 5), new Enum("dist", 6), new Enum("thaiDist", 7)});
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

