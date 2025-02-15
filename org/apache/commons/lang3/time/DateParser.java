/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DateParser {
    public Date parse(String var1) throws ParseException;

    public Date parse(String var1, ParsePosition var2);

    public boolean parse(String var1, ParsePosition var2, Calendar var3);

    public String getPattern();

    public TimeZone getTimeZone();

    public Locale getLocale();

    public Object parseObject(String var1) throws ParseException;

    public Object parseObject(String var1, ParsePosition var2);
}

