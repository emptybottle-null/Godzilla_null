/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.SystemProperties;

public class XmlCalendar
extends GregorianCalendar {
    private static int defaultYear = Integer.MIN_VALUE;
    private static final int DEFAULT_DEFAULT_YEAR = 0;
    private static Date _beginningOfTime = new Date(Long.MIN_VALUE);

    public XmlCalendar(String xmlSchemaDateString) {
        this(new GDate(xmlSchemaDateString));
    }

    public XmlCalendar(GDateSpecification date) {
        this(GDate.timeZoneForGDate(date), date);
    }

    private XmlCalendar(TimeZone tz, GDateSpecification date) {
        super(tz);
        this.setGregorianChange(_beginningOfTime);
        this.clear();
        if (date.hasYear()) {
            int y = date.getYear();
            if (y > 0) {
                this.set(0, 1);
            } else {
                this.set(0, 0);
                y = -y;
            }
            this.set(1, y);
        }
        if (date.hasMonth()) {
            this.set(2, date.getMonth() - 1);
        }
        if (date.hasDay()) {
            this.set(5, date.getDay());
        }
        if (date.hasTime()) {
            this.set(11, date.getHour());
            this.set(12, date.getMinute());
            this.set(13, date.getSecond());
            if (date.getFraction().scale() > 0) {
                this.set(14, date.getMillisecond());
            }
        }
        if (date.hasTimeZone()) {
            this.set(15, date.getTimeZoneSign() * 1000 * 60 * (date.getTimeZoneHour() * 60 + date.getTimeZoneMinute()));
            this.set(16, 0);
        }
    }

    public XmlCalendar(Date date) {
        this(TimeZone.getDefault(), new GDate(date));
        this.complete();
    }

    public XmlCalendar(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
        this(TimeZone.getDefault(), new GDate(year, month, day, hour, minute, second, fraction));
    }

    public XmlCalendar(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction, int tzSign, int tzHour, int tzMinute) {
        this(new GDate(year, month, day, hour, minute, second, fraction, tzSign, tzHour, tzMinute));
    }

    @Override
    public int get(int field) {
        if (!this.isSet(field) || this.isTimeSet) {
            return super.get(field);
        }
        return this.internalGet(field);
    }

    public XmlCalendar() {
        this.setGregorianChange(_beginningOfTime);
        this.clear();
    }

    public static int getDefaultYear() {
        if (defaultYear == Integer.MIN_VALUE) {
            try {
                String yearstring = SystemProperties.getProperty("user.defaultyear");
                defaultYear = yearstring != null ? Integer.parseInt(yearstring) : 0;
            } catch (Throwable t) {
                defaultYear = 0;
            }
        }
        return defaultYear;
    }

    public static void setDefaultYear(int year) {
        defaultYear = year;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void computeTime() {
        boolean unsetYear;
        boolean bl = unsetYear = !this.isSet(1);
        if (unsetYear) {
            this.set(1, XmlCalendar.getDefaultYear());
        }
        try {
            super.computeTime();
        } finally {
            if (unsetYear) {
                this.clear(1);
            }
        }
    }

    @Override
    public String toString() {
        return new GDate(this).toString();
    }
}

