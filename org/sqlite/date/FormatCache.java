/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.date;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private final ConcurrentMap<MultipartKey, F> cInstanceCache = new ConcurrentHashMap<MultipartKey, F>(7);
    private static final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = new ConcurrentHashMap<MultipartKey, String>(7);

    FormatCache() {
    }

    public F getInstance() {
        return this.getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String pattern, TimeZone timeZone, Locale locale) {
        Format previousValue;
        MultipartKey key;
        Format format;
        if (pattern == null) {
            throw new NullPointerException("pattern must not be null");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if ((format = (Format)this.cInstanceCache.get(key = new MultipartKey(pattern, timeZone, locale))) == null && (previousValue = this.cInstanceCache.putIfAbsent(key, format = this.createInstance(pattern, timeZone, locale))) != null) {
            format = previousValue;
        }
        return (F)format;
    }

    protected abstract F createInstance(String var1, TimeZone var2, Locale var3);

    private F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String pattern = FormatCache.getPatternForStyle(dateStyle, timeStyle, locale);
        return this.getInstance(pattern, timeZone, locale);
    }

    F getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
        return this.getDateTimeInstance((Integer)dateStyle, (Integer)timeStyle, timeZone, locale);
    }

    F getDateInstance(int dateStyle, TimeZone timeZone, Locale locale) {
        return this.getDateTimeInstance((Integer)dateStyle, null, timeZone, locale);
    }

    F getTimeInstance(int timeStyle, TimeZone timeZone, Locale locale) {
        return this.getDateTimeInstance(null, (Integer)timeStyle, timeZone, locale);
    }

    static String getPatternForStyle(Integer dateStyle, Integer timeStyle, Locale locale) {
        MultipartKey key = new MultipartKey(dateStyle, timeStyle, locale);
        String pattern = (String)cDateTimeInstanceCache.get(key);
        if (pattern == null) {
            try {
                DateFormat formatter = dateStyle == null ? DateFormat.getTimeInstance(timeStyle, locale) : (timeStyle == null ? DateFormat.getDateInstance(dateStyle, locale) : DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale));
                pattern = ((SimpleDateFormat)formatter).toPattern();
                String previous = cDateTimeInstanceCache.putIfAbsent(key, pattern);
                if (previous != null) {
                    pattern = previous;
                }
            } catch (ClassCastException ex) {
                throw new IllegalArgumentException("No date time pattern for locale: " + locale);
            }
        }
        return pattern;
    }

    private static class MultipartKey {
        private final Object[] keys;
        private int hashCode;

        public MultipartKey(Object ... keys) {
            this.keys = keys;
        }

        public boolean equals(Object obj) {
            return Arrays.equals(this.keys, ((MultipartKey)obj).keys);
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int rc = 0;
                for (Object key : this.keys) {
                    if (key == null) continue;
                    rc = rc * 7 + key.hashCode();
                }
                this.hashCode = rc;
            }
            return this.hashCode;
        }
    }
}

