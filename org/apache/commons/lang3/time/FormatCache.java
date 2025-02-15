/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.Validate;

abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private final ConcurrentMap<ArrayKey, F> cInstanceCache = new ConcurrentHashMap<ArrayKey, F>(7);
    private static final ConcurrentMap<ArrayKey, String> cDateTimeInstanceCache = new ConcurrentHashMap<ArrayKey, String>(7);

    FormatCache() {
    }

    public F getInstance() {
        return this.getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String pattern, TimeZone timeZone, Locale locale) {
        Format previousValue;
        ArrayKey key;
        Format format;
        Validate.notNull(pattern, "pattern", new Object[0]);
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if ((format = (Format)this.cInstanceCache.get(key = new ArrayKey(pattern, timeZone, locale = LocaleUtils.toLocale(locale)))) == null && (previousValue = this.cInstanceCache.putIfAbsent(key, format = this.createInstance(pattern, timeZone, locale))) != null) {
            format = previousValue;
        }
        return (F)format;
    }

    protected abstract F createInstance(String var1, TimeZone var2, Locale var3);

    private F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
        locale = LocaleUtils.toLocale(locale);
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
        Locale safeLocale = LocaleUtils.toLocale(locale);
        ArrayKey key = new ArrayKey(dateStyle, timeStyle, safeLocale);
        String pattern = (String)cDateTimeInstanceCache.get(key);
        if (pattern == null) {
            try {
                DateFormat formatter = dateStyle == null ? DateFormat.getTimeInstance(timeStyle, safeLocale) : (timeStyle == null ? DateFormat.getDateInstance(dateStyle, safeLocale) : DateFormat.getDateTimeInstance(dateStyle, timeStyle, safeLocale));
                pattern = ((SimpleDateFormat)formatter).toPattern();
                String previous = cDateTimeInstanceCache.putIfAbsent(key, pattern);
                if (previous != null) {
                    pattern = previous;
                }
            } catch (ClassCastException ex) {
                throw new IllegalArgumentException("No date time pattern for locale: " + safeLocale);
            }
        }
        return pattern;
    }

    private static final class ArrayKey {
        private final Object[] keys;
        private final int hashCode;

        private static int computeHashCode(Object[] keys) {
            int prime = 31;
            int result = 1;
            result = 31 * result + Arrays.hashCode(keys);
            return result;
        }

        ArrayKey(Object ... keys) {
            this.keys = keys;
            this.hashCode = ArrayKey.computeHashCode(keys);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            ArrayKey other = (ArrayKey)obj;
            return Arrays.deepEquals(this.keys, other.keys);
        }
    }
}

