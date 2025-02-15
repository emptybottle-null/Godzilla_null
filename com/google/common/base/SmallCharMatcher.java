/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;

@GwtIncompatible
final class SmallCharMatcher
extends CharMatcher.NamedFastMatcher {
    static final int MAX_SIZE = 1023;
    private final char[] table;
    private final boolean containsZero;
    private final long filter;
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5;

    private SmallCharMatcher(char[] table, long filter, boolean containsZero, String description) {
        super(description);
        this.table = table;
        this.filter = filter;
        this.containsZero = containsZero;
    }

    static int smear(int hashCode) {
        return 461845907 * Integer.rotateLeft(hashCode * -862048943, 15);
    }

    private boolean checkFilter(int c) {
        return 1L == (1L & this.filter >> c);
    }

    @VisibleForTesting
    static int chooseTableSize(int setSize) {
        if (setSize == 1) {
            return 2;
        }
        int tableSize = Integer.highestOneBit(setSize - 1) << 1;
        while ((double)tableSize * 0.5 < (double)setSize) {
            tableSize <<= 1;
        }
        return tableSize;
    }

    static CharMatcher from(BitSet chars, String description) {
        long filter = 0L;
        int size = chars.cardinality();
        boolean containsZero = chars.get(0);
        char[] table = new char[SmallCharMatcher.chooseTableSize(size)];
        int mask = table.length - 1;
        int c = chars.nextSetBit(0);
        while (c != -1) {
            filter |= 1L << c;
            int index = SmallCharMatcher.smear(c) & mask;
            while (true) {
                if (table[index] == '\u0000') break;
                index = index + 1 & mask;
            }
            table[index] = (char)c;
            c = chars.nextSetBit(c + 1);
        }
        return new SmallCharMatcher(table, filter, containsZero, description);
    }

    @Override
    public boolean matches(char c) {
        int startingIndex;
        if (c == '\u0000') {
            return this.containsZero;
        }
        if (!this.checkFilter(c)) {
            return false;
        }
        int mask = this.table.length - 1;
        int index = startingIndex = SmallCharMatcher.smear(c) & mask;
        do {
            if (this.table[index] == '\u0000') {
                return false;
            }
            if (this.table[index] != c) continue;
            return true;
        } while ((index = index + 1 & mask) != startingIndex);
        return false;
    }

    @Override
    void setBits(BitSet table) {
        if (this.containsZero) {
            table.set(0);
        }
        for (char c : this.table) {
            if (c == '\u0000') continue;
            table.set(c);
        }
    }
}

