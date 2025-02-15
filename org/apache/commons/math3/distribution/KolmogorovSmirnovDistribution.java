/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class KolmogorovSmirnovDistribution
implements Serializable {
    private static final long serialVersionUID = -4670676796862967187L;
    private int n;

    public KolmogorovSmirnovDistribution(int n) throws NotStrictlyPositiveException {
        if (n <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, n);
        }
        this.n = n;
    }

    public double cdf(double d) throws MathArithmeticException {
        return this.cdf(d, false);
    }

    public double cdfExact(double d) throws MathArithmeticException {
        return this.cdf(d, true);
    }

    public double cdf(double d, boolean exact) throws MathArithmeticException {
        double ninv = 1.0 / (double)this.n;
        double ninvhalf = 0.5 * ninv;
        if (d <= ninvhalf) {
            return 0.0;
        }
        if (ninvhalf < d && d <= ninv) {
            double res = 1.0;
            double f = 2.0 * d - ninv;
            for (int i = 1; i <= this.n; ++i) {
                res *= (double)i * f;
            }
            return res;
        }
        if (1.0 - ninv <= d && d < 1.0) {
            return 1.0 - 2.0 * FastMath.pow(1.0 - d, this.n);
        }
        if (1.0 <= d) {
            return 1.0;
        }
        return exact ? this.exactK(d) : this.roundedK(d);
    }

    private double exactK(double d) throws MathArithmeticException {
        int k = (int)FastMath.ceil((double)this.n * d);
        FieldMatrix<BigFraction> H = this.createH(d);
        FieldMatrix<BigFraction> Hpower = H.power(this.n);
        BigFraction pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= this.n; ++i) {
            pFrac = pFrac.multiply(i).divide(this.n);
        }
        return pFrac.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d) throws MathArithmeticException {
        int k = (int)FastMath.ceil((double)this.n * d);
        FieldMatrix<BigFraction> HBigFraction = this.createH(d);
        int m = HBigFraction.getRowDimension();
        Array2DRowRealMatrix H = new Array2DRowRealMatrix(m, m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                H.setEntry(i, j, HBigFraction.getEntry(i, j).doubleValue());
            }
        }
        RealMatrix Hpower = H.power(this.n);
        double pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= this.n; ++i) {
            pFrac *= (double)i / (double)this.n;
        }
        return pFrac;
    }

    private FieldMatrix<BigFraction> createH(double d) throws NumberIsTooLargeException, FractionConversionException {
        int i;
        int k = (int)FastMath.ceil((double)this.n * d);
        int m = 2 * k - 1;
        double hDouble = (double)k - (double)this.n * d;
        if (hDouble >= 1.0) {
            throw new NumberIsTooLargeException(hDouble, (Number)1.0, false);
        }
        BigFraction h = null;
        try {
            h = new BigFraction(hDouble, 1.0E-20, 10000);
        } catch (FractionConversionException e1) {
            try {
                h = new BigFraction(hDouble, 1.0E-10, 10000);
            } catch (FractionConversionException e2) {
                h = new BigFraction(hDouble, 1.0E-5, 10000);
            }
        }
        FieldElement[][] Hdata = new BigFraction[m][m];
        for (int i2 = 0; i2 < m; ++i2) {
            for (int j = 0; j < m; ++j) {
                Hdata[i2][j] = i2 - j + 1 < 0 ? BigFraction.ZERO : BigFraction.ONE;
            }
        }
        BigFraction[] hPowers = new BigFraction[m];
        hPowers[0] = h;
        for (i = 1; i < m; ++i) {
            hPowers[i] = h.multiply(hPowers[i - 1]);
        }
        for (i = 0; i < m; ++i) {
            Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
            Hdata[m - 1][i] = ((BigFraction)Hdata[m - 1][i]).subtract(hPowers[m - i - 1]);
        }
        if (h.compareTo(BigFraction.ONE_HALF) == 1) {
            Hdata[m - 1][0] = ((BigFraction)Hdata[m - 1][0]).add(h.multiply(2).subtract(1).pow(m));
        }
        for (i = 0; i < m; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                if (i - j + 1 <= 0) continue;
                for (int g = 2; g <= i - j + 1; ++g) {
                    Hdata[i][j] = ((BigFraction)Hdata[i][j]).divide(g);
                }
            }
        }
        return new Array2DRowFieldMatrix((Field)BigFractionField.getInstance(), Hdata);
    }
}

