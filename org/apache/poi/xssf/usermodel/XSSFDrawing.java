/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.vml.CTShape;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFConnector;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFGraphicFrame;
import org.apache.poi.xssf.usermodel.XSSFObjectData;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFShapeGroup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFTextBox;
import org.apache.poi.xssf.usermodel.XSSFVMLDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public final class XSSFDrawing
extends POIXMLDocumentPart
implements Drawing<XSSFShape> {
    private static final POILogger LOG = POILogFactory.getLogger(XSSFDrawing.class);
    private CTDrawing drawing;
    private long numOfGraphicFrames;
    protected static final String NAMESPACE_A = "http://schemas.openxmlformats.org/drawingml/2006/main";
    protected static final String NAMESPACE_C = "http://schemas.openxmlformats.org/drawingml/2006/chart";

    protected XSSFDrawing() {
        this.drawing = XSSFDrawing.newDrawing();
    }

    public XSSFDrawing(PackagePart part) throws IOException, XmlException {
        super(part);
        XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        options.setLoadReplaceDocumentElement(null);
        try (InputStream is = part.getInputStream();){
            this.drawing = CTDrawing.Factory.parse(is, options);
        }
    }

    private static CTDrawing newDrawing() {
        return CTDrawing.Factory.newInstance();
    }

    @Internal
    public CTDrawing getCTDrawing() {
        return this.drawing;
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTDrawing.type.getName().getNamespaceURI(), "wsDr", "xdr"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.drawing.save(out, xmlOptions);
        out.close();
    }

    @Override
    public XSSFClientAnchor createAnchor(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return new XSSFClientAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    public XSSFTextBox createTextbox(XSSFClientAnchor anchor) {
        long shapeId = this.newShapeId();
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape ctShape = ctAnchor.addNewSp();
        ctShape.set(XSSFSimpleShape.prototype());
        ctShape.getNvSpPr().getCNvPr().setId(shapeId);
        XSSFTextBox shape = new XSSFTextBox(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFPicture createPicture(XSSFClientAnchor anchor, int pictureIndex) {
        PackageRelationship rel = this.addPictureReference(pictureIndex);
        long shapeId = this.newShapeId();
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        CTPicture ctShape = ctAnchor.addNewPic();
        ctShape.set(XSSFPicture.prototype());
        ctShape.getNvPicPr().getCNvPr().setId(shapeId);
        XSSFPicture shape = new XSSFPicture(this, ctShape);
        shape.anchor = anchor;
        shape.setPictureReference(rel);
        ctShape.getSpPr().setXfrm(this.createXfrm(anchor));
        return shape;
    }

    @Override
    public XSSFPicture createPicture(ClientAnchor anchor, int pictureIndex) {
        return this.createPicture((XSSFClientAnchor)anchor, pictureIndex);
    }

    public XSSFChart createChart(XSSFClientAnchor anchor) {
        int chartNumber = this.getPackagePart().getPackage().getPartsByContentType(XSSFRelation.CHART.getContentType()).size() + 1;
        POIXMLDocumentPart.RelationPart rp = this.createRelationship(XSSFRelation.CHART, XSSFFactory.getInstance(), chartNumber, false);
        XSSFChart chart = (XSSFChart)rp.getDocumentPart();
        String chartRelId = rp.getRelationship().getId();
        XSSFGraphicFrame frame = this.createGraphicFrame(anchor);
        frame.setChart(chart, chartRelId);
        frame.getCTGraphicalObjectFrame().setXfrm(this.createXfrm(anchor));
        return chart;
    }

    public XSSFChart createChart(ClientAnchor anchor) {
        return this.createChart((XSSFClientAnchor)anchor);
    }

    public XSSFChart importChart(XSSFChart srcChart) {
        CTTwoCellAnchor anchor = ((XSSFDrawing)srcChart.getParent()).getCTDrawing().getTwoCellAnchorArray(0);
        CTMarker from = (CTMarker)anchor.getFrom().copy();
        CTMarker to = (CTMarker)anchor.getTo().copy();
        XSSFClientAnchor destAnchor = new XSSFClientAnchor(from, to);
        destAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        XSSFChart destChart = this.createChart(destAnchor);
        destChart.getCTChartSpace().set(srcChart.getCTChartSpace().copy());
        destChart.getCTChart().set(destChart.getCTChartSpace().getChart());
        return destChart;
    }

    protected PackageRelationship addPictureReference(int pictureIndex) {
        XSSFWorkbook wb = (XSSFWorkbook)this.getParent().getParent();
        XSSFPictureData data = wb.getAllPictures().get(pictureIndex);
        XSSFPictureData pic = new XSSFPictureData(data.getPackagePart());
        POIXMLDocumentPart.RelationPart rp = this.addRelation(null, XSSFRelation.IMAGES, pic);
        return rp.getRelationship();
    }

    public XSSFSimpleShape createSimpleShape(XSSFClientAnchor anchor) {
        long shapeId = this.newShapeId();
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape ctShape = ctAnchor.addNewSp();
        ctShape.set(XSSFSimpleShape.prototype());
        ctShape.getNvSpPr().getCNvPr().setId(shapeId);
        ctShape.getSpPr().setXfrm(this.createXfrm(anchor));
        XSSFSimpleShape shape = new XSSFSimpleShape(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFConnector createConnector(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        CTConnector ctShape = ctAnchor.addNewCxnSp();
        ctShape.set(XSSFConnector.prototype());
        XSSFConnector shape = new XSSFConnector(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFShapeGroup createGroup(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        CTGroupShape ctGroup = ctAnchor.addNewGrpSp();
        ctGroup.set(XSSFShapeGroup.prototype());
        CTTransform2D xfrm = this.createXfrm(anchor);
        CTGroupTransform2D grpXfrm = ctGroup.getGrpSpPr().getXfrm();
        grpXfrm.setOff(xfrm.getOff());
        grpXfrm.setExt(xfrm.getExt());
        grpXfrm.setChExt(xfrm.getExt());
        XSSFShapeGroup shape = new XSSFShapeGroup(this, ctGroup);
        shape.anchor = anchor;
        return shape;
    }

    @Override
    public XSSFComment createCellComment(ClientAnchor anchor) {
        CellAddress ref;
        XSSFClientAnchor ca = (XSSFClientAnchor)anchor;
        XSSFSheet sheet = this.getSheet();
        CommentsTable comments = sheet.getCommentsTable(true);
        XSSFVMLDrawing vml = sheet.getVMLDrawing(true);
        CTShape vmlShape = vml.newCommentShape();
        if (ca.isSet()) {
            int dx1Pixels = ca.getDx1() / 9525;
            int dy1Pixels = ca.getDy1() / 9525;
            int dx2Pixels = ca.getDx2() / 9525;
            int dy2Pixels = ca.getDy2() / 9525;
            String position = ca.getCol1() + ", " + dx1Pixels + ", " + ca.getRow1() + ", " + dy1Pixels + ", " + ca.getCol2() + ", " + dx2Pixels + ", " + ca.getRow2() + ", " + dy2Pixels;
            vmlShape.getClientDataArray(0).setAnchorArray(0, position);
        }
        if (comments.findCellComment(ref = new CellAddress(ca.getRow1(), ca.getCol1())) != null) {
            throw new IllegalArgumentException("Multiple cell comments in one cell are not allowed, cell: " + ref);
        }
        return new XSSFComment(comments, comments.newComment(ref), vmlShape);
    }

    private XSSFGraphicFrame createGraphicFrame(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor(anchor);
        CTGraphicalObjectFrame ctGraphicFrame = ctAnchor.addNewGraphicFrame();
        ctGraphicFrame.set(XSSFGraphicFrame.prototype());
        ctGraphicFrame.setXfrm(this.createXfrm(anchor));
        long frameId = this.numOfGraphicFrames++;
        XSSFGraphicFrame graphicFrame = new XSSFGraphicFrame(this, ctGraphicFrame);
        graphicFrame.setAnchor(anchor);
        graphicFrame.setId(frameId);
        graphicFrame.setName("Diagramm" + frameId);
        return graphicFrame;
    }

    @Override
    public XSSFObjectData createObjectData(ClientAnchor anchor, int storageId, int pictureIndex) {
        PackagePartName olePN;
        XSSFSheet sh = this.getSheet();
        PackagePart sheetPart = sh.getPackagePart();
        XSSFSheet sheet = this.getSheet();
        XSSFWorkbook wb = sheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        long shapeId = ((long)sheetIndex + 1L) * 1024L + this.newShapeId();
        try {
            olePN = PackagingURIHelper.createPartName("/xl/embeddings/oleObject" + storageId + ".bin");
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
        PackageRelationship olePR = sheetPart.addRelationship(olePN, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject");
        XSSFPictureData imgPD = sh.getWorkbook().getAllPictures().get(pictureIndex);
        PackagePartName imgPN = imgPD.getPackagePart().getPartName();
        PackageRelationship imgSheetPR = sheetPart.addRelationship(imgPN, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image");
        PackageRelationship imgDrawPR = this.getPackagePart().addRelationship(imgPN, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image");
        CTWorksheet cwb = sh.getCTWorksheet();
        CTOleObjects oo = cwb.isSetOleObjects() ? cwb.getOleObjects() : cwb.addNewOleObjects();
        CTOleObject ole1 = oo.addNewOleObject();
        ole1.setProgId("Package");
        ole1.setShapeId(shapeId);
        ole1.setId(olePR.getId());
        XmlCursor cur1 = ole1.newCursor();
        cur1.toEndToken();
        cur1.beginElement("objectPr", "http://schemas.openxmlformats.org/spreadsheetml/2006/main");
        cur1.insertAttributeWithValue("id", "http://schemas.openxmlformats.org/officeDocument/2006/relationships", imgSheetPR.getId());
        cur1.insertAttributeWithValue("defaultSize", "0");
        cur1.beginElement("anchor", "http://schemas.openxmlformats.org/spreadsheetml/2006/main");
        cur1.insertAttributeWithValue("moveWithCells", "1");
        CTTwoCellAnchor ctAnchor = this.createTwoCellAnchor((XSSFClientAnchor)anchor);
        XmlCursor cur2 = ctAnchor.newCursor();
        cur2.copyXmlContents(cur1);
        cur2.dispose();
        cur1.toParent();
        cur1.toFirstChild();
        cur1.setName(new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "from"));
        cur1.toNextSibling();
        cur1.setName(new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "to"));
        cur1.dispose();
        org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape ctShape = ctAnchor.addNewSp();
        ctShape.set(XSSFObjectData.prototype());
        ctShape.getSpPr().setXfrm(this.createXfrm((XSSFClientAnchor)anchor));
        CTBlipFillProperties blipFill = ctShape.getSpPr().addNewBlipFill();
        blipFill.addNewBlip().setEmbed(imgDrawPR.getId());
        blipFill.addNewStretch().addNewFillRect();
        CTNonVisualDrawingProps cNvPr = ctShape.getNvSpPr().getCNvPr();
        cNvPr.setId(shapeId);
        cNvPr.setName("Object " + shapeId);
        XmlCursor extCur = cNvPr.getExtLst().getExtArray(0).newCursor();
        extCur.toFirstChild();
        extCur.setAttributeText(new QName("spid"), "_x0000_s" + shapeId);
        extCur.dispose();
        XSSFObjectData shape = new XSSFObjectData(this, ctShape);
        shape.anchor = (XSSFClientAnchor)anchor;
        return shape;
    }

    public List<XSSFChart> getCharts() {
        ArrayList<XSSFChart> charts = new ArrayList<XSSFChart>();
        for (POIXMLDocumentPart part : this.getRelations()) {
            if (!(part instanceof XSSFChart)) continue;
            charts.add((XSSFChart)part);
        }
        return charts;
    }

    private CTTwoCellAnchor createTwoCellAnchor(XSSFClientAnchor anchor) {
        STEditAs.Enum editAs;
        CTTwoCellAnchor ctAnchor = this.drawing.addNewTwoCellAnchor();
        ctAnchor.setFrom(anchor.getFrom());
        ctAnchor.setTo(anchor.getTo());
        ctAnchor.addNewClientData();
        anchor.setTo(ctAnchor.getTo());
        anchor.setFrom(ctAnchor.getFrom());
        switch (anchor.getAnchorType()) {
            case DONT_MOVE_AND_RESIZE: {
                editAs = STEditAs.ABSOLUTE;
                break;
            }
            case MOVE_AND_RESIZE: {
                editAs = STEditAs.TWO_CELL;
                break;
            }
            case MOVE_DONT_RESIZE: {
                editAs = STEditAs.ONE_CELL;
                break;
            }
            default: {
                editAs = STEditAs.ONE_CELL;
            }
        }
        ctAnchor.setEditAs(editAs);
        return ctAnchor;
    }

    private CTTransform2D createXfrm(XSSFClientAnchor anchor) {
        CTTransform2D xfrm = CTTransform2D.Factory.newInstance();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(anchor.getDx1());
        off.setY(anchor.getDy1());
        XSSFSheet sheet = this.getSheet();
        double widthPx = 0.0;
        for (int col = anchor.getCol1(); col < anchor.getCol2(); ++col) {
            widthPx += (double)sheet.getColumnWidthInPixels(col);
        }
        double heightPx = 0.0;
        for (int row = anchor.getRow1(); row < anchor.getRow2(); ++row) {
            heightPx += ImageUtils.getRowHeightInPixels(sheet, row);
        }
        long width = Units.pixelToEMU((int)widthPx);
        long height = Units.pixelToEMU((int)heightPx);
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx(width - (long)anchor.getDx1() + (long)anchor.getDx2());
        ext.setCy(height - (long)anchor.getDy1() + (long)anchor.getDy2());
        return xfrm;
    }

    private long newShapeId() {
        return 1 + this.drawing.sizeOfAbsoluteAnchorArray() + this.drawing.sizeOfOneCellAnchorArray() + this.drawing.sizeOfTwoCellAnchorArray();
    }

    public List<XSSFShape> getShapes() {
        ArrayList<XSSFShape> lst = new ArrayList<XSSFShape>();
        XmlCursor cur = this.drawing.newCursor();
        try {
            if (cur.toFirstChild()) {
                this.addShapes(cur, lst);
            }
        } finally {
            cur.dispose();
        }
        return lst;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<XSSFShape> getShapes(XSSFShapeGroup groupshape) {
        ArrayList<XSSFShape> lst = new ArrayList<XSSFShape>();
        XmlCursor cur = groupshape.getCTGroupShape().newCursor();
        try {
            this.addShapes(cur, lst);
        } finally {
            cur.dispose();
        }
        return lst;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private void addShapes(XmlCursor cur, List<XSSFShape> lst) {
        try {
            do {
                block15: {
                    cur.push();
                    if (!cur.toFirstChild()) break block15;
                    do {
                        block19: {
                            block18: {
                                block17: {
                                    block16: {
                                        if ((obj = cur.getObject()) instanceof CTMarker) continue;
                                        if (!(obj instanceof CTPicture)) break block16;
                                        shape /* !! */  = new XSSFPicture(this, (CTPicture)obj);
                                        ** GOTO lbl46
                                    }
                                    if (!(obj instanceof CTConnector)) break block17;
                                    shape /* !! */  = new XSSFConnector(this, (CTConnector)obj);
                                    ** GOTO lbl46
                                }
                                if (!(obj instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape)) break block18;
                                shape /* !! */  = this.hasOleLink(obj) != false ? new XSSFObjectData(this, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape)obj) : new XSSFSimpleShape(this, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape)obj);
                                ** GOTO lbl46
                            }
                            if (!(obj instanceof CTGraphicalObjectFrame)) break block19;
                            shape /* !! */  = new XSSFGraphicFrame(this, (CTGraphicalObjectFrame)obj);
                            ** GOTO lbl46
                        }
                        if (!(obj instanceof CTGroupShape)) {
                            if (!(obj instanceof XmlAnyTypeImpl)) continue;
                            XSSFDrawing.LOG.log(5, new Object[]{"trying to parse AlternateContent, this unlinks the returned Shapes from the underlying xml content, so those shapes can't be used to modify the drawing, i.e. modifications will be ignored!"});
                            cur.push();
                            cur.toFirstChild();
                            cur2 = null;
                            try {
                                alterWS = CTDrawing.Factory.parse(cur.newXMLStreamReader());
                                cur2 = alterWS.newCursor();
                                if (!cur2.toFirstChild()) ** GOTO lbl51
                                this.addShapes(cur2, lst);
                            } catch (XmlException e) {
                                XSSFDrawing.LOG.log(5, new Object[]{"unable to parse CTDrawing in alternate content.", e});
                            } finally {
                                if (cur2 != null) {
                                    cur2.dispose();
                                }
                                cur.pop();
                            }
                        } else {
                            shape /* !! */  = new XSSFShapeGroup(this, (CTGroupShape)obj);
lbl46:
                            // 5 sources

                            if (!XSSFDrawing.$assertionsDisabled && shape /* !! */  == null) {
                                throw new AssertionError();
                            }
                            shape /* !! */ .anchor = this.getAnchorFromParent(obj);
                            lst.add(shape /* !! */ );
                        }
lbl51:
                        // 6 sources

                    } while (cur.toNextSibling());
                }
                cur.pop();
            } while (cur.toNextSibling());
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean hasOleLink(XmlObject shape) {
        QName uriName = new QName(null, "uri");
        String xquery = "declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:extLst/a:ext";
        XmlCursor cur = shape.newCursor();
        cur.selectPath(xquery);
        try {
            while (cur.toNextSelection()) {
                String uri = cur.getAttributeText(uriName);
                if (!"{63B3BB69-23CF-44E3-9099-C40C66FF867C}".equals(uri)) continue;
                boolean bl = true;
                return bl;
            }
        } finally {
            cur.dispose();
        }
        return false;
    }

    private XSSFAnchor getAnchorFromParent(XmlObject obj) {
        XSSFClientAnchor anchor = null;
        XmlObject parentXbean = null;
        XmlCursor cursor = obj.newCursor();
        if (cursor.toParent()) {
            parentXbean = cursor.getObject();
        }
        cursor.dispose();
        if (parentXbean != null) {
            if (parentXbean instanceof CTTwoCellAnchor) {
                CTTwoCellAnchor ct = (CTTwoCellAnchor)parentXbean;
                anchor = new XSSFClientAnchor(ct.getFrom(), ct.getTo());
            } else if (parentXbean instanceof CTOneCellAnchor) {
                CTOneCellAnchor ct = (CTOneCellAnchor)parentXbean;
                anchor = new XSSFClientAnchor(this.getSheet(), ct.getFrom(), ct.getExt());
            } else if (parentXbean instanceof CTAbsoluteAnchor) {
                CTAbsoluteAnchor ct = (CTAbsoluteAnchor)parentXbean;
                anchor = new XSSFClientAnchor(this.getSheet(), ct.getPos(), ct.getExt());
            }
        }
        return anchor;
    }

    @Override
    public Iterator<XSSFShape> iterator() {
        return this.getShapes().iterator();
    }

    public XSSFSheet getSheet() {
        return (XSSFSheet)this.getParent();
    }
}

