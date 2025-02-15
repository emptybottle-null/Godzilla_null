/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.AbstractFormat;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.fraction.ProperFractionFormat;

public class FractionFormat
extends AbstractFormat {
    private static final long serialVersionUID = 3008655719530972611L;

    public FractionFormat() {
    }

    public FractionFormat(NumberFormat format) {
        super(format);
    }

    public FractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatFraction(Fraction f) {
        return FractionFormat.getImproperInstance().format(f);
    }

    public static FractionFormat getImproperInstance() {
        return FractionFormat.getImproperInstance(Locale.getDefault());
    }

    public static FractionFormat getImproperInstance(Locale locale) {
        return new FractionFormat(FractionFormat.getDefaultNumberFormat(locale));
    }

    public static FractionFormat getProperInstance() {
        return FractionFormat.getProperInstance(Locale.getDefault());
    }

    public static FractionFormat getProperInstance(Locale locale) {
        return new ProperFractionFormat(FractionFormat.getDefaultNumberFormat(locale));
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return FractionFormat.getDefaultNumberFormat(Locale.getDefault());
    }

    public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        this.getNumeratorFormat().format(fraction.getNumerator(), toAppendTo, pos);
        toAppendTo.append(" / ");
        this.getDenominatorFormat().format(fraction.getDenominator(), toAppendTo, pos);
        return toAppendTo;
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) throws FractionConversionException, MathIllegalArgumentException {
        StringBuffer ret = null;
        if (obj instanceof Fraction) {
            ret = this.format((Fraction)obj, toAppendTo, pos);
        } else if (obj instanceof Number) {
            ret = this.format(new Fraction(((Number)obj).doubleValue()), toAppendTo, pos);
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
        }
        return ret;
    }

    public Fraction parse(String source) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Fraction result = this.parse(source, parsePosition);
        if (parsePosition.getIndex() == 0) {
            throw new MathParseException(source, parsePosition.getErrorIndex(), Fraction.class);
        }
        return result;
    }

    public Fraction parse(String source, ParsePosition pos) {
        int initialIndex = pos.getIndex();
        FractionFormat.parseAndIgnoreWhitespace(source, pos);
        Number num = this.getNumeratorFormat().parse(source, pos);
        if (num == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        int startIndex = pos.getIndex();
        char c = FractionFormat.parseNextCharacter(source, pos);
        switch (c) {
            case '\u0000': {
                return new Fraction(num.intValue(), 1);
            }
            case '/': {
                break;
            }
            default: {
                pos.setIndex(initialIndex);
                pos.setErrorIndex(startIndex);
                return null;
            }
        }
        FractionFormat.parseAndIgnoreWhitespace(source, pos);
        Number den = this.getDenominatorFormat().parse(source, pos);
        if (den == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        return new Fraction(num.intValue(), den.intValue());
    }
}

