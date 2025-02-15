/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.Objects;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;

public class ConstantInitializer<T>
implements ConcurrentInitializer<T> {
    private static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
    private final T object;

    public ConstantInitializer(T obj) {
        this.object = obj;
    }

    public final T getObject() {
        return this.object;
    }

    @Override
    public T get() throws ConcurrentException {
        return this.getObject();
    }

    public int hashCode() {
        return this.getObject() != null ? this.getObject().hashCode() : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstantInitializer)) {
            return false;
        }
        ConstantInitializer c = (ConstantInitializer)obj;
        return Objects.equals(this.getObject(), c.getObject());
    }

    public String toString() {
        return String.format(FMT_TO_STRING, System.identityHashCode(this), String.valueOf(this.getObject()));
    }
}

