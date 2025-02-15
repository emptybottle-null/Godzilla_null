/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.AbstractBagDecorator;

public final class CollectionBag<E>
extends AbstractBagDecorator<E> {
    private static final long serialVersionUID = -2560033712679053143L;

    public static <E> Bag<E> collectionBag(Bag<E> bag) {
        return new CollectionBag<E>(bag);
    }

    public CollectionBag(Bag<E> bag) {
        super(bag);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setCollection((Collection)in.readObject());
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        Iterator<?> e = coll.iterator();
        while (e.hasNext()) {
            if (this.contains(e.next())) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean add(E object) {
        return this.add(object, 1);
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        boolean changed = false;
        Iterator<E> i = coll.iterator();
        while (i.hasNext()) {
            boolean added = this.add(i.next(), 1);
            changed = changed || added;
        }
        return changed;
    }

    @Override
    public boolean remove(Object object) {
        return this.remove(object, 1);
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        if (coll != null) {
            boolean result = false;
            for (Object obj : coll) {
                boolean changed = this.remove(obj, this.getCount(obj));
                result = result || changed;
            }
            return result;
        }
        return this.decorated().removeAll(null);
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        if (coll != null) {
            boolean modified = false;
            Iterator e = this.iterator();
            while (e.hasNext()) {
                if (coll.contains(e.next())) continue;
                e.remove();
                modified = true;
            }
            return modified;
        }
        return this.decorated().retainAll(null);
    }

    @Override
    public boolean add(E object, int count) {
        this.decorated().add(object, count);
        return true;
    }
}

