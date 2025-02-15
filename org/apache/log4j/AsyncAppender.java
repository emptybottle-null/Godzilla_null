/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender
extends AppenderSkeleton
implements AppenderAttachable {
    public static final int DEFAULT_BUFFER_SIZE = 128;
    private final List buffer = new ArrayList();
    private final Map discardMap = new HashMap();
    private int bufferSize = 128;
    AppenderAttachableImpl aai = this.appenders = new AppenderAttachableImpl();
    private final AppenderAttachableImpl appenders;
    private final Thread dispatcher = new Thread(new Dispatcher(this, this.buffer, this.discardMap, this.appenders));
    private boolean locationInfo = false;
    private boolean blocking = true;

    public AsyncAppender() {
        this.dispatcher.setDaemon(true);
        this.dispatcher.setName("AsyncAppender-Dispatcher-" + this.dispatcher.getName());
        this.dispatcher.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addAppender(Appender newAppender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.addAppender(newAppender);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void append(LoggingEvent event) {
        if (this.dispatcher == null || !this.dispatcher.isAlive() || this.bufferSize <= 0) {
            AppenderAttachableImpl appenderAttachableImpl = this.appenders;
            synchronized (appenderAttachableImpl) {
                this.appenders.appendLoopOnAppenders(event);
            }
            return;
        }
        event.getNDC();
        event.getThreadName();
        event.getMDCCopy();
        if (this.locationInfo) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        List list = this.buffer;
        synchronized (list) {
            block15: {
                boolean discard;
                do {
                    int previousSize;
                    if ((previousSize = this.buffer.size()) < this.bufferSize) {
                        this.buffer.add(event);
                        if (previousSize == 0) {
                            this.buffer.notifyAll();
                        }
                        break block15;
                    }
                    discard = true;
                    if (!this.blocking || Thread.interrupted() || Thread.currentThread() == this.dispatcher) continue;
                    try {
                        this.buffer.wait();
                        discard = false;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } while (!discard);
                String loggerName = event.getLoggerName();
                DiscardSummary summary = (DiscardSummary)this.discardMap.get(loggerName);
                if (summary == null) {
                    summary = new DiscardSummary(event);
                    this.discardMap.put(loggerName, summary);
                } else {
                    summary.add(event);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() {
        Object object = this.buffer;
        synchronized (object) {
            this.closed = true;
            this.buffer.notifyAll();
        }
        try {
            this.dispatcher.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogLog.error("Got an InterruptedException while waiting for the dispatcher to finish.", e);
        }
        object = this.appenders;
        synchronized (object) {
            Enumeration iter = this.appenders.getAllAppenders();
            if (iter != null) {
                while (iter.hasMoreElements()) {
                    Object next = iter.nextElement();
                    if (!(next instanceof Appender)) continue;
                    ((Appender)next).close();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Enumeration getAllAppenders() {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.getAllAppenders();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Appender getAppender(String name) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.getAppender(name);
        }
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isAttached(Appender appender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.isAttached(appender);
        }
    }

    public boolean requiresLayout() {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAllAppenders() {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAllAppenders();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAppender(Appender appender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAppender(appender);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAppender(String name) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAppender(name);
        }
    }

    public void setLocationInfo(boolean flag) {
        this.locationInfo = flag;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setBufferSize(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException("size");
        }
        List list = this.buffer;
        synchronized (list) {
            this.bufferSize = size < 1 ? 1 : size;
            this.buffer.notifyAll();
        }
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setBlocking(boolean value) {
        List list = this.buffer;
        synchronized (list) {
            this.blocking = value;
            this.buffer.notifyAll();
        }
    }

    public boolean getBlocking() {
        return this.blocking;
    }

    private static class Dispatcher
    implements Runnable {
        private final AsyncAppender parent;
        private final List buffer;
        private final Map discardMap;
        private final AppenderAttachableImpl appenders;

        public Dispatcher(AsyncAppender parent, List buffer, Map discardMap, AppenderAttachableImpl appenders) {
            this.parent = parent;
            this.buffer = buffer;
            this.appenders = appenders;
            this.discardMap = discardMap;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void run() {
            boolean isActive = true;
            try {
                while (isActive) {
                    LoggingEvent[] events = null;
                    List list = this.buffer;
                    synchronized (list) {
                        int bufferSize = this.buffer.size();
                        boolean bl = isActive = !this.parent.closed;
                        while (bufferSize == 0 && isActive) {
                            this.buffer.wait();
                            bufferSize = this.buffer.size();
                            isActive = !this.parent.closed;
                        }
                        if (bufferSize > 0) {
                            events = new LoggingEvent[bufferSize + this.discardMap.size()];
                            this.buffer.toArray(events);
                            int index = bufferSize;
                            Iterator iter = this.discardMap.values().iterator();
                            while (iter.hasNext()) {
                                events[index++] = ((DiscardSummary)iter.next()).createEvent();
                            }
                            this.buffer.clear();
                            this.discardMap.clear();
                            this.buffer.notifyAll();
                        }
                    }
                    if (events == null) continue;
                    for (int i = 0; i < events.length; ++i) {
                        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
                        synchronized (appenderAttachableImpl) {
                            this.appenders.appendLoopOnAppenders(events[i]);
                            continue;
                        }
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static final class DiscardSummary {
        private LoggingEvent maxEvent;
        private int count;

        public DiscardSummary(LoggingEvent event) {
            this.maxEvent = event;
            this.count = 1;
        }

        public void add(LoggingEvent event) {
            if (event.getLevel().toInt() > this.maxEvent.getLevel().toInt()) {
                this.maxEvent = event;
            }
            ++this.count;
        }

        public LoggingEvent createEvent() {
            String msg = MessageFormat.format("Discarded {0} messages due to full event buffer including: {1}", new Integer(this.count), this.maxEvent.getMessage());
            return new LoggingEvent("org.apache.log4j.AsyncAppender.DONT_REPORT_LOCATION", Logger.getLogger(this.maxEvent.getLoggerName()), this.maxEvent.getLevel(), msg, null);
        }
    }
}

