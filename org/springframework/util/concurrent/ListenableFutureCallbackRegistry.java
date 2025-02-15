/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

public class ListenableFutureCallbackRegistry<T> {
    private final Queue<SuccessCallback<? super T>> successCallbacks = new ArrayDeque<SuccessCallback<? super T>>(1);
    private final Queue<FailureCallback> failureCallbacks = new ArrayDeque<FailureCallback>(1);
    private State state = State.NEW;
    @Nullable
    private Object result;
    private final Object mutex = new Object();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addCallback(ListenableFutureCallback<? super T> callback) {
        Assert.notNull(callback, "'callback' must not be null");
        Object object = this.mutex;
        synchronized (object) {
            switch (this.state) {
                case NEW: {
                    this.successCallbacks.add(callback);
                    this.failureCallbacks.add(callback);
                    break;
                }
                case SUCCESS: {
                    this.notifySuccess(callback);
                    break;
                }
                case FAILURE: {
                    this.notifyFailure(callback);
                }
            }
        }
    }

    private void notifySuccess(SuccessCallback<? super T> callback) {
        try {
            callback.onSuccess(this.result);
        } catch (Throwable throwable) {
            // empty catch block
        }
    }

    private void notifyFailure(FailureCallback callback) {
        Assert.state(this.result instanceof Throwable, "No Throwable result for failure state");
        try {
            callback.onFailure((Throwable)this.result);
        } catch (Throwable throwable) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addSuccessCallback(SuccessCallback<? super T> callback) {
        Assert.notNull(callback, "'callback' must not be null");
        Object object = this.mutex;
        synchronized (object) {
            switch (this.state) {
                case NEW: {
                    this.successCallbacks.add(callback);
                    break;
                }
                case SUCCESS: {
                    this.notifySuccess(callback);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addFailureCallback(FailureCallback callback) {
        Assert.notNull((Object)callback, "'callback' must not be null");
        Object object = this.mutex;
        synchronized (object) {
            switch (this.state) {
                case NEW: {
                    this.failureCallbacks.add(callback);
                    break;
                }
                case FAILURE: {
                    this.notifyFailure(callback);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void success(@Nullable T result) {
        Object object = this.mutex;
        synchronized (object) {
            SuccessCallback<? super T> callback;
            this.state = State.SUCCESS;
            this.result = result;
            while ((callback = this.successCallbacks.poll()) != null) {
                this.notifySuccess(callback);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void failure(Throwable ex) {
        Object object = this.mutex;
        synchronized (object) {
            FailureCallback callback;
            this.state = State.FAILURE;
            this.result = ex;
            while ((callback = this.failureCallbacks.poll()) != null) {
                this.notifyFailure(callback);
            }
        }
    }

    private static enum State {
        NEW,
        SUCCESS,
        FAILURE;

    }
}

