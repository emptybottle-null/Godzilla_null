/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.AbstractFormat;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.ProperBigFractionFormat;

public class BigFractionFormat
extends AbstractFormat
implements Serializable {
    private static final long serialVersionUID = -2932167925527338976L;

    public BigFractionFormat() {
    }

    public BigFractionFormat(NumberFormat format) {
        super(format);
    }

    public BigFractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatBigFraction(BigFraction f) {
        return BigFractionFormat.getImproperInstance().format(f);
    }

    public static BigFractionFormat getImproperInstance() {
        return BigFractionFormat.getImproperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getImproperInstance(Locale locale) {
        return new BigFractionFormat(BigFractionFormat.getDefaultNumberFormat(locale));
    }

    public static BigFractionFormat getProperInstance() {
        return BigFractionFormat.getProperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getProperInstance(Locale locale) {
        return new ProperBigFractionFormat(BigFractionFormat.getDefaultNumberFormat(locale));
    }

    public StringBuffer format(BigFraction BigFraction2, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        this.getNumeratorFormat().format(BigFraction2.getNumerator(), toAppendTo, pos);
        toAppendTo.append(" / ");
        this.getDenominatorFormat().format(BigFraction2.getDenominator(), toAppendTo, pos);
        return toAppendTo;
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        StringBuffer ret;
        if (obj instanceof BigFraction) {
            ret = this.format((BigFraction)obj, toAppendTo, pos);
        } else if (obj instanceof BigInteger) {
            ret = this.format(new BigFraction((BigInteger)obj), toAppendTo, pos);
        } else if (obj instanceof Number) {
            ret = this.format(new BigFraction(((Number)obj).doubleValue()), toAppendTo, pos);
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
        }
        return ret;
    }

    public BigFraction parse(String source) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        BigFraction result = this.parse(source, parsePosition);
        if (parsePosition.getIndex() == 0) {
            throw new MathParseException(source, parsePosition.getErrorIndex(), BigFraction.class);
        }
        return result;
    }

    public BigFraction parse(String source, ParsePosition pos) {
        int initialIndex = pos.getIndex();
        BigFractionFormat.parseAndIgnoreWhitespace(source, pos);
        BigInteger num = this.parseNextBigInteger(source, pos);
        if (num == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        int startIndex = pos.getIndex();
        char c = BigFractionFormat.parseNextCharacter(source, pos);
        switch (c) {
            case '\u0000': {
                return new BigFraction(num);
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
        BigFractionFormat.parseAndIgnoreWhitespace(source, pos);
        BigInteger den = this.parseNextBigInteger(source, pos);
        if (den == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        return new BigFraction(num, den);
    }

    protected BigInteger parseNextBigInteger(String source, ParsePosition pos) {
        int end;
        int start = pos.getIndex();
        int n = end = source.charAt(start) == '-' ? start + 1 : start;
        while (end < source.length() && Character.isDigit(source.charAt(end))) {
            ++end;
        }
        try {
            BigInteger n2 = new BigInteger(source.substring(start, end));
            pos.setIndex(end);
            return n2;
        } catch (NumberFormatException nfe) {
            pos.setErrorIndex(start);
            return null;
        }
    }
}

