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

public interface STRectAlignment
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STRectAlignment.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("strectalignmentd400type");
    public static final Enum TL = Enum.forString("tl");
    public static final Enum T = Enum.forString("t");
    public static final Enum TR = Enum.forString("tr");
    public static final Enum L = Enum.forString("l");
    public static final Enum CTR = Enum.forString("ctr");
    public static final Enum R = Enum.forString("r");
    public static final Enum BL = Enum.forString("bl");
    public static final Enum B = Enum.forString("b");
    public static final Enum BR = Enum.forString("br");
    public static final int INT_TL = 1;
    public static final int INT_T = 2;
    public static final int INT_TR = 3;
    public static final int INT_L = 4;
    public static final int INT_CTR = 5;
    public static final int INT_R = 6;
    public static final int INT_BL = 7;
    public static final int INT_B = 8;
    public static final int INT_BR = 9;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STRectAlignment newValue(Object object) {
            return (STRectAlignment)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STRectAlignment.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STRectAlignment newInstance() {
            return (STRectAlignment)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STRectAlignment newInstance(XmlOptions xmlOptions) {
            return (STRectAlignment)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STRectAlignment parse(String string) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STRectAlignment parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STRectAlignment parse(File file) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STRectAlignment parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STRectAlignment parse(URL uRL) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STRectAlignment parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STRectAlignment parse(InputStream inputStream) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STRectAlignment parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STRectAlignment parse(Reader reader) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STRectAlignment parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STRectAlignment)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STRectAlignment parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STRectAlignment parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STRectAlignment parse(Node node) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STRectAlignment parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STRectAlignment)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STRectAlignment parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STRectAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STRectAlignment parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STRectAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_TL = 1;
        static final int INT_T = 2;
        static final int INT_TR = 3;
        static final int INT_L = 4;
        static final int INT_CTR = 5;
        static final int INT_R = 6;
        static final int INT_BL = 7;
        static final int INT_B = 8;
        static final int INT_BR = 9;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("tl", 1), new Enum("t", 2), new Enum("tr", 3), new Enum("l", 4), new Enum("ctr", 5), new Enum("r", 6), new Enum("bl", 7), new Enum("b", 8), new Enum("br", 9)});
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

