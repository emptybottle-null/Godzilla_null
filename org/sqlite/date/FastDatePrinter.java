/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.date;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.sqlite.date.DatePrinter;

public class FastDatePrinter
implements DatePrinter,
Serializable {
    private static final long serialVersionUID = 1L;
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private final String mPattern;
    private final TimeZone mTimeZone;
    private final Locale mLocale;
    private transient Rule[] mRules;
    private transient int mMaxLengthEstimate;
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap<TimeZoneDisplayKey, String>(7);

    protected FastDatePrinter(String pattern, TimeZone timeZone, Locale locale) {
        this.mPattern = pattern;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        this.init();
    }

    private void init() {
        List<Rule> rulesList = this.parsePattern();
        this.mRules = rulesList.toArray(new Rule[rulesList.size()]);
        int len = 0;
        int i = this.mRules.length;
        while (--i >= 0) {
            len += this.mRules[i].estimateLength();
        }
        this.mMaxLengthEstimate = len;
    }

    protected List<Rule> parsePattern() {
        DateFormatSymbols symbols = new DateFormatSymbols(this.mLocale);
        ArrayList<Rule> rules = new ArrayList<Rule>();
        String[] ERAs = symbols.getEras();
        String[] months = symbols.getMonths();
        String[] shortMonths = symbols.getShortMonths();
        String[] weekdays = symbols.getWeekdays();
        String[] shortWeekdays = symbols.getShortWeekdays();
        String[] AmPmStrings = symbols.getAmPmStrings();
        int length = this.mPattern.length();
        int[] indexRef = new int[1];
        for (int i = 0; i < length; ++i) {
            Rule rule;
            indexRef[0] = i;
            String token = this.parseToken(this.mPattern, indexRef);
            i = indexRef[0];
            int tokenLen = token.length();
            if (tokenLen == 0) break;
            char c = token.charAt(0);
            switch (c) {
                case 'G': {
                    rule = new TextField(0, ERAs);
                    break;
                }
                case 'y': {
                    if (tokenLen == 2) {
                        rule = TwoDigitYearField.INSTANCE;
                        break;
                    }
                    rule = this.selectNumberRule(1, tokenLen < 4 ? 4 : tokenLen);
                    break;
                }
                case 'M': {
                    if (tokenLen >= 4) {
                        rule = new TextField(2, months);
                        break;
                    }
                    if (tokenLen == 3) {
                        rule = new TextField(2, shortMonths);
                        break;
                    }
                    if (tokenLen == 2) {
                        rule = TwoDigitMonthField.INSTANCE;
                        break;
                    }
                    rule = UnpaddedMonthField.INSTANCE;
                    break;
                }
                case 'd': {
                    rule = this.selectNumberRule(5, tokenLen);
                    break;
                }
                case 'h': {
                    rule = new TwelveHourField(this.selectNumberRule(10, tokenLen));
                    break;
                }
                case 'H': {
                    rule = this.selectNumberRule(11, tokenLen);
                    break;
                }
                case 'm': {
                    rule = this.selectNumberRule(12, tokenLen);
                    break;
                }
                case 's': {
                    rule = this.selectNumberRule(13, tokenLen);
                    break;
                }
                case 'S': {
                    rule = this.selectNumberRule(14, tokenLen);
                    break;
                }
                case 'E': {
                    rule = new TextField(7, tokenLen < 4 ? shortWeekdays : weekdays);
                    break;
                }
                case 'D': {
                    rule = this.selectNumberRule(6, tokenLen);
                    break;
                }
                case 'F': {
                    rule = this.selectNumberRule(8, tokenLen);
                    break;
                }
                case 'w': {
                    rule = this.selectNumberRule(3, tokenLen);
                    break;
                }
                case 'W': {
                    rule = this.selectNumberRule(4, tokenLen);
                    break;
                }
                case 'a': {
                    rule = new TextField(9, AmPmStrings);
                    break;
                }
                case 'k': {
                    rule = new TwentyFourHourField(this.selectNumberRule(11, tokenLen));
                    break;
                }
                case 'K': {
                    rule = this.selectNumberRule(10, tokenLen);
                    break;
                }
                case 'X': {
                    rule = Iso8601_Rule.getRule(tokenLen);
                    break;
                }
                case 'z': {
                    if (tokenLen >= 4) {
                        rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
                        break;
                    }
                    rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
                    break;
                }
                case 'Z': {
                    if (tokenLen == 1) {
                        rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                        break;
                    }
                    if (tokenLen == 2) {
                        rule = TimeZoneNumberRule.INSTANCE_ISO_8601;
                        break;
                    }
                    rule = TimeZoneNumberRule.INSTANCE_COLON;
                    break;
                }
                case '\'': {
                    String sub = token.substring(1);
                    if (sub.length() == 1) {
                        rule = new CharacterLiteral(sub.charAt(0));
                        break;
                    }
                    rule = new StringLiteral(sub);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Illegal pattern component: " + token);
                }
            }
            rules.add(rule);
        }
        return rules;
    }

    protected String parseToken(String pattern, int[] indexRef) {
        int i;
        StringBuilder buf = new StringBuilder();
        int length = pattern.length();
        char c = pattern.charAt(i);
        if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            char peek;
            buf.append(c);
            while (i + 1 < length && (peek = pattern.charAt(i + 1)) == c) {
                buf.append(c);
                ++i;
            }
        } else {
            buf.append('\'');
            boolean inLiteral = false;
            for (i = indexRef[0]; i < length; ++i) {
                c = pattern.charAt(i);
                if (c == '\'') {
                    if (i + 1 < length && pattern.charAt(i + 1) == '\'') {
                        ++i;
                        buf.append(c);
                        continue;
                    }
                    inLiteral = !inLiteral;
                    continue;
                }
                if (inLiteral || (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                    buf.append(c);
                    continue;
                }
                break;
            }
        }
        indexRef[0] = --i;
        return buf.toString();
    }

    protected NumberRule selectNumberRule(int field, int padding) {
        switch (padding) {
            case 1: {
                return new UnpaddedNumberField(field);
            }
            case 2: {
                return new TwoDigitNumberField(field);
            }
        }
        return new PaddedNumberField(field, padding);
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof Date) {
            return this.format((Date)obj, toAppendTo);
        }
        if (obj instanceof Calendar) {
            return this.format((Calendar)obj, toAppendTo);
        }
        if (obj instanceof Long) {
            return this.format((Long)obj, toAppendTo);
        }
        throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
    }

    @Override
    public String format(long millis) {
        GregorianCalendar c = this.newCalendar();
        c.setTimeInMillis(millis);
        return this.applyRulesToString(c);
    }

    private String applyRulesToString(Calendar c) {
        return this.applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    private GregorianCalendar newCalendar() {
        return new GregorianCalendar(this.mTimeZone, this.mLocale);
    }

    @Override
    public String format(Date date) {
        GregorianCalendar c = this.newCalendar();
        c.setTime(date);
        return this.applyRulesToString(c);
    }

    @Override
    public String format(Calendar calendar) {
        return this.format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    @Override
    public StringBuffer format(long millis, StringBuffer buf) {
        return this.format(new Date(millis), buf);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer buf) {
        GregorianCalendar c = this.newCalendar();
        c.setTime(date);
        return this.applyRules(c, buf);
    }

    @Override
    public StringBuffer format(Calendar calendar, StringBuffer buf) {
        return this.applyRules(calendar, buf);
    }

    protected StringBuffer applyRules(Calendar calendar, StringBuffer buf) {
        for (Rule rule : this.mRules) {
            rule.appendTo(buf, calendar);
        }
        return buf;
    }

    @Override
    public String getPattern() {
        return this.mPattern;
    }

    @Override
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    @Override
    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDatePrinter)) {
            return false;
        }
        FastDatePrinter other = (FastDatePrinter)obj;
        return this.mPattern.equals(other.mPattern) && this.mTimeZone.equals(other.mTimeZone) && this.mLocale.equals(other.mLocale);
    }

    public int hashCode() {
        return this.mPattern.hashCode() + 13 * (this.mTimeZone.hashCode() + 13 * this.mLocale.hashCode());
    }

    public String toString() {
        return "FastDatePrinter[" + this.mPattern + "," + this.mLocale + "," + this.mTimeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.init();
    }

    private static void appendDigits(StringBuffer buffer, int value) {
        buffer.append((char)(value / 10 + 48));
        buffer.append((char)(value % 10 + 48));
    }

    static String getTimeZoneDisplay(TimeZone tz, boolean daylight, int style, Locale locale) {
        String prior;
        TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
        String value = (String)cTimeZoneDisplayCache.get(key);
        if (value == null && (prior = cTimeZoneDisplayCache.putIfAbsent(key, value = tz.getDisplayName(daylight, style, locale))) != null) {
            value = prior;
        }
        return value;
    }

    private static class TimeZoneDisplayKey {
        private final TimeZone mTimeZone;
        private final int mStyle;
        private final Locale mLocale;

        TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale) {
            this.mTimeZone = timeZone;
            this.mStyle = daylight ? style | Integer.MIN_VALUE : style;
            this.mLocale = locale;
        }

        public int hashCode() {
            return (this.mStyle * 31 + this.mLocale.hashCode()) * 31 + this.mTimeZone.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
                return this.mTimeZone.equals(other.mTimeZone) && this.mStyle == other.mStyle && this.mLocale.equals(other.mLocale);
            }
            return false;
        }
    }

    private static class Iso8601_Rule
    implements Rule {
        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(3);
        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(5);
        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(6);
        final int length;

        static Iso8601_Rule getRule(int tokenLen) {
            switch (tokenLen) {
                case 1: {
                    return ISO8601_HOURS;
                }
                case 2: {
                    return ISO8601_HOURS_MINUTES;
                }
                case 3: {
                    return ISO8601_HOURS_COLON_MINUTES;
                }
            }
            throw new IllegalArgumentException("invalid number of X");
        }

        Iso8601_Rule(int length) {
            this.length = length;
        }

        @Override
        public int estimateLength() {
            return this.length;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int zoneOffset = calendar.get(15);
            if (zoneOffset == 0) {
                buffer.append("Z");
                return;
            }
            int offset = zoneOffset + calendar.get(16);
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            } else {
                buffer.append('+');
            }
            int hours = offset / 3600000;
            FastDatePrinter.appendDigits(buffer, hours);
            if (this.length < 5) {
                return;
            }
            if (this.length == 6) {
                buffer.append(':');
            }
            int minutes = offset / 60000 - 60 * hours;
            FastDatePrinter.appendDigits(buffer, minutes);
        }
    }

    private static class TimeZoneNumberRule
    implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true, false);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false, false);
        static final TimeZoneNumberRule INSTANCE_ISO_8601 = new TimeZoneNumberRule(true, true);
        final boolean mColon;
        final boolean mISO8601;

        TimeZoneNumberRule(boolean colon, boolean iso8601) {
            this.mColon = colon;
            this.mISO8601 = iso8601;
        }

        @Override
        public int estimateLength() {
            return 5;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            if (this.mISO8601 && calendar.getTimeZone().getID().equals("UTC")) {
                buffer.append("Z");
                return;
            }
            int offset = calendar.get(15) + calendar.get(16);
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            } else {
                buffer.append('+');
            }
            int hours = offset / 3600000;
            FastDatePrinter.appendDigits(buffer, hours);
            if (this.mColon) {
                buffer.append(':');
            }
            int minutes = offset / 60000 - 60 * hours;
            FastDatePrinter.appendDigits(buffer, minutes);
        }
    }

    private static class TimeZoneNameRule
    implements Rule {
        private final Locale mLocale;
        private final int mStyle;
        private final String mStandard;
        private final String mDaylight;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int style) {
            this.mLocale = locale;
            this.mStyle = style;
            this.mStandard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, style, locale);
            this.mDaylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, style, locale);
        }

        @Override
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            TimeZone zone = calendar.getTimeZone();
            if (calendar.get(16) != 0) {
                buffer.append(FastDatePrinter.getTimeZoneDisplay(zone, true, this.mStyle, this.mLocale));
            } else {
                buffer.append(FastDatePrinter.getTimeZoneDisplay(zone, false, this.mStyle, this.mLocale));
            }
        }
    }

    private static class TwentyFourHourField
    implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule rule) {
            this.mRule = rule;
        }

        @Override
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int value = calendar.get(11);
            if (value == 0) {
                value = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(buffer, value);
        }

        @Override
        public void appendTo(StringBuffer buffer, int value) {
            this.mRule.appendTo(buffer, value);
        }
    }

    private static class TwelveHourField
    implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule rule) {
            this.mRule = rule;
        }

        @Override
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int value = calendar.get(10);
            if (value == 0) {
                value = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(buffer, value);
        }

        @Override
        public void appendTo(StringBuffer buffer, int value) {
            this.mRule.appendTo(buffer, value);
        }
    }

    private static class TwoDigitMonthField
    implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        TwoDigitMonthField() {
        }

        @Override
        public int estimateLength() {
            return 2;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(2) + 1);
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            FastDatePrinter.appendDigits(buffer, value);
        }
    }

    private static class TwoDigitYearField
    implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        TwoDigitYearField() {
        }

        @Override
        public int estimateLength() {
            return 2;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(1) % 100);
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            FastDatePrinter.appendDigits(buffer, value);
        }
    }

    private static class TwoDigitNumberField
    implements NumberRule {
        private final int mField;

        TwoDigitNumberField(int field) {
            this.mField = field;
        }

        @Override
        public int estimateLength() {
            return 2;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 100) {
                FastDatePrinter.appendDigits(buffer, value);
            } else {
                buffer.append(value);
            }
        }
    }

    private static class PaddedNumberField
    implements NumberRule {
        private final int mField;
        private final int mSize;

        PaddedNumberField(int field, int size) {
            if (size < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = field;
            this.mSize = size;
        }

        @Override
        public int estimateLength() {
            return this.mSize;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            for (int digit = 0; digit < this.mSize; ++digit) {
                buffer.append('0');
            }
            int index = buffer.length();
            while (value > 0) {
                buffer.setCharAt(--index, (char)(48 + value % 10));
                value /= 10;
            }
        }
    }

    private static class UnpaddedMonthField
    implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        UnpaddedMonthField() {
        }

        @Override
        public int estimateLength() {
            return 2;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(2) + 1);
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 10) {
                buffer.append((char)(value + 48));
            } else {
                FastDatePrinter.appendDigits(buffer, value);
            }
        }
    }

    private static class UnpaddedNumberField
    implements NumberRule {
        private final int mField;

        UnpaddedNumberField(int field) {
            this.mField = field;
        }

        @Override
        public int estimateLength() {
            return 4;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }

        @Override
        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 10) {
                buffer.append((char)(value + 48));
            } else if (value < 100) {
                FastDatePrinter.appendDigits(buffer, value);
            } else {
                buffer.append(value);
            }
        }
    }

    private static class TextField
    implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int field, String[] values) {
            this.mField = field;
            this.mValues = values;
        }

        @Override
        public int estimateLength() {
            int max = 0;
            int i = this.mValues.length;
            while (--i >= 0) {
                int len = this.mValues[i].length();
                if (len <= max) continue;
                max = len;
            }
            return max;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    private static class StringLiteral
    implements Rule {
        private final String mValue;

        StringLiteral(String value) {
            this.mValue = value;
        }

        @Override
        public int estimateLength() {
            return this.mValue.length();
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValue);
        }
    }

    private static class CharacterLiteral
    implements Rule {
        private final char mValue;

        CharacterLiteral(char value) {
            this.mValue = value;
        }

        @Override
        public int estimateLength() {
            return 1;
        }

        @Override
        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValue);
        }
    }

    private static interface NumberRule
    extends Rule {
        public void appendTo(StringBuffer var1, int var2);
    }

    private static interface Rule {
        public int estimateLength();

        public void appendTo(StringBuffer var1, Calendar var2);
    }
}

