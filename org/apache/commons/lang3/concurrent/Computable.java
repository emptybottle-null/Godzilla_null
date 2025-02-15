/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

public interface Computable<I, O> {
    public O compute(I var1) throws InterruptedException;
}

