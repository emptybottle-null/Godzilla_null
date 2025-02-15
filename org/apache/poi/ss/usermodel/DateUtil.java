/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.util.LocaleUtil;

public class DateUtil {
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int HOURS_PER_DAY = 24;
    public static final int SECONDS_PER_DAY = 86400;
    private static final int BAD_DATE = -1;
    public static final long DAY_MILLISECONDS = 86400000L;
    private static final BigDecimal BD_NANOSEC_DAY = BigDecimal.valueOf(8.64E13);
    private static final BigDecimal BD_MILISEC_RND = BigDecimal.valueOf(500000.0);
    private static final BigDecimal BD_SECOND_RND = BigDecimal.valueOf(5.0E8);
    private static final Pattern TIME_SEPARATOR_PATTERN = Pattern.compile(":");
    private static final Pattern date_ptrn1 = Pattern.compile("^\\[\\$\\-.*?\\]");
    private static final Pattern date_ptrn2 = Pattern.compile("^\\[[a-zA-Z]+\\]");
    private static final Pattern date_ptrn3a = Pattern.compile("[yYmMdDhHsS]");
    private static final Pattern date_ptrn3b = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/\u5e74\u6708\u65e5,. :\"\\\\]+0*[ampAMP/]*$");
    private static final Pattern date_ptrn4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)\\]");
    private static final Pattern date_ptrn5 = Pattern.compile("^\\[DBNum(1|2|3)\\]");
    private static final DateTimeFormatter dateTimeFormats = new DateTimeFormatterBuilder().appendPattern("[dd MMM[ yyyy]][[ ]h:m[:s] a][[ ]H:m[:s]]").appendPattern("[[yyyy ]dd-MMM[-yyyy]][[ ]h:m[:s] a][[ ]H:m[:s]]").appendPattern("[M/dd[/yyyy]][[ ]h:m[:s] a][[ ]H:m[:s]]").appendPattern("[[yyyy/]M/dd][[ ]h:m[:s] a][[ ]H:m[:s]]").parseDefaulting(ChronoField.YEAR_OF_ERA, LocaleUtil.getLocaleCalendar().get(1)).toFormatter();
    private static ThreadLocal<Integer> lastFormatIndex = ThreadLocal.withInitial(() -> -1);
    private static ThreadLocal<String> lastFormatString = new ThreadLocal();
    private static ThreadLocal<Boolean> lastCachedResult = new ThreadLocal();

    protected DateUtil() {
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Calendar date) {
        return date.toInstant().atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDateTime();
    }

    public static double getExcelDate(LocalDate date) {
        return DateUtil.getExcelDate(date, false);
    }

    public static double getExcelDate(LocalDate date, boolean use1904windowing) {
        int year = date.getYear();
        int dayOfYear = date.getDayOfYear();
        int hour = 0;
        int minute = 0;
        int second = 0;
        int milliSecond = 0;
        return DateUtil.internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    public static double getExcelDate(LocalDateTime date) {
        return DateUtil.getExcelDate(date, false);
    }

    public static double getExcelDate(LocalDateTime date, boolean use1904windowing) {
        int year = date.getYear();
        int dayOfYear = date.getDayOfYear();
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        int milliSecond = date.getNano() / 1000000;
        return DateUtil.internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    public static double getExcelDate(Date date) {
        return DateUtil.getExcelDate(date, false);
    }

    public static double getExcelDate(Date date, boolean use1904windowing) {
        Calendar calStart = LocaleUtil.getLocaleCalendar();
        calStart.setTime(date);
        int year = calStart.get(1);
        int dayOfYear = calStart.get(6);
        int hour = calStart.get(11);
        int minute = calStart.get(12);
        int second = calStart.get(13);
        int milliSecond = calStart.get(14);
        return DateUtil.internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    public static double getExcelDate(Calendar date, boolean use1904windowing) {
        int year = date.get(1);
        int dayOfYear = date.get(6);
        int hour = date.get(11);
        int minute = date.get(12);
        int second = date.get(13);
        int milliSecond = date.get(14);
        return DateUtil.internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    private static double internalGetExcelDate(int year, int dayOfYear, int hour, int minute, int second, int milliSecond, boolean use1904windowing) {
        if (!use1904windowing && year < 1900 || use1904windowing && year < 1904) {
            return -1.0;
        }
        double fraction = ((((double)hour * 60.0 + (double)minute) * 60.0 + (double)second) * 1000.0 + (double)milliSecond) / 8.64E7;
        double value = fraction + (double)DateUtil.absoluteDay(year, dayOfYear, use1904windowing);
        if (!use1904windowing && value >= 60.0) {
            value += 1.0;
        } else if (use1904windowing) {
            value -= 1.0;
        }
        return value;
    }

    public static Date getJavaDate(double date, TimeZone tz) {
        return DateUtil.getJavaDate(date, false, tz, false);
    }

    public static Date getJavaDate(double date) {
        return DateUtil.getJavaDate(date, false, null, false);
    }

    public static Date getJavaDate(double date, boolean use1904windowing, TimeZone tz) {
        return DateUtil.getJavaDate(date, use1904windowing, tz, false);
    }

    public static Date getJavaDate(double date, boolean use1904windowing, TimeZone tz, boolean roundSeconds) {
        Calendar calendar = DateUtil.getJavaCalendar(date, use1904windowing, tz, roundSeconds);
        return calendar == null ? null : calendar.getTime();
    }

    public static Date getJavaDate(double date, boolean use1904windowing) {
        return DateUtil.getJavaDate(date, use1904windowing, null, false);
    }

    public static LocalDateTime getLocalDateTime(double date) {
        return DateUtil.getLocalDateTime(date, false, false);
    }

    public static LocalDateTime getLocalDateTime(double date, boolean use1904windowing) {
        return DateUtil.getLocalDateTime(date, use1904windowing, false);
    }

    public static LocalDateTime getLocalDateTime(double date, boolean use1904windowing, boolean roundSeconds) {
        if (!DateUtil.isValidExcelDate(date)) {
            return null;
        }
        BigDecimal bd = new BigDecimal(date);
        int wholeDays = bd.intValue();
        int startYear = 1900;
        int dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        LocalDateTime ldt = LocalDateTime.of(startYear, 1, 1, 0, 0);
        ldt = ldt.plusDays(wholeDays + dayAdjust - 1);
        long nanosTime = bd.subtract(BigDecimal.valueOf(wholeDays)).multiply(BD_NANOSEC_DAY).add(roundSeconds ? BD_SECOND_RND : BD_MILISEC_RND).longValue();
        ldt = ldt.plusNanos(nanosTime);
        ldt = ldt.truncatedTo(roundSeconds ? ChronoUnit.SECONDS : ChronoUnit.MILLIS);
        return ldt;
    }

    public static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing, boolean roundSeconds) {
        int startYear = 1900;
        int dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(14, millisecondsInDay);
        if (calendar.get(14) == 0) {
            calendar.clear(14);
        }
        if (roundSeconds) {
            calendar.add(14, 500);
            calendar.clear(14);
        }
    }

    public static Calendar getJavaCalendar(double date) {
        return DateUtil.getJavaCalendar(date, false, null, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing) {
        return DateUtil.getJavaCalendar(date, use1904windowing, null, false);
    }

    public static Calendar getJavaCalendarUTC(double date, boolean use1904windowing) {
        return DateUtil.getJavaCalendar(date, use1904windowing, LocaleUtil.TIMEZONE_UTC, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing, TimeZone timeZone) {
        return DateUtil.getJavaCalendar(date, use1904windowing, timeZone, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing, TimeZone timeZone, boolean roundSeconds) {
        if (!DateUtil.isValidExcelDate(date)) {
            return null;
        }
        int wholeDays = (int)Math.floor(date);
        int millisecondsInDay = (int)((date - (double)wholeDays) * 8.64E7 + 0.5);
        Calendar calendar = timeZone != null ? LocaleUtil.getLocaleCalendar(timeZone) : LocaleUtil.getLocaleCalendar();
        DateUtil.setCalendar(calendar, wholeDays, millisecondsInDay, use1904windowing, roundSeconds);
        return calendar;
    }

    private static boolean isCached(String formatString, int formatIndex) {
        return formatIndex == lastFormatIndex.get() && formatString.equals(lastFormatString.get());
    }

    private static void cache(String formatString, int formatIndex, boolean cached) {
        if (formatString == null || "".equals(formatString)) {
            lastFormatString.remove();
        } else {
            lastFormatString.set(formatString);
        }
        if (formatIndex == -1) {
            lastFormatIndex.remove();
        } else {
            lastFormatIndex.set(formatIndex);
        }
        lastCachedResult.set(cached);
    }

    public static boolean isADateFormat(ExcelNumberFormat numFmt) {
        if (numFmt == null) {
            return false;
        }
        return DateUtil.isADateFormat(numFmt.getIdx(), numFmt.getFormat());
    }

    public static boolean isADateFormat(int formatIndex, String formatString) {
        if (DateUtil.isInternalDateFormat(formatIndex)) {
            DateUtil.cache(formatString, formatIndex, true);
            return true;
        }
        if (formatString == null || formatString.length() == 0) {
            return false;
        }
        if (DateUtil.isCached(formatString, formatIndex)) {
            return lastCachedResult.get();
        }
        String fs = formatString;
        int length = fs.length();
        StringBuilder sb = new StringBuilder(length);
        block3: for (int i = 0; i < length; ++i) {
            char c = fs.charAt(i);
            if (i < length - 1) {
                char nc = fs.charAt(i + 1);
                if (c == '\\') {
                    switch (nc) {
                        case ' ': 
                        case ',': 
                        case '-': 
                        case '.': 
                        case '\\': {
                            continue block3;
                        }
                    }
                } else if (c == ';' && nc == '@') {
                    ++i;
                    continue;
                }
            }
            sb.append(c);
        }
        fs = sb.toString();
        if (date_ptrn4.matcher(fs).matches()) {
            DateUtil.cache(formatString, formatIndex, true);
            return true;
        }
        fs = date_ptrn5.matcher(fs).replaceAll("");
        fs = date_ptrn1.matcher(fs).replaceAll("");
        int separatorIndex = (fs = date_ptrn2.matcher(fs).replaceAll("")).indexOf(59);
        if (0 < separatorIndex && separatorIndex < fs.length() - 1) {
            fs = fs.substring(0, separatorIndex);
        }
        if (!date_ptrn3a.matcher(fs).find()) {
            return false;
        }
        boolean result = date_ptrn3b.matcher(fs).matches();
        DateUtil.cache(formatString, formatIndex, result);
        return result;
    }

    public static boolean isInternalDateFormat(int format) {
        switch (format) {
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 45: 
            case 46: 
            case 47: {
                return true;
            }
        }
        return false;
    }

    public static boolean isCellDateFormatted(Cell cell) {
        return DateUtil.isCellDateFormatted(cell, null);
    }

    public static boolean isCellDateFormatted(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        if (cell == null) {
            return false;
        }
        boolean bDate = false;
        double d = cell.getNumericCellValue();
        if (DateUtil.isValidExcelDate(d)) {
            ExcelNumberFormat nf = ExcelNumberFormat.from(cell, cfEvaluator);
            if (nf == null) {
                return false;
            }
            bDate = DateUtil.isADateFormat(nf);
        }
        return bDate;
    }

    public static boolean isCellInternalDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        boolean bDate = false;
        double d = cell.getNumericCellValue();
        if (DateUtil.isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            short i = style.getDataFormat();
            bDate = DateUtil.isInternalDateFormat(i);
        }
        return bDate;
    }

    public static boolean isValidExcelDate(double value) {
        return value > -4.9E-324;
    }

    protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
        return DateUtil.absoluteDay(cal.get(1), cal.get(6), use1904windowing);
    }

    protected static int absoluteDay(LocalDateTime date, boolean use1904windowing) {
        return DateUtil.absoluteDay(date.getYear(), date.getDayOfYear(), use1904windowing);
    }

    private static int absoluteDay(int year, int dayOfYear, boolean use1904windowing) {
        return dayOfYear + DateUtil.daysInPriorYears(year, use1904windowing);
    }

    static int daysInPriorYears(int yr, boolean use1904windowing) {
        if (!use1904windowing && yr < 1900 || use1904windowing && yr < 1904) {
            throw new IllegalArgumentException("'year' must be 1900 or greater");
        }
        int yr1 = yr - 1;
        int leapDays = yr1 / 4 - yr1 / 100 + yr1 / 400 - 460;
        return 365 * (yr - (use1904windowing ? 1904 : 1900)) + leapDays;
    }

    private static Calendar dayStart(Calendar cal) {
        cal.get(11);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.get(11);
        return cal;
    }

    public static double convertTime(String timeStr) {
        try {
            return DateUtil.convertTimeInternal(timeStr);
        } catch (FormatException e) {
            String msg = "Bad time format '" + timeStr + "' expected 'HH:MM' or 'HH:MM:SS' - " + e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

    private static double convertTimeInternal(String timeStr) throws FormatException {
        String secStr;
        int len = timeStr.length();
        if (len < 4 || len > 8) {
            throw new FormatException("Bad length");
        }
        String[] parts = TIME_SEPARATOR_PATTERN.split(timeStr);
        switch (parts.length) {
            case 2: {
                secStr = "00";
                break;
            }
            case 3: {
                secStr = parts[2];
                break;
            }
            default: {
                throw new FormatException("Expected 2 or 3 fields but got (" + parts.length + ")");
            }
        }
        String hourStr = parts[0];
        String minStr = parts[1];
        int hours = DateUtil.parseInt(hourStr, "hour", 24);
        int minutes = DateUtil.parseInt(minStr, "minute", 60);
        int seconds = DateUtil.parseInt(secStr, "second", 60);
        double totalSeconds = (double)seconds + ((double)minutes + (double)hours * 60.0) * 60.0;
        return totalSeconds / 86400.0;
    }

    public static Date parseYYYYMMDDDate(String dateStr) {
        try {
            return DateUtil.parseYYYYMMDDDateInternal(dateStr);
        } catch (FormatException e) {
            String msg = "Bad time format " + dateStr + " expected 'YYYY/MM/DD' - " + e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

    private static Date parseYYYYMMDDDateInternal(String timeStr) throws FormatException {
        if (timeStr.length() != 10) {
            throw new FormatException("Bad length");
        }
        String yearStr = timeStr.substring(0, 4);
        String monthStr = timeStr.substring(5, 7);
        String dayStr = timeStr.substring(8, 10);
        int year = DateUtil.parseInt(yearStr, "year", Short.MIN_VALUE, Short.MAX_VALUE);
        int month = DateUtil.parseInt(monthStr, "month", 1, 12);
        int day = DateUtil.parseInt(dayStr, "day", 1, 31);
        Calendar cal = LocaleUtil.getLocaleCalendar(year, month - 1, day);
        return cal.getTime();
    }

    private static int parseInt(String strVal, String fieldName, int rangeMax) throws FormatException {
        return DateUtil.parseInt(strVal, fieldName, 0, rangeMax - 1);
    }

    private static int parseInt(String strVal, String fieldName, int lowerLimit, int upperLimit) throws FormatException {
        int result;
        try {
            result = Integer.parseInt(strVal);
        } catch (NumberFormatException e) {
            throw new FormatException("Bad int format '" + strVal + "' for " + fieldName + " field");
        }
        if (result < lowerLimit || result > upperLimit) {
            throw new FormatException(fieldName + " value (" + result + ") is outside the allowable range(0.." + upperLimit + ")");
        }
        return result;
    }

    public static Double parseDateTime(String str) {
        TemporalAccessor tmp = dateTimeFormats.parse(str.replaceAll("\\s+", " "));
        LocalTime time = tmp.query(TemporalQueries.localTime());
        LocalDate date = tmp.query(TemporalQueries.localDate());
        if (time == null && date == null) {
            return null;
        }
        double tm = 0.0;
        if (date != null) {
            Date d = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            tm = DateUtil.getExcelDate(d);
        }
        if (time != null) {
            tm += 1.0 * (double)time.toSecondOfDay() / 86400.0;
        }
        return tm;
    }

    private static final class FormatException
    extends Exception {
        public FormatException(String msg) {
            super(msg);
        }
    }
}

