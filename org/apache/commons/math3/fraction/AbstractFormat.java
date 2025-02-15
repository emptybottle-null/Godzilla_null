/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class AbstractFormat
extends NumberFormat
implements Serializable {
    private static final long serialVersionUID = -6981118387974191891L;
    private NumberFormat denominatorFormat;
    private NumberFormat numeratorFormat;

    protected AbstractFormat() {
        this(AbstractFormat.getDefaultNumberFormat());
    }

    protected AbstractFormat(NumberFormat format) {
        this(format, (NumberFormat)format.clone());
    }

    protected AbstractFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        this.numeratorFormat = numeratorFormat;
        this.denominatorFormat = denominatorFormat;
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return AbstractFormat.getDefaultNumberFormat(Locale.getDefault());
    }

    protected static NumberFormat getDefaultNumberFormat(Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMaximumFractionDigits(0);
        nf.setParseIntegerOnly(true);
        return nf;
    }

    public NumberFormat getDenominatorFormat() {
        return this.denominatorFormat;
    }

    public NumberFormat getNumeratorFormat() {
        return this.numeratorFormat;
    }

    public void setDenominatorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.DENOMINATOR_FORMAT, new Object[0]);
        }
        this.denominatorFormat = format;
    }

    public void setNumeratorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.NUMERATOR_FORMAT, new Object[0]);
        }
        this.numeratorFormat = format;
    }

    protected static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
        AbstractFormat.parseNextCharacter(source, pos);
        pos.setIndex(pos.getIndex() - 1);
    }

    protected static char parseNextCharacter(String source, ParsePosition pos) {
        int index = pos.getIndex();
        int n = source.length();
        char ret = '\u0000';
        if (index < n) {
            char c;
            while (Character.isWhitespace(c = source.charAt(index++)) && index < n) {
            }
            pos.setIndex(index);
            if (index < n) {
                ret = c;
            }
        }
        return ret;
    }

    public StringBuffer format(double value, StringBuffer buffer, FieldPosition position) {
        return this.format((Object)value, buffer, position);
    }

    public StringBuffer format(long value, StringBuffer buffer, FieldPosition position) {
        return this.format((Object)value, buffer, position);
    }
}

