/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import java.util.Calendar;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;

@Internal
final class YearFracCalculator {
    private static final int MS_PER_HOUR = 3600000;
    private static final int MS_PER_DAY = 86400000;
    private static final int DAYS_PER_NORMAL_YEAR = 365;
    private static final int DAYS_PER_LEAP_YEAR = 366;
    private static final int LONG_MONTH_LEN = 31;
    private static final int SHORT_MONTH_LEN = 30;
    private static final int SHORT_FEB_LEN = 28;
    private static final int LONG_FEB_LEN = 29;

    private YearFracCalculator() {
    }

    public static double calculate(double pStartDateVal, double pEndDateVal, int basis) throws EvaluationException {
        int endDateVal;
        if (basis < 0 || basis >= 5) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
        int startDateVal = (int)Math.floor(pStartDateVal);
        if (startDateVal == (endDateVal = (int)Math.floor(pEndDateVal))) {
            return 0.0;
        }
        if (startDateVal > endDateVal) {
            int temp = startDateVal;
            startDateVal = endDateVal;
            endDateVal = temp;
        }
        switch (basis) {
            case 0: {
                return YearFracCalculator.basis0(startDateVal, endDateVal);
            }
            case 1: {
                return YearFracCalculator.basis1(startDateVal, endDateVal);
            }
            case 2: {
                return YearFracCalculator.basis2(startDateVal, endDateVal);
            }
            case 3: {
                return YearFracCalculator.basis3(startDateVal, endDateVal);
            }
            case 4: {
                return YearFracCalculator.basis4(startDateVal, endDateVal);
            }
        }
        throw new IllegalStateException("cannot happen");
    }

    private static double basis0(int startDateVal, int endDateVal) {
        SimpleDate startDate = YearFracCalculator.createDate(startDateVal);
        SimpleDate endDate = YearFracCalculator.createDate(endDateVal);
        int date1day = startDate.day;
        int date2day = endDate.day;
        if (date1day == 31 && date2day == 31) {
            date1day = 30;
            date2day = 30;
        } else if (date1day == 31) {
            date1day = 30;
        } else if (date1day == 30 && date2day == 31) {
            date2day = 30;
        } else if (startDate.month == 2 && YearFracCalculator.isLastDayOfMonth(startDate)) {
            date1day = 30;
            if (endDate.month == 2 && YearFracCalculator.isLastDayOfMonth(endDate)) {
                date2day = 30;
            }
        }
        return YearFracCalculator.calculateAdjusted(startDate, endDate, date1day, date2day);
    }

    private static double basis1(int startDateVal, int endDateVal) {
        double yearLength;
        SimpleDate endDate;
        assert (startDateVal <= endDateVal);
        SimpleDate startDate = YearFracCalculator.createDate(startDateVal);
        if (YearFracCalculator.isGreaterThanOneYear(startDate, endDate = YearFracCalculator.createDate(endDateVal))) {
            yearLength = YearFracCalculator.averageYearLength(startDate.year, endDate.year);
            assert (yearLength > 0.0);
        } else {
            yearLength = YearFracCalculator.shouldCountFeb29(startDate, endDate) ? 366.0 : 365.0;
        }
        return (double)YearFracCalculator.dateDiff(startDate.tsMilliseconds, endDate.tsMilliseconds) / yearLength;
    }

    private static double basis2(int startDateVal, int endDateVal) {
        return (double)(endDateVal - startDateVal) / 360.0;
    }

    private static double basis3(double startDateVal, double endDateVal) {
        return (endDateVal - startDateVal) / 365.0;
    }

    private static double basis4(int startDateVal, int endDateVal) {
        SimpleDate startDate = YearFracCalculator.createDate(startDateVal);
        SimpleDate endDate = YearFracCalculator.createDate(endDateVal);
        int date1day = startDate.day;
        int date2day = endDate.day;
        if (date1day == 31) {
            date1day = 30;
        }
        if (date2day == 31) {
            date2day = 30;
        }
        return YearFracCalculator.calculateAdjusted(startDate, endDate, date1day, date2day);
    }

    private static double calculateAdjusted(SimpleDate startDate, SimpleDate endDate, int date1day, int date2day) {
        double dayCount = (double)(endDate.year - startDate.year) * 360.0 + (double)(endDate.month - startDate.month) * 30.0 + (double)(date2day - date1day) * 1.0;
        return dayCount / 360.0;
    }

    private static boolean isLastDayOfMonth(SimpleDate date) {
        if (date.day < 28) {
            return false;
        }
        return date.day == YearFracCalculator.getLastDayOfMonth(date);
    }

    private static int getLastDayOfMonth(SimpleDate date) {
        switch (date.month) {
            case 1: 
            case 3: 
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 12: {
                return 31;
            }
            case 4: 
            case 6: 
            case 9: 
            case 11: {
                return 30;
            }
        }
        if (YearFracCalculator.isLeapYear(date.year)) {
            return 29;
        }
        return 28;
    }

    private static boolean shouldCountFeb29(SimpleDate start, SimpleDate end) {
        if (YearFracCalculator.isLeapYear(start.year)) {
            if (start.year == end.year) {
                return true;
            }
            switch (start.month) {
                case 1: 
                case 2: {
                    return true;
                }
            }
            return false;
        }
        if (YearFracCalculator.isLeapYear(end.year)) {
            switch (end.month) {
                case 1: {
                    return false;
                }
                case 2: {
                    break;
                }
                default: {
                    return true;
                }
            }
            return end.day == 29;
        }
        return false;
    }

    private static int dateDiff(long startDateMS, long endDateMS) {
        long msDiff = endDateMS - startDateMS;
        int remainderHours = (int)(msDiff % 86400000L / 3600000L);
        switch (remainderHours) {
            case 0: {
                break;
            }
            default: {
                throw new RuntimeException("Unexpected date diff between " + startDateMS + " and " + endDateMS);
            }
        }
        return (int)(0.5 + (double)msDiff / 8.64E7);
    }

    private static double averageYearLength(int startYear, int endYear) {
        assert (startYear <= endYear);
        int dayCount = 0;
        for (int i = startYear; i <= endYear; ++i) {
            dayCount += YearFracCalculator.isLeapYear(i) ? 366 : 365;
        }
        double numberOfYears = endYear - startYear + 1;
        return (double)dayCount / numberOfYears;
    }

    private static boolean isLeapYear(int i) {
        if (i % 4 != 0) {
            return false;
        }
        if (i % 400 == 0) {
            return true;
        }
        return i % 100 != 0;
    }

    private static boolean isGreaterThanOneYear(SimpleDate start, SimpleDate end) {
        assert (start.year <= end.year);
        if (start.year == end.year) {
            return false;
        }
        if (start.year + 1 != end.year) {
            return true;
        }
        if (start.month > end.month) {
            return false;
        }
        if (start.month < end.month) {
            return true;
        }
        return start.day < end.day;
    }

    private static SimpleDate createDate(int dayCount) {
        Calendar cal = LocaleUtil.getLocaleCalendar(LocaleUtil.TIMEZONE_UTC);
        DateUtil.setCalendar(cal, dayCount, 0, false, false);
        return new SimpleDate(cal);
    }

    private static final class SimpleDate {
        public static final int JANUARY = 1;
        public static final int FEBRUARY = 2;
        public final int year;
        public final int month;
        public final int day;
        public long tsMilliseconds;

        public SimpleDate(Calendar cal) {
            this.year = cal.get(1);
            this.month = cal.get(2) + 1;
            this.day = cal.get(5);
            this.tsMilliseconds = cal.getTimeInMillis();
        }
    }
}

