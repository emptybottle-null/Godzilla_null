/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.multiset.AbstractMapMultiSet;

public class HashMultiSet<E>
extends AbstractMapMultiSet<E>
implements Serializable {
    private static final long serialVersionUID = 20150610L;

    public HashMultiSet() {
        super(new HashMap());
    }

    public HashMultiSet(Collection<? extends E> coll) {
        this();
        this.addAll(coll);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        super.doWriteObject(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setMap(new HashMap());
        super.doReadObject(in);
    }
}

