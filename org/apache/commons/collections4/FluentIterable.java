/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.SingletonIterator;

public class FluentIterable<E>
implements Iterable<E> {
    private final Iterable<E> iterable;

    public static <T> FluentIterable<T> empty() {
        return IterableUtils.EMPTY_ITERABLE;
    }

    public static <T> FluentIterable<T> of(T singleton) {
        return FluentIterable.of(IteratorUtils.asIterable(new SingletonIterator<T>(singleton, false)));
    }

    public static <T> FluentIterable<T> of(T ... elements) {
        return FluentIterable.of(Arrays.asList(elements));
    }

    public static <T> FluentIterable<T> of(Iterable<T> iterable) {
        IterableUtils.checkNotNull(iterable);
        if (iterable instanceof FluentIterable) {
            return (FluentIterable)iterable;
        }
        return new FluentIterable<T>(iterable);
    }

    FluentIterable() {
        this.iterable = this;
    }

    private FluentIterable(Iterable<E> iterable) {
        this.iterable = iterable;
    }

    public FluentIterable<E> append(E ... elements) {
        return this.append((Iterable<? extends E>)Arrays.asList(elements));
    }

    public FluentIterable<E> append(Iterable<? extends E> other) {
        return FluentIterable.of(IterableUtils.chainedIterable(this.iterable, other));
    }

    public FluentIterable<E> collate(Iterable<? extends E> other) {
        return FluentIterable.of(IterableUtils.collatedIterable(this.iterable, other));
    }

    public FluentIterable<E> collate(Iterable<? extends E> other, Comparator<? super E> comparator) {
        return FluentIterable.of(IterableUtils.collatedIterable(comparator, this.iterable, other));
    }

    public FluentIterable<E> eval() {
        return FluentIterable.of(this.toList());
    }

    public FluentIterable<E> filter(Predicate<? super E> predicate) {
        return FluentIterable.of(IterableUtils.filteredIterable(this.iterable, predicate));
    }

    public FluentIterable<E> limit(long maxSize) {
        return FluentIterable.of(IterableUtils.boundedIterable(this.iterable, maxSize));
    }

    public FluentIterable<E> loop() {
        return FluentIterable.of(IterableUtils.loopingIterable(this.iterable));
    }

    public FluentIterable<E> reverse() {
        return FluentIterable.of(IterableUtils.reversedIterable(this.iterable));
    }

    public FluentIterable<E> skip(long elementsToSkip) {
        return FluentIterable.of(IterableUtils.skippingIterable(this.iterable, elementsToSkip));
    }

    public <O> FluentIterable<O> transform(Transformer<? super E, ? extends O> transformer) {
        return FluentIterable.of(IterableUtils.transformedIterable(this.iterable, transformer));
    }

    public FluentIterable<E> unique() {
        return FluentIterable.of(IterableUtils.uniqueIterable(this.iterable));
    }

    public FluentIterable<E> unmodifiable() {
        return FluentIterable.of(IterableUtils.unmodifiableIterable(this.iterable));
    }

    public FluentIterable<E> zip(Iterable<? extends E> other) {
        return FluentIterable.of(IterableUtils.zippingIterable(this.iterable, other));
    }

    public FluentIterable<E> zip(Iterable<? extends E> ... others) {
        return FluentIterable.of(IterableUtils.zippingIterable(this.iterable, others));
    }

    @Override
    public Iterator<E> iterator() {
        return this.iterable.iterator();
    }

    public Enumeration<E> asEnumeration() {
        return IteratorUtils.asEnumeration(this.iterator());
    }

    public boolean allMatch(Predicate<? super E> predicate) {
        return IterableUtils.matchesAll(this.iterable, predicate);
    }

    public boolean anyMatch(Predicate<? super E> predicate) {
        return IterableUtils.matchesAny(this.iterable, predicate);
    }

    public boolean isEmpty() {
        return IterableUtils.isEmpty(this.iterable);
    }

    public boolean contains(Object object) {
        return IterableUtils.contains(this.iterable, object);
    }

    @Override
    public void forEach(Closure<? super E> closure) {
        IterableUtils.forEach(this.iterable, closure);
    }

    public E get(int position) {
        return IterableUtils.get(this.iterable, position);
    }

    public int size() {
        return IterableUtils.size(this.iterable);
    }

    public void copyInto(Collection<? super E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        CollectionUtils.addAll(collection, this.iterable);
    }

    public E[] toArray(Class<E> arrayClass) {
        return IteratorUtils.toArray(this.iterator(), arrayClass);
    }

    public List<E> toList() {
        return IterableUtils.toList(this.iterable);
    }

    public String toString() {
        return IterableUtils.toString(this.iterable);
    }
}

