/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ValueEval;

public interface FreeRefFunction {
    public ValueEval evaluate(ValueEval[] var1, OperationEvaluationContext var2);
}

