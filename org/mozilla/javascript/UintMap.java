/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.mozilla.javascript.Kit;

public class UintMap
implements Serializable {
    static final long serialVersionUID = 4242698212885848444L;
    private static final int A = -1640531527;
    private static final int EMPTY = -1;
    private static final int DELETED = -2;
    private transient int[] keys;
    private transient Object[] values;
    private int power;
    private int keyCount;
    private transient int occupiedCount;
    private transient int ivaluesShift;
    private static final boolean check = false;

    public UintMap() {
        this(4);
    }

    public UintMap(int initialCapacity) {
        if (initialCapacity < 0) {
            Kit.codeBug();
        }
        int minimalCapacity = initialCapacity * 4 / 3;
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

    public boolean has(int key) {
        if (key < 0) {
            Kit.codeBug();
        }
        return 0 <= this.findIndex(key);
    }

    public Object getObject(int key) {
        int index;
        if (key < 0) {
            Kit.codeBug();
        }
        if (this.values != null && 0 <= (index = this.findIndex(key))) {
            return this.values[index];
        }
        return null;
    }

    public int getInt(int key, int defaultValue) {
        int index;
        if (key < 0) {
            Kit.codeBug();
        }
        if (0 <= (index = this.findIndex(key))) {
            if (this.ivaluesShift != 0) {
                return this.keys[this.ivaluesShift + index];
            }
            return 0;
        }
        return defaultValue;
    }

    public int getExistingInt(int key) {
        int index;
        if (key < 0) {
            Kit.codeBug();
        }
        if (0 <= (index = this.findIndex(key))) {
            if (this.ivaluesShift != 0) {
                return this.keys[this.ivaluesShift + index];
            }
            return 0;
        }
        Kit.codeBug();
        return 0;
    }

    public void put(int key, Object value) {
        if (key < 0) {
            Kit.codeBug();
        }
        int index = this.ensureIndex(key, false);
        if (this.values == null) {
            this.values = new Object[1 << this.power];
        }
        this.values[index] = value;
    }

    public void put(int key, int value) {
        if (key < 0) {
            Kit.codeBug();
        }
        int index = this.ensureIndex(key, true);
        if (this.ivaluesShift == 0) {
            int N = 1 << this.power;
            if (this.keys.length != N * 2) {
                int[] tmp = new int[N * 2];
                System.arraycopy(this.keys, 0, tmp, 0, N);
                this.keys = tmp;
            }
            this.ivaluesShift = N;
        }
        this.keys[this.ivaluesShift + index] = value;
    }

    public void remove(int key) {
        int index;
        if (key < 0) {
            Kit.codeBug();
        }
        if (0 <= (index = this.findIndex(key))) {
            this.keys[index] = -2;
            --this.keyCount;
            if (this.values != null) {
                this.values[index] = null;
            }
            if (this.ivaluesShift != 0) {
                this.keys[this.ivaluesShift + index] = 0;
            }
        }
    }

    public void clear() {
        int N = 1 << this.power;
        if (this.keys != null) {
            int i;
            for (i = 0; i != N; ++i) {
                this.keys[i] = -1;
            }
            if (this.values != null) {
                for (i = 0; i != N; ++i) {
                    this.values[i] = null;
                }
            }
        }
        this.ivaluesShift = 0;
        this.keyCount = 0;
        this.occupiedCount = 0;
    }

    public int[] getKeys() {
        int[] keys = this.keys;
        int n = this.keyCount;
        int[] result = new int[n];
        int i = 0;
        while (n != 0) {
            int entry = keys[i];
            if (entry != -1 && entry != -2) {
                result[--n] = entry;
            }
            ++i;
        }
        return result;
    }

    private static int tableLookupStep(int fraction, int mask, int power) {
        int shift = 32 - 2 * power;
        if (shift >= 0) {
            return fraction >>> shift & mask | 1;
        }
        return fraction & mask >>> -shift | 1;
    }

    private int findIndex(int key) {
        int[] keys = this.keys;
        if (keys != null) {
            int fraction = key * -1640531527;
            int index = fraction >>> 32 - this.power;
            int entry = keys[index];
            if (entry == key) {
                return index;
            }
            if (entry != -1) {
                int mask = (1 << this.power) - 1;
                int step = UintMap.tableLookupStep(fraction, mask, this.power);
                boolean n = false;
                do {
                    if ((entry = keys[index = index + step & mask]) != key) continue;
                    return index;
                } while (entry != -1);
            }
        }
        return -1;
    }

    private int insertNewKey(int key) {
        int[] keys = this.keys;
        int fraction = key * -1640531527;
        int index = fraction >>> 32 - this.power;
        if (keys[index] != -1) {
            int mask = (1 << this.power) - 1;
            int step = UintMap.tableLookupStep(fraction, mask, this.power);
            int firstIndex = index;
            while (keys[index = index + step & mask] != -1) {
            }
        }
        keys[index] = key;
        ++this.occupiedCount;
        ++this.keyCount;
        return index;
    }

    private void rehashTable(boolean ensureIntSpace) {
        if (this.keys != null && this.keyCount * 2 >= this.occupiedCount) {
            ++this.power;
        }
        int N = 1 << this.power;
        int[] old = this.keys;
        int oldShift = this.ivaluesShift;
        if (oldShift == 0 && !ensureIntSpace) {
            this.keys = new int[N];
        } else {
            this.ivaluesShift = N;
            this.keys = new int[N * 2];
        }
        for (int i = 0; i != N; ++i) {
            this.keys[i] = -1;
        }
        Object[] oldValues = this.values;
        if (oldValues != null) {
            this.values = new Object[N];
        }
        int oldCount = this.keyCount;
        this.occupiedCount = 0;
        if (oldCount != 0) {
            this.keyCount = 0;
            int i = 0;
            int remaining = oldCount;
            while (remaining != 0) {
                int key = old[i];
                if (key != -1 && key != -2) {
                    int index = this.insertNewKey(key);
                    if (oldValues != null) {
                        this.values[index] = oldValues[i];
                    }
                    if (oldShift != 0) {
                        this.keys[this.ivaluesShift + index] = old[oldShift + i];
                    }
                    --remaining;
                }
                ++i;
            }
        }
    }

    private int ensureIndex(int key, boolean intType) {
        int index = -1;
        int firstDeleted = -1;
        int[] keys = this.keys;
        if (keys != null) {
            int fraction = key * -1640531527;
            index = fraction >>> 32 - this.power;
            int entry = keys[index];
            if (entry == key) {
                return index;
            }
            if (entry != -1) {
                if (entry == -2) {
                    firstDeleted = index;
                }
                int mask = (1 << this.power) - 1;
                int step = UintMap.tableLookupStep(fraction, mask, this.power);
                boolean n = false;
                do {
                    if ((entry = keys[index = index + step & mask]) == key) {
                        return index;
                    }
                    if (entry != -2 || firstDeleted >= 0) continue;
                    firstDeleted = index;
                } while (entry != -1);
            }
        }
        if (firstDeleted >= 0) {
            index = firstDeleted;
        } else {
            if (keys == null || this.occupiedCount * 4 >= (1 << this.power) * 3) {
                this.rehashTable(intType);
                return this.insertNewKey(key);
            }
            ++this.occupiedCount;
        }
        keys[index] = key;
        ++this.keyCount;
        return index;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        int count = this.keyCount;
        if (count != 0) {
            boolean hasIntValues = this.ivaluesShift != 0;
            boolean hasObjectValues = this.values != null;
            out.writeBoolean(hasIntValues);
            out.writeBoolean(hasObjectValues);
            int i = 0;
            while (count != 0) {
                int key = this.keys[i];
                if (key != -1 && key != -2) {
                    --count;
                    out.writeInt(key);
                    if (hasIntValues) {
                        out.writeInt(this.keys[this.ivaluesShift + i]);
                    }
                    if (hasObjectValues) {
                        out.writeObject(this.values[i]);
                    }
                }
                ++i;
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int writtenKeyCount = this.keyCount;
        if (writtenKeyCount != 0) {
            int i;
            this.keyCount = 0;
            boolean hasIntValues = in.readBoolean();
            boolean hasObjectValues = in.readBoolean();
            int N = 1 << this.power;
            if (hasIntValues) {
                this.keys = new int[2 * N];
                this.ivaluesShift = N;
            } else {
                this.keys = new int[N];
            }
            for (i = 0; i != N; ++i) {
                this.keys[i] = -1;
            }
            if (hasObjectValues) {
                this.values = new Object[N];
            }
            for (i = 0; i != writtenKeyCount; ++i) {
                int key = in.readInt();
                int index = this.insertNewKey(key);
                if (hasIntValues) {
                    int ivalue;
                    this.keys[this.ivaluesShift + index] = ivalue = in.readInt();
                }
                if (!hasObjectValues) continue;
                this.values[index] = in.readObject();
            }
        }
    }
}

