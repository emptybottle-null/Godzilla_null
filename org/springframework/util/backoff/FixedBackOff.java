/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.backoff;

import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;

public class FixedBackOff
implements BackOff {
    public static final long DEFAULT_INTERVAL = 5000L;
    public static final long UNLIMITED_ATTEMPTS = Long.MAX_VALUE;
    private long interval = 5000L;
    private long maxAttempts = Long.MAX_VALUE;

    public FixedBackOff() {
    }

    public FixedBackOff(long interval, long maxAttempts) {
        this.interval = interval;
        this.maxAttempts = maxAttempts;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setMaxAttempts(long maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public long getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override
    public BackOffExecution start() {
        return new FixedBackOffExecution();
    }

    private class FixedBackOffExecution
    implements BackOffExecution {
        private long currentAttempts = 0L;

        private FixedBackOffExecution() {
        }

        @Override
        public long nextBackOff() {
            ++this.currentAttempts;
            if (this.currentAttempts <= FixedBackOff.this.getMaxAttempts()) {
                return FixedBackOff.this.getInterval();
            }
            return -1L;
        }

        public String toString() {
            String attemptValue = FixedBackOff.this.maxAttempts == Long.MAX_VALUE ? "unlimited" : String.valueOf(FixedBackOff.this.maxAttempts);
            return "FixedBackOff{interval=" + FixedBackOff.this.interval + ", currentAttempts=" + this.currentAttempts + ", maxAttempts=" + attemptValue + '}';
        }
    }
}

