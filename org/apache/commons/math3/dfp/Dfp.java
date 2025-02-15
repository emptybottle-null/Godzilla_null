/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.dfp;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.dfp.DfpField;
import org.apache.commons.math3.dfp.DfpMath;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Dfp
implements RealFieldElement<Dfp> {
    public static final int RADIX = 10000;
    public static final int MIN_EXP = -32767;
    public static final int MAX_EXP = 32768;
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    public static final byte INFINITE = 1;
    public static final byte SNAN = 2;
    public static final byte QNAN = 3;
    private static final String NAN_STRING = "NaN";
    private static final String POS_INFINITY_STRING = "Infinity";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String ADD_TRAP = "add";
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String DIVIDE_TRAP = "divide";
    private static final String SQRT_TRAP = "sqrt";
    private static final String ALIGN_TRAP = "align";
    private static final String TRUNC_TRAP = "trunc";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String LESS_THAN_TRAP = "lessThan";
    private static final String GREATER_THAN_TRAP = "greaterThan";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    protected int[] mant;
    protected byte sign;
    protected int exp;
    protected byte nans;
    private final DfpField field;

    protected Dfp(DfpField field) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = field;
    }

    protected Dfp(DfpField field, byte x) {
        this(field, (long)x);
    }

    protected Dfp(DfpField field, int x) {
        this(field, (long)x);
    }

    protected Dfp(DfpField field, long x) {
        this.mant = new int[field.getRadixDigits()];
        this.nans = 0;
        this.field = field;
        boolean isLongMin = false;
        if (x == Long.MIN_VALUE) {
            isLongMin = true;
            ++x;
        }
        if (x < 0L) {
            this.sign = (byte)-1;
            x = -x;
        } else {
            this.sign = 1;
        }
        this.exp = 0;
        while (x != 0L) {
            System.arraycopy(this.mant, this.mant.length - this.exp, this.mant, this.mant.length - 1 - this.exp, this.exp);
            this.mant[this.mant.length - 1] = (int)(x % 10000L);
            x /= 10000L;
            ++this.exp;
        }
        if (isLongMin) {
            for (int i = 0; i < this.mant.length - 1; ++i) {
                if (this.mant[i] == 0) continue;
                int n = i;
                this.mant[n] = this.mant[n] + 1;
                break;
            }
        }
    }

    protected Dfp(DfpField field, double x) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = field;
        long bits = Double.doubleToLongBits(x);
        long mantissa = bits & 0xFFFFFFFFFFFFFL;
        int exponent = (int)((bits & 0x7FF0000000000000L) >> 52) - 1023;
        if (exponent == -1023) {
            if (x == 0.0) {
                if ((bits & Long.MIN_VALUE) != 0L) {
                    this.sign = (byte)-1;
                }
                return;
            }
            ++exponent;
            while ((mantissa & 0x10000000000000L) == 0L) {
                --exponent;
                mantissa <<= 1;
            }
            mantissa &= 0xFFFFFFFFFFFFFL;
        }
        if (exponent == 1024) {
            if (x != x) {
                this.sign = 1;
                this.nans = (byte)3;
            } else if (x < 0.0) {
                this.sign = (byte)-1;
                this.nans = 1;
            } else {
                this.sign = 1;
                this.nans = 1;
            }
            return;
        }
        Dfp xdfp = new Dfp(field, mantissa);
        xdfp = xdfp.divide(new Dfp(field, 0x10000000000000L)).add(field.getOne());
        xdfp = xdfp.multiply(DfpMath.pow(field.getTwo(), exponent));
        if ((bits & Long.MIN_VALUE) != 0L) {
            xdfp = xdfp.negate();
        }
        System.arraycopy(xdfp.mant, 0, this.mant, 0, this.mant.length);
        this.sign = xdfp.sign;
        this.exp = xdfp.exp;
        this.nans = xdfp.nans;
    }

    public Dfp(Dfp d) {
        this.mant = (int[])d.mant.clone();
        this.sign = d.sign;
        this.exp = d.exp;
        this.nans = d.nans;
        this.field = d.field;
    }

    protected Dfp(DfpField field, String s) {
        String fpdecimal;
        this.mant = new int[field.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = field;
        boolean decimalFound = false;
        int rsize = 4;
        int offset = 4;
        char[] striped = new char[this.getRadixDigits() * 4 + 8];
        if (s.equals(POS_INFINITY_STRING)) {
            this.sign = 1;
            this.nans = 1;
            return;
        }
        if (s.equals(NEG_INFINITY_STRING)) {
            this.sign = (byte)-1;
            this.nans = 1;
            return;
        }
        if (s.equals(NAN_STRING)) {
            this.sign = 1;
            this.nans = (byte)3;
            return;
        }
        int p = s.indexOf("e");
        if (p == -1) {
            p = s.indexOf("E");
        }
        int sciexp = 0;
        if (p != -1) {
            fpdecimal = s.substring(0, p);
            String fpexp = s.substring(p + 1);
            boolean negative = false;
            for (int i = 0; i < fpexp.length(); ++i) {
                if (fpexp.charAt(i) == '-') {
                    negative = true;
                    continue;
                }
                if (fpexp.charAt(i) < '0' || fpexp.charAt(i) > '9') continue;
                sciexp = sciexp * 10 + fpexp.charAt(i) - 48;
            }
            if (negative) {
                sciexp = -sciexp;
            }
        } else {
            fpdecimal = s;
        }
        if (fpdecimal.indexOf("-") != -1) {
            this.sign = (byte)-1;
        }
        p = 0;
        int decimalPos = 0;
        while (fpdecimal.charAt(p) < '1' || fpdecimal.charAt(p) > '9') {
            if (decimalFound && fpdecimal.charAt(p) == '0') {
                --decimalPos;
            }
            if (fpdecimal.charAt(p) == '.') {
                decimalFound = true;
            }
            if (++p != fpdecimal.length()) continue;
        }
        int q = 4;
        striped[0] = 48;
        striped[1] = 48;
        striped[2] = 48;
        striped[3] = 48;
        int significantDigits = 0;
        while (p != fpdecimal.length() && q != this.mant.length * 4 + 4 + 1) {
            if (fpdecimal.charAt(p) == '.') {
                decimalFound = true;
                decimalPos = significantDigits;
                ++p;
                continue;
            }
            if (fpdecimal.charAt(p) < '0' || fpdecimal.charAt(p) > '9') {
                ++p;
                continue;
            }
            striped[q] = fpdecimal.charAt(p);
            ++q;
            ++p;
            ++significantDigits;
        }
        if (decimalFound && q != 4) {
            while (--q != 4 && striped[q] == '0') {
                --significantDigits;
            }
        }
        if (decimalFound && significantDigits == 0) {
            decimalPos = 0;
        }
        if (!decimalFound) {
            decimalPos = q - 4;
        }
        q = 4;
        for (p = significantDigits - 1 + 4; p > q && striped[p] == '0'; --p) {
        }
        int i = (400 - decimalPos - sciexp % 4) % 4;
        q -= i;
        decimalPos += i;
        while (p - q < this.mant.length * 4) {
            for (i = 0; i < 4; ++i) {
                striped[++p] = 48;
            }
        }
        for (i = this.mant.length - 1; i >= 0; --i) {
            this.mant[i] = (striped[q] - 48) * 1000 + (striped[q + 1] - 48) * 100 + (striped[q + 2] - 48) * 10 + (striped[q + 3] - 48);
            q += 4;
        }
        this.exp = (decimalPos + sciexp) / 4;
        if (q < striped.length) {
            this.round((striped[q] - 48) * 1000);
        }
    }

    protected Dfp(DfpField field, byte sign, byte nans) {
        this.field = field;
        this.mant = new int[field.getRadixDigits()];
        this.sign = sign;
        this.exp = 0;
        this.nans = nans;
    }

    public Dfp newInstance() {
        return new Dfp(this.getField());
    }

    public Dfp newInstance(byte x) {
        return new Dfp(this.getField(), x);
    }

    public Dfp newInstance(int x) {
        return new Dfp(this.getField(), x);
    }

    public Dfp newInstance(long x) {
        return new Dfp(this.getField(), x);
    }

    public Dfp newInstance(double x) {
        return new Dfp(this.getField(), x);
    }

    public Dfp newInstance(Dfp d) {
        if (this.field.getRadixDigits() != d.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            return this.dotrap(1, NEW_INSTANCE_TRAP, d, result);
        }
        return new Dfp(d);
    }

    public Dfp newInstance(String s) {
        return new Dfp(this.field, s);
    }

    public Dfp newInstance(byte sig, byte code) {
        return this.field.newDfp(sig, code);
    }

    public DfpField getField() {
        return this.field;
    }

    public int getRadixDigits() {
        return this.field.getRadixDigits();
    }

    public Dfp getZero() {
        return this.field.getZero();
    }

    public Dfp getOne() {
        return this.field.getOne();
    }

    public Dfp getTwo() {
        return this.field.getTwo();
    }

    protected void shiftLeft() {
        for (int i = this.mant.length - 1; i > 0; --i) {
            this.mant[i] = this.mant[i - 1];
        }
        this.mant[0] = 0;
        --this.exp;
    }

    protected void shiftRight() {
        for (int i = 0; i < this.mant.length - 1; ++i) {
            this.mant[i] = this.mant[i + 1];
        }
        this.mant[this.mant.length - 1] = 0;
        ++this.exp;
    }

    protected int align(int e) {
        int lostdigit = 0;
        boolean inexact = false;
        int diff = this.exp - e;
        int adiff = diff;
        if (adiff < 0) {
            adiff = -adiff;
        }
        if (diff == 0) {
            return 0;
        }
        if (adiff > this.mant.length + 1) {
            Arrays.fill(this.mant, 0);
            this.exp = e;
            this.field.setIEEEFlagsBits(16);
            this.dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        for (int i = 0; i < adiff; ++i) {
            if (diff < 0) {
                if (lostdigit != 0) {
                    inexact = true;
                }
                lostdigit = this.mant[0];
                this.shiftRight();
                continue;
            }
            this.shiftLeft();
        }
        if (inexact) {
            this.field.setIEEEFlagsBits(16);
            this.dotrap(16, ALIGN_TRAP, this, this);
        }
        return lostdigit;
    }

    public boolean lessThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            this.dotrap(1, LESS_THAN_TRAP, x, result);
            return false;
        }
        if (this.isNaN() || x.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, x, this.newInstance(this.getZero()));
            return false;
        }
        return Dfp.compare(this, x) < 0;
    }

    public boolean greaterThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            this.dotrap(1, GREATER_THAN_TRAP, x, result);
            return false;
        }
        if (this.isNaN() || x.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, GREATER_THAN_TRAP, x, this.newInstance(this.getZero()));
            return false;
        }
        return Dfp.compare(this, x) > 0;
    }

    public boolean negativeOrNull() {
        if (this.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, this, this.newInstance(this.getZero()));
            return false;
        }
        return this.sign < 0 || this.mant[this.mant.length - 1] == 0 && !this.isInfinite();
    }

    public boolean strictlyNegative() {
        if (this.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, this, this.newInstance(this.getZero()));
            return false;
        }
        return this.sign < 0 && (this.mant[this.mant.length - 1] != 0 || this.isInfinite());
    }

    public boolean positiveOrNull() {
        if (this.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, this, this.newInstance(this.getZero()));
            return false;
        }
        return this.sign > 0 || this.mant[this.mant.length - 1] == 0 && !this.isInfinite();
    }

    public boolean strictlyPositive() {
        if (this.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, this, this.newInstance(this.getZero()));
            return false;
        }
        return this.sign > 0 && (this.mant[this.mant.length - 1] != 0 || this.isInfinite());
    }

    @Override
    public Dfp abs() {
        Dfp result = this.newInstance(this);
        result.sign = 1;
        return result;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        return this.nans == 3 || this.nans == 2;
    }

    public boolean isZero() {
        if (this.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            this.dotrap(1, LESS_THAN_TRAP, this, this.newInstance(this.getZero()));
            return false;
        }
        return this.mant[this.mant.length - 1] == 0 && !this.isInfinite();
    }

    public boolean equals(Object other) {
        if (other instanceof Dfp) {
            Dfp x = (Dfp)other;
            if (this.isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits()) {
                return false;
            }
            return Dfp.compare(this, x) == 0;
        }
        return false;
    }

    public int hashCode() {
        return 17 + (this.isZero() ? 0 : this.sign << 8) + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp x) {
        if (this.isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits()) {
            return false;
        }
        return this.greaterThan(x) || this.lessThan(x);
    }

    private static int compare(Dfp a, Dfp b) {
        if (a.mant[a.mant.length - 1] == 0 && b.mant[b.mant.length - 1] == 0 && a.nans == 0 && b.nans == 0) {
            return 0;
        }
        if (a.sign != b.sign) {
            if (a.sign == -1) {
                return -1;
            }
            return 1;
        }
        if (a.nans == 1 && b.nans == 0) {
            return a.sign;
        }
        if (a.nans == 0 && b.nans == 1) {
            return -b.sign;
        }
        if (a.nans == 1 && b.nans == 1) {
            return 0;
        }
        if (b.mant[b.mant.length - 1] != 0 && a.mant[b.mant.length - 1] != 0) {
            if (a.exp < b.exp) {
                return -a.sign;
            }
            if (a.exp > b.exp) {
                return a.sign;
            }
        }
        for (int i = a.mant.length - 1; i >= 0; --i) {
            if (a.mant[i] > b.mant[i]) {
                return a.sign;
            }
            if (a.mant[i] >= b.mant[i]) continue;
            return -a.sign;
        }
        return 0;
    }

    @Override
    public Dfp rint() {
        return this.trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    @Override
    public Dfp floor() {
        return this.trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    @Override
    public Dfp ceil() {
        return this.trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    @Override
    public Dfp remainder(Dfp d) {
        Dfp result = this.subtract(this.divide(d).rint().multiply(d));
        if (result.mant[this.mant.length - 1] == 0) {
            result.sign = this.sign;
        }
        return result;
    }

    protected Dfp trunc(DfpField.RoundingMode rmode) {
        boolean changed = false;
        if (this.isNaN()) {
            return this.newInstance(this);
        }
        if (this.nans == 1) {
            return this.newInstance(this);
        }
        if (this.mant[this.mant.length - 1] == 0) {
            return this.newInstance(this);
        }
        if (this.exp < 0) {
            this.field.setIEEEFlagsBits(16);
            Dfp result = this.newInstance(this.getZero());
            result = this.dotrap(16, TRUNC_TRAP, this, result);
            return result;
        }
        if (this.exp >= this.mant.length) {
            return this.newInstance(this);
        }
        Dfp result = this.newInstance(this);
        for (int i = 0; i < this.mant.length - result.exp; ++i) {
            changed |= result.mant[i] != 0;
            result.mant[i] = 0;
        }
        if (changed) {
            switch (rmode) {
                case ROUND_FLOOR: {
                    if (result.sign != -1) break;
                    result = result.add(this.newInstance(-1));
                    break;
                }
                case ROUND_CEIL: {
                    if (result.sign != 1) break;
                    result = result.add(this.getOne());
                    break;
                }
                default: {
                    Dfp half = this.newInstance("0.5");
                    Dfp a = this.subtract(result);
                    a.sign = 1;
                    if (a.greaterThan(half)) {
                        a = this.newInstance(this.getOne());
                        a.sign = this.sign;
                        result = result.add(a);
                    }
                    if (!a.equals(half) || result.exp <= 0 || (result.mant[this.mant.length - result.exp] & 1) == 0) break;
                    a = this.newInstance(this.getOne());
                    a.sign = this.sign;
                    result = result.add(a);
                }
            }
            this.field.setIEEEFlagsBits(16);
            result = this.dotrap(16, TRUNC_TRAP, this, result);
            return result;
        }
        return result;
    }

    public int intValue() {
        int result = 0;
        Dfp rounded = this.rint();
        if (rounded.greaterThan(this.newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (rounded.lessThan(this.newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        for (int i = this.mant.length - 1; i >= this.mant.length - rounded.exp; --i) {
            result = result * 10000 + rounded.mant[i];
        }
        if (rounded.sign == -1) {
            result = -result;
        }
        return result;
    }

    public int log10K() {
        return this.exp - 1;
    }

    public Dfp power10K(int e) {
        Dfp d = this.newInstance(this.getOne());
        d.exp = e + 1;
        return d;
    }

    public int intLog10() {
        if (this.mant[this.mant.length - 1] > 1000) {
            return this.exp * 4 - 1;
        }
        if (this.mant[this.mant.length - 1] > 100) {
            return this.exp * 4 - 2;
        }
        if (this.mant[this.mant.length - 1] > 10) {
            return this.exp * 4 - 3;
        }
        return this.exp * 4 - 4;
    }

    public Dfp power10(int e) {
        Dfp d = this.newInstance(this.getOne());
        d.exp = e >= 0 ? e / 4 + 1 : (e + 1) / 4;
        switch ((e % 4 + 4) % 4) {
            case 0: {
                break;
            }
            case 1: {
                d = d.multiply(10);
                break;
            }
            case 2: {
                d = d.multiply(100);
                break;
            }
            default: {
                d = d.multiply(1000);
            }
        }
        return d;
    }

    protected int complement(int extra) {
        extra = 10000 - extra;
        for (int i = 0; i < this.mant.length; ++i) {
            this.mant[i] = 10000 - this.mant[i] - 1;
        }
        int rh = extra / 10000;
        extra -= rh * 10000;
        for (int i = 0; i < this.mant.length; ++i) {
            int r = this.mant[i] + rh;
            rh = r / 10000;
            this.mant[i] = r - rh * 10000;
        }
        return extra;
    }

    @Override
    public Dfp add(Dfp x) {
        int excp;
        int i;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            return this.dotrap(1, ADD_TRAP, x, result);
        }
        if (this.nans != 0 || x.nans != 0) {
            if (this.isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0) {
                return this;
            }
            if (x.nans == 1 && this.nans == 0) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign == x.sign) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign != x.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp result = this.newInstance(this.getZero());
                result.nans = (byte)3;
                result = this.dotrap(1, ADD_TRAP, x, result);
                return result;
            }
        }
        Dfp a = this.newInstance(this);
        Dfp b = this.newInstance(x);
        Dfp result = this.newInstance(this.getZero());
        byte asign = a.sign;
        byte bsign = b.sign;
        a.sign = 1;
        b.sign = 1;
        byte rsign = bsign;
        if (Dfp.compare(a, b) > 0) {
            rsign = asign;
        }
        if (b.mant[this.mant.length - 1] == 0) {
            b.exp = a.exp;
        }
        if (a.mant[this.mant.length - 1] == 0) {
            a.exp = b.exp;
        }
        int aextradigit = 0;
        int bextradigit = 0;
        if (a.exp < b.exp) {
            aextradigit = a.align(b.exp);
        } else {
            bextradigit = b.align(a.exp);
        }
        if (asign != bsign) {
            if (asign == rsign) {
                bextradigit = b.complement(bextradigit);
            } else {
                aextradigit = a.complement(aextradigit);
            }
        }
        int rh = 0;
        for (i = 0; i < this.mant.length; ++i) {
            int r = a.mant[i] + b.mant[i] + rh;
            rh = r / 10000;
            result.mant[i] = r - rh * 10000;
        }
        result.exp = a.exp;
        result.sign = rsign;
        if (rh != 0 && asign == bsign) {
            int lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[this.mant.length - 1] = rh;
            int excp2 = result.round(lostdigit);
            if (excp2 != 0) {
                result = this.dotrap(excp2, ADD_TRAP, x, result);
            }
        }
        for (i = 0; i < this.mant.length && result.mant[this.mant.length - 1] == 0; ++i) {
            result.shiftLeft();
            if (i != 0) continue;
            result.mant[0] = aextradigit + bextradigit;
            aextradigit = 0;
            bextradigit = 0;
        }
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
            if (asign != bsign) {
                result.sign = 1;
            }
        }
        if ((excp = result.round(aextradigit + bextradigit)) != 0) {
            result = this.dotrap(excp, ADD_TRAP, x, result);
        }
        return result;
    }

    @Override
    public Dfp negate() {
        Dfp result = this.newInstance(this);
        result.sign = -result.sign;
        return result;
    }

    @Override
    public Dfp subtract(Dfp x) {
        return this.add(x.negate());
    }

    protected int round(int n) {
        boolean inc = false;
        switch (this.field.getRoundingMode()) {
            case ROUND_DOWN: {
                inc = false;
                break;
            }
            case ROUND_UP: {
                inc = n != 0;
                break;
            }
            case ROUND_HALF_UP: {
                inc = n >= 5000;
                break;
            }
            case ROUND_HALF_DOWN: {
                inc = n > 5000;
                break;
            }
            case ROUND_HALF_EVEN: {
                inc = n > 5000 || n == 5000 && (this.mant[0] & 1) == 1;
                break;
            }
            case ROUND_HALF_ODD: {
                inc = n > 5000 || n == 5000 && (this.mant[0] & 1) == 0;
                break;
            }
            case ROUND_CEIL: {
                inc = this.sign == 1 && n != 0;
                break;
            }
            default: {
                boolean bl = inc = this.sign == -1 && n != 0;
            }
        }
        if (inc) {
            int rh = 1;
            for (int i = 0; i < this.mant.length; ++i) {
                int r = this.mant[i] + rh;
                rh = r / 10000;
                this.mant[i] = r - rh * 10000;
            }
            if (rh != 0) {
                this.shiftRight();
                this.mant[this.mant.length - 1] = rh;
            }
        }
        if (this.exp < -32767) {
            this.field.setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            this.field.setIEEEFlagsBits(4);
            return 4;
        }
        if (n != 0) {
            this.field.setIEEEFlagsBits(16);
            return 16;
        }
        return 0;
    }

    @Override
    public Dfp multiply(Dfp x) {
        int excp;
        int i;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            return this.dotrap(1, MULTIPLY_TRAP, x, result);
        }
        Dfp result = this.newInstance(this.getZero());
        if (this.nans != 0 || x.nans != 0) {
            if (this.isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] != 0) {
                result = this.newInstance(this);
                result.sign = (byte)(this.sign * x.sign);
                return result;
            }
            if (x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                result = this.newInstance(x);
                result.sign = (byte)(this.sign * x.sign);
                return result;
            }
            if (x.nans == 1 && this.nans == 1) {
                result = this.newInstance(this);
                result.sign = (byte)(this.sign * x.sign);
                return result;
            }
            if (x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] == 0 || this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] == 0) {
                this.field.setIEEEFlagsBits(1);
                result = this.newInstance(this.getZero());
                result.nans = (byte)3;
                result = this.dotrap(1, MULTIPLY_TRAP, x, result);
                return result;
            }
        }
        int[] product = new int[this.mant.length * 2];
        for (int i2 = 0; i2 < this.mant.length; ++i2) {
            int rh = 0;
            for (int j = 0; j < this.mant.length; ++j) {
                int r = this.mant[i2] * x.mant[j];
                rh = (r += product[i2 + j] + rh) / 10000;
                product[i2 + j] = r - rh * 10000;
            }
            product[i2 + this.mant.length] = rh;
        }
        int md = this.mant.length * 2 - 1;
        for (i = this.mant.length * 2 - 1; i >= 0; --i) {
            if (product[i] == 0) continue;
            md = i;
            break;
        }
        for (i = 0; i < this.mant.length; ++i) {
            result.mant[this.mant.length - i - 1] = product[md - i];
        }
        result.exp = this.exp + x.exp + md - 2 * this.mant.length + 1;
        result.sign = (byte)(this.sign == x.sign ? 1 : -1);
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
        }
        if ((excp = md > this.mant.length - 1 ? result.round(product[md - this.mant.length]) : result.round(0)) != 0) {
            result = this.dotrap(excp, MULTIPLY_TRAP, x, result);
        }
        return result;
    }

    @Override
    public Dfp multiply(int x) {
        if (x >= 0 && x < 10000) {
            return this.multiplyFast(x);
        }
        return this.multiply(this.newInstance(x));
    }

    private Dfp multiplyFast(int x) {
        int excp;
        Dfp result = this.newInstance(this);
        if (this.nans != 0) {
            if (this.isNaN()) {
                return this;
            }
            if (this.nans == 1 && x != 0) {
                result = this.newInstance(this);
                return result;
            }
            if (this.nans == 1 && x == 0) {
                this.field.setIEEEFlagsBits(1);
                result = this.newInstance(this.getZero());
                result.nans = (byte)3;
                result = this.dotrap(1, MULTIPLY_TRAP, this.newInstance(this.getZero()), result);
                return result;
            }
        }
        if (x < 0 || x >= 10000) {
            this.field.setIEEEFlagsBits(1);
            result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            result = this.dotrap(1, MULTIPLY_TRAP, result, result);
            return result;
        }
        int rh = 0;
        for (int i = 0; i < this.mant.length; ++i) {
            int r = this.mant[i] * x + rh;
            rh = r / 10000;
            result.mant[i] = r - rh * 10000;
        }
        int lostdigit = 0;
        if (rh != 0) {
            lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[this.mant.length - 1] = rh;
        }
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
        }
        if ((excp = result.round(lostdigit)) != 0) {
            result = this.dotrap(excp, MULTIPLY_TRAP, result, result);
        }
        return result;
    }

    @Override
    public Dfp divide(Dfp divisor) {
        int excp;
        int i;
        int trial = 0;
        int md = 0;
        if (this.field.getRadixDigits() != divisor.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            return this.dotrap(1, DIVIDE_TRAP, divisor, result);
        }
        Dfp result = this.newInstance(this.getZero());
        if (this.nans != 0 || divisor.nans != 0) {
            if (this.isNaN()) {
                return this;
            }
            if (divisor.isNaN()) {
                return divisor;
            }
            if (this.nans == 1 && divisor.nans == 0) {
                result = this.newInstance(this);
                result.sign = (byte)(this.sign * divisor.sign);
                return result;
            }
            if (divisor.nans == 1 && this.nans == 0) {
                result = this.newInstance(this.getZero());
                result.sign = (byte)(this.sign * divisor.sign);
                return result;
            }
            if (divisor.nans == 1 && this.nans == 1) {
                this.field.setIEEEFlagsBits(1);
                result = this.newInstance(this.getZero());
                result.nans = (byte)3;
                result = this.dotrap(1, DIVIDE_TRAP, divisor, result);
                return result;
            }
        }
        if (divisor.mant[this.mant.length - 1] == 0) {
            this.field.setIEEEFlagsBits(2);
            result = this.newInstance(this.getZero());
            result.sign = (byte)(this.sign * divisor.sign);
            result.nans = 1;
            result = this.dotrap(2, DIVIDE_TRAP, divisor, result);
            return result;
        }
        int[] dividend = new int[this.mant.length + 1];
        int[] quotient = new int[this.mant.length + 2];
        int[] remainder = new int[this.mant.length + 1];
        dividend[this.mant.length] = 0;
        quotient[this.mant.length] = 0;
        quotient[this.mant.length + 1] = 0;
        remainder[this.mant.length] = 0;
        for (i = 0; i < this.mant.length; ++i) {
            dividend[i] = this.mant[i];
            quotient[i] = 0;
            remainder[i] = 0;
        }
        int nsqd = 0;
        for (int qd = this.mant.length + 1; qd >= 0; --qd) {
            int divMsb = dividend[this.mant.length] * 10000 + dividend[this.mant.length - 1];
            int min = divMsb / (divisor.mant[this.mant.length - 1] + 1);
            int max = (divMsb + 1) / divisor.mant[this.mant.length - 1];
            boolean trialgood = false;
            while (!trialgood) {
                int i2;
                trial = (min + max) / 2;
                int rh = 0;
                for (i2 = 0; i2 < this.mant.length + 1; ++i2) {
                    int dm = i2 < this.mant.length ? divisor.mant[i2] : 0;
                    int r = dm * trial + rh;
                    rh = r / 10000;
                    remainder[i2] = r - rh * 10000;
                }
                rh = 1;
                for (i2 = 0; i2 < this.mant.length + 1; ++i2) {
                    int r = 9999 - remainder[i2] + dividend[i2] + rh;
                    rh = r / 10000;
                    remainder[i2] = r - rh * 10000;
                }
                if (rh == 0) {
                    max = trial - 1;
                    continue;
                }
                int minadj = remainder[this.mant.length] * 10000 + remainder[this.mant.length - 1];
                if ((minadj /= divisor.mant[this.mant.length - 1] + 1) >= 2) {
                    min = trial + minadj;
                    continue;
                }
                trialgood = false;
                for (i2 = this.mant.length - 1; i2 >= 0; --i2) {
                    if (divisor.mant[i2] > remainder[i2]) {
                        trialgood = true;
                    }
                    if (divisor.mant[i2] < remainder[i2]) break;
                }
                if (remainder[this.mant.length] != 0) {
                    trialgood = false;
                }
                if (trialgood) continue;
                min = trial + 1;
            }
            quotient[qd] = trial;
            if (trial != 0 || nsqd != 0) {
                ++nsqd;
            }
            if (this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && nsqd == this.mant.length || nsqd > this.mant.length) break;
            dividend[0] = 0;
            for (int i3 = 0; i3 < this.mant.length; ++i3) {
                dividend[i3 + 1] = remainder[i3];
            }
        }
        md = this.mant.length;
        for (i = this.mant.length + 1; i >= 0; --i) {
            if (quotient[i] == 0) continue;
            md = i;
            break;
        }
        for (i = 0; i < this.mant.length; ++i) {
            result.mant[this.mant.length - i - 1] = quotient[md - i];
        }
        result.exp = this.exp - divisor.exp + md - this.mant.length;
        result.sign = (byte)(this.sign == divisor.sign ? 1 : -1);
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
        }
        if ((excp = md > this.mant.length - 1 ? result.round(quotient[md - this.mant.length]) : result.round(0)) != 0) {
            result = this.dotrap(excp, DIVIDE_TRAP, divisor, result);
        }
        return result;
    }

    @Override
    public Dfp divide(int divisor) {
        int excp;
        if (this.nans != 0) {
            if (this.isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return this.newInstance(this);
            }
        }
        if (divisor == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp result = this.newInstance(this.getZero());
            result.sign = this.sign;
            result.nans = 1;
            result = this.dotrap(2, DIVIDE_TRAP, this.getZero(), result);
            return result;
        }
        if (divisor < 0 || divisor >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            result = this.dotrap(1, DIVIDE_TRAP, result, result);
            return result;
        }
        Dfp result = this.newInstance(this);
        int rl = 0;
        for (int i = this.mant.length - 1; i >= 0; --i) {
            int r = rl * 10000 + result.mant[i];
            int rh = r / divisor;
            rl = r - rh * divisor;
            result.mant[i] = rh;
        }
        if (result.mant[this.mant.length - 1] == 0) {
            result.shiftLeft();
            int r = rl * 10000;
            int rh = r / divisor;
            rl = r - rh * divisor;
            result.mant[0] = rh;
        }
        if ((excp = result.round(rl * 10000 / divisor)) != 0) {
            result = this.dotrap(excp, DIVIDE_TRAP, result, result);
        }
        return result;
    }

    @Override
    public Dfp reciprocal() {
        return this.field.getOne().divide(this);
    }

    @Override
    public Dfp sqrt() {
        if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
            return this.newInstance(this);
        }
        if (this.nans != 0) {
            if (this.nans == 1 && this.sign == 1) {
                return this.newInstance(this);
            }
            if (this.nans == 3) {
                return this.newInstance(this);
            }
            if (this.nans == 2) {
                this.field.setIEEEFlagsBits(1);
                Dfp result = this.newInstance(this);
                result = this.dotrap(1, SQRT_TRAP, null, result);
                return result;
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this);
            result.nans = (byte)3;
            result = this.dotrap(1, SQRT_TRAP, null, result);
            return result;
        }
        Dfp x = this.newInstance(this);
        if (x.exp < -1 || x.exp > 1) {
            x.exp = this.exp / 2;
        }
        switch (x.mant[this.mant.length - 1] / 2000) {
            case 0: {
                x.mant[this.mant.length - 1] = x.mant[this.mant.length - 1] / 2 + 1;
                break;
            }
            case 2: {
                x.mant[this.mant.length - 1] = 1500;
                break;
            }
            case 3: {
                x.mant[this.mant.length - 1] = 2200;
                break;
            }
            default: {
                x.mant[this.mant.length - 1] = 3000;
            }
        }
        Dfp dx = this.newInstance(x);
        Dfp px = this.getZero();
        Dfp ppx = this.getZero();
        while (x.unequal(px)) {
            dx = this.newInstance(x);
            dx.sign = (byte)-1;
            dx = dx.add(this.divide(x));
            dx = dx.divide(2);
            ppx = px;
            px = x;
            if (!(x = x.add(dx)).equals(ppx) && dx.mant[this.mant.length - 1] != 0) continue;
            break;
        }
        return x;
    }

    public String toString() {
        if (this.nans != 0) {
            if (this.nans == 1) {
                return this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
            }
            return NAN_STRING;
        }
        if (this.exp > this.mant.length || this.exp < -1) {
            return this.dfp2sci();
        }
        return this.dfp2string();
    }

    protected String dfp2sci() {
        int e;
        char[] rawdigits = new char[this.mant.length * 4];
        char[] outputbuffer = new char[this.mant.length * 4 + 20];
        int p = 0;
        for (int i = this.mant.length - 1; i >= 0; --i) {
            rawdigits[p++] = (char)(this.mant[i] / 1000 + 48);
            rawdigits[p++] = (char)(this.mant[i] / 100 % 10 + 48);
            rawdigits[p++] = (char)(this.mant[i] / 10 % 10 + 48);
            rawdigits[p++] = (char)(this.mant[i] % 10 + 48);
        }
        for (p = 0; p < rawdigits.length && rawdigits[p] == '0'; ++p) {
        }
        int shf = p;
        int q = 0;
        if (this.sign == -1) {
            outputbuffer[q++] = 45;
        }
        if (p != rawdigits.length) {
            outputbuffer[q++] = rawdigits[p++];
            outputbuffer[q++] = 46;
            while (p < rawdigits.length) {
                outputbuffer[q++] = rawdigits[p++];
            }
        } else {
            outputbuffer[q++] = 48;
            outputbuffer[q++] = 46;
            outputbuffer[q++] = 48;
            outputbuffer[q++] = 101;
            outputbuffer[q++] = 48;
            return new String(outputbuffer, 0, 5);
        }
        outputbuffer[q++] = 101;
        int ae = e = this.exp * 4 - shf - 1;
        if (e < 0) {
            ae = -e;
        }
        for (p = 1000000000; p > ae; p /= 10) {
        }
        if (e < 0) {
            outputbuffer[q++] = 45;
        }
        while (p > 0) {
            outputbuffer[q++] = (char)(ae / p + 48);
            ae %= p;
            p /= 10;
        }
        return new String(outputbuffer, 0, q);
    }

    protected String dfp2string() {
        char[] buffer = new char[this.mant.length * 4 + 20];
        int p = 1;
        int e = this.exp;
        boolean pointInserted = false;
        buffer[0] = 32;
        if (e <= 0) {
            buffer[p++] = 48;
            buffer[p++] = 46;
            pointInserted = true;
        }
        while (e < 0) {
            buffer[p++] = 48;
            buffer[p++] = 48;
            buffer[p++] = 48;
            buffer[p++] = 48;
            ++e;
        }
        for (int i = this.mant.length - 1; i >= 0; --i) {
            buffer[p++] = (char)(this.mant[i] / 1000 + 48);
            buffer[p++] = (char)(this.mant[i] / 100 % 10 + 48);
            buffer[p++] = (char)(this.mant[i] / 10 % 10 + 48);
            buffer[p++] = (char)(this.mant[i] % 10 + 48);
            if (--e != 0) continue;
            buffer[p++] = 46;
            pointInserted = true;
        }
        while (e > 0) {
            buffer[p++] = 48;
            buffer[p++] = 48;
            buffer[p++] = 48;
            buffer[p++] = 48;
            --e;
        }
        if (!pointInserted) {
            buffer[p++] = 46;
        }
        int q = 1;
        while (buffer[q] == '0') {
            ++q;
        }
        if (buffer[q] == '.') {
            --q;
        }
        while (buffer[p - 1] == '0') {
            --p;
        }
        if (this.sign < 0) {
            buffer[--q] = 45;
        }
        return new String(buffer, q, p - q);
    }

    public Dfp dotrap(int type, String what, Dfp oper, Dfp result) {
        Dfp def = result;
        switch (type) {
            case 1: {
                def = this.newInstance(this.getZero());
                def.sign = result.sign;
                def.nans = (byte)3;
                break;
            }
            case 2: {
                if (this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                    def = this.newInstance(this.getZero());
                    def.sign = (byte)(this.sign * oper.sign);
                    def.nans = 1;
                }
                if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
                    def = this.newInstance(this.getZero());
                    def.nans = (byte)3;
                }
                if (this.nans == 1 || this.nans == 3) {
                    def = this.newInstance(this.getZero());
                    def.nans = (byte)3;
                }
                if (this.nans != 1 && this.nans != 2) break;
                def = this.newInstance(this.getZero());
                def.nans = (byte)3;
                break;
            }
            case 8: {
                if (result.exp + this.mant.length < -32767) {
                    def = this.newInstance(this.getZero());
                    def.sign = result.sign;
                } else {
                    def = this.newInstance(result);
                }
                result.exp += 32760;
                break;
            }
            case 4: {
                result.exp -= 32760;
                def = this.newInstance(this.getZero());
                def.sign = result.sign;
                def.nans = 1;
                break;
            }
            default: {
                def = result;
            }
        }
        return this.trap(type, what, oper, def, result);
    }

    protected Dfp trap(int type, String what, Dfp oper, Dfp def, Dfp result) {
        return def;
    }

    public int classify() {
        return this.nans;
    }

    public static Dfp copysign(Dfp x, Dfp y) {
        Dfp result = x.newInstance(x);
        result.sign = y.sign;
        return result;
    }

    public Dfp nextAfter(Dfp x) {
        Dfp result;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result2 = this.newInstance(this.getZero());
            result2.nans = (byte)3;
            return this.dotrap(1, NEXT_AFTER_TRAP, x, result2);
        }
        boolean up = false;
        if (this.lessThan(x)) {
            up = true;
        }
        if (Dfp.compare(this, x) == 0) {
            return this.newInstance(x);
        }
        if (this.lessThan(this.getZero())) {
            boolean bl = up = !up;
        }
        if (up) {
            Dfp inc = this.newInstance(this.getOne());
            inc.exp = this.exp - this.mant.length + 1;
            inc.sign = this.sign;
            if (this.equals(this.getZero())) {
                inc.exp = -32767 - this.mant.length;
            }
            result = this.add(inc);
        } else {
            Dfp inc = this.newInstance(this.getOne());
            inc.exp = this.exp;
            inc.sign = this.sign;
            inc.exp = this.equals(inc) ? this.exp - this.mant.length : this.exp - this.mant.length + 1;
            if (this.equals(this.getZero())) {
                inc.exp = -32767 - this.mant.length;
            }
            result = this.subtract(inc);
        }
        if (result.classify() == 1 && this.classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            result = this.dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        if (result.equals(this.getZero()) && !this.equals(this.getZero())) {
            this.field.setIEEEFlagsBits(16);
            result = this.dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        return result;
    }

    public double toDouble() {
        int exponent;
        if (this.isInfinite()) {
            if (this.lessThan(this.getZero())) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (this.isNaN()) {
            return Double.NaN;
        }
        Dfp y = this;
        boolean negate = false;
        int cmp0 = Dfp.compare(this, this.getZero());
        if (cmp0 == 0) {
            return this.sign < 0 ? -0.0 : 0.0;
        }
        if (cmp0 < 0) {
            y = this.negate();
            negate = true;
        }
        if ((exponent = (int)((double)y.intLog10() * 3.32)) < 0) {
            --exponent;
        }
        Dfp tempDfp = DfpMath.pow(this.getTwo(), exponent);
        while (tempDfp.lessThan(y) || tempDfp.equals(y)) {
            tempDfp = tempDfp.multiply(2);
            ++exponent;
        }
        y = y.divide(DfpMath.pow(this.getTwo(), --exponent));
        if (exponent > -1023) {
            y = y.subtract(this.getOne());
        }
        if (exponent < -1074) {
            return 0.0;
        }
        if (exponent > 1023) {
            return negate ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        y = y.multiply(this.newInstance(0x10000000000000L)).rint();
        String str = y.toString();
        long mantissa = Long.parseLong(str = str.substring(0, str.length() - 1));
        if (mantissa == 0x10000000000000L) {
            mantissa = 0L;
            ++exponent;
        }
        if (exponent <= -1023) {
            --exponent;
        }
        while (exponent < -1023) {
            ++exponent;
            mantissa >>>= 1;
        }
        long bits = mantissa | (long)exponent + 1023L << 52;
        double x = Double.longBitsToDouble(bits);
        if (negate) {
            x = -x;
        }
        return x;
    }

    public double[] toSplitDouble() {
        double[] split = new double[2];
        long mask = -1073741824L;
        split[0] = Double.longBitsToDouble(Double.doubleToLongBits(this.toDouble()) & mask);
        split[1] = this.subtract(this.newInstance(split[0])).toDouble();
        return split;
    }

    @Override
    public double getReal() {
        return this.toDouble();
    }

    @Override
    public Dfp add(double a) {
        return this.add(this.newInstance(a));
    }

    @Override
    public Dfp subtract(double a) {
        return this.subtract(this.newInstance(a));
    }

    @Override
    public Dfp multiply(double a) {
        return this.multiply(this.newInstance(a));
    }

    @Override
    public Dfp divide(double a) {
        return this.divide(this.newInstance(a));
    }

    @Override
    public Dfp remainder(double a) {
        return this.remainder(this.newInstance(a));
    }

    @Override
    public long round() {
        return FastMath.round(this.toDouble());
    }

    @Override
    public Dfp signum() {
        if (this.isNaN() || this.isZero()) {
            return this;
        }
        return this.newInstance(this.sign > 0 ? 1 : -1);
    }

    @Override
    public Dfp copySign(Dfp s) {
        if (this.sign >= 0 && s.sign >= 0 || this.sign < 0 && s.sign < 0) {
            return this;
        }
        return this.negate();
    }

    @Override
    public Dfp copySign(double s) {
        long sb = Double.doubleToLongBits(s);
        if (this.sign >= 0 && sb >= 0L || this.sign < 0 && sb < 0L) {
            return this;
        }
        return this.negate();
    }

    @Override
    public Dfp scalb(int n) {
        return this.multiply(DfpMath.pow(this.getTwo(), n));
    }

    @Override
    public Dfp hypot(Dfp y) {
        return this.multiply(this).add(y.multiply(y)).sqrt();
    }

    @Override
    public Dfp cbrt() {
        return this.rootN(3);
    }

    @Override
    public Dfp rootN(int n) {
        return this.sign >= 0 ? DfpMath.pow(this, this.getOne().divide(n)) : DfpMath.pow(this.negate(), this.getOne().divide(n)).negate();
    }

    @Override
    public Dfp pow(double p) {
        return DfpMath.pow(this, this.newInstance(p));
    }

    @Override
    public Dfp pow(int n) {
        return DfpMath.pow(this, n);
    }

    @Override
    public Dfp pow(Dfp e) {
        return DfpMath.pow(this, e);
    }

    @Override
    public Dfp exp() {
        return DfpMath.exp(this);
    }

    @Override
    public Dfp expm1() {
        return DfpMath.exp(this).subtract(this.getOne());
    }

    @Override
    public Dfp log() {
        return DfpMath.log(this);
    }

    @Override
    public Dfp log1p() {
        return DfpMath.log(this.add(this.getOne()));
    }

    @Deprecated
    public int log10() {
        return this.intLog10();
    }

    @Override
    public Dfp cos() {
        return DfpMath.cos(this);
    }

    @Override
    public Dfp sin() {
        return DfpMath.sin(this);
    }

    @Override
    public Dfp tan() {
        return DfpMath.tan(this);
    }

    @Override
    public Dfp acos() {
        return DfpMath.acos(this);
    }

    @Override
    public Dfp asin() {
        return DfpMath.asin(this);
    }

    @Override
    public Dfp atan() {
        return DfpMath.atan(this);
    }

    @Override
    public Dfp atan2(Dfp x) throws DimensionMismatchException {
        Dfp r = x.multiply(x).add(this.multiply(this)).sqrt();
        if (x.sign >= 0) {
            return this.getTwo().multiply(this.divide(r.add(x)).atan());
        }
        Dfp tmp = this.getTwo().multiply(this.divide(r.subtract(x)).atan());
        Dfp pmPi = this.newInstance(tmp.sign <= 0 ? -Math.PI : Math.PI);
        return pmPi.subtract(tmp);
    }

    @Override
    public Dfp cosh() {
        return DfpMath.exp(this).add(DfpMath.exp(this.negate())).divide(2);
    }

    @Override
    public Dfp sinh() {
        return DfpMath.exp(this).subtract(DfpMath.exp(this.negate())).divide(2);
    }

    @Override
    public Dfp tanh() {
        Dfp ePlus = DfpMath.exp(this);
        Dfp eMinus = DfpMath.exp(this.negate());
        return ePlus.subtract(eMinus).divide(ePlus.add(eMinus));
    }

    @Override
    public Dfp acosh() {
        return this.multiply(this).subtract(this.getOne()).sqrt().add(this).log();
    }

    @Override
    public Dfp asinh() {
        return this.multiply(this).add(this.getOne()).sqrt().add(this).log();
    }

    @Override
    public Dfp atanh() {
        return this.getOne().add(this).divide(this.getOne().subtract(this)).log().divide(2);
    }

    public Dfp linearCombination(Dfp[] a, Dfp[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = this.getZero();
        for (int i = 0; i < a.length; ++i) {
            r = r.add(a[i].multiply(b[i]));
        }
        return r;
    }

    public Dfp linearCombination(double[] a, Dfp[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = this.getZero();
        for (int i = 0; i < a.length; ++i) {
            r = r.add(b[i].multiply(a[i]));
        }
        return r;
    }

    @Override
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2) {
        return a1.multiply(b1).add(a2.multiply(b2));
    }

    @Override
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2) {
        return b1.multiply(a1).add(b2.multiply(a2));
    }

    @Override
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2, Dfp a3, Dfp b3) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
    }

    @Override
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2, double a3, Dfp b3) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
    }

    @Override
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2, Dfp a3, Dfp b3, Dfp a4, Dfp b4) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
    }

    @Override
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2, double a3, Dfp b3, double a4, Dfp b4) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
    }
}

