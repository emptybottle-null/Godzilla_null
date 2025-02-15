/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFObjectShape;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public class XSLFDrawing {
    private XSLFSheet _sheet;
    private CTGroupShape _spTree;

    XSLFDrawing(XSLFSheet sheet, CTGroupShape spTree) {
        XmlObject[] cNvPr;
        this._sheet = sheet;
        this._spTree = spTree;
        for (XmlObject o : cNvPr = sheet.getSpTree().selectPath("declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main' .//*/p:cNvPr")) {
            if (!(o instanceof CTNonVisualDrawingProps)) continue;
            CTNonVisualDrawingProps p = (CTNonVisualDrawingProps)o;
            sheet.registerShapeId((int)p.getId());
        }
    }

    public XSLFAutoShape createAutoShape() {
        CTShape sp = this._spTree.addNewSp();
        sp.set(XSLFAutoShape.prototype(this._sheet.allocateShapeId()));
        XSLFAutoShape shape = new XSLFAutoShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public XSLFFreeformShape createFreeform() {
        CTShape sp = this._spTree.addNewSp();
        sp.set(XSLFFreeformShape.prototype(this._sheet.allocateShapeId()));
        XSLFFreeformShape shape = new XSLFFreeformShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public XSLFTextBox createTextBox() {
        CTShape sp = this._spTree.addNewSp();
        sp.set(XSLFTextBox.prototype(this._sheet.allocateShapeId()));
        XSLFTextBox shape = new XSLFTextBox(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public XSLFConnectorShape createConnector() {
        CTConnector sp = this._spTree.addNewCxnSp();
        sp.set(XSLFConnectorShape.prototype(this._sheet.allocateShapeId()));
        XSLFConnectorShape shape = new XSLFConnectorShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        shape.setLineColor(Color.black);
        shape.setLineWidth(0.75);
        return shape;
    }

    public XSLFGroupShape createGroup() {
        CTGroupShape sp = this._spTree.addNewGrpSp();
        sp.set(XSLFGroupShape.prototype(this._sheet.allocateShapeId()));
        XSLFGroupShape shape = new XSLFGroupShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public XSLFPictureShape createPicture(String rel) {
        CTPicture sp = this._spTree.addNewPic();
        sp.set(XSLFPictureShape.prototype(this._sheet.allocateShapeId(), rel));
        XSLFPictureShape shape = new XSLFPictureShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public XSLFTable createTable() {
        CTGraphicalObjectFrame sp = this._spTree.addNewGraphicFrame();
        sp.set(XSLFTable.prototype(this._sheet.allocateShapeId()));
        XSLFTable shape = new XSLFTable(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }

    public void addChart(String rID, Rectangle2D rect2D) {
        CTGraphicalObjectFrame sp = this._spTree.addNewGraphicFrame();
        sp.set(XSLFChart.prototype(this._sheet.allocateShapeId(), rID, rect2D));
    }

    public XSLFObjectShape createOleShape(String pictureRel) {
        CTGraphicalObjectFrame sp = this._spTree.addNewGraphicFrame();
        sp.set(XSLFObjectShape.prototype(this._sheet.allocateShapeId(), pictureRel));
        XSLFObjectShape shape = new XSLFObjectShape(sp, this._sheet);
        shape.setAnchor(new Rectangle2D.Double());
        return shape;
    }
}

