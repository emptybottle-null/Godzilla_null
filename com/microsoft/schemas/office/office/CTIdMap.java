/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTIdMap
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTIdMap.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctidmap63fatype");

    public STExt.Enum getExt();

    public STExt xgetExt();

    public boolean isSetExt();

    public void setExt(STExt.Enum var1);

    public void xsetExt(STExt var1);

    public void unsetExt();

    public String getData();

    public XmlString xgetData();

    public boolean isSetData();

    public void setData(String var1);

    public void xsetData(XmlString var1);

    public void unsetData();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTIdMap.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTIdMap newInstance() {
            return (CTIdMap)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTIdMap newInstance(XmlOptions xmlOptions) {
            return (CTIdMap)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTIdMap parse(String string) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTIdMap parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTIdMap parse(File file) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTIdMap parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTIdMap parse(URL uRL) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTIdMap parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTIdMap parse(InputStream inputStream) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTIdMap parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTIdMap parse(Reader reader) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTIdMap parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIdMap)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTIdMap parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTIdMap parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTIdMap parse(Node node) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTIdMap parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTIdMap)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTIdMap parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTIdMap)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTIdMap parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTIdMap)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

