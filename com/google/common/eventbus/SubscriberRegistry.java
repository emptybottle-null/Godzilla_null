/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.eventbus.Subscriber;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.qual.Nullable;

final class SubscriberRegistry {
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers = Maps.newConcurrentMap();
    @Weak
    private final EventBus bus;
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableList<Method>>(){

        @Override
        public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
            return SubscriberRegistry.getAnnotatedMethodsNotCached(concreteClass);
        }
    });
    private static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableSet<Class<?>>>(){

        @Override
        public ImmutableSet<Class<?>> load(Class<?> concreteClass) {
            return ImmutableSet.copyOf(TypeToken.of(concreteClass).getTypes().rawTypes());
        }
    });

    SubscriberRegistry(EventBus bus) {
        this.bus = Preconditions.checkNotNull(bus);
    }

    void register(Object listener) {
        Multimap<Class<?>, Subscriber> listenerMethods = this.findAllSubscribers(listener);
        for (Map.Entry<Class<?>, Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<Subscriber> eventMethodsInListener = entry.getValue();
            CopyOnWriteArraySet<Subscriber> eventSubscribers = (CopyOnWriteArraySet<Subscriber>)this.subscribers.get(eventType);
            if (eventSubscribers == null) {
                CopyOnWriteArraySet newSet = new CopyOnWriteArraySet();
                eventSubscribers = MoreObjects.firstNonNull(this.subscribers.putIfAbsent(eventType, newSet), newSet);
            }
            eventSubscribers.addAll(eventMethodsInListener);
        }
    }

    void unregister(Object listener) {
        Multimap<Class<?>, Subscriber> listenerMethods = this.findAllSubscribers(listener);
        for (Map.Entry<Class<?>, Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<Subscriber> listenerMethodsForType = entry.getValue();
            CopyOnWriteArraySet currentSubscribers = (CopyOnWriteArraySet)this.subscribers.get(eventType);
            if (currentSubscribers != null && currentSubscribers.removeAll(listenerMethodsForType)) continue;
            throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + listener + " registered?");
        }
    }

    @VisibleForTesting
    Set<Subscriber> getSubscribersForTesting(Class<?> eventType) {
        return MoreObjects.firstNonNull(this.subscribers.get(eventType), ImmutableSet.of());
    }

    Iterator<Subscriber> getSubscribers(Object event) {
        ImmutableSet<Class<?>> eventTypes = SubscriberRegistry.flattenHierarchy(event.getClass());
        ArrayList subscriberIterators = Lists.newArrayListWithCapacity(eventTypes.size());
        for (Class clazz : eventTypes) {
            CopyOnWriteArraySet eventSubscribers = (CopyOnWriteArraySet)this.subscribers.get(clazz);
            if (eventSubscribers == null) continue;
            subscriberIterators.add(eventSubscribers.iterator());
        }
        return Iterators.concat(subscriberIterators.iterator());
    }

    private Multimap<Class<?>, Subscriber> findAllSubscribers(Object listener) {
        HashMultimap<Class<?>, Subscriber> methodsInListener = HashMultimap.create();
        Class<?> clazz = listener.getClass();
        for (Method method : SubscriberRegistry.getAnnotatedMethods(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            methodsInListener.put(eventType, Subscriber.create(this.bus, listener, method));
        }
        return methodsInListener;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
        return subscriberMethodsCache.getUnchecked(clazz);
    }

    private static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> clazz) {
        Set supertypes = TypeToken.of(clazz).getTypes().rawTypes();
        HashMap<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (Class supertype : supertypes) {
            for (Method method : supertype.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Subscribe.class) || method.isSynthetic()) continue;
                Class<?>[] parameterTypes = method.getParameterTypes();
                Preconditions.checkArgument(parameterTypes.length == 1, "Method %s has @Subscribe annotation but has %s parameters.Subscriber methods must have exactly 1 parameter.", (Object)method, parameterTypes.length);
                MethodIdentifier ident = new MethodIdentifier(method);
                if (identifiers.containsKey(ident)) continue;
                identifiers.put(ident, method);
            }
        }
        return ImmutableList.copyOf(identifiers.values());
    }

    @VisibleForTesting
    static ImmutableSet<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        try {
            return flattenHierarchyCache.getUnchecked(concreteClass);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    private static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public int hashCode() {
            return Objects.hashCode(this.name, this.parameterTypes);
        }

        public boolean equals(@Nullable Object o) {
            if (o instanceof MethodIdentifier) {
                MethodIdentifier ident = (MethodIdentifier)o;
                return this.name.equals(ident.name) && this.parameterTypes.equals(ident.parameterTypes);
            }
            return false;
        }
    }
}

