/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
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
import org.w3c.dom.Node;

public interface PageContentsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PageContentsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("pagecontentstypea5d0type");

    public ShapesType getShapes();

    public boolean isSetShapes();

    public void setShapes(ShapesType var1);

    public ShapesType addNewShapes();

    public void unsetShapes();

    public ConnectsType getConnects();

    public boolean isSetConnects();

    public void setConnects(ConnectsType var1);

    public ConnectsType addNewConnects();

    public void unsetConnects();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(PageContentsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static PageContentsType newInstance() {
            return (PageContentsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static PageContentsType newInstance(XmlOptions xmlOptions) {
            return (PageContentsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static PageContentsType parse(String string) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static PageContentsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static PageContentsType parse(File file) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static PageContentsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static PageContentsType parse(URL uRL) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static PageContentsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static PageContentsType parse(InputStream inputStream) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static PageContentsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static PageContentsType parse(Reader reader) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static PageContentsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageContentsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static PageContentsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static PageContentsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static PageContentsType parse(Node node) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static PageContentsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (PageContentsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static PageContentsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (PageContentsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static PageContentsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (PageContentsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

