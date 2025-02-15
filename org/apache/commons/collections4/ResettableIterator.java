/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Iterator;

public interface ResettableIterator<E>
extends Iterator<E> {
    public void reset();
}

