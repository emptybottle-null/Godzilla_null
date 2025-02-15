/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.trie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.trie.AbstractBitwiseTrie;
import org.apache.commons.collections4.trie.KeyAnalyzer;

abstract class AbstractPatriciaTrie<K, V>
extends AbstractBitwiseTrie<K, V> {
    private static final long serialVersionUID = 5155253417231339498L;
    private transient TrieEntry<K, V> root = new TrieEntry<Object, Object>(null, null, -1);
    private volatile transient Set<K> keySet;
    private volatile transient Collection<V> values;
    private volatile transient Set<Map.Entry<K, V>> entrySet;
    private transient int size = 0;
    protected transient int modCount = 0;

    protected AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        super(keyAnalyzer);
    }

    protected AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer, Map<? extends K, ? extends V> map) {
        super(keyAnalyzer);
        this.putAll(map);
    }

    @Override
    public void clear() {
        this.root.key = null;
        this.root.bitIndex = -1;
        this.root.value = null;
        this.root.parent = null;
        this.root.left = this.root;
        this.root.right = null;
        this.root.predecessor = this.root;
        this.size = 0;
        this.incrementModCount();
    }

    @Override
    public int size() {
        return this.size;
    }

    void incrementSize() {
        ++this.size;
        this.incrementModCount();
    }

    void decrementSize() {
        --this.size;
        this.incrementModCount();
    }

    private void incrementModCount() {
        ++this.modCount;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        int lengthInBits = this.lengthInBits(key);
        if (lengthInBits == 0) {
            if (this.root.isEmpty()) {
                this.incrementSize();
            } else {
                this.incrementModCount();
            }
            return this.root.setKeyValue(key, value);
        }
        TrieEntry<K, V> found = this.getNearestEntryForKey(key, lengthInBits);
        if (this.compareKeys(key, found.key)) {
            if (found.isEmpty()) {
                this.incrementSize();
            } else {
                this.incrementModCount();
            }
            return found.setKeyValue(key, value);
        }
        int bitIndex = this.bitIndex(key, found.key);
        if (!KeyAnalyzer.isOutOfBoundsIndex(bitIndex)) {
            if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                TrieEntry<K, V> t = new TrieEntry<K, V>(key, value, bitIndex);
                this.addEntry(t, lengthInBits);
                this.incrementSize();
                return null;
            }
            if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                if (this.root.isEmpty()) {
                    this.incrementSize();
                } else {
                    this.incrementModCount();
                }
                return this.root.setKeyValue(key, value);
            }
            if (KeyAnalyzer.isEqualBitKey(bitIndex) && found != this.root) {
                this.incrementModCount();
                return found.setKeyValue(key, value);
            }
        }
        throw new IllegalArgumentException("Failed to put: " + key + " -> " + value + ", " + bitIndex);
    }

    TrieEntry<K, V> addEntry(TrieEntry<K, V> entry, int lengthInBits) {
        TrieEntry current = this.root.left;
        TrieEntry<K, V> path = this.root;
        while (true) {
            if (current.bitIndex >= entry.bitIndex || current.bitIndex <= path.bitIndex) {
                entry.predecessor = entry;
                if (!this.isBitSet(entry.key, entry.bitIndex, lengthInBits)) {
                    entry.left = entry;
                    entry.right = current;
                } else {
                    entry.left = current;
                    entry.right = entry;
                }
                entry.parent = path;
                if (current.bitIndex >= entry.bitIndex) {
                    current.parent = entry;
                }
                if (current.bitIndex <= path.bitIndex) {
                    current.predecessor = entry;
                }
                if (path == this.root || !this.isBitSet(entry.key, path.bitIndex, lengthInBits)) {
                    path.left = entry;
                } else {
                    path.right = entry;
                }
                return entry;
            }
            path = current;
            if (!this.isBitSet(entry.key, current.bitIndex, lengthInBits)) {
                current = current.left;
                continue;
            }
            current = current.right;
        }
    }

    @Override
    public V get(Object k) {
        TrieEntry<K, V> entry = this.getEntry(k);
        return entry != null ? (V)entry.getValue() : null;
    }

    TrieEntry<K, V> getEntry(Object k) {
        Object key = this.castKey(k);
        if (key == null) {
            return null;
        }
        int lengthInBits = this.lengthInBits(key);
        TrieEntry entry = this.getNearestEntryForKey(key, lengthInBits);
        return !entry.isEmpty() && this.compareKeys(key, entry.key) ? entry : null;
    }

    public Map.Entry<K, V> select(K key) {
        Reference<Map.Entry<K, V>> reference;
        int lengthInBits = this.lengthInBits(key);
        if (!this.selectR(this.root.left, -1, key, lengthInBits, reference = new Reference<Map.Entry<K, V>>())) {
            return reference.get();
        }
        return null;
    }

    public K selectKey(K key) {
        Map.Entry<K, V> entry = this.select(key);
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public V selectValue(K key) {
        Map.Entry<K, V> entry = this.select(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    private boolean selectR(TrieEntry<K, V> h, int bitIndex, K key, int lengthInBits, Reference<Map.Entry<K, V>> reference) {
        if (h.bitIndex <= bitIndex) {
            if (!h.isEmpty()) {
                reference.set(h);
                return false;
            }
            return true;
        }
        if (!this.isBitSet(key, h.bitIndex, lengthInBits)) {
            if (this.selectR(h.left, h.bitIndex, key, lengthInBits, reference)) {
                return this.selectR(h.right, h.bitIndex, key, lengthInBits, reference);
            }
        } else if (this.selectR(h.right, h.bitIndex, key, lengthInBits, reference)) {
            return this.selectR(h.left, h.bitIndex, key, lengthInBits, reference);
        }
        return false;
    }

    @Override
    public boolean containsKey(Object k) {
        int lengthInBits;
        if (k == null) {
            return false;
        }
        Object key = this.castKey(k);
        TrieEntry entry = this.getNearestEntryForKey(key, lengthInBits = this.lengthInBits(key));
        return !entry.isEmpty() && this.compareKeys(key, entry.key);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet();
        }
        return this.entrySet;
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet();
        }
        return this.keySet;
    }

    @Override
    public Collection<V> values() {
        if (this.values == null) {
            this.values = new Values();
        }
        return this.values;
    }

    @Override
    public V remove(Object k) {
        if (k == null) {
            return null;
        }
        Object key = this.castKey(k);
        int lengthInBits = this.lengthInBits(key);
        TrieEntry current = this.root.left;
        TrieEntry<K, V> path = this.root;
        while (true) {
            if (current.bitIndex <= path.bitIndex) {
                if (!current.isEmpty() && this.compareKeys(key, current.key)) {
                    return this.removeEntry(current);
                }
                return null;
            }
            path = current;
            if (!this.isBitSet(key, current.bitIndex, lengthInBits)) {
                current = current.left;
                continue;
            }
            current = current.right;
        }
    }

    TrieEntry<K, V> getNearestEntryForKey(K key, int lengthInBits) {
        TrieEntry current = this.root.left;
        TrieEntry<K, V> path = this.root;
        while (current.bitIndex > path.bitIndex) {
            path = current;
            if (!this.isBitSet(key, current.bitIndex, lengthInBits)) {
                current = current.left;
                continue;
            }
            current = current.right;
        }
        return current;
    }

    V removeEntry(TrieEntry<K, V> h) {
        if (h != this.root) {
            if (h.isInternalNode()) {
                this.removeInternalEntry(h);
            } else {
                this.removeExternalEntry(h);
            }
        }
        this.decrementSize();
        return h.setKeyValue(null, null);
    }

    private void removeExternalEntry(TrieEntry<K, V> h) {
        TrieEntry child;
        if (h == this.root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        }
        if (!h.isExternalNode()) {
            throw new IllegalArgumentException(h + " is not an external Entry!");
        }
        TrieEntry parent = h.parent;
        TrieEntry trieEntry = child = h.left == h ? h.right : h.left;
        if (parent.left == h) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child.bitIndex > parent.bitIndex) {
            child.parent = parent;
        } else {
            child.predecessor = parent;
        }
    }

    private void removeInternalEntry(TrieEntry<K, V> h) {
        TrieEntry child;
        if (h == this.root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        }
        if (!h.isInternalNode()) {
            throw new IllegalArgumentException(h + " is not an internal Entry!");
        }
        TrieEntry p = h.predecessor;
        p.bitIndex = h.bitIndex;
        TrieEntry parent = p.parent;
        TrieEntry trieEntry = child = p.left == h ? p.right : p.left;
        if (p.predecessor == p && p.parent != h) {
            p.predecessor = p.parent;
        }
        if (parent.left == p) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child.bitIndex > parent.bitIndex) {
            child.parent = parent;
        }
        if (h.left.parent == h) {
            h.left.parent = p;
        }
        if (h.right.parent == h) {
            h.right.parent = p;
        }
        if (h.parent.left == h) {
            h.parent.left = p;
        } else {
            h.parent.right = p;
        }
        p.parent = h.parent;
        p.left = h.left;
        p.right = h.right;
        if (AbstractPatriciaTrie.isValidUplink(p.left, p)) {
            p.left.predecessor = p;
        }
        if (AbstractPatriciaTrie.isValidUplink(p.right, p)) {
            p.right.predecessor = p;
        }
    }

    TrieEntry<K, V> nextEntry(TrieEntry<K, V> node) {
        if (node == null) {
            return this.firstEntry();
        }
        return this.nextEntryImpl(node.predecessor, node, null);
    }

    TrieEntry<K, V> nextEntryImpl(TrieEntry<K, V> start, TrieEntry<K, V> previous, TrieEntry<K, V> tree) {
        TrieEntry<K, V> current = start;
        if (previous == null || start != previous.predecessor) {
            while (!current.left.isEmpty() && previous != current.left) {
                if (AbstractPatriciaTrie.isValidUplink(current.left, current)) {
                    return current.left;
                }
                current = current.left;
            }
        }
        if (current.isEmpty()) {
            return null;
        }
        if (current.right == null) {
            return null;
        }
        if (previous != current.right) {
            if (AbstractPatriciaTrie.isValidUplink(current.right, current)) {
                return current.right;
            }
            return this.nextEntryImpl(current.right, previous, tree);
        }
        while (current == current.parent.right) {
            if (current == tree) {
                return null;
            }
            current = current.parent;
        }
        if (current == tree) {
            return null;
        }
        if (current.parent.right == null) {
            return null;
        }
        if (previous != current.parent.right && AbstractPatriciaTrie.isValidUplink(current.parent.right, current.parent)) {
            return current.parent.right;
        }
        if (current.parent.right == current.parent) {
            return null;
        }
        return this.nextEntryImpl(current.parent.right, previous, tree);
    }

    TrieEntry<K, V> firstEntry() {
        if (this.isEmpty()) {
            return null;
        }
        return this.followLeft(this.root);
    }

    TrieEntry<K, V> followLeft(TrieEntry<K, V> node) {
        while (true) {
            TrieEntry child;
            if ((child = node.left).isEmpty()) {
                child = node.right;
            }
            if (child.bitIndex <= node.bitIndex) {
                return child;
            }
            node = child;
        }
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.getKeyAnalyzer();
    }

    @Override
    public K firstKey() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return this.firstEntry().getKey();
    }

    @Override
    public K lastKey() {
        TrieEntry<K, V> entry = this.lastEntry();
        if (entry != null) {
            return entry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override
    public K nextKey(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        TrieEntry<K, V> entry = this.getEntry(key);
        if (entry != null) {
            TrieEntry<K, V> nextEntry = this.nextEntry(entry);
            return nextEntry != null ? (K)nextEntry.getKey() : null;
        }
        return null;
    }

    @Override
    public K previousKey(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        TrieEntry<K, V> entry = this.getEntry(key);
        if (entry != null) {
            TrieEntry<K, V> prevEntry = this.previousEntry(entry);
            return prevEntry != null ? (K)prevEntry.getKey() : null;
        }
        return null;
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        return new TrieMapIterator();
    }

    @Override
    public SortedMap<K, V> prefixMap(K key) {
        return this.getPrefixMapByBits(key, 0, this.lengthInBits(key));
    }

    private SortedMap<K, V> getPrefixMapByBits(K key, int offsetInBits, int lengthInBits) {
        int offsetLength = offsetInBits + lengthInBits;
        if (offsetLength > this.lengthInBits(key)) {
            throw new IllegalArgumentException(offsetInBits + " + " + lengthInBits + " > " + this.lengthInBits(key));
        }
        if (offsetLength == 0) {
            return this;
        }
        return new PrefixRangeMap(key, offsetInBits, lengthInBits);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return new RangeEntryMap(null, toKey);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return new RangeEntryMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return new RangeEntryMap(fromKey, null);
    }

    TrieEntry<K, V> higherEntry(K key) {
        int lengthInBits = this.lengthInBits(key);
        if (lengthInBits == 0) {
            if (!this.root.isEmpty()) {
                if (this.size() > 1) {
                    return this.nextEntry(this.root);
                }
                return null;
            }
            return this.firstEntry();
        }
        TrieEntry<K, V> found = this.getNearestEntryForKey(key, lengthInBits);
        if (this.compareKeys(key, found.key)) {
            return this.nextEntry(found);
        }
        int bitIndex = this.bitIndex(key, found.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, Object> added = new TrieEntry<K, Object>(key, null, bitIndex);
            this.addEntry(added, lengthInBits);
            this.incrementSize();
            TrieEntry<K, Object> ceil = this.nextEntry(added);
            this.removeEntry(added);
            this.modCount -= 2;
            return ceil;
        }
        if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!this.root.isEmpty()) {
                return this.firstEntry();
            }
            if (this.size() > 1) {
                return this.nextEntry(this.firstEntry());
            }
            return null;
        }
        if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return this.nextEntry(found);
        }
        throw new IllegalStateException("invalid lookup: " + key);
    }

    TrieEntry<K, V> ceilingEntry(K key) {
        int lengthInBits = this.lengthInBits(key);
        if (lengthInBits == 0) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return this.firstEntry();
        }
        TrieEntry<K, V> found = this.getNearestEntryForKey(key, lengthInBits);
        if (this.compareKeys(key, found.key)) {
            return found;
        }
        int bitIndex = this.bitIndex(key, found.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, Object> added = new TrieEntry<K, Object>(key, null, bitIndex);
            this.addEntry(added, lengthInBits);
            this.incrementSize();
            TrieEntry<K, Object> ceil = this.nextEntry(added);
            this.removeEntry(added);
            this.modCount -= 2;
            return ceil;
        }
        if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return this.firstEntry();
        }
        if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return found;
        }
        throw new IllegalStateException("invalid lookup: " + key);
    }

    TrieEntry<K, V> lowerEntry(K key) {
        int lengthInBits = this.lengthInBits(key);
        if (lengthInBits == 0) {
            return null;
        }
        TrieEntry<K, V> found = this.getNearestEntryForKey(key, lengthInBits);
        if (this.compareKeys(key, found.key)) {
            return this.previousEntry(found);
        }
        int bitIndex = this.bitIndex(key, found.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, Object> added = new TrieEntry<K, Object>(key, null, bitIndex);
            this.addEntry(added, lengthInBits);
            this.incrementSize();
            TrieEntry<K, Object> prior = this.previousEntry(added);
            this.removeEntry(added);
            this.modCount -= 2;
            return prior;
        }
        if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            return null;
        }
        if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return this.previousEntry(found);
        }
        throw new IllegalStateException("invalid lookup: " + key);
    }

    TrieEntry<K, V> floorEntry(K key) {
        int lengthInBits = this.lengthInBits(key);
        if (lengthInBits == 0) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return null;
        }
        TrieEntry<K, V> found = this.getNearestEntryForKey(key, lengthInBits);
        if (this.compareKeys(key, found.key)) {
            return found;
        }
        int bitIndex = this.bitIndex(key, found.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, Object> added = new TrieEntry<K, Object>(key, null, bitIndex);
            this.addEntry(added, lengthInBits);
            this.incrementSize();
            TrieEntry<K, Object> floor = this.previousEntry(added);
            this.removeEntry(added);
            this.modCount -= 2;
            return floor;
        }
        if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return null;
        }
        if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return found;
        }
        throw new IllegalStateException("invalid lookup: " + key);
    }

    TrieEntry<K, V> subtree(K prefix, int offsetInBits, int lengthInBits) {
        TrieEntry<K, V> entry;
        TrieEntry current = this.root.left;
        TrieEntry<K, V> path = this.root;
        while (current.bitIndex > path.bitIndex && lengthInBits > current.bitIndex) {
            path = current;
            if (!this.isBitSet(prefix, offsetInBits + current.bitIndex, offsetInBits + lengthInBits)) {
                current = current.left;
                continue;
            }
            current = current.right;
        }
        TrieEntry<K, V> trieEntry = entry = current.isEmpty() ? path : current;
        if (entry.isEmpty()) {
            return null;
        }
        int endIndexInBits = offsetInBits + lengthInBits;
        if (entry == this.root && this.lengthInBits(entry.getKey()) < endIndexInBits) {
            return null;
        }
        if (this.isBitSet(prefix, endIndexInBits - 1, endIndexInBits) != this.isBitSet(entry.key, lengthInBits - 1, this.lengthInBits(entry.key))) {
            return null;
        }
        int bitIndex = this.getKeyAnalyzer().bitIndex(prefix, offsetInBits, lengthInBits, entry.key, 0, this.lengthInBits(entry.getKey()));
        if (bitIndex >= 0 && bitIndex < lengthInBits) {
            return null;
        }
        return entry;
    }

    TrieEntry<K, V> lastEntry() {
        return this.followRight(this.root.left);
    }

    TrieEntry<K, V> followRight(TrieEntry<K, V> node) {
        if (node.right == null) {
            return null;
        }
        while (node.right.bitIndex > node.bitIndex) {
            node = node.right;
        }
        return node.right;
    }

    TrieEntry<K, V> previousEntry(TrieEntry<K, V> start) {
        if (start.predecessor == null) {
            throw new IllegalArgumentException("must have come from somewhere!");
        }
        if (start.predecessor.right == start) {
            if (AbstractPatriciaTrie.isValidUplink(start.predecessor.left, start.predecessor)) {
                return start.predecessor.left;
            }
            return this.followRight(start.predecessor.left);
        }
        TrieEntry node = start.predecessor;
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        if (node.parent == null) {
            return null;
        }
        if (AbstractPatriciaTrie.isValidUplink(node.parent.left, node.parent)) {
            if (node.parent.left == this.root) {
                if (this.root.isEmpty()) {
                    return null;
                }
                return this.root;
            }
            return node.parent.left;
        }
        return this.followRight(node.parent.left);
    }

    TrieEntry<K, V> nextEntryInSubtree(TrieEntry<K, V> node, TrieEntry<K, V> parentOfSubtree) {
        if (node == null) {
            return this.firstEntry();
        }
        return this.nextEntryImpl(node.predecessor, node, parentOfSubtree);
    }

    static boolean isValidUplink(TrieEntry<?, ?> next, TrieEntry<?, ?> from) {
        return next != null && next.bitIndex <= from.bitIndex && !next.isEmpty();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.root = new TrieEntry<Object, Object>(null, null, -1);
        int size = stream.readInt();
        for (int i = 0; i < size; ++i) {
            Object k = stream.readObject();
            Object v = stream.readObject();
            this.put(k, v);
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size());
        for (Map.Entry<K, V> entry : this.entrySet()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    private final class PrefixRangeEntrySet
    extends RangeEntrySet {
        private final PrefixRangeMap delegate;
        private TrieEntry<K, V> prefixStart;
        private int expectedModCount;

        public PrefixRangeEntrySet(PrefixRangeMap delegate) {
            super(delegate);
            this.expectedModCount = 0;
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return this.delegate.fixup();
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            if (AbstractPatriciaTrie.this.modCount != this.expectedModCount) {
                this.prefixStart = AbstractPatriciaTrie.this.subtree(this.delegate.prefix, this.delegate.offsetInBits, this.delegate.lengthInBits);
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            if (this.prefixStart == null) {
                Set empty = Collections.emptySet();
                return empty.iterator();
            }
            if (this.delegate.lengthInBits > this.prefixStart.bitIndex) {
                return new SingletonIterator(this.prefixStart);
            }
            return new EntryIterator(this.prefixStart, this.delegate.prefix, this.delegate.offsetInBits, this.delegate.lengthInBits);
        }

        private final class EntryIterator
        extends TrieIterator<Map.Entry<K, V>> {
            private final K prefix;
            private final int offset;
            private final int lengthInBits;
            private boolean lastOne;
            private TrieEntry<K, V> subtree;

            EntryIterator(TrieEntry<K, V> startScan, K prefix, int offset, int lengthInBits) {
                this.subtree = startScan;
                this.next = AbstractPatriciaTrie.this.followLeft(startScan);
                this.prefix = prefix;
                this.offset = offset;
                this.lengthInBits = lengthInBits;
            }

            @Override
            public Map.Entry<K, V> next() {
                TrieEntry entry = this.nextEntry();
                if (this.lastOne) {
                    this.next = null;
                }
                return entry;
            }

            @Override
            protected TrieEntry<K, V> findNext(TrieEntry<K, V> prior) {
                return AbstractPatriciaTrie.this.nextEntryInSubtree(prior, this.subtree);
            }

            @Override
            public void remove() {
                boolean needsFixing = false;
                int bitIdx = this.subtree.bitIndex;
                if (this.current == this.subtree) {
                    needsFixing = true;
                }
                super.remove();
                if (bitIdx != this.subtree.bitIndex || needsFixing) {
                    this.subtree = AbstractPatriciaTrie.this.subtree(this.prefix, this.offset, this.lengthInBits);
                }
                if (this.lengthInBits >= this.subtree.bitIndex) {
                    this.lastOne = true;
                }
            }
        }

        private final class SingletonIterator
        implements Iterator<Map.Entry<K, V>> {
            private final TrieEntry<K, V> entry;
            private int hit = 0;

            public SingletonIterator(TrieEntry<K, V> entry) {
                this.entry = entry;
            }

            @Override
            public boolean hasNext() {
                return this.hit == 0;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (this.hit != 0) {
                    throw new NoSuchElementException();
                }
                ++this.hit;
                return this.entry;
            }

            @Override
            public void remove() {
                if (this.hit != 1) {
                    throw new IllegalStateException();
                }
                ++this.hit;
                AbstractPatriciaTrie.this.removeEntry(this.entry);
            }
        }
    }

    private class PrefixRangeMap
    extends RangeMap {
        private final K prefix;
        private final int offsetInBits;
        private final int lengthInBits;
        private K fromKey;
        private K toKey;
        private transient int expectedModCount;
        private int size;

        private PrefixRangeMap(K prefix, int offsetInBits, int lengthInBits) {
            this.fromKey = null;
            this.toKey = null;
            this.expectedModCount = 0;
            this.size = -1;
            this.prefix = prefix;
            this.offsetInBits = offsetInBits;
            this.lengthInBits = lengthInBits;
        }

        private int fixup() {
            if (this.size == -1 || AbstractPatriciaTrie.this.modCount != this.expectedModCount) {
                Iterator it = super.entrySet().iterator();
                this.size = 0;
                Map.Entry entry = null;
                if (it.hasNext()) {
                    entry = it.next();
                    this.size = 1;
                }
                Object v0 = (this.fromKey = entry == null ? null : entry.getKey());
                if (this.fromKey != null) {
                    TrieEntry prior = AbstractPatriciaTrie.this.previousEntry((TrieEntry)entry);
                    this.fromKey = prior == null ? null : prior.getKey();
                }
                this.toKey = this.fromKey;
                while (it.hasNext()) {
                    ++this.size;
                    entry = it.next();
                }
                this.toKey = entry == null ? null : entry.getKey();
                Object v1 = this.toKey;
                if (this.toKey != null) {
                    this.toKey = (entry = AbstractPatriciaTrie.this.nextEntry((TrieEntry)entry)) == null ? null : entry.getKey();
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }

        @Override
        public K firstKey() {
            Object first;
            this.fixup();
            TrieEntry e = null;
            e = this.fromKey == null ? AbstractPatriciaTrie.this.firstEntry() : AbstractPatriciaTrie.this.higherEntry(this.fromKey);
            Object k = first = e != null ? (Object)e.getKey() : null;
            if (e == null || !AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, first)) {
                throw new NoSuchElementException();
            }
            return first;
        }

        @Override
        public K lastKey() {
            Object last;
            this.fixup();
            TrieEntry e = null;
            e = this.toKey == null ? AbstractPatriciaTrie.this.lastEntry() : AbstractPatriciaTrie.this.lowerEntry(this.toKey);
            Object k = last = e != null ? (Object)e.getKey() : null;
            if (e == null || !AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, last)) {
                throw new NoSuchElementException();
            }
            return last;
        }

        @Override
        protected boolean inRange(K key) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, key);
        }

        @Override
        protected boolean inRange2(K key) {
            return this.inRange(key);
        }

        @Override
        protected boolean inFromRange(K key, boolean forceInclusive) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, key);
        }

        @Override
        protected boolean inToRange(K key, boolean forceInclusive) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, key);
        }

        @Override
        protected Set<Map.Entry<K, V>> createEntrySet() {
            return new PrefixRangeEntrySet(this);
        }

        @Override
        public K getFromKey() {
            return this.fromKey;
        }

        @Override
        public K getToKey() {
            return this.toKey;
        }

        @Override
        public boolean isFromInclusive() {
            return false;
        }

        @Override
        public boolean isToInclusive() {
            return false;
        }

        @Override
        protected SortedMap<K, V> createRangeMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return new RangeEntryMap(fromKey, fromInclusive, toKey, toInclusive);
        }

        @Override
        public void clear() {
            Iterator it = AbstractPatriciaTrie.this.entrySet().iterator();
            Set currentKeys = this.keySet();
            while (it.hasNext()) {
                if (!currentKeys.contains(it.next().getKey())) continue;
                it.remove();
            }
        }
    }

    private class RangeEntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        private final RangeMap delegate;
        private transient int size = -1;
        private transient int expectedModCount;

        public RangeEntrySet(RangeMap delegate) {
            if (delegate == null) {
                throw new NullPointerException("delegate");
            }
            this.delegate = delegate;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            Object fromKey = this.delegate.getFromKey();
            Object toKey = this.delegate.getToKey();
            TrieEntry first = null;
            first = fromKey == null ? AbstractPatriciaTrie.this.firstEntry() : AbstractPatriciaTrie.this.ceilingEntry(fromKey);
            TrieEntry last = null;
            if (toKey != null) {
                last = AbstractPatriciaTrie.this.ceilingEntry(toKey);
            }
            return new EntryIterator(first, last);
        }

        @Override
        public int size() {
            if (this.size == -1 || this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                this.size = 0;
                Iterator it = this.iterator();
                while (it.hasNext()) {
                    ++this.size;
                    it.next();
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }

        @Override
        public boolean isEmpty() {
            return !this.iterator().hasNext();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)o;
            Object key = entry.getKey();
            if (!this.delegate.inRange(key)) {
                return false;
            }
            TrieEntry node = AbstractPatriciaTrie.this.getEntry(key);
            return node != null && AbstractBitwiseTrie.compare(node.getValue(), entry.getValue());
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)o;
            Object key = entry.getKey();
            if (!this.delegate.inRange(key)) {
                return false;
            }
            TrieEntry node = AbstractPatriciaTrie.this.getEntry(key);
            if (node != null && AbstractBitwiseTrie.compare(node.getValue(), entry.getValue())) {
                AbstractPatriciaTrie.this.removeEntry(node);
                return true;
            }
            return false;
        }

        private final class EntryIterator
        extends TrieIterator<Map.Entry<K, V>> {
            private final K excludedKey;

            private EntryIterator(TrieEntry<K, V> first, TrieEntry<K, V> last) {
                super(first);
                this.excludedKey = last != null ? last.getKey() : null;
            }

            @Override
            public boolean hasNext() {
                return this.next != null && !AbstractBitwiseTrie.compare(this.next.key, this.excludedKey);
            }

            @Override
            public Map.Entry<K, V> next() {
                if (this.next == null || AbstractBitwiseTrie.compare(this.next.key, this.excludedKey)) {
                    throw new NoSuchElementException();
                }
                return this.nextEntry();
            }
        }
    }

    private class RangeEntryMap
    extends RangeMap {
        private final K fromKey;
        private final K toKey;
        private final boolean fromInclusive;
        private final boolean toInclusive;

        protected RangeEntryMap(K fromKey, K toKey) {
            this(fromKey, true, toKey, false);
        }

        protected RangeEntryMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            if (fromKey == null && toKey == null) {
                throw new IllegalArgumentException("must have a from or to!");
            }
            if (fromKey != null && toKey != null && AbstractPatriciaTrie.this.getKeyAnalyzer().compare(fromKey, toKey) > 0) {
                throw new IllegalArgumentException("fromKey > toKey");
            }
            this.fromKey = fromKey;
            this.fromInclusive = fromInclusive;
            this.toKey = toKey;
            this.toInclusive = toInclusive;
        }

        @Override
        public K firstKey() {
            Object first;
            TrieEntry e = null;
            e = this.fromKey == null ? AbstractPatriciaTrie.this.firstEntry() : (this.fromInclusive ? AbstractPatriciaTrie.this.ceilingEntry(this.fromKey) : AbstractPatriciaTrie.this.higherEntry(this.fromKey));
            Object k = first = e != null ? (Object)e.getKey() : null;
            if (e == null || this.toKey != null && !this.inToRange(first, false)) {
                throw new NoSuchElementException();
            }
            return first;
        }

        @Override
        public K lastKey() {
            Object last;
            TrieEntry e = this.toKey == null ? AbstractPatriciaTrie.this.lastEntry() : (this.toInclusive ? AbstractPatriciaTrie.this.floorEntry(this.toKey) : AbstractPatriciaTrie.this.lowerEntry(this.toKey));
            Object k = last = e != null ? (Object)e.getKey() : null;
            if (e == null || this.fromKey != null && !this.inFromRange(last, false)) {
                throw new NoSuchElementException();
            }
            return last;
        }

        @Override
        protected Set<Map.Entry<K, V>> createEntrySet() {
            return new RangeEntrySet(this);
        }

        @Override
        public K getFromKey() {
            return this.fromKey;
        }

        @Override
        public K getToKey() {
            return this.toKey;
        }

        @Override
        public boolean isFromInclusive() {
            return this.fromInclusive;
        }

        @Override
        public boolean isToInclusive() {
            return this.toInclusive;
        }

        @Override
        protected SortedMap<K, V> createRangeMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return new RangeEntryMap(fromKey, fromInclusive, toKey, toInclusive);
        }
    }

    private abstract class RangeMap
    extends AbstractMap<K, V>
    implements SortedMap<K, V> {
        private volatile transient Set<Map.Entry<K, V>> entrySet;

        private RangeMap() {
        }

        protected abstract Set<Map.Entry<K, V>> createEntrySet();

        protected abstract K getFromKey();

        protected abstract boolean isFromInclusive();

        protected abstract K getToKey();

        protected abstract boolean isToInclusive();

        @Override
        public Comparator<? super K> comparator() {
            return AbstractPatriciaTrie.this.comparator();
        }

        @Override
        public boolean containsKey(Object key) {
            if (!this.inRange(AbstractPatriciaTrie.this.castKey(key))) {
                return false;
            }
            return AbstractPatriciaTrie.this.containsKey(key);
        }

        @Override
        public V remove(Object key) {
            if (!this.inRange(AbstractPatriciaTrie.this.castKey(key))) {
                return null;
            }
            return AbstractPatriciaTrie.this.remove(key);
        }

        @Override
        public V get(Object key) {
            if (!this.inRange(AbstractPatriciaTrie.this.castKey(key))) {
                return null;
            }
            return AbstractPatriciaTrie.this.get(key);
        }

        @Override
        public V put(K key, V value) {
            if (!this.inRange(key)) {
                throw new IllegalArgumentException("Key is out of range: " + key);
            }
            return AbstractPatriciaTrie.this.put(key, value);
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = this.createEntrySet();
            }
            return this.entrySet;
        }

        @Override
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            if (!this.inRange2(fromKey)) {
                throw new IllegalArgumentException("FromKey is out of range: " + fromKey);
            }
            if (!this.inRange2(toKey)) {
                throw new IllegalArgumentException("ToKey is out of range: " + toKey);
            }
            return this.createRangeMap(fromKey, this.isFromInclusive(), toKey, this.isToInclusive());
        }

        @Override
        public SortedMap<K, V> headMap(K toKey) {
            if (!this.inRange2(toKey)) {
                throw new IllegalArgumentException("ToKey is out of range: " + toKey);
            }
            return this.createRangeMap(this.getFromKey(), this.isFromInclusive(), toKey, this.isToInclusive());
        }

        @Override
        public SortedMap<K, V> tailMap(K fromKey) {
            if (!this.inRange2(fromKey)) {
                throw new IllegalArgumentException("FromKey is out of range: " + fromKey);
            }
            return this.createRangeMap(fromKey, this.isFromInclusive(), this.getToKey(), this.isToInclusive());
        }

        protected boolean inRange(K key) {
            Object fromKey = this.getFromKey();
            Object toKey = this.getToKey();
            return !(fromKey != null && !this.inFromRange(key, false) || toKey != null && !this.inToRange(key, false));
        }

        protected boolean inRange2(K key) {
            Object fromKey = this.getFromKey();
            Object toKey = this.getToKey();
            return !(fromKey != null && !this.inFromRange(key, false) || toKey != null && !this.inToRange(key, true));
        }

        protected boolean inFromRange(K key, boolean forceInclusive) {
            Object fromKey = this.getFromKey();
            boolean fromInclusive = this.isFromInclusive();
            int ret = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(key, fromKey);
            if (fromInclusive || forceInclusive) {
                return ret >= 0;
            }
            return ret > 0;
        }

        protected boolean inToRange(K key, boolean forceInclusive) {
            Object toKey = this.getToKey();
            boolean toInclusive = this.isToInclusive();
            int ret = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(key, toKey);
            if (toInclusive || forceInclusive) {
                return ret <= 0;
            }
            return ret < 0;
        }

        protected abstract SortedMap<K, V> createRangeMap(K var1, boolean var2, K var3, boolean var4);
    }

    private class TrieMapIterator
    extends TrieIterator<K>
    implements OrderedMapIterator<K, V> {
        protected TrieEntry<K, V> previous;

        private TrieMapIterator() {
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }

        @Override
        public K getKey() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.getKey();
        }

        @Override
        public V getValue() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.getValue();
        }

        @Override
        public V setValue(V value) {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.setValue(value);
        }

        @Override
        public boolean hasPrevious() {
            return this.previous != null;
        }

        @Override
        public K previous() {
            return this.previousEntry().getKey();
        }

        @Override
        protected TrieEntry<K, V> nextEntry() {
            TrieEntry nextEntry = super.nextEntry();
            this.previous = nextEntry;
            return nextEntry;
        }

        protected TrieEntry<K, V> previousEntry() {
            if (this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                throw new ConcurrentModificationException();
            }
            TrieEntry e = this.previous;
            if (e == null) {
                throw new NoSuchElementException();
            }
            this.previous = AbstractPatriciaTrie.this.previousEntry(e);
            this.next = this.current;
            this.current = e;
            return this.current;
        }
    }

    abstract class TrieIterator<E>
    implements Iterator<E> {
        protected int expectedModCount;
        protected TrieEntry<K, V> next;
        protected TrieEntry<K, V> current;

        protected TrieIterator() {
            this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            this.next = AbstractPatriciaTrie.this.nextEntry(null);
        }

        protected TrieIterator(TrieEntry<K, V> firstEntry) {
            this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            this.next = firstEntry;
        }

        protected TrieEntry<K, V> nextEntry() {
            if (this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                throw new ConcurrentModificationException();
            }
            TrieEntry e = this.next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            this.next = this.findNext(e);
            this.current = e;
            return e;
        }

        protected TrieEntry<K, V> findNext(TrieEntry<K, V> prior) {
            return AbstractPatriciaTrie.this.nextEntry(prior);
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            if (this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                throw new ConcurrentModificationException();
            }
            TrieEntry node = this.current;
            this.current = null;
            AbstractPatriciaTrie.this.removeEntry(node);
            this.expectedModCount = AbstractPatriciaTrie.this.modCount;
        }
    }

    private class Values
    extends AbstractCollection<V> {
        private Values() {
        }

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public boolean contains(Object o) {
            return AbstractPatriciaTrie.this.containsValue(o);
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        @Override
        public boolean remove(Object o) {
            Iterator it = this.iterator();
            while (it.hasNext()) {
                Object value = it.next();
                if (!AbstractBitwiseTrie.compare(value, o)) continue;
                it.remove();
                return true;
            }
            return false;
        }

        private class ValueIterator
        extends TrieIterator<V> {
            private ValueIterator() {
            }

            @Override
            public V next() {
                return this.nextEntry().getValue();
            }
        }
    }

    private class KeySet
    extends AbstractSet<K> {
        private KeySet() {
        }

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public boolean contains(Object o) {
            return AbstractPatriciaTrie.this.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            int size = this.size();
            AbstractPatriciaTrie.this.remove(o);
            return size != this.size();
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        private class KeyIterator
        extends TrieIterator<K> {
            private KeyIterator() {
            }

            @Override
            public K next() {
                return this.nextEntry().getKey();
            }
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
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            TrieEntry candidate = AbstractPatriciaTrie.this.getEntry(((Map.Entry)o).getKey());
            return candidate != null && candidate.equals(o);
        }

        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            if (!this.contains(obj)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            AbstractPatriciaTrie.this.remove(entry.getKey());
            return true;
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        private class EntryIterator
        extends TrieIterator<Map.Entry<K, V>> {
            private EntryIterator() {
            }

            @Override
            public Map.Entry<K, V> next() {
                return this.nextEntry();
            }
        }
    }

    protected static class TrieEntry<K, V>
    extends AbstractBitwiseTrie.BasicEntry<K, V> {
        private static final long serialVersionUID = 4596023148184140013L;
        protected int bitIndex;
        protected TrieEntry<K, V> parent;
        protected TrieEntry<K, V> left;
        protected TrieEntry<K, V> right;
        protected TrieEntry<K, V> predecessor;

        public TrieEntry(K key, V value, int bitIndex) {
            super(key, value);
            this.bitIndex = bitIndex;
            this.parent = null;
            this.left = this;
            this.right = null;
            this.predecessor = this;
        }

        public boolean isEmpty() {
            return this.key == null;
        }

        public boolean isInternalNode() {
            return this.left != this && this.right != this;
        }

        public boolean isExternalNode() {
            return !this.isInternalNode();
        }

        @Override
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            if (this.bitIndex == -1) {
                buffer.append("RootEntry(");
            } else {
                buffer.append("Entry(");
            }
            buffer.append("key=").append(this.getKey()).append(" [").append(this.bitIndex).append("], ");
            buffer.append("value=").append(this.getValue()).append(", ");
            if (this.parent != null) {
                if (this.parent.bitIndex == -1) {
                    buffer.append("parent=").append("ROOT");
                } else {
                    buffer.append("parent=").append(this.parent.getKey()).append(" [").append(this.parent.bitIndex).append("]");
                }
            } else {
                buffer.append("parent=").append("null");
            }
            buffer.append(", ");
            if (this.left != null) {
                if (this.left.bitIndex == -1) {
                    buffer.append("left=").append("ROOT");
                } else {
                    buffer.append("left=").append(this.left.getKey()).append(" [").append(this.left.bitIndex).append("]");
                }
            } else {
                buffer.append("left=").append("null");
            }
            buffer.append(", ");
            if (this.right != null) {
                if (this.right.bitIndex == -1) {
                    buffer.append("right=").append("ROOT");
                } else {
                    buffer.append("right=").append(this.right.getKey()).append(" [").append(this.right.bitIndex).append("]");
                }
            } else {
                buffer.append("right=").append("null");
            }
            buffer.append(", ");
            if (this.predecessor != null) {
                if (this.predecessor.bitIndex == -1) {
                    buffer.append("predecessor=").append("ROOT");
                } else {
                    buffer.append("predecessor=").append(this.predecessor.getKey()).append(" [").append(this.predecessor.bitIndex).append("]");
                }
            }
            buffer.append(")");
            return buffer.toString();
        }
    }

    private static class Reference<E> {
        private E item;

        private Reference() {
        }

        public void set(E item) {
            this.item = item;
        }

        public E get() {
            return this.item;
        }
    }
}

