/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.util.concurrent.Callables;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.SequentialExecutor;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import com.google.common.util.concurrent.WrappingExecutorService;
import com.google.common.util.concurrent.WrappingScheduledExecutorService;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@GwtCompatible(emulated=true)
public final class MoreExecutors {
    private MoreExecutors() {
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(executor, terminationTimeout, timeUnit);
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
        return new Application().getExitingExecutorService(executor);
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
        return new Application().getExitingScheduledExecutorService(executor);
    }

    @Beta
    @GwtIncompatible
    public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(service, terminationTimeout, timeUnit);
    }

    @GwtIncompatible
    private static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
        executor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(executor.getThreadFactory()).build());
    }

    @GwtIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    @Beta
    @GwtIncompatible
    public static Executor newSequentialExecutor(Executor delegate) {
        return new SequentialExecutor(delegate);
    }

    @GwtIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
        return delegate instanceof ListeningExecutorService ? (ListeningExecutorService)delegate : (delegate instanceof ScheduledExecutorService ? new ScheduledListeningDecorator((ScheduledExecutorService)delegate) : new ListeningDecorator(delegate));
    }

    @GwtIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
        return delegate instanceof ListeningScheduledExecutorService ? (ListeningScheduledExecutorService)delegate : new ScheduledListeningDecorator(delegate);
    }

    @GwtIncompatible
    static <T> T invokeAnyImpl(ListeningExecutorService executorService, Collection<? extends Callable<T>> tasks, boolean timed, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutionException ee;
        ArrayList<ListenableFuture<T>> futures;
        block15: {
            Preconditions.checkNotNull(executorService);
            Preconditions.checkNotNull(unit);
            int ntasks = tasks.size();
            Preconditions.checkArgument(ntasks > 0);
            futures = Lists.newArrayListWithCapacity(ntasks);
            LinkedBlockingQueue<Future<T>> futureQueue = Queues.newLinkedBlockingQueue();
            long timeoutNanos = unit.toNanos(timeout);
            ee = null;
            long lastTime = timed ? System.nanoTime() : 0L;
            Iterator<Callable<T>> it = tasks.iterator();
            futures.add(MoreExecutors.submitAndAddQueueListener(executorService, it.next(), futureQueue));
            --ntasks;
            int active = 1;
            while (true) {
                Object now22;
                Future f;
                if ((f = (Future)futureQueue.poll()) == null) {
                    if (ntasks > 0) {
                        --ntasks;
                        futures.add(MoreExecutors.submitAndAddQueueListener(executorService, it.next(), futureQueue));
                        ++active;
                    } else {
                        if (active == 0) break;
                        if (timed) {
                            f = (Future)futureQueue.poll(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (f == null) {
                                throw new TimeoutException();
                            }
                            long now22 = System.nanoTime();
                            timeoutNanos -= now22 - lastTime;
                            lastTime = now22;
                        } else {
                            f = (Future)futureQueue.take();
                        }
                    }
                }
                if (f == null) continue;
                --active;
                try {
                    now22 = f.get();
                } catch (ExecutionException eex) {
                    ee = eex;
                    continue;
                } catch (RuntimeException rex) {
                    ee = new ExecutionException(rex);
                    continue;
                }
                return (T)now22;
                break;
            }
            if (ee != null) break block15;
            ee = new ExecutionException(null);
        }
        throw ee;
        finally {
            for (Future future : futures) {
                future.cancel(true);
            }
        }
    }

    @GwtIncompatible
    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, final BlockingQueue<Future<T>> queue) {
        Future future = executorService.submit((Callable)task);
        future.addListener(new Runnable((ListenableFuture)future){
            final /* synthetic */ ListenableFuture val$future;
            {
                this.val$future = listenableFuture;
            }

            @Override
            public void run() {
                queue.add(this.val$future);
            }
        }, MoreExecutors.directExecutor());
        return future;
    }

    @Beta
    @GwtIncompatible
    public static ThreadFactory platformThreadFactory() {
        if (!MoreExecutors.isAppEngine()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory)Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (InvocationTargetException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    @GwtIncompatible
    private static boolean isAppEngine() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            return Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    @GwtIncompatible
    static Thread newThread(String name, Runnable runnable) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(runnable);
        Thread result = MoreExecutors.platformThreadFactory().newThread(runnable);
        try {
            result.setName(name);
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return result;
    }

    @GwtIncompatible
    static Executor renamingDecorator(final Executor executor, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(nameSupplier);
        if (MoreExecutors.isAppEngine()) {
            return executor;
        }
        return new Executor(){

            @Override
            public void execute(Runnable command) {
                executor.execute(Callables.threadRenaming(command, (Supplier<String>)nameSupplier));
            }
        };
    }

    @GwtIncompatible
    static ExecutorService renamingDecorator(ExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        if (MoreExecutors.isAppEngine()) {
            return service;
        }
        return new WrappingExecutorService(service){

            @Override
            protected <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>)nameSupplier);
            }

            @Override
            protected Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>)nameSupplier);
            }
        };
    }

    @GwtIncompatible
    static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        if (MoreExecutors.isAppEngine()) {
            return service;
        }
        return new WrappingScheduledExecutorService(service){

            @Override
            protected <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>)nameSupplier);
            }

            @Override
            protected Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>)nameSupplier);
            }
        };
    }

    @Beta
    @CanIgnoreReturnValue
    @GwtIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        long halfTimeoutNanos = unit.toNanos(timeout) / 2L;
        service.shutdown();
        try {
            if (!service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS)) {
                service.shutdownNow();
                service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        return service.isTerminated();
    }

    static Executor rejectionPropagatingExecutor(final Executor delegate, final AbstractFuture<?> future) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(future);
        if (delegate == MoreExecutors.directExecutor()) {
            return delegate;
        }
        return new Executor(){
            boolean thrownFromDelegate = true;

            @Override
            public void execute(final Runnable command) {
                block2: {
                    try {
                        delegate.execute(new Runnable(){

                            @Override
                            public void run() {
                                thrownFromDelegate = false;
                                command.run();
                            }
                        });
                    } catch (RejectedExecutionException e) {
                        if (!this.thrownFromDelegate) break block2;
                        future.setException(e);
                    }
                }
            }
        };
    }

    @GwtIncompatible
    private static final class ScheduledListeningDecorator
    extends ListeningDecorator
    implements ListeningScheduledExecutorService {
        final ScheduledExecutorService delegate;

        ScheduledListeningDecorator(ScheduledExecutorService delegate) {
            super(delegate);
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        public ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<Object> task = TrustedListenableFutureTask.create(command, null);
            ScheduledFuture<?> scheduled = this.delegate.schedule(task, delay, unit);
            return new ListenableScheduledTask<Object>(task, scheduled);
        }

        @Override
        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(callable);
            ScheduledFuture<?> scheduled = this.delegate.schedule(task, delay, unit);
            return new ListenableScheduledTask<V>(task, scheduled);
        }

        @Override
        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            ScheduledFuture<?> scheduled = this.delegate.scheduleAtFixedRate(task, initialDelay, period, unit);
            return new ListenableScheduledTask<Void>(task, scheduled);
        }

        @Override
        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            ScheduledFuture<?> scheduled = this.delegate.scheduleWithFixedDelay(task, initialDelay, delay, unit);
            return new ListenableScheduledTask<Void>(task, scheduled);
        }

        @GwtIncompatible
        private static final class NeverSuccessfulListenableFutureTask
        extends AbstractFuture.TrustedFuture<Void>
        implements Runnable {
            private final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable delegate) {
                this.delegate = Preconditions.checkNotNull(delegate);
            }

            @Override
            public void run() {
                try {
                    this.delegate.run();
                } catch (Throwable t) {
                    this.setException(t);
                    throw Throwables.propagate(t);
                }
            }
        }

        private static final class ListenableScheduledTask<V>
        extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V>
        implements ListenableScheduledFuture<V> {
            private final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableDelegate, ScheduledFuture<?> scheduledDelegate) {
                super(listenableDelegate);
                this.scheduledDelegate = scheduledDelegate;
            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean cancelled = super.cancel(mayInterruptIfRunning);
                if (cancelled) {
                    this.scheduledDelegate.cancel(mayInterruptIfRunning);
                }
                return cancelled;
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return this.scheduledDelegate.getDelay(unit);
            }

            @Override
            public int compareTo(Delayed other) {
                return this.scheduledDelegate.compareTo(other);
            }
        }
    }

    @GwtIncompatible
    private static class ListeningDecorator
    extends AbstractListeningExecutorService {
        private final ExecutorService delegate;

        ListeningDecorator(ExecutorService delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        @Override
        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override
        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override
        public final void shutdown() {
            this.delegate.shutdown();
        }

        @Override
        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        @Override
        public final void execute(Runnable command) {
            this.delegate.execute(command);
        }
    }

    @GwtIncompatible
    private static final class DirectExecutorService
    extends AbstractListeningExecutorService {
        private final Object lock = new Object();
        @GuardedBy(value="lock")
        private int runningTasks = 0;
        @GuardedBy(value="lock")
        private boolean shutdown = false;

        private DirectExecutorService() {
        }

        @Override
        public void execute(Runnable command) {
            this.startTask();
            try {
                command.run();
            } finally {
                this.endTask();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isShutdown() {
            Object object = this.lock;
            synchronized (object) {
                return this.shutdown;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void shutdown() {
            Object object = this.lock;
            synchronized (object) {
                this.shutdown = true;
                if (this.runningTasks == 0) {
                    this.lock.notifyAll();
                }
            }
        }

        @Override
        public List<Runnable> shutdownNow() {
            this.shutdown();
            return Collections.emptyList();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isTerminated() {
            Object object = this.lock;
            synchronized (object) {
                return this.shutdown && this.runningTasks == 0;
            }
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            long nanos = unit.toNanos(timeout);
            Object object = this.lock;
            synchronized (object) {
                while (true) {
                    if (this.shutdown && this.runningTasks == 0) {
                        return true;
                    }
                    if (nanos <= 0L) {
                        return false;
                    }
                    long now = System.nanoTime();
                    TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                    nanos -= System.nanoTime() - now;
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void startTask() {
            Object object = this.lock;
            synchronized (object) {
                if (this.shutdown) {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
                ++this.runningTasks;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void endTask() {
            Object object = this.lock;
            synchronized (object) {
                int numRunning = --this.runningTasks;
                if (numRunning == 0) {
                    this.lock.notifyAll();
                }
            }
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    static class Application {
        Application() {
        }

        final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ExecutorService service = Executors.unconfigurableExecutorService(executor);
            this.addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
            return this.getExitingExecutorService(executor, 120L, TimeUnit.SECONDS);
        }

        final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ScheduledExecutorService service = Executors.unconfigurableScheduledExecutorService(executor);
            this.addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
            return this.getExitingScheduledExecutorService(executor, 120L, TimeUnit.SECONDS);
        }

        final void addDelayedShutdownHook(final ExecutorService service, final long terminationTimeout, final TimeUnit timeUnit) {
            Preconditions.checkNotNull(service);
            Preconditions.checkNotNull(timeUnit);
            this.addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + service, new Runnable(){

                @Override
                public void run() {
                    try {
                        service.shutdown();
                        service.awaitTermination(terminationTimeout, timeUnit);
                    } catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                }
            }));
        }

        @VisibleForTesting
        void addShutdownHook(Thread hook) {
            Runtime.getRuntime().addShutdownHook(hook);
        }
    }
}

