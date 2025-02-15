/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatManager {
    private TimeZone _timeZone = null;
    private Locale _locale = null;
    private String _pattern = null;
    private DateFormat _dateFormat = null;

    public DateFormatManager() {
        this.configure();
    }

    public DateFormatManager(TimeZone timeZone) {
        this._timeZone = timeZone;
        this.configure();
    }

    public DateFormatManager(Locale locale) {
        this._locale = locale;
        this.configure();
    }

    public DateFormatManager(String pattern) {
        this._pattern = pattern;
        this.configure();
    }

    public DateFormatManager(TimeZone timeZone, Locale locale) {
        this._timeZone = timeZone;
        this._locale = locale;
        this.configure();
    }

    public DateFormatManager(TimeZone timeZone, String pattern) {
        this._timeZone = timeZone;
        this._pattern = pattern;
        this.configure();
    }

    public DateFormatManager(Locale locale, String pattern) {
        this._locale = locale;
        this._pattern = pattern;
        this.configure();
    }

    public DateFormatManager(TimeZone timeZone, Locale locale, String pattern) {
        this._timeZone = timeZone;
        this._locale = locale;
        this._pattern = pattern;
        this.configure();
    }

    public synchronized TimeZone getTimeZone() {
        if (this._timeZone == null) {
            return TimeZone.getDefault();
        }
        return this._timeZone;
    }

    public synchronized void setTimeZone(TimeZone timeZone) {
        this._timeZone = timeZone;
        this.configure();
    }

    public synchronized Locale getLocale() {
        if (this._locale == null) {
            return Locale.getDefault();
        }
        return this._locale;
    }

    public synchronized void setLocale(Locale locale) {
        this._locale = locale;
        this.configure();
    }

    public synchronized String getPattern() {
        return this._pattern;
    }

    public synchronized void setPattern(String pattern) {
        this._pattern = pattern;
        this.configure();
    }

    public synchronized String getOutputFormat() {
        return this._pattern;
    }

    public synchronized void setOutputFormat(String pattern) {
        this._pattern = pattern;
        this.configure();
    }

    public synchronized DateFormat getDateFormatInstance() {
        return this._dateFormat;
    }

    public synchronized void setDateFormatInstance(DateFormat dateFormat) {
        this._dateFormat = dateFormat;
    }

    public String format(Date date) {
        return this.getDateFormatInstance().format(date);
    }

    public String format(Date date, String pattern) {
        DateFormat formatter = null;
        formatter = this.getDateFormatInstance();
        if (formatter instanceof SimpleDateFormat) {
            formatter = (SimpleDateFormat)formatter.clone();
            ((SimpleDateFormat)formatter).applyPattern(pattern);
        }
        return formatter.format(date);
    }

    public Date parse(String date) throws ParseException {
        return this.getDateFormatInstance().parse(date);
    }

    public Date parse(String date, String pattern) throws ParseException {
        DateFormat formatter = null;
        formatter = this.getDateFormatInstance();
        if (formatter instanceof SimpleDateFormat) {
            formatter = (SimpleDateFormat)formatter.clone();
            ((SimpleDateFormat)formatter).applyPattern(pattern);
        }
        return formatter.parse(date);
    }

    private synchronized void configure() {
        this._dateFormat = SimpleDateFormat.getDateTimeInstance(0, 0, this.getLocale());
        this._dateFormat.setTimeZone(this.getTimeZone());
        if (this._pattern != null) {
            ((SimpleDateFormat)this._dateFormat).applyPattern(this._pattern);
        }
    }
}

