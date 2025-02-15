/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.PrintStream;
import org.apache.commons.math3.exception.DimensionMismatchException;

class FastMathCalc {
    private static final long HEX_40000000 = 0x40000000L;
    private static final double[] FACT = new double[]{1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 3.99168E7, 4.790016E8, 6.2270208E9, 8.71782912E10, 1.307674368E12, 2.0922789888E13, 3.55687428096E14, 6.402373705728E15, 1.21645100408832E17};
    private static final double[][] LN_SPLIT_COEF = new double[][]{{2.0, 0.0}, {0.6666666269302368, 3.9736429850260626E-8}, {0.3999999761581421, 2.3841857910019882E-8}, {0.2857142686843872, 1.7029898543501842E-8}, {0.2222222089767456, 1.3245471311735498E-8}, {0.1818181574344635, 2.4384203044354907E-8}, {0.1538461446762085, 9.140260083262505E-9}, {0.13333332538604736, 9.220590270857665E-9}, {0.11764700710773468, 1.2393345855018391E-8}, {0.10526403784751892, 8.251545029714408E-9}, {0.0952233225107193, 1.2675934823758863E-8}, {0.08713622391223907, 1.1430250008909141E-8}, {0.07842259109020233, 2.404307984052299E-9}, {0.08371849358081818, 1.176342548272881E-8}, {0.03058958f, 1.2958646899018938E-9}, {0.14982303977012634, 1.225743062930824E-8}};
    private static final String TABLE_START_DECL = "    {";
    private static final String TABLE_END_DECL = "    };";

    private FastMathCalc() {
    }

    private static void buildSinCosTables(double[] SINE_TABLE_A, double[] SINE_TABLE_B, double[] COSINE_TABLE_A, double[] COSINE_TABLE_B, int SINE_TABLE_LEN, double[] TANGENT_TABLE_A, double[] TANGENT_TABLE_B) {
        double[] as;
        double[] ys;
        int i;
        double[] result = new double[2];
        for (i = 0; i < 7; ++i) {
            double x = (double)i / 8.0;
            FastMathCalc.slowSin(x, result);
            SINE_TABLE_A[i] = result[0];
            SINE_TABLE_B[i] = result[1];
            FastMathCalc.slowCos(x, result);
            COSINE_TABLE_A[i] = result[0];
            COSINE_TABLE_B[i] = result[1];
        }
        for (i = 7; i < SINE_TABLE_LEN; ++i) {
            double[] xs = new double[2];
            ys = new double[2];
            as = new double[2];
            double[] bs = new double[2];
            double[] temps = new double[2];
            if ((i & 1) == 0) {
                xs[0] = SINE_TABLE_A[i / 2];
                xs[1] = SINE_TABLE_B[i / 2];
                ys[0] = COSINE_TABLE_A[i / 2];
                ys[1] = COSINE_TABLE_B[i / 2];
                FastMathCalc.splitMult(xs, ys, result);
                SINE_TABLE_A[i] = result[0] * 2.0;
                SINE_TABLE_B[i] = result[1] * 2.0;
                FastMathCalc.splitMult(ys, ys, as);
                FastMathCalc.splitMult(xs, xs, temps);
                temps[0] = -temps[0];
                temps[1] = -temps[1];
                FastMathCalc.splitAdd(as, temps, result);
                COSINE_TABLE_A[i] = result[0];
                COSINE_TABLE_B[i] = result[1];
                continue;
            }
            xs[0] = SINE_TABLE_A[i / 2];
            xs[1] = SINE_TABLE_B[i / 2];
            ys[0] = COSINE_TABLE_A[i / 2];
            ys[1] = COSINE_TABLE_B[i / 2];
            as[0] = SINE_TABLE_A[i / 2 + 1];
            as[1] = SINE_TABLE_B[i / 2 + 1];
            bs[0] = COSINE_TABLE_A[i / 2 + 1];
            bs[1] = COSINE_TABLE_B[i / 2 + 1];
            FastMathCalc.splitMult(xs, bs, temps);
            FastMathCalc.splitMult(ys, as, result);
            FastMathCalc.splitAdd(result, temps, result);
            SINE_TABLE_A[i] = result[0];
            SINE_TABLE_B[i] = result[1];
            FastMathCalc.splitMult(ys, bs, result);
            FastMathCalc.splitMult(xs, as, temps);
            temps[0] = -temps[0];
            temps[1] = -temps[1];
            FastMathCalc.splitAdd(result, temps, result);
            COSINE_TABLE_A[i] = result[0];
            COSINE_TABLE_B[i] = result[1];
        }
        for (i = 0; i < SINE_TABLE_LEN; ++i) {
            double[] xs = new double[2];
            ys = new double[2];
            as = new double[]{COSINE_TABLE_A[i], COSINE_TABLE_B[i]};
            FastMathCalc.splitReciprocal(as, ys);
            xs[0] = SINE_TABLE_A[i];
            xs[1] = SINE_TABLE_B[i];
            FastMathCalc.splitMult(xs, ys, as);
            TANGENT_TABLE_A[i] = as[0];
            TANGENT_TABLE_B[i] = as[1];
        }
    }

