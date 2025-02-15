/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.compress.utils.Iterators;

public class Lists {
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> iterator) {
        ArrayList<E> list = Lists.newArrayList();
        Iterators.addAll(list, iterator);
        return list;
    }

    private Lists() {
    }
}

