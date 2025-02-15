/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.util.CellReference;

final class FormulaUsedBlankCellSet {
    private final Map<BookSheetKey, BlankCellSheetGroup> _sheetGroupsByBookSheet = new HashMap<BookSheetKey, BlankCellSheetGroup>();

    public void addCell(EvaluationWorkbook evalWorkbook, int bookIndex, int sheetIndex, int rowIndex, int columnIndex) {
        BlankCellSheetGroup sbcg = this.getSheetGroup(evalWorkbook, bookIndex, sheetIndex);
        sbcg.addCell(rowIndex, columnIndex);
    }

    private BlankCellSheetGroup getSheetGroup(EvaluationWorkbook evalWorkbook, int bookIndex, int sheetIndex) {
        BookSheetKey key = new BookSheetKey(bookIndex, sheetIndex);
        BlankCellSheetGroup result = this._sheetGroupsByBookSheet.get(key);
        if (result == null) {
            result = new BlankCellSheetGroup(evalWorkbook.getSheet(sheetIndex).getLastRowNum());
            this._sheetGroupsByBookSheet.put(key, result);
        }
        return result;
    }

    public boolean containsCell(BookSheetKey key, int rowIndex, int columnIndex) {
        BlankCellSheetGroup bcsg = this._sheetGroupsByBookSheet.get(key);
        if (bcsg == null) {
            return false;
        }
        return bcsg.containsCell(rowIndex, columnIndex);
    }

    public boolean isEmpty() {
        return this._sheetGroupsByBookSheet.isEmpty();
    }

    private static final class BlankCellRectangleGroup {
        private final int _firstRowIndex;
        private final int _firstColumnIndex;
        private final int _lastColumnIndex;
        private int _lastRowIndex;

        public BlankCellRectangleGroup(int firstRowIndex, int firstColumnIndex, int lastColumnIndex) {
            this._firstRowIndex = firstRowIndex;
            this._firstColumnIndex = firstColumnIndex;
            this._lastColumnIndex = lastColumnIndex;
            this._lastRowIndex = firstRowIndex;
        }

        public boolean containsCell(int rowIndex, int columnIndex) {
            if (columnIndex < this._firstColumnIndex) {
                return false;
            }
            if (columnIndex > this._lastColumnIndex) {
                return false;
            }
            if (rowIndex < this._firstRowIndex) {
                return false;
            }
            return rowIndex <= this._lastRowIndex;
        }

        public boolean acceptRow(int rowIndex, int firstColumnIndex, int lastColumnIndex) {
            if (firstColumnIndex != this._firstColumnIndex) {
                return false;
            }
            if (lastColumnIndex != this._lastColumnIndex) {
                return false;
            }
            if (rowIndex != this._lastRowIndex + 1) {
                return false;
            }
            this._lastRowIndex = rowIndex;
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            CellReference crA = new CellReference(this._firstRowIndex, this._firstColumnIndex, false, false);
            CellReference crB = new CellReference(this._lastRowIndex, this._lastColumnIndex, false, false);
            sb.append(this.getClass().getName());
            sb.append(" [").append(crA.formatAsString()).append(':').append(crB.formatAsString()).append("]");
            return sb.toString();
        }
    }

    private static final class BlankCellSheetGroup {
        private final List<BlankCellRectangleGroup> _rectangleGroups = new ArrayList<BlankCellRectangleGroup>();
        private int _currentRowIndex = -1;
        private int _firstColumnIndex;
        private int _lastColumnIndex;
        private BlankCellRectangleGroup _currentRectangleGroup;
        private int _lastDefinedRow;

        public BlankCellSheetGroup(int lastDefinedRow) {
            this._lastDefinedRow = lastDefinedRow;
        }

        public void addCell(int rowIndex, int columnIndex) {
            if (rowIndex > this._lastDefinedRow) {
                return;
            }
            if (this._currentRowIndex == -1) {
                this._currentRowIndex = rowIndex;
                this._firstColumnIndex = columnIndex;
                this._lastColumnIndex = columnIndex;
            } else if (this._currentRowIndex == rowIndex && this._lastColumnIndex + 1 == columnIndex) {
                this._lastColumnIndex = columnIndex;
            } else {
                if (this._currentRectangleGroup == null) {
                    this._currentRectangleGroup = new BlankCellRectangleGroup(this._currentRowIndex, this._firstColumnIndex, this._lastColumnIndex);
                } else if (!this._currentRectangleGroup.acceptRow(this._currentRowIndex, this._firstColumnIndex, this._lastColumnIndex)) {
                    this._rectangleGroups.add(this._currentRectangleGroup);
                    this._currentRectangleGroup = new BlankCellRectangleGroup(this._currentRowIndex, this._firstColumnIndex, this._lastColumnIndex);
                }
                this._currentRowIndex = rowIndex;
                this._firstColumnIndex = columnIndex;
                this._lastColumnIndex = columnIndex;
            }
        }

        public boolean containsCell(int rowIndex, int columnIndex) {
            if (rowIndex > this._lastDefinedRow) {
                return true;
            }
            for (int i = this._rectangleGroups.size() - 1; i >= 0; --i) {
                BlankCellRectangleGroup bcrg = this._rectangleGroups.get(i);
                if (!bcrg.containsCell(rowIndex, columnIndex)) continue;
                return true;
            }
            if (this._currentRectangleGroup != null && this._currentRectangleGroup.containsCell(rowIndex, columnIndex)) {
                return true;
            }
            return this._currentRowIndex != -1 && this._currentRowIndex == rowIndex && this._firstColumnIndex <= columnIndex && columnIndex <= this._lastColumnIndex;
        }
    }

    public static final class BookSheetKey {
        private final int _bookIndex;
        private final int _sheetIndex;

        public BookSheetKey(int bookIndex, int sheetIndex) {
            this._bookIndex = bookIndex;
            this._sheetIndex = sheetIndex;
        }

        public int hashCode() {
            return this._bookIndex * 17 + this._sheetIndex;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BookSheetKey)) {
                return false;
            }
            BookSheetKey other = (BookSheetKey)obj;
            return this._bookIndex == other._bookIndex && this._sheetIndex == other._sheetIndex;
        }
    }
}

