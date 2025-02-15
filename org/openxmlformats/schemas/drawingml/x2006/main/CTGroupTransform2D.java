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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STAngle;
import org.w3c.dom.Node;

public interface CTGroupTransform2D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGroupTransform2D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgrouptransform2d411atype");

    public CTPoint2D getOff();

    public boolean isSetOff();

    public void setOff(CTPoint2D var1);

    public CTPoint2D addNewOff();

    public void unsetOff();

    public CTPositiveSize2D getExt();

    public boolean isSetExt();

    public void setExt(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewExt();

    public void unsetExt();

    public CTPoint2D getChOff();

    public boolean isSetChOff();

    public void setChOff(CTPoint2D var1);

    public CTPoint2D addNewChOff();

    public void unsetChOff();

    public CTPositiveSize2D getChExt();

    public boolean isSetChExt();

    public void setChExt(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewChExt();

    public void unsetChExt();

    public int getRot();

    public STAngle xgetRot();

    public boolean isSetRot();

    public void setRot(int var1);

    public void xsetRot(STAngle var1);

    public void unsetRot();

    public boolean getFlipH();

    public XmlBoolean xgetFlipH();

    public boolean isSetFlipH();

    public void setFlipH(boolean var1);

    public void xsetFlipH(XmlBoolean var1);

    public void unsetFlipH();

    public boolean getFlipV();

    public XmlBoolean xgetFlipV();

    public boolean isSetFlipV();

    public void setFlipV(boolean var1);

    public void xsetFlipV(XmlBoolean var1);

    public void unsetFlipV();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGroupTransform2D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGroupTransform2D newInstance() {
            return (CTGroupTransform2D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGroupTransform2D newInstance(XmlOptions xmlOptions) {
            return (CTGroupTransform2D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGroupTransform2D parse(String string) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGroupTransform2D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(File file) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGroupTransform2D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(URL uRL) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGroupTransform2D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGroupTransform2D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(Reader reader) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGroupTransform2D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGroupTransform2D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGroupTransform2D parse(Node node) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGroupTransform2D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGroupTransform2D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGroupTransform2D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGroupTransform2D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

