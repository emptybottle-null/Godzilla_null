/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;
import org.w3c.dom.Node;

public interface CTGraphicalObjectFrame
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGraphicalObjectFrame.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgraphicalobjectframe536ftype");

    public CTGraphicalObjectFrameNonVisual getNvGraphicFramePr();

    public void setNvGraphicFramePr(CTGraphicalObjectFrameNonVisual var1);

    public CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr();

    public CTTransform2D getXfrm();

    public void setXfrm(CTTransform2D var1);

    public CTTransform2D addNewXfrm();

    public CTGraphicalObject getGraphic();

    public void setGraphic(CTGraphicalObject var1);

    public CTGraphicalObject addNewGraphic();

    public String getMacro();

    public XmlString xgetMacro();

    public boolean isSetMacro();

    public void setMacro(String var1);

    public void xsetMacro(XmlString var1);

    public void unsetMacro();

    public boolean getFPublished();

    public XmlBoolean xgetFPublished();

    public boolean isSetFPublished();

    public void setFPublished(boolean var1);

    public void xsetFPublished(XmlBoolean var1);

    public void unsetFPublished();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGraphicalObjectFrame.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGraphicalObjectFrame newInstance() {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGraphicalObjectFrame newInstance(XmlOptions xmlOptions) {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(String string) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGraphicalObjectFrame parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(File file) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGraphicalObjectFrame parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(URL uRL) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGraphicalObjectFrame parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGraphicalObjectFrame parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(Reader reader) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGraphicalObjectFrame parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGraphicalObjectFrame parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGraphicalObjectFrame parse(Node node) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGraphicalObjectFrame parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGraphicalObjectFrame parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGraphicalObjectFrame parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGraphicalObjectFrame)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

