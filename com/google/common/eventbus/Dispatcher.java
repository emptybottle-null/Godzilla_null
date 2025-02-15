/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.common.eventbus.Subscriber;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

abstract class Dispatcher {
    Dispatcher() {
    }

    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    abstract void dispatch(Object var1, Iterator<Subscriber> var2);

    private static final class ImmediateDispatcher
    extends Dispatcher {
        private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        private ImmediateDispatcher() {
        }

        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }

    private static final class LegacyAsyncDispatcher
    extends Dispatcher {
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue = Queues.newConcurrentLinkedQueue();

        private LegacyAsyncDispatcher() {
        }

        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            EventWithSubscriber e;
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                this.queue.add(new EventWithSubscriber(event, subscribers.next()));
            }
            while ((e = this.queue.poll()) != null) {
                e.subscriber.dispatchEvent(e.event);
            }
        }

        private static final class EventWithSubscriber {
            private final Object event;
            private final Subscriber subscriber;

            private EventWithSubscriber(Object event, Subscriber subscriber) {
                this.event = event;
                this.subscriber = subscriber;
            }
        }
    }

    private static final class PerThreadQueuedDispatcher
    extends Dispatcher {
        private final ThreadLocal<Queue<Event>> queue = new ThreadLocal<Queue<Event>>(){

            @Override
            protected Queue<Event> initialValue() {
                return Queues.newArrayDeque();
            }
        };
        private final ThreadLocal<Boolean> dispatching = new ThreadLocal<Boolean>(){

            @Override
            protected Boolean initialValue() {
                return false;
            }
        };

        private PerThreadQueuedDispatcher() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            Preconditions.checkNotNull(subscribers);
            Queue<Event> queueForThread = this.queue.get();
            queueForThread.offer(new Event(event, subscribers));
            if (!this.dispatching.get().booleanValue()) {
                this.dispatching.set(true);
                try {
                    Event nextEvent;
                    while ((nextEvent = queueForThread.poll()) != null) {
                        while (nextEvent.subscribers.hasNext()) {
                            ((Subscriber)nextEvent.subscribers.next()).dispatchEvent(nextEvent.event);
                        }
                    }
                } finally {
                    this.dispatching.remove();
                    this.queue.remove();
                }
            }
        }

        private static final class Event {
            private final Object event;
            private final Iterator<Subscriber> subscribers;

            private Event(Object event, Iterator<Subscriber> subscribers) {
                this.event = event;
                this.subscribers = subscribers;
            }
        }
    }
}

