/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.bag.AbstractMapBag;

public class TreeBag<E>
extends AbstractMapBag<E>
implements SortedBag<E>,
Serializable {
    private static final long serialVersionUID = -7740146511091606676L;

    public TreeBag() {
        super(new TreeMap());
    }

    public TreeBag(Comparator<? super E> comparator) {
        super(new TreeMap(comparator));
    }

    public TreeBag(Collection<? extends E> coll) {
        this();
        this.addAll(coll);
    }

    @Override
    public boolean add(E object) {
        if (this.comparator() == null && !(object instanceof Comparable)) {
            if (object == null) {
                throw new NullPointerException();
            }
            throw new IllegalArgumentException("Objects of type " + object.getClass() + " cannot be added to a naturally ordered TreeBag as it does not implement Comparable");
        }
        return super.add(object);
    }

    @Override
    public E first() {
        return (E)this.getMap().firstKey();
    }

    @Override
    public E last() {
        return (E)this.getMap().lastKey();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.getMap().comparator();
    }

    @Override
    protected SortedMap<E, AbstractMapBag.MutableInteger> getMap() {
        return (SortedMap)super.getMap();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.comparator());
        super.doWriteObject(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Comparator comp = (Comparator)in.readObject();
        super.doReadObject(new TreeMap(comp), in);
    }
}

