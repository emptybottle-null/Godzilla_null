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

public interface STColorSchemeIndex
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STColorSchemeIndex.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stcolorschemeindexe6f5type");
    public static final Enum DK_1 = Enum.forString("dk1");
    public static final Enum LT_1 = Enum.forString("lt1");
    public static final Enum DK_2 = Enum.forString("dk2");
    public static final Enum LT_2 = Enum.forString("lt2");
    public static final Enum ACCENT_1 = Enum.forString("accent1");
    public static final Enum ACCENT_2 = Enum.forString("accent2");
    public static final Enum ACCENT_3 = Enum.forString("accent3");
    public static final Enum ACCENT_4 = Enum.forString("accent4");
    public static final Enum ACCENT_5 = Enum.forString("accent5");
    public static final Enum ACCENT_6 = Enum.forString("accent6");
    public static final Enum HLINK = Enum.forString("hlink");
    public static final Enum FOL_HLINK = Enum.forString("folHlink");
    public static final int INT_DK_1 = 1;
    public static final int INT_LT_1 = 2;
    public static final int INT_DK_2 = 3;
    public static final int INT_LT_2 = 4;
    public static final int INT_ACCENT_1 = 5;
    public static final int INT_ACCENT_2 = 6;
    public static final int INT_ACCENT_3 = 7;
    public static final int INT_ACCENT_4 = 8;
    public static final int INT_ACCENT_5 = 9;
    public static final int INT_ACCENT_6 = 10;
    public static final int INT_HLINK = 11;
    public static final int INT_FOL_HLINK = 12;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STColorSchemeIndex newValue(Object object) {
            return (STColorSchemeIndex)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STColorSchemeIndex.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STColorSchemeIndex newInstance() {
            return (STColorSchemeIndex)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STColorSchemeIndex newInstance(XmlOptions xmlOptions) {
            return (STColorSchemeIndex)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STColorSchemeIndex parse(String string) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STColorSchemeIndex parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(File file) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STColorSchemeIndex parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(URL uRL) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STColorSchemeIndex parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(InputStream inputStream) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STColorSchemeIndex parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(Reader reader) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STColorSchemeIndex parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STColorSchemeIndex parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STColorSchemeIndex parse(Node node) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STColorSchemeIndex parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STColorSchemeIndex parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STColorSchemeIndex parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STColorSchemeIndex)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DK_1 = 1;
        static final int INT_LT_1 = 2;
        static final int INT_DK_2 = 3;
        static final int INT_LT_2 = 4;
        static final int INT_ACCENT_1 = 5;
        static final int INT_ACCENT_2 = 6;
        static final int INT_ACCENT_3 = 7;
        static final int INT_ACCENT_4 = 8;
        static final int INT_ACCENT_5 = 9;
        static final int INT_ACCENT_6 = 10;
        static final int INT_HLINK = 11;
        static final int INT_FOL_HLINK = 12;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("dk1", 1), new Enum("lt1", 2), new Enum("dk2", 3), new Enum("lt2", 4), new Enum("accent1", 5), new Enum("accent2", 6), new Enum("accent3", 7), new Enum("accent4", 8), new Enum("accent5", 9), new Enum("accent6", 10), new Enum("hlink", 11), new Enum("folHlink", 12)});
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

