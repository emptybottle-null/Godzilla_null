/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel.helpers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class HSSFRowShifter
extends RowShifter {
    private static final POILogger logger = POILogFactory.getLogger(HSSFRowShifter.class);

    public HSSFRowShifter(HSSFSheet sh) {
        super(sh);
    }

    @Override
    @NotImplemented
    public void updateNamedRanges(FormulaShifter formulaShifter) {
        throw new NotImplementedException("HSSFRowShifter.updateNamedRanges");
    }

    @Override
    @NotImplemented
    public void updateFormulas(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateFormulas");
    }

    @Override
    @NotImplemented
    public void updateConditionalFormatting(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateConditionalFormatting");
    }

    @Override
    @NotImplemented
    public void updateHyperlinks(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateHyperlinks");
    }
}

