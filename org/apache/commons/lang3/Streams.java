/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.apache.commons.lang3.Functions;

@Deprecated
public class Streams {
    public static <O> FailableStream<O> stream(Stream<O> stream) {
        return new FailableStream<O>(stream);
    }

    public static <O> FailableStream<O> stream(Collection<O> stream) {
        return Streams.stream(stream.stream());
    }

    public static <O> Collector<O, ?, O[]> toArray(Class<O> pElementType) {
        return new ArrayCollector<O>(pElementType);
    }

    @Deprecated
    public static class ArrayCollector<O>
    implements Collector<O, List<O>, O[]> {
        private static final Set<Collector.Characteristics> characteristics = Collections.emptySet();
        private final Class<O> elementType;

        public ArrayCollector(Class<O> elementType) {
            this.elementType = elementType;
        }

        @Override
        public Supplier<List<O>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<O>, O> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<O>> combiner() {
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<List<O>, O[]> finisher() {
            return list -> {
                Object[] array = (Object[])Array.newInstance(this.elementType, list.size());
                return list.toArray(array);
            };
        }

        @Override
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }
    }

    @Deprecated
    public static class FailableStream<O> {
        private Stream<O> stream;
        private boolean terminated;

        public FailableStream(Stream<O> stream) {
            this.stream = stream;
        }

        protected void assertNotTerminated() {
            if (this.terminated) {
                throw new IllegalStateException("This stream is already terminated.");
            }
        }

        protected void makeTerminated() {
            this.assertNotTerminated();
            this.terminated = true;
        }

        public FailableStream<O> filter(Functions.FailablePredicate<O, ?> predicate) {
            this.assertNotTerminated();
            this.stream = this.stream.filter(Functions.asPredicate(predicate));
            return this;
        }

        public void forEach(Functions.FailableConsumer<O, ?> action) {
            this.makeTerminated();
            this.stream().forEach(Functions.asConsumer(action));
        }

        public <A, R> R collect(Collector<? super O, A, R> collector) {
            this.makeTerminated();
            return this.stream().collect(collector);
        }

        public <A, R> R collect(Supplier<R> pupplier, BiConsumer<R, ? super O> accumulator, BiConsumer<R, R> combiner) {
            this.makeTerminated();
            return this.stream().collect(pupplier, accumulator, combiner);
        }

        public O reduce(O identity, BinaryOperator<O> accumulator) {
            this.makeTerminated();
            return this.stream().reduce(identity, accumulator);
        }

        public <R> FailableStream<R> map(Functions.FailableFunction<O, R, ?> mapper) {
            this.assertNotTerminated();
            return new FailableStream<R>(this.stream.map(Functions.asFunction(mapper)));
        }

        public Stream<O> stream() {
            return this.stream;
        }

        public boolean allMatch(Functions.FailablePredicate<O, ?> predicate) {
            this.assertNotTerminated();
            return this.stream().allMatch(Functions.asPredicate(predicate));
        }

        public boolean anyMatch(Functions.FailablePredicate<O, ?> predicate) {
            this.assertNotTerminated();
            return this.stream().anyMatch(Functions.asPredicate(predicate));
        }
    }
}

