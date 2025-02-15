/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.serialization;

import java.util.regex.Pattern;
import org.apache.commons.io.serialization.ClassNameMatcher;

final class RegexpClassNameMatcher
implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String regex) {
        this(Pattern.compile(regex));
    }

    public RegexpClassNameMatcher(Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Null pattern");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String className) {
        return this.pattern.matcher(className).matches();
    }
}

