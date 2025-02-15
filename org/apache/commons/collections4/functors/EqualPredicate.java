/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NullPredicate;

public final class EqualPredicate<T>
implements Predicate<T>,
Serializable {
    private static final long serialVersionUID = 5633766978029907089L;
    private final T iValue;
    private final Equator<T> equator;

    public static <T> Predicate<T> equalPredicate(T object) {
        if (object == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate<T>(object);
    }

    public static <T> Predicate<T> equalPredicate(T object, Equator<T> equator) {
        if (object == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate<T>(object, equator);
    }

    public EqualPredicate(T object) {
        this(object, null);
    }

    public EqualPredicate(T object, Equator<T> equator) {
        this.iValue = object;
        this.equator = equator;
    }

    @Override
    public boolean evaluate(T object) {
        if (this.equator != null) {
            return this.equator.equate(this.iValue, object);
        }
        return this.iValue.equals(object);
    }

    public Object getValue() {
        return this.iValue;
    }
}

