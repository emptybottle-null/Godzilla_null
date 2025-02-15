/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

public class Finance {
    public static double pmt(double r, int nper, double pv, double fv, int type) {
        return -r * (pv * Math.pow(1.0 + r, nper) + fv) / ((1.0 + r * (double)type) * (Math.pow(1.0 + r, nper) - 1.0));
    }

    public static double pmt(double r, int nper, double pv, double fv) {
        return Finance.pmt(r, nper, pv, fv, 0);
    }

    public static double pmt(double r, int nper, double pv) {
        return Finance.pmt(r, nper, pv, 0.0);
    }

    public static double ipmt(double r, int per, int nper, double pv, double fv, int type) {
        double ipmt = Finance.fv(r, per - 1, Finance.pmt(r, nper, pv, fv, type), pv, type) * r;
        if (type == 1) {
            ipmt /= 1.0 + r;
        }
        return ipmt;
    }

    public static double ipmt(double r, int per, int nper, double pv, double fv) {
        return Finance.ipmt(r, per, nper, pv, fv, 0);
    }

    public static double ipmt(double r, int per, int nper, double pv) {
        return Finance.ipmt(r, per, nper, pv, 0.0);
    }

    public static double ppmt(double r, int per, int nper, double pv, double fv, int type) {
        return Finance.pmt(r, nper, pv, fv, type) - Finance.ipmt(r, per, nper, pv, fv, type);
    }

    public static double ppmt(double r, int per, int nper, double pv, double fv) {
        return Finance.pmt(r, nper, pv, fv) - Finance.ipmt(r, per, nper, pv, fv);
    }

    public static double ppmt(double r, int per, int nper, double pv) {
        return Finance.pmt(r, nper, pv) - Finance.ipmt(r, per, nper, pv);
    }

    public static double fv(double r, int nper, double pmt, double pv, int type) {
        return -(pv * Math.pow(1.0 + r, nper) + pmt * (1.0 + r * (double)type) * (Math.pow(1.0 + r, nper) - 1.0) / r);
    }

    public static double fv(double r, int nper, double c, double pv) {
        return Finance.fv(r, nper, c, pv, 0);
    }
}

