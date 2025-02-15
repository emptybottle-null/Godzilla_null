/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.map.AbstractIterableMap;

public final class StaticBucketMap<K, V>
extends AbstractIterableMap<K, V> {
    private static final int DEFAULT_BUCKETS = 255;
    private final Node<K, V>[] buckets;
    private final Lock[] locks;

    public StaticBucketMap() {
        this(255);
    }

    public StaticBucketMap(int numBuckets) {
        int size = Math.max(17, numBuckets);
        if (size % 2 == 0) {
            --size;
        }
        this.buckets = new Node[size];
        this.locks = new Lock[size];
        for (int i = 0; i < size; ++i) {
            this.locks[i] = new Lock();
        }
    }

    private int getHash(Object key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        hash += ~(hash << 15);
        hash ^= hash >>> 10;
        hash += hash << 3;
        hash ^= hash >>> 6;
        hash += ~(hash << 11);
        hash ^= hash >>> 16;
        return (hash %= this.buckets.length) < 0 ? hash * -1 : hash;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() {
        int cnt = 0;
        for (int i = 0; i < this.buckets.length; ++i) {
            Lock lock = this.locks[i];
            synchronized (lock) {
                cnt += this.locks[i].size;
                continue;
            }
        }
        return cnt;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V get(Object key) {
        int hash = this.getHash(key);
        Lock lock = this.locks[hash];
        synchronized (lock) {
            Node<K, V> n = this.buckets[hash];
            while (n != null) {
                if (n.key == key || n.key != null && n.key.equals(key)) {
                    return n.value;
                }
                n = n.next;
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsKey(Object key) {
        int hash = this.getHash(key);
        Lock lock = this.locks[hash];
        synchronized (lock) {
            Node<K, V> n = this.buckets[hash];
            while (n != null) {
                if (n.key == key || n.key != null && n.key.equals(key)) {
                    return true;
                }
                n = n.next;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < this.buckets.length; ++i) {
            Lock lock = this.locks[i];
            synchronized (lock) {
                Node<K, V> n = this.buckets[i];
                while (n != null) {
                    if (n.value == value || n.value != null && n.value.equals(value)) {
                        return true;
                    }
                    n = n.next;
                }
                continue;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V put(K key, V value) {
        int hash = this.getHash(key);
        Lock lock = this.locks[hash];
        synchronized (lock) {
            Node<K, V> n = this.buckets[hash];
            if (n == null) {
                n = new Node();
                n.key = key;
                n.value = value;
                this.buckets[hash] = n;
                ++this.locks[hash].size;
                return null;
            }
            Node<K, V> next = n;
            while (next != null) {
                n = next;
                if (n.key == key || n.key != null && n.key.equals(key)) {
                    Object returnVal = n.value;
                    n.value = value;
                    return returnVal;
                }
                next = next.next;
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            n.next = newNode;
            ++this.locks[hash].size;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V remove(Object key) {
        int hash = this.getHash(key);
        Lock lock = this.locks[hash];
        synchronized (lock) {
            Node<K, V> n = this.buckets[hash];
            Node<K, V> prev = null;
            while (n != null) {
                if (n.key == key || n.key != null && n.key.equals(key)) {
                    if (null == prev) {
                        this.buckets[hash] = n.next;
                    } else {
                        prev.next = n.next;
                    }
                    --this.locks[hash].size;
                    return n.value;
                }
                prev = n;
                n = n.next;
            }
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return new Values();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.buckets.length; ++i) {
            Lock lock;
            Lock lock2 = lock = this.locks[i];
            synchronized (lock2) {
                this.buckets[i] = null;
                lock.size = 0;
                continue;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map other = (Map)obj;
        return this.entrySet().equals(other.entrySet());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < this.buckets.length; ++i) {
            Lock lock = this.locks[i];
            synchronized (lock) {
                Node<K, V> n = this.buckets[i];
                while (n != null) {
                    hashCode += n.hashCode();
                    n = n.next;
                }
                continue;
            }
        }
        return hashCode;
    }

    public void atomic(Runnable r) {
        if (r == null) {
            throw new NullPointerException();
        }
        this.atomic(r, 0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void atomic(Runnable r, int bucket) {
        if (bucket >= this.buckets.length) {
            r.run();
            return;
        }
        Lock lock = this.locks[bucket];
        synchronized (lock) {
            this.atomic(r, bucket + 1);
        }
    }

    private class Values
    extends AbstractCollection<V> {
        private Values() {
        }

        @Override
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    private class KeySet
    extends AbstractSet<K> {
        private KeySet() {
        }

        @Override
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean contains(Object obj) {
            return StaticBucketMap.this.containsKey(obj);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean remove(Object obj) {
            int hash = StaticBucketMap.this.getHash(obj);
            Lock lock = StaticBucketMap.this.locks[hash];
            synchronized (lock) {
                Node n = StaticBucketMap.this.buckets[hash];
                while (n != null) {
                    Object k = n.getKey();
                    if (k == obj || k != null && k.equals(obj)) {
                        StaticBucketMap.this.remove(k);
                        return true;
                    }
                    n = n.next;
                }
            }
            return false;
        }
    }

    private class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry)obj;
            int hash = StaticBucketMap.this.getHash(entry.getKey());
            Lock lock = StaticBucketMap.this.locks[hash];
            synchronized (lock) {
                Node n = StaticBucketMap.this.buckets[hash];
                while (n != null) {
                    if (n.equals(entry)) {
                        return true;
                    }
                    n = n.next;
                }
            }
            return false;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            int hash = StaticBucketMap.this.getHash(entry.getKey());
            Lock lock = StaticBucketMap.this.locks[hash];
            synchronized (lock) {
                Node n = StaticBucketMap.this.buckets[hash];
                while (n != null) {
                    if (n.equals(entry)) {
                        StaticBucketMap.this.remove(n.getKey());
                        return true;
                    }
                    n = n.next;
                }
            }
            return false;
        }
    }

    private class KeyIterator
    extends BaseIterator
    implements Iterator<K> {
        private KeyIterator() {
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    private class ValueIterator
    extends BaseIterator
    implements Iterator<V> {
        private ValueIterator() {
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    private class EntryIterator
    extends BaseIterator
    implements Iterator<Map.Entry<K, V>> {
        private EntryIterator() {
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    private class BaseIterator {
        private final ArrayList<Map.Entry<K, V>> current = new ArrayList();
        private int bucket;
        private Map.Entry<K, V> last;

        private BaseIterator() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public boolean hasNext() {
            if (this.current.size() > 0) {
                return true;
            }
            while (this.bucket < StaticBucketMap.this.buckets.length) {
                Lock lock = StaticBucketMap.this.locks[this.bucket];
                synchronized (lock) {
                    Node n = StaticBucketMap.this.buckets[this.bucket];
                    while (n != null) {
                        this.current.add(n);
                        n = n.next;
                    }
                    ++this.bucket;
                    if (this.current.size() > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        protected Map.Entry<K, V> nextEntry() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.last = this.current.remove(this.current.size() - 1);
            return this.last;
        }

        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException();
            }
            StaticBucketMap.this.remove(this.last.getKey());
            this.last = null;
        }
    }

    private static final class Lock {
        public int size;

        private Lock() {
        }
    }

    private static final class Node<K, V>
    implements Map.Entry<K, V>,
    KeyValue<K, V> {
        protected K key;
        protected V value;
        protected Node<K, V> next;

        private Node() {
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
        public int hashCode() {
            return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e2 = (Map.Entry)obj;
            return (this.key == null ? e2.getKey() == null : this.key.equals(e2.getKey())) && (this.value == null ? e2.getValue() == null : this.value.equals(e2.getValue()));
        }

        @Override
        public V setValue(V obj) {
            V retVal = this.value;
            this.value = obj;
            return retVal;
        }
    }
}

