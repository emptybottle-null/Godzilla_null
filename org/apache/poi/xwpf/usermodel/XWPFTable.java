/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class XWPFTable
implements IBodyElement,
ISDTContents {
    public static final String REGEX_PERCENTAGE = "[0-9]+(\\.[0-9]+)?%";
    public static final String DEFAULT_PERCENTAGE_WIDTH = "100%";
    static final String NS_OOXML_WP_MAIN = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";
    public static final String REGEX_WIDTH_VALUE = "auto|[0-9]+|[0-9]+(\\.[0-9]+)?%";
    private static final EnumMap<XWPFBorderType, STBorder.Enum> xwpfBorderTypeMap = new EnumMap(XWPFBorderType.class);
    private static final HashMap<Integer, XWPFBorderType> stBorderTypeMap;
    protected StringBuilder text = new StringBuilder(64);
    protected final List<XWPFTableRow> tableRows = new ArrayList<XWPFTableRow>();
    protected IBody part;
    private CTTbl ctTbl;

    public XWPFTable(CTTbl table, IBody part, int row, int col) {
        this(table, part);
        for (int i = 0; i < row; ++i) {
            XWPFTableRow tabRow = this.getRow(i) == null ? this.createRow() : this.getRow(i);
            for (int k = 0; k < col; ++k) {
                if (tabRow.getCell(k) != null) continue;
                tabRow.createCell();
            }
        }
    }

    public XWPFTable(CTTbl table, IBody part) {
        this.part = part;
        this.ctTbl = table;
        if (table.sizeOfTrArray() == 0) {
            this.createEmptyTable(table);
        }
        for (CTRow row : table.getTrList()) {
            StringBuilder rowText = new StringBuilder();
            XWPFTableRow tabRow = new XWPFTableRow(row, this);
            this.tableRows.add(tabRow);
            for (CTTc cell : row.getTcList()) {
                for (CTP ctp : cell.getPList()) {
                    XWPFParagraph p = new XWPFParagraph(ctp, part);
                    if (rowText.length() > 0) {
                        rowText.append('\t');
                    }
                    rowText.append(p.getText());
                }
            }
            if (rowText.length() <= 0) continue;
            this.text.append((CharSequence)rowText);
            this.text.append('\n');
        }
    }

    private void createEmptyTable(CTTbl table) {
        table.addNewTr().addNewTc().addNewP();
        CTTblPr tblpro = table.addNewTblPr();
        tblpro.addNewTblW().setW(new BigInteger("0"));
        tblpro.getTblW().setType(STTblWidth.AUTO);
        CTTblBorders borders = tblpro.addNewTblBorders();
        borders.addNewBottom().setVal(STBorder.SINGLE);
        borders.addNewInsideH().setVal(STBorder.SINGLE);
        borders.addNewInsideV().setVal(STBorder.SINGLE);
        borders.addNewLeft().setVal(STBorder.SINGLE);
        borders.addNewRight().setVal(STBorder.SINGLE);
        borders.addNewTop().setVal(STBorder.SINGLE);
    }

    @Internal
    public CTTbl getCTTbl() {
        return this.ctTbl;
    }

    public String getText() {
        return this.text.toString();
    }

    @Deprecated
    @Removal
    @NotImplemented
    public void addNewRowBetween(int start, int end) {
        throw new UnsupportedOperationException("XWPFTable#addNewRowBetween(int, int) not implemented");
    }

    public void addNewCol() {
        if (this.ctTbl.sizeOfTrArray() == 0) {
            this.createRow();
        }
        for (int i = 0; i < this.ctTbl.sizeOfTrArray(); ++i) {
            XWPFTableRow tabRow = new XWPFTableRow(this.ctTbl.getTrArray(i), this);
            tabRow.createCell();
        }
    }

    public XWPFTableRow createRow() {
        int sizeCol = this.ctTbl.sizeOfTrArray() > 0 ? this.ctTbl.getTrArray(0).sizeOfTcArray() : 0;
        XWPFTableRow tabRow = new XWPFTableRow(this.ctTbl.addNewTr(), this);
        this.addColumn(tabRow, sizeCol);
        this.tableRows.add(tabRow);
        return tabRow;
    }

    public XWPFTableRow getRow(int pos) {
        if (pos >= 0 && pos < this.ctTbl.sizeOfTrArray()) {
            return this.getRows().get(pos);
        }
        return null;
    }

    public int getWidth() {
        CTTblPr tblPr = this.getTblPr();
        return tblPr.isSetTblW() ? tblPr.getTblW().getW().intValue() : -1;
    }

    public void setWidth(int width) {
        CTTblPr tblPr = this.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        tblWidth.setW(new BigInteger(Integer.toString(width)));
        tblWidth.setType(STTblWidth.DXA);
    }

    public int getNumberOfRows() {
        return this.ctTbl.sizeOfTrArray();
    }

    private CTTblPr getTblPr() {
        return this.getTblPr(true);
    }

    private CTTblPr getTblPr(boolean force) {
        return this.ctTbl.getTblPr() != null ? this.ctTbl.getTblPr() : (force ? this.ctTbl.addNewTblPr() : null);
    }

    private CTTblBorders getTblBorders(boolean force) {
        CTTblPr tblPr = this.getTblPr(force);
        return tblPr == null ? null : (tblPr.isSetTblBorders() ? tblPr.getTblBorders() : (force ? tblPr.addNewTblBorders() : null));
    }

    private CTBorder getTblBorder(boolean force, Border border) {
        Function<CTTblBorders, CTBorder> addNew;
        Function<CTTblBorders, CTBorder> get;
        Function<CTTblBorders, Boolean> isSet;
        switch (border) {
            case INSIDE_V: {
                isSet = CTTblBorders::isSetInsideV;
                get = CTTblBorders::getInsideV;
                addNew = CTTblBorders::addNewInsideV;
                break;
            }
            case INSIDE_H: {
                isSet = CTTblBorders::isSetInsideH;
                get = CTTblBorders::getInsideH;
                addNew = CTTblBorders::addNewInsideH;
                break;
            }
            case LEFT: {
                isSet = CTTblBorders::isSetLeft;
                get = CTTblBorders::getLeft;
                addNew = CTTblBorders::addNewLeft;
                break;
            }
            case TOP: {
                isSet = CTTblBorders::isSetTop;
                get = CTTblBorders::getTop;
                addNew = CTTblBorders::addNewTop;
                break;
            }
            case RIGHT: {
                isSet = CTTblBorders::isSetRight;
                get = CTTblBorders::getRight;
                addNew = CTTblBorders::addNewRight;
                break;
            }
            case BOTTOM: {
                isSet = CTTblBorders::isSetBottom;
                get = CTTblBorders::getBottom;
                addNew = CTTblBorders::addNewBottom;
                break;
            }
            default: {
                return null;
            }
        }
        CTTblBorders ctb = this.getTblBorders(force);
        return ctb == null ? null : (isSet.apply(ctb) != false ? get.apply(ctb) : (force ? addNew.apply(ctb) : null));
    }

    public TableRowAlign getTableAlignment() {
        CTTblPr tPr = this.getTblPr(false);
        return tPr == null ? null : (tPr.isSetJc() ? TableRowAlign.valueOf(tPr.getJc().getVal().intValue()) : null);
    }

    public void setTableAlignment(TableRowAlign tra) {
        CTTblPr tPr = this.getTblPr(true);
        CTJc jc = tPr.isSetJc() ? tPr.getJc() : tPr.addNewJc();
        jc.setVal(STJc.Enum.forInt(tra.getValue()));
    }

    public void removeTableAlignment() {
        CTTblPr tPr = this.getTblPr(false);
        if (tPr != null && tPr.isSetJc()) {
            tPr.unsetJc();
        }
    }

    private void addColumn(XWPFTableRow tabRow, int sizeCol) {
        if (sizeCol > 0) {
            for (int i = 0; i < sizeCol; ++i) {
                tabRow.createCell();
            }
        }
    }

    public String getStyleID() {
        CTString styleStr;
        String styleId = null;
        CTTblPr tblPr = this.ctTbl.getTblPr();
        if (tblPr != null && (styleStr = tblPr.getTblStyle()) != null) {
            styleId = styleStr.getVal();
        }
        return styleId;
    }

    public void setStyleID(String styleName) {
        CTTblPr tblPr = this.getTblPr();
        CTString styleStr = tblPr.getTblStyle();
        if (styleStr == null) {
            styleStr = tblPr.addNewTblStyle();
        }
        styleStr.setVal(styleName);
    }

    public XWPFBorderType getInsideHBorderType() {
        return this.getBorderType(Border.INSIDE_H);
    }

    public int getInsideHBorderSize() {
        return this.getBorderSize(Border.INSIDE_H);
    }

    public int getInsideHBorderSpace() {
        return this.getBorderSpace(Border.INSIDE_H);
    }

    public String getInsideHBorderColor() {
        return this.getBorderColor(Border.INSIDE_H);
    }

    public XWPFBorderType getInsideVBorderType() {
        return this.getBorderType(Border.INSIDE_V);
    }

    public int getInsideVBorderSize() {
        return this.getBorderSize(Border.INSIDE_V);
    }

    public int getInsideVBorderSpace() {
        return this.getBorderSpace(Border.INSIDE_V);
    }

    public String getInsideVBorderColor() {
        return this.getBorderColor(Border.INSIDE_V);
    }

    public XWPFBorderType getTopBorderType() {
        return this.getBorderType(Border.TOP);
    }

    public int getTopBorderSize() {
        return this.getBorderSize(Border.TOP);
    }

    public int getTopBorderSpace() {
        return this.getBorderSpace(Border.TOP);
    }

    public String getTopBorderColor() {
        return this.getBorderColor(Border.TOP);
    }

    public XWPFBorderType getBottomBorderType() {
        return this.getBorderType(Border.BOTTOM);
    }

    public int getBottomBorderSize() {
        return this.getBorderSize(Border.BOTTOM);
    }

    public int getBottomBorderSpace() {
        return this.getBorderSpace(Border.BOTTOM);
    }

    public String getBottomBorderColor() {
        return this.getBorderColor(Border.BOTTOM);
    }

    public XWPFBorderType getLeftBorderType() {
        return this.getBorderType(Border.LEFT);
    }

    public int getLeftBorderSize() {
        return this.getBorderSize(Border.LEFT);
    }

    public int getLeftBorderSpace() {
        return this.getBorderSpace(Border.LEFT);
    }

    public String getLeftBorderColor() {
        return this.getBorderColor(Border.LEFT);
    }

    public XWPFBorderType getRightBorderType() {
        return this.getBorderType(Border.RIGHT);
    }

    public int getRightBorderSize() {
        return this.getBorderSize(Border.RIGHT);
    }

    public int getRightBorderSpace() {
        return this.getBorderSpace(Border.RIGHT);
    }

    public String getRightBorderColor() {
        return this.getBorderColor(Border.RIGHT);
    }

    private XWPFBorderType getBorderType(Border border) {
        CTBorder b = this.getTblBorder(false, border);
        return b != null ? stBorderTypeMap.get(b.getVal().intValue()) : null;
    }

    private int getBorderSize(Border border) {
        CTBorder b = this.getTblBorder(false, border);
        return b != null ? (b.isSetSz() ? b.getSz().intValue() : -1) : -1;
    }

    private int getBorderSpace(Border border) {
        CTBorder b = this.getTblBorder(false, border);
        return b != null ? (b.isSetSpace() ? b.getSpace().intValue() : -1) : -1;
    }

    private String getBorderColor(Border border) {
        CTBorder b = this.getTblBorder(false, border);
        return b != null ? (b.isSetColor() ? b.xgetColor().getStringValue() : null) : null;
    }

    public int getRowBandSize() {
        int size = 0;
        CTTblPr tblPr = this.getTblPr();
        if (tblPr.isSetTblStyleRowBandSize()) {
            CTDecimalNumber rowSize = tblPr.getTblStyleRowBandSize();
            size = rowSize.getVal().intValue();
        }
        return size;
    }

    public void setRowBandSize(int size) {
        CTTblPr tblPr = this.getTblPr();
        CTDecimalNumber rowSize = tblPr.isSetTblStyleRowBandSize() ? tblPr.getTblStyleRowBandSize() : tblPr.addNewTblStyleRowBandSize();
        rowSize.setVal(BigInteger.valueOf(size));
    }

    public int getColBandSize() {
        int size = 0;
        CTTblPr tblPr = this.getTblPr();
        if (tblPr.isSetTblStyleColBandSize()) {
            CTDecimalNumber colSize = tblPr.getTblStyleColBandSize();
            size = colSize.getVal().intValue();
        }
        return size;
    }

    public void setColBandSize(int size) {
        CTTblPr tblPr = this.getTblPr();
        CTDecimalNumber colSize = tblPr.isSetTblStyleColBandSize() ? tblPr.getTblStyleColBandSize() : tblPr.addNewTblStyleColBandSize();
        colSize.setVal(BigInteger.valueOf(size));
    }

    public void setInsideHBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.INSIDE_H, type, size, space, rgbColor);
    }

    public void setInsideVBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.INSIDE_V, type, size, space, rgbColor);
    }

    public void setTopBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.TOP, type, size, space, rgbColor);
    }

    public void setBottomBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.BOTTOM, type, size, space, rgbColor);
    }

    public void setLeftBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.LEFT, type, size, space, rgbColor);
    }

    public void setRightBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        this.setBorder(Border.RIGHT, type, size, space, rgbColor);
    }

    private void setBorder(Border border, XWPFBorderType type, int size, int space, String rgbColor) {
        CTBorder b = this.getTblBorder(true, border);
        assert (b != null);
        b.setVal(xwpfBorderTypeMap.get((Object)type));
        b.setSz(BigInteger.valueOf(size));
        b.setSpace(BigInteger.valueOf(space));
        b.setColor(rgbColor);
    }

    public void removeInsideHBorder() {
        this.removeBorder(Border.INSIDE_H);
    }

    public void removeInsideVBorder() {
        this.removeBorder(Border.INSIDE_V);
    }

    public void removeTopBorder() {
        this.removeBorder(Border.TOP);
    }

    public void removeBottomBorder() {
        this.removeBorder(Border.BOTTOM);
    }

    public void removeLeftBorder() {
        this.removeBorder(Border.LEFT);
    }

    public void removeRightBorder() {
        this.removeBorder(Border.RIGHT);
    }

    public void removeBorders() {
        CTTblPr pr = this.getTblPr(false);
        if (pr != null && pr.isSetTblBorders()) {
            pr.unsetTblBorders();
        }
    }

    private void removeBorder(Border border) {
        Consumer<CTTblBorders> unSet;
        Function<CTTblBorders, Boolean> isSet;
        switch (border) {
            case INSIDE_H: {
                isSet = CTTblBorders::isSetInsideH;
                unSet = CTTblBorders::unsetInsideH;
                break;
            }
            case INSIDE_V: {
                isSet = CTTblBorders::isSetInsideV;
                unSet = CTTblBorders::unsetInsideV;
                break;
            }
            case LEFT: {
                isSet = CTTblBorders::isSetLeft;
                unSet = CTTblBorders::unsetLeft;
                break;
            }
            case TOP: {
                isSet = CTTblBorders::isSetTop;
                unSet = CTTblBorders::unsetTop;
                break;
            }
            case RIGHT: {
                isSet = CTTblBorders::isSetRight;
                unSet = CTTblBorders::unsetRight;
                break;
            }
            case BOTTOM: {
                isSet = CTTblBorders::isSetBottom;
                unSet = CTTblBorders::unsetBottom;
                break;
            }
            default: {
                return;
            }
        }
        CTTblBorders tbl = this.getTblBorders(false);
        if (tbl != null && isSet.apply(tbl).booleanValue()) {
            unSet.accept(tbl);
            this.cleanupTblBorders();
        }
    }

    private void cleanupTblBorders() {
        CTTblBorders b;
        CTTblPr pr = this.getTblPr(false);
        if (!(pr == null || !pr.isSetTblBorders() || (b = pr.getTblBorders()).isSetInsideH() || b.isSetInsideV() || b.isSetTop() || b.isSetBottom() || b.isSetLeft() || b.isSetRight())) {
            pr.unsetTblBorders();
        }
    }

    public int getCellMarginTop() {
        return this.getCellMargin(CTTblCellMar::getTop);
    }

    public int getCellMarginLeft() {
        return this.getCellMargin(CTTblCellMar::getLeft);
    }

    public int getCellMarginBottom() {
        return this.getCellMargin(CTTblCellMar::getBottom);
    }

    public int getCellMarginRight() {
        return this.getCellMargin(CTTblCellMar::getRight);
    }

    private int getCellMargin(Function<CTTblCellMar, CTTblWidth> margin) {
        CTTblWidth tw;
        CTTblPr tblPr = this.getTblPr();
        CTTblCellMar tcm = tblPr.getTblCellMar();
        if (tcm != null && (tw = margin.apply(tcm)) != null) {
            return tw.getW().intValue();
        }
        return 0;
    }

    public void setCellMargins(int top, int left, int bottom, int right) {
        CTTblPr tblPr = this.getTblPr();
        CTTblCellMar tcm = tblPr.isSetTblCellMar() ? tblPr.getTblCellMar() : tblPr.addNewTblCellMar();
        this.setCellMargin(tcm, CTTblCellMar::isSetTop, CTTblCellMar::getTop, CTTblCellMar::addNewTop, CTTblCellMar::unsetTop, top);
        this.setCellMargin(tcm, CTTblCellMar::isSetLeft, CTTblCellMar::getLeft, CTTblCellMar::addNewLeft, CTTblCellMar::unsetLeft, left);
        this.setCellMargin(tcm, CTTblCellMar::isSetBottom, CTTblCellMar::getBottom, CTTblCellMar::addNewBottom, CTTblCellMar::unsetBottom, bottom);
        this.setCellMargin(tcm, CTTblCellMar::isSetRight, CTTblCellMar::getRight, CTTblCellMar::addNewRight, CTTblCellMar::unsetRight, right);
    }

    private void setCellMargin(CTTblCellMar tcm, Function<CTTblCellMar, Boolean> isSet, Function<CTTblCellMar, CTTblWidth> get, Function<CTTblCellMar, CTTblWidth> addNew, Consumer<CTTblCellMar> unSet, int margin) {
        if (margin == 0) {
            if (isSet.apply(tcm).booleanValue()) {
                unSet.accept(tcm);
            }
        } else {
            CTTblWidth tw = (isSet.apply(tcm) != false ? get : addNew).apply(tcm);
            tw.setType(STTblWidth.DXA);
            tw.setW(BigInteger.valueOf(margin));
        }
    }

    public void addRow(XWPFTableRow row) {
        this.ctTbl.addNewTr();
        this.ctTbl.setTrArray(this.getNumberOfRows() - 1, row.getCtRow());
        this.tableRows.add(row);
    }

    public boolean addRow(XWPFTableRow row, int pos) {
        if (pos >= 0 && pos <= this.tableRows.size()) {
            this.ctTbl.insertNewTr(pos);
            this.ctTbl.setTrArray(pos, row.getCtRow());
            this.tableRows.add(pos, row);
            return true;
        }
        return false;
    }

    public XWPFTableRow insertNewTableRow(int pos) {
        if (pos >= 0 && pos <= this.tableRows.size()) {
            CTRow row = this.ctTbl.insertNewTr(pos);
            XWPFTableRow tableRow = new XWPFTableRow(row, this);
            this.tableRows.add(pos, tableRow);
            return tableRow;
        }
        return null;
    }

    public boolean removeRow(int pos) throws IndexOutOfBoundsException {
        if (pos >= 0 && pos < this.tableRows.size()) {
            if (this.ctTbl.sizeOfTrArray() > 0) {
                this.ctTbl.removeTr(pos);
            }
            this.tableRows.remove(pos);
            return true;
        }
        return false;
    }

    public List<XWPFTableRow> getRows() {
        return Collections.unmodifiableList(this.tableRows);
    }

    @Override
    public BodyElementType getElementType() {
        return BodyElementType.TABLE;
    }

    @Override
    public IBody getBody() {
        return this.part;
    }

    @Override
    public POIXMLDocumentPart getPart() {
        if (this.part != null) {
            return this.part.getPart();
        }
        return null;
    }

    @Override
    public BodyType getPartType() {
        return this.part.getPartType();
    }

    public XWPFTableRow getRow(CTRow row) {
        for (int i = 0; i < this.getRows().size(); ++i) {
            if (this.getRows().get(i).getCtRow() != row) continue;
            return this.getRow(i);
        }
        return null;
    }

    public double getWidthDecimal() {
        return XWPFTable.getWidthDecimal(this.getTblPr().getTblW());
    }

    protected static double getWidthDecimal(CTTblWidth ctWidth) {
        double result = 0.0;
        STTblWidth.Enum typeValue = ctWidth.getType();
        if (typeValue == STTblWidth.DXA || typeValue == STTblWidth.AUTO || typeValue == STTblWidth.NIL) {
            result = 0.0 + (double)ctWidth.getW().intValue();
        } else if (typeValue == STTblWidth.PCT) {
            result = (double)ctWidth.getW().intValue() / 50.0;
        }
        return result;
    }

    public TableWidthType getWidthType() {
        return XWPFTable.getWidthType(this.getTblPr().getTblW());
    }

    protected static TableWidthType getWidthType(CTTblWidth ctWidth) {
        STTblWidth.Enum typeValue = ctWidth.getType();
        if (typeValue == null) {
            typeValue = STTblWidth.NIL;
            ctWidth.setType(typeValue);
        }
        switch (typeValue.intValue()) {
            case 1: {
                return TableWidthType.NIL;
            }
            case 4: {
                return TableWidthType.AUTO;
            }
            case 3: {
                return TableWidthType.DXA;
            }
            case 2: {
                return TableWidthType.PCT;
            }
        }
        return TableWidthType.AUTO;
    }

    public void setWidth(String widthValue) {
        XWPFTable.setWidthValue(widthValue, this.getTblPr().getTblW());
    }

    protected static void setWidthValue(String widthValue, CTTblWidth ctWidth) {
        if (!widthValue.matches(REGEX_WIDTH_VALUE)) {
            throw new RuntimeException("Table width value \"" + widthValue + "\" must match regular expression \"" + REGEX_WIDTH_VALUE + "\".");
        }
        if (widthValue.matches("auto")) {
            ctWidth.setType(STTblWidth.AUTO);
            ctWidth.setW(BigInteger.ZERO);
        } else if (widthValue.matches(REGEX_PERCENTAGE)) {
            XWPFTable.setWidthPercentage(ctWidth, widthValue);
        } else {
            ctWidth.setW(new BigInteger(widthValue));
            ctWidth.setType(STTblWidth.DXA);
        }
    }

    protected static void setWidthPercentage(CTTblWidth ctWidth, String widthValue) {
        ctWidth.setType(STTblWidth.PCT);
        if (widthValue.matches(REGEX_PERCENTAGE)) {
            String numberPart = widthValue.substring(0, widthValue.length() - 1);
            double percentage = Double.parseDouble(numberPart) * 50.0;
            long intValue = Math.round(percentage);
            ctWidth.setW(BigInteger.valueOf(intValue));
        } else if (widthValue.matches("[0-9]+")) {
            ctWidth.setW(new BigInteger(widthValue));
        } else {
            throw new RuntimeException("setWidthPercentage(): Width value must be a percentage (\"33.3%\" or an integer, was \"" + widthValue + "\"");
        }
    }

    public void setWidthType(TableWidthType widthType) {
        XWPFTable.setWidthType(widthType, this.getTblPr().getTblW());
    }

    protected static void setWidthType(TableWidthType widthType, CTTblWidth ctWidth) {
        TableWidthType currentType = XWPFTable.getWidthType(ctWidth);
        if (!currentType.equals((Object)widthType)) {
            STTblWidth.Enum stWidthType = widthType.getSTWidthType();
            ctWidth.setType(stWidthType);
            switch (stWidthType.intValue()) {
                case 2: {
                    XWPFTable.setWidthPercentage(ctWidth, DEFAULT_PERCENTAGE_WIDTH);
                    break;
                }
                default: {
                    ctWidth.setW(BigInteger.ZERO);
                }
            }
        }
    }

    static {
        xwpfBorderTypeMap.put(XWPFBorderType.NIL, STBorder.Enum.forInt(1));
        xwpfBorderTypeMap.put(XWPFBorderType.NONE, STBorder.Enum.forInt(2));
        xwpfBorderTypeMap.put(XWPFBorderType.SINGLE, STBorder.Enum.forInt(3));
        xwpfBorderTypeMap.put(XWPFBorderType.THICK, STBorder.Enum.forInt(4));
        xwpfBorderTypeMap.put(XWPFBorderType.DOUBLE, STBorder.Enum.forInt(5));
        xwpfBorderTypeMap.put(XWPFBorderType.DOTTED, STBorder.Enum.forInt(6));
        xwpfBorderTypeMap.put(XWPFBorderType.DASHED, STBorder.Enum.forInt(7));
        xwpfBorderTypeMap.put(XWPFBorderType.DOT_DASH, STBorder.Enum.forInt(8));
        xwpfBorderTypeMap.put(XWPFBorderType.DOT_DOT_DASH, STBorder.Enum.forInt(9));
        xwpfBorderTypeMap.put(XWPFBorderType.TRIPLE, STBorder.Enum.forInt(10));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_SMALL_GAP, STBorder.Enum.forInt(11));
        xwpfBorderTypeMap.put(XWPFBorderType.THICK_THIN_SMALL_GAP, STBorder.Enum.forInt(12));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_THIN_SMALL_GAP, STBorder.Enum.forInt(13));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_MEDIUM_GAP, STBorder.Enum.forInt(14));
        xwpfBorderTypeMap.put(XWPFBorderType.THICK_THIN_MEDIUM_GAP, STBorder.Enum.forInt(15));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP, STBorder.Enum.forInt(16));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_LARGE_GAP, STBorder.Enum.forInt(17));
        xwpfBorderTypeMap.put(XWPFBorderType.THICK_THIN_LARGE_GAP, STBorder.Enum.forInt(18));
        xwpfBorderTypeMap.put(XWPFBorderType.THIN_THICK_THIN_LARGE_GAP, STBorder.Enum.forInt(19));
        xwpfBorderTypeMap.put(XWPFBorderType.WAVE, STBorder.Enum.forInt(20));
        xwpfBorderTypeMap.put(XWPFBorderType.DOUBLE_WAVE, STBorder.Enum.forInt(21));
        xwpfBorderTypeMap.put(XWPFBorderType.DASH_SMALL_GAP, STBorder.Enum.forInt(22));
        xwpfBorderTypeMap.put(XWPFBorderType.DASH_DOT_STROKED, STBorder.Enum.forInt(23));
        xwpfBorderTypeMap.put(XWPFBorderType.THREE_D_EMBOSS, STBorder.Enum.forInt(24));
        xwpfBorderTypeMap.put(XWPFBorderType.THREE_D_ENGRAVE, STBorder.Enum.forInt(25));
        xwpfBorderTypeMap.put(XWPFBorderType.OUTSET, STBorder.Enum.forInt(26));
        xwpfBorderTypeMap.put(XWPFBorderType.INSET, STBorder.Enum.forInt(27));
        stBorderTypeMap = new HashMap();
        stBorderTypeMap.put(1, XWPFBorderType.NIL);
        stBorderTypeMap.put(2, XWPFBorderType.NONE);
        stBorderTypeMap.put(3, XWPFBorderType.SINGLE);
        stBorderTypeMap.put(4, XWPFBorderType.THICK);
        stBorderTypeMap.put(5, XWPFBorderType.DOUBLE);
        stBorderTypeMap.put(6, XWPFBorderType.DOTTED);
        stBorderTypeMap.put(7, XWPFBorderType.DASHED);
        stBorderTypeMap.put(8, XWPFBorderType.DOT_DASH);
        stBorderTypeMap.put(9, XWPFBorderType.DOT_DOT_DASH);
        stBorderTypeMap.put(10, XWPFBorderType.TRIPLE);
        stBorderTypeMap.put(11, XWPFBorderType.THIN_THICK_SMALL_GAP);
        stBorderTypeMap.put(12, XWPFBorderType.THICK_THIN_SMALL_GAP);
        stBorderTypeMap.put(13, XWPFBorderType.THIN_THICK_THIN_SMALL_GAP);
        stBorderTypeMap.put(14, XWPFBorderType.THIN_THICK_MEDIUM_GAP);
        stBorderTypeMap.put(15, XWPFBorderType.THICK_THIN_MEDIUM_GAP);
        stBorderTypeMap.put(16, XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP);
        stBorderTypeMap.put(17, XWPFBorderType.THIN_THICK_LARGE_GAP);
        stBorderTypeMap.put(18, XWPFBorderType.THICK_THIN_LARGE_GAP);
        stBorderTypeMap.put(19, XWPFBorderType.THIN_THICK_THIN_LARGE_GAP);
        stBorderTypeMap.put(20, XWPFBorderType.WAVE);
        stBorderTypeMap.put(21, XWPFBorderType.DOUBLE_WAVE);
        stBorderTypeMap.put(22, XWPFBorderType.DASH_SMALL_GAP);
        stBorderTypeMap.put(23, XWPFBorderType.DASH_DOT_STROKED);
        stBorderTypeMap.put(24, XWPFBorderType.THREE_D_EMBOSS);
        stBorderTypeMap.put(25, XWPFBorderType.THREE_D_ENGRAVE);
        stBorderTypeMap.put(26, XWPFBorderType.OUTSET);
        stBorderTypeMap.put(27, XWPFBorderType.INSET);
    }

    private static enum Border {
        INSIDE_V,
        INSIDE_H,
        LEFT,
        TOP,
        BOTTOM,
        RIGHT;

    }

    public static enum XWPFBorderType {
        NIL,
        NONE,
        SINGLE,
        THICK,
        DOUBLE,
        DOTTED,
        DASHED,
        DOT_DASH,
        DOT_DOT_DASH,
        TRIPLE,
        THIN_THICK_SMALL_GAP,
        THICK_THIN_SMALL_GAP,
        THIN_THICK_THIN_SMALL_GAP,
        THIN_THICK_MEDIUM_GAP,
        THICK_THIN_MEDIUM_GAP,
        THIN_THICK_THIN_MEDIUM_GAP,
        THIN_THICK_LARGE_GAP,
        THICK_THIN_LARGE_GAP,
        THIN_THICK_THIN_LARGE_GAP,
        WAVE,
        DOUBLE_WAVE,
        DASH_SMALL_GAP,
        DASH_DOT_STROKED,
        THREE_D_EMBOSS,
        THREE_D_ENGRAVE,
        OUTSET,
        INSET;

    }
}

