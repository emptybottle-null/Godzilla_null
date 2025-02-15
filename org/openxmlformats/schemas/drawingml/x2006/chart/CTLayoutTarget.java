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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;
import org.w3c.dom.Node;

public interface CTLayoutTarget
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLayoutTarget.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlayouttarget1001type");

    public STLayoutTarget.Enum getVal();

    public STLayoutTarget xgetVal();

    public boolean isSetVal();

    public void setVal(STLayoutTarget.Enum var1);

    public void xsetVal(STLayoutTarget var1);

    public void unsetVal();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLayoutTarget.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLayoutTarget newInstance() {
            return (CTLayoutTarget)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLayoutTarget newInstance(XmlOptions xmlOptions) {
            return (CTLayoutTarget)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLayoutTarget parse(String string) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLayoutTarget parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLayoutTarget parse(File file) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLayoutTarget parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLayoutTarget parse(URL uRL) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLayoutTarget parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLayoutTarget parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLayoutTarget parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLayoutTarget parse(Reader reader) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLayoutTarget parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLayoutTarget parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLayoutTarget parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLayoutTarget parse(Node node) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLayoutTarget parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLayoutTarget parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLayoutTarget parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLayoutTarget)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

