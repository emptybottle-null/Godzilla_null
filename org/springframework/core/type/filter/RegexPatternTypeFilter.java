/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.filter;

import java.util.regex.Pattern;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.util.Assert;

public class RegexPatternTypeFilter
extends AbstractClassTestingTypeFilter {
    private final Pattern pattern;

    public RegexPatternTypeFilter(Pattern pattern) {
        Assert.notNull((Object)pattern, "Pattern must not be null");
        this.pattern = pattern;
    }

    @Override
    protected boolean match(ClassMetadata metadata) {
        return this.pattern.matcher(metadata.getClassName()).matches();
    }
}

