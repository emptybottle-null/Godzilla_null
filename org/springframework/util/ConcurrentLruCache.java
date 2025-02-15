/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import org.springframework.util.Assert;

public class ConcurrentLruCache<K, V> {
    private final int sizeLimit;
    private final Function<K, V> generator;
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap();
    private final ConcurrentLinkedDeque<K> queue = new ConcurrentLinkedDeque();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile int size;

    public ConcurrentLruCache(int sizeLimit, Function<K, V> generator) {
        Assert.isTrue(sizeLimit >= 0, "Cache size limit must not be negative");
        Assert.notNull(generator, "Generator function must not be null");
        this.sizeLimit = sizeLimit;
        this.generator = generator;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public V get(K key) {
        if (this.sizeLimit == 0) {
            return this.generator.apply(key);
        }
        V cached = this.cache.get(key);
        if (cached != null) {
            if (this.size < this.sizeLimit) {
                return cached;
            }
            this.lock.readLock().lock();
            try {
                if (this.queue.removeLastOccurrence(key)) {
                    this.queue.offer(key);
                }
                V v = cached;
                return v;
            } finally {
                this.lock.readLock().unlock();
            }
        }
        this.lock.writeLock().lock();
        try {
            K leastUsed;
            cached = this.cache.get(key);
            if (cached != null) {
                if (this.queue.removeLastOccurrence(key)) {
                    this.queue.offer(key);
                }
                V v = cached;
                return v;
            }
            V value = this.generator.apply(key);
            if (this.size == this.sizeLimit && (leastUsed = this.queue.poll()) != null) {
                this.cache.remove(leastUsed);
            }
            this.queue.offer(key);
            this.cache.put(key, value);
            this.size = this.cache.size();
            V v = value;
            return v;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public boolean contains(K key) {
        return this.cache.containsKey(key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean remove(K key) {
        this.lock.writeLock().lock();
        try {
            boolean wasPresent = this.cache.remove(key) != null;
            this.queue.remove(key);
            this.size = this.cache.size();
            boolean bl = wasPresent;
            return bl;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void clear() {
        this.lock.writeLock().lock();
        try {
            this.cache.clear();
            this.queue.clear();
            this.size = 0;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public int size() {
        return this.size;
    }

    public int sizeLimit() {
        return this.sizeLimit;
    }
}

