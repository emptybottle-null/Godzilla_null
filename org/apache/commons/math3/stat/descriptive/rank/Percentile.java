/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.KthSelector;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;
import org.apache.commons.math3.util.Precision;

public class Percentile
extends AbstractUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = -8091216485095130416L;
    private static final int MAX_CACHED_LEVELS = 10;
    private static final int PIVOTS_HEAP_LENGTH = 512;
    private final KthSelector kthSelector;
    private final EstimationType estimationType;
    private final NaNStrategy nanStrategy;
    private double quantile;
    private int[] cachedPivots;

    public Percentile() {
        this(50.0);
    }

    public Percentile(double quantile) throws MathIllegalArgumentException {
        this(quantile, EstimationType.LEGACY, NaNStrategy.REMOVED, new KthSelector(new MedianOf3PivotingStrategy()));
    }

    public Percentile(Percentile original) throws NullArgumentException {
        MathUtils.checkNotNull(original);
        this.estimationType = original.getEstimationType();
        this.nanStrategy = original.getNaNStrategy();
        this.kthSelector = original.getKthSelector();
        this.setData(original.getDataRef());
        if (original.cachedPivots != null) {
            System.arraycopy(original.cachedPivots, 0, this.cachedPivots, 0, original.cachedPivots.length);
        }
        this.setQuantile(original.quantile);
    }

    protected Percentile(double quantile, EstimationType estimationType, NaNStrategy nanStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
        this.setQuantile(quantile);
        this.cachedPivots = null;
        MathUtils.checkNotNull((Object)estimationType);
        MathUtils.checkNotNull((Object)nanStrategy);
        MathUtils.checkNotNull(kthSelector);
        this.estimationType = estimationType;
        this.nanStrategy = nanStrategy;
        this.kthSelector = kthSelector;
    }

    public void setData(double[] values) {
        if (values == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[512];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(values);
    }

    public void setData(double[] values, int begin, int length) throws MathIllegalArgumentException {
        if (values == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[512];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(values, begin, length);
    }

    public double evaluate(double p) throws MathIllegalArgumentException {
        return this.evaluate(this.getDataRef(), p);
    }

    public double evaluate(double[] values, double p) throws MathIllegalArgumentException {
        this.test(values, 0, 0);
        return this.evaluate(values, 0, values.length, p);
    }

    public double evaluate(double[] values, int start, int length) throws MathIllegalArgumentException {
        return this.evaluate(values, start, length, this.quantile);
    }

    public double evaluate(double[] values, int begin, int length, double p) throws MathIllegalArgumentException {
        this.test(values, begin, length);
        if (p > 100.0 || p <= 0.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, (Number)p, 0, 100);
        }
        if (length == 0) {
            return Double.NaN;
        }
        if (length == 1) {
            return values[begin];
        }
        double[] work = this.getWorkArray(values, begin, length);
        int[] pivotsHeap = this.getPivots(values);
        return work.length == 0 ? Double.NaN : this.estimationType.evaluate(work, pivotsHeap, p, this.kthSelector);
    }

    @Deprecated
    int medianOf3(double[] work, int begin, int end) {
        return new MedianOf3PivotingStrategy().pivotIndex(work, begin, end);
    }

    public double getQuantile() {
        return this.quantile;
    }

    public void setQuantile(double p) throws MathIllegalArgumentException {
        if (p <= 0.0 || p > 100.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, (Number)p, 0, 100);
        }
        this.quantile = p;
    }

    public Percentile copy() {
        return new Percentile(this);
    }

    @Deprecated
    public static void copy(Percentile source, Percentile dest) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    protected double[] getWorkArray(double[] values, int begin, int length) {
        double[] work;
        if (values == this.getDataRef()) {
            work = this.getDataRef();
        } else {
            switch (this.nanStrategy) {
                case MAXIMAL: {
                    work = Percentile.replaceAndSlice(values, begin, length, Double.NaN, Double.POSITIVE_INFINITY);
                    break;
                }
                case MINIMAL: {
                    work = Percentile.replaceAndSlice(values, begin, length, Double.NaN, Double.NEGATIVE_INFINITY);
                    break;
                }
                case REMOVED: {
                    work = Percentile.removeAndSlice(values, begin, length, Double.NaN);
                    break;
                }
                case FAILED: {
                    work = Percentile.copyOf(values, begin, length);
                    MathArrays.checkNotNaN(work);
                    break;
                }
                default: {
                    work = Percentile.copyOf(values, begin, length);
                }
            }
        }
        return work;
    }

    private static double[] copyOf(double[] values, int begin, int length) {
        MathArrays.verifyValues(values, begin, length);
        return MathArrays.copyOfRange(values, begin, begin + length);
    }

    private static double[] replaceAndSlice(double[] values, int begin, int length, double original, double replacement) {
        double[] temp = Percentile.copyOf(values, begin, length);
        for (int i = 0; i < length; ++i) {
            temp[i] = Precision.equalsIncludingNaN(original, temp[i]) ? replacement : temp[i];
        }
        return temp;
    }

    private static double[] removeAndSlice(double[] values, int begin, int length, double removedValue) {
        double[] temp;
        MathArrays.verifyValues(values, begin, length);
        BitSet bits = new BitSet(length);
        for (int i = begin; i < begin + length; ++i) {
            if (!Precision.equalsIncludingNaN(removedValue, values[i])) continue;
            bits.set(i - begin);
        }
        if (bits.isEmpty()) {
            temp = Percentile.copyOf(values, begin, length);
        } else if (bits.cardinality() == length) {
            temp = new double[]{};
        } else {
            temp = new double[length - bits.cardinality()];
            int start = begin;
            int dest = 0;
            int nextOne = -1;
            int bitSetPtr = 0;
            while ((nextOne = bits.nextSetBit(bitSetPtr)) != -1) {
                int lengthToCopy = nextOne - bitSetPtr;
                System.arraycopy(values, start, temp, dest, lengthToCopy);
                dest += lengthToCopy;
                bitSetPtr = bits.nextClearBit(nextOne);
                start = begin + bitSetPtr;
            }
            if (start < begin + length) {
                System.arraycopy(values, start, temp, dest, begin + length - start);
            }
        }
        return temp;
    }

    private int[] getPivots(double[] values) {
        int[] pivotsHeap;
        if (values == this.getDataRef()) {
            pivotsHeap = this.cachedPivots;
        } else {
            pivotsHeap = new int[512];
            Arrays.fill(pivotsHeap, -1);
        }
        return pivotsHeap;
    }

    public EstimationType getEstimationType() {
        return this.estimationType;
    }

    public Percentile withEstimationType(EstimationType newEstimationType) {
        return new Percentile(this.quantile, newEstimationType, this.nanStrategy, this.kthSelector);
    }

    public NaNStrategy getNaNStrategy() {
        return this.nanStrategy;
    }

    public Percentile withNaNStrategy(NaNStrategy newNaNStrategy) {
        return new Percentile(this.quantile, this.estimationType, newNaNStrategy, this.kthSelector);
    }

    public KthSelector getKthSelector() {
        return this.kthSelector;
    }

    public PivotingStrategyInterface getPivotingStrategy() {
        return this.kthSelector.getPivotingStrategy();
    }

    public Percentile withKthSelector(KthSelector newKthSelector) {
        return new Percentile(this.quantile, this.estimationType, this.nanStrategy, newKthSelector);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum EstimationType {
        LEGACY("Legacy Apache Commons Math"){

            protected double index(double p, int length) {
                double minLimit = 0.0;
                double maxLimit = 1.0;
                return Double.compare(p, 0.0) == 0 ? 0.0 : (Double.compare(p, 1.0) == 0 ? (double)length : p * (double)(length + 1));
            }
        }
        ,
        R_1("R-1"){

            protected double index(double p, int length) {
                double minLimit = 0.0;
                return Double.compare(p, 0.0) == 0 ? 0.0 : (double)length * p + 0.5;
            }

            protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
                return super.estimate(values, pivotsHeap, FastMath.ceil(pos - 0.5), length, selector);
            }
        }
        ,
        R_2("R-2"){

            protected double index(double p, int length) {
                double minLimit = 0.0;
                double maxLimit = 1.0;
                return Double.compare(p, 1.0) == 0 ? (double)length : (Double.compare(p, 0.0) == 0 ? 0.0 : (double)length * p + 0.5);
            }

            protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
                double low = super.estimate(values, pivotsHeap, FastMath.ceil(pos - 0.5), length, selector);
                double high = super.estimate(values, pivotsHeap, FastMath.floor(pos + 0.5), length, selector);
                return (low + high) / 2.0;
            }
        }
        ,
        R_3("R-3"){

            protected double index(double p, int length) {
                double minLimit = 0.5 / (double)length;
                return Double.compare(p, minLimit) <= 0 ? 0.0 : FastMath.rint((double)length * p);
            }
        }
        ,
        R_4("R-4"){

            protected double index(double p, int length) {
                double minLimit = 1.0 / (double)length;
                double maxLimit = 1.0;
                return Double.compare(p, minLimit) < 0 ? 0.0 : (Double.compare(p, 1.0) == 0 ? (double)length : (double)length * p);
            }
        }
        ,
        R_5("R-5"){

            protected double index(double p, int length) {
                double minLimit = 0.5 / (double)length;
                double maxLimit = ((double)length - 0.5) / (double)length;
                return Double.compare(p, minLimit) < 0 ? 0.0 : (Double.compare(p, maxLimit) >= 0 ? (double)length : (double)length * p + 0.5);
            }
        }
        ,
        R_6("R-6"){

            protected double index(double p, int length) {
                double minLimit = 1.0 / (double)(length + 1);
                double maxLimit = 1.0 * (double)length / (double)(length + 1);
                return Double.compare(p, minLimit) < 0 ? 0.0 : (Double.compare(p, maxLimit) >= 0 ? (double)length : (double)(length + 1) * p);
            }
        }
        ,
        R_7("R-7"){

            protected double index(double p, int length) {
                double minLimit = 0.0;
                double maxLimit = 1.0;
                return Double.compare(p, 0.0) == 0 ? 0.0 : (Double.compare(p, 1.0) == 0 ? (double)length : 1.0 + (double)(length - 1) * p);
            }
        }
        ,
        R_8("R-8"){

            protected double index(double p, int length) {
                double minLimit = 0.6666666666666666 / ((double)length + 0.3333333333333333);
                double maxLimit = ((double)length - 0.3333333333333333) / ((double)length + 0.3333333333333333);
                return Double.compare(p, minLimit) < 0 ? 0.0 : (Double.compare(p, maxLimit) >= 0 ? (double)length : ((double)length + 0.3333333333333333) * p + 0.3333333333333333);
            }
        }
        ,
        R_9("R-9"){

            protected double index(double p, int length) {
                double minLimit = 0.625 / ((double)length + 0.25);
                double maxLimit = ((double)length - 0.375) / ((double)length + 0.25);
                return Double.compare(p, minLimit) < 0 ? 0.0 : (Double.compare(p, maxLimit) >= 0 ? (double)length : ((double)length + 0.25) * p + 0.375);
            }
        };

        private final String name;

        private EstimationType(String type) {
            this.name = type;
        }

        protected abstract double index(double var1, int var3);

        protected double estimate(double[] work, int[] pivotsHeap, double pos, int length, KthSelector selector) {
            double fpos = FastMath.floor(pos);
            int intPos = (int)fpos;
            double dif = pos - fpos;
            if (pos < 1.0) {
                return selector.select(work, pivotsHeap, 0);
            }
            if (pos >= (double)length) {
                return selector.select(work, pivotsHeap, length - 1);
            }
            double lower = selector.select(work, pivotsHeap, intPos - 1);
            double upper = selector.select(work, pivotsHeap, intPos);
            return lower + dif * (upper - lower);
        }

        protected double evaluate(double[] work, int[] pivotsHeap, double p, KthSelector selector) {
            MathUtils.checkNotNull(work);
            if (p > 100.0 || p <= 0.0) {
                throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, (Number)p, 0, 100);
            }
            return this.estimate(work, pivotsHeap, this.index(p / 100.0, work.length), work.length, selector);
        }

        public double evaluate(double[] work, double p, KthSelector selector) {
            return this.evaluate(work, null, p, selector);
        }

        String getName() {
            return this.name;
        }
    }
}

