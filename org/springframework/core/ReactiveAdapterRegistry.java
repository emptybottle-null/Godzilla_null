/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  io.reactivex.BackpressureStrategy
 *  io.reactivex.Completable
 *  io.reactivex.Flowable
 *  io.reactivex.Maybe
 *  io.reactivex.Observable
 *  io.reactivex.Single
 *  io.reactivex.rxjava3.core.BackpressureStrategy
 *  io.reactivex.rxjava3.core.Completable
 *  io.reactivex.rxjava3.core.Flowable
 *  io.reactivex.rxjava3.core.Maybe
 *  io.reactivex.rxjava3.core.Observable
 *  io.reactivex.rxjava3.core.Single
 *  io.smallrye.mutiny.Multi
 *  io.smallrye.mutiny.Uni
 *  kotlinx.coroutines.CompletableDeferredKt
 *  kotlinx.coroutines.Deferred
 *  kotlinx.coroutines.flow.Flow
 *  kotlinx.coroutines.flow.FlowKt
 *  kotlinx.coroutines.reactive.ReactiveFlowKt
 *  kotlinx.coroutines.reactor.ReactorFlowKt
 *  reactor.blockhound.BlockHound$Builder
 *  reactor.blockhound.integration.BlockHoundIntegration
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 *  rx.Completable
 *  rx.Observable
 *  rx.RxReactiveStreams
 *  rx.Single
 */
package org.springframework.core;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Maybe;
import io.reactivex.rxjava3.core.Flowable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import kotlinx.coroutines.reactor.ReactorFlowKt;
import org.reactivestreams.Publisher;
import org.springframework.core.CoroutinesUtils;
import org.springframework.core.ReactiveAdapter;
import org.springframework.core.ReactiveTypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import reactor.blockhound.BlockHound;
import reactor.blockhound.integration.BlockHoundIntegration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Completable;
import rx.Observable;
import rx.RxReactiveStreams;
import rx.Single;

public class ReactiveAdapterRegistry {
    @Nullable
    private static volatile ReactiveAdapterRegistry sharedInstance;
    private static final boolean reactorPresent;
    private static final boolean rxjava1Present;
    private static final boolean rxjava2Present;
    private static final boolean rxjava3Present;
    private static final boolean flowPublisherPresent;
    private static final boolean kotlinCoroutinesPresent;
    private static final boolean mutinyPresent;
    private final List<ReactiveAdapter> adapters = new ArrayList<ReactiveAdapter>();

    public ReactiveAdapterRegistry() {
        if (reactorPresent) {
            new ReactorRegistrar().registerAdapters(this);
            if (flowPublisherPresent) {
                new ReactorJdkFlowAdapterRegistrar().registerAdapter(this);
            }
        }
        if (rxjava1Present) {
            new RxJava1Registrar().registerAdapters(this);
        }
        if (rxjava2Present) {
            new RxJava2Registrar().registerAdapters(this);
        }
        if (rxjava3Present) {
            new RxJava3Registrar().registerAdapters(this);
        }
        if (reactorPresent && kotlinCoroutinesPresent) {
            new CoroutinesRegistrar().registerAdapters(this);
        }
        if (mutinyPresent) {
            new MutinyRegistrar().registerAdapters(this);
        }
    }

    public boolean hasAdapters() {
        return !this.adapters.isEmpty();
    }

    public void registerReactiveType(ReactiveTypeDescriptor descriptor, Function<Object, Publisher<?>> toAdapter, Function<Publisher<?>, Object> fromAdapter) {
        if (reactorPresent) {
            this.adapters.add(new ReactorAdapter(descriptor, toAdapter, fromAdapter));
        } else {
            this.adapters.add(new ReactiveAdapter(descriptor, toAdapter, fromAdapter));
        }
    }

    @Nullable
    public ReactiveAdapter getAdapter(Class<?> reactiveType) {
        return this.getAdapter(reactiveType, null);
    }

