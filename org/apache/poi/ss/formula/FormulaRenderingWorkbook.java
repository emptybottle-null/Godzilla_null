/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;

public interface FormulaRenderingWorkbook {
    public EvaluationWorkbook.ExternalSheet getExternalSheet(int var1);

    public String getSheetFirstNameByExternSheet(int var1);

    public String getSheetLastNameByExternSheet(int var1);

    public String resolveNameXText(NameXPtg var1);

    public String getNameText(NamePtg var1);
}

