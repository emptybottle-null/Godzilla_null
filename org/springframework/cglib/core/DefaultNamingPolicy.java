/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Predicate;

public class DefaultNamingPolicy
implements NamingPolicy {
    public static final DefaultNamingPolicy INSTANCE = new DefaultNamingPolicy();
    private static final boolean STRESS_HASH_CODE = Boolean.getBoolean("org.springframework.cglib.test.stressHashCodes");

    public String getClassName(String prefix, String source, Object key, Predicate names) {
        String base;
        if (prefix == null) {
            prefix = "org.springframework.cglib.empty.Object";
        } else if (prefix.startsWith("java")) {
            prefix = "$" + prefix;
        }
        String attempt = base = prefix + "$$" + source.substring(source.lastIndexOf(46) + 1) + this.getTag() + "$$" + Integer.toHexString(STRESS_HASH_CODE ? 0 : key.hashCode());
        int index = 2;
        while (names.evaluate(attempt)) {
            attempt = base + "_" + index++;
        }
        return attempt;
    }

    protected String getTag() {
        return "ByCGLIB";
    }

    public int hashCode() {
        return this.getTag().hashCode();
    }

    public boolean equals(Object o) {
        return o instanceof DefaultNamingPolicy && ((DefaultNamingPolicy)o).getTag().equals(this.getTag());
    }
}

