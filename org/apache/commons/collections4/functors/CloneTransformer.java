/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.PrototypeFactory;

public class CloneTransformer<T>
implements Transformer<T, T> {
    public static final Transformer INSTANCE = new CloneTransformer();

    public static <T> Transformer<T, T> cloneTransformer() {
        return INSTANCE;
    }

    private CloneTransformer() {
    }

    @Override
    public T transform(T input) {
        if (input == null) {
            return null;
        }
        return PrototypeFactory.prototypeFactory(input).create();
    }
}

