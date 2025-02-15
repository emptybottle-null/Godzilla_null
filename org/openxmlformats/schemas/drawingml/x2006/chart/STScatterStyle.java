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

public interface STScatterStyle
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STScatterStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stscatterstyle9eb9type");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum LINE = Enum.forString("line");
    public static final Enum LINE_MARKER = Enum.forString("lineMarker");
    public static final Enum MARKER = Enum.forString("marker");
    public static final Enum SMOOTH = Enum.forString("smooth");
    public static final Enum SMOOTH_MARKER = Enum.forString("smoothMarker");
    public static final int INT_NONE = 1;
    public static final int INT_LINE = 2;
    public static final int INT_LINE_MARKER = 3;
    public static final int INT_MARKER = 4;
    public static final int INT_SMOOTH = 5;
    public static final int INT_SMOOTH_MARKER = 6;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STScatterStyle newValue(Object object) {
            return (STScatterStyle)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STScatterStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STScatterStyle newInstance() {
            return (STScatterStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STScatterStyle newInstance(XmlOptions xmlOptions) {
            return (STScatterStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STScatterStyle parse(String string) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STScatterStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STScatterStyle parse(File file) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STScatterStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STScatterStyle parse(URL uRL) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STScatterStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STScatterStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STScatterStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STScatterStyle parse(Reader reader) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STScatterStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STScatterStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STScatterStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STScatterStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STScatterStyle parse(Node node) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STScatterStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STScatterStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STScatterStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STScatterStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STScatterStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STScatterStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_LINE = 2;
        static final int INT_LINE_MARKER = 3;
        static final int INT_MARKER = 4;
        static final int INT_SMOOTH = 5;
        static final int INT_SMOOTH_MARKER = 6;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("line", 2), new Enum("lineMarker", 3), new Enum("marker", 4), new Enum("smooth", 5), new Enum("smoothMarker", 6)});
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

