/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

public final class FinanceLib {
    private FinanceLib() {
    }

    public static double fv(double r, double n, double y, double p, boolean t) {
        double retval = 0.0;
        if (r == 0.0) {
            retval = -1.0 * (p + n * y);
        } else {
            double r1 = r + 1.0;
            retval = (1.0 - Math.pow(r1, n)) * (t ? r1 : 1.0) * y / r - p * Math.pow(r1, n);
        }
        return retval;
    }

    public static double pv(double r, double n, double y, double f, boolean t) {
        double retval = 0.0;
        if (r == 0.0) {
            retval = -1.0 * (n * y + f);
        } else {
            double r1 = r + 1.0;
            retval = ((1.0 - Math.pow(r1, n)) / r * (t ? r1 : 1.0) * y - f) / Math.pow(r1, n);
        }
        return retval;
    }

    public static double npv(double r, double[] cfs) {
        double r1;
        double npv = 0.0;
        double trate = r1 = r + 1.0;
        int iSize = cfs.length;
        for (int i = 0; i < iSize; ++i) {
            npv += cfs[i] / trate;
            trate *= r1;
        }
        return npv;
    }

    public static double pmt(double r, double n, double p, double f, boolean t) {
        double retval = 0.0;
        if (r == 0.0) {
            retval = -1.0 * (f + p) / n;
        } else {
            double r1 = r + 1.0;
            retval = (f + p * Math.pow(r1, n)) * r / ((t ? r1 : 1.0) * (1.0 - Math.pow(r1, n)));
        }
        return retval;
    }

    public static double nper(double r, double y, double p, double f, boolean t) {
        double retval = 0.0;
        if (r == 0.0) {
            retval = -1.0 * (f + p) / y;
        } else {
            double r1 = r + 1.0;
            double ryr = (t ? r1 : 1.0) * y / r;
            double a1 = ryr - f < 0.0 ? Math.log(f - ryr) : Math.log(ryr - f);
            double a2 = ryr - f < 0.0 ? Math.log(-p - ryr) : Math.log(p + ryr);
            double a3 = Math.log(r1);
            retval = (a1 - a2) / a3;
        }
        return retval;
    }
}