    @Nullable
    public ReactiveAdapter getAdapter(@Nullable Class<?> reactiveType, @Nullable Object source) {
        Class<?> clazz;
        if (this.adapters.isEmpty()) {
            return null;
        }
        Object sourceToUse = source instanceof Optional ? ((Optional)source).orElse(null) : source;
        Class<?> clazz2 = clazz = sourceToUse != null ? sourceToUse.getClass() : reactiveType;
        if (clazz == null) {
            return null;
        }
        for (ReactiveAdapter adapter : this.adapters) {
            if (adapter.getReactiveType() != clazz) continue;
            return adapter;
        }
        for (ReactiveAdapter adapter : this.adapters) {
            if (!adapter.getReactiveType().isAssignableFrom(clazz)) continue;
            return adapter;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ReactiveAdapterRegistry getSharedInstance() {
        ReactiveAdapterRegistry registry = sharedInstance;
        if (registry != null) return registry;
        Class<ReactiveAdapterRegistry> clazz = ReactiveAdapterRegistry.class;
        synchronized (ReactiveAdapterRegistry.class) {
            registry = sharedInstance;
            if (registry != null) return registry;
            sharedInstance = registry = new ReactiveAdapterRegistry();
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return registry;
        }
    }

    static {
        ClassLoader classLoader = ReactiveAdapterRegistry.class.getClassLoader();
        reactorPresent = ClassUtils.isPresent("reactor.core.publisher.Flux", classLoader);
        flowPublisherPresent = ClassUtils.isPresent("java.util.concurrent.Flow.Publisher", classLoader);
        rxjava1Present = ClassUtils.isPresent("rx.Observable", classLoader) && ClassUtils.isPresent("rx.RxReactiveStreams", classLoader);
        rxjava2Present = ClassUtils.isPresent("io.reactivex.Flowable", classLoader);
        rxjava3Present = ClassUtils.isPresent("io.reactivex.rxjava3.core.Flowable", classLoader);
        kotlinCoroutinesPresent = ClassUtils.isPresent("kotlinx.coroutines.reactor.MonoKt", classLoader);
        mutinyPresent = ClassUtils.isPresent("io.smallrye.mutiny.Multi", classLoader);
    }

    public static class SpringCoreBlockHoundIntegration
    implements BlockHoundIntegration {
        public void applyTo(BlockHound.Builder builder) {
            builder.allowBlockingCallsInside("org.springframework.core.LocalVariableTableParameterNameDiscoverer", "inspectClass");
            String className = "org.springframework.util.ConcurrentReferenceHashMap$Segment";
            builder.allowBlockingCallsInside(className, "doTask");
            builder.allowBlockingCallsInside(className, "clear");
            builder.allowBlockingCallsInside(className, "restructure");
        }
    }

    private static class MutinyRegistrar {
        private MutinyRegistrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.singleOptionalValue(Uni.class, () -> Uni.createFrom().nothing()), uni -> ((Uni)uni).convert().toPublisher(), publisher -> Uni.createFrom().publisher(publisher));
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Multi.class, () -> Multi.createFrom().empty()), multi -> (Multi)multi, publisher -> Multi.createFrom().publisher(publisher));
        }
    }

    private static class CoroutinesRegistrar {
        private CoroutinesRegistrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.singleOptionalValue(Deferred.class, () -> CompletableDeferredKt.CompletableDeferred(null)), source -> CoroutinesUtils.deferredToMono((Deferred)source), source -> CoroutinesUtils.monoToDeferred(Mono.from((Publisher)source)));
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Flow.class, FlowKt::emptyFlow), source -> ReactorFlowKt.asFlux((Flow)((Flow)source)), ReactiveFlowKt::asFlow);
        }
    }

    private static class RxJava3Registrar {
        private RxJava3Registrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Flowable.class, Flowable::empty), source -> (Flowable)source, Flowable::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(io.reactivex.rxjava3.core.Observable.class, io.reactivex.rxjava3.core.Observable::empty), source -> ((io.reactivex.rxjava3.core.Observable)source).toFlowable(io.reactivex.rxjava3.core.BackpressureStrategy.BUFFER), io.reactivex.rxjava3.core.Observable::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.singleRequiredValue(io.reactivex.rxjava3.core.Single.class), source -> ((io.reactivex.rxjava3.core.Single)source).toFlowable(), io.reactivex.rxjava3.core.Single::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.singleOptionalValue(io.reactivex.rxjava3.core.Maybe.class, io.reactivex.rxjava3.core.Maybe::empty), source -> ((io.reactivex.rxjava3.core.Maybe)source).toFlowable(), io.reactivex.rxjava3.core.Maybe::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.noValue(io.reactivex.rxjava3.core.Completable.class, io.reactivex.rxjava3.core.Completable::complete), source -> ((io.reactivex.rxjava3.core.Completable)source).toFlowable(), io.reactivex.rxjava3.core.Completable::fromPublisher);
        }
    }

    private static class RxJava2Registrar {
        private RxJava2Registrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(io.reactivex.Flowable.class, io.reactivex.Flowable::empty), source -> (io.reactivex.Flowable)source, io.reactivex.Flowable::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(io.reactivex.Observable.class, io.reactivex.Observable::empty), source -> ((io.reactivex.Observable)source).toFlowable(BackpressureStrategy.BUFFER), io.reactivex.Observable::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.singleRequiredValue(io.reactivex.Single.class), source -> ((io.reactivex.Single)source).toFlowable(), io.reactivex.Single::fromPublisher);
            registry.registerReactiveType(ReactiveTypeDescriptor.singleOptionalValue(Maybe.class, Maybe::empty), source -> ((Maybe)source).toFlowable(), source -> io.reactivex.Flowable.fromPublisher((Publisher)source).toObservable().singleElement());
            registry.registerReactiveType(ReactiveTypeDescriptor.noValue(io.reactivex.Completable.class, io.reactivex.Completable::complete), source -> ((io.reactivex.Completable)source).toFlowable(), io.reactivex.Completable::fromPublisher);
        }
    }

    private static class RxJava1Registrar {
        private RxJava1Registrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Observable.class, Observable::empty), source -> RxReactiveStreams.toPublisher((Observable)((Observable)source)), RxReactiveStreams::toObservable);
            registry.registerReactiveType(ReactiveTypeDescriptor.singleRequiredValue(Single.class), source -> RxReactiveStreams.toPublisher((Single)((Single)source)), RxReactiveStreams::toSingle);
            registry.registerReactiveType(ReactiveTypeDescriptor.noValue(Completable.class, Completable::complete), source -> RxReactiveStreams.toPublisher((Completable)((Completable)source)), RxReactiveStreams::toCompletable);
        }
    }

    private static class ReactorJdkFlowAdapterRegistrar {
        private ReactorJdkFlowAdapterRegistrar() {
        }

        void registerAdapter(ReactiveAdapterRegistry registry) {
            try {
                String publisherName = "java.util.concurrent.Flow.Publisher";
                Class<?> publisherClass = ClassUtils.forName(publisherName, this.getClass().getClassLoader());
                String adapterName = "reactor.adapter.JdkFlowAdapter";
                Class<?> flowAdapterClass = ClassUtils.forName(adapterName, this.getClass().getClassLoader());
                Method toFluxMethod = flowAdapterClass.getMethod("flowPublisherToFlux", publisherClass);
                Method toFlowMethod = flowAdapterClass.getMethod("publisherToFlowPublisher", Publisher.class);
                Object emptyFlow = ReflectionUtils.invokeMethod(toFlowMethod, null, Flux.empty());
                registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(publisherClass, () -> emptyFlow), source -> (Publisher)ReflectionUtils.invokeMethod(toFluxMethod, null, source), publisher -> ReflectionUtils.invokeMethod(toFlowMethod, null, publisher));
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    private static class EmptyCompletableFuture<T>
    extends CompletableFuture<T> {
        EmptyCompletableFuture() {
            this.complete(null);
        }
    }

    private static class ReactorRegistrar {
        private ReactorRegistrar() {
        }

        void registerAdapters(ReactiveAdapterRegistry registry) {
            registry.registerReactiveType(ReactiveTypeDescriptor.singleOptionalValue(Mono.class, Mono::empty), source -> (Mono)source, Mono::from);
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Flux.class, Flux::empty), source -> (Flux)source, Flux::from);
            registry.registerReactiveType(ReactiveTypeDescriptor.multiValue(Publisher.class, Flux::empty), source -> (Publisher)source, source -> source);
            registry.registerReactiveType(ReactiveTypeDescriptor.nonDeferredAsyncValue(CompletionStage.class, EmptyCompletableFuture::new), source -> Mono.fromCompletionStage((CompletionStage)((CompletionStage)source)), source -> Mono.from((Publisher)source).toFuture());
        }
    }

    private static class ReactorAdapter
    extends ReactiveAdapter {
        ReactorAdapter(ReactiveTypeDescriptor descriptor, Function<Object, Publisher<?>> toPublisherFunction, Function<Publisher<?>, Object> fromPublisherFunction) {
            super(descriptor, toPublisherFunction, fromPublisherFunction);
        }

        @Override
        public <T> Publisher<T> toPublisher(@Nullable Object source) {
            Publisher publisher = super.toPublisher(source);
            return this.isMultiValue() ? Flux.from(publisher) : Mono.from(publisher);
        }
    }
}

