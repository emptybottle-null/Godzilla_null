/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Interner;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;

@Beta
@GwtIncompatible
public final class Interners {
    private Interners() {
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        return Interners.newBuilder().strong().build();
    }

    @GwtIncompatible(value="java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return Interners.newBuilder().weak().build();
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        return new InternerFunction<E>(Preconditions.checkNotNull(interner));
    }

    private static class InternerFunction<E>
    implements Function<E, E> {
        private final Interner<E> interner;

        public InternerFunction(Interner<E> interner) {
            this.interner = interner;
        }

        @Override
        public E apply(E input) {
            return this.interner.intern(input);
        }

        public int hashCode() {
            return this.interner.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof InternerFunction) {
                InternerFunction that = (InternerFunction)other;
                return this.interner.equals(that.interner);
            }
            return false;
        }
    }

    @VisibleForTesting
    static final class InternerImpl<E>
    implements Interner<E> {
        @VisibleForTesting
        final MapMakerInternalMap<E, MapMaker.Dummy, ?, ?> map;

        private InternerImpl(MapMaker mapMaker) {
            this.map = MapMakerInternalMap.createWithDummyValues(mapMaker.keyEquivalence(Equivalence.equals()));
        }

        @Override
        public E intern(E sample) {
            MapMaker.Dummy sneaky;
            do {
                Object canonical;
                Object entry;
                if ((entry = this.map.getEntry(sample)) == null || (canonical = entry.getKey()) == null) continue;
                return (E)canonical;
            } while ((sneaky = this.map.putIfAbsent(sample, MapMaker.Dummy.VALUE)) != null);
            return sample;
        }
    }

    public static class InternerBuilder {
        private final MapMaker mapMaker = new MapMaker();
        private boolean strong = true;

        private InternerBuilder() {
        }

        public InternerBuilder strong() {
            this.strong = true;
            return this;
        }

        @GwtIncompatible(value="java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.strong = false;
            return this;
        }

        public InternerBuilder concurrencyLevel(int concurrencyLevel) {
            this.mapMaker.concurrencyLevel(concurrencyLevel);
            return this;
        }

        public <E> Interner<E> build() {
            if (!this.strong) {
                this.mapMaker.weakKeys();
            }
            return new InternerImpl(this.mapMaker);
        }
    }
}

