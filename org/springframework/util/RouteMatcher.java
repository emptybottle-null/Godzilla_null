/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.Comparator;
import java.util.Map;
import org.springframework.lang.Nullable;

public interface RouteMatcher {
    public Route parseRoute(String var1);

    public boolean isPattern(String var1);

    public String combine(String var1, String var2);

    public boolean match(String var1, Route var2);

    @Nullable
    public Map<String, String> matchAndExtract(String var1, Route var2);

    public Comparator<String> getPatternComparator(Route var1);

    public static interface Route {
        public String value();
    }
}

