/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

public interface EvaluationCell {
    public Object getIdentityKey();

    public EvaluationSheet getSheet();

    public int getRowIndex();

    public int getColumnIndex();

    public CellType getCellType();

    @Deprecated
    @Removal(version="4.2")
    public CellType getCellTypeEnum();

    public double getNumericCellValue();

    public String getStringCellValue();

    public boolean getBooleanCellValue();

    public int getErrorCellValue();

    public CellRangeAddress getArrayFormulaRange();

    public boolean isPartOfArrayFormulaGroup();

    public CellType getCachedFormulaResultType();

    @Deprecated
    @Removal(version="4.2")
    public CellType getCachedFormulaResultTypeEnum();
}

