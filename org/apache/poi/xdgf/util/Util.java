/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.util;

public class Util {
    public static int countLines(String str) {
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            ++lines;
        }
        return lines;
    }

    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*\"?|<>]", "_");
    }
}

