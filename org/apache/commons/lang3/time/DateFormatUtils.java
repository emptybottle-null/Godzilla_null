/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.FastTimeZone;

public class DateFormatUtils {
    private static final TimeZone UTC_TIME_ZONE = FastTimeZone.getGmtTimeZone();
    public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATETIME_FORMAT;
    public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT;
    public static final FastDateFormat ISO_8601_EXTENDED_DATE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT;
    public static final FastDateFormat ISO_8601_EXTENDED_TIME_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_NO_T_FORMAT;
    public static final FastDateFormat ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT;
    public static final FastDateFormat SMTP_DATETIME_FORMAT;

    public static String formatUTC(long millis, String pattern) {
        return DateFormatUtils.format(new Date(millis), pattern, UTC_TIME_ZONE, null);
    }

    public static String formatUTC(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern, UTC_TIME_ZONE, null);
    }

    public static String formatUTC(long millis, String pattern, Locale locale) {
        return DateFormatUtils.format(new Date(millis), pattern, UTC_TIME_ZONE, locale);
    }

    public static String formatUTC(Date date, String pattern, Locale locale) {
        return DateFormatUtils.format(date, pattern, UTC_TIME_ZONE, locale);
    }

    public static String format(long millis, String pattern) {
        return DateFormatUtils.format(new Date(millis), pattern, null, null);
    }

    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern, null, null);
    }

    public static String format(Calendar calendar, String pattern) {
        return DateFormatUtils.format(calendar, pattern, null, null);
    }

    public static String format(long millis, String pattern, TimeZone timeZone) {
        return DateFormatUtils.format(new Date(millis), pattern, timeZone, null);
    }

    public static String format(Date date, String pattern, TimeZone timeZone) {
        return DateFormatUtils.format(date, pattern, timeZone, null);
    }

    public static String format(Calendar calendar, String pattern, TimeZone timeZone) {
        return DateFormatUtils.format(calendar, pattern, timeZone, null);
    }

    public static String format(long millis, String pattern, Locale locale) {
        return DateFormatUtils.format(new Date(millis), pattern, null, locale);
    }

    public static String format(Date date, String pattern, Locale locale) {
        return DateFormatUtils.format(date, pattern, null, locale);
    }

    public static String format(Calendar calendar, String pattern, Locale locale) {
        return DateFormatUtils.format(calendar, pattern, null, locale);
    }

    public static String format(long millis, String pattern, TimeZone timeZone, Locale locale) {
        return DateFormatUtils.format(new Date(millis), pattern, timeZone, locale);
    }

    public static String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
        FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
        return df.format(date);
    }

    public static String format(Calendar calendar, String pattern, TimeZone timeZone, Locale locale) {
        FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
        return df.format(calendar);
    }

    static {
        ISO_DATETIME_FORMAT = ISO_8601_EXTENDED_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
        ISO_DATETIME_TIME_ZONE_FORMAT = ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
        ISO_DATE_FORMAT = ISO_8601_EXTENDED_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
        ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");
        ISO_TIME_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ss");
        ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
        ISO_TIME_NO_T_FORMAT = ISO_8601_EXTENDED_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
        ISO_TIME_NO_T_TIME_ZONE_FORMAT = ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
        SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
    }
}

