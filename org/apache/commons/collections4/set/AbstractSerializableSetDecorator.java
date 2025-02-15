/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;
import org.apache.commons.collections4.set.AbstractSetDecorator;

public abstract class AbstractSerializableSetDecorator<E>
extends AbstractSetDecorator<E> {
    private static final long serialVersionUID = 1229469966212206107L;

    protected AbstractSerializableSetDecorator(Set<E> set) {
        super(set);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setCollection((Collection)in.readObject());
    }
}

