/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class Cut<C extends Comparable>
implements Comparable<Cut<C>>,
Serializable {
    final @Nullable C endpoint;
    private static final long serialVersionUID = 0L;

    Cut(@Nullable C endpoint) {
        this.endpoint = endpoint;
    }

    abstract boolean isLessThan(C var1);

    abstract BoundType typeAsLowerBound();

    abstract BoundType typeAsUpperBound();

    abstract Cut<C> withLowerBoundType(BoundType var1, DiscreteDomain<C> var2);

    abstract Cut<C> withUpperBoundType(BoundType var1, DiscreteDomain<C> var2);

    abstract void describeAsLowerBound(StringBuilder var1);

    abstract void describeAsUpperBound(StringBuilder var1);

    abstract C leastValueAbove(DiscreteDomain<C> var1);

    abstract C greatestValueBelow(DiscreteDomain<C> var1);

    Cut<C> canonical(DiscreteDomain<C> domain) {
        return this;
    }

    @Override
    public int compareTo(Cut<C> that) {
        if (that == Cut.belowAll()) {
            return 1;
        }
        if (that == Cut.aboveAll()) {
            return -1;
        }
        int result = Range.compareOrThrow(this.endpoint, that.endpoint);
        if (result != 0) {
            return result;
        }
        return Booleans.compare(this instanceof AboveValue, that instanceof AboveValue);
    }

    C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cut) {
            Cut that = (Cut)obj;
            try {
                int compareResult = this.compareTo(that);
                return compareResult == 0;
            } catch (ClassCastException classCastException) {
                // empty catch block
            }
        }
        return false;
    }

    public abstract int hashCode();

    static <C extends Comparable> Cut<C> belowAll() {
        return BelowAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> aboveAll() {
        return AboveAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> belowValue(C endpoint) {
        return new BelowValue<C>(endpoint);
    }

    static <C extends Comparable> Cut<C> aboveValue(C endpoint) {
        return new AboveValue<C>(endpoint);
    }

    private static final class AboveValue<C extends Comparable>
    extends Cut<C> {
        private static final long serialVersionUID = 0L;

        AboveValue(C endpoint) {
            super((Comparable)Preconditions.checkNotNull(endpoint));
        }

        @Override
        boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) < 0;
        }

        @Override
        BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        @Override
        BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        @Override
        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (boundType) {
                case OPEN: {
                    return this;
                }
                case CLOSED: {
                    Comparable next = domain.next(this.endpoint);
                    return next == null ? Cut.belowAll() : AboveValue.belowValue(next);
                }
            }
            throw new AssertionError();
        }

        @Override
        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (boundType) {
                case OPEN: {
                    Comparable next = domain.next(this.endpoint);
                    return next == null ? Cut.aboveAll() : AboveValue.belowValue(next);
                }
                case CLOSED: {
                    return this;
                }
            }
            throw new AssertionError();
        }

        @Override
        void describeAsLowerBound(StringBuilder sb) {
            sb.append('(').append(this.endpoint);
        }

        @Override
        void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint).append(']');
        }

        @Override
        C leastValueAbove(DiscreteDomain<C> domain) {
            return (C)domain.next(this.endpoint);
        }

        @Override
        C greatestValueBelow(DiscreteDomain<C> domain) {
            return (C)this.endpoint;
        }

        @Override
        Cut<C> canonical(DiscreteDomain<C> domain) {
            C next = this.leastValueAbove(domain);
            return next != null ? AboveValue.belowValue(next) : Cut.aboveAll();
        }

        @Override
        public int hashCode() {
            return ~this.endpoint.hashCode();
        }

        public String toString() {
            return "/" + this.endpoint + "\\";
        }
    }

    private static final class BelowValue<C extends Comparable>
    extends Cut<C> {
        private static final long serialVersionUID = 0L;

        BelowValue(C endpoint) {
            super((Comparable)Preconditions.checkNotNull(endpoint));
        }

        @Override
        boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) <= 0;
        }

        @Override
        BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        @Override
        BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        @Override
        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (boundType) {
                case CLOSED: {
                    return this;
                }
                case OPEN: {
                    Comparable previous = domain.previous(this.endpoint);
                    return previous == null ? Cut.belowAll() : new AboveValue<Comparable>(previous);
                }
            }
            throw new AssertionError();
        }

        @Override
        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (boundType) {
                case CLOSED: {
                    Comparable previous = domain.previous(this.endpoint);
                    return previous == null ? Cut.aboveAll() : new AboveValue<Comparable>(previous);
                }
                case OPEN: {
                    return this;
                }
            }
            throw new AssertionError();
        }

        @Override
        void describeAsLowerBound(StringBuilder sb) {
            sb.append('[').append(this.endpoint);
        }

        @Override
        void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint).append(')');
        }

        @Override
        C leastValueAbove(DiscreteDomain<C> domain) {
            return (C)this.endpoint;
        }

        @Override
        C greatestValueBelow(DiscreteDomain<C> domain) {
            return (C)domain.previous(this.endpoint);
        }

        @Override
        public int hashCode() {
            return this.endpoint.hashCode();
        }

        public String toString() {
            return "\\" + this.endpoint + "/";
        }
    }

    private static final class AboveAll
    extends Cut<Comparable<?>> {
        private static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0L;

        private AboveAll() {
            super(null);
        }

        @Override
        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override
        boolean isLessThan(Comparable<?> value) {
            return false;
        }

        @Override
        BoundType typeAsLowerBound() {
            throw new AssertionError((Object)"this statement should be unreachable");
        }

        @Override
        BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        @Override
        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError((Object)"this statement should be unreachable");
        }

        @Override
        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new IllegalStateException();
        }

        @Override
        void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override
        void describeAsUpperBound(StringBuilder sb) {
            sb.append("+\u221e)");
        }

        @Override
        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError();
        }

        @Override
        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> domain) {
            return domain.maxValue();
        }

        @Override
        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : 1;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        public String toString() {
            return "+\u221e";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class BelowAll
    extends Cut<Comparable<?>> {
        private static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0L;

        private BelowAll() {
            super(null);
        }

        @Override
        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override
        boolean isLessThan(Comparable<?> value) {
            return true;
        }

        @Override
        BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        @Override
        BoundType typeAsUpperBound() {
            throw new AssertionError((Object)"this statement should be unreachable");
        }

        @Override
        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new IllegalStateException();
        }

        @Override
        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError((Object)"this statement should be unreachable");
        }

        @Override
        void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-\u221e");
        }

        @Override
        void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override
        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> domain) {
            return domain.minValue();
        }

        @Override
        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError();
        }

        @Override
        Cut<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> domain) {
            try {
                return Cut.belowValue(domain.minValue());
            } catch (NoSuchElementException e) {
                return this;
            }
        }

        @Override
        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : -1;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        public String toString() {
            return "-\u221e";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}

