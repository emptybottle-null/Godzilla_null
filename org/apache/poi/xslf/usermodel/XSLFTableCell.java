/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFColor;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableStyle;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

public class XSLFTableCell
extends XSLFTextShape
implements TableCell<XSLFShape, XSLFTextParagraph> {
    private CTTableCellProperties _tcPr;
    private final XSLFTable table;
    private int row;
    private int col;
    private Rectangle2D anchor;

    XSLFTableCell(CTTableCell cell, XSLFTable table) {
        super(cell, table.getSheet());
        this.table = table;
    }

    @Override
    protected CTTextBody getTextBody(boolean create) {
        CTTableCell cell = this.getCell();
        CTTextBody txBody = cell.getTxBody();
        if (txBody == null && create) {
            XDDFTextBody body = new XDDFTextBody(this);
            cell.setTxBody(body.getXmlObject());
            txBody = cell.getTxBody();
        }
        return txBody;
    }

    static CTTableCell prototype() {
        CTTableCell cell = CTTableCell.Factory.newInstance();
        CTTableCellProperties pr = cell.addNewTcPr();
        pr.addNewLnL().addNewNoFill();
        pr.addNewLnR().addNewNoFill();
        pr.addNewLnT().addNewNoFill();
        pr.addNewLnB().addNewNoFill();
        return cell;
    }

    protected CTTableCellProperties getCellProperties(boolean create) {
        if (this._tcPr == null) {
            CTTableCell cell = this.getCell();
            this._tcPr = cell.getTcPr();
            if (this._tcPr == null && create) {
                this._tcPr = cell.addNewTcPr();
            }
        }
        return this._tcPr;
    }

    @Override
    public void setLeftInset(double margin) {
        CTTableCellProperties pr = this.getCellProperties(true);
        pr.setMarL(Units.toEMU(margin));
    }

    @Override
    public void setRightInset(double margin) {
        CTTableCellProperties pr = this.getCellProperties(true);
        pr.setMarR(Units.toEMU(margin));
    }

    @Override
    public void setTopInset(double margin) {
        CTTableCellProperties pr = this.getCellProperties(true);
        pr.setMarT(Units.toEMU(margin));
    }

    @Override
    public void setBottomInset(double margin) {
        CTTableCellProperties pr = this.getCellProperties(true);
        pr.setMarB(Units.toEMU(margin));
    }

    private CTLineProperties getCTLine(TableCell.BorderEdge edge, boolean create) {
        if (edge == null) {
            throw new IllegalArgumentException("BorderEdge needs to be specified.");
        }
        CTTableCellProperties pr = this.getCellProperties(create);
        if (pr == null) {
            return null;
        }
        switch (edge) {
            case bottom: {
                return pr.isSetLnB() ? pr.getLnB() : (create ? pr.addNewLnB() : null);
            }
            case left: {
                return pr.isSetLnL() ? pr.getLnL() : (create ? pr.addNewLnL() : null);
            }
            case top: {
                return pr.isSetLnT() ? pr.getLnT() : (create ? pr.addNewLnT() : null);
            }
            case right: {
                return pr.isSetLnR() ? pr.getLnR() : (create ? pr.addNewLnR() : null);
            }
        }
        return null;
    }

    @Override
    public void removeBorder(TableCell.BorderEdge edge) {
        CTTableCellProperties pr = this.getCellProperties(false);
        if (pr == null) {
            return;
        }
        switch (edge) {
            case bottom: {
                if (!pr.isSetLnB()) break;
                pr.unsetLnB();
                break;
            }
            case left: {
                if (!pr.isSetLnL()) break;
                pr.unsetLnL();
                break;
            }
            case top: {
                if (!pr.isSetLnT()) break;
                pr.unsetLnT();
                break;
            }
            case right: {
                if (!pr.isSetLnR()) break;
                pr.unsetLnR();
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public StrokeStyle getBorderStyle(final TableCell.BorderEdge edge) {
        final Double width = this.getBorderWidth(edge);
        return width == null ? null : new StrokeStyle(){

            @Override
            public PaintStyle getPaint() {
                return DrawPaint.createSolidPaint(XSLFTableCell.this.getBorderColor(edge));
            }

            @Override
            public StrokeStyle.LineCap getLineCap() {
                return XSLFTableCell.this.getBorderCap(edge);
            }

            @Override
            public StrokeStyle.LineDash getLineDash() {
                return XSLFTableCell.this.getBorderDash(edge);
            }

            @Override
            public StrokeStyle.LineCompound getLineCompound() {
                return XSLFTableCell.this.getBorderCompound(edge);
            }

            @Override
            public double getLineWidth() {
                return width;
            }
        };
    }

    @Override
    public void setBorderStyle(TableCell.BorderEdge edge, StrokeStyle style) {
        StrokeStyle.LineDash dash;
        StrokeStyle.LineCompound compound;
        if (style == null) {
            throw new IllegalArgumentException("StrokeStyle needs to be specified.");
        }
        StrokeStyle.LineCap cap = style.getLineCap();
        if (cap != null) {
            this.setBorderCap(edge, cap);
        }
        if ((compound = style.getLineCompound()) != null) {
            this.setBorderCompound(edge, compound);
        }
        if ((dash = style.getLineDash()) != null) {
            this.setBorderDash(edge, dash);
        }
        double width = style.getLineWidth();
        this.setBorderWidth(edge, width);
    }

    public Double getBorderWidth(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, false);
        return ln == null || !ln.isSetW() ? null : Double.valueOf(Units.toPoints(ln.getW()));
    }

    @Override
    public void setBorderWidth(TableCell.BorderEdge edge, double width) {
        CTLineProperties ln = this.getCTLine(edge, true);
        if (ln == null) {
            return;
        }
        ln.setW(Units.toEMU(width));
    }

    private CTLineProperties setBorderDefaults(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, true);
        if (ln == null) {
            throw new IllegalStateException("CTLineProperties couldn't be initialized");
        }
        if (ln.isSetNoFill()) {
            ln.unsetNoFill();
        }
        if (!ln.isSetPrstDash()) {
            ln.addNewPrstDash().setVal(STPresetLineDashVal.SOLID);
        }
        if (!ln.isSetCmpd()) {
            ln.setCmpd(STCompoundLine.SNG);
        }
        if (!ln.isSetAlgn()) {
            ln.setAlgn(STPenAlignment.CTR);
        }
        if (!ln.isSetCap()) {
            ln.setCap(STLineCap.FLAT);
        }
        if (!ln.isSetRound()) {
            ln.addNewRound();
        }
        if (!ln.isSetHeadEnd()) {
            CTLineEndProperties hd = ln.addNewHeadEnd();
            hd.setType(STLineEndType.NONE);
            hd.setW(STLineEndWidth.MED);
            hd.setLen(STLineEndLength.MED);
        }
        if (!ln.isSetTailEnd()) {
            CTLineEndProperties tl = ln.addNewTailEnd();
            tl.setType(STLineEndType.NONE);
            tl.setW(STLineEndWidth.MED);
            tl.setLen(STLineEndLength.MED);
        }
        return ln;
    }

    @Override
    public void setBorderColor(TableCell.BorderEdge edge, Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Colors need to be specified.");
        }
        CTLineProperties ln = this.setBorderDefaults(edge);
        CTSolidColorFillProperties fill = ln.addNewSolidFill();
        XSLFColor c = new XSLFColor(fill, this.getSheet().getTheme(), fill.getSchemeClr(), this.getSheet());
        c.setColor(color);
    }

    public Color getBorderColor(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill()) {
            return null;
        }
        CTSolidColorFillProperties fill = ln.getSolidFill();
        XSLFColor c = new XSLFColor(fill, this.getSheet().getTheme(), fill.getSchemeClr(), this.getSheet());
        return c.getColor();
    }

    public StrokeStyle.LineCompound getBorderCompound(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetCmpd()) {
            return null;
        }
        return StrokeStyle.LineCompound.fromOoxmlId(ln.getCmpd().intValue());
    }

    @Override
    public void setBorderCompound(TableCell.BorderEdge edge, StrokeStyle.LineCompound compound) {
        if (compound == null) {
            throw new IllegalArgumentException("LineCompound need to be specified.");
        }
        CTLineProperties ln = this.setBorderDefaults(edge);
        ln.setCmpd(STCompoundLine.Enum.forInt(compound.ooxmlId));
    }

    public StrokeStyle.LineDash getBorderDash(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetPrstDash()) {
            return null;
        }
        return StrokeStyle.LineDash.fromOoxmlId(ln.getPrstDash().getVal().intValue());
    }

    @Override
    public void setBorderDash(TableCell.BorderEdge edge, StrokeStyle.LineDash dash) {
        if (dash == null) {
            throw new IllegalArgumentException("LineDash need to be specified.");
        }
        CTLineProperties ln = this.setBorderDefaults(edge);
        if (!ln.isSetPrstDash()) {
            ln.addNewPrstDash();
        }
        ln.getPrstDash().setVal(STPresetLineDashVal.Enum.forInt(dash.ooxmlId));
    }

    public StrokeStyle.LineCap getBorderCap(TableCell.BorderEdge edge) {
        CTLineProperties ln = this.getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetCap()) {
            return null;
        }
        return StrokeStyle.LineCap.fromOoxmlId(ln.getCap().intValue());
    }

    public void setBorderCap(TableCell.BorderEdge edge, StrokeStyle.LineCap cap) {
        if (cap == null) {
            throw new IllegalArgumentException("LineCap need to be specified.");
        }
        CTLineProperties ln = this.setBorderDefaults(edge);
        ln.setCap(STLineCap.Enum.forInt(cap.ooxmlId));
    }

    @Override
    public void setFillColor(Color color) {
        CTTableCellProperties spPr = this.getCellProperties(true);
        if (color == null) {
            if (spPr.isSetSolidFill()) {
                spPr.unsetSolidFill();
            }
        } else {
            CTSolidColorFillProperties fill = spPr.isSetSolidFill() ? spPr.getSolidFill() : spPr.addNewSolidFill();
            XSLFColor c = new XSLFColor(fill, this.getSheet().getTheme(), fill.getSchemeClr(), this.getSheet());
            c.setColor(color);
        }
    }

    @Override
    public Color getFillColor() {
        PaintStyle ps = this.getFillPaint();
        if (ps instanceof PaintStyle.SolidPaint) {
            ColorStyle cs = ((PaintStyle.SolidPaint)ps).getSolidColor();
            return DrawPaint.applyColorTransform(cs);
        }
        return null;
    }

    @Override
    public PaintStyle getFillPaint() {
        PaintStyle paint;
        PaintStyle paint2;
        XSLFSheet sheet = this.getSheet();
        XSLFTheme theme = sheet.getTheme();
        boolean hasPlaceholder = this.getPlaceholder() != null;
        XmlObject props = this.getCellProperties(false);
        XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(props);
        if (fp != null && (paint2 = this.selectPaint(fp, null, sheet.getPackagePart(), theme, hasPlaceholder)) != null) {
            return paint2;
        }
        CTTablePartStyle tps = this.getTablePartStyle(null);
        if (!(tps != null && tps.isSetTcStyle() || (tps = this.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl)) != null && tps.isSetTcStyle())) {
            return null;
        }
        XMLSlideShow slideShow = sheet.getSlideShow();
        CTTableStyleCellStyle tcStyle = tps.getTcStyle();
        if (tcStyle.isSetFill()) {
            props = tcStyle.getFill();
        } else if (tcStyle.isSetFillRef()) {
            props = tcStyle.getFillRef();
        } else {
            return null;
        }
        fp = XSLFPropertiesDelegate.getFillDelegate(props);
        if (fp != null && (paint = this.selectPaint(fp, null, slideShow.getPackagePart(), theme, hasPlaceholder)) != null) {
            return paint;
        }
        return null;
    }

    private CTTablePartStyle getTablePartStyle(XSLFTableStyle.TablePartStyle tablePartStyle) {
        XSLFTableStyle.TablePartStyle tps;
        boolean lastCol;
        CTTable ct = this.table.getCTTable();
        if (!ct.isSetTblPr()) {
            return null;
        }
        CTTableProperties pr = ct.getTblPr();
        boolean bandRow = pr.isSetBandRow() && pr.getBandRow();
        boolean firstRow = pr.isSetFirstRow() && pr.getFirstRow();
        boolean lastRow = pr.isSetLastRow() && pr.getLastRow();
        boolean bandCol = pr.isSetBandCol() && pr.getBandCol();
        boolean firstCol = pr.isSetFirstCol() && pr.getFirstCol();
        boolean bl = lastCol = pr.isSetLastCol() && pr.getLastCol();
        if (tablePartStyle != null) {
            tps = tablePartStyle;
        } else if (this.row == 0 && firstRow) {
            tps = XSLFTableStyle.TablePartStyle.firstRow;
        } else if (this.row == this.table.getNumberOfRows() - 1 && lastRow) {
            tps = XSLFTableStyle.TablePartStyle.lastRow;
        } else if (this.col == 0 && firstCol) {
            tps = XSLFTableStyle.TablePartStyle.firstCol;
        } else if (this.col == this.table.getNumberOfColumns() - 1 && lastCol) {
            tps = XSLFTableStyle.TablePartStyle.lastCol;
        } else {
            tps = XSLFTableStyle.TablePartStyle.wholeTbl;
            int br = this.row + (firstRow ? 1 : 0);
            int bc = this.col + (firstCol ? 1 : 0);
            if (bandRow && (br & 1) == 0) {
                tps = XSLFTableStyle.TablePartStyle.band1H;
            } else if (bandCol && (bc & 1) == 0) {
                tps = XSLFTableStyle.TablePartStyle.band1V;
            }
        }
        XSLFTableStyle tabStyle = this.table.getTableStyle();
        if (tabStyle == null) {
            return null;
        }
        CTTablePartStyle part = tabStyle.getTablePartStyle(tps);
        return part == null ? tabStyle.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl) : part;
    }

    void setGridSpan(int gridSpan_) {
        this.getCell().setGridSpan(gridSpan_);
    }

    @Override
    public int getGridSpan() {
        CTTableCell c = this.getCell();
        return c.isSetGridSpan() ? c.getGridSpan() : 1;
    }

    void setRowSpan(int rowSpan_) {
        this.getCell().setRowSpan(rowSpan_);
    }

    @Override
    public int getRowSpan() {
        CTTableCell c = this.getCell();
        return c.isSetRowSpan() ? c.getRowSpan() : 1;
    }

    void setHMerge() {
        this.getCell().setHMerge(true);
    }

    void setVMerge() {
        this.getCell().setVMerge(true);
    }

    @Override
    public void setVerticalAlignment(VerticalAlignment anchor) {
        CTTableCellProperties cellProps = this.getCellProperties(true);
        if (anchor == null) {
            if (cellProps.isSetAnchor()) {
                cellProps.unsetAnchor();
            }
        } else {
            cellProps.setAnchor(STTextAnchoringType.Enum.forInt(anchor.ordinal() + 1));
        }
    }

    @Override
    public VerticalAlignment getVerticalAlignment() {
        CTTableCellProperties cellProps = this.getCellProperties(false);
        VerticalAlignment align = VerticalAlignment.TOP;
        if (cellProps != null && cellProps.isSetAnchor()) {
            int ival = cellProps.getAnchor().intValue();
            align = VerticalAlignment.values()[ival - 1];
        }
        return align;
    }

    @Override
    public void setTextDirection(TextShape.TextDirection orientation) {
        CTTableCellProperties cellProps = this.getCellProperties(true);
        if (orientation == null) {
            if (cellProps.isSetVert()) {
                cellProps.unsetVert();
            }
        } else {
            STTextVerticalType.Enum vt;
            switch (orientation) {
                default: {
                    vt = STTextVerticalType.HORZ;
                    break;
                }
                case VERTICAL: {
                    vt = STTextVerticalType.VERT;
                    break;
                }
                case VERTICAL_270: {
                    vt = STTextVerticalType.VERT_270;
                    break;
                }
                case STACKED: {
                    vt = STTextVerticalType.WORD_ART_VERT;
                }
            }
            cellProps.setVert(vt);
        }
    }

    @Override
    public TextShape.TextDirection getTextDirection() {
        CTTableCellProperties cellProps = this.getCellProperties(false);
        STTextVerticalType.Enum orientation = cellProps != null && cellProps.isSetVert() ? cellProps.getVert() : STTextVerticalType.HORZ;
        switch (orientation.intValue()) {
            default: {
                return TextShape.TextDirection.HORIZONTAL;
            }
            case 2: 
            case 5: 
            case 6: {
                return TextShape.TextDirection.VERTICAL;
            }
            case 3: {
                return TextShape.TextDirection.VERTICAL_270;
            }
            case 4: 
            case 7: 
        }
        return TextShape.TextDirection.STACKED;
    }

    private CTTableCell getCell() {
        return (CTTableCell)this.getXmlObject();
    }

    void setRowColIndex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    protected CTTransform2D getXfrm() {
        Rectangle2D anc = this.getAnchor();
        CTTransform2D xfrm = CTTransform2D.Factory.newInstance();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(Units.toEMU(anc.getX()));
        off.setY(Units.toEMU(anc.getY()));
        CTPositiveSize2D size = xfrm.addNewExt();
        size.setCx(Units.toEMU(anc.getWidth()));
        size.setCy(Units.toEMU(anc.getHeight()));
        return xfrm;
    }

    @Override
    public void setAnchor(Rectangle2D anchor) {
        if (this.anchor == null) {
            this.anchor = (Rectangle2D)anchor.clone();
        } else {
            this.anchor.setRect(anchor);
        }
    }

    @Override
    public Rectangle2D getAnchor() {
        if (this.anchor == null) {
            this.table.updateCellAnchor();
        }
        assert (this.anchor != null);
        return this.anchor;
    }

    @Override
    public boolean isMerged() {
        CTTableCell c = this.getCell();
        return c.isSetHMerge() && c.getHMerge() || c.isSetVMerge() && c.getVMerge();
    }

    @Override
    protected XSLFCellTextParagraph newTextParagraph(CTTextParagraph p) {
        return new XSLFCellTextParagraph(p, this);
    }

    @Override
    protected XmlObject getShapeProperties() {
        return this.getCellProperties(false);
    }

    private final class XSLFCellTextRun
    extends XSLFTextRun {
        private XSLFCellTextRun(XmlObject r, XSLFTextParagraph p) {
            super(r, p);
        }

        @Override
        public PaintStyle getFontColor() {
            CTTableStyleTextStyle txStyle = this.getTextStyle();
            if (txStyle == null) {
                return super.getFontColor();
            }
            CTSchemeColor phClr = null;
            CTFontReference fontRef = txStyle.getFontRef();
            if (fontRef != null) {
                phClr = fontRef.getSchemeClr();
            }
            XSLFTheme theme = XSLFTableCell.this.getSheet().getTheme();
            XSLFColor c = new XSLFColor(txStyle, theme, phClr, XSLFTableCell.this.getSheet());
            return DrawPaint.createSolidPaint(c.getColorStyle());
        }

        @Override
        public boolean isBold() {
            CTTableStyleTextStyle txStyle = this.getTextStyle();
            if (txStyle == null) {
                return super.isBold();
            }
            return txStyle.isSetB() && txStyle.getB().intValue() == 1;
        }

        @Override
        public boolean isItalic() {
            CTTableStyleTextStyle txStyle = this.getTextStyle();
            if (txStyle == null) {
                return super.isItalic();
            }
            return txStyle.isSetI() && txStyle.getI().intValue() == 1;
        }

        private CTTableStyleTextStyle getTextStyle() {
            CTTablePartStyle tps = XSLFTableCell.this.getTablePartStyle(null);
            if (tps == null || !tps.isSetTcTxStyle()) {
                tps = XSLFTableCell.this.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl);
            }
            return tps == null ? null : tps.getTcTxStyle();
        }
    }

    private final class XSLFCellTextParagraph
    extends XSLFTextParagraph {
        private XSLFCellTextParagraph(CTTextParagraph p, XSLFTextShape shape) {
            super(p, shape);
        }

        @Override
        protected XSLFCellTextRun newTextRun(XmlObject r) {
            return new XSLFCellTextRun(r, this);
        }
    }
}

