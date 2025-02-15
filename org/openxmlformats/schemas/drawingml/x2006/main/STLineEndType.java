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

public interface STLineEndType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STLineEndType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stlineendtype8902type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum TRIANGLE = Enum.forString("triangle");
    public static final Enum STEALTH = Enum.forString("stealth");
    public static final Enum DIAMOND = Enum.forString("diamond");
    public static final Enum OVAL = Enum.forString("oval");
    public static final Enum ARROW = Enum.forString("arrow");
    public static final int INT_NONE = 1;
    public static final int INT_TRIANGLE = 2;
    public static final int INT_STEALTH = 3;
    public static final int INT_DIAMOND = 4;
    public static final int INT_OVAL = 5;
    public static final int INT_ARROW = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STLineEndType newValue(Object object) {
            return (STLineEndType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STLineEndType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STLineEndType newInstance() {
            return (STLineEndType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STLineEndType newInstance(XmlOptions xmlOptions) {
            return (STLineEndType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STLineEndType parse(String string) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STLineEndType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STLineEndType parse(File file) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STLineEndType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STLineEndType parse(URL uRL) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STLineEndType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STLineEndType parse(InputStream inputStream) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STLineEndType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STLineEndType parse(Reader reader) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STLineEndType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STLineEndType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STLineEndType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STLineEndType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STLineEndType parse(Node node) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STLineEndType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STLineEndType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STLineEndType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STLineEndType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STLineEndType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STLineEndType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_TRIANGLE = 2;
        static final int INT_STEALTH = 3;
        static final int INT_DIAMOND = 4;
        static final int INT_OVAL = 5;
        static final int INT_ARROW = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("triangle", 2), new Enum("stealth", 3), new Enum("diamond", 4), new Enum("oval", 5), new Enum("arrow", 6)});
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

