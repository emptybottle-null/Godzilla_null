/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Comparator;
import org.apache.commons.collections4.Bag;

public interface SortedBag<E>
extends Bag<E> {
    public Comparator<? super E> comparator();

    public E first();

    public E last();
}

