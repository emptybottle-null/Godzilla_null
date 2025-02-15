/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.format;

import com.jgoodies.common.base.Preconditions;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class AbstractWrappedDateFormat
extends DateFormat {
    protected final DateFormat delegate;

    public AbstractWrappedDateFormat(DateFormat delegate) {
        this.delegate = Preconditions.checkNotNull(delegate, "The %1$s must not be null.", "delegate format");
    }

    @Override
    public abstract StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3);

    @Override
    public abstract Date parse(String var1, ParsePosition var2);

    @Override
    public Calendar getCalendar() {
        return this.delegate.getCalendar();
    }

    @Override
    public void setCalendar(Calendar newCalendar) {
        this.delegate.setCalendar(newCalendar);
    }

    @Override
    public NumberFormat getNumberFormat() {
        return this.delegate.getNumberFormat();
    }

    @Override
    public void setNumberFormat(NumberFormat newNumberFormat) {
        this.delegate.setNumberFormat(newNumberFormat);
    }

    @Override
    public TimeZone getTimeZone() {
        return this.delegate.getTimeZone();
    }

    @Override
    public void setTimeZone(TimeZone zone) {
        this.delegate.setTimeZone(zone);
    }

    @Override
    public boolean isLenient() {
        return this.delegate.isLenient();
    }

    @Override
    public void setLenient(boolean lenient) {
        this.delegate.setLenient(lenient);
    }

    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return this.delegate.formatToCharacterIterator(obj);
    }
}

