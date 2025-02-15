/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.LinearTransformation;
import com.google.common.math.PairedStats;
import com.google.common.math.Stats;
import com.google.common.math.StatsAccumulator;
import com.google.common.primitives.Doubles;

@Beta
@GwtIncompatible
public final class PairedStatsAccumulator {
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();
    private double sumOfProductsOfDeltas = 0.0;

    public void add(double x, double y) {
        this.xStats.add(x);
        if (Doubles.isFinite(x) && Doubles.isFinite(y)) {
            if (this.xStats.count() > 1L) {
                this.sumOfProductsOfDeltas += (x - this.xStats.mean()) * (y - this.yStats.mean());
            }
        } else {
            this.sumOfProductsOfDeltas = Double.NaN;
        }
        this.yStats.add(y);
    }

    public void addAll(PairedStats values) {
        if (values.count() == 0L) {
            return;
        }
        this.xStats.addAll(values.xStats());
        this.sumOfProductsOfDeltas = this.yStats.count() == 0L ? values.sumOfProductsOfDeltas() : (this.sumOfProductsOfDeltas += values.sumOfProductsOfDeltas() + (values.xStats().mean() - this.xStats.mean()) * (values.yStats().mean() - this.yStats.mean()) * (double)values.count());
        this.yStats.addAll(values.yStats());
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public long count() {
        return this.xStats.count();
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }

    public double populationCovariance() {
        Preconditions.checkState(this.count() != 0L);
        return this.sumOfProductsOfDeltas / (double)this.count();
    }

    public final double sampleCovariance() {
        Preconditions.checkState(this.count() > 1L);
        return this.sumOfProductsOfDeltas / (double)(this.count() - 1L);
    }

    public final double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(this.count() > 1L);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        double ySumOfSquaresOfDeltas = this.yStats.sumOfSquaresOfDeltas();
        Preconditions.checkState(xSumOfSquaresOfDeltas > 0.0);
        Preconditions.checkState(ySumOfSquaresOfDeltas > 0.0);
        double productOfSumsOfSquaresOfDeltas = this.ensurePositive(xSumOfSquaresOfDeltas * ySumOfSquaresOfDeltas);
        return PairedStatsAccumulator.ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(productOfSumsOfSquaresOfDeltas));
    }

    public final LinearTransformation leastSquaresFit() {
        Preconditions.checkState(this.count() > 1L);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        if (xSumOfSquaresOfDeltas > 0.0) {
            if (this.yStats.sumOfSquaresOfDeltas() > 0.0) {
                return LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / xSumOfSquaresOfDeltas);
            }
            return LinearTransformation.horizontal(this.yStats.mean());
        }
        Preconditions.checkState(this.yStats.sumOfSquaresOfDeltas() > 0.0);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    private double ensurePositive(double value) {
        if (value > 0.0) {
            return value;
        }
        return Double.MIN_VALUE;
    }

    private static double ensureInUnitRange(double value) {
        if (value >= 1.0) {
            return 1.0;
        }
        if (value <= -1.0) {
            return -1.0;
        }
        return value;
    }
}

