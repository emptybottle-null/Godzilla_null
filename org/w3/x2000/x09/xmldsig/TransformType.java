/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TransformType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TransformType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("transformtype550btype");

    public List<String> getXPathList();

    @Deprecated
    public String[] getXPathArray();

    public String getXPathArray(int var1);

    public List<XmlString> xgetXPathList();

    @Deprecated
    public XmlString[] xgetXPathArray();

    public XmlString xgetXPathArray(int var1);

    public int sizeOfXPathArray();

    public void setXPathArray(String[] var1);

    public void setXPathArray(int var1, String var2);

    public void xsetXPathArray(XmlString[] var1);

    public void xsetXPathArray(int var1, XmlString var2);

    public void insertXPath(int var1, String var2);

    public void addXPath(String var1);

    public XmlString insertNewXPath(int var1);

    public XmlString addNewXPath();

    public void removeXPath(int var1);

    public String getAlgorithm();

    public XmlAnyURI xgetAlgorithm();

    public void setAlgorithm(String var1);

    public void xsetAlgorithm(XmlAnyURI var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(TransformType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static TransformType newInstance() {
            return (TransformType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static TransformType newInstance(XmlOptions xmlOptions) {
            return (TransformType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static TransformType parse(String string) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static TransformType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static TransformType parse(File file) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static TransformType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static TransformType parse(URL uRL) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static TransformType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static TransformType parse(InputStream inputStream) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static TransformType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static TransformType parse(Reader reader) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static TransformType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TransformType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static TransformType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static TransformType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static TransformType parse(Node node) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static TransformType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (TransformType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static TransformType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (TransformType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static TransformType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (TransformType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

