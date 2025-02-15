/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;

public final class NOPClosure<E>
implements Closure<E>,
Serializable {
    private static final long serialVersionUID = 3518477308466486130L;
    public static final Closure INSTANCE = new NOPClosure();

    public static <E> Closure<E> nopClosure() {
        return INSTANCE;
    }

    private NOPClosure() {
    }

    @Override
    public void execute(E input) {
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

