/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import org.apache.commons.collections4.ResettableListIterator;
import org.apache.commons.collections4.iterators.AbstractEmptyIterator;

public class EmptyListIterator<E>
extends AbstractEmptyIterator<E>
implements ResettableListIterator<E> {
    public static final ResettableListIterator RESETTABLE_INSTANCE = new EmptyListIterator();
    public static final ListIterator INSTANCE = RESETTABLE_INSTANCE;

    public static <E> ResettableListIterator<E> resettableEmptyListIterator() {
        return RESETTABLE_INSTANCE;
    }

    public static <E> ListIterator<E> emptyListIterator() {
        return INSTANCE;
    }

    protected EmptyListIterator() {
    }
}

