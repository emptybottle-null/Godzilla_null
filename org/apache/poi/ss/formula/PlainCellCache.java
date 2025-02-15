/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.PlainValueCellCacheEntry;

final class PlainCellCache {
    private Map<Loc, PlainValueCellCacheEntry> _plainValueEntriesByLoc = new HashMap<Loc, PlainValueCellCacheEntry>();

    public void put(Loc key, PlainValueCellCacheEntry cce) {
        this._plainValueEntriesByLoc.put(key, cce);
    }

    public void clear() {
        this._plainValueEntriesByLoc.clear();
    }

    public PlainValueCellCacheEntry get(Loc key) {
        return this._plainValueEntriesByLoc.get(key);
    }

    public void remove(Loc key) {
        this._plainValueEntriesByLoc.remove(key);
    }

    public static final class Loc {
        private final long _bookSheetColumn;
        private final int _rowIndex;

        public Loc(int bookIndex, int sheetIndex, int rowIndex, int columnIndex) {
            this._bookSheetColumn = Loc.toBookSheetColumn(bookIndex, sheetIndex, columnIndex);
            this._rowIndex = rowIndex;
        }

        public static long toBookSheetColumn(int bookIndex, int sheetIndex, int columnIndex) {
            return (((long)bookIndex & 0xFFFFL) << 48) + (((long)sheetIndex & 0xFFFFL) << 32) + (((long)columnIndex & 0xFFFFL) << 0);
        }

        public Loc(long bookSheetColumn, int rowIndex) {
            this._bookSheetColumn = bookSheetColumn;
            this._rowIndex = rowIndex;
        }

        public int hashCode() {
            return (int)(this._bookSheetColumn ^ this._bookSheetColumn >>> 32) + 17 * this._rowIndex;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Loc)) {
                return false;
            }
            Loc other = (Loc)obj;
            return this._bookSheetColumn == other._bookSheetColumn && this._rowIndex == other._rowIndex;
        }

        public int getRowIndex() {
            return this._rowIndex;
        }

        public int getColumnIndex() {
            return (int)(this._bookSheetColumn & 0xFFFFL);
        }

        public int getSheetIndex() {
            return (int)(this._bookSheetColumn >> 32 & 0xFFFFL);
        }

        public int getBookIndex() {
            return (int)(this._bookSheetColumn >> 48 & 0xFFFFL);
        }
    }
}

