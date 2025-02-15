/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.model.DrawingManager2;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalSheet;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.AutoFilterInfoRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.WSBoolRecord;
import org.apache.poi.hssf.record.aggregates.DataValidityTable;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFAutoFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFShapeContainer;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.apache.poi.hssf.usermodel.HSSFSheetConditionalFormatting;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.helpers.HSSFColumnShifter;
import org.apache.poi.hssf.usermodel.helpers.HSSFRowShifter;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SSCellRange;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Configurator;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class HSSFSheet
implements Sheet {
    private static final POILogger log = POILogFactory.getLogger(HSSFSheet.class);
    private static final int DEBUG = 1;
    private static final float PX_DEFAULT = 32.0f;
    private static final float PX_MODIFIED = 36.56f;
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFSheet.RowInitialCapacity", 20);
    private final InternalSheet _sheet;
    private final TreeMap<Integer, HSSFRow> _rows;
    protected final InternalWorkbook _book;
    protected final HSSFWorkbook _workbook;
    private HSSFPatriarch _patriarch;
    private int _firstrow = -1;
    private int _lastrow = -1;

    protected HSSFSheet(HSSFWorkbook workbook) {
        this._sheet = InternalSheet.createSheet();
        this._rows = new TreeMap();
        this._workbook = workbook;
        this._book = workbook.getWorkbook();
    }

    protected HSSFSheet(HSSFWorkbook workbook, InternalSheet sheet) {
        this._sheet = sheet;
        this._rows = new TreeMap();
        this._workbook = workbook;
        this._book = workbook.getWorkbook();
        this.setPropertiesFromSheet(sheet);
    }

    HSSFSheet cloneSheet(HSSFWorkbook workbook) {
        this.getDrawingPatriarch();
        HSSFSheet sheet = new HSSFSheet(workbook, this._sheet.cloneSheet());
        int pos = sheet._sheet.findFirstRecordLocBySid((short)236);
        DrawingRecord dr = (DrawingRecord)sheet._sheet.findFirstRecordBySid((short)236);
        if (null != dr) {
            sheet._sheet.getRecords().remove(dr);
        }
        if (this.getDrawingPatriarch() != null) {
            HSSFPatriarch patr = HSSFPatriarch.createPatriarch(this.getDrawingPatriarch(), sheet);
            sheet._sheet.getRecords().add(pos, patr.getBoundAggregate());
            sheet._patriarch = patr;
        }
        return sheet;
    }

    protected void preSerialize() {
        if (this._patriarch != null) {
            this._patriarch.preSerialize();
        }
    }

    @Override
    public HSSFWorkbook getWorkbook() {
        return this._workbook;
    }

    private void setPropertiesFromSheet(InternalSheet sheet) {
        RowRecord row = sheet.getNextRow();
        while (row != null) {
            this.createRowFromRecord(row);
            row = sheet.getNextRow();
        }
        Iterator<CellValueRecordInterface> iter = sheet.getCellValueIterator();
        long timestart = System.currentTimeMillis();
        if (log.check(1)) {
            log.log(1, "Time at start of cell creating in HSSF sheet = ", timestart);
        }
        HSSFRow lastrow = null;
        while (iter.hasNext()) {
            CellValueRecordInterface cval = iter.next();
            long cellstart = System.currentTimeMillis();
            HSSFRow hrow = lastrow;
            if (hrow == null || hrow.getRowNum() != cval.getRow()) {
                lastrow = hrow = this.getRow(cval.getRow());
                if (hrow == null) {
                    RowRecord rowRec = new RowRecord(cval.getRow());
                    sheet.addRow(rowRec);
                    hrow = this.createRowFromRecord(rowRec);
                }
            }
            if (log.check(1)) {
                if (cval instanceof Record) {
                    log.log(1, "record id = " + Integer.toHexString(((Record)((Object)cval)).getSid()));
                } else {
                    log.log(1, "record = " + cval);
                }
            }
            hrow.createCellFromRecord(cval);
            if (!log.check(1)) continue;
            log.log(1, "record took ", System.currentTimeMillis() - cellstart);
        }
        if (log.check(1)) {
            log.log(1, "total sheet cell creation took ", System.currentTimeMillis() - timestart);
        }
    }

    @Override
    public HSSFRow createRow(int rownum) {
        HSSFRow row = new HSSFRow(this._workbook, this, rownum);
        row.setHeight(this.getDefaultRowHeight());
        row.getRowRecord().setBadFontHeight(false);
        this.addRow(row, true);
        return row;
    }

    private HSSFRow createRowFromRecord(RowRecord row) {
        HSSFRow hrow = new HSSFRow(this._workbook, this, row);
        this.addRow(hrow, false);
        return hrow;
    }

    @Override
    public void removeRow(Row row) {
        HSSFRow hrow = (HSSFRow)row;
        if (row.getSheet() != this) {
            throw new IllegalArgumentException("Specified row does not belong to this sheet");
        }
        for (Cell cell : row) {
            HSSFCell xcell = (HSSFCell)cell;
            if (!xcell.isPartOfArrayFormulaGroup()) continue;
            String msg = "Row[rownum=" + row.getRowNum() + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
            xcell.tryToDeleteArrayFormula(msg);
        }
        if (this._rows.size() > 0) {
            Integer key = row.getRowNum();
            HSSFRow removedRow = this._rows.remove(key);
            if (removedRow != row) {
                throw new IllegalArgumentException("Specified row does not belong to this sheet");
            }
            if (hrow.getRowNum() == this.getLastRowNum()) {
                this._lastrow = this.findLastRow(this._lastrow);
            }
            if (hrow.getRowNum() == this.getFirstRowNum()) {
                this._firstrow = this.findFirstRow(this._firstrow);
            }
            this._sheet.removeRow(hrow.getRowRecord());
            if (this._rows.size() == 0) {
                this._firstrow = -1;
                this._lastrow = -1;
            }
        }
    }

    private int findLastRow(int lastrow) {
        if (lastrow < 1) {
            return 0;
        }
        int rownum = lastrow - 1;
        HSSFRow r = this.getRow(rownum);
        while (r == null && rownum > 0) {
            r = this.getRow(--rownum);
        }
        if (r == null) {
            return 0;
        }
        return rownum;
    }

    private int findFirstRow(int firstrow) {
        int rownum = firstrow + 1;
        HSSFRow r = this.getRow(rownum);
        while (r == null && rownum <= this.getLastRowNum()) {
            r = this.getRow(++rownum);
        }
        if (rownum > this.getLastRowNum()) {
            return 0;
        }
        return rownum;
    }

    private void addRow(HSSFRow row, boolean addLow) {
        boolean firstRow;
        this._rows.put(row.getRowNum(), row);
        if (addLow) {
            this._sheet.addRow(row.getRowRecord());
        }
        boolean bl = firstRow = this._rows.size() == 1;
        if (row.getRowNum() > this.getLastRowNum() || firstRow) {
            this._lastrow = row.getRowNum();
        }
        if (row.getRowNum() < this.getFirstRowNum() || firstRow) {
            this._firstrow = row.getRowNum();
        }
    }

    @Override
    public HSSFRow getRow(int rowIndex) {
        return this._rows.get(rowIndex);
    }

    @Override
    public int getPhysicalNumberOfRows() {
        return this._rows.size();
    }

    @Override
    public int getFirstRowNum() {
        return this._firstrow;
    }

    @Override
    public int getLastRowNum() {
        return this._lastrow;
    }

    public List<HSSFDataValidation> getDataValidations() {
        DataValidityTable dvt = this._sheet.getOrCreateDataValidityTable();
        final ArrayList<HSSFDataValidation> hssfValidations = new ArrayList<HSSFDataValidation>();
        RecordAggregate.RecordVisitor visitor = new RecordAggregate.RecordVisitor(){
            private HSSFEvaluationWorkbook book;
            {
                this.book = HSSFEvaluationWorkbook.create(HSSFSheet.this.getWorkbook());
            }

            @Override
            public void visitRecord(Record r) {
                if (!(r instanceof DVRecord)) {
                    return;
                }
                DVRecord dvRecord = (DVRecord)r;
                CellRangeAddressList regions = dvRecord.getCellRangeAddress().copy();
                DVConstraint constraint = DVConstraint.createDVConstraint(dvRecord, this.book);
                HSSFDataValidation hssfDataValidation = new HSSFDataValidation(regions, constraint);
                hssfDataValidation.setErrorStyle(dvRecord.getErrorStyle());
                hssfDataValidation.setEmptyCellAllowed(dvRecord.getEmptyCellAllowed());
                hssfDataValidation.setSuppressDropDownArrow(dvRecord.getSuppressDropdownArrow());
                hssfDataValidation.createPromptBox(dvRecord.getPromptTitle(), dvRecord.getPromptText());
                hssfDataValidation.setShowPromptBox(dvRecord.getShowPromptOnCellSelected());
                hssfDataValidation.createErrorBox(dvRecord.getErrorTitle(), dvRecord.getErrorText());
                hssfDataValidation.setShowErrorBox(dvRecord.getShowErrorOnInvalidValue());
                hssfValidations.add(hssfDataValidation);
            }
        };
        dvt.visitContainedRecords(visitor);
        return hssfValidations;
    }

    @Override
    public void addValidationData(DataValidation dataValidation) {
        if (dataValidation == null) {
            throw new IllegalArgumentException("objValidation must not be null");
        }
        HSSFDataValidation hssfDataValidation = (HSSFDataValidation)dataValidation;
        DataValidityTable dvt = this._sheet.getOrCreateDataValidityTable();
        DVRecord dvRecord = hssfDataValidation.createDVRecord(this);
        dvt.addDataValidation(dvRecord);
    }

    @Override
    public void setColumnHidden(int columnIndex, boolean hidden) {
        this._sheet.setColumnHidden(columnIndex, hidden);
    }

    @Override
    public boolean isColumnHidden(int columnIndex) {
        return this._sheet.isColumnHidden(columnIndex);
    }

    @Override
    public void setColumnWidth(int columnIndex, int width) {
        this._sheet.setColumnWidth(columnIndex, width);
    }

    @Override
    public int getColumnWidth(int columnIndex) {
        return this._sheet.getColumnWidth(columnIndex);
    }

    @Override
    public float getColumnWidthInPixels(int column) {
        int def;
        int cw = this.getColumnWidth(column);
        float px = cw == (def = this.getDefaultColumnWidth() * 256) ? 32.0f : 36.56f;
        return (float)cw / px;
    }

    @Override
    public int getDefaultColumnWidth() {
        return this._sheet.getDefaultColumnWidth();
    }

    @Override
    public void setDefaultColumnWidth(int width) {
        this._sheet.setDefaultColumnWidth(width);
    }

    @Override
    public short getDefaultRowHeight() {
        return this._sheet.getDefaultRowHeight();
    }

    @Override
    public float getDefaultRowHeightInPoints() {
        return (float)this._sheet.getDefaultRowHeight() / 20.0f;
    }

    @Override
    public void setDefaultRowHeight(short height) {
        this._sheet.setDefaultRowHeight(height);
    }

    @Override
    public void setDefaultRowHeightInPoints(float height) {
        this._sheet.setDefaultRowHeight((short)(height * 20.0f));
    }

    @Override
    public HSSFCellStyle getColumnStyle(int column) {
        short styleIndex = this._sheet.getXFIndexForColAt((short)column);
        if (styleIndex == 15) {
            return null;
        }
        ExtendedFormatRecord xf = this._book.getExFormatAt(styleIndex);
        return new HSSFCellStyle(styleIndex, xf, this._book);
    }

    public boolean isGridsPrinted() {
        return this._sheet.isGridsPrinted();
    }

    public void setGridsPrinted(boolean value) {
        this._sheet.setGridsPrinted(value);
    }

    @Override
    public int addMergedRegion(CellRangeAddress region) {
        return this.addMergedRegion(region, true);
    }

    @Override
    public int addMergedRegionUnsafe(CellRangeAddress region) {
        return this.addMergedRegion(region, false);
    }

    @Override
    public void validateMergedRegions() {
        this.checkForMergedRegionsIntersectingArrayFormulas();
        this.checkForIntersectingMergedRegions();
    }

    private int addMergedRegion(CellRangeAddress region, boolean validate) {
        if (region.getNumberOfCells() < 2) {
            throw new IllegalArgumentException("Merged region " + region.formatAsString() + " must contain 2 or more cells");
        }
        region.validate(SpreadsheetVersion.EXCEL97);
        if (validate) {
            this.validateArrayFormulas(region);
            this.validateMergedRegions(region);
        }
        return this._sheet.addMergedRegion(region.getFirstRow(), region.getFirstColumn(), region.getLastRow(), region.getLastColumn());
    }

    private void validateArrayFormulas(CellRangeAddress region) {
        int firstRow = region.getFirstRow();
        int firstColumn = region.getFirstColumn();
        int lastRow = region.getLastRow();
        int lastColumn = region.getLastColumn();
        for (int rowIn = firstRow; rowIn <= lastRow; ++rowIn) {
            HSSFRow row = this.getRow(rowIn);
            if (row == null) continue;
            for (int colIn = firstColumn; colIn <= lastColumn; ++colIn) {
                CellRangeAddress arrayRange;
                HSSFCell cell = row.getCell(colIn);
                if (cell == null || !cell.isPartOfArrayFormulaGroup() || (arrayRange = cell.getArrayFormulaRange()).getNumberOfCells() <= 1 || !region.intersects(arrayRange)) continue;
                String msg = "The range " + region.formatAsString() + " intersects with a multi-cell array formula. You cannot merge cells of an array.";
                throw new IllegalStateException(msg);
            }
        }
    }

    private void checkForMergedRegionsIntersectingArrayFormulas() {
        for (CellRangeAddress region : this.getMergedRegions()) {
            this.validateArrayFormulas(region);
        }
    }

    private void validateMergedRegions(CellRangeAddress candidateRegion) {
        for (CellRangeAddress existingRegion : this.getMergedRegions()) {
            if (!existingRegion.intersects(candidateRegion)) continue;
            throw new IllegalStateException("Cannot add merged region " + candidateRegion.formatAsString() + " to sheet because it overlaps with an existing merged region (" + existingRegion.formatAsString() + ").");
        }
    }

    private void checkForIntersectingMergedRegions() {
        List<CellRangeAddress> regions = this.getMergedRegions();
        int size = regions.size();
        for (int i = 0; i < size; ++i) {
            CellRangeAddress region = regions.get(i);
            for (CellRangeAddress other : regions.subList(i + 1, regions.size())) {
                if (!region.intersects(other)) continue;
                String msg = "The range " + region.formatAsString() + " intersects with another merged region " + other.formatAsString() + " in this sheet";
                throw new IllegalStateException(msg);
            }
        }
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        this._sheet.setUncalced(value);
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        return this._sheet.getUncalced();
    }

    @Override
    public void setVerticallyCenter(boolean value) {
        this._sheet.getPageSettings().getVCenter().setVCenter(value);
    }

    @Override
    public boolean getVerticallyCenter() {
        return this._sheet.getPageSettings().getVCenter().getVCenter();
    }

    @Override
    public void setHorizontallyCenter(boolean value) {
        this._sheet.getPageSettings().getHCenter().setHCenter(value);
    }

    @Override
    public boolean getHorizontallyCenter() {
        return this._sheet.getPageSettings().getHCenter().getHCenter();
    }

    @Override
    public void setRightToLeft(boolean value) {
        this._sheet.getWindowTwo().setArabic(value);
    }

    @Override
    public boolean isRightToLeft() {
        return this._sheet.getWindowTwo().getArabic();
    }

    @Override
    public void removeMergedRegion(int index) {
        this._sheet.removeMergedRegion(index);
    }

    @Override
    public void removeMergedRegions(Collection<Integer> indices) {
        for (int i : new TreeSet<Integer>(indices).descendingSet()) {
            this._sheet.removeMergedRegion(i);
        }
    }

    @Override
    public int getNumMergedRegions() {
        return this._sheet.getNumMergedRegions();
    }

    @Override
    public CellRangeAddress getMergedRegion(int index) {
        return this._sheet.getMergedRegionAt(index);
    }

    @Override
    public List<CellRangeAddress> getMergedRegions() {
        ArrayList<CellRangeAddress> addresses = new ArrayList<CellRangeAddress>();
        int count = this._sheet.getNumMergedRegions();
        for (int i = 0; i < count; ++i) {
            addresses.add(this._sheet.getMergedRegionAt(i));
        }
        return addresses;
    }

    @Override
    public Iterator<Row> rowIterator() {
        Iterator<Row> result = this._rows.values().iterator();
        return result;
    }

    @Override
    public Iterator<Row> iterator() {
        return this.rowIterator();
    }

    @Internal
    public InternalSheet getSheet() {
        return this._sheet;
    }

    public void setAlternativeExpression(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setAlternateExpression(b);
    }

    public void setAlternativeFormula(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setAlternateFormula(b);
    }

    @Override
    public void setAutobreaks(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setAutobreaks(b);
    }

    public void setDialog(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setDialog(b);
    }

    @Override
    public void setDisplayGuts(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setDisplayGuts(b);
    }

    @Override
    public void setFitToPage(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setFitToPage(b);
    }

    @Override
    public void setRowSumsBelow(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setRowSumsBelow(b);
        record.setAlternateExpression(b);
    }

    @Override
    public void setRowSumsRight(boolean b) {
        WSBoolRecord record = (WSBoolRecord)this._sheet.findFirstRecordBySid((short)129);
        record.setRowSumsRight(b);
    }

    public boolean getAlternateExpression() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getAlternateExpression();
    }

    public boolean getAlternateFormula() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getAlternateFormula();
    }

    @Override
    public boolean getAutobreaks() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getAutobreaks();
    }

    public boolean getDialog() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getDialog();
    }

    @Override
    public boolean getDisplayGuts() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getDisplayGuts();
    }

    @Override
    public boolean isDisplayZeros() {
        return this._sheet.getWindowTwo().getDisplayZeros();
    }

    @Override
    public void setDisplayZeros(boolean value) {
        this._sheet.getWindowTwo().setDisplayZeros(value);
    }

    @Override
    public boolean getFitToPage() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getFitToPage();
    }

    @Override
    public boolean getRowSumsBelow() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getRowSumsBelow();
    }

    @Override
    public boolean getRowSumsRight() {
        return ((WSBoolRecord)this._sheet.findFirstRecordBySid((short)129)).getRowSumsRight();
    }

    @Override
    public boolean isPrintGridlines() {
        return this.getSheet().getPrintGridlines().getPrintGridlines();
    }

    @Override
    public void setPrintGridlines(boolean show) {
        this.getSheet().getPrintGridlines().setPrintGridlines(show);
    }

    @Override
    public boolean isPrintRowAndColumnHeadings() {
        return this.getSheet().getPrintHeaders().getPrintHeaders();
    }

    @Override
    public void setPrintRowAndColumnHeadings(boolean show) {
        this.getSheet().getPrintHeaders().setPrintHeaders(show);
    }

    @Override
    public HSSFPrintSetup getPrintSetup() {
        return new HSSFPrintSetup(this._sheet.getPageSettings().getPrintSetup());
    }

    @Override
    public HSSFHeader getHeader() {
        return new HSSFHeader(this._sheet.getPageSettings());
    }

    @Override
    public HSSFFooter getFooter() {
        return new HSSFFooter(this._sheet.getPageSettings());
    }

    @Override
    public boolean isSelected() {
        return this.getSheet().getWindowTwo().getSelected();
    }

    @Override
    public void setSelected(boolean sel) {
        this.getSheet().getWindowTwo().setSelected(sel);
    }

    public boolean isActive() {
        return this.getSheet().getWindowTwo().isActive();
    }

    public void setActive(boolean sel) {
        this.getSheet().getWindowTwo().setActive(sel);
    }

    @Override
    public double getMargin(short margin) {
        switch (margin) {
            case 5: {
                return this._sheet.getPageSettings().getPrintSetup().getFooterMargin();
            }
            case 4: {
                return this._sheet.getPageSettings().getPrintSetup().getHeaderMargin();
            }
        }
        return this._sheet.getPageSettings().getMargin(margin);
    }

    @Override
    public void setMargin(short margin, double size) {
        switch (margin) {
            case 5: {
                this._sheet.getPageSettings().getPrintSetup().setFooterMargin(size);
                break;
            }
            case 4: {
                this._sheet.getPageSettings().getPrintSetup().setHeaderMargin(size);
                break;
            }
            default: {
                this._sheet.getPageSettings().setMargin(margin, size);
            }
        }
    }

    private WorksheetProtectionBlock getProtectionBlock() {
        return this._sheet.getProtectionBlock();
    }

    @Override
    public boolean getProtect() {
        return this.getProtectionBlock().isSheetProtected();
    }

    public short getPassword() {
        return (short)this.getProtectionBlock().getPasswordHash();
    }

    public boolean getObjectProtect() {
        return this.getProtectionBlock().isObjectProtected();
    }

    @Override
    public boolean getScenarioProtect() {
        return this.getProtectionBlock().isScenarioProtected();
    }

    @Override
    public void protectSheet(String password) {
        this.getProtectionBlock().protectSheet(password, true, true);
    }

    public void setZoom(int numerator, int denominator) {
        if (numerator < 1 || numerator > 65535) {
            throw new IllegalArgumentException("Numerator must be greater than 0 and less than 65536");
        }
        if (denominator < 1 || denominator > 65535) {
            throw new IllegalArgumentException("Denominator must be greater than 0 and less than 65536");
        }
        SCLRecord sclRecord = new SCLRecord();
        sclRecord.setNumerator((short)numerator);
        sclRecord.setDenominator((short)denominator);
        this.getSheet().setSCLRecord(sclRecord);
    }

    @Override
    public void setZoom(int scale) {
        this.setZoom(scale, 100);
    }

    @Override
    public short getTopRow() {
        return this._sheet.getTopRow();
    }

    @Override
    public short getLeftCol() {
        return this._sheet.getLeftCol();
    }

    @Override
    public void showInPane(int topRow, int leftCol) {
        int maxrow = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (topRow > maxrow) {
            throw new IllegalArgumentException("Maximum row number is " + maxrow);
        }
        this.showInPane((short)topRow, (short)leftCol);
    }

    private void showInPane(short toprow, short leftcol) {
        this._sheet.setTopRow(toprow);
        this._sheet.setLeftCol(leftcol);
    }

    protected void shiftMerged(int startRow, int endRow, int n, boolean isRow) {
        HSSFRowShifter rowShifter = new HSSFRowShifter(this);
        rowShifter.shiftMergedRegions(startRow, endRow, n);
    }

    @Override
    public void shiftRows(int startRow, int endRow, int n) {
        this.shiftRows(startRow, endRow, n, false, false);
    }

    @Override
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {
        this.shiftRows(startRow, endRow, n, copyRowHeight, resetOriginalRowHeight, true);
    }

    private static int clip(int row) {
        return Math.min(Math.max(0, row), SpreadsheetVersion.EXCEL97.getLastRowIndex());
    }

    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight, boolean moveComments) {
        int inc;
        int s;
        if (endRow < startRow) {
            throw new IllegalArgumentException("startRow must be less than or equal to endRow. To shift rows up, use n<0.");
        }
        if (n < 0) {
            s = startRow;
            inc = 1;
        } else if (n > 0) {
            s = endRow;
            inc = -1;
        } else {
            return;
        }
        HSSFRowShifter rowShifter = new HSSFRowShifter(this);
        if (moveComments) {
            this.moveCommentsForRowShift(startRow, endRow, n);
        }
        rowShifter.shiftMergedRegions(startRow, endRow, n);
        this._sheet.getPageSettings().shiftRowBreaks(startRow, endRow, n);
        this.deleteOverwrittenHyperlinksForRowShift(startRow, endRow, n);
        for (int rowNum = s; rowNum >= startRow && rowNum <= endRow && rowNum >= 0 && rowNum < 65536; rowNum += inc) {
            HSSFRow row2Replace;
            HSSFRow row = this.getRow(rowNum);
            if (row != null) {
                this.notifyRowShifting(row);
            }
            if ((row2Replace = this.getRow(rowNum + n)) == null) {
                row2Replace = this.createRow(rowNum + n);
            }
            row2Replace.removeAllCells();
            if (row == null) continue;
            if (copyRowHeight) {
                row2Replace.setHeight(row.getHeight());
            }
            if (resetOriginalRowHeight) {
                row.setHeight((short)255);
            }
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                HSSFCell cell = (HSSFCell)cells.next();
                HSSFHyperlink link = cell.getHyperlink();
                row.removeCell(cell);
                CellValueRecordInterface cellRecord = cell.getCellValueRecord();
                cellRecord.setRow(rowNum + n);
                row2Replace.createCellFromRecord(cellRecord);
                this._sheet.addValueRecord(rowNum + n, cellRecord);
                if (link == null) continue;
                link.setFirstRow(link.getFirstRow() + n);
                link.setLastRow(link.getLastRow() + n);
            }
            row.removeAllCells();
        }
        this.recomputeFirstAndLastRowsForRowShift(startRow, endRow, n);
        int sheetIndex = this._workbook.getSheetIndex(this);
        short externSheetIndex = this._book.checkExternSheet(sheetIndex);
        String sheetName = this._workbook.getSheetName(sheetIndex);
        FormulaShifter formulaShifter = FormulaShifter.createForRowShift(externSheetIndex, sheetName, startRow, endRow, n, SpreadsheetVersion.EXCEL97);
        this.updateFormulasForShift(formulaShifter);
    }

    private void updateFormulasForShift(FormulaShifter formulaShifter) {
        int sheetIndex = this._workbook.getSheetIndex(this);
        short externSheetIndex = this._book.checkExternSheet(sheetIndex);
        this._sheet.updateFormulasAfterCellShift(formulaShifter, externSheetIndex);
        int nSheets = this._workbook.getNumberOfSheets();
        for (int i = 0; i < nSheets; ++i) {
            InternalSheet otherSheet = this._workbook.getSheetAt(i).getSheet();
            if (otherSheet == this._sheet) continue;
            short otherExtSheetIx = this._book.checkExternSheet(i);
            otherSheet.updateFormulasAfterCellShift(formulaShifter, otherExtSheetIx);
        }
        this._workbook.getWorkbook().updateNamesAfterCellShift(formulaShifter);
    }

    private void recomputeFirstAndLastRowsForRowShift(int startRow, int endRow, int n) {
        block6: {
            block5: {
                if (n <= 0) break block5;
                if (startRow == this._firstrow) {
                    this._firstrow = Math.max(startRow + n, 0);
                    for (int i = startRow + 1; i < startRow + n; ++i) {
                        if (this.getRow(i) == null) continue;
                        this._firstrow = i;
                        break;
                    }
                }
                if (endRow + n <= this._lastrow) break block6;
                this._lastrow = Math.min(endRow + n, SpreadsheetVersion.EXCEL97.getLastRowIndex());
                break block6;
            }
            if (startRow + n < this._firstrow) {
                this._firstrow = Math.max(startRow + n, 0);
            }
            if (endRow == this._lastrow) {
                this._lastrow = Math.min(endRow + n, SpreadsheetVersion.EXCEL97.getLastRowIndex());
                for (int i = endRow - 1; i > endRow + n; --i) {
                    if (this.getRow(i) == null) continue;
                    this._lastrow = i;
                    break;
                }
            }
        }
    }

    private void deleteOverwrittenHyperlinksForRowShift(int startRow, int endRow, int n) {
        int firstOverwrittenRow = startRow + n;
        int lastOverwrittenRow = endRow + n;
        for (HSSFHyperlink link : this.getHyperlinkList()) {
            int firstRow = link.getFirstRow();
            int lastRow = link.getLastRow();
            if (firstOverwrittenRow > firstRow || firstRow > lastOverwrittenRow || lastOverwrittenRow > lastRow || lastRow > lastOverwrittenRow) continue;
            this.removeHyperlink(link);
        }
    }

    private void moveCommentsForRowShift(int startRow, int endRow, int n) {
        HSSFPatriarch patriarch = this.createDrawingPatriarch();
        for (HSSFShape shape : patriarch.getChildren()) {
            HSSFComment comment;
            int r;
            if (!(shape instanceof HSSFComment) || startRow > (r = (comment = (HSSFComment)shape).getRow()) || r > endRow) continue;
            comment.setRow(HSSFSheet.clip(r + n));
        }
    }

    @Override
    public void shiftColumns(int startColumn, int endColumn, int n) {
        HSSFColumnShifter columnShifter = new HSSFColumnShifter(this);
        columnShifter.shiftColumns(startColumn, endColumn, n);
        int sheetIndex = this._workbook.getSheetIndex(this);
        short externSheetIndex = this._book.checkExternSheet(sheetIndex);
        String sheetName = this._workbook.getSheetName(sheetIndex);
        FormulaShifter formulaShifter = FormulaShifter.createForColumnShift(externSheetIndex, sheetName, startColumn, endColumn, n, SpreadsheetVersion.EXCEL97);
        this.updateFormulasForShift(formulaShifter);
    }

    protected void insertChartRecords(List<Record> records) {
        int window2Loc = this._sheet.findFirstRecordLocBySid((short)574);
        this._sheet.getRecords().addAll(window2Loc, records);
    }

    private void notifyRowShifting(HSSFRow row) {
        String msg = "Row[rownum=" + row.getRowNum() + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
        for (Cell cell : row) {
            HSSFCell hcell = (HSSFCell)cell;
            if (!hcell.isPartOfArrayFormulaGroup()) continue;
            hcell.tryToDeleteArrayFormula(msg);
        }
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        this.validateColumn(colSplit);
        this.validateRow(rowSplit);
        if (leftmostColumn < colSplit) {
            throw new IllegalArgumentException("leftmostColumn parameter must not be less than colSplit parameter");
        }
        if (topRow < rowSplit) {
            throw new IllegalArgumentException("topRow parameter must not be less than leftmostColumn parameter");
        }
        this.getSheet().createFreezePane(colSplit, rowSplit, topRow, leftmostColumn);
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit) {
        this.createFreezePane(colSplit, rowSplit, colSplit, rowSplit);
    }

    @Override
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {
        this.getSheet().createSplitPane(xSplitPos, ySplitPos, topRow, leftmostColumn, activePane);
    }

    @Override
    public PaneInformation getPaneInformation() {
        return this.getSheet().getPaneInformation();
    }

    @Override
    public void setDisplayGridlines(boolean show) {
        this._sheet.setDisplayGridlines(show);
    }

    @Override
    public boolean isDisplayGridlines() {
        return this._sheet.isDisplayGridlines();
    }

    @Override
    public void setDisplayFormulas(boolean show) {
        this._sheet.setDisplayFormulas(show);
    }

    @Override
    public boolean isDisplayFormulas() {
        return this._sheet.isDisplayFormulas();
    }

    @Override
    public void setDisplayRowColHeadings(boolean show) {
        this._sheet.setDisplayRowColHeadings(show);
    }

    @Override
    public boolean isDisplayRowColHeadings() {
        return this._sheet.isDisplayRowColHeadings();
    }

    @Override
    public void setRowBreak(int row) {
        this.validateRow(row);
        this._sheet.getPageSettings().setRowBreak(row, (short)0, (short)255);
    }

    @Override
    public boolean isRowBroken(int row) {
        return this._sheet.getPageSettings().isRowBroken(row);
    }

    @Override
    public void removeRowBreak(int row) {
        this._sheet.getPageSettings().removeRowBreak(row);
    }

    @Override
    public int[] getRowBreaks() {
        return this._sheet.getPageSettings().getRowBreaks();
    }

    @Override
    public int[] getColumnBreaks() {
        return this._sheet.getPageSettings().getColumnBreaks();
    }

    @Override
    public void setColumnBreak(int column) {
        this.validateColumn((short)column);
        this._sheet.getPageSettings().setColumnBreak((short)column, (short)0, (short)SpreadsheetVersion.EXCEL97.getLastRowIndex());
    }

    @Override
    public boolean isColumnBroken(int column) {
        return this._sheet.getPageSettings().isColumnBroken(column);
    }

    @Override
    public void removeColumnBreak(int column) {
        this._sheet.getPageSettings().removeColumnBreak(column);
    }

    protected void validateRow(int row) {
        int maxrow = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (row > maxrow) {
            throw new IllegalArgumentException("Maximum row number is " + maxrow);
        }
        if (row < 0) {
            throw new IllegalArgumentException("Minumum row number is 0");
        }
    }

    protected void validateColumn(int column) {
        int maxcol = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        if (column > maxcol) {
            throw new IllegalArgumentException("Maximum column number is " + maxcol);
        }
        if (column < 0) {
            throw new IllegalArgumentException("Minimum column number is 0");
        }
    }

    public void dumpDrawingRecords(boolean fat, PrintWriter pw) {
        this._sheet.aggregateDrawingRecords(this._book.getDrawingManager(), false);
        EscherAggregate r = (EscherAggregate)this.getSheet().findFirstRecordBySid((short)9876);
        List<EscherRecord> escherRecords = r.getEscherRecords();
        for (EscherRecord escherRecord : escherRecords) {
            if (fat) {
                pw.println(escherRecord);
                continue;
            }
            escherRecord.display(pw, 0);
        }
        pw.flush();
    }

    public EscherAggregate getDrawingEscherAggregate() {
        this._book.findDrawingGroup();
        if (this._book.getDrawingManager() == null) {
            return null;
        }
        int found = this._sheet.aggregateDrawingRecords(this._book.getDrawingManager(), false);
        if (found == -1) {
            return null;
        }
        return (EscherAggregate)this._sheet.findFirstRecordBySid((short)9876);
    }

    public HSSFPatriarch getDrawingPatriarch() {
        this._patriarch = this.getPatriarch(false);
        return this._patriarch;
    }

    public HSSFPatriarch createDrawingPatriarch() {
        this._patriarch = this.getPatriarch(true);
        return this._patriarch;
    }

    private HSSFPatriarch getPatriarch(boolean createIfMissing) {
        EscherAggregate agg;
        if (this._patriarch != null) {
            return this._patriarch;
        }
        DrawingManager2 dm = this._book.findDrawingGroup();
        if (null == dm) {
            if (!createIfMissing) {
                return null;
            }
            this._book.createDrawingGroup();
            dm = this._book.getDrawingManager();
        }
        if (null == (agg = (EscherAggregate)this._sheet.findFirstRecordBySid((short)9876))) {
            int pos = this._sheet.aggregateDrawingRecords(dm, false);
            if (-1 == pos) {
                if (createIfMissing) {
                    pos = this._sheet.aggregateDrawingRecords(dm, true);
                    agg = (EscherAggregate)this._sheet.getRecords().get(pos);
                    HSSFPatriarch patriarch = new HSSFPatriarch(this, agg);
                    patriarch.afterCreate();
                    return patriarch;
                }
                return null;
            }
            agg = (EscherAggregate)this._sheet.getRecords().get(pos);
        }
        return new HSSFPatriarch(this, agg);
    }

    @Override
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        this._sheet.setColumnGroupCollapsed(columnNumber, collapsed);
    }

    @Override
    public void groupColumn(int fromColumn, int toColumn) {
        this._sheet.groupColumnRange(fromColumn, toColumn, true);
    }

    @Override
    public void ungroupColumn(int fromColumn, int toColumn) {
        this._sheet.groupColumnRange(fromColumn, toColumn, false);
    }

    @Override
    public void groupRow(int fromRow, int toRow) {
        this._sheet.groupRowRange(fromRow, toRow, true);
    }

    @Override
    public void ungroupRow(int fromRow, int toRow) {
        this._sheet.groupRowRange(fromRow, toRow, false);
    }

    @Override
    public void setRowGroupCollapsed(int rowIndex, boolean collapse) {
        if (collapse) {
            this._sheet.getRowsAggregate().collapseRow(rowIndex);
        } else {
            this._sheet.getRowsAggregate().expandRow(rowIndex);
        }
    }

    @Override
    public void setDefaultColumnStyle(int column, CellStyle style) {
        this._sheet.setDefaultColumnStyle(column, style.getIndex());
    }

    @Override
    public void autoSizeColumn(int column) {
        this.autoSizeColumn(column, false);
    }

    @Override
    public void autoSizeColumn(int column, boolean useMergedCells) {
        double width = SheetUtil.getColumnWidth(this, column, useMergedCells);
        if (width != -1.0) {
            int maxColumnWidth = 65280;
            if ((width *= 256.0) > (double)maxColumnWidth) {
                width = maxColumnWidth;
            }
            this.setColumnWidth(column, (int)width);
        }
    }

    @Override
    public HSSFComment getCellComment(CellAddress ref) {
        return this.findCellComment(ref.getRow(), ref.getColumn());
    }

    @Override
    public HSSFHyperlink getHyperlink(int row, int column) {
        for (RecordBase rec : this._sheet.getRecords()) {
            HyperlinkRecord link;
            if (!(rec instanceof HyperlinkRecord) || (link = (HyperlinkRecord)rec).getFirstColumn() != column || link.getFirstRow() != row) continue;
            return new HSSFHyperlink(link);
        }
        return null;
    }

    @Override
    public HSSFHyperlink getHyperlink(CellAddress addr) {
        return this.getHyperlink(addr.getRow(), addr.getColumn());
    }

    public List<HSSFHyperlink> getHyperlinkList() {
        ArrayList<HSSFHyperlink> hyperlinkList = new ArrayList<HSSFHyperlink>();
        for (RecordBase rec : this._sheet.getRecords()) {
            if (!(rec instanceof HyperlinkRecord)) continue;
            HyperlinkRecord link = (HyperlinkRecord)rec;
            hyperlinkList.add(new HSSFHyperlink(link));
        }
        return hyperlinkList;
    }

    protected void removeHyperlink(HSSFHyperlink link) {
        this.removeHyperlink(link.record);
    }

    protected void removeHyperlink(HyperlinkRecord link) {
        Iterator<RecordBase> it = this._sheet.getRecords().iterator();
        while (it.hasNext()) {
            HyperlinkRecord recLink;
            RecordBase rec = it.next();
            if (!(rec instanceof HyperlinkRecord) || link != (recLink = (HyperlinkRecord)rec)) continue;
            it.remove();
            return;
        }
    }

    @Override
    public HSSFSheetConditionalFormatting getSheetConditionalFormatting() {
        return new HSSFSheetConditionalFormatting(this);
    }

    @Override
    public String getSheetName() {
        HSSFWorkbook wb = this.getWorkbook();
        int idx = wb.getSheetIndex(this);
        return wb.getSheetName(idx);
    }

    private CellRange<HSSFCell> getCellRange(CellRangeAddress range) {
        int firstRow = range.getFirstRow();
        int firstColumn = range.getFirstColumn();
        int lastRow = range.getLastRow();
        int lastColumn = range.getLastColumn();
        int height = lastRow - firstRow + 1;
        int width = lastColumn - firstColumn + 1;
        ArrayList<HSSFCell> temp = new ArrayList<HSSFCell>(height * width);
        for (int rowIn = firstRow; rowIn <= lastRow; ++rowIn) {
            for (int colIn = firstColumn; colIn <= lastColumn; ++colIn) {
                HSSFCell cell;
                HSSFRow row = this.getRow(rowIn);
                if (row == null) {
                    row = this.createRow(rowIn);
                }
                if ((cell = row.getCell(colIn)) == null) {
                    cell = row.createCell(colIn);
                }
                temp.add(cell);
            }
        }
        return SSCellRange.create(firstRow, firstColumn, height, width, temp, HSSFCell.class);
    }

    public CellRange<HSSFCell> setArrayFormula(String formula, CellRangeAddress range) {
        int sheetIndex = this._workbook.getSheetIndex(this);
        Ptg[] ptgs = HSSFFormulaParser.parse(formula, this._workbook, FormulaType.ARRAY, sheetIndex);
        CellRange<HSSFCell> cells = this.getCellRange(range);
        for (HSSFCell c : cells) {
            c.setCellArrayFormula(range);
        }
        HSSFCell mainArrayFormulaCell = cells.getTopLeftCell();
        FormulaRecordAggregate agg = (FormulaRecordAggregate)mainArrayFormulaCell.getCellValueRecord();
        agg.setArrayFormula(range, ptgs);
        return cells;
    }

    public CellRange<HSSFCell> removeArrayFormula(Cell cell) {
        if (cell.getSheet() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this sheet.");
        }
        CellValueRecordInterface rec = ((HSSFCell)cell).getCellValueRecord();
        if (!(rec instanceof FormulaRecordAggregate)) {
            String ref = new CellReference(cell).formatAsString();
            throw new IllegalArgumentException("Cell " + ref + " is not part of an array formula.");
        }
        FormulaRecordAggregate fra = (FormulaRecordAggregate)rec;
        CellRangeAddress range = fra.removeArrayFormula(cell.getRowIndex(), cell.getColumnIndex());
        CellRange<HSSFCell> result = this.getCellRange(range);
        for (Cell cell2 : result) {
            cell2.setBlank();
        }
        return result;
    }

    @Override
    public DataValidationHelper getDataValidationHelper() {
        return new HSSFDataValidationHelper(this);
    }

    @Override
    public HSSFAutoFilter setAutoFilter(CellRangeAddress range) {
        int firstRow;
        int sheetIndex;
        InternalWorkbook workbook = this._workbook.getWorkbook();
        NameRecord name = workbook.getSpecificBuiltinRecord((byte)13, (sheetIndex = this._workbook.getSheetIndex(this)) + 1);
        if (name == null) {
            name = workbook.createBuiltInName((byte)13, sheetIndex + 1);
        }
        if ((firstRow = range.getFirstRow()) == -1) {
            firstRow = 0;
        }
        Area3DPtg ptg = new Area3DPtg(firstRow, range.getLastRow(), range.getFirstColumn(), range.getLastColumn(), false, false, false, false, sheetIndex);
        name.setNameDefinition(new Ptg[]{ptg});
        AutoFilterInfoRecord r = new AutoFilterInfoRecord();
        int numcols = 1 + range.getLastColumn() - range.getFirstColumn();
        r.setNumEntries((short)numcols);
        int idx = this._sheet.findFirstRecordLocBySid((short)512);
        this._sheet.getRecords().add(idx, r);
        HSSFPatriarch p = this.createDrawingPatriarch();
        int firstColumn = range.getFirstColumn();
        int lastColumn = range.getLastColumn();
        for (int col = firstColumn; col <= lastColumn; ++col) {
            p.createComboBox(new HSSFClientAnchor(0, 0, 0, 0, (short)col, firstRow, (short)(col + 1), firstRow + 1));
        }
        return new HSSFAutoFilter(this);
    }

    protected HSSFComment findCellComment(int row, int column) {
        HSSFPatriarch patriarch = this.getDrawingPatriarch();
        if (null == patriarch) {
            patriarch = this.createDrawingPatriarch();
        }
        return this.lookForComment(patriarch, row, column);
    }

    private HSSFComment lookForComment(HSSFShapeContainer container, int row, int column) {
        for (HSSFShape object : container.getChildren()) {
            HSSFComment comment;
            HSSFShape shape = object;
            if (shape instanceof HSSFShapeGroup) {
                HSSFComment res = this.lookForComment((HSSFShapeContainer)((Object)shape), row, column);
                if (null == res) continue;
                return res;
            }
            if (!(shape instanceof HSSFComment) || !(comment = (HSSFComment)shape).hasPosition() || comment.getColumn() != column || comment.getRow() != row) continue;
            return comment;
        }
        return null;
    }

    public Map<CellAddress, HSSFComment> getCellComments() {
        HSSFPatriarch patriarch = this.getDrawingPatriarch();
        if (null == patriarch) {
            patriarch = this.createDrawingPatriarch();
        }
        TreeMap<CellAddress, HSSFComment> locations = new TreeMap<CellAddress, HSSFComment>();
        this.findCellCommentLocations(patriarch, locations);
        return locations;
    }

    private void findCellCommentLocations(HSSFShapeContainer container, Map<CellAddress, HSSFComment> locations) {
        for (HSSFShape object : container.getChildren()) {
            HSSFComment comment;
            HSSFShape shape = object;
            if (shape instanceof HSSFShapeGroup) {
                this.findCellCommentLocations((HSSFShapeGroup)shape, locations);
                continue;
            }
            if (!(shape instanceof HSSFComment) || !(comment = (HSSFComment)shape).hasPosition()) continue;
            locations.put(new CellAddress(comment.getRow(), comment.getColumn()), comment);
        }
    }

    @Override
    public CellRangeAddress getRepeatingRows() {
        return this.getRepeatingRowsOrColums(true);
    }

    @Override
    public CellRangeAddress getRepeatingColumns() {
        return this.getRepeatingRowsOrColums(false);
    }

    @Override
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {
        CellRangeAddress columnRangeRef = this.getRepeatingColumns();
        this.setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    @Override
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {
        CellRangeAddress rowRangeRef = this.getRepeatingRows();
        this.setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    private void setRepeatingRowsAndColumns(CellRangeAddress rowDef, CellRangeAddress colDef) {
        int sheetIndex = this._workbook.getSheetIndex(this);
        int maxRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        int maxColIndex = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        int col1 = -1;
        int col2 = -1;
        int row1 = -1;
        int row2 = -1;
        if (rowDef != null) {
            row1 = rowDef.getFirstRow();
            row2 = rowDef.getLastRow();
            if (row1 == -1 && row2 != -1 || row1 > row2 || row1 < 0 || row1 > maxRowIndex || row2 < 0 || row2 > maxRowIndex) {
                throw new IllegalArgumentException("Invalid row range specification");
            }
        }
        if (colDef != null) {
            col1 = colDef.getFirstColumn();
            col2 = colDef.getLastColumn();
            if (col1 == -1 && col2 != -1 || col1 > col2 || col1 < 0 || col1 > maxColIndex || col2 < 0 || col2 > maxColIndex) {
                throw new IllegalArgumentException("Invalid column range specification");
            }
        }
        short externSheetIndex = this._workbook.getWorkbook().checkExternSheet(sheetIndex);
        boolean setBoth = rowDef != null && colDef != null;
        boolean removeAll = rowDef == null && colDef == null;
        HSSFName name = this._workbook.getBuiltInName((byte)7, sheetIndex);
        if (removeAll) {
            if (name != null) {
                this._workbook.removeName(name);
            }
            return;
        }
        if (name == null) {
            name = this._workbook.createBuiltInName((byte)7, sheetIndex);
        }
        ArrayList<Ptg> ptgList = new ArrayList<Ptg>();
        if (setBoth) {
            int exprsSize = 23;
            ptgList.add(new MemFuncPtg(23));
        }
        if (colDef != null) {
            Area3DPtg colArea = new Area3DPtg(0, maxRowIndex, col1, col2, false, false, false, false, externSheetIndex);
            ptgList.add(colArea);
        }
        if (rowDef != null) {
            Area3DPtg rowArea = new Area3DPtg(row1, row2, 0, maxColIndex, false, false, false, false, externSheetIndex);
            ptgList.add(rowArea);
        }
        if (setBoth) {
            ptgList.add(UnionPtg.instance);
        }
        Ptg[] ptgs = new Ptg[ptgList.size()];
        ptgList.toArray(ptgs);
        name.setNameDefinition(ptgs);
        HSSFPrintSetup printSetup = this.getPrintSetup();
        printSetup.setValidSettings(false);
        this.setActive(true);
    }

    private CellRangeAddress getRepeatingRowsOrColums(boolean rows) {
        NameRecord rec = this.getBuiltinNameRecord((byte)7);
        if (rec == null) {
            return null;
        }
        Ptg[] nameDefinition = rec.getNameDefinition();
        if (nameDefinition == null) {
            return null;
        }
        int maxRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        int maxColIndex = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
        for (Ptg ptg : nameDefinition) {
            if (!(ptg instanceof Area3DPtg)) continue;
            Area3DPtg areaPtg = (Area3DPtg)ptg;
            if (areaPtg.getFirstColumn() == 0 && areaPtg.getLastColumn() == maxColIndex) {
                if (!rows) continue;
                return new CellRangeAddress(areaPtg.getFirstRow(), areaPtg.getLastRow(), -1, -1);
            }
            if (areaPtg.getFirstRow() != 0 || areaPtg.getLastRow() != maxRowIndex || rows) continue;
            return new CellRangeAddress(-1, -1, areaPtg.getFirstColumn(), areaPtg.getLastColumn());
        }
        return null;
    }

    private NameRecord getBuiltinNameRecord(byte builtinCode) {
        int sheetIndex = this._workbook.getSheetIndex(this);
        int recIndex = this._workbook.findExistingBuiltinNameRecordIdx(sheetIndex, builtinCode);
        if (recIndex == -1) {
            return null;
        }
        return this._workbook.getNameRecord(recIndex);
    }

    @Override
    public int getColumnOutlineLevel(int columnIndex) {
        return this._sheet.getColumnOutlineLevel(columnIndex);
    }

    @Override
    public CellAddress getActiveCell() {
        int row = this._sheet.getActiveCellRow();
        short col = this._sheet.getActiveCellCol();
        return new CellAddress(row, col);
    }

    @Override
    public void setActiveCell(CellAddress address) {
        int row = address.getRow();
        short col = (short)address.getColumn();
        this._sheet.setActiveCellRow(row);
        this._sheet.setActiveCellCol(col);
    }
}

