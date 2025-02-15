/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.util.Iterator;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;

public class IntegerSequence {
    private IntegerSequence() {
    }

    public static Range range(int start, int end) {
        return IntegerSequence.range(start, end, 1);
    }

    public static Range range(int start, int max, int step) {
        return new Range(start, max, step);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Incrementor
    implements Iterator<Integer> {
        private static final MaxCountExceededCallback CALLBACK = new MaxCountExceededCallback(){

            public void trigger(int max) throws MaxCountExceededException {
                throw new MaxCountExceededException(max);
            }
        };
        private final int init;
        private final int maximalCount;
        private final int increment;
        private final MaxCountExceededCallback maxCountCallback;
        private int count = 0;

        private Incrementor(int start, int max, int step, MaxCountExceededCallback cb) throws NullArgumentException {
            if (cb == null) {
                throw new NullArgumentException();
            }
            this.init = start;
            this.maximalCount = max;
            this.increment = step;
            this.maxCountCallback = cb;
            this.count = start;
        }

        public static Incrementor create() {
            return new Incrementor(0, 0, 1, CALLBACK);
        }

        public Incrementor withStart(int start) {
            return new Incrementor(start, this.maximalCount, this.increment, this.maxCountCallback);
        }

        public Incrementor withMaximalCount(int max) {
            return new Incrementor(this.init, max, this.increment, this.maxCountCallback);
        }

        public Incrementor withIncrement(int step) {
            if (step == 0) {
                throw new ZeroException();
            }
            return new Incrementor(this.init, this.maximalCount, step, this.maxCountCallback);
        }

        public Incrementor withCallback(MaxCountExceededCallback cb) {
            return new Incrementor(this.init, this.maximalCount, this.increment, cb);
        }

        public int getMaximalCount() {
            return this.maximalCount;
        }

        public int getCount() {
            return this.count;
        }

        public boolean canIncrement() {
            return this.canIncrement(1);
        }

        public boolean canIncrement(int nTimes) {
            int finalCount = this.count + nTimes * this.increment;
            return this.increment < 0 ? finalCount > this.maximalCount : finalCount < this.maximalCount;
        }

        public void increment(int nTimes) throws MaxCountExceededException {
            if (nTimes <= 0) {
                throw new NotStrictlyPositiveException(nTimes);
            }
            if (!this.canIncrement(0)) {
                this.maxCountCallback.trigger(this.maximalCount);
            }
            this.count += nTimes * this.increment;
        }

        public void increment() throws MaxCountExceededException {
            this.increment(1);
        }

        @Override
        public boolean hasNext() {
            return this.canIncrement(0);
        }

        @Override
        public Integer next() {
            int value = this.count;
            this.increment();
            return value;
        }

        @Override
        public void remove() {
            throw new MathUnsupportedOperationException();
        }

        public static interface MaxCountExceededCallback {
            public void trigger(int var1) throws MaxCountExceededException;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Range
    implements Iterable<Integer> {
        private final int size;
        private final int start;
        private final int max;
        private final int step;

        public Range(int start, int max, int step) {
            this.start = start;
            this.max = max;
            this.step = step;
            int s = (max - start) / step + 1;
            this.size = s < 0 ? 0 : s;
        }

        public int size() {
            return this.size;
        }

        @Override
        public Iterator<Integer> iterator() {
            return Incrementor.create().withStart(this.start).withMaximalCount(this.max + (this.step > 0 ? 1 : -1)).withIncrement(this.step);
        }
    }
}

