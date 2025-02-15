/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class Filter
implements OptionHandler {
    public Filter next;
    public static final int DENY = -1;
    public static final int NEUTRAL = 0;
    public static final int ACCEPT = 1;

    public void activateOptions() {
    }

    public abstract int decide(LoggingEvent var1);

    public void setNext(Filter next) {
        this.next = next;
    }

    public Filter getNext() {
        return this.next;
    }
}