    static double slowCos(double x, double[] result) {
        double[] xs = new double[2];
        double[] ys = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        FastMathCalc.split(x, xs);
        ys[1] = 0.0;
        ys[0] = 0.0;
        for (int i = FACT.length - 1; i >= 0; --i) {
            FastMathCalc.splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if ((i & 1) != 0) continue;
            FastMathCalc.split(FACT[i], as);
            FastMathCalc.splitReciprocal(as, facts);
            if ((i & 2) != 0) {
                facts[0] = -facts[0];
                facts[1] = -facts[1];
            }
            FastMathCalc.splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    static double slowSin(double x, double[] result) {
        double[] xs = new double[2];
        double[] ys = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        FastMathCalc.split(x, xs);
        ys[1] = 0.0;
        ys[0] = 0.0;
        for (int i = FACT.length - 1; i >= 0; --i) {
            FastMathCalc.splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if ((i & 1) == 0) continue;
            FastMathCalc.split(FACT[i], as);
            FastMathCalc.splitReciprocal(as, facts);
            if ((i & 2) != 0) {
                facts[0] = -facts[0];
                facts[1] = -facts[1];
            }
            FastMathCalc.splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    static double slowexp(double x, double[] result) {
        double[] xs = new double[2];
        double[] ys = new double[2];
        double[] facts = new double[2];
        double[] as = new double[2];
        FastMathCalc.split(x, xs);
        ys[1] = 0.0;
        ys[0] = 0.0;
        for (int i = FACT.length - 1; i >= 0; --i) {
            FastMathCalc.splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            FastMathCalc.split(FACT[i], as);
            FastMathCalc.splitReciprocal(as, facts);
            FastMathCalc.splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return ys[0] + ys[1];
    }

    private static void split(double d, double[] split) {
        if (d < 8.0E298 && d > -8.0E298) {
            double a = d * 1.073741824E9;
            split[0] = d + a - a;
            split[1] = d - split[0];
        } else {
            double a = d * 9.313225746154785E-10;
            split[0] = (d + a - d) * 1.073741824E9;
            split[1] = d - split[0];
        }
    }

    private static void resplit(double[] a) {
        double c = a[0] + a[1];
        double d = -(c - a[0] - a[1]);
        if (c < 8.0E298 && c > -8.0E298) {
            double z = c * 1.073741824E9;
            a[0] = c + z - z;
            a[1] = c - a[0] + d;
        } else {
            double z = c * 9.313225746154785E-10;
            a[0] = (c + z - c) * 1.073741824E9;
            a[1] = c - a[0] + d;
        }
    }

    private static void splitMult(double[] a, double[] b, double[] ans) {
        ans[0] = a[0] * b[0];
        ans[1] = a[0] * b[1] + a[1] * b[0] + a[1] * b[1];
        FastMathCalc.resplit(ans);
    }

    private static void splitAdd(double[] a, double[] b, double[] ans) {
        ans[0] = a[0] + b[0];
        ans[1] = a[1] + b[1];
        FastMathCalc.resplit(ans);
    }

    static void splitReciprocal(double[] in, double[] result) {
        double b = 2.384185791015625E-7;
        double a = 0.9999997615814209;
        if (in[0] == 0.0) {
            in[0] = in[1];
            in[1] = 0.0;
        }
        result[0] = 0.9999997615814209 / in[0];
        result[1] = (2.384185791015625E-7 * in[0] - 0.9999997615814209 * in[1]) / (in[0] * in[0] + in[0] * in[1]);
        if (result[1] != result[1]) {
            result[1] = 0.0;
        }
        FastMathCalc.resplit(result);
        for (int i = 0; i < 2; ++i) {
            double err = 1.0 - result[0] * in[0] - result[0] * in[1] - result[1] * in[0] - result[1] * in[1];
            result[1] = result[1] + (err *= result[0] + result[1]);
        }
    }

    private static void quadMult(double[] a, double[] b, double[] result) {
        double[] xs = new double[2];
        double[] ys = new double[2];
        double[] zs = new double[2];
        FastMathCalc.split(a[0], xs);
        FastMathCalc.split(b[0], ys);
        FastMathCalc.splitMult(xs, ys, zs);
        result[0] = zs[0];
        result[1] = zs[1];
        FastMathCalc.split(b[1], ys);
        FastMathCalc.splitMult(xs, ys, zs);
        double tmp = result[0] + zs[0];
        result[1] = result[1] - (tmp - result[0] - zs[0]);
        result[0] = tmp;
        tmp = result[0] + zs[1];
        result[1] = result[1] - (tmp - result[0] - zs[1]);
        result[0] = tmp;
        FastMathCalc.split(a[1], xs);
        FastMathCalc.split(b[0], ys);
        FastMathCalc.splitMult(xs, ys, zs);
        tmp = result[0] + zs[0];
        result[1] = result[1] - (tmp - result[0] - zs[0]);
        result[0] = tmp;
        tmp = result[0] + zs[1];
        result[1] = result[1] - (tmp - result[0] - zs[1]);
        result[0] = tmp;
        FastMathCalc.split(a[1], xs);
        FastMathCalc.split(b[1], ys);
        FastMathCalc.splitMult(xs, ys, zs);
        tmp = result[0] + zs[0];
        result[1] = result[1] - (tmp - result[0] - zs[0]);
        result[0] = tmp;
        tmp = result[0] + zs[1];
        result[1] = result[1] - (tmp - result[0] - zs[1]);
        result[0] = tmp;
    }

    static double expint(int p, double[] result) {
        double[] xs = new double[2];
        double[] as = new double[2];
        double[] ys = new double[2];
        xs[0] = Math.E;
        xs[1] = 1.4456468917292502E-16;
        FastMathCalc.split(1.0, ys);
        while (p > 0) {
            if ((p & 1) != 0) {
                FastMathCalc.quadMult(ys, xs, as);
                ys[0] = as[0];
                ys[1] = as[1];
            }
            FastMathCalc.quadMult(xs, xs, as);
            xs[0] = as[0];
            xs[1] = as[1];
            p >>= 1;
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
            FastMathCalc.resplit(result);
        }
        return ys[0] + ys[1];
    }

    static double[] slowLog(double xi) {
        double[] x = new double[2];
        double[] x2 = new double[2];
        double[] y = new double[2];
        double[] a = new double[2];
        FastMathCalc.split(xi, x);
        x[0] = x[0] + 1.0;
        FastMathCalc.resplit(x);
        FastMathCalc.splitReciprocal(x, a);
        x[0] = x[0] - 2.0;
        FastMathCalc.resplit(x);
        FastMathCalc.splitMult(x, a, y);
        x[0] = y[0];
        x[1] = y[1];
        FastMathCalc.splitMult(x, x, x2);
        y[0] = LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][0];
        y[1] = LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][1];
        for (int i = LN_SPLIT_COEF.length - 2; i >= 0; --i) {
            FastMathCalc.splitMult(y, x2, a);
            y[0] = a[0];
            y[1] = a[1];
            FastMathCalc.splitAdd(y, LN_SPLIT_COEF[i], a);
            y[0] = a[0];
            y[1] = a[1];
        }
        FastMathCalc.splitMult(y, x, a);
        y[0] = a[0];
        y[1] = a[1];
        return y;
    }

    static void printarray(PrintStream out, String name, int expectedLen, double[][] array2d) {
        out.println(name);
        FastMathCalc.checkLen(expectedLen, array2d.length);
        out.println("    { ");
        int i = 0;
        for (double[] array : array2d) {
            out.print("        {");
            for (double d : array) {
                out.printf("%-25.25s", FastMathCalc.format(d));
            }
            out.println("}, // " + i++);
        }
        out.println(TABLE_END_DECL);
    }

    static void printarray(PrintStream out, String name, int expectedLen, double[] array) {
        out.println(name + "=");
        FastMathCalc.checkLen(expectedLen, array.length);
        out.println(TABLE_START_DECL);
        for (double d : array) {
            out.printf("        %s%n", FastMathCalc.format(d));
        }
        out.println(TABLE_END_DECL);
    }

    static String format(double d) {
        if (d != d) {
            return "Double.NaN,";
        }
        return (d >= 0.0 ? "+" : "") + Double.toString(d) + "d,";
    }

    private static void checkLen(int expectedLen, int actual) throws DimensionMismatchException {
        if (expectedLen != actual) {
            throw new DimensionMismatchException(actual, expectedLen);
        }
    }
}

