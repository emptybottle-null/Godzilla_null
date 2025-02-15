/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowColShifter;

public final class XSSFRowShifter
extends RowShifter {
    private static final POILogger logger = POILogFactory.getLogger(XSSFRowShifter.class);

    public XSSFRowShifter(XSSFSheet sh) {
        super(sh);
    }

    @Override
    public void updateNamedRanges(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateNamedRanges(this.sheet, formulaShifter);
    }

    @Override
    public void updateFormulas(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateFormulas(this.sheet, formulaShifter);
    }

    @Internal(since="3.15 beta 2")
    public void updateRowFormulas(XSSFRow row, FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateRowFormulas(row, formulaShifter);
    }

    @Override
    public void updateConditionalFormatting(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateConditionalFormatting(this.sheet, formulaShifter);
    }

    @Override
    public void updateHyperlinks(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateHyperlinks(this.sheet, formulaShifter);
    }
}

