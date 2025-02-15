/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval.forked;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluationCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
final class ForkedEvaluationSheet
implements EvaluationSheet {
    private final EvaluationSheet _masterSheet;
    private final Map<RowColKey, ForkedEvaluationCell> _sharedCellsByRowCol;

    public ForkedEvaluationSheet(EvaluationSheet masterSheet) {
        this._masterSheet = masterSheet;
        this._sharedCellsByRowCol = new HashMap<RowColKey, ForkedEvaluationCell>();
    }

    @Override
    public int getLastRowNum() {
        return this._masterSheet.getLastRowNum();
    }

    @Override
    public boolean isRowHidden(int rowIndex) {
        return this._masterSheet.isRowHidden(rowIndex);
    }

    @Override
    public EvaluationCell getCell(int rowIndex, int columnIndex) {
        RowColKey key = new RowColKey(rowIndex, columnIndex);
        ForkedEvaluationCell result = this._sharedCellsByRowCol.get(key);
        if (result == null) {
            return this._masterSheet.getCell(rowIndex, columnIndex);
        }
        return result;
    }

    public ForkedEvaluationCell getOrCreateUpdatableCell(int rowIndex, int columnIndex) {
        RowColKey key = new RowColKey(rowIndex, columnIndex);
        ForkedEvaluationCell result = this._sharedCellsByRowCol.get(key);
        if (result == null) {
            EvaluationCell mcell = this._masterSheet.getCell(rowIndex, columnIndex);
            if (mcell == null) {
                CellReference cr = new CellReference(rowIndex, columnIndex);
                throw new UnsupportedOperationException("Underlying cell '" + cr.formatAsString() + "' is missing in master sheet.");
            }
            result = new ForkedEvaluationCell(this, mcell);
            this._sharedCellsByRowCol.put(key, result);
        }
        return result;
    }

    public void copyUpdatedCells(Sheet sheet) {
        Object[] keys = new RowColKey[this._sharedCellsByRowCol.size()];
        this._sharedCellsByRowCol.keySet().toArray(keys);
        Arrays.sort(keys);
        for (int i = 0; i < keys.length; ++i) {
            Cell destCell;
            Object key = keys[i];
            Row row = sheet.getRow(((RowColKey)key).getRowIndex());
            if (row == null) {
                row = sheet.createRow(((RowColKey)key).getRowIndex());
            }
            if ((destCell = row.getCell(((RowColKey)key).getColumnIndex())) == null) {
                destCell = row.createCell(((RowColKey)key).getColumnIndex());
            }
            ForkedEvaluationCell srcCell = this._sharedCellsByRowCol.get(key);
            srcCell.copyValue(destCell);
        }
    }

    public int getSheetIndex(EvaluationWorkbook mewb) {
        return mewb.getSheetIndex(this._masterSheet);
    }

    @Override
    public void clearAllCachedResultValues() {
        this._masterSheet.clearAllCachedResultValues();
    }

    private static final class RowColKey
    implements Comparable<RowColKey> {
        private final int _rowIndex;
        private final int _columnIndex;

        public RowColKey(int rowIndex, int columnIndex) {
            this._rowIndex = rowIndex;
            this._columnIndex = columnIndex;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof RowColKey)) {
                return false;
            }
            RowColKey other = (RowColKey)obj;
            return this._rowIndex == other._rowIndex && this._columnIndex == other._columnIndex;
        }

        public int hashCode() {
            return this._rowIndex ^ this._columnIndex;
        }

        @Override
        public int compareTo(RowColKey o) {
            int cmp = this._rowIndex - o._rowIndex;
            if (cmp != 0) {
                return cmp;
            }
            return this._columnIndex - o._columnIndex;
        }

        public int getRowIndex() {
            return this._rowIndex;
        }

        public int getColumnIndex() {
            return this._columnIndex;
        }
    }
}

