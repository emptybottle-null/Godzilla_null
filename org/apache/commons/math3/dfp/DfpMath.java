/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.dfp;

import org.apache.commons.math3.dfp.Dfp;
import org.apache.commons.math3.dfp.DfpField;

public class DfpMath {
    private static final String POW_TRAP = "pow";

    private DfpMath() {
    }

    protected static Dfp[] split(DfpField field, String a) {
        int i;
        Dfp[] result = new Dfp[2];
        boolean leading = true;
        int sp = 0;
        int sig = 0;
        char[] buf = new char[a.length()];
        for (i = 0; i < buf.length; ++i) {
            buf[i] = a.charAt(i);
            if (buf[i] >= '1' && buf[i] <= '9') {
                leading = false;
            }
            if (buf[i] == '.') {
                sig += (400 - sig) % 4;
                leading = false;
            }
            if (sig == field.getRadixDigits() / 2 * 4) {
                sp = i;
                break;
            }
            if (buf[i] < '0' || buf[i] > '9' || leading) continue;
            ++sig;
        }
        result[0] = field.newDfp(new String(buf, 0, sp));
        for (i = 0; i < buf.length; ++i) {
            buf[i] = a.charAt(i);
            if (buf[i] < '0' || buf[i] > '9' || i >= sp) continue;
            buf[i] = 48;
        }
        result[1] = field.newDfp(new String(buf));
        return result;
    }

    protected static Dfp[] split(Dfp a) {
        Dfp[] result = new Dfp[2];
        Dfp shift = a.multiply(a.power10K(a.getRadixDigits() / 2));
        result[0] = a.add(shift).subtract(shift);
        result[1] = a.subtract(result[0]);
        return result;
    }

    protected static Dfp[] splitMult(Dfp[] a, Dfp[] b) {
        Dfp[] result = new Dfp[2];
        result[1] = a[0].getZero();
        result[0] = a[0].multiply(b[0]);
        if (result[0].classify() == 1 || result[0].equals(result[1])) {
            return result;
        }
        result[1] = a[0].multiply(b[1]).add(a[1].multiply(b[0])).add(a[1].multiply(b[1]));
        return result;
    }

    protected static Dfp[] splitDiv(Dfp[] a, Dfp[] b) {
        Dfp[] result = new Dfp[]{a[0].divide(b[0]), a[1].multiply(b[0]).subtract(a[0].multiply(b[1]))};
        result[1] = result[1].divide(b[0].multiply(b[0]).add(b[0].multiply(b[1])));
        return result;
    }

    protected static Dfp splitPow(Dfp[] base, int a) {
        int trial;
        boolean invert = false;
        Dfp[] r = new Dfp[2];
        Dfp[] result = new Dfp[]{base[0].getOne(), base[0].getZero()};
        if (a == 0) {
            return result[0].add(result[1]);
        }
        if (a < 0) {
            invert = true;
            a = -a;
        }
        do {
            int prevtrial;
            r[0] = new Dfp(base[0]);
            r[1] = new Dfp(base[1]);
            trial = 1;
            while (true) {
                prevtrial = trial;
                if ((trial *= 2) > a) break;
                r = DfpMath.splitMult(r, r);
            }
            trial = prevtrial;
            result = DfpMath.splitMult(result, r);
        } while ((a -= trial) >= 1);
        result[0] = result[0].add(result[1]);
        if (invert) {
            result[0] = base[0].getOne().divide(result[0]);
        }
        return result[0];
    }

    public static Dfp pow(Dfp base, int a) {
        int trial;
        boolean invert = false;
        Dfp result = base.getOne();
        if (a == 0) {
            return result;
        }
        if (a < 0) {
            invert = true;
            a = -a;
        }
        do {
            int prevtrial;
            Dfp prevr;
            Dfp r = new Dfp(base);
            trial = 1;
            do {
                prevr = new Dfp(r);
                prevtrial = trial;
                r = r.multiply(r);
            } while (a > (trial *= 2));
            r = prevr;
            trial = prevtrial;
            result = result.multiply(r);
        } while ((a -= trial) >= 1);
        if (invert) {
            result = base.getOne().divide(result);
        }
        return base.newInstance(result);
    }

