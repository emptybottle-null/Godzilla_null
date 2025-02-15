/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;

public class XSSFRow
implements Row,
Comparable<XSSFRow> {
    private final CTRow _row;
    private final TreeMap<Integer, XSSFCell> _cells;
    private final XSSFSheet _sheet;

    protected XSSFRow(CTRow row, XSSFSheet sheet) {
        this._row = row;
        this._sheet = sheet;
        this._cells = new TreeMap();
        for (CTCell c : row.getCArray()) {
            XSSFCell cell = new XSSFCell(this, c);
            Integer colI = cell.getColumnIndex();
            this._cells.put(colI, cell);
            sheet.onReadCell(cell);
        }
        if (!row.isSetR()) {
            int nextRowNum = sheet.getLastRowNum() + 2;
            if (nextRowNum == 2 && sheet.getPhysicalNumberOfRows() == 0) {
                nextRowNum = 1;
            }
            row.setR(nextRowNum);
        }
    }

    @Override
    public XSSFSheet getSheet() {
        return this._sheet;
    }

    @Override
    public Iterator<Cell> cellIterator() {
        return this._cells.values().iterator();
    }

    @Override
    public Iterator<Cell> iterator() {
        return this.cellIterator();
    }

    @Override
    public int compareTo(XSSFRow other) {
        if (this.getSheet() != other.getSheet()) {
            throw new IllegalArgumentException("The compared rows must belong to the same sheet");
        }
        int thisRow = this.getRowNum();
        int otherRow = other.getRowNum();
        return Integer.compare(thisRow, otherRow);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFRow)) {
            return false;
        }
        XSSFRow other = (XSSFRow)obj;
        return this.getRowNum() == other.getRowNum() && this.getSheet() == other.getSheet();
    }

    public int hashCode() {
        return this._row.hashCode();
    }

    @Override
    public XSSFCell createCell(int columnIndex) {
        return this.createCell(columnIndex, CellType.BLANK);
    }

    @Override
    public XSSFCell createCell(int columnIndex, CellType type) {
        CTCell ctCell;
        Integer colI = columnIndex;
        XSSFCell prev = this._cells.get(colI);
        if (prev != null) {
            ctCell = prev.getCTCell();
            ctCell.set(CTCell.Factory.newInstance());
        } else {
            ctCell = this._row.addNewC();
        }
        XSSFCell xcell = new XSSFCell(this, ctCell);
        try {
            xcell.setCellNum(columnIndex);
        } catch (IllegalArgumentException e) {
            this._row.removeC(this._row.getCList().size() - 1);
            throw e;
        }
        if (type != CellType.BLANK && type != CellType.FORMULA) {
            XSSFRow.setDefaultValue(xcell, type);
        }
        this._cells.put(colI, xcell);
        return xcell;
    }

    private static void setDefaultValue(XSSFCell cell, CellType type) {
        switch (type) {
            case NUMERIC: {
                cell.setCellValue(0.0);
                break;
            }
            case STRING: {
                cell.setCellValue("");
                break;
            }
            case BOOLEAN: {
                cell.setCellValue(false);
                break;
            }
            case ERROR: {
                cell.setCellErrorValue(FormulaError._NO_ERROR);
                break;
            }
            default: {
                throw new AssertionError((Object)("Unknown cell-type specified: " + (Object)((Object)type)));
            }
        }
    }

    @Override
    public XSSFCell getCell(int cellnum) {
        return this.getCell(cellnum, this._sheet.getWorkbook().getMissingCellPolicy());
    }

    @Override
    public XSSFCell getCell(int cellnum, Row.MissingCellPolicy policy) {
        if (cellnum < 0) {
            throw new IllegalArgumentException("Cell index must be >= 0");
        }
        Integer colI = cellnum;
        XSSFCell cell = this._cells.get(colI);
        switch (policy) {
            case RETURN_NULL_AND_BLANK: {
                return cell;
            }
            case RETURN_BLANK_AS_NULL: {
                boolean isBlank = cell != null && cell.getCellType() == CellType.BLANK;
                return isBlank ? null : cell;
            }
            case CREATE_NULL_AS_BLANK: {
                return cell == null ? this.createCell(cellnum, CellType.BLANK) : cell;
            }
        }
        throw new IllegalArgumentException("Illegal policy " + (Object)((Object)policy));
    }

    @Override
    public short getFirstCellNum() {
        return (short)(this._cells.size() == 0 ? -1 : this._cells.firstKey());
    }

    @Override
    public short getLastCellNum() {
        return (short)(this._cells.size() == 0 ? -1 : this._cells.lastKey() + 1);
    }

    @Override
    public short getHeight() {
        return (short)(this.getHeightInPoints() * 20.0f);
    }

    @Override
    public float getHeightInPoints() {
        if (this._row.isSetHt()) {
            return (float)this._row.getHt();
        }
        return this._sheet.getDefaultRowHeightInPoints();
    }

    @Override
    public void setHeight(short height) {
        if (height == -1) {
            if (this._row.isSetHt()) {
                this._row.unsetHt();
            }
            if (this._row.isSetCustomHeight()) {
                this._row.unsetCustomHeight();
            }
        } else {
            this._row.setHt((double)height / 20.0);
            this._row.setCustomHeight(true);
        }
    }

    @Override
    public void setHeightInPoints(float height) {
        this.setHeight((short)(height == -1.0f ? -1.0f : height * 20.0f));
    }

    @Override
    public int getPhysicalNumberOfCells() {
        return this._cells.size();
    }

    @Override
    public int getRowNum() {
        return Math.toIntExact(this._row.getR() - 1L);
    }

    @Override
    public void setRowNum(int rowIndex) {
        int maxrow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (rowIndex < 0 || rowIndex > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rowIndex + ") outside allowable range (0.." + maxrow + ")");
        }
        this._row.setR(rowIndex + 1);
    }

    @Override
    public boolean getZeroHeight() {
        return this._row.getHidden();
    }

    @Override
    public void setZeroHeight(boolean height) {
        this._row.setHidden(height);
    }

    @Override
    public boolean isFormatted() {
        return this._row.isSetS();
    }

    @Override
    public XSSFCellStyle getRowStyle() {
        if (!this.isFormatted()) {
            return null;
        }
        StylesTable stylesSource = this.getSheet().getWorkbook().getStylesSource();
        if (stylesSource.getNumCellStyles() > 0) {
            return stylesSource.getStyleAt(Math.toIntExact(this._row.getS()));
        }
        return null;
    }

    @Override
    public void setRowStyle(CellStyle style) {
        if (style == null) {
            if (this._row.isSetS()) {
                this._row.unsetS();
                this._row.unsetCustomFormat();
            }
        } else {
            StylesTable styleSource = this.getSheet().getWorkbook().getStylesSource();
            XSSFCellStyle xStyle = (XSSFCellStyle)style;
            xStyle.verifyBelongsToStylesSource(styleSource);
            long idx = styleSource.putStyle(xStyle);
            this._row.setS(idx);
            this._row.setCustomFormat(true);
        }
    }

    @Override
    public void removeCell(Cell cell) {
        if (cell.getRow() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this row");
        }
        if (!this._cells.containsValue(cell)) {
            throw new IllegalArgumentException("the row does not contain this cell");
        }
        XSSFCell xcell = (XSSFCell)cell;
        if (xcell.isPartOfArrayFormulaGroup()) {
            xcell.setCellFormula(null);
        }
        if (cell.getCellType() == CellType.FORMULA) {
            this._sheet.getWorkbook().onDeleteFormula(xcell);
        }
        Integer colI = cell.getColumnIndex();
        XSSFCell removed = this._cells.remove(colI);
        int i = 0;
        for (CTCell ctCell : this._row.getCArray()) {
            if (ctCell == removed.getCTCell()) {
                this._row.removeC(i);
            }
            ++i;
        }
    }

    @Internal
    public CTRow getCTRow() {
        return this._row;
    }

    protected void onDocumentWrite() {
        CTCell[] cArrayOrig = this._row.getCArray();
        if (cArrayOrig.length == this._cells.size()) {
            boolean allEqual = true;
            Iterator<XSSFCell> it = this._cells.values().iterator();
            for (CTCell ctCell : cArrayOrig) {
                XSSFCell cell = it.next();
                if (ctCell == cell.getCTCell()) continue;
                allEqual = false;
                break;
            }
            if (allEqual) {
                return;
            }
        }
        this.fixupCTCells(cArrayOrig);
    }

    private void fixupCTCells(CTCell[] cArrayOrig) {
        CTCell[] cArrayCopy = new CTCell[cArrayOrig.length];
        IdentityHashMap<CTCell, Integer> map = new IdentityHashMap<CTCell, Integer>(this._cells.size());
        int i = 0;
        for (CTCell ctCell : cArrayOrig) {
            cArrayCopy[i] = (CTCell)ctCell.copy();
            map.put(ctCell, i);
            ++i;
        }
        i = 0;
        for (XSSFCell cell : this._cells.values()) {
            Integer correctPosition = (Integer)map.get(cell.getCTCell());
            Objects.requireNonNull(correctPosition, "Should find CTCell in _row");
            if (correctPosition != i) {
                this._row.setCArray(i, cArrayCopy[correctPosition]);
                cell.setCTCell(this._row.getCArray(i));
            }
            ++i;
        }
        while (cArrayOrig.length > this._cells.size()) {
            this._row.removeC(this._cells.size());
        }
    }

    public String toString() {
        return this._row.toString();
    }

    protected void shift(int n) {
        int rownum = this.getRowNum() + n;
        String msg = "Row[rownum=" + this.getRowNum() + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
        this.setRowNum(rownum);
        for (Cell c : this) {
            ((XSSFCell)c).updateCellReferencesForShifting(msg);
        }
    }

    public void copyRowFrom(Row srcRow, CellCopyPolicy policy) {
        if (srcRow == null) {
            for (Cell destCell : this) {
                Cell srcCell = null;
                ((XSSFCell)destCell).copyCellFrom(srcCell, policy);
            }
            if (policy.isCopyMergedRegions()) {
                int destRowNum = this.getRowNum();
                int index = 0;
                HashSet<Integer> indices = new HashSet<Integer>();
                for (CellRangeAddress destRegion : this.getSheet().getMergedRegions()) {
                    if (destRowNum == destRegion.getFirstRow() && destRowNum == destRegion.getLastRow()) {
                        indices.add(index);
                    }
                    ++index;
                }
                this.getSheet().removeMergedRegions(indices);
            }
            if (policy.isCopyRowHeight()) {
                this.setHeight((short)-1);
            }
        } else {
            for (Cell c : srcRow) {
                XSSFCell srcCell = (XSSFCell)c;
                XSSFCell destCell = this.createCell(srcCell.getColumnIndex());
                destCell.copyCellFrom(srcCell, policy);
            }
            int sheetIndex = this._sheet.getWorkbook().getSheetIndex(this._sheet);
            String sheetName = this._sheet.getWorkbook().getSheetName(sheetIndex);
            int srcRowNum = srcRow.getRowNum();
            int destRowNum = this.getRowNum();
            int rowDifference = destRowNum - srcRowNum;
            FormulaShifter formulaShifter = FormulaShifter.createForRowCopy(sheetIndex, sheetName, srcRowNum, srcRowNum, rowDifference, SpreadsheetVersion.EXCEL2007);
            XSSFRowShifter rowShifter = new XSSFRowShifter(this._sheet);
            rowShifter.updateRowFormulas(this, formulaShifter);
            if (policy.isCopyMergedRegions()) {
                for (CellRangeAddress srcRegion : srcRow.getSheet().getMergedRegions()) {
                    if (srcRowNum != srcRegion.getFirstRow() || srcRowNum != srcRegion.getLastRow()) continue;
                    CellRangeAddress destRegion = srcRegion.copy();
                    destRegion.setFirstRow(destRowNum);
                    destRegion.setLastRow(destRowNum);
                    this.getSheet().addMergedRegion(destRegion);
                }
            }
            if (policy.isCopyRowHeight()) {
                this.setHeight(srcRow.getHeight());
            }
        }
    }

    @Override
    public int getOutlineLevel() {
        return this._row.getOutlineLevel();
    }

    @Override
    public void shiftCellsRight(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        int columnIndex;
        RowShifter.validateShiftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (columnIndex = lastShiftColumnIndex; columnIndex >= firstShiftColumnIndex; --columnIndex) {
            this.shiftCell(columnIndex, step);
        }
        for (columnIndex = firstShiftColumnIndex; columnIndex <= firstShiftColumnIndex + step - 1; ++columnIndex) {
            this._cells.remove(columnIndex);
            XSSFCell targetCell = this.getCell(columnIndex);
            if (targetCell == null) continue;
            targetCell.getCTCell().set(CTCell.Factory.newInstance());
        }
    }

    @Override
    public void shiftCellsLeft(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        int columnIndex;
        RowShifter.validateShiftLeftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (columnIndex = firstShiftColumnIndex; columnIndex <= lastShiftColumnIndex; ++columnIndex) {
            this.shiftCell(columnIndex, -step);
        }
        for (columnIndex = lastShiftColumnIndex - step + 1; columnIndex <= lastShiftColumnIndex; ++columnIndex) {
            this._cells.remove(columnIndex);
            XSSFCell targetCell = this.getCell(columnIndex);
            if (targetCell == null) continue;
            targetCell.getCTCell().set(CTCell.Factory.newInstance());
        }
    }

    private void shiftCell(int columnIndex, int step) {
        if (columnIndex + step < 0) {
            throw new IllegalStateException("Column index less than zero : " + Integer.valueOf(columnIndex + step));
        }
        XSSFCell currentCell = this.getCell(columnIndex);
        if (currentCell != null) {
            currentCell.setCellNum(columnIndex + step);
            this._cells.put(columnIndex + step, currentCell);
        } else {
            this._cells.remove(columnIndex + step);
            XSSFCell targetCell = this.getCell(columnIndex + step);
            if (targetCell != null) {
                targetCell.getCTCell().set(CTCell.Factory.newInstance());
            }
        }
    }
}

