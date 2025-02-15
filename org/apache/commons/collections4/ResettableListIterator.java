/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.ListIterator;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.ResettableIterator;

public interface ResettableListIterator<E>
extends ListIterator<E>,
ResettableIterator<E>,
OrderedIterator<E> {
}

