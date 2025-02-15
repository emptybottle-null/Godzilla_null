/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Iterator;

public interface OrderedIterator<E>
extends Iterator<E> {
    public boolean hasPrevious();

    public E previous();
}

