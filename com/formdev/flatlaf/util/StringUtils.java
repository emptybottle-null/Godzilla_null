/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String removeLeading(String string, String leading) {
        return string.startsWith(leading) ? string.substring(leading.length()) : string;
    }

    public static String removeTrailing(String string, String trailing) {
        return string.endsWith(trailing) ? string.substring(0, string.length() - trailing.length()) : string;
    }

    public static List<String> split(String str, char delim) {
        ArrayList<String> strs = new ArrayList<String>();
        int delimIndex = str.indexOf(delim);
        int index = 0;
        while (delimIndex >= 0) {
            strs.add(str.substring(index, delimIndex));
            index = delimIndex + 1;
            delimIndex = str.indexOf(delim, index);
        }
        strs.add(str.substring(index));
        return strs;
    }
}

