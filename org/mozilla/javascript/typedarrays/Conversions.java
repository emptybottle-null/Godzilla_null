/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.ScriptRuntime;

public class Conversions {
    public static final int EIGHT_BIT = 256;
    public static final int SIXTEEN_BIT = 65536;
    public static final long THIRTYTWO_BIT = 0x100000000L;

    public static int toInt8(Object arg) {
        int iv = arg instanceof Integer ? (Integer)arg : ScriptRuntime.toInt32(arg);
        int int8Bit = iv % 256;
        return int8Bit >= 128 ? int8Bit - 256 : int8Bit;
    }

    public static int toUint8(Object arg) {
        int iv = arg instanceof Integer ? (Integer)arg : ScriptRuntime.toInt32(arg);
        return iv % 256;
    }

    public static int toUint8Clamp(Object arg) {
        double d = ScriptRuntime.toNumber(arg);
        if (d <= 0.0) {
            return 0;
        }
        if (d >= 255.0) {
            return 255;
        }
        double f = Math.floor(d);
        if (f + 0.5 < d) {
            return (int)(f + 1.0);
        }
        if (d < f + 0.5) {
            return (int)f;
        }
        if ((int)f % 2 != 0) {
            return (int)f + 1;
        }
        return (int)f;
    }

    public static int toInt16(Object arg) {
        int iv = arg instanceof Integer ? (Integer)arg : ScriptRuntime.toInt32(arg);
        int int16Bit = iv % 65536;
        return int16Bit >= 32768 ? int16Bit - 65536 : int16Bit;
    }

    public static int toUint16(Object arg) {
        int iv = arg instanceof Integer ? (Integer)arg : ScriptRuntime.toInt32(arg);
        return iv % 65536;
    }

    public static int toInt32(Object arg) {
        long lv = (long)ScriptRuntime.toNumber(arg);
        long int32Bit = lv % 0x100000000L;
        return (int)(int32Bit >= 0x80000000L ? int32Bit - 0x100000000L : int32Bit);
    }

    public static long toUint32(Object arg) {
        long lv = (long)ScriptRuntime.toNumber(arg);
        return lv % 0x100000000L;
    }
}

