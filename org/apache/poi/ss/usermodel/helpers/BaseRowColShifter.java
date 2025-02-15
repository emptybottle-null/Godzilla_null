/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.helpers;

import java.util.List;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
public abstract class BaseRowColShifter {
    public abstract void updateNamedRanges(FormulaShifter var1);

    public abstract void updateFormulas(FormulaShifter var1);

    public abstract List<CellRangeAddress> shiftMergedRegions(int var1, int var2, int var3);

    public abstract void updateConditionalFormatting(FormulaShifter var1);

    public abstract void updateHyperlinks(FormulaShifter var1);

    public static CellRangeAddress shiftRange(FormulaShifter formulaShifter, CellRangeAddress cra, int currentExternSheetIx) {
        AreaPtg aptg = new AreaPtg(cra.getFirstRow(), cra.getLastRow(), cra.getFirstColumn(), cra.getLastColumn(), false, false, false, false);
        Ptg[] ptgs = new Ptg[]{aptg};
        if (!formulaShifter.adjustFormula(ptgs, currentExternSheetIx)) {
            return cra;
        }
        Ptg ptg0 = ptgs[0];
        if (ptg0 instanceof AreaPtg) {
            AreaPtg bptg = (AreaPtg)ptg0;
            return new CellRangeAddress(bptg.getFirstRow(), bptg.getLastRow(), bptg.getFirstColumn(), bptg.getLastColumn());
        }
        if (ptg0 instanceof AreaErrPtg) {
            return null;
        }
        throw new IllegalStateException("Unexpected shifted ptg class (" + ptg0.getClass().getName() + ")");
    }
}

