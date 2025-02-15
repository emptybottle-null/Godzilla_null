/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.ref.WeakReference;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WeakCacheKey<T>
extends WeakReference<T> {
    private final int hash;

    public WeakCacheKey(T referent) {
        super(referent);
        this.hash = referent.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WeakCacheKey)) {
            return false;
        }
        Object ours = this.get();
        Object theirs = ((WeakCacheKey)obj).get();
        return ours != null && theirs != null && ours.equals(theirs);
    }

    public int hashCode() {
        return this.hash;
    }

    public String toString() {
        Object t = this.get();
        return t == null ? "Clean WeakIdentityKey, hash: " + this.hash : t.toString();
    }
}

