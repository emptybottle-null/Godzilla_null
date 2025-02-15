/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.ValueEval;

public final class BlankEval
implements ValueEval {
    public static final BlankEval instance = new BlankEval();

    private BlankEval() {
    }
}

