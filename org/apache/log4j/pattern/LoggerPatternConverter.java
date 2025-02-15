/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.NamePatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public final class LoggerPatternConverter
extends NamePatternConverter {
    private static final LoggerPatternConverter INSTANCE = new LoggerPatternConverter(null);

    private LoggerPatternConverter(String[] options) {
        super("Logger", "logger", options);
    }

    public static LoggerPatternConverter newInstance(String[] options) {
        if (options == null || options.length == 0) {
            return INSTANCE;
        }
        return new LoggerPatternConverter(options);
    }

    public void format(LoggingEvent event, StringBuffer toAppendTo) {
        int initialLength = toAppendTo.length();
        toAppendTo.append(event.getLoggerName());
        this.abbreviate(initialLength, toAppendTo);
    }
}

