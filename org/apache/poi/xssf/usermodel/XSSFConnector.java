/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STFontCollectionIndex;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual;

public final class XSSFConnector
extends XSSFShape {
    private static CTConnector prototype;
    private CTConnector ctShape;

    protected XSSFConnector(XSSFDrawing drawing, CTConnector ctShape) {
        this.drawing = drawing;
        this.ctShape = ctShape;
    }

    protected static CTConnector prototype() {
        if (prototype == null) {
            CTConnector shape = CTConnector.Factory.newInstance();
            CTConnectorNonVisual nv = shape.addNewNvCxnSpPr();
            CTNonVisualDrawingProps nvp = nv.addNewCNvPr();
            nvp.setId(1L);
            nvp.setName("Shape 1");
            nv.addNewCNvCxnSpPr();
            CTShapeProperties sp = shape.addNewSpPr();
            CTTransform2D t2d = sp.addNewXfrm();
            CTPositiveSize2D p1 = t2d.addNewExt();
            p1.setCx(0L);
            p1.setCy(0L);
            CTPoint2D p2 = t2d.addNewOff();
            p2.setX(0L);
            p2.setY(0L);
            CTPresetGeometry2D geom = sp.addNewPrstGeom();
            geom.setPrst(STShapeType.LINE);
            geom.addNewAvLst();
            CTShapeStyle style = shape.addNewStyle();
            CTSchemeColor scheme = style.addNewLnRef().addNewSchemeClr();
            scheme.setVal(STSchemeColorVal.ACCENT_1);
            style.getLnRef().setIdx(1L);
            CTStyleMatrixReference fillref = style.addNewFillRef();
            fillref.setIdx(0L);
            fillref.addNewSchemeClr().setVal(STSchemeColorVal.ACCENT_1);
            CTStyleMatrixReference effectRef = style.addNewEffectRef();
            effectRef.setIdx(0L);
            effectRef.addNewSchemeClr().setVal(STSchemeColorVal.ACCENT_1);
            CTFontReference fontRef = style.addNewFontRef();
            fontRef.setIdx(STFontCollectionIndex.MINOR);
            fontRef.addNewSchemeClr().setVal(STSchemeColorVal.TX_1);
            prototype = shape;
        }
        return prototype;
    }

    @Internal
    public CTConnector getCTConnector() {
        return this.ctShape;
    }

    public int getShapeType() {
        return this.ctShape.getSpPr().getPrstGeom().getPrst().intValue();
    }

    public void setShapeType(int type) {
        this.ctShape.getSpPr().getPrstGeom().setPrst(STShapeType.Enum.forInt(type));
    }

    @Override
    protected CTShapeProperties getShapeProperties() {
        return this.ctShape.getSpPr();
    }

    @Override
    public String getShapeName() {
        return this.ctShape.getNvCxnSpPr().getCNvPr().getName();
    }
}

