/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent
 */
package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;
import org.w3c.dom.Node;

public interface CTInline
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTInline.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctinline5726type");

    public CTPositiveSize2D getExtent();

    public void setExtent(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewExtent();

    public CTEffectExtent getEffectExtent();

    public boolean isSetEffectExtent();

    public void setEffectExtent(CTEffectExtent var1);

    public CTEffectExtent addNewEffectExtent();

    public void unsetEffectExtent();

    public CTNonVisualDrawingProps getDocPr();

    public void setDocPr(CTNonVisualDrawingProps var1);

    public CTNonVisualDrawingProps addNewDocPr();

    public CTNonVisualGraphicFrameProperties getCNvGraphicFramePr();

    public boolean isSetCNvGraphicFramePr();

    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties var1);

    public CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr();

    public void unsetCNvGraphicFramePr();

    public CTGraphicalObject getGraphic();

    public void setGraphic(CTGraphicalObject var1);

    public CTGraphicalObject addNewGraphic();

    public long getDistT();

    public STWrapDistance xgetDistT();

    public boolean isSetDistT();

    public void setDistT(long var1);

    public void xsetDistT(STWrapDistance var1);

    public void unsetDistT();

    public long getDistB();

    public STWrapDistance xgetDistB();

    public boolean isSetDistB();

    public void setDistB(long var1);

    public void xsetDistB(STWrapDistance var1);

    public void unsetDistB();

    public long getDistL();

    public STWrapDistance xgetDistL();

    public boolean isSetDistL();

    public void setDistL(long var1);

    public void xsetDistL(STWrapDistance var1);

    public void unsetDistL();

    public long getDistR();

    public STWrapDistance xgetDistR();

    public boolean isSetDistR();

    public void setDistR(long var1);

    public void xsetDistR(STWrapDistance var1);

    public void unsetDistR();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTInline.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTInline newInstance() {
            return (CTInline)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTInline newInstance(XmlOptions xmlOptions) {
            return (CTInline)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTInline parse(String string) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTInline parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTInline parse(File file) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTInline parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTInline parse(URL uRL) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTInline parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTInline parse(InputStream inputStream) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTInline parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTInline parse(Reader reader) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTInline parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInline)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTInline parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTInline parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTInline parse(Node node) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTInline parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTInline)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTInline parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTInline)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTInline parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTInline)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

