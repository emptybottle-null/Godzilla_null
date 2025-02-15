/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Pattern;
import org.apache.poi.ss.format.SimpleFraction;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class FractionFormat
extends Format {
    private static final POILogger LOGGER = POILogFactory.getLogger(FractionFormat.class);
    private static final Pattern DENOM_FORMAT_PATTERN = Pattern.compile("(?:(#+)|(\\d+))");
    private static final int MAX_DENOM_POW = 4;
    private final int exactDenom;
    private final int maxDenom;
    private final String wholePartFormatString;

    /*
     * Unable to fully structure code
     */
    public FractionFormat(String wholePartFormatString, String denomFormatString) {
        super();
        this.wholePartFormatString = wholePartFormatString;
        m = FractionFormat.DENOM_FORMAT_PATTERN.matcher(denomFormatString);
        tmpExact = -1;
        tmpMax = -1;
        if (m.find()) {
            if (m.group(2) != null) {
                try {
                    tmpExact = Integer.parseInt(m.group(2));
                    if (tmpExact != 0) ** GOTO lbl21
                    tmpExact = -1;
                } catch (NumberFormatException e) {
                    throw new IllegalStateException(e);
                }
            } else if (m.group(1) != null) {
                len = m.group(1).length();
                len = len > 4 ? 4 : len;
                tmpMax = (int)Math.pow(10.0, len);
            } else {
                tmpExact = 100;
            }
        }
lbl21:
        // 6 sources

        if (tmpExact <= 0 && tmpMax <= 0) {
            tmpExact = 100;
        }
        this.exactDenom = tmpExact;
        this.maxDenom = tmpMax;
    }

    public String format(Number num) {
        SimpleFraction fract;
        BigDecimal decPart;
        BigDecimal doubleValue = new BigDecimal(num.doubleValue());
        boolean isNeg = doubleValue.compareTo(BigDecimal.ZERO) < 0;
        BigDecimal absValue = doubleValue.abs();
        BigDecimal wholePart = new BigDecimal(absValue.toBigInteger());
        if (wholePart.add(decPart = absValue.remainder(BigDecimal.ONE)).compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        if (decPart.compareTo(BigDecimal.ZERO) == 0) {
            StringBuilder sb = new StringBuilder();
            if (isNeg) {
                sb.append("-");
            }
            sb.append(wholePart);
            return sb.toString();
        }
        try {
            fract = this.exactDenom > 0 ? SimpleFraction.buildFractionExactDenominator(decPart.doubleValue(), this.exactDenom) : SimpleFraction.buildFractionMaxDenominator(decPart.doubleValue(), this.maxDenom);
        } catch (RuntimeException e) {
            LOGGER.log(5, "Can't format fraction", e);
            return Double.toString(doubleValue.doubleValue());
        }
        StringBuilder sb = new StringBuilder();
        if (isNeg) {
            sb.append("-");
        }
        if (this.wholePartFormatString == null || this.wholePartFormatString.isEmpty()) {
            int fden = fract.getDenominator();
            int fnum = fract.getNumerator();
            BigDecimal trueNum = wholePart.multiply(new BigDecimal(fden)).add(new BigDecimal(fnum));
            sb.append(trueNum.toBigInteger()).append("/").append(fden);
            return sb.toString();
        }
        if (fract.getNumerator() == 0) {
            sb.append(wholePart);
            return sb.toString();
        }
        if (fract.getNumerator() == fract.getDenominator()) {
            sb.append(wholePart.add(BigDecimal.ONE));
            return sb.toString();
        }
        if (wholePart.compareTo(BigDecimal.ZERO) > 0) {
            sb.append(wholePart).append(" ");
        }
        sb.append(fract.getNumerator()).append("/").append(fract.getDenominator());
        return sb.toString();
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return toAppendTo.append(this.format((Number)obj));
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        throw new NotImplementedException("Reverse parsing not supported");
    }
}

