/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;

public class SXSSFRow
implements Row,
Comparable<SXSSFRow> {
    private static final Boolean UNDEFINED = null;
    private final SXSSFSheet _sheet;
    private final SortedMap<Integer, SXSSFCell> _cells = new TreeMap<Integer, SXSSFCell>();
    private short _style = (short)-1;
    private short _height = (short)-1;
    private boolean _zHeight;
    private int _outlineLevel;
    private Boolean _hidden = UNDEFINED;
    private Boolean _collapsed = UNDEFINED;

    public SXSSFRow(SXSSFSheet sheet) {
        this._sheet = sheet;
    }

    public Iterator<Cell> allCellsIterator() {
        return new CellIterator();
    }

    public boolean hasCustomHeight() {
        return this._height != -1;
    }

    @Override
    public int getOutlineLevel() {
        return this._outlineLevel;
    }

    void setOutlineLevel(int level) {
        this._outlineLevel = level;
    }

    public Boolean getHidden() {
        return this._hidden;
    }

    public void setHidden(Boolean hidden) {
        this._hidden = hidden;
    }

    public Boolean getCollapsed() {
        return this._collapsed;
    }

    public void setCollapsed(Boolean collapsed) {
        this._collapsed = collapsed;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new FilledCellIterator();
    }

    @Override
    public SXSSFCell createCell(int column) {
        return this.createCell(column, CellType.BLANK);
    }

    @Override
    public SXSSFCell createCell(int column, CellType type) {
        SXSSFRow.checkBounds(column);
        SXSSFCell cell = new SXSSFCell(this, type);
        this._cells.put(column, cell);
        return cell;
    }

    private static void checkBounds(int cellIndex) {
        SpreadsheetVersion v = SpreadsheetVersion.EXCEL2007;
        int maxcol = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        if (cellIndex < 0 || cellIndex > maxcol) {
            throw new IllegalArgumentException("Invalid column index (" + cellIndex + ").  Allowable column range for " + v.name() + " is (0.." + maxcol + ") or ('A'..'" + v.getLastColumnName() + "')");
        }
    }

    @Override
    public void removeCell(Cell cell) {
        int index = this.getCellIndex((SXSSFCell)cell);
        this._cells.remove(index);
    }

    int getCellIndex(SXSSFCell cell) {
        for (Map.Entry<Integer, SXSSFCell> entry : this._cells.entrySet()) {
            if (entry.getValue() != cell) continue;
            return entry.getKey();
        }
        return -1;
    }

    @Override
    public void setRowNum(int rowNum) {
        this._sheet.changeRowNum(this, rowNum);
    }

    @Override
    public int getRowNum() {
        return this._sheet.getRowNum(this);
    }

    @Override
    public SXSSFCell getCell(int cellnum) {
        Row.MissingCellPolicy policy = this._sheet.getWorkbook().getMissingCellPolicy();
        return this.getCell(cellnum, policy);
    }

    @Override
    public SXSSFCell getCell(int cellnum, Row.MissingCellPolicy policy) {
        SXSSFRow.checkBounds(cellnum);
        SXSSFCell cell = (SXSSFCell)this._cells.get(cellnum);
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
        try {
            return this._cells.firstKey().shortValue();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    @Override
    public short getLastCellNum() {
        return this._cells.isEmpty() ? (short)-1 : (short)(this._cells.lastKey() + 1);
    }

    @Override
    public int getPhysicalNumberOfCells() {
        return this._cells.size();
    }

    @Override
    public void setHeight(short height) {
        this._height = height;
    }

    @Override
    public void setZeroHeight(boolean zHeight) {
        this._zHeight = zHeight;
    }

    @Override
    public boolean getZeroHeight() {
        return this._zHeight;
    }

    @Override
    public void setHeightInPoints(float height) {
        this._height = height == -1.0f ? (short)-1 : (short)(height * 20.0f);
    }

    @Override
    public short getHeight() {
        return (short)(this._height == -1 ? this.getSheet().getDefaultRowHeightInPoints() * 20.0f : (float)this._height);
    }

    @Override
    public float getHeightInPoints() {
        return (float)(this._height == -1 ? (double)this.getSheet().getDefaultRowHeightInPoints() : (double)this._height / 20.0);
    }

    @Override
    public boolean isFormatted() {
        return this._style > -1;
    }

    @Override
    public CellStyle getRowStyle() {
        if (!this.isFormatted()) {
            return null;
        }
        return this.getSheet().getWorkbook().getCellStyleAt(this._style);
    }

    @Internal
    int getRowStyleIndex() {
        return this._style;
    }

    @Override
    public void setRowStyle(CellStyle style) {
        this._style = style == null ? (short)-1 : style.getIndex();
    }

    @Override
    public Iterator<Cell> cellIterator() {
        return this.iterator();
    }

    @Override
    public SXSSFSheet getSheet() {
        return this._sheet;
    }

    @Override
    public int compareTo(SXSSFRow other) {
        if (this.getSheet() != other.getSheet()) {
            throw new IllegalArgumentException("The compared rows must belong to the same sheet");
        }
        int thisRow = this.getRowNum();
        int otherRow = other.getRowNum();
        return Integer.compare(thisRow, otherRow);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SXSSFRow)) {
            return false;
        }
        SXSSFRow other = (SXSSFRow)obj;
        return this.getRowNum() == other.getRowNum() && this.getSheet() == other.getSheet();
    }

    public int hashCode() {
        return this._cells.hashCode();
    }

    @Override
    @NotImplemented
    public void shiftCellsRight(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        throw new NotImplementedException("shiftCellsRight");
    }

    @Override
    @NotImplemented
    public void shiftCellsLeft(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        throw new NotImplementedException("shiftCellsLeft");
    }

    public class CellIterator
    implements Iterator<Cell> {
        final int maxColumn;
        int pos;

        public CellIterator() {
            this.maxColumn = SXSSFRow.this.getLastCellNum();
        }

        @Override
        public boolean hasNext() {
            return this.pos < this.maxColumn;
        }

        @Override
        public Cell next() throws NoSuchElementException {
            if (this.hasNext()) {
                return (Cell)SXSSFRow.this._cells.get(this.pos++);
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class FilledCellIterator
    implements Iterator<Cell> {
        private final Iterator<SXSSFCell> iter;

        public FilledCellIterator() {
            this.iter = SXSSFRow.this._cells.values().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override
        public Cell next() throws NoSuchElementException {
            return this.iter.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

