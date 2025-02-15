/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public final class NDCPatternConverter
extends LoggingEventPatternConverter {
    private static final NDCPatternConverter INSTANCE = new NDCPatternConverter();

    private NDCPatternConverter() {
        super("NDC", "ndc");
    }

    public static NDCPatternConverter newInstance(String[] options) {
        return INSTANCE;
    }

    public void format(LoggingEvent event, StringBuffer toAppendTo) {
        toAppendTo.append(event.getNDC());
    }
}

