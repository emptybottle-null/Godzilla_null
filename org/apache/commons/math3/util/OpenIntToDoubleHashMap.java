/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.util.FastMath;

public class OpenIntToDoubleHashMap
implements Serializable {
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    protected static final byte REMOVED = 2;
    private static final long serialVersionUID = -3646337053166149105L;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final int PERTURB_SHIFT = 5;
    private int[] keys;
    private double[] values;
    private byte[] states;
    private final double missingEntries;
    private int size;
    private int mask;
    private transient int count;

    public OpenIntToDoubleHashMap() {
        this(16, Double.NaN);
    }

    public OpenIntToDoubleHashMap(double missingEntries) {
        this(16, missingEntries);
    }

    public OpenIntToDoubleHashMap(int expectedSize) {
        this(expectedSize, Double.NaN);
    }

    public OpenIntToDoubleHashMap(int expectedSize, double missingEntries) {
        int capacity = OpenIntToDoubleHashMap.computeCapacity(expectedSize);
        this.keys = new int[capacity];
        this.values = new double[capacity];
        this.states = new byte[capacity];
        this.missingEntries = missingEntries;
        this.mask = capacity - 1;
    }

    public OpenIntToDoubleHashMap(OpenIntToDoubleHashMap source) {
        int length = source.keys.length;
        this.keys = new int[length];
        System.arraycopy(source.keys, 0, this.keys, 0, length);
        this.values = new double[length];
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
        return OpenIntToDoubleHashMap.nextPowerOfTwo(capacity);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    public double get(int key) {
        int hash = OpenIntToDoubleHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return this.values[index];
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = OpenIntToDoubleHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToDoubleHashMap.probe(perturb, j)) & this.mask;
            if (this.containsKey(key, index)) {
                return this.values[index];
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    public boolean containsKey(int key) {
        int hash = OpenIntToDoubleHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return true;
        }
        if (this.states[index] == 0) {
            return false;
        }
        int j = index;
        int perturb = OpenIntToDoubleHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToDoubleHashMap.probe(perturb, j)) & this.mask;
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
        return OpenIntToDoubleHashMap.findInsertionIndex(this.keys, this.states, key, this.mask);
    }

    private static int findInsertionIndex(int[] keys, byte[] states, int key, int mask) {
        int hash = OpenIntToDoubleHashMap.hashOf(key);
        int index = hash & mask;
        if (states[index] == 0) {
            return index;
        }
        if (states[index] == 1 && keys[index] == key) {
            return OpenIntToDoubleHashMap.changeIndexSign(index);
        }
        int perturb = OpenIntToDoubleHashMap.perturb(hash);
        int j = index;
        if (states[index] == 1) {
            do {
                j = OpenIntToDoubleHashMap.probe(perturb, j);
                index = j & mask;
                perturb >>= 5;
            } while (states[index] == 1 && keys[index] != key);
        }
        if (states[index] == 0) {
            return index;
        }
        if (states[index] == 1) {
            return OpenIntToDoubleHashMap.changeIndexSign(index);
        }
        int firstRemoved = index;
        while (states[index = (j = OpenIntToDoubleHashMap.probe(perturb, j)) & mask] != 0) {
            if (states[index] == 1 && keys[index] == key) {
                return OpenIntToDoubleHashMap.changeIndexSign(index);
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

    public double remove(int key) {
        int hash = OpenIntToDoubleHashMap.hashOf(key);
        int index = hash & this.mask;
        if (this.containsKey(key, index)) {
            return this.doRemove(index);
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = OpenIntToDoubleHashMap.perturb(hash);
        while (this.states[index] != 0) {
            index = (j = OpenIntToDoubleHashMap.probe(perturb, j)) & this.mask;
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

    private double doRemove(int index) {
        this.keys[index] = 0;
        this.states[index] = 2;
        double previous = this.values[index];
        this.values[index] = this.missingEntries;
        --this.size;
        ++this.count;
        return previous;
    }

    public double put(int key, double value) {
        int index = this.findInsertionIndex(key);
        double previous = this.missingEntries;
        boolean newMapping = true;
        if (index < 0) {
            index = OpenIntToDoubleHashMap.changeIndexSign(index);
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
        double[] oldValues = this.values;
        byte[] oldStates = this.states;
        int newLength = 2 * oldLength;
        int[] newKeys = new int[newLength];
        double[] newValues = new double[newLength];
        byte[] newStates = new byte[newLength];
        int newMask = newLength - 1;
        for (int i = 0; i < oldLength; ++i) {
            if (oldStates[i] != 1) continue;
            int key = oldKeys[i];
            int index = OpenIntToDoubleHashMap.findInsertionIndex(newKeys, newStates, key, newMask);
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

    public class Iterator {
        private final int referenceCount;
        private int current;
        private int next;

        private Iterator() {
            this.referenceCount = OpenIntToDoubleHashMap.this.count;
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
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current < 0) {
                throw new NoSuchElementException();
            }
            return OpenIntToDoubleHashMap.this.keys[this.current];
        }

        public double value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current < 0) {
                throw new NoSuchElementException();
            }
            return OpenIntToDoubleHashMap.this.values[this.current];
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            block4: {
                if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                    throw new ConcurrentModificationException();
                }
                this.current = this.next;
                try {
                    while (OpenIntToDoubleHashMap.this.states[++this.next] != 1) {
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

