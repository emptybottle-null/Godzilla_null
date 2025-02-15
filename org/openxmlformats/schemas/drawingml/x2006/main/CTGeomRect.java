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
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.w3c.dom.Node;

public interface CTGeomRect
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGeomRect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgeomrect53dbtype");

    public Object getL();

    public STAdjCoordinate xgetL();

    public void setL(Object var1);

    public void xsetL(STAdjCoordinate var1);

    public Object getT();

    public STAdjCoordinate xgetT();

    public void setT(Object var1);

    public void xsetT(STAdjCoordinate var1);

    public Object getR();

    public STAdjCoordinate xgetR();

    public void setR(Object var1);

    public void xsetR(STAdjCoordinate var1);

    public Object getB();

    public STAdjCoordinate xgetB();

    public void setB(Object var1);

    public void xsetB(STAdjCoordinate var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGeomRect.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGeomRect newInstance() {
            return (CTGeomRect)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGeomRect newInstance(XmlOptions xmlOptions) {
            return (CTGeomRect)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGeomRect parse(String string) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGeomRect parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGeomRect parse(File file) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGeomRect parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGeomRect parse(URL uRL) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGeomRect parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGeomRect parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGeomRect parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGeomRect parse(Reader reader) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGeomRect parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGeomRect)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGeomRect parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGeomRect parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGeomRect parse(Node node) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGeomRect parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGeomRect)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGeomRect parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGeomRect)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGeomRect parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGeomRect)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

