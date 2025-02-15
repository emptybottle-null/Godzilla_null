/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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

public interface STTabTlc
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTabTlc.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttabtlc6f42type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum DOT = Enum.forString("dot");
    public static final Enum HYPHEN = Enum.forString("hyphen");
    public static final Enum UNDERSCORE = Enum.forString("underscore");
    public static final Enum HEAVY = Enum.forString("heavy");
    public static final Enum MIDDLE_DOT = Enum.forString("middleDot");
    public static final int INT_NONE = 1;
    public static final int INT_DOT = 2;
    public static final int INT_HYPHEN = 3;
    public static final int INT_UNDERSCORE = 4;
    public static final int INT_HEAVY = 5;
    public static final int INT_MIDDLE_DOT = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTabTlc newValue(Object object) {
            return (STTabTlc)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTabTlc.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTabTlc newInstance() {
            return (STTabTlc)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTabTlc newInstance(XmlOptions xmlOptions) {
            return (STTabTlc)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTabTlc parse(String string) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTabTlc parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTabTlc parse(File file) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTabTlc parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTabTlc parse(URL uRL) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTabTlc parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTabTlc parse(InputStream inputStream) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTabTlc parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTabTlc parse(Reader reader) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTabTlc parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTabTlc)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTabTlc parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTabTlc parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTabTlc parse(Node node) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTabTlc parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTabTlc)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTabTlc parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTabTlc)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTabTlc parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTabTlc)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_DOT = 2;
        static final int INT_HYPHEN = 3;
        static final int INT_UNDERSCORE = 4;
        static final int INT_HEAVY = 5;
        static final int INT_MIDDLE_DOT = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("dot", 2), new Enum("hyphen", 3), new Enum("underscore", 4), new Enum("heavy", 5), new Enum("middleDot", 6)});
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

