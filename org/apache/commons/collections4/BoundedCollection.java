/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;

public interface BoundedCollection<E>
extends Collection<E> {
    public boolean isFull();

    public int maxSize();
}

