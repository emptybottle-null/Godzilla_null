/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.util.Objects;
import org.apache.commons.io.FilenameUtils;

public enum IOCase {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());

    private static final long serialVersionUID = -6343169151696340687L;
    private final String name;
    private final transient boolean sensitive;

    public static boolean isCaseSensitive(IOCase caseSensitivity) {
        return caseSensitivity != null && !caseSensitivity.isCaseSensitive();
    }

    public static IOCase forName(String name) {
        for (IOCase ioCase : IOCase.values()) {
            if (!ioCase.getName().equals(name)) continue;
            return ioCase;
        }
        throw new IllegalArgumentException("Invalid IOCase name: " + name);
    }

    private IOCase(String name, boolean sensitive) {
        this.name = name;
        this.sensitive = sensitive;
    }

    private Object readResolve() {
        return IOCase.forName(this.name);
    }

    public String getName() {
        return this.name;
    }

    public boolean isCaseSensitive() {
        return this.sensitive;
    }

    public int checkCompareTo(String str1, String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str1.compareTo(str2) : str1.compareToIgnoreCase(str2);
    }

    public boolean checkEquals(String str1, String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str1.equals(str2) : str1.equalsIgnoreCase(str2);
    }

    public boolean checkStartsWith(String str, String start) {
        return str != null && start != null && str.regionMatches(!this.sensitive, 0, start, 0, start.length());
    }

    public boolean checkEndsWith(String str, String end) {
        if (str == null || end == null) {
            return false;
        }
        int endLen = end.length();
        return str.regionMatches(!this.sensitive, str.length() - endLen, end, 0, endLen);
    }

    public int checkIndexOf(String str, int strStartIndex, String search) {
        int endIndex = str.length() - search.length();
        if (endIndex >= strStartIndex) {
            for (int i = strStartIndex; i <= endIndex; ++i) {
                if (!this.checkRegionMatches(str, i, search)) continue;
                return i;
            }
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int strStartIndex, String search) {
        return str.regionMatches(!this.sensitive, strStartIndex, search, 0, search.length());
    }

    public String toString() {
        return this.name;
    }
}

