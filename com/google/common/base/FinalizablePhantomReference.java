/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.FinalizableReference;
import com.google.common.base.FinalizableReferenceQueue;
import java.lang.ref.PhantomReference;

@GwtIncompatible
public abstract class FinalizablePhantomReference<T>
extends PhantomReference<T>
implements FinalizableReference {
    protected FinalizablePhantomReference(T referent, FinalizableReferenceQueue queue) {
        super(referent, queue.queue);
        queue.cleanUp();
    }
}

