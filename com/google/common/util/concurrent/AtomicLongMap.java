/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@GwtCompatible
public final class AtomicLongMap<K>
implements Serializable {
    private final ConcurrentHashMap<K, Long> map;
    private transient @MonotonicNonNull Map<K, Long> asMap;

    private AtomicLongMap(ConcurrentHashMap<K, Long> map) {
        this.map = Preconditions.checkNotNull(map);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap(new ConcurrentHashMap());
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> m) {
        AtomicLongMap<? extends K> result = AtomicLongMap.create();
        result.putAll(m);
        return result;
    }

    public long get(K key) {
        return this.map.getOrDefault(key, 0L);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K key) {
        return this.addAndGet(key, 1L);
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K key) {
        return this.addAndGet(key, -1L);
    }

    @CanIgnoreReturnValue
    public long addAndGet(K key, long delta) {
        return this.accumulateAndGet(key, delta, Long::sum);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K key) {
        return this.getAndAdd(key, 1L);
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K key) {
        return this.getAndAdd(key, -1L);
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K key, long delta) {
        return this.getAndAccumulate(key, delta, Long::sum);
    }

    @CanIgnoreReturnValue
    public long updateAndGet(K key, LongUnaryOperator updaterFunction) {
        Preconditions.checkNotNull(updaterFunction);
        return this.map.compute(key, (k, value) -> updaterFunction.applyAsLong(value == null ? 0L : value));
    }

    @CanIgnoreReturnValue
    public long getAndUpdate(K key, LongUnaryOperator updaterFunction) {
        Preconditions.checkNotNull(updaterFunction);
        AtomicLong holder = new AtomicLong();
        this.map.compute(key, (k, value) -> {
            long oldValue = value == null ? 0L : value;
            holder.set(oldValue);
            return updaterFunction.applyAsLong(oldValue);
        });
        return holder.get();
    }

    @CanIgnoreReturnValue
    public long accumulateAndGet(K key, long x, LongBinaryOperator accumulatorFunction) {
        Preconditions.checkNotNull(accumulatorFunction);
        return this.updateAndGet(key, oldValue -> accumulatorFunction.applyAsLong(oldValue, x));
    }

    @CanIgnoreReturnValue
    public long getAndAccumulate(K key, long x, LongBinaryOperator accumulatorFunction) {
        Preconditions.checkNotNull(accumulatorFunction);
        return this.getAndUpdate(key, oldValue -> accumulatorFunction.applyAsLong(oldValue, x));
    }

    @CanIgnoreReturnValue
    public long put(K key, long newValue) {
        return this.getAndUpdate(key, x -> newValue);
    }

    public void putAll(Map<? extends K, ? extends Long> m) {
        m.forEach(this::put);
    }

    @CanIgnoreReturnValue
    public long remove(K key) {
        Long result = this.map.remove(key);
        return result == null ? 0L : result;
    }

    boolean remove(K key, long value) {
        return this.map.remove(key, value);
    }

    @Beta
    @CanIgnoreReturnValue
    public boolean removeIfZero(K key) {
        return this.remove(key, 0L);
    }

    public void removeAllZeros() {
        this.map.values().removeIf(x -> x == 0L);
    }

    public long sum() {
        return this.map.values().stream().mapToLong(Long::longValue).sum();
    }

    public Map<K, Long> asMap() {
        Map<K, Long> result = this.asMap;
        return result == null ? (this.asMap = this.createAsMap()) : result;
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(this.map);
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }

    long putIfAbsent(K key, long newValue) {
        AtomicBoolean noValue = new AtomicBoolean(false);
        Long result = this.map.compute(key, (k, oldValue) -> {
            if (oldValue == null || oldValue == 0L) {
                noValue.set(true);
                return newValue;
            }
            return oldValue;
        });
        return noValue.get() ? 0L : result;
    }

    boolean replace(K key, long expectedOldValue, long newValue) {
        if (expectedOldValue == 0L) {
            return this.putIfAbsent(key, newValue) == 0L;
        }
        return this.map.replace(key, expectedOldValue, newValue);
    }
}