    public static Dfp exp(Dfp a) {
        Dfp inta = a.rint();
        Dfp fraca = a.subtract(inta);
        int ia = inta.intValue();
        if (ia > 0x7FFFFFFE) {
            return a.newInstance((byte)1, (byte)1);
        }
        if (ia < -2147483646) {
            return a.newInstance();
        }
        Dfp einta = DfpMath.splitPow(a.getField().getESplit(), ia);
        Dfp efraca = DfpMath.expInternal(fraca);
        return einta.multiply(efraca);
    }

    protected static Dfp expInternal(Dfp a) {
        Dfp y = a.getOne();
        Dfp x = a.getOne();
        Dfp fact = a.getOne();
        Dfp py = new Dfp(y);
        for (int i = 1; i < 90 && !(y = y.add((x = x.multiply(a)).multiply(fact = fact.divide(i)))).equals(py); ++i) {
            py = new Dfp(y);
        }
        return y;
    }

    public static Dfp log(Dfp a) {
        int p2 = 0;
        if (a.equals(a.getZero()) || a.lessThan(a.getZero()) || a.isNaN()) {
            a.getField().setIEEEFlagsBits(1);
            return a.dotrap(1, "ln", a, a.newInstance((byte)1, (byte)3));
        }
        if (a.classify() == 1) {
            return a;
        }
        Dfp x = new Dfp(a);
        int lr = x.log10K();
        x = x.divide(DfpMath.pow(a.newInstance(10000), lr));
        int ix = x.floor().intValue();
        while (ix > 2) {
            ix >>= 1;
            ++p2;
        }
        Dfp[] spx = DfpMath.split(x);
        Dfp[] spy = new Dfp[2];
        spy[0] = DfpMath.pow(a.getTwo(), p2);
        spx[0] = spx[0].divide(spy[0]);
        spx[1] = spx[1].divide(spy[0]);
        spy[0] = a.newInstance("1.33333");
        while (spx[0].add(spx[1]).greaterThan(spy[0])) {
            spx[0] = spx[0].divide(2);
            spx[1] = spx[1].divide(2);
            ++p2;
        }
        Dfp[] spz = DfpMath.logInternal(spx);
        spx[0] = a.newInstance("" + (p2 + 4 * lr));
        spx[1] = a.getZero();
        spy = DfpMath.splitMult(a.getField().getLn2Split(), spx);
        spz[0] = spz[0].add(spy[0]);
        spz[1] = spz[1].add(spy[1]);
        spx[0] = a.newInstance("" + 4 * lr);
        spx[1] = a.getZero();
        spy = DfpMath.splitMult(a.getField().getLn5Split(), spx);
        spz[0] = spz[0].add(spy[0]);
        spz[1] = spz[1].add(spy[1]);
        return a.newInstance(spz[0].add(spz[1]));
    }

    protected static Dfp[] logInternal(Dfp[] a) {
        Dfp t = a[0].divide(4).add(a[1].divide(4));
        Dfp x = t.add(a[0].newInstance("-0.25")).divide(t.add(a[0].newInstance("0.25")));
        Dfp y = new Dfp(x);
        Dfp num = new Dfp(x);
        Dfp py = new Dfp(y);
        int den = 1;
        for (int i = 0; i < 10000; ++i) {
            num = num.multiply(x);
            t = (num = num.multiply(x)).divide(den += 2);
            if ((y = y.add(t)).equals(py)) break;
            py = new Dfp(y);
        }
        y = y.multiply(a[0].getTwo());
        return DfpMath.split(y);
    }

