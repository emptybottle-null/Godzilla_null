/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFDrawing;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFObjectShape;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFShapeContainer;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public class XSLFGroupShape
extends XSLFShape
implements XSLFShapeContainer,
GroupShape<XSLFShape, XSLFTextParagraph> {
    private static final POILogger _logger = POILogFactory.getLogger(XSLFGroupShape.class);
    private final List<XSLFShape> _shapes;
    private final CTGroupShapeProperties _grpSpPr;
    private XSLFDrawing _drawing;

    protected XSLFGroupShape(CTGroupShape shape, XSLFSheet sheet) {
        super(shape, sheet);
        this._shapes = XSLFSheet.buildShapes(shape, this);
        this._grpSpPr = shape.getGrpSpPr();
    }

    @Override
    protected CTGroupShapeProperties getGrpSpPr() {
        return this._grpSpPr;
    }

    private CTGroupTransform2D getSafeXfrm() {
        CTGroupTransform2D xfrm = this.getXfrm();
        return xfrm == null ? this.getGrpSpPr().addNewXfrm() : xfrm;
    }

    protected CTGroupTransform2D getXfrm() {
        return this.getGrpSpPr().getXfrm();
    }

    @Override
    public Rectangle2D getAnchor() {
        CTGroupTransform2D xfrm = this.getXfrm();
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
        CTGroupTransform2D xfrm = this.getSafeXfrm();
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

    @Override
    public Rectangle2D getInteriorAnchor() {
        CTGroupTransform2D xfrm = this.getXfrm();
        CTPoint2D off = xfrm.getChOff();
        double x = Units.toPoints(off.getX());
        double y = Units.toPoints(off.getY());
        CTPositiveSize2D ext = xfrm.getChExt();
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override
    public void setInteriorAnchor(Rectangle2D anchor) {
        CTGroupTransform2D xfrm = this.getSafeXfrm();
        CTPoint2D off = xfrm.isSetChOff() ? xfrm.getChOff() : xfrm.addNewChOff();
        long x = Units.toEMU(anchor.getX());
        long y = Units.toEMU(anchor.getY());
        off.setX(x);
        off.setY(y);
        CTPositiveSize2D ext = xfrm.isSetChExt() ? xfrm.getChExt() : xfrm.addNewChExt();
        long cx = Units.toEMU(anchor.getWidth());
        long cy = Units.toEMU(anchor.getHeight());
        ext.setCx(cx);
        ext.setCy(cy);
    }

    @Override
    public List<XSLFShape> getShapes() {
        return this._shapes;
    }

    @Override
    public Iterator<XSLFShape> iterator() {
        return this._shapes.iterator();
    }

    @Override
    public boolean removeShape(XSLFShape xShape) {
        XmlObject obj = xShape.getXmlObject();
        CTGroupShape grpSp = (CTGroupShape)this.getXmlObject();
        this.getSheet().deregisterShapeId(xShape.getShapeId());
        if (obj instanceof CTShape) {
            grpSp.getSpList().remove(obj);
        } else if (obj instanceof CTGroupShape) {
            XSLFGroupShape gs = (XSLFGroupShape)xShape;
            new ArrayList<XSLFShape>(gs.getShapes()).forEach(gs::removeShape);
            grpSp.getGrpSpList().remove(obj);
        } else if (obj instanceof CTConnector) {
            grpSp.getCxnSpList().remove(obj);
        } else if (obj instanceof CTGraphicalObjectFrame) {
            grpSp.getGraphicFrameList().remove(obj);
        } else if (obj instanceof CTPicture) {
            XSLFPictureShape ps = (XSLFPictureShape)xShape;
            XSLFSheet sh = this.getSheet();
            if (sh != null) {
                sh.removePictureRelation(ps);
            }
            grpSp.getPicList().remove(obj);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xShape);
        }
        return this._shapes.remove(xShape);
    }

    static CTGroupShape prototype(int shapeId) {
        CTGroupShape ct = CTGroupShape.Factory.newInstance();
        CTGroupShapeNonVisual nvSpPr = ct.addNewNvGrpSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Group " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvGrpSpPr();
        nvSpPr.addNewNvPr();
        ct.addNewGrpSpPr();
        return ct;
    }

    private XSLFDrawing getDrawing() {
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(this.getSheet(), (CTGroupShape)this.getXmlObject());
        }
        return this._drawing;
    }

    @Override
    public XSLFAutoShape createAutoShape() {
        XSLFAutoShape sh = this.getDrawing().createAutoShape();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFFreeformShape createFreeform() {
        XSLFFreeformShape sh = this.getDrawing().createFreeform();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFTextBox createTextBox() {
        XSLFTextBox sh = this.getDrawing().createTextBox();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFConnectorShape createConnector() {
        XSLFConnectorShape sh = this.getDrawing().createConnector();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFGroupShape createGroup() {
        XSLFGroupShape sh = this.getDrawing().createGroup();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFPictureShape createPicture(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = this.getSheet().addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData)pictureData);
        XSLFPictureShape sh = this.getDrawing().createPicture(rp.getRelationship().getId());
        new DrawPictureShape(sh).resize();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = this.getSheet().addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData)pictureData);
        XSLFObjectShape sh = this.getDrawing().createOleShape(rp.getRelationship().getId());
        CTOleObject oleObj = sh.getCTOleObject();
        Dimension dim = pictureData.getImageDimension();
        oleObj.setImgW(Units.toEMU(dim.getWidth()));
        oleObj.setImgH(Units.toEMU(dim.getHeight()));
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable() {
        XSLFTable sh = this.getDrawing().createTable();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable(int numRows, int numCols) {
        if (numRows < 1 || numCols < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable sh = this.getDrawing().createTable();
        this._shapes.add(sh);
        sh.setParent(this);
        for (int r = 0; r < numRows; ++r) {
            XSLFTableRow row = sh.addRow();
            for (int c = 0; c < numCols; ++c) {
                row.addCell();
            }
        }
        return sh;
    }

    @Override
    public void setFlipHorizontal(boolean flip) {
        this.getSafeXfrm().setFlipH(flip);
    }

    @Override
    public void setFlipVertical(boolean flip) {
        this.getSafeXfrm().setFlipV(flip);
    }

    @Override
    public boolean getFlipHorizontal() {
        CTGroupTransform2D xfrm = this.getXfrm();
        return xfrm != null && xfrm.isSetFlipH() && xfrm.getFlipH();
    }

    @Override
    public boolean getFlipVertical() {
        CTGroupTransform2D xfrm = this.getXfrm();
        return xfrm != null && xfrm.isSetFlipV() && xfrm.getFlipV();
    }

    @Override
    public void setRotation(double theta) {
        this.getSafeXfrm().setRot((int)(theta * 60000.0));
    }

    @Override
    public double getRotation() {
        CTGroupTransform2D xfrm = this.getXfrm();
        return xfrm == null || !xfrm.isSetRot() ? 0.0 : (double)xfrm.getRot() / 60000.0;
    }

    @Override
    void copy(XSLFShape src) {
        XSLFGroupShape gr = (XSLFGroupShape)src;
        List<XSLFShape> tgtShapes = this.getShapes();
        List<XSLFShape> srcShapes = gr.getShapes();
        if (tgtShapes.size() == srcShapes.size()) {
            for (int i = 0; i < tgtShapes.size(); ++i) {
                XSLFShape s1 = srcShapes.get(i);
                XSLFShape s2 = tgtShapes.get(i);
                s2.copy(s1);
            }
        } else {
            this.clear();
            for (XSLFShape shape : srcShapes) {
                XSLFShape newShape;
                if (shape instanceof XSLFTextBox) {
                    newShape = this.createTextBox();
                } else if (shape instanceof XSLFFreeformShape) {
                    newShape = this.createFreeform();
                } else if (shape instanceof XSLFAutoShape) {
                    newShape = this.createAutoShape();
                } else if (shape instanceof XSLFConnectorShape) {
                    newShape = this.createConnector();
                } else if (shape instanceof XSLFPictureShape) {
                    XSLFPictureShape p = (XSLFPictureShape)shape;
                    XSLFPictureData pd = p.getPictureData();
                    XSLFPictureData pdNew = this.getSheet().getSlideShow().addPicture(pd.getData(), pd.getType());
                    newShape = this.createPicture(pdNew);
                } else if (shape instanceof XSLFGroupShape) {
                    newShape = this.createGroup();
                } else if (shape instanceof XSLFTable) {
                    newShape = this.createTable();
                } else {
                    _logger.log(5, "copying of class " + shape.getClass() + " not supported.");
                    continue;
                }
                ((XSLFShape)newShape).copy(shape);
            }
        }
    }

    @Override
    public void clear() {
        ArrayList<XSLFShape> shapes = new ArrayList<XSLFShape>(this.getShapes());
        for (XSLFShape shape : shapes) {
            this.removeShape(shape);
        }
    }

    @Override
    public void addShape(XSLFShape shape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch with XSLFGroupShape.create* methods");
    }
}

