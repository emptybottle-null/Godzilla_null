/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.special;

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class Erf {
    private static final double X_CRIT = 0.4769362762044697;

    private Erf() {
    }

    public static double erf(double x) {
        if (FastMath.abs(x) > 40.0) {
            return x > 0.0 ? 1.0 : -1.0;
        }
        double ret = Gamma.regularizedGammaP(0.5, x * x, 1.0E-15, 10000);
        return x < 0.0 ? -ret : ret;
    }

    public static double erfc(double x) {
        if (FastMath.abs(x) > 40.0) {
            return x > 0.0 ? 0.0 : 2.0;
        }
        double ret = Gamma.regularizedGammaQ(0.5, x * x, 1.0E-15, 10000);
        return x < 0.0 ? 2.0 - ret : ret;
    }

    public static double erf(double x1, double x2) {
        if (x1 > x2) {
            return -Erf.erf(x2, x1);
        }
        return x1 < -0.4769362762044697 ? (x2 < 0.0 ? Erf.erfc(-x2) - Erf.erfc(-x1) : Erf.erf(x2) - Erf.erf(x1)) : (x2 > 0.4769362762044697 && x1 > 0.0 ? Erf.erfc(x1) - Erf.erfc(x2) : Erf.erf(x2) - Erf.erf(x1));
    }

    public static double erfInv(double x) {
        double p;
        double w = -FastMath.log((1.0 - x) * (1.0 + x));
        if (w < 6.25) {
            p = -3.64441206401782E-21;
            p = -1.6850591381820166E-19 + p * (w -= 3.125);
            p = 1.28584807152564E-18 + p * w;
            p = 1.1157877678025181E-17 + p * w;
            p = -1.333171662854621E-16 + p * w;
            p = 2.0972767875968562E-17 + p * w;
            p = 6.637638134358324E-15 + p * w;
            p = -4.054566272975207E-14 + p * w;
            p = -8.151934197605472E-14 + p * w;
            p = 2.6335093153082323E-12 + p * w;
            p = -1.2975133253453532E-11 + p * w;
            p = -5.415412054294628E-11 + p * w;
            p = 1.0512122733215323E-9 + p * w;
            p = -4.112633980346984E-9 + p * w;
            p = -2.9070369957882005E-8 + p * w;
            p = 4.2347877827932404E-7 + p * w;
            p = -1.3654692000834679E-6 + p * w;
            p = -1.3882523362786469E-5 + p * w;
            p = 1.8673420803405714E-4 + p * w;
            p = -7.40702534166267E-4 + p * w;
            p = -0.006033670871430149 + p * w;
            p = 0.24015818242558962 + p * w;
            p = 1.6536545626831027 + p * w;
        } else if (w < 16.0) {
            w = FastMath.sqrt(w) - 3.25;
            p = 2.2137376921775787E-9;
            p = 9.075656193888539E-8 + p * w;
            p = -2.7517406297064545E-7 + p * w;
            p = 1.8239629214389228E-8 + p * w;
            p = 1.5027403968909828E-6 + p * w;
            p = -4.013867526981546E-6 + p * w;
            p = 2.9234449089955446E-6 + p * w;
            p = 1.2475304481671779E-5 + p * w;
            p = -4.7318229009055734E-5 + p * w;
            p = 6.828485145957318E-5 + p * w;
            p = 2.4031110387097894E-5 + p * w;
            p = -3.550375203628475E-4 + p * w;
            p = 9.532893797373805E-4 + p * w;
            p = -0.0016882755560235047 + p * w;
            p = 0.002491442096107851 + p * w;
            p = -0.003751208507569241 + p * w;
            p = 0.005370914553590064 + p * w;
            p = 1.0052589676941592 + p * w;
            p = 3.0838856104922208 + p * w;
        } else if (!Double.isInfinite(w)) {
            w = FastMath.sqrt(w) - 5.0;
            p = -2.7109920616438573E-11;
            p = -2.555641816996525E-10 + p * w;
            p = 1.5076572693500548E-9 + p * w;
            p = -3.789465440126737E-9 + p * w;
            p = 7.61570120807834E-9 + p * w;
            p = -1.496002662714924E-8 + p * w;
            p = 2.914795345090108E-8 + p * w;
            p = -6.771199775845234E-8 + p * w;
            p = 2.2900482228026655E-7 + p * w;
            p = -9.9298272942317E-7 + p * w;
            p = 4.526062597223154E-6 + p * w;
            p = -1.968177810553167E-5 + p * w;
            p = 7.599527703001776E-5 + p * w;
            p = -2.1503011930044477E-4 + p * w;
            p = -1.3871931833623122E-4 + p * w;
            p = 1.0103004648645344 + p * w;
            p = 4.849906401408584 + p * w;
        } else {
            p = Double.POSITIVE_INFINITY;
        }
        return p * x;
    }

    public static double erfcInv(double x) {
        return Erf.erfInv(1.0 - x);
    }
}

