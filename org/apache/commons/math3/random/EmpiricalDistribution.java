/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EmpiricalDistribution
extends AbstractRealDistribution {
    public static final int DEFAULT_BIN_COUNT = 1000;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final long serialVersionUID = 5729073523949762654L;
    protected final RandomDataGenerator randomData;
    private final List<SummaryStatistics> binStats;
    private SummaryStatistics sampleStats = null;
    private double max = Double.NEGATIVE_INFINITY;
    private double min = Double.POSITIVE_INFINITY;
    private double delta = 0.0;
    private final int binCount;
    private boolean loaded = false;
    private double[] upperBounds = null;

    public EmpiricalDistribution() {
        this(1000);
    }

    public EmpiricalDistribution(int binCount) {
        this(binCount, new RandomDataGenerator());
    }

    public EmpiricalDistribution(int binCount, RandomGenerator generator) {
        this(binCount, new RandomDataGenerator(generator));
    }

    public EmpiricalDistribution(RandomGenerator generator) {
        this(1000, generator);
    }

    @Deprecated
    public EmpiricalDistribution(int binCount, RandomDataImpl randomData) {
        this(binCount, randomData.getDelegate());
    }

    @Deprecated
    public EmpiricalDistribution(RandomDataImpl randomData) {
        this(1000, randomData);
    }

    private EmpiricalDistribution(int binCount, RandomDataGenerator randomData) {
        super(randomData.getRandomGenerator());
        if (binCount <= 0) {
            throw new NotStrictlyPositiveException(binCount);
        }
        this.binCount = binCount;
        this.randomData = randomData;
        this.binStats = new ArrayList<SummaryStatistics>();
    }

    public void load(double[] in) throws NullArgumentException {
        ArrayDataAdapter da = new ArrayDataAdapter(in);
        try {
            ((DataAdapter)da).computeStats();
            this.fillBinStats(new ArrayDataAdapter(in));
        } catch (IOException ex) {
            throw new MathInternalError();
        }
        this.loaded = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load(URL url) throws IOException, NullArgumentException, ZeroException {
        MathUtils.checkNotNull(url);
        Charset charset = Charset.forName(FILE_CHARSET);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
        try {
            StreamDataAdapter da = new StreamDataAdapter(in);
            ((DataAdapter)da).computeStats();
            if (this.sampleStats.getN() == 0L) {
                throw new ZeroException((Localizable)LocalizedFormats.URL_CONTAINS_NO_DATA, url);
            }
            in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
            this.fillBinStats(new StreamDataAdapter(in));
            this.loaded = true;
        } finally {
            try {
                in.close();
            } catch (IOException iOException) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load(File file) throws IOException, NullArgumentException {
        MathUtils.checkNotNull(file);
        Charset charset = Charset.forName(FILE_CHARSET);
        FileInputStream is = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader((InputStream)is, charset));
        try {
            StreamDataAdapter da = new StreamDataAdapter(in);
            ((DataAdapter)da).computeStats();
            is = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader((InputStream)is, charset));
            this.fillBinStats(new StreamDataAdapter(in));
            this.loaded = true;
        } finally {
            try {
                in.close();
            } catch (IOException iOException) {}
        }
    }

    private void fillBinStats(DataAdapter da) throws IOException {
        int i;
        this.min = this.sampleStats.getMin();
        this.max = this.sampleStats.getMax();
        this.delta = (this.max - this.min) / (double)this.binCount;
        if (!this.binStats.isEmpty()) {
            this.binStats.clear();
        }
        for (i = 0; i < this.binCount; ++i) {
            SummaryStatistics stats = new SummaryStatistics();
            this.binStats.add(i, stats);
        }
        da.computeBinStats();
        this.upperBounds = new double[this.binCount];
        this.upperBounds[0] = (double)this.binStats.get(0).getN() / (double)this.sampleStats.getN();
        for (i = 1; i < this.binCount - 1; ++i) {
            this.upperBounds[i] = this.upperBounds[i - 1] + (double)this.binStats.get(i).getN() / (double)this.sampleStats.getN();
        }
        this.upperBounds[this.binCount - 1] = 1.0;
    }

    private int findBin(double value) {
        return FastMath.min(FastMath.max((int)FastMath.ceil((value - this.min) / this.delta) - 1, 0), this.binCount - 1);
    }

    public double getNextValue() throws MathIllegalStateException {
        if (!this.loaded) {
            throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]);
        }
        return this.sample();
    }

    public StatisticalSummary getSampleStats() {
        return this.sampleStats;
    }

    public int getBinCount() {
        return this.binCount;
    }

    public List<SummaryStatistics> getBinStats() {
        return this.binStats;
    }

    public double[] getUpperBounds() {
        double[] binUpperBounds = new double[this.binCount];
        for (int i = 0; i < this.binCount - 1; ++i) {
            binUpperBounds[i] = this.min + this.delta * (double)(i + 1);
        }
        binUpperBounds[this.binCount - 1] = this.max;
        return binUpperBounds;
    }

    public double[] getGeneratorUpperBounds() {
        int len = this.upperBounds.length;
        double[] out = new double[len];
        System.arraycopy(this.upperBounds, 0, out, 0, len);
        return out;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void reSeed(long seed) {
        this.randomData.reSeed(seed);
    }

    @Override
    public double probability(double x) {
        return 0.0;
    }

    @Override
    public double density(double x) {
        if (x < this.min || x > this.max) {
            return 0.0;
        }
        int binIndex = this.findBin(x);
        RealDistribution kernel = this.getKernel(this.binStats.get(binIndex));
        return kernel.density(x) * this.pB(binIndex) / this.kB(binIndex);
    }

    @Override
    public double cumulativeProbability(double x) {
        if (x < this.min) {
            return 0.0;
        }
        if (x >= this.max) {
            return 1.0;
        }
        int binIndex = this.findBin(x);
        double pBminus = this.pBminus(binIndex);
        double pB = this.pB(binIndex);
        RealDistribution kernel = this.k(x);
        if (kernel instanceof ConstantRealDistribution) {
            if (x < kernel.getNumericalMean()) {
                return pBminus;
            }
            return pBminus + pB;
        }
        double[] binBounds = this.getUpperBounds();
        double kB = this.kB(binIndex);
        double lower = binIndex == 0 ? this.min : binBounds[binIndex - 1];
        double withinBinCum = (kernel.cumulativeProbability(x) - kernel.cumulativeProbability(lower)) / kB;
        return pBminus + pB * withinBinCum;
    }

    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        if (p == 0.0) {
            return this.getSupportLowerBound();
        }
        if (p == 1.0) {
            return this.getSupportUpperBound();
        }
        int i = 0;
        while (this.cumBinP(i) < p) {
            ++i;
        }
        RealDistribution kernel = this.getKernel(this.binStats.get(i));
        double kB = this.kB(i);
        double[] binBounds = this.getUpperBounds();
        double lower = i == 0 ? this.min : binBounds[i - 1];
        double kBminus = kernel.cumulativeProbability(lower);
        double pB = this.pB(i);
        double pBminus = this.pBminus(i);
        double pCrit = p - pBminus;
        if (pCrit <= 0.0) {
            return lower;
        }
        return kernel.inverseCumulativeProbability(kBminus + pCrit * kB / pB);
    }

    @Override
    public double getNumericalMean() {
        return this.sampleStats.getMean();
    }

    @Override
    public double getNumericalVariance() {
        return this.sampleStats.getVariance();
    }

    @Override
    public double getSupportLowerBound() {
        return this.min;
    }

    @Override
    public double getSupportUpperBound() {
        return this.max;
    }

    @Override
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    @Override
    public boolean isSupportConnected() {
        return true;
    }

    @Override
    public void reseedRandomGenerator(long seed) {
        this.randomData.reSeed(seed);
    }

    private double pB(int i) {
        return i == 0 ? this.upperBounds[0] : this.upperBounds[i] - this.upperBounds[i - 1];
    }

    private double pBminus(int i) {
        return i == 0 ? 0.0 : this.upperBounds[i - 1];
    }

    private double kB(int i) {
        double[] binBounds = this.getUpperBounds();
        RealDistribution kernel = this.getKernel(this.binStats.get(i));
        return i == 0 ? kernel.cumulativeProbability(this.min, binBounds[0]) : kernel.cumulativeProbability(binBounds[i - 1], binBounds[i]);
    }

    private RealDistribution k(double x) {
        int binIndex = this.findBin(x);
        return this.getKernel(this.binStats.get(binIndex));
    }

    private double cumBinP(int binIndex) {
        return this.upperBounds[binIndex];
    }

    protected RealDistribution getKernel(SummaryStatistics bStats) {
        if (bStats.getN() == 1L || bStats.getVariance() == 0.0) {
            return new ConstantRealDistribution(bStats.getMean());
        }
        return new NormalDistribution(this.randomData.getRandomGenerator(), bStats.getMean(), bStats.getStandardDeviation(), 1.0E-9);
    }

    private class ArrayDataAdapter
    extends DataAdapter {
        private double[] inputArray;

        ArrayDataAdapter(double[] in) throws NullArgumentException {
            MathUtils.checkNotNull(in);
            this.inputArray = in;
        }

        public void computeStats() throws IOException {
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            for (int i = 0; i < this.inputArray.length; ++i) {
                EmpiricalDistribution.this.sampleStats.addValue(this.inputArray[i]);
            }
        }

        public void computeBinStats() throws IOException {
            for (int i = 0; i < this.inputArray.length; ++i) {
                SummaryStatistics stats = (SummaryStatistics)EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]));
                stats.addValue(this.inputArray[i]);
            }
        }
    }

    private class StreamDataAdapter
    extends DataAdapter {
        private BufferedReader inputStream;

        StreamDataAdapter(BufferedReader in) {
            this.inputStream = in;
        }

        public void computeBinStats() throws IOException {
            String str = null;
            double val = 0.0;
            while ((str = this.inputStream.readLine()) != null) {
                val = Double.parseDouble(str);
                SummaryStatistics stats = (SummaryStatistics)EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(val));
                stats.addValue(val);
            }
            this.inputStream.close();
            this.inputStream = null;
        }

        public void computeStats() throws IOException {
            String str = null;
            double val = 0.0;
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            while ((str = this.inputStream.readLine()) != null) {
                val = Double.parseDouble(str);
                EmpiricalDistribution.this.sampleStats.addValue(val);
            }
            this.inputStream.close();
            this.inputStream = null;
        }
    }

    private abstract class DataAdapter {
        private DataAdapter() {
        }

        public abstract void computeBinStats() throws IOException;

        public abstract void computeStats() throws IOException;
    }
}

