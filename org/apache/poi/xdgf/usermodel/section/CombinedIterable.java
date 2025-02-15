/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

public class CombinedIterable<T>
implements Iterable<T> {
    final SortedMap<Long, T> _baseItems;
    final SortedMap<Long, T> _masterItems;

    public CombinedIterable(SortedMap<Long, T> baseItems, SortedMap<Long, T> masterItems) {
        this._baseItems = baseItems;
        this._masterItems = masterItems;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<Map.Entry<Long, Object>> vmasterI;
        if (this._masterItems != null) {
            vmasterI = this._masterItems.entrySet().iterator();
        } else {
            Set empty = Collections.emptySet();
            vmasterI = empty.iterator();
        }
        return new Iterator<T>(){
            Long lastI = Long.MIN_VALUE;
            Map.Entry<Long, T> currentBase;
            Map.Entry<Long, T> currentMaster;
            Iterator<Map.Entry<Long, T>> baseI;
            Iterator<Map.Entry<Long, T>> masterI;
            {
                this.baseI = CombinedIterable.this._baseItems.entrySet().iterator();
                this.masterI = vmasterI;
            }

            @Override
            public boolean hasNext() {
                return this.currentBase != null || this.currentMaster != null || this.baseI.hasNext() || this.masterI.hasNext();
            }

            @Override
            public T next() {
                Object val;
                long baseIdx = Long.MAX_VALUE;
                long masterIdx = Long.MAX_VALUE;
                if (this.currentBase == null) {
                    while (this.baseI.hasNext()) {
                        this.currentBase = this.baseI.next();
                        if (this.currentBase.getKey() <= this.lastI) continue;
                        baseIdx = this.currentBase.getKey();
                        break;
                    }
                } else {
                    baseIdx = this.currentBase.getKey();
                }
                if (this.currentMaster == null) {
                    while (this.masterI.hasNext()) {
                        this.currentMaster = this.masterI.next();
                        if (this.currentMaster.getKey() <= this.lastI) continue;
                        masterIdx = this.currentMaster.getKey();
                        break;
                    }
                } else {
                    masterIdx = this.currentMaster.getKey();
                }
                if (this.currentBase != null) {
                    if (baseIdx <= masterIdx) {
                        this.lastI = baseIdx;
                        val = this.currentBase.getValue();
                        if (masterIdx == baseIdx) {
                            this.currentMaster = null;
                        }
                        this.currentBase = null;
                    } else {
                        this.lastI = masterIdx;
                        val = this.currentMaster != null ? this.currentMaster.getValue() : null;
                        this.currentMaster = null;
                    }
                } else if (this.currentMaster != null) {
                    this.lastI = this.currentMaster.getKey();
                    val = this.currentMaster.getValue();
                    this.currentMaster = null;
                } else {
                    throw new NoSuchElementException();
                }
                return val;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

