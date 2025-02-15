/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender
extends AppenderSkeleton {
    private static NullAppender instance = new NullAppender();

    public void activateOptions() {
    }

    public NullAppender getInstance() {
        return instance;
    }

    public static NullAppender getNullAppender() {
        return instance;
    }

    public void close() {
    }

    public void doAppend(LoggingEvent event) {
    }

    protected void append(LoggingEvent event) {
    }

    public boolean requiresLayout() {
        return false;
    }
}

