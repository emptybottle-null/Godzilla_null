/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ptg.Area2DPtgBase;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.Deleted3DPxg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;

public final class FormulaShifter {
    private final int _externSheetIndex;
    private final String _sheetName;
    private final int _firstMovedIndex;
    private final int _lastMovedIndex;
    private final int _amountToMove;
    private final int _srcSheetIndex;
    private final int _dstSheetIndex;
    private final SpreadsheetVersion _version;
    private final ShiftMode _mode;

    private FormulaShifter(int externSheetIndex, String sheetName, int firstMovedIndex, int lastMovedIndex, int amountToMove, ShiftMode mode, SpreadsheetVersion version) {
        if (amountToMove == 0) {
            throw new IllegalArgumentException("amountToMove must not be zero");
        }
        if (firstMovedIndex > lastMovedIndex) {
            throw new IllegalArgumentException("firstMovedIndex, lastMovedIndex out of order");
        }
        this._externSheetIndex = externSheetIndex;
        this._sheetName = sheetName;
        this._firstMovedIndex = firstMovedIndex;
        this._lastMovedIndex = lastMovedIndex;
        this._amountToMove = amountToMove;
        this._mode = mode;
        this._version = version;
        this._dstSheetIndex = -1;
        this._srcSheetIndex = -1;
    }

    private FormulaShifter(int srcSheetIndex, int dstSheetIndex) {
        this._amountToMove = -1;
        this._lastMovedIndex = -1;
        this._firstMovedIndex = -1;
        this._externSheetIndex = -1;
        this._sheetName = null;
        this._version = null;
        this._srcSheetIndex = srcSheetIndex;
        this._dstSheetIndex = dstSheetIndex;
        this._mode = ShiftMode.SheetMove;
    }

