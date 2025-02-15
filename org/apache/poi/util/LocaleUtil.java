/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.poi.util.LocaleID;
import org.apache.poi.util.SuppressForbidden;

public final class LocaleUtil {
    public static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
    public static final Charset CHARSET_1252 = Charset.forName("CP1252");
    private static final ThreadLocal<TimeZone> userTimeZone = new ThreadLocal();
    private static final ThreadLocal<Locale> userLocale = new ThreadLocal();

    private LocaleUtil() {
    }

    public static void setUserTimeZone(TimeZone timezone) {
        userTimeZone.set(timezone);
    }

    @SuppressForbidden(value="implementation around default locales in POI")
    public static TimeZone getUserTimeZone() {
        TimeZone timeZone = userTimeZone.get();
        return timeZone != null ? timeZone : TimeZone.getDefault();
    }

    public static void resetUserTimeZone() {
        userTimeZone.remove();
    }

    public static void setUserLocale(Locale locale) {
        userLocale.set(locale);
    }

    @SuppressForbidden(value="implementation around default locales in POI")
    public static Locale getUserLocale() {
        Locale locale = userLocale.get();
        return locale != null ? locale : Locale.getDefault();
    }

    public static void resetUserLocale() {
        userLocale.remove();
    }

    public static Calendar getLocaleCalendar() {
        return LocaleUtil.getLocaleCalendar(LocaleUtil.getUserTimeZone());
    }

    public static Calendar getLocaleCalendar(int year, int month, int day) {
        return LocaleUtil.getLocaleCalendar(year, month, day, 0, 0, 0);
    }

    public static Calendar getLocaleCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = LocaleUtil.getLocaleCalendar();
        cal.set(year, month, day, hour, minute, second);
        cal.clear(14);
        return cal;
    }

    public static Calendar getLocaleCalendar(TimeZone timeZone) {
        return Calendar.getInstance(timeZone, LocaleUtil.getUserLocale());
    }

    public static String getLocaleFromLCID(int lcid) {
        LocaleID lid = LocaleID.lookupByLcid(lcid & 0xFFFF);
        return lid == null ? "invalid" : lid.getLanguageTag();
    }

    public static int getDefaultCodePageFromLCID(int lcid) {
        LocaleID lid = LocaleID.lookupByLcid(lcid & 0xFFFF);
        return lid == null ? 0 : lid.getDefaultCodepage();
    }
}

