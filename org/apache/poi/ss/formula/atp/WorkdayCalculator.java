/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class WorkdayCalculator {
    public static final WorkdayCalculator instance = new WorkdayCalculator();

    private WorkdayCalculator() {
    }

    public int calculateWorkdays(double start, double end, double[] holidays) {
        int saturdaysPast = this.pastDaysOfWeek(start, end, 7);
        int sundaysPast = this.pastDaysOfWeek(start, end, 1);
        int nonWeekendHolidays = this.calculateNonWeekendHolidays(start, end, holidays);
        return (int)(end - start + 1.0) - saturdaysPast - sundaysPast - nonWeekendHolidays;
    }

    public Date calculateWorkdays(double start, int workdays, double[] holidays) {
        Date startDate = DateUtil.getJavaDate(start);
        int direction = workdays < 0 ? -1 : 1;
        Calendar endDate = LocaleUtil.getLocaleCalendar();
        endDate.setTime(startDate);
        double excelEndDate = DateUtil.getExcelDate(endDate.getTime());
        while (workdays != 0) {
            endDate.add(6, direction);
            if (endDate.get(7) == 7 || endDate.get(7) == 1 || this.isHoliday(excelEndDate += (double)direction, holidays)) continue;
            workdays -= direction;
        }
        return endDate.getTime();
    }

    protected int pastDaysOfWeek(double start, double end, int dayOfWeek) {
        int pastDaysOfWeek = 0;
        int endDay = (int)Math.floor(end > start ? end : start);
        for (int startDay = (int)Math.floor(start < end ? start : end); startDay <= endDay; ++startDay) {
            Calendar today = LocaleUtil.getLocaleCalendar();
            today.setTime(DateUtil.getJavaDate(startDay));
            if (today.get(7) != dayOfWeek) continue;
            ++pastDaysOfWeek;
        }
        return start <= end ? pastDaysOfWeek : -pastDaysOfWeek;
    }

    protected int calculateNonWeekendHolidays(double start, double end, double[] holidays) {
        int nonWeekendHolidays = 0;
        double startDay = start < end ? start : end;
        double endDay = end > start ? end : start;
        for (double holiday : holidays) {
            if (!this.isInARange(startDay, endDay, holiday) || this.isWeekend(holiday)) continue;
            ++nonWeekendHolidays;
        }
        return start <= end ? nonWeekendHolidays : -nonWeekendHolidays;
    }

    protected boolean isWeekend(double aDate) {
        Calendar date = LocaleUtil.getLocaleCalendar();
        date.setTime(DateUtil.getJavaDate(aDate));
        return date.get(7) == 7 || date.get(7) == 1;
    }

    protected boolean isHoliday(double aDate, double[] holidays) {
        for (double holiday : holidays) {
            if (Math.round(holiday) != Math.round(aDate)) continue;
            return true;
        }
        return false;
    }

    protected boolean isInARange(double start, double end, double aDate) {
        return aDate >= start && aDate <= end;
    }
}

