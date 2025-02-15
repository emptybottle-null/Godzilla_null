/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public final class LevelPatternConverter
extends LoggingEventPatternConverter {
    private static final int TRACE_INT = 5000;
    private static final LevelPatternConverter INSTANCE = new LevelPatternConverter();

    private LevelPatternConverter() {
        super("Level", "level");
    }

    public static LevelPatternConverter newInstance(String[] options) {
        return INSTANCE;
    }

    public void format(LoggingEvent event, StringBuffer output) {
        output.append(event.getLevel().toString());
    }

    public String getStyleClass(Object e) {
        if (e instanceof LoggingEvent) {
            int lint = ((LoggingEvent)e).getLevel().toInt();
            switch (lint) {
                case 5000: {
                    return "level trace";
                }
                case 10000: {
                    return "level debug";
                }
                case 20000: {
                    return "level info";
                }
                case 30000: {
                    return "level warn";
                }
                case 40000: {
                    return "level error";
                }
                case 50000: {
                    return "level fatal";
                }
            }
            return "level " + ((LoggingEvent)e).getLevel().toString();
        }
        return "level";
    }
}

