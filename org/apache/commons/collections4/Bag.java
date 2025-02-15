/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface Bag<E>
extends Collection<E> {
    public int getCount(Object var1);

    @Override
    public boolean add(E var1);

    public boolean add(E var1, int var2);

    @Override
    public boolean remove(Object var1);

    public boolean remove(Object var1, int var2);

    public Set<E> uniqueSet();

    @Override
    public int size();

    @Override
    public boolean containsAll(Collection<?> var1);

    @Override
    public boolean removeAll(Collection<?> var1);

    @Override
    public boolean retainAll(Collection<?> var1);

    @Override
    public Iterator<E> iterator();
}

