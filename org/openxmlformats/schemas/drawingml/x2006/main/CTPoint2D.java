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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.w3c.dom.Node;

public interface CTPoint2D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPoint2D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpoint2d8193type");

    public long getX();

    public STCoordinate xgetX();

    public void setX(long var1);

    public void xsetX(STCoordinate var1);

    public long getY();

    public STCoordinate xgetY();

    public void setY(long var1);

    public void xsetY(STCoordinate var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPoint2D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPoint2D newInstance() {
            return (CTPoint2D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPoint2D newInstance(XmlOptions xmlOptions) {
            return (CTPoint2D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPoint2D parse(String string) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPoint2D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPoint2D parse(File file) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPoint2D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPoint2D parse(URL uRL) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPoint2D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPoint2D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPoint2D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPoint2D parse(Reader reader) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPoint2D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPoint2D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPoint2D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPoint2D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPoint2D parse(Node node) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPoint2D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPoint2D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPoint2D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPoint2D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPoint2D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPoint2D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

