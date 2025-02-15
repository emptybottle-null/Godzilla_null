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

public interface STPresetLineDashVal
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPresetLineDashVal.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpresetlinedashval159dtype");
    public static final Enum SOLID = Enum.forString("solid");
    public static final Enum DOT = Enum.forString("dot");
    public static final Enum DASH = Enum.forString("dash");
    public static final Enum LG_DASH = Enum.forString("lgDash");
    public static final Enum DASH_DOT = Enum.forString("dashDot");
    public static final Enum LG_DASH_DOT = Enum.forString("lgDashDot");
    public static final Enum LG_DASH_DOT_DOT = Enum.forString("lgDashDotDot");
    public static final Enum SYS_DASH = Enum.forString("sysDash");
    public static final Enum SYS_DOT = Enum.forString("sysDot");
    public static final Enum SYS_DASH_DOT = Enum.forString("sysDashDot");
    public static final Enum SYS_DASH_DOT_DOT = Enum.forString("sysDashDotDot");
    public static final int INT_SOLID = 1;
    public static final int INT_DOT = 2;
    public static final int INT_DASH = 3;
    public static final int INT_LG_DASH = 4;
    public static final int INT_DASH_DOT = 5;
    public static final int INT_LG_DASH_DOT = 6;
    public static final int INT_LG_DASH_DOT_DOT = 7;
    public static final int INT_SYS_DASH = 8;
    public static final int INT_SYS_DOT = 9;
    public static final int INT_SYS_DASH_DOT = 10;
    public static final int INT_SYS_DASH_DOT_DOT = 11;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPresetLineDashVal newValue(Object object) {
            return (STPresetLineDashVal)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPresetLineDashVal.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPresetLineDashVal newInstance() {
            return (STPresetLineDashVal)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPresetLineDashVal newInstance(XmlOptions xmlOptions) {
            return (STPresetLineDashVal)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPresetLineDashVal parse(String string) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPresetLineDashVal parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(File file) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPresetLineDashVal parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(URL uRL) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPresetLineDashVal parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(InputStream inputStream) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPresetLineDashVal parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(Reader reader) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPresetLineDashVal parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPresetLineDashVal parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPresetLineDashVal parse(Node node) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPresetLineDashVal parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPresetLineDashVal parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPresetLineDashVal parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPresetLineDashVal)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_SOLID = 1;
        static final int INT_DOT = 2;
        static final int INT_DASH = 3;
        static final int INT_LG_DASH = 4;
        static final int INT_DASH_DOT = 5;
        static final int INT_LG_DASH_DOT = 6;
        static final int INT_LG_DASH_DOT_DOT = 7;
        static final int INT_SYS_DASH = 8;
        static final int INT_SYS_DOT = 9;
        static final int INT_SYS_DASH_DOT = 10;
        static final int INT_SYS_DASH_DOT_DOT = 11;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("solid", 1), new Enum("dot", 2), new Enum("dash", 3), new Enum("lgDash", 4), new Enum("dashDot", 5), new Enum("lgDashDot", 6), new Enum("lgDashDotDot", 7), new Enum("sysDash", 8), new Enum("sysDot", 9), new Enum("sysDashDot", 10), new Enum("sysDashDotDot", 11)});
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

