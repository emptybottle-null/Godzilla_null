/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.xmlbeans.XmlCalendar;

public interface GDateSpecification {
    public static final int HAS_TIMEZONE = 1;
    public static final int HAS_YEAR = 2;
    public static final int HAS_MONTH = 4;
    public static final int HAS_DAY = 8;
    public static final int HAS_TIME = 16;

    public int getFlags();

    public boolean isImmutable();

    public boolean isValid();

    public boolean hasTimeZone();

    public boolean hasYear();

    public boolean hasMonth();

    public boolean hasDay();

    public boolean hasTime();

    public boolean hasDate();

    public int getYear();

    public int getMonth();

    public int getDay();

    public int getHour();

    public int getMinute();

    public int getSecond();

    public int getTimeZoneSign();

    public int getTimeZoneHour();

    public int getTimeZoneMinute();

    public BigDecimal getFraction();

    public int getMillisecond();

    public int getJulianDate();

    public XmlCalendar getCalendar();

    public Date getDate();

    public int compareToGDate(GDateSpecification var1);

    public int getBuiltinTypeCode();

    public String canonicalString();

    public String toString();
}

