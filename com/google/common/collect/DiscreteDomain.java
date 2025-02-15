/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.NoSuchElementException;

@GwtCompatible
public abstract class DiscreteDomain<C extends Comparable> {
    final boolean supportsFastOffset;

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    public static DiscreteDomain<Long> longs() {
        return LongDomain.INSTANCE;
    }

    public static DiscreteDomain<BigInteger> bigIntegers() {
        return BigIntegerDomain.INSTANCE;
    }

    protected DiscreteDomain() {
        this(false);
    }

    private DiscreteDomain(boolean supportsFastOffset) {
        this.supportsFastOffset = supportsFastOffset;
    }

    C offset(C origin, long distance) {
        CollectPreconditions.checkNonnegative(distance, "distance");
        for (long i = 0L; i < distance; ++i) {
            origin = this.next(origin);
        }
        return origin;
    }

    public abstract C next(C var1);

    public abstract C previous(C var1);

    public abstract long distance(C var1, C var2);

    @CanIgnoreReturnValue
    public C minValue() {
        throw new NoSuchElementException();
    }

    @CanIgnoreReturnValue
    public C maxValue() {
        throw new NoSuchElementException();
    }

    private static final class BigIntegerDomain
    extends DiscreteDomain<BigInteger>
    implements Serializable {
        private static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
        private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
        private static final long serialVersionUID = 0L;

        BigIntegerDomain() {
            super(true);
        }

        @Override
        public BigInteger next(BigInteger value) {
            return value.add(BigInteger.ONE);
        }

        @Override
        public BigInteger previous(BigInteger value) {
            return value.subtract(BigInteger.ONE);
        }

        @Override
        BigInteger offset(BigInteger origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            return origin.add(BigInteger.valueOf(distance));
        }

        @Override
        public long distance(BigInteger start, BigInteger end) {
            return end.subtract(start).max(MIN_LONG).min(MAX_LONG).longValue();
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.bigIntegers()";
        }
    }

    private static final class LongDomain
    extends DiscreteDomain<Long>
    implements Serializable {
        private static final LongDomain INSTANCE = new LongDomain();
        private static final long serialVersionUID = 0L;

        LongDomain() {
            super(true);
        }

        @Override
        public Long next(Long value) {
            long l = value;
            return l == Long.MAX_VALUE ? null : Long.valueOf(l + 1L);
        }

        @Override
        public Long previous(Long value) {
            long l = value;
            return l == Long.MIN_VALUE ? null : Long.valueOf(l - 1L);
        }

        @Override
        Long offset(Long origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            long result = origin + distance;
            if (result < 0L) {
                Preconditions.checkArgument(origin < 0L, "overflow");
            }
            return result;
        }

        @Override
        public long distance(Long start, Long end) {
            long result = end - start;
            if (end > start && result < 0L) {
                return Long.MAX_VALUE;
            }
            if (end < start && result > 0L) {
                return Long.MIN_VALUE;
            }
            return result;
        }

        @Override
        public Long minValue() {
            return Long.MIN_VALUE;
        }

        @Override
        public Long maxValue() {
            return Long.MAX_VALUE;
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.longs()";
        }
    }

    private static final class IntegerDomain
    extends DiscreteDomain<Integer>
    implements Serializable {
        private static final IntegerDomain INSTANCE = new IntegerDomain();
        private static final long serialVersionUID = 0L;

        IntegerDomain() {
            super(true);
        }

        @Override
        public Integer next(Integer value) {
            int i = value;
            return i == Integer.MAX_VALUE ? null : Integer.valueOf(i + 1);
        }

        @Override
        public Integer previous(Integer value) {
            int i = value;
            return i == Integer.MIN_VALUE ? null : Integer.valueOf(i - 1);
        }

        @Override
        Integer offset(Integer origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            return Ints.checkedCast(origin.longValue() + distance);
        }

        @Override
        public long distance(Integer start, Integer end) {
            return (long)end.intValue() - (long)start.intValue();
        }

        @Override
        public Integer minValue() {
            return Integer.MIN_VALUE;
        }

        @Override
        public Integer maxValue() {
            return Integer.MAX_VALUE;
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }
    }
}

