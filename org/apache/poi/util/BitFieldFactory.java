/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.BitField;

public class BitFieldFactory {
    private static Map<Integer, BitField> instances = new HashMap<Integer, BitField>();

    public static BitField getInstance(int mask) {
        BitField f = instances.get(mask);
        if (f == null) {
            f = new BitField(mask);
            instances.put(mask, f);
        }
        return f;
    }
}

