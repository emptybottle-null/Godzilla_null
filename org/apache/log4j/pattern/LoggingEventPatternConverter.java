/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import org.apache.log4j.pattern.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public abstract class LoggingEventPatternConverter
extends PatternConverter {
    protected LoggingEventPatternConverter(String name, String style) {
        super(name, style);
    }

    public abstract void format(LoggingEvent var1, StringBuffer var2);

    public void format(Object obj, StringBuffer output) {
        if (obj instanceof LoggingEvent) {
            this.format((LoggingEvent)obj, output);
        }
    }

    public boolean handlesThrowable() {
        return false;
    }
}

