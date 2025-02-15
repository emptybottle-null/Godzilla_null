/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

@GwtCompatible(serializable=true, emulated=true)
abstract class ImmutableAsList<E>
extends ImmutableList<E> {
    ImmutableAsList() {
    }

    abstract ImmutableCollection<E> delegateCollection();

    @Override
    public boolean contains(Object target) {
        return this.delegateCollection().contains(target);
    }

    @Override
    public int size() {
        return this.delegateCollection().size();
    }

    @Override
    public boolean isEmpty() {
        return this.delegateCollection().isEmpty();
    }

    @Override
    boolean isPartialView() {
        return this.delegateCollection().isPartialView();
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override
    @GwtIncompatible
    Object writeReplace() {
        return new SerializedForm(this.delegateCollection());
    }

    @GwtIncompatible
    static class SerializedForm
    implements Serializable {
        final ImmutableCollection<?> collection;
        private static final long serialVersionUID = 0L;

        SerializedForm(ImmutableCollection<?> collection) {
            this.collection = collection;
        }

        Object readResolve() {
            return this.collection.asList();
        }
    }
}

