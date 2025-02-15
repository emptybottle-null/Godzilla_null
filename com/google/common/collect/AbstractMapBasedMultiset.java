/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Count;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
abstract class AbstractMapBasedMultiset<E>
extends AbstractMultiset<E>
implements Serializable {
    private transient Map<E, Count> backingMap;
    private transient long size;
    @GwtIncompatible
    private static final long serialVersionUID = -2250766705698539974L;

    protected AbstractMapBasedMultiset(Map<E, Count> backingMap) {
        Preconditions.checkArgument(backingMap.isEmpty());
        this.backingMap = backingMap;
    }

    void setBackingMap(Map<E, Count> backingMap) {
        this.backingMap = backingMap;
    }

    @Override
    public Set<Multiset.Entry<E>> entrySet() {
        return super.entrySet();
    }

    @Override
    Iterator<E> elementIterator() {
        final Iterator<Map.Entry<E, Count>> backingEntries = this.backingMap.entrySet().iterator();
        return new Iterator<E>(){
            @Nullable Map.Entry<E, Count> toRemove;

            @Override
            public boolean hasNext() {
                return backingEntries.hasNext();
            }

            @Override
            public E next() {
                Map.Entry mapEntry;
                this.toRemove = mapEntry = (Map.Entry)backingEntries.next();
                return mapEntry.getKey();
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.toRemove != null);
                AbstractMapBasedMultiset.this.size = AbstractMapBasedMultiset.this.size - (long)this.toRemove.getValue().getAndSet(0);
                backingEntries.remove();
                this.toRemove = null;
            }
        };
    }

    @Override
    Iterator<Multiset.Entry<E>> entryIterator() {
        final Iterator<Map.Entry<E, Count>> backingEntries = this.backingMap.entrySet().iterator();
        return new Iterator<Multiset.Entry<E>>(){
            @Nullable Map.Entry<E, Count> toRemove;

            @Override
            public boolean hasNext() {
                return backingEntries.hasNext();
            }

            @Override
            public Multiset.Entry<E> next() {
                Map.Entry mapEntry;
                this.toRemove = mapEntry = (Map.Entry)backingEntries.next();
                return new Multisets.AbstractEntry<E>(){

                    @Override
                    public E getElement() {
                        return mapEntry.getKey();
                    }

                    @Override
                    public int getCount() {
                        Count frequency;
                        Count count = (Count)mapEntry.getValue();
                        if ((count == null || count.get() == 0) && (frequency = (Count)AbstractMapBasedMultiset.this.backingMap.get(this.getElement())) != null) {
                            return frequency.get();
                        }
                        return count == null ? 0 : count.get();
                    }
                };
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.toRemove != null);
                AbstractMapBasedMultiset.this.size = AbstractMapBasedMultiset.this.size - (long)this.toRemove.getValue().getAndSet(0);
                backingEntries.remove();
                this.toRemove = null;
            }
        };
    }

    @Override
    public void forEachEntry(ObjIntConsumer<? super E> action) {
        Preconditions.checkNotNull(action);
        this.backingMap.forEach((? super K element, ? super V count) -> action.accept((Object)element, count.get()));
    }

    @Override
    public void clear() {
        for (Count frequency : this.backingMap.values()) {
            frequency.set(0);
        }
        this.backingMap.clear();
        this.size = 0L;
    }

    @Override
    int distinctElements() {
        return this.backingMap.size();
    }

    @Override
    public int size() {
        return Ints.saturatedCast(this.size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MapBasedMultisetIterator();
    }

    @Override
    public int count(@Nullable Object element) {
        Count frequency = Maps.safeGet(this.backingMap, element);
        return frequency == null ? 0 : frequency.get();
    }

    @Override
    @CanIgnoreReturnValue
    public int add(@Nullable E element, int occurrences) {
        int oldCount;
        if (occurrences == 0) {
            return this.count(element);
        }
        Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", occurrences);
        Count frequency = this.backingMap.get(element);
        if (frequency == null) {
            oldCount = 0;
            this.backingMap.put(element, new Count(occurrences));
        } else {
            oldCount = frequency.get();
            long newCount = (long)oldCount + (long)occurrences;
            Preconditions.checkArgument(newCount <= Integer.MAX_VALUE, "too many occurrences: %s", newCount);
            frequency.add(occurrences);
        }
        this.size += (long)occurrences;
        return oldCount;
    }

    @Override
    @CanIgnoreReturnValue
    public int remove(@Nullable Object element, int occurrences) {
        int numberRemoved;
        if (occurrences == 0) {
            return this.count(element);
        }
        Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", occurrences);
        Count frequency = this.backingMap.get(element);
        if (frequency == null) {
            return 0;
        }
        int oldCount = frequency.get();
        if (oldCount > occurrences) {
            numberRemoved = occurrences;
        } else {
            numberRemoved = oldCount;
            this.backingMap.remove(element);
        }
        frequency.add(-numberRemoved);
        this.size -= (long)numberRemoved;
        return oldCount;
    }

    @Override
    @CanIgnoreReturnValue
    public int setCount(@Nullable E element, int count) {
        int oldCount;
        CollectPreconditions.checkNonnegative(count, "count");
        if (count == 0) {
            Count existingCounter = this.backingMap.remove(element);
            oldCount = AbstractMapBasedMultiset.getAndSet(existingCounter, count);
        } else {
            Count existingCounter = this.backingMap.get(element);
            oldCount = AbstractMapBasedMultiset.getAndSet(existingCounter, count);
            if (existingCounter == null) {
                this.backingMap.put(element, new Count(count));
            }
        }
        this.size += (long)(count - oldCount);
        return oldCount;
    }

    private static int getAndSet(@Nullable Count i, int count) {
        if (i == null) {
            return 0;
        }
        return i.getAndSet(count);
    }

    @GwtIncompatible
    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("Stream data required");
    }

    private class MapBasedMultisetIterator
    implements Iterator<E> {
        final Iterator<Map.Entry<E, Count>> entryIterator;
        @MonotonicNonNull Map.Entry<E, Count> currentEntry;
        int occurrencesLeft;
        boolean canRemove;

        MapBasedMultisetIterator() {
            this.entryIterator = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.occurrencesLeft > 0 || this.entryIterator.hasNext();
        }

        @Override
        public E next() {
            if (this.occurrencesLeft == 0) {
                this.currentEntry = this.entryIterator.next();
                this.occurrencesLeft = this.currentEntry.getValue().get();
            }
            --this.occurrencesLeft;
            this.canRemove = true;
            return this.currentEntry.getKey();
        }

        @Override
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            int frequency = this.currentEntry.getValue().get();
            if (frequency <= 0) {
                throw new ConcurrentModificationException();
            }
            if (this.currentEntry.getValue().addAndGet(-1) == 0) {
                this.entryIterator.remove();
            }
            AbstractMapBasedMultiset.this.size--;
            this.canRemove = false;
        }
    }
}

