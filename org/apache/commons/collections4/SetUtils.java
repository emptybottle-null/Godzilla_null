/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.collections4.set.PredicatedNavigableSet;
import org.apache.commons.collections4.set.PredicatedSet;
import org.apache.commons.collections4.set.PredicatedSortedSet;
import org.apache.commons.collections4.set.TransformedNavigableSet;
import org.apache.commons.collections4.set.TransformedSet;
import org.apache.commons.collections4.set.TransformedSortedSet;
import org.apache.commons.collections4.set.UnmodifiableNavigableSet;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

public class SetUtils {
    public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.unmodifiableSortedSet(new TreeSet());

    public static <E> SetView<E> difference(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final Predicate notContainedInB = new Predicate<E>(){

            @Override
            public boolean evaluate(E object) {
                return !b.contains(object);
            }
        };
        return new SetView<E>(){

            @Override
            public boolean contains(Object o) {
                return a.contains(o) && !b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(a.iterator(), notContainedInB);
            }
        };
    }

    public static <E> SetView<E> disjunction(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final SetView<? extends E> aMinusB = SetUtils.difference(a, b);
        final SetView<? extends E> bMinusA = SetUtils.difference(b, a);
        return new SetView<E>(){

            @Override
            public boolean contains(Object o) {
                return a.contains(o) ^ b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(aMinusB.iterator(), bMinusA.iterator());
            }

            @Override
            public boolean isEmpty() {
                return aMinusB.isEmpty() && bMinusA.isEmpty();
            }

            @Override
            public int size() {
                return aMinusB.size() + bMinusA.size();
            }
        };
    }

    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    public static <E> Set<E> emptySet() {
        return Collections.emptySet();
    }

    public static <E> SortedSet<E> emptySortedSet() {
        return EMPTY_SORTED_SET;
    }

    public static <T> int hashCodeForSet(Collection<T> set) {
        if (set == null) {
            return 0;
        }
        int hashCode = 0;
        for (T obj : set) {
            if (obj == null) continue;
            hashCode += obj.hashCode();
        }
        return hashCode;
    }

    public static <E> HashSet<E> hashSet(E ... items) {
        if (items == null) {
            return null;
        }
        return new HashSet<E>(Arrays.asList(items));
    }

    public static <E> SetView<E> intersection(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final Predicate containedInB = new Predicate<E>(){

            @Override
            public boolean evaluate(E object) {
                return b.contains(object);
            }
        };
        return new SetView<E>(){

            @Override
            public boolean contains(Object o) {
                return a.contains(o) && b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(a.iterator(), containedInB);
            }
        };
    }

    public static boolean isEqualSet(Collection<?> set1, Collection<?> set2) {
        if (set1 == set2) {
            return true;
        }
        if (set1 == null || set2 == null || set1.size() != set2.size()) {
            return false;
        }
        return set1.containsAll(set2);
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static <E> Set<E> orderedSet(Set<E> set) {
        return ListOrderedSet.listOrderedSet(set);
    }

    public static <E> SortedSet<E> predicatedNavigableSet(NavigableSet<E> set, Predicate<? super E> predicate) {
        return PredicatedNavigableSet.predicatedNavigableSet(set, predicate);
    }

    public static <E> Set<E> predicatedSet(Set<E> set, Predicate<? super E> predicate) {
        return PredicatedSet.predicatedSet(set, predicate);
    }

    public static <E> SortedSet<E> predicatedSortedSet(SortedSet<E> set, Predicate<? super E> predicate) {
        return PredicatedSortedSet.predicatedSortedSet(set, predicate);
    }

    public static <E> Set<E> synchronizedSet(Set<E> set) {
        return Collections.synchronizedSet(set);
    }

    public static <E> SortedSet<E> synchronizedSortedSet(SortedSet<E> set) {
        return Collections.synchronizedSortedSet(set);
    }

    public static <E> SortedSet<E> transformedNavigableSet(NavigableSet<E> set, Transformer<? super E, ? extends E> transformer) {
        return TransformedNavigableSet.transformingNavigableSet(set, transformer);
    }

    public static <E> Set<E> transformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        return TransformedSet.transformingSet(set, transformer);
    }

    public static <E> SortedSet<E> transformedSortedSet(SortedSet<E> set, Transformer<? super E, ? extends E> transformer) {
        return TransformedSortedSet.transformingSortedSet(set, transformer);
    }

    public static <E> SetView<E> union(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final SetView<? extends E> bMinusA = SetUtils.difference(b, a);
        return new SetView<E>(){

            @Override
            public boolean contains(Object o) {
                return a.contains(o) || b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(a.iterator(), bMinusA.iterator());
            }

            @Override
            public boolean isEmpty() {
                return a.isEmpty() && b.isEmpty();
            }

            @Override
            public int size() {
                return a.size() + bMinusA.size();
            }
        };
    }

    public static <E> SortedSet<E> unmodifiableNavigableSet(NavigableSet<E> set) {
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(set);
    }

    public static <E> Set<E> unmodifiableSet(E ... items) {
        if (items == null) {
            return null;
        }
        return UnmodifiableSet.unmodifiableSet(SetUtils.hashSet(items));
    }

    public static <E> Set<E> unmodifiableSet(Set<? extends E> set) {
        return UnmodifiableSet.unmodifiableSet(set);
    }

    public static <E> SortedSet<E> unmodifiableSortedSet(SortedSet<E> set) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(set);
    }

    private SetUtils() {
    }

    public static abstract class SetView<E>
    extends AbstractSet<E> {
        public <S extends Set<E>> void copyInto(S set) {
            CollectionUtils.addAll(set, this);
        }

        protected abstract Iterator<E> createIterator();

        @Override
        public Iterator<E> iterator() {
            return IteratorUtils.unmodifiableIterator(this.createIterator());
        }

        @Override
        public int size() {
            return IteratorUtils.size(this.iterator());
        }

        public Set<E> toSet() {
            HashSet set = new HashSet(this.size());
            this.copyInto(set);
            return set;
        }
    }
}

