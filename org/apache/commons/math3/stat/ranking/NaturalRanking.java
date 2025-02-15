/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.RankingAlgorithm;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NaturalRanking
implements RankingAlgorithm {
    public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;
    public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
    private final NaNStrategy nanStrategy;
    private final TiesStrategy tiesStrategy;
    private final RandomDataGenerator randomData;

    public NaturalRanking() {
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(TiesStrategy tiesStrategy) {
        this.tiesStrategy = tiesStrategy;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(NaNStrategy nanStrategy) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = tiesStrategy;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(RandomGenerator randomGenerator) {
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaturalRanking(NaNStrategy nanStrategy, RandomGenerator randomGenerator) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaNStrategy getNanStrategy() {
        return this.nanStrategy;
    }

    public TiesStrategy getTiesStrategy() {
        return this.tiesStrategy;
    }

    @Override
    public double[] rank(double[] data) {
        Object[] ranks = new IntDoublePair[data.length];
        for (int i = 0; i < data.length; ++i) {
            ranks[i] = new IntDoublePair(data[i], i);
        }
        List<Integer> nanPositions = null;
        switch (this.nanStrategy) {
            case MAXIMAL: {
                this.recodeNaNs((IntDoublePair[])ranks, Double.POSITIVE_INFINITY);
                break;
            }
            case MINIMAL: {
                this.recodeNaNs((IntDoublePair[])ranks, Double.NEGATIVE_INFINITY);
                break;
            }
            case REMOVED: {
                ranks = this.removeNaNs((IntDoublePair[])ranks);
                break;
            }
            case FIXED: {
                nanPositions = this.getNanPositions((IntDoublePair[])ranks);
                break;
            }
            case FAILED: {
                nanPositions = this.getNanPositions((IntDoublePair[])ranks);
                if (nanPositions.size() <= 0) break;
                throw new NotANumberException();
            }
            default: {
                throw new MathInternalError();
            }
        }
        Arrays.sort(ranks);
        double[] out = new double[ranks.length];
        int pos = 1;
        out[((IntDoublePair)ranks[0]).getPosition()] = pos;
        ArrayList<Integer> tiesTrace = new ArrayList<Integer>();
        tiesTrace.add(((IntDoublePair)ranks[0]).getPosition());
        for (int i = 1; i < ranks.length; ++i) {
            if (Double.compare(((IntDoublePair)ranks[i]).getValue(), ((IntDoublePair)ranks[i - 1]).getValue()) > 0) {
                pos = i + 1;
                if (tiesTrace.size() > 1) {
                    this.resolveTie(out, tiesTrace);
                }
                tiesTrace = new ArrayList();
                tiesTrace.add(((IntDoublePair)ranks[i]).getPosition());
            } else {
                tiesTrace.add(((IntDoublePair)ranks[i]).getPosition());
            }
            out[((IntDoublePair)ranks[i]).getPosition()] = pos;
        }
        if (tiesTrace.size() > 1) {
            this.resolveTie(out, tiesTrace);
        }
        if (this.nanStrategy == NaNStrategy.FIXED) {
            this.restoreNaNs(out, nanPositions);
        }
        return out;
    }

    private IntDoublePair[] removeNaNs(IntDoublePair[] ranks) {
        if (!this.containsNaNs(ranks)) {
            return ranks;
        }
        IntDoublePair[] outRanks = new IntDoublePair[ranks.length];
        int j = 0;
        for (int i = 0; i < ranks.length; ++i) {
            if (Double.isNaN(ranks[i].getValue())) {
                for (int k = i + 1; k < ranks.length; ++k) {
                    ranks[k] = new IntDoublePair(ranks[k].getValue(), ranks[k].getPosition() - 1);
                }
                continue;
            }
            outRanks[j] = new IntDoublePair(ranks[i].getValue(), ranks[i].getPosition());
            ++j;
        }
        IntDoublePair[] returnRanks = new IntDoublePair[j];
        System.arraycopy(outRanks, 0, returnRanks, 0, j);
        return returnRanks;
    }

    private void recodeNaNs(IntDoublePair[] ranks, double value) {
        for (int i = 0; i < ranks.length; ++i) {
            if (!Double.isNaN(ranks[i].getValue())) continue;
            ranks[i] = new IntDoublePair(value, ranks[i].getPosition());
        }
    }

    private boolean containsNaNs(IntDoublePair[] ranks) {
        for (int i = 0; i < ranks.length; ++i) {
            if (!Double.isNaN(ranks[i].getValue())) continue;
            return true;
        }
        return false;
    }

    private void resolveTie(double[] ranks, List<Integer> tiesTrace) {
        double c = ranks[tiesTrace.get(0)];
        int length = tiesTrace.size();
        switch (this.tiesStrategy) {
            case AVERAGE: {
                this.fill(ranks, tiesTrace, (2.0 * c + (double)length - 1.0) / 2.0);
                break;
            }
            case MAXIMUM: {
                this.fill(ranks, tiesTrace, c + (double)length - 1.0);
                break;
            }
            case MINIMUM: {
                this.fill(ranks, tiesTrace, c);
                break;
            }
            case RANDOM: {
                Iterator<Integer> iterator = tiesTrace.iterator();
                long f = FastMath.round(c);
                while (iterator.hasNext()) {
                    ranks[iterator.next().intValue()] = this.randomData.nextLong(f, f + (long)length - 1L);
                }
                break;
            }
            case SEQUENTIAL: {
                Iterator<Integer> iterator = tiesTrace.iterator();
                long f = FastMath.round(c);
                int i = 0;
                while (iterator.hasNext()) {
                    ranks[iterator.next().intValue()] = f + (long)i++;
                }
                break;
            }
            default: {
                throw new MathInternalError();
            }
        }
    }

    private void fill(double[] data, List<Integer> tiesTrace, double value) {
        Iterator<Integer> iterator = tiesTrace.iterator();
        while (iterator.hasNext()) {
            data[iterator.next().intValue()] = value;
        }
    }

    private void restoreNaNs(double[] ranks, List<Integer> nanPositions) {
        if (nanPositions.size() == 0) {
            return;
        }
        Iterator<Integer> iterator = nanPositions.iterator();
        while (iterator.hasNext()) {
            ranks[iterator.next().intValue()] = Double.NaN;
        }
    }

    private List<Integer> getNanPositions(IntDoublePair[] ranks) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < ranks.length; ++i) {
            if (!Double.isNaN(ranks[i].getValue())) continue;
            out.add(i);
        }
        return out;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class IntDoublePair
    implements Comparable<IntDoublePair> {
        private final double value;
        private final int position;

        IntDoublePair(double value, int position) {
            this.value = value;
            this.position = position;
        }

        @Override
        public int compareTo(IntDoublePair other) {
            return Double.compare(this.value, other.value);
        }

        public double getValue() {
            return this.value;
        }

        public int getPosition() {
            return this.position;
        }
    }
}

