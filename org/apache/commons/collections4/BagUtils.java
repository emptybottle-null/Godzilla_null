/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.CollectionBag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.bag.PredicatedSortedBag;
import org.apache.commons.collections4.bag.SynchronizedBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TransformedBag;
import org.apache.commons.collections4.bag.TransformedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.bag.UnmodifiableSortedBag;

public class BagUtils {
    public static final Bag EMPTY_BAG = UnmodifiableBag.unmodifiableBag(new HashBag());
    public static final Bag EMPTY_SORTED_BAG = UnmodifiableSortedBag.unmodifiableSortedBag(new TreeBag());

    private BagUtils() {
    }

    public static <E> Bag<E> synchronizedBag(Bag<E> bag) {
        return SynchronizedBag.synchronizedBag(bag);
    }

    public static <E> Bag<E> unmodifiableBag(Bag<? extends E> bag) {
        return UnmodifiableBag.unmodifiableBag(bag);
    }

    public static <E> Bag<E> predicatedBag(Bag<E> bag, Predicate<? super E> predicate) {
        return PredicatedBag.predicatedBag(bag, predicate);
    }

    public static <E> Bag<E> transformingBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return TransformedBag.transformingBag(bag, transformer);
    }

    public static <E> Bag<E> collectionBag(Bag<E> bag) {
        return CollectionBag.collectionBag(bag);
    }

    public static <E> SortedBag<E> synchronizedSortedBag(SortedBag<E> bag) {
        return SynchronizedSortedBag.synchronizedSortedBag(bag);
    }

    public static <E> SortedBag<E> unmodifiableSortedBag(SortedBag<E> bag) {
        return UnmodifiableSortedBag.unmodifiableSortedBag(bag);
    }

    public static <E> SortedBag<E> predicatedSortedBag(SortedBag<E> bag, Predicate<? super E> predicate) {
        return PredicatedSortedBag.predicatedSortedBag(bag, predicate);
    }

    public static <E> SortedBag<E> transformingSortedBag(SortedBag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return TransformedSortedBag.transformingSortedBag(bag, transformer);
    }

    public static <E> Bag<E> emptyBag() {
        return EMPTY_BAG;
    }

    public static <E> SortedBag<E> emptySortedBag() {
        return (SortedBag)EMPTY_SORTED_BAG;
    }
}

