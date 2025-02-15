/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public final class ConcurrentHashMultiset<E>
extends AbstractMultiset<E>
implements Serializable {
    private final transient ConcurrentMap<E, AtomicInteger> countMap;
    private static final long serialVersionUID = 1L;

    public static <E> ConcurrentHashMultiset<E> create() {
        return new ConcurrentHashMultiset(new ConcurrentHashMap());
    }

    public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> elements) {
        ConcurrentHashMultiset<E> multiset = ConcurrentHashMultiset.create();
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    @Beta
    public static <E> ConcurrentHashMultiset<E> create(ConcurrentMap<E, AtomicInteger> countMap) {
        return new ConcurrentHashMultiset<E>(countMap);
    }

    @VisibleForTesting
    ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> countMap) {
        Preconditions.checkArgument(countMap.isEmpty(), "the backing map (%s) must be empty", countMap);
        this.countMap = countMap;
    }

    @Override
    public int count(@Nullable Object element) {
        AtomicInteger existingCounter = Maps.safeGet(this.countMap, element);
        return existingCounter == null ? 0 : existingCounter.get();
    }

    @Override
    public int size() {
        long sum = 0L;
        for (AtomicInteger value : this.countMap.values()) {
            sum += (long)value.get();
        }
        return Ints.saturatedCast(sum);
    }

    @Override
    public Object[] toArray() {
        return this.snapshot().toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return this.snapshot().toArray(array);
    }

    private List<E> snapshot() {
        ArrayList list = Lists.newArrayListWithExpectedSize(this.size());
        for (Multiset.Entry entry : this.entrySet()) {
            Object element = entry.getElement();
            for (int i = entry.getCount(); i > 0; --i) {
                list.add(element);
            }
        }
        return list;
    }

    @Override
    @CanIgnoreReturnValue
    public int add(E element, int occurrences) {
        AtomicInteger existingCounter;
        AtomicInteger newCounter;
        Preconditions.checkNotNull(element);
        if (occurrences == 0) {
            return this.count(element);
        }
        CollectPreconditions.checkPositive(occurrences, "occurences");
        do {
            int oldValue;
            if ((existingCounter = Maps.safeGet(this.countMap, element)) == null && (existingCounter = this.countMap.putIfAbsent(element, new AtomicInteger(occurrences))) == null) {
                return 0;
            }
            while ((oldValue = existingCounter.get()) != 0) {
                try {
                    int newValue = IntMath.checkedAdd(oldValue, occurrences);
                    if (!existingCounter.compareAndSet(oldValue, newValue)) continue;
                    return oldValue;
                } catch (ArithmeticException overflow) {
                    throw new IllegalArgumentException("Overflow adding " + occurrences + " occurrences to a count of " + oldValue);
                }
            }
        } while (this.countMap.putIfAbsent(element, newCounter = new AtomicInteger(occurrences)) != null && !this.countMap.replace(element, existingCounter, newCounter));
        return 0;
    }

    @Override
    @CanIgnoreReturnValue
    public int remove(@Nullable Object element, int occurrences) {
        int oldValue;
        if (occurrences == 0) {
            return this.count(element);
        }
        CollectPreconditions.checkPositive(occurrences, "occurences");
        AtomicInteger existingCounter = Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return 0;
        }
        while ((oldValue = existingCounter.get()) != 0) {
            int newValue = Math.max(0, oldValue - occurrences);
            if (!existingCounter.compareAndSet(oldValue, newValue)) continue;
            if (newValue == 0) {
                this.countMap.remove(element, existingCounter);
            }
            return oldValue;
        }
        return 0;
    }

    @CanIgnoreReturnValue
    public boolean removeExactly(@Nullable Object element, int occurrences) {
        int newValue;
        int oldValue;
        if (occurrences == 0) {
            return true;
        }
        CollectPreconditions.checkPositive(occurrences, "occurences");
        AtomicInteger existingCounter = Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return false;
        }
        do {
            if ((oldValue = existingCounter.get()) >= occurrences) continue;
            return false;
        } while (!existingCounter.compareAndSet(oldValue, newValue = oldValue - occurrences));
        if (newValue == 0) {
            this.countMap.remove(element, existingCounter);
        }
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public int setCount(E element, int count) {
        int oldValue;
        AtomicInteger existingCounter;
        Preconditions.checkNotNull(element);
        CollectPreconditions.checkNonnegative(count, "count");
        block0: while (true) {
            if ((existingCounter = Maps.safeGet(this.countMap, element)) == null) {
                if (count == 0) {
                    return 0;
                }
                existingCounter = this.countMap.putIfAbsent(element, new AtomicInteger(count));
                if (existingCounter == null) {
                    return 0;
                }
            }
            do {
                if ((oldValue = existingCounter.get()) != 0) continue;
                if (count == 0) {
                    return 0;
                }
                AtomicInteger newCounter = new AtomicInteger(count);
                if (this.countMap.putIfAbsent(element, newCounter) != null && !this.countMap.replace(element, existingCounter, newCounter)) continue block0;
                return 0;
            } while (!existingCounter.compareAndSet(oldValue, count));
            break;
        }
        if (count == 0) {
            this.countMap.remove(element, existingCounter);
        }
        return oldValue;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean setCount(E element, int expectedOldCount, int newCount) {
        Preconditions.checkNotNull(element);
        CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        AtomicInteger existingCounter = Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            if (expectedOldCount != 0) {
                return false;
            }
            if (newCount == 0) {
                return true;
            }
            return this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null;
        }
        int oldValue = existingCounter.get();
        if (oldValue == expectedOldCount) {
            if (oldValue == 0) {
                if (newCount == 0) {
                    this.countMap.remove(element, existingCounter);
                    return true;
                }
                AtomicInteger newCounter = new AtomicInteger(newCount);
                return this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter);
            }
            if (existingCounter.compareAndSet(oldValue, newCount)) {
                if (newCount == 0) {
                    this.countMap.remove(element, existingCounter);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    Set<E> createElementSet() {
        final Set delegate = this.countMap.keySet();
        return new ForwardingSet<E>(){

            @Override
            protected Set<E> delegate() {
                return delegate;
            }

            @Override
            public boolean contains(@Nullable Object object) {
                return object != null && Collections2.safeContains(delegate, object);
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return this.standardContainsAll(collection);
            }

            @Override
            public boolean remove(Object object) {
                return object != null && Collections2.safeRemove(delegate, object);
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return this.standardRemoveAll(c);
            }
        };
    }

    @Override
    Iterator<E> elementIterator() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    @Deprecated
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    @Override
    int distinctElements() {
        return this.countMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.countMap.isEmpty();
    }

    @Override
    Iterator<Multiset.Entry<E>> entryIterator() {
        final AbstractIterator readOnlyIterator = new AbstractIterator<Multiset.Entry<E>>(){
            private final Iterator<Map.Entry<E, AtomicInteger>> mapEntries;
            {
                this.mapEntries = ConcurrentHashMultiset.this.countMap.entrySet().iterator();
            }

            @Override
            protected Multiset.Entry<E> computeNext() {
                Map.Entry mapEntry;
                int count;
                do {
                    if (this.mapEntries.hasNext()) continue;
                    return (Multiset.Entry)this.endOfData();
                } while ((count = (mapEntry = this.mapEntries.next()).getValue().get()) == 0);
                return Multisets.immutableEntry(mapEntry.getKey(), count);
            }
        };
        return new ForwardingIterator<Multiset.Entry<E>>(){
            private  @Nullable Multiset.Entry<E> last;

            @Override
            protected Iterator<Multiset.Entry<E>> delegate() {
                return readOnlyIterator;
            }

            @Override
            public Multiset.Entry<E> next() {
                this.last = (Multiset.Entry)super.next();
                return this.last;
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.last != null);
                ConcurrentHashMultiset.this.setCount(this.last.getElement(), 0);
                this.last = null;
            }
        };
    }

    @Override
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override
    public void clear() {
        this.countMap.clear();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.countMap);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        ConcurrentMap deserializedCountMap = (ConcurrentMap)stream.readObject();
        FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, deserializedCountMap);
    }

    private class EntrySet
    extends AbstractMultiset.EntrySet {
        private EntrySet() {
        }

        @Override
        ConcurrentHashMultiset<E> multiset() {
            return ConcurrentHashMultiset.this;
        }

        @Override
        public Object[] toArray() {
            return this.snapshot().toArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return this.snapshot().toArray(array);
        }

        private List<Multiset.Entry<E>> snapshot() {
            ArrayList list = Lists.newArrayListWithExpectedSize(this.size());
            Iterators.addAll(list, this.iterator());
            return list;
        }
    }

    private static class FieldSettersHolder {
        static final Serialization.FieldSetter<ConcurrentHashMultiset> COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");

        private FieldSettersHolder() {
        }
    }
}

