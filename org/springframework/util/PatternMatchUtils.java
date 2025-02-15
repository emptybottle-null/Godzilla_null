/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import org.springframework.lang.Nullable;

public abstract class PatternMatchUtils {
    public static boolean simpleMatch(@Nullable String pattern, @Nullable String str) {
        if (pattern == null || str == null) {
            return false;
        }
        int firstIndex = pattern.indexOf(42);
        if (firstIndex == -1) {
            return pattern.equals(str);
        }
        if (firstIndex == 0) {
            if (pattern.length() == 1) {
                return true;
            }
            int nextIndex = pattern.indexOf(42, 1);
            if (nextIndex == -1) {
                return str.endsWith(pattern.substring(1));
            }
            String part = pattern.substring(1, nextIndex);
            if (part.isEmpty()) {
                return PatternMatchUtils.simpleMatch(pattern.substring(nextIndex), str);
            }
            int partIndex = str.indexOf(part);
            while (partIndex != -1) {
                if (PatternMatchUtils.simpleMatch(pattern.substring(nextIndex), str.substring(partIndex + part.length()))) {
                    return true;
                }
                partIndex = str.indexOf(part, partIndex + 1);
            }
            return false;
        }
        return str.length() >= firstIndex && pattern.startsWith(str.substring(0, firstIndex)) && PatternMatchUtils.simpleMatch(pattern.substring(firstIndex), str.substring(firstIndex));
    }

    public static boolean simpleMatch(@Nullable String[] patterns, String str) {
        if (patterns != null) {
            for (String pattern : patterns) {
                if (!PatternMatchUtils.simpleMatch(pattern, str)) continue;
                return true;
            }
        }
        return false;
    }
}

