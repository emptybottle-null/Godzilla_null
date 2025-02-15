/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.NOPLoggerRepository;

public final class NOPLogger
extends Logger {
    public NOPLogger(NOPLoggerRepository repo, String name) {
        super(name);
        this.repository = repo;
        this.level = Level.OFF;
        this.parent = this;
    }

    public void addAppender(Appender newAppender) {
    }

    public void assertLog(boolean assertion, String msg) {
    }

    public void callAppenders(LoggingEvent event) {
    }

    void closeNestedAppenders() {
    }

    public void debug(Object message) {
    }

    public void debug(Object message, Throwable t) {
    }

    public void error(Object message) {
    }

    public void error(Object message, Throwable t) {
    }

    public void fatal(Object message) {
    }

    public void fatal(Object message, Throwable t) {
    }

    public Enumeration getAllAppenders() {
        return new Vector().elements();
    }

    public Appender getAppender(String name) {
        return null;
    }

    public Level getEffectiveLevel() {
        return Level.OFF;
    }

    public Priority getChainedPriority() {
        return this.getEffectiveLevel();
    }

    public ResourceBundle getResourceBundle() {
        return null;
    }

    public void info(Object message) {
    }

    public void info(Object message, Throwable t) {
    }

    public boolean isAttached(Appender appender) {
        return false;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isEnabledFor(Priority level) {
        return false;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public void l7dlog(Priority priority, String key, Throwable t) {
    }

    public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
    }

    public void log(Priority priority, Object message, Throwable t) {
    }

    public void log(Priority priority, Object message) {
    }

    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
    }

    public void removeAllAppenders() {
    }

    public void removeAppender(Appender appender) {
    }

    public void removeAppender(String name) {
    }

    public void setLevel(Level level) {
    }

    public void setPriority(Priority priority) {
    }

    public void setResourceBundle(ResourceBundle bundle) {
    }

    public void warn(Object message) {
    }

    public void warn(Object message, Throwable t) {
    }

    public void trace(Object message) {
    }

    public void trace(Object message, Throwable t) {
    }

    public boolean isTraceEnabled() {
        return false;
    }
}

