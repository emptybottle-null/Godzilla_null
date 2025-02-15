/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;

public abstract class Pair<L, R>
implements Map.Entry<L, R>,
Comparable<Pair<L, R>>,
Serializable {
    private static final long serialVersionUID = 4954918890077093841L;
    public static final Pair<?, ?>[] EMPTY_ARRAY = new PairAdapter[0];

    public static <L, R> Pair<L, R>[] emptyArray() {
        return EMPTY_ARRAY;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return ImmutablePair.of(left, right);
    }

    public static <L, R> Pair<L, R> of(Map.Entry<L, R> pair) {
        return ImmutablePair.of(pair);
    }

    @Override
    public int compareTo(Pair<L, R> other) {
        return new CompareToBuilder().append(this.getLeft(), other.getLeft()).append(this.getRight(), other.getRight()).toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry other = (Map.Entry)obj;
            return Objects.equals(this.getKey(), other.getKey()) && Objects.equals(this.getValue(), other.getValue());
        }
        return false;
    }

    @Override
    public final L getKey() {
        return this.getLeft();
    }

    public abstract L getLeft();

    public abstract R getRight();

    @Override
    public R getValue() {
        return this.getRight();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getKey()) ^ Objects.hashCode(this.getValue());
    }

    public String toString() {
        return "(" + this.getLeft() + ',' + this.getRight() + ')';
    }

    public String toString(String format) {
        return String.format(format, this.getLeft(), this.getRight());
    }

    private static final class PairAdapter<L, R>
    extends Pair<L, R> {
        private static final long serialVersionUID = 1L;

        private PairAdapter() {
        }

        @Override
        public L getLeft() {
            return null;
        }

        @Override
        public R getRight() {
            return null;
        }

        @Override
        public R setValue(R value) {
            return null;
        }
    }
}

