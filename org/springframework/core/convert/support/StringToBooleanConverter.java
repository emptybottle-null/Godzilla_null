/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.HashSet;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

final class StringToBooleanConverter
implements Converter<String, Boolean> {
    private static final Set<String> trueValues = new HashSet<String>(8);
    private static final Set<String> falseValues = new HashSet<String>(8);

    StringToBooleanConverter() {
    }

    @Override
    @Nullable
    public Boolean convert(String source) {
        String value = source.trim();
        if (value.isEmpty()) {
            return null;
        }
        if (trueValues.contains(value = value.toLowerCase())) {
            return Boolean.TRUE;
        }
        if (falseValues.contains(value)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
    }

    static {
        trueValues.add("true");
        trueValues.add("on");
        trueValues.add("yes");
        trueValues.add("1");
        falseValues.add("false");
        falseValues.add("off");
        falseValues.add("no");
        falseValues.add("0");
    }
}

