/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import com.google.common.util.concurrent.ForwardingCondition;
import com.google.common.util.concurrent.ForwardingLock;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Beta
@GwtIncompatible
public abstract class Striped<L> {
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>(){

        @Override
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };
    private static final Supplier<ReadWriteLock> WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>(){

        @Override
        public ReadWriteLock get() {
            return new WeakSafeReadWriteLock();
        }
    };
    private static final int ALL_SET = -1;

    private Striped() {
    }

    public abstract L get(Object var1);

    public abstract L getAt(int var1);

    abstract int indexFor(Object var1);

    public abstract int size();

    public Iterable<L> bulkGet(Iterable<?> keys) {
        Object[] array = Iterables.toArray(keys, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] stripes = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            stripes[i] = this.indexFor(array[i]);
        }
        Arrays.sort(stripes);
        int previousStripe = stripes[0];
        array[0] = this.getAt(previousStripe);
        for (int i = 1; i < array.length; ++i) {
            int currentStripe = stripes[i];
            if (currentStripe == previousStripe) {
                array[i] = array[i - 1];
                continue;
            }
            array[i] = this.getAt(currentStripe);
            previousStripe = currentStripe;
        }
        List<Object> asList = Arrays.asList(array);
        return Collections.unmodifiableList(asList);
    }

    static <L> Striped<L> custom(int stripes, Supplier<L> supplier) {
        return new CompactStriped(stripes, supplier);
    }

    public static Striped<Lock> lock(int stripes) {
        return Striped.custom(stripes, new Supplier<Lock>(){

            @Override
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<Lock> lazyWeakLock(int stripes) {
        return Striped.lazy(stripes, new Supplier<Lock>(){

            @Override
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    private static <L> Striped<L> lazy(int stripes, Supplier<L> supplier) {
        return stripes < 1024 ? new SmallLazyStriped<L>(stripes, supplier) : new LargeLazyStriped<L>(stripes, supplier);
    }

    public static Striped<Semaphore> semaphore(int stripes, final int permits) {
        return Striped.custom(stripes, new Supplier<Semaphore>(){

            @Override
            public Semaphore get() {
                return new PaddedSemaphore(permits);
            }
        });
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int stripes, final int permits) {
        return Striped.lazy(stripes, new Supplier<Semaphore>(){

            @Override
            public Semaphore get() {
                return new Semaphore(permits, false);
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int stripes) {
        return Striped.custom(stripes, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int stripes) {
        return Striped.lazy(stripes, WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER);
    }

    private static int ceilToPowerOfTwo(int x) {
        return 1 << IntMath.log2(x, RoundingMode.CEILING);
    }

    private static int smear(int hashCode) {
        hashCode ^= hashCode >>> 20 ^ hashCode >>> 12;
        return hashCode ^ hashCode >>> 7 ^ hashCode >>> 4;
    }

    private static class PaddedSemaphore
    extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        PaddedSemaphore(int permits) {
            super(permits, false);
        }
    }

    private static class PaddedLock
    extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        PaddedLock() {
            super(false);
        }
    }

    @VisibleForTesting
    static class LargeLazyStriped<L>
    extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final Supplier<L> supplier;
        final int size;

        LargeLazyStriped(int stripes, Supplier<L> supplier) {
            super(stripes);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.supplier = supplier;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        @Override
        public L getAt(int index) {
            Object existing;
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, this.size());
            }
            if ((existing = this.locks.get(index)) != null) {
                return (L)existing;
            }
            L created = this.supplier.get();
            existing = this.locks.putIfAbsent(index, created);
            return (L)MoreObjects.firstNonNull(existing, created);
        }

        @Override
        public int size() {
            return this.size;
        }
    }

    @VisibleForTesting
    static class SmallLazyStriped<L>
    extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final Supplier<L> supplier;
        final int size;
        final ReferenceQueue<L> queue = new ReferenceQueue();

        SmallLazyStriped(int stripes, Supplier<L> supplier) {
            super(stripes);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.locks = new AtomicReferenceArray(this.size);
            this.supplier = supplier;
        }

        @Override
        public L getAt(int index) {
            ArrayReference<? extends L> existingRef;
            L existing;
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, this.size());
            }
            L l = existing = (existingRef = this.locks.get(index)) == null ? null : (L)existingRef.get();
            if (existing != null) {
                return existing;
            }
            L created = this.supplier.get();
            ArrayReference<L> newRef = new ArrayReference<L>(created, index, this.queue);
            while (!this.locks.compareAndSet(index, existingRef, newRef)) {
                existingRef = this.locks.get(index);
                existing = existingRef == null ? null : (L)existingRef.get();
                if (existing == null) continue;
                return existing;
            }
            this.drainQueue();
            return created;
        }

        private void drainQueue() {
            Reference<L> ref;
            while ((ref = this.queue.poll()) != null) {
                ArrayReference arrayRef = (ArrayReference)ref;
                this.locks.compareAndSet(arrayRef.index, arrayRef, null);
            }
        }

        @Override
        public int size() {
            return this.size;
        }

        private static final class ArrayReference<L>
        extends WeakReference<L> {
            final int index;

            ArrayReference(L referent, int index, ReferenceQueue<L> queue) {
                super(referent, queue);
                this.index = index;
            }
        }
    }

    private static class CompactStriped<L>
    extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int stripes, Supplier<L> supplier) {
            super(stripes);
            Preconditions.checkArgument(stripes <= 0x40000000, "Stripes must be <= 2^30)");
            this.array = new Object[this.mask + 1];
            for (int i = 0; i < this.array.length; ++i) {
                this.array[i] = supplier.get();
            }
        }

        @Override
        public L getAt(int index) {
            return (L)this.array[index];
        }

        @Override
        public int size() {
            return this.array.length;
        }
    }

    private static abstract class PowerOfTwoStriped<L>
    extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int stripes) {
            Preconditions.checkArgument(stripes > 0, "Stripes must be positive");
            this.mask = stripes > 0x40000000 ? -1 : Striped.ceilToPowerOfTwo(stripes) - 1;
        }

        @Override
        final int indexFor(Object key) {
            int hash = Striped.smear(key.hashCode());
            return hash & this.mask;
        }

        @Override
        public final L get(Object key) {
            return this.getAt(this.indexFor(key));
        }
    }

    private static final class WeakSafeCondition
    extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeCondition(Condition delegate, WeakSafeReadWriteLock strongReference) {
            this.delegate = delegate;
            this.strongReference = strongReference;
        }

        @Override
        Condition delegate() {
            return this.delegate;
        }
    }

    private static final class WeakSafeLock
    extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeLock(Lock delegate, WeakSafeReadWriteLock strongReference) {
            this.delegate = delegate;
            this.strongReference = strongReference;
        }

        @Override
        Lock delegate() {
            return this.delegate;
        }

        @Override
        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    private static final class WeakSafeReadWriteLock
    implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        WeakSafeReadWriteLock() {
        }

        @Override
        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        @Override
        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }
}

