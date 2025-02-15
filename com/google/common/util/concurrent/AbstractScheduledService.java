/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.ForwardingFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public abstract class AbstractScheduledService
implements Service {
    private static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    private final AbstractService delegate = new ServiceDelegate();

    protected AbstractScheduledService() {
    }

    protected abstract void runOneIteration() throws Exception;

    protected void startUp() throws Exception {
    }

    protected void shutDown() throws Exception {
    }

    protected abstract Scheduler scheduler();

    protected ScheduledExecutorService executor() {
        class ThreadFactoryImpl
        implements ThreadFactory {
            ThreadFactoryImpl() {
            }

            @Override
            public Thread newThread(Runnable runnable) {
                return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
            }
        }
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl());
        this.addListener(new Service.Listener(){

            @Override
            public void terminated(Service.State from) {
                executor.shutdown();
            }

            @Override
            public void failed(Service.State from, Throwable failure) {
                executor.shutdown();
            }
        }, MoreExecutors.directExecutor());
        return executor;
    }

    protected String serviceName() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        return this.serviceName() + " [" + (Object)((Object)this.state()) + "]";
    }

    @Override
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    @Override
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    @Beta
    public static abstract class CustomScheduler
    extends Scheduler {
        @Override
        final Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
            ReschedulableCallable task = new ReschedulableCallable(service, executor, runnable);
            task.reschedule();
            return task;
        }

        protected abstract Schedule getNextSchedule() throws Exception;

        @Beta
        protected static final class Schedule {
            private final long delay;
            private final TimeUnit unit;

            public Schedule(long delay, TimeUnit unit) {
                this.delay = delay;
                this.unit = Preconditions.checkNotNull(unit);
            }
        }

        private class ReschedulableCallable
        extends ForwardingFuture<Void>
        implements Callable<Void> {
            private final Runnable wrappedRunnable;
            private final ScheduledExecutorService executor;
            private final AbstractService service;
            private final ReentrantLock lock = new ReentrantLock();
            @GuardedBy(value="lock")
            private @Nullable Future<Void> currentFuture;

            ReschedulableCallable(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = executor;
                this.service = service;
            }

            @Override
            public Void call() throws Exception {
                this.wrappedRunnable.run();
                this.reschedule();
                return null;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void reschedule() {
                Schedule schedule;
                try {
                    schedule = CustomScheduler.this.getNextSchedule();
                } catch (Throwable t) {
                    this.service.notifyFailed(t);
                    return;
                }
                Throwable scheduleFailure = null;
                this.lock.lock();
                try {
                    if (this.currentFuture == null || !this.currentFuture.isCancelled()) {
                        this.currentFuture = this.executor.schedule(this, schedule.delay, schedule.unit);
                    }
                } catch (Throwable e) {
                    scheduleFailure = e;
                } finally {
                    this.lock.unlock();
                }
                if (scheduleFailure != null) {
                    this.service.notifyFailed(scheduleFailure);
                }
            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                this.lock.lock();
                try {
                    boolean bl = this.currentFuture.cancel(mayInterruptIfRunning);
                    return bl;
                } finally {
                    this.lock.unlock();
                }
            }

            @Override
            public boolean isCancelled() {
                this.lock.lock();
                try {
                    boolean bl = this.currentFuture.isCancelled();
                    return bl;
                } finally {
                    this.lock.unlock();
                }
            }

            @Override
            protected Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel and isCancelled is supported by this future");
            }
        }
    }

    private final class ServiceDelegate
    extends AbstractService {
        private volatile @MonotonicNonNull Future<?> runningTask;
        private volatile @MonotonicNonNull ScheduledExecutorService executorService;
        private final ReentrantLock lock = new ReentrantLock();
        private final Runnable task = new Task();

        private ServiceDelegate() {
        }

        @Override
        protected final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), new Supplier<String>(){

                @Override
                public String get() {
                    return AbstractScheduledService.this.serviceName() + " " + (Object)((Object)ServiceDelegate.this.state());
                }
            });
            this.executorService.execute(new Runnable(){

                @Override
                public void run() {
                    ServiceDelegate.this.lock.lock();
                    try {
                        AbstractScheduledService.this.startUp();
                        ServiceDelegate.this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, ServiceDelegate.this.executorService, ServiceDelegate.this.task);
                        ServiceDelegate.this.notifyStarted();
                    } catch (Throwable t) {
                        ServiceDelegate.this.notifyFailed(t);
                        if (ServiceDelegate.this.runningTask != null) {
                            ServiceDelegate.this.runningTask.cancel(false);
                        }
                    } finally {
                        ServiceDelegate.this.lock.unlock();
                    }
                }
            });
        }

        @Override
        protected final void doStop() {
            this.runningTask.cancel(false);
            this.executorService.execute(new Runnable(){

                @Override
                public void run() {
                    try {
                        ServiceDelegate.this.lock.lock();
                        try {
                            if (ServiceDelegate.this.state() != Service.State.STOPPING) {
                                return;
                            }
                            AbstractScheduledService.this.shutDown();
                        } finally {
                            ServiceDelegate.this.lock.unlock();
                        }
                        ServiceDelegate.this.notifyStopped();
                    } catch (Throwable t) {
                        ServiceDelegate.this.notifyFailed(t);
                    }
                }
            });
        }

        @Override
        public String toString() {
            return AbstractScheduledService.this.toString();
        }

        class Task
        implements Runnable {
            Task() {
            }

            @Override
            public void run() {
                ServiceDelegate.this.lock.lock();
                try {
                    if (ServiceDelegate.this.runningTask.isCancelled()) {
                        return;
                    }
                    AbstractScheduledService.this.runOneIteration();
                } catch (Throwable t) {
                    try {
                        AbstractScheduledService.this.shutDown();
                    } catch (Exception ignored) {
                        logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", ignored);
                    }
                    ServiceDelegate.this.notifyFailed(t);
                    ServiceDelegate.this.runningTask.cancel(false);
                } finally {
                    ServiceDelegate.this.lock.unlock();
                }
            }
        }
    }

    public static abstract class Scheduler {
        public static Scheduler newFixedDelaySchedule(final long initialDelay, final long delay, final TimeUnit unit) {
            Preconditions.checkNotNull(unit);
            Preconditions.checkArgument(delay > 0L, "delay must be > 0, found %s", delay);
            return new Scheduler(){

                @Override
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleWithFixedDelay(task, initialDelay, delay, unit);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(final long initialDelay, final long period, final TimeUnit unit) {
            Preconditions.checkNotNull(unit);
            Preconditions.checkArgument(period > 0L, "period must be > 0, found %s", period);
            return new Scheduler(){

                @Override
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleAtFixedRate(task, initialDelay, period, unit);
                }
            };
        }

        abstract Future<?> schedule(AbstractService var1, ScheduledExecutorService var2, Runnable var3);

        private Scheduler() {
        }
    }
}

