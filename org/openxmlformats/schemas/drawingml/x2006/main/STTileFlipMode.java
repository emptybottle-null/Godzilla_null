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

public interface STTileFlipMode
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTileFlipMode.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttileflipmode2429type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum X = Enum.forString("x");
    public static final Enum Y = Enum.forString("y");
    public static final Enum XY = Enum.forString("xy");
    public static final int INT_NONE = 1;
    public static final int INT_X = 2;
    public static final int INT_Y = 3;
    public static final int INT_XY = 4;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTileFlipMode newValue(Object object) {
            return (STTileFlipMode)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTileFlipMode.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTileFlipMode newInstance() {
            return (STTileFlipMode)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTileFlipMode newInstance(XmlOptions xmlOptions) {
            return (STTileFlipMode)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTileFlipMode parse(String string) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTileFlipMode parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTileFlipMode parse(File file) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTileFlipMode parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTileFlipMode parse(URL uRL) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTileFlipMode parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTileFlipMode parse(InputStream inputStream) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTileFlipMode parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTileFlipMode parse(Reader reader) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTileFlipMode parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTileFlipMode parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTileFlipMode parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTileFlipMode parse(Node node) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTileFlipMode parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTileFlipMode parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTileFlipMode parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTileFlipMode)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_X = 2;
        static final int INT_Y = 3;
        static final int INT_XY = 4;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("x", 2), new Enum("y", 3), new Enum("xy", 4)});
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

