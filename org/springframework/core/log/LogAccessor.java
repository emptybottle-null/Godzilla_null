/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.log;

import java.util.function.Supplier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;

public class LogAccessor {
    private final Log log;

    public LogAccessor(Log log) {
        this.log = log;
    }

    public LogAccessor(Class<?> logCategory) {
        this.log = LogFactory.getLog(logCategory);
    }

    public LogAccessor(String logCategory) {
        this.log = LogFactory.getLog(logCategory);
    }

    public final Log getLog() {
        return this.log;
    }

    public boolean isFatalEnabled() {
        return this.log.isFatalEnabled();
    }

    public boolean isErrorEnabled() {
        return this.log.isErrorEnabled();
    }

    public boolean isWarnEnabled() {
        return this.log.isWarnEnabled();
    }

    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    public void fatal(CharSequence message) {
        this.log.fatal(message);
    }

    public void fatal(Throwable cause, CharSequence message) {
        this.log.fatal(message, cause);
    }

    public void error(CharSequence message) {
        this.log.error(message);
    }

    public void error(Throwable cause, CharSequence message) {
        this.log.error(message, cause);
    }

    public void warn(CharSequence message) {
        this.log.warn(message);
    }

    public void warn(Throwable cause, CharSequence message) {
        this.log.warn(message, cause);
    }

    public void info(CharSequence message) {
        this.log.info(message);
    }

    public void info(Throwable cause, CharSequence message) {
        this.log.info(message, cause);
    }

    public void debug(CharSequence message) {
        this.log.debug(message);
    }

    public void debug(Throwable cause, CharSequence message) {
        this.log.debug(message, cause);
    }

    public void trace(CharSequence message) {
        this.log.trace(message);
    }

    public void trace(Throwable cause, CharSequence message) {
        this.log.trace(message, cause);
    }

    public void fatal(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isFatalEnabled()) {
            this.log.fatal(LogMessage.of(messageSupplier));
        }
    }

    public void fatal(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isFatalEnabled()) {
            this.log.fatal(LogMessage.of(messageSupplier), cause);
        }
    }

    public void error(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isErrorEnabled()) {
            this.log.error(LogMessage.of(messageSupplier));
        }
    }

    public void error(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isErrorEnabled()) {
            this.log.error(LogMessage.of(messageSupplier), cause);
        }
    }

    public void warn(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isWarnEnabled()) {
            this.log.warn(LogMessage.of(messageSupplier));
        }
    }

    public void warn(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isWarnEnabled()) {
            this.log.warn(LogMessage.of(messageSupplier), cause);
        }
    }

    public void info(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isInfoEnabled()) {
            this.log.info(LogMessage.of(messageSupplier));
        }
    }

    public void info(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isInfoEnabled()) {
            this.log.info(LogMessage.of(messageSupplier), cause);
        }
    }

    public void debug(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isDebugEnabled()) {
            this.log.debug(LogMessage.of(messageSupplier));
        }
    }

    public void debug(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isDebugEnabled()) {
            this.log.debug(LogMessage.of(messageSupplier), cause);
        }
    }

    public void trace(Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isTraceEnabled()) {
            this.log.trace(LogMessage.of(messageSupplier));
        }
    }

    public void trace(Throwable cause, Supplier<? extends CharSequence> messageSupplier) {
        if (this.log.isTraceEnabled()) {
            this.log.trace(LogMessage.of(messageSupplier), cause);
        }
    }
}

