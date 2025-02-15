/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.Arrays;
import org.mozilla.javascript.Wrapper;

class ResolvedOverload {
    final Class<?>[] types;
    final int index;

    ResolvedOverload(Object[] args, int index) {
        this.index = index;
        this.types = new Class[args.length];
        for (Object arg : args) {
            if (arg instanceof Wrapper) {
                arg = ((Wrapper)arg).unwrap();
            }
            this.types[i] = arg == null ? null : arg.getClass();
        }
    }

    boolean matches(Object[] args) {
        if (args.length != this.types.length) {
            return false;
        }
        int l = args.length;
        for (int i = 0; i < l; ++i) {
            Object arg = args[i];
            if (arg instanceof Wrapper) {
                arg = ((Wrapper)arg).unwrap();
            }
            if (!(arg == null ? this.types[i] != null : arg.getClass() != this.types[i])) continue;
            return false;
        }
        return true;
    }

    public boolean equals(Object other) {
        if (!(other instanceof ResolvedOverload)) {
            return false;
        }
        ResolvedOverload ovl = (ResolvedOverload)other;
        return Arrays.equals(this.types, ovl.types) && this.index == ovl.index;
    }

    public int hashCode() {
        return Arrays.hashCode(this.types);
    }
}

