/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.beans;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FixedKeySet
extends AbstractSet {
    private Set set;
    private int size;

    public FixedKeySet(String[] keys) {
        this.size = keys.length;
        this.set = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(keys)));
    }

    public Iterator iterator() {
        return this.set.iterator();
    }

    public int size() {
        return this.size;
    }
}

