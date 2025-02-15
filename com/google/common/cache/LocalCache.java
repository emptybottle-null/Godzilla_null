/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.ForwardingCache;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.ReferenceEntry;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
class LocalCache<K, V>
extends AbstractMap<K, V>
implements ConcurrentMap<K, V> {
    static final int MAXIMUM_CAPACITY = 0x40000000;
    static final int MAX_SEGMENTS = 65536;
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_THRESHOLD = 63;
    static final int DRAIN_MAX = 16;
    static final Logger logger = Logger.getLogger(LocalCache.class.getName());
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    final int concurrencyLevel;
    final Equivalence<Object> keyEquivalence;
    final Equivalence<Object> valueEquivalence;
    final Strength keyStrength;
    final Strength valueStrength;
    final long maxWeight;
    final Weigher<K, V> weigher;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final long refreshNanos;
    final Queue<RemovalNotification<K, V>> removalNotificationQueue;
    final RemovalListener<K, V> removalListener;
    final Ticker ticker;
    final EntryFactory entryFactory;
    final AbstractCache.StatsCounter globalStatsCounter;
    final @Nullable CacheLoader<? super K, V> defaultLoader;
    static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>(){

        @Override
        public Object get() {
            return null;
        }

        @Override
        public int getWeight() {
            return 0;
        }

        @Override
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        @Override
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> queue, @Nullable Object value, ReferenceEntry<Object, Object> entry) {
            return this;
        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public Object waitForValue() {
            return null;
        }

        @Override
        public void notifyNewValue(Object newValue) {
        }
    };
    static final Queue<?> DISCARDING_QUEUE = new AbstractQueue<Object>(){

        @Override
        public boolean offer(Object o) {
            return true;
        }

        @Override
        public Object peek() {
            return null;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }
    };
    @MonotonicNonNull Set<K> keySet;
    @MonotonicNonNull Collection<V> values;
    @MonotonicNonNull Set<Map.Entry<K, V>> entrySet;

    LocalCache(CacheBuilder<? super K, ? super V> builder, @Nullable CacheLoader<? super K, V> loader) {
        int segmentSize;
        int segmentCount;
        this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
        this.keyStrength = builder.getKeyStrength();
        this.valueStrength = builder.getValueStrength();
        this.keyEquivalence = builder.getKeyEquivalence();
        this.valueEquivalence = builder.getValueEquivalence();
        this.maxWeight = builder.getMaximumWeight();
        this.weigher = builder.getWeigher();
        this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
        this.refreshNanos = builder.getRefreshNanos();
        this.removalListener = builder.getRemovalListener();
        this.removalNotificationQueue = this.removalListener == CacheBuilder.NullListener.INSTANCE ? LocalCache.discardingQueue() : new ConcurrentLinkedQueue();
        this.ticker = builder.getTicker(this.recordsTime());
        this.entryFactory = EntryFactory.getFactory(this.keyStrength, this.usesAccessEntries(), this.usesWriteEntries());
        this.globalStatsCounter = builder.getStatsCounterSupplier().get();
        this.defaultLoader = loader;
        int initialCapacity = Math.min(builder.getInitialCapacity(), 0x40000000);
        if (this.evictsBySize() && !this.customWeigher()) {
            initialCapacity = (int)Math.min((long)initialCapacity, this.maxWeight);
        }
        int segmentShift = 0;
        for (segmentCount = 1; !(segmentCount >= this.concurrencyLevel || this.evictsBySize() && (long)(segmentCount * 20) > this.maxWeight); segmentCount <<= 1) {
            ++segmentShift;
        }
        this.segmentShift = 32 - segmentShift;
        this.segmentMask = segmentCount - 1;
        this.segments = this.newSegmentArray(segmentCount);
        int segmentCapacity = initialCapacity / segmentCount;
        if (segmentCapacity * segmentCount < initialCapacity) {
            ++segmentCapacity;
        }
        for (segmentSize = 1; segmentSize < segmentCapacity; segmentSize <<= 1) {
        }
        if (this.evictsBySize()) {
            long maxSegmentWeight = this.maxWeight / (long)segmentCount + 1L;
            long remainder = this.maxWeight % (long)segmentCount;
            for (int i = 0; i < this.segments.length; ++i) {
                if ((long)i == remainder) {
                    --maxSegmentWeight;
                }
                this.segments[i] = this.createSegment(segmentSize, maxSegmentWeight, builder.getStatsCounterSupplier().get());
            }
        } else {
            for (int i = 0; i < this.segments.length; ++i) {
                this.segments[i] = this.createSegment(segmentSize, -1L, builder.getStatsCounterSupplier().get());
            }
        }
    }

    boolean evictsBySize() {
        return this.maxWeight >= 0L;
    }

    boolean customWeigher() {
        return this.weigher != CacheBuilder.OneWeigher.INSTANCE;
    }

    boolean expires() {
        return this.expiresAfterWrite() || this.expiresAfterAccess();
    }

    boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0L;
    }

    boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0L;
    }

    boolean refreshes() {
        return this.refreshNanos > 0L;
    }

    boolean usesAccessQueue() {
        return this.expiresAfterAccess() || this.evictsBySize();
    }

    boolean usesWriteQueue() {
        return this.expiresAfterWrite();
    }

    boolean recordsWrite() {
        return this.expiresAfterWrite() || this.refreshes();
    }

    boolean recordsAccess() {
        return this.expiresAfterAccess();
    }

    boolean recordsTime() {
        return this.recordsWrite() || this.recordsAccess();
    }

    boolean usesWriteEntries() {
        return this.usesWriteQueue() || this.recordsWrite();
    }

    boolean usesAccessEntries() {
        return this.usesAccessQueue() || this.recordsAccess();
    }

    boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    static <K, V> ValueReference<K, V> unset() {
        return UNSET;
    }

    static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    static <E> Queue<E> discardingQueue() {
        return DISCARDING_QUEUE;
    }

    static int rehash(int h) {
        h += h << 15 ^ 0xFFFFCD7D;
        h ^= h >>> 10;
        h += h << 3;
        h ^= h >>> 6;
        h += (h << 2) + (h << 14);
        return h ^ h >>> 16;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @VisibleForTesting
    ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        Segment<K, V> segment = this.segmentFor(hash);
        segment.lock();
        try {
            ReferenceEntry<K, V> referenceEntry = segment.newEntry(key, hash, next);
            return referenceEntry;
        } finally {
            segment.unlock();
        }
    }

    @VisibleForTesting
    ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
        int hash = original.getHash();
        return this.segmentFor(hash).copyEntry(original, newNext);
    }

    @VisibleForTesting
    ValueReference<K, V> newValueReference(ReferenceEntry<K, V> entry, V value, int weight) {
        int hash = entry.getHash();
        return this.valueStrength.referenceValue(this.segmentFor(hash), entry, Preconditions.checkNotNull(value), weight);
    }

    int hash(@Nullable Object key) {
        int h = this.keyEquivalence.hash(key);
        return LocalCache.rehash(h);
    }

    void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        this.segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    void reclaimKey(ReferenceEntry<K, V> entry) {
        int hash = entry.getHash();
        this.segmentFor(hash).reclaimKey(entry, hash);
    }

    @VisibleForTesting
    boolean isLive(ReferenceEntry<K, V> entry, long now) {
        return this.segmentFor(entry.getHash()).getLiveValue(entry, now) != null;
    }

    Segment<K, V> segmentFor(int hash) {
        return this.segments[hash >>> this.segmentShift & this.segmentMask];
    }

    Segment<K, V> createSegment(int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
        return new Segment(this, initialCapacity, maxSegmentWeight, statsCounter);
    }

    @Nullable V getLiveValue(ReferenceEntry<K, V> entry, long now) {
        if (entry.getKey() == null) {
            return null;
        }
        V value = entry.getValueReference().get();
        if (value == null) {
            return null;
        }
        if (this.isExpired(entry, now)) {
            return null;
        }
        return value;
    }

    boolean isExpired(ReferenceEntry<K, V> entry, long now) {
        Preconditions.checkNotNull(entry);
        if (this.expiresAfterAccess() && now - entry.getAccessTime() >= this.expireAfterAccessNanos) {
            return true;
        }
        return this.expiresAfterWrite() && now - entry.getWriteTime() >= this.expireAfterWriteNanos;
    }

    static <K, V> void connectAccessOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextInAccessQueue(next);
        next.setPreviousInAccessQueue(previous);
    }

    static <K, V> void nullifyAccessOrder(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = LocalCache.nullEntry();
        nulled.setNextInAccessQueue(nullEntry);
        nulled.setPreviousInAccessQueue(nullEntry);
    }

    static <K, V> void connectWriteOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextInWriteQueue(next);
        next.setPreviousInWriteQueue(previous);
    }

    static <K, V> void nullifyWriteOrder(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = LocalCache.nullEntry();
        nulled.setNextInWriteQueue(nullEntry);
        nulled.setPreviousInWriteQueue(nullEntry);
    }

    void processPendingNotifications() {
        RemovalNotification<K, V> notification;
        while ((notification = this.removalNotificationQueue.poll()) != null) {
            try {
                this.removalListener.onRemoval(notification);
            } catch (Throwable e) {
                logger.log(Level.WARNING, "Exception thrown by removal listener", e);
            }
        }
    }

    final Segment<K, V>[] newSegmentArray(int ssize) {
        return new Segment[ssize];
    }

    public void cleanUp() {
        for (Segment<K, V> segment : this.segments) {
            segment.cleanUp();
        }
    }

    @Override
    public boolean isEmpty() {
        int i;
        long sum = 0L;
        Segment<K, V>[] segments = this.segments;
        for (i = 0; i < segments.length; ++i) {
            if (segments[i].count != 0) {
                return false;
            }
            sum += (long)segments[i].modCount;
        }
        if (sum != 0L) {
            for (i = 0; i < segments.length; ++i) {
                if (segments[i].count != 0) {
                    return false;
                }
                sum -= (long)segments[i].modCount;
            }
            if (sum != 0L) {
                return false;
            }
        }
        return true;
    }

    long longSize() {
        Segment<K, V>[] segments = this.segments;
        long sum = 0L;
        for (int i = 0; i < segments.length; ++i) {
            sum += (long)Math.max(0, segments[i].count);
        }
        return sum;
    }

    @Override
    public int size() {
        return Ints.saturatedCast(this.longSize());
    }

    @Override
    public @Nullable V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).get(key, hash);
    }

    V get(K key, CacheLoader<? super K, V> loader) throws ExecutionException {
        int hash = this.hash(Preconditions.checkNotNull(key));
        return this.segmentFor(hash).get((K)key, hash, loader);
    }

    public @Nullable V getIfPresent(Object key) {
        int hash = this.hash(Preconditions.checkNotNull(key));
        V value = this.segmentFor(hash).get(key, hash);
        if (value == null) {
            this.globalStatsCounter.recordMisses(1);
        } else {
            this.globalStatsCounter.recordHits(1);
        }
        return value;
    }

    @Override
    public @Nullable V getOrDefault(@Nullable Object key, @Nullable V defaultValue) {
        V result = this.get(key);
        return result != null ? result : defaultValue;
    }

    V getOrLoad(K key) throws ExecutionException {
        return this.get(key, this.defaultLoader);
    }

    ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
        int hits = 0;
        int misses = 0;
        LinkedHashMap<?, V> result = Maps.newLinkedHashMap();
        for (Object key : keys) {
            V value = this.get(key);
            if (value == null) {
                ++misses;
                continue;
            }
            Object castKey = key;
            result.put(castKey, value);
            ++hits;
        }
        this.globalStatsCounter.recordHits(hits);
        this.globalStatsCounter.recordMisses(misses);
        return ImmutableMap.copyOf(result);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        int hits = 0;
        int misses = 0;
        LinkedHashMap<Object, V> result = Maps.newLinkedHashMap();
        LinkedHashSet<K> keysToLoad = Sets.newLinkedHashSet();
        for (K key : keys) {
            V value = this.get(key);
            if (result.containsKey(key)) continue;
            result.put(key, value);
            if (value == null) {
                ++misses;
                keysToLoad.add(key);
                continue;
            }
            ++hits;
        }
        try {
            if (!keysToLoad.isEmpty()) {
                try {
                    Map<K, V> newEntries = this.loadAll(keysToLoad, this.defaultLoader);
                    for (Object key : keysToLoad) {
                        V value = newEntries.get(key);
                        if (value == null) {
                            throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + key);
                        }
                        result.put(key, value);
                    }
                } catch (CacheLoader.UnsupportedLoadingOperationException e) {
                    for (Object key : keysToLoad) {
                        --misses;
                        result.put(key, this.get(key, this.defaultLoader));
                    }
                }
            }
            ImmutableMap immutableMap = ImmutableMap.copyOf(result);
            return immutableMap;
        } finally {
            this.globalStatsCounter.recordHits(hits);
            this.globalStatsCounter.recordMisses(misses);
        }
    }

    @Nullable Map<K, V> loadAll(Set<? extends K> keys, CacheLoader<? super K, V> loader) throws ExecutionException {
        Map<K, V> result;
        Preconditions.checkNotNull(loader);
        Preconditions.checkNotNull(keys);
        Stopwatch stopwatch = Stopwatch.createStarted();
        boolean success = false;
        try {
            Map<K, V> map;
            result = map = loader.loadAll(keys);
            success = true;
        } catch (CacheLoader.UnsupportedLoadingOperationException e) {
            success = true;
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e);
        } catch (RuntimeException e) {
            throw new UncheckedExecutionException(e);
        } catch (Exception e) {
            throw new ExecutionException(e);
        } catch (Error e) {
            throw new ExecutionError(e);
        } finally {
            if (!success) {
                this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            }
        }
        if (result == null) {
            this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(loader + " returned null map from loadAll");
        }
        stopwatch.stop();
        boolean nullsPresent = false;
        for (Map.Entry<K, V> entry : result.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (key == null || value == null) {
                nullsPresent = true;
                continue;
            }
            this.put(key, value);
        }
        if (nullsPresent) {
            this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(loader + " returned null keys or values from loadAll");
        }
        this.globalStatsCounter.recordLoadSuccess(stopwatch.elapsed(TimeUnit.NANOSECONDS));
        return result;
    }

    ReferenceEntry<K, V> getEntry(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).getEntry(key, hash);
    }

    void refresh(K key) {
        int hash = this.hash(Preconditions.checkNotNull(key));
        this.segmentFor(hash).refresh((K)key, hash, this.defaultLoader, false);
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        if (key == null) {
            return false;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).containsKey(key, hash);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        if (value == null) {
            return false;
        }
        long now = this.ticker.read();
        Segment<K, V>[] segments = this.segments;
        long last = -1L;
        for (int i = 0; i < 3; ++i) {
            long sum = 0L;
            for (Segment segment : segments) {
                int unused = segment.count;
                AtomicReferenceArray table = segment.table;
                for (int j = 0; j < table.length(); ++j) {
                    for (ReferenceEntry e = table.get(j); e != null; e = e.getNext()) {
                        V v = segment.getLiveValue(e, now);
                        if (v == null || !this.valueEquivalence.equivalent(value, v)) continue;
                        return true;
                    }
                }
                sum += (long)segment.modCount;
            }
            if (sum == last) break;
            last = sum;
        }
        return false;
    }

    @Override
    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = this.hash(key);
        return this.segmentFor(hash).put(key, hash, value, false);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = this.hash(key);
        return this.segmentFor(hash).put(key, hash, value, true);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(function);
        int hash = this.hash(key);
        return this.segmentFor(hash).compute((K)key, hash, (BiFunction<? super K, ? extends V, ? extends V>)function);
    }

    @Override
    public V computeIfAbsent(K key, java.util.function.Function<? super K, ? extends V> function) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(function);
        return (V)this.compute(key, (k, oldValue) -> oldValue == null ? function.apply((Object)key) : oldValue);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(function);
        return (V)this.compute(key, (k, oldValue) -> oldValue == null ? null : function.apply((Object)k, (Object)oldValue));
    }

    @Override
    public V merge(K key, V newValue, BiFunction<? super V, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        Preconditions.checkNotNull(function);
        return (V)this.compute(key, (k, oldValue) -> oldValue == null ? newValue : function.apply((Object)oldValue, (Object)newValue));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public V remove(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).remove(key, hash);
    }

    @Override
    public boolean remove(@Nullable Object key, @Nullable Object value) {
        if (key == null || value == null) {
            return false;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).remove(key, hash, value);
    }

    @Override
    public boolean replace(K key, @Nullable V oldValue, V newValue) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        if (oldValue == null) {
            return false;
        }
        int hash = this.hash(key);
        return this.segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = this.hash(key);
        return this.segmentFor(hash).replace(key, hash, value);
    }

    @Override
    public void clear() {
        for (Segment<K, V> segment : this.segments) {
            segment.clear();
        }
    }

    void invalidateAll(Iterable<?> keys) {
        for (Object key : keys) {
            this.remove(key);
        }
    }

    @Override
    public Set<K> keySet() {
        KeySet ks = this.keySet;
        return ks != null ? ks : (this.keySet = new KeySet(this));
    }

    @Override
    public Collection<V> values() {
        Values vs = this.values;
        return vs != null ? vs : (this.values = new Values(this));
    }

    @Override
    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet es = this.entrySet;
        return es != null ? es : (this.entrySet = new EntrySet(this));
    }

    private static <E> ArrayList<E> toArrayList(Collection<E> c) {
        ArrayList result = new ArrayList(c.size());
        Iterators.addAll(result, c.iterator());
        return result;
    }

    boolean removeIf(BiPredicate<? super K, ? super V> filter) {
        Preconditions.checkNotNull(filter);
        boolean changed = false;
        block0: for (K key : this.keySet()) {
            V value;
            while ((value = this.get(key)) != null && filter.test(key, value)) {
                if (!this.remove(key, value)) continue;
                changed = true;
                continue block0;
            }
        }
        return changed;
    }

    static class LocalLoadingCache<K, V>
    extends LocalManualCache<K, V>
    implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1L;

        LocalLoadingCache(CacheBuilder<? super K, ? super V> builder, CacheLoader<? super K, V> loader) {
            super(new LocalCache<K, V>(builder, Preconditions.checkNotNull(loader)));
        }

        @Override
        public V get(K key) throws ExecutionException {
            return this.localCache.getOrLoad(key);
        }

        @Override
        public V getUnchecked(K key) {
            try {
                return this.get(key);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        @Override
        public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
            return this.localCache.getAll(keys);
        }

        @Override
        public void refresh(K key) {
            this.localCache.refresh(key);
        }

        @Override
        public final V apply(K key) {
            return this.getUnchecked(key);
        }

        @Override
        Object writeReplace() {
            return new LoadingSerializationProxy(this.localCache);
        }
    }

    static class LocalManualCache<K, V>
    implements Cache<K, V>,
    Serializable {
        final LocalCache<K, V> localCache;
        private static final long serialVersionUID = 1L;

        LocalManualCache(CacheBuilder<? super K, ? super V> builder) {
            this(new LocalCache<K, V>(builder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }

        @Override
        public @Nullable V getIfPresent(Object key) {
            return this.localCache.getIfPresent(key);
        }

        @Override
        public V get(K key, final Callable<? extends V> valueLoader) throws ExecutionException {
            Preconditions.checkNotNull(valueLoader);
            return this.localCache.get(key, new CacheLoader<Object, V>(){

                @Override
                public V load(Object key) throws Exception {
                    return valueLoader.call();
                }
            });
        }

        @Override
        public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
            return this.localCache.getAllPresent(keys);
        }

        @Override
        public void put(K key, V value) {
            this.localCache.put(key, value);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            this.localCache.putAll(m);
        }

        @Override
        public void invalidate(Object key) {
            Preconditions.checkNotNull(key);
            this.localCache.remove(key);
        }

        @Override
        public void invalidateAll(Iterable<?> keys) {
            this.localCache.invalidateAll(keys);
        }

        @Override
        public void invalidateAll() {
            this.localCache.clear();
        }

        @Override
        public long size() {
            return this.localCache.longSize();
        }

        @Override
        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        @Override
        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter aggregator = new AbstractCache.SimpleStatsCounter();
            aggregator.incrementBy(this.localCache.globalStatsCounter);
            for (Segment segment : this.localCache.segments) {
                aggregator.incrementBy(segment.statsCounter);
            }
            return aggregator.snapshot();
        }

        @Override
        public void cleanUp() {
            this.localCache.cleanUp();
        }

        Object writeReplace() {
            return new ManualSerializationProxy<K, V>(this.localCache);
        }
    }

    static final class LoadingSerializationProxy<K, V>
    extends ManualSerializationProxy<K, V>
    implements LoadingCache<K, V>,
    Serializable {
        private static final long serialVersionUID = 1L;
        transient @MonotonicNonNull LoadingCache<K, V> autoDelegate;

        LoadingSerializationProxy(LocalCache<K, V> cache) {
            super(cache);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            CacheBuilder builder = this.recreateCacheBuilder();
            this.autoDelegate = builder.build(this.loader);
        }

        @Override
        public V get(K key) throws ExecutionException {
            return this.autoDelegate.get(key);
        }

        @Override
        public V getUnchecked(K key) {
            return this.autoDelegate.getUnchecked(key);
        }

        @Override
        public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
            return this.autoDelegate.getAll(keys);
        }

        @Override
        public final V apply(K key) {
            return this.autoDelegate.apply(key);
        }

        @Override
        public void refresh(K key) {
            this.autoDelegate.refresh(key);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }
    }

    static class ManualSerializationProxy<K, V>
    extends ForwardingCache<K, V>
    implements Serializable {
        private static final long serialVersionUID = 1L;
        final Strength keyStrength;
        final Strength valueStrength;
        final Equivalence<Object> keyEquivalence;
        final Equivalence<Object> valueEquivalence;
        final long expireAfterWriteNanos;
        final long expireAfterAccessNanos;
        final long maxWeight;
        final Weigher<K, V> weigher;
        final int concurrencyLevel;
        final RemovalListener<? super K, ? super V> removalListener;
        final @Nullable Ticker ticker;
        final CacheLoader<? super K, V> loader;
        transient @MonotonicNonNull Cache<K, V> delegate;

        ManualSerializationProxy(LocalCache<K, V> cache) {
            this(cache.keyStrength, cache.valueStrength, cache.keyEquivalence, cache.valueEquivalence, cache.expireAfterWriteNanos, cache.expireAfterAccessNanos, cache.maxWeight, cache.weigher, cache.concurrencyLevel, cache.removalListener, cache.ticker, cache.defaultLoader);
        }

        private ManualSerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, long maxWeight, Weigher<K, V> weigher, int concurrencyLevel, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> loader) {
            this.keyStrength = keyStrength;
            this.valueStrength = valueStrength;
            this.keyEquivalence = keyEquivalence;
            this.valueEquivalence = valueEquivalence;
            this.expireAfterWriteNanos = expireAfterWriteNanos;
            this.expireAfterAccessNanos = expireAfterAccessNanos;
            this.maxWeight = maxWeight;
            this.weigher = weigher;
            this.concurrencyLevel = concurrencyLevel;
            this.removalListener = removalListener;
            this.ticker = ticker == Ticker.systemTicker() || ticker == CacheBuilder.NULL_TICKER ? null : ticker;
            this.loader = loader;
        }

        CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K, V> builder = CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
            builder.strictParsing = false;
            if (this.expireAfterWriteNanos > 0L) {
                builder.expireAfterWrite(this.expireAfterWriteNanos, TimeUnit.NANOSECONDS);
            }
            if (this.expireAfterAccessNanos > 0L) {
                builder.expireAfterAccess(this.expireAfterAccessNanos, TimeUnit.NANOSECONDS);
            }
            if (this.weigher != CacheBuilder.OneWeigher.INSTANCE) {
                builder.weigher(this.weigher);
                if (this.maxWeight != -1L) {
                    builder.maximumWeight(this.maxWeight);
                }
            } else if (this.maxWeight != -1L) {
                builder.maximumSize(this.maxWeight);
            }
            if (this.ticker != null) {
                builder.ticker(this.ticker);
            }
            return builder;
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            CacheBuilder<K, V> builder = this.recreateCacheBuilder();
            this.delegate = builder.build();
        }

        private Object readResolve() {
            return this.delegate;
        }

        @Override
        protected Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    final class EntrySet
    extends AbstractCacheSet<Map.Entry<K, V>> {
        EntrySet(ConcurrentMap<?, ?> map) {
            super(map);
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean removeIf(Predicate<? super Map.Entry<K, V>> filter) {
            Preconditions.checkNotNull(filter);
            return LocalCache.this.removeIf((? super K k, ? super V v) -> filter.test(Maps.immutableEntry(k, v)));
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry)o;
            Object key = e.getKey();
            if (key == null) {
                return false;
            }
            Object v = LocalCache.this.get(key);
            return v != null && LocalCache.this.valueEquivalence.equivalent(e.getValue(), v);
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry)o;
            Object key = e.getKey();
            return key != null && LocalCache.this.remove(key, e.getValue());
        }
    }

    final class Values
    extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> map;

        Values(ConcurrentMap<?, ?> map) {
            this.map = map;
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public void clear() {
            this.map.clear();
        }

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public boolean removeIf(Predicate<? super V> filter) {
            Preconditions.checkNotNull(filter);
            return LocalCache.this.removeIf((? super K k, ? super V v) -> filter.test((Object)v));
        }

        @Override
        public boolean contains(Object o) {
            return this.map.containsValue(o);
        }

        @Override
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override
        public <E> E[] toArray(E[] a) {
            return LocalCache.toArrayList(this).toArray(a);
        }
    }

    final class KeySet
    extends AbstractCacheSet<K> {
        KeySet(ConcurrentMap<?, ?> map) {
            super(map);
        }

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean contains(Object o) {
            return this.map.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            return this.map.remove(o) != null;
        }
    }

    abstract class AbstractCacheSet<T>
    extends AbstractSet<T> {
        @Weak
        final ConcurrentMap<?, ?> map;

        AbstractCacheSet(ConcurrentMap<?, ?> map) {
            this.map = map;
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public void clear() {
            this.map.clear();
        }

        @Override
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override
        public <E> E[] toArray(E[] a) {
            return LocalCache.toArrayList(this).toArray(a);
        }
    }

    final class EntryIterator
    extends HashIterator<Map.Entry<K, V>> {
        EntryIterator() {
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    final class WriteThroughEntry
    implements Map.Entry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object instanceof Map.Entry) {
                Map.Entry that = (Map.Entry)object;
                return this.key.equals(that.getKey()) && this.value.equals(that.getValue());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override
        public V setValue(V newValue) {
            Object oldValue = LocalCache.this.put(this.key, newValue);
            this.value = newValue;
            return oldValue;
        }

        public String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class ValueIterator
    extends HashIterator<V> {
        ValueIterator() {
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    final class KeyIterator
    extends HashIterator<K> {
        KeyIterator() {
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    abstract class HashIterator<T>
    implements Iterator<T> {
        int nextSegmentIndex;
        int nextTableIndex;
        @MonotonicNonNull Segment<K, V> currentSegment;
        @MonotonicNonNull AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
        @Nullable ReferenceEntry<K, V> nextEntry;
        @Nullable WriteThroughEntry nextExternal;
        @Nullable WriteThroughEntry lastReturned;

        HashIterator() {
            this.nextSegmentIndex = LocalCache.this.segments.length - 1;
            this.nextTableIndex = -1;
            this.advance();
        }

        @Override
        public abstract T next();

        final void advance() {
            this.nextExternal = null;
            if (this.nextInChain()) {
                return;
            }
            if (this.nextInTable()) {
                return;
            }
            while (this.nextSegmentIndex >= 0) {
                this.currentSegment = LocalCache.this.segments[this.nextSegmentIndex--];
                if (this.currentSegment.count == 0) continue;
                this.currentTable = this.currentSegment.table;
                this.nextTableIndex = this.currentTable.length() - 1;
                if (!this.nextInTable()) continue;
                return;
            }
        }

        boolean nextInChain() {
            if (this.nextEntry != null) {
                this.nextEntry = this.nextEntry.getNext();
                while (this.nextEntry != null) {
                    if (this.advanceTo(this.nextEntry)) {
                        return true;
                    }
                    this.nextEntry = this.nextEntry.getNext();
                }
            }
            return false;
        }

        boolean nextInTable() {
            while (this.nextTableIndex >= 0) {
                if ((this.nextEntry = this.currentTable.get(this.nextTableIndex--)) == null || !this.advanceTo(this.nextEntry) && !this.nextInChain()) continue;
                return true;
            }
            return false;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean advanceTo(ReferenceEntry<K, V> entry) {
            try {
                long now = LocalCache.this.ticker.read();
                Object key = entry.getKey();
                Object value = LocalCache.this.getLiveValue(entry, now);
                if (value != null) {
                    this.nextExternal = new WriteThroughEntry(key, value);
                    boolean bl = true;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.currentSegment.postReadCleanup();
            }
        }

        @Override
        public boolean hasNext() {
            return this.nextExternal != null;
        }

        WriteThroughEntry nextEntry() {
            if (this.nextExternal == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = this.nextExternal;
            this.advance();
            return this.lastReturned;
        }

        @Override
        public void remove() {
            Preconditions.checkState(this.lastReturned != null);
            LocalCache.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    static final class AccessQueue<K, V>
    extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>(){
            ReferenceEntry<K, V> nextAccess = this;
            ReferenceEntry<K, V> previousAccess = this;

            @Override
            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            @Override
            public void setAccessTime(long time) {
            }

            @Override
            public ReferenceEntry<K, V> getNextInAccessQueue() {
                return this.nextAccess;
            }

            @Override
            public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
                this.nextAccess = next;
            }

            @Override
            public ReferenceEntry<K, V> getPreviousInAccessQueue() {
                return this.previousAccess;
            }

            @Override
            public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
                this.previousAccess = previous;
            }
        };

        AccessQueue() {
        }

        @Override
        public boolean offer(ReferenceEntry<K, V> entry) {
            LocalCache.connectAccessOrder(entry.getPreviousInAccessQueue(), entry.getNextInAccessQueue());
            LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), entry);
            LocalCache.connectAccessOrder(entry, this.head);
            return true;
        }

        @Override
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
            return next == this.head ? null : next;
        }

        @Override
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
            if (next == this.head) {
                return null;
            }
            this.remove(next);
            return next;
        }

        @Override
        public boolean remove(Object o) {
            ReferenceEntry e = (ReferenceEntry)o;
            ReferenceEntry previous = e.getPreviousInAccessQueue();
            ReferenceEntry next = e.getNextInAccessQueue();
            LocalCache.connectAccessOrder(previous, next);
            LocalCache.nullifyAccessOrder(e);
            return next != NullEntry.INSTANCE;
        }

        @Override
        public boolean contains(Object o) {
            ReferenceEntry e = (ReferenceEntry)o;
            return e.getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        @Override
        public boolean isEmpty() {
            return this.head.getNextInAccessQueue() == this.head;
        }

        @Override
        public int size() {
            int size = 0;
            for (ReferenceEntry<K, V> e = this.head.getNextInAccessQueue(); e != this.head; e = e.getNextInAccessQueue()) {
                ++size;
            }
            return size;
        }

        @Override
        public void clear() {
            ReferenceEntry<K, V> e = this.head.getNextInAccessQueue();
            while (e != this.head) {
                ReferenceEntry<K, V> next = e.getNextInAccessQueue();
                LocalCache.nullifyAccessOrder(e);
                e = next;
            }
            this.head.setNextInAccessQueue(this.head);
            this.head.setPreviousInAccessQueue(this.head);
        }

        @Override
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>((ReferenceEntry)this.peek()){

                @Override
                protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                    ReferenceEntry next = previous.getNextInAccessQueue();
                    return next == head ? null : next;
                }
            };
        }
    }

    static final class WriteQueue<K, V>
    extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>(){
            ReferenceEntry<K, V> nextWrite = this;
            ReferenceEntry<K, V> previousWrite = this;

            @Override
            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            @Override
            public void setWriteTime(long time) {
            }

            @Override
            public ReferenceEntry<K, V> getNextInWriteQueue() {
                return this.nextWrite;
            }

            @Override
            public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
                this.nextWrite = next;
            }

            @Override
            public ReferenceEntry<K, V> getPreviousInWriteQueue() {
                return this.previousWrite;
            }

            @Override
            public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
                this.previousWrite = previous;
            }
        };

        WriteQueue() {
        }

        @Override
        public boolean offer(ReferenceEntry<K, V> entry) {
            LocalCache.connectWriteOrder(entry.getPreviousInWriteQueue(), entry.getNextInWriteQueue());
            LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), entry);
            LocalCache.connectWriteOrder(entry, this.head);
            return true;
        }

        @Override
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
            return next == this.head ? null : next;
        }

        @Override
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
            if (next == this.head) {
                return null;
            }
            this.remove(next);
            return next;
        }

        @Override
        public boolean remove(Object o) {
            ReferenceEntry e = (ReferenceEntry)o;
            ReferenceEntry previous = e.getPreviousInWriteQueue();
            ReferenceEntry next = e.getNextInWriteQueue();
            LocalCache.connectWriteOrder(previous, next);
            LocalCache.nullifyWriteOrder(e);
            return next != NullEntry.INSTANCE;
        }

        @Override
        public boolean contains(Object o) {
            ReferenceEntry e = (ReferenceEntry)o;
            return e.getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        @Override
        public boolean isEmpty() {
            return this.head.getNextInWriteQueue() == this.head;
        }

        @Override
        public int size() {
            int size = 0;
            for (ReferenceEntry<K, V> e = this.head.getNextInWriteQueue(); e != this.head; e = e.getNextInWriteQueue()) {
                ++size;
            }
            return size;
        }

        @Override
        public void clear() {
            ReferenceEntry<K, V> e = this.head.getNextInWriteQueue();
            while (e != this.head) {
                ReferenceEntry<K, V> next = e.getNextInWriteQueue();
                LocalCache.nullifyWriteOrder(e);
                e = next;
            }
            this.head.setNextInWriteQueue(this.head);
            this.head.setPreviousInWriteQueue(this.head);
        }

        @Override
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>((ReferenceEntry)this.peek()){

                @Override
                protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                    ReferenceEntry next = previous.getNextInWriteQueue();
                    return next == head ? null : next;
                }
            };
        }
    }

    static class LoadingValueReference<K, V>
    implements ValueReference<K, V> {
        volatile ValueReference<K, V> oldValue;
        final SettableFuture<V> futureValue = SettableFuture.create();
        final Stopwatch stopwatch = Stopwatch.createUnstarted();

        public LoadingValueReference() {
            this(null);
        }

        public LoadingValueReference(ValueReference<K, V> oldValue) {
            this.oldValue = oldValue == null ? LocalCache.unset() : oldValue;
        }

        @Override
        public boolean isLoading() {
            return true;
        }

        @Override
        public boolean isActive() {
            return this.oldValue.isActive();
        }

        @Override
        public int getWeight() {
            return this.oldValue.getWeight();
        }

        public boolean set(@Nullable V newValue) {
            return this.futureValue.set(newValue);
        }

        public boolean setException(Throwable t) {
            return this.futureValue.setException(t);
        }

        private ListenableFuture<V> fullyFailedFuture(Throwable t) {
            return Futures.immediateFailedFuture(t);
        }

        @Override
        public void notifyNewValue(@Nullable V newValue) {
            if (newValue != null) {
                this.set(newValue);
            } else {
                this.oldValue = LocalCache.unset();
            }
        }

        public ListenableFuture<V> loadFuture(K key, CacheLoader<? super K, V> loader) {
            try {
                this.stopwatch.start();
                V previousValue = this.oldValue.get();
                if (previousValue == null) {
                    V newValue = loader.load(key);
                    return this.set(newValue) ? this.futureValue : Futures.immediateFuture(newValue);
                }
                ListenableFuture<V> newValue = loader.reload(key, previousValue);
                if (newValue == null) {
                    return Futures.immediateFuture(null);
                }
                return Futures.transform(newValue, new Function<V, V>(){

                    @Override
                    public V apply(V newValue) {
                        this.set(newValue);
                        return newValue;
                    }
                }, MoreExecutors.directExecutor());
            } catch (Throwable t) {
                ListenableFuture<V> result;
                ListenableFuture<V> listenableFuture = result = this.setException(t) ? this.futureValue : this.fullyFailedFuture(t);
                if (t instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return result;
            }
        }

        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> function) {
            V newValue;
            V previousValue;
            this.stopwatch.start();
            try {
                previousValue = this.oldValue.waitForValue();
            } catch (ExecutionException e) {
                previousValue = null;
            }
            try {
                newValue = function.apply(key, previousValue);
            } catch (Throwable th) {
                this.setException(th);
                throw th;
            }
            this.set(newValue);
            return newValue;
        }

        public long elapsedNanos() {
            return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
        }

        @Override
        public V waitForValue() throws ExecutionException {
            return Uninterruptibles.getUninterruptibly(this.futureValue);
        }

        @Override
        public V get() {
            return this.oldValue.get();
        }

        public ValueReference<K, V> getOldValue() {
            return this.oldValue;
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, @Nullable V value, ReferenceEntry<K, V> entry) {
            return this;
        }
    }

    static class Segment<K, V>
    extends ReentrantLock {
        @Weak
        final LocalCache<K, V> map;
        volatile int count;
        @GuardedBy(value="this")
        long totalWeight;
        int modCount;
        int threshold;
        volatile @MonotonicNonNull AtomicReferenceArray<ReferenceEntry<K, V>> table;
        final long maxSegmentWeight;
        final @Nullable ReferenceQueue<K> keyReferenceQueue;
        final @Nullable ReferenceQueue<V> valueReferenceQueue;
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        final AtomicInteger readCount = new AtomicInteger();
        @GuardedBy(value="this")
        final Queue<ReferenceEntry<K, V>> writeQueue;
        @GuardedBy(value="this")
        final Queue<ReferenceEntry<K, V>> accessQueue;
        final AbstractCache.StatsCounter statsCounter;

        Segment(LocalCache<K, V> map, int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
            this.map = map;
            this.maxSegmentWeight = maxSegmentWeight;
            this.statsCounter = Preconditions.checkNotNull(statsCounter);
            this.initTable(this.newEntryArray(initialCapacity));
            this.keyReferenceQueue = map.usesKeyReferences() ? new ReferenceQueue() : null;
            this.valueReferenceQueue = map.usesValueReferences() ? new ReferenceQueue() : null;
            this.recencyQueue = map.usesAccessQueue() ? new ConcurrentLinkedQueue() : LocalCache.discardingQueue();
            this.writeQueue = map.usesWriteQueue() ? new WriteQueue() : LocalCache.discardingQueue();
            this.accessQueue = map.usesAccessQueue() ? new AccessQueue() : LocalCache.discardingQueue();
        }

        AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int size) {
            return new AtomicReferenceArray<ReferenceEntry<K, V>>(size);
        }

        void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> newTable) {
            this.threshold = newTable.length() * 3 / 4;
            if (!this.map.customWeigher() && (long)this.threshold == this.maxSegmentWeight) {
                ++this.threshold;
            }
            this.table = newTable;
        }

        @GuardedBy(value="this")
        ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(key), hash, next);
        }

        @GuardedBy(value="this")
        ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            if (original.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = valueReference.get();
            if (value == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
            newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
            return newEntry;
        }

        @GuardedBy(value="this")
        void setValue(ReferenceEntry<K, V> entry, K key, V value, long now) {
            ValueReference<K, V> previous = entry.getValueReference();
            int weight = this.map.weigher.weigh(key, value);
            Preconditions.checkState(weight >= 0, "Weights must be non-negative");
            ValueReference<K, V> valueReference = this.map.valueStrength.referenceValue(this, entry, value, weight);
            entry.setValueReference(valueReference);
            this.recordWrite(entry, weight, now);
            previous.notifyNewValue(value);
        }

        V get(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(loader);
            try {
                ReferenceEntry<K, V> e;
                if (this.count != 0 && (e = this.getEntry(key, hash)) != null) {
                    long now = this.map.ticker.read();
                    V value = this.getLiveValue(e, now);
                    if (value != null) {
                        this.recordRead(e, now);
                        this.statsCounter.recordHits(1);
                        V v = this.scheduleRefresh(e, key, hash, value, now, loader);
                        return v;
                    }
                    ValueReference<K, V> valueReference = e.getValueReference();
                    if (valueReference.isLoading()) {
                        V v = this.waitForLoadingValue(e, key, valueReference);
                        return v;
                    }
                }
                e = this.lockedGetOrLoad(key, hash, loader);
                return (V)e;
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error)cause);
                }
                if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                }
                throw ee;
            } finally {
                this.postReadCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable V get(Object key, int hash) {
            try {
                if (this.count != 0) {
                    long now = this.map.ticker.read();
                    ReferenceEntry<K, V> e = this.getLiveEntry(key, hash, now);
                    if (e == null) {
                        V v = null;
                        return v;
                    }
                    V value = e.getValueReference().get();
                    if (value != null) {
                        this.recordRead(e, now);
                        V v = this.scheduleRefresh(e, e.getKey(), hash, value, now, this.map.defaultLoader);
                        return v;
                    }
                    this.tryDrainReferenceQueues();
                }
                V v = null;
                return v;
            } finally {
                this.postReadCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        V lockedGetOrLoad(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
            ReferenceEntry e;
            ValueReference<K, V> valueReference = null;
            LoadingValueReference loadingValueReference = null;
            boolean createNewEntry = true;
            this.lock();
            try {
                ReferenceEntry first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    valueReference = e.getValueReference();
                    if (valueReference.isLoading()) {
                        createNewEntry = false;
                        break;
                    }
                    V value = valueReference.get();
                    if (value == null) {
                        this.enqueueNotification(entryKey, hash, value, valueReference.getWeight(), RemovalCause.COLLECTED);
                    } else if (this.map.isExpired(e, now)) {
                        this.enqueueNotification(entryKey, hash, value, valueReference.getWeight(), RemovalCause.EXPIRED);
                    } else {
                        this.recordLockedRead(e, now);
                        this.statsCounter.recordHits(1);
                        V v = value;
                        return v;
                    }
                    this.writeQueue.remove(e);
                    this.accessQueue.remove(e);
                    this.count = newCount;
                    break;
                }
                if (createNewEntry) {
                    loadingValueReference = new LoadingValueReference();
                    if (e == null) {
                        e = this.newEntry(key, hash, first);
                        e.setValueReference(loadingValueReference);
                        table.set(index, e);
                    } else {
                        e.setValueReference(loadingValueReference);
                    }
                }
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
            if (createNewEntry) {
                try {
                    ReferenceEntry referenceEntry = e;
                    synchronized (referenceEntry) {
                        Object v = this.loadSync(key, hash, loadingValueReference, loader);
                        return v;
                    }
                } finally {
                    this.statsCounter.recordMisses(1);
                }
            }
            return this.waitForLoadingValue(e, key, valueReference);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        V waitForLoadingValue(ReferenceEntry<K, V> e, K key, ValueReference<K, V> valueReference) throws ExecutionException {
            if (!valueReference.isLoading()) {
                throw new AssertionError();
            }
            Preconditions.checkState(!Thread.holdsLock(e), "Recursive load of: %s", key);
            try {
                V value = valueReference.waitForValue();
                if (value == null) {
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
                }
                long now = this.map.ticker.read();
                this.recordRead(e, now);
                V v = value;
                return v;
            } finally {
                this.statsCounter.recordMisses(1);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        V compute(K key, int hash, BiFunction<? super K, ? super V, ? extends V> function) {
            ValueReference<K, V> valueReference = null;
            LoadingValueReference<? super K, Object> loadingValueReference = null;
            boolean createNewEntry = true;
            this.lock();
            try {
                V v;
                Object entryKey;
                ReferenceEntry<K, V> first;
                ReferenceEntry<K, V> e;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (e = first = table.get(index); e != null; e = e.getNext()) {
                    entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    valueReference = e.getValueReference();
                    if (this.map.isExpired(e, now)) {
                        this.enqueueNotification(entryKey, hash, valueReference.get(), valueReference.getWeight(), RemovalCause.EXPIRED);
                    }
                    this.writeQueue.remove(e);
                    this.accessQueue.remove(e);
                    createNewEntry = false;
                    break;
                }
                loadingValueReference = new LoadingValueReference<K, Object>(valueReference);
                if (e == null) {
                    createNewEntry = true;
                    e = this.newEntry(key, hash, first);
                    e.setValueReference(loadingValueReference);
                    table.set(index, e);
                } else {
                    e.setValueReference(loadingValueReference);
                }
                V newValue = loadingValueReference.compute((K)key, (BiFunction<? super K, Object, Object>)function);
                if (newValue != null) {
                    if (valueReference != null && newValue == valueReference.get()) {
                        loadingValueReference.set(newValue);
                        e.setValueReference(valueReference);
                        this.recordWrite(e, 0, now);
                        entryKey = newValue;
                        return (V)entryKey;
                    }
                    entryKey = this.getAndRecordStats(key, hash, loadingValueReference, Futures.immediateFuture(newValue));
                    return (V)entryKey;
                }
                if (createNewEntry) {
                    this.removeLoadingValue(key, hash, loadingValueReference);
                    v = null;
                    return v;
                }
                this.removeEntry(e, hash, RemovalCause.EXPLICIT);
                v = null;
                return v;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        V loadSync(K key, int hash, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) throws ExecutionException {
            ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture((K)key, loader);
            return this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
        }

        ListenableFuture<V> loadAsync(final K key, final int hash, final LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) {
            final ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture((K)key, loader);
            loadingFuture.addListener(new Runnable(){

                @Override
                public void run() {
                    try {
                        this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
                    } catch (Throwable t) {
                        logger.log(Level.WARNING, "Exception thrown during refresh", t);
                        loadingValueReference.setException(t);
                    }
                }
            }, MoreExecutors.directExecutor());
            return loadingFuture;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        V getAndRecordStats(K key, int hash, LoadingValueReference<K, V> loadingValueReference, ListenableFuture<V> newValue) throws ExecutionException {
            V value = null;
            try {
                value = Uninterruptibles.getUninterruptibly(newValue);
                if (value == null) {
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
                }
                this.statsCounter.recordLoadSuccess(loadingValueReference.elapsedNanos());
                this.storeLoadedValue(key, hash, loadingValueReference, value);
                V v = value;
                return v;
            } finally {
                if (value == null) {
                    this.statsCounter.recordLoadException(loadingValueReference.elapsedNanos());
                    this.removeLoadingValue(key, hash, loadingValueReference);
                }
            }
        }

        V scheduleRefresh(ReferenceEntry<K, V> entry, K key, int hash, V oldValue, long now, CacheLoader<? super K, V> loader) {
            V newValue;
            if (this.map.refreshes() && now - entry.getWriteTime() > this.map.refreshNanos && !entry.getValueReference().isLoading() && (newValue = this.refresh(key, hash, loader, true)) != null) {
                return newValue;
            }
            return oldValue;
        }

        @Nullable V refresh(K key, int hash, CacheLoader<? super K, V> loader, boolean checkTime) {
            LoadingValueReference<K, V> loadingValueReference = this.insertLoadingValueReference(key, hash, checkTime);
            if (loadingValueReference == null) {
                return null;
            }
            ListenableFuture<V> result = this.loadAsync(key, hash, loadingValueReference, loader);
            if (result.isDone()) {
                try {
                    return Uninterruptibles.getUninterruptibly(result);
                } catch (Throwable throwable) {
                    // empty catch block
                }
            }
            return null;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable LoadingValueReference<K, V> insertLoadingValueReference(K key, int hash, boolean checkTime) {
            ReferenceEntry e = null;
            this.lock();
            try {
                ReferenceEntry first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    if (valueReference.isLoading() || checkTime && now - e.getWriteTime() < this.map.refreshNanos) {
                        LoadingValueReference<K, V> loadingValueReference = null;
                        return loadingValueReference;
                    }
                    ++this.modCount;
                    LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference<K, V>(valueReference);
                    e.setValueReference(loadingValueReference);
                    LoadingValueReference<K, V> loadingValueReference2 = loadingValueReference;
                    return loadingValueReference2;
                }
                ++this.modCount;
                LoadingValueReference loadingValueReference = new LoadingValueReference();
                e = this.newEntry(key, hash, first);
                e.setValueReference(loadingValueReference);
                table.set(index, e);
                LoadingValueReference loadingValueReference3 = loadingValueReference;
                return loadingValueReference3;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        void tryDrainReferenceQueues() {
            if (this.tryLock()) {
                try {
                    this.drainReferenceQueues();
                } finally {
                    this.unlock();
                }
            }
        }

        @GuardedBy(value="this")
        void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                this.drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                this.drainValueReferenceQueue();
            }
        }

        @GuardedBy(value="this")
        void drainKeyReferenceQueue() {
            Reference<K> ref;
            int i = 0;
            while ((ref = this.keyReferenceQueue.poll()) != null) {
                ReferenceEntry entry = (ReferenceEntry)((Object)ref);
                this.map.reclaimKey(entry);
                if (++i != 16) continue;
                break;
            }
        }

        @GuardedBy(value="this")
        void drainValueReferenceQueue() {
            Reference<V> ref;
            int i = 0;
            while ((ref = this.valueReferenceQueue.poll()) != null) {
                ValueReference valueReference = (ValueReference)((Object)ref);
                this.map.reclaimValue(valueReference);
                if (++i != 16) continue;
                break;
            }
        }

        void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                this.clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                this.clearValueReferenceQueue();
            }
        }

        void clearKeyReferenceQueue() {
            while (this.keyReferenceQueue.poll() != null) {
            }
        }

        void clearValueReferenceQueue() {
            while (this.valueReferenceQueue.poll() != null) {
            }
        }

        void recordRead(ReferenceEntry<K, V> entry, long now) {
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            this.recencyQueue.add(entry);
        }

        @GuardedBy(value="this")
        void recordLockedRead(ReferenceEntry<K, V> entry, long now) {
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            this.accessQueue.add(entry);
        }

        @GuardedBy(value="this")
        void recordWrite(ReferenceEntry<K, V> entry, int weight, long now) {
            this.drainRecencyQueue();
            this.totalWeight += (long)weight;
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            if (this.map.recordsWrite()) {
                entry.setWriteTime(now);
            }
            this.accessQueue.add(entry);
            this.writeQueue.add(entry);
        }

        @GuardedBy(value="this")
        void drainRecencyQueue() {
            ReferenceEntry<K, V> e;
            while ((e = this.recencyQueue.poll()) != null) {
                if (!this.accessQueue.contains(e)) continue;
                this.accessQueue.add(e);
            }
        }

        void tryExpireEntries(long now) {
            if (this.tryLock()) {
                try {
                    this.expireEntries(now);
                } finally {
                    this.unlock();
                }
            }
        }

        @GuardedBy(value="this")
        void expireEntries(long now) {
            ReferenceEntry<K, V> e;
            this.drainRecencyQueue();
            while ((e = this.writeQueue.peek()) != null && this.map.isExpired(e, now)) {
                if (!this.removeEntry(e, e.getHash(), RemovalCause.EXPIRED)) {
                    throw new AssertionError();
                }
            }
            while ((e = this.accessQueue.peek()) != null && this.map.isExpired(e, now)) {
                if (!this.removeEntry(e, e.getHash(), RemovalCause.EXPIRED)) {
                    throw new AssertionError();
                }
            }
        }

        @GuardedBy(value="this")
        void enqueueNotification(@Nullable K key, int hash, @Nullable V value, int weight, RemovalCause cause) {
            this.totalWeight -= (long)weight;
            if (cause.wasEvicted()) {
                this.statsCounter.recordEviction();
            }
            if (this.map.removalNotificationQueue != DISCARDING_QUEUE) {
                RemovalNotification<K, V> notification = RemovalNotification.create(key, value, cause);
                this.map.removalNotificationQueue.offer(notification);
            }
        }

        @GuardedBy(value="this")
        void evictEntries(ReferenceEntry<K, V> newest) {
            if (!this.map.evictsBySize()) {
                return;
            }
            this.drainRecencyQueue();
            if ((long)newest.getValueReference().getWeight() > this.maxSegmentWeight && !this.removeEntry(newest, newest.getHash(), RemovalCause.SIZE)) {
                throw new AssertionError();
            }
            while (this.totalWeight > this.maxSegmentWeight) {
                ReferenceEntry<K, V> e = this.getNextEvictable();
                if (!this.removeEntry(e, e.getHash(), RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
            }
        }

        @GuardedBy(value="this")
        ReferenceEntry<K, V> getNextEvictable() {
            for (ReferenceEntry referenceEntry : this.accessQueue) {
                int weight = referenceEntry.getValueReference().getWeight();
                if (weight <= 0) continue;
                return referenceEntry;
            }
            throw new AssertionError();
        }

        ReferenceEntry<K, V> getFirst(int hash) {
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            return table.get(hash & table.length() - 1);
        }

        @Nullable ReferenceEntry<K, V> getEntry(Object key, int hash) {
            for (ReferenceEntry<K, V> e = this.getFirst(hash); e != null; e = e.getNext()) {
                if (e.getHash() != hash) continue;
                K entryKey = e.getKey();
                if (entryKey == null) {
                    this.tryDrainReferenceQueues();
                    continue;
                }
                if (!this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                return e;
            }
            return null;
        }

        @Nullable ReferenceEntry<K, V> getLiveEntry(Object key, int hash, long now) {
            ReferenceEntry<K, V> e = this.getEntry(key, hash);
            if (e == null) {
                return null;
            }
            if (this.map.isExpired(e, now)) {
                this.tryExpireEntries(now);
                return null;
            }
            return e;
        }

        V getLiveValue(ReferenceEntry<K, V> entry, long now) {
            if (entry.getKey() == null) {
                this.tryDrainReferenceQueues();
                return null;
            }
            V value = entry.getValueReference().get();
            if (value == null) {
                this.tryDrainReferenceQueues();
                return null;
            }
            if (this.map.isExpired(entry, now)) {
                this.tryExpireEntries(now);
                return null;
            }
            return value;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean containsKey(Object key, int hash) {
            try {
                if (this.count != 0) {
                    long now = this.map.ticker.read();
                    ReferenceEntry<K, V> e = this.getLiveEntry(key, hash, now);
                    if (e == null) {
                        boolean bl = false;
                        return bl;
                    }
                    boolean bl = e.getValueReference().get() != null;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.postReadCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @VisibleForTesting
        boolean containsValue(Object value) {
            try {
                if (this.count != 0) {
                    long now = this.map.ticker.read();
                    AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                    int length = table.length();
                    for (int i = 0; i < length; ++i) {
                        for (ReferenceEntry<K, V> e = table.get(i); e != null; e = e.getNext()) {
                            V entryValue = this.getLiveValue(e, now);
                            if (entryValue == null || !this.map.valueEquivalence.equivalent(value, entryValue)) continue;
                            boolean bl = true;
                            return bl;
                        }
                    }
                }
                boolean bl = false;
                return bl;
            } finally {
                this.postReadCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable V put(K key, int hash, V value, boolean onlyIfAbsent) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                int newCount = this.count + 1;
                if (newCount > this.threshold) {
                    this.expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (entryValue == null) {
                        ++this.modCount;
                        if (valueReference.isActive()) {
                            this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.COLLECTED);
                            this.setValue(e, key, value, now);
                            newCount = this.count;
                        } else {
                            this.setValue(e, key, value, now);
                            newCount = this.count + 1;
                        }
                        this.count = newCount;
                        this.evictEntries(e);
                        V v = null;
                        return v;
                    }
                    if (onlyIfAbsent) {
                        this.recordLockedRead(e, now);
                        V v = entryValue;
                        return v;
                    }
                    ++this.modCount;
                    this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                    this.setValue(e, key, value, now);
                    this.evictEntries(e);
                    V v = entryValue;
                    return v;
                }
                ++this.modCount;
                ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
                this.setValue(newEntry, key, value, now);
                table.set(index, newEntry);
                this.count = newCount = this.count + 1;
                this.evictEntries(newEntry);
                V v = null;
                return v;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        @GuardedBy(value="this")
        void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> oldTable = this.table;
            int oldCapacity = oldTable.length();
            if (oldCapacity >= 0x40000000) {
                return;
            }
            int newCount = this.count;
            AtomicReferenceArray<ReferenceEntry<K, V>> newTable = this.newEntryArray(oldCapacity << 1);
            this.threshold = newTable.length() * 3 / 4;
            int newMask = newTable.length() - 1;
            for (int oldIndex = 0; oldIndex < oldCapacity; ++oldIndex) {
                int newIndex;
                ReferenceEntry<K, V> e;
                ReferenceEntry<K, V> head = oldTable.get(oldIndex);
                if (head == null) continue;
                ReferenceEntry<K, V> next = head.getNext();
                int headIndex = head.getHash() & newMask;
                if (next == null) {
                    newTable.set(headIndex, head);
                    continue;
                }
                ReferenceEntry<K, V> tail = head;
                int tailIndex = headIndex;
                for (e = next; e != null; e = e.getNext()) {
                    newIndex = e.getHash() & newMask;
                    if (newIndex == tailIndex) continue;
                    tailIndex = newIndex;
                    tail = e;
                }
                newTable.set(tailIndex, tail);
                for (e = head; e != tail; e = e.getNext()) {
                    newIndex = e.getHash() & newMask;
                    ReferenceEntry<K, V> newNext = newTable.get(newIndex);
                    ReferenceEntry<K, V> newFirst = this.copyEntry(e, newNext);
                    if (newFirst != null) {
                        newTable.set(newIndex, newFirst);
                        continue;
                    }
                    this.removeCollectedEntry(e);
                    --newCount;
                }
            }
            this.table = newTable;
            this.count = newCount;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean replace(K key, int hash, V oldValue, V newValue) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (entryValue == null) {
                        if (valueReference.isActive()) {
                            int newCount = this.count - 1;
                            ++this.modCount;
                            ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, RemovalCause.COLLECTED);
                            newCount = this.count - 1;
                            table.set(index, newFirst);
                            this.count = newCount;
                        }
                        boolean bl = false;
                        return bl;
                    }
                    if (this.map.valueEquivalence.equivalent(oldValue, entryValue)) {
                        ++this.modCount;
                        this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                        this.setValue(e, key, newValue, now);
                        this.evictEntries(e);
                        boolean bl = true;
                        return bl;
                    }
                    this.recordLockedRead(e, now);
                    boolean bl = false;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable V replace(K key, int hash, V newValue) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (entryValue == null) {
                        if (valueReference.isActive()) {
                            int newCount = this.count - 1;
                            ++this.modCount;
                            ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, RemovalCause.COLLECTED);
                            newCount = this.count - 1;
                            table.set(index, newFirst);
                            this.count = newCount;
                        }
                        V v = null;
                        return v;
                    }
                    ++this.modCount;
                    this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                    this.setValue(e, key, newValue, now);
                    this.evictEntries(e);
                    V v = entryValue;
                    return v;
                }
                V v = null;
                return v;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable V remove(Object key, int hash) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    RemovalCause cause;
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (entryValue != null) {
                        cause = RemovalCause.EXPLICIT;
                    } else if (valueReference.isActive()) {
                        cause = RemovalCause.COLLECTED;
                    } else {
                        V v = null;
                        return v;
                    }
                    ++this.modCount;
                    ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, cause);
                    newCount = this.count - 1;
                    table.set(index, newFirst);
                    this.count = newCount;
                    V v = entryValue;
                    return v;
                }
                V v = null;
                return v;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean remove(Object key, int hash, Object value) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    RemovalCause cause;
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                        cause = RemovalCause.EXPLICIT;
                    } else if (entryValue == null && valueReference.isActive()) {
                        cause = RemovalCause.COLLECTED;
                    } else {
                        boolean bl = false;
                        return bl;
                    }
                    ++this.modCount;
                    ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, cause);
                    newCount = this.count - 1;
                    table.set(index, newFirst);
                    this.count = newCount;
                    boolean bl = cause == RemovalCause.EXPLICIT;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean storeLoadedValue(K key, int hash, LoadingValueReference<K, V> oldValueReference, V newValue) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                long now = this.map.ticker.read();
                this.preWriteCleanup(now);
                int newCount = this.count + 1;
                if (newCount > this.threshold) {
                    this.expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> valueReference = e.getValueReference();
                    V entryValue = valueReference.get();
                    if (oldValueReference == valueReference || entryValue == null && valueReference != UNSET) {
                        ++this.modCount;
                        if (oldValueReference.isActive()) {
                            RemovalCause cause = entryValue == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED;
                            this.enqueueNotification(key, hash, entryValue, oldValueReference.getWeight(), cause);
                            --newCount;
                        }
                        this.setValue(e, key, newValue, now);
                        this.count = newCount;
                        this.evictEntries(e);
                        boolean bl = true;
                        return bl;
                    }
                    this.enqueueNotification(key, hash, newValue, 0, RemovalCause.REPLACED);
                    boolean bl = false;
                    return bl;
                }
                ++this.modCount;
                ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
                this.setValue(newEntry, key, newValue, now);
                table.set(index, newEntry);
                this.count = newCount;
                this.evictEntries(newEntry);
                boolean bl = true;
                return bl;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void clear() {
            if (this.count != 0) {
                this.lock();
                try {
                    int i;
                    long now = this.map.ticker.read();
                    this.preWriteCleanup(now);
                    AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                    for (i = 0; i < table.length(); ++i) {
                        for (ReferenceEntry<K, V> e = table.get(i); e != null; e = e.getNext()) {
                            if (!e.getValueReference().isActive()) continue;
                            K key = e.getKey();
                            V value = e.getValueReference().get();
                            RemovalCause cause = key == null || value == null ? RemovalCause.COLLECTED : RemovalCause.EXPLICIT;
                            this.enqueueNotification(key, e.getHash(), value, e.getValueReference().getWeight(), cause);
                        }
                    }
                    for (i = 0; i < table.length(); ++i) {
                        table.set(i, null);
                    }
                    this.clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    ++this.modCount;
                    this.count = 0;
                } finally {
                    this.unlock();
                    this.postWriteCleanup();
                }
            }
        }

        @GuardedBy(value="this")
        @Nullable ReferenceEntry<K, V> removeValueFromChain(ReferenceEntry<K, V> first, ReferenceEntry<K, V> entry, @Nullable K key, int hash, V value, ValueReference<K, V> valueReference, RemovalCause cause) {
            this.enqueueNotification(key, hash, value, valueReference.getWeight(), cause);
            this.writeQueue.remove(entry);
            this.accessQueue.remove(entry);
            if (valueReference.isLoading()) {
                valueReference.notifyNewValue(null);
                return first;
            }
            return this.removeEntryFromChain(first, entry);
        }

        @GuardedBy(value="this")
        @Nullable ReferenceEntry<K, V> removeEntryFromChain(ReferenceEntry<K, V> first, ReferenceEntry<K, V> entry) {
            int newCount = this.count;
            ReferenceEntry<K, V> newFirst = entry.getNext();
            for (ReferenceEntry<K, V> e = first; e != entry; e = e.getNext()) {
                ReferenceEntry<K, V> next = this.copyEntry(e, newFirst);
                if (next != null) {
                    newFirst = next;
                    continue;
                }
                this.removeCollectedEntry(e);
                --newCount;
            }
            this.count = newCount;
            return newFirst;
        }

        @GuardedBy(value="this")
        void removeCollectedEntry(ReferenceEntry<K, V> entry) {
            this.enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference().get(), entry.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.writeQueue.remove(entry);
            this.accessQueue.remove(entry);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean reclaimKey(ReferenceEntry<K, V> entry, int hash) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    if (e != entry) continue;
                    ++this.modCount;
                    ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference().get(), e.getValueReference(), RemovalCause.COLLECTED);
                    newCount = this.count - 1;
                    table.set(index, newFirst);
                    this.count = newCount;
                    boolean bl = true;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean reclaimValue(K key, int hash, ValueReference<K, V> valueReference) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> v = e.getValueReference();
                    if (v == valueReference) {
                        ++this.modCount;
                        ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference.get(), valueReference, RemovalCause.COLLECTED);
                        newCount = this.count - 1;
                        table.set(index, newFirst);
                        this.count = newCount;
                        boolean bl = true;
                        return bl;
                    }
                    boolean bl = false;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.unlock();
                if (!this.isHeldByCurrentThread()) {
                    this.postWriteCleanup();
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        boolean removeLoadingValue(K key, int hash, LoadingValueReference<K, V> valueReference) {
            this.lock();
            try {
                ReferenceEntry<K, V> first;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & table.length() - 1;
                for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) continue;
                    ValueReference<K, V> v = e.getValueReference();
                    if (v == valueReference) {
                        if (valueReference.isActive()) {
                            e.setValueReference(valueReference.getOldValue());
                        } else {
                            ReferenceEntry<K, V> newFirst = this.removeEntryFromChain(first, e);
                            table.set(index, newFirst);
                        }
                        boolean bl = true;
                        return bl;
                    }
                    boolean bl = false;
                    return bl;
                }
                boolean bl = false;
                return bl;
            } finally {
                this.unlock();
                this.postWriteCleanup();
            }
        }

        @VisibleForTesting
        @GuardedBy(value="this")
        boolean removeEntry(ReferenceEntry<K, V> entry, int hash, RemovalCause cause) {
            ReferenceEntry<K, V> first;
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            for (ReferenceEntry<K, V> e = first = table.get(index); e != null; e = e.getNext()) {
                if (e != entry) continue;
                ++this.modCount;
                ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference().get(), e.getValueReference(), cause);
                newCount = this.count - 1;
                table.set(index, newFirst);
                this.count = newCount;
                return true;
            }
            return false;
        }

        void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 0x3F) == 0) {
                this.cleanUp();
            }
        }

        @GuardedBy(value="this")
        void preWriteCleanup(long now) {
            this.runLockedCleanup(now);
        }

        void postWriteCleanup() {
            this.runUnlockedCleanup();
        }

        void cleanUp() {
            long now = this.map.ticker.read();
            this.runLockedCleanup(now);
            this.runUnlockedCleanup();
        }

        void runLockedCleanup(long now) {
            if (this.tryLock()) {
                try {
                    this.drainReferenceQueues();
                    this.expireEntries(now);
                    this.readCount.set(0);
                } finally {
                    this.unlock();
                }
            }
        }

        void runUnlockedCleanup() {
            if (!this.isHeldByCurrentThread()) {
                this.map.processPendingNotifications();
            }
        }
    }

    static final class WeightedStrongValueReference<K, V>
    extends StrongValueReference<K, V> {
        final int weight;

        WeightedStrongValueReference(V referent, int weight) {
            super(referent);
            this.weight = weight;
        }

        @Override
        public int getWeight() {
            return this.weight;
        }
    }

    static final class WeightedSoftValueReference<K, V>
    extends SoftValueReference<K, V> {
        final int weight;

        WeightedSoftValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry, int weight) {
            super(queue, referent, entry);
            this.weight = weight;
        }

        @Override
        public int getWeight() {
            return this.weight;
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
            return new WeightedSoftValueReference<K, V>(queue, value, entry, this.weight);
        }
    }

    static final class WeightedWeakValueReference<K, V>
    extends WeakValueReference<K, V> {
        final int weight;

        WeightedWeakValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry, int weight) {
            super(queue, referent, entry);
            this.weight = weight;
        }

        @Override
        public int getWeight() {
            return this.weight;
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
            return new WeightedWeakValueReference<K, V>(queue, value, entry, this.weight);
        }
    }

    static class StrongValueReference<K, V>
    implements ValueReference<K, V> {
        final V referent;

        StrongValueReference(V referent) {
            this.referent = referent;
        }

        @Override
        public V get() {
            return this.referent;
        }

        @Override
        public int getWeight() {
            return 1;
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
            return this;
        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public V waitForValue() {
            return this.get();
        }

        @Override
        public void notifyNewValue(V newValue) {
        }
    }

    static class SoftValueReference<K, V>
    extends SoftReference<V>
    implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        SoftValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry) {
            super(referent, queue);
            this.entry = entry;
        }

        @Override
        public int getWeight() {
            return 1;
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override
        public void notifyNewValue(V newValue) {
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
            return new SoftValueReference<K, V>(queue, value, entry);
        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public V waitForValue() {
            return (V)this.get();
        }
    }

    static class WeakValueReference<K, V>
    extends WeakReference<V>
    implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        WeakValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry) {
            super(referent, queue);
            this.entry = entry;
        }

        @Override
        public int getWeight() {
            return 1;
        }

        @Override
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override
        public void notifyNewValue(V newValue) {
        }

        @Override
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
            return new WeakValueReference<K, V>(queue, value, entry);
        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public V waitForValue() {
            return (V)this.get();
        }
    }

    static final class WeakAccessWriteEntry<K, V>
    extends WeakEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();

        WeakAccessWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override
        public void setAccessTime(long time) {
            this.accessTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            this.nextAccess = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            this.previousAccess = previous;
        }

        @Override
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override
        public void setWriteTime(long time) {
            this.writeTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            this.nextWrite = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            this.previousWrite = previous;
        }
    }

    static final class WeakWriteEntry<K, V>
    extends WeakEntry<K, V> {
        volatile long writeTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();

        WeakWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override
        public void setWriteTime(long time) {
            this.writeTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            this.nextWrite = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            this.previousWrite = previous;
        }
    }

    static final class WeakAccessEntry<K, V>
    extends WeakEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();

        WeakAccessEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override
        public void setAccessTime(long time) {
            this.accessTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            this.nextAccess = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            this.previousAccess = previous;
        }
    }

    static class WeakEntry<K, V>
    extends WeakReference<K>
    implements ReferenceEntry<K, V> {
        final int hash;
        final @Nullable ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        WeakEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, queue);
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return (K)this.get();
        }

        @Override
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setAccessTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setWriteTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class StrongAccessWriteEntry<K, V>
    extends StrongEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();
        volatile long writeTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();

        StrongAccessWriteEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override
        public void setAccessTime(long time) {
            this.accessTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            this.nextAccess = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            this.previousAccess = previous;
        }

        @Override
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override
        public void setWriteTime(long time) {
            this.writeTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            this.nextWrite = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            this.previousWrite = previous;
        }
    }

    static final class StrongWriteEntry<K, V>
    extends StrongEntry<K, V> {
        volatile long writeTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();

        StrongWriteEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override
        public void setWriteTime(long time) {
            this.writeTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            this.nextWrite = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            this.previousWrite = previous;
        }
    }

    static final class StrongAccessEntry<K, V>
    extends StrongEntry<K, V> {
        volatile long accessTime = Long.MAX_VALUE;
        ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
        ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();

        StrongAccessEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override
        public void setAccessTime(long time) {
            this.accessTime = time;
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            this.nextAccess = next;
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            this.previousAccess = previous;
        }
    }

    static class StrongEntry<K, V>
    extends AbstractReferenceEntry<K, V> {
        final K key;
        final int hash;
        final @Nullable ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        StrongEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            this.key = key;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static abstract class AbstractReferenceEntry<K, V>
    implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        @Override
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setAccessTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setWriteTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            throw new UnsupportedOperationException();
        }
    }

    private static enum NullEntry implements ReferenceEntry<Object, Object>
    {
        INSTANCE;


        @Override
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        @Override
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override
        public int getHash() {
            return 0;
        }

        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public long getAccessTime() {
            return 0L;
        }

        @Override
        public void setAccessTime(long time) {
        }

        @Override
        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override
        public void setNextInAccessQueue(ReferenceEntry<Object, Object> next) {
        }

        @Override
        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override
        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> previous) {
        }

        @Override
        public long getWriteTime() {
            return 0L;
        }

        @Override
        public void setWriteTime(long time) {
        }

        @Override
        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override
        public void setNextInWriteQueue(ReferenceEntry<Object, Object> next) {
        }

        @Override
        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override
        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> previous) {
        }
    }

    static interface ValueReference<K, V> {
        public @Nullable V get();

        public V waitForValue() throws ExecutionException;

        public int getWeight();

        public @Nullable ReferenceEntry<K, V> getEntry();

        public ValueReference<K, V> copyFor(ReferenceQueue<V> var1, @Nullable V var2, ReferenceEntry<K, V> var3);

        public void notifyNewValue(@Nullable V var1);

        public boolean isLoading();

        public boolean isActive();
    }

    static enum EntryFactory {
        STRONG{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongEntry<K, V>(key, hash, next);
            }
        }
        ,
        STRONG_ACCESS{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongAccessEntry<K, V>(key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyAccessEntry(original, newEntry);
                return newEntry;
            }
        }
        ,
        STRONG_WRITE{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongWriteEntry<K, V>(key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyWriteEntry(original, newEntry);
                return newEntry;
            }
        }
        ,
        STRONG_ACCESS_WRITE{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongAccessWriteEntry<K, V>(key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyAccessEntry(original, newEntry);
                this.copyWriteEntry(original, newEntry);
                return newEntry;
            }
        }
        ,
        WEAK{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakEntry(segment.keyReferenceQueue, key, hash, next);
            }
        }
        ,
        WEAK_ACCESS{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakAccessEntry(segment.keyReferenceQueue, key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyAccessEntry(original, newEntry);
                return newEntry;
            }
        }
        ,
        WEAK_WRITE{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakWriteEntry(segment.keyReferenceQueue, key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyWriteEntry(original, newEntry);
                return newEntry;
            }
        }
        ,
        WEAK_ACCESS_WRITE{

            @Override
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakAccessWriteEntry(segment.keyReferenceQueue, key, hash, next);
            }

            @Override
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                this.copyAccessEntry(original, newEntry);
                this.copyWriteEntry(original, newEntry);
                return newEntry;
            }
        };

        static final int ACCESS_MASK = 1;
        static final int WRITE_MASK = 2;
        static final int WEAK_MASK = 4;
        static final EntryFactory[] factories;

        static EntryFactory getFactory(Strength keyStrength, boolean usesAccessQueue, boolean usesWriteQueue) {
            int flags = (keyStrength == Strength.WEAK ? 4 : 0) | (usesAccessQueue ? 1 : 0) | (usesWriteQueue ? 2 : 0);
            return factories[flags];
        }

        abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> var1, K var2, int var3, @Nullable ReferenceEntry<K, V> var4);

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            return this.newEntry(segment, original.getKey(), original.getHash(), newNext);
        }

        <K, V> void copyAccessEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
            newEntry.setAccessTime(original.getAccessTime());
            LocalCache.connectAccessOrder(original.getPreviousInAccessQueue(), newEntry);
            LocalCache.connectAccessOrder(newEntry, original.getNextInAccessQueue());
            LocalCache.nullifyAccessOrder(original);
        }

        <K, V> void copyWriteEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
            newEntry.setWriteTime(original.getWriteTime());
            LocalCache.connectWriteOrder(original.getPreviousInWriteQueue(), newEntry);
            LocalCache.connectWriteOrder(newEntry, original.getNextInWriteQueue());
            LocalCache.nullifyWriteOrder(original);
        }

        static {
            factories = new EntryFactory[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};
        }
    }

    static enum Strength {
        STRONG{

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value, int weight) {
                return weight == 1 ? new StrongValueReference(value) : new WeightedStrongValueReference(value, weight);
            }

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        }
        ,
        SOFT{

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value, int weight) {
                return weight == 1 ? new SoftValueReference(segment.valueReferenceQueue, value, entry) : new WeightedSoftValueReference(segment.valueReferenceQueue, value, entry, weight);
            }

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        }
        ,
        WEAK{

            @Override
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value, int weight) {
                return weight == 1 ? new WeakValueReference(segment.valueReferenceQueue, value, entry) : new WeightedWeakValueReference(segment.valueReferenceQueue, value, entry, weight);
            }

            @Override
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };


        abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> var1, ReferenceEntry<K, V> var2, V var3, int var4);

        abstract Equivalence<Object> defaultEquivalence();
    }
}

