/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.commons.collections4.iterators.UniqueFilterIterator;

public class IterableUtils {
    static final FluentIterable EMPTY_ITERABLE = new FluentIterable<Object>(){

        @Override
        public Iterator<Object> iterator() {
            return IteratorUtils.emptyIterator();
        }
    };

    public static <E> Iterable<E> emptyIterable() {
        return EMPTY_ITERABLE;
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> a, Iterable<? extends E> b) {
        return IterableUtils.chainedIterable(new Iterable[]{a, b});
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> a, Iterable<? extends E> b, Iterable<? extends E> c) {
        return IterableUtils.chainedIterable(new Iterable[]{a, b, c});
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> a, Iterable<? extends E> b, Iterable<? extends E> c, Iterable<? extends E> d) {
        return IterableUtils.chainedIterable(new Iterable[]{a, b, c, d});
    }

    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E> ... iterables) {
        IterableUtils.checkNotNull(iterables);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return new LazyIteratorChain<E>(){

                    @Override
                    protected Iterator<? extends E> nextIterator(int count) {
                        if (count > iterables.length) {
                            return null;
                        }
                        return iterables[count - 1].iterator();
                    }
                };
            }
        };
    }

    public static <E> Iterable<E> collatedIterable(final Iterable<? extends E> a, final Iterable<? extends E> b) {
        IterableUtils.checkNotNull(a, b);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.collatedIterator(null, a.iterator(), b.iterator());
            }
        };
    }

    public static <E> Iterable<E> collatedIterable(final Comparator<? super E> comparator, final Iterable<? extends E> a, final Iterable<? extends E> b) {
        IterableUtils.checkNotNull(a, b);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.collatedIterator(comparator, a.iterator(), b.iterator());
            }
        };
    }

    public static <E> Iterable<E> filteredIterable(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        IterableUtils.checkNotNull(iterable);
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null.");
        }
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.filteredIterator(IterableUtils.emptyIteratorIfNull(iterable), predicate);
            }
        };
    }

    public static <E> Iterable<E> boundedIterable(final Iterable<E> iterable, final long maxSize) {
        IterableUtils.checkNotNull(iterable);
        if (maxSize < 0L) {
            throw new IllegalArgumentException("MaxSize parameter must not be negative.");
        }
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.boundedIterator(iterable.iterator(), maxSize);
            }
        };
    }

    public static <E> Iterable<E> loopingIterable(final Iterable<E> iterable) {
        IterableUtils.checkNotNull(iterable);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return new LazyIteratorChain<E>(){

                    @Override
                    protected Iterator<? extends E> nextIterator(int count) {
                        if (IterableUtils.isEmpty(iterable)) {
                            return null;
                        }
                        return iterable.iterator();
                    }
                };
            }
        };
    }

    public static <E> Iterable<E> reversedIterable(final Iterable<E> iterable) {
        IterableUtils.checkNotNull(iterable);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                List list = iterable instanceof List ? (List)iterable : IteratorUtils.toList(iterable.iterator());
                return new ReverseListIterator(list);
            }
        };
    }

    public static <E> Iterable<E> skippingIterable(final Iterable<E> iterable, final long elementsToSkip) {
        IterableUtils.checkNotNull(iterable);
        if (elementsToSkip < 0L) {
            throw new IllegalArgumentException("ElementsToSkip parameter must not be negative.");
        }
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.skippingIterator(iterable.iterator(), elementsToSkip);
            }
        };
    }

    public static <I, O> Iterable<O> transformedIterable(final Iterable<I> iterable, final Transformer<? super I, ? extends O> transformer) {
        IterableUtils.checkNotNull(iterable);
        if (transformer == null) {
            throw new NullPointerException("Transformer must not be null.");
        }
        return new FluentIterable<O>(){

            @Override
            public Iterator<O> iterator() {
                return IteratorUtils.transformedIterator(iterable.iterator(), transformer);
            }
        };
    }

    public static <E> Iterable<E> uniqueIterable(final Iterable<E> iterable) {
        IterableUtils.checkNotNull(iterable);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return new UniqueFilterIterator(iterable.iterator());
            }
        };
    }

    public static <E> Iterable<E> unmodifiableIterable(Iterable<E> iterable) {
        IterableUtils.checkNotNull(iterable);
        if (iterable instanceof UnmodifiableIterable) {
            return iterable;
        }
        return new UnmodifiableIterable<E>(iterable);
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> a, final Iterable<? extends E> b) {
        IterableUtils.checkNotNull(a);
        IterableUtils.checkNotNull(b);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.zippingIterator(a.iterator(), b.iterator());
            }
        };
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> first, final Iterable<? extends E> ... others) {
        IterableUtils.checkNotNull(first);
        IterableUtils.checkNotNull(others);
        return new FluentIterable<E>(){

            @Override
            public Iterator<E> iterator() {
                Iterator[] iterators = new Iterator[others.length + 1];
                iterators[0] = first.iterator();
                for (int i = 0; i < others.length; ++i) {
                    iterators[i + 1] = others[i].iterator();
                }
                return IteratorUtils.zippingIterator(iterators);
            }
        };
    }

    public static <E> Iterable<E> emptyIfNull(Iterable<E> iterable) {
        return iterable == null ? IterableUtils.emptyIterable() : iterable;
    }

    public static <E> void forEach(Iterable<E> iterable, Closure<? super E> closure) {
        IteratorUtils.forEach(IterableUtils.emptyIteratorIfNull(iterable), closure);
    }

    public static <E> E forEachButLast(Iterable<E> iterable, Closure<? super E> closure) {
        return IteratorUtils.forEachButLast(IterableUtils.emptyIteratorIfNull(iterable), closure);
    }

    public static <E> E find(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.find(IterableUtils.emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> int indexOf(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.indexOf(IterableUtils.emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> boolean matchesAll(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.matchesAll(IterableUtils.emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> boolean matchesAny(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.matchesAny(IterableUtils.emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> long countMatches(Iterable<E> input, Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null.");
        }
        return IterableUtils.size(IterableUtils.filteredIterable(IterableUtils.emptyIfNull(input), predicate));
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection)iterable).isEmpty();
        }
        return IteratorUtils.isEmpty(IterableUtils.emptyIteratorIfNull(iterable));
    }

    public static <E> boolean contains(Iterable<E> iterable, Object object) {
        if (iterable instanceof Collection) {
            return ((Collection)iterable).contains(object);
        }
        return IteratorUtils.contains(IterableUtils.emptyIteratorIfNull(iterable), object);
    }

    public static <E> boolean contains(Iterable<? extends E> iterable, E object, Equator<? super E> equator) {
        if (equator == null) {
            throw new NullPointerException("Equator must not be null.");
        }
        return IterableUtils.matchesAny(iterable, EqualPredicate.equalPredicate(object, equator));
    }

    public static <E, T extends E> int frequency(Iterable<E> iterable, T obj) {
        if (iterable instanceof Set) {
            return ((Set)iterable).contains(obj) ? 1 : 0;
        }
        if (iterable instanceof Bag) {
            return ((Bag)iterable).getCount(obj);
        }
        return IterableUtils.size(IterableUtils.filteredIterable(IterableUtils.emptyIfNull(iterable), EqualPredicate.equalPredicate(obj)));
    }

    public static <T> T get(Iterable<T> iterable, int index) {
        CollectionUtils.checkIndexBounds(index);
        if (iterable instanceof List) {
            return (T)((List)iterable).get(index);
        }
        return IteratorUtils.get(IterableUtils.emptyIteratorIfNull(iterable), index);
    }

    public static <T> T first(Iterable<T> iterable) {
        return IterableUtils.get(iterable, 0);
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection)iterable).size();
        }
        return IteratorUtils.size(IterableUtils.emptyIteratorIfNull(iterable));
    }

    public static <O> List<List<O>> partition(Iterable<? extends O> iterable, Predicate<? super O> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null.");
        }
        Factory<ArrayList> factory = FactoryUtils.instantiateFactory(ArrayList.class);
        Predicate[] predicates = new Predicate[]{predicate};
        return IterableUtils.partition(iterable, factory, predicates);
    }

    public static <O> List<List<O>> partition(Iterable<? extends O> iterable, Predicate<? super O> ... predicates) {
        Factory<ArrayList> factory = FactoryUtils.instantiateFactory(ArrayList.class);
        return IterableUtils.partition(iterable, factory, predicates);
    }

    /*
     * WARNING - void declaration
     */
    public static <O, R extends Collection<O>> List<R> partition(Iterable<? extends O> iterable, Factory<R> partitionFactory, Predicate<? super O> ... predicates) {
        void var6_11;
        if (iterable == null) {
            Iterable empty = IterableUtils.emptyIterable();
            return IterableUtils.partition(empty, partitionFactory, predicates);
        }
        if (predicates == null) {
            throw new NullPointerException("Predicates must not be null.");
        }
        for (Predicate<O> predicate : predicates) {
            if (predicate != null) continue;
            throw new NullPointerException("Predicate must not be null.");
        }
        if (predicates.length < 1) {
            Collection singlePartition = (Collection)partitionFactory.create();
            CollectionUtils.addAll(singlePartition, iterable);
            return Collections.singletonList(singlePartition);
        }
        int numberOfPredicates = predicates.length;
        int numberOfPartitions = numberOfPredicates + 1;
        ArrayList<R> partitions = new ArrayList<R>(numberOfPartitions);
        boolean bl = false;
        while (var6_11 < numberOfPartitions) {
            partitions.add(partitionFactory.create());
            ++var6_11;
        }
        for (O element : iterable) {
            boolean elementAssigned = false;
            for (int i = 0; i < numberOfPredicates; ++i) {
                if (!predicates[i].evaluate(element)) continue;
                ((Collection)partitions.get(i)).add(element);
                elementAssigned = true;
                break;
            }
            if (elementAssigned) continue;
            ((Collection)partitions.get(numberOfPredicates)).add(element);
        }
        return partitions;
    }

    public static <E> List<E> toList(Iterable<E> iterable) {
        return IteratorUtils.toList(IterableUtils.emptyIteratorIfNull(iterable));
    }

    public static <E> String toString(Iterable<E> iterable) {
        return IteratorUtils.toString(IterableUtils.emptyIteratorIfNull(iterable));
    }

    public static <E> String toString(Iterable<E> iterable, Transformer<? super E, String> transformer) {
        if (transformer == null) {
            throw new NullPointerException("Transformer must not be null.");
        }
        return IteratorUtils.toString(IterableUtils.emptyIteratorIfNull(iterable), transformer);
    }

    public static <E> String toString(Iterable<E> iterable, Transformer<? super E, String> transformer, String delimiter, String prefix, String suffix) {
        return IteratorUtils.toString(IterableUtils.emptyIteratorIfNull(iterable), transformer, delimiter, prefix, suffix);
    }

    static void checkNotNull(Iterable<?> iterable) {
        if (iterable == null) {
            throw new NullPointerException("Iterable must not be null.");
        }
    }

    static void checkNotNull(Iterable<?> ... iterables) {
        if (iterables == null) {
            throw new NullPointerException("Iterables must not be null.");
        }
        for (Iterable<?> iterable : iterables) {
            IterableUtils.checkNotNull(iterable);
        }
    }

    private static <E> Iterator<E> emptyIteratorIfNull(Iterable<E> iterable) {
        return iterable != null ? iterable.iterator() : IteratorUtils.emptyIterator();
    }

    private static final class UnmodifiableIterable<E>
    extends FluentIterable<E> {
        private final Iterable<E> unmodifiable;

        public UnmodifiableIterable(Iterable<E> iterable) {
            this.unmodifiable = iterable;
        }

        @Override
        public Iterator<E> iterator() {
            return IteratorUtils.unmodifiableIterator(this.unmodifiable.iterator());
        }
    }
}

