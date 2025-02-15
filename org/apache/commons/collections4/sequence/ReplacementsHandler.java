/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.sequence;

import java.util.List;

@FunctionalInterface
public interface ReplacementsHandler<T> {
    public void handleReplacement(int var1, List<T> var2, List<T> var3);
}

