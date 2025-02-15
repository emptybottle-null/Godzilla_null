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

public interface STPaneState
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPaneState.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stpanestateae58type");
    public static final Enum SPLIT = Enum.forString("split");
    public static final Enum FROZEN = Enum.forString("frozen");
    public static final Enum FROZEN_SPLIT = Enum.forString("frozenSplit");
    public static final int INT_SPLIT = 1;
    public static final int INT_FROZEN = 2;
    public static final int INT_FROZEN_SPLIT = 3;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPaneState newValue(Object object) {
            return (STPaneState)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPaneState.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPaneState newInstance() {
            return (STPaneState)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPaneState newInstance(XmlOptions xmlOptions) {
            return (STPaneState)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPaneState parse(String string) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPaneState parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPaneState parse(File file) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPaneState parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPaneState parse(URL uRL) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPaneState parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPaneState parse(InputStream inputStream) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPaneState parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPaneState parse(Reader reader) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPaneState parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPaneState)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPaneState parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPaneState parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPaneState parse(Node node) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPaneState parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPaneState)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPaneState parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPaneState)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPaneState parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPaneState)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_SPLIT = 1;
        static final int INT_FROZEN = 2;
        static final int INT_FROZEN_SPLIT = 3;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("split", 1), new Enum("frozen", 2), new Enum("frozenSplit", 3)});
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

