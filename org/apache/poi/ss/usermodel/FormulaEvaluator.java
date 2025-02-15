/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.util.Removal;

public interface FormulaEvaluator {
    public void clearAllCachedResultValues();

    public void notifySetFormula(Cell var1);

    public void notifyDeleteCell(Cell var1);

    public void notifyUpdateCell(Cell var1);

    public void evaluateAll();

    public CellValue evaluate(Cell var1);

    public CellType evaluateFormulaCell(Cell var1);

    @Deprecated
    @Removal(version="4.2")
    public CellType evaluateFormulaCellEnum(Cell var1);

    public Cell evaluateInCell(Cell var1);

    public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> var1);

    public void setIgnoreMissingWorkbooks(boolean var1);

    public void setDebugEvaluationOutputForNextEval(boolean var1);
}

