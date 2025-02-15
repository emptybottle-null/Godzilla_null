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

public interface STTextVerticalType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextVerticalType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextverticaltyped988type");
    public static final Enum HORZ = Enum.forString("horz");
    public static final Enum VERT = Enum.forString("vert");
    public static final Enum VERT_270 = Enum.forString("vert270");
    public static final Enum WORD_ART_VERT = Enum.forString("wordArtVert");
    public static final Enum EA_VERT = Enum.forString("eaVert");
    public static final Enum MONGOLIAN_VERT = Enum.forString("mongolianVert");
    public static final Enum WORD_ART_VERT_RTL = Enum.forString("wordArtVertRtl");
    public static final int INT_HORZ = 1;
    public static final int INT_VERT = 2;
    public static final int INT_VERT_270 = 3;
    public static final int INT_WORD_ART_VERT = 4;
    public static final int INT_EA_VERT = 5;
    public static final int INT_MONGOLIAN_VERT = 6;
    public static final int INT_WORD_ART_VERT_RTL = 7;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextVerticalType newValue(Object object) {
            return (STTextVerticalType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextVerticalType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextVerticalType newInstance() {
            return (STTextVerticalType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextVerticalType newInstance(XmlOptions xmlOptions) {
            return (STTextVerticalType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextVerticalType parse(String string) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextVerticalType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextVerticalType parse(File file) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextVerticalType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextVerticalType parse(URL uRL) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextVerticalType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextVerticalType parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextVerticalType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextVerticalType parse(Reader reader) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextVerticalType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextVerticalType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextVerticalType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextVerticalType parse(Node node) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextVerticalType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextVerticalType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextVerticalType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextVerticalType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_HORZ = 1;
        static final int INT_VERT = 2;
        static final int INT_VERT_270 = 3;
        static final int INT_WORD_ART_VERT = 4;
        static final int INT_EA_VERT = 5;
        static final int INT_MONGOLIAN_VERT = 6;
        static final int INT_WORD_ART_VERT_RTL = 7;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("horz", 1), new Enum("vert", 2), new Enum("vert270", 3), new Enum("wordArtVert", 4), new Enum("eaVert", 5), new Enum("mongolianVert", 6), new Enum("wordArtVertRtl", 7)});
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

