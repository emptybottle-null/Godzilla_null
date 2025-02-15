/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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

public interface STShape
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STShape.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stshapecdf5type");
    public static final Enum CONE = Enum.forString("cone");
    public static final Enum CONE_TO_MAX = Enum.forString("coneToMax");
    public static final Enum BOX = Enum.forString("box");
    public static final Enum CYLINDER = Enum.forString("cylinder");
    public static final Enum PYRAMID = Enum.forString("pyramid");
    public static final Enum PYRAMID_TO_MAX = Enum.forString("pyramidToMax");
    public static final int INT_CONE = 1;
    public static final int INT_CONE_TO_MAX = 2;
    public static final int INT_BOX = 3;
    public static final int INT_CYLINDER = 4;
    public static final int INT_PYRAMID = 5;
    public static final int INT_PYRAMID_TO_MAX = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STShape newValue(Object object) {
            return (STShape)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STShape.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STShape newInstance() {
            return (STShape)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STShape newInstance(XmlOptions xmlOptions) {
            return (STShape)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STShape parse(String string) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STShape parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STShape parse(File file) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STShape parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STShape parse(URL uRL) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STShape parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STShape parse(InputStream inputStream) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STShape parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STShape parse(Reader reader) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STShape parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShape)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STShape parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STShape parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STShape parse(Node node) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STShape parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STShape)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STShape parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STShape)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STShape parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STShape)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_CONE = 1;
        static final int INT_CONE_TO_MAX = 2;
        static final int INT_BOX = 3;
        static final int INT_CYLINDER = 4;
        static final int INT_PYRAMID = 5;
        static final int INT_PYRAMID_TO_MAX = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("cone", 1), new Enum("coneToMax", 2), new Enum("box", 3), new Enum("cylinder", 4), new Enum("pyramid", 5), new Enum("pyramidToMax", 6)});
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

