/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.util.Internal;

@Internal
public interface EvaluationSheet {
    public EvaluationCell getCell(int var1, int var2);

    public void clearAllCachedResultValues();

    public int getLastRowNum();

    public boolean isRowHidden(int var1);
}

