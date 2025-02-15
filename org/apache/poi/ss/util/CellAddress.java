/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.Locale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

public class CellAddress
implements Comparable<CellAddress> {
    public static final CellAddress A1 = new CellAddress(0, 0);
    private final int _row;
    private final int _col;

    public CellAddress(int row, int column) {
        this._row = row;
        this._col = column;
    }

    public CellAddress(String address) {
        char ch;
        int loc;
        int length = address.length();
        for (loc = 0; loc < length && !Character.isDigit(ch = address.charAt(loc)); ++loc) {
        }
        String sCol = address.substring(0, loc).toUpperCase(Locale.ROOT);
        String sRow = address.substring(loc);
        this._row = Integer.parseInt(sRow) - 1;
        this._col = CellReference.convertColStringToIndex(sCol);
    }

    public CellAddress(CellReference reference) {
        this(reference.getRow(), reference.getCol());
    }

    public CellAddress(CellAddress address) {
        this(address.getRow(), address.getColumn());
    }

    public CellAddress(Cell cell) {
        this(cell.getRowIndex(), cell.getColumnIndex());
    }

    public int getRow() {
        return this._row;
    }

    public int getColumn() {
        return this._col;
    }

    @Override
    public int compareTo(CellAddress other) {
        int r = this._row - other._row;
        if (r != 0) {
            return r;
        }
        r = this._col - other._col;
        if (r != 0) {
            return r;
        }
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CellAddress)) {
            return false;
        }
        CellAddress other = (CellAddress)o;
        return this._row == other._row && this._col == other._col;
    }

    public int hashCode() {
        return this._row + this._col << 16;
    }

    public String toString() {
        return this.formatAsString();
    }

    public String formatAsString() {
        return CellReference.convertNumToColString(this._col) + (this._row + 1);
    }
}

