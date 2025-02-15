/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.pattern.NameAbbreviator;

public abstract class NamePatternConverter
extends LoggingEventPatternConverter {
    private final NameAbbreviator abbreviator;

    protected NamePatternConverter(String name, String style, String[] options) {
        super(name, style);
        this.abbreviator = options != null && options.length > 0 ? NameAbbreviator.getAbbreviator(options[0]) : NameAbbreviator.getDefaultAbbreviator();
    }

    protected final void abbreviate(int nameStart, StringBuffer buf) {
        this.abbreviator.abbreviate(nameStart, buf);
    }
}

