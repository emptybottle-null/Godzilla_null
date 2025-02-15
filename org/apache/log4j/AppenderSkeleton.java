/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton
implements Appender,
OptionHandler {
    protected Layout layout;
    protected String name;
    protected Priority threshold;
    protected ErrorHandler errorHandler = new OnlyOnceErrorHandler();
    protected Filter headFilter;
    protected Filter tailFilter;
    protected boolean closed = false;

    public AppenderSkeleton() {
    }

    protected AppenderSkeleton(boolean isActive) {
    }

    public void activateOptions() {
    }

    public void addFilter(Filter newFilter) {
        if (this.headFilter == null) {
            this.headFilter = this.tailFilter = newFilter;
        } else {
            this.tailFilter.setNext(newFilter);
            this.tailFilter = newFilter;
        }
    }

    protected abstract void append(LoggingEvent var1);

    public void clearFilters() {
        this.tailFilter = null;
        this.headFilter = null;
    }

    public void finalize() {
        if (this.closed) {
            return;
        }
        LogLog.debug("Finalizing appender named [" + this.name + "].");
        this.close();
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public Filter getFilter() {
        return this.headFilter;
    }

    public final Filter getFirstFilter() {
        return this.headFilter;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public final String getName() {
        return this.name;
    }

    public Priority getThreshold() {
        return this.threshold;
    }

    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.threshold == null || priority.isGreaterOrEqual(this.threshold);
    }

    public synchronized void doAppend(LoggingEvent event) {
        if (this.closed) {
            LogLog.error("Attempted to append to closed appender named [" + this.name + "].");
            return;
        }
        if (!this.isAsSevereAsThreshold(event.getLevel())) {
            return;
        }
        Filter f = this.headFilter;
        block5: while (f != null) {
            switch (f.decide(event)) {
                case -1: {
                    return;
                }
                case 1: {
                    break block5;
                }
                case 0: {
                    f = f.getNext();
                }
                default: {
                    continue block5;
                }
            }
        }
        this.append(event);
    }

    public synchronized void setErrorHandler(ErrorHandler eh) {
        if (eh == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = eh;
        }
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThreshold(Priority threshold) {
        this.threshold = threshold;
    }
}

