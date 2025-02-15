/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.FormulaCellCacheEntry;

final class FormulaCellCache {
    private final Map<Object, FormulaCellCacheEntry> _formulaEntriesByCell = new HashMap<Object, FormulaCellCacheEntry>();

    public CellCacheEntry[] getCacheEntries() {
        CellCacheEntry[] result = new FormulaCellCacheEntry[this._formulaEntriesByCell.size()];
        this._formulaEntriesByCell.values().toArray(result);
        return result;
    }

    public void clear() {
        this._formulaEntriesByCell.clear();
    }

    public FormulaCellCacheEntry get(EvaluationCell cell) {
        return this._formulaEntriesByCell.get(cell.getIdentityKey());
    }

    public void put(EvaluationCell cell, FormulaCellCacheEntry entry) {
        this._formulaEntriesByCell.put(cell.getIdentityKey(), entry);
    }

    public FormulaCellCacheEntry remove(EvaluationCell cell) {
        return this._formulaEntriesByCell.remove(cell.getIdentityKey());
    }

    public void applyOperation(IEntryOperation operation) {
        Iterator<FormulaCellCacheEntry> i = this._formulaEntriesByCell.values().iterator();
        while (i.hasNext()) {
            operation.processEntry(i.next());
        }
    }

    static interface IEntryOperation {
        public void processEntry(FormulaCellCacheEntry var1);
    }
}

