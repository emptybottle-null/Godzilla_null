/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OpenIntToFieldHashMap<T extends FieldElement<T>>
implements Serializable {
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    protected static final byte REMOVED = 2;
    private static final long serialVersionUID = -9179080286849120720L;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final int PERTURB_SHIFT = 5;
    private final Field<T> field;
    private int[] keys;
    private T[] values;
    private byte[] states;
    private final T missingEntries;
    private int size;
    private int mask;
    private transient int count;

    public OpenIntToFieldHashMap(Field<T> field) {
        this(field, 16, (FieldElement)field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, T missingEntries) {
        this(field, 16, missingEntries);
    }

    public OpenIntToFieldHashMap(Field<T> field, int expectedSize) {
        this(field, expectedSize, (FieldElement)field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, int expectedSize, T missingEntries) {
        this.field = field;
        int capacity = OpenIntToFieldHashMap.computeCapacity(expectedSize);
        this.keys = new int[capacity];
        this.values = this.buildArray(capacity);
        this.states = new byte[capacity];
        this.missingEntries = missingEntries;
        this.mask = capacity - 1;
    }

    public OpenIntToFieldHashMap(OpenIntToFieldHashMap<T> source) {
        this.field = source.field;
        int length = source.keys.length;
        this.keys = new int[length];
        System.arraycopy(source.keys, 0, this.keys, 0, length);
        this.values = this.buildArray(length);
        System.arraycopy(source.values, 0, this.values, 0, length);
        this.states = new byte[length];
        System.arraycopy(source.states, 0, this.states, 0, length);
        this.missingEntries = source.missingEntries;
        this.size = source.size;
        this.mask = source.mask;
        this.count = source.count;
    }

    private static int computeCapacity(int expectedSize) {
        if (expectedSize == 0) {
            return 1;
        }
        int capacity = (int)FastMath.ceil((float)expectedSize / 0.5f);
        int powerOfTwo = Integer.highestOneBit(capacity);
        if (powerOfTwo == capacity) {
            return capacity;
        }
        return OpenIntToFieldHashMap.nextPowerOfTwo(capacity);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    public T get(int key) {
        int hash = OpenIntToFieldHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return this.values[index];
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = OpenIntToFieldHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToFieldHashMap.probe(perturb, j)) & this.mask;
            if (this.containsKey(key, index)) {
                return this.values[index];
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    public boolean containsKey(int key) {
        int hash = OpenIntToFieldHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return true;
        }
        if (this.states[index] == 0) {
            return false;
        }
        int j = index;
        int perturb = OpenIntToFieldHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToFieldHashMap.probe(perturb, j)) & this.mask;
            if (this.containsKey(key, index)) {
                return true;
            }
            perturb >>= 5;
        }
        return false;
    }

    public Iterator iterator() {
        return new Iterator();
    }

    private static int perturb(int hash) {
        return hash & Integer.MAX_VALUE;
    }

    private int findInsertionIndex(int key) {
        return OpenIntToFieldHashMap.findInsertionIndex(this.keys, this.states, key, this.mask);
    }

    private static int findInsertionIndex(int[] keys, byte[] states, int key, int mask) {
        int hash = OpenIntToFieldHashMap.hashOf(key);
        int index = hash & mask;
        if (states[index] == 0) {
            return index;
        }
        if (states[index] == 1 && keys[index] == key) {
            return OpenIntToFieldHashMap.changeIndexSign(index);
        }
        int perturb = OpenIntToFieldHashMap.perturb(hash);
        int j = index;
        if (states[index] == 1) {
            do {
                j = OpenIntToFieldHashMap.probe(perturb, j);
                index = j & mask;
                perturb >>= 5;
            } while (states[index] == 1 && keys[index] != key);
        }
        if (states[index] == 0) {
            return index;
        }
        if (states[index] == 1) {
            return OpenIntToFieldHashMap.changeIndexSign(index);
        }
        int firstRemoved = index;
        while (states[index = (j = OpenIntToFieldHashMap.probe(perturb, j)) & mask] != 0) {
            if (states[index] == 1 && keys[index] == key) {
                return OpenIntToFieldHashMap.changeIndexSign(index);
            }
            perturb >>= 5;
        }
        return firstRemoved;
    }

    private static int probe(int perturb, int j) {
        return (j << 2) + j + perturb + 1;
    }

    private static int changeIndexSign(int index) {
        return -index - 1;
    }

    public int size() {
        return this.size;
    }

    public T remove(int key) {
        int hash = OpenIntToFieldHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return this.doRemove(index);
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = OpenIntToFieldHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToFieldHashMap.probe(perturb, j)) & this.mask;
            if (this.containsKey(key, index)) {
                return this.doRemove(index);
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    private boolean containsKey(int key, int index) {
        return (key != 0 || this.states[index] == 1) && this.keys[index] == key;
    }

    private T doRemove(int index) {
        this.keys[index] = 0;
        this.states[index] = 2;
        T previous = this.values[index];
        this.values[index] = this.missingEntries;
        --this.size;
        ++this.count;
        return previous;
    }

    public T put(int key, T value) {
        int index = this.findInsertionIndex(key);
        T previous = this.missingEntries;
        boolean newMapping = true;
        if (index < 0) {
            index = OpenIntToFieldHashMap.changeIndexSign(index);
            previous = this.values[index];
            newMapping = false;
        }
        this.keys[index] = key;
        this.states[index] = 1;
        this.values[index] = value;
        if (newMapping) {
            ++this.size;
            if (this.shouldGrowTable()) {
                this.growTable();
            }
            ++this.count;
        }
        return previous;
    }

    private void growTable() {
        int oldLength = this.states.length;
        int[] oldKeys = this.keys;
        T[] oldValues = this.values;
        byte[] oldStates = this.states;
        int newLength = 2 * oldLength;
        int[] newKeys = new int[newLength];
        FieldElement[] newValues = this.buildArray(newLength);
        byte[] newStates = new byte[newLength];
        int newMask = newLength - 1;
        for (int i = 0; i < oldLength; ++i) {
            if (oldStates[i] != 1) continue;
            int key = oldKeys[i];
            int index = OpenIntToFieldHashMap.findInsertionIndex(newKeys, newStates, key, newMask);
            newKeys[index] = key;
            newValues[index] = oldValues[i];
            newStates[index] = 1;
        }
        this.mask = newMask;
        this.keys = newKeys;
        this.values = newValues;
        this.states = newStates;
    }

    private boolean shouldGrowTable() {
        return (float)this.size > (float)(this.mask + 1) * 0.5f;
    }

    private static int hashOf(int key) {
        int h = key ^ (key >>> 20 ^ key >>> 12);
        return h ^ h >>> 7 ^ h >>> 4;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.count = 0;
    }

    private T[] buildArray(int length) {
        return (FieldElement[])Array.newInstance(this.field.getRuntimeClass(), length);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class Iterator {
        private final int referenceCount;
        private int current;
        private int next;

        private Iterator() {
            this.referenceCount = OpenIntToFieldHashMap.this.count;
            this.next = -1;
            try {
                this.advance();
            } catch (NoSuchElementException noSuchElementException) {
                // empty catch block
            }
        }

        public boolean hasNext() {
            return this.next >= 0;
        }

        public int key() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current < 0) {
                throw new NoSuchElementException();
            }
            return OpenIntToFieldHashMap.this.keys[this.current];
        }

        public T value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current < 0) {
                throw new NoSuchElementException();
            }
            return OpenIntToFieldHashMap.this.values[this.current];
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            block4: {
                if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                    throw new ConcurrentModificationException();
                }
                this.current = this.next;
                try {
                    while (OpenIntToFieldHashMap.this.states[++this.next] != 1) {
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.next = -2;
                    if (this.current >= 0) break block4;
                    throw new NoSuchElementException();
                }
            }
        }
    }
}

