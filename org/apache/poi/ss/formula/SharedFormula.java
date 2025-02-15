/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;

public class SharedFormula {
    private final int _columnWrappingMask;
    private final int _rowWrappingMask;

    public SharedFormula(SpreadsheetVersion ssVersion) {
        this._columnWrappingMask = ssVersion.getLastColumnIndex();
        this._rowWrappingMask = ssVersion.getLastRowIndex();
    }

    public Ptg[] convertSharedFormulas(Ptg[] ptgs, int formulaRow, int formulaColumn) {
        Ptg[] newPtgStack = new Ptg[ptgs.length];
        for (int k = 0; k < ptgs.length; ++k) {
            Ptg ptg = ptgs[k];
            byte originalOperandClass = -1;
            if (!ptg.isBaseToken()) {
                originalOperandClass = ptg.getPtgClass();
            }
            if (ptg instanceof RefPtgBase) {
                RefPtgBase refNPtg = (RefPtgBase)ptg;
                ptg = new RefPtg(this.fixupRelativeRow(formulaRow, refNPtg.getRow(), refNPtg.isRowRelative()), this.fixupRelativeColumn(formulaColumn, refNPtg.getColumn(), refNPtg.isColRelative()), refNPtg.isRowRelative(), refNPtg.isColRelative());
                ptg.setClass(originalOperandClass);
            } else if (ptg instanceof AreaPtgBase) {
                AreaPtgBase areaNPtg = (AreaPtgBase)ptg;
                ptg = new AreaPtg(this.fixupRelativeRow(formulaRow, areaNPtg.getFirstRow(), areaNPtg.isFirstRowRelative()), this.fixupRelativeRow(formulaRow, areaNPtg.getLastRow(), areaNPtg.isLastRowRelative()), this.fixupRelativeColumn(formulaColumn, areaNPtg.getFirstColumn(), areaNPtg.isFirstColRelative()), this.fixupRelativeColumn(formulaColumn, areaNPtg.getLastColumn(), areaNPtg.isLastColRelative()), areaNPtg.isFirstRowRelative(), areaNPtg.isLastRowRelative(), areaNPtg.isFirstColRelative(), areaNPtg.isLastColRelative());
                ptg.setClass(originalOperandClass);
            } else if (ptg instanceof OperandPtg) {
                ptg = ((OperandPtg)ptg).copy();
            }
            newPtgStack[k] = ptg;
        }
        return newPtgStack;
    }

    private int fixupRelativeColumn(int currentcolumn, int column, boolean relative) {
        if (relative) {
            return column + currentcolumn & this._columnWrappingMask;
        }
        return column;
    }

    private int fixupRelativeRow(int currentrow, int row, boolean relative) {
        if (relative) {
            return row + currentrow & this._rowWrappingMask;
        }
        return row;
    }
}

