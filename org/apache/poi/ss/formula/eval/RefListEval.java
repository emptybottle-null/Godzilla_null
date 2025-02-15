/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.eval.ValueEval;

public class RefListEval
implements ValueEval {
    private final List<ValueEval> list = new ArrayList<ValueEval>();

    public RefListEval(ValueEval v1, ValueEval v2) {
        this.add(v1);
        this.add(v2);
    }

    private void add(ValueEval v) {
        if (v instanceof RefListEval) {
            this.list.addAll(((RefListEval)v).list);
        } else {
            this.list.add(v);
        }
    }

    public List<ValueEval> getList() {
        return this.list;
    }
}

