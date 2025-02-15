/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
public abstract class AbstractIdleService
implements Service {
    private final Supplier<String> threadNameSupplier = new ThreadNameSupplier();
    private final Service delegate = new DelegateService();

    protected AbstractIdleService() {
    }

    protected abstract void startUp() throws Exception;

    protected abstract void shutDown() throws Exception;

    protected Executor executor() {
        return new Executor(){

            @Override
            public void execute(Runnable command) {
                MoreExecutors.newThread((String)AbstractIdleService.this.threadNameSupplier.get(), command).start();
            }
        };
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

    protected String serviceName() {
        return this.getClass().getSimpleName();
    }

    private final class DelegateService
    extends AbstractService {
        private DelegateService() {
        }

        @Override
        protected final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), (Supplier<String>)AbstractIdleService.this.threadNameSupplier).execute(new Runnable(){

                @Override
                public void run() {
                    try {
                        AbstractIdleService.this.startUp();
                        DelegateService.this.notifyStarted();
                    } catch (Throwable t) {
                        DelegateService.this.notifyFailed(t);
                    }
                }
            });
        }

        @Override
        protected final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), (Supplier<String>)AbstractIdleService.this.threadNameSupplier).execute(new Runnable(){

                @Override
                public void run() {
                    try {
                        AbstractIdleService.this.shutDown();
                        DelegateService.this.notifyStopped();
                    } catch (Throwable t) {
                        DelegateService.this.notifyFailed(t);
                    }
                }
            });
        }

        @Override
        public String toString() {
            return AbstractIdleService.this.toString();
        }
    }

    private final class ThreadNameSupplier
    implements Supplier<String> {
        private ThreadNameSupplier() {
        }

        @Override
        public String get() {
            return AbstractIdleService.this.serviceName() + " " + (Object)((Object)AbstractIdleService.this.state());
        }
    }
}

