/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Executor;

@GwtCompatible
enum DirectExecutor implements Executor
{
    INSTANCE;


    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }
}

