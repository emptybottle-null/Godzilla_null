/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Collection;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.CauchyDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.apache.commons.math3.distribution.PascalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.distribution.ZipfDistribution;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RandomDataGenerator
implements RandomData,
Serializable {
    private static final long serialVersionUID = -626730818244969716L;
    private RandomGenerator rand = null;
    private RandomGenerator secRand = null;

    public RandomDataGenerator() {
    }

    public RandomDataGenerator(RandomGenerator rand) {
        this.rand = rand;
    }

    @Override
    public String nextHexString(int len) throws NotStrictlyPositiveException {
        if (len <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.LENGTH, len);
        }
        RandomGenerator ran = this.getRandomGenerator();
        StringBuilder outBuffer = new StringBuilder();
        byte[] randomBytes = new byte[len / 2 + 1];
        ran.nextBytes(randomBytes);
        for (int i = 0; i < randomBytes.length; ++i) {
            Integer c = randomBytes[i];
            String hex = Integer.toHexString(c + 128);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            outBuffer.append(hex);
        }
        return outBuffer.toString().substring(0, len);
    }

    @Override
    public int nextInt(int lower, int upper) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(this.getRandomGenerator(), lower, upper).sample();
    }

    @Override
    public long nextLong(long lower, long upper) throws NumberIsTooLargeException {
        if (lower >= upper) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, (Number)lower, upper, false);
        }
        long max = upper - lower + 1L;
        if (max <= 0L) {
            long r;
            RandomGenerator rng = this.getRandomGenerator();
            while ((r = rng.nextLong()) < lower || r > upper) {
            }
            return r;
        }
        if (max < Integer.MAX_VALUE) {
            return lower + (long)this.getRandomGenerator().nextInt((int)max);
        }
        return lower + RandomDataGenerator.nextLong(this.getRandomGenerator(), max);
    }

    private static long nextLong(RandomGenerator rng, long n) throws IllegalArgumentException {
        if (n > 0L) {
            long bits;
            long val;
            byte[] byteArray = new byte[8];
            do {
                rng.nextBytes(byteArray);
                bits = 0L;
                for (byte b : byteArray) {
                    bits = bits << 8 | (long)b & 0xFFL;
                }
            } while ((bits &= Long.MAX_VALUE) - (val = bits % n) + (n - 1L) < 0L);
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    @Override
    public String nextSecureHexString(int len) throws NotStrictlyPositiveException {
        if (len <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.LENGTH, len);
        }
        RandomGenerator secRan = this.getSecRan();
        MessageDigest alg = null;
        try {
            alg = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            throw new MathInternalError(ex);
        }
        alg.reset();
        int numIter = len / 40 + 1;
        StringBuilder outBuffer = new StringBuilder();
        for (int iter = 1; iter < numIter + 1; ++iter) {
            byte[] randomBytes = new byte[40];
            secRan.nextBytes(randomBytes);
            alg.update(randomBytes);
            byte[] hash = alg.digest();
            for (int i = 0; i < hash.length; ++i) {
                Integer c = hash[i];
                String hex = Integer.toHexString(c + 128);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                outBuffer.append(hex);
            }
        }
        return outBuffer.toString().substring(0, len);
    }

    @Override
    public int nextSecureInt(int lower, int upper) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(this.getSecRan(), lower, upper).sample();
    }

    @Override
    public long nextSecureLong(long lower, long upper) throws NumberIsTooLargeException {
        if (lower >= upper) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, (Number)lower, upper, false);
        }
        RandomGenerator rng = this.getSecRan();
        long max = upper - lower + 1L;
        if (max <= 0L) {
            long r;
            while ((r = rng.nextLong()) < lower || r > upper) {
            }
            return r;
        }
        if (max < Integer.MAX_VALUE) {
            return lower + (long)rng.nextInt((int)max);
        }
        return lower + RandomDataGenerator.nextLong(rng, max);
    }

    @Override
    public long nextPoisson(double mean) throws NotStrictlyPositiveException {
        return new PoissonDistribution(this.getRandomGenerator(), mean, 1.0E-12, 10000000).sample();
    }

    @Override
    public double nextGaussian(double mu, double sigma) throws NotStrictlyPositiveException {
        if (sigma <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.STANDARD_DEVIATION, sigma);
        }
        return sigma * this.getRandomGenerator().nextGaussian() + mu;
    }

    @Override
    public double nextExponential(double mean) throws NotStrictlyPositiveException {
        return new ExponentialDistribution(this.getRandomGenerator(), mean, 1.0E-9).sample();
    }

    public double nextGamma(double shape, double scale) throws NotStrictlyPositiveException {
        return new GammaDistribution(this.getRandomGenerator(), shape, scale, 1.0E-9).sample();
    }

    public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        return new HypergeometricDistribution(this.getRandomGenerator(), populationSize, numberOfSuccesses, sampleSize).sample();
    }

    public int nextPascal(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        return new PascalDistribution(this.getRandomGenerator(), r, p).sample();
    }

    public double nextT(double df) throws NotStrictlyPositiveException {
        return new TDistribution(this.getRandomGenerator(), df, 1.0E-9).sample();
    }

    public double nextWeibull(double shape, double scale) throws NotStrictlyPositiveException {
        return new WeibullDistribution(this.getRandomGenerator(), shape, scale, 1.0E-9).sample();
    }

    public int nextZipf(int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        return new ZipfDistribution(this.getRandomGenerator(), numberOfElements, exponent).sample();
    }

    public double nextBeta(double alpha, double beta) {
        return new BetaDistribution(this.getRandomGenerator(), alpha, beta, 1.0E-9).sample();
    }

    public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) {
        return new BinomialDistribution(this.getRandomGenerator(), numberOfTrials, probabilityOfSuccess).sample();
    }

    public double nextCauchy(double median, double scale) {
        return new CauchyDistribution(this.getRandomGenerator(), median, scale, 1.0E-9).sample();
    }

    public double nextChiSquare(double df) {
        return new ChiSquaredDistribution(this.getRandomGenerator(), df, 1.0E-9).sample();
    }

    public double nextF(double numeratorDf, double denominatorDf) throws NotStrictlyPositiveException {
        return new FDistribution(this.getRandomGenerator(), numeratorDf, denominatorDf, 1.0E-9).sample();
    }

    @Override
    public double nextUniform(double lower, double upper) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return this.nextUniform(lower, upper, false);
    }

    @Override
    public double nextUniform(double lower, double upper, boolean lowerInclusive) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        if (lower >= upper) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, (Number)lower, upper, false);
        }
        if (Double.isInfinite(lower)) {
            throw new NotFiniteNumberException((Localizable)LocalizedFormats.INFINITE_BOUND, lower, new Object[0]);
        }
        if (Double.isInfinite(upper)) {
            throw new NotFiniteNumberException((Localizable)LocalizedFormats.INFINITE_BOUND, upper, new Object[0]);
        }
        if (Double.isNaN(lower) || Double.isNaN(upper)) {
            throw new NotANumberException();
        }
        RandomGenerator generator = this.getRandomGenerator();
        double u = generator.nextDouble();
        while (!lowerInclusive && u <= 0.0) {
            u = generator.nextDouble();
        }
        return u * upper + (1.0 - u) * lower;
    }

    @Override
    public int[] nextPermutation(int n, int k) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        if (k > n) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.PERMUTATION_EXCEEDS_N, (Number)k, n, true);
        }
        if (k <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.PERMUTATION_SIZE, k);
        }
        int[] index = MathArrays.natural(n);
        MathArrays.shuffle(index, this.getRandomGenerator());
        return MathArrays.copyOf(index, k);
    }

    @Override
    public Object[] nextSample(Collection<?> c, int k) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        int len = c.size();
        if (k > len) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, (Number)k, len, true);
        }
        if (k <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SAMPLES, k);
        }
        Object[] objects = c.toArray();
        int[] index = this.nextPermutation(len, k);
        Object[] result = new Object[k];
        for (int i = 0; i < k; ++i) {
            result[i] = objects[index[i]];
        }
        return result;
    }

    public void reSeed(long seed) {
        this.getRandomGenerator().setSeed(seed);
    }

    public void reSeedSecure() {
        this.getSecRan().setSeed(System.currentTimeMillis());
    }

    public void reSeedSecure(long seed) {
        this.getSecRan().setSeed(seed);
    }

    public void reSeed() {
        this.getRandomGenerator().setSeed(System.currentTimeMillis() + (long)System.identityHashCode(this));
    }

    public void setSecureAlgorithm(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.secRand = RandomGeneratorFactory.createRandomGenerator(SecureRandom.getInstance(algorithm, provider));
    }

    public RandomGenerator getRandomGenerator() {
        if (this.rand == null) {
            this.initRan();
        }
        return this.rand;
    }

    private void initRan() {
        this.rand = new Well19937c(System.currentTimeMillis() + (long)System.identityHashCode(this));
    }

    private RandomGenerator getSecRan() {
        if (this.secRand == null) {
            this.secRand = RandomGeneratorFactory.createRandomGenerator(new SecureRandom());
            this.secRand.setSeed(System.currentTimeMillis() + (long)System.identityHashCode(this));
        }
        return this.secRand;
    }
}

