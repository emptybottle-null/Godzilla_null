/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import org.apache.log4j.lf5.LogLevel;

public abstract class LogRecord
implements Serializable {
    protected static long _seqCount = 0L;
    protected LogLevel _level;
    protected String _message = "";
    protected long _sequenceNumber;
    protected long _millis = System.currentTimeMillis();
    protected String _category = "Debug";
    protected String _thread;
    protected String _thrownStackTrace;
    protected Throwable _thrown;
    protected String _ndc = "";
    protected String _location = "";

    public LogRecord() {
        this._level = LogLevel.INFO;
        this._sequenceNumber = LogRecord.getNextId();
        this._thread = Thread.currentThread().toString();
    }

    public LogLevel getLevel() {
        return this._level;
    }

    public void setLevel(LogLevel level) {
        this._level = level;
    }

    public abstract boolean isSevereLevel();

    public boolean hasThrown() {
        Throwable thrown = this.getThrown();
        if (thrown == null) {
            return false;
        }
        String thrownString = thrown.toString();
        return thrownString != null && thrownString.trim().length() != 0;
    }

    public boolean isFatal() {
        return this.isSevereLevel() || this.hasThrown();
    }

    public String getCategory() {
        return this._category;
    }

    public void setCategory(String category) {
        this._category = category;
    }

    public String getMessage() {
        return this._message;
    }

    public void setMessage(String message) {
        this._message = message;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public void setSequenceNumber(long number) {
        this._sequenceNumber = number;
    }

    public long getMillis() {
        return this._millis;
    }

    public void setMillis(long millis) {
        this._millis = millis;
    }

    public String getThreadDescription() {
        return this._thread;
    }

    public void setThreadDescription(String threadDescription) {
        this._thread = threadDescription;
    }

    public String getThrownStackTrace() {
        return this._thrownStackTrace;
    }

    public void setThrownStackTrace(String trace) {
        this._thrownStackTrace = trace;
    }

    public Throwable getThrown() {
        return this._thrown;
    }

    public void setThrown(Throwable thrown) {
        if (thrown == null) {
            return;
        }
        this._thrown = thrown;
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        thrown.printStackTrace(out);
        out.flush();
        this._thrownStackTrace = sw.toString();
        try {
            out.close();
            sw.close();
        } catch (IOException e) {
            // empty catch block
        }
        out = null;
        sw = null;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("LogRecord: [" + this._level + ", " + this._message + "]");
        return buf.toString();
    }

    public String getNDC() {
        return this._ndc;
    }

    public void setNDC(String ndc) {
        this._ndc = ndc;
    }

    public String getLocation() {
        return this._location;
    }

    public void setLocation(String location) {
        this._location = location;
    }

    public static synchronized void resetSequenceNumber() {
        _seqCount = 0L;
    }

    protected static synchronized long getNextId() {
        return ++_seqCount;
    }
}

