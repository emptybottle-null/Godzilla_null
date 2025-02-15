/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
abstract class SmoothRateLimiter
extends RateLimiter {
    double storedPermits;
    double maxPermits;
    double stableIntervalMicros;
    private long nextFreeTicketMicros = 0L;

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch stopwatch) {
        super(stopwatch);
    }

    @Override
    final void doSetRate(double permitsPerSecond, long nowMicros) {
        double stableIntervalMicros;
        this.resync(nowMicros);
        this.stableIntervalMicros = stableIntervalMicros = (double)TimeUnit.SECONDS.toMicros(1L) / permitsPerSecond;
        this.doSetRate(permitsPerSecond, stableIntervalMicros);
    }

    abstract void doSetRate(double var1, double var3);

    @Override
    final double doGetRate() {
        return (double)TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    @Override
    final long queryEarliestAvailable(long nowMicros) {
        return this.nextFreeTicketMicros;
    }

    @Override
    final long reserveEarliestAvailable(int requiredPermits, long nowMicros) {
        this.resync(nowMicros);
        long returnValue = this.nextFreeTicketMicros;
        double storedPermitsToSpend = Math.min((double)requiredPermits, this.storedPermits);
        double freshPermits = (double)requiredPermits - storedPermitsToSpend;
        long waitMicros = this.storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend) + (long)(freshPermits * this.stableIntervalMicros);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, waitMicros);
        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    abstract long storedPermitsToWaitTime(double var1, double var3);

    abstract double coolDownIntervalMicros();

    void resync(long nowMicros) {
        if (nowMicros > this.nextFreeTicketMicros) {
            double newPermits = (double)(nowMicros - this.nextFreeTicketMicros) / this.coolDownIntervalMicros();
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + newPermits);
            this.nextFreeTicketMicros = nowMicros;
        }
    }

    static final class SmoothBursty
    extends SmoothRateLimiter {
        final double maxBurstSeconds;

        SmoothBursty(RateLimiter.SleepingStopwatch stopwatch, double maxBurstSeconds) {
            super(stopwatch);
            this.maxBurstSeconds = maxBurstSeconds;
        }

        @Override
        void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
            double oldMaxPermits = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
            this.storedPermits = oldMaxPermits == Double.POSITIVE_INFINITY ? this.maxPermits : (oldMaxPermits == 0.0 ? 0.0 : this.storedPermits * this.maxPermits / oldMaxPermits);
        }

        @Override
        long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
            return 0L;
        }

        @Override
        double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }
    }

    static final class SmoothWarmingUp
    extends SmoothRateLimiter {
        private final long warmupPeriodMicros;
        private double slope;
        private double thresholdPermits;
        private double coldFactor;

        SmoothWarmingUp(RateLimiter.SleepingStopwatch stopwatch, long warmupPeriod, TimeUnit timeUnit, double coldFactor) {
            super(stopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(warmupPeriod);
            this.coldFactor = coldFactor;
        }

        @Override
        void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
            double oldMaxPermits = this.maxPermits;
            double coldIntervalMicros = stableIntervalMicros * this.coldFactor;
            this.thresholdPermits = 0.5 * (double)this.warmupPeriodMicros / stableIntervalMicros;
            this.maxPermits = this.thresholdPermits + 2.0 * (double)this.warmupPeriodMicros / (stableIntervalMicros + coldIntervalMicros);
            this.slope = (coldIntervalMicros - stableIntervalMicros) / (this.maxPermits - this.thresholdPermits);
            this.storedPermits = oldMaxPermits == Double.POSITIVE_INFINITY ? 0.0 : (oldMaxPermits == 0.0 ? this.maxPermits : this.storedPermits * this.maxPermits / oldMaxPermits);
        }

        @Override
        long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
            double availablePermitsAboveThreshold = storedPermits - this.thresholdPermits;
            long micros = 0L;
            if (availablePermitsAboveThreshold > 0.0) {
                double permitsAboveThresholdToTake = Math.min(availablePermitsAboveThreshold, permitsToTake);
                double length = this.permitsToTime(availablePermitsAboveThreshold) + this.permitsToTime(availablePermitsAboveThreshold - permitsAboveThresholdToTake);
                micros = (long)(permitsAboveThresholdToTake * length / 2.0);
                permitsToTake -= permitsAboveThresholdToTake;
            }
            return micros += (long)(this.stableIntervalMicros * permitsToTake);
        }

        private double permitsToTime(double permits) {
            return this.stableIntervalMicros + permits * this.slope;
        }

        @Override
        double coolDownIntervalMicros() {
            return (double)this.warmupPeriodMicros / this.maxPermits;
        }
    }
}

