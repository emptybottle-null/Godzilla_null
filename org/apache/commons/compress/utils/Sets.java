/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.util.Collections;
import java.util.HashSet;

public class Sets {
    private Sets() {
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E ... elements) {
        HashSet set = new HashSet(elements.length);
        Collections.addAll(set, elements);
        return set;
    }
}

