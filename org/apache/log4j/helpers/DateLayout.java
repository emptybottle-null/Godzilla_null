/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.AbsoluteTimeDateFormat;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.apache.log4j.helpers.ISO8601DateFormat;
import org.apache.log4j.helpers.RelativeTimeDateFormat;
import org.apache.log4j.spi.LoggingEvent;

public abstract class DateLayout
extends Layout {
    public static final String NULL_DATE_FORMAT = "NULL";
    public static final String RELATIVE_TIME_DATE_FORMAT = "RELATIVE";
    protected FieldPosition pos = new FieldPosition(0);
    public static final String DATE_FORMAT_OPTION = "DateFormat";
    public static final String TIMEZONE_OPTION = "TimeZone";
    private String timeZoneID;
    private String dateFormatOption;
    protected DateFormat dateFormat;
    protected Date date = new Date();

    public String[] getOptionStrings() {
        return new String[]{DATE_FORMAT_OPTION, TIMEZONE_OPTION};
    }

    public void setOption(String option, String value) {
        if (option.equalsIgnoreCase(DATE_FORMAT_OPTION)) {
            this.dateFormatOption = value.toUpperCase();
        } else if (option.equalsIgnoreCase(TIMEZONE_OPTION)) {
            this.timeZoneID = value;
        }
    }

    public void setDateFormat(String dateFormat) {
        if (dateFormat != null) {
            this.dateFormatOption = dateFormat;
        }
        this.setDateFormat(this.dateFormatOption, TimeZone.getDefault());
    }

    public String getDateFormat() {
        return this.dateFormatOption;
    }

    public void setTimeZone(String timeZone) {
        this.timeZoneID = timeZone;
    }

    public String getTimeZone() {
        return this.timeZoneID;
    }

    public void activateOptions() {
        this.setDateFormat(this.dateFormatOption);
        if (this.timeZoneID != null && this.dateFormat != null) {
            this.dateFormat.setTimeZone(TimeZone.getTimeZone(this.timeZoneID));
        }
    }

    public void dateFormat(StringBuffer buf, LoggingEvent event) {
        if (this.dateFormat != null) {
            this.date.setTime(event.timeStamp);
            this.dateFormat.format(this.date, buf, this.pos);
            buf.append(' ');
        }
    }

    public void setDateFormat(DateFormat dateFormat, TimeZone timeZone) {
        this.dateFormat = dateFormat;
        this.dateFormat.setTimeZone(timeZone);
    }

    public void setDateFormat(String dateFormatType, TimeZone timeZone) {
        if (dateFormatType == null) {
            this.dateFormat = null;
            return;
        }
        if (dateFormatType.equalsIgnoreCase(NULL_DATE_FORMAT)) {
            this.dateFormat = null;
        } else if (dateFormatType.equalsIgnoreCase(RELATIVE_TIME_DATE_FORMAT)) {
            this.dateFormat = new RelativeTimeDateFormat();
        } else if (dateFormatType.equalsIgnoreCase("ABSOLUTE")) {
            this.dateFormat = new AbsoluteTimeDateFormat(timeZone);
        } else if (dateFormatType.equalsIgnoreCase("DATE")) {
            this.dateFormat = new DateTimeDateFormat(timeZone);
        } else if (dateFormatType.equalsIgnoreCase("ISO8601")) {
            this.dateFormat = new ISO8601DateFormat(timeZone);
        } else {
            this.dateFormat = new SimpleDateFormat(dateFormatType);
            this.dateFormat.setTimeZone(timeZone);
        }
    }
}

