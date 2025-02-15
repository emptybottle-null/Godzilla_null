/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class ProperFractionFormat
extends FractionFormat {
    private static final long serialVersionUID = 760934726031766749L;
    private NumberFormat wholeFormat;

    public ProperFractionFormat() {
        this(ProperFractionFormat.getDefaultNumberFormat());
    }

    public ProperFractionFormat(NumberFormat format) {
        this(format, (NumberFormat)format.clone(), (NumberFormat)format.clone());
    }

    public ProperFractionFormat(NumberFormat wholeFormat, NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
        this.setWholeFormat(wholeFormat);
    }

    public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        int num = fraction.getNumerator();
        int den = fraction.getDenominator();
        int whole = num / den;
        num %= den;
        if (whole != 0) {
            this.getWholeFormat().format(whole, toAppendTo, pos);
            toAppendTo.append(' ');
            num = FastMath.abs(num);
        }
        this.getNumeratorFormat().format(num, toAppendTo, pos);
        toAppendTo.append(" / ");
        this.getDenominatorFormat().format(den, toAppendTo, pos);
        return toAppendTo;
    }

    public NumberFormat getWholeFormat() {
        return this.wholeFormat;
    }

    public Fraction parse(String source, ParsePosition pos) {
        Fraction ret = super.parse(source, pos);
        if (ret != null) {
            return ret;
        }
        int initialIndex = pos.getIndex();
        ProperFractionFormat.parseAndIgnoreWhitespace(source, pos);
        Number whole = this.getWholeFormat().parse(source, pos);
        if (whole == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        ProperFractionFormat.parseAndIgnoreWhitespace(source, pos);
        Number num = this.getNumeratorFormat().parse(source, pos);
        if (num == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        if (num.intValue() < 0) {
            pos.setIndex(initialIndex);
            return null;
        }
        int startIndex = pos.getIndex();
        char c = ProperFractionFormat.parseNextCharacter(source, pos);
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
        ProperFractionFormat.parseAndIgnoreWhitespace(source, pos);
        Number den = this.getDenominatorFormat().parse(source, pos);
        if (den == null) {
            pos.setIndex(initialIndex);
            return null;
        }
        if (den.intValue() < 0) {
            pos.setIndex(initialIndex);
            return null;
        }
        int w = whole.intValue();
        int n = num.intValue();
        int d = den.intValue();
        return new Fraction((FastMath.abs(w) * d + n) * MathUtils.copySign(1, w), d);
    }

    public void setWholeFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT, new Object[0]);
        }
        this.wholeFormat = format;
    }
}