    public static Dfp pow(Dfp x, Dfp y) {
        Dfp r;
        if (x.getField().getRadixDigits() != y.getField().getRadixDigits()) {
            x.getField().setIEEEFlagsBits(1);
            Dfp result = x.newInstance(x.getZero());
            result.nans = (byte)3;
            return x.dotrap(1, POW_TRAP, x, result);
        }
        Dfp zero = x.getZero();
        Dfp one = x.getOne();
        Dfp two = x.getTwo();
        boolean invert = false;
        if (y.equals(zero)) {
            return x.newInstance(one);
        }
        if (y.equals(one)) {
            if (x.isNaN()) {
                x.getField().setIEEEFlagsBits(1);
                return x.dotrap(1, POW_TRAP, x, x);
            }
            return x;
        }
        if (x.isNaN() || y.isNaN()) {
            x.getField().setIEEEFlagsBits(1);
            return x.dotrap(1, POW_TRAP, x, x.newInstance((byte)1, (byte)3));
        }
        if (x.equals(zero)) {
            if (Dfp.copysign(one, x).greaterThan(zero)) {
                if (y.greaterThan(zero)) {
                    return x.newInstance(zero);
                }
                return x.newInstance(x.newInstance((byte)1, (byte)1));
            }
            if (y.classify() == 0 && y.rint().equals(y) && !y.remainder(two).equals(zero)) {
                if (y.greaterThan(zero)) {
                    return x.newInstance(zero.negate());
                }
                return x.newInstance(x.newInstance((byte)-1, (byte)1));
            }
            if (y.greaterThan(zero)) {
                return x.newInstance(zero);
            }
            return x.newInstance(x.newInstance((byte)1, (byte)1));
        }
        if (x.lessThan(zero)) {
            x = x.negate();
            invert = true;
        }
        if (x.greaterThan(one) && y.classify() == 1) {
            if (y.greaterThan(zero)) {
                return y;
            }
            return x.newInstance(zero);
        }
        if (x.lessThan(one) && y.classify() == 1) {
            if (y.greaterThan(zero)) {
                return x.newInstance(zero);
            }
            return x.newInstance(Dfp.copysign(y, one));
        }
        if (x.equals(one) && y.classify() == 1) {
            x.getField().setIEEEFlagsBits(1);
            return x.dotrap(1, POW_TRAP, x, x.newInstance((byte)1, (byte)3));
        }
        if (x.classify() == 1) {
            if (invert) {
                if (y.classify() == 0 && y.rint().equals(y) && !y.remainder(two).equals(zero)) {
                    if (y.greaterThan(zero)) {
                        return x.newInstance(x.newInstance((byte)-1, (byte)1));
                    }
                    return x.newInstance(zero.negate());
                }
                if (y.greaterThan(zero)) {
                    return x.newInstance(x.newInstance((byte)1, (byte)1));
                }
                return x.newInstance(zero);
            }
            if (y.greaterThan(zero)) {
                return x;
            }
            return x.newInstance(zero);
        }
        if (invert && !y.rint().equals(y)) {
            x.getField().setIEEEFlagsBits(1);
            return x.dotrap(1, POW_TRAP, x, x.newInstance((byte)1, (byte)3));
        }
        if (y.lessThan(x.newInstance(100000000)) && y.greaterThan(x.newInstance(-100000000))) {
            Dfp u = y.rint();
            int ui = u.intValue();
            Dfp v = y.subtract(u);
            if (v.unequal(zero)) {
                Dfp a = v.multiply(DfpMath.log(x));
                Dfp b = a.divide(x.getField().getLn2()).rint();
                Dfp c = a.subtract(b.multiply(x.getField().getLn2()));
                r = DfpMath.splitPow(DfpMath.split(x), ui);
                r = r.multiply(DfpMath.pow(two, b.intValue()));
                r = r.multiply(DfpMath.exp(c));
            } else {
                r = DfpMath.splitPow(DfpMath.split(x), ui);
            }
        } else {
            r = DfpMath.exp(DfpMath.log(x).multiply(y));
        }
        if (invert && y.rint().equals(y) && !y.remainder(two).equals(zero)) {
            r = r.negate();
        }
        return x.newInstance(r);
    }

    protected static Dfp sinInternal(Dfp[] a) {
        Dfp c;
        Dfp y = c = a[0].add(a[1]);
        c = c.multiply(c);
        Dfp x = y;
        Dfp fact = a[0].getOne();
        Dfp py = new Dfp(y);
        for (int i = 3; i < 90; i += 2) {
            x = x.multiply(c);
            if ((y = y.add((x = x.negate()).multiply(fact = fact.divide((i - 1) * i)))).equals(py)) break;
            py = new Dfp(y);
        }
        return y;
    }

    protected static Dfp cosInternal(Dfp[] a) {
        Dfp one;
        Dfp x = one = a[0].getOne();
        Dfp y = one;
        Dfp c = a[0].add(a[1]);
        c = c.multiply(c);
        Dfp fact = one;
        Dfp py = new Dfp(y);
        for (int i = 2; i < 90; i += 2) {
            x = x.multiply(c);
            if ((y = y.add((x = x.negate()).multiply(fact = fact.divide((i - 1) * i)))).equals(py)) break;
            py = new Dfp(y);
        }
        return y;
    }

