/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;
import org.w3c.dom.Node;

public interface CTAnchor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAnchor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctanchorff8atype");

    public CTPoint2D getSimplePos();

    public void setSimplePos(CTPoint2D var1);

    public CTPoint2D addNewSimplePos();

    public CTPosH getPositionH();

    public void setPositionH(CTPosH var1);

    public CTPosH addNewPositionH();

    public CTPosV getPositionV();

    public void setPositionV(CTPosV var1);

    public CTPosV addNewPositionV();

    public CTPositiveSize2D getExtent();

    public void setExtent(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewExtent();

    public CTEffectExtent getEffectExtent();

    public boolean isSetEffectExtent();

    public void setEffectExtent(CTEffectExtent var1);

    public CTEffectExtent addNewEffectExtent();

    public void unsetEffectExtent();

    public CTWrapNone getWrapNone();

    public boolean isSetWrapNone();

    public void setWrapNone(CTWrapNone var1);

    public CTWrapNone addNewWrapNone();

    public void unsetWrapNone();

    public CTWrapSquare getWrapSquare();

    public boolean isSetWrapSquare();

    public void setWrapSquare(CTWrapSquare var1);

    public CTWrapSquare addNewWrapSquare();

    public void unsetWrapSquare();

    public CTWrapTight getWrapTight();

    public boolean isSetWrapTight();

    public void setWrapTight(CTWrapTight var1);

    public CTWrapTight addNewWrapTight();

    public void unsetWrapTight();

    public CTWrapThrough getWrapThrough();

    public boolean isSetWrapThrough();

    public void setWrapThrough(CTWrapThrough var1);

    public CTWrapThrough addNewWrapThrough();

    public void unsetWrapThrough();

    public CTWrapTopBottom getWrapTopAndBottom();

    public boolean isSetWrapTopAndBottom();

    public void setWrapTopAndBottom(CTWrapTopBottom var1);

    public CTWrapTopBottom addNewWrapTopAndBottom();

    public void unsetWrapTopAndBottom();

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

    public boolean getSimplePos2();

    public XmlBoolean xgetSimplePos2();

    public boolean isSetSimplePos2();

    public void setSimplePos2(boolean var1);

    public void xsetSimplePos2(XmlBoolean var1);

    public void unsetSimplePos2();

    public long getRelativeHeight();

    public XmlUnsignedInt xgetRelativeHeight();

    public void setRelativeHeight(long var1);

    public void xsetRelativeHeight(XmlUnsignedInt var1);

    public boolean getBehindDoc();

    public XmlBoolean xgetBehindDoc();

    public void setBehindDoc(boolean var1);

    public void xsetBehindDoc(XmlBoolean var1);

    public boolean getLocked();

    public XmlBoolean xgetLocked();

    public void setLocked(boolean var1);

    public void xsetLocked(XmlBoolean var1);

    public boolean getLayoutInCell();

    public XmlBoolean xgetLayoutInCell();

    public void setLayoutInCell(boolean var1);

    public void xsetLayoutInCell(XmlBoolean var1);

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public boolean getAllowOverlap();

    public XmlBoolean xgetAllowOverlap();

    public void setAllowOverlap(boolean var1);

    public void xsetAllowOverlap(XmlBoolean var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAnchor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAnchor newInstance() {
            return (CTAnchor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAnchor newInstance(XmlOptions xmlOptions) {
            return (CTAnchor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAnchor parse(String string) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAnchor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAnchor parse(File file) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAnchor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAnchor parse(URL uRL) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAnchor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAnchor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAnchor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAnchor parse(Reader reader) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAnchor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAnchor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAnchor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAnchor parse(Node node) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAnchor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAnchor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAnchor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAnchor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAnchor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

