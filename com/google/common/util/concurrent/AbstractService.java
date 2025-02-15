/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public abstract class AbstractService
implements Service {
    private static final ListenerCallQueue.Event<Service.Listener> STARTING_EVENT = new ListenerCallQueue.Event<Service.Listener>(){

        @Override
        public void call(Service.Listener listener) {
            listener.starting();
        }

        public String toString() {
            return "starting()";
        }
    };
    private static final ListenerCallQueue.Event<Service.Listener> RUNNING_EVENT = new ListenerCallQueue.Event<Service.Listener>(){

        @Override
        public void call(Service.Listener listener) {
            listener.running();
        }

        public String toString() {
            return "running()";
        }
    };
    private static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_STARTING_EVENT = AbstractService.stoppingEvent(Service.State.STARTING);
    private static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_RUNNING_EVENT = AbstractService.stoppingEvent(Service.State.RUNNING);
    private static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_NEW_EVENT = AbstractService.terminatedEvent(Service.State.NEW);
    private static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STARTING_EVENT = AbstractService.terminatedEvent(Service.State.STARTING);
    private static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_RUNNING_EVENT = AbstractService.terminatedEvent(Service.State.RUNNING);
    private static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STOPPING_EVENT = AbstractService.terminatedEvent(Service.State.STOPPING);
    private final Monitor monitor = new Monitor();
    private final Monitor.Guard isStartable = new IsStartableGuard();
    private final Monitor.Guard isStoppable = new IsStoppableGuard();
    private final Monitor.Guard hasReachedRunning = new HasReachedRunningGuard();
    private final Monitor.Guard isStopped = new IsStoppedGuard();
    private final ListenerCallQueue<Service.Listener> listeners = new ListenerCallQueue();
    private volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    private static ListenerCallQueue.Event<Service.Listener> terminatedEvent(final Service.State from) {
        return new ListenerCallQueue.Event<Service.Listener>(){

            @Override
            public void call(Service.Listener listener) {
                listener.terminated(from);
            }

            public String toString() {
                return "terminated({from = " + (Object)((Object)from) + "})";
            }
        };
    }

    private static ListenerCallQueue.Event<Service.Listener> stoppingEvent(final Service.State from) {
        return new ListenerCallQueue.Event<Service.Listener>(){

            @Override
            public void call(Service.Listener listener) {
                listener.stopping(from);
            }

            public String toString() {
                return "stopping({from = " + (Object)((Object)from) + "})";
            }
        };
    }

    protected AbstractService() {
    }

    @ForOverride
    protected abstract void doStart();

    @ForOverride
    protected abstract void doStop();

    @ForOverride
    protected void doCancelStart() {
    }

    @Override
    @CanIgnoreReturnValue
    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                this.enqueueStartingEvent();
                this.doStart();
            } catch (Throwable startupFailure) {
                this.notifyFailed(startupFailure);
            } finally {
                this.monitor.leave();
                this.dispatchListenerEvents();
            }
        } else {
            throw new IllegalStateException("Service " + this + " has already been started");
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        if (!this.monitor.enterIf(this.isStoppable)) return this;
        try {
            Service.State previous = this.state();
            switch (previous) {
                case NEW: {
                    this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                    this.enqueueTerminatedEvent(Service.State.NEW);
                    return this;
                }
                case STARTING: {
                    this.snapshot = new StateSnapshot(Service.State.STARTING, true, null);
                    this.enqueueStoppingEvent(Service.State.STARTING);
                    this.doCancelStart();
                    return this;
                }
                case RUNNING: {
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    this.enqueueStoppingEvent(Service.State.RUNNING);
                    this.doStop();
                    return this;
                }
                case STOPPING: 
                case TERMINATED: 
                case FAILED: {
                    throw new AssertionError((Object)("isStoppable is incorrectly implemented, saw: " + (Object)((Object)previous)));
                }
            }
            return this;
        } catch (Throwable shutdownFailure) {
            this.notifyFailed(shutdownFailure);
            return this;
        } finally {
            this.monitor.leave();
            this.dispatchListenerEvents();
        }
    }

    @Override
    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            this.checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, timeout, unit)) {
            try {
                this.checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach the RUNNING state.");
        }
    }

    @Override
    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            this.checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, timeout, unit)) {
            try {
                this.checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach a terminal state. Current state: " + (Object)((Object)this.state()));
        }
    }

    @GuardedBy(value="monitor")
    private void checkCurrentState(Service.State expected) {
        Service.State actual = this.state();
        if (actual != expected) {
            if (actual == Service.State.FAILED) {
                throw new IllegalStateException("Expected the service " + this + " to be " + (Object)((Object)expected) + ", but the service has FAILED", this.failureCause());
            }
            throw new IllegalStateException("Expected the service " + this + " to be " + (Object)((Object)expected) + ", but was " + (Object)((Object)actual));
        }
    }

    protected final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state != Service.State.STARTING) {
                IllegalStateException failure = new IllegalStateException("Cannot notifyStarted() when the service is " + (Object)((Object)this.snapshot.state));
                this.notifyFailed(failure);
                throw failure;
            }
            if (this.snapshot.shutdownWhenStartupFinishes) {
                this.snapshot = new StateSnapshot(Service.State.STOPPING);
                this.doStop();
            } else {
                this.snapshot = new StateSnapshot(Service.State.RUNNING);
                this.enqueueRunningEvent();
            }
        } finally {
            this.monitor.leave();
            this.dispatchListenerEvents();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final void notifyStopped() {
        this.monitor.enter();
        try {
            Service.State previous = this.state();
            switch (previous) {
                case NEW: 
                case TERMINATED: 
                case FAILED: {
                    throw new IllegalStateException("Cannot notifyStopped() when the service is " + (Object)((Object)previous));
                }
                case STARTING: 
                case RUNNING: 
                case STOPPING: {
                    this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                    this.enqueueTerminatedEvent(previous);
                    return;
                }
            }
            return;
        } finally {
            this.monitor.leave();
            this.dispatchListenerEvents();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final void notifyFailed(Throwable cause) {
        Preconditions.checkNotNull(cause);
        this.monitor.enter();
        try {
            Service.State previous = this.state();
            switch (previous) {
                case NEW: 
                case TERMINATED: {
                    throw new IllegalStateException("Failed while in state:" + (Object)((Object)previous), cause);
                }
                case STARTING: 
                case RUNNING: 
                case STOPPING: {
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, cause);
                    this.enqueueFailedEvent(previous, cause);
                    return;
                }
            }
            return;
        } finally {
            this.monitor.leave();
            this.dispatchListenerEvents();
        }
    }

    @Override
    public final boolean isRunning() {
        return this.state() == Service.State.RUNNING;
    }

    @Override
    public final Service.State state() {
        return this.snapshot.externalState();
    }

    @Override
    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    @Override
    public final void addListener(Service.Listener listener, Executor executor) {
        this.listeners.addListener(listener, executor);
    }

    public String toString() {
        return this.getClass().getSimpleName() + " [" + (Object)((Object)this.state()) + "]";
    }

    private void dispatchListenerEvents() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            this.listeners.dispatch();
        }
    }

    private void enqueueStartingEvent() {
        this.listeners.enqueue(STARTING_EVENT);
    }

    private void enqueueRunningEvent() {
        this.listeners.enqueue(RUNNING_EVENT);
    }

    private void enqueueStoppingEvent(Service.State from) {
        if (from == Service.State.STARTING) {
            this.listeners.enqueue(STOPPING_FROM_STARTING_EVENT);
        } else if (from == Service.State.RUNNING) {
            this.listeners.enqueue(STOPPING_FROM_RUNNING_EVENT);
        } else {
            throw new AssertionError();
        }
    }

    private void enqueueTerminatedEvent(Service.State from) {
        switch (from) {
            case NEW: {
                this.listeners.enqueue(TERMINATED_FROM_NEW_EVENT);
                break;
            }
            case STARTING: {
                this.listeners.enqueue(TERMINATED_FROM_STARTING_EVENT);
                break;
            }
            case RUNNING: {
                this.listeners.enqueue(TERMINATED_FROM_RUNNING_EVENT);
                break;
            }
            case STOPPING: {
                this.listeners.enqueue(TERMINATED_FROM_STOPPING_EVENT);
                break;
            }
            case TERMINATED: 
            case FAILED: {
                throw new AssertionError();
            }
        }
    }

    private void enqueueFailedEvent(final Service.State from, final Throwable cause) {
        this.listeners.enqueue(new ListenerCallQueue.Event<Service.Listener>(){

            @Override
            public void call(Service.Listener listener) {
                listener.failed(from, cause);
            }

            public String toString() {
                return "failed({from = " + (Object)((Object)from) + ", cause = " + cause + "})";
            }
        });
    }

    private static final class StateSnapshot {
        final Service.State state;
        final boolean shutdownWhenStartupFinishes;
        final @Nullable Throwable failure;

        StateSnapshot(Service.State internalState) {
            this(internalState, false, null);
        }

        StateSnapshot(Service.State internalState, boolean shutdownWhenStartupFinishes, @Nullable Throwable failure) {
            Preconditions.checkArgument(!shutdownWhenStartupFinishes || internalState == Service.State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", (Object)internalState);
            Preconditions.checkArgument(!(failure != null ^ internalState == Service.State.FAILED), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", (Object)internalState, (Object)failure);
            this.state = internalState;
            this.shutdownWhenStartupFinishes = shutdownWhenStartupFinishes;
            this.failure = failure;
        }

        Service.State externalState() {
            if (this.shutdownWhenStartupFinishes && this.state == Service.State.STARTING) {
                return Service.State.STOPPING;
            }
            return this.state;
        }

        Throwable failureCause() {
            Preconditions.checkState(this.state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", (Object)this.state);
            return this.failure;
        }
    }

    private final class IsStoppedGuard
    extends Monitor.Guard {
        IsStoppedGuard() {
            super(AbstractService.this.monitor);
        }

        @Override
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    }

    private final class HasReachedRunningGuard
    extends Monitor.Guard {
        HasReachedRunningGuard() {
            super(AbstractService.this.monitor);
        }

        @Override
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    }

    private final class IsStoppableGuard
    extends Monitor.Guard {
        IsStoppableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    }

    private final class IsStartableGuard
    extends Monitor.Guard {
        IsStartableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override
        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    }
}

