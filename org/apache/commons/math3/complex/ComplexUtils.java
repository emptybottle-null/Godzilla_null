/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.complex;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class ComplexUtils {
    private ComplexUtils() {
    }

    public static Complex polar2Complex(double r, double theta) throws MathIllegalArgumentException {
        if (r < 0.0) {
            throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_COMPLEX_MODULE, r);
        }
        return new Complex(r * FastMath.cos(theta), r * FastMath.sin(theta));
    }

    public static Complex[] convertToComplex(double[] real) {
        Complex[] c = new Complex[real.length];
        for (int i = 0; i < real.length; ++i) {
            c[i] = new Complex(real[i], 0.0);
        }
        return c;
    }
}

