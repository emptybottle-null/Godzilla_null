/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import org.apache.log4j.spi.LoggingEvent;

public interface TriggeringEventEvaluator {
    public boolean isTriggeringEvent(LoggingEvent var1);
}

