/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.ValueEval;

public interface TwoDEval
extends ValueEval {
    public ValueEval getValue(int var1, int var2);

    public int getWidth();

    public int getHeight();

    public boolean isRow();

    public boolean isColumn();

    public TwoDEval getRow(int var1);

    public TwoDEval getColumn(int var1);

    public boolean isSubTotal(int var1, int var2);

    public boolean isRowHidden(int var1);
}

