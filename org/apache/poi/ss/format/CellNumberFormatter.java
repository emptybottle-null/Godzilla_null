/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import com.zaxxer.sparsebits.SparseBitSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.format.CellFormatter;
import org.apache.poi.ss.format.CellNumberPartHandler;
import org.apache.poi.ss.format.CellNumberStringMod;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.format.SimpleFraction;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class CellNumberFormatter
extends CellFormatter {
    private static final POILogger LOG = POILogFactory.getLogger(CellNumberFormatter.class);
    private final String desc;
    private final String printfFmt;
    private final double scale;
    private final Special decimalPoint;
    private final Special slash;
    private final Special exponent;
    private final Special numerator;
    private final Special afterInteger;
    private final Special afterFractional;
    private final boolean showGroupingSeparator;
    private final List<Special> specials = new ArrayList<Special>();
    private final List<Special> integerSpecials = new ArrayList<Special>();
    private final List<Special> fractionalSpecials = new ArrayList<Special>();
    private final List<Special> numeratorSpecials = new ArrayList<Special>();
    private final List<Special> denominatorSpecials = new ArrayList<Special>();
    private final List<Special> exponentSpecials = new ArrayList<Special>();
    private final List<Special> exponentDigitSpecials = new ArrayList<Special>();
    private final int maxDenominator;
    private final String numeratorFmt;
    private final String denominatorFmt;
    private final boolean improperFraction;
    private final DecimalFormat decimalFmt;
    private final CellFormatter SIMPLE_NUMBER = new GeneralNumberFormatter(this.locale);

    public CellNumberFormatter(String format) {
        this(LocaleUtil.getUserLocale(), format);
    }

    public CellNumberFormatter(Locale locale, String format) {
        super(locale, format);
        CellNumberPartHandler ph = new CellNumberPartHandler();
        StringBuffer descBuf = CellFormatPart.parseFormat(format, CellFormatType.NUMBER, ph);
        this.exponent = ph.getExponent();
        this.specials.addAll(ph.getSpecials());
        this.improperFraction = ph.isImproperFraction();
        if ((ph.getDecimalPoint() != null || ph.getExponent() != null) && ph.getSlash() != null) {
            this.slash = null;
            this.numerator = null;
        } else {
            this.slash = ph.getSlash();
            this.numerator = ph.getNumerator();
        }
        int precision = CellNumberFormatter.interpretPrecision(ph.getDecimalPoint(), this.specials);
        int fractionPartWidth = 0;
        if (ph.getDecimalPoint() != null) {
            fractionPartWidth = 1 + precision;
            if (precision == 0) {
                this.specials.remove(ph.getDecimalPoint());
                this.decimalPoint = null;
            } else {
                this.decimalPoint = ph.getDecimalPoint();
            }
        } else {
            this.decimalPoint = null;
        }
        this.afterInteger = this.decimalPoint != null ? this.decimalPoint : (this.exponent != null ? this.exponent : (this.numerator != null ? this.numerator : null));
        this.afterFractional = this.exponent != null ? this.exponent : (this.numerator != null ? this.numerator : null);
        double[] scaleByRef = new double[]{ph.getScale()};
        this.showGroupingSeparator = CellNumberFormatter.interpretIntegerCommas(descBuf, this.specials, this.decimalPoint, this.integerEnd(), this.fractionalEnd(), scaleByRef);
        this.scale = this.exponent == null ? scaleByRef[0] : 1.0;
        if (precision != 0) {
            this.fractionalSpecials.addAll(this.specials.subList(this.specials.indexOf(this.decimalPoint) + 1, this.fractionalEnd()));
        }
        if (this.exponent != null) {
            int exponentPos = this.specials.indexOf(this.exponent);
            this.exponentSpecials.addAll(this.specialsFor(exponentPos, 2));
            this.exponentDigitSpecials.addAll(this.specialsFor(exponentPos + 2));
        }
        if (this.slash != null) {
            if (this.numerator != null) {
                this.numeratorSpecials.addAll(this.specialsFor(this.specials.indexOf(this.numerator)));
            }
            this.denominatorSpecials.addAll(this.specialsFor(this.specials.indexOf(this.slash) + 1));
            if (this.denominatorSpecials.isEmpty()) {
                this.numeratorSpecials.clear();
                this.maxDenominator = 1;
                this.numeratorFmt = null;
                this.denominatorFmt = null;
            } else {
                this.maxDenominator = CellNumberFormatter.maxValue(this.denominatorSpecials);
                this.numeratorFmt = CellNumberFormatter.singleNumberFormat(this.numeratorSpecials);
                this.denominatorFmt = CellNumberFormatter.singleNumberFormat(this.denominatorSpecials);
            }
        } else {
            this.maxDenominator = 1;
            this.numeratorFmt = null;
            this.denominatorFmt = null;
        }
        this.integerSpecials.addAll(this.specials.subList(0, this.integerEnd()));
        if (this.exponent == null) {
            StringBuilder fmtBuf = new StringBuilder("%");
            int integerPartWidth = this.calculateIntegerPartWidth();
            int totalWidth = integerPartWidth + fractionPartWidth;
            fmtBuf.append('0').append(totalWidth).append('.').append(precision);
            fmtBuf.append("f");
            this.printfFmt = fmtBuf.toString();
            this.decimalFmt = null;
        } else {
            StringBuffer fmtBuf = new StringBuffer();
            boolean first = true;
            if (this.integerSpecials.size() == 1) {
                fmtBuf.append("0");
                first = false;
            } else {
                for (Special s : this.integerSpecials) {
                    if (!CellNumberFormatter.isDigitFmt(s)) continue;
                    fmtBuf.append(first ? (char)'#' : '0');
                    first = false;
                }
            }
            if (this.fractionalSpecials.size() > 0) {
                fmtBuf.append('.');
                for (Special s : this.fractionalSpecials) {
                    if (!CellNumberFormatter.isDigitFmt(s)) continue;
                    if (!first) {
                        fmtBuf.append('0');
                    }
                    first = false;
                }
            }
            fmtBuf.append('E');
            CellNumberFormatter.placeZeros(fmtBuf, this.exponentSpecials.subList(2, this.exponentSpecials.size()));
            this.decimalFmt = new DecimalFormat(fmtBuf.toString(), this.getDecimalFormatSymbols());
            this.printfFmt = null;
        }
        this.desc = descBuf.toString();
    }

    private DecimalFormatSymbols getDecimalFormatSymbols() {
        return DecimalFormatSymbols.getInstance(this.locale);
    }

    private static void placeZeros(StringBuffer sb, List<Special> specials) {
        for (Special s : specials) {
            if (!CellNumberFormatter.isDigitFmt(s)) continue;
            sb.append('0');
        }
    }

    private static CellNumberStringMod insertMod(Special special, CharSequence toAdd, int where) {
        return new CellNumberStringMod(special, toAdd, where);
    }

    private static CellNumberStringMod deleteMod(Special start, boolean startInclusive, Special end, boolean endInclusive) {
        return new CellNumberStringMod(start, startInclusive, end, endInclusive);
    }

    private static CellNumberStringMod replaceMod(Special start, boolean startInclusive, Special end, boolean endInclusive, char withChar) {
        return new CellNumberStringMod(start, startInclusive, end, endInclusive, withChar);
    }

    private static String singleNumberFormat(List<Special> numSpecials) {
        return "%0" + numSpecials.size() + "d";
    }

    private static int maxValue(List<Special> s) {
        return Math.toIntExact(Math.round(Math.pow(10.0, s.size()) - 1.0));
    }

    private List<Special> specialsFor(int pos, int takeFirst) {
        Special s;
        if (pos >= this.specials.size()) {
            return Collections.emptyList();
        }
        ListIterator<Special> it = this.specials.listIterator(pos + takeFirst);
        Special last = it.next();
        int end = pos + takeFirst;
        while (it.hasNext() && CellNumberFormatter.isDigitFmt(s = it.next()) && s.pos - last.pos <= 1) {
            ++end;
            last = s;
        }
        return this.specials.subList(pos, end + 1);
    }

    private List<Special> specialsFor(int pos) {
        return this.specialsFor(pos, 0);
    }

    private static boolean isDigitFmt(Special s) {
        return s.ch == '0' || s.ch == '?' || s.ch == '#';
    }

    private int calculateIntegerPartWidth() {
        int digitCount = 0;
        for (Special s : this.specials) {
            if (s == this.afterInteger) break;
            if (!CellNumberFormatter.isDigitFmt(s)) continue;
            ++digitCount;
        }
        return digitCount;
    }

    private static int interpretPrecision(Special decimalPoint, List<Special> specials) {
        int idx = specials.indexOf(decimalPoint);
        int precision = 0;
        if (idx != -1) {
            Special s;
            ListIterator<Special> it = specials.listIterator(idx + 1);
            while (it.hasNext() && CellNumberFormatter.isDigitFmt(s = it.next())) {
                ++precision;
            }
        }
        return precision;
    }

    private static boolean interpretIntegerCommas(StringBuffer sb, List<Special> specials, Special decimalPoint, int integerEnd, int fractionalEnd, double[] scale) {
        Special s;
        ListIterator<Special> it = specials.listIterator(integerEnd);
        boolean stillScaling = true;
        boolean integerCommas = false;
        while (it.hasPrevious()) {
            s = it.previous();
            if (s.ch != ',') {
                stillScaling = false;
                continue;
            }
            if (stillScaling) {
                scale[0] = scale[0] / 1000.0;
                continue;
            }
            integerCommas = true;
        }
        if (decimalPoint != null) {
            it = specials.listIterator(fractionalEnd);
            while (it.hasPrevious()) {
                s = it.previous();
                if (s.ch != ',') break;
                scale[0] = scale[0] / 1000.0;
            }
        }
        it = specials.listIterator();
        int removed = 0;
        while (it.hasNext()) {
            Special s2 = it.next();
            s2.pos -= removed;
            if (s2.ch != ',') continue;
            ++removed;
            it.remove();
            sb.deleteCharAt(s2.pos);
        }
        return integerCommas;
    }

    private int integerEnd() {
        return this.afterInteger == null ? this.specials.size() : this.specials.indexOf(this.afterInteger);
    }

    private int fractionalEnd() {
        return this.afterFractional == null ? this.specials.size() : this.specials.indexOf(this.afterFractional);
    }

    @Override
    public void formatValue(StringBuffer toAppendTo, Object valueObject) {
        boolean negative;
        double value = ((Number)valueObject).doubleValue();
        boolean bl = negative = (value *= this.scale) < 0.0;
        if (negative) {
            value = -value;
        }
        double fractional = 0.0;
        if (this.slash != null) {
            if (this.improperFraction) {
                fractional = value;
                value = 0.0;
            } else {
                fractional = value % 1.0;
                value = (long)value;
            }
        }
        TreeSet<CellNumberStringMod> mods = new TreeSet<CellNumberStringMod>();
        StringBuffer output = new StringBuffer(this.localiseFormat(this.desc));
        if (this.exponent != null) {
            this.writeScientific(value, output, mods);
        } else if (this.improperFraction) {
            this.writeFraction(value, null, fractional, output, mods);
        } else {
            StringBuffer result = new StringBuffer();
            try (Formatter f = new Formatter(result, this.locale);){
                f.format(this.locale, this.printfFmt, value);
            }
            if (this.numerator == null) {
                this.writeFractional(result, output);
                this.writeInteger(result, output, this.integerSpecials, mods, this.showGroupingSeparator);
            } else {
                this.writeFraction(value, result, fractional, output, mods);
            }
        }
        DecimalFormatSymbols dfs = this.getDecimalFormatSymbols();
        String groupingSeparator = Character.toString(dfs.getGroupingSeparator());
        Iterator changes = mods.iterator();
        CellNumberStringMod nextChange = changes.hasNext() ? (CellNumberStringMod)changes.next() : null;
        SparseBitSet deletedChars = new SparseBitSet();
        int adjust = 0;
        for (Special s : this.specials) {
            int adjustedPos = s.pos + adjust;
            if (!deletedChars.get(s.pos) && output.charAt(adjustedPos) == '#') {
                output.deleteCharAt(adjustedPos);
                --adjust;
                deletedChars.set(s.pos);
            }
            while (nextChange != null && s == nextChange.getSpecial()) {
                int lenBefore = output.length();
                int modPos = s.pos + adjust;
                switch (nextChange.getOp()) {
                    case 2: {
                        if (nextChange.getToAdd().equals(groupingSeparator) && deletedChars.get(s.pos)) break;
                        output.insert(modPos + 1, nextChange.getToAdd());
                        break;
                    }
                    case 1: {
                        output.insert(modPos, nextChange.getToAdd());
                        break;
                    }
                    case 3: {
                        int modEndPos;
                        int delPos = s.pos;
                        if (!nextChange.isStartInclusive()) {
                            ++delPos;
                            ++modPos;
                        }
                        while (deletedChars.get(delPos)) {
                            ++delPos;
                            ++modPos;
                        }
                        int delEndPos = nextChange.getEnd().pos;
                        if (nextChange.isEndInclusive()) {
                            ++delEndPos;
                        }
                        if (modPos >= (modEndPos = delEndPos + adjust)) break;
                        if ("".equals(nextChange.getToAdd())) {
                            output.delete(modPos, modEndPos);
                        } else {
                            char fillCh = nextChange.getToAdd().charAt(0);
                            for (int i = modPos; i < modEndPos; ++i) {
                                output.setCharAt(i, fillCh);
                            }
                        }
                        deletedChars.set(delPos, delEndPos);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Unknown op: " + nextChange.getOp());
                    }
                }
                adjust += output.length() - lenBefore;
                nextChange = changes.hasNext() ? (CellNumberStringMod)changes.next() : null;
            }
        }
        if (negative) {
            toAppendTo.append('-');
        }
        toAppendTo.append(output);
    }

    private void writeScientific(double value, StringBuffer output, Set<CellNumberStringMod> mods) {
        StringBuffer result = new StringBuffer();
        FieldPosition fractionPos = new FieldPosition(1);
        this.decimalFmt.format(value, result, fractionPos);
        this.writeInteger(result, output, this.integerSpecials, mods, this.showGroupingSeparator);
        this.writeFractional(result, output);
        int ePos = fractionPos.getEndIndex();
        int signPos = ePos + 1;
        char expSignRes = result.charAt(signPos);
        if (expSignRes != '-') {
            expSignRes = '+';
            result.insert(signPos, '+');
        }
        ListIterator<Special> it = this.exponentSpecials.listIterator(1);
        Special expSign = it.next();
        char expSignFmt = expSign.ch;
        if (expSignRes == '-' || expSignFmt == '+') {
            mods.add(CellNumberFormatter.replaceMod(expSign, true, expSign, true, expSignRes));
        } else {
            mods.add(CellNumberFormatter.deleteMod(expSign, true, expSign, true));
        }
        StringBuffer exponentNum = new StringBuffer(result.substring(signPos + 1));
        this.writeInteger(exponentNum, output, this.exponentDigitSpecials, mods, false);
    }

    private void writeFraction(double value, StringBuffer result, double fractional, StringBuffer output, Set<CellNumberStringMod> mods) {
        if (!this.improperFraction) {
            boolean removeBecauseFraction;
            if (fractional == 0.0 && !CellNumberFormatter.hasChar('0', this.numeratorSpecials)) {
                this.writeInteger(result, output, this.integerSpecials, mods, false);
                Special start = CellNumberFormatter.lastSpecial(this.integerSpecials);
                Special end = CellNumberFormatter.lastSpecial(this.denominatorSpecials);
                if (CellNumberFormatter.hasChar('?', this.integerSpecials, this.numeratorSpecials, this.denominatorSpecials)) {
                    mods.add(CellNumberFormatter.replaceMod(start, false, end, true, ' '));
                } else {
                    mods.add(CellNumberFormatter.deleteMod(start, false, end, true));
                }
                return;
            }
            boolean numNoZero = !CellNumberFormatter.hasChar('0', this.numeratorSpecials);
            boolean intNoZero = !CellNumberFormatter.hasChar('0', this.integerSpecials);
            boolean intOnlyHash = this.integerSpecials.isEmpty() || this.integerSpecials.size() == 1 && CellNumberFormatter.hasChar('#', this.integerSpecials);
            boolean removeBecauseZero = fractional == 0.0 && (intOnlyHash || numNoZero);
            boolean bl = removeBecauseFraction = fractional != 0.0 && intNoZero;
            if (value == 0.0 && (removeBecauseZero || removeBecauseFraction)) {
                Special start = CellNumberFormatter.lastSpecial(this.integerSpecials);
                boolean hasPlaceHolder = CellNumberFormatter.hasChar('?', this.integerSpecials, this.numeratorSpecials);
                CellNumberStringMod sm = hasPlaceHolder ? CellNumberFormatter.replaceMod(start, true, this.numerator, false, ' ') : CellNumberFormatter.deleteMod(start, true, this.numerator, false);
                mods.add(sm);
            } else {
                this.writeInteger(result, output, this.integerSpecials, mods, false);
            }
        }
        try {
            int d;
            int n;
            if (fractional == 0.0 || this.improperFraction && fractional % 1.0 == 0.0) {
                n = (int)Math.round(fractional);
                d = 1;
            } else {
                SimpleFraction frac = SimpleFraction.buildFractionMaxDenominator(fractional, this.maxDenominator);
                n = frac.getNumerator();
                d = frac.getDenominator();
            }
            if (this.improperFraction) {
                n = (int)((long)n + Math.round(value * (double)d));
            }
            this.writeSingleInteger(this.numeratorFmt, n, output, this.numeratorSpecials, mods);
            this.writeSingleInteger(this.denominatorFmt, d, output, this.denominatorSpecials, mods);
        } catch (RuntimeException ignored) {
            LOG.log(7, "error while fraction evaluation", ignored);
        }
    }

    private String localiseFormat(String format) {
        DecimalFormatSymbols dfs = this.getDecimalFormatSymbols();
        if (format.contains(",") && dfs.getGroupingSeparator() != ',') {
            if (format.contains(".") && dfs.getDecimalSeparator() != '.') {
                format = CellNumberFormatter.replaceLast(format, "\\.", "[DECIMAL_SEPARATOR]");
                format = format.replace(',', dfs.getGroupingSeparator()).replace("[DECIMAL_SEPARATOR]", Character.toString(dfs.getDecimalSeparator()));
            } else {
                format = format.replace(',', dfs.getGroupingSeparator());
            }
        } else if (format.contains(".") && dfs.getDecimalSeparator() != '.') {
            format = format.replace('.', dfs.getDecimalSeparator());
        }
        return format;
    }

    private static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    private static boolean hasChar(char ch, List<Special> ... numSpecials) {
        for (List<Special> specials : numSpecials) {
            for (Special s : specials) {
                if (s.ch != ch) continue;
                return true;
            }
        }
        return false;
    }

    private void writeSingleInteger(String fmt, int num, StringBuffer output, List<Special> numSpecials, Set<CellNumberStringMod> mods) {
        StringBuffer sb = new StringBuffer();
        try (Formatter formatter = new Formatter(sb, this.locale);){
            formatter.format(this.locale, fmt, num);
        }
        this.writeInteger(sb, output, numSpecials, mods, false);
    }

    private void writeInteger(StringBuffer result, StringBuffer output, List<Special> numSpecials, Set<CellNumberStringMod> mods, boolean showGroupingSeparator) {
        char resultCh;
        int strip;
        DecimalFormatSymbols dfs = this.getDecimalFormatSymbols();
        String decimalSeparator = Character.toString(dfs.getDecimalSeparator());
        String groupingSeparator = Character.toString(dfs.getGroupingSeparator());
        int pos = result.indexOf(decimalSeparator) - 1;
        if (pos < 0) {
            pos = this.exponent != null && numSpecials == this.integerSpecials ? result.indexOf("E") - 1 : result.length() - 1;
        }
        for (strip = 0; strip < pos && ((resultCh = result.charAt(strip)) == '0' || resultCh == dfs.getGroupingSeparator()); ++strip) {
        }
        ListIterator<Special> it = numSpecials.listIterator(numSpecials.size());
        boolean followWithGroupingSeparator = false;
        Special lastOutputIntegerDigit = null;
        int digit = 0;
        while (it.hasPrevious()) {
            char resultCh2 = pos >= 0 ? (char)result.charAt(pos) : (char)'0';
            Special s = it.previous();
            followWithGroupingSeparator = showGroupingSeparator && digit > 0 && digit % 3 == 0;
            boolean zeroStrip = false;
            if (resultCh2 != '0' || s.ch == '0' || s.ch == '?' || pos >= strip) {
                zeroStrip = s.ch == '?' && pos < strip;
                output.setCharAt(s.pos, zeroStrip ? (char)' ' : resultCh2);
                lastOutputIntegerDigit = s;
            }
            if (followWithGroupingSeparator) {
                mods.add(CellNumberFormatter.insertMod(s, zeroStrip ? " " : groupingSeparator, 2));
                followWithGroupingSeparator = false;
            }
            ++digit;
            --pos;
        }
        if (pos >= 0) {
            StringBuffer extraLeadingDigits = new StringBuffer(result.substring(0, ++pos));
            if (showGroupingSeparator) {
                while (pos > 0) {
                    if (digit > 0 && digit % 3 == 0) {
                        extraLeadingDigits.insert(pos, groupingSeparator);
                    }
                    ++digit;
                    --pos;
                }
            }
            mods.add(CellNumberFormatter.insertMod(lastOutputIntegerDigit, extraLeadingDigits, 1));
        }
    }

    private void writeFractional(StringBuffer result, StringBuffer output) {
        if (this.fractionalSpecials.size() > 0) {
            int strip;
            String decimalSeparator = Character.toString(this.getDecimalFormatSymbols().getDecimalSeparator());
            int digit = result.indexOf(decimalSeparator) + 1;
            for (strip = this.exponent != null ? result.indexOf("e") - 1 : result.length() - 1; strip > digit && result.charAt(strip) == '0'; --strip) {
            }
            for (Special s : this.fractionalSpecials) {
                char resultCh = result.charAt(digit);
                if (resultCh != '0' || s.ch == '0' || digit < strip) {
                    output.setCharAt(s.pos, resultCh);
                } else if (s.ch == '?') {
                    output.setCharAt(s.pos, ' ');
                }
                ++digit;
            }
        }
    }

    @Override
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        this.SIMPLE_NUMBER.formatValue(toAppendTo, value);
    }

    private static Special lastSpecial(List<Special> s) {
        return s.get(s.size() - 1);
    }

    static class Special {
        final char ch;
        int pos;

        Special(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }

        public String toString() {
            return "'" + this.ch + "' @ " + this.pos;
        }
    }

    private static class GeneralNumberFormatter
    extends CellFormatter {
        private GeneralNumberFormatter(Locale locale) {
            super(locale, "General");
        }

        @Override
        public void formatValue(StringBuffer toAppendTo, Object value) {
            Number num;
            if (value == null) {
                return;
            }
            CellFormatter cf = value instanceof Number ? ((num = (Number)value).doubleValue() % 1.0 == 0.0 ? new CellNumberFormatter(this.locale, "#") : new CellNumberFormatter(this.locale, "#.#")) : CellTextFormatter.SIMPLE_TEXT;
            cf.formatValue(toAppendTo, value);
        }

        @Override
        public void simpleValue(StringBuffer toAppendTo, Object value) {
            this.formatValue(toAppendTo, value);
        }
    }
}

