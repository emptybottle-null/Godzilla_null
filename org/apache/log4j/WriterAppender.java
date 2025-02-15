/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class WriterAppender
extends AppenderSkeleton {
    protected boolean immediateFlush = true;
    protected String encoding;
    protected QuietWriter qw;

    public WriterAppender() {
    }

    public WriterAppender(Layout layout, OutputStream os) {
        this(layout, new OutputStreamWriter(os));
    }

    public WriterAppender(Layout layout, Writer writer) {
        this.layout = layout;
        this.setWriter(writer);
    }

    public void setImmediateFlush(boolean value) {
        this.immediateFlush = value;
    }

    public boolean getImmediateFlush() {
        return this.immediateFlush;
    }

    public void activateOptions() {
    }

    public void append(LoggingEvent event) {
        if (!this.checkEntryConditions()) {
            return;
        }
        this.subAppend(event);
    }

    protected boolean checkEntryConditions() {
        if (this.closed) {
            LogLog.warn("Not allowed to write to a closed appender.");
            return false;
        }
        if (this.qw == null) {
            this.errorHandler.error("No output stream or file set for the appender named [" + this.name + "].");
            return false;
        }
        if (this.layout == null) {
            this.errorHandler.error("No layout set for the appender named [" + this.name + "].");
            return false;
        }
        return true;
    }

    public synchronized void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.writeFooter();
        this.reset();
    }

    protected void closeWriter() {
        if (this.qw != null) {
            try {
                this.qw.close();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("Could not close " + this.qw, e);
            }
        }
    }

    protected OutputStreamWriter createWriter(OutputStream os) {
        OutputStreamWriter retval = null;
        String enc = this.getEncoding();
        if (enc != null) {
            try {
                retval = new OutputStreamWriter(os, enc);
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.warn("Error initializing output writer.");
                LogLog.warn("Unsupported encoding?");
            }
        }
        if (retval == null) {
            retval = new OutputStreamWriter(os);
        }
        return retval;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String value) {
        this.encoding = value;
    }

    public synchronized void setErrorHandler(ErrorHandler eh) {
        if (eh == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = eh;
            if (this.qw != null) {
                this.qw.setErrorHandler(eh);
            }
        }
    }

    public synchronized void setWriter(Writer writer) {
        this.reset();
        this.qw = new QuietWriter(writer, this.errorHandler);
        this.writeHeader();
    }

    protected void subAppend(LoggingEvent event) {
        String[] s;
        this.qw.write(this.layout.format(event));
        if (this.layout.ignoresThrowable() && (s = event.getThrowableStrRep()) != null) {
            int len = s.length;
            for (int i = 0; i < len; ++i) {
                this.qw.write(s[i]);
                this.qw.write(Layout.LINE_SEP);
            }
        }
        if (this.shouldFlush(event)) {
            this.qw.flush();
        }
    }

    public boolean requiresLayout() {
        return true;
    }

    protected void reset() {
        this.closeWriter();
        this.qw = null;
    }

    protected void writeFooter() {
        String f;
        if (this.layout != null && (f = this.layout.getFooter()) != null && this.qw != null) {
            this.qw.write(f);
            this.qw.flush();
        }
    }

    protected void writeHeader() {
        String h;
        if (this.layout != null && (h = this.layout.getHeader()) != null && this.qw != null) {
            this.qw.write(h);
        }
    }

    protected boolean shouldFlush(LoggingEvent event) {
        return this.immediateFlush;
    }
}

