/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractSortedMultiset;
import com.google.common.collect.BoundType;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.GeneralRange;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.Serialization;
import com.google.common.collect.SortedMultiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class TreeMultiset<E>
extends AbstractSortedMultiset<E>
implements Serializable {
    private final transient Reference<AvlNode<E>> rootReference;
    private final transient GeneralRange<E> range;
    private final transient AvlNode<E> header;
    @GwtIncompatible
    private static final long serialVersionUID = 1L;

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset(Ordering.natural());
    }

    public static <E> TreeMultiset<E> create(@Nullable Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset(Ordering.natural()) : new TreeMultiset<E>(comparator);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> elements) {
        TreeMultiset<E> multiset = TreeMultiset.create();
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    TreeMultiset(Reference<AvlNode<E>> rootReference, GeneralRange<E> range, AvlNode<E> endLink) {
        super(range.comparator());
        this.rootReference = rootReference;
        this.range = range;
        this.header = endLink;
    }

    TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        this.header = new AvlNode<Object>(null, 1);
        TreeMultiset.successor(this.header, this.header);
        this.rootReference = new Reference();
    }

    private long aggregateForEntries(Aggregate aggr) {
        AvlNode<E> root = this.rootReference.get();
        long total = aggr.treeAggregate(root);
        if (this.range.hasLowerBound()) {
            total -= this.aggregateBelowRange(aggr, root);
        }
        if (this.range.hasUpperBound()) {
            total -= this.aggregateAboveRange(aggr, root);
        }
        return total;
    }

    private long aggregateBelowRange(Aggregate aggr, @Nullable AvlNode<E> node) {
        if (node == null) {
            return 0L;
        }
        int cmp = this.comparator().compare(this.range.getLowerEndpoint(), ((AvlNode)node).elem);
        if (cmp < 0) {
            return this.aggregateBelowRange(aggr, ((AvlNode)node).left);
        }
        if (cmp == 0) {
            switch (this.range.getLowerBoundType()) {
                case OPEN: {
                    return (long)aggr.nodeAggregate(node) + aggr.treeAggregate(((AvlNode)node).left);
                }
                case CLOSED: {
                    return aggr.treeAggregate(((AvlNode)node).left);
                }
            }
            throw new AssertionError();
        }
        return aggr.treeAggregate(((AvlNode)node).left) + (long)aggr.nodeAggregate(node) + this.aggregateBelowRange(aggr, ((AvlNode)node).right);
    }

    private long aggregateAboveRange(Aggregate aggr, @Nullable AvlNode<E> node) {
        if (node == null) {
            return 0L;
        }
        int cmp = this.comparator().compare(this.range.getUpperEndpoint(), ((AvlNode)node).elem);
        if (cmp > 0) {
            return this.aggregateAboveRange(aggr, ((AvlNode)node).right);
        }
        if (cmp == 0) {
            switch (this.range.getUpperBoundType()) {
                case OPEN: {
                    return (long)aggr.nodeAggregate(node) + aggr.treeAggregate(((AvlNode)node).right);
                }
                case CLOSED: {
                    return aggr.treeAggregate(((AvlNode)node).right);
                }
            }
            throw new AssertionError();
        }
        return aggr.treeAggregate(((AvlNode)node).right) + (long)aggr.nodeAggregate(node) + this.aggregateAboveRange(aggr, ((AvlNode)node).left);
    }

    @Override
    public int size() {
        return Ints.saturatedCast(this.aggregateForEntries(Aggregate.SIZE));
    }

    @Override
    int distinctElements() {
        return Ints.saturatedCast(this.aggregateForEntries(Aggregate.DISTINCT));
    }

    static int distinctElements(@Nullable AvlNode<?> node) {
        return node == null ? 0 : ((AvlNode)node).distinctElements;
    }

    @Override
    public int count(@Nullable Object element) {
        try {
            Object e = element;
            AvlNode<Object> root = this.rootReference.get();
            if (!this.range.contains(e) || root == null) {
                return 0;
            }
            return root.count(this.comparator(), e);
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    @Override
    @CanIgnoreReturnValue
    public int add(@Nullable E element, int occurrences) {
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return this.count(element);
        }
        Preconditions.checkArgument(this.range.contains(element));
        AvlNode<E> root = this.rootReference.get();
        if (root == null) {
            this.comparator().compare(element, element);
            AvlNode<E> newRoot = new AvlNode<E>(element, occurrences);
            TreeMultiset.successor(this.header, newRoot, this.header);
            this.rootReference.checkAndSet(root, newRoot);
            return 0;
        }
        int[] result = new int[1];
        AvlNode<E> newRoot = root.add(this.comparator(), element, occurrences, result);
        this.rootReference.checkAndSet(root, newRoot);
        return result[0];
    }

    @Override
    @CanIgnoreReturnValue
    public int remove(@Nullable Object element, int occurrences) {
        AvlNode<Object> newRoot;
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return this.count(element);
        }
        AvlNode<Object> root = this.rootReference.get();
        int[] result = new int[1];
        try {
            Object e = element;
            if (!this.range.contains(e) || root == null) {
                return 0;
            }
            newRoot = root.remove(this.comparator(), e, occurrences, result);
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
        this.rootReference.checkAndSet(root, newRoot);
        return result[0];
    }

    @Override
    @CanIgnoreReturnValue
    public int setCount(@Nullable E element, int count) {
        CollectPreconditions.checkNonnegative(count, "count");
        if (!this.range.contains(element)) {
            Preconditions.checkArgument(count == 0);
            return 0;
        }
        AvlNode<E> root = this.rootReference.get();
        if (root == null) {
            if (count > 0) {
                this.add(element, count);
            }
            return 0;
        }
        int[] result = new int[1];
        AvlNode<E> newRoot = root.setCount(this.comparator(), element, count, result);
        this.rootReference.checkAndSet(root, newRoot);
        return result[0];
    }

    @Override
    @CanIgnoreReturnValue
    public boolean setCount(@Nullable E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        Preconditions.checkArgument(this.range.contains(element));
        AvlNode<E> root = this.rootReference.get();
        if (root == null) {
            if (oldCount == 0) {
                if (newCount > 0) {
                    this.add(element, newCount);
                }
                return true;
            }
            return false;
        }
        int[] result = new int[1];
        AvlNode<E> newRoot = root.setCount(this.comparator(), element, oldCount, newCount, result);
        this.rootReference.checkAndSet(root, newRoot);
        return result[0] == oldCount;
    }

    @Override
    public void clear() {
        if (!this.range.hasLowerBound() && !this.range.hasUpperBound()) {
            AvlNode current = ((AvlNode)this.header).succ;
            while (current != this.header) {
                AvlNode next = current.succ;
                current.elemCount = 0;
                current.left = null;
                current.right = null;
                current.pred = null;
                current.succ = null;
                current = next;
            }
            TreeMultiset.successor(this.header, this.header);
            this.rootReference.clear();
        } else {
            Iterators.clear(this.entryIterator());
        }
    }

    private Multiset.Entry<E> wrapEntry(final AvlNode<E> baseEntry) {
        return new Multisets.AbstractEntry<E>(){

            @Override
            public E getElement() {
                return baseEntry.getElement();
            }

            @Override
            public int getCount() {
                int result = baseEntry.getCount();
                if (result == 0) {
                    return TreeMultiset.this.count(this.getElement());
                }
                return result;
            }
        };
    }

    private @Nullable AvlNode<E> firstNode() {
        AvlNode node;
        AvlNode<E> root = this.rootReference.get();
        if (root == null) {
            return null;
        }
        if (this.range.hasLowerBound()) {
            E endpoint = this.range.getLowerEndpoint();
            node = ((AvlNode)this.rootReference.get()).ceiling(this.comparator(), endpoint);
            if (node == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && this.comparator().compare(endpoint, node.getElement()) == 0) {
                node = node.succ;
            }
        } else {
            node = ((AvlNode)this.header).succ;
        }
        return node == this.header || !this.range.contains(node.getElement()) ? null : node;
    }

    private @Nullable AvlNode<E> lastNode() {
        AvlNode node;
        AvlNode<E> root = this.rootReference.get();
        if (root == null) {
            return null;
        }
        if (this.range.hasUpperBound()) {
            E endpoint = this.range.getUpperEndpoint();
            node = ((AvlNode)this.rootReference.get()).floor(this.comparator(), endpoint);
            if (node == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && this.comparator().compare(endpoint, node.getElement()) == 0) {
                node = node.pred;
            }
        } else {
            node = ((AvlNode)this.header).pred;
        }
        return node == this.header || !this.range.contains(node.getElement()) ? null : node;
    }

    @Override
    Iterator<E> elementIterator() {
        return Multisets.elementIterator(this.entryIterator());
    }

    @Override
    Iterator<Multiset.Entry<E>> entryIterator() {
        return new Iterator<Multiset.Entry<E>>(){
            AvlNode<E> current;
            @Nullable Multiset.Entry<E> prevEntry;
            {
                this.current = TreeMultiset.this.firstNode();
            }

            @Override
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (TreeMultiset.this.range.tooHigh(this.current.getElement())) {
                    this.current = null;
                    return false;
                }
                return true;
            }

            @Override
            public Multiset.Entry<E> next() {
                Multiset.Entry result;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.prevEntry = result = TreeMultiset.this.wrapEntry(this.current);
                this.current = this.current.succ == TreeMultiset.this.header ? null : this.current.succ;
                return result;
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }
        };
    }

    @Override
    Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new Iterator<Multiset.Entry<E>>(){
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry;
            {
                this.current = TreeMultiset.this.lastNode();
                this.prevEntry = null;
            }

            @Override
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (TreeMultiset.this.range.tooLow(this.current.getElement())) {
                    this.current = null;
                    return false;
                }
                return true;
            }

            @Override
            public Multiset.Entry<E> next() {
                Multiset.Entry result;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.prevEntry = result = TreeMultiset.this.wrapEntry(this.current);
                this.current = this.current.pred == TreeMultiset.this.header ? null : this.current.pred;
                return result;
            }

            @Override
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }
        };
    }

    @Override
    public void forEachEntry(ObjIntConsumer<? super E> action) {
        Preconditions.checkNotNull(action);
        AvlNode node = this.firstNode();
        while (node != this.header && node != null && !this.range.tooHigh(node.getElement())) {
            action.accept(node.getElement(), node.getCount());
            node = node.succ;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override
    public SortedMultiset<E> headMultiset(@Nullable E upperBound, BoundType boundType) {
        return new TreeMultiset<E>(this.rootReference, this.range.intersect(GeneralRange.upTo(this.comparator(), upperBound, boundType)), this.header);
    }

    @Override
    public SortedMultiset<E> tailMultiset(@Nullable E lowerBound, BoundType boundType) {
        return new TreeMultiset<E>(this.rootReference, this.range.intersect(GeneralRange.downTo(this.comparator(), lowerBound, boundType)), this.header);
    }

    private static <T> void successor(AvlNode<T> a, AvlNode<T> b) {
        ((AvlNode)a).succ = (AvlNode)b;
        ((AvlNode)b).pred = (AvlNode)a;
    }

    private static <T> void successor(AvlNode<T> a, AvlNode<T> b, AvlNode<T> c) {
        TreeMultiset.successor(a, b);
        TreeMultiset.successor(b, c);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.elementSet().comparator());
        Serialization.writeMultiset(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        Comparator comparator = (Comparator)stream.readObject();
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set((AbstractSortedMultiset)this, comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set(this, GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set(this, new Reference());
        AvlNode<Object> header = new AvlNode<Object>(null, 1);
        Serialization.getFieldSetter(TreeMultiset.class, "header").set(this, header);
        TreeMultiset.successor(header, header);
        Serialization.populateMultiset(this, stream);
    }

    private static final class AvlNode<E> {
        private final @Nullable E elem;
        private int elemCount;
        private int distinctElements;
        private long totalCount;
        private int height;
        private @Nullable AvlNode<E> left;
        private @Nullable AvlNode<E> right;
        private @Nullable AvlNode<E> pred;
        private @Nullable AvlNode<E> succ;

        AvlNode(@Nullable E elem, int elemCount) {
            Preconditions.checkArgument(elemCount > 0);
            this.elem = elem;
            this.elemCount = elemCount;
            this.totalCount = elemCount;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        public int count(Comparator<? super E> comparator, E e) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                return this.left == null ? 0 : this.left.count(comparator, e);
            }
            if (cmp > 0) {
                return this.right == null ? 0 : this.right.count(comparator, e);
            }
            return this.elemCount;
        }

        private AvlNode<E> addRightChild(E e, int count) {
            this.right = new AvlNode<E>(e, count);
            TreeMultiset.successor(this, this.right, this.succ);
            this.height = Math.max(2, this.height);
            ++this.distinctElements;
            this.totalCount += (long)count;
            return this;
        }

        private AvlNode<E> addLeftChild(E e, int count) {
            this.left = new AvlNode<E>(e, count);
            TreeMultiset.successor(this.pred, this.left, this);
            this.height = Math.max(2, this.height);
            ++this.distinctElements;
            this.totalCount += (long)count;
            return this;
        }

        AvlNode<E> add(Comparator<? super E> comparator, @Nullable E e, int count, int[] result) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                AvlNode<E> initLeft = this.left;
                if (initLeft == null) {
                    result[0] = 0;
                    return this.addLeftChild(e, count);
                }
                int initHeight = initLeft.height;
                this.left = initLeft.add(comparator, e, count, result);
                if (result[0] == 0) {
                    ++this.distinctElements;
                }
                this.totalCount += (long)count;
                return this.left.height == initHeight ? this : this.rebalance();
            }
            if (cmp > 0) {
                AvlNode<E> initRight = this.right;
                if (initRight == null) {
                    result[0] = 0;
                    return this.addRightChild(e, count);
                }
                int initHeight = initRight.height;
                this.right = initRight.add(comparator, e, count, result);
                if (result[0] == 0) {
                    ++this.distinctElements;
                }
                this.totalCount += (long)count;
                return this.right.height == initHeight ? this : super.rebalance();
            }
            result[0] = this.elemCount;
            long resultCount = (long)this.elemCount + (long)count;
            Preconditions.checkArgument(resultCount <= Integer.MAX_VALUE);
            this.elemCount += count;
            this.totalCount += (long)count;
            return this;
        }

        AvlNode<E> remove(Comparator<? super E> comparator, @Nullable E e, int count, int[] result) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                AvlNode<E> initLeft = this.left;
                if (initLeft == null) {
                    result[0] = 0;
                    return this;
                }
                this.left = initLeft.remove(comparator, e, count, result);
                if (result[0] > 0) {
                    if (count >= result[0]) {
                        --this.distinctElements;
                        this.totalCount -= (long)result[0];
                    } else {
                        this.totalCount -= (long)count;
                    }
                }
                return result[0] == 0 ? this : this.rebalance();
            }
            if (cmp > 0) {
                AvlNode<E> initRight = this.right;
                if (initRight == null) {
                    result[0] = 0;
                    return this;
                }
                this.right = initRight.remove(comparator, e, count, result);
                if (result[0] > 0) {
                    if (count >= result[0]) {
                        --this.distinctElements;
                        this.totalCount -= (long)result[0];
                    } else {
                        this.totalCount -= (long)count;
                    }
                }
                return this.rebalance();
            }
            result[0] = this.elemCount;
            if (count >= this.elemCount) {
                return this.deleteMe();
            }
            this.elemCount -= count;
            this.totalCount -= (long)count;
            return this;
        }

        AvlNode<E> setCount(Comparator<? super E> comparator, @Nullable E e, int count, int[] result) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                AvlNode<E> initLeft = this.left;
                if (initLeft == null) {
                    result[0] = 0;
                    return count > 0 ? this.addLeftChild(e, count) : this;
                }
                this.left = initLeft.setCount(comparator, e, count, result);
                if (count == 0 && result[0] != 0) {
                    --this.distinctElements;
                } else if (count > 0 && result[0] == 0) {
                    ++this.distinctElements;
                }
                this.totalCount += (long)(count - result[0]);
                return super.rebalance();
            }
            if (cmp > 0) {
                AvlNode<E> initRight = this.right;
                if (initRight == null) {
                    result[0] = 0;
                    return count > 0 ? super.addRightChild(e, count) : this;
                }
                this.right = initRight.setCount(comparator, e, count, result);
                if (count == 0 && result[0] != 0) {
                    --this.distinctElements;
                } else if (count > 0 && result[0] == 0) {
                    ++this.distinctElements;
                }
                this.totalCount += (long)(count - result[0]);
                return super.rebalance();
            }
            result[0] = this.elemCount;
            if (count == 0) {
                return this.deleteMe();
            }
            this.totalCount += (long)(count - this.elemCount);
            this.elemCount = count;
            return this;
        }

        AvlNode<E> setCount(Comparator<? super E> comparator, @Nullable E e, int expectedCount, int newCount, int[] result) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                AvlNode<E> initLeft = this.left;
                if (initLeft == null) {
                    result[0] = 0;
                    if (expectedCount == 0 && newCount > 0) {
                        return this.addLeftChild(e, newCount);
                    }
                    return this;
                }
                this.left = initLeft.setCount(comparator, e, expectedCount, newCount, result);
                if (result[0] == expectedCount) {
                    if (newCount == 0 && result[0] != 0) {
                        --this.distinctElements;
                    } else if (newCount > 0 && result[0] == 0) {
                        ++this.distinctElements;
                    }
                    this.totalCount += (long)(newCount - result[0]);
                }
                return this.rebalance();
            }
            if (cmp > 0) {
                AvlNode<E> initRight = this.right;
                if (initRight == null) {
                    result[0] = 0;
                    if (expectedCount == 0 && newCount > 0) {
                        return this.addRightChild(e, newCount);
                    }
                    return this;
                }
                this.right = initRight.setCount(comparator, e, expectedCount, newCount, result);
                if (result[0] == expectedCount) {
                    if (newCount == 0 && result[0] != 0) {
                        --this.distinctElements;
                    } else if (newCount > 0 && result[0] == 0) {
                        ++this.distinctElements;
                    }
                    this.totalCount += (long)(newCount - result[0]);
                }
                return this.rebalance();
            }
            result[0] = this.elemCount;
            if (expectedCount == this.elemCount) {
                if (newCount == 0) {
                    return this.deleteMe();
                }
                this.totalCount += (long)(newCount - this.elemCount);
                this.elemCount = newCount;
            }
            return this;
        }

        private AvlNode<E> deleteMe() {
            int oldElemCount = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            if (this.left == null) {
                return this.right;
            }
            if (this.right == null) {
                return this.left;
            }
            if (this.left.height >= this.right.height) {
                AvlNode<E> newTop = this.pred;
                newTop.left = super.removeMax(newTop);
                newTop.right = this.right;
                newTop.distinctElements = this.distinctElements - 1;
                newTop.totalCount = this.totalCount - (long)oldElemCount;
                return super.rebalance();
            }
            AvlNode<E> newTop = this.succ;
            newTop.right = super.removeMin(newTop);
            newTop.left = this.left;
            newTop.distinctElements = this.distinctElements - 1;
            newTop.totalCount = this.totalCount - (long)oldElemCount;
            return super.rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> node) {
            if (this.left == null) {
                return this.right;
            }
            this.left = super.removeMin(node);
            --this.distinctElements;
            this.totalCount -= (long)node.elemCount;
            return this.rebalance();
        }

        private AvlNode<E> removeMax(AvlNode<E> node) {
            if (this.right == null) {
                return this.left;
            }
            this.right = super.removeMax(node);
            --this.distinctElements;
            this.totalCount -= (long)node.elemCount;
            return this.rebalance();
        }

        private void recomputeMultiset() {
            this.distinctElements = 1 + TreeMultiset.distinctElements(this.left) + TreeMultiset.distinctElements(this.right);
            this.totalCount = (long)this.elemCount + AvlNode.totalCount(this.left) + AvlNode.totalCount(this.right);
        }

        private void recomputeHeight() {
            this.height = 1 + Math.max(AvlNode.height(this.left), AvlNode.height(this.right));
        }

        private void recompute() {
            this.recomputeMultiset();
            this.recomputeHeight();
        }

        private AvlNode<E> rebalance() {
            switch (this.balanceFactor()) {
                case -2: {
                    if (super.balanceFactor() > 0) {
                        this.right = super.rotateRight();
                    }
                    return this.rotateLeft();
                }
                case 2: {
                    if (super.balanceFactor() < 0) {
                        this.left = super.rotateLeft();
                    }
                    return this.rotateRight();
                }
            }
            this.recomputeHeight();
            return this;
        }

        private int balanceFactor() {
            return AvlNode.height(this.left) - AvlNode.height(this.right);
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> newTop = this.right;
            this.right = newTop.left;
            newTop.left = this;
            newTop.totalCount = this.totalCount;
            newTop.distinctElements = this.distinctElements;
            this.recompute();
            super.recomputeHeight();
            return newTop;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> newTop = this.left;
            this.left = newTop.right;
            newTop.right = this;
            newTop.totalCount = this.totalCount;
            newTop.distinctElements = this.distinctElements;
            this.recompute();
            super.recomputeHeight();
            return newTop;
        }

        private static long totalCount(@Nullable AvlNode<?> node) {
            return node == null ? 0L : node.totalCount;
        }

        private static int height(@Nullable AvlNode<?> node) {
            return node == null ? 0 : node.height;
        }

        private @Nullable AvlNode<E> ceiling(Comparator<? super E> comparator, E e) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp < 0) {
                return this.left == null ? this : MoreObjects.firstNonNull(super.ceiling(comparator, e), this);
            }
            if (cmp == 0) {
                return this;
            }
            return this.right == null ? null : super.ceiling(comparator, e);
        }

        private @Nullable AvlNode<E> floor(Comparator<? super E> comparator, E e) {
            int cmp = comparator.compare(e, this.elem);
            if (cmp > 0) {
                return this.right == null ? this : MoreObjects.firstNonNull(super.floor(comparator, e), this);
            }
            if (cmp == 0) {
                return this;
            }
            return this.left == null ? null : super.floor(comparator, e);
        }

        E getElement() {
            return this.elem;
        }

        int getCount() {
            return this.elemCount;
        }

        public String toString() {
            return Multisets.immutableEntry(this.getElement(), this.getCount()).toString();
        }
    }

    private static final class Reference<T> {
        private @Nullable T value;

        private Reference() {
        }

        public @Nullable T get() {
            return this.value;
        }

        public void checkAndSet(@Nullable T expected, T newValue) {
            if (this.value != expected) {
                throw new ConcurrentModificationException();
            }
            this.value = newValue;
        }

        void clear() {
            this.value = null;
        }
    }

    private static enum Aggregate {
        SIZE{

            @Override
            int nodeAggregate(AvlNode<?> node) {
                return ((AvlNode)node).elemCount;
            }

            @Override
            long treeAggregate(@Nullable AvlNode<?> root) {
                return root == null ? 0L : ((AvlNode)root).totalCount;
            }
        }
        ,
        DISTINCT{

            @Override
            int nodeAggregate(AvlNode<?> node) {
                return 1;
            }

            @Override
            long treeAggregate(@Nullable AvlNode<?> root) {
                return root == null ? 0L : (long)((AvlNode)root).distinctElements;
            }
        };


        abstract int nodeAggregate(AvlNode<?> var1);

        abstract long treeAggregate(@Nullable AvlNode<?> var1);
    }
}

