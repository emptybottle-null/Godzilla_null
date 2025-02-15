/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.ImmutableList;
import com.google.common.math.LongMath;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Streams {
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return iterable instanceof Collection ? ((Collection)iterable).stream() : StreamSupport.stream(iterable.spliterator(), false);
    }

    @Deprecated
    @Beta
    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection.stream();
    }

    @Beta
    public static <T> Stream<T> stream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    @Beta
    public static <T> Stream<T> stream(com.google.common.base.Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.of(new Object[0]);
    }

    @Beta
    public static <T> Stream<T> stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.of(new Object[0]);
    }

    @Beta
    public static IntStream stream(OptionalInt optional) {
        return optional.isPresent() ? IntStream.of(optional.getAsInt()) : IntStream.empty();
    }

    @Beta
    public static LongStream stream(OptionalLong optional) {
        return optional.isPresent() ? LongStream.of(optional.getAsLong()) : LongStream.empty();
    }

    @Beta
    public static DoubleStream stream(OptionalDouble optional) {
        return optional.isPresent() ? DoubleStream.of(optional.getAsDouble()) : DoubleStream.empty();
    }

    @SafeVarargs
    public static <T> Stream<T> concat(Stream<? extends T> ... streams) {
        boolean isParallel = false;
        int characteristics = 336;
        long estimatedSize = 0L;
        ImmutableList.Builder splitrsBuilder = new ImmutableList.Builder(streams.length);
        for (Stream<T> stream : streams) {
            isParallel |= stream.isParallel();
            Spliterator splitr2 = stream.spliterator();
            splitrsBuilder.add(splitr2);
            characteristics &= splitr2.characteristics();
            estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr2.estimateSize());
        }
        return (Stream)StreamSupport.stream(CollectSpliterators.flatMap(((ImmutableList)splitrsBuilder.build()).spliterator(), splitr -> splitr, characteristics, estimatedSize), isParallel).onClose(() -> {
            for (Stream stream : streams) {
                stream.close();
            }
        });
    }

    public static IntStream concat(IntStream ... streams) {
        return Stream.of(streams).flatMapToInt(stream -> stream);
    }

    public static LongStream concat(LongStream ... streams) {
        return Stream.of(streams).flatMapToLong(stream -> stream);
    }

    public static DoubleStream concat(DoubleStream ... streams) {
        return Stream.of(streams).flatMapToDouble(stream -> stream);
    }

    @Beta
    public static <A, B, R> Stream<R> zip(Stream<A> streamA, Stream<B> streamB, final BiFunction<? super A, ? super B, R> function) {
        Preconditions.checkNotNull(streamA);
        Preconditions.checkNotNull(streamB);
        Preconditions.checkNotNull(function);
        boolean isParallel = streamA.isParallel() || streamB.isParallel();
        Spliterator splitrA = streamA.spliterator();
        Spliterator splitrB = streamB.spliterator();
        int characteristics = splitrA.characteristics() & splitrB.characteristics() & 0x50;
        final Iterator itrA = Spliterators.iterator(splitrA);
        final Iterator itrB = Spliterators.iterator(splitrB);
        return (Stream)((Stream)StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Math.min(splitrA.estimateSize(), splitrB.estimateSize()), characteristics){

            @Override
            public boolean tryAdvance(Consumer<? super R> action) {
                if (itrA.hasNext() && itrB.hasNext()) {
                    action.accept(function.apply(itrA.next(), itrB.next()));
                    return true;
                }
                return false;
            }
        }, isParallel).onClose(streamA::close)).onClose(streamB::close);
    }

    @Beta
    public static <A, B> void forEachPair(Stream<A> streamA, Stream<B> streamB, BiConsumer<? super A, ? super B> consumer) {
        Preconditions.checkNotNull(consumer);
        if (streamA.isParallel() || streamB.isParallel()) {
            Streams.zip(streamA, streamB, TemporaryPair::new).forEach(pair -> consumer.accept(pair.a, pair.b));
        } else {
            Iterator iterA = streamA.iterator();
            Iterator iterB = streamB.iterator();
            while (iterA.hasNext() && iterB.hasNext()) {
                consumer.accept(iterA.next(), iterB.next());
            }
        }
    }

    @Beta
    public static <T, R> Stream<R> mapWithIndex(Stream<T> stream, final FunctionWithIndex<? super T, ? extends R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        Spliterator fromSpliterator = stream.spliterator();
        if (!fromSpliterator.hasCharacteristics(16384)) {
            final Iterator fromIterator = Spliterators.iterator(fromSpliterator);
            return (Stream)StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 0x50){
                long index;
                {
                    super(x0, x1);
                    this.index = 0L;
                }

                @Override
                public boolean tryAdvance(Consumer<? super R> action) {
                    if (fromIterator.hasNext()) {
                        action.accept(function.apply(fromIterator.next(), this.index++));
                        return true;
                    }
                    return false;
                }
            }, isParallel).onClose(stream::close);
        }
        class Splitr
        extends MapWithIndexSpliterator<Spliterator<T>, R, Splitr>
        implements Consumer<T> {
            @Nullable T holder;
            final /* synthetic */ FunctionWithIndex val$function;

            Splitr(Spliterator<T> splitr, long index) {
                this.val$function = var4_3;
                super(splitr, index);
            }

            @Override
            public void accept(@Nullable T t) {
                this.holder = t;
            }

            @Override
            public boolean tryAdvance(Consumer<? super R> action) {
                if (this.fromSpliterator.tryAdvance(this)) {
                    try {
                        action.accept(this.val$function.apply(this.holder, this.index++));
                        boolean bl = true;
                        return bl;
                    } finally {
                        this.holder = null;
                    }
                }
                return false;
            }

            @Override
            Splitr createSplit(Spliterator<T> from, long i) {
                return new Splitr(from, i, this.val$function);
            }
        }
        return (Stream)StreamSupport.stream(new Splitr(fromSpliterator, 0L, function), isParallel).onClose(stream::close);
    }

    @Beta
    public static <R> Stream<R> mapWithIndex(IntStream stream, final IntFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        Spliterator.OfInt fromSpliterator = stream.spliterator();
        if (!fromSpliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfInt fromIterator = Spliterators.iterator(fromSpliterator);
            return (Stream)StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 0x50){
                long index;
                {
                    super(x0, x1);
                    this.index = 0L;
                }

                @Override
                public boolean tryAdvance(Consumer<? super R> action) {
                    if (fromIterator.hasNext()) {
                        action.accept(function.apply(fromIterator.nextInt(), this.index++));
                        return true;
                    }
                    return false;
                }
            }, isParallel).onClose(stream::close);
        }
        class Splitr
        extends MapWithIndexSpliterator<Spliterator.OfInt, R, Splitr>
        implements IntConsumer,
        Spliterator<R> {
            int holder;
            final /* synthetic */ IntFunctionWithIndex val$function;

            Splitr(Spliterator.OfInt splitr, long index) {
                this.val$function = var4_3;
                super(splitr, index);
            }

            @Override
            public void accept(int t) {
                this.holder = t;
            }

            @Override
            public boolean tryAdvance(Consumer<? super R> action) {
                if (((Spliterator.OfInt)this.fromSpliterator).tryAdvance(this)) {
                    action.accept(this.val$function.apply(this.holder, this.index++));
                    return true;
                }
                return false;
            }

            @Override
            Splitr createSplit(Spliterator.OfInt from, long i) {
                return new Splitr(from, i, this.val$function);
            }
        }
        return (Stream)StreamSupport.stream(new Splitr(fromSpliterator, 0L, function), isParallel).onClose(stream::close);
    }

    @Beta
    public static <R> Stream<R> mapWithIndex(LongStream stream, final LongFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        Spliterator.OfLong fromSpliterator = stream.spliterator();
        if (!fromSpliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfLong fromIterator = Spliterators.iterator(fromSpliterator);
            return (Stream)StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 0x50){
                long index;
                {
                    super(x0, x1);
                    this.index = 0L;
                }

                @Override
                public boolean tryAdvance(Consumer<? super R> action) {
                    if (fromIterator.hasNext()) {
                        action.accept(function.apply(fromIterator.nextLong(), this.index++));
                        return true;
                    }
                    return false;
                }
            }, isParallel).onClose(stream::close);
        }
        class Splitr
        extends MapWithIndexSpliterator<Spliterator.OfLong, R, Splitr>
        implements LongConsumer,
        Spliterator<R> {
            long holder;
            final /* synthetic */ LongFunctionWithIndex val$function;

            Splitr(Spliterator.OfLong splitr, long index) {
                this.val$function = var4_3;
                super(splitr, index);
            }

            @Override
            public void accept(long t) {
                this.holder = t;
            }

            @Override
            public boolean tryAdvance(Consumer<? super R> action) {
                if (((Spliterator.OfLong)this.fromSpliterator).tryAdvance(this)) {
                    action.accept(this.val$function.apply(this.holder, this.index++));
                    return true;
                }
                return false;
            }

            @Override
            Splitr createSplit(Spliterator.OfLong from, long i) {
                return new Splitr(from, i, this.val$function);
            }
        }
        return (Stream)StreamSupport.stream(new Splitr(fromSpliterator, 0L, function), isParallel).onClose(stream::close);
    }

    @Beta
    public static <R> Stream<R> mapWithIndex(DoubleStream stream, final DoubleFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        Spliterator.OfDouble fromSpliterator = stream.spliterator();
        if (!fromSpliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfDouble fromIterator = Spliterators.iterator(fromSpliterator);
            return (Stream)StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 0x50){
                long index;
                {
                    super(x0, x1);
                    this.index = 0L;
                }

                @Override
                public boolean tryAdvance(Consumer<? super R> action) {
                    if (fromIterator.hasNext()) {
                        action.accept(function.apply(fromIterator.nextDouble(), this.index++));
                        return true;
                    }
                    return false;
                }
            }, isParallel).onClose(stream::close);
        }
        class Splitr
        extends MapWithIndexSpliterator<Spliterator.OfDouble, R, Splitr>
        implements DoubleConsumer,
        Spliterator<R> {
            double holder;
            final /* synthetic */ DoubleFunctionWithIndex val$function;

            Splitr(Spliterator.OfDouble splitr, long index) {
                this.val$function = var4_3;
                super(splitr, index);
            }

            @Override
            public void accept(double t) {
                this.holder = t;
            }

            @Override
            public boolean tryAdvance(Consumer<? super R> action) {
                if (((Spliterator.OfDouble)this.fromSpliterator).tryAdvance(this)) {
                    action.accept(this.val$function.apply(this.holder, this.index++));
                    return true;
                }
                return false;
            }

            @Override
            Splitr createSplit(Spliterator.OfDouble from, long i) {
                return new Splitr(from, i, this.val$function);
            }
        }
        return (Stream)StreamSupport.stream(new Splitr(fromSpliterator, 0L, function), isParallel).onClose(stream::close);
    }

    @Beta
    public static <T> Optional<T> findLast(Stream<T> stream) {
        class OptionalState {
            boolean set = false;
            T value = null;

            OptionalState() {
            }

            void set(@Nullable T value) {
                this.set = true;
                this.value = value;
            }

            T get() {
                Preconditions.checkState(this.set);
                return this.value;
            }
        }
        OptionalState state = new OptionalState();
        ArrayDeque splits = new ArrayDeque();
        splits.addLast(stream.spliterator());
        while (!splits.isEmpty()) {
            Spliterator<Object> prefix;
            Spliterator<Object> spliterator;
            block7: {
                block6: {
                    spliterator = (Spliterator<Object>)splits.removeLast();
                    if (spliterator.getExactSizeIfKnown() == 0L) continue;
                    if (spliterator.hasCharacteristics(16384)) {
                        while ((prefix = spliterator.trySplit()) != null && prefix.getExactSizeIfKnown() != 0L) {
                            if (spliterator.getExactSizeIfKnown() != 0L) continue;
                            spliterator = prefix;
                            break;
                        }
                        spliterator.forEachRemaining(state::set);
                        return Optional.of(state.get());
                    }
                    prefix = spliterator.trySplit();
                    if (prefix == null) break block6;
                    if (prefix.getExactSizeIfKnown() != 0L) break block7;
                }
                spliterator.forEachRemaining(state::set);
                if (!state.set) continue;
                return Optional.of(state.get());
            }
            splits.addLast(prefix);
            splits.addLast(spliterator);
        }
        return Optional.empty();
    }

    @Beta
    public static OptionalInt findLast(IntStream stream) {
        Optional<Integer> boxedLast = Streams.findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalInt.of(boxedLast.get()) : OptionalInt.empty();
    }

    @Beta
    public static OptionalLong findLast(LongStream stream) {
        Optional<Long> boxedLast = Streams.findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalLong.of(boxedLast.get()) : OptionalLong.empty();
    }

    @Beta
    public static OptionalDouble findLast(DoubleStream stream) {
        Optional<Double> boxedLast = Streams.findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalDouble.of(boxedLast.get()) : OptionalDouble.empty();
    }

    private Streams() {
    }

    @Beta
    public static interface DoubleFunctionWithIndex<R> {
        public R apply(double var1, long var3);
    }

    @Beta
    public static interface LongFunctionWithIndex<R> {
        public R apply(long var1, long var3);
    }

    @Beta
    public static interface IntFunctionWithIndex<R> {
        public R apply(int var1, long var2);
    }

    private static abstract class MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends MapWithIndexSpliterator<F, R, S>>
    implements Spliterator<R> {
        final F fromSpliterator;
        long index;

        MapWithIndexSpliterator(F fromSpliterator, long index) {
            this.fromSpliterator = fromSpliterator;
            this.index = index;
        }

        abstract S createSplit(F var1, long var2);

        public S trySplit() {
            Spliterator split = this.fromSpliterator.trySplit();
            if (split == null) {
                return null;
            }
            S result = this.createSplit(split, this.index);
            this.index += split.getExactSizeIfKnown();
            return result;
        }

        @Override
        public long estimateSize() {
            return this.fromSpliterator.estimateSize();
        }

        @Override
        public int characteristics() {
            return this.fromSpliterator.characteristics() & 0x4050;
        }
    }

    @Beta
    public static interface FunctionWithIndex<T, R> {
        public R apply(T var1, long var2);
    }

    private static class TemporaryPair<A, B> {
        final A a;
        final B b;

        TemporaryPair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}

