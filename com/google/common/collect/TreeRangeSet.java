/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractNavigableMap;
import com.google.common.collect.AbstractRangeSet;
import com.google.common.collect.BoundType;
import com.google.common.collect.Cut;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public class TreeRangeSet<C extends Comparable<?>>
extends AbstractRangeSet<C>
implements Serializable {
    @VisibleForTesting
    final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
    private transient @MonotonicNonNull Set<Range<C>> asRanges;
    private transient @MonotonicNonNull Set<Range<C>> asDescendingSetOfRanges;
    private transient @MonotonicNonNull RangeSet<C> complement;

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<C>(new TreeMap<Cut<C>, Range<C>>());
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> result = TreeRangeSet.create();
        result.addAll((RangeSet)rangeSet);
        return result;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> ranges) {
        TreeRangeSet<C> result = TreeRangeSet.create();
        result.addAll(ranges);
        return result;
    }

    private TreeRangeSet(NavigableMap<Cut<C>, Range<C>> rangesByLowerCut) {
        this.rangesByLowerBound = rangesByLowerCut;
    }

    @Override
    public Set<Range<C>> asRanges() {
        AsRanges result = this.asRanges;
        return result == null ? (this.asRanges = new AsRanges(this.rangesByLowerBound.values())) : result;
    }

    @Override
    public Set<Range<C>> asDescendingSetOfRanges() {
        AsRanges result = this.asDescendingSetOfRanges;
        return result == null ? (this.asDescendingSetOfRanges = new AsRanges(this.rangesByLowerBound.descendingMap().values())) : result;
    }

    @Override
    public @Nullable Range<C> rangeContaining(C value) {
        Preconditions.checkNotNull(value);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(Cut.belowValue(value));
        if (floorEntry != null && floorEntry.getValue().contains(value)) {
            return floorEntry.getValue();
        }
        return null;
    }

    @Override
    public boolean intersects(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry ceilingEntry = this.rangesByLowerBound.ceilingEntry(range.lowerBound);
        if (ceilingEntry != null && ceilingEntry.getValue().isConnected(range) && !ceilingEntry.getValue().intersection(range).isEmpty()) {
            return true;
        }
        Map.Entry priorEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
        return priorEntry != null && priorEntry.getValue().isConnected(range) && !priorEntry.getValue().intersection(range).isEmpty();
    }

    @Override
    public boolean encloses(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return floorEntry != null && floorEntry.getValue().encloses(range);
    }

    private @Nullable Range<C> rangeEnclosing(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return floorEntry != null && floorEntry.getValue().encloses(range) ? floorEntry.getValue() : null;
    }

    @Override
    public Range<C> span() {
        Map.Entry<Cut<C>, Range<C>> firstEntry = this.rangesByLowerBound.firstEntry();
        Map.Entry<Cut<C>, Range<C>> lastEntry = this.rangesByLowerBound.lastEntry();
        if (firstEntry == null) {
            throw new NoSuchElementException();
        }
        return Range.create(firstEntry.getValue().lowerBound, lastEntry.getValue().upperBound);
    }

    @Override
    public void add(Range<C> rangeToAdd) {
        Map.Entry entryBelowUB;
        Preconditions.checkNotNull(rangeToAdd);
        if (rangeToAdd.isEmpty()) {
            return;
        }
        Cut lbToAdd = rangeToAdd.lowerBound;
        Cut ubToAdd = rangeToAdd.upperBound;
        Map.Entry entryBelowLB = this.rangesByLowerBound.lowerEntry(lbToAdd);
        if (entryBelowLB != null) {
            Range<C> rangeBelowLB = entryBelowLB.getValue();
            if (rangeBelowLB.upperBound.compareTo(lbToAdd) >= 0) {
                if (rangeBelowLB.upperBound.compareTo(ubToAdd) >= 0) {
                    ubToAdd = rangeBelowLB.upperBound;
                }
                lbToAdd = rangeBelowLB.lowerBound;
            }
        }
        if ((entryBelowUB = this.rangesByLowerBound.floorEntry(ubToAdd)) != null) {
            Range<C> rangeBelowUB = entryBelowUB.getValue();
            if (rangeBelowUB.upperBound.compareTo(ubToAdd) >= 0) {
                ubToAdd = rangeBelowUB.upperBound;
            }
        }
        this.rangesByLowerBound.subMap(lbToAdd, ubToAdd).clear();
        this.replaceRangeWithSameLowerBound(Range.create(lbToAdd, ubToAdd));
    }

    @Override
    public void remove(Range<C> rangeToRemove) {
        Map.Entry entryBelowUB;
        Preconditions.checkNotNull(rangeToRemove);
        if (rangeToRemove.isEmpty()) {
            return;
        }
        Map.Entry entryBelowLB = this.rangesByLowerBound.lowerEntry(rangeToRemove.lowerBound);
        if (entryBelowLB != null) {
            Range<C> rangeBelowLB = entryBelowLB.getValue();
            if (rangeBelowLB.upperBound.compareTo(rangeToRemove.lowerBound) >= 0) {
                if (rangeToRemove.hasUpperBound() && rangeBelowLB.upperBound.compareTo(rangeToRemove.upperBound) >= 0) {
                    this.replaceRangeWithSameLowerBound(Range.create(rangeToRemove.upperBound, rangeBelowLB.upperBound));
                }
                this.replaceRangeWithSameLowerBound(Range.create(rangeBelowLB.lowerBound, rangeToRemove.lowerBound));
            }
        }
        if ((entryBelowUB = this.rangesByLowerBound.floorEntry(rangeToRemove.upperBound)) != null) {
            Range<C> rangeBelowUB = entryBelowUB.getValue();
            if (rangeToRemove.hasUpperBound() && rangeBelowUB.upperBound.compareTo(rangeToRemove.upperBound) >= 0) {
                this.replaceRangeWithSameLowerBound(Range.create(rangeToRemove.upperBound, rangeBelowUB.upperBound));
            }
        }
        this.rangesByLowerBound.subMap(rangeToRemove.lowerBound, rangeToRemove.upperBound).clear();
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.rangesByLowerBound.remove(range.lowerBound);
        } else {
            this.rangesByLowerBound.put(range.lowerBound, range);
        }
    }

    @Override
    public RangeSet<C> complement() {
        Complement result = this.complement;
        return result == null ? (this.complement = new Complement()) : result;
    }

    @Override
    public RangeSet<C> subRangeSet(Range<C> view) {
        return view.equals(Range.all()) ? this : new SubRangeSet(view);
    }

    private final class SubRangeSet
    extends TreeRangeSet<C> {
        private final Range<C> restriction;

        SubRangeSet(Range<C> restriction) {
            super(new SubRangeSetRangesByLowerBound(Range.all(), restriction, TreeRangeSet.this.rangesByLowerBound));
            this.restriction = restriction;
        }

        @Override
        public boolean encloses(Range<C> range) {
            if (!this.restriction.isEmpty() && this.restriction.encloses(range)) {
                Range enclosing = TreeRangeSet.this.rangeEnclosing(range);
                return enclosing != null && !enclosing.intersection(this.restriction).isEmpty();
            }
            return false;
        }

        @Override
        public @Nullable Range<C> rangeContaining(C value) {
            if (!this.restriction.contains(value)) {
                return null;
            }
            Range result = TreeRangeSet.this.rangeContaining(value);
            return result == null ? null : result.intersection(this.restriction);
        }

        @Override
        public void add(Range<C> rangeToAdd) {
            Preconditions.checkArgument(this.restriction.encloses(rangeToAdd), "Cannot add range %s to subRangeSet(%s)", rangeToAdd, this.restriction);
            super.add(rangeToAdd);
        }

        @Override
        public void remove(Range<C> rangeToRemove) {
            if (rangeToRemove.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(rangeToRemove.intersection(this.restriction));
            }
        }

        @Override
        public boolean contains(C value) {
            return this.restriction.contains(value) && TreeRangeSet.this.contains((Comparable)value);
        }

        @Override
        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        @Override
        public RangeSet<C> subRangeSet(Range<C> view) {
            if (view.encloses(this.restriction)) {
                return this;
            }
            if (view.isConnected(this.restriction)) {
                return new SubRangeSet(this.restriction.intersection(view));
            }
            return ImmutableRangeSet.of();
        }
    }

    private static final class SubRangeSetRangesByLowerBound<C extends Comparable<?>>
    extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final Range<Cut<C>> lowerBoundWindow;
        private final Range<C> restriction;
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> rangesByUpperBound;

        private SubRangeSetRangesByLowerBound(Range<Cut<C>> lowerBoundWindow, Range<C> restriction, NavigableMap<Cut<C>, Range<C>> rangesByLowerBound) {
            this.lowerBoundWindow = Preconditions.checkNotNull(lowerBoundWindow);
            this.restriction = Preconditions.checkNotNull(restriction);
            this.rangesByLowerBound = Preconditions.checkNotNull(rangesByLowerBound);
            this.rangesByUpperBound = new RangesByUpperBound<C>(rangesByLowerBound);
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            if (!window.isConnected(this.lowerBoundWindow)) {
                return ImmutableSortedMap.of();
            }
            return new SubRangeSetRangesByLowerBound<C>(this.lowerBoundWindow.intersection(window), this.restriction, this.rangesByLowerBound);
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return this.subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return this.subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return this.subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return this.get(key) != null;
        }

        @Override
        public @Nullable Range<C> get(@Nullable Object key) {
            if (key instanceof Cut) {
                try {
                    Cut cut = (Cut)key;
                    if (!this.lowerBoundWindow.contains(cut) || cut.compareTo(this.restriction.lowerBound) < 0 || cut.compareTo(this.restriction.upperBound) >= 0) {
                        return null;
                    }
                    if (cut.equals(this.restriction.lowerBound)) {
                        Range<C> candidate = Maps.valueOrNull(this.rangesByLowerBound.floorEntry(cut));
                        if (candidate != null && candidate.upperBound.compareTo(this.restriction.lowerBound) > 0) {
                            return candidate.intersection(this.restriction);
                        }
                    } else {
                        Range result = (Range)this.rangesByLowerBound.get(cut);
                        if (result != null) {
                            return result.intersection(this.restriction);
                        }
                    }
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            if (this.restriction.isEmpty()) {
                return Iterators.emptyIterator();
            }
            if (this.lowerBoundWindow.upperBound.isLessThan(this.restriction.lowerBound)) {
                return Iterators.emptyIterator();
            }
            final Iterator completeRangeItr = this.lowerBoundWindow.lowerBound.isLessThan(this.restriction.lowerBound) ? this.rangesByUpperBound.tailMap(this.restriction.lowerBound, false).values().iterator() : this.rangesByLowerBound.tailMap((Cut<C>)this.lowerBoundWindow.lowerBound.endpoint(), this.lowerBoundWindow.lowerBoundType() == BoundType.CLOSED).values().iterator();
            final Cut upperBoundOnLowerBounds = Ordering.natural().min(this.lowerBoundWindow.upperBound, Cut.belowValue(this.restriction.upperBound));
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!completeRangeItr.hasNext()) {
                        return (Map.Entry)this.endOfData();
                    }
                    Range nextRange = (Range)completeRangeItr.next();
                    if (upperBoundOnLowerBounds.isLessThan(nextRange.lowerBound)) {
                        return (Map.Entry)this.endOfData();
                    }
                    nextRange = nextRange.intersection(restriction);
                    return Maps.immutableEntry(nextRange.lowerBound, nextRange);
                }
            };
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            if (this.restriction.isEmpty()) {
                return Iterators.emptyIterator();
            }
            Cut upperBoundOnLowerBounds = Ordering.natural().min(this.lowerBoundWindow.upperBound, Cut.belowValue(this.restriction.upperBound));
            final Iterator completeRangeItr = this.rangesByLowerBound.headMap(upperBoundOnLowerBounds.endpoint(), upperBoundOnLowerBounds.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!completeRangeItr.hasNext()) {
                        return (Map.Entry)this.endOfData();
                    }
                    Range nextRange = (Range)completeRangeItr.next();
                    if (((SubRangeSetRangesByLowerBound)this).restriction.lowerBound.compareTo(nextRange.upperBound) >= 0) {
                        return (Map.Entry)this.endOfData();
                    }
                    nextRange = nextRange.intersection(restriction);
                    if (lowerBoundWindow.contains(nextRange.lowerBound)) {
                        return Maps.immutableEntry(nextRange.lowerBound, nextRange);
                    }
                    return (Map.Entry)this.endOfData();
                }
            };
        }

        @Override
        public int size() {
            return Iterators.size(this.entryIterator());
        }
    }

    private final class Complement
    extends TreeRangeSet<C> {
        Complement() {
            super(new ComplementRangesByLowerBound(TreeRangeSet.this.rangesByLowerBound));
        }

        @Override
        public void add(Range<C> rangeToAdd) {
            TreeRangeSet.this.remove(rangeToAdd);
        }

        @Override
        public void remove(Range<C> rangeToRemove) {
            TreeRangeSet.this.add(rangeToRemove);
        }

        @Override
        public boolean contains(C value) {
            return !TreeRangeSet.this.contains((Comparable)value);
        }

        @Override
        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }
    }

    private static final class ComplementRangesByLowerBound<C extends Comparable<?>>
    extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByUpperBound;
        private final Range<Cut<C>> complementLowerBoundWindow;

        ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound) {
            this(positiveRangesByLowerBound, Range.all());
        }

        private ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound, Range<Cut<C>> window) {
            this.positiveRangesByLowerBound = positiveRangesByLowerBound;
            this.positiveRangesByUpperBound = new RangesByUpperBound<C>(positiveRangesByLowerBound);
            this.complementLowerBoundWindow = window;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> subWindow) {
            if (!this.complementLowerBoundWindow.isConnected(subWindow)) {
                return ImmutableSortedMap.of();
            }
            subWindow = subWindow.intersection(this.complementLowerBoundWindow);
            return new ComplementRangesByLowerBound<C>(this.positiveRangesByLowerBound, subWindow);
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return this.subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return this.subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return this.subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            Cut firstComplementRangeLowerBound;
            Collection positiveRanges = this.complementLowerBoundWindow.hasLowerBound() ? this.positiveRangesByUpperBound.tailMap(this.complementLowerBoundWindow.lowerEndpoint(), this.complementLowerBoundWindow.lowerBoundType() == BoundType.CLOSED).values() : this.positiveRangesByUpperBound.values();
            final PeekingIterator positiveItr = Iterators.peekingIterator(positiveRanges.iterator());
            if (this.complementLowerBoundWindow.contains(Cut.belowAll()) && (!positiveItr.hasNext() || ((Range)positiveItr.peek()).lowerBound != Cut.belowAll())) {
                firstComplementRangeLowerBound = Cut.belowAll();
            } else if (positiveItr.hasNext()) {
                firstComplementRangeLowerBound = ((Range)positiveItr.next()).upperBound;
            } else {
                return Iterators.emptyIterator();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){
                Cut<C> nextComplementRangeLowerBound;
                {
                    this.nextComplementRangeLowerBound = firstComplementRangeLowerBound;
                }

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    Range negativeRange;
                    if (((ComplementRangesByLowerBound)this).complementLowerBoundWindow.upperBound.isLessThan(this.nextComplementRangeLowerBound) || this.nextComplementRangeLowerBound == Cut.aboveAll()) {
                        return (Map.Entry)this.endOfData();
                    }
                    if (positiveItr.hasNext()) {
                        Range positiveRange = (Range)positiveItr.next();
                        negativeRange = Range.create(this.nextComplementRangeLowerBound, positiveRange.lowerBound);
                        this.nextComplementRangeLowerBound = positiveRange.upperBound;
                    } else {
                        negativeRange = Range.create(this.nextComplementRangeLowerBound, Cut.aboveAll());
                        this.nextComplementRangeLowerBound = Cut.aboveAll();
                    }
                    return Maps.immutableEntry(negativeRange.lowerBound, negativeRange);
                }
            };
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            Cut cut;
            boolean inclusive;
            Cut startingPoint = this.complementLowerBoundWindow.hasUpperBound() ? this.complementLowerBoundWindow.upperEndpoint() : Cut.aboveAll();
            final PeekingIterator positiveItr = Iterators.peekingIterator(this.positiveRangesByUpperBound.headMap(startingPoint, inclusive = this.complementLowerBoundWindow.hasUpperBound() && this.complementLowerBoundWindow.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (positiveItr.hasNext()) {
                cut = ((Range)positiveItr.peek()).upperBound == Cut.aboveAll() ? ((Range)positiveItr.next()).lowerBound : this.positiveRangesByLowerBound.higherKey(((Range)positiveItr.peek()).upperBound);
            } else {
                if (!this.complementLowerBoundWindow.contains(Cut.belowAll()) || this.positiveRangesByLowerBound.containsKey(Cut.belowAll())) {
                    return Iterators.emptyIterator();
                }
                cut = this.positiveRangesByLowerBound.higherKey(Cut.belowAll());
            }
            final Cut firstComplementRangeUpperBound = MoreObjects.firstNonNull(cut, Cut.aboveAll());
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){
                Cut<C> nextComplementRangeUpperBound;
                {
                    this.nextComplementRangeUpperBound = firstComplementRangeUpperBound;
                }

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (this.nextComplementRangeUpperBound == Cut.belowAll()) {
                        return (Map.Entry)this.endOfData();
                    }
                    if (positiveItr.hasNext()) {
                        Range positiveRange = (Range)positiveItr.next();
                        Range negativeRange = Range.create(positiveRange.upperBound, this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = positiveRange.lowerBound;
                        if (((ComplementRangesByLowerBound)this).complementLowerBoundWindow.lowerBound.isLessThan(negativeRange.lowerBound)) {
                            return Maps.immutableEntry(negativeRange.lowerBound, negativeRange);
                        }
                    } else if (((ComplementRangesByLowerBound)this).complementLowerBoundWindow.lowerBound.isLessThan(Cut.belowAll())) {
                        Range negativeRange = Range.create(Cut.belowAll(), this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = Cut.belowAll();
                        return Maps.immutableEntry(Cut.belowAll(), negativeRange);
                    }
                    return (Map.Entry)this.endOfData();
                }
            };
        }

        @Override
        public int size() {
            return Iterators.size(this.entryIterator());
        }

        @Override
        public @Nullable Range<C> get(Object key) {
            if (key instanceof Cut) {
                try {
                    Cut cut = (Cut)key;
                    Map.Entry<Cut<C>, Range<C>> firstEntry = this.tailMap(cut, true).firstEntry();
                    if (firstEntry != null && firstEntry.getKey().equals(cut)) {
                        return firstEntry.getValue();
                    }
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        @Override
        public boolean containsKey(Object key) {
            return this.get(key) != null;
        }
    }

    @VisibleForTesting
    static final class RangesByUpperBound<C extends Comparable<?>>
    extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final Range<Cut<C>> upperBoundWindow;

        RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound) {
            this.rangesByLowerBound = rangesByLowerBound;
            this.upperBoundWindow = Range.all();
        }

        private RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound, Range<Cut<C>> upperBoundWindow) {
            this.rangesByLowerBound = rangesByLowerBound;
            this.upperBoundWindow = upperBoundWindow;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            if (window.isConnected(this.upperBoundWindow)) {
                return new RangesByUpperBound<C>(this.rangesByLowerBound, window.intersection(this.upperBoundWindow));
            }
            return ImmutableSortedMap.of();
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return this.subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return this.subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return this.subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        @Override
        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return this.get(key) != null;
        }

        @Override
        public Range<C> get(@Nullable Object key) {
            if (key instanceof Cut) {
                try {
                    Cut cut = (Cut)key;
                    if (!this.upperBoundWindow.contains(cut)) {
                        return null;
                    }
                    Map.Entry<Cut, Range<C>> candidate = this.rangesByLowerBound.lowerEntry(cut);
                    if (candidate != null && candidate.getValue().upperBound.equals(cut)) {
                        return candidate.getValue();
                    }
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            Map.Entry<Cut<C>, Range<C>> lowerEntry;
            final Iterator backingItr = !this.upperBoundWindow.hasLowerBound() ? this.rangesByLowerBound.values().iterator() : ((lowerEntry = this.rangesByLowerBound.lowerEntry(this.upperBoundWindow.lowerEndpoint())) == null ? this.rangesByLowerBound.values().iterator() : (this.upperBoundWindow.lowerBound.isLessThan(lowerEntry.getValue().upperBound) ? this.rangesByLowerBound.tailMap(lowerEntry.getKey(), true).values().iterator() : this.rangesByLowerBound.tailMap(this.upperBoundWindow.lowerEndpoint(), true).values().iterator()));
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!backingItr.hasNext()) {
                        return (Map.Entry)this.endOfData();
                    }
                    Range range = (Range)backingItr.next();
                    if (((RangesByUpperBound)this).upperBoundWindow.upperBound.isLessThan(range.upperBound)) {
                        return (Map.Entry)this.endOfData();
                    }
                    return Maps.immutableEntry(range.upperBound, range);
                }
            };
        }

        @Override
        Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            Collection candidates = this.upperBoundWindow.hasUpperBound() ? this.rangesByLowerBound.headMap(this.upperBoundWindow.upperEndpoint(), false).descendingMap().values() : this.rangesByLowerBound.descendingMap().values();
            final PeekingIterator backingItr = Iterators.peekingIterator(candidates.iterator());
            if (backingItr.hasNext() && this.upperBoundWindow.upperBound.isLessThan(((Range)backingItr.peek()).upperBound)) {
                backingItr.next();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(){

                @Override
                protected Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!backingItr.hasNext()) {
                        return (Map.Entry)this.endOfData();
                    }
                    Range range = (Range)backingItr.next();
                    return ((RangesByUpperBound)this).upperBoundWindow.lowerBound.isLessThan(range.upperBound) ? Maps.immutableEntry(range.upperBound, range) : (Map.Entry)this.endOfData();
                }
            };
        }

        @Override
        public int size() {
            if (this.upperBoundWindow.equals(Range.all())) {
                return this.rangesByLowerBound.size();
            }
            return Iterators.size(this.entryIterator());
        }

        @Override
        public boolean isEmpty() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.isEmpty() : !this.entryIterator().hasNext();
        }
    }

    final class AsRanges
    extends ForwardingCollection<Range<C>>
    implements Set<Range<C>> {
        final Collection<Range<C>> delegate;

        AsRanges(Collection<Range<C>> delegate) {
            this.delegate = delegate;
        }

        @Override
        protected Collection<Range<C>> delegate() {
            return this.delegate;
        }

        @Override
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        @Override
        public boolean equals(@Nullable Object o) {
            return Sets.equalsImpl(this, o);
        }
    }
}

