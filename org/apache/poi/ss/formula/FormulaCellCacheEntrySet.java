/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.FormulaCellCacheEntry;

final class FormulaCellCacheEntrySet {
    private static final FormulaCellCacheEntry[] EMPTY_ARRAY = new FormulaCellCacheEntry[0];
    private int _size;
    private FormulaCellCacheEntry[] _arr = EMPTY_ARRAY;

    public FormulaCellCacheEntry[] toArray() {
        int nItems = this._size;
        if (nItems < 1) {
            return EMPTY_ARRAY;
        }
        FormulaCellCacheEntry[] result = new FormulaCellCacheEntry[nItems];
        int j = 0;
        for (int i = 0; i < this._arr.length; ++i) {
            FormulaCellCacheEntry cce = this._arr[i];
            if (cce == null) continue;
            result[j++] = cce;
        }
        if (j != nItems) {
            throw new IllegalStateException("size mismatch");
        }
        return result;
    }

    public void add(CellCacheEntry cce) {
        if (this._size * 3 >= this._arr.length * 2) {
            FormulaCellCacheEntry[] prevArr = this._arr;
            CellCacheEntry[] newArr = new FormulaCellCacheEntry[4 + this._arr.length * 3 / 2];
            for (int i = 0; i < prevArr.length; ++i) {
                FormulaCellCacheEntry prevCce = this._arr[i];
                if (prevCce == null) continue;
                FormulaCellCacheEntrySet.addInternal(newArr, prevCce);
            }
            this._arr = newArr;
        }
        if (FormulaCellCacheEntrySet.addInternal(this._arr, cce)) {
            ++this._size;
        }
    }

    private static boolean addInternal(CellCacheEntry[] arr, CellCacheEntry cce) {
        CellCacheEntry item;
        int startIx;
        int i;
        for (i = startIx = Math.abs(cce.hashCode() % arr.length); i < arr.length; ++i) {
            item = arr[i];
            if (item == cce) {
                return false;
            }
            if (item != null) continue;
            arr[i] = cce;
            return true;
        }
        for (i = 0; i < startIx; ++i) {
            item = arr[i];
            if (item == cce) {
                return false;
            }
            if (item != null) continue;
            arr[i] = cce;
            return true;
        }
        throw new IllegalStateException("No empty space found");
    }

    public boolean remove(CellCacheEntry cce) {
        FormulaCellCacheEntry item;
        int startIx;
        int i;
        FormulaCellCacheEntry[] arr = this._arr;
        if (this._size * 3 < this._arr.length && this._arr.length > 8) {
            boolean found = false;
            FormulaCellCacheEntry[] prevArr = this._arr;
            CellCacheEntry[] newArr = new FormulaCellCacheEntry[this._arr.length / 2];
            for (int i2 = 0; i2 < prevArr.length; ++i2) {
                FormulaCellCacheEntry prevCce = this._arr[i2];
                if (prevCce == null) continue;
                if (prevCce == cce) {
                    found = true;
                    --this._size;
                    continue;
                }
                FormulaCellCacheEntrySet.addInternal(newArr, prevCce);
            }
            this._arr = newArr;
            return found;
        }
        for (i = startIx = Math.abs(cce.hashCode() % arr.length); i < arr.length; ++i) {
            item = arr[i];
            if (item != cce) continue;
            arr[i] = null;
            --this._size;
            return true;
        }
        for (i = 0; i < startIx; ++i) {
            item = arr[i];
            if (item != cce) continue;
            arr[i] = null;
            --this._size;
            return true;
        }
        return false;
    }
}

