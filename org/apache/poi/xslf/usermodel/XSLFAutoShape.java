/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class XSLFAutoShape
extends XSLFTextShape
implements AutoShape<XSLFShape, XSLFTextParagraph> {
    XSLFAutoShape(CTShape shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    static XSLFAutoShape create(CTShape shape, XSLFSheet sheet) {
        if (shape.getSpPr().isSetCustGeom()) {
            return new XSLFFreeformShape(shape, sheet);
        }
        if (shape.getNvSpPr().getCNvSpPr().isSetTxBox()) {
            return new XSLFTextBox(shape, sheet);
        }
        return new XSLFAutoShape(shape, sheet);
    }

    static CTShape prototype(int shapeId) {
        CTShape ct = CTShape.Factory.newInstance();
        CTShapeNonVisual nvSpPr = ct.addNewNvSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("AutoShape " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvSpPr();
        nvSpPr.addNewNvPr();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTPresetGeometry2D prst = spPr.addNewPrstGeom();
        prst.setPrst(STShapeType.RECT);
        prst.addNewAvLst();
        return ct;
    }

    @Override
    protected CTTextBody getTextBody(boolean create) {
        CTShape shape = (CTShape)this.getXmlObject();
        CTTextBody txBody = shape.getTxBody();
        if (txBody == null && create) {
            XDDFTextBody body = new XDDFTextBody(this);
            shape.setTxBody(body.getXmlObject());
            txBody = shape.getTxBody();
        }
        return txBody;
    }

    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] " + this.getShapeName();
    }
}

