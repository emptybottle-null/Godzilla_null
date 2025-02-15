/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.Comparator;

public abstract class KeyAnalyzer<K>
implements Comparator<K>,
Serializable {
    private static final long serialVersionUID = -20497563720380683L;
    public static final int NULL_BIT_KEY = -1;
    public static final int EQUAL_BIT_KEY = -2;
    public static final int OUT_OF_BOUNDS_BIT_KEY = -3;

    static boolean isOutOfBoundsIndex(int bitIndex) {
        return bitIndex == -3;
    }

    static boolean isEqualBitKey(int bitIndex) {
        return bitIndex == -2;
    }

    static boolean isNullBitKey(int bitIndex) {
        return bitIndex == -1;
    }

    static boolean isValidBitIndex(int bitIndex) {
        return bitIndex >= 0;
    }

    public abstract int bitsPerElement();

    public abstract int lengthInBits(K var1);

    public abstract boolean isBitSet(K var1, int var2, int var3);

    public abstract int bitIndex(K var1, int var2, int var3, K var4, int var5, int var6);

    public abstract boolean isPrefix(K var1, int var2, int var3, K var4);

    @Override
    public int compare(K o1, K o2) {
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        }
        if (o2 == null) {
            return 1;
        }
        return ((Comparable)o1).compareTo(o2);
    }
}

