/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.eval.ValueEval;

final class PlainValueCellCacheEntry
extends CellCacheEntry {
    public PlainValueCellCacheEntry(ValueEval value) {
        this.updateValue(value);
    }
}

