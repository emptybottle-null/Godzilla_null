/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import java.io.Serializable;
import java.util.Comparator;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
final class GeneralRange<T>
implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final @Nullable T lowerEndpoint;
    private final BoundType lowerBoundType;
    private final boolean hasUpperBound;
    private final @Nullable T upperEndpoint;
    private final BoundType upperBoundType;
    private transient @MonotonicNonNull GeneralRange<T> reverse;

    static <T extends Comparable> GeneralRange<T> from(Range<T> range) {
        @Nullable T lowerEndpoint = range.hasLowerBound() ? (T)range.lowerEndpoint() : null;
        BoundType lowerBoundType = range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN;
        @Nullable T upperEndpoint = range.hasUpperBound() ? (T)range.upperEndpoint() : null;
        BoundType upperBoundType = range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN;
        return new GeneralRange<Object>(Ordering.natural(), range.hasLowerBound(), lowerEndpoint, lowerBoundType, range.hasUpperBound(), upperEndpoint, upperBoundType);
    }

    static <T> GeneralRange<T> all(Comparator<? super T> comparator) {
        return new GeneralRange<Object>(comparator, false, null, BoundType.OPEN, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> downTo(Comparator<? super T> comparator, @Nullable T endpoint, BoundType boundType) {
        return new GeneralRange<Object>((Comparator<Object>)comparator, true, endpoint, boundType, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> upTo(Comparator<? super T> comparator, @Nullable T endpoint, BoundType boundType) {
        return new GeneralRange<Object>((Comparator<Object>)comparator, false, null, BoundType.OPEN, true, endpoint, boundType);
    }

    static <T> GeneralRange<T> range(Comparator<? super T> comparator, @Nullable T lower, BoundType lowerType, @Nullable T upper, BoundType upperType) {
        return new GeneralRange<T>(comparator, true, lower, lowerType, true, upper, upperType);
    }

    private GeneralRange(Comparator<? super T> comparator, boolean hasLowerBound, @Nullable T lowerEndpoint, BoundType lowerBoundType, boolean hasUpperBound, @Nullable T upperEndpoint, BoundType upperBoundType) {
        this.comparator = Preconditions.checkNotNull(comparator);
        this.hasLowerBound = hasLowerBound;
        this.hasUpperBound = hasUpperBound;
        this.lowerEndpoint = lowerEndpoint;
        this.lowerBoundType = Preconditions.checkNotNull(lowerBoundType);
        this.upperEndpoint = upperEndpoint;
        this.upperBoundType = Preconditions.checkNotNull(upperBoundType);
        if (hasLowerBound) {
            comparator.compare(lowerEndpoint, lowerEndpoint);
        }
        if (hasUpperBound) {
            comparator.compare(upperEndpoint, upperEndpoint);
        }
        if (hasLowerBound && hasUpperBound) {
            int cmp = comparator.compare(lowerEndpoint, upperEndpoint);
            Preconditions.checkArgument(cmp <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", lowerEndpoint, upperEndpoint);
            if (cmp == 0) {
                Preconditions.checkArgument(lowerBoundType != BoundType.OPEN | upperBoundType != BoundType.OPEN);
            }
        }
    }

    Comparator<? super T> comparator() {
        return this.comparator;
    }

    boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    boolean isEmpty() {
        return this.hasUpperBound() && this.tooLow(this.getUpperEndpoint()) || this.hasLowerBound() && this.tooHigh(this.getLowerEndpoint());
    }

    boolean tooLow(@Nullable T t) {
        if (!this.hasLowerBound()) {
            return false;
        }
        T lbound = this.getLowerEndpoint();
        int cmp = this.comparator.compare(t, lbound);
        return cmp < 0 | cmp == 0 & this.getLowerBoundType() == BoundType.OPEN;
    }

    boolean tooHigh(@Nullable T t) {
        if (!this.hasUpperBound()) {
            return false;
        }
        T ubound = this.getUpperEndpoint();
        int cmp = this.comparator.compare(t, ubound);
        return cmp > 0 | cmp == 0 & this.getUpperBoundType() == BoundType.OPEN;
    }

    boolean contains(@Nullable T t) {
        return !this.tooLow(t) && !this.tooHigh(t);
    }

    GeneralRange<T> intersect(GeneralRange<T> other) {
        int cmp;
        int cmp2;
        Preconditions.checkNotNull(other);
        Preconditions.checkArgument(this.comparator.equals(other.comparator));
        boolean hasLowBound = this.hasLowerBound;
        @Nullable T lowEnd = this.getLowerEndpoint();
        BoundType lowType = this.getLowerBoundType();
        if (!this.hasLowerBound()) {
            hasLowBound = other.hasLowerBound;
            lowEnd = other.getLowerEndpoint();
            lowType = other.getLowerBoundType();
        } else if (other.hasLowerBound() && ((cmp2 = this.comparator.compare(this.getLowerEndpoint(), other.getLowerEndpoint())) < 0 || cmp2 == 0 && other.getLowerBoundType() == BoundType.OPEN)) {
            lowEnd = other.getLowerEndpoint();
            lowType = other.getLowerBoundType();
        }
        boolean hasUpBound = this.hasUpperBound;
        @Nullable T upEnd = this.getUpperEndpoint();
        BoundType upType = this.getUpperBoundType();
        if (!this.hasUpperBound()) {
            hasUpBound = other.hasUpperBound;
            upEnd = other.getUpperEndpoint();
            upType = other.getUpperBoundType();
        } else if (other.hasUpperBound() && ((cmp = this.comparator.compare(this.getUpperEndpoint(), other.getUpperEndpoint())) > 0 || cmp == 0 && other.getUpperBoundType() == BoundType.OPEN)) {
            upEnd = other.getUpperEndpoint();
            upType = other.getUpperBoundType();
        }
        if (hasLowBound && hasUpBound && ((cmp = this.comparator.compare(lowEnd, upEnd)) > 0 || cmp == 0 && lowType == BoundType.OPEN && upType == BoundType.OPEN)) {
            lowEnd = upEnd;
            lowType = BoundType.OPEN;
            upType = BoundType.CLOSED;
        }
        return new GeneralRange<T>(this.comparator, hasLowBound, lowEnd, lowType, hasUpBound, upEnd, upType);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof GeneralRange) {
            GeneralRange r = (GeneralRange)obj;
            return this.comparator.equals(r.comparator) && this.hasLowerBound == r.hasLowerBound && this.hasUpperBound == r.hasUpperBound && this.getLowerBoundType().equals((Object)r.getLowerBoundType()) && this.getUpperBoundType().equals((Object)r.getUpperBoundType()) && Objects.equal(this.getLowerEndpoint(), r.getLowerEndpoint()) && Objects.equal(this.getUpperEndpoint(), r.getUpperEndpoint());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.comparator, this.getLowerEndpoint(), this.getLowerBoundType(), this.getUpperEndpoint(), this.getUpperBoundType()});
    }

    GeneralRange<T> reverse() {
        GeneralRange<Object> result = this.reverse;
        if (result == null) {
            result = new GeneralRange(Ordering.from(this.comparator).reverse(), this.hasUpperBound, this.getUpperEndpoint(), this.getUpperBoundType(), this.hasLowerBound, this.getLowerEndpoint(), this.getLowerBoundType());
            result.reverse = this;
            this.reverse = result;
            return this.reverse;
        }
        return result;
    }

    public String toString() {
        return this.comparator + ":" + (this.lowerBoundType == BoundType.CLOSED ? (char)'[' : '(') + (this.hasLowerBound ? this.lowerEndpoint : "-\u221e") + ',' + (this.hasUpperBound ? this.upperEndpoint : "\u221e") + (this.upperBoundType == BoundType.CLOSED ? (char)']' : ')');
    }

    T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    BoundType getUpperBoundType() {
        return this.upperBoundType;
    }
}

