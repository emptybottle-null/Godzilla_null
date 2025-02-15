/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;

public abstract class CellRangeAddressBase
implements Iterable<CellAddress>,
Duplicatable {
    private int _firstRow;
    private int _firstCol;
    private int _lastRow;
    private int _lastCol;

    protected CellRangeAddressBase(int firstRow, int lastRow, int firstCol, int lastCol) {
        this._firstRow = firstRow;
        this._lastRow = lastRow;
        this._firstCol = firstCol;
        this._lastCol = lastCol;
    }

    public void validate(SpreadsheetVersion ssVersion) {
        CellRangeAddressBase.validateRow(this._firstRow, ssVersion);
        CellRangeAddressBase.validateRow(this._lastRow, ssVersion);
        CellRangeAddressBase.validateColumn(this._firstCol, ssVersion);
        CellRangeAddressBase.validateColumn(this._lastCol, ssVersion);
    }

    private static void validateRow(int row, SpreadsheetVersion ssVersion) {
        int maxrow = ssVersion.getLastRowIndex();
        if (row > maxrow) {
            throw new IllegalArgumentException("Maximum row number is " + maxrow);
        }
        if (row < 0) {
            throw new IllegalArgumentException("Minumum row number is 0");
        }
    }

    private static void validateColumn(int column, SpreadsheetVersion ssVersion) {
        int maxcol = ssVersion.getLastColumnIndex();
        if (column > maxcol) {
            throw new IllegalArgumentException("Maximum column number is " + maxcol);
        }
        if (column < 0) {
            throw new IllegalArgumentException("Minimum column number is 0");
        }
    }

    public final boolean isFullColumnRange() {
        return this._firstRow == 0 && this._lastRow == SpreadsheetVersion.EXCEL97.getLastRowIndex() || this._firstRow == -1 && this._lastRow == -1;
    }

    public final boolean isFullRowRange() {
        return this._firstCol == 0 && this._lastCol == SpreadsheetVersion.EXCEL97.getLastColumnIndex() || this._firstCol == -1 && this._lastCol == -1;
    }

    public final int getFirstColumn() {
        return this._firstCol;
    }

    public final int getFirstRow() {
        return this._firstRow;
    }

    public final int getLastColumn() {
        return this._lastCol;
    }

    public final int getLastRow() {
        return this._lastRow;
    }

    public boolean isInRange(int rowInd, int colInd) {
        return this._firstRow <= rowInd && rowInd <= this._lastRow && this._firstCol <= colInd && colInd <= this._lastCol;
    }

    public boolean isInRange(CellReference ref) {
        return this.isInRange(ref.getRow(), ref.getCol());
    }

    public boolean isInRange(CellAddress ref) {
        return this.isInRange(ref.getRow(), ref.getColumn());
    }

    public boolean isInRange(Cell cell) {
        return this.isInRange(cell.getRowIndex(), cell.getColumnIndex());
    }

    public boolean containsRow(int rowInd) {
        return this._firstRow <= rowInd && rowInd <= this._lastRow;
    }

    public boolean containsColumn(int colInd) {
        return this._firstCol <= colInd && colInd <= this._lastCol;
    }

    public boolean intersects(CellRangeAddressBase other) {
        return this._firstRow <= other._lastRow && this._firstCol <= other._lastCol && other._firstRow <= this._lastRow && other._firstCol <= this._lastCol;
    }

    public Set<CellPosition> getPosition(int rowInd, int colInd) {
        EnumSet<CellPosition> positions = EnumSet.noneOf(CellPosition.class);
        if (rowInd > this.getFirstRow() && rowInd < this.getLastRow() && colInd > this.getFirstColumn() && colInd < this.getLastColumn()) {
            positions.add(CellPosition.INSIDE);
            return positions;
        }
        if (rowInd == this.getFirstRow()) {
            positions.add(CellPosition.TOP);
        }
        if (rowInd == this.getLastRow()) {
            positions.add(CellPosition.BOTTOM);
        }
        if (colInd == this.getFirstColumn()) {
            positions.add(CellPosition.LEFT);
        }
        if (colInd == this.getLastColumn()) {
            positions.add(CellPosition.RIGHT);
        }
        return positions;
    }

    public final void setFirstColumn(int firstCol) {
        this._firstCol = firstCol;
    }

    public final void setFirstRow(int firstRow) {
        this._firstRow = firstRow;
    }

    public final void setLastColumn(int lastCol) {
        this._lastCol = lastCol;
    }

    public final void setLastRow(int lastRow) {
        this._lastRow = lastRow;
    }

    public int getNumberOfCells() {
        return (this._lastRow - this._firstRow + 1) * (this._lastCol - this._firstCol + 1);
    }

    @Override
    public Iterator<CellAddress> iterator() {
        return new RowMajorCellAddressIterator(this);
    }

    public final String toString() {
        CellAddress crA = new CellAddress(this._firstRow, this._firstCol);
        CellAddress crB = new CellAddress(this._lastRow, this._lastCol);
        return this.getClass().getName() + " [" + crA.formatAsString() + ":" + crB.formatAsString() + "]";
    }

    protected int getMinRow() {
        return Math.min(this._firstRow, this._lastRow);
    }

    protected int getMaxRow() {
        return Math.max(this._firstRow, this._lastRow);
    }

    protected int getMinColumn() {
        return Math.min(this._firstCol, this._lastCol);
    }

    protected int getMaxColumn() {
        return Math.max(this._firstCol, this._lastCol);
    }

    public boolean equals(Object other) {
        if (other instanceof CellRangeAddressBase) {
            CellRangeAddressBase o = (CellRangeAddressBase)other;
            return this.getMinRow() == o.getMinRow() && this.getMaxRow() == o.getMaxRow() && this.getMinColumn() == o.getMinColumn() && this.getMaxColumn() == o.getMaxColumn();
        }
        return false;
    }

    public int hashCode() {
        return this.getMinColumn() + (this.getMaxColumn() << 8) + (this.getMinRow() << 16) + (this.getMaxRow() << 24);
    }

    private static class RowMajorCellAddressIterator
    implements Iterator<CellAddress> {
        private final int firstRow;
        private final int firstCol;
        private final int lastRow;
        private final int lastCol;
        private int r;
        private int c;

        public RowMajorCellAddressIterator(CellRangeAddressBase ref) {
            this.r = this.firstRow = ref.getFirstRow();
            this.c = this.firstCol = ref.getFirstColumn();
            this.lastRow = ref.getLastRow();
            this.lastCol = ref.getLastColumn();
            if (this.firstRow < 0) {
                throw new IllegalStateException("First row cannot be negative.");
            }
            if (this.firstCol < 0) {
                throw new IllegalStateException("First column cannot be negative.");
            }
            if (this.firstRow > this.lastRow) {
                throw new IllegalStateException("First row cannot be greater than last row.");
            }
            if (this.firstCol > this.lastCol) {
                throw new IllegalStateException("First column cannot be greater than last column.");
            }
        }

        @Override
        public boolean hasNext() {
            return this.r <= this.lastRow && this.c <= this.lastCol;
        }

        @Override
        public CellAddress next() {
            if (this.hasNext()) {
                CellAddress addr = new CellAddress(this.r, this.c);
                if (this.c < this.lastCol) {
                    ++this.c;
                } else {
                    this.c = this.firstCol;
                    ++this.r;
                }
                return addr;
            }
            throw new NoSuchElementException();
        }
    }

    public static enum CellPosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        INSIDE;

    }
}

