/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public interface FormulaParsingWorkbook {
    public EvaluationName getName(String var1, int var2);

    public Name createName();

    public Table getTable(String var1);

    public Ptg getNameXPtg(String var1, SheetIdentifier var2);

    public Ptg get3DReferencePtg(CellReference var1, SheetIdentifier var2);

    public Ptg get3DReferencePtg(AreaReference var1, SheetIdentifier var2);

    public int getExternalSheetIndex(String var1);

    public int getExternalSheetIndex(String var1, String var2);

    public SpreadsheetVersion getSpreadsheetVersion();
}

