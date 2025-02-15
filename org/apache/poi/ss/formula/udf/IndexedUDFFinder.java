/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.udf;

import java.util.HashMap;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.util.Internal;

@Internal
public class IndexedUDFFinder
extends AggregatingUDFFinder {
    private final HashMap<Integer, String> _funcMap = new HashMap();

    public IndexedUDFFinder(UDFFinder ... usedToolPacks) {
        super(usedToolPacks);
    }

    @Override
    public FreeRefFunction findFunction(String name) {
        FreeRefFunction func = super.findFunction(name);
        if (func != null) {
            int idx = this.getFunctionIndex(name);
            this._funcMap.put(idx, name);
        }
        return func;
    }

    public String getFunctionName(int idx) {
        return this._funcMap.get(idx);
    }

    public int getFunctionIndex(String name) {
        return name.hashCode();
    }
}

