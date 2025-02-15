/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.Comparator;
import java.util.Map;

public interface PathMatcher {
    public boolean isPattern(String var1);

    public boolean match(String var1, String var2);

    public boolean matchStart(String var1, String var2);

    public String extractPathWithinPattern(String var1, String var2);

    public Map<String, String> extractUriTemplateVariables(String var1, String var2);

    public Comparator<String> getPatternComparator(String var1);

    public String combine(String var1, String var2);
}

