/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle
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
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;
import org.w3c.dom.Node;

public interface CTPolarAdjustHandle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPolarAdjustHandle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpolaradjusthandled0a6type");

    public CTAdjPoint2D getPos();

    public void setPos(CTAdjPoint2D var1);

    public CTAdjPoint2D addNewPos();

    public String getGdRefR();

    public STGeomGuideName xgetGdRefR();

    public boolean isSetGdRefR();

    public void setGdRefR(String var1);

    public void xsetGdRefR(STGeomGuideName var1);

    public void unsetGdRefR();

    public Object getMinR();

    public STAdjCoordinate xgetMinR();

    public boolean isSetMinR();

    public void setMinR(Object var1);

    public void xsetMinR(STAdjCoordinate var1);

    public void unsetMinR();

    public Object getMaxR();

    public STAdjCoordinate xgetMaxR();

    public boolean isSetMaxR();

    public void setMaxR(Object var1);

    public void xsetMaxR(STAdjCoordinate var1);

    public void unsetMaxR();

    public String getGdRefAng();

    public STGeomGuideName xgetGdRefAng();

    public boolean isSetGdRefAng();

    public void setGdRefAng(String var1);

    public void xsetGdRefAng(STGeomGuideName var1);

    public void unsetGdRefAng();

    public Object getMinAng();

    public STAdjAngle xgetMinAng();

    public boolean isSetMinAng();

    public void setMinAng(Object var1);

    public void xsetMinAng(STAdjAngle var1);

    public void unsetMinAng();

    public Object getMaxAng();

    public STAdjAngle xgetMaxAng();

    public boolean isSetMaxAng();

    public void setMaxAng(Object var1);

    public void xsetMaxAng(STAdjAngle var1);

    public void unsetMaxAng();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPolarAdjustHandle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPolarAdjustHandle newInstance() {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPolarAdjustHandle newInstance(XmlOptions xmlOptions) {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(String string) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPolarAdjustHandle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(File file) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPolarAdjustHandle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(URL uRL) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPolarAdjustHandle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPolarAdjustHandle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(Reader reader) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPolarAdjustHandle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPolarAdjustHandle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPolarAdjustHandle parse(Node node) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPolarAdjustHandle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPolarAdjustHandle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPolarAdjustHandle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPolarAdjustHandle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

