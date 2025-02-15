/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

public final class RKUtil {
    private RKUtil() {
    }

    public static double decodeNumber(int number) {
        long raw_number = number;
        double rvalue = 0.0;
        rvalue = (number & 2) == 2 ? (double)raw_number : Double.longBitsToDouble((raw_number >>= 2) << 34);
        if ((number & 1) == 1) {
            rvalue /= 100.0;
        }
        return rvalue;
    }
}

