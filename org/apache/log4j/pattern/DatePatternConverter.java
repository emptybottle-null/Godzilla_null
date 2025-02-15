/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.pattern.CachedDateFormat;
import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public final class DatePatternConverter
extends LoggingEventPatternConverter {
    private static final String ABSOLUTE_FORMAT = "ABSOLUTE";
    private static final String ABSOLUTE_TIME_PATTERN = "HH:mm:ss,SSS";
    private static final String DATE_AND_TIME_FORMAT = "DATE";
    private static final String DATE_AND_TIME_PATTERN = "dd MMM yyyy HH:mm:ss,SSS";
    private static final String ISO8601_FORMAT = "ISO8601";
    private static final String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
    private final CachedDateFormat df;

    private DatePatternConverter(String[] options) {
        super("Date", "date");
        String patternOption = options == null || options.length == 0 ? null : options[0];
        String pattern = patternOption == null || patternOption.equalsIgnoreCase(ISO8601_FORMAT) ? ISO8601_PATTERN : (patternOption.equalsIgnoreCase(ABSOLUTE_FORMAT) ? ABSOLUTE_TIME_PATTERN : (patternOption.equalsIgnoreCase(DATE_AND_TIME_FORMAT) ? DATE_AND_TIME_PATTERN : patternOption));
        int maximumCacheValidity = 1000;
        DateFormat simpleFormat = null;
        try {
            simpleFormat = new SimpleDateFormat(pattern);
            maximumCacheValidity = CachedDateFormat.getMaximumCacheValidity(pattern);
        } catch (IllegalArgumentException e) {
            LogLog.warn("Could not instantiate SimpleDateFormat with pattern " + patternOption, e);
            simpleFormat = new SimpleDateFormat(ISO8601_PATTERN);
        }
        if (options != null && options.length > 1) {
            TimeZone tz = TimeZone.getTimeZone(options[1]);
            simpleFormat.setTimeZone(tz);
        } else {
            simpleFormat = new DefaultZoneDateFormat(simpleFormat);
        }
        this.df = new CachedDateFormat(simpleFormat, maximumCacheValidity);
    }

    public static DatePatternConverter newInstance(String[] options) {
        return new DatePatternConverter(options);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void format(LoggingEvent event, StringBuffer output) {
        DatePatternConverter datePatternConverter = this;
        synchronized (datePatternConverter) {
            this.df.format(event.timeStamp, output);
        }
    }

    public void format(Object obj, StringBuffer output) {
        if (obj instanceof Date) {
            this.format((Date)obj, output);
        }
        super.format(obj, output);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void format(Date date, StringBuffer toAppendTo) {
        DatePatternConverter datePatternConverter = this;
        synchronized (datePatternConverter) {
            this.df.format(date.getTime(), toAppendTo);
        }
    }

    private static class DefaultZoneDateFormat
    extends DateFormat {
        private static final long serialVersionUID = 1L;
        private final DateFormat dateFormat;

        public DefaultZoneDateFormat(DateFormat format) {
            this.dateFormat = format;
        }

        public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
            this.dateFormat.setTimeZone(TimeZone.getDefault());
            return this.dateFormat.format(date, toAppendTo, fieldPosition);
        }

        public Date parse(String source, ParsePosition pos) {
            this.dateFormat.setTimeZone(TimeZone.getDefault());
            return this.dateFormat.parse(source, pos);
        }
    }
}

