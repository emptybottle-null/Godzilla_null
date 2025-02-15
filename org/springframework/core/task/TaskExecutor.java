/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task;

import java.util.concurrent.Executor;

@FunctionalInterface
public interface TaskExecutor
extends Executor {
    @Override
    public void execute(Runnable var1);
}

