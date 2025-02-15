/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface StringList
extends Iterable<String> {
    public boolean add(String var1);

    public String get(int var1);

    public int size();

    public String[] toStringArray();

    public String[] toStringArray(int var1, int var2);
}

