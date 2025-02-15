/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.util;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.util.AdapterLogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class LogMonitorAdapter {
    public static final int LOG4J_LOG_LEVELS = 0;
    public static final int JDK14_LOG_LEVELS = 1;
    private LogBrokerMonitor _logMonitor;
    private LogLevel _defaultLevel = null;

    private LogMonitorAdapter(List userDefinedLevels) {
        this._defaultLevel = (LogLevel)userDefinedLevels.get(0);
        this._logMonitor = new LogBrokerMonitor(userDefinedLevels);
        this._logMonitor.setFrameSize(LogMonitorAdapter.getDefaultMonitorWidth(), LogMonitorAdapter.getDefaultMonitorHeight());
        this._logMonitor.setFontSize(12);
        this._logMonitor.show();
    }

    public static LogMonitorAdapter newInstance(int loglevels) {
        LogMonitorAdapter adapter;
        if (loglevels == 1) {
            adapter = LogMonitorAdapter.newInstance(LogLevel.getJdk14Levels());
            adapter.setDefaultLevel(LogLevel.FINEST);
            adapter.setSevereLevel(LogLevel.SEVERE);
        } else {
            adapter = LogMonitorAdapter.newInstance(LogLevel.getLog4JLevels());
            adapter.setDefaultLevel(LogLevel.DEBUG);
            adapter.setSevereLevel(LogLevel.FATAL);
        }
        return adapter;
    }

    public static LogMonitorAdapter newInstance(LogLevel[] userDefined) {
        if (userDefined == null) {
            return null;
        }
        return LogMonitorAdapter.newInstance(Arrays.asList(userDefined));
    }

    public static LogMonitorAdapter newInstance(List userDefinedLevels) {
        return new LogMonitorAdapter(userDefinedLevels);
    }

    public void addMessage(LogRecord record) {
        this._logMonitor.addMessage(record);
    }

    public void setMaxNumberOfRecords(int maxNumberOfRecords) {
        this._logMonitor.setMaxNumberOfLogRecords(maxNumberOfRecords);
    }

    public void setDefaultLevel(LogLevel level) {
        this._defaultLevel = level;
    }

    public LogLevel getDefaultLevel() {
        return this._defaultLevel;
    }

    public void setSevereLevel(LogLevel level) {
        AdapterLogRecord.setSevereLevel(level);
    }

    public LogLevel getSevereLevel() {
        return AdapterLogRecord.getSevereLevel();
    }

    public void log(String category, LogLevel level, String message, Throwable t, String NDC2) {
        AdapterLogRecord record = new AdapterLogRecord();
        record.setCategory(category);
        record.setMessage(message);
        record.setNDC(NDC2);
        record.setThrown(t);
        if (level == null) {
            record.setLevel(this.getDefaultLevel());
        } else {
            record.setLevel(level);
        }
        this.addMessage(record);
    }

    public void log(String category, String message) {
        this.log(category, null, message);
    }

    public void log(String category, LogLevel level, String message, String NDC2) {
        this.log(category, level, message, null, NDC2);
    }

    public void log(String category, LogLevel level, String message, Throwable t) {
        this.log(category, level, message, t, null);
    }

    public void log(String category, LogLevel level, String message) {
        this.log(category, level, message, null, null);
    }

    protected static int getScreenWidth() {
        try {
            return Toolkit.getDefaultToolkit().getScreenSize().width;
        } catch (Throwable t) {
            return 800;
        }
    }

    protected static int getScreenHeight() {
        try {
            return Toolkit.getDefaultToolkit().getScreenSize().height;
        } catch (Throwable t) {
            return 600;
        }
    }

    protected static int getDefaultMonitorWidth() {
        return 3 * LogMonitorAdapter.getScreenWidth() / 4;
    }

    protected static int getDefaultMonitorHeight() {
        return 3 * LogMonitorAdapter.getScreenHeight() / 4;
    }
}

