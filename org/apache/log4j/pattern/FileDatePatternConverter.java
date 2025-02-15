/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.DatePatternConverter;
import org.apache.log4j.pattern.PatternConverter;

public final class FileDatePatternConverter {
    private FileDatePatternConverter() {
    }

    public static PatternConverter newInstance(String[] options) {
        if (options == null || options.length == 0) {
            return DatePatternConverter.newInstance(new String[]{"yyyy-MM-dd"});
        }
        return DatePatternConverter.newInstance(options);
    }
}

