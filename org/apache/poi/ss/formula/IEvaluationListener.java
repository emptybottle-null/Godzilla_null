/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.eval.ValueEval;

interface IEvaluationListener {
    public void onCacheHit(int var1, int var2, int var3, ValueEval var4);

    public void onReadPlainValue(int var1, int var2, int var3, ICacheEntry var4);

    public void onStartEvaluate(EvaluationCell var1, ICacheEntry var2);

    public void onEndEvaluate(ICacheEntry var1, ValueEval var2);

    public void onClearWholeCache();

    public void onClearCachedValue(ICacheEntry var1);

    public void sortDependentCachedValues(ICacheEntry[] var1);

    public void onClearDependentCachedValue(ICacheEntry var1, int var2);

    public void onChangeFromBlankValue(int var1, int var2, int var3, EvaluationCell var4, ICacheEntry var5);

    public static interface ICacheEntry {
        public ValueEval getValue();
    }
}

