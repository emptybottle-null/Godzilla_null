/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.date;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sqlite.date.DateParser;

public class FastDateParser
implements DateParser,
Serializable {
    private static final long serialVersionUID = 2L;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private final String pattern;
    private final TimeZone timeZone;
    private final Locale locale;
    private final int century;
    private final int startYear;
    private transient Pattern parsePattern;
    private transient Strategy[] strategies;
    private transient String currentFormatField;
    private transient Strategy nextStrategy;
    private static final Pattern formatPattern = Pattern.compile("D+|E+|F+|G+|H+|K+|M+|S+|W+|X+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++");
    private static final ConcurrentMap<Locale, Strategy>[] caches = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1){

        @Override
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            int iValue = Integer.parseInt(value);
            if (iValue < 100) {
                iValue = parser.adjustYear(iValue);
            }
            cal.set(1, iValue);
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2){

        @Override
        int modify(int iValue) {
            return iValue - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11){

        @Override
        int modify(int iValue) {
            return iValue == 24 ? 0 : iValue;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10){

        @Override
        int modify(int iValue) {
            return iValue == 12 ? 0 : iValue;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);
    private static final Strategy ISO_8601_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::?\\d{2})?))");

    protected FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    protected FastDateParser(String pattern, TimeZone timeZone, Locale locale, Date centuryStart) {
        int centuryStartYear;
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = locale;
        Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
        if (centuryStart != null) {
            definingCalendar.setTime(centuryStart);
            centuryStartYear = definingCalendar.get(1);
        } else if (locale.equals(JAPANESE_IMPERIAL)) {
            centuryStartYear = 0;
        } else {
            definingCalendar.setTime(new Date());
            centuryStartYear = definingCalendar.get(1) - 80;
        }
        this.century = centuryStartYear / 100 * 100;
        this.startYear = centuryStartYear - this.century;
        this.init(definingCalendar);
    }

    private void init(Calendar definingCalendar) {
        StringBuilder regex = new StringBuilder();
        ArrayList<Strategy> collector = new ArrayList<Strategy>();
        Matcher patternMatcher = formatPattern.matcher(this.pattern);
        if (!patternMatcher.lookingAt()) {
            throw new IllegalArgumentException("Illegal pattern character '" + this.pattern.charAt(patternMatcher.regionStart()) + "'");
        }
        this.currentFormatField = patternMatcher.group();
        Strategy currentStrategy = this.getStrategy(this.currentFormatField, definingCalendar);
        while (true) {
            patternMatcher.region(patternMatcher.end(), patternMatcher.regionEnd());
            if (!patternMatcher.lookingAt()) break;
            String nextFormatField = patternMatcher.group();
            this.nextStrategy = this.getStrategy(nextFormatField, definingCalendar);
            if (currentStrategy.addRegex(this, regex)) {
                collector.add(currentStrategy);
            }
            this.currentFormatField = nextFormatField;
            currentStrategy = this.nextStrategy;
        }
        this.nextStrategy = null;
        if (patternMatcher.regionStart() != patternMatcher.regionEnd()) {
            throw new IllegalArgumentException("Failed to parse \"" + this.pattern + "\" ; gave up at index " + patternMatcher.regionStart());
        }
        if (currentStrategy.addRegex(this, regex)) {
            collector.add(currentStrategy);
        }
        this.currentFormatField = null;
        this.strategies = collector.toArray(new Strategy[collector.size()]);
        this.parsePattern = Pattern.compile(regex.toString());
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    Pattern getParsePattern() {
        return this.parsePattern;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateParser)) {
            return false;
        }
        FastDateParser other = (FastDateParser)obj;
        return this.pattern.equals(other.pattern) && this.timeZone.equals(other.timeZone) && this.locale.equals(other.locale);
    }

    public int hashCode() {
        return this.pattern.hashCode() + 13 * (this.timeZone.hashCode() + 13 * this.locale.hashCode());
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Calendar definingCalendar = Calendar.getInstance(this.timeZone, this.locale);
        this.init(definingCalendar);
    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return this.parse(source);
    }

    @Override
    public Date parse(String source) throws ParseException {
        Date date = this.parse(source, new ParsePosition(0));
        if (date == null) {
            if (this.locale.equals(JAPANESE_IMPERIAL)) {
                throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + source + "\" does not match " + this.parsePattern.pattern(), 0);
            }
            throw new ParseException("Unparseable date: \"" + source + "\" does not match " + this.parsePattern.pattern(), 0);
        }
        return date;
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        return this.parse(source, pos);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        int offset = pos.getIndex();
        Matcher matcher = this.parsePattern.matcher(source.substring(offset));
        if (!matcher.lookingAt()) {
            return null;
        }
        Calendar cal = Calendar.getInstance(this.timeZone, this.locale);
        cal.clear();
        int i = 0;
        while (i < this.strategies.length) {
            Strategy strategy = this.strategies[i++];
            strategy.setCalendar(this, cal, matcher.group(i));
        }
        pos.setIndex(offset + matcher.end());
        return cal.getTime();
    }

    private static StringBuilder escapeRegex(StringBuilder regex, String value, boolean unquote) {
        regex.append("\\Q");
        for (int i = 0; i < value.length(); ++i) {
            int c = value.charAt(i);
            switch (c) {
                case 39: {
                    if (!unquote) break;
                    if (++i == value.length()) {
                        return regex;
                    }
                    c = value.charAt(i);
                    break;
                }
                case 92: {
                    if (++i == value.length()) break;
                    regex.append((char)c);
                    c = value.charAt(i);
                    if (c != 69) break;
                    regex.append("E\\\\E\\");
                    c = 81;
                    break;
                }
            }
            regex.append((char)c);
        }
        regex.append("\\E");
        return regex;
    }

    private static Map<String, Integer> getDisplayNames(int field, Calendar definingCalendar, Locale locale) {
        return definingCalendar.getDisplayNames(field, 0, locale);
    }

    private int adjustYear(int twoDigitYear) {
        int trial = this.century + twoDigitYear;
        return twoDigitYear >= this.startYear ? trial : trial + 100;
    }

    boolean isNextNumber() {
        return this.nextStrategy != null && this.nextStrategy.isNumber();
    }

    int getFieldWidth() {
        return this.currentFormatField.length();
    }

    private Strategy getStrategy(String formatField, Calendar definingCalendar) {
        switch (formatField.charAt(0)) {
            case '\'': {
                if (formatField.length() > 2) {
                    return new CopyQuotedStrategy(formatField.substring(1, formatField.length() - 1));
                }
            }
            default: {
                return new CopyQuotedStrategy(formatField);
            }
            case 'D': {
                return DAY_OF_YEAR_STRATEGY;
            }
            case 'E': {
                return this.getLocaleSpecificStrategy(7, definingCalendar);
            }
            case 'F': {
                return DAY_OF_WEEK_IN_MONTH_STRATEGY;
            }
            case 'G': {
                return this.getLocaleSpecificStrategy(0, definingCalendar);
            }
            case 'H': {
                return HOUR_OF_DAY_STRATEGY;
            }
            case 'K': {
                return HOUR_STRATEGY;
            }
            case 'M': {
                return formatField.length() >= 3 ? this.getLocaleSpecificStrategy(2, definingCalendar) : NUMBER_MONTH_STRATEGY;
            }
            case 'S': {
                return MILLISECOND_STRATEGY;
            }
            case 'W': {
                return WEEK_OF_MONTH_STRATEGY;
            }
            case 'a': {
                return this.getLocaleSpecificStrategy(9, definingCalendar);
            }
            case 'd': {
                return DAY_OF_MONTH_STRATEGY;
            }
            case 'h': {
                return HOUR12_STRATEGY;
            }
            case 'k': {
                return HOUR24_OF_DAY_STRATEGY;
            }
            case 'm': {
                return MINUTE_STRATEGY;
            }
            case 's': {
                return SECOND_STRATEGY;
            }
            case 'w': {
                return WEEK_OF_YEAR_STRATEGY;
            }
            case 'y': {
                return formatField.length() > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
            }
            case 'X': {
                return ISO8601TimeZoneStrategy.getStrategy(formatField.length());
            }
            case 'Z': {
                if (!formatField.equals("ZZ")) break;
                return ISO_8601_STRATEGY;
            }
            case 'z': 
        }
        return this.getLocaleSpecificStrategy(15, definingCalendar);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static ConcurrentMap<Locale, Strategy> getCache(int field) {
        ConcurrentMap<Locale, Strategy>[] concurrentMapArray = caches;
        synchronized (caches) {
            if (caches[field] == null) {
                FastDateParser.caches[field] = new ConcurrentHashMap<Locale, Strategy>(3);
            }
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return caches[field];
        }
    }

    private Strategy getLocaleSpecificStrategy(int field, Calendar definingCalendar) {
        Strategy inCache;
        ConcurrentMap<Locale, Strategy> cache = FastDateParser.getCache(field);
        Strategy strategy = (Strategy)cache.get(this.locale);
        if (strategy == null && (inCache = cache.putIfAbsent(this.locale, strategy = field == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, this.locale))) != null) {
            return inCache;
        }
        return strategy;
    }

    private static class ISO8601TimeZoneStrategy
    extends Strategy {
        private final String pattern;
        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");

        ISO8601TimeZoneStrategy(String pattern) {
            this.pattern = pattern;
        }

        @Override
        boolean addRegex(FastDateParser parser, StringBuilder regex) {
            regex.append(this.pattern);
            return true;
        }

        @Override
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            if (value.equals("Z")) {
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            } else {
                cal.setTimeZone(TimeZone.getTimeZone("GMT" + value));
            }
        }

        static Strategy getStrategy(int tokenLen) {
            switch (tokenLen) {
                case 1: {
                    return ISO_8601_1_STRATEGY;
                }
                case 2: {
                    return ISO_8601_2_STRATEGY;
                }
                case 3: {
                    return ISO_8601_3_STRATEGY;
                }
            }
            throw new IllegalArgumentException("invalid number of X");
        }
    }

    private static class TimeZoneStrategy
    extends Strategy {
        private final String validTimeZoneChars;
        private final SortedMap<String, TimeZone> tzNames = new TreeMap<String, TimeZone>(String.CASE_INSENSITIVE_ORDER);
        private static final int ID = 0;
        private static final int LONG_STD = 1;
        private static final int SHORT_STD = 2;
        private static final int LONG_DST = 3;
        private static final int SHORT_DST = 4;

        TimeZoneStrategy(Locale locale) {
            String[][] zones;
            for (String[] zone : zones = DateFormatSymbols.getInstance(locale).getZoneStrings()) {
                if (zone[0].startsWith("GMT")) continue;
                TimeZone tz = TimeZone.getTimeZone(zone[0]);
                if (!this.tzNames.containsKey(zone[1])) {
                    this.tzNames.put(zone[1], tz);
                }
                if (!this.tzNames.containsKey(zone[2])) {
                    this.tzNames.put(zone[2], tz);
                }
                if (!tz.useDaylightTime()) continue;
                if (!this.tzNames.containsKey(zone[3])) {
                    this.tzNames.put(zone[3], tz);
                }
                if (this.tzNames.containsKey(zone[4])) continue;
                this.tzNames.put(zone[4], tz);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("(GMT[+-]\\d{1,2}:\\d{2}").append('|');
            sb.append("[+-]\\d{4}").append('|');
            for (String id : this.tzNames.keySet()) {
                FastDateParser.escapeRegex(sb, id, false).append('|');
            }
            sb.setCharAt(sb.length() - 1, ')');
            this.validTimeZoneChars = sb.toString();
        }

        @Override
        boolean addRegex(FastDateParser parser, StringBuilder regex) {
            regex.append(this.validTimeZoneChars);
            return true;
        }

        @Override
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            TimeZone tz;
            if (value.charAt(0) == '+' || value.charAt(0) == '-') {
                tz = TimeZone.getTimeZone("GMT" + value);
            } else if (value.startsWith("GMT")) {
                tz = TimeZone.getTimeZone(value);
            } else {
                tz = (TimeZone)this.tzNames.get(value);
                if (tz == null) {
                    throw new IllegalArgumentException(value + " is not a supported timezone name");
                }
            }
            cal.setTimeZone(tz);
        }
    }

    private static class NumberStrategy
    extends Strategy {
        private final int field;

        NumberStrategy(int field) {
            this.field = field;
        }

        @Override
        boolean isNumber() {
            return true;
        }

        @Override
        boolean addRegex(FastDateParser parser, StringBuilder regex) {
            if (parser.isNextNumber()) {
                regex.append("(\\p{Nd}{").append(parser.getFieldWidth()).append("}+)");
            } else {
                regex.append("(\\p{Nd}++)");
            }
            return true;
        }

        @Override
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            cal.set(this.field, this.modify(Integer.parseInt(value)));
        }

        int modify(int iValue) {
            return iValue;
        }
    }

    private static class CaseInsensitiveTextStrategy
    extends Strategy {
        private final int field;
        private final Locale locale;
        private final Map<String, Integer> lKeyValues;

        CaseInsensitiveTextStrategy(int field, Calendar definingCalendar, Locale locale) {
            this.field = field;
            this.locale = locale;
            Map keyValues = FastDateParser.getDisplayNames(field, definingCalendar, locale);
            this.lKeyValues = new HashMap<String, Integer>();
            for (Map.Entry entry : keyValues.entrySet()) {
                this.lKeyValues.put(((String)entry.getKey()).toLowerCase(locale), (Integer)entry.getValue());
            }
        }

        @Override
        boolean addRegex(FastDateParser parser, StringBuilder regex) {
            regex.append("((?iu)");
            for (String textKeyValue : this.lKeyValues.keySet()) {
                FastDateParser.escapeRegex(regex, textKeyValue, false).append('|');
            }
            regex.setCharAt(regex.length() - 1, ')');
            return true;
        }

        @Override
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            Integer iVal = this.lKeyValues.get(value.toLowerCase(this.locale));
            if (iVal == null) {
                StringBuilder sb = new StringBuilder(value);
                sb.append(" not in (");
                for (String textKeyValue : this.lKeyValues.keySet()) {
                    sb.append(textKeyValue).append(' ');
                }
                sb.setCharAt(sb.length() - 1, ')');
                throw new IllegalArgumentException(sb.toString());
            }
            cal.set(this.field, iVal);
        }
    }

    private static class CopyQuotedStrategy
    extends Strategy {
        private final String formatField;

        CopyQuotedStrategy(String formatField) {
            this.formatField = formatField;
        }

        @Override
        boolean isNumber() {
            char c = this.formatField.charAt(0);
            if (c == '\'') {
                c = this.formatField.charAt(1);
            }
            return Character.isDigit(c);
        }

        @Override
        boolean addRegex(FastDateParser parser, StringBuilder regex) {
            FastDateParser.escapeRegex(regex, this.formatField, true);
            return false;
        }
    }

    private static abstract class Strategy {
        private Strategy() {
        }

        boolean isNumber() {
            return false;
        }

        void setCalendar(FastDateParser parser, Calendar cal, String value) {
        }

        abstract boolean addRegex(FastDateParser var1, StringBuilder var2);
    }
}

