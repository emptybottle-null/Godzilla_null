/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public interface AreaEval
extends TwoDEval,
ThreeDEval {
    public int getFirstRow();

    public int getLastRow();

    public int getFirstColumn();

    public int getLastColumn();

    public ValueEval getAbsoluteValue(int var1, int var2);

    public boolean contains(int var1, int var2);

    public boolean containsColumn(int var1);

    public boolean containsRow(int var1);

    @Override
    public int getWidth();

    @Override
    public int getHeight();

    public ValueEval getRelativeValue(int var1, int var2);

    public AreaEval offset(int var1, int var2, int var3, int var4);
}

