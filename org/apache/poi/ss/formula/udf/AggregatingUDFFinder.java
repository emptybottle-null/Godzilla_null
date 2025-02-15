/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.udf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.poi.ss.formula.atp.AnalysisToolPak;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.udf.UDFFinder;

public class AggregatingUDFFinder
implements UDFFinder {
    public static final UDFFinder DEFAULT = new AggregatingUDFFinder(AnalysisToolPak.instance);
    private final Collection<UDFFinder> _usedToolPacks;

    public AggregatingUDFFinder(UDFFinder ... usedToolPacks) {
        this._usedToolPacks = new ArrayList<UDFFinder>(usedToolPacks.length);
        this._usedToolPacks.addAll(Arrays.asList(usedToolPacks));
    }

    @Override
    public FreeRefFunction findFunction(String name) {
        for (UDFFinder pack : this._usedToolPacks) {
            FreeRefFunction evaluatorForFunction = pack.findFunction(name);
            if (evaluatorForFunction == null) continue;
            return evaluatorForFunction;
        }
        return null;
    }

    public void add(UDFFinder toolPack) {
        this._usedToolPacks.add(toolPack);
    }
}

