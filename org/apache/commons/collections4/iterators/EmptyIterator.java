/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractEmptyIterator;

public class EmptyIterator<E>
extends AbstractEmptyIterator<E>
implements ResettableIterator<E> {
    public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
    public static final Iterator INSTANCE = RESETTABLE_INSTANCE;

    public static <E> ResettableIterator<E> resettableEmptyIterator() {
        return RESETTABLE_INSTANCE;
    }

    public static <E> Iterator<E> emptyIterator() {
        return INSTANCE;
    }

    protected EmptyIterator() {
    }
}

