/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldHermiteInterpolator<T extends FieldElement<T>> {
    private final List<T> abscissae = new ArrayList<T>();
    private final List<T[]> topDiagonal = new ArrayList<T[]>();
    private final List<T[]> bottomDiagonal = new ArrayList<T[]>();

    public void addSamplePoint(T x, T[] ... value) throws ZeroException, MathArithmeticException, DimensionMismatchException, NullArgumentException {
        MathUtils.checkNotNull(x);
        FieldElement factorial = (FieldElement)x.getField().getOne();
        for (int i = 0; i < value.length; ++i) {
            FieldElement[] y = (FieldElement[])value[i].clone();
            if (i > 1) {
                factorial = (FieldElement)factorial.multiply(i);
                FieldElement inv = (FieldElement)factorial.reciprocal();
                for (int j = 0; j < y.length; ++j) {
                    y[j] = y[j].multiply(inv);
                }
            }
            int n = this.abscissae.size();
            this.bottomDiagonal.add(n - i, y);
            FieldElement[] bottom0 = y;
            for (int j = i; j < n; ++j) {
                FieldElement[] bottom1 = (FieldElement[])this.bottomDiagonal.get(n - (j + 1));
                if (x.equals(this.abscissae.get(n - (j + 1)))) {
                    throw new ZeroException((Localizable)LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, x);
                }
                FieldElement inv = (FieldElement)((FieldElement)x.subtract(this.abscissae.get(n - (j + 1)))).reciprocal();
                for (int k = 0; k < y.length; ++k) {
                    bottom1[k] = inv.multiply(bottom0[k].subtract(bottom1[k]));
                }
                bottom0 = bottom1;
            }
            this.topDiagonal.add((T[])bottom0.clone());
            this.abscissae.add(x);
        }
    }

    public T[] value(T x) throws NoDataException, NullArgumentException {
        MathUtils.checkNotNull(x);
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        FieldElement[] value = (FieldElement[])MathArrays.buildArray(x.getField(), ((FieldElement[])this.topDiagonal.get(0)).length);
        FieldElement valueCoeff = (FieldElement)x.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); ++i) {
            FieldElement[] dividedDifference = (FieldElement[])this.topDiagonal.get(i);
            for (int k = 0; k < value.length; ++k) {
                value[k] = value[k].add(dividedDifference[k].multiply(valueCoeff));
            }
            FieldElement deltaX = (FieldElement)x.subtract(this.abscissae.get(i));
            valueCoeff = valueCoeff.multiply(deltaX);
        }
        return value;
    }

    public T[][] derivatives(T x, int order) throws NoDataException, NullArgumentException {
        MathUtils.checkNotNull(x);
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        FieldElement zero = (FieldElement)x.getField().getZero();
        FieldElement one = (FieldElement)x.getField().getOne();
        FieldElement[] tj = (FieldElement[])MathArrays.buildArray(x.getField(), order + 1);
        tj[0] = zero;
        for (int i = 0; i < order; ++i) {
            tj[i + 1] = tj[i].add(one);
        }
        FieldElement[][] derivatives = (FieldElement[][])MathArrays.buildArray(x.getField(), order + 1, ((FieldElement[])this.topDiagonal.get(0)).length);
        FieldElement[] valueCoeff = (FieldElement[])MathArrays.buildArray(x.getField(), order + 1);
        valueCoeff[0] = (FieldElement)x.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); ++i) {
            FieldElement[] dividedDifference = (FieldElement[])this.topDiagonal.get(i);
            FieldElement deltaX = (FieldElement)x.subtract(this.abscissae.get(i));
            for (int j = order; j >= 0; --j) {
                for (int k = 0; k < derivatives[j].length; ++k) {
                    derivatives[j][k] = derivatives[j][k].add(dividedDifference[k].multiply(valueCoeff[j]));
                }
                valueCoeff[j] = valueCoeff[j].multiply(deltaX);
                if (j <= 0) continue;
                valueCoeff[j] = valueCoeff[j].add(tj[j].multiply(valueCoeff[j - 1]));
            }
        }
        return derivatives;
    }
}

