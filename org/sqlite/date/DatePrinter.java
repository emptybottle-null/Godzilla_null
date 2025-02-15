/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.date;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DatePrinter {
    public String format(long var1);

    public String format(Date var1);

    public String format(Calendar var1);

    public StringBuffer format(long var1, StringBuffer var3);

    public StringBuffer format(Date var1, StringBuffer var2);

    public StringBuffer format(Calendar var1, StringBuffer var2);

    public String getPattern();

    public TimeZone getTimeZone();

    public Locale getLocale();

    public StringBuffer format(Object var1, StringBuffer var2, FieldPosition var3);
}