    public static FormulaShifter createForRowShift(int externSheetIndex, String sheetName, int firstMovedRowIndex, int lastMovedRowIndex, int numberOfRowsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedRowIndex, lastMovedRowIndex, numberOfRowsToMove, ShiftMode.RowMove, version);
    }

    public static FormulaShifter createForRowCopy(int externSheetIndex, String sheetName, int firstMovedRowIndex, int lastMovedRowIndex, int numberOfRowsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedRowIndex, lastMovedRowIndex, numberOfRowsToMove, ShiftMode.RowCopy, version);
    }

    public static FormulaShifter createForColumnShift(int externSheetIndex, String sheetName, int firstMovedColumnIndex, int lastMovedColumnIndex, int numberOfColumnsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedColumnIndex, lastMovedColumnIndex, numberOfColumnsToMove, ShiftMode.ColumnMove, version);
    }

    public static FormulaShifter createForColumnCopy(int externSheetIndex, String sheetName, int firstMovedColumnIndex, int lastMovedColumnIndex, int numberOfColumnsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedColumnIndex, lastMovedColumnIndex, numberOfColumnsToMove, ShiftMode.ColumnCopy, version);
    }

    public static FormulaShifter createForSheetShift(int srcSheetIndex, int dstSheetIndex) {
        return new FormulaShifter(srcSheetIndex, dstSheetIndex);
    }

    public String toString() {
        return this.getClass().getName() + " [" + this._firstMovedIndex + this._lastMovedIndex + this._amountToMove + "]";
    }

    public boolean adjustFormula(Ptg[] ptgs, int currentExternSheetIx) {
        boolean refsWereChanged = false;
        for (int i = 0; i < ptgs.length; ++i) {
            Ptg newPtg = this.adjustPtg(ptgs[i], currentExternSheetIx);
            if (newPtg == null) continue;
            refsWereChanged = true;
            ptgs[i] = newPtg;
        }
        return refsWereChanged;
    }

    private Ptg adjustPtg(Ptg ptg, int currentExternSheetIx) {
        switch (this._mode) {
            case RowMove: {
                return this.adjustPtgDueToRowMove(ptg, currentExternSheetIx);
            }
            case RowCopy: {
                return this.adjustPtgDueToRowCopy(ptg);
            }
            case ColumnMove: {
                return this.adjustPtgDueToColumnMove(ptg, currentExternSheetIx);
            }
            case ColumnCopy: {
                return this.adjustPtgDueToColumnCopy(ptg);
            }
            case SheetMove: {
                return this.adjustPtgDueToSheetMove(ptg);
            }
        }
        throw new IllegalStateException("Unsupported shift mode: " + (Object)((Object)this._mode));
    }

    private Ptg adjustPtgDueToMove(Ptg ptg, int currentExternSheetIx, boolean isRowMove) {
        if (ptg instanceof RefPtg) {
            if (currentExternSheetIx != this._externSheetIndex) {
                return null;
            }
            RefPtg rptg = (RefPtg)ptg;
            return isRowMove ? this.rowMoveRefPtg(rptg) : this.columnMoveRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg = (Ref3DPtg)ptg;
            if (this._externSheetIndex != rptg.getExternSheetIndex()) {
                return null;
            }
            return isRowMove ? this.rowMoveRefPtg(rptg) : this.columnMoveRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg rpxg = (Ref3DPxg)ptg;
            if (rpxg.getExternalWorkbookNumber() > 0 || !this._sheetName.equalsIgnoreCase(rpxg.getSheetName())) {
                return null;
            }
            return isRowMove ? this.rowMoveRefPtg(rpxg) : this.columnMoveRefPtg(rpxg);
        }
        if (ptg instanceof Area2DPtgBase) {
            if (currentExternSheetIx != this._externSheetIndex) {
                return ptg;
            }
            Area2DPtgBase aptg = (Area2DPtgBase)ptg;
            return isRowMove ? this.rowMoveAreaPtg(aptg) : this.columnMoveAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg aptg = (Area3DPtg)ptg;
            if (this._externSheetIndex != aptg.getExternSheetIndex()) {
                return null;
            }
            return isRowMove ? this.rowMoveAreaPtg(aptg) : this.columnMoveAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPxg) {
            Area3DPxg apxg = (Area3DPxg)ptg;
            if (apxg.getExternalWorkbookNumber() > 0 || !this._sheetName.equalsIgnoreCase(apxg.getSheetName())) {
                return null;
            }
            return isRowMove ? this.rowMoveAreaPtg(apxg) : this.columnMoveAreaPtg(apxg);
        }
        return null;
    }

    private Ptg adjustPtgDueToRowMove(Ptg ptg, int currentExternSheetIx) {
        return this.adjustPtgDueToMove(ptg, currentExternSheetIx, true);
    }

    private Ptg adjustPtgDueToColumnMove(Ptg ptg, int currentExternSheetIx) {
        return this.adjustPtgDueToMove(ptg, currentExternSheetIx, false);
    }

    private Ptg adjustPtgDueToCopy(Ptg ptg, boolean isRowCopy) {
        if (ptg instanceof RefPtg) {
            RefPtg rptg = (RefPtg)ptg;
            return isRowCopy ? this.rowCopyRefPtg(rptg) : this.columnCopyRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg = (Ref3DPtg)ptg;
            return isRowCopy ? this.rowCopyRefPtg(rptg) : this.columnCopyRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg rpxg = (Ref3DPxg)ptg;
            return isRowCopy ? this.rowCopyRefPtg(rpxg) : this.columnCopyRefPtg(rpxg);
        }
        if (ptg instanceof Area2DPtgBase) {
            Area2DPtgBase aptg = (Area2DPtgBase)ptg;
            return isRowCopy ? this.rowCopyAreaPtg(aptg) : this.columnCopyAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg aptg = (Area3DPtg)ptg;
            return isRowCopy ? this.rowCopyAreaPtg(aptg) : this.columnCopyAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPxg) {
            Area3DPxg apxg = (Area3DPxg)ptg;
            return isRowCopy ? this.rowCopyAreaPtg(apxg) : this.columnCopyAreaPtg(apxg);
        }
        return null;
    }

    private Ptg adjustPtgDueToRowCopy(Ptg ptg) {
        return this.adjustPtgDueToCopy(ptg, true);
    }

    private Ptg adjustPtgDueToColumnCopy(Ptg ptg) {
        return this.adjustPtgDueToCopy(ptg, false);
    }

    private Ptg adjustPtgDueToSheetMove(Ptg ptg) {
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg ref = (Ref3DPtg)ptg;
            int oldSheetIndex = ref.getExternSheetIndex();
            if (oldSheetIndex < this._srcSheetIndex && oldSheetIndex < this._dstSheetIndex) {
                return null;
            }
            if (oldSheetIndex > this._srcSheetIndex && oldSheetIndex > this._dstSheetIndex) {
                return null;
            }
            if (oldSheetIndex == this._srcSheetIndex) {
                ref.setExternSheetIndex(this._dstSheetIndex);
                return ref;
            }
            if (this._dstSheetIndex < this._srcSheetIndex) {
                ref.setExternSheetIndex(oldSheetIndex + 1);
                return ref;
            }
            if (this._dstSheetIndex > this._srcSheetIndex) {
                ref.setExternSheetIndex(oldSheetIndex - 1);
                return ref;
            }
        }
        return null;
    }

    private Ptg rowMoveRefPtg(RefPtgBase rptg) {
        int refRow = rptg.getRow();
        if (this._firstMovedIndex <= refRow && refRow <= this._lastMovedIndex) {
            rptg.setRow(refRow + this._amountToMove);
            return rptg;
        }
        int destFirstRowIndex = this._firstMovedIndex + this._amountToMove;
        int destLastRowIndex = this._lastMovedIndex + this._amountToMove;
        if (destLastRowIndex < refRow || refRow < destFirstRowIndex) {
            return null;
        }
        if (destFirstRowIndex <= refRow && refRow <= destLastRowIndex) {
            return FormulaShifter.createDeletedRef(rptg);
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + refRow + ", " + refRow + ")");
    }

    private Ptg rowMoveAreaPtg(AreaPtgBase aptg) {
        int aFirstRow = aptg.getFirstRow();
        int aLastRow = aptg.getLastRow();
        if (this._firstMovedIndex <= aFirstRow && aLastRow <= this._lastMovedIndex) {
            aptg.setFirstRow(aFirstRow + this._amountToMove);
            aptg.setLastRow(aLastRow + this._amountToMove);
            return aptg;
        }
        int destFirstRowIndex = this._firstMovedIndex + this._amountToMove;
        int destLastRowIndex = this._lastMovedIndex + this._amountToMove;
        if (aFirstRow < this._firstMovedIndex && this._lastMovedIndex < aLastRow) {
            if (destFirstRowIndex < aFirstRow && aFirstRow <= destLastRowIndex) {
                aptg.setFirstRow(destLastRowIndex + 1);
                return aptg;
            }
            if (destFirstRowIndex <= aLastRow && aLastRow < destLastRowIndex) {
                aptg.setLastRow(destFirstRowIndex - 1);
                return aptg;
            }
            return null;
        }
        if (this._firstMovedIndex <= aFirstRow && aFirstRow <= this._lastMovedIndex) {
            if (this._amountToMove < 0) {
                aptg.setFirstRow(aFirstRow + this._amountToMove);
                return aptg;
            }
            if (destFirstRowIndex > aLastRow) {
                return null;
            }
            int newFirstRowIx = aFirstRow + this._amountToMove;
            if (destLastRowIndex < aLastRow) {
                aptg.setFirstRow(newFirstRowIx);
                return aptg;
            }
            int areaRemainingTopRowIx = this._lastMovedIndex + 1;
            if (destFirstRowIndex > areaRemainingTopRowIx) {
                newFirstRowIx = areaRemainingTopRowIx;
            }
            aptg.setFirstRow(newFirstRowIx);
            aptg.setLastRow(Math.max(aLastRow, destLastRowIndex));
            return aptg;
        }
        if (this._firstMovedIndex <= aLastRow && aLastRow <= this._lastMovedIndex) {
            if (this._amountToMove > 0) {
                aptg.setLastRow(aLastRow + this._amountToMove);
                return aptg;
            }
            if (destLastRowIndex < aFirstRow) {
                return null;
            }
            int newLastRowIx = aLastRow + this._amountToMove;
            if (destFirstRowIndex > aFirstRow) {
                aptg.setLastRow(newLastRowIx);
                return aptg;
            }
            int areaRemainingBottomRowIx = this._firstMovedIndex - 1;
            if (destLastRowIndex < areaRemainingBottomRowIx) {
                newLastRowIx = areaRemainingBottomRowIx;
            }
            aptg.setFirstRow(Math.min(aFirstRow, destFirstRowIndex));
            aptg.setLastRow(newLastRowIx);
            return aptg;
        }
        if (destLastRowIndex < aFirstRow || aLastRow < destFirstRowIndex) {
            return null;
        }
        if (destFirstRowIndex <= aFirstRow && aLastRow <= destLastRowIndex) {
            return FormulaShifter.createDeletedRef(aptg);
        }
        if (aFirstRow <= destFirstRowIndex && destLastRowIndex <= aLastRow) {
            return null;
        }
        if (destFirstRowIndex < aFirstRow && aFirstRow <= destLastRowIndex) {
            aptg.setFirstRow(destLastRowIndex + 1);
            return aptg;
        }
        if (destFirstRowIndex <= aLastRow && aLastRow < destLastRowIndex) {
            aptg.setLastRow(destFirstRowIndex - 1);
            return aptg;
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + aFirstRow + ", " + aLastRow + ")");
    }

    private Ptg rowCopyRefPtg(RefPtgBase rptg) {
        int refRow = rptg.getRow();
        if (rptg.isRowRelative()) {
            int destRowIndex = this._firstMovedIndex + this._amountToMove;
            if (destRowIndex < 0 || this._version.getLastRowIndex() < destRowIndex) {
                return FormulaShifter.createDeletedRef(rptg);
            }
            int newRowIndex = refRow + this._amountToMove;
            if (newRowIndex < 0 || this._version.getLastRowIndex() < newRowIndex) {
                return FormulaShifter.createDeletedRef(rptg);
            }
            rptg.setRow(newRowIndex);
            return rptg;
        }
        return null;
    }

    private Ptg rowCopyAreaPtg(AreaPtgBase aptg) {
        boolean changed = false;
        int aFirstRow = aptg.getFirstRow();
        int aLastRow = aptg.getLastRow();
        if (aptg.isFirstRowRelative()) {
            int destFirstRowIndex = aFirstRow + this._amountToMove;
            if (destFirstRowIndex < 0 || this._version.getLastRowIndex() < destFirstRowIndex) {
                return FormulaShifter.createDeletedRef(aptg);
            }
            aptg.setFirstRow(destFirstRowIndex);
            changed = true;
        }
        if (aptg.isLastRowRelative()) {
            int destLastRowIndex = aLastRow + this._amountToMove;
            if (destLastRowIndex < 0 || this._version.getLastRowIndex() < destLastRowIndex) {
                return FormulaShifter.createDeletedRef(aptg);
            }
            aptg.setLastRow(destLastRowIndex);
            changed = true;
        }
        if (changed) {
            aptg.sortTopLeftToBottomRight();
        }
        return changed ? aptg : null;
    }

    private Ptg columnMoveRefPtg(RefPtgBase rptg) {
        int refColumn = rptg.getColumn();
        if (this._firstMovedIndex <= refColumn && refColumn <= this._lastMovedIndex) {
            rptg.setColumn(refColumn + this._amountToMove);
            return rptg;
        }
        int destFirstColumnIndex = this._firstMovedIndex + this._amountToMove;
        int destLastColumnIndex = this._lastMovedIndex + this._amountToMove;
        if (destLastColumnIndex < refColumn || refColumn < destFirstColumnIndex) {
            return null;
        }
        if (destFirstColumnIndex <= refColumn && refColumn <= destLastColumnIndex) {
            return FormulaShifter.createDeletedRef(rptg);
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + refColumn + ", " + refColumn + ")");
    }

    private Ptg columnMoveAreaPtg(AreaPtgBase aptg) {
        int aFirstColumn = aptg.getFirstColumn();
        int aLastColumn = aptg.getLastColumn();
        if (this._firstMovedIndex <= aFirstColumn && aLastColumn <= this._lastMovedIndex) {
            aptg.setFirstColumn(aFirstColumn + this._amountToMove);
            aptg.setLastColumn(aLastColumn + this._amountToMove);
            return aptg;
        }
        int destFirstColumnIndex = this._firstMovedIndex + this._amountToMove;
        int destLastColumnIndex = this._lastMovedIndex + this._amountToMove;
        if (aFirstColumn < this._firstMovedIndex && this._lastMovedIndex < aLastColumn) {
            if (destFirstColumnIndex < aFirstColumn && aFirstColumn <= destLastColumnIndex) {
                aptg.setFirstColumn(destLastColumnIndex + 1);
                return aptg;
            }
            if (destFirstColumnIndex <= aLastColumn && aLastColumn < destLastColumnIndex) {
                aptg.setLastColumn(destFirstColumnIndex - 1);
                return aptg;
            }
            return null;
        }
        if (this._firstMovedIndex <= aFirstColumn && aFirstColumn <= this._lastMovedIndex) {
            if (this._amountToMove < 0) {
                aptg.setFirstColumn(aFirstColumn + this._amountToMove);
                return aptg;
            }
            if (destFirstColumnIndex > aLastColumn) {
                return null;
            }
            int newFirstColumnIx = aFirstColumn + this._amountToMove;
            if (destLastColumnIndex < aLastColumn) {
                aptg.setFirstColumn(newFirstColumnIx);
                return aptg;
            }
            int areaRemainingTopColumnIx = this._lastMovedIndex + 1;
            if (destFirstColumnIndex > areaRemainingTopColumnIx) {
                newFirstColumnIx = areaRemainingTopColumnIx;
            }
            aptg.setFirstColumn(newFirstColumnIx);
            aptg.setLastColumn(Math.max(aLastColumn, destLastColumnIndex));
            return aptg;
        }
        if (this._firstMovedIndex <= aLastColumn && aLastColumn <= this._lastMovedIndex) {
            if (this._amountToMove > 0) {
                aptg.setLastColumn(aLastColumn + this._amountToMove);
                return aptg;
            }
            if (destLastColumnIndex < aFirstColumn) {
                return null;
            }
            int newLastColumnIx = aLastColumn + this._amountToMove;
            if (destFirstColumnIndex > aFirstColumn) {
                aptg.setLastColumn(newLastColumnIx);
                return aptg;
            }
            int areaRemainingBottomColumnIx = this._firstMovedIndex - 1;
            if (destLastColumnIndex < areaRemainingBottomColumnIx) {
                newLastColumnIx = areaRemainingBottomColumnIx;
            }
            aptg.setFirstColumn(Math.min(aFirstColumn, destFirstColumnIndex));
            aptg.setLastColumn(newLastColumnIx);
            return aptg;
        }
        if (destLastColumnIndex < aFirstColumn || aLastColumn < destFirstColumnIndex) {
            return null;
        }
        if (destFirstColumnIndex <= aFirstColumn && aLastColumn <= destLastColumnIndex) {
            return FormulaShifter.createDeletedRef(aptg);
        }
        if (aFirstColumn <= destFirstColumnIndex && destLastColumnIndex <= aLastColumn) {
            return null;
        }
        if (destFirstColumnIndex < aFirstColumn && aFirstColumn <= destLastColumnIndex) {
            aptg.setFirstColumn(destLastColumnIndex + 1);
            return aptg;
        }
        if (destFirstColumnIndex <= aLastColumn && aLastColumn < destLastColumnIndex) {
            aptg.setLastColumn(destFirstColumnIndex - 1);
            return aptg;
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + aFirstColumn + ", " + aLastColumn + ")");
    }

    private Ptg columnCopyRefPtg(RefPtgBase rptg) {
        int refColumn = rptg.getColumn();
        if (rptg.isColRelative()) {
            int destColumnIndex = this._firstMovedIndex + this._amountToMove;
            if (destColumnIndex < 0 || this._version.getLastColumnIndex() < destColumnIndex) {
                return FormulaShifter.createDeletedRef(rptg);
            }
            int newColumnIndex = refColumn + this._amountToMove;
            if (newColumnIndex < 0 || this._version.getLastColumnIndex() < newColumnIndex) {
                return FormulaShifter.createDeletedRef(rptg);
            }
            rptg.setColumn(newColumnIndex);
            return rptg;
        }
        return null;
    }

    private Ptg columnCopyAreaPtg(AreaPtgBase aptg) {
        boolean changed = false;
        int aFirstColumn = aptg.getFirstColumn();
        int aLastColumn = aptg.getLastColumn();
        if (aptg.isFirstColRelative()) {
            int destFirstColumnIndex = aFirstColumn + this._amountToMove;
            if (destFirstColumnIndex < 0 || this._version.getLastColumnIndex() < destFirstColumnIndex) {
                return FormulaShifter.createDeletedRef(aptg);
            }
            aptg.setFirstColumn(destFirstColumnIndex);
            changed = true;
        }
        if (aptg.isLastColRelative()) {
            int destLastColumnIndex = aLastColumn + this._amountToMove;
            if (destLastColumnIndex < 0 || this._version.getLastColumnIndex() < destLastColumnIndex) {
                return FormulaShifter.createDeletedRef(aptg);
            }
            aptg.setLastColumn(destLastColumnIndex);
            changed = true;
        }
        if (changed) {
            aptg.sortTopLeftToBottomRight();
        }
        return changed ? aptg : null;
    }

    private static Ptg createDeletedRef(Ptg ptg) {
        if (ptg instanceof RefPtg) {
            return new RefErrorPtg();
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg = (Ref3DPtg)ptg;
            return new DeletedRef3DPtg(rptg.getExternSheetIndex());
        }
        if (ptg instanceof AreaPtg) {
            return new AreaErrPtg();
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg area3DPtg = (Area3DPtg)ptg;
            return new DeletedArea3DPtg(area3DPtg.getExternSheetIndex());
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg pxg = (Ref3DPxg)ptg;
            return new Deleted3DPxg(pxg.getExternalWorkbookNumber(), pxg.getSheetName());
        }
        if (ptg instanceof Area3DPxg) {
            Area3DPxg pxg = (Area3DPxg)ptg;
            return new Deleted3DPxg(pxg.getExternalWorkbookNumber(), pxg.getSheetName());
        }
        throw new IllegalArgumentException("Unexpected ref ptg class (" + ptg.getClass().getName() + ")");
    }

    private static enum ShiftMode {
        RowMove,
        RowCopy,
        ColumnMove,
        ColumnCopy,
        SheetMove;

    }
}

