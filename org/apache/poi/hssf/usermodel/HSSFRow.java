/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.util.Configurator;

public final class HSSFRow
implements Row,
Comparable<HSSFRow> {
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFRow.ColInitialCapacity", 5);
    private int rowNum;
    private HSSFCell[] cells;
    private final RowRecord row;
    private final HSSFWorkbook book;
    private final HSSFSheet sheet;

    HSSFRow(HSSFWorkbook book, HSSFSheet sheet, int rowNum) {
        this(book, sheet, new RowRecord(rowNum));
    }

    HSSFRow(HSSFWorkbook book, HSSFSheet sheet, RowRecord record) {
        this.book = book;
        this.sheet = sheet;
        this.row = record;
        this.setRowNum(record.getRowNumber());
        this.cells = new HSSFCell[record.getLastCol() + INITIAL_CAPACITY];
        record.setEmpty();
    }

    @Override
    public HSSFCell createCell(int column) {
        return this.createCell(column, CellType.BLANK);
    }

    @Override
    public HSSFCell createCell(int columnIndex, CellType type) {
        short shortCellNum = (short)columnIndex;
        if (columnIndex > Short.MAX_VALUE) {
            shortCellNum = (short)(65535 - columnIndex);
        }
        HSSFCell cell = new HSSFCell(this.book, this.sheet, this.getRowNum(), shortCellNum, type);
        this.addCell(cell);
        this.sheet.getSheet().addValueRecord(this.getRowNum(), cell.getCellValueRecord());
        return cell;
    }

    @Override
    public void removeCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("cell must not be null");
        }
        this.removeCell((HSSFCell)cell, true);
    }

    private void removeCell(HSSFCell cell, boolean alsoRemoveRecords) {
        int column = cell.getColumnIndex();
        if (column < 0) {
            throw new RuntimeException("Negative cell indexes not allowed");
        }
        if (column >= this.cells.length || cell != this.cells[column]) {
            throw new RuntimeException("Specified cell is not from this row");
        }
        if (cell.isPartOfArrayFormulaGroup()) {
            cell.tryToDeleteArrayFormula(null);
        }
        this.cells[column] = null;
        if (alsoRemoveRecords) {
            CellValueRecordInterface cval = cell.getCellValueRecord();
            this.sheet.getSheet().removeValueRecord(this.getRowNum(), cval);
        }
        if (cell.getColumnIndex() + 1 == this.row.getLastCol()) {
            this.row.setLastCol(this.calculateNewLastCellPlusOne(this.row.getLastCol()));
        }
        if (cell.getColumnIndex() == this.row.getFirstCol()) {
            this.row.setFirstCol(this.calculateNewFirstCell(this.row.getFirstCol()));
        }
    }

    protected void removeAllCells() {
        for (HSSFCell cell : this.cells) {
            if (cell == null) continue;
            this.removeCell(cell, true);
        }
        this.cells = new HSSFCell[INITIAL_CAPACITY];
    }

    HSSFCell createCellFromRecord(CellValueRecordInterface cell) {
        HSSFCell hcell = new HSSFCell(this.book, this.sheet, cell);
        this.addCell(hcell);
        short colIx = cell.getColumn();
        if (this.row.isEmpty()) {
            this.row.setFirstCol(colIx);
            this.row.setLastCol(colIx + 1);
        } else if (colIx < this.row.getFirstCol()) {
            this.row.setFirstCol(colIx);
        } else if (colIx > this.row.getLastCol()) {
            this.row.setLastCol(colIx + 1);
        }
        return hcell;
    }

    @Override
    public void setRowNum(int rowIndex) {
        int maxrow = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (rowIndex < 0 || rowIndex > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rowIndex + ") outside allowable range (0.." + maxrow + ")");
        }
        this.rowNum = rowIndex;
        if (this.row != null) {
            this.row.setRowNumber(rowIndex);
        }
    }

    @Override
    public int getRowNum() {
        return this.rowNum;
    }

    @Override
    public HSSFSheet getSheet() {
        return this.sheet;
    }

    @Override
    public int getOutlineLevel() {
        return this.row.getOutlineLevel();
    }

    public void moveCell(HSSFCell cell, short newColumn) {
        if (this.cells.length > newColumn && this.cells[newColumn] != null) {
            throw new IllegalArgumentException("Asked to move cell to column " + newColumn + " but there's already a cell there");
        }
        if (!this.cells[cell.getColumnIndex()].equals(cell)) {
            throw new IllegalArgumentException("Asked to move a cell, but it didn't belong to our row");
        }
        this.removeCell(cell, false);
        cell.updateCellNum(newColumn);
        this.addCell(cell);
    }

    private void addCell(HSSFCell cell) {
        int column = cell.getColumnIndex();
        if (column >= this.cells.length) {
            HSSFCell[] oldCells = this.cells;
            int newSize = oldCells.length * 3 / 2 + 1;
            if (newSize < column + 1) {
                newSize = column + INITIAL_CAPACITY;
            }
            this.cells = new HSSFCell[newSize];
            System.arraycopy(oldCells, 0, this.cells, 0, oldCells.length);
        }
        this.cells[column] = cell;
        if (this.row.isEmpty() || column < this.row.getFirstCol()) {
            this.row.setFirstCol((short)column);
        }
        if (this.row.isEmpty() || column >= this.row.getLastCol()) {
            this.row.setLastCol((short)(column + 1));
        }
    }

    private HSSFCell retrieveCell(int cellIndex) {
        if (cellIndex < 0 || cellIndex >= this.cells.length) {
            return null;
        }
        return this.cells[cellIndex];
    }

    @Override
    public HSSFCell getCell(int cellnum) {
        return this.getCell(cellnum, this.book.getMissingCellPolicy());
    }

    @Override
    public HSSFCell getCell(int cellnum, Row.MissingCellPolicy policy) {
        HSSFCell cell = this.retrieveCell(cellnum);
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
        if (this.row.isEmpty()) {
            return -1;
        }
        return (short)this.row.getFirstCol();
    }

    @Override
    public short getLastCellNum() {
        if (this.row.isEmpty()) {
            return -1;
        }
        return (short)this.row.getLastCol();
    }

    @Override
    public int getPhysicalNumberOfCells() {
        int count = 0;
        for (HSSFCell cell : this.cells) {
            if (cell == null) continue;
            ++count;
        }
        return count;
    }

    @Override
    public void setHeight(short height) {
        if (height == -1) {
            this.row.setHeight((short)-32513);
            this.row.setBadFontHeight(false);
        } else {
            this.row.setBadFontHeight(true);
            this.row.setHeight(height);
        }
    }

    @Override
    public void setZeroHeight(boolean zHeight) {
        this.row.setZeroHeight(zHeight);
    }

    @Override
    public boolean getZeroHeight() {
        return this.row.getZeroHeight();
    }

    @Override
    public void setHeightInPoints(float height) {
        if (height == -1.0f) {
            this.row.setHeight((short)-32513);
            this.row.setBadFontHeight(false);
        } else {
            this.row.setBadFontHeight(true);
            this.row.setHeight((short)(height * 20.0f));
        }
    }

    @Override
    public short getHeight() {
        short height = this.row.getHeight();
        height = (height & 0x8000) != 0 ? this.sheet.getSheet().getDefaultRowHeight() : (short)(height & Short.MAX_VALUE);
        return height;
    }

    @Override
    public float getHeightInPoints() {
        return (float)this.getHeight() / 20.0f;
    }

    protected RowRecord getRowRecord() {
        return this.row;
    }

    private int calculateNewLastCellPlusOne(int lastcell) {
        int cellIx = lastcell - 1;
        HSSFCell r = this.retrieveCell(cellIx);
        while (r == null) {
            if (cellIx < 0) {
                return 0;
            }
            r = this.retrieveCell(--cellIx);
        }
        return cellIx + 1;
    }

    private int calculateNewFirstCell(int firstcell) {
        int cellIx = firstcell + 1;
        HSSFCell r = this.retrieveCell(cellIx);
        while (r == null) {
            if (cellIx <= this.cells.length) {
                return 0;
            }
            r = this.retrieveCell(++cellIx);
        }
        return cellIx;
    }

    @Override
    public boolean isFormatted() {
        return this.row.getFormatted();
    }

    @Override
    public HSSFCellStyle getRowStyle() {
        if (!this.isFormatted()) {
            return null;
        }
        short styleIndex = this.row.getXFIndex();
        ExtendedFormatRecord xf = this.book.getWorkbook().getExFormatAt(styleIndex);
        return new HSSFCellStyle(styleIndex, xf, this.book);
    }

    public void setRowStyle(HSSFCellStyle style) {
        this.row.setFormatted(true);
        this.row.setXFIndex(style.getIndex());
    }

    @Override
    public void setRowStyle(CellStyle style) {
        this.setRowStyle((HSSFCellStyle)style);
    }

    @Override
    public Iterator<Cell> cellIterator() {
        return new CellIterator();
    }

    @Override
    public Iterator<Cell> iterator() {
        return this.cellIterator();
    }

    @Override
    public int compareTo(HSSFRow other) {
        if (this.getSheet() != other.getSheet()) {
            throw new IllegalArgumentException("The compared rows must belong to the same sheet");
        }
        int thisRow = this.getRowNum();
        int otherRow = other.getRowNum();
        return Integer.compare(thisRow, otherRow);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HSSFRow)) {
            return false;
        }
        HSSFRow other = (HSSFRow)obj;
        return this.getRowNum() == other.getRowNum() && this.getSheet() == other.getSheet();
    }

    public int hashCode() {
        return this.row.hashCode();
    }

    @Override
    public void shiftCellsRight(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        int columnIndex;
        RowShifter.validateShiftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        if (lastShiftColumnIndex + step + 1 > this.cells.length) {
            this.extend(lastShiftColumnIndex + step + 1);
        }
        for (columnIndex = lastShiftColumnIndex; columnIndex >= firstShiftColumnIndex; --columnIndex) {
            HSSFCell cell = this.getCell(columnIndex);
            this.cells[columnIndex + step] = null;
            if (cell == null) continue;
            this.moveCell(cell, (short)(columnIndex + step));
        }
        for (columnIndex = firstShiftColumnIndex; columnIndex <= firstShiftColumnIndex + step - 1; ++columnIndex) {
            this.cells[columnIndex] = null;
        }
    }

    private void extend(int newLength) {
        HSSFCell[] temp = (HSSFCell[])this.cells.clone();
        this.cells = new HSSFCell[newLength];
        System.arraycopy(temp, 0, this.cells, 0, temp.length);
    }

    @Override
    public void shiftCellsLeft(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        int columnIndex;
        RowShifter.validateShiftLeftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (columnIndex = firstShiftColumnIndex; columnIndex <= lastShiftColumnIndex; ++columnIndex) {
            HSSFCell cell = this.getCell(columnIndex);
            if (cell != null) {
                this.cells[columnIndex - step] = null;
                this.moveCell(cell, (short)(columnIndex - step));
                continue;
            }
            this.cells[columnIndex - step] = null;
        }
        for (columnIndex = lastShiftColumnIndex - step + 1; columnIndex <= lastShiftColumnIndex; ++columnIndex) {
            this.cells[columnIndex] = null;
        }
    }

    private class CellIterator
    implements Iterator<Cell> {
        int thisId = -1;
        int nextId = -1;

        public CellIterator() {
            this.findNext();
        }

        @Override
        public boolean hasNext() {
            return this.nextId < HSSFRow.this.cells.length;
        }

        @Override
        public Cell next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("At last element");
            }
            HSSFCell cell = HSSFRow.this.cells[this.nextId];
            this.thisId = this.nextId;
            this.findNext();
            return cell;
        }

        @Override
        public void remove() {
            if (this.thisId == -1) {
                throw new IllegalStateException("remove() called before next()");
            }
            ((HSSFRow)HSSFRow.this).cells[this.thisId] = null;
        }

        private void findNext() {
            int i;
            for (i = this.nextId + 1; i < HSSFRow.this.cells.length && HSSFRow.this.cells[i] == null; ++i) {
            }
            this.nextId = i;
        }
    }
}

