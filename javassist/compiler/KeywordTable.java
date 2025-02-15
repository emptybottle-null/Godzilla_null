/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import java.util.HashMap;

public final class KeywordTable
extends HashMap<String, Integer> {
    private static final long serialVersionUID = 1L;

    public int lookup(String name) {
        return this.containsKey(name) ? (Integer)this.get(name) : -1;
    }

    public void append(String name, int t) {
        this.put(name, t);
    }
}