    public static Dfp sin(Dfp a) {
        Dfp y;
        Dfp pi = a.getField().getPi();
        Dfp zero = a.getField().getZero();
        boolean neg = false;
        Dfp x = a.remainder(pi.multiply(2));
        if (x.lessThan(zero)) {
            x = x.negate();
            neg = true;
        }
        if (x.greaterThan(pi.divide(2))) {
            x = pi.subtract(x);
        }
        if (x.lessThan(pi.divide(4))) {
            y = DfpMath.sinInternal(DfpMath.split(x));
        } else {
            Dfp[] c = new Dfp[2];
            Dfp[] piSplit = a.getField().getPiSplit();
            c[0] = piSplit[0].divide(2).subtract(x);
            c[1] = piSplit[1].divide(2);
            y = DfpMath.cosInternal(c);
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    public static Dfp cos(Dfp a) {
        Dfp y;
        Dfp pi = a.getField().getPi();
        Dfp zero = a.getField().getZero();
        boolean neg = false;
        Dfp x = a.remainder(pi.multiply(2));
        if (x.lessThan(zero)) {
            x = x.negate();
        }
        if (x.greaterThan(pi.divide(2))) {
            x = pi.subtract(x);
            neg = true;
        }
        if (x.lessThan(pi.divide(4))) {
            Dfp[] c = new Dfp[]{x, zero};
            y = DfpMath.cosInternal(c);
        } else {
            Dfp[] c = new Dfp[2];
            Dfp[] piSplit = a.getField().getPiSplit();
            c[0] = piSplit[0].divide(2).subtract(x);
            c[1] = piSplit[1].divide(2);
            y = DfpMath.sinInternal(c);
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    public static Dfp tan(Dfp a) {
        return DfpMath.sin(a).divide(DfpMath.cos(a));
    }

    protected static Dfp atanInternal(Dfp a) {
        Dfp y = new Dfp(a);
        Dfp x = new Dfp(y);
        Dfp py = new Dfp(y);
        for (int i = 3; i < 90; i += 2) {
            x = x.multiply(a);
            x = x.multiply(a);
            if ((y = y.add((x = x.negate()).divide(i))).equals(py)) break;
            py = new Dfp(y);
        }
        return y;
    }

    public static Dfp atan(Dfp a) {
        Dfp zero = a.getField().getZero();
        Dfp one = a.getField().getOne();
        Dfp[] sqr2Split = a.getField().getSqr2Split();
        Dfp[] piSplit = a.getField().getPiSplit();
        boolean recp = false;
        boolean neg = false;
        boolean sub = false;
        Dfp ty = sqr2Split[0].subtract(one).add(sqr2Split[1]);
        Dfp x = new Dfp(a);
        if (x.lessThan(zero)) {
            neg = true;
            x = x.negate();
        }
        if (x.greaterThan(one)) {
            recp = true;
            x = one.divide(x);
        }
        if (x.greaterThan(ty)) {
            Dfp[] sty = new Dfp[2];
            sub = true;
            sty[0] = sqr2Split[0].subtract(one);
            sty[1] = sqr2Split[1];
            Dfp[] xs = DfpMath.split(x);
            Dfp[] ds = DfpMath.splitMult(xs, sty);
            ds[0] = ds[0].add(one);
            xs[0] = xs[0].subtract(sty[0]);
            xs[1] = xs[1].subtract(sty[1]);
            xs = DfpMath.splitDiv(xs, ds);
            x = xs[0].add(xs[1]);
        }
        Dfp y = DfpMath.atanInternal(x);
        if (sub) {
            y = y.add(piSplit[0].divide(8)).add(piSplit[1].divide(8));
        }
        if (recp) {
            y = piSplit[0].divide(2).subtract(y).add(piSplit[1].divide(2));
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    public static Dfp asin(Dfp a) {
        return DfpMath.atan(a.divide(a.getOne().subtract(a.multiply(a)).sqrt()));
    }

    public static Dfp acos(Dfp a) {
        boolean negative = false;
        if (a.lessThan(a.getZero())) {
            negative = true;
        }
        a = Dfp.copysign(a, a.getOne());
        Dfp result = DfpMath.atan(a.getOne().subtract(a.multiply(a)).sqrt().divide(a));
        if (negative) {
            result = a.getField().getPi().subtract(result);
        }
        return a.newInstance(result);
    }
}

