/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.hash.Funnel;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class BloomFilter<T>
implements Predicate<T>,
Serializable {
    private final BloomFilterStrategies.LockFreeBitArray bits;
    private final int numHashFunctions;
    private final Funnel<? super T> funnel;
    private final Strategy strategy;

    private BloomFilter(BloomFilterStrategies.LockFreeBitArray bits, int numHashFunctions, Funnel<? super T> funnel, Strategy strategy) {
        Preconditions.checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", numHashFunctions);
        Preconditions.checkArgument(numHashFunctions <= 255, "numHashFunctions (%s) must be <= 255", numHashFunctions);
        this.bits = Preconditions.checkNotNull(bits);
        this.numHashFunctions = numHashFunctions;
        this.funnel = Preconditions.checkNotNull(funnel);
        this.strategy = Preconditions.checkNotNull(strategy);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<T>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T object) {
        return this.strategy.mightContain(object, this.funnel, this.numHashFunctions, this.bits);
    }

    @Override
    @Deprecated
    public boolean apply(T input) {
        return this.mightContain(input);
    }

    @CanIgnoreReturnValue
    public boolean put(T object) {
        return this.strategy.put(object, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow((double)this.bits.bitCount() / (double)this.bitSize(), this.numHashFunctions);
    }

    public long approximateElementCount() {
        long bitSize = this.bits.bitSize();
        long bitCount = this.bits.bitCount();
        double fractionOfBitsSet = (double)bitCount / (double)bitSize;
        return DoubleMath.roundToLong(-Math.log1p(-fractionOfBitsSet) * (double)bitSize / (double)this.numHashFunctions, RoundingMode.HALF_UP);
    }

    @VisibleForTesting
    long bitSize() {
        return this.bits.bitSize();
    }

    public boolean isCompatible(BloomFilter<T> that) {
        Preconditions.checkNotNull(that);
        return this != that && this.numHashFunctions == that.numHashFunctions && this.bitSize() == that.bitSize() && this.strategy.equals(that.strategy) && this.funnel.equals(that.funnel);
    }

    public void putAll(BloomFilter<T> that) {
        Preconditions.checkNotNull(that);
        Preconditions.checkArgument(this != that, "Cannot combine a BloomFilter with itself.");
        Preconditions.checkArgument(this.numHashFunctions == that.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", this.numHashFunctions, that.numHashFunctions);
        Preconditions.checkArgument(this.bitSize() == that.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", this.bitSize(), that.bitSize());
        Preconditions.checkArgument(this.strategy.equals(that.strategy), "BloomFilters must have equal strategies (%s != %s)", (Object)this.strategy, (Object)that.strategy);
        Preconditions.checkArgument(this.funnel.equals(that.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, that.funnel);
        this.bits.putAll(that.bits);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BloomFilter) {
            BloomFilter that = (BloomFilter)object;
            return this.numHashFunctions == that.numHashFunctions && this.funnel.equals(that.funnel) && this.bits.equals(that.bits) && this.strategy.equals(that.strategy);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.numHashFunctions, this.funnel, this.strategy, this.bits);
    }

    public static <T> Collector<T, ?, BloomFilter<T>> toBloomFilter(Funnel<? super T> funnel, long expectedInsertions) {
        return BloomFilter.toBloomFilter(funnel, expectedInsertions, 0.03);
    }

    public static <T> Collector<T, ?, BloomFilter<T>> toBloomFilter(Funnel<? super T> funnel, long expectedInsertions, double fpp) {
        Preconditions.checkNotNull(funnel);
        Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", expectedInsertions);
        Preconditions.checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", (Object)fpp);
        Preconditions.checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", (Object)fpp);
        return Collector.of(() -> BloomFilter.create(funnel, expectedInsertions, fpp), BloomFilter::put, (bf1, bf2) -> {
            bf1.putAll((BloomFilter)bf2);
            return bf1;
        }, Collector.Characteristics.UNORDERED, Collector.Characteristics.CONCURRENT);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp) {
        return BloomFilter.create(funnel, (long)expectedInsertions, fpp);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp) {
        return BloomFilter.create(funnel, expectedInsertions, fpp, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp, Strategy strategy) {
        Preconditions.checkNotNull(funnel);
        Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", expectedInsertions);
        Preconditions.checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", (Object)fpp);
        Preconditions.checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", (Object)fpp);
        Preconditions.checkNotNull(strategy);
        if (expectedInsertions == 0L) {
            expectedInsertions = 1L;
        }
        long numBits = BloomFilter.optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = BloomFilter.optimalNumOfHashFunctions(expectedInsertions, numBits);
        try {
            return new BloomFilter<T>(new BloomFilterStrategies.LockFreeBitArray(numBits), numHashFunctions, funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions) {
        return BloomFilter.create(funnel, (long)expectedInsertions);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions) {
        return BloomFilter.create(funnel, expectedInsertions, 0.03);
    }

    @VisibleForTesting
    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int)Math.round((double)m / (double)n * Math.log(2.0)));
    }

    @VisibleForTesting
    static long optimalNumOfBits(long n, double p) {
        if (p == 0.0) {
            p = Double.MIN_VALUE;
        }
        return (long)((double)(-n) * Math.log(p) / (Math.log(2.0) * Math.log(2.0)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    public void writeTo(OutputStream out) throws IOException {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeByte(SignedBytes.checkedCast(this.strategy.ordinal()));
        dout.writeByte(UnsignedBytes.checkedCast(this.numHashFunctions));
        dout.writeInt(this.bits.data.length());
        for (int i = 0; i < this.bits.data.length(); ++i) {
            dout.writeLong(this.bits.data.get(i));
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream in, Funnel<? super T> funnel) throws IOException {
        Preconditions.checkNotNull(in, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        int strategyOrdinal = -1;
        int numHashFunctions = -1;
        int dataLength = -1;
        try {
            DataInputStream din = new DataInputStream(in);
            strategyOrdinal = din.readByte();
            numHashFunctions = UnsignedBytes.toInt(din.readByte());
            dataLength = din.readInt();
            BloomFilterStrategies strategy = BloomFilterStrategies.values()[strategyOrdinal];
            long[] data = new long[dataLength];
            for (int i = 0; i < data.length; ++i) {
                data[i] = din.readLong();
            }
            return new BloomFilter<T>(new BloomFilterStrategies.LockFreeBitArray(data), numHashFunctions, funnel, strategy);
        } catch (RuntimeException e) {
            String message = "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + strategyOrdinal + " numHashFunctions: " + numHashFunctions + " dataLength: " + dataLength;
            throw new IOException(message, e);
        }
    }

    private static class SerialForm<T>
    implements Serializable {
        final long[] data;
        final int numHashFunctions;
        final Funnel<? super T> funnel;
        final Strategy strategy;
        private static final long serialVersionUID = 1L;

        SerialForm(BloomFilter<T> bf) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(((BloomFilter)bf).bits.data);
            this.numHashFunctions = ((BloomFilter)bf).numHashFunctions;
            this.funnel = ((BloomFilter)bf).funnel;
            this.strategy = ((BloomFilter)bf).strategy;
        }

        Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    static interface Strategy
    extends Serializable {
        public <T> boolean put(T var1, Funnel<? super T> var2, int var3, BloomFilterStrategies.LockFreeBitArray var4);

        public <T> boolean mightContain(T var1, Funnel<? super T> var2, int var3, BloomFilterStrategies.LockFreeBitArray var4);

        public int ordinal();
    }
}

