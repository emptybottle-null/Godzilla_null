/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.dfp;

import org.apache.commons.math3.dfp.Dfp;
import org.apache.commons.math3.dfp.DfpField;

public class DfpDec
extends Dfp {
    protected DfpDec(DfpField factory) {
        super(factory);
    }

    protected DfpDec(DfpField factory, byte x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, int x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, long x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, double x) {
        super(factory, x);
        this.round(0);
    }

    public DfpDec(Dfp d) {
        super(d);
        this.round(0);
    }

    protected DfpDec(DfpField factory, String s) {
        super(factory, s);
        this.round(0);
    }

    protected DfpDec(DfpField factory, byte sign, byte nans) {
        super(factory, sign, nans);
    }

    public Dfp newInstance() {
        return new DfpDec(this.getField());
    }

    public Dfp newInstance(byte x) {
        return new DfpDec(this.getField(), x);
    }

    public Dfp newInstance(int x) {
        return new DfpDec(this.getField(), x);
    }

    public Dfp newInstance(long x) {
        return new DfpDec(this.getField(), x);
    }

    public Dfp newInstance(double x) {
        return new DfpDec(this.getField(), x);
    }

    public Dfp newInstance(Dfp d) {
        if (this.getField().getRadixDigits() != d.getField().getRadixDigits()) {
            this.getField().setIEEEFlagsBits(1);
            Dfp result = this.newInstance(this.getZero());
            result.nans = (byte)3;
            return this.dotrap(1, "newInstance", d, result);
        }
        return new DfpDec(d);
    }

    public Dfp newInstance(String s) {
        return new DfpDec(this.getField(), s);
    }

    public Dfp newInstance(byte sign, byte nans) {
        return new DfpDec(this.getField(), sign, nans);
    }

    protected int getDecimalDigits() {
        return this.getRadixDigits() * 4 - 3;
    }

    protected int round(int in) {
        boolean inc;
        int n;
        int msb = this.mant[this.mant.length - 1];
        if (msb == 0) {
            return 0;
        }
        int cmaxdigits = this.mant.length * 4;
        int lsbthreshold = 1000;
        while (lsbthreshold > msb) {
            lsbthreshold /= 10;
            --cmaxdigits;
        }
        int digits = this.getDecimalDigits();
        int lsbshift = cmaxdigits - digits;
        int lsd = lsbshift / 4;
        lsbthreshold = 1;
        for (int i = 0; i < lsbshift % 4; ++i) {
            lsbthreshold *= 10;
        }
        int lsb = this.mant[lsd];
        if (lsbthreshold <= 1 && digits == 4 * this.mant.length - 3) {
            return super.round(in);
        }
        int discarded = in;
        if (lsbthreshold == 1) {
            n = this.mant[lsd - 1] / 1000 % 10;
            int n2 = lsd - 1;
            this.mant[n2] = this.mant[n2] % 1000;
            discarded |= this.mant[lsd - 1];
        } else {
            n = lsb * 10 / lsbthreshold % 10;
            discarded |= lsb % (lsbthreshold / 10);
        }
        for (int i = 0; i < lsd; ++i) {
            discarded |= this.mant[i];
            this.mant[i] = 0;
        }
        this.mant[lsd] = lsb / lsbthreshold * lsbthreshold;
        switch (this.getField().getRoundingMode()) {
            case ROUND_DOWN: {
                inc = false;
                break;
            }
            case ROUND_UP: {
                inc = n != 0 || discarded != 0;
                break;
            }
            case ROUND_HALF_UP: {
                inc = n >= 5;
                break;
            }
            case ROUND_HALF_DOWN: {
                inc = n > 5;
                break;
            }
            case ROUND_HALF_EVEN: {
                inc = n > 5 || n == 5 && discarded != 0 || n == 5 && discarded == 0 && (lsb / lsbthreshold & 1) == 1;
                break;
            }
            case ROUND_HALF_ODD: {
                inc = n > 5 || n == 5 && discarded != 0 || n == 5 && discarded == 0 && (lsb / lsbthreshold & 1) == 0;
                break;
            }
            case ROUND_CEIL: {
                inc = this.sign == 1 && (n != 0 || discarded != 0);
                break;
            }
            default: {
                boolean bl = inc = this.sign == -1 && (n != 0 || discarded != 0);
            }
        }
        if (inc) {
            int rh = lsbthreshold;
            for (int i = lsd; i < this.mant.length; ++i) {
                int r = this.mant[i] + rh;
                rh = r / 10000;
                this.mant[i] = r % 10000;
            }
            if (rh != 0) {
                this.shiftRight();
                this.mant[this.mant.length - 1] = rh;
            }
        }
        if (this.exp < -32767) {
            this.getField().setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            this.getField().setIEEEFlagsBits(4);
            return 4;
        }
        if (n != 0 || discarded != 0) {
            this.getField().setIEEEFlagsBits(16);
            return 16;
        }
        return 0;
    }

    public Dfp nextAfter(Dfp x) {
        Dfp result;
        String trapName = "nextAfter";
        if (this.getField().getRadixDigits() != x.getField().getRadixDigits()) {
            this.getField().setIEEEFlagsBits(1);
            Dfp result2 = this.newInstance(this.getZero());
            result2.nans = (byte)3;
            return this.dotrap(1, "nextAfter", x, result2);
        }
        boolean up = false;
        if (this.lessThan(x)) {
            up = true;
        }
        if (this.equals(x)) {
            return this.newInstance(x);
        }
        if (this.lessThan(this.getZero())) {
            boolean bl = up = !up;
        }
        if (up) {
            Dfp inc = this.power10(this.intLog10() - this.getDecimalDigits() + 1);
            inc = DfpDec.copysign(inc, this);
            if (this.equals(this.getZero())) {
                inc = this.power10K(-32767 - this.mant.length - 1);
            }
            result = inc.equals(this.getZero()) ? DfpDec.copysign(this.newInstance(this.getZero()), this) : this.add(inc);
        } else {
            Dfp inc = this.power10(this.intLog10());
            inc = this.equals(inc = DfpDec.copysign(inc, this)) ? inc.divide(this.power10(this.getDecimalDigits())) : inc.divide(this.power10(this.getDecimalDigits() - 1));
            if (this.equals(this.getZero())) {
                inc = this.power10K(-32767 - this.mant.length - 1);
            }
            result = inc.equals(this.getZero()) ? DfpDec.copysign(this.newInstance(this.getZero()), this) : this.subtract(inc);
        }
        if (result.classify() == 1 && this.classify() != 1) {
            this.getField().setIEEEFlagsBits(16);
            result = this.dotrap(16, "nextAfter", x, result);
        }
        if (result.equals(this.getZero()) && !this.equals(this.getZero())) {
            this.getField().setIEEEFlagsBits(16);
            result = this.dotrap(16, "nextAfter", x, result);
        }
        return result;
    }
}

