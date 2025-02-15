/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;
import com.google.common.hash.LongAddable;
import com.google.common.hash.LongAddables;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import org.checkerframework.checker.nullness.qual.Nullable;

enum BloomFilterStrategies implements BloomFilter.Strategy
{
    MURMUR128_MITZ_32{

        @Override
        public <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, LockFreeBitArray bits) {
            long bitSize = bits.bitSize();
            long hash64 = Hashing.murmur3_128().hashObject(object, funnel).asLong();
            int hash1 = (int)hash64;
            int hash2 = (int)(hash64 >>> 32);
            boolean bitsChanged = false;
            for (int i = 1; i <= numHashFunctions; ++i) {
                int combinedHash = hash1 + i * hash2;
                if (combinedHash < 0) {
                    combinedHash ^= 0xFFFFFFFF;
                }
                bitsChanged |= bits.set((long)combinedHash % bitSize);
            }
            return bitsChanged;
        }

        @Override
        public <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, LockFreeBitArray bits) {
            long bitSize = bits.bitSize();
            long hash64 = Hashing.murmur3_128().hashObject(object, funnel).asLong();
            int hash1 = (int)hash64;
            int hash2 = (int)(hash64 >>> 32);
            for (int i = 1; i <= numHashFunctions; ++i) {
                int combinedHash = hash1 + i * hash2;
                if (combinedHash < 0) {
                    combinedHash ^= 0xFFFFFFFF;
                }
                if (bits.get((long)combinedHash % bitSize)) continue;
                return false;
            }
            return true;
        }
    }
    ,
    MURMUR128_MITZ_64{

        @Override
        public <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, LockFreeBitArray bits) {
            long bitSize = bits.bitSize();
            byte[] bytes = Hashing.murmur3_128().hashObject(object, funnel).getBytesInternal();
            long hash1 = this.lowerEight(bytes);
            long hash2 = this.upperEight(bytes);
            boolean bitsChanged = false;
            long combinedHash = hash1;
            for (int i = 0; i < numHashFunctions; ++i) {
                bitsChanged |= bits.set((combinedHash & Long.MAX_VALUE) % bitSize);
                combinedHash += hash2;
            }
            return bitsChanged;
        }

        @Override
        public <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, LockFreeBitArray bits) {
            long bitSize = bits.bitSize();
            byte[] bytes = Hashing.murmur3_128().hashObject(object, funnel).getBytesInternal();
            long hash1 = this.lowerEight(bytes);
            long hash2 = this.upperEight(bytes);
            long combinedHash = hash1;
            for (int i = 0; i < numHashFunctions; ++i) {
                if (!bits.get((combinedHash & Long.MAX_VALUE) % bitSize)) {
                    return false;
                }
                combinedHash += hash2;
            }
            return true;
        }

        private long lowerEight(byte[] bytes) {
            return Longs.fromBytes(bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]);
        }

        private long upperEight(byte[] bytes) {
            return Longs.fromBytes(bytes[15], bytes[14], bytes[13], bytes[12], bytes[11], bytes[10], bytes[9], bytes[8]);
        }
    };


    static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;
        final AtomicLongArray data;
        private final LongAddable bitCount;

        LockFreeBitArray(long bits) {
            this(new long[Ints.checkedCast(LongMath.divide(bits, 64L, RoundingMode.CEILING))]);
        }

        LockFreeBitArray(long[] data) {
            Preconditions.checkArgument(data.length > 0, "data length is zero!");
            this.data = new AtomicLongArray(data);
            this.bitCount = LongAddables.create();
            long bitCount = 0L;
            for (long value : data) {
                bitCount += (long)Long.bitCount(value);
            }
            this.bitCount.add(bitCount);
        }

        boolean set(long bitIndex) {
            long newValue;
            long oldValue;
            if (this.get(bitIndex)) {
                return false;
            }
            int longIndex = (int)(bitIndex >>> 6);
            long mask = 1L << (int)bitIndex;
            do {
                if ((oldValue = this.data.get(longIndex)) != (newValue = oldValue | mask)) continue;
                return false;
            } while (!this.data.compareAndSet(longIndex, oldValue, newValue));
            this.bitCount.increment();
            return true;
        }

        boolean get(long bitIndex) {
            return (this.data.get((int)(bitIndex >>> 6)) & 1L << (int)bitIndex) != 0L;
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            long[] array = new long[atomicLongArray.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = atomicLongArray.get(i);
            }
            return array;
        }

        long bitSize() {
            return (long)this.data.length() * 64L;
        }

        long bitCount() {
            return this.bitCount.sum();
        }

        LockFreeBitArray copy() {
            return new LockFreeBitArray(LockFreeBitArray.toPlainArray(this.data));
        }

        void putAll(LockFreeBitArray other) {
            Preconditions.checkArgument(this.data.length() == other.data.length(), "BitArrays must be of equal length (%s != %s)", this.data.length(), other.data.length());
            for (int i = 0; i < this.data.length(); ++i) {
                long ourLongNew;
                long ourLongOld;
                long otherLong = other.data.get(i);
                boolean changedAnyBits = true;
                do {
                    if ((ourLongOld = this.data.get(i)) != (ourLongNew = ourLongOld | otherLong)) continue;
                    changedAnyBits = false;
                    break;
                } while (!this.data.compareAndSet(i, ourLongOld, ourLongNew));
                if (!changedAnyBits) continue;
                int bitsAdded = Long.bitCount(ourLongNew) - Long.bitCount(ourLongOld);
                this.bitCount.add(bitsAdded);
            }
        }

        public boolean equals(@Nullable Object o) {
            if (o instanceof LockFreeBitArray) {
                LockFreeBitArray lockFreeBitArray = (LockFreeBitArray)o;
                return Arrays.equals(LockFreeBitArray.toPlainArray(this.data), LockFreeBitArray.toPlainArray(lockFreeBitArray.data));
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(LockFreeBitArray.toPlainArray(this.data));
        }
    }
}

