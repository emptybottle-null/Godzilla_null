/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class XSSFGraphicFrame
extends XSSFShape {
    private static CTGraphicalObjectFrame prototype;
    private CTGraphicalObjectFrame graphicFrame;

    protected XSSFGraphicFrame(XSSFDrawing drawing, CTGraphicalObjectFrame ctGraphicFrame) {
        this.drawing = drawing;
        this.graphicFrame = ctGraphicFrame;
        CTGraphicalObjectData graphicData = this.graphicFrame.getGraphic().getGraphicData();
        if (graphicData != null) {
            NodeList nodes = graphicData.getDomNode().getChildNodes();
            for (int i = 0; i < nodes.getLength(); ++i) {
                POIXMLDocumentPart relation;
                Node node = nodes.item(i);
                if (!node.getNodeName().equals("c:chart") || !((relation = drawing.getRelationById(node.getAttributes().getNamedItem("r:id").getNodeValue())) instanceof XSSFChart)) continue;
                ((XSSFChart)relation).setGraphicFrame(this);
            }
        }
    }

    @Internal
    public CTGraphicalObjectFrame getCTGraphicalObjectFrame() {
        return this.graphicFrame;
    }

    protected static CTGraphicalObjectFrame prototype() {
        if (prototype == null) {
            CTGraphicalObjectFrame graphicFrame = CTGraphicalObjectFrame.Factory.newInstance();
            CTGraphicalObjectFrameNonVisual nvGraphic = graphicFrame.addNewNvGraphicFramePr();
            CTNonVisualDrawingProps props = nvGraphic.addNewCNvPr();
            props.setId(0L);
            props.setName("Diagramm 1");
            nvGraphic.addNewCNvGraphicFramePr();
            CTTransform2D transform = graphicFrame.addNewXfrm();
            CTPositiveSize2D extPoint = transform.addNewExt();
            CTPoint2D offPoint = transform.addNewOff();
            extPoint.setCx(0L);
            extPoint.setCy(0L);
            offPoint.setX(0L);
            offPoint.setY(0L);
            graphicFrame.addNewGraphic();
            prototype = graphicFrame;
        }
        return prototype;
    }

    public void setMacro(String macro) {
        this.graphicFrame.setMacro(macro);
    }

    public void setName(String name) {
        this.getNonVisualProperties().setName(name);
    }

    public String getName() {
        return this.getNonVisualProperties().getName();
    }

    private CTNonVisualDrawingProps getNonVisualProperties() {
        CTGraphicalObjectFrameNonVisual nvGraphic = this.graphicFrame.getNvGraphicFramePr();
        return nvGraphic.getCNvPr();
    }

    protected void setAnchor(XSSFClientAnchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public XSSFClientAnchor getAnchor() {
        return (XSSFClientAnchor)this.anchor;
    }

    protected void setChart(XSSFChart chart, String relId) {
        CTGraphicalObjectData data = this.graphicFrame.getGraphic().addNewGraphicData();
        this.appendChartElement(data, relId);
        chart.setGraphicFrame(this);
    }

    public long getId() {
        return this.graphicFrame.getNvGraphicFramePr().getCNvPr().getId();
    }

    protected void setId(long id) {
        this.graphicFrame.getNvGraphicFramePr().getCNvPr().setId(id);
    }

    private void appendChartElement(CTGraphicalObjectData data, String id) {
        String r_namespaceUri = STRelationshipId.type.getName().getNamespaceURI();
        String c_namespaceUri = "http://schemas.openxmlformats.org/drawingml/2006/chart";
        XmlCursor cursor = data.newCursor();
        cursor.toNextToken();
        cursor.beginElement(new QName(c_namespaceUri, "chart", "c"));
        cursor.insertAttributeWithValue(new QName(r_namespaceUri, "id", "r"), id);
        cursor.dispose();
        data.setUri(c_namespaceUri);
    }

    @Override
    protected CTShapeProperties getShapeProperties() {
        return null;
    }

    @Override
    public String getShapeName() {
        return this.graphicFrame.getNvGraphicFramePr().getCNvPr().getName();
    }
}

