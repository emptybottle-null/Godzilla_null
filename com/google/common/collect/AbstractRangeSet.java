/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
abstract class AbstractRangeSet<C extends Comparable>
implements RangeSet<C> {
    AbstractRangeSet() {
    }

    @Override
    public boolean contains(C value) {
        return this.rangeContaining(value) != null;
    }

    @Override
    public abstract Range<C> rangeContaining(C var1);

    @Override
    public boolean isEmpty() {
        return this.asRanges().isEmpty();
    }

    @Override
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.remove(Range.all());
    }

    @Override
    public boolean enclosesAll(RangeSet<C> other) {
        return this.enclosesAll(other.asRanges());
    }

    @Override
    public void addAll(RangeSet<C> other) {
        this.addAll(other.asRanges());
    }

    @Override
    public void removeAll(RangeSet<C> other) {
        this.removeAll(other.asRanges());
    }

    @Override
    public boolean intersects(Range<C> otherRange) {
        return !this.subRangeSet(otherRange).isEmpty();
    }

    @Override
    public abstract boolean encloses(Range<C> var1);

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RangeSet) {
            RangeSet other = (RangeSet)obj;
            return this.asRanges().equals(other.asRanges());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.asRanges().hashCode();
    }

    @Override
    public final String toString() {
        return this.asRanges().toString();
    }
}

