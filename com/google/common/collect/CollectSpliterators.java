/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
final class CollectSpliterators {
    private CollectSpliterators() {
    }

    static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function) {
        return CollectSpliterators.indexed(size, extraCharacteristics, function, null);
    }

    static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function, Comparator<? super T> comparator) {
        if (comparator != null) {
            Preconditions.checkArgument((extraCharacteristics & 4) != 0);
        }
        class WithCharacteristics
        implements Spliterator<T> {
            private final Spliterator.OfInt delegate;
            final /* synthetic */ IntFunction val$function;
            final /* synthetic */ int val$extraCharacteristics;
            final /* synthetic */ Comparator val$comparator;

            WithCharacteristics(Spliterator.OfInt delegate) {
                this.val$function = intFunction;
                this.val$extraCharacteristics = n;
                this.val$comparator = comparator;
                this.delegate = delegate;
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return this.delegate.tryAdvance((int i) -> action.accept((Object)this.val$function.apply(i)));
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                this.delegate.forEachRemaining((int i) -> action.accept((Object)this.val$function.apply(i)));
            }

            @Override
            public @Nullable Spliterator<T> trySplit() {
                Spliterator.OfInt split = this.delegate.trySplit();
                return split == null ? null : new WithCharacteristics(split);
            }

            @Override
            public long estimateSize() {
                return this.delegate.estimateSize();
            }

            @Override
            public int characteristics() {
                return 0x4050 | this.val$extraCharacteristics;
            }

            @Override
            public Comparator<? super T> getComparator() {
                if (this.hasCharacteristics(4)) {
                    return this.val$comparator;
                }
                throw new IllegalStateException();
            }
        }
        return new WithCharacteristics(IntStream.range(0, size).spliterator());
    }

    static <F, T> Spliterator<T> map(final Spliterator<F> fromSpliterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(function);
        return new Spliterator<T>(){

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return fromSpliterator.tryAdvance(fromElement -> action.accept((Object)function.apply(fromElement)));
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                fromSpliterator.forEachRemaining(fromElement -> action.accept((Object)function.apply(fromElement)));
            }

            @Override
            public Spliterator<T> trySplit() {
                Spliterator fromSplit = fromSpliterator.trySplit();
                return fromSplit != null ? CollectSpliterators.map(fromSplit, function) : null;
            }

            @Override
            public long estimateSize() {
                return fromSpliterator.estimateSize();
            }

            @Override
            public int characteristics() {
                return fromSpliterator.characteristics() & 0xFFFFFEFA;
            }
        };
    }

    static <T> Spliterator<T> filter(final Spliterator<T> fromSpliterator, final Predicate<? super T> predicate) {
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(predicate);
        class Splitr
        implements Spliterator<T>,
        Consumer<T> {
            T holder = null;

            Splitr() {
            }

            @Override
            public void accept(T t) {
                this.holder = t;
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                while (fromSpliterator.tryAdvance(this)) {
                    try {
                        if (!predicate.test(this.holder)) continue;
                        action.accept(this.holder);
                        boolean bl = true;
                        return bl;
                    } finally {
                        this.holder = null;
                    }
                }
                return false;
            }

            @Override
            public Spliterator<T> trySplit() {
                Spliterator fromSplit = fromSpliterator.trySplit();
                return fromSplit == null ? null : CollectSpliterators.filter(fromSplit, predicate);
            }

            @Override
            public long estimateSize() {
                return fromSpliterator.estimateSize() / 2L;
            }

            @Override
            public Comparator<? super T> getComparator() {
                return fromSpliterator.getComparator();
            }

            @Override
            public int characteristics() {
                return fromSpliterator.characteristics() & 0x115;
            }
        }
        return new Splitr();
    }

    static <F, T> Spliterator<T> flatMap(Spliterator<F> fromSpliterator, Function<? super F, Spliterator<T>> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 0x4000) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(function);
        class FlatMapSpliterator
        implements Spliterator<T> {
            @Nullable Spliterator<T> prefix;
            final Spliterator<F> from;
            int characteristics;
            long estimatedSize;
            final /* synthetic */ Function val$function;

            FlatMapSpliterator(Spliterator<T> prefix, Spliterator<F> from, int characteristics, long estimatedSize) {
                this.val$function = var6_5;
                this.prefix = prefix;
                this.from = from;
                this.characteristics = characteristics;
                this.estimatedSize = estimatedSize;
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                do {
                    if (this.prefix != null && this.prefix.tryAdvance(action)) {
                        if (this.estimatedSize != Long.MAX_VALUE) {
                            --this.estimatedSize;
                        }
                        return true;
                    }
                    this.prefix = null;
                } while (this.from.tryAdvance(fromElement -> {
                    this.prefix = (Spliterator)this.val$function.apply(fromElement);
                }));
                return false;
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                if (this.prefix != null) {
                    this.prefix.forEachRemaining(action);
                    this.prefix = null;
                }
                this.from.forEachRemaining(fromElement -> ((Spliterator)this.val$function.apply(fromElement)).forEachRemaining(action));
                this.estimatedSize = 0L;
            }

            @Override
            public Spliterator<T> trySplit() {
                Spliterator fromSplit = this.from.trySplit();
                if (fromSplit != null) {
                    int splitCharacteristics = this.characteristics & 0xFFFFFFBF;
                    long estSplitSize = this.estimateSize();
                    if (estSplitSize < Long.MAX_VALUE) {
                        this.estimatedSize -= (estSplitSize /= 2L);
                        this.characteristics = splitCharacteristics;
                    }
                    FlatMapSpliterator result = new FlatMapSpliterator(this.prefix, fromSplit, splitCharacteristics, estSplitSize, this.val$function);
                    this.prefix = null;
                    return result;
                }
                if (this.prefix != null) {
                    Spliterator result = this.prefix;
                    this.prefix = null;
                    return result;
                }
                return null;
            }

            @Override
            public long estimateSize() {
                if (this.prefix != null) {
                    this.estimatedSize = Math.max(this.estimatedSize, this.prefix.estimateSize());
                }
                return Math.max(this.estimatedSize, 0L);
            }

            @Override
            public int characteristics() {
                return this.characteristics;
            }
        }
        return new FlatMapSpliterator(null, fromSpliterator, topCharacteristics, topSize, function);
    }
}

