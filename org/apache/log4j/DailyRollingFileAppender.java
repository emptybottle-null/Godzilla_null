/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.RollingCalendar;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DailyRollingFileAppender
extends FileAppender {
    static final int TOP_OF_TROUBLE = -1;
    static final int TOP_OF_MINUTE = 0;
    static final int TOP_OF_HOUR = 1;
    static final int HALF_DAY = 2;
    static final int TOP_OF_DAY = 3;
    static final int TOP_OF_WEEK = 4;
    static final int TOP_OF_MONTH = 5;
    private String datePattern = "'.'yyyy-MM-dd";
    private String scheduledFilename;
    private long nextCheck = System.currentTimeMillis() - 1L;
    Date now = new Date();
    SimpleDateFormat sdf;
    RollingCalendar rc = new RollingCalendar();
    int checkPeriod = -1;
    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

    public DailyRollingFileAppender() {
    }

    public DailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, true);
        this.datePattern = datePattern;
        this.activateOptions();
    }

    public void setDatePattern(String pattern) {
        this.datePattern = pattern;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public void activateOptions() {
        super.activateOptions();
        if (this.datePattern != null && this.fileName != null) {
            this.now.setTime(System.currentTimeMillis());
            this.sdf = new SimpleDateFormat(this.datePattern);
            int type = this.computeCheckPeriod();
            this.printPeriodicity(type);
            this.rc.setType(type);
            File file = new File(this.fileName);
            this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));
        } else {
            LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
        }
    }

    void printPeriodicity(int type) {
        switch (type) {
            case 0: {
                LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
                break;
            }
            case 1: {
                LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
                break;
            }
            case 2: {
                LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
                break;
            }
            case 3: {
                LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
                break;
            }
            case 4: {
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
                break;
            }
            case 5: {
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
                break;
            }
            default: {
                LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
            }
        }
    }

    int computeCheckPeriod() {
        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.getDefault());
        Date epoch = new Date(0L);
        if (this.datePattern != null) {
            for (int i = 0; i <= 5; ++i) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
                simpleDateFormat.setTimeZone(gmtTimeZone);
                String r0 = simpleDateFormat.format(epoch);
                rollingCalendar.setType(i);
                Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
                String r1 = simpleDateFormat.format(next);
                if (r0 == null || r1 == null || r0.equals(r1)) continue;
                return i;
            }
        }
        return -1;
    }

    void rollOver() throws IOException {
        File file;
        boolean result;
        if (this.datePattern == null) {
            this.errorHandler.error("Missing DatePattern option in rollOver().");
            return;
        }
        String datedFilename = this.fileName + this.sdf.format(this.now);
        if (this.scheduledFilename.equals(datedFilename)) {
            return;
        }
        this.closeFile();
        File target = new File(this.scheduledFilename);
        if (target.exists()) {
            target.delete();
        }
        if (result = (file = new File(this.fileName)).renameTo(target)) {
            LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
        } else {
            LogLog.error("Failed to rename [" + this.fileName + "] to [" + this.scheduledFilename + "].");
        }
        try {
            this.setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            this.errorHandler.error("setFile(" + this.fileName + ", true) call failed.");
        }
        this.scheduledFilename = datedFilename;
    }

    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= this.nextCheck) {
            this.now.setTime(n);
            this.nextCheck = this.rc.getNextCheckMillis(this.now);
            try {
                this.rollOver();
            } catch (IOException ioe) {
                if (ioe instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("rollOver() failed.", ioe);
            }
        }
        super.subAppend(event);
    }
}

