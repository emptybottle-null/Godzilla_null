/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.streaming.AutoSizeColumnTracker;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SheetDataWriter;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class SXSSFSheet
implements Sheet {
    final XSSFSheet _sh;
    private final SXSSFWorkbook _workbook;
    private final TreeMap<Integer, SXSSFRow> _rows = new TreeMap();
    private final SheetDataWriter _writer;
    private int _randomAccessWindowSize = 100;
    private final AutoSizeColumnTracker _autoSizeColumnTracker;
    private int outlineLevelRow;
    private int lastFlushedRowNumber = -1;
    private boolean allFlushed;

    public SXSSFSheet(SXSSFWorkbook workbook, XSSFSheet xSheet) throws IOException {
        this._workbook = workbook;
        this._sh = xSheet;
        this._writer = workbook.createSheetDataWriter();
        this.setRandomAccessWindowSize(this._workbook.getRandomAccessWindowSize());
        this._autoSizeColumnTracker = new AutoSizeColumnTracker(this);
    }

    @Internal
    SheetDataWriter getSheetDataWriter() {
        return this._writer;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        this.flushRows(0);
        this._writer.close();
        return this._writer.getWorksheetXMLInputStream();
    }

    @Override
    public Iterator<Row> iterator() {
        return this.rowIterator();
    }

    @Override
    public SXSSFRow createRow(int rownum) {
        int maxrow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (rownum < 0 || rownum > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rownum + ") outside allowable range (0.." + maxrow + ")");
        }
        if (rownum <= this._writer.getLastFlushedRow()) {
            throw new IllegalArgumentException("Attempting to write a row[" + rownum + "] in the range [0," + this._writer.getLastFlushedRow() + "] that is already written to disk.");
        }
        if (this._sh.getPhysicalNumberOfRows() > 0 && rownum <= this._sh.getLastRowNum()) {
            throw new IllegalArgumentException("Attempting to write a row[" + rownum + "] in the range [0," + this._sh.getLastRowNum() + "] that is already written to disk.");
        }
        SXSSFRow newRow = new SXSSFRow(this);
        this._rows.put(rownum, newRow);
        this.allFlushed = false;
        if (this._randomAccessWindowSize >= 0 && this._rows.size() > this._randomAccessWindowSize) {
            try {
                this.flushRows(this._randomAccessWindowSize);
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        return newRow;
    }

    @Override
    public void removeRow(Row row) {
        if (row.getSheet() != this) {
            throw new IllegalArgumentException("Specified row does not belong to this sheet");
        }
        Iterator<Map.Entry<Integer, SXSSFRow>> iter = this._rows.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, SXSSFRow> entry = iter.next();
            if (entry.getValue() != row) continue;
            iter.remove();
            return;
        }
    }

    @Override
    public SXSSFRow getRow(int rownum) {
        return this._rows.get(rownum);
    }

    @Override
    public int getPhysicalNumberOfRows() {
        return this._rows.size() + this._writer.getNumberOfFlushedRows();
    }

    @Override
    public int getFirstRowNum() {
        if (this._writer.getNumberOfFlushedRows() > 0) {
            return this._writer.getLowestIndexOfFlushedRows();
        }
        return this._rows.size() == 0 ? -1 : this._rows.firstKey();
    }

    @Override
    public int getLastRowNum() {
        return this._rows.size() == 0 ? -1 : this._rows.lastKey();
    }

    @Override
    public void setColumnHidden(int columnIndex, boolean hidden) {
        this._sh.setColumnHidden(columnIndex, hidden);
    }

    @Override
    public boolean isColumnHidden(int columnIndex) {
        return this._sh.isColumnHidden(columnIndex);
    }

    @Override
    public void setColumnWidth(int columnIndex, int width) {
        this._sh.setColumnWidth(columnIndex, width);
    }

    @Override
    public int getColumnWidth(int columnIndex) {
        return this._sh.getColumnWidth(columnIndex);
    }

    @Override
    public float getColumnWidthInPixels(int columnIndex) {
        return this._sh.getColumnWidthInPixels(columnIndex);
    }

    @Override
    public void setDefaultColumnWidth(int width) {
        this._sh.setDefaultColumnWidth(width);
    }

    @Override
    public int getDefaultColumnWidth() {
        return this._sh.getDefaultColumnWidth();
    }

    @Override
    public short getDefaultRowHeight() {
        return this._sh.getDefaultRowHeight();
    }

    @Override
    public float getDefaultRowHeightInPoints() {
        return this._sh.getDefaultRowHeightInPoints();
    }

    @Override
    public void setDefaultRowHeight(short height) {
        this._sh.setDefaultRowHeight(height);
    }

    @Override
    public void setDefaultRowHeightInPoints(float height) {
        this._sh.setDefaultRowHeightInPoints(height);
    }

    @Override
    public CellStyle getColumnStyle(int column) {
        return this._sh.getColumnStyle(column);
    }

    @Override
    public int addMergedRegion(CellRangeAddress region) {
        return this._sh.addMergedRegion(region);
    }

    @Override
    public int addMergedRegionUnsafe(CellRangeAddress region) {
        return this._sh.addMergedRegionUnsafe(region);
    }

    @Override
    public void validateMergedRegions() {
        this._sh.validateMergedRegions();
    }

    @Override
    public void setVerticallyCenter(boolean value) {
        this._sh.setVerticallyCenter(value);
    }

    @Override
    public void setHorizontallyCenter(boolean value) {
        this._sh.setHorizontallyCenter(value);
    }

    @Override
    public boolean getHorizontallyCenter() {
        return this._sh.getHorizontallyCenter();
    }

    @Override
    public boolean getVerticallyCenter() {
        return this._sh.getVerticallyCenter();
    }

    @Override
    public void removeMergedRegion(int index) {
        this._sh.removeMergedRegion(index);
    }

    @Override
    public void removeMergedRegions(Collection<Integer> indices) {
        this._sh.removeMergedRegions(indices);
    }

    @Override
    public int getNumMergedRegions() {
        return this._sh.getNumMergedRegions();
    }

    @Override
    public CellRangeAddress getMergedRegion(int index) {
        return this._sh.getMergedRegion(index);
    }

    @Override
    public List<CellRangeAddress> getMergedRegions() {
        return this._sh.getMergedRegions();
    }

    @Override
    public Iterator<Row> rowIterator() {
        Iterator<Row> result = this._rows.values().iterator();
        return result;
    }

    @Override
    public void setAutobreaks(boolean value) {
        this._sh.setAutobreaks(value);
    }

    @Override
    public void setDisplayGuts(boolean value) {
        this._sh.setDisplayGuts(value);
    }

    @Override
    public void setDisplayZeros(boolean value) {
        this._sh.setDisplayZeros(value);
    }

    @Override
    public boolean isDisplayZeros() {
        return this._sh.isDisplayZeros();
    }

    @Override
    public void setRightToLeft(boolean value) {
        this._sh.setRightToLeft(value);
    }

    @Override
    public boolean isRightToLeft() {
        return this._sh.isRightToLeft();
    }

    @Override
    public void setFitToPage(boolean value) {
        this._sh.setFitToPage(value);
    }

    @Override
    public void setRowSumsBelow(boolean value) {
        this._sh.setRowSumsBelow(value);
    }

    @Override
    public void setRowSumsRight(boolean value) {
        this._sh.setRowSumsRight(value);
    }

    @Override
    public boolean getAutobreaks() {
        return this._sh.getAutobreaks();
    }

    @Override
    public boolean getDisplayGuts() {
        return this._sh.getDisplayGuts();
    }

    @Override
    public boolean getFitToPage() {
        return this._sh.getFitToPage();
    }

    @Override
    public boolean getRowSumsBelow() {
        return this._sh.getRowSumsBelow();
    }

    @Override
    public boolean getRowSumsRight() {
        return this._sh.getRowSumsRight();
    }

    @Override
    public boolean isPrintGridlines() {
        return this._sh.isPrintGridlines();
    }

    @Override
    public void setPrintGridlines(boolean show) {
        this._sh.setPrintGridlines(show);
    }

    @Override
    public boolean isPrintRowAndColumnHeadings() {
        return this._sh.isPrintRowAndColumnHeadings();
    }

    @Override
    public void setPrintRowAndColumnHeadings(boolean show) {
        this._sh.setPrintRowAndColumnHeadings(show);
    }

    @Override
    public PrintSetup getPrintSetup() {
        return this._sh.getPrintSetup();
    }

    @Override
    public Header getHeader() {
        return this._sh.getHeader();
    }

    @Override
    public Footer getFooter() {
        return this._sh.getFooter();
    }

    @Override
    public void setSelected(boolean value) {
        this._sh.setSelected(value);
    }

    @Override
    public double getMargin(short margin) {
        return this._sh.getMargin(margin);
    }

    @Override
    public void setMargin(short margin, double size) {
        this._sh.setMargin(margin, size);
    }

    @Override
    public boolean getProtect() {
        return this._sh.getProtect();
    }

    @Override
    public void protectSheet(String password) {
        this._sh.protectSheet(password);
    }

    @Override
    public boolean getScenarioProtect() {
        return this._sh.getScenarioProtect();
    }

    @Override
    public void setZoom(int scale) {
        this._sh.setZoom(scale);
    }

    @Override
    public short getTopRow() {
        return this._sh.getTopRow();
    }

    @Override
    public short getLeftCol() {
        return this._sh.getLeftCol();
    }

    @Override
    public void showInPane(int topRow, int leftCol) {
        this._sh.showInPane(topRow, leftCol);
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        this._sh.setForceFormulaRecalculation(value);
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        return this._sh.getForceFormulaRecalculation();
    }

    @Override
    @NotImplemented
    public void shiftRows(int startRow, int endRow, int n) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    @NotImplemented
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        this._sh.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit) {
        this._sh.createFreezePane(colSplit, rowSplit);
    }

    @Override
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {
        this._sh.createSplitPane(xSplitPos, ySplitPos, leftmostColumn, topRow, activePane);
    }

    @Override
    public PaneInformation getPaneInformation() {
        return this._sh.getPaneInformation();
    }

    @Override
    public void setDisplayGridlines(boolean show) {
        this._sh.setDisplayGridlines(show);
    }

    @Override
    public boolean isDisplayGridlines() {
        return this._sh.isDisplayGridlines();
    }

    @Override
    public void setDisplayFormulas(boolean show) {
        this._sh.setDisplayFormulas(show);
    }

    @Override
    public boolean isDisplayFormulas() {
        return this._sh.isDisplayFormulas();
    }

    @Override
    public void setDisplayRowColHeadings(boolean show) {
        this._sh.setDisplayRowColHeadings(show);
    }

    @Override
    public boolean isDisplayRowColHeadings() {
        return this._sh.isDisplayRowColHeadings();
    }

    @Override
    public void setRowBreak(int row) {
        this._sh.setRowBreak(row);
    }

    @Override
    public boolean isRowBroken(int row) {
        return this._sh.isRowBroken(row);
    }

    @Override
    public void removeRowBreak(int row) {
        this._sh.removeRowBreak(row);
    }

    @Override
    public int[] getRowBreaks() {
        return this._sh.getRowBreaks();
    }

    @Override
    public int[] getColumnBreaks() {
        return this._sh.getColumnBreaks();
    }

    @Override
    public void setColumnBreak(int column) {
        this._sh.setColumnBreak(column);
    }

    @Override
    public boolean isColumnBroken(int column) {
        return this._sh.isColumnBroken(column);
    }

    @Override
    public void removeColumnBreak(int column) {
        this._sh.removeColumnBreak(column);
    }

    @Override
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        this._sh.setColumnGroupCollapsed(columnNumber, collapsed);
    }

    @Override
    public void groupColumn(int fromColumn, int toColumn) {
        this._sh.groupColumn(fromColumn, toColumn);
    }

    @Override
    public void ungroupColumn(int fromColumn, int toColumn) {
        this._sh.ungroupColumn(fromColumn, toColumn);
    }

    @Override
    public void groupRow(int fromRow, int toRow) {
        for (SXSSFRow row : this._rows.subMap(fromRow, toRow + 1).values()) {
            int level = row.getOutlineLevel() + 1;
            row.setOutlineLevel(level);
            if (level <= this.outlineLevelRow) continue;
            this.outlineLevelRow = level;
        }
        this.setWorksheetOutlineLevelRow();
    }

    public void setRowOutlineLevel(int rownum, int level) {
        SXSSFRow row = this._rows.get(rownum);
        row.setOutlineLevel(level);
        if (level > 0 && level > this.outlineLevelRow) {
            this.outlineLevelRow = level;
            this.setWorksheetOutlineLevelRow();
        }
    }

    private void setWorksheetOutlineLevelRow() {
        CTSheetFormatPr pr;
        CTWorksheet ct = this._sh.getCTWorksheet();
        CTSheetFormatPr cTSheetFormatPr = pr = ct.isSetSheetFormatPr() ? ct.getSheetFormatPr() : ct.addNewSheetFormatPr();
        if (this.outlineLevelRow > 0) {
            pr.setOutlineLevelRow((short)this.outlineLevelRow);
        }
    }

    @Override
    public void ungroupRow(int fromRow, int toRow) {
        this._sh.ungroupRow(fromRow, toRow);
    }

    @Override
    public void setRowGroupCollapsed(int row, boolean collapse) {
        if (!collapse) {
            throw new RuntimeException("Unable to expand row: Not Implemented");
        }
        this.collapseRow(row);
    }

    private void collapseRow(int rowIndex) {
        SXSSFRow row = this.getRow(rowIndex);
        if (row == null) {
            throw new IllegalArgumentException("Invalid row number(" + rowIndex + "). Row does not exist.");
        }
        int startRow = this.findStartOfRowOutlineGroup(rowIndex);
        int lastRow = this.writeHidden(row, startRow);
        SXSSFRow lastRowObj = this.getRow(lastRow);
        if (lastRowObj != null) {
            lastRowObj.setCollapsed(true);
        } else {
            SXSSFRow newRow = this.createRow(lastRow);
            newRow.setCollapsed(true);
        }
    }

    private int findStartOfRowOutlineGroup(int rowIndex) {
        SXSSFRow row = this.getRow(rowIndex);
        int level = row.getOutlineLevel();
        if (level == 0) {
            throw new IllegalArgumentException("Outline level is zero for the row (" + rowIndex + ").");
        }
        int currentRow = rowIndex;
        while (this.getRow(currentRow) != null) {
            if (this.getRow(currentRow).getOutlineLevel() < level) {
                return currentRow + 1;
            }
            --currentRow;
        }
        return currentRow + 1;
    }

    private int writeHidden(SXSSFRow xRow, int rowIndex) {
        int level = xRow.getOutlineLevel();
        SXSSFRow currRow = this.getRow(rowIndex);
        while (currRow != null && currRow.getOutlineLevel() >= level) {
            currRow.setHidden(true);
            currRow = this.getRow(++rowIndex);
        }
        return rowIndex;
    }

    @Override
    public void setDefaultColumnStyle(int column, CellStyle style) {
        this._sh.setDefaultColumnStyle(column, style);
    }

    public void trackColumnForAutoSizing(int column) {
        this._autoSizeColumnTracker.trackColumn(column);
    }

    public void trackColumnsForAutoSizing(Collection<Integer> columns) {
        this._autoSizeColumnTracker.trackColumns(columns);
    }

    public void trackAllColumnsForAutoSizing() {
        this._autoSizeColumnTracker.trackAllColumns();
    }

    public boolean untrackColumnForAutoSizing(int column) {
        return this._autoSizeColumnTracker.untrackColumn(column);
    }

    public boolean untrackColumnsForAutoSizing(Collection<Integer> columns) {
        return this._autoSizeColumnTracker.untrackColumns(columns);
    }

    public void untrackAllColumnsForAutoSizing() {
        this._autoSizeColumnTracker.untrackAllColumns();
    }

    public boolean isColumnTrackedForAutoSizing(int column) {
        return this._autoSizeColumnTracker.isColumnTracked(column);
    }

    public Set<Integer> getTrackedColumnsForAutoSizing() {
        return this._autoSizeColumnTracker.getTrackedColumns();
    }

    @Override
    public void autoSizeColumn(int column) {
        this.autoSizeColumn(column, false);
    }

    @Override
    public void autoSizeColumn(int column, boolean useMergedCells) {
        int flushedWidth;
        try {
            flushedWidth = this._autoSizeColumnTracker.getBestFitColumnWidth(column, useMergedCells);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Could not auto-size column. Make sure the column was tracked prior to auto-sizing the column.", e);
        }
        int activeWidth = (int)(256.0 * SheetUtil.getColumnWidth(this, column, useMergedCells));
        int bestFitWidth = Math.max(flushedWidth, activeWidth);
        if (bestFitWidth > 0) {
            int maxColumnWidth = 65280;
            int width = Math.min(bestFitWidth, 65280);
            this.setColumnWidth(column, width);
        }
    }

    @Override
    public XSSFComment getCellComment(CellAddress ref) {
        return this._sh.getCellComment(ref);
    }

    public Map<CellAddress, XSSFComment> getCellComments() {
        return this._sh.getCellComments();
    }

    @Override
    public XSSFHyperlink getHyperlink(int row, int column) {
        return this._sh.getHyperlink(row, column);
    }

    @Override
    public XSSFHyperlink getHyperlink(CellAddress addr) {
        return this._sh.getHyperlink(addr);
    }

    public List<XSSFHyperlink> getHyperlinkList() {
        return this._sh.getHyperlinkList();
    }

    public XSSFDrawing getDrawingPatriarch() {
        return this._sh.getDrawingPatriarch();
    }

    public SXSSFDrawing createDrawingPatriarch() {
        return new SXSSFDrawing(this.getWorkbook(), this._sh.createDrawingPatriarch());
    }

    @Override
    public SXSSFWorkbook getWorkbook() {
        return this._workbook;
    }

    @Override
    public String getSheetName() {
        return this._sh.getSheetName();
    }

    @Override
    public boolean isSelected() {
        return this._sh.isSelected();
    }

    @Override
    public CellRange<? extends Cell> setArrayFormula(String formula, CellRangeAddress range) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public CellRange<? extends Cell> removeArrayFormula(Cell cell) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public DataValidationHelper getDataValidationHelper() {
        return this._sh.getDataValidationHelper();
    }

    public List<XSSFDataValidation> getDataValidations() {
        return this._sh.getDataValidations();
    }

    @Override
    public void addValidationData(DataValidation dataValidation) {
        this._sh.addValidationData(dataValidation);
    }

    @Override
    public AutoFilter setAutoFilter(CellRangeAddress range) {
        return this._sh.setAutoFilter(range);
    }

    @Override
    public SheetConditionalFormatting getSheetConditionalFormatting() {
        return this._sh.getSheetConditionalFormatting();
    }

    @Override
    public CellRangeAddress getRepeatingRows() {
        return this._sh.getRepeatingRows();
    }

    @Override
    public CellRangeAddress getRepeatingColumns() {
        return this._sh.getRepeatingColumns();
    }

    @Override
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {
        this._sh.setRepeatingRows(rowRangeRef);
    }

    @Override
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {
        this._sh.setRepeatingColumns(columnRangeRef);
    }

    public void setRandomAccessWindowSize(int value) {
        if (value == 0 || value < -1) {
            throw new IllegalArgumentException("RandomAccessWindowSize must be either -1 or a positive integer");
        }
        this._randomAccessWindowSize = value;
    }

    public boolean areAllRowsFlushed() {
        return this.allFlushed;
    }

    public int getLastFlushedRowNum() {
        return this.lastFlushedRowNumber;
    }

    public void flushRows(int remaining) throws IOException {
        while (this._rows.size() > remaining) {
            this.flushOneRow();
        }
        if (remaining == 0) {
            this.allFlushed = true;
        }
    }

    public void flushRows() throws IOException {
        this.flushRows(0);
    }

    private void flushOneRow() throws IOException {
        Integer firstRowNum = this._rows.firstKey();
        if (firstRowNum != null) {
            int rowIndex = firstRowNum;
            SXSSFRow row = this._rows.get(firstRowNum);
            this._autoSizeColumnTracker.updateColumnWidths(row);
            this._writer.writeRow(rowIndex, row);
            this._rows.remove(firstRowNum);
            this.lastFlushedRowNumber = rowIndex;
        }
    }

    public void changeRowNum(SXSSFRow row, int newRowNum) {
        this.removeRow(row);
        this._rows.put(newRowNum, row);
    }

    public int getRowNum(SXSSFRow row) {
        for (Map.Entry<Integer, SXSSFRow> entry : this._rows.entrySet()) {
            if (entry.getValue() != row) continue;
            return entry.getKey();
        }
        return -1;
    }

    boolean dispose() throws IOException {
        if (!this.allFlushed) {
            this.flushRows();
        }
        return this._writer.dispose();
    }

    @Override
    public int getColumnOutlineLevel(int columnIndex) {
        return this._sh.getColumnOutlineLevel(columnIndex);
    }

    @Override
    public CellAddress getActiveCell() {
        return this._sh.getActiveCell();
    }

    @Override
    public void setActiveCell(CellAddress address) {
        this._sh.setActiveCell(address);
    }

    public XSSFColor getTabColor() {
        return this._sh.getTabColor();
    }

    public void setTabColor(XSSFColor color) {
        this._sh.setTabColor(color);
    }

    public void enableLocking() {
        this.safeGetProtectionField().setSheet(true);
    }

    public void disableLocking() {
        this.safeGetProtectionField().setSheet(false);
    }

    public void lockAutoFilter(boolean enabled) {
        this.safeGetProtectionField().setAutoFilter(enabled);
    }

    public void lockDeleteColumns(boolean enabled) {
        this.safeGetProtectionField().setDeleteColumns(enabled);
    }

    public void lockDeleteRows(boolean enabled) {
        this.safeGetProtectionField().setDeleteRows(enabled);
    }

    public void lockFormatCells(boolean enabled) {
        this.safeGetProtectionField().setFormatCells(enabled);
    }

    public void lockFormatColumns(boolean enabled) {
        this.safeGetProtectionField().setFormatColumns(enabled);
    }

    public void lockFormatRows(boolean enabled) {
        this.safeGetProtectionField().setFormatRows(enabled);
    }

    public void lockInsertColumns(boolean enabled) {
        this.safeGetProtectionField().setInsertColumns(enabled);
    }

    public void lockInsertHyperlinks(boolean enabled) {
        this.safeGetProtectionField().setInsertHyperlinks(enabled);
    }

    public void lockInsertRows(boolean enabled) {
        this.safeGetProtectionField().setInsertRows(enabled);
    }

    public void lockPivotTables(boolean enabled) {
        this.safeGetProtectionField().setPivotTables(enabled);
    }

    public void lockSort(boolean enabled) {
        this.safeGetProtectionField().setSort(enabled);
    }

    public void lockObjects(boolean enabled) {
        this.safeGetProtectionField().setObjects(enabled);
    }

    public void lockScenarios(boolean enabled) {
        this.safeGetProtectionField().setScenarios(enabled);
    }

    public void lockSelectLockedCells(boolean enabled) {
        this.safeGetProtectionField().setSelectLockedCells(enabled);
    }

    public void lockSelectUnlockedCells(boolean enabled) {
        this.safeGetProtectionField().setSelectUnlockedCells(enabled);
    }

    private CTSheetProtection safeGetProtectionField() {
        CTWorksheet ct = this._sh.getCTWorksheet();
        if (!this.isSheetProtectionEnabled()) {
            return ct.addNewSheetProtection();
        }
        return ct.getSheetProtection();
    }

    boolean isSheetProtectionEnabled() {
        CTWorksheet ct = this._sh.getCTWorksheet();
        return ct.isSetSheetProtection();
    }

    public void setTabColor(int colorIndex) {
        CTWorksheet ct = this._sh.getCTWorksheet();
        CTSheetPr pr = ct.getSheetPr();
        if (pr == null) {
            pr = ct.addNewSheetPr();
        }
        CTColor color = CTColor.Factory.newInstance();
        color.setIndexed(colorIndex);
        pr.setTabColor(color);
    }

    @Override
    @NotImplemented
    public void shiftColumns(int startColumn, int endColumn, int n) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}

