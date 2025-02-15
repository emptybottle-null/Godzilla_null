/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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

public interface STMarkerStyle
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STMarkerStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stmarkerstyle177ftype");
    public static final Enum CIRCLE = Enum.forString("circle");
    public static final Enum DASH = Enum.forString("dash");
    public static final Enum DIAMOND = Enum.forString("diamond");
    public static final Enum DOT = Enum.forString("dot");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum PICTURE = Enum.forString("picture");
    public static final Enum PLUS = Enum.forString("plus");
    public static final Enum SQUARE = Enum.forString("square");
    public static final Enum STAR = Enum.forString("star");
    public static final Enum TRIANGLE = Enum.forString("triangle");
    public static final Enum X = Enum.forString("x");
    public static final int INT_CIRCLE = 1;
    public static final int INT_DASH = 2;
    public static final int INT_DIAMOND = 3;
    public static final int INT_DOT = 4;
    public static final int INT_NONE = 5;
    public static final int INT_PICTURE = 6;
    public static final int INT_PLUS = 7;
    public static final int INT_SQUARE = 8;
    public static final int INT_STAR = 9;
    public static final int INT_TRIANGLE = 10;
    public static final int INT_X = 11;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STMarkerStyle newValue(Object object) {
            return (STMarkerStyle)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STMarkerStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STMarkerStyle newInstance() {
            return (STMarkerStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STMarkerStyle newInstance(XmlOptions xmlOptions) {
            return (STMarkerStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STMarkerStyle parse(String string) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STMarkerStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STMarkerStyle parse(File file) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STMarkerStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STMarkerStyle parse(URL uRL) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STMarkerStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STMarkerStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STMarkerStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STMarkerStyle parse(Reader reader) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STMarkerStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STMarkerStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STMarkerStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STMarkerStyle parse(Node node) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STMarkerStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STMarkerStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STMarkerStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STMarkerStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_CIRCLE = 1;
        static final int INT_DASH = 2;
        static final int INT_DIAMOND = 3;
        static final int INT_DOT = 4;
        static final int INT_NONE = 5;
        static final int INT_PICTURE = 6;
        static final int INT_PLUS = 7;
        static final int INT_SQUARE = 8;
        static final int INT_STAR = 9;
        static final int INT_TRIANGLE = 10;
        static final int INT_X = 11;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("circle", 1), new Enum("dash", 2), new Enum("diamond", 3), new Enum("dot", 4), new Enum("none", 5), new Enum("picture", 6), new Enum("plus", 7), new Enum("square", 8), new Enum("star", 9), new Enum("triangle", 10), new Enum("x", 11)});
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

