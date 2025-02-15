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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;
import org.w3c.dom.Node;

public interface CTXYAdjustHandle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTXYAdjustHandle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctxyadjusthandlefaf3type");

    public CTAdjPoint2D getPos();

    public void setPos(CTAdjPoint2D var1);

    public CTAdjPoint2D addNewPos();

    public String getGdRefX();

    public STGeomGuideName xgetGdRefX();

    public boolean isSetGdRefX();

    public void setGdRefX(String var1);

    public void xsetGdRefX(STGeomGuideName var1);

    public void unsetGdRefX();

    public Object getMinX();

    public STAdjCoordinate xgetMinX();

    public boolean isSetMinX();

    public void setMinX(Object var1);

    public void xsetMinX(STAdjCoordinate var1);

    public void unsetMinX();

    public Object getMaxX();

    public STAdjCoordinate xgetMaxX();

    public boolean isSetMaxX();

    public void setMaxX(Object var1);

    public void xsetMaxX(STAdjCoordinate var1);

    public void unsetMaxX();

    public String getGdRefY();

    public STGeomGuideName xgetGdRefY();

    public boolean isSetGdRefY();

    public void setGdRefY(String var1);

    public void xsetGdRefY(STGeomGuideName var1);

    public void unsetGdRefY();

    public Object getMinY();

    public STAdjCoordinate xgetMinY();

    public boolean isSetMinY();

    public void setMinY(Object var1);

    public void xsetMinY(STAdjCoordinate var1);

    public void unsetMinY();

    public Object getMaxY();

    public STAdjCoordinate xgetMaxY();

    public boolean isSetMaxY();

    public void setMaxY(Object var1);

    public void xsetMaxY(STAdjCoordinate var1);

    public void unsetMaxY();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTXYAdjustHandle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTXYAdjustHandle newInstance() {
            return (CTXYAdjustHandle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTXYAdjustHandle newInstance(XmlOptions xmlOptions) {
            return (CTXYAdjustHandle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(String string) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTXYAdjustHandle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(File file) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTXYAdjustHandle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(URL uRL) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTXYAdjustHandle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTXYAdjustHandle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(Reader reader) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTXYAdjustHandle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTXYAdjustHandle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTXYAdjustHandle parse(Node node) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTXYAdjustHandle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTXYAdjustHandle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTXYAdjustHandle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTXYAdjustHandle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

