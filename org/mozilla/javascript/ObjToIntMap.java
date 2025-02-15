/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.UniqueTag;

public class ObjToIntMap
implements Serializable {
    static final long serialVersionUID = -1542220580748809402L;
    private static final int A = -1640531527;
    private static final Object DELETED = new Object();
    private transient Object[] keys;
    private transient int[] values;
    private int power;
    private int keyCount;
    private transient int occupiedCount;
    private static final boolean check = false;

    public ObjToIntMap() {
        this(4);
    }

    public ObjToIntMap(int keyCountHint) {
        if (keyCountHint < 0) {
            Kit.codeBug();
        }
        int minimalCapacity = keyCountHint * 4 / 3;
        int i = 2;
        while (1 << i < minimalCapacity) {
            ++i;
        }
        this.power = i;
    }

    public boolean isEmpty() {
        return this.keyCount == 0;
    }

    public int size() {
        return this.keyCount;
    }

    public boolean has(Object key) {
        if (key == null) {
            key = UniqueTag.NULL_VALUE;
        }
        return 0 <= this.findIndex(key);
    }

    public int get(Object key, int defaultValue) {
        int index;
        if (key == null) {
            key = UniqueTag.NULL_VALUE;
        }
        if (0 <= (index = this.findIndex(key))) {
            return this.values[index];
        }
        return defaultValue;
    }

    public int getExisting(Object key) {
        int index;
        if (key == null) {
            key = UniqueTag.NULL_VALUE;
        }
        if (0 <= (index = this.findIndex(key))) {
            return this.values[index];
        }
        Kit.codeBug();
        return 0;
    }

    public void put(Object key, int value) {
        if (key == null) {
            key = UniqueTag.NULL_VALUE;
        }
        int index = this.ensureIndex(key);
        this.values[index] = value;
    }

    public Object intern(Object keyArg) {
        boolean nullKey = false;
        if (keyArg == null) {
            nullKey = true;
            keyArg = UniqueTag.NULL_VALUE;
        }
        int index = this.ensureIndex(keyArg);
        this.values[index] = 0;
        return nullKey ? null : this.keys[index];
    }

    public void remove(Object key) {
        int index;
        if (key == null) {
            key = UniqueTag.NULL_VALUE;
        }
        if (0 <= (index = this.findIndex(key))) {
            this.keys[index] = DELETED;
            --this.keyCount;
        }
    }

    public void clear() {
        int i = this.keys.length;
        while (i != 0) {
            this.keys[--i] = null;
        }
        this.keyCount = 0;
        this.occupiedCount = 0;
    }

    public Iterator newIterator() {
        return new Iterator(this);
    }

    final void initIterator(Iterator i) {
        i.init(this.keys, this.values, this.keyCount);
    }

    public Object[] getKeys() {
        Object[] array = new Object[this.keyCount];
        this.getKeys(array, 0);
        return array;
    }

    public void getKeys(Object[] array, int offset) {
        int count = this.keyCount;
        int i = 0;
        while (count != 0) {
            Object key = this.keys[i];
            if (key != null && key != DELETED) {
                if (key == UniqueTag.NULL_VALUE) {
                    key = null;
                }
                array[offset] = key;
                ++offset;
                --count;
            }
            ++i;
        }
    }

    private static int tableLookupStep(int fraction, int mask, int power) {
        int shift = 32 - 2 * power;
        if (shift >= 0) {
            return fraction >>> shift & mask | 1;
        }
        return fraction & mask >>> -shift | 1;
    }

    private int findIndex(Object key) {
        int hash;
        int fraction;
        int index;
        Object test;
        if (this.keys != null && (test = this.keys[index = (fraction = (hash = key.hashCode()) * -1640531527) >>> 32 - this.power]) != null) {
            int N = 1 << this.power;
            if (test == key || this.values[N + index] == hash && test.equals(key)) {
                return index;
            }
            int mask = N - 1;
            int step = ObjToIntMap.tableLookupStep(fraction, mask, this.power);
            boolean n = false;
            while ((test = this.keys[index = index + step & mask]) != null) {
                if (test != key && (this.values[N + index] != hash || !test.equals(key))) continue;
                return index;
            }
        }
        return -1;
    }

    private int insertNewKey(Object key, int hash) {
        int fraction = hash * -1640531527;
        int index = fraction >>> 32 - this.power;
        int N = 1 << this.power;
        if (this.keys[index] != null) {
            int mask = N - 1;
            int step = ObjToIntMap.tableLookupStep(fraction, mask, this.power);
            int firstIndex = index;
            while (this.keys[index = index + step & mask] != null) {
            }
        }
        this.keys[index] = key;
        this.values[N + index] = hash;
        ++this.occupiedCount;
        ++this.keyCount;
        return index;
    }

    private void rehashTable() {
        if (this.keys == null) {
            int N = 1 << this.power;
            this.keys = new Object[N];
            this.values = new int[2 * N];
        } else {
            if (this.keyCount * 2 >= this.occupiedCount) {
                ++this.power;
            }
            int N = 1 << this.power;
            Object[] oldKeys = this.keys;
            int[] oldValues = this.values;
            int oldN = oldKeys.length;
            this.keys = new Object[N];
            this.values = new int[2 * N];
            int remaining = this.keyCount;
            this.keyCount = 0;
            this.occupiedCount = 0;
            int i = 0;
            while (remaining != 0) {
                Object key = oldKeys[i];
                if (key != null && key != DELETED) {
                    int keyHash = oldValues[oldN + i];
                    int index = this.insertNewKey(key, keyHash);
                    this.values[index] = oldValues[i];
                    --remaining;
                }
                ++i;
            }
        }
    }

    private int ensureIndex(Object key) {
        int fraction;
        Object test;
        int hash = key.hashCode();
        int index = -1;
        int firstDeleted = -1;
        if (this.keys != null && (test = this.keys[index = (fraction = hash * -1640531527) >>> 32 - this.power]) != null) {
            int N = 1 << this.power;
            if (test == key || this.values[N + index] == hash && test.equals(key)) {
                return index;
            }
            if (test == DELETED) {
                firstDeleted = index;
            }
            int mask = N - 1;
            int step = ObjToIntMap.tableLookupStep(fraction, mask, this.power);
            boolean n = false;
            while ((test = this.keys[index = index + step & mask]) != null) {
                if (test == key || this.values[N + index] == hash && test.equals(key)) {
                    return index;
                }
                if (test != DELETED || firstDeleted >= 0) continue;
                firstDeleted = index;
            }
        }
        if (firstDeleted >= 0) {
            index = firstDeleted;
        } else {
            if (this.keys == null || this.occupiedCount * 4 >= (1 << this.power) * 3) {
                this.rehashTable();
                return this.insertNewKey(key, hash);
            }
            ++this.occupiedCount;
        }
        this.keys[index] = key;
        this.values[(1 << this.power) + index] = hash;
        ++this.keyCount;
        return index;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        int count = this.keyCount;
        int i = 0;
        while (count != 0) {
            Object key = this.keys[i];
            if (key != null && key != DELETED) {
                --count;
                out.writeObject(key);
                out.writeInt(this.values[i]);
            }
            ++i;
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int writtenKeyCount = this.keyCount;
        if (writtenKeyCount != 0) {
            this.keyCount = 0;
            int N = 1 << this.power;
            this.keys = new Object[N];
            this.values = new int[2 * N];
            for (int i = 0; i != writtenKeyCount; ++i) {
                Object key = in.readObject();
                int hash = key.hashCode();
                int index = this.insertNewKey(key, hash);
                this.values[index] = in.readInt();
            }
        }
    }

    public static class Iterator {
        ObjToIntMap master;
        private int cursor;
        private int remaining;
        private Object[] keys;
        private int[] values;

        Iterator(ObjToIntMap master) {
            this.master = master;
        }

        final void init(Object[] keys, int[] values, int keyCount) {
            this.keys = keys;
            this.values = values;
            this.cursor = -1;
            this.remaining = keyCount;
        }

        public void start() {
            this.master.initIterator(this);
            this.next();
        }

        public boolean done() {
            return this.remaining < 0;
        }

        public void next() {
            if (this.remaining == -1) {
                Kit.codeBug();
            }
            if (this.remaining == 0) {
                this.remaining = -1;
                this.cursor = -1;
            } else {
                ++this.cursor;
                while (true) {
                    Object key;
                    if ((key = this.keys[this.cursor]) != null && key != DELETED) {
                        --this.remaining;
                        break;
                    }
                    ++this.cursor;
                }
            }
        }

        public Object getKey() {
            Object key = this.keys[this.cursor];
            if (key == UniqueTag.NULL_VALUE) {
                key = null;
            }
            return key;
        }

        public int getValue() {
            return this.values[this.cursor];
        }

        public void setValue(int value) {
            this.values[this.cursor] = value;
        }
    }
}

