/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class ConcurrentReferenceHashMap<K, V>
extends AbstractMap<K, V>
implements ConcurrentMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final ReferenceType DEFAULT_REFERENCE_TYPE = ReferenceType.SOFT;
    private static final int MAXIMUM_CONCURRENCY_LEVEL = 65536;
    private static final int MAXIMUM_SEGMENT_SIZE = 0x40000000;
    private final Segment[] segments;
    private final float loadFactor;
    private final ReferenceType referenceType;
    private final int shift;
    @Nullable
    private volatile Set<Map.Entry<K, V>> entrySet;

    public ConcurrentReferenceHashMap() {
        this(16, 0.75f, 16, DEFAULT_REFERENCE_TYPE);
    }

    public ConcurrentReferenceHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f, 16, DEFAULT_REFERENCE_TYPE);
    }

    public ConcurrentReferenceHashMap(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, 16, DEFAULT_REFERENCE_TYPE);
    }

    public ConcurrentReferenceHashMap(int initialCapacity, int concurrencyLevel) {
        this(initialCapacity, 0.75f, concurrencyLevel, DEFAULT_REFERENCE_TYPE);
    }

    public ConcurrentReferenceHashMap(int initialCapacity, ReferenceType referenceType) {
        this(initialCapacity, 0.75f, 16, referenceType);
    }

    public ConcurrentReferenceHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        this(initialCapacity, loadFactor, concurrencyLevel, DEFAULT_REFERENCE_TYPE);
    }

    public ConcurrentReferenceHashMap(int initialCapacity, float loadFactor, int concurrencyLevel, ReferenceType referenceType) {
        Assert.isTrue(initialCapacity >= 0, "Initial capacity must not be negative");
        Assert.isTrue(loadFactor > 0.0f, "Load factor must be positive");
        Assert.isTrue(concurrencyLevel > 0, "Concurrency level must be positive");
        Assert.notNull((Object)referenceType, "Reference type must not be null");
        this.loadFactor = loadFactor;
        this.shift = ConcurrentReferenceHashMap.calculateShift(concurrencyLevel, 65536);
        int size = 1 << this.shift;
        this.referenceType = referenceType;
        int roundedUpSegmentCapacity = (int)(((long)(initialCapacity + size) - 1L) / (long)size);
        int initialSize = 1 << ConcurrentReferenceHashMap.calculateShift(roundedUpSegmentCapacity, 0x40000000);
        Segment[] segments = (Segment[])Array.newInstance(Segment.class, size);
        int resizeThreshold = (int)((float)initialSize * this.getLoadFactor());
        for (int i = 0; i < segments.length; ++i) {
            segments[i] = new Segment(initialSize, resizeThreshold);
        }
        this.segments = segments;
    }

    protected final float getLoadFactor() {
        return this.loadFactor;
    }

    protected final int getSegmentsSize() {
        return this.segments.length;
    }

    protected final Segment getSegment(int index) {
        return this.segments[index];
    }

    protected ReferenceManager createReferenceManager() {
        return new ReferenceManager();
    }

    protected int getHash(@Nullable Object o) {
        int hash = o != null ? o.hashCode() : 0;
        hash += hash << 15 ^ 0xFFFFCD7D;
        hash ^= hash >>> 10;
        hash += hash << 3;
        hash ^= hash >>> 6;
        hash += (hash << 2) + (hash << 14);
        hash ^= hash >>> 16;
        return hash;
    }

    @Override
    @Nullable
    public V get(@Nullable Object key) {
        Reference<K, V> ref = this.getReference(key, Restructure.WHEN_NECESSARY);
        Entry<K, V> entry = ref != null ? ref.get() : null;
        return entry != null ? (V)entry.getValue() : null;
    }

    @Override
    @Nullable
    public V getOrDefault(@Nullable Object key, @Nullable V defaultValue) {
        Reference<K, V> ref = this.getReference(key, Restructure.WHEN_NECESSARY);
        Entry<K, V> entry = ref != null ? ref.get() : null;
        return entry != null ? entry.getValue() : defaultValue;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        Reference<K, V> ref = this.getReference(key, Restructure.WHEN_NECESSARY);
        Entry<K, V> entry = ref != null ? ref.get() : null;
        return entry != null && ObjectUtils.nullSafeEquals(entry.getKey(), key);
    }

    @Nullable
    protected final Reference<K, V> getReference(@Nullable Object key, Restructure restructure) {
        int hash = this.getHash(key);
        return this.getSegmentForHash(hash).getReference(key, hash, restructure);
    }

    @Override
    @Nullable
    public V put(@Nullable K key, @Nullable V value) {
        return this.put(key, value, true);
    }

    @Override
    @Nullable
    public V putIfAbsent(@Nullable K key, @Nullable V value) {
        return this.put(key, value, false);
    }

    @Nullable
    private V put(@Nullable K key, final @Nullable V value, final boolean overwriteExisting) {
        return (V)this.doTask(key, new Task<V>(new TaskOption[]{TaskOption.RESTRUCTURE_BEFORE, TaskOption.RESIZE}){

            @Override
            @Nullable
            protected V execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry, @Nullable Entries<V> entries) {
                if (entry != null) {
                    Object oldValue = entry.getValue();
                    if (overwriteExisting) {
                        entry.setValue(value);
                    }
                    return oldValue;
                }
                Assert.state(entries != null, "No entries segment");
                entries.add(value);
                return null;
            }
        });
    }

    @Override
    @Nullable
    public V remove(@Nullable Object key) {
        return (V)this.doTask(key, new Task<V>(new TaskOption[]{TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY}){

            @Override
            @Nullable
            protected V execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry) {
                if (entry != null) {
                    if (ref != null) {
                        ref.release();
                    }
                    return entry.value;
                }
                return null;
            }
        });
    }

    @Override
    public boolean remove(@Nullable Object key, final @Nullable Object value) {
        Boolean result = this.doTask(key, new Task<Boolean>(new TaskOption[]{TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY}){

            @Override
            protected Boolean execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry) {
                if (entry != null && ObjectUtils.nullSafeEquals(entry.getValue(), value)) {
                    if (ref != null) {
                        ref.release();
                    }
                    return true;
                }
                return false;
            }
        });
        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean replace(@Nullable K key, final @Nullable V oldValue, final @Nullable V newValue) {
        Boolean result = this.doTask(key, new Task<Boolean>(new TaskOption[]{TaskOption.RESTRUCTURE_BEFORE, TaskOption.SKIP_IF_EMPTY}){

            @Override
            protected Boolean execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry) {
                if (entry != null && ObjectUtils.nullSafeEquals(entry.getValue(), oldValue)) {
                    entry.setValue(newValue);
                    return true;
                }
                return false;
            }
        });
        return Boolean.TRUE.equals(result);
    }

    @Override
    @Nullable
    public V replace(@Nullable K key, final @Nullable V value) {
        return (V)this.doTask(key, new Task<V>(new TaskOption[]{TaskOption.RESTRUCTURE_BEFORE, TaskOption.SKIP_IF_EMPTY}){

            @Override
            @Nullable
            protected V execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry) {
                if (entry != null) {
                    Object oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
                return null;
            }
        });
    }

    @Override
    public void clear() {
        for (Segment segment : this.segments) {
            segment.clear();
        }
    }

    public void purgeUnreferencedEntries() {
        for (Segment segment : this.segments) {
            segment.restructureIfNecessary(false);
        }
    }

    @Override
    public int size() {
        int size = 0;
        for (Segment segment : this.segments) {
            size += segment.getCount();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Segment segment : this.segments) {
            if (segment.getCount() <= 0) continue;
            return false;
        }
        return true;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet entrySet = this.entrySet;
        if (entrySet == null) {
            this.entrySet = entrySet = new EntrySet();
        }
        return entrySet;
    }

    @Nullable
    private <T> T doTask(@Nullable Object key, Task<T> task) {
        int hash = this.getHash(key);
        return this.getSegmentForHash(hash).doTask(hash, key, task);
    }

    private Segment getSegmentForHash(int hash) {
        return this.segments[hash >>> 32 - this.shift & this.segments.length - 1];
    }

    protected static int calculateShift(int minimumValue, int maximumValue) {
        int shift = 0;
        int value = 1;
        while (value < minimumValue && value < maximumValue) {
            value <<= 1;
            ++shift;
        }
        return shift;
    }

    private static final class WeakEntryReference<K, V>
    extends WeakReference<Entry<K, V>>
    implements Reference<K, V> {
        private final int hash;
        @Nullable
        private final Reference<K, V> nextReference;

        public WeakEntryReference(Entry<K, V> entry, int hash, @Nullable Reference<K, V> next, ReferenceQueue<Entry<K, V>> queue) {
            super(entry, queue);
            this.hash = hash;
            this.nextReference = next;
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        @Nullable
        public Reference<K, V> getNext() {
            return this.nextReference;
        }

        @Override
        public void release() {
            this.enqueue();
            this.clear();
        }
    }

    private static final class SoftEntryReference<K, V>
    extends SoftReference<Entry<K, V>>
    implements Reference<K, V> {
        private final int hash;
        @Nullable
        private final Reference<K, V> nextReference;

        public SoftEntryReference(Entry<K, V> entry, int hash, @Nullable Reference<K, V> next, ReferenceQueue<Entry<K, V>> queue) {
            super(entry, queue);
            this.hash = hash;
            this.nextReference = next;
        }

        @Override
        public int getHash() {
            return this.hash;
        }

        @Override
        @Nullable
        public Reference<K, V> getNext() {
            return this.nextReference;
        }

        @Override
        public void release() {
            this.enqueue();
            this.clear();
        }
    }

    protected class ReferenceManager {
        private final ReferenceQueue<Entry<K, V>> queue = new ReferenceQueue();

        protected ReferenceManager() {
        }

        public Reference<K, V> createReference(Entry<K, V> entry, int hash, @Nullable Reference<K, V> next) {
            if (ConcurrentReferenceHashMap.this.referenceType == ReferenceType.WEAK) {
                return new WeakEntryReference(entry, hash, next, this.queue);
            }
            return new SoftEntryReference(entry, hash, next, this.queue);
        }

        @Nullable
        public Reference<K, V> pollForPurge() {
            return (Reference)((Object)this.queue.poll());
        }
    }

    protected static enum Restructure {
        WHEN_NECESSARY,
        NEVER;

    }

    private class EntryIterator
    implements Iterator<Map.Entry<K, V>> {
        private int segmentIndex;
        private int referenceIndex;
        @Nullable
        private Reference<K, V>[] references;
        @Nullable
        private Reference<K, V> reference;
        @Nullable
        private Entry<K, V> next;
        @Nullable
        private Entry<K, V> last;

        public EntryIterator() {
            this.moveToNextSegment();
        }

        @Override
        public boolean hasNext() {
            this.getNextIfNecessary();
            return this.next != null;
        }

        @Override
        public Entry<K, V> next() {
            this.getNextIfNecessary();
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            this.last = this.next;
            this.next = null;
            return this.last;
        }

        private void getNextIfNecessary() {
            while (this.next == null) {
                this.moveToNextReference();
                if (this.reference == null) {
                    return;
                }
                this.next = this.reference.get();
            }
        }

        private void moveToNextReference() {
            if (this.reference != null) {
                this.reference = this.reference.getNext();
            }
            while (this.reference == null && this.references != null) {
                if (this.referenceIndex >= this.references.length) {
                    this.moveToNextSegment();
                    this.referenceIndex = 0;
                    continue;
                }
                this.reference = this.references[this.referenceIndex];
                ++this.referenceIndex;
            }
        }

        private void moveToNextSegment() {
            this.reference = null;
            this.references = null;
            if (this.segmentIndex < ConcurrentReferenceHashMap.this.segments.length) {
                this.references = ConcurrentReferenceHashMap.this.segments[this.segmentIndex].references;
                ++this.segmentIndex;
            }
        }

        @Override
        public void remove() {
            Assert.state(this.last != null, "No element to remove");
            ConcurrentReferenceHashMap.this.remove(this.last.getKey());
            this.last = null;
        }
    }

    private class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean contains(@Nullable Object o) {
            if (o instanceof Map.Entry) {
                Entry otherEntry;
                Map.Entry entry = (Map.Entry)o;
                Reference ref = ConcurrentReferenceHashMap.this.getReference(entry.getKey(), Restructure.NEVER);
                Entry entry2 = otherEntry = ref != null ? ref.get() : null;
                if (otherEntry != null) {
                    return ObjectUtils.nullSafeEquals(entry.getValue(), otherEntry.getValue());
                }
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                return ConcurrentReferenceHashMap.this.remove(entry.getKey(), entry.getValue());
            }
            return false;
        }

        @Override
        public int size() {
            return ConcurrentReferenceHashMap.this.size();
        }

        @Override
        public void clear() {
            ConcurrentReferenceHashMap.this.clear();
        }
    }

    private static interface Entries<V> {
        public void add(@Nullable V var1);
    }

    private static enum TaskOption {
        RESTRUCTURE_BEFORE,
        RESTRUCTURE_AFTER,
        SKIP_IF_EMPTY,
        RESIZE;

    }

    private abstract class Task<T> {
        private final EnumSet<TaskOption> options;

        public Task(TaskOption ... options) {
            this.options = options.length == 0 ? EnumSet.noneOf(TaskOption.class) : EnumSet.of(options[0], options);
        }

        public boolean hasOption(TaskOption option) {
            return this.options.contains((Object)option);
        }

        @Nullable
        protected T execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry, @Nullable Entries<V> entries) {
            return this.execute(ref, entry);
        }

        @Nullable
        protected T execute(@Nullable Reference<K, V> ref, @Nullable Entry<K, V> entry) {
            return null;
        }
    }

    protected static final class Entry<K, V>
    implements Map.Entry<K, V> {
        @Nullable
        private final K key;
        @Nullable
        private volatile V value;

        public Entry(@Nullable K key, @Nullable V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        @Nullable
        public K getKey() {
            return this.key;
        }

        @Override
        @Nullable
        public V getValue() {
            return this.value;
        }

        @Override
        @Nullable
        public V setValue(@Nullable V value) {
            V previous = this.value;
            this.value = value;
            return previous;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        @Override
        public final boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Map.Entry)) {
                return false;
            }
            Map.Entry otherEntry = (Map.Entry)other;
            return ObjectUtils.nullSafeEquals(this.getKey(), otherEntry.getKey()) && ObjectUtils.nullSafeEquals(this.getValue(), otherEntry.getValue());
        }

        @Override
        public final int hashCode() {
            return ObjectUtils.nullSafeHashCode(this.key) ^ ObjectUtils.nullSafeHashCode(this.value);
        }
    }

    protected static interface Reference<K, V> {
        @Nullable
        public Entry<K, V> get();

        public int getHash();

        @Nullable
        public Reference<K, V> getNext();

        public void release();
    }

    protected final class Segment
    extends ReentrantLock {
        private final ReferenceManager referenceManager;
        private final int initialSize;
        private volatile Reference<K, V>[] references;
        private final AtomicInteger count = new AtomicInteger();
        private int resizeThreshold;

        public Segment(int initialSize, int resizeThreshold) {
            this.referenceManager = ConcurrentReferenceHashMap.this.createReferenceManager();
            this.initialSize = initialSize;
            this.references = this.createReferenceArray(initialSize);
            this.resizeThreshold = resizeThreshold;
        }

        @Nullable
        public Reference<K, V> getReference(@Nullable Object key, int hash, Restructure restructure) {
            if (restructure == Restructure.WHEN_NECESSARY) {
                this.restructureIfNecessary(false);
            }
            if (this.count.get() == 0) {
                return null;
            }
            Reference<K, V>[] references = this.references;
            int index = this.getIndex(hash, references);
            Reference head = references[index];
            return this.findInChain(head, key, hash);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable
        public <T> T doTask(int hash, @Nullable Object key, Task<T> task) {
            boolean resize = task.hasOption(TaskOption.RESIZE);
            if (task.hasOption(TaskOption.RESTRUCTURE_BEFORE)) {
                this.restructureIfNecessary(resize);
            }
            if (task.hasOption(TaskOption.SKIP_IF_EMPTY) && this.count.get() == 0) {
                return task.execute(null, null, null);
            }
            this.lock();
            try {
                int index = this.getIndex(hash, this.references);
                Reference head = this.references[index];
                Reference ref = this.findInChain(head, key, hash);
                Entry entry = ref != null ? ref.get() : null;
                Entries<Object> entries = value -> {
                    Entry<Object, Object> newEntry = new Entry<Object, Object>(key, value);
                    Reference newReference = this.referenceManager.createReference(newEntry, hash, head);
                    this.references[index] = newReference;
                    this.count.incrementAndGet();
                };
                T t = task.execute(ref, entry, entries);
                return t;
            } finally {
                this.unlock();
                if (task.hasOption(TaskOption.RESTRUCTURE_AFTER)) {
                    this.restructureIfNecessary(resize);
                }
            }
        }

        public void clear() {
            if (this.count.get() == 0) {
                return;
            }
            this.lock();
            try {
                this.references = this.createReferenceArray(this.initialSize);
                this.resizeThreshold = (int)((float)this.references.length * ConcurrentReferenceHashMap.this.getLoadFactor());
                this.count.set(0);
            } finally {
                this.unlock();
            }
        }

        protected final void restructureIfNecessary(boolean allowResize) {
            int currCount = this.count.get();
            boolean needsResize = allowResize && currCount > 0 && currCount >= this.resizeThreshold;
            Reference ref = this.referenceManager.pollForPurge();
            if (ref != null || needsResize) {
                this.restructure(allowResize, ref);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void restructure(boolean allowResize, @Nullable Reference<K, V> ref) {
            this.lock();
            try {
                int countAfterRestructure = this.count.get();
                Set toPurge = Collections.emptySet();
                if (ref != null) {
                    toPurge = new HashSet();
                    while (ref != null) {
                        toPurge.add(ref);
                        ref = this.referenceManager.pollForPurge();
                    }
                }
                boolean needsResize = (countAfterRestructure -= toPurge.size()) > 0 && countAfterRestructure >= this.resizeThreshold;
                boolean resizing = false;
                int restructureSize = this.references.length;
                if (allowResize && needsResize && restructureSize < 0x40000000) {
                    restructureSize <<= 1;
                    resizing = true;
                }
                Reference<K, V>[] restructured = resizing ? this.createReferenceArray(restructureSize) : this.references;
                for (int i = 0; i < this.references.length; ++i) {
                    ref = this.references[i];
                    if (!resizing) {
                        restructured[i] = null;
                    }
                    while (ref != null) {
                        Entry entry;
                        if (!toPurge.contains(ref) && (entry = ref.get()) != null) {
                            int index = this.getIndex(ref.getHash(), restructured);
                            restructured[index] = this.referenceManager.createReference(entry, ref.getHash(), restructured[index]);
                        }
                        ref = ref.getNext();
                    }
                }
                if (resizing) {
                    this.references = restructured;
                    this.resizeThreshold = (int)((float)this.references.length * ConcurrentReferenceHashMap.this.getLoadFactor());
                }
                this.count.set(Math.max(countAfterRestructure, 0));
            } finally {
                this.unlock();
            }
        }

        @Nullable
        private Reference<K, V> findInChain(Reference<K, V> ref, @Nullable Object key, int hash) {
            for (Reference currRef = ref; currRef != null; currRef = currRef.getNext()) {
                Object entryKey;
                Entry entry;
                if (currRef.getHash() != hash || (entry = currRef.get()) == null || !ObjectUtils.nullSafeEquals(entryKey = entry.getKey(), key)) continue;
                return currRef;
            }
            return null;
        }

        private Reference<K, V>[] createReferenceArray(int size) {
            return new Reference[size];
        }

        private int getIndex(int hash, Reference<K, V>[] references) {
            return hash & references.length - 1;
        }

        public final int getSize() {
            return this.references.length;
        }

        public final int getCount() {
            return this.count.get();
        }
    }

    public static enum ReferenceType {
        SOFT,
        WEAK;

    }
}

