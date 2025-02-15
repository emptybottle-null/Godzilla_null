/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.NOPLogger;

public final class NOPLoggerRepository
implements LoggerRepository {
    public void addHierarchyEventListener(HierarchyEventListener listener) {
    }

    public boolean isDisabled(int level) {
        return true;
    }

    public void setThreshold(Level level) {
    }

    public void setThreshold(String val) {
    }

    public void emitNoAppenderWarning(Category cat) {
    }

    public Level getThreshold() {
        return Level.OFF;
    }

    public Logger getLogger(String name) {
        return new NOPLogger(this, name);
    }

    public Logger getLogger(String name, LoggerFactory factory) {
        return new NOPLogger(this, name);
    }

    public Logger getRootLogger() {
        return new NOPLogger(this, "root");
    }

    public Logger exists(String name) {
        return null;
    }

    public void shutdown() {
    }

    public Enumeration getCurrentLoggers() {
        return new Vector().elements();
    }

    public Enumeration getCurrentCategories() {
        return this.getCurrentLoggers();
    }

    public void fireAddAppenderEvent(Category logger, Appender appender) {
    }

    public void resetConfiguration() {
    }
}

