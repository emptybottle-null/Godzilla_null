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

public interface STIconSetType
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STIconSetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sticonsettype6112type");
    public static final Enum X_3_ARROWS = Enum.forString("3Arrows");
    public static final Enum X_3_ARROWS_GRAY = Enum.forString("3ArrowsGray");
    public static final Enum X_3_FLAGS = Enum.forString("3Flags");
    public static final Enum X_3_TRAFFIC_LIGHTS_1 = Enum.forString("3TrafficLights1");
    public static final Enum X_3_TRAFFIC_LIGHTS_2 = Enum.forString("3TrafficLights2");
    public static final Enum X_3_SIGNS = Enum.forString("3Signs");
    public static final Enum X_3_SYMBOLS = Enum.forString("3Symbols");
    public static final Enum X_3_SYMBOLS_2 = Enum.forString("3Symbols2");
    public static final Enum X_4_ARROWS = Enum.forString("4Arrows");
    public static final Enum X_4_ARROWS_GRAY = Enum.forString("4ArrowsGray");
    public static final Enum X_4_RED_TO_BLACK = Enum.forString("4RedToBlack");
    public static final Enum X_4_RATING = Enum.forString("4Rating");
    public static final Enum X_4_TRAFFIC_LIGHTS = Enum.forString("4TrafficLights");
    public static final Enum X_5_ARROWS = Enum.forString("5Arrows");
    public static final Enum X_5_ARROWS_GRAY = Enum.forString("5ArrowsGray");
    public static final Enum X_5_RATING = Enum.forString("5Rating");
    public static final Enum X_5_QUARTERS = Enum.forString("5Quarters");
    public static final int INT_X_3_ARROWS = 1;
    public static final int INT_X_3_ARROWS_GRAY = 2;
    public static final int INT_X_3_FLAGS = 3;
    public static final int INT_X_3_TRAFFIC_LIGHTS_1 = 4;
    public static final int INT_X_3_TRAFFIC_LIGHTS_2 = 5;
    public static final int INT_X_3_SIGNS = 6;
    public static final int INT_X_3_SYMBOLS = 7;
    public static final int INT_X_3_SYMBOLS_2 = 8;
    public static final int INT_X_4_ARROWS = 9;
    public static final int INT_X_4_ARROWS_GRAY = 10;
    public static final int INT_X_4_RED_TO_BLACK = 11;
    public static final int INT_X_4_RATING = 12;
    public static final int INT_X_4_TRAFFIC_LIGHTS = 13;
    public static final int INT_X_5_ARROWS = 14;
    public static final int INT_X_5_ARROWS_GRAY = 15;
    public static final int INT_X_5_RATING = 16;
    public static final int INT_X_5_QUARTERS = 17;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STIconSetType newValue(Object object) {
            return (STIconSetType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STIconSetType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STIconSetType newInstance() {
            return (STIconSetType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STIconSetType newInstance(XmlOptions xmlOptions) {
            return (STIconSetType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STIconSetType parse(String string) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STIconSetType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STIconSetType parse(File file) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STIconSetType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STIconSetType parse(URL uRL) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STIconSetType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STIconSetType parse(InputStream inputStream) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STIconSetType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STIconSetType parse(Reader reader) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STIconSetType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STIconSetType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STIconSetType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STIconSetType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STIconSetType parse(Node node) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STIconSetType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STIconSetType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STIconSetType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STIconSetType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STIconSetType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STIconSetType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_X_3_ARROWS = 1;
        static final int INT_X_3_ARROWS_GRAY = 2;
        static final int INT_X_3_FLAGS = 3;
        static final int INT_X_3_TRAFFIC_LIGHTS_1 = 4;
        static final int INT_X_3_TRAFFIC_LIGHTS_2 = 5;
        static final int INT_X_3_SIGNS = 6;
        static final int INT_X_3_SYMBOLS = 7;
        static final int INT_X_3_SYMBOLS_2 = 8;
        static final int INT_X_4_ARROWS = 9;
        static final int INT_X_4_ARROWS_GRAY = 10;
        static final int INT_X_4_RED_TO_BLACK = 11;
        static final int INT_X_4_RATING = 12;
        static final int INT_X_4_TRAFFIC_LIGHTS = 13;
        static final int INT_X_5_ARROWS = 14;
        static final int INT_X_5_ARROWS_GRAY = 15;
        static final int INT_X_5_RATING = 16;
        static final int INT_X_5_QUARTERS = 17;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("3Arrows", 1), new Enum("3ArrowsGray", 2), new Enum("3Flags", 3), new Enum("3TrafficLights1", 4), new Enum("3TrafficLights2", 5), new Enum("3Signs", 6), new Enum("3Symbols", 7), new Enum("3Symbols2", 8), new Enum("4Arrows", 9), new Enum("4ArrowsGray", 10), new Enum("4RedToBlack", 11), new Enum("4Rating", 12), new Enum("4TrafficLights", 13), new Enum("5Arrows", 14), new Enum("5ArrowsGray", 15), new Enum("5Rating", 16), new Enum("5Quarters", 17)});
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

