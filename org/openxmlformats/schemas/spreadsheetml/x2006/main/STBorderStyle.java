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

public interface STBorderStyle
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STBorderStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stborderstylec774type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum THIN = Enum.forString("thin");
    public static final Enum MEDIUM = Enum.forString("medium");
    public static final Enum DASHED = Enum.forString("dashed");
    public static final Enum DOTTED = Enum.forString("dotted");
    public static final Enum THICK = Enum.forString("thick");
    public static final Enum DOUBLE = Enum.forString("double");
    public static final Enum HAIR = Enum.forString("hair");
    public static final Enum MEDIUM_DASHED = Enum.forString("mediumDashed");
    public static final Enum DASH_DOT = Enum.forString("dashDot");
    public static final Enum MEDIUM_DASH_DOT = Enum.forString("mediumDashDot");
    public static final Enum DASH_DOT_DOT = Enum.forString("dashDotDot");
    public static final Enum MEDIUM_DASH_DOT_DOT = Enum.forString("mediumDashDotDot");
    public static final Enum SLANT_DASH_DOT = Enum.forString("slantDashDot");
    public static final int INT_NONE = 1;
    public static final int INT_THIN = 2;
    public static final int INT_MEDIUM = 3;
    public static final int INT_DASHED = 4;
    public static final int INT_DOTTED = 5;
    public static final int INT_THICK = 6;
    public static final int INT_DOUBLE = 7;
    public static final int INT_HAIR = 8;
    public static final int INT_MEDIUM_DASHED = 9;
    public static final int INT_DASH_DOT = 10;
    public static final int INT_MEDIUM_DASH_DOT = 11;
    public static final int INT_DASH_DOT_DOT = 12;
    public static final int INT_MEDIUM_DASH_DOT_DOT = 13;
    public static final int INT_SLANT_DASH_DOT = 14;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STBorderStyle newValue(Object object) {
            return (STBorderStyle)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STBorderStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STBorderStyle newInstance() {
            return (STBorderStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STBorderStyle newInstance(XmlOptions xmlOptions) {
            return (STBorderStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STBorderStyle parse(String string) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STBorderStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STBorderStyle parse(File file) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STBorderStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STBorderStyle parse(URL uRL) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STBorderStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STBorderStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STBorderStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STBorderStyle parse(Reader reader) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STBorderStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STBorderStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STBorderStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STBorderStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STBorderStyle parse(Node node) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STBorderStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STBorderStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STBorderStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STBorderStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STBorderStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STBorderStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_THIN = 2;
        static final int INT_MEDIUM = 3;
        static final int INT_DASHED = 4;
        static final int INT_DOTTED = 5;
        static final int INT_THICK = 6;
        static final int INT_DOUBLE = 7;
        static final int INT_HAIR = 8;
        static final int INT_MEDIUM_DASHED = 9;
        static final int INT_DASH_DOT = 10;
        static final int INT_MEDIUM_DASH_DOT = 11;
        static final int INT_DASH_DOT_DOT = 12;
        static final int INT_MEDIUM_DASH_DOT_DOT = 13;
        static final int INT_SLANT_DASH_DOT = 14;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("thin", 2), new Enum("medium", 3), new Enum("dashed", 4), new Enum("dotted", 5), new Enum("thick", 6), new Enum("double", 7), new Enum("hair", 8), new Enum("mediumDashed", 9), new Enum("dashDot", 10), new Enum("mediumDashDot", 11), new Enum("dashDotDot", 12), new Enum("mediumDashDotDot", 13), new Enum("slantDashDot", 14)});
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

