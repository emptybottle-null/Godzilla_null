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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;
import org.w3c.dom.Node;

public interface CTCustomGeometry2D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCustomGeometry2D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcustomgeometry2dca70type");

    public CTGeomGuideList getAvLst();

    public boolean isSetAvLst();

    public void setAvLst(CTGeomGuideList var1);

    public CTGeomGuideList addNewAvLst();

    public void unsetAvLst();

    public CTGeomGuideList getGdLst();

    public boolean isSetGdLst();

    public void setGdLst(CTGeomGuideList var1);

    public CTGeomGuideList addNewGdLst();

    public void unsetGdLst();

    public CTAdjustHandleList getAhLst();

    public boolean isSetAhLst();

    public void setAhLst(CTAdjustHandleList var1);

    public CTAdjustHandleList addNewAhLst();

    public void unsetAhLst();

    public CTConnectionSiteList getCxnLst();

    public boolean isSetCxnLst();

    public void setCxnLst(CTConnectionSiteList var1);

    public CTConnectionSiteList addNewCxnLst();

    public void unsetCxnLst();

    public CTGeomRect getRect();

    public boolean isSetRect();

    public void setRect(CTGeomRect var1);

    public CTGeomRect addNewRect();

    public void unsetRect();

    public CTPath2DList getPathLst();

    public void setPathLst(CTPath2DList var1);

    public CTPath2DList addNewPathLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCustomGeometry2D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCustomGeometry2D newInstance() {
            return (CTCustomGeometry2D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCustomGeometry2D newInstance(XmlOptions xmlOptions) {
            return (CTCustomGeometry2D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(String string) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCustomGeometry2D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(File file) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCustomGeometry2D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(URL uRL) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCustomGeometry2D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCustomGeometry2D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(Reader reader) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCustomGeometry2D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCustomGeometry2D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCustomGeometry2D parse(Node node) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCustomGeometry2D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCustomGeometry2D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCustomGeometry2D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCustomGeometry2D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

