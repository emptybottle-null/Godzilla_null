/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PermutationIterator<E>
implements Iterator<List<E>> {
    private final int[] keys;
    private final Map<Integer, E> objectMap;
    private final boolean[] direction;
    private List<E> nextPermutation;

    public PermutationIterator(Collection<? extends E> coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        this.keys = new int[coll.size()];
        this.direction = new boolean[coll.size()];
        Arrays.fill(this.direction, false);
        int value = 1;
        this.objectMap = new HashMap<Integer, E>();
        for (E e : coll) {
            this.objectMap.put(value, e);
            this.keys[value - 1] = value;
            ++value;
        }
        this.nextPermutation = new ArrayList<E>(coll);
    }

    @Override
    public boolean hasNext() {
        return this.nextPermutation != null;
    }

    @Override
    public List<E> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int indexOfLargestMobileInteger = -1;
        int largestKey = -1;
        for (int i = 0; i < this.keys.length; ++i) {
            if ((!this.direction[i] || i >= this.keys.length - 1 || this.keys[i] <= this.keys[i + 1]) && (this.direction[i] || i <= 0 || this.keys[i] <= this.keys[i - 1]) || this.keys[i] <= largestKey) continue;
            largestKey = this.keys[i];
            indexOfLargestMobileInteger = i;
        }
        if (largestKey == -1) {
            List<E> toReturn = this.nextPermutation;
            this.nextPermutation = null;
            return toReturn;
        }
        int offset = this.direction[indexOfLargestMobileInteger] ? 1 : -1;
        int tmpKey = this.keys[indexOfLargestMobileInteger];
        this.keys[indexOfLargestMobileInteger] = this.keys[indexOfLargestMobileInteger + offset];
        this.keys[indexOfLargestMobileInteger + offset] = tmpKey;
        boolean tmpDirection = this.direction[indexOfLargestMobileInteger];
        this.direction[indexOfLargestMobileInteger] = this.direction[indexOfLargestMobileInteger + offset];
        this.direction[indexOfLargestMobileInteger + offset] = tmpDirection;
        ArrayList<E> nextP = new ArrayList<E>();
        for (int i = 0; i < this.keys.length; ++i) {
            if (this.keys[i] > largestKey) {
                this.direction[i] = !this.direction[i];
            }
            nextP.add(this.objectMap.get(this.keys[i]));
        }
        List<E> result = this.nextPermutation;
        this.nextPermutation = nextP;
        return result;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}

