/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xslf.usermodel.XSLFFactory;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

public final class XSLFChart
extends XDDFChart {
    private static String CHART_URI = "http://schemas.openxmlformats.org/drawingml/2006/chart";

    protected XSLFChart() {
    }

    protected XSLFChart(PackagePart part) throws IOException, XmlException {
        super(part);
    }

    @Override
    protected POIXMLRelation getChartRelation() {
        return XSLFRelation.CHART;
    }

    @Override
    protected POIXMLRelation getChartWorkbookRelation() {
        return XSLFRelation.WORKBOOK;
    }

    @Override
    protected POIXMLFactory getChartFactory() {
        return XSLFFactory.getInstance();
    }

    public XSLFTextShape getTitleShape() {
        CTTitle title;
        if (!this.chart.isSetTitle()) {
            this.chart.addNewTitle();
        }
        if ((title = this.chart.getTitle()).getTx() != null && title.getTx().isSetRich()) {
            return new XSLFTextShape(title, null){

                @Override
                protected CTTextBody getTextBody(boolean create) {
                    return title.getTx().getRich();
                }
            };
        }
        return new XSLFTextShape(title, null){

            @Override
            protected CTTextBody getTextBody(boolean create) {
                return title.getTxPr();
            }
        };
    }

    static CTGraphicalObjectFrame prototype(int shapeId, String rID, Rectangle2D anchor) {
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Chart " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        nvGr.addNewNvPr();
        CTTransform2D xfrm = frame.addNewXfrm();
        CTPoint2D off = xfrm.addNewOff();
        off.setX((int)anchor.getX());
        off.setY((int)anchor.getY());
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx((int)anchor.getWidth());
        ext.setCy((int)anchor.getHeight());
        xfrm.setExt(ext);
        xfrm.setOff(off);
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        XmlCursor grCur = gr.newCursor();
        grCur.toNextToken();
        grCur.beginElement(new QName(CHART_URI, "chart"));
        grCur.insertAttributeWithValue("id", "http://schemas.openxmlformats.org/officeDocument/2006/relationships", rID);
        grCur.dispose();
        gr.setUri(CHART_URI);
        return frame;
    }
}

