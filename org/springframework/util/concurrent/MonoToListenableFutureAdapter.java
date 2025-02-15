/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Mono
 */
package org.springframework.util.concurrent;

import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import reactor.core.publisher.Mono;

public class MonoToListenableFutureAdapter<T>
extends CompletableToListenableFutureAdapter<T> {
    public MonoToListenableFutureAdapter(Mono<T> mono) {
        super(mono.toFuture());
    }
}

