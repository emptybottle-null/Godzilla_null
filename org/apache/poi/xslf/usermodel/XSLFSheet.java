/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import com.zaxxer.sparsebits.SparseBitSet;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawSheet;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFDrawing;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFGraphicFrame;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFObjectShape;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFShapeContainer;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public abstract class XSLFSheet
extends POIXMLDocumentPart
implements XSLFShapeContainer,
Sheet<XSLFShape, XSLFTextParagraph> {
    private static POILogger LOG = POILogFactory.getLogger(XSLFSheet.class);
    private XSLFDrawing _drawing;
    private List<XSLFShape> _shapes;
    private CTGroupShape _spTree;
    private XSLFTheme _theme;
    private List<XSLFTextShape> _placeholders;
    private Map<Integer, XSLFSimpleShape> _placeholderByIdMap;
    private Map<Integer, XSLFSimpleShape> _placeholderByTypeMap;
    private final SparseBitSet shapeIds = new SparseBitSet();

    protected XSLFSheet() {
    }

    protected XSLFSheet(PackagePart part) {
        super(part);
    }

    public XMLSlideShow getSlideShow() {
        for (POIXMLDocumentPart p = this.getParent(); p != null; p = p.getParent()) {
            if (!(p instanceof XMLSlideShow)) continue;
            return (XMLSlideShow)p;
        }
        throw new IllegalStateException("SlideShow was not found");
    }

    protected int allocateShapeId() {
        int nextId = this.shapeIds.nextClearBit(1);
        this.shapeIds.set(nextId);
        return nextId;
    }

    protected void registerShapeId(int shapeId) {
        if (this.shapeIds.get(shapeId)) {
            LOG.log(5, "shape id " + shapeId + " has been already used.");
        }
        this.shapeIds.set(shapeId);
    }

    protected void deregisterShapeId(int shapeId) {
        if (!this.shapeIds.get(shapeId)) {
            LOG.log(5, "shape id " + shapeId + " hasn't been registered.");
        }
        this.shapeIds.clear(shapeId);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static List<XSLFShape> buildShapes(CTGroupShape spTree, XSLFShapeContainer parent) {
        XSLFSheet sheet = parent instanceof XSLFSheet ? (XSLFSheet)parent : ((XSLFShape)((Object)parent)).getSheet();
        ArrayList<XSLFShape> shapes = new ArrayList<XSLFShape>();
        XmlCursor cur = spTree.newCursor();
        try {
            boolean b = cur.toFirstChild();
            while (b) {
                XSLFShape shape;
                XmlObject ch = cur.getObject();
                if (ch instanceof CTShape) {
                    shape = XSLFAutoShape.create((CTShape)ch, sheet);
                    shapes.add(shape);
                } else if (ch instanceof CTGroupShape) {
                    shapes.add(new XSLFGroupShape((CTGroupShape)ch, sheet));
                } else if (ch instanceof CTConnector) {
                    shapes.add(new XSLFConnectorShape((CTConnector)ch, sheet));
                } else if (ch instanceof CTPicture) {
                    shapes.add(new XSLFPictureShape((CTPicture)ch, sheet));
                } else if (ch instanceof CTGraphicalObjectFrame) {
                    shape = XSLFGraphicFrame.create((CTGraphicalObjectFrame)ch, sheet);
                    shapes.add(shape);
                } else if (ch instanceof XmlAnyTypeImpl) {
                    cur.push();
                    if (cur.toChild("http://schemas.openxmlformats.org/markup-compatibility/2006", "Choice") && cur.toFirstChild()) {
                        try {
                            CTGroupShape grp = CTGroupShape.Factory.parse(cur.newXMLStreamReader());
                            shapes.addAll(XSLFSheet.buildShapes(grp, parent));
                        } catch (XmlException e) {
                            LOG.log(1, "unparsable alternate content", e);
                        }
                    }
                    cur.pop();
                }
                b = cur.toNextSibling();
            }
        } finally {
            cur.dispose();
        }
        for (XSLFShape s : shapes) {
            s.setParent(parent);
        }
        return shapes;
    }

    public abstract XmlObject getXmlObject();

    private XSLFDrawing getDrawing() {
        this.initDrawingAndShapes();
        return this._drawing;
    }

    @Override
    public List<XSLFShape> getShapes() {
        this.initDrawingAndShapes();
        return this._shapes;
    }

    private void initDrawingAndShapes() {
        CTGroupShape cgs = this.getSpTree();
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(this, cgs);
        }
        if (this._shapes == null) {
            this._shapes = XSLFSheet.buildShapes(cgs, this);
        }
    }

    @Override
    public XSLFAutoShape createAutoShape() {
        XSLFAutoShape sh = this.getDrawing().createAutoShape();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFFreeformShape createFreeform() {
        XSLFFreeformShape sh = this.getDrawing().createFreeform();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFTextBox createTextBox() {
        XSLFTextBox sh = this.getDrawing().createTextBox();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFConnectorShape createConnector() {
        XSLFConnectorShape sh = this.getDrawing().createConnector();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFGroupShape createGroup() {
        XSLFGroupShape sh = this.getDrawing().createGroup();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public XSLFPictureShape createPicture(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = this.addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData)pictureData);
        XSLFPictureShape sh = this.getDrawing().createPicture(rp.getRelationship().getId());
        new DrawPictureShape(sh).resize();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable() {
        XSLFTable sh = this.getDrawing().createTable();
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable(int numRows, int numCols) {
        if (numRows < 1 || numCols < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable sh = this.getDrawing().createTable();
        this.getShapes().add(sh);
        sh.setParent(this);
        for (int r = 0; r < numRows; ++r) {
            XSLFTableRow row = sh.addRow();
            for (int c = 0; c < numCols; ++c) {
                row.addCell();
            }
        }
        return sh;
    }

    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = this.addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData)pictureData);
        XSLFObjectShape sh = this.getDrawing().createOleShape(rp.getRelationship().getId());
        CTOleObject oleObj = sh.getCTOleObject();
        Dimension dim = pictureData.getImageDimension();
        oleObj.setImgW(Units.toEMU(dim.getWidth()));
        oleObj.setImgH(Units.toEMU(dim.getHeight()));
        this.getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override
    public Iterator<XSLFShape> iterator() {
        return this.getShapes().iterator();
    }

    @Override
    public void addShape(XSLFShape shape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch witht XSLFSheet.create* methods");
    }

    @Override
    public boolean removeShape(XSLFShape xShape) {
        XmlObject obj = xShape.getXmlObject();
        CTGroupShape spTree = this.getSpTree();
        this.deregisterShapeId(xShape.getShapeId());
        if (obj instanceof CTShape) {
            spTree.getSpList().remove(obj);
        } else if (obj instanceof CTGroupShape) {
            XSLFGroupShape gs = (XSLFGroupShape)xShape;
            new ArrayList<XSLFShape>(gs.getShapes()).forEach(gs::removeShape);
            spTree.getGrpSpList().remove(obj);
        } else if (obj instanceof CTConnector) {
            spTree.getCxnSpList().remove(obj);
        } else if (obj instanceof CTGraphicalObjectFrame) {
            spTree.getGraphicFrameList().remove(obj);
        } else if (obj instanceof CTPicture) {
            XSLFPictureShape ps = (XSLFPictureShape)xShape;
            this.removePictureRelation(ps);
            spTree.getPicList().remove(obj);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xShape);
        }
        return this.getShapes().remove(xShape);
    }

    @Override
    public void clear() {
        ArrayList<XSLFShape> shapes = new ArrayList<XSLFShape>(this.getShapes());
        for (XSLFShape shape : shapes) {
            this.removeShape(shape);
        }
    }

    protected abstract String getRootElementName();

    protected CTGroupShape getSpTree() {
        if (this._spTree == null) {
            XmlObject root = this.getXmlObject();
            XmlObject[] sp = root.selectPath("declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main' .//*/p:spTree");
            if (sp.length == 0) {
                throw new IllegalStateException("CTGroupShape was not found");
            }
            this._spTree = (CTGroupShape)sp[0];
        }
        return this._spTree;
    }

    @Override
    protected final void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        String docName = this.getRootElementName();
        if (docName != null) {
            xmlOptions.setSaveSyntheticDocumentElement(new QName("http://schemas.openxmlformats.org/presentationml/2006/main", docName));
        }
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.getXmlObject().save(out, xmlOptions);
        out.close();
    }

    public XSLFSheet importContent(XSLFSheet src) {
        this._spTree = null;
        this.getSpTree().set(src.getSpTree().copy());
        this.wipeAndReinitialize(src, 0);
        return this;
    }

    private void wipeAndReinitialize(XSLFSheet src, int offset) {
        this._shapes = null;
        this._drawing = null;
        this.initDrawingAndShapes();
        this._placeholders = null;
        List<XSLFShape> tgtShapes = this.getShapes();
        List<XSLFShape> srcShapes = src.getShapes();
        for (int i = 0; i < srcShapes.size(); ++i) {
            XSLFShape s1 = srcShapes.get(i);
            XSLFShape s2 = tgtShapes.get(offset + i);
            s2.copy(s1);
        }
    }

    public XSLFSheet appendContent(XSLFSheet src) {
        int numShapes = this.getShapes().size();
        CTGroupShape spTree = this.getSpTree();
        CTGroupShape srcTree = src.getSpTree();
        for (XmlObject ch : srcTree.selectPath("*")) {
            if (ch instanceof CTShape) {
                spTree.addNewSp().set(ch.copy());
                continue;
            }
            if (ch instanceof CTGroupShape) {
                spTree.addNewGrpSp().set(ch.copy());
                continue;
            }
            if (ch instanceof CTConnector) {
                spTree.addNewCxnSp().set(ch.copy());
                continue;
            }
            if (ch instanceof CTPicture) {
                spTree.addNewPic().set(ch.copy());
                continue;
            }
            if (!(ch instanceof CTGraphicalObjectFrame)) continue;
            spTree.addNewGraphicFrame().set(ch.copy());
        }
        this.wipeAndReinitialize(src, numShapes);
        return this;
    }

    public XSLFTheme getTheme() {
        if (this._theme != null || !this.isSupportTheme()) {
            return this._theme;
        }
        this.getRelations().stream().filter(p -> p instanceof XSLFTheme).findAny().ifPresent(p -> {
            this._theme = (XSLFTheme)p;
        });
        return this._theme;
    }

    boolean isSupportTheme() {
        return false;
    }

    String mapSchemeColor(String schemeColor) {
        return null;
    }

    protected XSLFTextShape getTextShapeByType(Placeholder type) {
        for (XSLFShape shape : this.getShapes()) {
            XSLFTextShape txt;
            if (!(shape instanceof XSLFTextShape) || (txt = (XSLFTextShape)shape).getTextType() != type) continue;
            return txt;
        }
        return null;
    }

    public XSLFSimpleShape getPlaceholder(Placeholder ph) {
        return this.getPlaceholderByType(ph.ooxmlId);
    }

    XSLFSimpleShape getPlaceholder(CTPlaceholder ph) {
        XSLFSimpleShape shape = null;
        if (ph.isSetIdx()) {
            shape = this.getPlaceholderById((int)ph.getIdx());
        }
        if (shape == null && ph.isSetType()) {
            shape = this.getPlaceholderByType(ph.getType().intValue());
        }
        return shape;
    }

    private void initPlaceholders() {
        if (this._placeholders == null) {
            this._placeholders = new ArrayList<XSLFTextShape>();
            this._placeholderByIdMap = new HashMap<Integer, XSLFSimpleShape>();
            this._placeholderByTypeMap = new HashMap<Integer, XSLFSimpleShape>();
            for (XSLFShape sh : this.getShapes()) {
                XSLFTextShape sShape;
                CTPlaceholder ph;
                if (!(sh instanceof XSLFTextShape) || (ph = ((XSLFPlaceholderDetails)(sShape = (XSLFTextShape)sh).getPlaceholderDetails()).getCTPlaceholder(false)) == null) continue;
                this._placeholders.add(sShape);
                if (ph.isSetIdx()) {
                    int idx = (int)ph.getIdx();
                    this._placeholderByIdMap.put(idx, sShape);
                }
                if (!ph.isSetType()) continue;
                this._placeholderByTypeMap.put(ph.getType().intValue(), sShape);
            }
        }
    }

    private XSLFSimpleShape getPlaceholderById(int id) {
        this.initPlaceholders();
        return this._placeholderByIdMap.get(id);
    }

    XSLFSimpleShape getPlaceholderByType(int ordinal) {
        this.initPlaceholders();
        return this._placeholderByTypeMap.get(ordinal);
    }

    public XSLFTextShape getPlaceholder(int idx) {
        this.initPlaceholders();
        return this._placeholders.get(idx);
    }

    public XSLFTextShape[] getPlaceholders() {
        this.initPlaceholders();
        return this._placeholders.toArray(new XSLFTextShape[0]);
    }

    @Override
    public boolean getFollowMasterGraphics() {
        return false;
    }

    public XSLFBackground getBackground() {
        return null;
    }

    @Override
    public void draw(Graphics2D graphics) {
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        DrawSheet draw = drawFact.getDrawable(this);
        draw.draw(graphics);
    }

    String importBlip(String blipId, POIXMLDocumentPart parent) {
        XSLFPictureData pictureData;
        XSLFPictureData parData = (XSLFPictureData)parent.getRelationPartById(blipId).getDocumentPart();
        if (this.getPackagePart().getPackage() == parent.getPackagePart().getPackage()) {
            pictureData = parData;
        } else {
            XMLSlideShow ppt = this.getSlideShow();
            pictureData = ppt.addPicture(parData.getData(), parData.getType());
        }
        POIXMLDocumentPart.RelationPart rp = this.addRelation(blipId, XSLFRelation.IMAGES, pictureData);
        return rp.getRelationship().getId();
    }

    void importPart(PackageRelationship srcRel, PackagePart srcPafrt) {
        PackagePart destPP = this.getPackagePart();
        PackagePartName srcPPName = srcPafrt.getPartName();
        OPCPackage pkg = destPP.getPackage();
        if (pkg.containPart(srcPPName)) {
            return;
        }
        destPP.addRelationship(srcPPName, TargetMode.INTERNAL, srcRel.getRelationshipType());
        PackagePart part = pkg.createPart(srcPPName, srcPafrt.getContentType());
        try {
            OutputStream out = part.getOutputStream();
            InputStream is = srcPafrt.getInputStream();
            IOUtils.copy(is, out);
            is.close();
            out.close();
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    void removePictureRelation(XSLFPictureShape pictureShape) {
        int numberOfRelations = 0;
        String targetBlipId = pictureShape.getBlipId();
        for (XSLFShape shape : pictureShape.getSheet().getShapes()) {
            XSLFPictureShape currentPictureShape;
            String currentBlipId;
            if (!(shape instanceof XSLFPictureShape) || (currentBlipId = (currentPictureShape = (XSLFPictureShape)shape).getBlipId()) == null || !currentBlipId.equals(targetBlipId)) continue;
            ++numberOfRelations;
        }
        if (numberOfRelations <= 1) {
            this.removeRelation(pictureShape.getBlipId());
        }
    }

    @Override
    public XSLFPlaceholderDetails getPlaceholderDetails(Placeholder placeholder) {
        XSLFSimpleShape ph = this.getPlaceholder(placeholder);
        return ph == null ? null : new XSLFPlaceholderDetails(ph);
    }

    public void addChart(XSLFChart chart) {
        Rectangle rect2D = new Rectangle(10, 10, 500000, 500000);
        this.addChart(chart, rect2D);
    }

    public void addChart(XSLFChart chart, Rectangle2D rect2D) {
        POIXMLDocumentPart.RelationPart rp = this.addRelation(null, XSLFRelation.CHART, chart);
        this.getDrawing().addChart(rp.getRelationship().getId(), rect2D);
    }

    protected String mapSchemeColor(CTColorMappingOverride cmapOver, String schemeColor) {
        String slideColor = this.mapSchemeColor(cmapOver == null ? null : cmapOver.getOverrideClrMapping(), schemeColor);
        if (slideColor != null) {
            return slideColor;
        }
        XSLFSheet master = (XSLFSheet)((Object)this.getMasterSheet());
        String masterColor = master == null ? null : master.mapSchemeColor(schemeColor);
        return masterColor == null ? schemeColor : masterColor;
    }

    protected String mapSchemeColor(CTColorMapping cmap, String schemeColor) {
        StringEnumAbstractBase schemeMap = null;
        if (cmap != null && schemeColor != null) {
            switch (schemeColor) {
                case "accent1": {
                    schemeMap = cmap.getAccent1();
                    break;
                }
                case "accent2": {
                    schemeMap = cmap.getAccent2();
                    break;
                }
                case "accent3": {
                    schemeMap = cmap.getAccent3();
                    break;
                }
                case "accent4": {
                    schemeMap = cmap.getAccent4();
                    break;
                }
                case "accent5": {
                    schemeMap = cmap.getAccent5();
                    break;
                }
                case "accent6": {
                    schemeMap = cmap.getAccent6();
                    break;
                }
                case "bg1": {
                    schemeMap = cmap.getBg1();
                    break;
                }
                case "bg2": {
                    schemeMap = cmap.getBg2();
                    break;
                }
                case "folHlink": {
                    schemeMap = cmap.getFolHlink();
                    break;
                }
                case "hlink": {
                    schemeMap = cmap.getHlink();
                    break;
                }
                case "tx1": {
                    schemeMap = cmap.getTx1();
                    break;
                }
                case "tx2": {
                    schemeMap = cmap.getTx2();
                    break;
                }
            }
        }
        return schemeMap == null ? null : schemeMap.toString();
    }
}

