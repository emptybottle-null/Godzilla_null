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

public interface STOnOff
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STOnOff.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stonofffcd2type");
    public static final Enum TRUE = Enum.forString("true");
    public static final Enum FALSE = Enum.forString("false");
    public static final Enum ON = Enum.forString("on");
    public static final Enum OFF = Enum.forString("off");
    public static final Enum X_0 = Enum.forString("0");
    public static final Enum X_1 = Enum.forString("1");
    public static final int INT_TRUE = 1;
    public static final int INT_FALSE = 2;
    public static final int INT_ON = 3;
    public static final int INT_OFF = 4;
    public static final int INT_X_0 = 5;
    public static final int INT_X_1 = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STOnOff newValue(Object object) {
            return (STOnOff)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STOnOff.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STOnOff newInstance() {
            return (STOnOff)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STOnOff newInstance(XmlOptions xmlOptions) {
            return (STOnOff)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STOnOff parse(String string) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STOnOff parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STOnOff parse(File file) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STOnOff parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STOnOff parse(URL uRL) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STOnOff parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STOnOff parse(InputStream inputStream) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STOnOff parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STOnOff parse(Reader reader) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STOnOff parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STOnOff)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STOnOff parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STOnOff parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STOnOff parse(Node node) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STOnOff parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STOnOff)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STOnOff parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STOnOff)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STOnOff parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STOnOff)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_TRUE = 1;
        static final int INT_FALSE = 2;
        static final int INT_ON = 3;
        static final int INT_OFF = 4;
        static final int INT_X_0 = 5;
        static final int INT_X_1 = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("true", 1), new Enum("false", 2), new Enum("on", 3), new Enum("off", 4), new Enum("0", 5), new Enum("1", 6)});
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

