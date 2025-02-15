/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringEnumAbstractBase
implements Serializable {
    private static final long serialVersionUID = 1L;
    private String _string;
    private int _int;

    protected StringEnumAbstractBase(String s, int i) {
        this._string = s;
        this._int = i;
    }

    public final String toString() {
        return this._string;
    }

    public final int intValue() {
        return this._int;
    }

    public final int hashCode() {
        return this._string.hashCode();
    }

    public static final class Table {
        private Map _map;
        private List _list;

        public Table(StringEnumAbstractBase[] array) {
            this._map = new HashMap(array.length);
            this._list = new ArrayList(array.length + 1);
            for (int i = 0; i < array.length; ++i) {
                this._map.put(array[i].toString(), array[i]);
                int j = array[i].intValue();
                while (this._list.size() <= j) {
                    this._list.add(null);
                }
                this._list.set(j, array[i]);
            }
        }

        public StringEnumAbstractBase forString(String s) {
            return (StringEnumAbstractBase)this._map.get(s);
        }

        public StringEnumAbstractBase forInt(int i) {
            if (i < 0 || i > this._list.size()) {
                return null;
            }
            return (StringEnumAbstractBase)this._list.get(i);
        }

        public int lastInt() {
            return this._list.size() - 1;
        }
    }
}

