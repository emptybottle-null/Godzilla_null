/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

@Internal
final class XSSFEvaluationSheet
implements EvaluationSheet {
    private final XSSFSheet _xs;
    private Map<CellKey, EvaluationCell> _cellCache;

    public XSSFEvaluationSheet(XSSFSheet sheet) {
        this._xs = sheet;
    }

    public XSSFSheet getXSSFSheet() {
        return this._xs;
    }

    @Override
    public int getLastRowNum() {
        return this._xs.getLastRowNum();
    }

    @Override
    public boolean isRowHidden(int rowIndex) {
        XSSFRow row = this._xs.getRow(rowIndex);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    @Override
    public void clearAllCachedResultValues() {
        this._cellCache = null;
    }

    @Override
    public EvaluationCell getCell(int rowIndex, int columnIndex) {
        CellKey key;
        EvaluationCell evalcell;
        if (rowIndex > this.getLastRowNum()) {
            return null;
        }
        if (this._cellCache == null) {
            this._cellCache = new HashMap<CellKey, EvaluationCell>(this._xs.getLastRowNum() * 3);
            for (Row row : this._xs) {
                int rowNum = row.getRowNum();
                for (Cell cell : row) {
                    CellKey key2 = new CellKey(rowNum, cell.getColumnIndex());
                    XSSFEvaluationCell evalcell2 = new XSSFEvaluationCell((XSSFCell)cell, this);
                    this._cellCache.put(key2, evalcell2);
                }
            }
        }
        if ((evalcell = this._cellCache.get(key = new CellKey(rowIndex, columnIndex))) == null) {
            XSSFRow row = this._xs.getRow(rowIndex);
            if (row == null) {
                return null;
            }
            XSSFCell cell = row.getCell(columnIndex);
            if (cell == null) {
                return null;
            }
            evalcell = new XSSFEvaluationCell(cell, this);
            this._cellCache.put(key, evalcell);
        }
        return evalcell;
    }

    private static class CellKey {
        private final int _row;
        private final int _col;
        private int _hash = -1;

        protected CellKey(int row, int col) {
            this._row = row;
            this._col = col;
        }

        public int hashCode() {
            if (this._hash == -1) {
                this._hash = (629 + this._row) * 37 + this._col;
            }
            return this._hash;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CellKey)) {
                return false;
            }
            CellKey oKey = (CellKey)obj;
            return this._row == oKey._row && this._col == oKey._col;
        }
    }
}

