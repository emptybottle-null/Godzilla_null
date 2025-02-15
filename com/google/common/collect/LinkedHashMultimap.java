/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.LinkedHashMultimapGwtSerializationDependencies;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Platform;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public final class LinkedHashMultimap<K, V>
extends LinkedHashMultimapGwtSerializationDependencies<K, V> {
    private static final int DEFAULT_KEY_CAPACITY = 16;
    private static final int DEFAULT_VALUE_SET_CAPACITY = 2;
    @VisibleForTesting
    static final double VALUE_SET_LOAD_FACTOR = 1.0;
    @VisibleForTesting
    transient int valueSetCapacity = 2;
    private transient ValueEntry<K, V> multimapHeaderEntry;
    @GwtIncompatible
    private static final long serialVersionUID = 1L;

    public static <K, V> LinkedHashMultimap<K, V> create() {
        return new LinkedHashMultimap<K, V>(16, 2);
    }

    public static <K, V> LinkedHashMultimap<K, V> create(int expectedKeys, int expectedValuesPerKey) {
        return new LinkedHashMultimap<K, V>(Maps.capacity(expectedKeys), Maps.capacity(expectedValuesPerKey));
    }

    public static <K, V> LinkedHashMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        LinkedHashMultimap<K, V> result = LinkedHashMultimap.create(multimap.keySet().size(), 2);
        result.putAll((Multimap)multimap);
        return result;
    }

    private static <K, V> void succeedsInValueSet(ValueSetLink<K, V> pred, ValueSetLink<K, V> succ) {
        pred.setSuccessorInValueSet(succ);
        succ.setPredecessorInValueSet(pred);
    }

    private static <K, V> void succeedsInMultimap(ValueEntry<K, V> pred, ValueEntry<K, V> succ) {
        pred.setSuccessorInMultimap(succ);
        succ.setPredecessorInMultimap(pred);
    }

    private static <K, V> void deleteFromValueSet(ValueSetLink<K, V> entry) {
        LinkedHashMultimap.succeedsInValueSet(entry.getPredecessorInValueSet(), entry.getSuccessorInValueSet());
    }

    private static <K, V> void deleteFromMultimap(ValueEntry<K, V> entry) {
        LinkedHashMultimap.succeedsInMultimap(entry.getPredecessorInMultimap(), entry.getSuccessorInMultimap());
    }

    private LinkedHashMultimap(int keyCapacity, int valueSetCapacity) {
        super(Platform.newLinkedHashMapWithExpectedSize(keyCapacity));
        CollectPreconditions.checkNonnegative(valueSetCapacity, "expectedValuesPerKey");
        this.valueSetCapacity = valueSetCapacity;
        this.multimapHeaderEntry = new ValueEntry<Object, Object>(null, null, 0, null);
        LinkedHashMultimap.succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
    }

    @Override
    Set<V> createCollection() {
        return Platform.newLinkedHashSetWithExpectedSize(this.valueSetCapacity);
    }

    @Override
    Collection<V> createCollection(K key) {
        return new ValueSet(key, this.valueSetCapacity);
    }

    @Override
    @CanIgnoreReturnValue
    public Set<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        return super.replaceValues((Object)key, (Iterable)values);
    }

    @Override
    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    Iterator<Map.Entry<K, V>> entryIterator() {
        return new Iterator<Map.Entry<K, V>>(){
            ValueEntry<K, V> nextEntry;
            @Nullable ValueEntry<K, V> toRemove;
            {
                this.nextEntry = ((LinkedHashMultimap)LinkedHashMultimap.this).multimapHeaderEntry.successorInMultimap;
            }

            @Override
            public boolean hasNext() {
                return this.nextEntry != LinkedHashMultimap.this.multimapHeaderEntry;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                ValueEntry result = this.nextEntry;
                this.toRemove = result;
                this.nextEntry = this.nextEntry.successorInMultimap;
                return result;
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.toRemove != null);
                LinkedHashMultimap.this.remove(this.toRemove.getKey(), this.toRemove.getValue());
                this.toRemove = null;
            }
        };
    }

    @Override
    Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return Spliterators.spliterator(this.entries(), 17);
    }

    @Override
    Iterator<V> valueIterator() {
        return Maps.valueIterator(this.entryIterator());
    }

    @Override
    Spliterator<V> valueSpliterator() {
        return CollectSpliterators.map(this.entrySpliterator(), Map.Entry::getValue);
    }

    @Override
    public void clear() {
        super.clear();
        LinkedHashMultimap.succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.keySet().size());
        for (K key : this.keySet()) {
            stream.writeObject(key);
        }
        stream.writeInt(this.size());
        for (Map.Entry entry : this.entries()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.multimapHeaderEntry = new ValueEntry<Object, Object>(null, null, 0, null);
        LinkedHashMultimap.succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
        this.valueSetCapacity = 2;
        int distinctKeys = stream.readInt();
        Map<Object, Collection<V>> map = Platform.newLinkedHashMapWithExpectedSize(12);
        for (int i = 0; i < distinctKeys; ++i) {
            Object key = stream.readObject();
            map.put(key, this.createCollection(key));
        }
        int entries = stream.readInt();
        for (int i = 0; i < entries; ++i) {
            Object key = stream.readObject();
            Object value = stream.readObject();
            ((Collection)map.get(key)).add(value);
        }
        this.setMap(map);
    }

    @VisibleForTesting
    final class ValueSet
    extends Sets.ImprovedAbstractSet<V>
    implements ValueSetLink<K, V> {
        private final K key;
        @VisibleForTesting
        ValueEntry<K, V>[] hashTable;
        private int size = 0;
        private int modCount = 0;
        private ValueSetLink<K, V> firstEntry;
        private ValueSetLink<K, V> lastEntry;

        ValueSet(K key, int expectedValues) {
            this.key = key;
            this.firstEntry = this;
            this.lastEntry = this;
            int tableSize = Hashing.closedTableSize(expectedValues, 1.0);
            ValueEntry[] hashTable = new ValueEntry[tableSize];
            this.hashTable = hashTable;
        }

        private int mask() {
            return this.hashTable.length - 1;
        }

        @Override
        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.lastEntry;
        }

        @Override
        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.firstEntry;
        }

        @Override
        public void setPredecessorInValueSet(ValueSetLink<K, V> entry) {
            this.lastEntry = entry;
        }

        @Override
        public void setSuccessorInValueSet(ValueSetLink<K, V> entry) {
            this.firstEntry = entry;
        }

        @Override
        public Iterator<V> iterator() {
            return new Iterator<V>(){
                ValueSetLink<K, V> nextEntry;
                @Nullable ValueEntry<K, V> toRemove;
                int expectedModCount;
                {
                    this.nextEntry = ValueSet.this.firstEntry;
                    this.expectedModCount = ValueSet.this.modCount;
                }

                private void checkForComodification() {
                    if (ValueSet.this.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public boolean hasNext() {
                    this.checkForComodification();
                    return this.nextEntry != ValueSet.this;
                }

                @Override
                public V next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    ValueEntry entry = (ValueEntry)this.nextEntry;
                    Object result = entry.getValue();
                    this.toRemove = entry;
                    this.nextEntry = entry.getSuccessorInValueSet();
                    return result;
                }

                @Override
                public void remove() {
                    this.checkForComodification();
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    ValueSet.this.remove(this.toRemove.getValue());
                    this.expectedModCount = ValueSet.this.modCount;
                    this.toRemove = null;
                }
            };
        }

        @Override
        public void forEach(Consumer<? super V> action) {
            Preconditions.checkNotNull(action);
            for (ValueSetLink entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
                action.accept(((ValueEntry)entry).getValue());
            }
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            int smearedHash = Hashing.smearedHash(o);
            ValueEntry entry = this.hashTable[smearedHash & this.mask()];
            while (entry != null) {
                if (entry.matchesValue(o, smearedHash)) {
                    return true;
                }
                entry = entry.nextInValueBucket;
            }
            return false;
        }

        @Override
        public boolean add(@Nullable V value) {
            ValueEntry rowHead;
            int smearedHash = Hashing.smearedHash(value);
            int bucket = smearedHash & this.mask();
            ValueEntry entry = rowHead = this.hashTable[bucket];
            while (entry != null) {
                if (entry.matchesValue(value, smearedHash)) {
                    return false;
                }
                entry = entry.nextInValueBucket;
            }
            ValueEntry newEntry = new ValueEntry(this.key, value, smearedHash, rowHead);
            LinkedHashMultimap.succeedsInValueSet(this.lastEntry, newEntry);
            LinkedHashMultimap.succeedsInValueSet(newEntry, this);
            LinkedHashMultimap.succeedsInMultimap(LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap(), newEntry);
            LinkedHashMultimap.succeedsInMultimap(newEntry, LinkedHashMultimap.this.multimapHeaderEntry);
            this.hashTable[bucket] = newEntry;
            ++this.size;
            ++this.modCount;
            this.rehashIfNecessary();
            return true;
        }

        private void rehashIfNecessary() {
            if (Hashing.needsResizing(this.size, this.hashTable.length, 1.0)) {
                ValueEntry[] hashTable = new ValueEntry[this.hashTable.length * 2];
                this.hashTable = hashTable;
                int mask = hashTable.length - 1;
                for (ValueSetLink entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
                    ValueEntry valueEntry = (ValueEntry)entry;
                    int bucket = valueEntry.smearedValueHash & mask;
                    valueEntry.nextInValueBucket = hashTable[bucket];
                    hashTable[bucket] = valueEntry;
                }
            }
        }

        @Override
        @CanIgnoreReturnValue
        public boolean remove(@Nullable Object o) {
            int smearedHash = Hashing.smearedHash(o);
            int bucket = smearedHash & this.mask();
            ValueEntry prev = null;
            ValueEntry entry = this.hashTable[bucket];
            while (entry != null) {
                if (entry.matchesValue(o, smearedHash)) {
                    if (prev == null) {
                        this.hashTable[bucket] = entry.nextInValueBucket;
                    } else {
                        prev.nextInValueBucket = entry.nextInValueBucket;
                    }
                    LinkedHashMultimap.deleteFromValueSet(entry);
                    LinkedHashMultimap.deleteFromMultimap(entry);
                    --this.size;
                    ++this.modCount;
                    return true;
                }
                prev = entry;
                entry = entry.nextInValueBucket;
            }
            return false;
        }

        @Override
        public void clear() {
            Arrays.fill(this.hashTable, null);
            this.size = 0;
            for (ValueSetLink entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
                ValueEntry valueEntry = (ValueEntry)entry;
                LinkedHashMultimap.deleteFromMultimap(valueEntry);
            }
            LinkedHashMultimap.succeedsInValueSet(this, this);
            ++this.modCount;
        }
    }

    @VisibleForTesting
    static final class ValueEntry<K, V>
    extends ImmutableEntry<K, V>
    implements ValueSetLink<K, V> {
        final int smearedValueHash;
        @Nullable ValueEntry<K, V> nextInValueBucket;
        @Nullable ValueSetLink<K, V> predecessorInValueSet;
        @Nullable ValueSetLink<K, V> successorInValueSet;
        @Nullable ValueEntry<K, V> predecessorInMultimap;
        @Nullable ValueEntry<K, V> successorInMultimap;

        ValueEntry(@Nullable K key, @Nullable V value, int smearedValueHash, @Nullable ValueEntry<K, V> nextInValueBucket) {
            super(key, value);
            this.smearedValueHash = smearedValueHash;
            this.nextInValueBucket = nextInValueBucket;
        }

        boolean matchesValue(@Nullable Object v, int smearedVHash) {
            return this.smearedValueHash == smearedVHash && Objects.equal(this.getValue(), v);
        }

        @Override
        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.predecessorInValueSet;
        }

        @Override
        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.successorInValueSet;
        }

        @Override
        public void setPredecessorInValueSet(ValueSetLink<K, V> entry) {
            this.predecessorInValueSet = entry;
        }

        @Override
        public void setSuccessorInValueSet(ValueSetLink<K, V> entry) {
            this.successorInValueSet = entry;
        }

        public ValueEntry<K, V> getPredecessorInMultimap() {
            return this.predecessorInMultimap;
        }

        public ValueEntry<K, V> getSuccessorInMultimap() {
            return this.successorInMultimap;
        }

        public void setSuccessorInMultimap(ValueEntry<K, V> multimapSuccessor) {
            this.successorInMultimap = multimapSuccessor;
        }

        public void setPredecessorInMultimap(ValueEntry<K, V> multimapPredecessor) {
            this.predecessorInMultimap = multimapPredecessor;
        }
    }

    private static interface ValueSetLink<K, V> {
        public ValueSetLink<K, V> getPredecessorInValueSet();

        public ValueSetLink<K, V> getSuccessorInValueSet();

        public void setPredecessorInValueSet(ValueSetLink<K, V> var1);

        public void setSuccessorInValueSet(ValueSetLink<K, V> var1);
    }
}

