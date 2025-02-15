/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

import java.util.ArrayList;
import java.util.List;

public class LazilyConcatenatedByteArray {
    private final List<byte[]> arrays = new ArrayList<byte[]>(1);

    public void clear() {
        this.arrays.clear();
    }

    public void concatenate(byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        this.arrays.add(array);
    }

    public void concatenate(LazilyConcatenatedByteArray other) {
        this.arrays.addAll(other.arrays);
    }

    public byte[] toArray() {
        if (this.arrays.isEmpty()) {
            return null;
        }
        if (this.arrays.size() > 1) {
            int totalLength = 0;
            for (byte[] array : this.arrays) {
                totalLength += array.length;
            }
            byte[] concatenated = new byte[totalLength];
            int destPos = 0;
            for (byte[] array : this.arrays) {
                System.arraycopy(array, 0, concatenated, destPos, array.length);
                destPos += array.length;
            }
            this.arrays.clear();
            this.arrays.add(concatenated);
        }
        return this.arrays.get(0);
    }
}

