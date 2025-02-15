/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.format;

import com.jgoodies.common.base.Objects;
import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

public class EmptyFormat
extends Format {
    private final Format delegate;
    private final Object emptyValue;

    public EmptyFormat(Format delegate) {
        this(delegate, null);
    }

    public EmptyFormat(Format delegate, Object emptyValue) {
        this.delegate = Preconditions.checkNotNull(delegate, "The %1$s must not be null.", "delegate format");
        this.emptyValue = emptyValue;
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return Objects.equals(obj, this.emptyValue) ? toAppendTo : this.delegate.format(obj, toAppendTo, pos);
    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return Strings.isBlank(source) ? this.emptyValue : super.parseObject(source);
    }

    @Override
    public final Object parseObject(String source, ParsePosition pos) {
        return this.delegate.parseObject(source, pos);
    }

    @Override
    public final AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return this.delegate.formatToCharacterIterator(obj);
    }
}

