/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.format;

import com.jgoodies.common.base.Objects;
import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

public final class EmptyNumberFormat
extends NumberFormat {
    private final NumberFormat delegate;
    private final Number emptyValue;

    public EmptyNumberFormat(NumberFormat delegate) {
        this(delegate, null);
    }

    public EmptyNumberFormat(NumberFormat delegate, int emptyValue) {
        this(delegate, (Number)emptyValue);
    }

    public EmptyNumberFormat(NumberFormat delegate, Number emptyValue) {
        this.delegate = Preconditions.checkNotNull(delegate, "The %1$s must not be null.", "delegate format");
        this.emptyValue = emptyValue;
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return Objects.equals(obj, this.emptyValue) ? toAppendTo : this.delegate.format(obj, toAppendTo, pos);
    }

    @Override
    public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
        return this.delegate.format(number, toAppendTo, pos);
    }

    @Override
    public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
        return this.delegate.format(number, toAppendTo, pos);
    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return Strings.isBlank(source) ? this.emptyValue : super.parseObject(source);
    }

    @Override
    public Number parse(String source) throws ParseException {
        return Strings.isBlank(source) ? (Number)this.emptyValue : (Number)super.parse(source);
    }

    @Override
    public Number parse(String source, ParsePosition pos) {
        return this.delegate.parse(source, pos);
    }
}

