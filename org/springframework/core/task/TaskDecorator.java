/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task;

@FunctionalInterface
public interface TaskDecorator {
    public Runnable decorate(Runnable var1);
}

