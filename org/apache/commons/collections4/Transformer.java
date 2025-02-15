/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

@FunctionalInterface
public interface Transformer<I, O> {
    public O transform(I var1);
}

