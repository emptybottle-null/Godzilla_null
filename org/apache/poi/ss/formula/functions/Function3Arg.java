/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function;

public interface Function3Arg
extends Function {
    public ValueEval evaluate(int var1, int var2, ValueEval var3, ValueEval var4, ValueEval var5);
}

