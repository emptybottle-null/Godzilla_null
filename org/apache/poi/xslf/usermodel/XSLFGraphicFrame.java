/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFObjectShape;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

public class XSLFGraphicFrame
extends XSLFShape
implements GraphicalFrame<XSLFShape, XSLFTextParagraph> {
    private static final String DRAWINGML_CHART_URI = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    private static final POILogger LOG = POILogFactory.getLogger(XSLFGraphicFrame.class);

    XSLFGraphicFrame(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    public ShapeType getShapeType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rectangle2D getAnchor() {
        CTTransform2D xfrm = ((CTGraphicalObjectFrame)this.getXmlObject()).getXfrm();
        CTPoint2D off = xfrm.getOff();
        double x = Units.toPoints(off.getX());
        double y = Units.toPoints(off.getY());
        CTPositiveSize2D ext = xfrm.getExt();
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override
    public void setAnchor(Rectangle2D anchor) {
        CTTransform2D xfrm = ((CTGraphicalObjectFrame)this.getXmlObject()).getXfrm();
        CTPoint2D off = xfrm.isSetOff() ? xfrm.getOff() : xfrm.addNewOff();
        long x = Units.toEMU(anchor.getX());
        long y = Units.toEMU(anchor.getY());
        off.setX(x);
        off.setY(y);
        CTPositiveSize2D ext = xfrm.isSetExt() ? xfrm.getExt() : xfrm.addNewExt();
        long cx = Units.toEMU(anchor.getWidth());
        long cy = Units.toEMU(anchor.getHeight());
        ext.setCx(cx);
        ext.setCy(cy);
    }

    static XSLFGraphicFrame create(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        String uri = XSLFGraphicFrame.getUri(shape);
        switch (uri == null ? "" : uri) {
            case "http://schemas.openxmlformats.org/drawingml/2006/table": {
                return new XSLFTable(shape, sheet);
            }
            case "http://schemas.openxmlformats.org/presentationml/2006/ole": {
                return new XSLFObjectShape(shape, sheet);
            }
        }
        return new XSLFGraphicFrame(shape, sheet);
    }

    private static String getUri(CTGraphicalObjectFrame shape) {
        CTGraphicalObject g = shape.getGraphic();
        if (g == null) {
            return null;
        }
        CTGraphicalObjectData gd = g.getGraphicData();
        return gd == null ? null : gd.getUri();
    }

    @Override
    public void setRotation(double theta) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override
    public double getRotation() {
        return 0.0;
    }

    @Override
    public void setFlipHorizontal(boolean flip) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override
    public void setFlipVertical(boolean flip) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override
    public boolean getFlipHorizontal() {
        return false;
    }

    @Override
    public boolean getFlipVertical() {
        return false;
    }

    public boolean hasChart() {
        String uri = this.getGraphicalData().getUri();
        return uri.equals(DRAWINGML_CHART_URI);
    }

    private CTGraphicalObjectData getGraphicalData() {
        return ((CTGraphicalObjectFrame)this.getXmlObject()).getGraphic().getGraphicData();
    }

    public XSLFChart getChart() {
        if (this.hasChart()) {
            String id = null;
            String xpath = "declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart";
            XmlObject[] obj = this.getGraphicalData().selectPath(xpath);
            if (obj != null && obj.length == 1) {
                XmlCursor c = obj[0].newCursor();
                QName idQualifiedName = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
                id = c.getAttributeText(idQualifiedName);
                c.dispose();
            }
            if (id == null) {
                return null;
            }
            return (XSLFChart)this.getSheet().getRelationById(id);
        }
        return null;
    }

    @Override
    void copy(XSLFShape sh) {
        super.copy(sh);
        CTGraphicalObjectData data = this.getGraphicalData();
        String uri = data.getUri();
        if (uri.equals("http://schemas.openxmlformats.org/drawingml/2006/diagram")) {
            this.copyDiagram(data, (XSLFGraphicFrame)sh);
        }
        if (uri.equals(DRAWINGML_CHART_URI)) {
            this.copyChart(data, (XSLFGraphicFrame)sh);
        }
    }

    private void copyChart(CTGraphicalObjectData objData, XSLFGraphicFrame srcShape) {
        XSLFSlide slide = (XSLFSlide)this.getSheet();
        XSLFSheet src = srcShape.getSheet();
        String xpath = "declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart";
        XmlObject[] obj = objData.selectPath(xpath);
        if (obj != null && obj.length == 1) {
            XmlCursor c = obj[0].newCursor();
            try {
                QName idQualifiedName = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
                String id = c.getAttributeText(idQualifiedName);
                XSLFChart srcChart = (XSLFChart)src.getRelationById(id);
                XSLFChart chartCopy = slide.getSlideShow().createChart(slide);
                chartCopy.importContent(srcChart);
                chartCopy.setWorkbook(srcChart.getWorkbook());
                c.setAttributeText(idQualifiedName, slide.getRelationId(chartCopy));
            } catch (IOException | InvalidFormatException e) {
                throw new POIXMLException(e);
            }
            c.dispose();
        }
    }

    private void copyDiagram(CTGraphicalObjectData objData, XSLFGraphicFrame srcShape) {
        String xpath = "declare namespace dgm='http://schemas.openxmlformats.org/drawingml/2006/diagram' $this//dgm:relIds";
        XmlObject[] obj = objData.selectPath(xpath);
        if (obj != null && obj.length == 1) {
            XmlCursor c = obj[0].newCursor();
            XSLFSheet sheet = srcShape.getSheet();
            try {
                String dm = c.getAttributeText(new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "dm"));
                PackageRelationship dmRel = sheet.getPackagePart().getRelationship(dm);
                PackagePart dmPart = sheet.getPackagePart().getRelatedPart(dmRel);
                this.getSheet().importPart(dmRel, dmPart);
                String lo = c.getAttributeText(new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "lo"));
                PackageRelationship loRel = sheet.getPackagePart().getRelationship(lo);
                PackagePart loPart = sheet.getPackagePart().getRelatedPart(loRel);
                this.getSheet().importPart(loRel, loPart);
                String qs = c.getAttributeText(new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "qs"));
                PackageRelationship qsRel = sheet.getPackagePart().getRelationship(qs);
                PackagePart qsPart = sheet.getPackagePart().getRelatedPart(qsRel);
                this.getSheet().importPart(qsRel, qsPart);
                String cs = c.getAttributeText(new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "cs"));
                PackageRelationship csRel = sheet.getPackagePart().getRelationship(cs);
                PackagePart csPart = sheet.getPackagePart().getRelatedPart(csRel);
                this.getSheet().importPart(csRel, csPart);
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
            c.dispose();
        }
    }

    public XSLFPictureShape getFallbackPicture() {
        CTGroupShape gs;
        String xquery = "declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main'; declare namespace mc='http://schemas.openxmlformats.org/markup-compatibility/2006' .//mc:Fallback/*/p:pic";
        XmlObject xo = this.selectProperty(XmlObject.class, xquery);
        if (xo == null) {
            return null;
        }
        try {
            gs = CTGroupShape.Factory.parse(xo.newDomNode());
        } catch (XmlException e) {
            LOG.log(5, "Can't parse fallback picture stream of graphical frame", e);
            return null;
        }
        if (gs.sizeOfPicArray() == 0) {
            return null;
        }
        return new XSLFPictureShape(gs.getPicArray(0), this.getSheet());
    }
}

