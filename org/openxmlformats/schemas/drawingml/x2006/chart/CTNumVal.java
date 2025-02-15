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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.STXstring;
import org.w3c.dom.Node;

public interface CTNumVal
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNumVal.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnumval2fe1type");

    public String getV();

    public STXstring xgetV();

    public void setV(String var1);

    public void xsetV(STXstring var1);

    public long getIdx();

    public XmlUnsignedInt xgetIdx();

    public void setIdx(long var1);

    public void xsetIdx(XmlUnsignedInt var1);

    public String getFormatCode();

    public STXstring xgetFormatCode();

    public boolean isSetFormatCode();

    public void setFormatCode(String var1);

    public void xsetFormatCode(STXstring var1);

    public void unsetFormatCode();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNumVal.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNumVal newInstance() {
            return (CTNumVal)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNumVal newInstance(XmlOptions xmlOptions) {
            return (CTNumVal)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNumVal parse(String string) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNumVal parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNumVal parse(File file) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNumVal parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNumVal parse(URL uRL) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNumVal parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNumVal parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNumVal parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNumVal parse(Reader reader) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNumVal parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumVal)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNumVal parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNumVal parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNumVal parse(Node node) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNumVal parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNumVal)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNumVal parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNumVal)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNumVal parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNumVal)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
}

