/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.net;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

class DefaultEvaluator
implements TriggeringEventEvaluator {
    DefaultEvaluator() {
    }

    public boolean isTriggeringEvent(LoggingEvent event) {
        return event.getLevel().isGreaterOrEqual(Level.ERROR);
    }
}

