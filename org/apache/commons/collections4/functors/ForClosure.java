/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.functors.NOPClosure;

public class ForClosure<E>
implements Closure<E> {
    private final int iCount;
    private final Closure<? super E> iClosure;

    public static <E> Closure<E> forClosure(int count, Closure<? super E> closure) {
        if (count <= 0 || closure == null) {
            return NOPClosure.nopClosure();
        }
        if (count == 1) {
            return closure;
        }
        return new ForClosure<E>(count, closure);
    }

    public ForClosure(int count, Closure<? super E> closure) {
        this.iCount = count;
        this.iClosure = closure;
    }

    @Override
    public void execute(E input) {
        for (int i = 0; i < this.iCount; ++i) {
            this.iClosure.execute(input);
        }
    }

    public Closure<? super E> getClosure() {
        return this.iClosure;
    }

    public int getCount() {
        return this.iCount;
    }
}

