/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

final class Util {
    Util() {
    }

    static String stripLeadingHyphens(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("--")) {
            return str.substring(2, str.length());
        }
        if (str.startsWith("-")) {
            return str.substring(1, str.length());
        }
        return str;
    }

    static String stripLeadingAndTrailingQuotes(String str) {
        int length = str.length();
        if (length > 1 && str.startsWith("\"") && str.endsWith("\"") && str.substring(1, length - 1).indexOf(34) == -1) {
            str = str.substring(1, length - 1);
        }
        return str;
    }
}

