/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collection;
import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class RandomDataImpl
implements RandomData,
Serializable {
    private static final long serialVersionUID = -626730818244969716L;
    private final RandomDataGenerator delegate;

    public RandomDataImpl() {
        this.delegate = new RandomDataGenerator();
    }

    public RandomDataImpl(RandomGenerator rand) {
        this.delegate = new RandomDataGenerator(rand);
    }

    @Deprecated
    RandomDataGenerator getDelegate() {
        return this.delegate;
    }

    @Override
    public String nextHexString(int len) throws NotStrictlyPositiveException {
        return this.delegate.nextHexString(len);
    }

    @Override
    public int nextInt(int lower, int upper) throws NumberIsTooLargeException {
        return this.delegate.nextInt(lower, upper);
    }

    @Override
    public long nextLong(long lower, long upper) throws NumberIsTooLargeException {
        return this.delegate.nextLong(lower, upper);
    }

    @Override
    public String nextSecureHexString(int len) throws NotStrictlyPositiveException {
        return this.delegate.nextSecureHexString(len);
    }

    @Override
    public int nextSecureInt(int lower, int upper) throws NumberIsTooLargeException {
        return this.delegate.nextSecureInt(lower, upper);
    }

    @Override
    public long nextSecureLong(long lower, long upper) throws NumberIsTooLargeException {
        return this.delegate.nextSecureLong(lower, upper);
    }

    @Override
    public long nextPoisson(double mean) throws NotStrictlyPositiveException {
        return this.delegate.nextPoisson(mean);
    }

    @Override
    public double nextGaussian(double mu, double sigma) throws NotStrictlyPositiveException {
        return this.delegate.nextGaussian(mu, sigma);
    }

    @Override
    public double nextExponential(double mean) throws NotStrictlyPositiveException {
        return this.delegate.nextExponential(mean);
    }

    @Override
    public double nextUniform(double lower, double upper) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return this.delegate.nextUniform(lower, upper);
    }

    @Override
    public double nextUniform(double lower, double upper, boolean lowerInclusive) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return this.delegate.nextUniform(lower, upper, lowerInclusive);
    }

    public double nextBeta(double alpha, double beta) {
        return this.delegate.nextBeta(alpha, beta);
    }

    public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) {
        return this.delegate.nextBinomial(numberOfTrials, probabilityOfSuccess);
    }

    public double nextCauchy(double median, double scale) {
        return this.delegate.nextCauchy(median, scale);
    }

    public double nextChiSquare(double df) {
        return this.delegate.nextChiSquare(df);
    }

    public double nextF(double numeratorDf, double denominatorDf) throws NotStrictlyPositiveException {
        return this.delegate.nextF(numeratorDf, denominatorDf);
    }

    public double nextGamma(double shape, double scale) throws NotStrictlyPositiveException {
        return this.delegate.nextGamma(shape, scale);
    }

    public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        return this.delegate.nextHypergeometric(populationSize, numberOfSuccesses, sampleSize);
    }

    public int nextPascal(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        return this.delegate.nextPascal(r, p);
    }

    public double nextT(double df) throws NotStrictlyPositiveException {
        return this.delegate.nextT(df);
    }

    public double nextWeibull(double shape, double scale) throws NotStrictlyPositiveException {
        return this.delegate.nextWeibull(shape, scale);
    }

    public int nextZipf(int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        return this.delegate.nextZipf(numberOfElements, exponent);
    }

    public void reSeed(long seed) {
        this.delegate.reSeed(seed);
    }

    public void reSeedSecure() {
        this.delegate.reSeedSecure();
    }

    public void reSeedSecure(long seed) {
        this.delegate.reSeedSecure(seed);
    }

    public void reSeed() {
        this.delegate.reSeed();
    }

    public void setSecureAlgorithm(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.delegate.setSecureAlgorithm(algorithm, provider);
    }

    @Override
    public int[] nextPermutation(int n, int k) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return this.delegate.nextPermutation(n, k);
    }

    @Override
    public Object[] nextSample(Collection<?> c, int k) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return this.delegate.nextSample(c, k);
    }

    @Deprecated
    public double nextInversionDeviate(RealDistribution distribution) throws MathIllegalArgumentException {
        return distribution.inverseCumulativeProbability(this.nextUniform(0.0, 1.0));
    }

    @Deprecated
    public int nextInversionDeviate(IntegerDistribution distribution) throws MathIllegalArgumentException {
        return distribution.inverseCumulativeProbability(this.nextUniform(0.0, 1.0));
    }
}

