/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerFactory;

public class Logger
extends Category {
    private static final String FQCN = Logger.class.getName();

    protected Logger(String name) {
        super(name);
    }

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz.getName());
    }

    public static Logger getRootLogger() {
        return LogManager.getRootLogger();
    }

    public static Logger getLogger(String name, LoggerFactory factory) {
        return LogManager.getLogger(name, factory);
    }

    public void trace(Object message) {
        if (this.repository.isDisabled(5000)) {
            return;
        }
        if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, message, null);
        }
    }

    public void trace(Object message, Throwable t) {
        if (this.repository.isDisabled(5000)) {
            return;
        }
        if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, message, t);
        }
    }

    public boolean isTraceEnabled() {
        if (this.repository.isDisabled(5000)) {
            return false;
        }
        return Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel());
    }
}

