/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.Ptg;

public interface EvaluationName {
    public String getNameText();

    public boolean isFunctionName();

    public boolean hasFormula();

    public Ptg[] getNameDefinition();

    public boolean isRange();

    public NamePtg createPtg();
}

