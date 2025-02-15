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

public interface STTextStrikeType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextStrikeType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextstriketype4744type");
    public static final Enum NO_STRIKE = Enum.forString("noStrike");
    public static final Enum SNG_STRIKE = Enum.forString("sngStrike");
    public static final Enum DBL_STRIKE = Enum.forString("dblStrike");
    public static final int INT_NO_STRIKE = 1;
    public static final int INT_SNG_STRIKE = 2;
    public static final int INT_DBL_STRIKE = 3;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextStrikeType newValue(Object object) {
            return (STTextStrikeType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextStrikeType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextStrikeType newInstance() {
            return (STTextStrikeType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextStrikeType newInstance(XmlOptions xmlOptions) {
            return (STTextStrikeType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextStrikeType parse(String string) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextStrikeType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextStrikeType parse(File file) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextStrikeType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextStrikeType parse(URL uRL) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextStrikeType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextStrikeType parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextStrikeType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextStrikeType parse(Reader reader) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextStrikeType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextStrikeType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextStrikeType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextStrikeType parse(Node node) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextStrikeType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextStrikeType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextStrikeType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextStrikeType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NO_STRIKE = 1;
        static final int INT_SNG_STRIKE = 2;
        static final int INT_DBL_STRIKE = 3;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("noStrike", 1), new Enum("sngStrike", 2), new Enum("dblStrike", 3)});
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

