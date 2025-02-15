/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.transform;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.TransformType;
import org.apache.commons.math3.transform.TransformUtils;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class FastFourierTransformer
implements Serializable {
    static final long serialVersionUID = 20120210L;
    private static final double[] W_SUB_N_R = new double[]{1.0, -1.0, 6.123233995736766E-17, 0.7071067811865476, 0.9238795325112867, 0.9807852804032304, 0.9951847266721969, 0.9987954562051724, 0.9996988186962042, 0.9999247018391445, 0.9999811752826011, 0.9999952938095762, 0.9999988234517019, 0.9999997058628822, 0.9999999264657179, 0.9999999816164293, 0.9999999954041073, 0.9999999988510269, 0.9999999997127567, 0.9999999999281892, 0.9999999999820472, 0.9999999999955118, 0.999999999998878, 0.9999999999997194, 0.9999999999999298, 0.9999999999999825, 0.9999999999999957, 0.9999999999999989, 0.9999999999999998, 0.9999999999999999, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
    private static final double[] W_SUB_N_I = new double[]{2.4492935982947064E-16, -1.2246467991473532E-16, -1.0, -0.7071067811865475, -0.3826834323650898, -0.19509032201612825, -0.0980171403295606, -0.049067674327418015, -0.024541228522912288, -0.012271538285719925, -0.006135884649154475, -0.003067956762965976, -0.0015339801862847655, -7.669903187427045E-4, -3.8349518757139556E-4, -1.917475973107033E-4, -9.587379909597734E-5, -4.793689960306688E-5, -2.396844980841822E-5, -1.1984224905069705E-5, -5.9921124526424275E-6, -2.996056226334661E-6, -1.4980281131690111E-6, -7.490140565847157E-7, -3.7450702829238413E-7, -1.8725351414619535E-7, -9.362675707309808E-8, -4.681337853654909E-8, -2.340668926827455E-8, -1.1703344634137277E-8, -5.8516723170686385E-9, -2.9258361585343192E-9, -1.4629180792671596E-9, -7.314590396335798E-10, -3.657295198167899E-10, -1.8286475990839495E-10, -9.143237995419748E-11, -4.571618997709874E-11, -2.285809498854937E-11, -1.1429047494274685E-11, -5.714523747137342E-12, -2.857261873568671E-12, -1.4286309367843356E-12, -7.143154683921678E-13, -3.571577341960839E-13, -1.7857886709804195E-13, -8.928943354902097E-14, -4.4644716774510487E-14, -2.2322358387255243E-14, -1.1161179193627622E-14, -5.580589596813811E-15, -2.7902947984069054E-15, -1.3951473992034527E-15, -6.975736996017264E-16, -3.487868498008632E-16, -1.743934249004316E-16, -8.71967124502158E-17, -4.35983562251079E-17, -2.179917811255395E-17, -1.0899589056276974E-17, -5.449794528138487E-18, -2.7248972640692436E-18, -1.3624486320346218E-18};
    private final DftNormalization normalization;

    public FastFourierTransformer(DftNormalization normalization) {
        this.normalization = normalization;
    }

    private static void bitReversalShuffle2(double[] a, double[] b) {
        int n = a.length;
        assert (b.length == n);
        int halfOfN = n >> 1;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            int k;
            if (i < j) {
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                temp = b[i];
                b[i] = b[j];
                b[j] = temp;
            }
            for (k = halfOfN; k <= j && k > 0; j -= k, k >>= 1) {
            }
            j += k;
        }
    }

    private static void normalizeTransformedData(double[][] dataRI, DftNormalization normalization, TransformType type) {
        double[] dataR = dataRI[0];
        double[] dataI = dataRI[1];
        int n = dataR.length;
        assert (dataI.length == n);
        switch (normalization) {
            case STANDARD: {
                if (type != TransformType.INVERSE) break;
                double scaleFactor = 1.0 / (double)n;
                int i = 0;
                while (i < n) {
                    int n2 = i;
                    dataR[n2] = dataR[n2] * scaleFactor;
                    int n3 = i++;
                    dataI[n3] = dataI[n3] * scaleFactor;
                }
                break;
            }
            case UNITARY: {
                double scaleFactor = 1.0 / FastMath.sqrt(n);
                int i = 0;
                while (i < n) {
                    int n4 = i;
                    dataR[n4] = dataR[n4] * scaleFactor;
                    int n5 = i++;
                    dataI[n5] = dataI[n5] * scaleFactor;
                }
                break;
            }
            default: {
                throw new MathIllegalStateException();
            }
        }
    }

    public static void transformInPlace(double[][] dataRI, DftNormalization normalization, TransformType type) {
        double srcI3;
        double srcR3;
        double srcI2;
        double srcR2;
        double srcI1;
        double srcR1;
        double srcI0;
        double srcR0;
        int i3;
        int i2;
        int i1;
        int i0;
        if (dataRI.length != 2) {
            throw new DimensionMismatchException(dataRI.length, 2);
        }
        double[] dataR = dataRI[0];
        double[] dataI = dataRI[1];
        if (dataR.length != dataI.length) {
            throw new DimensionMismatchException(dataI.length, dataR.length);
        }
        int n = dataR.length;
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, n);
        }
        if (n == 1) {
            return;
        }
        if (n == 2) {
            double srcR02 = dataR[0];
            double srcI02 = dataI[0];
            double srcR12 = dataR[1];
            double srcI12 = dataI[1];
            dataR[0] = srcR02 + srcR12;
            dataI[0] = srcI02 + srcI12;
            dataR[1] = srcR02 - srcR12;
            dataI[1] = srcI02 - srcI12;
            FastFourierTransformer.normalizeTransformedData(dataRI, normalization, type);
            return;
        }
        FastFourierTransformer.bitReversalShuffle2(dataR, dataI);
        if (type == TransformType.INVERSE) {
            for (i0 = 0; i0 < n; i0 += 4) {
                i1 = i0 + 1;
                i2 = i0 + 2;
                i3 = i0 + 3;
                srcR0 = dataR[i0];
                srcI0 = dataI[i0];
                srcR1 = dataR[i2];
                srcI1 = dataI[i2];
                srcR2 = dataR[i1];
                srcI2 = dataI[i1];
                srcR3 = dataR[i3];
                srcI3 = dataI[i3];
                dataR[i0] = srcR0 + srcR1 + srcR2 + srcR3;
                dataI[i0] = srcI0 + srcI1 + srcI2 + srcI3;
                dataR[i1] = srcR0 - srcR2 + (srcI3 - srcI1);
                dataI[i1] = srcI0 - srcI2 + (srcR1 - srcR3);
                dataR[i2] = srcR0 - srcR1 + srcR2 - srcR3;
                dataI[i2] = srcI0 - srcI1 + srcI2 - srcI3;
                dataR[i3] = srcR0 - srcR2 + (srcI1 - srcI3);
                dataI[i3] = srcI0 - srcI2 + (srcR3 - srcR1);
            }
        } else {
            for (i0 = 0; i0 < n; i0 += 4) {
                i1 = i0 + 1;
                i2 = i0 + 2;
                i3 = i0 + 3;
                srcR0 = dataR[i0];
                srcI0 = dataI[i0];
                srcR1 = dataR[i2];
                srcI1 = dataI[i2];
                srcR2 = dataR[i1];
                srcI2 = dataI[i1];
                srcR3 = dataR[i3];
                srcI3 = dataI[i3];
                dataR[i0] = srcR0 + srcR1 + srcR2 + srcR3;
                dataI[i0] = srcI0 + srcI1 + srcI2 + srcI3;
                dataR[i1] = srcR0 - srcR2 + (srcI1 - srcI3);
                dataI[i1] = srcI0 - srcI2 + (srcR3 - srcR1);
                dataR[i2] = srcR0 - srcR1 + srcR2 - srcR3;
                dataI[i2] = srcI0 - srcI1 + srcI2 - srcI3;
                dataR[i3] = srcR0 - srcR2 + (srcI3 - srcI1);
                dataI[i3] = srcI0 - srcI2 + (srcR1 - srcR3);
            }
        }
        int lastN0 = 4;
        int lastLogN0 = 2;
        while (lastN0 < n) {
            int n0 = lastN0 << 1;
            int logN0 = lastLogN0 + 1;
            double wSubN0R = W_SUB_N_R[logN0];
            double wSubN0I = W_SUB_N_I[logN0];
            if (type == TransformType.INVERSE) {
                wSubN0I = -wSubN0I;
            }
            for (int destEvenStartIndex = 0; destEvenStartIndex < n; destEvenStartIndex += n0) {
                int destOddStartIndex = destEvenStartIndex + lastN0;
                double wSubN0ToRR = 1.0;
                double wSubN0ToRI = 0.0;
                for (int r = 0; r < lastN0; ++r) {
                    double grR = dataR[destEvenStartIndex + r];
                    double grI = dataI[destEvenStartIndex + r];
                    double hrR = dataR[destOddStartIndex + r];
                    double hrI = dataI[destOddStartIndex + r];
                    dataR[destEvenStartIndex + r] = grR + wSubN0ToRR * hrR - wSubN0ToRI * hrI;
                    dataI[destEvenStartIndex + r] = grI + wSubN0ToRR * hrI + wSubN0ToRI * hrR;
                    dataR[destOddStartIndex + r] = grR - (wSubN0ToRR * hrR - wSubN0ToRI * hrI);
                    dataI[destOddStartIndex + r] = grI - (wSubN0ToRR * hrI + wSubN0ToRI * hrR);
                    double nextWsubN0ToRR = wSubN0ToRR * wSubN0R - wSubN0ToRI * wSubN0I;
                    double nextWsubN0ToRI = wSubN0ToRR * wSubN0I + wSubN0ToRI * wSubN0R;
                    wSubN0ToRR = nextWsubN0ToRR;
                    wSubN0ToRI = nextWsubN0ToRI;
                }
            }
            lastN0 = n0;
            lastLogN0 = logN0;
        }
        FastFourierTransformer.normalizeTransformedData(dataRI, normalization, type);
    }

    public Complex[] transform(double[] f, TransformType type) {
        double[][] dataRI = new double[][]{MathArrays.copyOf(f, f.length), new double[f.length]};
        FastFourierTransformer.transformInPlace(dataRI, this.normalization, type);
        return TransformUtils.createComplexArray(dataRI);
    }

    public Complex[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
        double[] data = FunctionUtils.sample(f, min, max, n);
        return this.transform(data, type);
    }

    public Complex[] transform(Complex[] f, TransformType type) {
        double[][] dataRI = TransformUtils.createRealImaginaryArray(f);
        FastFourierTransformer.transformInPlace(dataRI, this.normalization, type);
        return TransformUtils.createComplexArray(dataRI);
    }

    @Deprecated
    public Object mdfft(Object mdca, TransformType type) {
        MultiDimensionalComplexMatrix mdcm = (MultiDimensionalComplexMatrix)new MultiDimensionalComplexMatrix(mdca).clone();
        int[] dimensionSize = mdcm.getDimensionSizes();
        for (int i = 0; i < dimensionSize.length; ++i) {
            this.mdfft(mdcm, type, i, new int[0]);
        }
        return mdcm.getArray();
    }

    @Deprecated
    private void mdfft(MultiDimensionalComplexMatrix mdcm, TransformType type, int d, int[] subVector) {
        int[] dimensionSize = mdcm.getDimensionSizes();
        if (subVector.length == dimensionSize.length) {
            int i;
            Complex[] temp = new Complex[dimensionSize[d]];
            for (i = 0; i < dimensionSize[d]; ++i) {
                subVector[d] = i;
                temp[i] = mdcm.get(subVector);
            }
            temp = this.transform(temp, type);
            for (i = 0; i < dimensionSize[d]; ++i) {
                subVector[d] = i;
                mdcm.set(temp[i], subVector);
            }
        } else {
            int[] vector = new int[subVector.length + 1];
            System.arraycopy(subVector, 0, vector, 0, subVector.length);
            if (subVector.length == d) {
                vector[d] = 0;
                this.mdfft(mdcm, type, d, vector);
            } else {
                int i = 0;
                while (i < dimensionSize[subVector.length]) {
                    vector[subVector.length] = i++;
                    this.mdfft(mdcm, type, d, vector);
                }
            }
        }
    }

    @Deprecated
    private static class MultiDimensionalComplexMatrix
    implements Cloneable {
        protected int[] dimensionSize;
        protected Object multiDimensionalComplexArray;

        MultiDimensionalComplexMatrix(Object multiDimensionalComplexArray) {
            Object[] array;
            this.multiDimensionalComplexArray = multiDimensionalComplexArray;
            int numOfDimensions = 0;
            Object lastDimension = multiDimensionalComplexArray;
            while (lastDimension instanceof Object[]) {
                array = (Object[])lastDimension;
                ++numOfDimensions;
                lastDimension = array[0];
            }
            this.dimensionSize = new int[numOfDimensions];
            numOfDimensions = 0;
            lastDimension = multiDimensionalComplexArray;
            while (lastDimension instanceof Object[]) {
                array = (Object[])lastDimension;
                this.dimensionSize[numOfDimensions++] = array.length;
                lastDimension = array[0];
            }
        }

        public Complex get(int ... vector) throws DimensionMismatchException {
            if (vector == null) {
                if (this.dimensionSize.length > 0) {
                    throw new DimensionMismatchException(0, this.dimensionSize.length);
                }
                return null;
            }
            if (vector.length != this.dimensionSize.length) {
                throw new DimensionMismatchException(vector.length, this.dimensionSize.length);
            }
            Object lastDimension = this.multiDimensionalComplexArray;
            for (int i = 0; i < this.dimensionSize.length; ++i) {
                lastDimension = ((Object[])lastDimension)[vector[i]];
            }
            return (Complex)lastDimension;
        }

        public Complex set(Complex magnitude, int ... vector) throws DimensionMismatchException {
            if (vector == null) {
                if (this.dimensionSize.length > 0) {
                    throw new DimensionMismatchException(0, this.dimensionSize.length);
                }
                return null;
            }
            if (vector.length != this.dimensionSize.length) {
                throw new DimensionMismatchException(vector.length, this.dimensionSize.length);
            }
            Object[] lastDimension = (Object[])this.multiDimensionalComplexArray;
            for (int i = 0; i < this.dimensionSize.length - 1; ++i) {
                lastDimension = (Object[])lastDimension[vector[i]];
            }
            Complex lastValue = (Complex)lastDimension[vector[this.dimensionSize.length - 1]];
            lastDimension[vector[this.dimensionSize.length - 1]] = magnitude;
            return lastValue;
        }

        public int[] getDimensionSizes() {
            return (int[])this.dimensionSize.clone();
        }

        public Object getArray() {
            return this.multiDimensionalComplexArray;
        }

        public Object clone() {
            MultiDimensionalComplexMatrix mdcm = new MultiDimensionalComplexMatrix(Array.newInstance(Complex.class, this.dimensionSize));
            this.clone(mdcm);
            return mdcm;
        }

        private void clone(MultiDimensionalComplexMatrix mdcm) {
            int[][] vectorList;
            int[] vector = new int[this.dimensionSize.length];
            int size = 1;
            for (int i = 0; i < this.dimensionSize.length; ++i) {
                size *= this.dimensionSize[i];
            }
            block1: for (int[] nextVector : vectorList = new int[size][this.dimensionSize.length]) {
                System.arraycopy(vector, 0, nextVector, 0, this.dimensionSize.length);
                for (int i = 0; i < this.dimensionSize.length; ++i) {
                    int n = i;
                    vector[n] = vector[n] + 1;
                    if (vector[i] < this.dimensionSize[i]) continue block1;
                    vector[i] = 0;
                }
            }
            for (int[] nextVector : vectorList) {
                mdcm.set(this.get(nextVector), nextVector);
            }
        }
    }
}

