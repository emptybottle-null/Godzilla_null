/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.SheetRange;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public interface RefEval
extends ValueEval,
SheetRange {
    public ValueEval getInnerValueEval(int var1);

    public int getColumn();

    public int getRow();

    @Override
    public int getFirstSheetIndex();

    @Override
    public int getLastSheetIndex();

    public int getNumberOfSheets();

    public AreaEval offset(int var1, int var2, int var3, int var4);
}

