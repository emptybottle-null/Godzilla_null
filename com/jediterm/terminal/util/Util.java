/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.util;

import java.lang.reflect.Array;
import java.util.BitSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Util {
    public static <T> T[] copyOf(T[] original, int newLength) {
        Class<?> type = original.getClass().getComponentType();
        Object[] newArr = (Object[])Array.newInstance(type, newLength);
        System.arraycopy(original, 0, newArr, 0, Math.min(original.length, newLength));
        return newArr;
    }

    public static int[] copyOf(int[] original, int newLength) {
        int[] newArr = new int[newLength];
        System.arraycopy(original, 0, newArr, 0, Math.min(original.length, newLength));
        return newArr;
    }

    public static char[] copyOf(char[] original, int newLength) {
        char[] newArr = new char[newLength];
        System.arraycopy(original, 0, newArr, 0, Math.min(original.length, newLength));
        return newArr;
    }

    public static void bitsetCopy(BitSet src, int srcOffset, BitSet dest, int destOffset, int length) {
        for (int i = 0; i < length; ++i) {
            dest.set(destOffset + i, src.get(srcOffset + i));
        }
    }

    public static String trimTrailing(String string) {
        int index;
        for (index = string.length() - 1; index >= 0 && Character.isWhitespace(string.charAt(index)); --index) {
        }
        return string.substring(0, index + 1);
    }

    public static boolean containsIgnoreCase(@NotNull String where, @NotNull String what) {
        if (where == null) {
            Util.$$$reportNull$$$0(0);
        }
        if (what == null) {
            Util.$$$reportNull$$$0(1);
        }
        return Util.indexOfIgnoreCase(where, what, 0) >= 0;
    }

    public static int indexOfIgnoreCase(@NotNull String where, @NotNull String what, int fromIndex) {
        if (where == null) {
            Util.$$$reportNull$$$0(2);
        }
        if (what == null) {
            Util.$$$reportNull$$$0(3);
        }
        int targetCount = what.length();
        int sourceCount = where.length();
        if (fromIndex >= sourceCount) {
            return targetCount == 0 ? sourceCount : -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }
        char first = what.charAt(0);
        int max = sourceCount - targetCount;
        for (int i = fromIndex; i <= max; ++i) {
            if (!Util.charsEqualIgnoreCase(where.charAt(i), first)) {
                while (++i <= max && !Util.charsEqualIgnoreCase(where.charAt(i), first)) {
                }
            }
            if (i > max) continue;
            int j = i + 1;
            int end = j + targetCount - 1;
            int k = 1;
            while (j < end && Util.charsEqualIgnoreCase(where.charAt(j), what.charAt(k))) {
                ++j;
                ++k;
            }
            if (j != end) continue;
            return i;
        }
        return -1;
    }

    public static boolean charsEqualIgnoreCase(char a, char b) {
        return a == b || Util.toUpperCase(a) == Util.toUpperCase(b) || Util.toLowerCase(a) == Util.toLowerCase(b);
    }

    private static char toLowerCase(char b) {
        return Character.toLowerCase(b);
    }

    private static char toUpperCase(char a) {
        return Character.toUpperCase(a);
    }

    public static int compareVersionNumbers(@Nullable String v1, @Nullable String v2) {
        int idx;
        if (v1 == null && v2 == null) {
            return 0;
        }
        if (v1 == null) {
            return -1;
        }
        if (v2 == null) {
            return 1;
        }
        String[] part1 = v1.split("[\\.\\_\\-]");
        String[] part2 = v2.split("[\\.\\_\\-]");
        for (idx = 0; idx < part1.length && idx < part2.length; ++idx) {
            String p1 = part1[idx];
            String p2 = part2[idx];
            int cmp = p1.matches("\\d+") && p2.matches("\\d+") ? new Integer(p1).compareTo(new Integer(p2)) : part1[idx].compareTo(part2[idx]);
            if (cmp == 0) continue;
            return cmp;
        }
        if (part1.length == part2.length) {
            return 0;
        }
        if (part1.length > idx) {
            return 1;
        }
        return -1;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "where";
                break;
            }
            case 1: 
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "what";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/util/Util";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "containsIgnoreCase";
                break;
            }
            case 2: 
            case 3: {
                objectArray = objectArray2;
                objectArray2[2] = "indexOfIgnoreCase";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

