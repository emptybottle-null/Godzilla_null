/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.helpers.AbsoluteTimeDateFormat;

public class ISO8601DateFormat
extends AbsoluteTimeDateFormat {
    private static final long serialVersionUID = -759840745298755296L;
    private static long lastTime;
    private static char[] lastTimeString;

    public ISO8601DateFormat() {
    }

    public ISO8601DateFormat(TimeZone timeZone) {
        super(timeZone);
    }

    public StringBuffer format(Date date, StringBuffer sbuf, FieldPosition fieldPosition) {
        int millis;
        long now = date.getTime();
        if (now - (long)(millis = (int)(now % 1000L)) != lastTime || lastTimeString[0] == '\u0000') {
            String month;
            this.calendar.setTime(date);
            int start = sbuf.length();
            int year = this.calendar.get(1);
            sbuf.append(year);
            switch (this.calendar.get(2)) {
                case 0: {
                    month = "-01-";
                    break;
                }
                case 1: {
                    month = "-02-";
                    break;
                }
                case 2: {
                    month = "-03-";
                    break;
                }
                case 3: {
                    month = "-04-";
                    break;
                }
                case 4: {
                    month = "-05-";
                    break;
                }
                case 5: {
                    month = "-06-";
                    break;
                }
                case 6: {
                    month = "-07-";
                    break;
                }
                case 7: {
                    month = "-08-";
                    break;
                }
                case 8: {
                    month = "-09-";
                    break;
                }
                case 9: {
                    month = "-10-";
                    break;
                }
                case 10: {
                    month = "-11-";
                    break;
                }
                case 11: {
                    month = "-12-";
                    break;
                }
                default: {
                    month = "-NA-";
                }
            }
            sbuf.append(month);
            int day = this.calendar.get(5);
            if (day < 10) {
                sbuf.append('0');
            }
            sbuf.append(day);
            sbuf.append(' ');
            int hour = this.calendar.get(11);
            if (hour < 10) {
                sbuf.append('0');
            }
            sbuf.append(hour);
            sbuf.append(':');
            int mins = this.calendar.get(12);
            if (mins < 10) {
                sbuf.append('0');
            }
            sbuf.append(mins);
            sbuf.append(':');
            int secs = this.calendar.get(13);
            if (secs < 10) {
                sbuf.append('0');
            }
            sbuf.append(secs);
            sbuf.append(',');
            sbuf.getChars(start, sbuf.length(), lastTimeString, 0);
            lastTime = now - (long)millis;
        } else {
            sbuf.append(lastTimeString);
        }
        if (millis < 100) {
            sbuf.append('0');
        }
        if (millis < 10) {
            sbuf.append('0');
        }
        sbuf.append(millis);
        return sbuf;
    }

    public Date parse(String s, ParsePosition pos) {
        return null;
    }

    static {
        lastTimeString = new char[20];
    }
}

