/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import java.util.concurrent.ExecutionException;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.FutureAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

public abstract class ListenableFutureAdapter<T, S>
extends FutureAdapter<T, S>
implements ListenableFuture<T> {
    protected ListenableFutureAdapter(ListenableFuture<S> adaptee) {
        super(adaptee);
    }

    @Override
    public void addCallback(ListenableFutureCallback<? super T> callback) {
        this.addCallback(callback, callback);
    }

    @Override
    public void addCallback(final SuccessCallback<? super T> successCallback, final FailureCallback failureCallback) {
        ListenableFuture listenableAdaptee = (ListenableFuture)this.getAdaptee();
        listenableAdaptee.addCallback(new ListenableFutureCallback<S>(){

            @Override
            public void onSuccess(@Nullable S result) {
                Object adapted = null;
                if (result != null) {
                    try {
                        adapted = ListenableFutureAdapter.this.adaptInternal(result);
                    } catch (ExecutionException ex) {
                        Throwable cause = ex.getCause();
                        this.onFailure(cause != null ? cause : ex);
                        return;
                    } catch (Throwable ex) {
                        this.onFailure(ex);
                        return;
                    }
                }
                successCallback.onSuccess(adapted);
            }

            @Override
            public void onFailure(Throwable ex) {
                failureCallback.onFailure(ex);
            }
        });
    }
}

